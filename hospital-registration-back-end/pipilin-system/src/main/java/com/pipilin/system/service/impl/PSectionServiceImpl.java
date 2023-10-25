package com.pipilin.system.service.impl;

import java.util.List;

import cn.hutool.core.util.ObjectUtil;
import com.pipilin.common.exception.base.BaseException;
import com.pipilin.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pipilin.system.mapper.PSectionMapper;
import com.pipilin.system.domain.PSection;
import com.pipilin.system.service.IPSectionService;

/**
 * 科室信息Service业务层处理
 * 
 * @author pipilin
 * @date 2023-02-26
 */
@Service
public class PSectionServiceImpl implements IPSectionService 
{
    @Autowired
    private PSectionMapper pSectionMapper;

    /**
     * 查询科室信息
     * 
     * @param deptId 科室信息主键
     * @return 科室信息
     */
    @Override
    public PSection selectPSectionByDeptId(Long deptId)
    {
        return pSectionMapper.selectPSectionByDeptId(deptId);
    }

    /**
     * 查询科室信息列表
     * 
     * @param pSection 科室信息
     * @return 科室信息
     */
    @Override
    public List<PSection> selectPSectionList(PSection pSection)
    {
        return pSectionMapper.selectPSectionList(pSection);
    }

    /**
     * 新增科室信息
     * 
     * @param pSection 科室信息
     * @return 结果
     */
    @Override
    public int insertPSection(PSection pSection)
    {
        PSection pSection1 = pSectionMapper.selectPSectionByDeptName(pSection.getDeptName());
        if(ObjectUtil.isNotNull(pSection1)){
            throw new BaseException("科室名称已存在!");
        }
        pSection.setCreateTime(DateUtils.getNowDate());
        return pSectionMapper.insertPSection(pSection);
    }

    /**
     * 修改科室信息
     * 
     * @param pSection 科室信息
     * @return 结果
     */
    @Override
    public int updatePSection(PSection pSection)
    {
        PSection pSection1 = pSectionMapper.selectPSectionByDeptDeptIdName(pSection.getDeptId(),pSection.getDeptName());
        if(ObjectUtil.isNotNull(pSection1)){
            throw new BaseException("科室名称已存在!");
        }
        return pSectionMapper.updatePSection(pSection);
    }

    /**
     * 批量删除科室信息
     * 
     * @param deptIds 需要删除的科室信息主键
     * @return 结果
     */
    @Override
    public int deletePSectionByDeptIds(Long[] deptIds)
    {
        return pSectionMapper.deletePSectionByDeptIds(deptIds);
    }

    /**
     * 删除科室信息信息
     * 
     * @param deptId 科室信息主键
     * @return 结果
     */
    @Override
    public int deletePSectionByDeptId(Long deptId)
    {
        return pSectionMapper.deletePSectionByDeptId(deptId);
    }
}
