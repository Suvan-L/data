---
title: Eclipse_错误记录
date: 2016-04-17 15:04:41
tags: Eclipse
categories: Eclipse
---

一. 目录
=======================

1. Eclipse报错：由于对必须的库具有一定限制无法访问类型
2. 无法导入项目
3. 编译器版本与JRE版本不一致
4. 每次jdbc连接数据库都出现红色警告信息
5. 启动eclise，进入主界面，马上弹窗界面就报错
6. 启动eclipse,进入某工作站时报错


二. 错误
=======================


## 1. Eclipse报错：由于对必须的库具有一定限制无法访问类型
```
<1>原因：
	对于必须的库org.eclipse.swt.win32.win32.x86_3.7.0.v3735b.jar具有一定的限制。


<2>解决办法
eclipse中 ->	右键项目
	属性
			构建路径
				双击 JRE系统库[.....]
					1.将Execution environment(执行环境)中更改为上一个版本兼容即可
					2.直接改为工作空间缺省即可

```
---


## 2. 无法导入项目
　　有时候我们导入现有的工程时会出现错误，没有继续下一步的那个按钮，错误提示如下：
`some projects were hidden because they exist in the workspace directory。`
eclipse 导入项目是提示：某些项目因位于工作空间目录中而被隐藏。

　　导致这个错误的原因是工程重名了！
并不是仅仅指文件夹重名，相信很多人也曾经修改过文件夹的名称，可惜没什么用处，关键是修改工程里面的一个文件！也就是.project这个文件！

　　用记事本打开，修改一下，其中的 projectname 再次导入的话，就OK了。
```
<projectDescription>
	<name>projectname</name>
```
---


## 3. 编译器版本与JRE版本不一致
1.报错信息:
+ 弹窗报错`Could not find the main class:test.program will exit`
+ 控制台报错`java.lang.UnsupportedClassVersionError: Test : Unsupported major.minor version 52.0`


解决方案
&emsp;查看编译器版本和JRE版本
```
右键点击项目----->Preference----->Java ----->Compiler:Compiler compliance level 【编译器版本】
右键点击项目----->Build Path----->Configure Build Path----->Libraries:JRE system library【JRE版本】
```
---


## 4. 每次jdbc连接数据库都出现红色警告信息
能正常运行，但是eclipse控制台每次连接都会弹出红色提示
`Sat Dec 03 15:21:36 CST 2016 WARN: Establishing SSL connection without server's identity verification is not recommended. According to MySQL 5.5.45+, 5.6.26+ and 5.7.6+ requirements SSL connection must be established by default if explicit option isn't set. For compliance with existing applications not using SSL the verifyServerCertificate property is set to 'false'. You need either to explicitly disable SSL by setting useSSL=false, or set useSSL=true and provide truststore for server certificate verification.`


搜了下度娘，
原因：这警告不是错误,以后使用是不影响的。大概的意思就是说建立ssl连接，但是服务器没有身份认证，这种方式不推荐使用。


解决办法：【在连接后面加上&useSSL=false】
```
//方法1: 连接database数据库
        public void connDatabase(String database)  throws SQLException,ClassNotFoundException{ //参数：数据库名称
             Class.forName(drive);
             conn=DriverManager.getConnection(link+database+"?characterEncoding=UTF-8&useSSL=false",username,password); 
             st=conn.createStatement();
//           System.out.println("数据库连接成功......");
        }
```
---


## 5. 启动eclise，进入主界面，马上弹窗界面就报错
报错信息
&emsp;`An error has occurred.see error log for more details eclipse `


原因：
&emsp;某功能出现问题【可能是缓存或者日志文件那里】

解决办法：
1.进入Ctrl+R,进入DOS界面，进入eclipse的安装目录【eclipse/eclipse.exe当前目录】，输入`eclipse -clean`,重启eclipse即可
2.删除configuration文件夹里除config.ini以外的所有文件，重启eclipse

---

## 6. 启动eclipse,进入某工作站时报错
报错信息
&emsp;`Workspace in use or cannot be created, choose a different one`

出现原因：
&emsp;工作站中配置文件出现出现了.lock文件（workspace/.metadata/.lock），锁定了workspace。

解决办法:
&emsp;把.lock文件删除即可【正常情况，如果已经打开某工作站，再重复启动eclipse进入该工作站时，会报此错】