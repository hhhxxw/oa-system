package com.tdt.oa.service.impl;

import java.util.List;
import com.tdt.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tdt.oa.mapper.HrKpiPlanMapper;
import com.tdt.oa.domain.HrKpiPlan;
import com.tdt.oa.service.IHrKpiPlanService;

/**
 * 考核计划Service业务层处理
 * 
 * @author hxw
 * @date 2025-06-12
 */
@Service
public class HrKpiPlanServiceImpl implements IHrKpiPlanService 
{
    @Autowired
    private HrKpiPlanMapper hrKpiPlanMapper;

    /**
     * 查询考核计划
     * 
     * @param id 考核计划主键
     * @return 考核计划
     */
    @Override
    public HrKpiPlan selectHrKpiPlanById(Long id)
    {
        return hrKpiPlanMapper.selectHrKpiPlanById(id);
    }

    /**
     * 查询考核计划列表
     * 
     * @param hrKpiPlan 考核计划
     * @return 考核计划
     */
    @Override
    public List<HrKpiPlan> selectHrKpiPlanList(HrKpiPlan hrKpiPlan)
    {
        return hrKpiPlanMapper.selectHrKpiPlanList(hrKpiPlan);
    }

    /**
     * 新增考核计划
     * 
     * @param hrKpiPlan 考核计划
     * @return 结果
     */
    @Override
    public int insertHrKpiPlan(HrKpiPlan hrKpiPlan)
    {
        hrKpiPlan.setCreateTime(DateUtils.getNowDate());
        return hrKpiPlanMapper.insertHrKpiPlan(hrKpiPlan);
    }

    /**
     * 修改考核计划
     * 
     * @param hrKpiPlan 考核计划
     * @return 结果
     */
    @Override
    public int updateHrKpiPlan(HrKpiPlan hrKpiPlan)
    {
        return hrKpiPlanMapper.updateHrKpiPlan(hrKpiPlan);
    }

    /**
     * 批量删除考核计划
     * 
     * @param ids 需要删除的考核计划主键
     * @return 结果
     */
    @Override
    public int deleteHrKpiPlanByIds(Long[] ids)
    {
        return hrKpiPlanMapper.deleteHrKpiPlanByIds(ids);
    }

    /**
     * 删除考核计划信息
     * 
     * @param id 考核计划主键
     * @return 结果
     */
    @Override
    public int deleteHrKpiPlanById(Long id)
    {
        return hrKpiPlanMapper.deleteHrKpiPlanById(id);
    }
}
