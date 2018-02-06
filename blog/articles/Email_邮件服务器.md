---
title: 邮件服务器
date: 2017-05-30 13:30:25
tags: Email
categories: Email
---


# 参考资料：
+ [hMailServer官网](https://www.hmailserver.com/)
+ [几款优秀的免费邮件服务器软件](http://www.xtjc.com/tech/201105/10-7207.html)
+ [Java开源网络服务器端组件](http://www.open-open.com/36.htm)


# 目录：
1. hMailServer


## 1. hMailServer     

#### hMailServer资料
+ [hMailServer官网](https://www.hmailserver.com/)
+ [hMailServer中文论坛](https://www.hmailserver.org/index.php)
+ [hMailServer最新稳定版下载5.6（生产环境）](https://www.hmailserver.org/app.php/page/download)
+ [hMailServer配置图文详细教程](https://www.hmailserver.org/viewtopic.php?t=6)
+ [hMailServer汉化文件](https://www.hmailserver.org/viewtopic.php?f=5&t=13)
  <br>

#### 安装与配置
+ 汉化
```
	-> 网上下载：chinese.ini
	-> 将其复制到hMailServer根目录的\Languages目录下
	-> 根目录\BIN
		-->修改hMailServer.INI[ValidLanguages=english,swedish,chinese]
	-> 重启hMailServer服务[控制面板-管理工具-服务]
	-> 开启软件,输入软件密码[l....XXXi]-菜单栏-File-Select language
```

+ 设置使用本机MySQL数据库
```
	-> 需要将数据库MySQL Server 5.7\lib\libmysql.dll
	-> 拷贝到hMailServer\Bin\目录下
		【注意：如果是64位的MySQL不支持，MySQL (32bit) 取得裡面的 libmysql.dll】
```


+ 启动邮件系统
```
	开启系统服务 -> hMailServer
```

+ 服务端配置
```
	1-A.域名
		boss.com  [无限制]
		suvan.com [最大容量500MB,最大邮件大小200KB,帐号最大容量20MB]
		1-B.添加帐号【可添加外部帐号,用于外网下载邮件】
			(b1)配置地址
			(b2)配置密码
	
	2.设置-协议
		SMTP -> 邮件递交[设置本地主机名(随意设置)]
	
	3.设置-日志
		打勾"已启用"[根据需求勾选记录]
	
	3.设置-高级
		(a)自动屏蔽[不启用]
		(b)IP范围
			My computer  -> 主要设置“运行递交来自”和“SMTP需要验证”
			Internet	 ->	同上
		(c)TCP/IP端口 [一般默认,也可能根据需求设置]
	
	4.实用工具
		备份  -> 选择路径,打勾"设置","域名","邮件","压缩文件" -> 保存
	
	5.诊断
		启用[镜像测试，全部通过(全绿色)表示服务器端配置完成]
```


+ 客户端配置【Foxmail】
```
	设置  ->  帐号
		-> 新建
			 ->(1)手动设置
			 		接收服务器类型：POP3
					邮件帐号: [例：test@suvan.com,boss@boss.com]
					密码：		
	
					POP服务器(收件)： 填写邮件服务在局域网的ip地址[例：10.4.104.34]
					SMTP服务器(发件)： 同上
										【注：服务器在本机可填：localhost 或 127.0.0.1】
		ip
			外网ip: 百度输入ip
			内网ip：cmd 输入ipconfig,以太网的ipv4地址
	
			注意：
				如果两个ip一样,说明正处在外网
				如果不一样，说明正处在内网


批量添加用户
	在hMailServer中文论坛下载,批量添加用户.rar
			import.vbs [运行脚本]
			bulk.cvs   [存放导入数据]


		(1)修改脚本内的hMaiServer密码[hAdminpwd = "XXXX"]
		(2)填写导入数据 [基本格式在cvs文件里有案例,脚本里有注释]
	 【注意：1次只能导入1种域名的用户数据】




 邮箱使用情况测试
 	成功选项：A.局域网收发 B.外网收发

 	云服务器邮箱
 		suvan@liushuwei.cn       [AB]
 		liushuwei@云服务器的IP   [A]
 		test@suvan.net.cn        [A]
 	局域网邮箱
 		liushuwei@suvan.com      [A]
 		boss@boss.com            [A]
```
+ 云服务器服务端需要开启的
   1. 域名
   2. 开启25 110端口【控制面板-防火墙-高级设置-入站规则(右键,新建,端口...)】【在腾讯云就是配置安全组】
   3. 添加DKIM签名
   4. 添加SSL证书
     ​

+ 特别注意：
    1. 任务进程强制关闭MailServer会导致邮箱无法接收服务器
    2. 域名可以同时,www指向个人网站,@指向邮箱服务器
    3. 邮箱服务器需要开启部分TCP/IP端口【防火墙的入站规则】
