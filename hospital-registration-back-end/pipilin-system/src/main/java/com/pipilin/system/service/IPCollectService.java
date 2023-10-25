package com.pipilin.system.service;

import java.util.List;
import com.pipilin.system.domain.PCollect;

/**
 * 收藏信息Service接口
 * 
 * @author pipilin
 * @date 2023-03-03
 */
public interface IPCollectService 
{
    /**
     * 查询收藏信息
     * 
     * @param id 收藏信息主键
     * @return 收藏信息
     */
    public PCollect selectPCollectById(Long id);

    /**
     * 查询收藏信息列表
     * 
     * @param pCollect 收藏信息
     * @return 收藏信息集合
     */
    public List<PCollect> selectPCollectList(PCollect pCollect);

    /**
     * 新增收藏信息
     * 
     * @param pCollect 收藏信息
     * @return 结果
     */
    public int insertPCollect(PCollect pCollect);

    /**
     * 修改收藏信息
     * 
     * @param pCollect 收藏信息
     * @return 结果
     */
    public int updatePCollect(PCollect pCollect);

    /**
     * 批量删除收藏信息
     * 
     * @param ids 需要删除的收藏信息主键集合
     * @return 结果
     */
    public int deletePCollectByIds(Long[] ids);

    /**
     * 删除收藏信息信息
     * 
     * @param id 收藏信息主键
     * @return 结果
     */
    public int deletePCollectById(Long id);
}
