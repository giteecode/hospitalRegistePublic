package com.pipilin.system.service;

import java.util.List;
import com.pipilin.system.domain.PRegistration;

/**
 * 预约挂号记录Service接口
 * 
 * @author pipilin
 * @date 2023-02-27
 */
public interface IPRegistrationService 
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
     * 批量删除预约挂号记录
     * 
     * @param ids 需要删除的预约挂号记录主键集合
     * @return 结果
     */
    public int deletePRegistrationByIds(Long[] ids);

    /**
     * 删除预约挂号记录信息
     * 
     * @param id 预约挂号记录主键
     * @return 结果
     */
    public int deletePRegistrationById(Long id);
}
