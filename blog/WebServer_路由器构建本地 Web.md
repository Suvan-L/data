---
title: 路由器构建本地 Web
date: 2017-06-26 00:59:59
tags: WebServer
categories: WebServer
---



# 目录
1. 动态域名服务DDNS
2. 端口映射
3. 访问本地服务器
4. 腾讯云域名解析
5. frp实现内网穿透



## 1. 动态域名服务DDNS

+ 动态域名服务 DDNS（Dynamic Domain Name Server）是一种将动态 IP 地址映射到一个固定的域名解析服务上的系统。当启用 DDNS 后，路由器会把它的动态 WAN IP 与一个固定域名进行绑定。这样，通过因特网，您可以随时随地使用固定的域名访问路由器的服务资源，而不用亲自追踪路由器的 WAN IP
  ​

```
a.判断是否是公网ip,如果是公网ip可直接做端口映射
b.选择DDNS服务器(例如:花生壳www.oray.com)注册,获取域名 + 用户名 + 密码
c. 填入,保存[在DDNS服务器上注册过的数据]


```



## 2. 端口映射

+ 【NAT服务】通过设置路由器的转发规则，因特网上的用户可以方便地访问您通过个人计算机提供的服务器资源（如个人网站、FTP 服务器等）
+ 【端口映射】将华为路由 WS318 增强版广域网 IP 地址的一个端口映射到局域网中的一台计算机上。当因特网用户访问该 IP 的该端口时，华为路由 WS318 增强版将会自动将该请求映射到已指定的计算机上，并通过该计算机对外提供服务
  ​

```
a.更多功能 -> 安全设置 -> NAT服务 -> 端口映射
b.
	服务名
	服务类型
	设备
	主机IP
	协议类型
	内部端口 [设置对外提供服务的计算机的服务端口号]
	外部端口 [设置路由器供因特网用户访问的服务端口号]

```



## 3. 访问本地服务器
-----------------

```
举例：
	8080端口
		f174a65989.51mypc.cn:8080/blog     [访问本机的8080端口【Tomcat(Apache服务器)】的blog项目]
	80端口
		http://f174a65989.51mypc.cn/blog/

```



## 4. 腾讯云域名解析
修改记录：
+ 记录类型 		[A:将域名指向一个IPv4地址（例如：8.8.8.8）]
  + 主机记录 	[www:解析后的域名为www.qcloud.com。]
    + 线路类型 [默认]
  + 关联云资源 [否]
    + 记录值[填写服务器IP或者DDNS的指向域名]
+ TTL  [各地DNS服务器缓存解析记录时长，缓存失效后才会重新获取记录值。
  建议正常情况下设定10分钟即可。]




## 5. frp实现内网穿透
------------------

参考资料
+ [frp中文文档【Github】](https://github.com/fatedier/frp/blob/master/README_zh.md)
+ [frp下载页面](https://github.com/fatedier/frp/releases)
+ [frp怎样开机启动和后台运行](https://github.com/fatedier/frp/issues/176)
  ​


内网机器搭建web服务
```
环境需求
	一台公网IP的机器, 一台内网环境的机器


Linux环境搭建
	<1>进入目录 cd /usr/local 

	<2>下载【注意：要看清Linux的版本是什么,我的是	Centos 6 x86   】
		  wget https://github.com/fatedier/frp/releases/download/v0.12.0/frp_0.12.0_linux_386.tar.gz
		 解压
		 	tar -zxvf frp_0.12.0_linux_386.tar.gz

	<3>进入cd frp_0.12.0_linux_386
	   配置 vi frps.ini
	<4>启动 ./frps -c ./frps.ini

	<5>设置开机启动
		vi /etc/rc.local
		在最下面加2行
			#开启自动启动frps内网穿透
			sleep 10   [需先休眠10秒再启动]
			/usr/local/myfrp/frps -c /usr/local/myfrp/frps.ini
			【其/usr/local/myfrp/是程序放置的目录，重启ok】




搭建web服务使用流程
1.下载frp_0.12.0_windows_amd64.zip,解压
	frps和frps.ini  	->  	公网 IP 的机器上【服务端】
	frpc和frpc.ini 		-> 	    内网环境的机器上。【客户端】

2.配置服务端frps.ini
		[common]
		bind_port = 7000 		 [默认,frp的执行端口]
		vhost_http_port = 80     [服务器开放http访问端口(80端口的URL可以设置URL访问无需端口号)]
		max_pool_count = 5       [每个代理的连接池上限]

3.客户端
	   [common]
		server_addr = x.x.x.x    [服务器ip]
		server_port = 7000		 [默认,frp的执行端口]
		pool_count = 1           [预创建连接的数量]

		[web]
		type = http              [http协议]
		local_port = 8080          [内网机器的web服务端口(Tomcat的端口)]
		custom_domains = www.liushuwei.cn [自定义域名]


4.启动【下面是Linux命令行版本,windons可以直接点击exe程序启动】
	服务端./frps -c ./frps.ini
   	客户端./frpc -c ./frpc.ini

   	在linux任何位置
		/usr/local/myfrp/frps -c /usr/local/myfrp/frps.ini



5.外网可访问
	www.liushuwei.cn/blog




```


