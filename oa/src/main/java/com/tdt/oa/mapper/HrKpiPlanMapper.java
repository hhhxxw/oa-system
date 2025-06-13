package com.tdt.oa.mapper;

import java.util.List;
import com.tdt.oa.domain.HrKpiPlan;

/**
 * 考核计划Mapper接口
 * 
 * @author hxw
 * @date 2025-06-12
 */
public interface HrKpiPlanMapper 
{
    /**
     * 查询考核计划
     * 
     * @param id 考核计划主键
     * @return 考核计划
     */
    public HrKpiPlan selectHrKpiPlanById(Long id);

    /**
     * 查询考核计划列表
     * 
     * @param hrKpiPlan 考核计划
     * @return 考核计划集合
     */
    public List<HrKpiPlan> selectHrKpiPlanList(HrKpiPlan hrKpiPlan);

    /**
     * 新增考核计划
     * 
     * @param hrKpiPlan 考核计划
     * @return 结果
     */
    public int insertHrKpiPlan(HrKpiPlan hrKpiPlan);

    /**
     * 修改考核计划
     * 
     * @param hrKpiPlan 考核计划
     * @return 结果
     */
    public int updateHrKpiPlan(HrKpiPlan hrKpiPlan);

    /**
     * 删除考核计划
     * 
     * @param id 考核计划主键
     * @return 结果
     */
    public int deleteHrKpiPlanById(Long id);

    /**
     * 批量删除考核计划
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteHrKpiPlanByIds(Long[] ids);
}
