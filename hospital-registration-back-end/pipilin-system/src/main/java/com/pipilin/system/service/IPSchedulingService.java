package com.pipilin.system.service;

import java.util.List;
import com.pipilin.system.domain.PScheduling;

/**
 * 医生排班信息Service接口
 * 
 * @author pipilin
 * @date 2023-03-09
 */
public interface IPSchedulingService 
{
    /**
     * 查询医生排班信息
     * 
     * @param id 医生排班信息主键
     * @return 医生排班信息
     */
    public PScheduling selectPSchedulingById(Long id);

    /**
     * 查询医生排班信息列表
     * 
     * @param pScheduling 医生排班信息
     * @return 医生排班信息集合
     */
    public List<PScheduling> selectPSchedulingList(PScheduling pScheduling);

    /**
     * 新增医生排班信息
     * 
     * @param pScheduling 医生排班信息
     * @return 结果
     */
    public int insertPScheduling(PScheduling pScheduling);

    /**
     * 修改医生排班信息
     * 
     * @param pScheduling 医生排班信息
     * @return 结果
     */
    public int updatePScheduling(PScheduling pScheduling);

    /**
     * 批量删除医生排班信息
     * 
     * @param ids 需要删除的医生排班信息主键集合
     * @return 结果
     */
    public int deletePSchedulingByIds(Long[] ids);

    /**
     * 删除医生排班信息信息
     * 
     * @param id 医生排班信息主键
     * @return 结果
     */
    public int deletePSchedulingById(Long id);
}
