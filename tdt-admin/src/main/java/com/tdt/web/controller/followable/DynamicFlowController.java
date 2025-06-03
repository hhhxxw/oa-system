package com.tdt.web.controller.followable;

import com.tdt.common.core.domain.AjaxResult;
import com.tdt.common.utils.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.flowable.bpmn.model.*;
import org.flowable.engine.HistoryService;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.runtime.Execution;
import org.flowable.task.api.Task;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author 一只咸鱼的大厂梦-hxw
 * @date 2025-06-03 08:45
 */
@Api(value = "动态流程接口")
@Controller
@RequestMapping("/dynamic/flow")
public class DynamicFlowController {
    @Resource
    private RuntimeService runtimeService;

    @Resource
    private TaskService taskService;

    @Resource
    RepositoryService repositoryService;

    @Resource
    private HistoryService historyService;

    @ApiOperation("遍历流程信息")
    @GetMapping(value = "/info/{processInstanceId}")
    @ResponseBody
    public AjaxResult remove(@PathVariable String processInstanceId) {
        String processDefinitionId = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult().getProcessDefinitionId();
        BpmnModel bpmnModel = repositoryService.getBpmnModel(processDefinitionId);
        Collection<FlowElement> flowElements = bpmnModel.getMainProcess().getFlowElements();
        for (FlowElement flowElement : flowElements) {
            if (flowElement instanceof UserTask) {
                UserTask userTask = (UserTask) flowElement;
                System.out.println(flowElement.getName());
                System.out.println(flowElement.getId());
                System.out.println(userTask.getAssignee());
                String assigneeEl = userTask.getAssignee();
                if (StringUtils.isBlank(assigneeEl)) {
                    continue;
                }
                if (assigneeEl.startsWith("${") && assigneeEl.endsWith("}") && assigneeEl.length() > 3) {
                    String assignee = assigneeEl.substring(2, assigneeEl.length() - 2);
                    System.out.println("assignee:" + assignee);
                }
            }
        }
        return AjaxResult.success(flowElements);
    }
    @ApiOperation("撤销:强制结束一个流程")
    @GetMapping(value = "/forceEnd/{taskId}")
    @ResponseBody
    public AjaxResult forceEnd(@PathVariable String taskId) {
        Task t = taskService.createTaskQuery().taskId(taskId).singleResult();
        String processDefinitionId = runtimeService.createProcessInstanceQuery().processInstanceId(t.getProcessInstanceId()).singleResult().getProcessDefinitionId();
        BpmnModel bpmnModel = repositoryService.getBpmnModel(processDefinitionId);
        // 寻找流程实例当前任务的activeId
        Execution execution = runtimeService.createExecutionQuery().executionId(t.getExecutionId()).singleResult();
        String activityId = execution.getActivityId();
        FlowNode currentNode = (FlowNode)bpmnModel.getMainProcess().getFlowElement(activityId);
        // 创建结束节点和连接线
        EndEvent end = new EndEvent();
        end.setName("强制结束");
        end.setId("forceEnd");
        List<SequenceFlow> newSequenceFlowList = new ArrayList<SequenceFlow>();
        SequenceFlow newSequenceFlow = new SequenceFlow();
        newSequenceFlow.setId("newFlow");
        newSequenceFlow.setSourceFlowElement(currentNode);
        newSequenceFlow.setTargetFlowElement(end);
        newSequenceFlowList.add(newSequenceFlow);
        // 备份原有方向
        List<SequenceFlow> dataflows = currentNode.getOutgoingFlows();
        List<SequenceFlow> oriSequenceFlows = new ArrayList<SequenceFlow>();
        oriSequenceFlows.addAll(dataflows);
        // 清空原有方向
        currentNode.getOutgoingFlows().clear();
        // 设置新方向
        currentNode.setOutgoingFlows(newSequenceFlowList);
        // 完成当前任务
        taskService.addComment(taskId, t.getProcessInstanceId(), "comment", "撤销流程");
        taskService.complete(taskId);
        // 恢复原有方向
        currentNode.setOutgoingFlows(oriSequenceFlows);
        return AjaxResult.success();
    }



}
