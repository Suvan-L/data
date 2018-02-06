---
title: Oracle_知识体系
date: 2016-06-02 16:12:21
tags: Oracle
categories: Oracle
---


前言
====================

参考资料
- [ITPUB技术论坛](http://www.itpub.net/)
- [Oracle官方文档查阅](http://www.oracle.com/index.html)

**Oracle认证**
- OCA----认证专员，面对高校学员，考两门，一千多元        
- OCP-----Oracle认证专家
- OCM-----认证大师    



目录
========================


1. 登录与连接
2. DDL操作(创建-修改-删除)
3. DML操作(查询-更新-插入-删减)
4. 条件判断与内置函数
5. 服务器结构
6. 数据存储结构
7. 表空间和表分区
8. 表的存储参数
9. 序列
10. 数据类型
11. 约束
12. DCL操作(授权-回滚-提交)
13. 用户,权限，角色
14. 视图
15. 同义词
16. 事务处理
17. dual表
18. 设置概要文件
19. edit命令




## 1. 登录与连接
```
1.开启系统服务
	手动开启：计算机管理 -> 服务 -> OracleServiceORCL服务
	命令开启： net start OracleServiceORCL

2.进入sqlplus
	开启DOS
		用户密码登录：sqlplus
		无用户登录: sqlplus/nolog

3.连接用户
	<1>完整连接
		conn [用户名]/[密码]@IP:[端口号]/[数据库实例名] as [以什么身份登入]
			例：conn system/123 @10.3.62.27:1521/orcl as sysdba
					system用户			密码123，
					主机IP10.3.62.27 	端口1521
					实例名orcl   		以超级系统管理员身份登入。
	<2>普通用户
		conn [用户]/[密码]
	<3>无用户登录
		conn / as sysdba
	<4>断开连接
		disconn


4.修改用户密码流程
		1.show user 【显示当前正在连接的用户】
			password -> 输入新口令

		2.alter user [用户名] identified by [新密码]  
			【此操作需要权限】


5.锁定/解锁 用户
	alter user [用户] account (lock/unlock);  锁定/解锁


```

---
<br><br>



## 2. DDL操作(创建-修改-删除)

data definition language
+ create
+ alter
+ drop

```
<1>create

<2>alter
	　 修改约束：   alter table 表名 modify 列名 数据类型
　 　  删除列：     alter table 表名 drop column 列名
	   添加列：     alter table 表名 add 列名 数据类型【default 表达式】
	   设置列为不可用: alter table 表名 set unused(列名)

<3>drop
	删除表:  drop table 表名;



扩展*******************************
	<A>闪回表(从回收站恢复): 
		FLASHBACK TABLE 表名 TO BEFORE DROP;
	<B>截断表(删除所有行并释放空间)
		TRUNCATE TABLE 表名
	<C>表重命名
		 Rename 原表名 to  新表名

```

---
<br><br>



## 3. DML操作(查询-更新-插入-删除)

data manipulation language
+ insert
+ update
+ delete
+ select

```
<1>insert
	1.单行插入
		INSERT INTO table [(column1,column2,...)] VALUEs (value1,value2,...)
	2.多行插入
		 insert into 表 (列1,列2)
                     select 列1的值, 列2的值 from dual union all
                     select 列1的值, 列2的值 from dual union all
                     select 列1的值, 列2的值 from dual
			;
            commit;	
    3.查询插入
    	 INSERT into table2(field1,field2,...)   
　　　　　　            SELECT  value1,value2,... FROM table1；
<2>update
	update 表名 set 列名 = 新值 where 列名 = 已有值(条件判断)
	子查询更新
		UPDATE  tablename  SET column1 =  (select 子句) 

<3>delete
	delete from 表名 where 条件判断
	【注：只是将数据标记为unused，要清除大表数据存储空间，用truncate】

<4>select
	SELECT 目标表的列名或列表达式序列
　　　        FROM   基本表名和（或）视图序列
　　　            [ WHERE  行条件表达式 ]
　　　                [ GROUP BY   列名序列
　　　                     [ HAVING  组条件表达式 ]　]
　　　                        [ ORDER BY  列名[ ASC|DESC ]，… ] 
	【查询分类】
		连接查询
			等值连接
			内连接(join)
			外连接(左,右,全)
			交叉连接
		嵌套查询(select相互嵌套)
		集合查询
			并(union 和 union all)
			交(intersect)
			差(minus)
		分组查询
		单行子查询
		多行子查询
		多表查询

```

---
<br><br>


## 4. 条件判断与内置函数


where 后面加入判断语句
+ null `is null / is not null`
+ like 【通配符】

内置函数
1. 单行数字函数
2. 单行字符函数
3. 单行日期函数
4. 单行转换函数


案例：
```
查询忽略大小写？
select * from t_goods where lower（pname） like lower('&商品名称')
```
---
<br><br>


## 5. Oracle服务器结构
组成
+ 实例(Instance)
+ 数据库(Database)
+ 程序全局区(PGA)
+ 前台进程

```
<1>实例
	系统全局区(SGA)
		高速数据缓冲区
			脏数据区
			空闲区
			保留区
		共享池(Shared Pool)
			库告诉缓冲区
			共享SQL区
			私有SQL取
			字典高速缓冲区
		重做日志缓存区(Red log buffer cache)
		Java池
		大型池
		流池
	后台进程
		数据写入进程[DBWR]
		检查点进程[CKPT]
		日志写入进程[LGWR]
		归档进程[ARCH]
		进程监控进程[PMON]
		锁定进程[LCKN]
		恢复进程[RECO]
		调度进程[DNNN]
		快照进程[SNPN]
		....


<2>数据库
	数据文件(Data files)
	控制文件(Control files)
	重做日志文件(Redo log file)
  
<3>程序全局区(Program Global Area  -> PGA)
	非共享的内存区域【管理用户进程的私有资源】
	私有SQL区
		静态区
		动态区
		绘会话区

<4>前台进程
	用户进程
		连接
		会话
	服务器进程
		专用服务器模式
		共享服务器模式


```
---
<br><br>

## 6. 数据存储结构

+ 逻辑存储结构
+ 物理存储结构
```
<1>逻辑存储结构
	数据块(Data Blocks)
	数据区(Extent)
	段(Segment)
	表空间
		SYSTEM(SYSAUX01.DBF)
		UCDO(UNDOTBS01.DBF)
		USERS(USERS01.DBF)


<2>物理存储结构
	数据文件
		系统数据
		撤销数据
		用户数据
	控制文件
		二进制
	日志文件
		重做日志(Redo Log File)
		归档日志(Archive Log File)
	服务器数文件
		二进制
	口令文件(密码文件)
		二进制
	警告文件
	跟踪文件
		后台进程跟踪
		用户进程跟踪

```

---
<br><br>


## 7. 表分区和表空间

分区
+ 范围分区
+ 散列分区
+ 列表分区
+ 组合分区

常用操作
```
<1>显示表空间的结构	desc v$tablespace

<2>显示当前数据库所拥有的表空间
	select name from v$tablespace
	select name from v$database

<3>查询系统拥有的数据文件名 和 表空间名
	select file_name,tablespace_name from dba_data_files;

<4>删除数据文件
alter tablespace tablespace_name drop datafile file_name;

<5>创建建表空间语句：
    create tablespace testtp datafile 'd:/testtp01.dbf' size 1m
    autoextend on next 1m maxsize 10m   

<6>增加新的控制文件，修改表空间大小
alter teblespace testtp add datafile 'd:/tesstp02.dbf' size 10m;


<7>删除表空间 同时删除控制文件：
drop tablespace tpname including contents and datafiles


<8>将表空间模式改为只读：alter tablespace tpname READ ONLY
        模式改为读写：alter tablespace liushuwei read write;

<9>扩展表空间：
	增加数据文件：
 		alter tablespace table_name add datafile '数据文件' size 400m;
	增加数据文件的大小：
		alter tablespace table_name add datafile '数据文件' resize 400m;
		【若是你单独的想增大数据文件的大小，一个个增加就可以，同上】
 
	可以设置数据文件的自动增长
		alter tablespace table_name datafile '数据文件' autoextend on next 10m maxsize 500m;

```

---
<br><br>



## 8. 表的存储参数

+ TABLESPACE：表格所在的表空间
+ PCTUSED : 数据块使用空间参数
+ PCTFREE : 数据块自由空间参数
+ INITRANS ：同时更新某个数据块的事务处理的数目
+ MAXTRANS：更新某个数据块的事务处理的最大数目
+ INITIAL：     第一扩展区的大小
+ NEXT:          第二扩展区的大小
+ PCTINCREASE : 扩展区递增百分率
+ MINEXTENTS:   指定最小扩展区的数量
+ MAXEXTENTS：指定最大扩展区的数量
+ FREELISTS：    指定空闲列表组中表数量，默认及最小值为1
+ FREELIST GROUPS：指定表格空闲列表组的数量,用于并行服务器
+ Buffer_pool：   指定表格是否存储到数据缓存及存储缓存的具体位置
    　 　 （缓冲池：KEEP、RECYCLE 、DEFAULT）

<br>

例子：带参数的表格创建
```
CREATE TABLE EMP ( 
    EMP_ID    CHAR (10) primary key, 
    EMP_NAME  VARCHAR2 (30), 
    SAL       NUMBER (5), 
    TEL       VARCHAR2 (20)  )
>TABLESPACE MYSPACENAME
        PCTUSED    10 
        PCTFREE    10 
        INITRANS    1
         MAXTRANS   100
         STORAGE ( 
            INITIAL      1048576   
            NEXT        1048576
            PCTINCREASE  50       
            MINEXTENTS   1
            MAXEXTENTS   512     
            FREELISTS       1 
            FREELIST GROUPS 1   
            Buffer_pool  Recycle  
        )


```
---
<br><br>


## 9. 序列


序列（SEQUENCE）
　序列是一数据库对象，利用它可生成唯一的整数。由于它属于可共享对象，所以允许多个用户访问。一般情况下，序列用于创建主键值。

```
<1>创建
	 CREATE SEQUENCE sequence_name 
　　　　 　　[START WITH start_num] 
　　　　　 　[INCREMENT BY increment_num] 
　　　　 　　[{MAXVALUE maximum_num|NOMAXVALUE}] 
　　       　[{MINVALUE minimum_num|NOMINVALUE}] 
　　     　　[{CYCLE|NOCYCLE}] 
　　     　　[{CACHE cache_num|NOCACHE}] 
　　     　　[{ORDER|NOORDER}] 

			start_num序列开始整数  
			increment 每次增加的整数增量  
			maximum：序列最大整数  
			minimum：序列最小整数  
			CYCLE：循环生成  
			NOCYCLE：不循环生成，默认值  
			cache_num：保存在内存中的整数的个数  
			NOCACHE：不缓存任何整数  



<2>查询
	DESC user_sequences
	SELECT 序列名, min_value, max_value, increment_by, last_number
        FROM user_sequences;
    
    SELECT * FROM USER_SEQUENCES;
	SELECT * FROM ALL_SEQUENCES;
	SELECT * FROM DBA_SEQUENCES;


<3>修改
	不能修改序列的初值  
	序列的最小值不能大于当前值  
	序列的最大值不能小于当前值  

	ALTER SEQUENCE SEQ_TEST INCREMENT BY 2;


<4>删除
	DROP SEQUENCE seq_test;

<5>nextval 和 currval
	CURRVAL: 返回序列的当前值。
	NEXTVAL： 序列递增，返回下一值。


<6>例子：
/*
  设计系统商品数据盘点的表
  表名： t_goods_date
  字段：
    dateid: NUMBER(10)  数据id，自动递增
    checktime: DATE  盘点时间
    gid: CHAR(6) 
    datetype: CHAR(1) 1-损耗，2，结余
*/
CREATE TABLE  t_goods_date(
  deteid NUMBER(10) PRIMARY KEY,
  checktime DATE DEFAULT SYSDATE,
  gid CHAR(6) REFERENCES t_goods(gid),
  detetype CHAR(1) check(detetype in('1','2')) NOT NULL
)TABLESPACE tpshopping
PCTUSED 30  --默认10，数据块使用空间参数
PCTFREE 20;  --默认40数据块自由空间参数 ，两个加起来做好不要超过100

--创建自动增长序列序列
CREATE SEQUENCE seq_goodsdate
START with 1
INCREMENT BY  1
MAXVALUE 99999999
MINVALUE 1
NOCACHE;

--创建触发器实现自动增长
CREATE OR REPLACE TRIGGER tri_seq_insert
  BEFORE INSERT ON t_goods_date
  FOR EACH ROW
    BEGIN
      SELECT seq_goodsdate.nextval INTO :new.gid FROM dual;
    END;
    /
show ERROR;


```

---
<br><br>


## 10. 数据类型

+ char(n)		【定长字符串】
  + varchar2(n)【可变长字符串】
  + number(m,n)【数字,m-位数，n-小数点位数】
+ data          【日期】
  + timestamp(n)	【日期，可精确到毫秒】
+ blob
+ club
  + nclob		【4GB大对象(LOB)】


---
<br><br>


## 11. 约束

+ 非空(NOT NULL)
+ 唯一(UNIQUE)
+ 主键(PRIMARY KEY)
+ 外键(FOREIGN KEY)
+ 检查(CHECK)

扩展
1. ON DELETE CASCADE 【引用关联删除，外键约束时可设置】
2. ONDELETE SET NULL 【引用关联设置NULL，删除父表某行时，关联的设置为NULL，外键约束时可设置】

```
<1>查询约束
	查询T_USER表内所有的约束(主键，外键)
		SELECT * FROM user_cons_columns WHERE table_name='T_USER';


<2>修改约束
	修改某列主键外键约束
		ALTER TABLE t_user MODIFY uiid VARCHAR2(20);

<3>删除约束【根据约束名】
	删除外键约束 ALTER TABLE t_user DROP CONSTRAINT SYS_C0011816;
	删除主键约束 ALTER TABLE t_user DROP CONSTRAINT SYS_C0011818 cascade;

<4>增加约束
	添加主键
		不指定约束名,系统自动
			ALTER TABLE t_user ADD primary key(uiid);
		指定约束名
			ALTER TABLE t_user ADD constraint pk_one primary key(uiid);
	添加外键
		不指定约束名,系统自动,且设置(ON DELETE CASECADE)属性
			ALTER TABLE t_main_order ADD foreign key(uiid) references t_user(uiid) ON DELETE CASCADE;
		指定约束名
			ALTER TABLE t_main_order ADD constraint fk_one foreign key(uiid) references t_user(uiid) ON DELETE CASCADE; 

	注意：
		1.重新添加外键引用时候，要先删除原有的外键约束！！
		2.添加外键约束的时候，有ON DELETE CASCADE这个属性，则说明t_user(主表) 删除一条记录的同时，那么t_main_order(子表)对应关联的数据，也跟着删除


<5>案例：查询当前用户所有的主键，外键信息所对应的表信息
		SELECT
		  USER_CONS_COLUMNS.CONSTRAINT_NAME AS 约束名,
		  USER_INDEXES.TABLE_NAME AS 主表名,
		  USER_IND_COLUMNS.COLUMN_NAME AS 主表列名,
		  USER_CONS_COLUMNS.TABLE_NAME AS 子表名,
		  USER_CONS_COLUMNS.COLUMN_NAME AS 子表列名,
		  USER_CONS_COLUMNS.POSITION AS 位置

		FROM
		  USER_CONSTRAINTS
		    JOIN USER_CONS_COLUMNS
		    ON (USER_CONSTRAINTS.CONSTRAINT_NAME
		        = USER_CONS_COLUMNS.CONSTRAINT_NAME)
		    JOIN USER_INDEXES
		    ON (USER_CONSTRAINTS.R_CONSTRAINT_NAME
		        = USER_INDEXES.INDEX_NAME)
		    JOIN USER_IND_COLUMNS
		    ON (USER_INDEXES.INDEX_NAME = USER_IND_COLUMNS.INDEX_NAME)
		WHERE
		  CONSTRAINT_TYPE = 'R';
```

---
<br><br>


## 12. DCL操作(授权-回滚-提交)

Data Control Language
+ grant
+ rollback
+ commit


```
<1>grant
<2>rollback
	rollback会返回整个事物

	DML语句手动提交，可以进行rollback
	DDL和DCL语句，已经自动提交了，无法rollback
<3>commit
	显式提交
		用commit命令直接完成的提交为显式提交。
	隐式提交
		用SQL命令间接完成的提交为隐式提交。
	自动提交
		若把AUTOCOMMIT设置为ON，则在插入、修改、删除语句执行后，系统将自动进行提交，这就是自动提
		例如： SQL>SET AUTOCOMMIT ON


```

---
<br><br><br>


## 13. 用户,权限，角色

+ 用户(user)
+ 权限
+ 角色

```
<1>用户
	创建
		默认
			create  user  用户名  identified  by  密码 ;
		自定义
			create user 用户 identified by 密码
	            default tablespace 默认表空间   
	            quota 空间配额(m) on 表空间        
	            password expire;
     删除用户
     	默认
     		Drop user annie
     	拥有对象的用户
     		Drop user annie cascade

     查询
     	所有用户
     		select * from dba_users;     
    		select * from all_users;     
    		select * from user_users;
    	用户权限
    		    select * from dba_sys_privs;【系统权限】
   	 			select * from user_sys_privs;
   	 			select * from dba_tab_privs;【用户对象权限】
     		    select * from all_tab_privs;
    			select * from user_tab_privs;
    	用户拥有角色
    			select * from dba_role_privs;
  				select * from user_role_privs;

  		案例；
  			查询s04用户的 用户名 和 所对应的表空间 
  				select username,default_tablespace from dba_users where username='S04';
  			查询库里所有用户所，和 所对应的表空间
  				select username,default_tablespace from dba_users;
  			查询16年03月24日创建的用户
				select username,default_tablespace from dba_users where to_char(created,'yymmdd') ='160324';
			查询s04用户的 用户名 和 所对应的表空间 
       			select username,default_tablespace from dba_users where username='S04';

<2>权限
	授权
		授予用户
		 	grant create session to annie;
		 授予角色，再将角色授予用户
		 	  Create role  teach;                
           	  grant  select  on  class  to  teach;   
              grant  teach  to  annie;       

    系统权限
    对象权限
    	类型
    		 Select
    		 Update
    		 Delete
    		 Insert
    		 Execute
　 　 　 	 Index
			 reference
			 Alter
			 Read
			 With Grant Option【对象传递权限】

	回收权限
		回收系统权限：revoke select any  from annie;
　  　  回收对象权限：revoke select on class from annie;

<3>角色
	常见预定义角色
		connect
		resource
		dba
		SELECT_ CATALOG_ROLE

	创建
		Create  role  角色名

	授权
		Grant 权限 on class  to  角色名

	授予用户
		Grant  角色名  to  用户
	授予角色
		rant 角色 to 接收角色  [with admin option]

	回收角色权限
		Revoke 权限 on class from  角色名

	从用户回收角色
		Revoke 用户  from 角色名
	从角色回收角色
		Revoke 角色 from 被回收角色

	删除角色
		drop role r_teach

	设置默认角色的4中方式
	　  1)  Alter User Annie Default Role r_Teach
　  	2)  Alter User Annie Default Role  All
　  	3)  Alter User Annie Default Role  All Except Role r_Stu
　 		4)  Alter User Annie Default Role  None

	查询
		视图DBA_Roles: 角色信息
　  	视图Role_role_privs:角色与传递权限信息
　  	视图Role_SYS_privs:角色系统权限
　  	视图Role_tab_privs:角色对象权限



```

---
<br><br>

## 14. 视图

视图视图（类似于表）是一种数据库对象。

```
<1>语法
	CREATE [OR REPLACE] [FORCE|NOFORCE] VIEW 试图名   
			[(别名[, 别名]...)]   
			AS 子查询  
			[WITH CHECK OPTION [CONSTRAINT constraint]]   
			[WITH READ ONLY] 
  


```
---
<br><br>

## 15. 同义词

　 同义词在SQL 中的含义与其它语言中的含义一样，是指可接受的替代另一个词的词或表达式。
　 同义词可简化访问对象的操作，即为对象创建另一个名称。同义词可以简化访问其他用户所有的表的操作，并可缩短冗长的对象名。

语法结构：
　   create [public] synonym  同义词for 对象;

解释：   
+ public：创建所有用户都可以访问的同义词
+ 同义词：是要创建的同义词的名称
+ 对象：标识要为其创建同义词的对象

---
<br><br>


## 16. 事务处理
　处理允许用户对数据进行更改，然后决定是保存还是放弃所做的更改。数据库事务处理将多个步骤捆绑到一个逻辑工作单元。

事务处理何时开始或结束事务处理
　　　　从第一个DML（INSERT、UPDATE、DELETE 或MERGE）语句开始。
　　　  出现以下任一情况时事务处理结束：
　　　　　　　　<1>发出了COMMIT 或ROLLBACK 语句
　　　　　　　　<2>发出了DDL和DCL
　　　　　　　　<3>用户退出了iSQL*Plus 或SQL*Plus

---
<br><br>


## 17. dual表

虚拟表

```
<1>应用场景
	查看当前用户 	 select user from dual;
	获取当前系统时间 select to_char(sysdate,'yyyy-mm-dd hh24:mi:ss') from dual
    获得主机名 		 select SYS_CONTEXT('USERENV','TERMINAL') from dual;
    获得当前位置 	 select SYS_CONTEXT('USERENV','language') from dual;
    获得一个随机数   select dbms_random.random from dual;

    获得序列your_sequence的下一个值 select your_sequence.nextval from dual;
    获得序列your_sequence的当前值 select your_sequence.currval from dual;

    用作计数器 select 7*9 from dual;

```
---
<br><br>


## 18. 设置概要文件
```
<1>创建
	CREATE PROFILE "TEMPPROFILE" LIMIT 
　　CPU_PER_SESSION 1000            【限制一个会话使用的CPU时间】
　　CPU_PER_CALL 1000               【限制一个SQL语句使用的CPU时间】
　　CONNECT_TIME 30                 【指定一个会话连接到数据库的最大时间】
　　IDLE_TIME DEFAULT               【指定一个会话可以连续空闲的最长时间，单位：分钟】
 　 SESSIONS_PER_USER 10            【限制用户当前会话的数量】
　　LOGICAL_READS_PER_SESSION 1000【限制每个会话读取的数据库数据块数，包括从内存和磁盘读取的总和】
　　LOGICAL_READS_PER_CALL 1000   【限制SQL语句读取的数据库数据块数，包括从内存和磁盘读取的总和】
　　PRIVATE_SGA 16K                 【CONNECT_TIME综合决定】
　　COMPOSITE_LIMIT 1000000         【设置用户对系统资源的综合消耗】
　　FAILED_LOGIN_ATTEMPTS 3         【 最大错误登录次数】
　　PASSWORD_LOCK_TIME 5            【登录失败后账户被锁天数】
　　PASSWORD_GRACE_TIME 60          【用户密码被中止前多少天提醒用户修改密码】
　　PASSWORD_LIFE_TIME 30           【密码有效天数】
　　PASSWORD_REUSE_MAX   DEFAULT    【密码被重新使用后，可修改的次数】
　　PASSWORD_REUSE_TIME 30          【用户修改密码后多少天，用户才可以再次使用原来的密码】
　　 PASSWORD_VERIFY_FUNCTION DEFAULT【密码复杂度审计函数】


<2>查询
	显示dba_users表结构 desc dba_users
	查询用户和 所对应的概要文件 select username,profile from dba_users;


<3>修改
	将s04用户默认所指定的概要文件default改为，p_test
		 alter user s04 profile p_test;


<4>删除
	drop profile p_test

注意：
 	1.oracle默认一个会话的连续空闲时间是15分钟，就会自动退出


```
---
<br><br>


## 19.edit命令
在sqlplust输入`edit`会弹出文本，显示缓冲区最前的命令(上一个命令)
【可以更改命令，然后直接关闭文本，点击保存，返回DOS窗体，输入/ 回车，命令重新运行】

