package com.pipilin.framework.web.service;

import cn.hutool.core.util.ObjectUtil;
import com.pipilin.framework.manager.AsyncManager;
import com.pipilin.framework.manager.factory.AsyncFactory;
import com.pipilin.system.domain.SysUserRole;
import com.pipilin.system.mapper.SysUserRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.pipilin.common.constant.CacheConstants;
import com.pipilin.common.constant.Constants;
import com.pipilin.common.constant.UserConstants;
import com.pipilin.common.core.domain.entity.SysUser;
import com.pipilin.common.core.domain.model.RegisterBody;
import com.pipilin.common.core.redis.RedisCache;
import com.pipilin.common.exception.user.CaptchaException;
import com.pipilin.common.exception.user.CaptchaExpireException;
import com.pipilin.common.utils.MessageUtils;
import com.pipilin.common.utils.SecurityUtils;
import com.pipilin.common.utils.StringUtils;
import com.pipilin.system.service.ISysConfigService;
import com.pipilin.system.service.ISysUserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 注册校验方法
 * 
 * @author  2891517520@qq.com
 */
@Component
public class SysRegisterService
{
    @Autowired
    private ISysUserService userService;

    @Autowired
    private ISysConfigService configService;

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private SysUserRoleMapper userRoleMapper;

    /**
     * 注册
     */
    public String register(RegisterBody registerBody)
    {
        String msg = "", username = registerBody.getUsername(), password = registerBody.getPassword();
        SysUser sysUser = new SysUser();
        sysUser.setUserName(username);

        if(ObjectUtil.isNotNull(registerBody.getLoginType())){
            if(!"MA".equals(registerBody.getLoginType())){
                // 验证码开关
                boolean captchaEnabled = configService.selectCaptchaEnabled();
                if (captchaEnabled)
                {
                    validateCaptcha(username, registerBody.getCode(), registerBody.getUuid());
                }
            }
        }else {

            // 验证码开关
            boolean captchaEnabled = configService.selectCaptchaEnabled();
            if (captchaEnabled)
            {
                validateCaptcha(username, registerBody.getCode(), registerBody.getUuid());
            }
        }


        if (StringUtils.isEmpty(username))
        {
            msg = "用户名不能为空";
        }
        else if (StringUtils.isEmpty(password))
        {
            msg = "用户密码不能为空";
        }
        else if (username.length() < UserConstants.USERNAME_MIN_LENGTH
                || username.length() > UserConstants.USERNAME_MAX_LENGTH)
        {
            msg = "账户长度必须在2到20个字符之间";
        }
        else if (password.length() < UserConstants.PASSWORD_MIN_LENGTH
                || password.length() > UserConstants.PASSWORD_MAX_LENGTH)
        {
            msg = "密码长度必须在5到20个字符之间";
        }
        else if (UserConstants.NOT_UNIQUE.equals(userService.checkUserNameUnique(sysUser)))
        {
            msg = "保存用户'" + username + "'失败，注册账号已存在";
        }
        else
        {
            if(ObjectUtil.isNotNull(registerBody.getLoginType())){
                if("MA".equals(registerBody.getLoginType())){
                    sysUser.setUserName(username);
                    sysUser.setNickName(username);
                    sysUser.setPassword(SecurityUtils.encryptPassword(password));
                    // 随机用户头像
                    Random random = new Random();
                    int i = random.nextInt(Constants.AVATARS.length);
                    sysUser.setAvatar(Constants.AVATARS[i]);
                }else {
                    sysUser.setUserName(username);
                    sysUser.setNickName(username);
                    sysUser.setPassword(SecurityUtils.encryptPassword(registerBody.getPassword()));
                }
            }else {
                sysUser.setUserName(username);
                sysUser.setNickName(username);
                sysUser.setPassword(SecurityUtils.encryptPassword(registerBody.getPassword()));
            }
            boolean regFlag = userService.registerUser(sysUser);
            if (!regFlag)
            {
                msg = "注册失败,请联系系统管理人员";
            }
            else
            {
                // 根据用户名查询用户ID
                SysUser sysUser1 = userService.selectUserByUserName(username);
                // 新增用户角色
                Long[] roles = {2L};
                // 新增用户与角色管理
                List<SysUserRole> list = new ArrayList<SysUserRole>();
                for (Long roleId : roles)
                {
                    SysUserRole ur = new SysUserRole();
                    ur.setUserId(sysUser1.getUserId());
                    ur.setRoleId(roleId);
                    list.add(ur);
                }
                userRoleMapper.batchUserRole(list);
                AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.REGISTER, MessageUtils.message("user.register.success")));
            }
        }
        return msg;
    }

    /**
     * 校验验证码
     * 
     * @param username 用户名
     * @param code 验证码
     * @param uuid 唯一标识
     * @return 结果
     */
    public void validateCaptcha(String username, String code, String uuid)
    {
        String verifyKey = CacheConstants.CAPTCHA_CODE_KEY + StringUtils.nvl(uuid, "");
        String captcha = redisCache.getCacheObject(verifyKey);
        redisCache.deleteObject(verifyKey);
        if (captcha == null)
        {
            throw new CaptchaExpireException();
        }
        if (!code.equalsIgnoreCase(captcha))
        {
            throw new CaptchaException();
        }
    }
}
