package com.pipilin.web.controller.system;

import com.pipilin.common.annotation.Log;
import com.pipilin.common.core.controller.BaseController;
import com.pipilin.common.core.domain.AjaxResult;
import com.pipilin.common.core.domain.model.LoginUser;
import com.pipilin.common.core.page.TableDataInfo;
import com.pipilin.common.enums.BusinessType;
import com.pipilin.system.domain.PRegistration;
import com.pipilin.system.domain.vo.StatisticsDayVO;
import com.pipilin.system.domain.vo.StatisticsVO;
import com.pipilin.system.domain.vo.StatisticsWeekVO;
import com.pipilin.system.mapper.PRegistrationMapper;
import com.pipilin.system.service.IPRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 预约挂号记录Controller
 * 
 * @author pipilin
 * @date 2023-02-27
 */
@RestController
@RequestMapping("/system/registration")
public class PRegistrationController extends BaseController
{
    @Autowired
    private IPRegistrationService pRegistrationService;

    @Autowired
    private PRegistrationMapper pRegistrationMapper;

    /**
     * 查询预约挂号记录列表
     */
    @PreAuthorize("@ss.hasPermi('system:registration:list')")
    @GetMapping("/list")
    public TableDataInfo list(PRegistration pRegistration)
    {
        LoginUser loginUser = getLoginUser();
        if(!loginUser.getUser().isAdmin()){
            pRegistration.setDoctorId(loginUser.getUserId());
        }
        startPage();
        List<PRegistration> list = pRegistrationService.selectPRegistrationList(pRegistration);
        return getDataTable(list);
    }

    // 每月统计
    @GetMapping("/getStatistics")
    public TableDataInfo getStatistics()
    {
        List<StatisticsVO> statisticsVO = pRegistrationMapper.getStatisticsVO();
        return getDataTable(statisticsVO);
    }

    // 每周统计
    @GetMapping("/getStatisticsWeekVO")
    public TableDataInfo getStatisticsWeekVO()
    {
        List<StatisticsWeekVO> statisticsVO = pRegistrationMapper.getStatisticsWeekVO();
        return getDataTable(statisticsVO);
    }

    // 每天统计
    @GetMapping("/getStatisticsDayVO")
    public TableDataInfo getStatisticsDayVO()
    {
        List<StatisticsDayVO> statisticsVO = pRegistrationMapper.getStatisticsDayVO();
        return getDataTable(statisticsVO);
    }


    /**
     * 获取预约挂号记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:registration:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(pRegistrationService.selectPRegistrationById(id));
    }

    /**
     * 新增预约挂号记录
     */
    @PreAuthorize("@ss.hasPermi('system:registration:add')")
    @Log(title = "预约挂号记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PRegistration pRegistration)
    {
        return toAjax(pRegistrationService.insertPRegistration(pRegistration));
    }

    /**
     * 修改预约挂号记录
     */
    @PreAuthorize("@ss.hasPermi('system:registration:edit')")
    @Log(title = "预约挂号记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PRegistration pRegistration)
    {
        return toAjax(pRegistrationService.updatePRegistration(pRegistration));
    }

    /**
     * 删除预约挂号记录
     */
    @PreAuthorize("@ss.hasPermi('system:registration:remove')")
    @Log(title = "预约挂号记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(pRegistrationService.deletePRegistrationByIds(ids));
    }
}
