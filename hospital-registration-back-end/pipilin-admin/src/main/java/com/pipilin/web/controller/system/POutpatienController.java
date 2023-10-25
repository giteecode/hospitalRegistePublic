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
import com.pipilin.system.domain.POutpatien;
import com.pipilin.system.service.IPOutpatienService;
import com.pipilin.common.utils.poi.ExcelUtil;
import com.pipilin.common.core.page.TableDataInfo;

/**
 * 门诊信息Controller
 * 
 * @author pipilin
 * @date 2023-02-26
 */
@RestController
@RequestMapping("/system/outpatien")
public class POutpatienController extends BaseController
{
    @Autowired
    private IPOutpatienService pOutpatienService;

    /**
     * 查询门诊信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:outpatien:list')")
    @GetMapping("/list")
    public TableDataInfo list(POutpatien pOutpatien)
    {
        startPage();
        List<POutpatien> list = pOutpatienService.selectPOutpatienList(pOutpatien);
        return getDataTable(list);
    }


    /**
     * 获取门诊信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:outpatien:query')")
    @GetMapping(value = "/{roomId}")
    public AjaxResult getInfo(@PathVariable("roomId") Long roomId)
    {
        return success(pOutpatienService.selectPOutpatienByRoomId(roomId));
    }

    /**
     * 新增门诊信息
     */
    @PreAuthorize("@ss.hasPermi('system:outpatien:add')")
    @Log(title = "门诊信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody POutpatien pOutpatien)
    {
        pOutpatien.setCreateBy(getUserId().toString());
        return toAjax(pOutpatienService.insertPOutpatien(pOutpatien));
    }

    /**
     * 修改门诊信息
     */
    @PreAuthorize("@ss.hasPermi('system:outpatien:edit')")
    @Log(title = "门诊信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody POutpatien pOutpatien)
    {
        return toAjax(pOutpatienService.updatePOutpatien(pOutpatien));
    }

    /**
     * 删除门诊信息
     */
    @PreAuthorize("@ss.hasPermi('system:outpatien:remove')")
    @Log(title = "门诊信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{roomIds}")
    public AjaxResult remove(@PathVariable Long[] roomIds)
    {
        return toAjax(pOutpatienService.deletePOutpatienByRoomIds(roomIds));
    }
}
