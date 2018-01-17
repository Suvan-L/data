---
title: Netbeans_错误记录
date: 2017-03-17 12:01:43
tags: Netbeans
categories: Netbeans
---


目录
=======================
1. 下载地址
2. build.xml 1045  尚未部署问题【已解决】
3. 创建web项目后，运行项目,报错Tomcat启动失败




## 1.下载地址
&emsp;[官网](https://netbeans.org/)



## 2.build.xml 1045  尚未部署问题【已解决】

```
就地在E:\Java\NetBeans\PM\WebApplication1\build\web中部署
deploy?config=file%3A%2FD%3A%2Ftemp%2Fcontext5620394177451846722.xml&path=/WebApplication1
http://localhost:8095/manager/text/deploy?config=file%3A%2FD%3A%2Ftemp%2Fcontext5620394177451846722.xml&path=/WebApplication1
E:\Java\NetBeans\PM\WebApplication1\nbproject\build-impl.xml:1045: 尚未部署该模块。
有关详细信息, 请查看服务器日志。s
```


怀疑原因:
+ JDK版本(1.6,1.7,1.8的不同)
+ 代码问题
+ Tomcat问题(7,8,9)
+ Netbeans版本(8.1和8.2)


解决问题：
1. JDK1.7和1.8都试过，同样报错
  2 .代码没错误，因为之前项目是可以跑的
  3 .Tomcat用7和9的话会有一些启动报错(未解决), Tomcat 可以启动但是仍然报错未部署该模块, tomcat8 是安装 Netbeans 时候携带安装的，也报错
2. 上述都是使用8.2的 Netbeans ,后来去官网下载了8.1的，用的是1.8的JDK，Tomcat8，成功运行

终极解决套路：
将Tomcat下的servlet.api.jar替代jdk/lib/ext/目录下的servlet包
【即解决浏览器页面空白，又解决尚未部署问题】



## 3.创建 web 项目后，运行项目,报错 Tomcat 启动失败
```
正在启动 Tomcat 进程...
正在等待 Tomcat...
启动 Tomcat 失败。
C:\Users\Liu-shuwei\Documents\NetBeansProjects\WebApplication1\nbproject\build-impl.xml:1066: 部署错误: 启动 Tomcat 失败。
有关详细信息, 请查看服务器日志。
构建失败 (总时间: 3 秒)
```

可能原因：
1. 端口占用:
  Tomcat/conf/目录下的server有里需要配置3个端口
  Sutdown,HTTP/1.1,AJP/1.3  三个端口重叠

>注意这三个端口不要重复，可设置为，8084，8085，8086
