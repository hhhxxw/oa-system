package com.tdt.web.controller.followable;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import com.tdt.common.core.controller.BaseController;
import com.tdt.common.core.domain.AjaxResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.IOUtils;
import org.flowable.engine.ProcessEngineConfiguration;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipInputStream;

/**
 * 流程管理
 */
@Api(value = "部署管理接口")
@Controller
@RequestMapping("/flow/manage")
public class FlowController extends BaseController {

    @Resource
    private RuntimeService runtimeService;

    @Resource
    private TaskService taskService;

    @Resource
    RepositoryService repositoryService;

    @Resource
    ProcessEngineConfiguration configuration;

    private String prefix = "flowable/manage";

    @GetMapping("")
    public String processList()
    {
        return prefix + "/processList";
    }

    @GetMapping("deploy")
    public String deploy()
    {
        return prefix + "/deployProcess";
    }

    @ApiOperation("上传一个工作流文件")
    @RequestMapping(value = "/uploadworkflow", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult fileupload(@RequestParam MultipartFile uploadfile) {
        try {
            String filename = uploadfile.getOriginalFilename();
            InputStream is = uploadfile.getInputStream();
            if (filename.endsWith("zip")) {
                repositoryService.createDeployment().name(filename).addZipInputStream(new ZipInputStream(is)).deploy();
            } else if (filename.endsWith("bpmn") || filename.endsWith("xml")) {
                repositoryService.createDeployment().name(filename).addInputStream(filename, is).deploy();
            } else {
                return AjaxResult.error("文件格式错误");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.error("部署失败");
        }
        return AjaxResult.success("部署成功");
    }
}
