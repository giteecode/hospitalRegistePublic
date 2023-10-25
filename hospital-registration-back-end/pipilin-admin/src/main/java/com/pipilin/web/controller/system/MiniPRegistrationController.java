package com.pipilin.web.controller.system;

import com.pipilin.common.annotation.Log;
import com.pipilin.common.core.controller.BaseController;
import com.pipilin.common.core.domain.AjaxResult;
import com.pipilin.common.core.page.TableDataInfo;
import com.pipilin.common.enums.BusinessType;
import com.pipilin.system.domain.PRegistration;
import com.pipilin.system.service.IPRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 预约挂号记录Controller
 * 
 * @author pipilin
 * @date 2023-02-27
 */
@RestController
@RequestMapping("/system/registration/mini")
public class MiniPRegistrationController extends BaseController
{
    @Autowired
    private IPRegistrationService pRegistrationService;

    /**
     * 查询预约挂号记录列表
     */
    @GetMapping("/list")
    public TableDataInfo list(PRegistration pRegistration)
    {
        startPage();
        List<PRegistration> list = pRegistrationService.selectPRegistrationList(pRegistration);
        return getDataTable(list);
    }

    /**
     * 获取预约挂号记录详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(pRegistrationService.selectPRegistrationById(id));
    }

    /**
     * 新增预约挂号记录
     */
    @Log(title = "预约挂号记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PRegistration pRegistration)
    {
        return toAjax(pRegistrationService.insertPRegistration(pRegistration));
    }

    /**
     * 修改预约挂号记录
     */
    @Log(title = "预约挂号记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PRegistration pRegistration)
    {
        return toAjax(pRegistrationService.updatePRegistration(pRegistration));
    }

    /**
     * 删除预约挂号记录
     */
    @Log(title = "预约挂号记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/remove/{id}")
    public AjaxResult remove(@PathVariable Long id)
    {
        return toAjax(pRegistrationService.deletePRegistrationById(id));
    }
}
