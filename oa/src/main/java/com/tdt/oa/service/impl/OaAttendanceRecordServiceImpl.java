package com.tdt.oa.service.impl;

import java.util.List;
import com.tdt.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tdt.oa.mapper.OaAttendanceRecordMapper;
import com.tdt.oa.domain.OaAttendanceRecord;
import com.tdt.oa.service.IOaAttendanceRecordService;

/**
 * 打卡记录Service业务层处理
 * 
 * @author hxw
 * @date 2025-05-30
 */
@Service
public class OaAttendanceRecordServiceImpl implements IOaAttendanceRecordService 
{
    @Autowired
    private OaAttendanceRecordMapper oaAttendanceRecordMapper;

    /**
     * 查询打卡记录
     * 
     * @param id 打卡记录主键
     * @return 打卡记录
     */
    @Override
    public OaAttendanceRecord selectOaAttendanceRecordById(Long id)
    {
        return oaAttendanceRecordMapper.selectOaAttendanceRecordById(id);
    }

    /**
     * 查询打卡记录列表
     * 
     * @param oaAttendanceRecord 打卡记录
     * @return 打卡记录
     */
    @Override
    public List<OaAttendanceRecord> selectOaAttendanceRecordList(OaAttendanceRecord oaAttendanceRecord)
    {
        return oaAttendanceRecordMapper.selectOaAttendanceRecordList(oaAttendanceRecord);
    }

    /**
     * 新增打卡记录
     * 
     * @param oaAttendanceRecord 打卡记录
     * @return 结果
     */
    @Override
    public int insertOaAttendanceRecord(OaAttendanceRecord oaAttendanceRecord)
    {
        oaAttendanceRecord.setCreateTime(DateUtils.getNowDate());
        return oaAttendanceRecordMapper.insertOaAttendanceRecord(oaAttendanceRecord);
    }

    /**
     * 修改打卡记录
     * 
     * @param oaAttendanceRecord 打卡记录
     * @return 结果
     */
    @Override
    public int updateOaAttendanceRecord(OaAttendanceRecord oaAttendanceRecord)
    {
        oaAttendanceRecord.setUpdateTime(DateUtils.getNowDate());
        return oaAttendanceRecordMapper.updateOaAttendanceRecord(oaAttendanceRecord);
    }

    /**
     * 批量删除打卡记录
     * 
     * @param ids 需要删除的打卡记录主键
     * @return 结果
     */
    @Override
    public int deleteOaAttendanceRecordByIds(Long[] ids)
    {
        return oaAttendanceRecordMapper.deleteOaAttendanceRecordByIds(ids);
    }

    /**
     * 删除打卡记录信息
     * 
     * @param id 打卡记录主键
     * @return 结果
     */
    @Override
    public int deleteOaAttendanceRecordById(Long id)
    {
        return oaAttendanceRecordMapper.deleteOaAttendanceRecordById(id);
    }
}
