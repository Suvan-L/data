---
title: Java_Maven 错误记录
date: 2017-04-29 10:43:40
tags: Java
categories: Java
---


目录
=====================

1. 本地安装Maven
2. 我的 settings 配置文件
3. 创建 maven 项目【无模版-基本环境搭建】
4. idea 的 java项目添加Maven支持


## 1. 本地安装Maven
```
<1>网：http://maven.apache.org/download.cgi
    下载apache-maven-3.3.9-bin.zip

<2>配置环境变量:
    MAVEN_HOME:E:\Java\Maven\apache-maven-3.3.9\bin
    Path: ......%MAVEN_HOME%;e

<3>验证
    打开cmd 输入 mvn -v 【验证是否配置成功】


<4>更换中央仓库：【修改maven根目录下的conf文件中的setting.xml文件】
  <mirrors>
    <mirror>
      <id>alimaven</id>
      <name>aliyun maven</name>
      <url>http://maven.aliyun.com/nexus/content/groups/public/</url>
      <mirrorOf>central</mirrorOf>        
    </mirror>
  </mirrors>

```



## 2. 我的settings配置文件
```
<?xml version="1.0" encoding="UTF-8"?>
<settings xmlns="http://maven.apache.org/SETTINGS/1.0.0"
          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
          xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0 http://maven.apache.org/xsd/settings-1.0.0.xsd">
 
  <!--
    1.全局配置：${M2_HOME}/conf/settings.xml 【对操作系统的所有使用者生效】
    2.用户配置：${user.home}/.m2/settings.xml
    【对当前操作用户的使用者生效】
            两者都存在，内容合并，用户的settings会覆盖全局的settings

  -->
  <!-- 本地仓库位置 -->
  <localRepository>E:\Java\Maven\repository</localRepository>

  <!--搜索插件组织ID的列表 -->
   <pluginGroups>
  </pluginGroups>

  <!-- 用于来配置不同的代理[简单的设置profile id可以很容易的更换整个代理配置] -->
  <proxies>
  </proxies>

 　<!-- 配置服务端的设置 -->
  <servers>
  </servers>

  <!-- 为仓库列表配置下载的镜像列表【只要本地仓库没有，就到指定公共仓库下载】-->
  <mirrors>
    <!-- 阿里云仓库 -->
    <mirror>
      <id>alimaven</id>
      <mirrorOf>repositoryId</mirrorOf>
      <name>aliyun maven</name>
      <url>http://maven.aliyun.com/nexus/content/groups/public/</url>
    </mirror>
    <!--Maven仓库1-->
    <mirror>
        <id>UK</id>
        <mirrorOf>central</mirrorOf>
        <url>http://uk.maven.org/maven2</url>
    </mirror>  
  </mirrors>

  <!-- 根据环境参数来调整构造配置的列表 -->
  <profiles>
  </profiles>

  <!-- activeProfiles
   | List of profiles that are active for all builds.
   |
  <activeProfiles>
    <activeProfile>alwaysActiveProfile</activeProfile>
    <activeProfile>anotherAlwaysActiveProfile</activeProfile>
  </activeProfiles>
  -->
</settings>

```


## 3. 创建maven项目【无模版-基本环境搭建】
```
1. 创建Maven项目【不使用模版】,自定义GroupId和Arifactld
2. 部署项目[Porject Structure]
	A.添加Modules【选web模版】
		(a1)修改资源目录[Web Resource Directories]
			E:\Java\Intellij IDEA\项目\My_Blog\src\main\webapp
			 ~~~【这里指定src\main文件，并输入webapp】
		(a2)修改描述文件[Deployment Descriptor]
			E:\Java\Intellij IDEA\项目\My_Blog\src\main\webapp\WEB-INF\web.xml
			~~~【这里同样将目录指定到webapp】
	B.添加Artifacts【描述了当前项目的发布信息】
		'+' 号 
			-> Web Application Exploded
			-> From Modules...
			-> 选择当前项目
			【左列表出现已有的发布项目，右列表的output root目录描述了当前项目的编译目录及适配服务】
3.在webapp\WEB-INF\目录下 -> 新建lib目录
4.部署服务器
	菜单栏Run
		(A)
		->Edit Configurations
		-> '+' 号
		-> Tomcat Server ==> Local
		->右界面Deployment【第二列菜单栏】
		->'+' 号 ==>  Artifact...

		(B)
		->回到Server【第二列菜单栏】
			在[On 'Update' action]
			  [On frame deactivation]
			  		==> 都选择Update classes and resources

		(C)
		->回到Server【第二列菜单栏】
			选择默认浏览器

5.添加Tomcat依赖
	部署项目[Project Structure] 
		-> Modules 
		-> 右界面选择项目 ==> Dependencies[依赖]
	    -> '+'号  ==>  Library...
	    -> Application Server Libraries ==> 选择相应Tomcat版本


6.项目结构
	My_Blog
		out  【编译文件目录】
		src  
			main  【主要源码目录】
			 	java
			 	resources
			 	webapp
			 		_lib         【存放扩展jar包】
			 		_WEB-INF
			 			web.xml  【web项目配置文件】
			test   【测试目录】
				java
		target
		My_Blog.iml
		pom.xml    【Maven配置文件】
	External Libraries  【库】


**********************************************
	若需要用模版创建则在New Project时
		[打勾][Create from archetype]
		然后选择
			org.appache.maven.archetypes:maven-archetype-webapp

```



## 4. idea的java项目添加Maven支持
```
	先新建项目
		->在左侧边栏右键项目 -> Add Framwork Support... 
		-> 找到Maven

```
