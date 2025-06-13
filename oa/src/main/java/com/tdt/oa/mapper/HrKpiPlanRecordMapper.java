package com.tdt.oa.mapper;

import java.util.List;
import com.tdt.oa.domain.HrKpiPlanRecord;

/**
 * 人力资源考核详情Mapper接口
 * 
 * @author hxw
 * @date 2025-06-12
 */
public interface HrKpiPlanRecordMapper 
{
    /**
     * 查询人力资源考核详情
     * 
     * @param id 人力资源考核详情主键
     * @return 人力资源考核详情
     */
    public HrKpiPlanRecord selectHrKpiPlanRecordById(Long id);

    /**
     * 查询人力资源考核详情列表
     * 
     * @param hrKpiPlanRecord 人力资源考核详情
     * @return 人力资源考核详情集合
     */
    public List<HrKpiPlanRecord> selectHrKpiPlanRecordList(HrKpiPlanRecord hrKpiPlanRecord);

    /**
     * 新增人力资源考核详情
     * 
     * @param hrKpiPlanRecord 人力资源考核详情
     * @return 结果
     */
    public int insertHrKpiPlanRecord(HrKpiPlanRecord hrKpiPlanRecord);

    /**
     * 修改人力资源考核详情
     * 
     * @param hrKpiPlanRecord 人力资源考核详情
     * @return 结果
     */
    public int updateHrKpiPlanRecord(HrKpiPlanRecord hrKpiPlanRecord);

    /**
     * 删除人力资源考核详情
     * 
     * @param id 人力资源考核详情主键
     * @return 结果
     */
    public int deleteHrKpiPlanRecordById(Long id);

    /**
     * 批量删除人力资源考核详情
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteHrKpiPlanRecordByIds(Long[] ids);
}
