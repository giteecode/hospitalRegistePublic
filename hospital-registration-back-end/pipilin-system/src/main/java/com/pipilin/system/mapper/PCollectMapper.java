package com.pipilin.system.mapper;

import java.util.List;
import com.pipilin.system.domain.PCollect;

/**
 * 收藏信息Mapper接口
 * 
 * @author pipilin
 * @date 2023-03-03
 */
public interface PCollectMapper 
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
     * 删除收藏信息
     * 
     * @param id 收藏信息主键
     * @return 结果
     */
    public int deletePCollectById(Long id);

    /**
     * 批量删除收藏信息
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePCollectByIds(Long[] ids);
}
