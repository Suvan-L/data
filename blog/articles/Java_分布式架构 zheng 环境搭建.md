---
title: 分布式架构 zheng 环境搭建
date: 2018-03-26 00:19:55
tags: Java
categories: Java
---


目录
========================
1. 项目介绍
2. 环境搭建 - 运行
3. 错误记录



## 1. 项目介绍
- 基于Spring+SpringMVC+Mybatis分布式敏捷开发系统架构，提供整套公共微服务服务模块：集中权限管理（单点登录）、内容管理、支付中心、用户管理（支持第三方登录）、微信平台、存储系统、配置中心、日志分析、任务和通知等，支持服务治理、监控和追踪，努力为中小型企业打造全方位J2EE企业级开发解决方案。
- [GitHub 地址](https://github.com/shuzheng/zheng)


## 2. 环境搭建 - 运行
1. 下载项目，参考文档`..\zheng-master\README.md`
  - 需具备以下环境
    - java jdk 1.7+
    - Mysql（开启系统服务，脚本导入可用图形工具：Navicat Premium）
    - Redis（windows 版若不存在 redis.conf 配置文件，需网上下载，然配置密码`requirepass liushuwei`）
    - Zookeeper(建议根据文档用 3.5.2-alpha 版本)
    - ActiveMQ（建议用 5.9.0 版本）
    - Nginx（需将 `..\zheng-master\project-tools\nginx\nginx.conf` 的配置文件替换安装目录 conf 目录内的相同文件）（nginx.exe 启动服务，无界面，通过 nginx -s quit 命令启动，可配置 bat 脚本）
2. 新建数据库 zheng，安装脚本
  - 执行 sql 脚本 `..\zheng-master\project-datamodel\zheng.sql`
  - 自动建表并插入测试数据
3. idea 导入 Maven 项目，并在 "Maven Project" 插件栏里
  - 找到 'zheng' 模块 -> Lifecycle -> install 安装依赖
4. 修改配置文件
  - 使用 AES 加密类的主函数，生成数据用户密码的密文 `..\zheng-master\zheng-common\src\main\java\com\zheng\common\util\AESUtil.java`
  - 修改配置文件，使用 idea 的全局搜索(Ctrl + Shift + n)（安装 Ctrl + Shift 然后移动方向键，可多选，同时打开多个文件），搜索以下文件类型`redis.properties` ，`jdbc.properties`, `generator.properties`， 将包含 username 和 password 的地方，改成相应的用户名和密文密码（Ctrl + Shift + r）全局内容替换，可检索出相同的地方，一键替换
5. 修改本地 host 文件（默认路径：C:\Windows\System32\drivers\etc\hosts），打开文件底部追加内容（若无法保存，可考虑 host 复制到另一地方，然后修改，然后再替换）
```
#zheng project
127.0.0.1 upms.zhangshuzheng.cn
127.0.0.1 cms.zhangshuzheng.cn
127.0.0.1 pay.zhangshuzheng.cn
127.0.0.1 ucenter.zhangshuzheng.cn
127.0.0.1 wechat.zhangshuzheng.cn
127.0.0.1 api.zhangshuzheng.cn
127.0.0.1 oss.zhangshuzheng.cn

127.0.0.1 zkserver
127.0.0.1 rdserver
127.0.0.1 dbserver
127.0.0.1 mqserver
```
6. 启动顺序
  - a. Mysql，Redis，Zookeeper， ActiveMQ，Nginx
  - b. zheng-upms-rpc-service 模块下的通用服务启动类 `..\zheng-master\zheng-upms\zheng-upms-rpc-service\src\main\java\com\zheng\upms\rpc\ZhengUpmsRpcServiceApplication.java` 
  - c. zheng-cms-rpc-service 模块下权限内容服务启动类 `..\zheng-master\zheng-cms\zheng-cms-rpc-service\src\main\java\com\zheng\cms\rpc\ZhengCmsRpcServiceApplication.java`
  - d. Maven Project 内 zheng-upms-server 模块的 Plugins 的 jetty 插件的 `jetty:run`，右键 Run
  - e. Maven Project 内 zheng-cms-admin 模块下 Plugins 的 jetty 插件的 `jetty:run`，右键 Run
  - 开启浏览器，访问`http://upms.zhangshuzheng.cn:1111/sso/login`,输入账号 - 密码`admin - 123456`，成功进入页面


## 3. 错误记录
1. 本次开发环境搭建使用以下版本，提供参考
  - window 10 家庭版
  - jdk 1.8.0_65
  - Intellij IDEA 2016.03.05
  - apache-maven-3.3.9
  - MySQL 5.7
  - apache-activemq-5.9.0
  - zookeeper-3.5.2-alpha
  - nginx-1.13.7
  - Redis 3.0.503（windows 版）
2. 启动模块问题
  - 启动 upms 模块，能够从访问登陆网页，但是无法进入，按默认账号登陆会提示账户为空，并日志打印 log `com.alibaba.dubbo.rpc.RpcException: No provider available from registry zkserver: 2181 for service com.zheng.upms.rpc.api.UpmsSystemServer on consumer ......`
  - 需继续启动 cms 模块，方可通过验证，登陆权限管理系统
3. 修改 host 是为了能够直接通过本地域名，dns 解析到本地 ip，例如 ： 配置 mysql 路径`generator.jdbc.url=jdbc\:mysql\://dbserver\:3306/zheng`
4. 建议可将 apache-activemq，zookeeper，nginx，Redis 的 bin 目录下的 启动 bat 脚本统一发送快捷方式，到指定目录管理，然后使用 bat 脚本一键启动
5. Redis 默认是无配置文件 redis.conf, 可将 [redis.conf 网页版] (http://download.redis.io/redis-stable/redis.conf)的内容，复制到本地，启动redis-server.exe 时默认会搜索当前目录下的配置文件（当然，无配置文件也能启动）
6. zookeeper 目录的 conf 目录下的 `zoo_sample.cfg` 时样板目录配置文件，可复制，并改名成 `zoo.cfg` 作为启动读取的配置文件，可定义打印日志的路径