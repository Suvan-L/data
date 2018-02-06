---
title: Apache 的 http 服务器
date: 2016-12-14 21:23:43
tags: WebServers
categories: WebServers
---

# 目录
1. 资源
2. 安装与使用
3. 遇到问题
  1. 启动httpd.exe或者ApacheMonitor.exe报错	
  2. 进入Apache24\bin目录的时候,安装服务器报错
  3. 无法启动'Apache2.4'服务 



## 1. 资源

+ [Apache服务器官网](http://httpd.apache.org/download.cgi#apache24)
+ [如何从Apache官网下载windows版apache服务器](http://jingyan.baidu.com/article/29697b912f6539ab20de3cf8.html)
+ [ApacheHaus下载windows版](http://www.apachehaus.com/cgi-bin/download.plx#APACHE24VC14)
  ​



## 2. 安装与使用

&emsp;参考资源进行下载压缩包【httpd-2.4.23-x64-vc14-r3.zip】到本地目录

1. 安装
```
1.解压文件
    ---->Apache24【主要目录】
    ---->readme_first.html【英文版基础教程】

2.CMD【以管理员身份启动DOS】,进入Apache\bin目录
    输入 httpd -k install,进行安装【会新建一个服务】【如果想卸载服务的话：sc delete Apache2.4】

3.启动ApacheMonitor.exe程序
    任务栏会出现小图标【左键-开启关闭服务,右键-启动服务,启动界面,退出】
    需要先启动'Apache2.4'服务，才能start

4.打开浏览器，输入http://localhost/，跳出Apache Haus界面，表示启动成功


5.可以左键小图标,Stop【停止服务】,然后右键小图标Exit退出服务器
    【提醒：记得把服务设置为手动启动】
```

---


2. 简单访问
```
在Apache24\htdocs目录下可以放xml或者JSON数据

1.新建get_data.xml,浏览器输入http://localhost/get_data.xml 访问
<apps>
    <app>
        <id>id</id>
        <name>Google Maps</name>
        <version>1.0</version>
    </app>
    <app>
        <id>2</id>
        <name>Chrome</name>
        <version>2.1</version>
    </app>
    <app>
        <id>3</id>
        <name>Google Play</name>
        <version>2.3</version>
    </app>
</app>


2.新建get_data.json,浏览器输入http://localhost/get_data.json访问

[
    {"id":"5","version":"5.5","name":"Angry Birds"},
    {"id":"6","version":"7.0","name":"Clash of Clans"},
    {"id":"7","version":"3.5","name":"Hey Day"}
]
```



## 3. 遇到问题

### 1. 启动httpd.exe或者ApacheMonitor.exe报错

报错信息
`无法启动此程序，因为计算机丢失VCRUNTIME140.dll.尝试重新安装该程序以解决此问题`

解决方案：


&emsp;到Microsoft下个补丁,安装就行,[补丁链接](https://www.microsoft.com/zh-cn/download/confirmation.aspx?id=48145)




参考：[httpd 系统错误 无法启动此程序，因为计算机中丢失VCRUNTIME140.dll](http://www.cnblogs.com/drcoding/p/5441922.html)




### 2. 进入Apache24\bin目录的时候,安装服务器报错

报错描述
Ctrl+R,cmd,进入Apache24目录输入http -k install,
报错`httpd:Syntax error on line 39 of E:/Java/WebServers/ApacheHttp/Apache24/conf/httpd.conf 
ServerRoot must be a valid directory`


原因：
&emsp;httpd.conf里面配置的ServerRoot路径跟实际路径不一致，导致路径无效。


解决办法：
1. 进入conf目录，打开httpd.conf文件，找到39行
2. 将Define SEVROOT 后的路径补充完整,比如Define SRVROOT "E:\Java\WebServers\ApacheHttp\Apache24"【当前完整路径】



参考:[Apache报ServerRoot must be a valid directory](http://jingyan.baidu.com/article/915fc41491c68751384b2040.html)

### 3. 无法启动'Apache2.4'服务

报错描述：
控制面板====》大类别====》管理工具=====》服务====》右键'Apache2.4'启动
报错：`Windows不能在本地计算机启动Apache2.4.有关更多信息，查阅系统事件日志.如果这是非Microsft服务，请与服务厂商练习，并参考特定服务错误代码`

<br>

可能原因：
1. 端口号占用【80端口被使用】
2. httpd.conf里面配置的ServerRoot路径跟实际路径不一致
3. 安装Apache2的路径中不能含有中文.
  <br>

解决方案：
```
1.修改httpd.conf文件
        #Listen 12.34.56.78:80    修改为-----> #Listen 12.34.56.78:81080
        Listen 80                               Listen 8080

2.修改路径
    Define SRVROOT "/Apache24"    修改为-----> Define SRVROOT "E:\Java\WebServers\ApacheHttp\Apache24"
    ServerRoot "${SRVROOT}"                    ServerRoot "${SRVROOT}"

```
参考：[Windows不能在本地计算机启动Apache2 ](http://blog.csdn.net/woaini1115077272/article/details/45649413)

