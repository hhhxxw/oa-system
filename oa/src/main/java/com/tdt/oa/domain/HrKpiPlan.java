package com.tdt.oa.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.tdt.common.annotation.Excel;
import com.tdt.common.core.domain.BaseEntity;

/**
 * 考核计划对象 hr_kpi_plan
 * 
 * @author hxw
 * @date 2025-06-12
 */
public class HrKpiPlan extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long sortNo;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String planId;

    /** 考核标题 */
    @Excel(name = "考核标题")
    private String title;

    /** 考核开始时间 */
    @Excel(name = "考核开始时间")
    private String beginTime;

    /** 考核结束时间 */
    @Excel(name = "考核结束时间")
    private String endTime;

    /** 考核规则 */
    @Excel(name = "考核规则")
    private String kpiRule;

    /** 考核项Id */
    @Excel(name = "考核项Id")
    private String itemId;

    /** 考核人 */
    @Excel(name = "考核人")
    private String chargeUser;

    /** 被考核人 */
    @Excel(name = "被考核人")
    private String userRole;

    /** 被考核人 */
    @Excel(name = "被考核人")
    private String deptRole;

    /** 被考核人 */
    @Excel(name = "被考核人")
    private String levelRole;

    /** 相关附件 */
    @Excel(name = "相关附件")
    private String attachId;

    /** 消息提醒方式 */
    @Excel(name = "消息提醒方式")
    private String msgType;

    /** 0:未生效，1：生效中，2：终止 */
    @Excel(name = "0:未生效，1：生效中，2：终止")
    private String status;

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

    public void setSortNo(Long sortNo) 
    {
        this.sortNo = sortNo;
    }

    public Long getSortNo() 
    {
        return sortNo;
    }

    public void setPlanId(String planId) 
    {
        this.planId = planId;
    }

    public String getPlanId() 
    {
        return planId;
    }

    public void setTitle(String title) 
    {
        this.title = title;
    }

    public String getTitle() 
    {
        return title;
    }

    public void setBeginTime(String beginTime) 
    {
        this.beginTime = beginTime;
    }

    public String getBeginTime() 
    {
        return beginTime;
    }

    public void setEndTime(String endTime) 
    {
        this.endTime = endTime;
    }

    public String getEndTime() 
    {
        return endTime;
    }

    public void setKpiRule(String kpiRule) 
    {
        this.kpiRule = kpiRule;
    }

    public String getKpiRule() 
    {
        return kpiRule;
    }

    public void setItemId(String itemId) 
    {
        this.itemId = itemId;
    }

    public String getItemId() 
    {
        return itemId;
    }

    public void setChargeUser(String chargeUser) 
    {
        this.chargeUser = chargeUser;
    }

    public String getChargeUser() 
    {
        return chargeUser;
    }

    public void setUserRole(String userRole) 
    {
        this.userRole = userRole;
    }

    public String getUserRole() 
    {
        return userRole;
    }

    public void setDeptRole(String deptRole) 
    {
        this.deptRole = deptRole;
    }

    public String getDeptRole() 
    {
        return deptRole;
    }

    public void setLevelRole(String levelRole) 
    {
        this.levelRole = levelRole;
    }

    public String getLevelRole() 
    {
        return levelRole;
    }

    public void setAttachId(String attachId) 
    {
        this.attachId = attachId;
    }

    public String getAttachId() 
    {
        return attachId;
    }

    public void setMsgType(String msgType) 
    {
        this.msgType = msgType;
    }

    public String getMsgType() 
    {
        return msgType;
    }

    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
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
            .append("sortNo", getSortNo())
            .append("planId", getPlanId())
            .append("title", getTitle())
            .append("beginTime", getBeginTime())
            .append("endTime", getEndTime())
            .append("kpiRule", getKpiRule())
            .append("itemId", getItemId())
            .append("chargeUser", getChargeUser())
            .append("userRole", getUserRole())
            .append("deptRole", getDeptRole())
            .append("levelRole", getLevelRole())
            .append("attachId", getAttachId())
            .append("msgType", getMsgType())
            .append("remark", getRemark())
            .append("status", getStatus())
            .append("createUser", getCreateUser())
            .append("createTime", getCreateTime())
            .append("orgId", getOrgId())
            .toString();
    }
}
