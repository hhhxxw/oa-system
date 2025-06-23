package com.tdt.activiti;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author 一只咸鱼的大厂梦-hxw
 * @date 2025-06-19 17:24
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProcessTest {
    @Autowired
    private RepositoryService repositoryService;
    @Test
    public void deployProcess(){
        // 找到指定文件进行部署-流程部署

        Deployment deploy = repositoryService.createDeployment()
                .addClasspathResource("process/qingjia.bpmn20.xml")
                .addClasspathResource("process/qingjia.png")
                .name("请假申请流程")
                .deploy();
        System.out.println(deploy.getId());
        System.out.println(deploy.getName());
    }

}
