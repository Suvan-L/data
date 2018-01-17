---
title: Java_错误记录
date: 2016-12-29 10:03:25
tags: Java
categories: Java
---


目录
========================

&emsp;总结一些遇到的错误,下次出现能够及时处理


1. JDK不同版本之间不一定兼容
2. 编译问题
3. 使用命令行java运行class文件时候报错
4. javac编译,报错编码GBK的不可映射字符
5. java的jdbc连接数据库的警告信息



## 1. JDK不同版本之间不一定兼容
&emsp;每个版本之间有特性，Java 2 SDK 1.4...安装之后会有两个程序



## 2. 编译问题
&emsp;有时候编译报错是环境变量问题，环境变量配置哪个JDK的路径，就会默认的使用那个版本的java虚拟机进行编译

+ 有些软件会而外配置环境变量，可能会与本地的JDK冲突，导致编译报错例如`Pick up....JAVA_)OPPTION..`,是用QTP可能会出现这个问题，需要把它删掉


## 3. 使用命令行java运行class文件时候报错


场景：
&emsp;javac编译完java文件后,输入java XXX的时候，

报错
&emsp;`错误:找不到或者无法加载主类`

原因：
1. 找不到相应包名【java程序运行class文件,对于有包名的类，会把包名当成文件夹处理】


解决方案：
1. 删除java文件内的的导包语句【例如：package qqSG;】
2. 检查系统环境变量CLASSPATH的变量值是否有.:【例如 .;%JAVA_HOME%\lib;】【.;表示在当前目录下寻找类】



## 4.javac编译,报错编码GBK的不可映射字符

场景：
&emsp;在DOS操作系统，用javac命令编译java文件的时候，报错

报错信息:
&emsp;编码GBK的不可映射字符。

原因：
&emsp;没有指定JAVA源程序的编码格式，则javac.exe会获得我们操作系统默认采用的格式【这里是GBK】

解决方案:
&emsp;参考[javac编译 编码GBK的不可映射字符](http://www.mamicode.com/info-detail-456244.html),
输入javac -encoding UTF-8 XXX.java命令进行编译



## 5. java的jdbc连接数据库的警告信息，以及各种框架的警告信息归纳
(1)1JDBC连接MYsql数据库提示SSL
```
设置编码且去除警告信息[加入`?characterEncoding=UTF-8&useSSL=false`]
		正常思路
		 Class.forName(com.mysql.jdbc.Driver);
	 	 conn=DriverManager.getConnection(jdbc:mysql://127.0.0.1:3306/数据库名,username,password);	//		
	     Statement st=conn.createStatement();

	 去除警告信息
	 	 Class.forName(com.mysql.jdbc.Driver);
	 	 conn=DriverManager.getConnection(jdbc:mysql://127.0.0.1:3306/数据库名?characterEncoding=UTF-8&useSSL=false,username,password);	//		
	     Statement st=conn.createStatement();

```

---


(2)连接数据库时候出现SSL警告
```
`Mon Mar 13 11:28:41 CST 2017 WARN: Establishing SSL connection without server's identity verification is not recommended. According to MySQL 5.5.45+, 5.6.26+ and 5.7.6+ requirements SSL connection must be established by default if explicit option isn't set. For compliance with existing applications not using SSL the verifyServerCertificate property is set to 'false'. You need either to explicitly disable SSL by setting useSSL=false, or set useSSL=true and provide truststore for server certificate verification.

```