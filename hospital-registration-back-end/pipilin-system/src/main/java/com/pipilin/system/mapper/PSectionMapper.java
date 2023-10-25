package com.pipilin.system.mapper;

import java.util.List;
import com.pipilin.system.domain.PSection;
import org.apache.ibatis.annotations.Param;

/**
 * 科室信息Mapper接口
 * 
 * @author pipilin
 * @date 2023-02-26
 */
public interface PSectionMapper 
{
    /**
     * 查询科室信息
     * 
     * @param deptId 科室信息主键
     * @return 科室信息
     */
    public PSection selectPSectionByDeptId(Long deptId);

    /**
     * 查询科室信息
     *
     * @param deptName 科室信息名称
     * @return 科室信息
     */
    public PSection selectPSectionByDeptName(String deptName);

    /**
     * 查询科室信息
     *
     * @param deptName 科室信息名称
     * @return 科室信息
     */
    public PSection selectPSectionByDeptDeptIdName(@Param("deptId") Long deptId,@Param("deptName") String deptName);

    /**
     * 查询科室信息列表
     * 
     * @param pSection 科室信息
     * @return 科室信息集合
     */
    public List<PSection> selectPSectionList(PSection pSection);

    /**
     * 新增科室信息
     * 
     * @param pSection 科室信息
     * @return 结果
     */
    public int insertPSection(PSection pSection);

    /**
     * 修改科室信息
     * 
     * @param pSection 科室信息
     * @return 结果
     */
    public int updatePSection(PSection pSection);

    /**
     * 删除科室信息
     * 
     * @param deptId 科室信息主键
     * @return 结果
     */
    public int deletePSectionByDeptId(Long deptId);

    /**
     * 批量删除科室信息
     * 
     * @param deptIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePSectionByDeptIds(Long[] deptIds);
}
