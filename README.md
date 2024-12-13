### frps-user-auth

##### 本项目是一个可以实现`frps`多租户鉴权的项目，可以很方便的是用一个`frps`服务端支持多个租户，并且可以给其中某个租户设置过期日期。

### 配置文件

- 创建配置文件

```bash
touch config.yml
```

- 配置文件参数

```yaml
frps:
  users:
    # 用户1
    - user: ""
      # token
      token: ""
    # 用户2
    - user: ""
      # token
      token: ""
      # 过期日期
      expirationDate: "yyyy-MM-dd"
```

### 运行服务

```bash
docker run -d \
  --name frps-auth-users \
  --restart=always \
  -p 9000:9000 \
  -v $(pwd)/frps-auth-users/config/config.yml:/app/config/config.yml \
  -v $(pwd)/frps-auth-users/logs:/app/logs \
  -e PARAMS="--spring.config.additional-location=file:///app/config/config.yml" \
  ghcr.io/wuai1024/frps-user-auth:latest
```

### 插件配置

```bash
bindPort = 7000

[[httpPlugins]]
name = "user-manager"
addr = "127.0.0.1:9000"
path = "/handler"
ops = ["Login"]
```
