package com.pipilin.web.controller.system;

import com.pipilin.common.constant.UserConstants;
import com.pipilin.common.core.controller.BaseController;
import com.pipilin.common.core.domain.AjaxResult;
import com.pipilin.common.core.domain.entity.SysUser;
import com.pipilin.common.core.page.TableDataInfo;
import com.pipilin.common.utils.SecurityUtils;
import com.pipilin.common.utils.StringUtils;
import com.pipilin.system.domain.PCollect;
import com.pipilin.system.service.IPCollectService;
import com.pipilin.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户信息
 * 
 * @author  2891517520@qq.com
 */
@RestController
@RequestMapping("/mini/user")
public class MiniUserController extends BaseController
{
    @Autowired
    private ISysUserService userService;

    @Autowired
    private IPCollectService pCollectService;

    /**
     * 获取用户列表
     */
    @GetMapping("/list")
    public TableDataInfo list(SysUser user)
    {
        startPage();
        List<SysUser> list = userService.selectUserList(user);
        PCollect pCollect = new PCollect();
        pCollect.setUserId(getUserId());
        List<PCollect> pCollects = pCollectService.selectPCollectList(pCollect);
        list.forEach(item1 -> {
            pCollects.forEach(item2 -> {
                if(item1.getUserId().toString().equals(item2.getDoctorId().toString())){
                    item1.setFlag(true);
                }
            });
        });
        return getDataTable(list);
    }

    /**
     * 通过用户名查询用户
     *
     * @param userName 用户名
     * @return 用户对象信息
     */
    @GetMapping("/getUserByUserName/{userName}")
    public AjaxResult getUserByUserName(@PathVariable String userName)
    {
        SysUser sysUser = userService.selectUserByUserName(userName);
        return AjaxResult.success().put("userInfo",sysUser);
    }


    @PutMapping("/edit")
    public AjaxResult edit(@Validated @RequestBody SysUser user)
    {
        if (UserConstants.NOT_UNIQUE.equals(userService.checkUserNameUnique(user)))
        {
            return error("修改用户'" + user.getUserName() + "'失败，登录账号已存在");
        }
        else if (StringUtils.isNotEmpty(user.getPhonenumber())
                && UserConstants.NOT_UNIQUE.equals(userService.checkPhoneUnique(user)))
        {
            return error("修改用户'" + user.getUserName() + "'失败，手机号码已存在");
        }
        else if (StringUtils.isNotEmpty(user.getEmail())
                && UserConstants.NOT_UNIQUE.equals(userService.checkEmailUnique(user)))
        {
            return error("修改用户'" + user.getUserName() + "'失败，邮箱账号已存在");
        }
        return toAjax(userService.updateUser(user));
    }

    @PutMapping("/resetPwdMini")
    public AjaxResult resetPwdMini(@RequestBody SysUser user)
    {
        user.setPassword(SecurityUtils.encryptPassword(user.getPassword()));
        return toAjax(userService.resetPwd(user));
    }
}
