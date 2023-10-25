package com.pipilin.system.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.pipilin.common.annotation.Excel;
import com.pipilin.common.core.domain.BaseEntity;
import com.pipilin.common.core.domain.entity.SysUser;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 预约挂号记录对象 p_registration
 * 
 * @author pipilin
 * @date 2023-02-27
 */
public class PRegistration extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    private Long id;

    /** 订单号 */
    @Excel(name = "订单号")
    private String orderNo;

    /** 就诊人ID */
    @Excel(name = "就诊人ID")
    private Long userId;

    /** 预约日期 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @Excel(name = "预约日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date registrationTime;

    /** 就诊状态（0待就诊 1已取消 2已完成） */
    @Excel(name = "就诊状态", readConverterExp = "0=待就诊,1=已取消,2=已完成")
    private String status;

    /** 就诊医生 */
    @Excel(name = "就诊医生")
    private Long doctorId;

    /** 就诊科室 */
    @Excel(name = "就诊科室")
    private Long sectionId;

    /** 就诊诊室 */
    @Excel(name = "就诊诊室")
    private Long outpatien;

    /** 取消时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "取消时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date cancelTime;

    private BigDecimal registrationPrice;

    private SysUser sysUser;

    private PSection pSection;

    private POutpatien pOutpatien;

    private String doctorName;

    public BigDecimal getRegistrationPrice() {
        return registrationPrice;
    }

    public void setRegistrationPrice(BigDecimal registrationPrice) {
        this.registrationPrice = registrationPrice;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public SysUser getSysUser() {
        return sysUser;
    }

    public void setSysUser(SysUser sysUser) {
        this.sysUser = sysUser;
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

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setOrderNo(String orderNo) 
    {
        this.orderNo = orderNo;
    }

    public String getOrderNo() 
    {
        return orderNo;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setRegistrationTime(Date registrationTime) 
    {
        this.registrationTime = registrationTime;
    }

    public Date getRegistrationTime() 
    {
        return registrationTime;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
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
    public void setCancelTime(Date cancelTime) 
    {
        this.cancelTime = cancelTime;
    }

    public Date getCancelTime() 
    {
        return cancelTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("orderNo", getOrderNo())
            .append("userId", getUserId())
            .append("registrationTime", getRegistrationTime())
            .append("createTime", getCreateTime())
            .append("status", getStatus())
            .append("doctorId", getDoctorId())
            .append("sectionId", getSectionId())
            .append("outpatien", getOutpatien())
            .append("cancelTime", getCancelTime())
            .append("remark", getRemark())
            .toString();
    }
}
