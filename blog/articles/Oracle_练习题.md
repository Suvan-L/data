---
title: Oracle_练习题
date: 2016-04-24 14:19:21
tags: Oracle
categories: Oracle
---


# 目录

1. 在用户表中添加以学号为中心的前后共5个同学的信息
2. 用批量添加数据的方法为每一个用户创建一个订单，其中订单号采用序列的产生流水号
3. 为每个用户的订单增加3个以上的商品明细记录
4. 查询显示商品信息，要求显示商品编号、商品名称、商品单价
5. 查询显示商品单价在10和100之间的商品信息
6. 查询显示商品库存超过最高库存或低于最低库存的商品信息
7. 查询没有电话号码的供应商信息
8. 查询供应商中联系人姓李的信息
9. 查询显示商品信息，要求显示商品ID、商品名称、单价（取整），折扣（保留1位小数位），库存（取整）
10. 查询当月过生日的用户信息
11. 查询XXXX年XX月订单总金额在200块以上的用户的订单次数和订单总金额。
12. 查询显示有折扣的商品信息，要求显示商品名称、商品类型名、商品单价、折扣、库存
13. 查询商品领带共采购了多少？
14. 查询显示采购单信息，要求显示采购单号、采购日期、供应商名称、总金额、状态（1显示待审核，2显示已审核）
15. 查找年龄最小的用户信息
16. 按条件查询显示在当月有采购来往的供应商信息
17. 查询显示在当月有采购来往的供应商所有信息
18. 年底报表中需要将采购单主表和订单主表信息显示在一个报表中，显示：单号、客户名称（采购为供应商名称，订单为用户名称）、日期（yy-mm-dd）、总金额（四舍五入取整）、单据状态、类型（采购单显示‘采购’，订单显示‘订购’）
19. 将17中的查询定义为一个视图，并针对该视图查询一下信息
20. 系统中有几个商品信息数据维护人员 是根据商品类型分工的，其中一个维护人员Jane是维护童装精品的商品信息的，请为Jane创建一个视图
21. SQL脚本(删除,创建，插入)
22. 根据需求建表
23. 在读者表中增加读者等级列，列名为使用查询的账户名，字符串长度为1，等级可选为：教师(可借图书为5本)、学生(可借图书为3本)、社会人员(可借图书为1本)
24. 请在读者表中添加以你的账户名前后最近的至少3个以上不同的读者数据，图书表中添加3本以上你本学期使用的教材图书信息，在借书记录中为你的每个读者添加至少一条借书记录
25. 创建一个视图（名字自拟），视图数据为借阅记录信息(借书记录号,借阅读者姓名，图书名称，应该归还日期，实际归还日期（yyyy-mm-dd）,借阅天数,超期天数)
26. 创建一个视图（名字自拟），视图为图书信息（图书编号，图书名称，出版社，在馆数（库存数减去已借出未归还的图书数）


## 1. 在用户表中添加以学号为中心的前后共5个同学的信息
```
CREATE SEQUENCE stu_num
    START WITH 36
    INCREMENT BY 1
    MAXVALUE 41;
    
INSERT INTO t_user(uiid,uname,ubirthday,usex,uaddress,utelephone)
    VALUES (stu_num.nextval,'学生36',to_date('1996-07-28','yyyy-mm-dd'),'f','佛山','123456');
INSERT INTO t_user(uiid,uname,ubirthday,usex,uaddress,utelephone)
    VALUES (stu_num.nextval,'学生37',to_date('1996-09-28','yyyy-mm-dd'),'f','深圳','123456');
INSERT INTO t_user(uiid,uname,ubirthday,usex,uaddress,utelephone)
    VALUES (stu_num.nextval,'学生38',to_date('1998-07-28','yyyy-mm-dd'),'m','四川','123456');
INSERT INTO t_user(uiid,uname,ubirthday,usex,uaddress,utelephone)
    VALUES (stu_num.nextval,'学生39',to_date('1999-07-28','yyyy-mm-dd'),'f','香港','123456');
INSERT INTO t_user(uiid,uname,ubirthday,usex,uaddress,utelephone)
    VALUES (stu_num.nextval,'学生40',to_date('1995-07-28','yyyy-mm-dd'),'m','佛山','123456');
```



## 2. 用批量添加数据的方法为每一个用户创建一个订单，其中订单号采用序列的产生流水号
```
CREATE SEQUENCE uiid_num
    START WITH 1
    INCREMENT BY 1;
  
INSERT INTO t_main_order (omid,uiid,odate,oamount,ostate)
    SELECT uiid_num.nextval,uiid,sysdate,null,'1' FROM t_user;
    
DELETE   t_order_items;
```




## 3. 为每个用户的订单增加3个以上的商品明细记录
```
CREATE SEQUENCE ope_money
  START WITH 20
  INCREMENT BY 2;

INSERT INTO t_order_items (omid,gid,oprice,onum,omemo)
  SELECT OMID,gid,ope_money.nextval,null,null FROM t_main_order,t_goods;
```




## 4. 查询显示商品信息，要求显示商品编号、商品名称、商品单价
```
DESC t_goods;
SELECT gid AS 商品编号,gname AS 商品名称,gprice AS 商品单价 FROM t_goods;
```




## 5.  查询显示商品单价在10和100之间的商品信息
```
DESC t_goods;
SELECT gid,gname,gid,gprice,gdiscount,gstocks,gmaxstocks,gminstocks,gmemo
    FROM t_goods WHERE gprice between 10 and 100;
```





## 6. 查询显示商品库存超过最高库存或低于最低库存的商品信息
```
DESC t_goods
SELECT gid,gname,gid,gprice,gdiscount,gstocks,gmaxstocks,gminstocks,gmemo
    FROM t_goods WHERE gstocks >gmaxstocks or gstocks <gminstocks;
```




## 7. 查询没有电话号码的供应商信息
```
DESC t_supplier
SELECT sid,sname,scontact,sphone,smemo
  FROM t_supplier WHERE sphone is null;
```




## 8. 查询供应商中联系人姓李的信息
------------------------
```
DESC t_supplier
SELECT sid,sname,scontact,sphone,smemo
  FROM t_supplier WHERE scontact Like '李%';
```



## 9.  查询显示商品信息，要求显示商品ID、商品名称、单价（取整），折扣（保留1位小数位），库存（取整）
-------------------------
```
DESC t_goods
SELECT gid,gname,gid,ROUND(gprice),ROUND(gdiscount,1),TRUNC(gstocks)
  FROM t_goods;
```





## 10.  查询当月过生日的用户信息
```
DESC t_user;
SELECT uiid,uname,ubirthday,usex,uaddress,utelephone
    FROM t_user
    WHERE to_char(ubirthday,'mm')=to_char(sysdate,'mm');
```




## 11. 查询XXXX年XX月订单总金额在200块以上的用户的订单次数和订单总金额
```
DESC t_mian_order
SELECT  omid,oamount
    FROM t_main_order
    WHERE to_char(odate,'yyyy-mm')='2016-04'
        AND oamount >=200;
```




## 12. 查询显示有折扣的商品信息，要求显示商品名称、商品类型名、商品单价、折扣、库存
```
DESC t_goods       
SELECT gname,gtname,gprice,gdiscount,gstocks
  FROM t_goods a,t_gtype b
  WHERE  a.gid=b.gtid AND
    gdiscount IS NOT NULL;
```




## 13. 查询商品领带共采购了多少？
```
DESC t_procure_items
SELECT sum(pinum) AS 领带 FROM t_procure_items
  WHERE gid =
    (SELECT gid FROM t_goods WHERE gname='领带');
```




## 14. 查询显示采购单信息，要求显示采购单号、采购日期、供应商名称、总金额、状态（1显示待审核，2显示已审核）
```
DESC t_main_procure
SELECT  pmid,pdate,b.sid,pamount,pstate
  FROM t_main_procure a,t_supplier b
  WHERE a.sid =b.sid(+);
```



## 15. 查找年龄最小的用户信息
``` 
DESC t_user;
SELECT uiid,uname,ubirthday,usex,uaddress,utelephone
  FROM t_user
  WHERE ubirthday=
  (SELECT max(ubirthday) from t_user);
```





## 16. 按条件查询显示在当月有采购来往的供应商信息
```
DESC t_supplier;
SELECT a.sid,sname,scontact,scontact,sphone,smemo
  FROM t_supplier a,t_main_procure b
  WHERE  a.sid=b.sid(+)
      AND to_char(b.pdate,'mm')=to_char(sysdate,'mm');
```





## 17.  查询显示在当月有采购来往的供应商所有信息
```
DESC t_user;
SELECT a.uiid,uname,ubirthday,usex,uaddress,utelephone,oamount
  FROM t_user a,t_main_order b
  WHERE a.uiid =b.uiid(+);
```





## 18. 年底报表中需要将采购单主表和订单主表信息显示在一个报表中，显示：单号、客户名称（采购为供应商名称，订单为用户名称）、日期（yy-mm-dd）、总金额（四舍五入取整）、单据状态、类型（采购单显示‘采购’，订单显示‘订购’）
``` 
DESC t_main_procure;
DESC t_main_order;
SELECT omid AS 订单单号,uname AS 用户名称,
       to_char(odate,'yy-mm-dd') AS 销售日期,round(oamount) AS 总金额,
       decode(ostate,'1','待审核','2','发货中','3','已完结','4','取消'),'订单'
       FROM t_main_order a ,t_user b
       WHERE a.uiid =b.uiid(+)
UNION      
SELECT pmid AS 采购单号,sname AS 供应商名称，
      to_char(pdate,'yy-mm-dd') AS 采购日期,round(pamount) AS 总金额,
      decode(pstate,'1','待审核','2','已经审核'),'采购'
      FROM t_main_procure c,t_supplier d
      WHERE c.sid =d.sid(+);
  
```





## 19. 将17中的查询定义为一个视图，并针对该视图查询一下信息
```
CREATE VIEW one_view(单号,客户名称，销售日期,总金额，单据状态，类型) AS
DESC t_main_order;
SELECT omid AS 订单单号,uname AS 用户名称,
       to_char(odate,'yy-mm-dd') AS 销售日期,round(oamount) AS 总金额,
       decode(ostate,'1','待审核','2','发货中','3','已完结','4','取消'),'订单'
       FROM t_main_order a ,t_user b
       WHERE a.uiid =b.uiid(+)
UNION      
SELECT pmid AS 采购单号,sname AS 供应商名称，
      to_char(pdate,'yy-mm-dd') AS 采购日期,round(pamount) AS 总金额,
      decode(pstate,'1','待审核','2','已经审核'),'采购'
      FROM t_main_procure c,t_supplier d
      WHERE c.sid =d.sid(+);
```





## 20. 系统中有几个商品信息数据维护人员 是根据商品类型分工的，其中一个维护人员Jane是维护童装精品的商品信息的，请为Jane创建一个视图 
```      
CREATE OR REPLACE VIEW two_view_people AS
  SELECT gid,gname,gtname,gprice,gdiscount,gstocks,gmaxstocks,gminstocks,gmemo
    FROM t_goods a,t_gtype b
    WHERE a.gtid =b.gtid(+);
```



## 21. SQL脚本(删除,创建，插入)
```
---删除表
drop table t_user_evaluation;
drop table t_procure_items;
drop table t_order_items;
drop table t_order;
drop table t_main_procure;
drop table t_goods;
drop table t_supplier;
drop table t_gtype;
drop table t_user;


---注册用户表：t_user
        create table t_user(
            uiid    char(6) primary key,    
            uname   varchar2(20) not null,  
            ubirthday date,         
            Usex  char(1) check(usex in ('f', 'm')),
            uaddress varchar2(50),      
            utelephone  varchar2(20)
        );

--商品类型表:t_gtype
        create table t_gtype(
            gtid char(6) primary key,
                gtname varchar2(20) not null
        );

--商品信息表
        create table t_goods(
            gid char(6) primary key,
            gname varchar2(20) not null,
            gtid char(6) references t_gtype(gtid),
            gprice number(12,3) check(gprice>0),
            gdiscount number(5,2),
            gstocks number(7,2) check(gstocks>=0),
            gmaxstocks number(7,2) check(gmaxstocks>=0),
            gminstocks number(7,2) check(gminstocks>=0),
            gmemo varchar2(50)
        );

----供应商信息表(t_supplier)
        create table t_supplier(
            sid char(6) primary key,    
            sname   varchar2(20) not null,  
            scontact varchar2(20) ,     
            sphone  varchar2(15) ,      
            smemo   varchar2(50)
        );  
    

--采购单主表(t_main_procure)
        create table t_main_procure(
            pmid char(12) primary key,
            sid char(6)  references t_supplier(sid),
            pdate date default sysdate,
            pamount number(12,3),
            pstate char(1) check(pstate in('1','2')),
            pmemo   varchar2(50)
        );


--采购明细表(t_procure_items)
        create table t_procure_items(
            pmid char(12) references t_main_procure(pmid)  ,
            gid char(6)  references t_goods(gid) ,
            piprice number(8,2) check(piprice>0),
            pinum number(8,2) check(pinum>0),
            pimoney number(12,3),
            pimemo  varchar2(50)
        );


--订单主表（t_main_order）
        create table t_main_order(
            omid char(12) primary key,
            uiid char(6) not null references t_user(uiid),
            odate date default sysdate,
            oamount number(12,3),
            ostate char(1) check(ostate in('1','2','3','4'))
        );


--订单明细表(t_order_items)

        create table t_order_items(
            omid char(12) references t_main_order(omid) ,
            gid char(6)  references t_goods(gid) ,
            oprice number(8,2) check(oprice>0),
            onum number(8,2) check(onum>0),
            omemo   varchar2(50),
            primary key(omid,gid)
        );


--评价表：t_user_evaluation
        create table t_user_evaluation(
            ueid char(10) primary key,
            omid char(6)  ,
            gid char(6) ,
            foreign key(omid,gid) references t_order_items(omid,gid),
            uedate date default sysdate,
            uetype char(1) check(uetype='A' or uetype='B' or uetype='C'),
        );



insert into t_gtype
values('t001','日常百货');
insert into t_gtype 
values('t002','儿童用品');
insert into t_gtype 
values('t003','女装精品');
insert into t_gtype 
values('t004','男装精品');
insert into t_gtype 
values('t005','电子精品');



insert into t_goods values('g0001','computer','t005',7000,8,50,50,5,'无');
insert into t_goods values('g0002','phone','t005',1780,8,100,100,5,'无');
insert into t_goods values('g0003','tshir','t004',1000,8,1000,1000,5,'无');
insert into t_goods values('g0004','西装','t004',6000,8,200,200,5,'无');
insert into t_goods values('g0005','裙子','t003',200,8,100,100,3,'无');
insert into t_goods values('g0006','coat','t003',4570,8,100,100,3,'无');
insert into t_goods values('g0007','儿童鞋','t002',430,7,100,100,3,'无');
insert into t_goods values('g0008','picture','t002',20,7,100,100,3,'无');
insert into t_goods values('g0009','杯子','t001',45,100,6,100,5,'无');
insert into t_goods values('g0010','牙膏','t001',4,100,9,100,5,'无');


    
insert into t_supplier
values('p001','广州有线',null,'87654567',null);
insert into t_supplier
values('p002','珠江数码','李生','13924567890',null);
insert into t_supplier
values('p003','广州电信','王小姐','13323456789',null);
insert into t_supplier
values('p004','广州百佳','万小姐','18987654321',null);



insert into t_main_procure values('i001','p001',sysdate,null,'1',null);
insert into t_main_procure values('i002','p002',sysdate,null,'1',null);
insert into t_main_procure values('i003','p003',sysdate,null,'1',null);


insert into t_procure_items select 'i001',gid,gprice,gstocks ,null,null 
from t_goods where gtid in ('t001','t002') ;
insert into t_procure_items select 'i002',gid,gprice,gstocks ,null ,null
from t_goods where gtid in ('t003','t004');
insert into t_procure_items select 'i003',gid,gprice,gstocks ,null ,null
from t_goods where gtid ='t005';

commit;


```




## 22. 根据需求建表
```
CREATE TABLE student(
   stnum VARCHAR2(12) primary key,
   stname VARCHAR2(10) not null,
   stsex CHAR(2) check(stsex in('男','女')),
   stclass VARCHAR2(20),
   stphone VARCHAR2(20)
);

CREATE TABLE book(
  boid VARCHAR2(10) primary key,
  boisbn VARCHAR2(13) not null,
  boname VARCHAR2(50),
  boauthor VARCHAR2(50),
  boprice NUMBER(6,2) check(boprice>0),
  boloan NUMBER(2),
  boamount NUMBER(2)
);

CREATE TABLE sbook(
  sbonum VARCHAR2(10) primary key,
  sboid VARCHAR2(12) references book(boid),
  sbonamenum VARCHAR2(12) references student(stnum),
  sboloantime DATE default sysdate,
  sborepaytime DATE default sysdate+30,
  sbolasttime DATE,
  sbomoney NUMBER(6,2)
);
```




## 23. 在读者表中增加读者等级列，列名为使用查询的账户名，字符串长度为1，等级可选为：教师(可借图书为5本)、学生(可借图书为3本)、社会人员(可借图书为1本)
```
ALTER TABLE student ADD A14210120238 CHAR(1) check(A14210120238 in('5','3','1'));
```


## 24. 请在读者表中添加以你的账户名前后最近的至少3个以上不同的读者数据，图书表中添加3本以上你本学期使用的教材图书信息，在借书记录中为你的每个读者添加至少一条借书记录
```
INSERT INTO student VALUES ('A241','江岳勋','男','计算机系软工2班','123456789','3');
INSERT INTO student VALUES ('A237','林靖儒','男','计算机系软工2班','123456789','3');
INSERT INTO student VALUES ('A238','刘淑玮','男','计算机系软工2班','123456789','3');
INSERT INTO student VALUES ('A239','叶瑞强','男','计算机系软工2班','123456789','3');

INSERT INTO book VALUES('001','x88887777','大学英语','王明',23.5,20,0);
INSERT INTO book VALUES('002','x88889999','沟通与演讲','陈零',23.5,20,0);
INSERT INTO book VALUES('003','x88886666','计算机组成原理','叶方',23.5,20,0);

INSERT INTO sbook(sbonum,sboid,sbonamenum) VALUES('sbo001','001','A241');
INSERT INTO sbook(sbonum,sboid,sbonamenum) VALUES('sbo002','002','A237');
INSERT INTO sbook(sbonum,sboid,sbonamenum) VALUES('sbo003','002','A238');
INSERT INTO sbook(sbonum,sboid,sbonamenum) VALUES('sbo004','003','A239');
```




## 25. 创建一个视图（名字自拟），视图数据为借阅记录信息(借书记录号,借阅读者姓名，图书名称，应该归还日期，实际归还日期（yyyy-mm-dd）,借阅天数,超期天数)
```
create view view_238_7 as
(select a.sbonum as 借书记录号,a.sboid as 图书ID,b.boname as 图书名称,c.stname as 借阅读者姓名,to_char(a.sboloantime,'yyyy-mm-dd')
as 借书日期,to_char(a.sbolasttime,'yyyy-mm-dd')as 应该归还日期
from sbook a left join book b on a.sboid=b.boid left join student c on a.sbonamenum=c.stnum);

```



## 26. 创建一个视图（名字自拟），视图为图书信息（图书编号，图书名称，出版社，在馆数（库存数减去已借出未归还的图书数）
```
create view view_238_8 as
(select a.boid as 图书编号,a.boname as 图书名称,a.boisbn as 出版社,a.boamount as 在馆数 from book a left join sbook b
on a.boid=b.sboid where a.boid =(select sbonum from sbook where a.boid=sbonum)
);
```



