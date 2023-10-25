package com.pipilin.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.pipilin.common.annotation.Excel;
import com.pipilin.common.core.domain.BaseEntity;
import com.pipilin.common.core.domain.entity.SysUser;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 医生排班信息对象 p_scheduling
 * 
 * @author pipilin
 * @date 2023-03-09
 */
public class PScheduling extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    private Long id;

    /** 值班人ID */
    @Excel(name = "值班人ID")
    private Long schedulingId;

    /** 值班开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date schedulingStartTime;

    /** 值班结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date schedulingEndTime;

    private SysUser sysUser;

    private PSection pSection;

    private POutpatien pOutpatien;

    private String keyWord1;

    private Long sectionId;
    private Long outpatienId;

    private boolean flag;

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public Long getSectionId() {
        return sectionId;
    }

    public void setSectionId(Long sectionId) {
        this.sectionId = sectionId;
    }

    public Long getOutpatienId() {
        return outpatienId;
    }

    public void setOutpatienId(Long outpatienId) {
        this.outpatienId = outpatienId;
    }

    public String getKeyWord1() {
        return keyWord1;
    }

    public void setKeyWord1(String keyWord1) {
        this.keyWord1 = keyWord1;
    }

    public PSection getpSection() {
        return pSection;
    }

    public void setpSection(PSection pSection) {
        this.pSection = pSection;
    }

    public POutpatien getpOutpatien() {
        return pOutpatien;
    }

    public void setpOutpatien(POutpatien pOutpatien) {
        this.pOutpatien = pOutpatien;
    }

    public SysUser getSysUser() {
        return sysUser;
    }

    public void setSysUser(SysUser sysUser) {
        this.sysUser = sysUser;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setSchedulingId(Long schedulingId) 
    {
        this.schedulingId = schedulingId;
    }

    public Long getSchedulingId() 
    {
        return schedulingId;
    }
    public void setSchedulingStartTime(Date schedulingStartTime) 
    {
        this.schedulingStartTime = schedulingStartTime;
    }

    public Date getSchedulingStartTime() 
    {
        return schedulingStartTime;
    }
    public void setSchedulingEndTime(Date schedulingEndTime) 
    {
        this.schedulingEndTime = schedulingEndTime;
    }

    public Date getSchedulingEndTime() 
    {
        return schedulingEndTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("schedulingId", getSchedulingId())
            .append("schedulingStartTime", getSchedulingStartTime())
            .append("schedulingEndTime", getSchedulingEndTime())
            .append("createTime", getCreateTime())
            .toString();
    }
}
