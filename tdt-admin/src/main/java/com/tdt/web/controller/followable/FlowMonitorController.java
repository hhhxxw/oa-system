package com.tdt.web.controller.followable;

/**
 * @author 一只咸鱼的大厂梦-hxw
 * @date 2025-06-03 08:45
 */

import com.tdt.common.core.controller.BaseController;
import com.tdt.common.core.domain.AjaxResult;
import com.tdt.common.core.page.TableDataInfo;
import com.tdt.common.utils.StringUtils;
import com.tdt.common.utils.bean.BeanUtils;
import com.tdt.system.domain.*;
import com.tdt.system.mapper.ActRuExecutionMapper;
import com.tdt.web.controller.util.ActivitiTracingChart;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.flowable.bpmn.model.BpmnModel;
import org.flowable.bpmn.model.FlowElement;
import org.flowable.engine.*;
import org.flowable.engine.history.HistoricActivityInstance;
import org.flowable.engine.history.HistoricProcessInstance;
import org.flowable.engine.history.HistoricProcessInstanceQuery;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.engine.runtime.ProcessInstanceQuery;
import org.flowable.engine.task.Comment;
import org.flowable.job.api.*;
import org.flowable.task.api.Task;
import org.flowable.variable.api.history.HistoricVariableInstance;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 流程监控
 */
@Api(value = "流程监控接口")
@Controller
@RequestMapping("/flow/monitor")
public class FlowMonitorController extends BaseController {

    @Resource
    private RuntimeService runtimeService;

    @Resource
    private TaskService taskService;

    @Resource
    private HistoryService historyService;

    @Resource
    ManagementService managementService;

    @Resource
    RepositoryService repositoryService;

    @Resource
    ProcessEngineConfiguration configuration;

    @Resource
    private ActivitiTracingChart activitiTracingChart;

    @Resource
    ActRuExecutionMapper actRuExecutionMapper;


    private String prefix = "flowable/monitor";

    @GetMapping("/instance")
    public String processList() {
        return prefix + "/processInstance";
    }

    @GetMapping("/history")
    public String processHistory() {
        return prefix + "/processHistory";
    }

    @GetMapping("/execution")
    public String execution() {
        return prefix + "/execution";
    }

    @GetMapping("/historyDetail")
    public String historyDetail(String processInstanceId, ModelMap mmap) {
        mmap.put("processInstanceId", processInstanceId);
        return prefix + "/processHistoryDetail";
    }

    @GetMapping("/processVariablesDetail")
    public String processVariablesDetail(String processInstanceId, ModelMap mmap) {
        mmap.put("processInstanceId", processInstanceId);
        return prefix + "/processVariablesDetail";
    }

    @ApiOperation("查询所有正在运行的流程实例列表")
    @RequestMapping(value = "/listProcess", method = RequestMethod.POST)
    @ResponseBody
    public TableDataInfo getlist(@RequestParam(required = false) String bussinesskey, @RequestParam(required = false) String name,
                                 Integer pageSize, Integer pageNum) {
        int start = (pageNum - 1) * pageSize;
        ProcessInstanceQuery condition = runtimeService.createProcessInstanceQuery();
        if (StringUtils.isNotEmpty(bussinesskey)) {
            condition.processInstanceBusinessKey(bussinesskey);
        }
        if (StringUtils.isNotEmpty(name)) {
            condition.processDefinitionName(name);
        }
        int total = condition.orderByProcessDefinitionId().desc().list().size();
        List<ProcessInstance> processList = condition.orderByProcessDefinitionId().desc().listPage(start, pageSize);
        List<FlowInfo> flows = new ArrayList<>();
        processList.stream().forEach(p -> {
            FlowInfo info = new FlowInfo();
            info.setProcessInstanceId(p.getProcessInstanceId());
            info.setBusinessKey(p.getBusinessKey());
            info.setName(p.getProcessDefinitionName());
            info.setStartTime(p.getStartTime());
            info.setStartUserId(p.getStartUserId());
            info.setSuspended(p.isSuspended());
            info.setEnded(p.isEnded());
            // 查看当前活动任务
            List<Task> tasks =  taskService.createTaskQuery().processInstanceId(p.getProcessInstanceId()).list();
            String taskName = "";
            String assignee = "";
            for (Task t : tasks) {
                taskName += t.getName() + ",";
                assignee += t.getAssignee() + ",";
            }
            taskName = taskName.substring(0, taskName.length() -1);
            assignee = assignee.substring(0, assignee.length() -1);
            info.setCurrentTask(taskName);
            info.setAssignee(assignee);
            flows.add(info);
        });
        TableDataInfo rspData = new TableDataInfo();
        rspData.setCode(0);
        rspData.setRows(flows);
        rspData.setTotal(total);
        return rspData;
    }

    @ApiOperation("查询所有流程实例列表-包含在运行和已结束")
    @RequestMapping(value = "/listHistoryProcess", method = RequestMethod.POST)
    @ResponseBody
    public TableDataInfo listHistoryProcess(@RequestParam(required = false) String bussinesskey, @RequestParam(required = false) String name,
                                            Integer pageSize, Integer pageNum) {
        int start = (pageNum - 1) * pageSize;
        HistoricProcessInstanceQuery condition = historyService.createHistoricProcessInstanceQuery();
        if (StringUtils.isNotEmpty(bussinesskey)) {
            condition.processInstanceBusinessKey(bussinesskey);
        }
        if (StringUtils.isNotEmpty(name)) {
            condition.processDefinitionName(name);
        }
        int total = condition.orderByProcessInstanceStartTime().desc().list().size();
        List<HistoricProcessInstance> processList = condition.orderByProcessInstanceStartTime().desc().listPage(start, pageSize);
        List<FlowInfo> flows = new ArrayList<>();
        processList.stream().forEach(p -> {
            FlowInfo info = new FlowInfo();
            info.setProcessInstanceId(p.getId());
            info.setBusinessKey(p.getBusinessKey());
            info.setName(p.getProcessDefinitionName());
            info.setStartTime(p.getStartTime());
            info.setEndTime(p.getEndTime());
            info.setStartUserId(p.getStartUserId());
            if (p.getEndTime() == null) {
                info.setEnded(false);
                // 查看当前活动任务
                List<Task> tasks =  taskService.createTaskQuery().processInstanceId(p.getId()).list();
                String taskName = "";
                String assignee = "";
                for (Task t : tasks) {
                    taskName += t.getName() + ",";
                    assignee += t.getAssignee() + ",";
                }
                taskName = taskName.substring(0, taskName.length() -1);
                assignee = assignee.substring(0, assignee.length() -1);
                info.setCurrentTask(taskName);
                info.setAssignee(assignee);
            } else {
                info.setEnded(true);
            }
            flows.add(info);
        });
        TableDataInfo rspData = new TableDataInfo();
        rspData.setCode(0);
        rspData.setRows(flows);
        rspData.setTotal(total);
        return rspData;
    }

    @ApiOperation("查询一个流程的活动历史")
    @RequestMapping(value = "/history/{processInstanceId}", method = RequestMethod.POST)
    @ResponseBody
    public TableDataInfo history(@PathVariable String processInstanceId, Integer pageSize, Integer pageNum) {
        int start = (pageNum - 1) * pageSize;
        List<HistoricActivityInstance> history = historyService.createHistoricActivityInstanceQuery().processInstanceId(processInstanceId).activityType("userTask").orderByHistoricActivityInstanceStartTime().asc().listPage(start, pageSize);
        int total = historyService.createHistoricActivityInstanceQuery().processInstanceId(processInstanceId).activityType("userTask").orderByHistoricActivityInstanceStartTime().asc().list().size();
        List<TaskInfo> infos  = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        history.stream().forEach(h->{
            com.tdt.system.domain.TaskInfo info = new com.tdt.system.domain.TaskInfo();
            info.setProcessInstanceId(h.getProcessInstanceId());
            info.setStartTime(sdf.format(h.getStartTime()));
            if (h.getEndTime() != null) {
                info.setEndTime(sdf.format(h.getEndTime()));
            }
            info.setAssignee(h.getAssignee());
            info.setTaskName(h.getActivityName());
            List<Comment> comments = taskService.getTaskComments(h.getTaskId());
            if (comments.size() > 0) {
                info.setComment(comments.get(0).getFullMessage());
            }
            infos.add(info);
        });
        TableDataInfo rspData = new TableDataInfo();
        rspData.setCode(0);
        rspData.setRows(infos);
        rspData.setTotal(total);
        return rspData;
    }

    @ApiOperation("查询所有正在运行的执行实例列表")
    @RequestMapping(value = "/listExecutions", method = RequestMethod.POST)
    @ResponseBody
    public List<FlowInfo> listExecutions(@RequestParam(required = false) String name) {
        List<ActRuExecution> executionList = actRuExecutionMapper.selectActRuExecutionListByProcessName(name);
        List<FlowInfo> flows = new ArrayList<>();
        executionList.stream().forEach(p -> {
            FlowInfo info = new FlowInfo();
            info.setProcessInstanceId(p.getProcInstId());
            if (p.getSuspensionState() == 1L) {
                info.setSuspended(false);
            } else {
                info.setSuspended(true);
            }
            if (p.getIsActive() == 0) {
                info.setActive(false);
            } else {
                info.setActive(true);
            }
            if (p.getActId() != null) {
                ProcessInstance process = runtimeService.createProcessInstanceQuery().processInstanceId(p.getProcInstId()).singleResult();
                BpmnModel bpmnModel = repositoryService.getBpmnModel(process.getProcessDefinitionId());
                Map<String, FlowElement> nodes = bpmnModel.getMainProcess().getFlowElementMap();
                info.setCurrentTask(nodes.get(p.getActId()).getName());
                info.setName(process.getProcessDefinitionName());
            } else {
                ProcessInstance process = runtimeService.createProcessInstanceQuery().processInstanceId(p.getProcInstId()).singleResult();
                info.setStartTime(process.getStartTime());
                info.setStartUserId(process.getStartUserId());
                info.setName(process.getProcessDefinitionName());
                List<Task> tasks =  taskService.createTaskQuery().processInstanceId(p.getProcInstId()).list();
                String taskName = "";
                for (Task t : tasks) {
                    taskName += t.getName() + ",";
                }
                taskName = taskName.substring(0, taskName.length() -1);
                info.setCurrentTask(taskName);
            }
            info.setStartTime(p.getStartTime());
            info.setExecutionId(p.getId());
            if (p.getParentId() == null) {
                info.setParentExecutionId("0");
            } else {
                info.setParentExecutionId(p.getParentId());
            }
            flows.add(info);
        });
        return flows;
    }

    @ApiOperation("流程图进度追踪")
    @RequestMapping(value = {"/traceProcess/{processInstanceId}"}, method = RequestMethod.GET)
    public void traceprocess(@PathVariable String processInstanceId, HttpServletResponse response) throws IOException {
        response.setContentType("image/jpeg;charset=UTF-8");
        response.setHeader("Content-Disposition", "inline; filename= trace.png");
        activitiTracingChart.generateFlowChart(processInstanceId, response.getOutputStream());
    }

    @ApiOperation("挂起一个流程实例")
    @RequestMapping(value = "/suspend/{processInstanceId}", method = RequestMethod.GET)
    @ResponseBody
    public AjaxResult suspend(@PathVariable String processInstanceId) {
        runtimeService.suspendProcessInstanceById(processInstanceId);
        return AjaxResult.success();
    }

    @ApiOperation("唤醒一个挂起的流程实例")
    @RequestMapping(value = "/run/{processInstanceId}", method = RequestMethod.GET)
    @ResponseBody
    public AjaxResult rerun(@PathVariable String processInstanceId) {
        runtimeService.activateProcessInstanceById(processInstanceId);
        return AjaxResult.success();
    }

    @ApiOperation("查询一个流程的变量")
    @RequestMapping(value = "/variables/{processInstanceId}", method = RequestMethod.POST)
    @ResponseBody
    public TableDataInfo variables(@PathVariable String processInstanceId, Integer pageSize, Integer pageNum) {
        int start = (pageNum - 1) * pageSize;
        List<HistoricVariableInstance> variables = historyService.createHistoricVariableInstanceQuery().processInstanceId(processInstanceId).orderByVariableName().asc().listPage(start, pageSize);
        int total = historyService.createHistoricVariableInstanceQuery().processInstanceId(processInstanceId).orderByVariableName().asc().list().size();
        List<VariableInfo> infos = new ArrayList<>();
        variables.forEach(v->{
            VariableInfo info = new VariableInfo();
            BeanUtils.copyBeanProp(info, v);
            if (v.getValue() != null) {
                info.setValue(v.getValue().toString());
            }
            infos.add(info);
        });
        TableDataInfo rspData = new TableDataInfo();
        rspData.setCode(0);
        rspData.setRows(infos);
        rspData.setTotal(total);
        return rspData;
    }

    @ApiOperation("按类型查询所有的作业列表:定时作业、异步作业、挂起作业、死亡作业")
    @PostMapping(value = "/listJobs")
    @ResponseBody
    public TableDataInfo listJobs(@RequestParam(required = false) String processDefinitionId, @RequestParam(required = false) String startDate,
                                  @RequestParam(required = false) String endDate,@RequestParam Integer type, Integer pageSize, Integer pageNum) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        int start = (pageNum - 1) * pageSize;
        int total = 0;
        List<Job> jobList = null;
        ArrayList<DeadLetterJob> jobs = new ArrayList<>();
        TableDataInfo rspData = new TableDataInfo();
        if (type == 1) {
            // 定时作业
            TimerJobQuery condition = managementService.createTimerJobQuery();
            if (StringUtils.isNotEmpty(processDefinitionId)) {
                condition.processDefinitionId(processDefinitionId);
            }
            if (StringUtils.isNotEmpty(startDate)) {
                condition.duedateHigherThan(sdf.parse(startDate));
            }
            if (StringUtils.isNotEmpty(endDate)) {
                condition.duedateLowerThan(sdf.parse(endDate));
            }
            total = condition.orderByJobDuedate().desc().list().size();
            jobList = condition.orderByJobDuedate().desc().listPage(start, pageSize);
            rspData.setRows(jobList);
        } else if (type == 2) {
            // 异步作业
            JobQuery condition = managementService.createJobQuery();
            if (StringUtils.isNotEmpty(processDefinitionId)) {
                condition.processDefinitionId(processDefinitionId);
            }
            if (StringUtils.isNotEmpty(startDate)) {
                condition.duedateHigherThan(sdf.parse(startDate));
            }
            if (StringUtils.isNotEmpty(endDate)) {
                condition.duedateLowerThan(sdf.parse(endDate));
            }
            total = condition.orderByJobDuedate().desc().list().size();
            jobList = condition.orderByJobDuedate().desc().listPage(start, pageSize);
            rspData.setRows(jobList);
        } else if (type == 3) {
            // 挂起作业
            SuspendedJobQuery condition = managementService.createSuspendedJobQuery();
            if (StringUtils.isNotEmpty(processDefinitionId)) {
                condition.processDefinitionId(processDefinitionId);
            }
            if (StringUtils.isNotEmpty(startDate)) {
                condition.duedateHigherThan(sdf.parse(startDate));
            }
            if (StringUtils.isNotEmpty(endDate)) {
                condition.duedateLowerThan(sdf.parse(endDate));
            }
            total = condition.orderByJobDuedate().desc().list().size();
            jobList = condition.orderByJobDuedate().desc().listPage(start, pageSize);
            rspData.setRows(jobList);
        } else if (type == 4) {
            // 死亡作业
            DeadLetterJobQuery condition = managementService.createDeadLetterJobQuery();
            if (StringUtils.isNotEmpty(processDefinitionId)) {
                condition.processDefinitionId(processDefinitionId);
            }
            if (StringUtils.isNotEmpty(startDate)) {
                condition.duedateHigherThan(sdf.parse(startDate));
            }
            if (StringUtils.isNotEmpty(endDate)) {
                condition.duedateLowerThan(sdf.parse(endDate));
            }
            total = condition.orderByJobDuedate().desc().list().size();
            jobList = condition.orderByJobDuedate().desc().listPage(start, pageSize);

            jobList.forEach(j->{
                DeadLetterJob job = new DeadLetterJob();
                job.setId(j.getId());
                job.setDueDate(j.getDuedate());
                job.setJobType(j.getJobType());
                job.setExceptionMessage(j.getExceptionMessage());
                job.setJobHandlerType(j.getJobHandlerType());
                job.setProcessDefId(j.getProcessDefinitionId());
                job.setProcessInstanceId(j.getProcessInstanceId());
                job.setExecutionId(j.getExecutionId());
                jobs.add(job);
            });
            rspData.setRows(jobs);
        }
        rspData.setCode(0);
        rspData.setTotal(total);
        return rspData;
    }

}
