package com.tdt.process.mapper;

import java.util.List;
import com.tdt.process.domain.ProcessType;

/**
 * 审批类型Mapper接口
 * 
 * @author hxw
 * @date 2025-06-19
 */
public interface ProcessTypeMapper 
{
    /**
     * 查询审批类型
     * 
     * @param id 审批类型主键
     * @return 审批类型
     */
    public ProcessType selectProcessTypeById(Long id);

    /**
     * 查询审批类型列表
     * 
     * @param processType 审批类型
     * @return 审批类型集合
     */
    public List<ProcessType> selectProcessTypeList(ProcessType processType);

    /**
     * 新增审批类型
     * 
     * @param processType 审批类型
     * @return 结果
     */
    public int insertProcessType(ProcessType processType);

    /**
     * 修改审批类型
     * 
     * @param processType 审批类型
     * @return 结果
     */
    public int updateProcessType(ProcessType processType);

    /**
     * 删除审批类型
     * 
     * @param id 审批类型主键
     * @return 结果
     */
    public int deleteProcessTypeById(Long id);

    /**
     * 批量删除审批类型
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteProcessTypeByIds(Long[] ids);
}
