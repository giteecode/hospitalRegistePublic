package com.pipilin.web.controller.system;

import com.pipilin.common.core.controller.BaseController;
import com.pipilin.common.core.domain.AjaxResult;
import com.pipilin.common.core.page.TableDataInfo;
import com.pipilin.system.domain.POutpatien;
import com.pipilin.system.service.IPOutpatienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 门诊信息Controller
 * 
 * @author pipilin
 * @date 2023-02-26
 */
@RestController
@RequestMapping("/mini/outpatien")
public class MiniPOutpatienController extends BaseController
{
    @Autowired
    private IPOutpatienService pOutpatienService;

    /**
     * 查询门诊信息列表
     */
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
    @GetMapping(value = "/{roomId}")
    public AjaxResult getInfo(@PathVariable("roomId") Long roomId)
    {
        return success(pOutpatienService.selectPOutpatienByRoomId(roomId));
    }
}
