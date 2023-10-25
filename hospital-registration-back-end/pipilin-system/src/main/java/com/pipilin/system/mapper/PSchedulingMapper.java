package com.pipilin.system.mapper;

import java.util.List;
import com.pipilin.system.domain.PScheduling;

/**
 * 医生排班信息Mapper接口
 * 
 * @author pipilin
 * @date 2023-03-09
 */
public interface PSchedulingMapper 
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
     * 删除医生排班信息
     * 
     * @param id 医生排班信息主键
     * @return 结果
     */
    public int deletePSchedulingById(Long id);

    /**
     * 批量删除医生排班信息
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePSchedulingByIds(Long[] ids);
}
