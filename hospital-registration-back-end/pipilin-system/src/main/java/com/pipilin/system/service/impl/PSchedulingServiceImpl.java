package com.pipilin.system.service.impl;

import java.util.List;

import com.pipilin.common.exception.base.BaseException;
import com.pipilin.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pipilin.system.mapper.PSchedulingMapper;
import com.pipilin.system.domain.PScheduling;
import com.pipilin.system.service.IPSchedulingService;

/**
 * 医生排班信息Service业务层处理
 * 
 * @author pipilin
 * @date 2023-03-09
 */
@Service
public class PSchedulingServiceImpl implements IPSchedulingService 
{
    @Autowired
    private PSchedulingMapper pSchedulingMapper;

    /**
     * 查询医生排班信息
     * 
     * @param id 医生排班信息主键
     * @return 医生排班信息
     */
    @Override
    public PScheduling selectPSchedulingById(Long id)
    {
        return pSchedulingMapper.selectPSchedulingById(id);
    }

    /**
     * 查询医生排班信息列表
     * 
     * @param pScheduling 医生排班信息
     * @return 医生排班信息
     */
    @Override
    public List<PScheduling> selectPSchedulingList(PScheduling pScheduling)
    {
        return pSchedulingMapper.selectPSchedulingList(pScheduling);
    }

    /**
     * 新增医生排班信息
     * 
     * @param pScheduling 医生排班信息
     * @return 结果
     */
    @Override
    public int insertPScheduling(PScheduling pScheduling)
    {
        PScheduling pScheduling1 = new PScheduling();
        pScheduling1.setSchedulingId(pScheduling.getSchedulingId());
        List<PScheduling> pSchedulings = pSchedulingMapper.selectPSchedulingList(pScheduling1);
        if(pSchedulings.size() > 0){
            throw new BaseException("该医生已分配排班");
        }
        pScheduling.setCreateTime(DateUtils.getNowDate());
        return pSchedulingMapper.insertPScheduling(pScheduling);
    }

    /**
     * 修改医生排班信息
     * 
     * @param pScheduling 医生排班信息
     * @return 结果
     */
    @Override
    public int updatePScheduling(PScheduling pScheduling)
    {
        return pSchedulingMapper.updatePScheduling(pScheduling);
    }

    /**
     * 批量删除医生排班信息
     * 
     * @param ids 需要删除的医生排班信息主键
     * @return 结果
     */
    @Override
    public int deletePSchedulingByIds(Long[] ids)
    {
        return pSchedulingMapper.deletePSchedulingByIds(ids);
    }

    /**
     * 删除医生排班信息信息
     * 
     * @param id 医生排班信息主键
     * @return 结果
     */
    @Override
    public int deletePSchedulingById(Long id)
    {
        return pSchedulingMapper.deletePSchedulingById(id);
    }
}
