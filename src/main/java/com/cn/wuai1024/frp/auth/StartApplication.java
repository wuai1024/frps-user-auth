package com.cn.wuai1024.frp.auth;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author 杨康
 * @version 1.0
 * @date 2024-05-28 14:53:30
 */
@Log4j2
@SpringBootApplication
public class StartApplication {

    public static void main(String[] args) {
        SpringApplication.run(StartApplication.class, args);
        log.info("Run the container with \"-v /host/path/to/application.yml:/app/config/config.yml\" to map host configuration file");
    }
}
