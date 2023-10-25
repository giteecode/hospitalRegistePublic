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
import com.pipilin.system.domain.PSection;
import com.pipilin.system.service.IPSectionService;
import com.pipilin.common.utils.poi.ExcelUtil;
import com.pipilin.common.core.page.TableDataInfo;

/**
 * 科室信息Controller
 * 
 * @author pipilin
 * @date 2023-02-26
 */
@RestController
@RequestMapping("/system/section")
public class PSectionController extends BaseController
{
    @Autowired
    private IPSectionService pSectionService;

    /**
     * 查询科室信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:section:list')")
    @GetMapping("/list")
    public TableDataInfo list(PSection pSection)
    {
        startPage();
        List<PSection> list = pSectionService.selectPSectionList(pSection);
        return getDataTable(list);
    }

    /**
     * 获取科室信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:section:query')")
    @GetMapping(value = "/{deptId}")
    public AjaxResult getInfo(@PathVariable("deptId") Long deptId)
    {
        return success(pSectionService.selectPSectionByDeptId(deptId));
    }

    /**
     * 新增科室信息
     */
    @PreAuthorize("@ss.hasPermi('system:section:add')")
    @Log(title = "科室信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PSection pSection)
    {
        pSection.setCreateBy(getUserId().toString());
        return toAjax(pSectionService.insertPSection(pSection));
    }

    /**
     * 修改科室信息
     */
    @PreAuthorize("@ss.hasPermi('system:section:edit')")
    @Log(title = "科室信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PSection pSection)
    {
        return toAjax(pSectionService.updatePSection(pSection));
    }

    /**
     * 删除科室信息
     */
    @PreAuthorize("@ss.hasPermi('system:section:remove')")
    @Log(title = "科室信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{deptIds}")
    public AjaxResult remove(@PathVariable Long[] deptIds)
    {
        return toAjax(pSectionService.deletePSectionByDeptIds(deptIds));
    }
}
