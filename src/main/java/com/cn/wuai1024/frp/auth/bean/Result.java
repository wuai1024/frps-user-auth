package com.cn.wuai1024.frp.auth.bean;

import cn.hutool.json.JSONObject;
import lombok.extern.log4j.Log4j2;

/**
 * @author 杨康
 * @version 1.0
 * @date 2024-05-28 15:23:41
 */
@Log4j2
public class Result {

    public static Object fail(String failInfo) {
        JSONObject jsonObject = new JSONObject().set("reject", true).set("reject_reason", failInfo);
        log.error(() -> jsonObject);
        return jsonObject;
    }

    public static Object success() {
        JSONObject jsonObject = new JSONObject().set("reject", false).set("unchange", true);
        log.info(() -> jsonObject);
        return jsonObject;
    }

    public static Object success(Object content) {
        JSONObject jsonObject = new JSONObject().set("unchange", false).set("content", content);
        log.info(() -> jsonObject);
        return jsonObject;
    }
}
