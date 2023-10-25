package com.pipilin.system.domain;

import com.pipilin.common.annotation.Excel;
import com.pipilin.common.core.domain.BaseEntity;
import com.pipilin.common.core.domain.entity.SysUser;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 评价信息对象 p_evaluate
 * 
 * @author pipilin
 * @date 2023-03-03
 */
public class PEvaluate extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    private Long id;

    /** 用户ID */
    @Excel(name = "用户ID")
    private Long userId;

    /** 内容 */
    @Excel(name = "内容")
    private String evaluateContent;

    /** 医生 */
    @Excel(name = "医生")
    private Long doctorId;

    private Long registrationId;

    private SysUser sysUser;

    private String doctorNickName;

    private String doctorUserName;

    private String doctorAvatar;

    private String keyWord1;

    private String keyWord2;

    public String getKeyWord1() {
        return keyWord1;
    }

    public void setKeyWord1(String keyWord1) {
        this.keyWord1 = keyWord1;
    }

    public String getKeyWord2() {
        return keyWord2;
    }

    public void setKeyWord2(String keyWord2) {
        this.keyWord2 = keyWord2;
    }

    public String getDoctorNickName() {
        return doctorNickName;
    }

    public void setDoctorNickName(String doctorNickName) {
        this.doctorNickName = doctorNickName;
    }

    public String getDoctorUserName() {
        return doctorUserName;
    }

    public void setDoctorUserName(String doctorUserName) {
        this.doctorUserName = doctorUserName;
    }

    public String getDoctorAvatar() {
        return doctorAvatar;
    }

    public void setDoctorAvatar(String doctorAvatar) {
        this.doctorAvatar = doctorAvatar;
    }

    public SysUser getSysUser() {
        return sysUser;
    }

    public void setSysUser(SysUser sysUser) {
        this.sysUser = sysUser;
    }

    public Long getRegistrationId() {
        return registrationId;
    }

    public void setRegistrationId(Long registrationId) {
        this.registrationId = registrationId;
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
    public void setEvaluateContent(String evaluateContent) 
    {
        this.evaluateContent = evaluateContent;
    }

    public String getEvaluateContent() 
    {
        return evaluateContent;
    }
    public void setDoctorId(Long doctorId) 
    {
        this.doctorId = doctorId;
    }

    public Long getDoctorId() 
    {
        return doctorId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("evaluateContent", getEvaluateContent())
            .append("doctorId", getDoctorId())
            .append("createTime", getCreateTime())
            .toString();
    }
}
