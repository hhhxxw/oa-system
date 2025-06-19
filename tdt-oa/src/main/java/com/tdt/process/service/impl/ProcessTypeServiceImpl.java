package com.tdt.process.service.impl;

import java.util.List;
import com.tdt.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tdt.process.mapper.ProcessTypeMapper;
import com.tdt.process.domain.ProcessType;
import com.tdt.process.service.IProcessTypeService;

/**
 * 审批类型Service业务层处理
 * 
 * @author hxw
 * @date 2025-06-19
 */
@Service
public class ProcessTypeServiceImpl implements IProcessTypeService 
{
    @Autowired
    private ProcessTypeMapper processTypeMapper;

    /**
     * 查询审批类型
     * 
     * @param id 审批类型主键
     * @return 审批类型
     */
    @Override
    public ProcessType selectProcessTypeById(Long id)
    {
        return processTypeMapper.selectProcessTypeById(id);
    }

    /**
     * 查询审批类型列表
     * 
     * @param processType 审批类型
     * @return 审批类型
     */
    @Override
    public List<ProcessType> selectProcessTypeList(ProcessType processType)
    {
        return processTypeMapper.selectProcessTypeList(processType);
    }

    /**
     * 新增审批类型
     * 
     * @param processType 审批类型
     * @return 结果
     */
    @Override
    public int insertProcessType(ProcessType processType)
    {
        processType.setCreateTime(DateUtils.getNowDate());
        return processTypeMapper.insertProcessType(processType);
    }

    /**
     * 修改审批类型
     * 
     * @param processType 审批类型
     * @return 结果
     */
    @Override
    public int updateProcessType(ProcessType processType)
    {
        processType.setUpdateTime(DateUtils.getNowDate());
        return processTypeMapper.updateProcessType(processType);
    }

    /**
     * 批量删除审批类型
     * 
     * @param ids 需要删除的审批类型主键
     * @return 结果
     */
    @Override
    public int deleteProcessTypeByIds(Long[] ids)
    {
        return processTypeMapper.deleteProcessTypeByIds(ids);
    }

    /**
     * 删除审批类型信息
     * 
     * @param id 审批类型主键
     * @return 结果
     */
    @Override
    public int deleteProcessTypeById(Long id)
    {
        return processTypeMapper.deleteProcessTypeById(id);
    }
}
