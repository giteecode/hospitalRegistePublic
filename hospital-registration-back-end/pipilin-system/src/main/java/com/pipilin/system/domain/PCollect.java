package com.pipilin.system.domain;

import com.pipilin.common.annotation.Excel;
import com.pipilin.common.core.domain.BaseEntity;
import com.pipilin.common.core.domain.entity.SysUser;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 收藏信息对象 p_collect
 * 
 * @author pipilin
 * @date 2023-03-03
 */
public class PCollect extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    private Long id;

    /** 用户ID */
    @Excel(name = "用户ID")
    private Long userId;

    /** 医生ID */
    @Excel(name = "医生ID")
    private Long doctorId;

    /** 科室ID */
    @Excel(name = "科室ID")
    private Long sectionId;

    /** 门诊ID */
    @Excel(name = "门诊ID")
    private Long outpatien;

    private SysUser sysUser;

    private PSection pSection;
    private POutpatien pOutpatien;

    private String nickName;

    private String keyWord;

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
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
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setDoctorId(Long doctorId) 
    {
        this.doctorId = doctorId;
    }

    public Long getDoctorId() 
    {
        return doctorId;
    }
    public void setSectionId(Long sectionId) 
    {
        this.sectionId = sectionId;
    }

    public Long getSectionId() 
    {
        return sectionId;
    }
    public void setOutpatien(Long outpatien) 
    {
        this.outpatien = outpatien;
    }

    public Long getOutpatien() 
    {
        return outpatien;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("doctorId", getDoctorId())
            .append("sectionId", getSectionId())
            .append("outpatien", getOutpatien())
            .append("createTime", getCreateTime())
            .toString();
    }
}
