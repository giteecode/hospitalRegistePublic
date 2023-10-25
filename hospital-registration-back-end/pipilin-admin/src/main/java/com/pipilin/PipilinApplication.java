package com.pipilin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * 启动程序
 * 
 * @author  2891517520@qq.com
 */
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class PipilinApplication
{
    public static void main(String[] args)
    {
        // System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication.run(PipilinApplication.class, args);
        System.out.println("PipilinApplication start success");
    }
}
