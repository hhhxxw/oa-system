package com.tdt.oa.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.tdt.common.annotation.Excel;
import com.tdt.common.core.domain.BaseEntity;

/**
 * 人力考核指标集对象 hr_kpi_item
 * 
 * @author ruoyi
 * @date 2025-06-12
 */
public class HrKpiItem extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 指标ID */
    @Excel(name = "指标ID")
    private String itemId;

    /** 排序号 */
    @Excel(name = "排序号")
    private Long sortNo;

    /** 指标标题 */
    @Excel(name = "指标标题")
    private String title;

    /** 类型 */
    @Excel(name = "类型")
    private String kpiType;

    /** 指标内容 */
    @Excel(name = "指标内容")
    private String itemJson;

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

    public void setItemId(String itemId) 
    {
        this.itemId = itemId;
    }

    public String getItemId() 
    {
        return itemId;
    }

    public void setSortNo(Long sortNo) 
    {
        this.sortNo = sortNo;
    }

    public Long getSortNo() 
    {
        return sortNo;
    }

    public void setTitle(String title) 
    {
        this.title = title;
    }

    public String getTitle() 
    {
        return title;
    }

    public void setKpiType(String kpiType) 
    {
        this.kpiType = kpiType;
    }

    public String getKpiType() 
    {
        return kpiType;
    }

    public void setItemJson(String itemJson) 
    {
        this.itemJson = itemJson;
    }

    public String getItemJson() 
    {
        return itemJson;
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
            .append("itemId", getItemId())
            .append("sortNo", getSortNo())
            .append("title", getTitle())
            .append("kpiType", getKpiType())
            .append("itemJson", getItemJson())
            .append("remark", getRemark())
            .append("createTime", getCreateTime())
            .append("createUser", getCreateUser())
            .append("orgId", getOrgId())
            .toString();
    }
}
