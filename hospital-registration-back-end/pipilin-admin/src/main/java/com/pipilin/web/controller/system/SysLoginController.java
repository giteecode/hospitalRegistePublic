package com.pipilin.web.controller.system;

import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Set;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.pipilin.common.exception.base.BaseException;
import com.pipilin.common.utils.DateUtils;
import com.pipilin.system.mapper.SysUserMapper;
import com.pipilin.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.pipilin.common.constant.Constants;
import com.pipilin.common.core.domain.AjaxResult;
import com.pipilin.common.core.domain.entity.SysMenu;
import com.pipilin.common.core.domain.entity.SysUser;
import com.pipilin.common.core.domain.model.LoginBody;
import com.pipilin.common.utils.SecurityUtils;
import com.pipilin.framework.web.service.SysLoginService;
import com.pipilin.framework.web.service.SysPermissionService;
import com.pipilin.system.service.ISysMenuService;

/**
 * 登录验证
 * 
 * @author  2891517520@qq.com
 */
@RestController
public class SysLoginController
{
    @Autowired
    private SysLoginService loginService;

    @Autowired
    private ISysMenuService menuService;

    @Autowired
    private SysPermissionService permissionService;

    @Autowired
    private ISysUserService userService;

    @Autowired
    private SysUserMapper sysUserMapper;

    /**
     * 获取openId 长期授权字符串
     *
     * @param code 临时登陆凭证
     * @return openId 长期授权字符串
     */
    private String getOpenId(String code) {
        String url = "https://api.weixin.qq.com/sns/jscode2session";
        HashMap map = new HashMap();
        map.put("appid", "wx5c6870982b8ecbca");
        map.put("secret", "5c658de0df1ec3dc8468b0ad7cce4b2f");
        map.put("js_code", code);
        map.put("grant_type", "authorization_code");
        String response = HttpUtil.post(url, map);
        JSONObject jsonObject = JSONUtil.parseObj(response);
        String openId = jsonObject.getStr("openid");
        return openId;
    }

    /**
     * 登录方法
     * 
     * @param loginBody 登录信息
     * @return 结果
     */
    @PostMapping("/login")
    public AjaxResult login(@RequestBody LoginBody loginBody)
    {
        AjaxResult ajax = AjaxResult.success();
        // 生成令牌
        if ("MA".equals(loginBody.getLoginType())) {
            System.out.println("登录~");
            // 生成令牌
            String token = loginService.miniLogin(loginBody.getUsername(), loginBody.getPassword());
            SysUser SysUserUserInfo = userService.selectUserByUserName(loginBody.getUsername());
            ajax.put(Constants.TOKEN, token).put("userInfo", SysUserUserInfo);
        }else if("MA1".equals(loginBody.getLoginType())){
            String openId = getOpenId(loginBody.getCode());
            SysUser sysUser = sysUserMapper.selectUserByIdOpenId(openId);
            if(ObjectUtil.isNull(sysUser)){
                System.out.println("注册再登录~");
                SysUser sysUser1 = new SysUser();
                sysUser1.setOpenId(openId);
                sysUser1.setNickName(loginBody.getUsername());
                sysUser1.setUserName(openId);
                sysUser1.setAvatar(loginBody.getAvatarUrl());
                sysUser1.setSex(loginBody.getSex());
                sysUser1.setCreateTime(DateUtils.getNowDate());
                sysUser1.setPassword(SecurityUtils.encryptPassword(openId));
                boolean regFlag = userService.registerUser(sysUser1);
                if(regFlag){
                    sysUser = sysUserMapper.selectUserByIdOpenId(openId);
                    // 生成令牌
                    String token = loginService.miniLogin1(sysUser.getUserName(), sysUser.getOpenId());
                    SysUser SysUserUserInfo = userService.selectUserByUserName(sysUser.getUserName());
                    ajax.put(Constants.TOKEN, token).put("userInfo", SysUserUserInfo);
                }else {
                    throw new BaseException("登录失败请重试!");
                }
            }else {
                sysUser = sysUserMapper.selectUserByIdOpenId(openId);
                // 生成令牌
                String token = loginService.miniLogin1(sysUser.getUserName(), sysUser.getOpenId());
                SysUser SysUserUserInfo = userService.selectUserByUserName(sysUser.getUserName());
                ajax.put(Constants.TOKEN, token).put("userInfo", SysUserUserInfo);
            }
        } else {
            // 生成令牌
            String token = loginService.login(loginBody.getUsername(), loginBody.getPassword(), loginBody.getCode(),
                    loginBody.getUuid());
            SysUser SysUserUserInfo = userService.selectUserByUserName(loginBody.getUsername());
            ajax.put(Constants.TOKEN, token).put("userInfo", SysUserUserInfo);

            // 生成令牌
//            String token = loginService.login(loginBody.getUsername(), loginBody.getPassword(), loginBody.getCode(),
//                    loginBody.getUuid());
//            ajax.put(Constants.TOKEN, token);
        }
        return ajax;
    }

    /**
     * 获取用户信息
     * 
     * @return 用户信息
     */
    @GetMapping("getInfo")
    public AjaxResult getInfo()
    {
        SysUser user = SecurityUtils.getLoginUser().getUser();
        // 角色集合
        Set<String> roles = permissionService.getRolePermission(user);
        // 权限集合
        Set<String> permissions = permissionService.getMenuPermission(user);
        AjaxResult ajax = AjaxResult.success();
        ajax.put("user", user);
        ajax.put("roles", roles);
        ajax.put("permissions", permissions);
        return ajax;
    }

    /**
     * 获取路由信息
     * 
     * @return 路由信息
     */
    @GetMapping("getRouters")
    public AjaxResult getRouters()
    {
        Long userId = SecurityUtils.getUserId();
        List<SysMenu> menus = menuService.selectMenuTreeByUserId(userId);
        return AjaxResult.success(menuService.buildMenus(menus));
    }
}
