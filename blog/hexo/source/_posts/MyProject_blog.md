---
title: blog
date: 2017-05-02 08:07:43
tags: MyProject
categories: MyProject
---



一. 前言：
======================

&emsp;完整搭建个人博客，前端 + 后端
<br>

开发环境
+ IDE: IntelliJ IDEA 2016.3.5
+ Java: jdk1.8.0_65
+ 服务器：Tomcat-8.0.41
+ 项目管理(本地仓库管理依赖)：maven-3.3.9
+ 版本控制：Github Git2.7.2.0
+ 数据库：MySQL57
  <br>

所用的技术
+ HTML+CSS+JavaScript
+ jsp + Servlet + ajax
  <br>


集成的框架
```
前端：
		BookStrap
		Query,Layui
后端：
	IoC容器：Spring
	MVC的Controller: SpringMVC
	ORM持久层框架: MyBatis

```
<br>

期望功能：
1. 博客【登录,注册(验证),文件上传与下载,】
2. 文章增删查改,阅读数统计,评论,点赞

<br>

项目结构：
```
My_Blog
	logs 	[存放日志文件(.log后缀)]
		ssm.log
	out 	[编译后文件]
	src
		main
			java
				com.blog
					controller		[控制器]
						interceptor 	[拦截器]
					dao				[dao接口]
					entity			[存放实体类-POJO(普通的java对象)]
					extend			[第三方库的组件]
						javamail    	[发送邮件]
					myunits			[工具类]
					service			[Server接口]
						impl			[Server接口的实现实现类]
			resources
				mapping 			[存放dao接口对应的映射xml]
				jdbc.properties 	[JDBC属性文件(文件编码为utf-8)]
				log4j.properties 	[日志文件]
				spring-mvc.xml      [spring MVC配置文件(自动扫描控制器,视图模式,注解启动)]
				spring-mybatis.xml  [spring和mybatis整合配置文件]
			webapp
				css
				extend 				[扩展]
					icon				[Iconfont-阿里巴巴矢量图标库]
				file 				[存放文件]
					download			[下载文件]
				frame  				[前端框架]
					bootstrap-3.3.7-dist
					jQuery
					layui
				html
				img    				[项目用图]
				js
				WEB-INF
					jsp
					lib
					web.xml
				index.jsp  			[博客首页]
		test
			java
				test.com.blog [存放jUnit的测试类]

	target	[Tomcat服务器编译临时文件]
	.gitignore[Github屏蔽不上传的目录]
	MyProject_个人博客.md 
	My_Blog.iml
	pom.xml

```
<br>


设计数据库表【.sql脚本】
```

/*
 * 一.自己新建数据库【在数据库里执行下列SQL脚本】
 *      数据库名：myblog
 *      字符集：  utf8--UTF-8 Unicode
 *      排序规则: utf8_general_ci
 *  
 *  SQL语句：
 *     CREATE DATABASE myblog;   
 */


/*
 * 二.建表
 */
-- 1.用户表
    CREATE TABLE user(
        u_id INT  AUTO_INCREMENT primary key,
        u_name VARCHAR(15) UNIQUE KEY,
        u_password VARCHAR(15) NOT NULL,
        u_sex  VARCHAR(2),
        u_birthday VARCHAR(20),
        u_address VARCHAR(15),
        u_phone VARCHAR(15),
        u_email  VARCHAR(50)  NOT NULL,
        u_registertime DATETIME DEFAULT NOW()
     )ENGINE = innoDB;


-- 2.文章表
CREATE TABLE article(             
    a_id INT  AUTO_INCREMENT primary key,       /*[文章id(自增)]*/
    a_title VARCHAR(50) NOT NULL,               /*[标题]*/
    a_content LongText  NOT NULL,         			/*[内容]*/
    a_categories VARCHAR(20) NOT NULL,			/*文章类型*/
    a_read VARCHAR(10) default '0',             /*[阅读数]*/
    a_comment VARCHAR(10) default '0',          /*[评论数]*/
    a_like  VARCHAR(10) default '0',            /*[点赞数]*/
    u_id INT NOT NULL,                          /*[用户id]*/
    a_publictime  DATETIME DEFAULT NOW()        /*[发布时间]*/
)ENGINE = innoDB;


-- 3.评论表
CREATE TABLE comment( 
    c_id INT  AUTO_INCREMENT primary key,       /*[评论id[自增]]*/
    c_content VARCHAR(300)  NOT NULL,           /*[内容]*/
    c_agree VARCHAR(10) default '0',            /*[同意数]*/
    c_oppose VARCHAR(10) default '1',           /*[反对数]*/
    a_id INT,                                   /*[文章id]*/
    u_id INT,                                   /*[用户id]*/
    c_publictime DATETIME DEFAULT NOW()     /*[发布时间]*/
)ENGINE = innoDB;


/*
 * 三.插入测试数据
 */
#user表
insert into user(u_name,u_password,u_sex,u_birthday,u_address,u_phone,u_email) values('suvan','12345','男','2001-05-18','广东省佛山市南海区','13289966310','suvan@gmail.com');
insert into user(u_name,u_password,u_sex,u_birthday,u_address,u_phone,u_email) values('TOM','12345','女','1983-05-18','广东省深圳市罗湖区','1389128573','1389128573@163.com');
insert into user(u_name,u_password,u_sex,u_birthday,u_address,u_phone,u_email) values('瓜皮','12345','男','2001-05-18','广东省河源市龙川','18154876543','723583341@qq.com');

#article表
insert into article(a_title,a_content,a_read,a_comment,a_like,u_id) values('学习使我快乐','皮皮皮皮123','523','64','83','1');
insert into article(a_title,a_content,a_read,a_comment,a_like,u_id) values('看书吗','死亡开端还不错啦','56','2','23','2');
insert into article(a_title,a_content,a_read,a_comment,a_like,u_id) values('看书吗','死亡开端还不错啦','182','5','62','3');
insert into article(a_title,a_content,a_read,a_comment,a_like,u_id) values('第五部','皮皮皮皮123','523','64','83','1');
insert into article(a_title,a_content,a_read,a_comment,a_like,u_id) values('书5','死亡开端还不错啦','56','2','23','2');
insert into article(a_title,a_content,a_read,a_comment,a_like,u_id) values('今天还好吗','死亡开端还不错啦','56','2','23','2');
insert into article(a_title,a_content,a_read,a_comment,a_like,u_id) values('我就一脚吹过去','死亡开端还不错啦','56','2','23','2');

#comment表
insert into comment(c_content,c_agree,c_oppose,a_id,u_id) values('不错','52','23','1','2');
insert into comment(c_content,c_agree,c_oppose,a_id,u_id) values('挺好的','892','673','1','1');
insert into comment(c_content,c_agree,c_oppose,a_id,u_id) values('很棒哦','5431','889','1','2');
```


​	


二. 开发日志
===============================
<br>

2017年
+ 【5.2】构思项目体系,复习HTML,学习[Layui](https://www.layui.com/),
+ 【5.3】复习CSS，学习[BookStrap](http://v3.bootcss.com/)
+ 【5.4】在[W3C](http://www.w3school.com.cn/复习HTML),熟悉BookStrap的CSS样式
+ 【5.9】熟悉BookStrap的栅格系统,导航栏样式,开发index.html(首页)导航栏
+ 【5.10】 响应式导航栏
+ 【5.11】 首页背景图
+ 【5.13】 首页登录框,复习JavaScript
+ 【5.14】 复习jQuery,注册页面的HTML+CSS
+ 【5.15】 注册页面优化 + JavaScript验证[jQuery进行事件绑定]
+ 【5.16】 后台管理页面[基本HTML+CSS]
+ 【5.17】 后台管理页面[用layui+jQueryJS切换布局]
+ 【5.18】 设计数据库表 + 学习——搭建SSM(Spring4 + Spring MVC +MyBatis)
+ 【5.19】 搭建SSM后台环境,使用jUnit进行的简单元测试(MyBatis数据库查询)
+ 【5.20】根据myblog的数据表编写POJO,SQL脚本插入测试数据
+ 【5.21】数据表[user,article,comment],使用MyBatis编写增删查改,并用jUnit进行测试
+ 【5.22】idea安装JRebel插件[激活破解,实现java代码热更新],规划spring-mvc.xml文件,学习编写的controller[控制器]和interceptor[拦截器],尝试RESTFul风格的URL访问请求
+ 【5.23】SpringMVC的注解,文件上传与下载,拦截器
+ 【5.24】web.xml,spring和mybatis配置文件整理
+ 【5.27】html/index.html,页面优化，文章展示
+ 【5.28】前后端JSON数据进行交互【jQuery的ajax接收JSON数据与生成DOM元素 + SpringMVC的@Response传递JSON数据(单篇or多篇文章[查询单条 or 多条记录])】
+ 【5.29】优化register.html[注册页面]的jQuery代码的JS验证,使用JavaMail完成发送邮件验证码功能【可利用163,qq,gmail邮箱发送】
+ 【5.30】尝试使用开源的hMailServer,搭建邮箱服务器【失败,偶尔能发邮件,无法接收】
+ 【5.31】局域网和腾讯云服务器搭建hMailserver,购买域名liushuwei.cn,配置DNS，指向云服务器,设置个人邮箱suvan@liushuwei.cn,注册页面的发送邮箱验证码使用改邮箱进行发送
+ 【6.1】半个咸鱼,更新域名,博客www.liushuwei.cn,邮箱suvan@liushuwei.cn,邮件客户端使用foxmail,可实现局域网内互传(他人连我Wifi)【校园局域网无法连接到我的服务器IP,未解决】
+ 【6.6】设计博客首页,规划与重写html/index.html的html和CSS
+ 【6.7】博客首页(html/index.html)的动画效果
+ 【6.8】博客文章页面的基本HTML+CSS+jQuery(标题,返回按钮,小球动画,内容区,评论区,小球动画),异步请求获取文章评论信息
+ 【6.9】文章页面的点赞反对(带有动画)阅读计数,评论功能,markdonw解析器[js的marked(未自己定义css样式)]
+ 【6.10】归档页面,index.html转为index.jsp,学习Filte过滤器拦截请求
+ 【6.11】学习JSP,熟悉EL表达式,JSTL标签,article.html转为article.jsp[重点改JS文件]研究Spring代理过滤器【失败】
+ 【6.13】归档页设计archiving.jsp,CSS布局,浮动,块元素理解
+ 【6.15】Bootstrap栅格系统重写归档页,jsp分页
+ 【6.20】 分类页年份列表
+ 【6.23】研究爬虫webMagic,爬取金蝶行动流(https://www.xingdongliu.com/)的数据,页面JS动态生成,数据经过woff加密,使用selenium浏览器渲染爬取,仍然失败,设计ClassMastesUser[同学会统计功能],在云服务器配置JDK+MySQL+Tomcat+sftp
+ 【6.24】尝试发布到腾讯云服务器,数据表单,js验证,数据库持久化操作,邮件发送,页面无法手机浏览器兼容,不是响应式页面
+ 【6.25】尝试在本地进行路由器配置,动态域名服务 DDNS【花生壳】,端口映射
+ 【6.26】尝试frp进行内网穿透(依旧很卡)
+ 【6.27】同学会统计表单页面完成【自动播放音频,js控制暂停】,功能实现,明日尝试发布到服务器,初步了解Linux,
+ 【6.28】加入stock[股票页面],学习jsoup访问新浪提供的股票接口[应用在项目里报错,未解决]
+ 【7.6】同学会统计功能,本地服务器,frp内网穿透[公网Linux服务器],createExcel,自动生成excel表工具
+ 【7.7】createExcel生成工具,实现Excel表去重(倒叙最新),总结慕课网Socket通信知识
+ 【9.8】2017.09.08 第15次提交 参考nexT主题,重新设计index页面,重写HTML与CSS,加入jQuery动画【未解决:查询数据库最新生成的5篇文章失败】
+ 【9.9】解决9.8问题(mapping中的SQL语句改为以publictime排序),重写登录界面,未实现功能
  +【9.11】SpringMVC的Cookie+Interceptor实现自动登录
  +【9.12】代码优化,细节整理
  +【9.15~9.16】重写注册页面HTML+CSS+js(jQuery),实现JS验证,用户唯一性ajax验证,发送邮箱验证码验证,注册用户功能
  +【9.16晚】归档页面优化与整理
+ 【9.17~9.18】Article的Mapper,DAO,Service,重新设计,IDEA加入ideavim和emacsIDEAS插件,尝试键盘流,分类页面重新设计
+ 【9.21】重新设计文章页面HTML+CSS
+ 【9.22】文章评论模块的样式调整