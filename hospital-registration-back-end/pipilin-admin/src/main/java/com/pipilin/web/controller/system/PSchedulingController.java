package com.pipilin.web.controller.system;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.pipilin.common.annotation.Log;
import com.pipilin.common.core.controller.BaseController;
import com.pipilin.common.core.domain.AjaxResult;
import com.pipilin.common.enums.BusinessType;
import com.pipilin.system.domain.PScheduling;
import com.pipilin.system.service.IPSchedulingService;
import com.pipilin.common.utils.poi.ExcelUtil;
import com.pipilin.common.core.page.TableDataInfo;

/**
 * 医生排班信息Controller
 * 
 * @author pipilin
 * @date 2023-03-09
 */
@RestController
@RequestMapping("/system/scheduling")
public class PSchedulingController extends BaseController
{
    @Autowired
    private IPSchedulingService pSchedulingService;

    /**
     * 查询医生排班信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:scheduling:list')")
    @GetMapping("/list")
    public TableDataInfo list(PScheduling pScheduling)
    {
        startPage();
        List<PScheduling> list = pSchedulingService.selectPSchedulingList(pScheduling);
        return getDataTable(list);
    }

    /**
     * 获取医生排班信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:scheduling:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(pSchedulingService.selectPSchedulingById(id));
    }

    /**
     * 新增医生排班信息
     */
    @PreAuthorize("@ss.hasPermi('system:scheduling:add')")
    @Log(title = "医生排班信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PScheduling pScheduling)
    {
        return toAjax(pSchedulingService.insertPScheduling(pScheduling));
    }

    /**
     * 修改医生排班信息
     */
    @PreAuthorize("@ss.hasPermi('system:scheduling:edit')")
    @Log(title = "医生排班信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PScheduling pScheduling)
    {
        return toAjax(pSchedulingService.updatePScheduling(pScheduling));
    }

    /**
     * 删除医生排班信息
     */
    @PreAuthorize("@ss.hasPermi('system:scheduling:remove')")
    @Log(title = "医生排班信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(pSchedulingService.deletePSchedulingByIds(ids));
    }
}
