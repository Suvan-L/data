---
title: Tomcat 错误记录
date: 2016-08-22 11:09:43
tags: WebServer
categories: WebServer
---


目录
========================
1. Tomcat5.0安装时候,进度条卡在jvm.dll
2. 接双击启动tomcat中的startup.bat,DOS窗体闪退
3. 解决无法部署问题
4. 设置新管理用户
5. eclipse无法启动Tomcat
6. Tomcat启动异常，开启浏览器输入默认URL，显示空白页(网络404)
7. 浏览器访问URL隐藏端口号【修改Tomcat默认端口号】

环境配置
+ [Tomcat官网下载](http://tomcat.apache.org/)
+ [配置Tomcat和JDK--百度经验](http://jingyan.baidu.com/article/8065f87fcc0f182330249841.html)
+ [Eclipse4.6(Neon) + Tomcat8 + MAVEN3.3.9 项目完整环境搭建](http://www.mamicode.com/info-detail-1557665.html)
  ​

Tomcat目录结构
```
  /bin        【存放各种平台下用于启动和停止Tomcat的命令文件】
  /conf       【存放Tomcat服务器的各种配置】
  /lib        【存放Tomcat服务器所需各种JAR文件】
  /logs       【存放Tomcat的日志文件】
  /temp       【Tomcat运行是用于存放临时文件】
  /webapps    【当发布Web应用时,默认会将Web应用的文件发布到此目录中】
  /work       【Tomcat把由JSP生成的Servelt放于此目录下】
```





## 1. Tomcat5.0安装时候,进度条卡在jvm.dll

场景:
&emsp;使用安装包安装Tomcat的时候，卡在进度条，一直无法不前进,无报错
<br>

原因：
&emsp;安装的时候有两个进程无法跳过，导致主线程堵塞[ns数字.tmp]
<br>

解决办法:
&emsp;打开任务管理器，在不同的卡住时机，分别结束掉这两个进程【不是同时出现，】





## 2. 接双击启动tomcat中的startup.bat,DOS窗体闪退
场景:
&emsp;免安装的tomcat双击startup.bat后，启动窗口一闪而过

原因
&emsp;在启动tomcat是，需要读取环境变量和配置信息，缺少了这些信息，就不能登记环境变量，导致了tomcat的闪退。
&emsp;Tomcat的环境变量没配置好

解决办法：

1.重新配置环境变量

2.配置局部环境变量
　在已解压的tomcat的bin文件夹下找到startup.bat，右击->编辑。在文件头加入下面两行：
　　  SET JAVA_HOME=D:\Java\jdk1.6.0_10   （java jdk目录）
　　  SET TOMCAT_HOME=E:\tomcat-6.0.35   （解压后的tomcat文件目录）
　同样的。在已解压的tomcat的bin文件夹下找到shutdown.bat，右击->编辑。在文件头加入下面两行：
　　  SET JAVA_HOME=D:\Java\jdk1.6.0_10   （java jdk目录）
　　  SET TOMCAT_HOME=E:\tomcat-6.0.35   （解压后的tomcat文件目录）
　将你要发布/部署的web文件复制到tomcat的webapps目录下。
　　双击startup.bat即可启动tomcat，成功启动。





## 3.解决无法部署问题

流程1：Tomcat9.0 work\Catalina下的文件

　　是启动Tomcat的时候的，将项目jsp编译成class文件

　　是缓存，可以删除，下次启动的时候又会重新编译

---

流程2：如果Tomcat启动的时候DOS黑窗体无法一直停留，说明有错误
　　 查看conf/server.xml的文件
　　    ---》用浏览器打开会提示报错信息，
　　         根据报错信息，用记事本打开，进行修改

---


流程3：war进行部署
　　1）启动Tomcat，并在IE输入Tomcat网址，如：http://localhost:9000/
　　2）点击“Manager App”，进入Tomcat项目管理页面
　　3）在WAR file to deploy，选择需要部署的项目文件（*.war）【文件夹下的shopping.war】,　　再点击“Deploy”进行部署
　　4）部署完成，可在Tomcat安装目录下的webapps下也能找到对应目录

注意：
　　a。部署完之后会在webapps目录下产生同样大小的waf文件
　　    和项目文件夹

　　　　并且浏览器的Tomact测试页面（http://localhost:8080/manager/html）
　　　　　　 ---下的Application 也会显示项目文件夹

　　   b。再次部署同一个war文件，会报错
　　　　Message:  
           - War file "shopping.war" already exists on server
                署项目


　　c。如果部署后的文件与源文件大小不同，名字不同
　　　   请删除该文件，以及Tomcat缓存，并且将源文件
　　   直接复制到webapps目录下，重启Tomcat




## 4. 设置新管理用户

我们首先打开Tomcat的配置文件，
　　--->进入Tomcat的安装了路径，打开”conf“文件夹
　　　　--->再打开”tomcat-users.xml“文件
```
　　　　将”<user username="admin" password="1234" roles="manager-gui"/>插入进去

例如：
<!--
  <role rolename="tomcat"/>
  <role rolename="role1"/>
  <user username="tomcat" password="<must-be-changed>" roles="tomcat"/>
  <user username="both" password="<must-be-changed>" roles="tomcat,role1"/>
  <user username="role1" password="<must-be-changed>" roles="role1"/>
-->
  <user username="admin" password="1234" roles="manager-gui"/>
</tomcat-users>


            --->再点击保存，即可完成设置！
```



我们在浏览器中输入”http://localhost:8080“

输入之前设置的账户(admin)和密码(1234)就能登录了！




## 5. eclipse无法启动Tomcat

可能原因：
1. JDK不兼容Tomcat版本
2. 项目的web.xml配置有错
3. 项目内jar包冲突无法正确解析web.xml

情况展示
+ myeclipse使用Tomcat 7.0,Tomcat的JDK版本是Sun JDK1.6.0_13[使用1.8的JDK，Tomcat启动报错，index.jsp和localhost:8080界面一片空白]
+ netbeans使用jdk1.80 ，一直报错，未部署该模块
+ eclise配置JDK 1.8  使用Tomcat7，8，9都会报错
+ eclipse配置JDK1.7 使用Tomcat7,8没问题


解决思路：
	先考虑Tomcat版本是否合适，再考虑JDK版本是否兼容





## 6. Tomcat启动异常，开启浏览器输入默认URL，显示空白页(网络404)
` java.lang.NoSuchMethodError: javax.servlet.ServletContext.getClassLoader()Ljava/lang/ClassLoader;`


将Tomcat/lib/目录下的servlet-api.jar替代jdk1.8.0_65\jre\lib\ext\目录下的servlet包



## 7. 浏览器访问URL隐藏端口号【修改Tomcat默认端口号】

将端口号改为80
```
a.本地访问:将端口号改为80 
	 <Connector connectionTimeout="20000" port="8080" protocol="HTTP/1.1" redirectPort="8443"/>     [这里的port改为80]
b.路由器的端口映射
	外部端口和内部端口都改为80


访问实例：
	http://f174a65989.51mypc.cn/blog/



```