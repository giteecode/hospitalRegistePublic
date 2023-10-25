package com.pipilin.web.controller.system;

import com.pipilin.common.annotation.Log;
import com.pipilin.common.core.controller.BaseController;
import com.pipilin.common.core.domain.AjaxResult;
import com.pipilin.common.core.page.TableDataInfo;
import com.pipilin.common.enums.BusinessType;
import com.pipilin.system.domain.PCollect;
import com.pipilin.system.service.IPCollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 收藏信息Controller
 * 
 * @author pipilin
 * @date 2023-03-03
 */
@RestController
@RequestMapping("/system/collect/mini")
public class MiniPCollectController extends BaseController
{
    @Autowired
    private IPCollectService pCollectService;

    /**
     * 查询收藏信息列表
     */
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
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(pCollectService.selectPCollectById(id));
    }

    /**
     * 新增收藏信息
     */
    @Log(title = "收藏信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PCollect pCollect)
    {
        PCollect pCollect1 = new PCollect();
        pCollect1.setUserId(pCollect.getUserId());
        pCollect1.setDoctorId(pCollect.getDoctorId());
        List<PCollect> pCollects = pCollectService.selectPCollectList(pCollect1);
        if(pCollects.size() > 0){
            pCollectService.deletePCollectById(pCollects.get(0).getId());
            return AjaxResult.success().put("flag",false);
        }else {
            pCollectService.insertPCollect(pCollect);
            return AjaxResult.success().put("flag",true);
        }
    }

    /**
     * 删除收藏信息
     */
    @Log(title = "收藏信息", businessType = BusinessType.DELETE)
	@GetMapping("/remove/{id}")
    public AjaxResult remove(@PathVariable Long id)
    {
        return toAjax(pCollectService.deletePCollectById(id));
    }
}
