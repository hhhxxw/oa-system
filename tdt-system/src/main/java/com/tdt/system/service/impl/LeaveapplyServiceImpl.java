package com.tdt.system.service.impl;

import com.tdt.common.core.text.Convert;
import com.tdt.system.domain.Leaveapply;
import com.tdt.system.mapper.LeaveapplyMapper;
import com.tdt.system.service.ILeaveapplyService;
import org.flowable.engine.HistoryService;
import org.flowable.engine.IdentityService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.history.HistoricProcessInstance;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * @author 一只咸鱼的大厂梦-hxw
 * @date 2025-06-03 09:36
 */
@Service
@Transactional
public class LeaveapplyServiceImpl implements ILeaveapplyService
{
    @Resource
    private LeaveapplyMapper leaveapplyMapper;

    @Resource
    private RuntimeService runtimeService;

    @Resource
    private TaskService taskService;

    @Resource
    IdentityService identityService;

    @Resource
    HistoryService historyService;

    /**
     * 查询请假
     *
     * @param id 请假主键
     * @return 请假
     */
    @Override
    public Leaveapply selectLeaveapplyById(Long id)
    {
        return leaveapplyMapper.selectLeaveapplyById(id);
    }

    /**
     * 查询请假列表
     *
     * @param leaveapply 请假
     * @return 请假
     */
    @Override
    public List<Leaveapply> selectLeaveapplyList(Leaveapply leaveapply)
    {
        return leaveapplyMapper.selectLeaveapplyList(leaveapply);
    }

    /**
     * 新增请假
     *
     * @param leaveapply 请假
     * @return 结果
     */
    @Override
    public int insertLeaveapply(Leaveapply leaveapply)
    {
        // 将leaveapply对象存储到数据库，rows表示数据库操作影响的行数，用于判断是否成功
        int rows = leaveapplyMapper.insertLeaveapply(leaveapply);
        // 设置流程发起人，记录流程的操作者（请假人）
        identityService.setAuthenticatedUserId(leaveapply.getUserId());
        //将业务数据注入流程遍历，供流程结点使用
        HashMap<String, Object> variables = new HashMap<>();
        variables.put("applyuserid", leaveapply.getUserId());
        variables.put("deptleader", leaveapply.getDeptleader());
        // 通过runtimeService启动流程定义键为“leave”的流程实例
        runtimeService.startProcessInstanceByKey("leave", String.valueOf(leaveapply.getId()), variables);
        // 自动完成第一个任务
        Task autoTask = taskService.createTaskQuery().processDefinitionKey("leave").processInstanceBusinessKey(String.valueOf(leaveapply.getId())).singleResult();
        taskService.complete(autoTask.getId());
        return rows;
    }

    /**
     * 修改请假
     *
     * @param leaveapply 请假
     * @return 结果
     */
    @Override
    public int updateLeaveapply(Leaveapply leaveapply)
    {
        return leaveapplyMapper.updateLeaveapply(leaveapply);
    }

    /**
     * 批量删除请假
     *
     * @param ids 需要删除的请假主键
     * @return 结果
     */
    @Override
    public int deleteLeaveapplyByIds(String ids)
    {
        String[] keys = Convert.toStrArray(ids);
        for (String key : keys) {
            ProcessInstance process = runtimeService.createProcessInstanceQuery().processDefinitionKey("leave").processInstanceBusinessKey(key).singleResult();
            if (process != null) {
                runtimeService.deleteProcessInstance(process.getId(), "删除");
            }
            // 删除历史数据
            HistoricProcessInstance history = historyService.createHistoricProcessInstanceQuery().processDefinitionKey("leave").processInstanceBusinessKey(key).singleResult();
            if (history != null){
                historyService.deleteHistoricProcessInstance(history.getId());
            }
            leaveapplyMapper.deleteLeaveapplyById(Long.parseLong(key));
        }
        return keys.length;
    }

    /**
     * 删除请假信息
     *
     * @param id 请假主键
     * @return 结果
     */
    @Override
    public int deleteLeaveapplyById(Long id)
    {
        return leaveapplyMapper.deleteLeaveapplyById(id);
    }
}
