package com.cn.wuai1024.frp.auth.properties;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author 杨康
 * @version 1.0
 * @date 2024-05-28 16:09:30
 */
@Getter
@Setter
@Log4j2
@Component
@ConfigurationProperties(prefix = "frps")
public class FrpsProperties {

    private List<User> users;

    @PostConstruct
    public void init() {
        if (users == null || users.isEmpty()) {
            log.warn("No users loaded!");
        } else {
            users.forEach(u -> log.debug("Loaded user:{}", u.getUser()));
        }
    }

    @Getter
    @Setter
    public static class User {

        private String user;
        private String token;
        private String expirationDate;
    }
}
