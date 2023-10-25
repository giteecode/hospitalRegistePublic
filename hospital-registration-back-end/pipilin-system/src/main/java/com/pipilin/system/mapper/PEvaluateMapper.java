package com.pipilin.system.mapper;

import java.util.List;
import com.pipilin.system.domain.PEvaluate;

/**
 * 评价信息Mapper接口
 * 
 * @author pipilin
 * @date 2023-03-03
 */
public interface PEvaluateMapper 
{
    /**
     * 查询评价信息
     * 
     * @param id 评价信息主键
     * @return 评价信息
     */
    public PEvaluate selectPEvaluateById(Long id);

    /**
     * 查询评价信息列表
     * 
     * @param pEvaluate 评价信息
     * @return 评价信息集合
     */
    public List<PEvaluate> selectPEvaluateList(PEvaluate pEvaluate);

    /**
     * 新增评价信息
     * 
     * @param pEvaluate 评价信息
     * @return 结果
     */
    public int insertPEvaluate(PEvaluate pEvaluate);

    /**
     * 修改评价信息
     * 
     * @param pEvaluate 评价信息
     * @return 结果
     */
    public int updatePEvaluate(PEvaluate pEvaluate);

    /**
     * 删除评价信息
     * 
     * @param id 评价信息主键
     * @return 结果
     */
    public int deletePEvaluateById(Long id);

    /**
     * 批量删除评价信息
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePEvaluateByIds(Long[] ids);
}
