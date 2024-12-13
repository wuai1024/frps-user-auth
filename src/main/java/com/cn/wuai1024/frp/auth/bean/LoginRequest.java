package com.cn.wuai1024.frp.auth.bean;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

/**
 * @author 杨康
 * @version 1.0
 * @date 2024-05-28 15:44:44
 */
@Getter
@Setter
public class LoginRequest {

    private String version;
    private String op;
    private Content content;

    @Getter
    @Setter
    public static class Content {

        private String version;
        private String os;
        private String arch;
        private String user;
        private String privilegeKey;
        private long timestamp;
        private Map<String, String> metas;
        private Map<String, Object> clientSpec;
        private int poolCount;
        private String clientAddress;
    }
}
