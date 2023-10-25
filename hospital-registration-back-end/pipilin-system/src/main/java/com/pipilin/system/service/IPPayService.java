package com.pipilin.system.service;

import java.util.List;
import com.pipilin.system.domain.PPay;

/**
 * 充值记录信息Service接口
 * 
 * @author pipilin
 * @date 2023-03-03
 */
public interface IPPayService 
{
    /**
     * 查询充值记录信息
     * 
     * @param id 充值记录信息主键
     * @return 充值记录信息
     */
    public PPay selectPPayById(Long id);

    /**
     * 查询充值记录信息列表
     * 
     * @param pPay 充值记录信息
     * @return 充值记录信息集合
     */
    public List<PPay> selectPPayList(PPay pPay);

    /**
     * 新增充值记录信息
     * 
     * @param pPay 充值记录信息
     * @return 结果
     */
    public int insertPPay(PPay pPay);

    /**
     * 修改充值记录信息
     * 
     * @param pPay 充值记录信息
     * @return 结果
     */
    public int updatePPay(PPay pPay);

    /**
     * 批量删除充值记录信息
     * 
     * @param ids 需要删除的充值记录信息主键集合
     * @return 结果
     */
    public int deletePPayByIds(Long[] ids);

    /**
     * 删除充值记录信息信息
     * 
     * @param id 充值记录信息主键
     * @return 结果
     */
    public int deletePPayById(Long id);
}
