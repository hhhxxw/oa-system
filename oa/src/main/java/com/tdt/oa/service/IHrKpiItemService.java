package com.tdt.oa.service;

import java.util.List;
import com.tdt.oa.domain.HrKpiItem;

/**
 * 人力考核指标集Service接口
 * 
 * @author ruoyi
 * @date 2025-06-12
 */
public interface IHrKpiItemService 
{
    /**
     * 查询人力考核指标集
     * 
     * @param id 人力考核指标集主键
     * @return 人力考核指标集
     */
    public HrKpiItem selectHrKpiItemById(Long id);

    /**
     * 查询人力考核指标集列表
     * 
     * @param hrKpiItem 人力考核指标集
     * @return 人力考核指标集集合
     */
    public List<HrKpiItem> selectHrKpiItemList(HrKpiItem hrKpiItem);

    /**
     * 新增人力考核指标集
     * 
     * @param hrKpiItem 人力考核指标集
     * @return 结果
     */
    public int insertHrKpiItem(HrKpiItem hrKpiItem);

    /**
     * 修改人力考核指标集
     * 
     * @param hrKpiItem 人力考核指标集
     * @return 结果
     */
    public int updateHrKpiItem(HrKpiItem hrKpiItem);

    /**
     * 批量删除人力考核指标集
     * 
     * @param ids 需要删除的人力考核指标集主键集合
     * @return 结果
     */
    public int deleteHrKpiItemByIds(Long[] ids);

    /**
     * 删除人力考核指标集信息
     * 
     * @param id 人力考核指标集主键
     * @return 结果
     */
    public int deleteHrKpiItemById(Long id);
}
