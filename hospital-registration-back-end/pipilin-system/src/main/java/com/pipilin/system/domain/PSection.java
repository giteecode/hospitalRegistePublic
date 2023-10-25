package com.pipilin.system.domain;

import com.pipilin.common.annotation.Excel;
import com.pipilin.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 科室信息对象 p_section
 * 
 * @author pipilin
 * @date 2023-02-26
 */
public class PSection extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键地ID */
    private Long deptId;

    /** 名称 */
    @Excel(name = "名称")
    private String deptName;

    public void setDeptId(Long deptId) 
    {
        this.deptId = deptId;
    }

    public Long getDeptId() 
    {
        return deptId;
    }
    public void setDeptName(String deptName) 
    {
        this.deptName = deptName;
    }

    public String getDeptName() 
    {
        return deptName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("deptId", getDeptId())
            .append("deptName", getDeptName())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .toString();
    }
}
