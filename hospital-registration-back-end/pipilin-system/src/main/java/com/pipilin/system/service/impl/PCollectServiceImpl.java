package com.pipilin.system.service.impl;

import java.util.List;
import com.pipilin.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pipilin.system.mapper.PCollectMapper;
import com.pipilin.system.domain.PCollect;
import com.pipilin.system.service.IPCollectService;

/**
 * 收藏信息Service业务层处理
 * 
 * @author pipilin
 * @date 2023-03-03
 */
@Service
public class PCollectServiceImpl implements IPCollectService 
{
    @Autowired
    private PCollectMapper pCollectMapper;

    /**
     * 查询收藏信息
     * 
     * @param id 收藏信息主键
     * @return 收藏信息
     */
    @Override
    public PCollect selectPCollectById(Long id)
    {
        return pCollectMapper.selectPCollectById(id);
    }

    /**
     * 查询收藏信息列表
     * 
     * @param pCollect 收藏信息
     * @return 收藏信息
     */
    @Override
    public List<PCollect> selectPCollectList(PCollect pCollect)
    {
        return pCollectMapper.selectPCollectList(pCollect);
    }

    /**
     * 新增收藏信息
     * 
     * @param pCollect 收藏信息
     * @return 结果
     */
    @Override
    public int insertPCollect(PCollect pCollect)
    {
        pCollect.setCreateTime(DateUtils.getNowDate());
        return pCollectMapper.insertPCollect(pCollect);
    }

    /**
     * 修改收藏信息
     * 
     * @param pCollect 收藏信息
     * @return 结果
     */
    @Override
    public int updatePCollect(PCollect pCollect)
    {
        return pCollectMapper.updatePCollect(pCollect);
    }

    /**
     * 批量删除收藏信息
     * 
     * @param ids 需要删除的收藏信息主键
     * @return 结果
     */
    @Override
    public int deletePCollectByIds(Long[] ids)
    {
        return pCollectMapper.deletePCollectByIds(ids);
    }

    /**
     * 删除收藏信息信息
     * 
     * @param id 收藏信息主键
     * @return 结果
     */
    @Override
    public int deletePCollectById(Long id)
    {
        return pCollectMapper.deletePCollectById(id);
    }
}
