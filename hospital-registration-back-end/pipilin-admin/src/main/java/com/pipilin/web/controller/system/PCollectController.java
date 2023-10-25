package com.pipilin.web.controller.system;

import com.pipilin.common.annotation.Log;
import com.pipilin.common.core.controller.BaseController;
import com.pipilin.common.core.domain.AjaxResult;
import com.pipilin.common.core.page.TableDataInfo;
import com.pipilin.common.enums.BusinessType;
import com.pipilin.system.domain.PCollect;
import com.pipilin.system.service.IPCollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 收藏信息Controller
 * 
 * @author pipilin
 * @date 2023-03-03
 */
@RestController
@RequestMapping("/system/collect")
public class PCollectController extends BaseController
{
    @Autowired
    private IPCollectService pCollectService;

    /**
     * 查询收藏信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:collect:list')")
    @GetMapping("/list")
    public TableDataInfo list(PCollect pCollect)
    {
        startPage();
        List<PCollect> list = pCollectService.selectPCollectList(pCollect);
        return getDataTable(list);
    }


    /**
     * 获取收藏信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:collect:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(pCollectService.selectPCollectById(id));
    }

    /**
     * 新增收藏信息
     */
    @PreAuthorize("@ss.hasPermi('system:collect:add')")
    @Log(title = "收藏信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PCollect pCollect)
    {
        return toAjax(pCollectService.insertPCollect(pCollect));
    }

    /**
     * 修改收藏信息
     */
    @PreAuthorize("@ss.hasPermi('system:collect:edit')")
    @Log(title = "收藏信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PCollect pCollect)
    {
        return toAjax(pCollectService.updatePCollect(pCollect));
    }

    /**
     * 删除收藏信息
     */
    @PreAuthorize("@ss.hasPermi('system:collect:remove')")
    @Log(title = "收藏信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(pCollectService.deletePCollectByIds(ids));
    }
}
