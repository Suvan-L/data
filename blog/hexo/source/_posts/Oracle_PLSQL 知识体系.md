---
title: Oracle_PLSQL 知识体系
date: 2016-05-03 10:47:43
tags: Oracle
categories: Oracle
---

目录
=============================

　PLSQP是Oracle对sql语言的过程化扩展指在SQL命令中增加过程处理语句(如果分支，循环等)时SQL语言具有过程处理能力
　PL/SQL是面向过程的语言：简单，高效，灵活，实用
　操作Oracle数据库效率最高的语言就是PL/SQL,而不是其它的编程语言


1. PL/SQL程序结构
2. 变量
3. 选择
4. 循环
5. 例外
6. 函数 
7. 游标(光标)
8. 运算符和表达式
9. 触发器
10. 存储过程
11. 程序包
12. PL/SQL程序书写规范




## 1. PL/SQL的程序结构
```
declare
　　说明部分(变量说明，光标声明，例外说明)
begin
　　语句序列（DML语句）
exception
　　例外处理语句
end;
/
```





## 2. 变量

基本变量类型
	CHAR,VARCHAR2,DATE,NUMBER,BOOLEAN,LONG


+ 引用型变量(锚定变量)
+ 记录型变量



## 3.选择
3种形式
```
<1>
    IF 条件 THEN 语句1;
    语句2;
    END IF;

<2>
    IF 条件1 THEN 语句1;
    ELSE 语句2;
    END IF;

<3>
    IF 条件 THEN 语句;
    ELSIF 语句 THEN 语句;
    ELSE 语句;
    END IF；

```






## 4. 循环
3种循环
+ WHILE循环
+ LOOP循环
+ FOR循




## 5. 例外
系统例外
+ No_data_found     没有找到数据
+ Too_many_rows     SELECT...INTO语句匹配多个行
+ Zero_Divide       被零除（0作除数）
+ Value_error       算术或转换错误
+ Timeout_on_resource   在等待资源时发生超时

例子：
```
SET serveroutput ON;

DECLARE
    pename emp.ename%type;
BEGIN
    --查员工号是1234的员工姓名
    SELECT ename INTO pename FROM emp WHERE empno=1234;

EXCEPTION
    WHEN no_data_found THEN dbms_output.put_line('没有找到该员工');
    WHEN others THEN dbms_output.put_line('其他例外');   
                    -->捕获除了no_data_found例外之外的所有例外
```




自定义例外

+ 定义变量，类型是EXCEPTION
+ 使用raise抛出自定义例外

例子：
```
SET serveroutput ON;

DECLARE
    My_job CHAR(10);
    v_sal emp.sal%type;

    No_date EXCEPTION;     --声明一个变量，自定义一个例外

    CURSOR c1 IS SELECT distinct,job FROM emp ORDER BY job;
BEGIN
    OPEN c1;

        FETCH c1 INTO v_job;
            IF c1%notfound THEN raise no_date;  --抛出例外
            END IF;

    CLOSE c1;

    EXCEPTION
        WHEN no_date THEN insert into emp   
                values('fetch语句没有获得数据或数据已经处理完')；

END;
/


注意：
	1.定义自定义例外变量的时候，名字最好取的有意义
	2.PL/SQL当中，大小写不敏感
	3.PL/SQL中更常用的做法是在捕获异常后，执行一条insert语句，把最终的结果保存到一张表当中去,程序完成后，屏幕关闭后，数据就会在一张表中，我们可以通过一条查询语句，把结果查询出来
	4.当发现PL/SQL程序被常退出了，异常中断了，而程序中依然有没关闭的资源（例如:光标未正常关闭）,
	5.Oracle数据库会自动启动一个pmon进程(process monitor-进程监视器)这个进程会把程序当中遗留的系统垃圾和资源，一个个释放掉，清理干净


```

