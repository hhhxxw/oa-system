package com.tdt.oa.service.impl;

import java.util.List;
import com.tdt.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tdt.oa.mapper.HrKpiItemMapper;
import com.tdt.oa.domain.HrKpiItem;
import com.tdt.oa.service.IHrKpiItemService;

/**
 * 人力考核指标集Service业务层处理
 * 
 * @author hxw
 * @date 2025-06-13
 */
@Service
public class HrKpiItemServiceImpl implements IHrKpiItemService 
{
    @Autowired
    private HrKpiItemMapper hrKpiItemMapper;

    /**
     * 查询人力考核指标集
     * 
     * @param id 人力考核指标集主键
     * @return 人力考核指标集
     */
    @Override
    public HrKpiItem selectHrKpiItemById(Long id)
    {
        return hrKpiItemMapper.selectHrKpiItemById(id);
    }

    /**
     * 查询人力考核指标集列表
     * 
     * @param hrKpiItem 人力考核指标集
     * @return 人力考核指标集
     */
    @Override
    public List<HrKpiItem> selectHrKpiItemList(HrKpiItem hrKpiItem)
    {
        return hrKpiItemMapper.selectHrKpiItemList(hrKpiItem);
    }

    /**
     * 新增人力考核指标集
     * 
     * @param hrKpiItem 人力考核指标集
     * @return 结果
     */
    @Override
    public int insertHrKpiItem(HrKpiItem hrKpiItem)
    {
        hrKpiItem.setCreateTime(DateUtils.getNowDate());
        return hrKpiItemMapper.insertHrKpiItem(hrKpiItem);
    }

    /**
     * 修改人力考核指标集
     * 
     * @param hrKpiItem 人力考核指标集
     * @return 结果
     */
    @Override
    public int updateHrKpiItem(HrKpiItem hrKpiItem)
    {
        return hrKpiItemMapper.updateHrKpiItem(hrKpiItem);
    }

    /**
     * 批量删除人力考核指标集
     * 
     * @param ids 需要删除的人力考核指标集主键
     * @return 结果
     */
    @Override
    public int deleteHrKpiItemByIds(Long[] ids)
    {
        return hrKpiItemMapper.deleteHrKpiItemByIds(ids);
    }

    /**
     * 删除人力考核指标集信息
     * 
     * @param id 人力考核指标集主键
     * @return 结果
     */
    @Override
    public int deleteHrKpiItemById(Long id)
    {
        return hrKpiItemMapper.deleteHrKpiItemById(id);
    }
}
