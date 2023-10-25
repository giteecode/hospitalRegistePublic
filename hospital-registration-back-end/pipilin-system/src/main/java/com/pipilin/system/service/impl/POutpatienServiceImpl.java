package com.pipilin.system.service.impl;

import java.util.List;
import com.pipilin.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pipilin.system.mapper.POutpatienMapper;
import com.pipilin.system.domain.POutpatien;
import com.pipilin.system.service.IPOutpatienService;

/**
 * 门诊信息Service业务层处理
 * 
 * @author pipilin
 * @date 2023-02-26
 */
@Service
public class POutpatienServiceImpl implements IPOutpatienService 
{
    @Autowired
    private POutpatienMapper pOutpatienMapper;

    /**
     * 查询门诊信息
     * 
     * @param roomId 门诊信息主键
     * @return 门诊信息
     */
    @Override
    public POutpatien selectPOutpatienByRoomId(Long roomId)
    {
        return pOutpatienMapper.selectPOutpatienByRoomId(roomId);
    }

    /**
     * 查询门诊信息列表
     * 
     * @param pOutpatien 门诊信息
     * @return 门诊信息
     */
    @Override
    public List<POutpatien> selectPOutpatienList(POutpatien pOutpatien)
    {
        return pOutpatienMapper.selectPOutpatienList(pOutpatien);
    }

    /**
     * 新增门诊信息
     * 
     * @param pOutpatien 门诊信息
     * @return 结果
     */
    @Override
    public int insertPOutpatien(POutpatien pOutpatien)
    {
        pOutpatien.setCreateTime(DateUtils.getNowDate());
        return pOutpatienMapper.insertPOutpatien(pOutpatien);
    }

    /**
     * 修改门诊信息
     * 
     * @param pOutpatien 门诊信息
     * @return 结果
     */
    @Override
    public int updatePOutpatien(POutpatien pOutpatien)
    {
        return pOutpatienMapper.updatePOutpatien(pOutpatien);
    }

    /**
     * 批量删除门诊信息
     * 
     * @param roomIds 需要删除的门诊信息主键
     * @return 结果
     */
    @Override
    public int deletePOutpatienByRoomIds(Long[] roomIds)
    {
        return pOutpatienMapper.deletePOutpatienByRoomIds(roomIds);
    }

    /**
     * 删除门诊信息信息
     * 
     * @param roomId 门诊信息主键
     * @return 结果
     */
    @Override
    public int deletePOutpatienByRoomId(Long roomId)
    {
        return pOutpatienMapper.deletePOutpatienByRoomId(roomId);
    }
}
