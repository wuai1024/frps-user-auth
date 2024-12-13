package com.cn.wuai1024.frp.auth.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.cn.wuai1024.frp.auth.bean.LoginRequest;
import com.cn.wuai1024.frp.auth.bean.Result;
import com.cn.wuai1024.frp.auth.properties.FrpsProperties;
import com.cn.wuai1024.frp.auth.service.LoginService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author 杨康
 * @version 1.0
 * @date 2024-05-28 15:49:18
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Resource
    private FrpsProperties frpsProperties;

    @Override
    public Object login(String content) {
        LoginRequest loginRequest = JSONUtil.toBean(content, LoginRequest.class);
        String user = loginRequest.getContent().getUser();
        Map<String, String> metas = loginRequest.getContent().getMetas();
        if (Objects.isNull(metas) || !metas.containsKey("token")) {
            return Result.fail("client config is not metadatas.token!");
        }
        String token = metas.get("token");

        if (Objects.isNull(frpsProperties) || Objects.isNull(frpsProperties.getUsers())) {
            return Result.fail("Please configure the account information.");
        }

        if (frpsProperties.getUsers().isEmpty()) {
            return Result.fail("Please register the account information to be logged in on the server.");
        }

        Map<String, FrpsProperties.User> collect = frpsProperties.getUsers()
                .stream()
                .filter(item -> StrUtil.equals(user, item.getUser()) && StrUtil.equals(token, item.getToken()))
                .collect(Collectors.toMap(FrpsProperties.User::getUser, item -> item));

        if (!Objects.isNull(collect.get(user))) {
            FrpsProperties.User loginUser = collect.get(user);
            if (StrUtil.isNotBlank(loginUser.getExpirationDate())) {
                // 比较给定日期与当前日期
                int comparisonResult = LocalDate.parse(loginUser.getExpirationDate()).compareTo(LocalDate.now());
                if (comparisonResult < 0) {
                    return Result.fail("The current login account has expired, please renew.");
                }
            }
            return Result.success();
        }
        return Result.fail("Login fail The account or password is incorrect");
    }
}
