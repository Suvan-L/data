---
title: Oracle_PLSQL 错误记录
date: 2016-05-12 19:16:43
tags: Oracle
categories: Oracle
---


# 目录
1. PLSQL_begin__end语句块中的return和exit
2. 注意细节




## 1. PLSQL_begin__end语句块中的return和exit
+ exit只能用于循环中,并且退出循环往下执行
+ return可用于循环或非循环，并且退出整个begin..end块不往下执行




## 2. 注意细节
1. 只要程序中有INSERT,UPDATE,DELETE,就一定要做错误处理，并且把rollBack放到错误处理当中
2. dbms_output.put_line()这个输出只对Oracle有效，对其他程序设计语言的界面无效
3. 一般都在异常里进行ROLLBACK
4. BEGIN END；中的SELECT...INTO语句，如果查询不到数据，会抛出异常
5. MAX(某字段)，MAX函数如果找不到数据不会抛出异常，会返回null
6. 手动抛出异常 例如:rasie_application_error(-12402,'啦啦啦');
7. trim函数---去除char两边的空格，sbstr函数---截取字符，to_char--格式化为char类型，to_number--格式化为number类型
  sysdate--系统默认时间函数
8. 记得写END IF; END LOOP
9. FOR EACH ROW; 行级触发器，一行一次
10. SQLERRM ---打印错误信息
11. 程序包规范，程序包主体，程序包说明
12. 函数可以没有输入参数输，返回值类型不用写具体长度，例如直接NUMBER,CHAR
13. 触发器的几个特殊变量:new,:old,insertingupdating,deleting


