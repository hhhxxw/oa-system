package com.tdt.oa.service.impl;

import java.util.List;
import com.tdt.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tdt.oa.mapper.OaHolidayMapper;
import com.tdt.oa.domain.OaHoliday;
import com.tdt.oa.service.IOaHolidayService;

/**
 * 节假日Service业务层处理
 * 
 * @author hxw
 * @date 2025-05-30
 */
@Service
public class OaHolidayServiceImpl implements IOaHolidayService 
{
    @Autowired
    private OaHolidayMapper oaHolidayMapper;

    /**
     * 查询节假日
     * 
     * @param id 节假日主键
     * @return 节假日
     */
    @Override
    public OaHoliday selectOaHolidayById(String id)
    {
        return oaHolidayMapper.selectOaHolidayById(id);
    }

    /**
     * 查询节假日列表
     * 
     * @param oaHoliday 节假日
     * @return 节假日
     */
    @Override
    public List<OaHoliday> selectOaHolidayList(OaHoliday oaHoliday)
    {
        return oaHolidayMapper.selectOaHolidayList(oaHoliday);
    }

    /**
     * 新增节假日
     * 
     * @param oaHoliday 节假日
     * @return 结果
     */
    @Override
    public int insertOaHoliday(OaHoliday oaHoliday)
    {
        oaHoliday.setCreateTime(DateUtils.getNowDate());
        return oaHolidayMapper.insertOaHoliday(oaHoliday);
    }

    /**
     * 修改节假日
     * 
     * @param oaHoliday 节假日
     * @return 结果
     */
    @Override
    public int updateOaHoliday(OaHoliday oaHoliday)
    {
        oaHoliday.setUpdateTime(DateUtils.getNowDate());
        return oaHolidayMapper.updateOaHoliday(oaHoliday);
    }

    /**
     * 批量删除节假日
     * 
     * @param ids 需要删除的节假日主键
     * @return 结果
     */
    @Override
    public int deleteOaHolidayByIds(String[] ids)
    {
        return oaHolidayMapper.deleteOaHolidayByIds(ids);
    }

    /**
     * 删除节假日信息
     * 
     * @param id 节假日主键
     * @return 结果
     */
    @Override
    public int deleteOaHolidayById(String id)
    {
        return oaHolidayMapper.deleteOaHolidayById(id);
    }
}
