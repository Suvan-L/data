---
title: MySQL_知识体系
date: 2016-04-17 11:28:36
tags: MySQL
categories: MySQL
---

目录
===========================
1. 登录与连接
2. DDL操作(创建-修改-删除)
3. DML操作(查询-更新-插入-删减)
4. 约束
5. 内置函数库
6. 分组和限制
7. 图形化管理工具



## 1.  登录与连接
```
<1>登录
	开启DOS窗体
		mysql -V 【输出版本信息】
		mysql -h[主机名 or 主机ip] -u[用户名] P[端口号] -p
		mysql -h[主机名 or 主机ip] -u[用户名] P[端口号] -p[密码]

		例子：mysql -u root -p

<2>修改Mysql提示符
	prompt [字符 or 预定义提示符]
		\D 【完整日期】
　　　　\d 【当前数据库】
　　　　\h 【服务器名称】
　　　　\u 【当前用户】
	例：prompt suvan|\d> 


<3>退出
	三种命令
		exit
		quit
		\q


<4>查询数据库情况

	describe mysql.user;	【查询当前数据库所有用户】

	SELECT DATABASE();	【显示当前使用的数据库】
	SELECT NOW();		【显示当前完整日期】
	SELECT USER();		【显示当前用户】
	SELECT VERSION();	【显示当前服务器版本】
	

	SHOW CREATE DATABASE [数据库名]  【创建数据库时的指令】
	SHOW COLUMNS FROM [表名]		 【查看数据库表结构】
	SHOW DATABASE					 【展示所有数据库】
	SHOW INDEXES FROM [表名]		 【查看表的索引】
	SHOW TABLES; 					 【显示当前数据库中的所有表】
	SHOW TABLES FROM mysql           【显示Mysql所有数据库的所有表】
	SHOW WARNINGS					 【提示警告信息】

	SET [数据库名] gbk				 【客户端以gbk编码显示数据】

	USE [数据库名]					 【使用指定数据库】


<5>修改用户密码
	DOS窗体，进入mysql\bin目录(如果当前目录配置了Path环境变量则不必进入)
		输入： mysqladmin -u[用户名] -p[旧密码] password [新密码]
		例： mysqladmin -uuser1 -pabc321 password abcdefg


<6>数据库备份
	DOS窗体，进入mysql\bin目录(如果当前目录配置了Path环境变量则不必进入)
		输入：mysqldump -u[用户名] -p[密码] --databases [数据库名表] > [文件名.后缀]
		例： mysqldump -uroot -p123 --databases test >test.sql


<7>数据库恢复
		输入：mysql -u[用户名] -p[密码] [数据库名] <[备份文件名]
		例子：mysql -uroot -p123456 test>test.sq


<8>修改数据库字符集
	use mydb
	alter database mydb character set utf8;


<9>创建数据库时,指定字符集
	create database mydb character set utf8;

```



## 2. DDL操作(创建-修改-删除)

+ create
+ alter
+ drop

```
<1>create

<2>alter
	增加列：
		ALTER TABLE [表名] ADD  [列名]([列约束]);
	删除列：
		ALTER TABLE [表名] DROP [列名] 
	添加主约束
	删除约束
		例: ALTER TABLE [表名] DROP PRIMARY KEY;

<3>drop

```

---
<br><br>

## 3. DML操作(查询-更新-插入-删减)

+ insert
+ update
+ delete
+ select

```
<1>insert

	INSERT INTO [表名] ([列名],....) [VALUES|VALUE]
　　　　　　([值],...)
	INSERT INTO [表名] SET [列名] ={expr|DEFAULT},...
	INSERT INTO [表名] ([列名],...) SELECT ...

<2>update
	单表更新
		例：UPDATE user set age =age+5;
			UPDATE user SET age =age -id,sex=0;  
			UPDATE users SET age= age +10 WHERE id % 2=0;
	多表更新

<3>delete
	单表删除
		例：DELETE FROM user WHERE id= 6;
	多表删除

<4>select
	网格显示查询结果
		SELECT * FROM [表名]\G;

	子查询
	ANY,SOME,ALL比较运算符
 	[NOT] IN的子查询
 	[NOT] EXISTS的子查询

 	IN JOIN				【内连接】
 	LEFT [OUTER] JOIN   【左外连接】
 	RIGHT [OUTER] JOIN  【右外连接】

```

---
<br><br>



## 4. 约束
+ AUTO_INCREMENT	【主键自增，默认起始值为1，增量1】
  + PRIMARY KEY 【主键约束，表中唯一，NOT NULL】
+ UNIQUE KEY        【唯一约束，记录唯一】
  + DEFAULT	【默认约束，自动赋予默认值】
+ FOREIGN KEY       【外键约束，一对一 or 一对多】


```
<1>AUTO_INCREMENT
<2>PRIMARY
	例：
		CREATE TABLE one（
	　　　　　　id SMALLINT UNSIGNED AUTO_INCREMENT PRIMARY KEY
	　　):

<3>UNIQUE KEY 

<4>DEFAULT
	例：
		CREATE TABLE A(
	　　　　id SMALLINT PRIMARY KEY,
	　　　　sex ENUM('1','2','3') DEFALUT '3' 【sex字段默认为3】
	　　);   

```

---
<br><br>

## 5. 内置函数库
1. 字符函数
2. 数值运算符与函数
3. 比较运算符与函数
4. 日期时间函数
5. 信息函数
6. 聚合函数
7. 加密函数

---
<br><br>



## 6. 分组和限制
+ group by 【查询结果分组】
+ having   【分组条件】
+ limit    【查询结果限制】


```
<1>group by
	GROUP BY [列名 | 数字] [ASC(升序) | DESC(降序)],..
		例：SELECT  sex FROM users GROUP BY sex;
	对查询结果排序
		例：SELECT * FROM users ORDER BY id DESC

<2>having
	HAVING 判断条件
	例：　SELECT sex FROM user GROUP BY 1 HAVING count(id) >=2


<3>limit
	[LIMIT {[o默认从第一行] 行数 | 行数 OFFSET [记录偏移值]}]
	例：SELECT * FROM users LIMIT 2; 【从第一条开始，返回两条记录】
		SELECT * FROM user LIMIT 3,2; 
			【从第三条开始，返回两条记录，返回的是4，5，不是3，4】

	注意：
		1.当前SELECT语句是从0开始编号的，第1条记录是0，第2条是1
		2.结果集排列顺序，和id没关系，第一个位置就是0

```

---
<br><br>


## 7.图形化管理工具
+ PHPMyAdmin(Web界面的)，使用前提，计算机上使用PHP来支持它
+ Navicat(以平台方式运行的)（中文版）需要注册
+ MySQL Workbench(以平台方式运行的)（英文版）