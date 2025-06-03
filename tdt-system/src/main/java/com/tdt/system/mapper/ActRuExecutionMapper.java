package com.tdt.system.mapper;


import com.tdt.system.domain.ActRuExecution;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @da
 * @date 2025-06-03 14:20
 */
public interface ActRuExecutionMapper 
{
    /**
     * 查询执行实例
     * 
     * @param id 执行实例主键
     * @return 执行实例
     */
    public ActRuExecution selectActRuExecutionById(String id);

    /**
     * 查询执行实例列表
     * 
     * @param actRuExecution 执行实例
     * @return 执行实例集合
     */
    public List<ActRuExecution> selectActRuExecutionList(ActRuExecution actRuExecution);

    /**
     * 用流程名称查询实例
     * @return
     */
    public List<ActRuExecution> selectActRuExecutionListByProcessName(@Param("name") String name);

    /**
     * 新增执行实例
     * 
     * @param actRuExecution 执行实例
     * @return 结果
     */
    public int insertActRuExecution(ActRuExecution actRuExecution);

    /**
     * 修改执行实例
     * 
     * @param actRuExecution 执行实例
     * @return 结果
     */
    public int updateActRuExecution(ActRuExecution actRuExecution);

    /**
     * 删除执行实例
     * 
     * @param id 执行实例主键
     * @return 结果
     */
    public int deleteActRuExecutionById(String id);

    /**
     * 批量删除执行实例
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteActRuExecutionByIds(String[] ids);
}
