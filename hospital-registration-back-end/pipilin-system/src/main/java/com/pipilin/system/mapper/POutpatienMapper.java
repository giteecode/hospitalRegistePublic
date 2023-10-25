package com.pipilin.system.mapper;

import java.util.List;
import com.pipilin.system.domain.POutpatien;

/**
 * 门诊信息Mapper接口
 * 
 * @author pipilin
 * @date 2023-02-26
 */
public interface POutpatienMapper 
{
    /**
     * 查询门诊信息
     * 
     * @param roomId 门诊信息主键
     * @return 门诊信息
     */
    public POutpatien selectPOutpatienByRoomId(Long roomId);

    /**
     * 查询门诊信息列表
     * 
     * @param pOutpatien 门诊信息
     * @return 门诊信息集合
     */
    public List<POutpatien> selectPOutpatienList(POutpatien pOutpatien);

    /**
     * 新增门诊信息
     * 
     * @param pOutpatien 门诊信息
     * @return 结果
     */
    public int insertPOutpatien(POutpatien pOutpatien);

    /**
     * 修改门诊信息
     * 
     * @param pOutpatien 门诊信息
     * @return 结果
     */
    public int updatePOutpatien(POutpatien pOutpatien);

    /**
     * 删除门诊信息
     * 
     * @param roomId 门诊信息主键
     * @return 结果
     */
    public int deletePOutpatienByRoomId(Long roomId);

    /**
     * 批量删除门诊信息
     * 
     * @param roomIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePOutpatienByRoomIds(Long[] roomIds);
}
