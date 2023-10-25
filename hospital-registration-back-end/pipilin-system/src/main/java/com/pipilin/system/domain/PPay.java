package com.pipilin.system.domain;

import java.math.BigDecimal;
import com.pipilin.common.annotation.Excel;
import com.pipilin.common.core.domain.BaseEntity;
import com.pipilin.common.core.domain.entity.SysUser;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 充值记录信息对象 p_pay
 * 
 * @author pipilin
 * @date 2023-03-03
 */
public class PPay extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    private Long id;

    /** 充值用户ID */
    @Excel(name = "充值用户ID")
    private Long userId;

    /** 充值订单号 */
    @Excel(name = "充值订单号")
    private String orderNo;

    /** 充值金额 */
    @Excel(name = "充值金额")
    private BigDecimal price;

    private SysUser sysUser;

    private String keyWord1;

    public String getKeyWord1() {
        return keyWord1;
    }

    public void setKeyWord1(String keyWord1) {
        this.keyWord1 = keyWord1;
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
    public void setOrderNo(String orderNo) 
    {
        this.orderNo = orderNo;
    }

    public String getOrderNo() 
    {
        return orderNo;
    }
    public void setPrice(BigDecimal price) 
    {
        this.price = price;
    }

    public BigDecimal getPrice() 
    {
        return price;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("orderNo", getOrderNo())
            .append("price", getPrice())
            .append("createTime", getCreateTime())
            .toString();
    }
}
