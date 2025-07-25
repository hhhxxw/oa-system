package com.tdt;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * 启动程序
 * @author ruoyi
 */
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class})
public class TdtApplication
{
    public static void main(String[] args)
    {
        // System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication.run(TdtApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  天地图管理系统启动成功   ლ(´ڡ`ლ)ﾞ  \n");
    }

}
