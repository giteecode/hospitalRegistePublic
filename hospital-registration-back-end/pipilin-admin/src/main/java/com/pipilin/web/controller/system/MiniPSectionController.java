package com.pipilin.web.controller.system;

import com.pipilin.common.core.controller.BaseController;
import com.pipilin.common.core.domain.AjaxResult;
import com.pipilin.common.core.page.TableDataInfo;
import com.pipilin.system.domain.PSection;
import com.pipilin.system.service.IPSectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 科室信息Controller
 * 
 * @author pipilin
 * @date 2023-02-26
 */
@RestController
@RequestMapping("/mini/section")
public class MiniPSectionController extends BaseController
{
    @Autowired
    private IPSectionService pSectionService;

    /**
     * 查询科室信息列表
     */
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
    @GetMapping(value = "/{deptId}")
    public AjaxResult getInfo(@PathVariable("deptId") Long deptId)
    {
        return success(pSectionService.selectPSectionByDeptId(deptId));
    }
}
