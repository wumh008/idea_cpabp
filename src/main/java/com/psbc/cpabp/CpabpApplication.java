package com.psbc.cpabp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileNotFoundException;

@Slf4j
//@SpringBootApplication(exclude = com.psbc.cpabp.config.DataSourceConfig.class)  //来标注一个主程序类，说明这是一个Spring Boot应用
//@ComponentScan(basePackages = {"com.psbc.cpabp.controller","com.psbc.cpabp.service"})
@SpringBootApplication
public class CpabpApplication {

    //Spring应用启动入口
    public static void main(String[] args) throws FileNotFoundException {
        log.info("CpabpApplication begin......");
        SpringApplication.run(CpabpApplication.class, args);
        log.info("CpabpApplication ready......");
        //final File basePath = new File(ResourceUtils.getURL("classpath:").getPath());
        //log.info("classpath:" + basePath);
    }

}
