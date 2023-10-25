package com.pipilin.system.domain;

import com.pipilin.common.annotation.Excel;
import com.pipilin.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 门诊信息对象 p_outpatien
 * 
 * @author pipilin
 * @date 2023-02-26
 */
public class POutpatien extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    private Long roomId;

    /** 科室ID */
    @Excel(name = "科室ID")
    private Long deptId;

    /** 名称 */
    @Excel(name = "名称")
    private String roomName;

    private PSection pSection;

    public PSection getpSection() {
        return pSection;
    }

    public void setpSection(PSection pSection) {
        this.pSection = pSection;
    }

    public void setRoomId(Long roomId)
    {
        this.roomId = roomId;
    }

    public Long getRoomId() 
    {
        return roomId;
    }
    public void setDeptId(Long deptId) 
    {
        this.deptId = deptId;
    }

    public Long getDeptId() 
    {
        return deptId;
    }
    public void setRoomName(String roomName) 
    {
        this.roomName = roomName;
    }

    public String getRoomName() 
    {
        return roomName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("roomId", getRoomId())
            .append("deptId", getDeptId())
            .append("roomName", getRoomName())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .toString();
    }
}
