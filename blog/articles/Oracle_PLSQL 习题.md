---
title: Oracle_PLSQL 习题
date: 2016-6-4 0:21:43
tags: Oracle
categories: Oracle
---


目录
==================
1. 编写匿名块程序，实现根据商品名称进行模糊查询，输出该商品的其他基本信息
2. 根据“双11促销活动”策略，编写一个匿名块程序，将原来折扣为0的，调整为0.9，将原来折扣为1的调整为0.95，其他的调整为0.92，之后输出“目前9折的商品有XX件， 92折的商品有XX件， 95折的商品有XX件”
3. 优化第1题，写成存储过程
4. 优化第2题,简单的方式解决
5. 银行转账，将程序改造为存储过程
  5.2 另一种版本
6. 审核采购表，编写存储过程
7. 编写根据订单单号审核订单的存储过程 
8. 计算税后工资
9. 自动生成采购单号（使用函数）
10. 使用函数实现订单编号的智能编码，规则为：用户Id+yyyymm+XXX(001-999)
11. 在添加订单明细时，用函数实现订单明细中根据商品编号返回商品的单价（商品信息表中）
12. 编写存储过程，实现订单的审核出库
13. 将上面10，11，12合成一个程序包
14. 编写触发器，实现当订单明细发生改变时，调整订单总金额
15. 编写触发器，实现删除订单前，检查是否为2、发货中，3、已完结，如是提醒不能删除
16. 设计一个用于系统商品数据盘点的的表和存储过程
17. 编写存储过程完成借书过程
18. 编写函数实现：借书记录号编码
19. 编写触发器,实现删除前判断

以上题目都是针对本人电脑进行的，均经过检验，在**本人电脑**都可以运行使用

电脑配置：
+ Windows 8.1中文版本 64位操作系统，基于X64的处理器
+ 处理器:Intel(R) Core(TM) i5-4200H CPU @ 2.80GHz
+ 安装内存:12.0GB
+ Oracle 11G
+ Java 1.8.0_65
+ Oracle SQL Develper 版本4.0.0.12 工作版本MAIN-12.84


目录:




## 1. 编写匿名块程序，实现根据商品名称进行模糊查询，输出该商品的其他基本信息
```
 SET serveroutput ON;

 DECLARE
  v_one t_goods.gname%type := '&请输入要查询的商品名称';
  v_gname t_goods.gname%type;
  v_goods_all t_goods%rowtype;
  
 BEGIN
  dbms_output.put_line('您输入的关键词为:'||v_one);
  
  dbms_output.put_line('..........正在搜索');
  SELECT gname INTO v_gname FROM t_goods 
      WHERE gname LIKE '%'||v_one||'%';
  dbms_output.put_line('..........查询完毕');
  
  dbms_output.put_line('存在商品:'||v_gname||'----:商品相关信息如下');
  SELECT * INTO v_goods_all FROM t_goods WHERE gname = v_gname;
  
  dbms_output.put_line
  (
    '编号:'||v_goods_all.gid||
    '名称:'||v_goods_all.gname||
    '类型:'||v_goods_all.gtid||
    '价格:'||v_goods_all.gprice||
    '折扣:'||v_goods_all.gdiscount||
    '库存:'||v_goods_all.gstocks||
    '最高库存:'||v_goods_all.gmaxstocks||
    '最低库存:'||v_goods_all.gminstocks||
    '备注:'||v_goods_all.gmemo
  );
  
 END;
 /

```

---
<br>


## 2. 根据“双11促销活动”策略，编写一个匿名块程序，将原来折扣为0的，调整为0.9，将原来折扣为1的调整为0.95，其他的调整为0.92，之后输出“目前9折的商品有XX件， 92折的商品有XX件， 95折的商品有XX件”
```
 SET serveroutput ON;

 DECLARE
    v_gdiscount t_goods.gdiscount%type;
    v_gname t_goods.gname%type;
    pnum_9 NUMBER := 0;
    pnum_92 NUMBER := 0;
    pnum_95 NUMBER := 0;
    pnum NUMBER := 1;
    CURSOR cemp is SELECT gdiscount,gname FROM t_goods;
  
 BEGIN
  OPEN cemp;
    LOOP EXIT WHEN cemp%NOTFOUND;  
      fetch cemp INTO v_gdiscount,v_gname;
        dbms_output.put_line('现在是针对第'||pnum||'商品'||v_gname||'进行操作');
        dbms_output.put_line(pnum||'号商品'||v_gname||'的折扣是:'||v_gdiscount);
        
        IF  v_gdiscount = 100 then UPDATE t_goods SET gdiscount=90 WHERE gname=v_gname;
          ELSIF v_gdiscount = 10 then UPDATE t_goods SET gdiscount=95 WHERE gname=v_gname;
          ELSE  UPDATE t_goods SET gdiscount = 92 WHERE gname=v_gname;                                   
        END IF;
        
        
      pnum := pnum +1;
    END LOOP;
    CLOSE cemp;
    
    SELECT count(*) INTO pnum_9 FROM t_goods WHERE gdiscount=90 GROUP BY gdiscount;
    SELECT count(*) INTO pnum_92 FROM t_goods WHERE gdiscount=92 GROUP  BY gdiscount;
    SELECT count(*) INTO pnum_95 FROM t_goods WHERE gdiscount=95 GROUP  BY gdiscount;

    
    dbms_output.put_line('目前9折的商品'||pnum_9||'有件');
    dbms_output.put_line('目前92折的商品'||pnum_92||'有件');
    dbms_output.put_line('目前95折的商品'||pnum_95||'有件');
 END;
 /
```

---
<br>


## 3. 优化第1题，写成存储过程
```
SET serveroutput ON;                            
CREATE OR REPLACE PROCEDURE p_querygoods(i_gname in VARCHAR2 )     --创建存储过程  ,输入in，输出out
AS
  v_gid t_goods.gid%type;   --锚定变量
  v_gname t_goods.gname%type;
  v_gprice t_goods.gprice%type;
BEGIN
  SELECT gid,gname,gprice INTO v_gid,v_gname,v_gprice
    FROM t_goods
      WHERE gname LIKE '%'||i_gname||'%'; --绑定变量
    
    dbms_output.put_line(v_gid||v_gname||v_gprice);   
EXCEPTION 
  WHEN no_data_found THEN 
    dbms_output.put_line('找不到满足条件的数据');
  WHEN too_many_rows THEN
    dbms_output.put_line('找到过多的数据');
END;
/
show error;

SET serveroutput ON;
exec p_querygoods('com');  --调用存储过程
```

---
<br>


## 4. 优化第2题,简单的方式解决
```
SET serveroutput ON;
DECLARE
  v_92 number;
  v_95 number;
  v_90 number;
BEGIN
  UPDATE t_goods SET gdiscount=92 WHERE gdiscount <> 0 and gdiscount <> 10;
  v_92 := SQL%rowcount;  --影响了几行
  UPDATE t_goods SET gdiscount=90 WHERE gdiscount=0;
  v_90 := SQL%rowcount;
  UPDATE t_goods SET gdiscount=0.95 WHERE gdiscount=10;
  v_95 := SQL%rowcount;
  
  dbms_output.put_line('目前9折的商品有'||v_90||'件,92折的商品有'||v_92||'件，95折的商品有'||v_95||'件');
  
  COMMIT;
  dbms_output.put_line('修改成功');
EXCEPTION
    WHEN others THEN 
      rollback;
      dbms_output.put_line(SQLERRM);
END;
/

 SHOW error;

```

---
<br>


## 5. 银行转账，将程序改造为存储过程
```

/*
  存储过程:p_zhuanzhang
  功能： 实现转账
  输入参数:1.i_money : number  转账金额;
          2.i_out : CHAR  转出的账户;
          3.i_in :  CHAR  转入的账户;
  创建人： 创建日期
  修改人，修改日期...
  
*/
CREATE or REPLACE procedure p_zhuanzhang (i_money NUMBER,i_out CHAR,i_in CHAR)
AS
  v_num NUMBER := 0;
  v_pmoney NUMBER(10,2);
  e_errorout EXCEPTION;   --自定义的异常
BEGIN
    SELECT count(tid) INTO v_num 
      FROM LIQIANG.t_account WHERE tid=i_out;
    IF vnum =0 THEN      --验证用户是否正确
      raise e_errorout;  --抛出自定义异常，程序跳到EXCEPTION语句块中
    END IF;

    --查询转出账户的余额
    SELECT tmoney INTO v_pmoney
      FROM LIQIANG.t_account WHERE tid=i_out;
      
    IF v_pmoney<i_money THEN 
      dbms_output.put_line('余额不足，无法进行转账');
      RAISE_APPLICATION_ERROR(-20001,'余额不足');   --抛出异常，自定义编号和提示
      RETURN;
    END IF;
    
    UPDATE LIQIANG.t_account SET tmoney=tmoney-i_money WHERE tid=i_out;   --i_in用户转账
    INSERT INTO LIQIANG.t_items(tid1,iid,itype,tid2,imoney)               --添加转账记录
      VALUES (i_out,seq_ma.nextval,1,i_in,i_money);
      
    UPDATE LIQIANG.t_account SET tmoney=tmoney-i_money WHERE tid=i_in;    --i_out用户接收转账
    INSERT INTO LIQIANG.t_items(tid1,iid,itype,tid2,imoney)               --添加转账记录
      VALUES (i_in,seq_ma.nextval,2,i_out,i_money);
      
      commit;
      
EXCEPTION 
  WHEN e_errorout THEN   --捕获自定义异常
    dbms_output.put_line('您的转出账户不存在');
  WHEN others THEN       --捕获其它异常
    dbms_output.put_line('转账失败'||SQLERRM);   
    rollback;
END;
/

show error;   --显示错误信息

SET serveroutput ON;
execute p_money(1000,'A14210120238','LIQIANG');  ---调用存储过程

```

---
<br>


## 5.2 另一种版本
```
SET serveroutput ON;

/*
存储过程：进行转账
输入参数：i_out,转出账户，类型为char；i_in,转入账户，类型为char；i_money,转账金额，类型为number
创建人： 创建日期：
版本4.0*/
create or replace procedure p_trans(i_out in char,i_in in char,i_money in number)
as
 v_numout number:=0;v_numin number:=0;
 v_tmoney number(10,2);
 e_error EXCEPTION;--自定义了一个异常1)
begin
  select count(tid) into v_numout  from liqiang.t_account
     where TID =i_out ;--验证账户是否正确
  select count(tid) into v_numin  from liqiang.t_account
     where TID =i_in ;--验证账户是否正确
  if v_numout<>1 or v_numin<>1 then 
    raise e_error;--抛出自定义的异常2)
  end if;
  select tmoney into v_tmoney    from liqiang.t_account
     where TID =i_out ;
  if v_tmoney<=i_money then  
    RAISE_APPLICATION_ERROR(-20001,'您的余额不足'); 
  end if;
     update liqiang.t_account set TMONEY = TMONEY-i_money 
      where TID =i_out ;
     insert into liqiang.t_items(iid,tid1,idate,itype,tid2,imoney) 
         values (seq_tiid.nextval,i_out,default,-1,i_in,i_money);
     update liqiang.t_account set TMONEY = TMONEY+i_money where TID =i_in;
     insert into liqiang.t_items(iid,tid1,idate,itype,tid2,imoney) 
              values (seq_tiid.nextval,i_in,default,1,i_out,i_money);
  commit;  
 exception
    when e_error then 
      dbms_output.put_line('您的账户输入不正确');--捕获自定义的异常3)
    when others then
        dbms_output.put_line('转账失败'||SQLERRM);
       rollback;
END; 
/
 SHOW error;

```

---
<br>


## 6. 审核采购表，编写存储过程
```
/*
  功能需求:
    判断此采购单是否已审核
    将采购单主表的状态修改为已审核 
    输出一个审核的结果
    修改对应商品的库存数
    判断采购的商品入库以后是否溢出
    对比价格并修改成本价
    修改对应商品的库存数
    
  存储过程:p_checkmainprocure
  功能： 审核采购信息
  输入参数:1.i_procureid :CHAR,采购单号;
  输出参数:1.o_result ：NUMBER,审核结果
  创建人：...
  创建日期: ...
  修改人: ...
  修改日期: ...
*/

 --o_result:1：审核成功，-1:单据已经审核,SQLCODE
CREATE OR REPLACE PROCEDURE p_checkmainprocure(i_pmid in CHAR,
 o_result out NUMBER) 
AS
  v_num NUMBER;   --用于计数
  v_pstate t_main_procure.pstate%type;   --采购状态
  e_over EXCEPTION;  --自定义异常
  
  CURSOR cur_items IS SELECT gid,pinum FROM  --1.定义游标
    t_procure_items WHERE  pmid= i_pmid;   
    v_gid t_procure_items.gid%type;       --商品ID
    v_pinum t_procure_items.pinum%type;   --采购数量
    v_gstocks t_goods.gstocks%type;       --库存量
    v_max t_goods.gmaxstocks%type;        --最大库存量
BEGIN
  SELECT COUNT(pmid) INTO v_num FROM t_main_procure WHERE pmid=i_pmid;    --查询对应的采购订单
  IF v_num=0 THEN 
      RAISE_APPLICATION_ERROR(-20001,'单据不存在！');    --抛出异常，编号-20001，提示信息："单据不存在！"
  END IF;
  
  SELECT  pstate INTO v_pstate FROM t_main_procure WHERE pmid =i_pmid;   --查询对应的采购状态
  IF(v_pstate='2') THEN       --1、待审核，2、已审核 
     raise e_over;
  END IF;

  UPDATE t_main_procure SET pstate='2' WHERE pmid= i_pmid;   --修改采购状态

  OPEN cur_items; --2.打开游标
    FETCH cur_items INTO v_gid,v_pinum;   --3.提取游标(第1行数据)
    IF(cur_items%notfound) THEN     --判断对应的采购主表是否有采购明细表
      raise_application_error(-20003, '主表没有明细');
    END IF;
    
    WHILE(cur_items%found) 
      Loop
        SELECT gstocks, gmaxstocks  INTO v_gstocks,v_max FROM t_goods WHERE gid=v_gid; --查询库存量，和最大库存量
        IF(v_max>v_gstocks+v_pinum) THEN     --判断库存量+采购数量是否>最大库存量，商品入库是否溢出
          UPDATE t_goods SET gstocks=gstocks+v_pinum WHERE gid =v_gid;
          ELSE 
            raise_application_error(-20002, '已超过该商品的最大存库量');
        END IF;
        
        FETCH cur_items INTO v_gid,v_pinum; --3_2.提取下一个游标
      END LOOP;
      
  CLOSE  cur_items; --4.关闭游标 
  
   commit;
   o_result:=1;   --输出参数返回1，审核成功
EXCEPTION
    WHEN e_over THEN o_result:=-1;  --返回输出参数
    WHEN others THEN 
      dbms_output.put_line('出现其他异常'||SQLERRM);
      o_result := SQLCODE;
      rollback;
      
END;
/
show error;  --显示错误信息


set serveroutput on
exec p_checkmainprocure('i002');--没有输出参数的调用

DECLARE  ---带有输出参数的调用
  v_result NUMBER;
BEGIN
  p_checkmainprocure('i002',v_result) ;
  
  IF v_result=1 THEN 
     dbms_output.put_line('审核成功！');
  ELSIF
    v_result=-1 then
    dbms_output.put_line('采购单已经审核，不能重复审核！');
  ELSE
    dbms_output.put_line('审核发生错误，错误代码为'||v_result);
 END IF;  
 
END;

```

---
<br>


## 7. 编写根据订单单号审核订单的存储过程 
```

/*
  编写根据订单单号审核订单的存储过程 
    功能需求：
      1.根据订单号判断是否存在该订单
          如果没有，则报错，不存在该订单
          如果有，则进行2
      2.判断该订单是否拥有订单明细表(t_order_items)
          如果没有，则报错，不存在订单明细表
          如果有，则进行4
      3.判断该订单的订单状态
        如果是发货中，已完结，取消，则退出程序；
        如果审核中，则进行4
      4.进行审核
         4.1判断商品信息表(t_goods)中是否拥有对应的商品ID
            .如果没有，则报错，不存在对应商品
            .如果有的，则进行4.2
         4.2判断商品信息表(t_goods)中库存量是否满足订单数量
            .如果不满足，则报错，库存量不足
            .如果满足，则进行4.3
         4.3判断订单主表(t_main_order)中订单总金额
            是否符合（订单数量X （商品标价x商品折扣））应付的钱
            .高了，则报错，付钱过多
            .低了，则报错，金额不足
            .刚好，则进行4.4
         4.4修改商品信息表（t_goods）的库存量
             将库存量修改为（库存量-订单数量）,并且4.5
         4.5审核完毕，修改订单状态
       5.输出审核结果
       
    必要性与可行性:
      1.必要,可行
      2.必要,可行
      3.必要，可行
      4.
         4.1 必要，可行
         4.2 必要，可行
         4.3 必要，可行
         4.4 必要，可行
         4.5 必要，可行
      5.必要，可行
      
     存储过程:p_auditindent
        功能： 审核订单
         输入参数:1.i_omid :CHAR,订单号;
         输出参数:1.o_result ：NUMBER,审核结果;
         创建人：刘淑玮
         创建日期: 2016.5.16
*/

CREATE OR REPLACE PROCEDURE p_auditindent(i_omid IN CHAR,o_result OUT NUMBER)
AS
  v_num NUMBER;
  v_totalmoney NUMBER(10,2);  --（订单数量X （商品标价x商品折扣））
  v_ostate t_main_order.ostate%type;  --储存订单状态
                                      --1、审核中，2、发货中，3、已完结，4、取消
  v_oamount t_main_order.oamount%type;  --订单总金额
  
  v_gid_O t_order_items.gid%type;   --储存订单上的商品ID
  v_onum t_order_items.onum%type;    --储存订单的数量

  
  CURSOR c_items IS SELECT omid FROM t_order_items WHERE omid=i_omid;  --定义光标，是否有对应的明细表
  CURSOR c_goods IS SELECT gid,gprice,gdiscount,gstocks FROM t_goods; --定义光标，查询商品ID，商品标价，折扣，库存量
  v_gid_G t_goods.gid%type;             --储存商品ID
  v_gprice t_goods.gid%type;            --储存商品标价
  v_gdiscount t_goods.gdiscount%type;   --储存商品折扣
  v_gstocks t_goods.gstocks%type;        --储存商品库存量
  
  e_no_items EXCEPTION; --没有明细表
  
BEGIN
      ---1.根据订单号判断是否存在该订单
      SELECT COUNT(omid) INTO v_num FROM t_main_order WHERE omid=i_omid;
      IF v_num=0 THEN
         raise_application_error(-10011,'不存在该订单');
      END IF;
      ---2.判断该订单是否拥有订单明细表(t_order_items)
      OPEN c_items;
        IF C_items%notfound THEN RAISE e_no_items;
        END IF;
      CLOSE c_items;
        
      ---3.判断该订单的订单状态，不为1（审核中）的时候报错，并且退出程序
      SELECT ostate INTO v_ostate FROM t_main_order WHERE omid=i_omid;
      IF v_ostate=2 THEN  
            o_result:=-30031;
            raise_application_error(-30031,'商品发货中...');
      ELSIF v_ostate=3 THEN
            o_result:=-30032;
            raise_application_error(-30032,'商品已完结');
      ELSIF v_ostate=4 THEN
            o_result:=-30033;
            raise_application_error(-30033,'商品已取消');
      END IF;
      
      
      --4.进行审核
          SELECT gid,onum INTO v_gid_O,v_onum FROM t_order_items WHERE omid=i_omid;  --查询订单的商品ID，订单数量，并储存
          SELECT oamount INTO v_oamount FROM t_main_order WHERE omid=i_omid ;         --查询订单的总金额，并储存
      
      --4.1判断商品信息表(t_goods)中是否拥有对应的商品ID
      OPEN c_goods;
         LOOP 
            FETCH c_goods INTO v_gid_G,v_gprice,v_gdiscount,v_gstocks;
          
            EXIT WHEN v_gid_O=v_gid_G;   --遇到满足条件则退出循环，向下执行
            IF c_goods%notfound THEN
              o_result:=-30041;
              raise_application_error(-30041,'抱歉，仓库中没有与订单对应商品，即将退出程序...');
            END IF;
         END LOOP;
         
         --4.2判断商品信息表(t_goods)中库存量是否满足订单数量
          IF v_gstocks < v_onum THEN
            o_result:=-30042;
            raise_application_error(-30042,'仓库库存量不足.');
          END IF;
          
          --4.3判断订单主表(t_main_order)中订单总金额
           -- 是否符合（订单数量X （商品标价x商品折扣））应付的钱
                   
           v_totalmoney := v_onum*(v_gprice*v_gdiscount);  --进行计算
            IF  v_oamount > v_totalmoney  THEN
               o_result:= -300431;
               raise_application_error(-300431,'订单金额过多');
            ELSIF v_oamount < v_totalmoney THEN 
               o_result:= -300431;
               raise_application_error(-300431,'订单金额过少');
            END IF;
            
      CLOSE c_goods;

         --4.4修改商品信息表（t_goods）的库存量，将库存量修改为（库存量-订单数量）,并且进行4.5
             UPDATE t_goods SET gstocks=gstocks-v_onum WHERE gid=v_gid_G;
             
         --4.5审核完毕，修改订单状态
             UPDATE t_main_order SET ostate=3 WHERE omid=i_omid ;   --将订单状态修改为已完结
      
        --5.，提交操作，输出结果，输出审核结果
      commit;
      o_result:=1;  --输出参数为1，订单审核成功
EXCEPTION
    WHEN e_no_items THEN dbms_output.put_line('没有明细表');
      return;   
    WHEN others THEN dbms_output.put_line('捕获其他异常，错误信息为'||SQLERRM);
      rollback;
END;
/
show error;  --显示错误信息


SET serveroutput ON;
DECLARE
  v_result NUMBER;
BEGIN
  p_auditindent('1',v_result);   --调用带参的存储过程
  
  IF v_result=1 THEN
    dbms_output.put_line('审核成功');
    ELSE 
      dbms_output.put_line('审核发生错误，错误代码为'||v_result);
  END IF;
END;

```

---
<br>


## 8. 计算税后工资
```  


          ///////不完整，仅供参考
/*
工资个税的计算公式为：应纳税额
输入：yfgz
*/
--输出参数
--函数只能返回一个值
create or replace function f_gerensuodesui(yfgz in number) return number --返回值类型 名字f_开头
as
ynse number:=0;--应纳税额
v_yjs number;
begin
v_yjs:=yfgz-3500;
  if v_yjs<=0 then
    ynse:=0;
    elsif v_yjs<=1500 then
    ynse:=v_yjs*0.03;
  elsif v_yjs <=4500 then
    ynse:=v_yjs*0.1-105;
  elsif v_yjs<=9000 then
    ynse:=v_yjs*0.2-555;
  elsif v_yjs<=35000 then
    ynse:=v_yjs*0.25-1005;
  elsif v_yjs<=35000 then
    ynse:=v_yjs*0.25-1005;
  elsif v_yjs<=55000 then
    ynse:=v_yjs*0.3-2755;
  elsif v_yjs<=80000 then
    ynse:=v_yjs*0.35-5505;
  elsif v_yjs>80000 then
    ynse:=v_yjs*0.45-13505;
  end if;
   return ynse;
exception
    when others then
   return -1;
end;
/
show error

select f_gerensuodesui(6000) from dual;
```

---
<br>



## 9. 自动生成采购单号（使用函数）
```
/*
功能需求:
        采购单号的编码规则为“PYYYYMMXXXXX”，其中“P”为采购单的前缀表示，
        “YYYYMM”表示采购单生成的年月，“XXXXX”为每月的流水号码。
        比如2016年6月3号生成的本月的第20张的采购单号为：“P20160600020”。
        类似地要求订单号的编码规则为“OYYYYMMXXXXX”，请编写函数实现。
*/

CREATE OR REPLACE FUNCTION f_createpmid RETURN VARCHAR2
AS
  v_pmid VARCHAR2(12);
  v_maxomid t_main_procure.omid%type;
BEGIN
   v_pmid:='P';
   v_pmid:=v_pmid||to_char(sysdate,'yyyymm');
   SELECT max(omid) INTO v_maxomid FROM t_main_procure    --查询当月最大的采购单
      WHERE to_char(pdate,'yyyymm')=to_char(sysdate,'yyyymm');
    IF v_maxomid IS NULL THEN
      v_pmid:=v_pmid||'00001';
    ELSE
      v_pmid:=v_pmid||trim(to_char(to_number(substr(v_maxomid,8,5))+1,'00000'));  ---得到流水号
    END IF;
    RETURN v_pmid;
EXCEPTION
  WHEN others THEN
      RETURN -1;

END;
/
SHOW error;


SELECT f_createpmid pmid from dual;   --尝试调用
INSERT INTO t_main_procure(omid,pstate) VALUES (f_createpmid,'1'); 


```

---
<br>


## 10. 使用函数实现订单编号的智能编码，规则为：用户Id+yyyymm+XXX(001-999)
```
/*
  一：
    功能需求：使用函数实现订单编号的智能编码，规则为：用户Id+yyyymm+XXX(001-999).
    函数名：fun_number
    返回值：CHAR 订单编号的智能编码
    创建时间：2016.6.1
*/
CREATE OR REPLACE FUNCTION fun_number RETURN CHAR
AS
  v_number CHAR(12);   --存储智能编码
  v_omid t_main_order.omid%type;     --存储订单编号
BEGIN
  SELECT  MAX(omid)INTO v_omid FROM  t_main_order   --查找最大的订单编号
    WHERE omid LIKE '038%';
  IF v_omid IS NULL THEN       --如果ID+yyyymm+XXX，这种类型的订单编号
      v_number :='038'||trim(to_char(sysdate,'yyyymm'))||'001';
  ELSIF trim(substr(v_omid,10,3))='999' THEN                       --订单编号流水号达到999
      raise_application_error(-10012,'该用户的订单达到饱和，无法再次添加..');
    ELSE                          --将之前表中订单编号的流水号最大的号码+1，再格式化存入订单主表中
      v_number :='038'||trim(to_char(sysdate,'yyyymm'))||trim(to_char(to_number(substr(v_omid,10,3))+1,'000'));
  END IF;
  RETURN v_number;
EXCEPTION
  WHEN others THEN 
    dbms_output.put_line('捕获其他异常，错误信息为'||SQLERRM);
      rollback;
END fun_number;
/
show ERROR;

--测试
SELECT fun_number FROM dual;
INSERT INTO t_main_order(omid,uiid,odate,ostate) VALUES (fun_number,'37',sysdate,'1');

```

---
<br>


## 11. 在添加订单明细时，用函数实现订单明细中根据商品编号返回商品的单价（商品信息表中）
```
/*
  二：
    功能需求：在添加订单明细时，用函数实现订单明细中根据商品编号返回商品的单价（商品信息表中）
    函数名称：fun_returnprice
    输入参数：in_gid: CHAR 商品编号
    返回值: NUMBER  商品价格
    创建时间：2016.6.1
*/
CREATE OR REPLACE FUNCTION fun_returnprice(in_gid IN CHAR) RETURN NUMBER
AS
  v_ifgid NUMBER;  --存储是对应的商品记录数
  v_gprice t_goods.gprice%type;  --存储商品价格

  no_date_found EXCEPTION;
BEGIN
  --1.判断是否存在对应商品
  SELECT COUNT(gid) INTO v_ifgid FROM t_goods
       WHERE gid LIKE '%'||substr(in_gid,2,4)||'%'; 

    IF v_ifgid=0 THEN 
      raise no_date_found;
    END IF;
  --2.根据查询商品编号，查询对应的商品价格
  SELECT gprice INTO v_gprice FROM t_goods    
      WHERE gid LIKE '%'||substr(in_gid,2,4)||'%'; 

  RETURN v_gprice;
  
EXCEPTION 
  WHEN no_date_found THEN
    dbms_output.put_line(in_gid||'该订单编号，不存在对应的商品');
    return -1;
  WHEN others THEN 
    dbms_output.put_line('捕获其他异常，错误信息为'||SQLERRM);
    return -2;
END fun_returnprice;
/
show ERROR;

--测试
SELECT  fun_returnprice('q0001') FROM dual;


```

---
<br>


## 12. 编写存储过程，实现订单的审核出库
```
/*
  三,
    功能需求：编写存储过程，实现订单的审核出库
    存储过程:p_auditindent
    功能审核订单
         输入参数:1.i_omid :CHAR,订单号;
         输出参数:1.o_result ：NUMBER,审核结果;
    创建时间：2016.6.1
        
*/
CREATE OR REPLACE PROCEDURE p_auditindent(i_omid IN CHAR,o_result OUT NUMBER)
AS
  v_totalmoney NUMBER(10,2);  --（订单数量X （商品标价x商品折扣））
  v_ostate t_main_order.ostate%type;  --储存订单状态
                                      --1、审核中，2、发货中，3、已完结，4、取消
  
  v_gid_O t_order_items.gid%type;   --储存订单上的商品ID
  v_onum t_order_items.onum%type;    --储存订单的数量

  CURSOR c_goods IS SELECT gid,gstocks FROM t_goods; --定义光标，查询商品库存量
  v_gid_G t_goods.gid%type;             --储存商品ID
  v_gstocks t_goods.gstocks%type;        --储存商品库存量
  
BEGIN
      --1.判断该订单的订单状态，不为2（发货中）的时候报错，并且退出程序
      SELECT ostate INTO v_ostate FROM t_main_order WHERE omid=i_omid;
      IF v_ostate=1 THEN  
            o_result:=-30031;
            raise_application_error(-30031,'商品审核中');
      ELSIF v_ostate=3 THEN
            o_result:=-30032;
            raise_application_error(-30032,'商品已完结');
      ELSIF v_ostate=4 THEN
            o_result:=-30033;
            raise_application_error(-30033,'商品已取消');
      END IF;
      
      --2.进行出库审核
          SELECT gid,onum INTO v_gid_O,v_onum FROM t_order_items WHERE omid=i_omid;  --查询订单的商品ID和订单数量，并储存
      
        --2.1提取订单所中商品id，所对应商品信息表中相应商品ID和库存量
        OPEN c_goods;   --开启光标
           LOOP
              FETCH c_goods INTO v_gid_G,v_gstocks; 
              EXIT WHEN v_gid_O=v_gid_G;  --退出条件
              
              IF c_goods%notfound THEN
                o_result:=-300411;
                  raise_application_error(-30041,'抱歉，仓库中没有与订单对应商品...');
              END IF;
            END LOOP;
         
      CLOSE c_goods;  --关闭光标

         --2.2判断商品信息表(t_goods)中库存量是否满足订单数量
            IF v_onum > v_gstocks THEN
              o_result:=-30042;
              raise_application_error(-30042,'仓库库存量不足，无法出库');
            END IF;  

          --2.2出库成功，，修改商品信息表（t_goods）的库存量，将库存量修改为（库存量-订单数量）
             UPDATE t_goods SET gstocks=gstocks-v_onum WHERE gid=v_gid_G;
             
         --2.3订单审核出库完毕，修改订单状态
             UPDATE t_main_order SET ostate=3 WHERE omid=i_omid ;   --将订单状态修改为已完结
      
        --3.提交操作，输出结果，输出审核结果
      commit;
      o_result:=1;  --输出参数为1，订单出库审核成功
EXCEPTION
    WHEN others THEN dbms_output.put_line('捕获其他异常，错误信息为'||SQLERRM);
    rollback;
END;
/
show error;  --显示错误信息


SET serveroutput ON;
DECLARE
  v_result NUMBER;
BEGIN
  p_auditindent('1',v_result);   --调用带参的存储过程
  
  IF v_result=1 THEN
    dbms_output.put_line('审核出库成功');
    ELSE 
      dbms_output.put_line('审核发生错误，错误代码为'||v_result);
  END IF;

END;
  
```

---
<br>


## 13. 将上面10，11，12合成一个程序包
```
/*
  程序包规范：
    程序包名:pack_checkorder
    公有函数:
      1.fun_number
      2.fun_returnprice
    公有存储过程
    p_auditiondent
*/
CREATE OR REPLACE PACKAGE pack_checkorder 
AS
  --函数
  FUNCTION fun_number RETURN CHAR;  
  FUNCTION fun_returnprice(in_gid IN CHAR) RETURN NUMBER;
  
  --存储过程
  PROCEDURE p_auditindent(i_omid IN CHAR,o_result OUT NUMBER);
END pack_checkorder;

/
/*
  程序包主体
*/
CREATE OR REPLACE PACKAGE BODY pack_checkorder
AS
---fun_number函数
FUNCTION fun_number RETURN CHAR
AS
  v_number CHAR(12);   --存储智能编码
  v_omid t_main_order.omid%type;     --存储订单编号
BEGIN
  SELECT  MAX(omid)INTO v_omid FROM  t_main_order   --查找最大的订单编号
    WHERE omid LIKE '038%';
  IF v_omid IS NULL THEN       --如果ID+yyyymm+XXX，这种类型的订单编号
      v_number :='038'||trim(to_char(sysdate,'yyyymm'))||'001';
  ELSIF trim(substr(v_omid,10,3))='999' THEN                       --订单编号流水号达到999
      raise_application_error(-10012,'该用户的订单达到饱和，无法再次添加..');
    ELSE                          --将之前表中订单编号的流水号最大的号码+1，再格式化存入订单主表中
      v_number :='038'||trim(to_char(sysdate,'yyyymm'))||trim(to_char(to_number(substr(v_omid,10,3))+1,'000'));
  END IF;
  RETURN v_number;
EXCEPTION
  WHEN others THEN 
    dbms_output.put_line('捕获其他异常，错误信息为'||SQLERRM);
      rollback;
END fun_number;

--fun_returnprice函数
FUNCTION fun_returnprice(in_gid IN CHAR) RETURN NUMBER
AS
  v_ifgid NUMBER;  --存储是对应的商品记录数
  v_gprice t_goods.gprice%type;  --存储商品价格

  no_date_found EXCEPTION;
BEGIN
  --1.判断是否存在对应商品
  SELECT COUNT(gid) INTO v_ifgid FROM t_goods
       WHERE gid LIKE '%'||substr(in_gid,2,4)||'%'; 

    IF v_ifgid=0 THEN 
      raise no_date_found;
    END IF;
  --2.根据查询商品编号，查询对应的商品价格
  SELECT gprice INTO v_gprice FROM t_goods    
      WHERE gid LIKE '%'||substr(in_gid,2,4)||'%'; 

  RETURN v_gprice;
  
EXCEPTION 
  WHEN no_date_found THEN
    dbms_output.put_line(in_gid||'该订单编号，不存在对应的商品');
    return -1;
  WHEN others THEN 
    dbms_output.put_line('捕获其他异常，错误信息为'||SQLERRM);
    return -2;
END fun_returnprice;

--p_auditindent存储过程
PROCEDURE p_auditindent(i_omid IN CHAR,o_result OUT NUMBER)
AS
  v_totalmoney NUMBER(10,2);  --（订单数量X （商品标价x商品折扣））
  v_ostate t_main_order.ostate%type;  --储存订单状态
                                      --1、审核中，2、发货中，3、已完结，4、取消
  
  v_gid_O t_order_items.gid%type;   --储存订单上的商品ID
  v_onum t_order_items.onum%type;    --储存订单的数量

  CURSOR c_goods IS SELECT gid,gstocks FROM t_goods; --定义光标，查询商品库存量
  v_gid_G t_goods.gid%type;             --储存商品ID
  v_gstocks t_goods.gstocks%type;        --储存商品库存量
  
BEGIN
      --1.判断该订单的订单状态，不为2（发货中）的时候报错，并且退出程序
      SELECT ostate INTO v_ostate FROM t_main_order WHERE omid=i_omid;
      IF v_ostate=1 THEN  
            o_result:=-30031;
            raise_application_error(-30031,'商品审核中');
      ELSIF v_ostate=3 THEN
            o_result:=-30032;
            raise_application_error(-30032,'商品已完结');
      ELSIF v_ostate=4 THEN
            o_result:=-30033;
            raise_application_error(-30033,'商品已取消');
      END IF;
      
      --2.进行出库审核
          SELECT gid,onum INTO v_gid_O,v_onum FROM t_order_items WHERE omid=i_omid;  --查询订单的商品ID和订单数量，并储存
      
        --2.1提取订单所中商品id，所对应商品信息表中相应商品ID和库存量
        OPEN c_goods;   --开启光标
           LOOP
              FETCH c_goods INTO v_gid_G,v_gstocks; 
              EXIT WHEN v_gid_O=v_gid_G;  --退出条件
              
              IF c_goods%notfound THEN
                o_result:=-300411;
                  raise_application_error(-30041,'抱歉，仓库中没有与订单对应商品...');
              END IF;
            END LOOP;
         
      CLOSE c_goods;  --关闭光标

         --2.2判断商品信息表(t_goods)中库存量是否满足订单数量
            IF v_onum > v_gstocks THEN
              o_result:=-30042;
              raise_application_error(-30042,'仓库库存量不足，无法出库');
            END IF;  

          --2.2出库成功，，修改商品信息表（t_goods）的库存量，将库存量修改为（库存量-订单数量）
             UPDATE t_goods SET gstocks=gstocks-v_onum WHERE gid=v_gid_G;
             
         --2.3订单审核出库完毕，修改订单状态
             UPDATE t_main_order SET ostate=3 WHERE omid=i_omid ;   --将订单状态修改为已完结
      
        --3.提交操作，输出结果，输出审核结果
      commit;
      o_result:=1;  --输出参数为1，订单出库审核成功
EXCEPTION
    WHEN others THEN dbms_output.put_line('捕获其他异常，错误信息为'||SQLERRM);
    rollback;
END;

END  pack_checkorder;
/
show ERROR;

```

---
<br>


## 14. 编写触发器，实现当订单明细发生改变时，调整订单总金额
```
/*
  功能需求：编写触发器，实现当订单明细发生改变时，调整订单总金额
  触发器:tri_adjustoamount
*/
CREATE OR REPLACE TRIGGER tri_adjustoamount
  AFTER INSERT OR DELETE OR UPDATE
  ON t_order_items
  FOR EACH ROW
BEGIN
  IF inserting THEN
    UPDATE t_main_order 
        SET oamount =nvl(oamount,0)+:new.oprice * :new.onum WHERE omid=:new.omid;
  ELSIF deleting THEN
    UPDATE t_main_order
        SET oamount=oamount - :old.oprice * :old.onum WHERE omid=:old.omid;
  ELSIF updating THEN
    UPDATE t_main_order
        SET oamount=oamount - :old.oprice * :old.onum +:new.oprice * :new.onum;
  END IF;
END tri_adjustoamount;
/
show ERROR;

  
```

---
<br>


## 15. 编写触发器，实现删除订单前，检查是否为2、发货中，3、已完结，如是提醒不能删除
```
/*
  功能需求：编写触发器，实现删除订单前，检查是否为2、发货中，3、已完结，如是提醒不能删除
  触发器:tri_checkostate
*/
CREATE OR REPLACE TRIGGER tri_checkostate 
  BEFORE DELETE 
  ON t_main_order
  FOR EACH ROW
BEGIN
  IF :old.ostate IN ('2','3') THEN
    raise_application_error(-20000,:old.omid||'订单不能删除');
  END IF;
END tri_checkostate;
/
show ERROR;
```

---
<br>



## 16. 设计一个用于系统商品数据盘点的的表和存储过程
```
请你设计一个用于系统商品数据盘点的的 表（盘点时间，商品编号，类型（损耗/结余），数量）和存储过程，
总公式为：商品的库存量=商品的总采购量-商品的总销售量，
如有少，添加一条损耗记录，如有多，添加一条结余记录。


---查询用户所对应的表空间
DESC dba_users;
SELECT username,default_tablespace FROM dba_users;

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

/*
  存储过程:p_goodsdate
功能
    实现判断损耗，结余
    总公式为：商品的库存量=商品的总采购量-商品的总销售量，
         如有少，添加一条损耗记录，如有多，添加一条结余记录。
    输入参数，i_gid:CHAR(6) 商品ID
  创建人：刘淑玮
*/
CREATE OR REPLACE PROCEDURE p_goodsdate(i_gid IN CHAR)
AS
  v_gstocks t_goods.gstocks%type;  --存储当前商品库存量
  
  v_pinum t_procure_items.pinum%type;  --存储上的总采购量
  v_onum t_order_items.onum%type;  --存储商品的总销售量
  
  insert_table VARCHAR(200);  --添加语句
BEGIN
  SELECT gstocks INTO v_gstocks FROM t_goods WHERE gid=i_gid;
  SELECT pinum INTO v_pinum FROM t_procure_items WHERE gid=i_gid;
  SELECT onum INTO v_onum FROM t_order_items WHERE gid=i_gid;
  
  IF v_gstocks<(v_pinum-v_onum) THEN
      insert_table:='INSERT INTO t_goods_date(gid,detetype) VALUES ('||i_gid||'1'||')';
      EXECUTE IMMEDIATE insert_table;
    ELSIF v_gstocks>(v_pinum-v_onum) THEN
      insert_table:='INSERT INTO t_goods_date(gid,detetype) VALUES ('||i_gid||'2'||')';
      EXECUTE IMMEDIATE insert_table;
  END IF;
  
END;
/
show ERROR;


```

---
<br>


## 17. 编写存储过程完成借书过程
```
CREATE OR REPLACE PROCEDURE p_238vkw
    (i_stunum IN VARCHAR2,i_boid IN VARCHAR2,o_result OUT NUMBER)
AS
    v_A14210120238 student.A14210120238%type;  --读者类型与可借数量
    v_boloan  book.boloan%type;     ---图书id
BEGIN

    SELECT A14210120238 INTO v_A14210120238 FROM student WHERE stnum=i_stunum;
    SELECT boloan INTO v_boloan FROM book WHERE boloan=i_boid;  
          
   IF v_A14210120238>0 and v_boloan>0 THEN  --读者可借数量>0  图书可接数量>0
      o_result :=1;
   ELSE
      o_result:=-1;
    END IF;
EXCEPTION 
  WHEN others THEN  
      dbms_output.put_line('出现其他异常'||SQLERRM);
      o_result :=-1;
END;
/
show ERROR;
```

---
<br>


## 18. 编写函数实现：借书记录号编码
```
/*
   函数：f_238fpu
   功能：  
        编写函数实现：借书记录号编码，
        yymm+读者编号后三位+三位流水号，
        并添加一条记录调用此函数实现
   输入参数:i_sbonamenum VARCHAR2 读者编号
   返回值： VARCHAR2
   创建人：刘淑玮
   创建日期：2016.6.16

*/
CREATE OR REPLACE FUNCTION f_238fpu(i_sbonamenum IN VARCHAR2) RETURN VARCHAR2
AS 
  v_number VARCHAR(10);  --智能生成的编号
  
  v_sbonum sbook.sbonum%type;  --查询是否存在智能编号
BEGIN
  v_number:=trim(substr(i_sbonamenum,2,3));
  SELECT max(sbonum) INTO v_sbonum FROM sbook WHERE sbonum LIKE '%'||v_number||'%';
  IF v_sbonum IS NULL THEN       
      v_number :=trim(to_char(sysdate,'yymm'))||v_number||'001';
    ELSE                  
      v_number :=trim(to_char(sysdate,'yymm'))||v_number||trim(to_char(to_number(substr(v_sbonum,8,3))+1,'000'));
  END IF;
  RETURN v_number;
EXCEPTION
  WHEN others THEN 
    dbms_output.put_line('捕获其他异常，错误信息为'||SQLERRM);
      rollback;
END;
/
show ERROR;


----测试
INSERT INTO sbook(sbonum,sboid,sbonamenum) VALUES (f_238fpu('A241'),'003','A241');

```

---
<br>


## 19. 编写触发器,实现删除前判断
```
CREATE OR REPLACE TRIGGER tr_238poy
  BEFORE DELETE 
  ON sbook
  FOR EACH ROW
BEGIN
  IF :old.sbolasttime IS NULL THEN   --如果实际归还时间为空，则说明未归还
      raise_application_error(-20000,:old.sbolasttime||'借书记录不能删除');
  END IF;
END;
/
show ERROR;

```
