package com.pipilin.web.controller.system;

import com.pipilin.common.annotation.Log;
import com.pipilin.common.core.controller.BaseController;
import com.pipilin.common.core.domain.AjaxResult;
import com.pipilin.common.core.page.TableDataInfo;
import com.pipilin.common.enums.BusinessType;
import com.pipilin.system.domain.PEvaluate;
import com.pipilin.system.service.IPEvaluateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 评价信息Controller
 * 
 * @author pipilin
 * @date 2023-03-03
 */
@RestController
@RequestMapping("/system/evaluate/mini")
public class MiniPEvaluateController extends BaseController
{
    @Autowired
    private IPEvaluateService pEvaluateService;

    /**
     * 查询评价信息列表
     */
    @GetMapping("/list")
    public TableDataInfo list(PEvaluate pEvaluate)
    {
        startPage();
        List<PEvaluate> list = pEvaluateService.selectPEvaluateList(pEvaluate);
        return getDataTable(list);
    }


    /**
     * 获取评价信息详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(pEvaluateService.selectPEvaluateById(id));
    }

    /**
     * 新增评价信息
     */
    @Log(title = "评价信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PEvaluate pEvaluate)
    {
        return toAjax(pEvaluateService.insertPEvaluate(pEvaluate));
    }

    /**
     * 修改评价信息
     */
    @Log(title = "评价信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PEvaluate pEvaluate)
    {
        return toAjax(pEvaluateService.updatePEvaluate(pEvaluate));
    }

    /**
     * 删除评价信息
     */
    @Log(title = "评价信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(pEvaluateService.deletePEvaluateByIds(ids));
    }
}
