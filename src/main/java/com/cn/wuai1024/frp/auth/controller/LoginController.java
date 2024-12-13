package com.cn.wuai1024.frp.auth.controller;

import cn.hutool.json.JSONUtil;
import com.cn.wuai1024.frp.auth.bean.Result;
import com.cn.wuai1024.frp.auth.service.LoginService;
import jakarta.annotation.Resource;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

/**
 * @author 杨康
 * @version 1.0
 * @date 2024-05-28 14:54:18
 */
@Log4j2
@RestController
public class LoginController {

    @Resource
    private LoginService loginService;

    @PostMapping("/handler")
    public Object handler(String version, String op, @RequestBody String content) {
        log.info("version:{} op:{} content:{}", version, op, JSONUtil.toJsonStr(content));
        if (Objects.equals("Login", op)) {
            return loginService.login(content);
        } else {
            return Result.fail("op is not supported");
        }
    }
}
