package com.pipilin.system.mapper;

import java.util.List;
import com.pipilin.system.domain.PRegistration;
import com.pipilin.system.domain.vo.StatisticsDayVO;
import com.pipilin.system.domain.vo.StatisticsVO;
import com.pipilin.system.domain.vo.StatisticsWeekVO;

/**
 * 预约挂号记录Mapper接口
 * 
 * @author pipilin
 * @date 2023-02-27
 */
public interface PRegistrationMapper 
{
    /**
     * 查询预约挂号记录
     * 
     * @param id 预约挂号记录主键
     * @return 预约挂号记录
     */
    public PRegistration selectPRegistrationById(Long id);

    /**
     * 查询预约挂号记录列表
     * 
     * @param pRegistration 预约挂号记录
     * @return 预约挂号记录集合
     */
    public List<PRegistration> selectPRegistrationList(PRegistration pRegistration);


    /**
     * 查询预约挂号记录列表
     *
     * @return 预约挂号记录集合
     */
    public List<StatisticsVO> getStatisticsVO();

    /**
     * 查询预约挂号记录列表
     *
     * @return 预约挂号记录集合
     */
    public List<StatisticsWeekVO> getStatisticsWeekVO();

    /**
     * 查询预约挂号记录列表
     *
     * @return 预约挂号记录集合
     */
    public List<StatisticsDayVO> getStatisticsDayVO();


    /**
     * 新增预约挂号记录
     * 
     * @param pRegistration 预约挂号记录
     * @return 结果
     */
    public int insertPRegistration(PRegistration pRegistration);

    /**
     * 修改预约挂号记录
     * 
     * @param pRegistration 预约挂号记录
     * @return 结果
     */
    public int updatePRegistration(PRegistration pRegistration);

    /**
     * 删除预约挂号记录
     * 
     * @param id 预约挂号记录主键
     * @return 结果
     */
    public int deletePRegistrationById(Long id);

    /**
     * 批量删除预约挂号记录
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePRegistrationByIds(Long[] ids);
}
