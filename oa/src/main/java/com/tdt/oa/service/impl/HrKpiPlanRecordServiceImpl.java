package com.tdt.oa.service.impl;

import java.util.List;
import com.tdt.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tdt.oa.mapper.HrKpiPlanRecordMapper;
import com.tdt.oa.domain.HrKpiPlanRecord;
import com.tdt.oa.service.IHrKpiPlanRecordService;

/**
 * 人力资源考核详情Service业务层处理
 * 
 * @author hxw
 * @date 2025-06-12
 */
@Service
public class HrKpiPlanRecordServiceImpl implements IHrKpiPlanRecordService 
{
    @Autowired
    private HrKpiPlanRecordMapper hrKpiPlanRecordMapper;

    /**
     * 查询人力资源考核详情
     * 
     * @param id 人力资源考核详情主键
     * @return 人力资源考核详情
     */
    @Override
    public HrKpiPlanRecord selectHrKpiPlanRecordById(Long id)
    {
        return hrKpiPlanRecordMapper.selectHrKpiPlanRecordById(id);
    }

    /**
     * 查询人力资源考核详情列表
     * 
     * @param hrKpiPlanRecord 人力资源考核详情
     * @return 人力资源考核详情
     */
    @Override
    public List<HrKpiPlanRecord> selectHrKpiPlanRecordList(HrKpiPlanRecord hrKpiPlanRecord)
    {
        return hrKpiPlanRecordMapper.selectHrKpiPlanRecordList(hrKpiPlanRecord);
    }

    /**
     * 新增人力资源考核详情
     * 
     * @param hrKpiPlanRecord 人力资源考核详情
     * @return 结果
     */
    @Override
    public int insertHrKpiPlanRecord(HrKpiPlanRecord hrKpiPlanRecord)
    {
        hrKpiPlanRecord.setCreateTime(DateUtils.getNowDate());
        return hrKpiPlanRecordMapper.insertHrKpiPlanRecord(hrKpiPlanRecord);
    }

    /**
     * 修改人力资源考核详情
     * 
     * @param hrKpiPlanRecord 人力资源考核详情
     * @return 结果
     */
    @Override
    public int updateHrKpiPlanRecord(HrKpiPlanRecord hrKpiPlanRecord)
    {
        return hrKpiPlanRecordMapper.updateHrKpiPlanRecord(hrKpiPlanRecord);
    }

    /**
     * 批量删除人力资源考核详情
     * 
     * @param ids 需要删除的人力资源考核详情主键
     * @return 结果
     */
    @Override
    public int deleteHrKpiPlanRecordByIds(Long[] ids)
    {
        return hrKpiPlanRecordMapper.deleteHrKpiPlanRecordByIds(ids);
    }

    /**
     * 删除人力资源考核详情信息
     * 
     * @param id 人力资源考核详情主键
     * @return 结果
     */
    @Override
    public int deleteHrKpiPlanRecordById(Long id)
    {
        return hrKpiPlanRecordMapper.deleteHrKpiPlanRecordById(id);
    }
}
