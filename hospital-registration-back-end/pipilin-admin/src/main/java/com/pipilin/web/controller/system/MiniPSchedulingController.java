package com.pipilin.web.controller.system;

import com.pipilin.common.core.controller.BaseController;
import com.pipilin.common.core.domain.AjaxResult;
import com.pipilin.common.core.page.TableDataInfo;
import com.pipilin.system.domain.PCollect;
import com.pipilin.system.domain.PScheduling;
import com.pipilin.system.service.IPCollectService;
import com.pipilin.system.service.IPSchedulingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 医生排班信息Controller
 * 
 * @author pipilin
 * @date 2023-03-09
 */
@RestController
@RequestMapping("/system/scheduling/mini")
public class MiniPSchedulingController extends BaseController
{
    @Autowired
    private IPSchedulingService pSchedulingService;

    @Autowired
    private IPCollectService pCollectService;

    /**
     * 查询医生排班信息列表
     */
    @GetMapping("/list")
    public TableDataInfo list(PScheduling pScheduling)
    {
        startPage();
        List<PScheduling> list = pSchedulingService.selectPSchedulingList(pScheduling);
        list.forEach(item -> {
            PCollect pCollect1 = new PCollect();
            pCollect1.setUserId(getUserId());
            pCollect1.setDoctorId(item.getSysUser().getUserId());
            List<PCollect> pCollects = pCollectService.selectPCollectList(pCollect1);
            if(pCollects.size() > 0){
                item.setFlag(true);
            }else {
                item.setFlag(false);
            }
        });
        return getDataTable(list);
    }


    /**
     * 获取医生排班信息详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(pSchedulingService.selectPSchedulingById(id));
    }
}
