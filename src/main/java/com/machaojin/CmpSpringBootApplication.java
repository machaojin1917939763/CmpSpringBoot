package com.machaojin;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author machaojin
 */
@Slf4j
@SpringBootApplication
@MapperScan("com.machaojin.mapper")
@EnableTransactionManagement //开启事物的注解支持
@EnableAsync(proxyTargetClass=true)//事物的支持
public class CmpSpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(CmpSpringBootApplication.class, args);
        log.info("项目启动。。。。。");

    }

}
