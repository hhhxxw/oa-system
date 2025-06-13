package com.tdt.oa.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.tdt.common.annotation.Excel;
import com.tdt.common.core.domain.BaseEntity;

/**
 * 人力资源考核详情对象 hr_kpi_plan_record
 * 
 * @author hxw
 * @date 2025-06-12
 */
public class HrKpiPlanRecord extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String recordId;

    /** 关联计划ID */
    @Excel(name = "关联计划ID")
    private String planId;

    /** 关联考核指标 */
    @Excel(name = "关联考核指标")
    private String itemId;

    /** 分数 */
    @Excel(name = "分数")
    private BigDecimal score;

    /** 0:未考核，1:已考核 */
    @Excel(name = "0:未考核，1:已考核")
    private String status;

    /** 被考核人 */
    @Excel(name = "被考核人")
    private String accountId;

    /** 考核人 */
    @Excel(name = "考核人")
    private String chargeUser;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String createUser;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String orgId;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }

    public void setRecordId(String recordId) 
    {
        this.recordId = recordId;
    }

    public String getRecordId() 
    {
        return recordId;
    }

    public void setPlanId(String planId) 
    {
        this.planId = planId;
    }

    public String getPlanId() 
    {
        return planId;
    }

    public void setItemId(String itemId) 
    {
        this.itemId = itemId;
    }

    public String getItemId() 
    {
        return itemId;
    }

    public void setScore(BigDecimal score) 
    {
        this.score = score;
    }

    public BigDecimal getScore() 
    {
        return score;
    }

    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }

    public void setAccountId(String accountId) 
    {
        this.accountId = accountId;
    }

    public String getAccountId() 
    {
        return accountId;
    }

    public void setChargeUser(String chargeUser) 
    {
        this.chargeUser = chargeUser;
    }

    public String getChargeUser() 
    {
        return chargeUser;
    }

    public void setCreateUser(String createUser) 
    {
        this.createUser = createUser;
    }

    public String getCreateUser() 
    {
        return createUser;
    }

    public void setOrgId(String orgId) 
    {
        this.orgId = orgId;
    }

    public String getOrgId() 
    {
        return orgId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("recordId", getRecordId())
            .append("planId", getPlanId())
            .append("itemId", getItemId())
            .append("score", getScore())
            .append("status", getStatus())
            .append("accountId", getAccountId())
            .append("chargeUser", getChargeUser())
            .append("remark", getRemark())
            .append("createTime", getCreateTime())
            .append("createUser", getCreateUser())
            .append("orgId", getOrgId())
            .toString();
    }
}
