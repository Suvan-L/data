---
title: Java_知识体系
date: 2017-08-02 17:43:18
tags: Java
categories: Java
---

# 目录:

## 一. 语法整理
1. 概述(基本语法与规范)
2. 变量
3. 运算符
4. 循环和判断
5. 数组
6. 函数(方法)
7. 异常处理
8. 面向对象【继承,多态,封装,抽象和接口】
9. Java的数据结构【集合框架】
10. 泛型与对象序列化
11. 静态关键字static
12. 内部类
13. 多线程基础
14. Java网络编程(Socket编程)
15. IO体系构图
16. 解析Java注解
17. JVM虚拟机【管理内存分区】
18. 了解Java中的内存泄漏的
19. 专业术语
20. 程序设计
21. 项目管理
22. 数据结构基础


## 二. 操作整理
1. 操作日期和时间
2. 自定义异常
3. 反射操作
4. 正则表达式
5. 操作I/O输入输出流和文件
6. 编写定时任务
7. 获取控制台键盘输入
8. 文档注释【JavaDoc】
9. 编码问题
10. Applet的简单介绍
11. 发送邮件【JavaMail】
12. GUI开发
13. 自动化操作【模拟键盘和鼠标】
14. JDBC连接数据库
15. 遍历与控制台打印输出
16. XML文件读取与生成
17. 
   1. Java加密与解密机制介绍
   2. 运用Base64算法
   3. 运用消息摘要算法
   4. 运用对称加密算法
   5. 运用非对称加密算法
   6. 运用数字签名算法
18. 
   1. JSP开发
   2. Servlet基础
   3. 过滤器
   4. 监听器
   5. JSTL标签
   6. JSP自定义标签
19. JSON数据处理
20. DWR实现服务器向客户端推送


## 三. 研究Github开源项目
1. [JavaWeb项目之客户管理系统](https://github.com/codingXiaxw/CustomerManagement)
2. [一个案例带你快速入门SSM开发](https://github.com/codingXiaxw/ssm) 和 [SSM注解开发的高级知识讲解](https://github.com/codingXiaxw/ssm2)
3. [Shiro整合Web项目及整合后的开发](https://github.com/codingXiaxw/shiro)
4. [Java高并发秒杀系统API](https://github.com/codingXiaxw/seckill)
5. [简单的教务系统网站(结合了图书订购功能)【SSM框架】](https://github.com/C0de8ug/Javaee-tutorial)
6. [本地Markdown文档读取展示工具【springboot框架】](https://github.com/nl101531/MarkdownViewTools)
7. [microblog【实现了增删改查技术的微博】](https://github.com/zhangxiang1/microblog)和[microblog_ssm【用SSM实现】](https://github.com/zhangxiang1/microblog_ssm)
8. [Spring-mvc-mini【基于Spring MVC,实现增删查改】](https://github.com/hot13399/spring-mvc-mini) 和[spring-mvc-REST【简单的Java RESTful 项目】](https://github.com/hot13399/spring-mvc-REST)
9. [tiny-spring【Spring精简版】](https://github.com/code4craft/tiny-spring)
10. [JStruts2【一个精简版Struts2框架】](https://github.com/HuangFromJYU/JStruts2)
11. [简易木材管理系统【JQuery-Servlet-JDBC】](https://github.com/atrphy/WoodManager/)





一.语法整理
=======================================================

1. 概述(基本语法与规范)
------------------

+ Java SE
+ Java EE
+ Java ME
  本地配置Java运行环境JDK：
```
<1>变量1
        JAVA_HOME:C:\Program Files\Java\jdk1.8.0_65
        【作用：找到java编译器以及执行文件2】

            在Path变量结尾处添加：%JAVA_HOME%\bin;%JAVA_HOME%\jre\bin;

<2>变量2
        CLASSPATH:E:\Class;%JAVA_HOME%\lib;
        【租用：指定Java类所在的目录(例如：导入原生工具包)】
```


JRE和JDK

+ 【Java运行时环境(JRE)】Java虚拟机,Java核心类库和支持文件
+ 【JAVA开发工具包(JDK)】是完整的Java软件开发包,包含jre,编译器,和其他工具(JavaDoc,java调试器等),可以让开发者开发,编译，执行Java应用程序
  ​



Java语言鲁棒性(Robust,即健壮性)特点
+ 能检查程序在编译和运行时的错误
+ 自己操纵内存减少了内存出错的可能性
+ 实现了真数组,避免了覆盖数据的可能
  ​


Java程序的种类
+ Applet【内嵌于Web文件中,用浏览器观看】
+ Application【可独立运行】
+ Servlet【服务器端】
  ​


Java基本命令
```
    javac 编译
    javac -D 指定编译后类层次的根目录
    java运行程序
```



Java命名：
+ 大小写敏感
+ 类名    【每个单词首字母大写】
+ 方法名【小写字母开头，之后每个单词首字母大写】
+ 文件名【.java后缀，文件名与类中唯一的public类名希艾娜沟通】
  ​


Java注释
+ 单行注释(//)
+ 多行注释(/* */)
+ 文档注释(/**  */)
  ​



Java转义序列
```
\t  Tab键空行
\n  换行
\r  回车
\b  退格符
\f  换页符
\'  单引号
\"  双引号
\\  反斜杠(\)
```




程序包
+ 包名(全小写)
+ 导包(例:import java.io.*)
+ java.lang【此包是由java解释器自动引入】
  ​



程序入口
+ 主函数(main方法)
+ public static void main(String [] args){ ... }
  ​


标识符：
+ 类，变量和方法的名称称为标识符
+ 必须以字母（ A 到 Z 或者 a 到 z ）、货币字符（ $ ）或者下划线( _ )开头【之后可以是任意字符】
+ 不能是关键词



修饰符
+ 【访问修饰符 】private,default,protected,public
+ 【非访问修饰符】final,abstract,strictfp,synchronized,volatile
  ​

访问修饰符
+ 【public】本类,同包,子类(包内,包外),包外其他
+ 【protected】本类,同包,子类(包内,包外)
+ 【default_默认】本类,同包,子类(包内)
+ 【private】本类
  ​



关键字
+ java中所有关键字都是小写的
+ 不能用作常量、变量和其他标识符的名字
+ java中的true,false,null,friendly,sizeof不是关键字,也不是保留字,它们只是显示常量值,但是程序中不能使用他们作为标识符
+ const和goto是java的保留的关键字,native是关键字
```
abstract, assert
boolean, break, byte
case, catch, char, class,const(保留),continue
default, do, double
else, enum(枚举), extends
final, finally, float, for
goto(保留关键字,没有具体含义)
if, implements, import, instanceof, int, interface
long
native( 用来声明一个方法是由与计算机相关的语言<如C/C++/FORTRAN语言>实现的),new
package, private, protected, public
return
strictfp(用来声明FP_strict（单精度或双精度浮点数）表达式遵循IEEE 754算术规范),short, static, super, switch, synchronized
this, throw, throws, transient, try
void, volatile, while
```



static关键字
+ 【静态变量 】类加载时初始化,所有实例共享
+ 【静态方法】可类名.方法名()调用,必须实现,不能抽象,类加载时已经存在
+ 【静态代码块】 可存在该类任意地方,类加载时一并执行
+ Java中并不存在全局变量,static可以实现一个"伪全局",JVM加载一个类时,会为该类中static修饰的成员变量和成员方法,在固定的位置开辟一个固定大小的内存区域
  ​


final关键字
+ 使用final关键字修饰的函数称为"内联函数"
+ "函数代码小,频繁调用"的情况下,适宜采用内联函数
  ​




枚举
+ java1.5新增加的特性,枚举类型是一种一种特殊的新类型
+ 枚举类型可以定义玮一个单独的文件,也可定义在其他类的内部
+ 默认继承java.lang.Enum抽象类,并且枚举类是final修饰,无法继承
+ 枚举类允许定义抽象方法,每个实例都必须实现
+ 枚举类可以实现多接口
+ 参考资料:[深入理解Java枚举类型(enum) ](http://blog.csdn.net/javazejian/article/details/71333103)
```
/*枚举类型,定义枚举常量,简单模型调用*/
    enum Day{
        MONDAY,TUESDAY,WEDNESDAY,
        THURSDAY,FRIDAY,SATURDAY,SUNDAY
    }

    public class EnumDemo{
        public static void main(String [] args){
            Day day = Day.MONDAY; //直接引用
        }
    }
```





## 2.变量


java中两种有效的数据类型
+ 【 原始数据类型】存放在栈中
+ 【引用数据类型】JVM中虚拟栈存放“对象的地址”,堆中存放“对象的实质”,通过地址来找到堆中对象的过程
  <br>

Java编译器在基本数据类型和对应的包装类型进行转换
+ 装箱(基本 -> 对象) 【可自动装箱】
+ 拆箱(对象 -> 基本) 【可自动拆箱】
  <br>


内存区域存放
+ new创建的对象在堆区
+ 函数中的临时变量在栈区
+ java中的String的字符串,在字符串常量区
  <br>



变量类型
+ 本地变量 (无默认值,栈深度内部实施)
+ 实例变量 (有默认值[0,false,null],与对象共存共毁)
+ 类变量(有默认值[0,false,null],与程序共开始共结束)
  <br>


数据类型
+ 【整数类型】byte,short,int,long
+ 【浮点类型】float,double
+ 【逻辑类型】boolean
+ 【字符型】char
  <br>


原始数据类型(基本数据类型,从小到大排序)
+ 【byte】1字节(8bit)-2^7 ~ 2^7-1
+ 【short】2字节,-2^15 ~ 2^15-1
+ 【short】2字节,16位(16bit),\u0000 ~ 、\uFFFF
+ 【int】4字节,-2^31 ~ 2^31-1
+ 【long】8字节,-2^63 ~ 2^63-1
+ 【long】单精度32位浮点
+ 【double】双精度64位浮点
+ 【boolean】true和false
  <br>


数字类型的包装类【都是抽象类Number的子类】
+ Byte
+ Integer
+ Short
+ Long
+ Float
+ Double
  <br>

成员变量(静态与非静态)默认值
```
【boolean】false
【byte】0
【short】0
【char】 (此处空白,默认为空字符就是'\u0000',数值为0)
【int】0
【long】0
【float】0.0
【double】0.0
【String】 null
【String []】 null
```
<br>



字符型常量
+ 单引号括起来的单个普通字符or转义字符
+ 【注意】字符串用双引号
  <br>



不确定值的默认类型
+ 整数默认是int
+ 带小数的默认是double
  <br>




类型互相转换
+ 小转大 [自动装换]
+ 大转小 [强制转换，丢失精度]
+ 原生类中的数据类型不可任意转换【范围大的转为范围小,需要强制类型转换】
  <br>



字符串和字节的转换
```
<1>字符串 -> 字节
		String s;
		byte [] bs = s.getBytes()

<2>字节 -> 字符串
		byte [] bs = new byte[int];
		String s = new String(bs) 或者 String s = new String(bs,encode);//encode指的是编码方式
```
<br>


特殊例题
```
<1>
        double d = 5.3e12;
         【表示5.3乘以10的12次方】

<2>
        Integer对象范围是-127 ~ 127
                如果超出范围会新建一个对象
                否则就是使用内存中那个对象

<3>
        实现CharSequence接口的类
                CharBuffer,String,StringBuffer,StringBuilder

<4>
        'a' = 1/3  这个表达式是错的
              'a'是个常数,不能赋值,字符型与整型运算时会自动转换为整型,a的ASCII码为97
                  'A' + 32  正确
                  'a' % 3 正确

<5>
    执行`"int a ='2'"`的值,a的值是50
    【注意常见字符的ASCII码是空格_32,0_48,A_65,a_97】
```


3.运算符
-------------------------

类型
+ 算数运算符  [+ - * / % ++ --]
+ 关系运算符  [== != > < >= <=]
  + 位运算符  [& | ^(异或,同真异假) ~ << >> >>>]
+ 逻辑运算符  [&& || !]
+ 赋值运算符  [= += -= *= /= %= <<= >>= &= ^= |=]
+ 其他运算符  [?:(三元运算符) instanceof(判断左变量 是否为 右侧类型【同类型or子类】]instanceof运算符(二元运算符)


+ 判断"左边的对象"是否为"右边类(接口,抽象类,父类)"的实例


位运算
```
<1>左移动运算符【<<】  
	丢弃最高位,0补最低位  [左移n位相当于 乘以2的n次方]
<2>右移运算符
	符号位不变，左边补上符号位(正补0,负补1) [右移n位相当于 除以2的n次方]
<3>无符号右移运算符 【>>>】
	丢弃最低位，0补最高位 【只是对32位和64位有意义】

***********************************************************

<4>按位与运算符【&】
	两位同时为“1”，结果才为“1”，否则为0 
		a.清零[与一个二进制各位都为0的数相与]
		b.取一个数中指定位[与一个数,需取的指定位为1,其余位置为0]，进入&1

<5>按位或运算符 【|】
	两位有一个1,即为1
		a.指定某些位置为1[与一个数指定位为1,其余位置为0]


<6>异或运算符
	同假异真
		a.使特定为反转[需要翻转的位置为1,其余位为0]
		b.保留原值[与0进行异或]
		c.两个变量值交换[A=A^B;B=A^B;A=A^B]
			【原理:利用一个数异或本身等于0和异或运算符合交换率】

<7>取反运算符【~】
	相反

```

二进制
+ 原码  [二进制]
+ 反码  [正数反码是本身,负数反码,符号位不变,各个位取反]
+ 补码  [正数补码是本身,负数补码,符号位不变，各个位取反,最后+1]
  ​


二进制的算术运算
+ 加法 [逢二进一]
+ 减法 [借一有二]
+ 乘法 [与十进制类似,00 = 0; 01=10=0;11 = 1]
+ 除法 [与十进制类似]
```
<1>加   
	 1110 [被加数]
   + 1011 [加数] 
   ------------
    11001 

<2>减
     1101 [被减数]
   + 1011 [减数]
   -------------
     0010

<3>乘
	 1001 [被乘数]
   * 1010 [乘数]
   --------------
     0000     [部分积(1对1相乘)]
    1001
   0000   	 
  1001
  ---------------
  1011010     [乘积]

<4>除
            000110    [商]
		   ----------------------
 [除数]110| 100110         [被除数]
 	      |  110
 	        --------
 	         01110
 	          110
 	        -------
 	            10
```


Java内置的进制转换
```
Integer.toHexString(int i);					[十进制 -> 十六进制]
Integer.toOctalString(int i);				[十进制 -> 八进制]
Integer.toBinaryString(int i);				[十进制 -> 二进制]
Integer.valueOf("FFF",16).toString();		[十六进制 -> 十进制]
Integer.valueOf("376",8).toString();		[八进制 -> 十进制]
Integer.valueOf("0101",2).toString();	    [二进制 -> 十进制]


八进制
    以8为基数的算法,逢8进1,所以“10进制的8”就是010【前面哪个0是为了和十进制区分用的,又称转义符】
```

0xff为16进制
二进制 1111 1111
十进制 255
八进制 377




反码【正数是本身,负数是符号位不变,各个位取反】
补码【正数是本身,负数符号位不变,各个位数取反,最后+1】



案例
```
10
    【原码】0000000000000000 0000000000001010
	【~10】 1111111111111111 1111111111110101(变为负数,计算机用补码存储)
【~10反码】 1000000000000000 0000000000001010
【~10补码】 1000000000000000 0000000000001011(计算机最终储存数字-11)
```





计算机中通常采用的字节存储机制大小端
+ 小端法(Little-Endian) [低位字节排放在内存的低地址端(即该值的起始地址,高位自己排放在内存的高地址端)]
+ 大端法(Big-Endian) [高位自己排放在内存的低地址端(即该值的起始位置),低位自己排放在内存的高地址端]
+ 网络字节序：TCP/IP各层协议将字节序定义为Big-Endian，因此TCP/IP协议中使用的字节序通常称之为网络字节序
  ​




4.循环和判断
-------------------

循环类型
```
<1>while(条件){ ... }
<2>do{ ... }while(条件);
<3>for(变量;条件;每次循环更新){ ... }
<4>for(类型 变量：类型集合){ ... }   【for-each循环】
```


循环中特有关键字
+ continue 【循环跳过此次，进行下一次】
+ break    【退出循环】



条件判断
```
<1>
    if(条件){ ... };
<2>
    if(条件1){ ... }else if(条件2){ ... };
<3>
    f(条件1){ ... }else if(条件2){ ... }else{ ... }
<4>
    switch(变量){ case 值1: case 值2: ....; default: ....; } 【对应变量值为入口执行，遇到break退出，未遇到执行到最后】
```




switch判断
+ JDK7之前,switch只能支持byte,short,char,int,JDK7后支持String
+ switch的表达式的值不能是null,否则会在运行时抛出NullPointerException,
+ case子句不能使用null,否则会出现编译错误





5.数组
--------------------------

&emsp;储存相同类型的元素，固定大小的连续集合,下标从0开始[数组元素通过索引访问]



特点
+ 数组是一个对象
+ 不是一种原生类
+ 数组存储在堆中连续内存空间里
  ​


应用
```
<1>创建数组
        a. int [] number = new int[3]; 
        b. int [] number = {1,2,3};


<2>赋值
        number[0] = 2;
```



工具类
java.util.Arrays 【排序,搜索,比较和填充】



6.函数(方法)
---------------------

函数类型：
+ 原始类型是值传递,引用类型是引用传递
+ void关键字 [返回值类型,声明后方法体可不用返回值]
+ 构造函数   【无返回值类型,方法名与类名一致】
+ 普通函数   
+ 类函数(静态函数)
  ​



构造方法
+ 新对象被创建时,会被调用
+ 每一个类都有构造方法,程序员没有给类提供构造方法的情况下,Java编译器会为该类创建一个默认的构造方法
+ 【构造方法重载】可以为一个类创建多个构造方法,每一个构造方法必须有它唯一的参数列表
+ 【不支持复制构造方法】Java不支持像C++中的复制构造方法(又称为拷贝构造函数,是一种特殊的构造函数,它由编译器调用来完成一些基于同一类的其他对象的构造及初始化)
  ​


值传递和引用传递
+ Java中只有按值传递，没有按引用传递,参考资料:[http://guhanjie.iteye.com/blog/1683637](http://guhanjie.iteye.com/blog/1683637)
+ 【都是值传递】基本型变量传递的是"变量副本",对象型变量传递的是"对象引用的副本"
+ 【引用传递】传递的是对象地址,并不是原对象本身(形参与实参指向同一内存地址,对形式参数的操作就是对实际参数的操作,方法中执行的形参改变会影响实际参数的值)
+ String是被final修饰的类,本身的内容是不会改变的
  ​



类函数
+ 指的被static修饰的方法,无this指针【this不能在static方法内使用】
+ 可以调用其他类方法(同为static修饰)
  ​



重载
+ 形参列表必须不同(类型,个数or顺序)
+ 访问修饰符 or 返回值 or 抛出异常 都可以不同
+ 能够在一个类中或者一个子类中被重载
  ​


重写
+ 对从基类中继承来的方法进行重写
+ 返回值类型 and 函数名 and 参数列表 必须相同
+ 重写方法的访问权限>=被重写的方法
  ​


类的默认执行顺序 [Father.java 和 Child.java(extends Father)]
1. 父类静态变量
  2 父类的静态代码块【static{ ... }】
  3.子类静态变量
2. 子类静态代码块
3. 父类构造(非静态)代码块【{ ... }】
  6 父类构造函数
4. 子类构造代码块
5. 子类构造函数
  ​



虚函数
+ 在Java语言中, 所有的方法默认都是"虚函数". 只有以关键字final 标记的方法才是非虚函数
+ 【结论】当执行到超类的构造器时，会调用move()，但是派生类也存在重写的move(),所以,最终只会执行派生类里的move()【超类的move()不执行】, 示例
```
<1>Father类 【超类(父类】
		public class Father {
			public Father(){
				System.out.println("Father constructor------------");
				move();
			}
			public void move(){
				System.out.println("Father move");
			}
		}


<2>Child类 【派生类(子类)】
		public class Child extends Father{
			public Child(){
				System.out.println("Child constructor------------");
				move();
			}
			public void move(){
				System.out.println("Childe move");
			}
		}


<3>测试方法
		public class test{
			public static void main(String[] args) {
						Father child = new Child();
		//				Child child = new Child();	     两种结果一样
			}
		}

*************************************************
控制台输出
	Father constructor------------
	Childe move
	Child constructor----------------
	Childe move
```


终结器
+ 仅在对象被垃圾收集器销毁时调用
+ 关键字 protected 是为了保证在包外的代码不能访问 finalize() 方法【包外子类可以访问】
+ 无法确定 finalize() 什么时候执行【如果你的程序在垃圾收集器发生之前就结束了，finalize() 方法将不会被执行。】
```
finalize()结构
	protected void finalize( ){
		 ...
	}
```



7.异常处理
------------------------

三种类别
+ 检测异常	  [IOException]
+ 运行时异常  [RunntimeException]
+ 错误  [Error]
```
		 ____Error(错误)
Throwable			
                                            	____IOException
    	 _____Exception(异常)
    	 		         ____RunntimeException
```


RuntimeException异常
+ 运行时异常(包含子类)将由系统自动抛出,不需要使用throw语句
+ Java编译器允许忽略运行时异常,一个方法可以既不捕捉,也不声明抛出运行时异常
+ 例如"除数为0"等ArithmeticException,是RuntimeExcpetion的子类
  ​



常见的异常
+ JVM异常  [由JVM在逻辑层上专门抛出]
+ 程序性异常 [由应用程序或是编写API的程序明确抛出]
  ​


关键字
+ throws
+ throw
+ finally  【无论是否抛出异常,都会执行finally代码块】
  ​


try,catch,finally中返回值总结
```
<1>finally中无return,try或者catch中有return
    【在return前一定会执行finally的代码块,但finally内容不会影响返回值,返回值以try或者catch执行结果为主】
                try{
                        ....
                        return 1;
                }catch(Exception e){
                        return 2;
                }finally{}

<2>finally中有return,try或者catch中有return
     【依旧一定会执行return,返回值以finally中的结执行结果为主】
                try{
                    return 1;
                }catch(Exception e){
                    return 2;
                }finally{
                    return 3;
                }
```




捕获异常
+ try/catch  【 [可添加finally{}]】
+ 多个catch块 【[顺序依次寻找，范围由小_大，知道找到该异常类型]】
  ​



抛出异常
+ throws [方法定义时，定义抛出异常]
+ throw  [方法题内部抛出]
  ​


自定义异常 [继承Exception类,异常类和其他类一样,包含字段和函数]
```
class MyException extends Exception{
	public MyException(){	

	}
	public MyException(String message){
		super(message);
	}
}
```





实际应用中的经验与总结
+ 处理运行时异常时,采用逻辑去合理规避同时辅助try-catch处理
+ 在多重catch块后面,可以加一个catch(Exception)来处理可能会被遗漏的异常
+ 对于不确定的代码,也可以加入try-catch,处理潜在异常
+ 尽量去处理异常,切忌知识简单的调用printStackTrace()去打印输出
+ 具体如何处理异常,要根据不同的业务需求和异常类型去决定
+ 尽量添加finally去释放占用的资源
  ​


程序代码出错
+ java调试器为jdb.exe,对程序进行调试
  ​






8.面向对象【继承,多态,封装,抽象和接口】
-----------------

类和对象
+ 【类】定义对象的行为和状态，的模版
+ 【对象】 具有状态和行为
```
<1.类 
	局部变量
	类变量(静态)
	实例变量(非静态)
	
	构造器[无参／有参　构造方法]
,2>对象
	声明
	实例化
	初始化
```



面向对象三大特征
+ 封装,继承,多态
+ c 是面向过程,java 和 c++ 都是面向对象
  ​


面向对象的五大基本原则
+ 单一职责原则  [SRP]
+ 开放封闭原则  [OCP]
+ 里式替换原则  [LSP]
+ 依赖倒置原则  [DIP]
+ 接口隔离原则  [ISF]
  ​


面向对象程序设计四大基本概念
1. 继承
2. 多态
3. 抽象
4. 封装
  ​


继承
+ 【IS-A关系】 继承关系 A is-a B(B是A的父类),使用insAtanceof关键字 检查左变量 是否 右类型(同类型 or 子类)
+ 【HAS-A关系】 从属关系 A has a B(B是A的组成部分,例：属性)
+ 【LIKE-A关系】 组合关系 A like a B(B是A的接口)
+ 【继承缺点】1.父类变-子类必须变 2.强耦合关系 3.破坏了封装,对于父类,子类可以了解父类的实现细节
+ 【子类继将继承父类所有的属性和方法】注意:1.父类private方法不能直接调用;2.静态方法不能重;3.父类构造函数不能被继承
+ 【子类可以重写父类方法】final修饰的method的不能重写,重写时若需要调用父类函数时用super.函数名()
+ 【子类重写父类方法】子类的访问权限>=父类访问权限
  ​


多态
+ 【父类引用指向子类对象】父类引用,实例化子类对象
+ 【多接口机制】     指父类是多态,多个继承/接口
+ 【子类可以覆盖(重写)父类函数】 必要调用时候用super关键字
+ 【虚函数 】无论是什么数据类型的引用，运行时会调用被覆盖方法(只运行子类函数)，在编译时都会遵循于源码
+ 【编译和运行】编译时多态【重载】和运行时多态 【动态绑定】
+ 继承链中调用对象的方法存在优先级【this.show(O),super.show(O)、this.show((super)O)、super.show((super)O)】
+ 经典案例[深入理解java的多态性](http://blog.csdn.net/thinkGhoster/article/details/2307001)
  ​


封装
+ 【特点】使类中字段私有，通过共有函数访问私有字段 (例如setter和getter,是该对象对外开发的接口)
+ 【特点】良好的封装能够减少耦合,可以对成员进行更加精确的控制
+ 【特点】使代码具有可维护性,灵活性和扩展性[类内部的结构可以自由修改]
+ 【特点】隐藏信息,实现细节
+ 【特点】封装确实可以使我们容易地修改类的内部实现，而无需修改使用了该类的客户代码(在setter和getter添加相应的业务逻辑)
  ​




抽象
+ 【OOP(面向对象程序设计)中的抽象类】使用关键字abstract声明,extends继承
+ 【特点】子类只能继承1个抽象类,必须实现所有抽象方法
+ 【特点】接口能包含构造方法,不能被实例化
+ 【特点】成员变量可以有final,也可包含非final
+ 【特点】成员方法可以抽象也可以不抽象(抽象方法无方法体)
+ 【特点】抽象类不能用static修饰方法(抽象类不能实例化,不能分配内存,而static修饰的方法在类实例化前就分配内存,矛盾)
+ 【特点】抽象类必须被继承意义,final修饰类为终态,不能被继承,所以final不能修饰抽象类
+ 【特点】抽象类可以在不提供接口方法实现的情况下实现接口。
+ 【特点】以前的抽象类抽象方法默认是protected,jdk1.8后改成默认是default
+ 【特点】子类抽象方法不能与父类抽象方法重名
+ 【特点】abstract不能与final并列修饰同一个类
+ 【特点】abstract 不能与 private、static、final 或 native 并列修饰同一个方法




接口
+ 【是抽象方法的集合】 使用关键字interface声明,implements继承
+ 【特点】子类可继承多个接口,必须实现接口内所有抽象方法
+ 【特点】接口不能包含构造方法,不能被实例化
+ 【特点】成员变量必须是public,static,final
+ 【特点】成员方法必须是public,abstract,没有方法体
+ 【特点】接口的成员变量默认public,static,final,需要直接附上初始值,子类实例类可以直接访问接口中的变量
+ 【特点】不能用final修饰接口的成员方法
+ 【特点】private和protected不能修饰接口,1.8以后支持static修饰接口,以前不支持
+ 【注意】子类避免实现多个接口时,方法名出现重复



抽象类与接口的区别
1. 抽象层次[抽象类是对类抽象,接口是对行为抽象]
2. 跨域不同[抽象类体现继承,父类和派生类必须存在IS-A关系,接口是like-a关系]
3. 设计层次[抽象类是底向上抽象而来,接口是自顶向下设计]
  ​





9.Java的数据结构【集合框架】
----------------------

&emsp;Java中的集合类,是一种工具类,就像是容器,储存任意数量的具有共同属性的对象,java提供集合框架[java.util包].
<br>

参考资源
+ 集合框架[java.util包]
+ [The Collection Algorithms(所有算法实现的列表)](http://www.tutorialspoint.com/java/java_collection_algorithms.htm)


作用
+ 在类的内部,对数据进行组织
+ 简单而快速的搜索大数量的条目
+ 有的集合接口,提供了一些列排列有序的元素,并且可以在序列中间快速的插入或者删除有关元素
+ 有的集合接口,提供了映射关系,可以通过关键字(key)去快速查找到对应的唯一对象,而这个关键字可以是任意类型
  ​


数组和集合的区别
+ 数组长度固定,集合长度可变
+ 数组只能通过下标访问元素,类型固定,而有的集合可以通过任意类型查找所映射的具体对象
  ​



集合框架体系图
```
<1>常用两个2根接口
			  ___List(序列)[有序,可重复]
	Collection       ___Queue(队列)[有序,可重复]
			  ___Set(集)[无序,不可重复(通过hashcode和equals函数保证)]
	
	Map       ___(键值对)
```





Collection类【实现集合接口的标准集合类】
```
【ArrayList】   动态数组序列,异步,实现List接口
【LinkedList】  双向链表,异步,实现List,Queue接口 
【Vector】       动态数组,同步(线程安全)
【Stack】         栈(后进先出)
【HashSet】      哈希集
                                (1.查询速度最快的集合; 
                                 2.内部HashCode实现;
                                 3.内部顺序由哈希码决定;
                                 4.不保证Set的迭代顺序 and 不保证顺序恒久不变),实现Set接口
【LinkedHashSet】 继承HashSet,允许顺序插入迭代
【TreeSet】            排序树集(1.内部TreeMap实现;2.使用元素自然顺序排序 or 使用提供的Comparator排序器排序)
【HashMap】           哈希键值对
                                    (1.哈希表数据结构实现;
                                     2.j遇到冲突,使用散列链表的形式将所有相同的哈希地址的元素串起来)
【TreeMap】           排序树键值对(
                                     1.内部red-black(红-黑)数据结构实现;
                                     2.实现了SortedMap接口);
                                     3.需要维持内部元素的顺序,所以通常要比HashMap和HashTable慢
【LinkedHashMap】     哈希键值对,允许顺序插入迭代
【HashTable】     
                        1.哈希表数据结构实现;
                        2.解决冲突的方式与HashMap一样采用散列列表形式,不过性能比HashMap低;
【Queue】	
                        队列(先进先出),
                        1.两类
                                a.阻塞式队列(队列满了以后插入元素,抛出异常)
                                b.双端队列(支持在头尾两端和移除元素)

【PriorityQueue】 优先队列,是线程不安全

 【LinkedBlockingQueue】可选有界队列,线程安全,不允许有null值
```



ArrayList特性
+ 底层是基于数组实现(动态数组),动态增长内存,初始容量10,默认扩容1.5倍
+ 非线程安全
+ 查找效率高(通过下标直接查找指定元素),插入删除效率低(需要大量移动元素)
+ 允许元素为null
+ 可以指定初始容量(提升速度“效率和资源利用率”)
+ 实现了Serializable接口(支持序列化，可以通过序列化传输)
+ 实现了RandomAccess接口(支持快速随机访问,通过下标序号)
+ 实现了Cloneable接口(能被克隆)
  ​


LinkedList特性
+ 底层是基于双向循环链表(头结点不存放数据)
+ 非线程安全
+ 基于链表实现,不存在容量不足问题【无扩容方法】
+ 查找效率低(有加速动作-index <> size/2),插入删除效率高
+ 可作为栈,队列和双端队列来使用
+ 实现了Serializable接口(支持序列化)
+ 实现了Cloneable接口(能被克隆)
+ 允许元素为null
  ​


Vector特性
+ 线程安全(相对安全，有时候需要添加同步语句保证线程安全)
+ 默认容量为10,默认扩容2倍
+ 很多方法加入synchronized关键字
+ 允许元素为null
+ 实现了Serializable接口(支持序列化，可以通过序列化传输)
+ 实现了RandomAccess接口(支持快速随机访问,通过下标序号)
+ 实现了Cloneable接口(能被克隆)
  ​


HashMap特性
+ 非线程安全(多线程需采用concurrent并发包下的concurrentHashMap)
+ 初始容量(默认16)和加载因子(默认为0.75)
+ key和value都允许为null
+ 实现了Serializable接口(支持序列化，可以通过序列化传输)
+ 实现了Cloneable接口(能被克隆)
  ​


Hashtable特性
+ 线程安全
+ 同样基于哈希表，同样每个元素都是key-value(内部也通过单链表解决冲突问题)
+ 与HashMap存储结构和解决冲突的方法是相同的
+ 默认初始容量11【HashMap初始为16，要求一定为2的整数次幂】
+ key和value都不允许为null
+ 实现了Serializable接口(支持序列化，可以通过序列化传输)
+ 实现了Cloneable接口(能被克隆)
  ​


Arrays和Collections的作用(都在java.util包内)
+ 操作数组和集合的两个工具类
+ ArrayList和Vector中大量调用Arrays.Copyof()
+ Collections中有很多static方法可以返回集合类的synchronized版本(线程安全版, 要用线程安全的结合类，首选Concurrent并发包的集合类)
  ​


Comparable接口
+ 实现该接口表示,该实例可以比较大小,可以进行自然排序
+ 定义"默认"的比较规则
+ 需要自定义实现compareTo()函数
+ compareTo()返回正数表示大,负数表示小,0标识相等
  ​

Comparator接口
+ 用于定义"临时"比较规则
+ 需自定义实现compare()方法
+ Comparator和Comparable都是Java集合框架的成员
  ​


Iterator(迭代器)
+ java.util包的接口,在集合中循环遍历元素
+ 支持从源集合安全的删除对象(指针,it.remove()),当打开Iterator迭代集合同时有在对集合进行修改,避免ConcurrentModifiedException
+ 只能遍历Collection的子类,不能遍历Map的子类
+ Collection的实现类都实现了iterator()，返回Iterator对象(ListIterator专门遍历List,EnumerationJDK.1.0引用，作用与其相同，功能要少,只能在Hashtable,Vector和Stack中使用)
+ ListIterator(接口) 继承了 Iterator [来允许一个列表的双向遍历和元素的修改]
```
		public static void main(String[] args) {	
				List<Integer> list = new ArrayList<Integer>();
				for(int i = 0; i < 100; i++){
					list.add(i);
				}
				Iterator<Integer> it = list.iterator();
				while(it.hasNext()){
					System.out.print(it.next() + " ");
				}
			}
```



判断是集合中是否拥有指定元素
+ 使用contains()或者containsValue()【键值对】
+ 遍历List中每一个元素，然后调用每一个元素的equals()，去跟contains()中方法参数进行比较,如果有一个元素的equals()返回true,则结果最终返回true(如果是自定义Object对象,需要根据需求重写equals方法),时间复杂度是O(n)
  ​


集合排序
```
<1>默认升序【注意:进行sort排序时,列表中的所有元素都必须实现Comparable接口否则编译器会提错】
		Collections.sort(List<T> list); //默认从小到大


<1-2>将元素反转【顺序调换】
		Collections.reverse(List<T> list);

<2>自定义排序 [对象外部,可改为升序 or 降序]
		Collections.sort(list, new Comparator<Integer>() {  
				    @Override  
				    public int compare(Integer a, Integer b) {  
				        return a>b ?  1: ( a==b ? 0 : -1);    //a >b(1) a==b(0) a<b(-1)
				    }  
				});  


<3>对象排序[实现Comparable接口,对象内部定义排序]
			/*Bean对象*/
			public class Person implements Comparable<Person>{  
			    private String name;  
			    private int age;  
	
			    public Person(String name, int age) {  
			        this.name = name;  
			        this.age = age;  
			    }  
			    
			    //实现compareTo()函数
			    @Override  
			    public int compareTo(Person o) {  
			        return (o.age < age) ? -1 : ((o.age==age)?0 : 1);     //这里定义根据年龄，降序 
			    }  
			      
			    //getter
			    public String getName(){		
			    	return name; 
			    }
			    public int getAge(){			
			    	return age;  	
			    }
			}  
	
			/*测试类*/
			public class test{
				public static void main(String[] args) {	
					Person a = new Person("皮皮翔",5);  
					Person b = new Person("皮皮勋",62);  
					Person c = new Person("皮皮强",12);  
					List<Person> plist = new ArrayList<Person>();  
					plist.add(a);  
					plist.add(b);  
					plist.add(c);  
					Collections.sort(plist);  
					for(Person info : plist){  
					    System.out.println("Name:"+info.getName()+"	->	Age:"+info.getAge());  
					}  
				}
			}
	
				*************输出结果**************
				Name:皮皮勋	->	Age:62
				Name:皮皮强	->	Age:12
				Name:皮皮翔	->	Age:5

```



集合去重
```
<1>ArrayList有序去重【自定义对象】
	     //a.将集合中元素倒叙
	        Collections.reverse(clist);  //反转排序
	  	    //Collections.sort(clist);    //自定义对象倒叙，内部需实现Comparable接口[里面的public int compareTo(Object obj) ]


	    //b.将集合元素去重
	        LinkedHashSet<ClassMastesUser>  linkSet=new LinkedHashSet<ClassMastesUser>(clist); [需根据需求重写hashCode()和equals(),Set集合先比较hasCode值,如果相等,再比较equals]
	        clist.clear();
	        clist.addAll(linkSet);
	        Collections.reverse(clist);
```




10.泛型与对象序列化
--------------------------------

泛型(generics)的应用
+ 限制类型参数(不能是原始类型(例:int double),必须是引用类型)
+ 泛型函数
+ 泛型类
  ​

特点
+ 只在程序源码中存在(编译后字节码文件,已被替换为原来原生类型,并在相应类型插入强制转换代码)
+ 对于运行期的Java语言来说，ArrayList<String>和ArrayList<Integer>就是同一个类
+ 泛型技术实际是Java语言的一颗语法糖(Java语言中的泛型实现方法称为类型擦除,基于这种方法实现的泛型被称为伪泛型)
+ Java中集合的泛型,是防止错误输入的,只能在编译阶段有效【有泛型和无泛型的ArrayList,得到的Class对象进行==比较,返回true】
+ 可以对对象的Class进行反射操作,绕过泛型添加元素
  ​

简单实例
```
<1>遍历打印不同类型的数组
		public class test{	
			public static <E> void printArray(E [] array){
				for(E element: array){    //遍历数组
					System.out.print(element);
				}
				System.out.println(); //换行
			}
			
			public static void main(String[] args) {
				  Integer[] intArray = { 1, 2, 3, 4, 5 };
			      Double [] doubleArray = { 1.1, 2.2, 3.3, 4.4 };
			      Character[] charArray = { 'H', 'E', 'L', 'L', 'O' };
			      
			      printArray(intArray);
			      printArray(doubleArray);
			      printArray(charArray);
			      
			}
		}


<2>泛型类
		public class test{	

			public static void main(String[] args) {
				//1.声明
				Box<Integer> iBox = new Box<Integer>();
			    Box<String> sBox = new Box<String>();  
			    
			    //2.赋值
			    iBox.add(10);
			    sBox.add("皮皮");
			    
			    //3.输出
			    System.out.println(iBox.get());
			    System.out.println(sBox.get());
			}
		}
	
		class Box<T>{
			private T t;
			public void add(T t){
				this.t =t;
			}
			public T get(){
				return t;
			}
		}		
```

---



对象序列化
+ Java的一种对象序列化的机制
+ 对象被描述成一系列包括对象的数据,以及有关对象的类型，和在对象中存储的数据的类型**的字节**
+ 一个序列化的对象被写进文件,能在文件中被读出,并反序列化为类型信息和表示对象的字节,并且它的数据可以被用来重新创建为内存中的对象
+ 整个过程都是 Java 虚拟机（JVM）独立的，也就是说，在一个平台上序列化的对象可以在另一个完全不同的平台上反序列化该对象。 
  ​


序列化标准
+ 类必须实现java.io.Serializable[接口]【不实现就不能序列化】
+ 标记为transient(瞬态)的元素,不会进行jvm的默认序列化
+ 序列化对象到文件中时候,java中标准是文件扩展名(后缀)为.ser
+ 一个类实现了Serializable接口,那么其子类都能进行序列化
+ 若父类没有实现Serializable接口,子类继承父类并实现Serializable接口,那么子类执行序列化操作时,父类的构造函数会被默认调用(如果都执行了序列化,则父类的构造函数是不会被调用的)
  ​

案例【对象序列化和反序列化】
```
		class Employee implements Serializable{
			public String name;
			public String  address;
			public transient int SSN;
			public int number;
			public void check(){
				System.out.println("name : " + name + " --- address : " + address);
			}
		}
		public class test{	
			//测试主方法
				public static void main(String[] args) {
					Employee e = new Employee();
						e.name = "皮皮";
						e.address = "大街上";
						e.SSN = 142101;
						e.number = 1;
						
					doSerializable(e);										 //执行序列化
					doInSerializable("src/test/object.ser");    //执行反序列化
				}
				
			//A-序列化
			public static <E> void doSerializable(E e){//参数： 对象(泛型)
				try{
					//1.定义
					FileOutputStream fileOut = new FileOutputStream("src/test/object.ser");
					ObjectOutputStream  out = new ObjectOutputStream(fileOut);
					
					//2.写入流
					out.writeObject(e);
					
					//3.关闭流
					out.close();
					fileOut.close();
					
					System.out.println("序列化成功！  ->	文件保存至项目根路径/src/test/object.ser");
					
				}catch(IOException i){
					i.printStackTrace();
				}
			}
			
			//B-反序列化
			public static void doInSerializable(String path){ //参数：路径
			
				Employee e = null;    //泛型
				try{
					//1.定义
					FileInputStream  fileIn = new FileInputStream(path);
					ObjectInputStream in = new ObjectInputStream(fileIn);
					
					//2.读取流
					e = (Employee)in.readObject();
					
					//关闭流
					in.close();
					fileIn.close();
	
					System.out.println("反序列化成功！  ->	成功读取" + path + "文件");
					
				}catch(IOException i){
					i.printStackTrace();
				}catch(ClassNotFoundException c){
					System.out.println("没有发现文件");
					c.printStackTrace();
				}
						
				//打印输出【验证是否反序列化成功】
				    System.out.println("Name: " + e.name);
				    System.out.println("Address: " + e.address);
				    System.out.println("SSN: " + e.SSN);       //因为SSN设置了transient(瞬态),不能被序列化，所以输出的是SSN的类型(int)的默认值0
				    System.out.println("Number: " + e.number);
			
			}
		}
	
			*******************控制台输出***********************
	
				序列化成功！  ->	文件保存至项目根路径/src/test/object.ser
				反序列化成功！  ->	成功读取src/test/object.ser文件
				Employee对象反序列化结果------------------->
				Name: 皮皮
				Address: 大街上
				SSN: 0
				Number: 1


注意：
	1.object.ser是生成的序列化文件
	2.Emplyee对象的SSN字段,被设置成transient(瞬态),所以没被序列化
	3.反序列化时,调用readObject() ,得到结果需要强转为对象类型
	4.try/catch需要捕获2个异常(IOException 和 ClassNotFoundException)

```



11.静态关键字static
-------------------
&emsp; “static”关键字表明一个成员变量or成员方法可以在没有所属的类的实例变量的情况下被访问。



Java中的static方法不能被覆盖
+ 因为方法覆盖是基于运行时动态绑定的,而static方法是编译时静态绑定的。static跟类的任何实例都不相关,概念上不适用
  ​


不可以在static环境下访问非static变量
+ static是属于类的,在该类中所有实例的值是共享唯一的,当Java虚拟机载入类时,会对static变量进行初始化,如果代码尝试不用实例访问非static变量,编译器会报错,因为非static变量还没被创建出来,没有跟任何实例关联
  ​


静态方法
+ 静态方法中,可以直接调用同类中的静态成员,但不能直接调用非静态成员[(变量 or 方法)如需调用,则要创建类的对象]
+ 普通成员方法中,可以直接访问同类的非静态变量和静态变量
  ​


静态初始块
+ 只在类加载时执行,只执行1依次
+ 只能给静态变量赋值,不能给普通成员变量赋值
  ​


类的默认执行顺序 [Father.java 和 Child.java(extends Father)]
1. 父类静态变量
2. 父类的静态代码块【static{ ... }】
3. 子类静态变量
4. 子类静态代码块
5. 父类构造(非静态)代码块【{ ... }】
6. 父类构造函数
7. 子类构造代码块
8. 子类构造函数
  <br>


---





12.内部类
-------------------
&emsp;《Think in java》中的一句话`使用内部类最吸引人的原因是：每个内部类都能独立地继承一个（接口的）实现，所以无论外围类是否已经继承了某个（接口的）实现，对于内部类都没有影响`
<br>

特点：【部分摘自《Think in java》】
1. 解决多重继承的问题
2. 内部类可以用多个实例[每个实例都有自己的状态信息 and 与其他外围对象的信息相互独立]
3. 在单个外围类中，可以让多个内部类以不同的方式实现同一个接口，或者继承同一个类
4. 创建内部类对象,不依赖于外围类对象的创建【内部类对象需要另外创建,[
  外部类.内部类 内部类对象名 = 外部类对象名.new 内部类()]】
5. 内部类并没有令人迷惑的“is-a”关系，他就是一个独立的实体
6. 内部类提供了更好的封装，除了该外围类，其他类都不能访问 
7. 内部类无限制访问外围类元素
8. 内部类是编译时的概念【编译成功后,它就与外围类属于两个完全不同的类,会生成两个文件(例如:Test.class和Test&Inner.class),它们之间的联系依然存在】
9. 内部类可以使用代码块
  ​


外部类和内部类的修饰符区别
外部类只用public,abstract,final
内部类可以用修饰成员变量的修饰符修饰内部类,比如private,static,protected




final的使用
+ 所在的方法的形参需要被内部类(局部内部类)里面使用时，该形参必须为 final
  ​


四种内部类类型:
+ 成员内部类
+ 局部内部类 
+ 匿名内部类 
+ 静态内部类
  ​



成员内部类
+ 定义在Outer类的内部,相当于Outer类一个成员变量的位置,可适用任意的访问控制符(public,protected,private)
+ Inner(内部)类可以直接访问Outer(外部)类中的数据,不受访问控制符影响(可直接访问私有属性)
+ Outer类不能直接使用Inner类的成员和方法
+ 定义后,必须Outer类对象来创建Inner类对象,不能直接new(例:内部类 对象名 = 外部类对象.new 内部类())
+ Outer类和Inner类具有相同成员变量or方法时,Inner默认访问自己的,如果要访问外部类,需要使用this关键字
+ 编译文件后,会初相两个.class
+ 当于非静态内部类,可以直接访问外围类的数据(包含私有数据)
```
    public  class Test {
                private String name;
                public void setName(String name){
                    this.name = name;
                }
                //成员内部类
                public class Persion{
                    public void run(){
                        System.out.println( name + "在奔跑");  //调用外围类的成员变量
                    }
                }
                
                //测试主方法
                public static void main(String[] args) {
                    Test t = new Test();
                    t.setName("皮皮");
                    Persion  p = t.new Persion();
                    p.run();
                }
            }
    
            *************控制台输出****************
                        皮皮在奔跑
```




局部内部类
+ 不能在方法以外的地方使用
+ 不能使用访问控制符(private,default,protected,public)和static修饰符
```
    public  class Test {
                public void show(final String name){  //声明final的形参，才能被内部里调用
                    class Person{
                        public  Person(){
                            System.out.println(name + "在吃饭");  
                        }
                    }
                    Person p = new Person();
                }
                
                //主方法
                public static void main(String[] args) {
                    Test t = new Test();
                    t.show("皮皮");
                }
            }   
    
        *************控制台输出****************
                     皮皮在奔跑
```




匿名内部类
+ 使用时,必须继承一个类 or 实现一个接口(两者不能兼得,同时也只能各有1个)
+ 本身不能是抽象的,必须实现继承or接口的所有抽象方法
+ 不能定义构造函数
+ 不能有static的成员变量和方法
+ 类似于"局部内部类",所以局部内部类所有限制同样生效
```
    interface Wing{                                              //翅膀接口
            public void fly(); 
        }
        abstract class Animal implements Wing{   //动物抽象类
            public abstract void eat ();
        }
        public  class Test {
            public void test(Animal animal){            //Test对象的测试方法，传入一个Animal对象
                animal.eat();
                animal.fly();
            }
            public static void main(String[] args) {
                Test t  = new Test();
                t.test(new Animal(){                             //调用test方法,且用匿名内部类去实现 动物抽象类 and 翅膀接口的 抽象方法
                    @Override
                    public void eat(){
                        System.out.println("动物需要吃饭");
                    }
                    @Override
                    public void fly(){
                        System.out.println("有翅膀的动物可以飞");
                    }
                });
            }
        }   


        *************控制台输出****************
                    动物需要吃饭
                    有翅膀的动物可以飞
```



静态内部类
+ 不能直接访问Outer类发的非静态成员(可通过“new 外部类().成员”)
  +【同名问题】1.与Outer类的静态成员名相同,使用"类名.静态成员访问";2.与Outer类的静态成员名不同,使用"静态成员名"直接访问;
+ 创建静态内部类对象时,不需要外部类对象"内部类 对象名 = new 内部类()"
+ 只有静态内部类才可以用关键字static
```
	public  class Test {
	            static private String name;
	            private Integer height;
	            private Integer weight;
	            
	            static class Animal{
	                static  private String age;
	                public static void show(){
	                    name = "皮皮";
	                    age = "21";
	                    System.out.println("这个动物 name : " + name  + "------age :" + age); //只能访问外部静态的name,不能访问非静态的height,weight
	                }
	            }
	
	            //主方法
	            public static void main(String[] args) {
	                Test.Animal.show();  //直接 外围类名.内部类名.方法名()调用
	            }
	        }
	
	        *************控制台输出****************
	            这个动物 name : 皮皮------age :21		
```

---




13.多线程基础
-------------------------


进程
+ 程序(任务)的执行过程
+ 持有资源(共享内存,共享文件)和线程
  ​

线程
+ 线程是系统中最小的执行单元
+ 同一进程中有多个线程
+ 线程共享进程的资源
+ java工具,查看线程类型(用户or守护)和线程状态【`jstat.exe`命令行工具,`jvisualvm.exe`界面话工具】
  ​



进程和线程的区别
+ 【定义】进程是执行着的应用程序,线程是进程内部的一个执行序列
+ 【从属关系】进程划分大于线程,一个进程可以包含多个线程
+ 【形式】进程是程序的一个动态形式,是CPU,内存等资源占用的基本单位;线程无法占用这些资源
+ 【通信】进程相互独立,通信困难;线程共享一块内存区域,通信方便
+ 【固定】进程执行过程中,包含比较固定的入口,执行顺序,出口;而线程的这些过程被应用程序所控制
  ​


Java线程两种类型
1.【用户线程】运行在前台,执行具体的任务(例:程序主线程,连接网络的子线)
2.【守护线程】运行在后台,为其他前台线程服务



守护线程的特点
+ 一旦所有用户线程都结束运行,守护线程会随 JVM 一起结束工作(自动结束)
+ 【应用】数据连接池中的和JVM虚拟机启动后的监测线程,垃圾回收线程
+ 通过Thread类的`setDaemon(true)`,设置当前线程为守护线程[]
+ 【注意】setDaemon(true)必须在start()方法前调用,否则会抛出IllegalThreadStateException异常
+ 【注意】在守护线程中产生的新线程也是守护线程
+ 【注意】不是所有任务都可以分配守护线程(比如读写操作 or 计算逻辑)
  ​



线程的生命周期
+ 【新建New】
+ 【可运行Runnable】调用线程的start(),此时线程只是进入线程队列,等待获取CPU服务
+ 【运行 Running】处于就绪的线程,获取了PCU资源,进入运行状态,指向run()内程序代码
+ 【阻塞 Blocked】由于某些原因而暂时让出CPU资源,暂停自己的执行(例如:调用sleep())
+ 【死亡 Dead】 线程的run()执行完毕 or 线程调用了stop()
```
<1> 新建( new )：新创建了一个线程对象。

<2.>可运行( runnable )：线程对象创建后，其他线程(比如 main 线程）调用了该对象 的 start ()方法。该状态的线程位于可运行线程池中，等待被线程调度选中，获 取 cpu 的使用权 。

<3>运行( running )：可运行状态( runnable )的线程获得了 cpu 时间片（ timeslice ） ，执行程序代码。

<4> 阻塞( block )：阻塞状态是指线程因为某种原因放弃了 cpu 使用权，也即让出了 cpu timeslice ，暂时停止运行。直到线程进入可运行( runnable )状态，才有 机会再次获得 cpu timeslice 转到运行( running )状态。阻塞的情况分三种：
          (一). 等待阻塞：运行( running )的线程执行 o . wait ()方法， JVM 会把该线程放 入等待队列( waitting queue )中。
          (二). 同步阻塞：运行( running )的线程在获取对象的同步锁时，若该同步锁 被别的线程占用，则 JVM 会把该线程放入锁池( lock pool )中。
          (三). 其他阻塞: 运行( running )的线程执行 Thread . sleep ( long ms )或 t . join ()方法，或者发出了 I / O 请求时， JVM 会把该线程置为阻塞状态。            当 sleep ()状态超时、 join ()等待线程终止或者超时、或者 I / O 处理完毕时，线程重新转入可运行( runnable )状态。
<5> 死亡( dead )：线程 run ()、 main () 方法执行结束，或者因异常退出了 run ()方法，则该线程结束生命周期。死亡的线程不可再次复生。

```




线程优先级
+ 1到10的范围内,默认优先级为5
+ MIN_PRIORITY(表示常数1),MAX_PRIORITY(常熟10),NORM_PRIORITY(常数5)
  ​



四种方式创建线程  
1. 继承Thread类，重写run方法；
2. 实现Runnable接口，重写run方法，但是比继承Thread类好用，实现接口还可以继承类，避免了单继承带来的局限性；
3. 实现callable接口，重写call方法(优点是可以获取返回值)
4. 使用Executor框架实现线程池 
  ​


停止线程的方法
1. 【使用退出的标志】全局变量,用volatie关键字修饰变量(可见性关键,保证了线程正确的读取变量的值)
2. 【使用stop()】 突然立刻停止，强行关闭,已被淘汰
3. 【使用interrupt()】 线程1由于sleep,join等方法进入阻塞状态,其他线程运行中调用线程1的interrup(),则清除线程1的中断状态(isterrupted()-中断标志为false和并当前线程收到InterrruptedException异常)
  ​


线程之间的交互
```
【同步的实现】
【wait() 】在其他线程调用此对象的notify()或者notifyAll()之前,线程等待
【notify() 】随机唤醒此对象监视器上等待的单个线程
【nofifyAll()】唤醒此对象监视器上等待的所有线程
【可见性】一个线程对共享变量值的修改,能够及时被其他线程看到
【共享变量】如果一个变量在多个线程的工作内存中都存在副本,那么这几个变量就是这几个线程的共享变量
```


同步方法和同步代码块的区别
+ 【锁】同步方法默认用this 或者当前类class对象作为锁,同步代码快可以自主选择以什么来加锁(比同步方法更细颗粒度),可以之选会发生同步问题的部分代码,而不是整个方法
+ 【修饰】同步方法用关键字synchronized修饰,同步代码只修饰需要进行同步的代码用`synchronized(object){ 代码.... }`
  ​




在监视器(Monitor)内部,如果和做到线程同步
+ 监视器和锁在Java虚拟机中一块使用,监视器监视一块同步代码块,确保依次只有一个线程执行同步代码快
+ 每一个监视器都和一个对象引用相关联
+ 线程在获取锁之前不运行执行同步代码块
  ​



死锁
+ 是指多个进 程因竞争资源而造成的一种僵局（互相等待），若无外力作用，这些进程都将无法向前推进
+ 【死锁产生的四个必要条件】
  - 互斥条件;
  - .不剥夺条件;
  - 请求和保持条件;
  - 循环等待条件
    ​


如何确保N个线程可以访问N个资源同时不导致死锁
+ 指定获取锁的顺序,并强制线程按照指定顺序获取锁(所有线程以同样顺序加锁和释放锁,就不会造成死锁了)
  ​


 线程的应用实例和场景:
- 【线程池】     实现Thread子类的实例可以执行多个实现了Runnable的线程
- 【共享变量】   关键字volatile声明,所有线程可见,读取最后一次修改
- 【同步关键字】 声明为synchronized
  <br>



Thread(继承父类)和Runnable(实现接口)的区别
+ Runnable方式可以避免Thread方式由于Java单继承特性带来的缺陷
+ Runnable的代码可以被多个线程(Thread实例)共享,适用于多个线程处理“同一资源”的情况 【一个实现Runnable对象,分别构建三个Thread】
  <br>




典型例题
```
<1>java的main线程是不是最后一个退出的线程
           a. JVM会在所有的非守护线程（用户线程）执行完毕后退出；
           b. main线程是用户线程；
           c. 仅有main线程一个用户线程执行完毕，不能决定JVM是否退出，(也即是说main线程并不一定是最后一个退出的线程)

<2>Thread.run(),并没有启动一个新的线程，而只是调用线程中名为run()方法
     Thread.start(),启动线程并调用run()方法


<3> 创建时，定义线程的名字(方便根据名字查找哪个线程正在输出)
    MyRunnable runnable = new MyRunnable();
    Thread thread = new Thread(runnable, "New Thread Name");

<4>得到当先线程的名字(Thread.currentThread() -> 得到当前线程的引用)
    String threadName = Thread.currentThread().getName();

```
<br>




Thread类常用方法
```
【获取线程名称】getName();
【取得当前线程对象】currentThread();
【判断是否启动】isAlive();
【强行运行[其余所有等待]】：join();
【线程休眠】：sleep();
【线程礼让(当前线程释放处理资源)】：yield();
【配套锁】suspend()和resume()配套使用,suspend使线程进入阻塞,并不会自动恢复,必须等其对应的resume被调用时,才能使线程进入可执行状态

注意：方法容易造成死锁,已被标记过时(Thread.suspend行为被打断不会导致InterruptedException)
```
<br>



ThreadLocal类
+ 【用途】用于创建一个线个线程都提供一个变量的副本,为每个线程都维护了自己独有的变量拷贝(重要作用在于数据程本地变量
+ 【原理】采用哈希表的方式来为每的独立,而不是多线程的数据共享)
+ 【优点】保证各个线程间数据安全,每个线程的数据不会被另外线程访问和破坏
+ 【应用场景】数据库连接,既可以保证线程安全,又可以让性能不会太低,缺点是占用较多的空间
  <br>




java创建线程的四种方
+ 实现Runnable接口式
+ 继承Thread类
+ 匿名内部类[继承Thread(有对象 or 无对象)]
+ 匿名内部类[实现Runnable(有对象 or 无对象)]
```
<1>实现Runnable接口
        class R implements Runnable{
            public void run(){
                for(int i = 0; i < 10; i++){
                    System.out.println("R线程 ：疯狂奔跑的皮皮" + i );
                }
            }
        }
        public class test{
            public static void main(String[] args) {
                        Thread t = new Thread(new R());   //新建实例化Thread类,将实现Runable接口的类作为参数传入
                        t.start();
                        for(int i = 0;i < 10;i++){
                            System.out.println("Main线程 :"+"走路的糖糖" + i );
                        }
            }
        }



<2>继承Thread类
        class T extends Thread{
            private String name;
            
            public T(String name){
                this.name = name;
            }
            public void run(){                     //运行
                for(int i = 0;i < 5; i++){
                    System.out.println(name+"线程 :"+"走路的" + i );
                }
            }
        }
        public class test {
            public static void main(String[] args) {
                    T t = new T("皮皮");
                    t.start();
                    T t2 = new T("糖糖");
                    t2.start();
            }
        }
    
        ***************控制台输出**************
            皮皮线程 :走路的0
            糖糖线程 :走路的0
            糖糖线程 :走路的1
            皮皮线程 :走路的1
            皮皮线程 :走路的2
            皮皮线程 :走路的3
            皮皮线程 :走路的4
            糖糖线程 :走路的2
            糖糖线程 :走路的3
            糖糖线程 :走路的4




<3>匿名内部类[继承Thread(有对象 or 无对象)]
        /*有对象*/
        public class test {
            public static void main(String[] args) {
                    Thread t = new Thread("皮皮"){
                        public void run(){                     //运行
                            for(int i = 0;i < 5;i++){
                                System.out.println(this.getName()+"线程 :"+"走路的" + i );
                            }
                        }
                    };
                    t.start();
                    
                    //继续mian线程
                    for(int i = 0;i< 5; i++){
                            System.out.println("糖糖开始奔跑"+i);
                    }
            }
        }   


        /* 无对象*/
        public class test {
            public static void main(String[] args) {
                    new Thread("皮皮"){
                        public void run(){                     //运行
                            for(int i = 0;i < 5;i++){
                                System.out.println(this.getName()+"线程 :"+"走路的" + i );
                            }
                        }
                    }.start();
                    
                    //继续mian线程
                    for(int i = 0;i< 5; i++){
                            System.out.println("糖糖开始奔跑"+i);
                    }
            }
        }


<4>匿名内部类[实现Runnable(有对象 or 无对象)]
        /* 有对象*/
        public class test {
            public static void main(String[] args) {
                    Thread  t = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            for(int i = 0;i < 5;i++){
                                System.out.println("线程 :"+"走路的" + i );
                            }
                        }
                    });
                    t.start();
                    
                    //继续mian线程
                    for(int i = 0;i< 5; i++){
                            System.out.println("糖糖开始奔跑"+i);
                    }
            }
        }


        /* 无对象*/
        public class test {
            public static void main(String[] args) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            for(int i = 0;i < 5;i++){
                                System.out.println("线程 :"+"走路的" + i );
                            }
                        }
                    }).start();
                    
                    //继续mian线程
                    for(int i = 0;i< 5; i++){
                            System.out.println("糖糖开始奔跑"+i);
                    }
            }
        }
```
<br>



java concurrent包下的类
```
【Semaphore】控制某个资源可被同时访问的个数
【ReentrantLock】具有与使用ynchronized方法和语句锁访问的隐式监视器相同的一些基本行为和语义,功能更强大
【Future(接口)】表示异步计算的结果
【CountDownLatch】可以用来在一个线程中等待多个线程完成任务的类
```
<br>



多线程编程常用的交互模型
+ Producer-Consumer模型
+ Read-Writer Lock模型
+ Future模型
+ Woker Thread模型
  <br>



Java5 并发编程工具
+ java.uitl.concurent
+ 线程池ExecutorService
+ Callable 和Futrue
+ BlockingQueue
  ​




Java内存模型(JMM ~ Java Memory Model)
+ 【定义】描述了Java程序中各种变量(线程共享变量)的访问规则,以及在JVM中将变量存储到内存和从内存中取出变量,这样的底层细节
+ 【特点】所有变量都存储在主内存
+ 【特点】每个线程都有自己独立的"工作内存",里面保存该线程使用到的变量的副本(主内存中该变量的一份拷贝)
+ 【规定1】线程对共享变量的所有操作都必须在自己的工作内存中进行,不能直接从主内存中读写
+ 【规定2】不同线程之间无法直接访问其他线程工作内存中的变量,线程间变量值的传递需要通过主内存来完成
```
<1>JMM模型图
	线程1 <---> 工作内存【X的副本1】	<--->       主

	线程2 <---> 工作内存【X的副本2】    <--->       内【共享变量X】

	线程3 <---> 工作内存【X的副本3】	<--->       存


<2>共享变量可见性的原理

	A.原状态
		  线程1   工作内存1(X=0)
							主内存(X=0)
	            线程2   工作内存2(X=0)
	
	B.线程1的X变为1的步骤
	    线程1 --->  工作内存1(X=1) --->
						          主内存(X=1)
	    线程2 <---  工作内存2(X=1) <--- 

```
<br>



重排序
+ 【定义】代码书写顺序与实际执行的顺序不同,指令重排序是编译器or处理器为了提高程序性能而做的优化
+ 【as~if~serial语义】无论如何重排序,程序执行的结果应该与代码顺序执行的结果一致(Java编译器,运行时和处理器都会保证Java在单线程下遵循as~if~serial语义)
+ 【可见性】重排序不会给单线程带来内存可见性问题,但是多线程中程序交错执行时,重排序可能会造成内存可见性问题
+ 树级关系的代码禁止重排序
```
三种重排序
        1.编译器优化的重排序(编译器优化)
        2.指令级并行重排序(处理器优化)
        3.内存系统的重排序(处理器优化)
```
<br>



线程可见性

1. 实现共享变量的可见性,保证两点
2. 导致共享变量在线程间不可见的原因
3. Java"语言层面"支持的可见性实现方式
4. synchronized关键字
5. volatile关键字
6. 保证变量自增(x++)操作的原子性的方式
7. synchronized和volatile比较

```
<1>实现共享变量的可见性,保证两点
	a.线程修改后的共享变量值能够及时从工作内存刷新到主内存中
	b.其他线程能够及时把共享变量的最新值从主内存更新到自己的

<2>导致共享变量在线程间不可见的原因
	a.线程的交叉执行
	b.重排序结合线程交叉执行
	c.共享变量更新后的值,没有在工作内存与主内存间及时更新


<3>Java"语言层面"支持的可见性实现方式
	a.synchronized关键字
	b.volatile关键字

<4>synchronized关键字
	(1)说明
		a.原子性(同步)【能够实现互斥锁,保证任何一时刻都只有1个线程在执行锁内的代码】
		b.可见性
	
	(2)JMM关于synchronized的两条规定
		a.线程解锁前,必须把共享变量的最新值刷新到主内存中
		b.线程加锁时,将清空工作内存中共享变量的值,从而使用共享变量时,需要从主内存中重新读取最新的值【注意:加锁与解锁需要是同一把锁,线程解锁前对共享变量的修改在下次加锁时对其他线程可见】
	
	(3)线程执行互斥代码的过程
		a.获得互斥锁
		b.清空工作内存
		c.从主内存拷贝变量的最新副本到工作内存
		d.执行代码
		e.将更改后的共享变量的值刷新到主内存
		f.释放互斥锁
	
	(4)synchronized解决可见性的问题
		a.保证了锁内部代码的原子性,避免线程在锁内部交叉执行
		b.根据synchronized的两台可见性规范,来实现共享变量的及时更新



<5>volatile关键字
	(1)说明
		a.轻量级的同步锁
		b.能保证可见性
		c.不能保证volatile变量相关操作的原子性
	
	(2)volatile如何实现内存可见性【通过加入内存屏障和静止重排序优化来实现】
		a.对volatile变量执行写操作时,会在写操作后加入一条store屏障指令
		b.对volatile变量执行读操作时,会在读操作前加入一条load屏障指令
		【volatile变量每次被线程访问时,都强迫从主内存中重读该变量的值,而当该变量发生变化时,又会强迫线程将最新的值刷新到主内存,这样在任何时刻,不同的线程总能看到该变量的最新值】
	
	(3)线程写volatile变量的过程
		a.改变线程工作内存中volatile便利nag副本的值
		b.将改变后的副本的值从工作内存刷新到主内存
	
	(4)线程读volatile变量的过程
		a.从主内存中读取volatile变量的最新值到线程的工作内存中
		b.从工作内存中读取volatile变量的副本
	
	(5)【适用场合】要在多线程中安全的使用volatile变量,必须同时满足
		a.对变量的写入操作不依赖当前值
			.不满足: number++,count = count * 5等;
			.满足： boolean变量,记录温度变化的变量等
		b.该变量没有包含在具有其他变量的的不定式中【程序中有多个volatile变量,每个变量的状态应该独立与其他变量】
			.不满足:不定式low < up【例:程序中存在两个volatile变量low和up,这两个直接进行比较】



<6>保证变量自增(x++)操作的原子性的方式
	(1)使用synchronized关键字
		A./*修饰方法体*/
			public synchronized void increate(){}
	
		B./*加锁代码块*/
			private final Ojbect lockObj = new Object();
	
			public void transfer(){
				synchronized(lockObj){
					//获得lockObj对象锁的线程.能够进入这块区域【java的语法保证了同一时间只能有一个线程获得lockObj】
				}
			}
	
	(2)使用ReentrantLock(java.util.concurrent.locaks包下)
		private Lock lock = new ReentrantLock();
		private int number = 0;
	
		public void increase(){
	
			lock.lock();//加锁
			try{
				this.number++;
			}finally{
				lock.unlock();//释放锁
			}
		}
	
	(3)使用AtomicInterger(vava.util.concurrent.atomic包下)



<7>synchronized和volatile比较
	a.volatile不需要加锁,比synchronized更轻量级,不会阻塞线程
	b.从内存可见性角度来说,volatile读相当于加锁,volatile写相当于解锁
	c.synchronized既能保证可见性,又能保证原子性,而volatile只能保证可见性,无法保证原子性
	d.volatile比synchronized更轻量级,但是更多先知,所有没有synchronized使用广泛
	e.【扩展】final也可以保存内存可见性



<8>扩展两个问题
	 (1)问:即时没有保证可见性的措施,为什么很多时候共享变量依然能偶在主内存和工作内存中得到及时的更新?

	 	答:一般只有在短时间内高并发的情况下才会出现变量不能及时更新的情况,因为CPU在执行时会很快的刷新缓存,所以一般情况下很难看到这种问题


	 (2)问:64位(long,double)变量的读写可能不是原子操作如何解决?
	 		【Java内存模型运行JVM将没有被volatile修饰的64为数据类型读写操作分为两次32位的读写操作来进行】
	
	 	答:可能会导致出现读取到"半个变量"的情况
	 		解决方案是:加volatile关键字
```
<br>



扩展定义(自己去了解)
+ 【线程控制逃逸规则】 如果一个资源的创建，使用，销毁都在同一个线程内完成，且永远不会脱离该线程的控制，则该资源的使用就是线程安全的
+ 【争用条件】 多个线程同时共享访同一数据(内存区域)时,每个线程都尝试操作该数据,从而导致数据破外(corrupted)的现象]
+ 【happens-before原则】通过synchronized,volatile,final实现这一原则
+ 【Locks和Condition对象】5.0版本后引入,java锁机制和等待条件的高层实现
+ 【线程安全性】原子性与可见性,通过java.util.concurrent.atomic避免原子性编程的问题,如何使用synchronized和voliatile进行可见性编程,避免发生死锁(DeadLocks)
  <br>






14.Java网络编程(Socket编程)
----------------

参考资料
+ [ TCP/IP 相关知识点](http://www.cnblogs.com/obama/p/3292335.html)

TCP是什么
+ 【定义】TCP/IP是目前世界上应用最为广泛的协议,是以TCP和IP为基础的不同层次上多个协议的集合(也称TCP/IP 协议栈)
+ 【特点】为实现网络中不同计算机之间的通信,每台机器必须有一个唯一的标识(IP地址),目前常用的是IPv4版本(定义IP地址的长度为32位的二进制),端口用于区分不同应用程序,端口号范围为0 ~ 65535(其中0 ~ 1023 为系统保留),IP地址和端口号组成Socket(Socket是网络上运行的程序之间双向通信链路的终结点,是TCP和UDP基础)
  <br>



网络模型5层
5. 【应用层】HTTP,FTP,SMTP
6. 【传输层】TCP/IP协议,UDP
7. 【网络层】 TCMP,ARP,RARP(ARP和RARP工作内容在数据链路层)
8. 【数据链路层】
9. 【物理层】  网线,网卡
  <br>

各种网络协议
+ 【TCP】         传输控制协议
+ 【IP】           互联网协议
+ 【FTP 】        文本传输协议
+ 【SMTP】      简单邮件传输协议
+ 【Telnet】     远程登录协议
+ 【DNS 】       域名解析服务
+ 【TETP】       简单文件传输协议
+ 【HTTP】      超文本传输协议
+ 【HTTPS 】  安全的超文本传输协议
  <br>

网路关键词
+ URL [统一资源定位符,四部分组成“协议”“主机”“端口”“路径”]
  <br>




TCP/IP的三次握手
```
	<1>客户端向服务器发送SYN包(syn =j),进入SYN_SEDN状态(等待服务器确认)
	<2>服务器接收SYN包,确认SYN包,此时syn = j + 1,同时向客户端发送1个SYN包(syn = k)【即是SYN+ACK包】,进入SYN_RECV状态[等待客户端确认]
	<3>客户端接收到SYN+ACK包,向服务器发送ACK确认包,此时客户端+服务器,都进入ESTABLSIHED状态
```



Java提供网络功能的四大类(java.net包下)
```
+ 【InetAddress 】此类物联网协议(IP)地址,用于获取网络上指定机器的IP信息
+ 【URL】 表示统一资源定位符,指向互联网的"资源",可通过openStream()获取指定资源的输入流,通过流读取,访问网络上的数据
+ 【ServerSocket】 TCP服务端
+ 【Socket】 使用TCP协议实现网络通信的Socket相关类
+ 【DatagramPacket】 表示数据包
+ 【DatagramSocket】 进行端到端通信的类
```
<br>


TCP编程
+ TCP协议是面向连接,可靠的,有序的,以字节流的方式发送数据
+ 基于TCP协议实现网络通信的类(服务端的ServerSocket类 和 客户端的Socket类)
  <br>


UDP编程
+ UDP协议(用户数据报协议)是无连接,不可靠的,无序的
+ 以数据报作为数据传输的载体
+ 将传输的数据定义成数据报,在数据报中指明数据所要达到的Socket,然后再将数据报发送出去
  <br>



Socket通信注意
+ 在服务端与多个客户端通信时,注意多线程的优先级[未设置优先级可能会导致运行时速度非常慢,适当将另启的线程优先级降低]
+ 是否关闭输入流和输出流【同一个Socket,直接Socket关闭时,同时会将流关闭(不需要单独去关闭输入输出流)】
+ 使用TCP通信传输对象【对象的形式进行传输,在流中使用ObjectInputStream和ObjectOutputStream进行传输,需要序列化(os.writeObject(user))】
+ socket编程传递文件【IO的知识,通过输入输出流,读取文件的数据,并发送到服务器端】
  <br>




Socket通信实现步骤【TCP编程】

1. 创建ServerSocket和Socket
2. 打开连接到Socket的输入/输出流
3. 按照协议对Socket进行读/写操作
4. 关闭输入输出流,关闭Socket

```
启动步骤:
	先启动服务端
	再启动客户端

服务器端
	a.创建ServerSocket对象，绑定监听端口
	b.通过accept()方法监听客户端请求
	c.连接建立后,通过输入流读取客户端发送的请求信息
	d.通过输出流向客户端发送响应信息

客户端
	a.创建Socket对象,指明需要连接的服务器的地址和端口号
	b.连接建立后,通过输出流向服务器端发送请求信息
	c.通过输入流获取服务器响应的信息
	d.关闭相关资源



服务端
		public static void main(String [] args){
			try{
				ServerSocket serverSocket = new ServerSocket(888);
				Socket socket = serverSocket.accept();
	
				//等待
				InputStrema is = socket.getInputStream();
				InputStreamReader isr = new InputStreamReader(is);
				BufferedReader br = new BufferedReader(isr);
				String info = null;
				while((info = br.readLine()) != null){
					System.out.println(info);
				}
				socket.shudownInput();//关闭输入流
	
				//获取输出流,相应客户端
				OutputStream os = socket.getOutputStream();
				PrintWriter pw = new PrintWrtier(os);
				pw.write("欢迎您！");
				pw.flush();    //刷新缓存[将缓存输出]


				//关闭资源
				pw.close();
				os.close();
				br.close();
				isr.close();
				is.close();
				socket.close();
				serverSocket.close();
	
			}catch(Exception e){
				e.printStrackTrace();
			}
		}

客户端
		public static void main(String [] args){
			try{
				Socket socket = new Socket("localhost",8888);
	
				//发送
				OutputStream os = socket.getOutputStream();
				PrintWriter pw = new PrintWriter(os);
				pw.write("用户名:admin - 密码:123");
				pw.flush();  //调用此步发送信息	
				socket.shutdownOutput();//关闭输出流
	
				//获取输入流,读取服务器端的响应
				InputStream is = socket.getInputStream();
				InputStreamReader isr = new InputStreamReader(is);
				BufferedReader br = new BufferedReader(isr);
				String info = null;
				while((info = br.readLine()) != null){
					System.out.println("这里是客户端,服务器端的响应" + info);
				}


				//关闭资源
				br.close();
				isr.close();
				is.close();
				pw.close();
				os.close();
				socket.close();


			}catch(UnknowHostException e){
				e.printStackTrace();
			}catch(IOException e){
				e.printStackTrace();
			}
		}

```
<br>

---


多线程服务器[TCP编程,实现服务器与多个客户端之间的通信]
```
基本步骤
	a.服务器创建ServerSocket,循环调用accept()等待客户端连接
	b.客户端创建一个socket并请求和服务器端连接
	c.服务器端接受客户端请求,创建socket与客户建立专线连接
	d.建立连接的两个socket在一个单独的线程上对话
	d.服务器端继续等待新的连接
	
		/*一.服务端*/
		public class Server {
			/*main方法*/
			public static void main(String [] args){
				try{
					//A.启动服务端,监听8888端口
					System.out.println("【服务器】");
					ServerSocket serverSocket = new ServerSocket(8888);


					//B.监听8888端口[若有客户端响应,则启动服务器线程处理专线连接Socket]
					int client = 1;
					while(true){
						System.out.println("服务器正在监听8888端口 ---------->");
						Socket socket = serverSocket.accept();
						
						System.out.println("----------Client:"+client + "------------");
						ServerThread sst = new ServerThread(socket);
						Thread thread = new Thread(sst);
						
						thread.start();
						client++;
					}


				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}
	
		/*二.服务器线程*/
		class ServerThread implements Runnable{
			
			private Socket socket;
			
			public ServerThread(Socket socket) {
				this.socket = socket;
			}
			
			/*线程运行*/
			public void run(){
				InputStream is = null;
				InputStreamReader  isr = null;
				BufferedReader br = null;
				OutputStream  os = null;
				PrintWriter pw = null;
				
				try{
					//A.接收客户端信息,接收完后关闭输入流
					 is = socket.getInputStream();
					 isr = new InputStreamReader(is);
					 br = new BufferedReader(isr);
					String info = null;
					while((info = br.readLine()) != null){
						System.out.println(info);
					}			
					socket.shutdownInput();  //关闭输入流
					
					//B.向客户端发送信息
					os = socket.getOutputStream();
					pw = new PrintWriter(os);
					pw.write("来自服务器的信息("+ socket.getLocalAddress() +"):  欢迎登录！");
					pw.flush();


				}catch (IOException e) {
					e.printStackTrace();
				}finally{
					
					//C.关闭资源
					try{
						if(pw != null) pw.close();
						if(os != null) os.close();
						if(br != null) br.close();
						if(isr != null) isr.close();
						if(is != null) is.close();
						if(socket != null) socket.close();
					}catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}

**************************************************************

		/*客户端*/
		public class Client {
			/*main方法*/
			public static void main(String[] args) {
				try{
					
					//A.向服务器发送信息,接收完后关闭输出流
				    System.out.println("【客户端】");
					Socket socket = new Socket("localhost",8888);
					OutputStream os = socket.getOutputStream();
					PrintWriter pw = new PrintWriter(os);
					pw.write("来自客户端的信息(" + socket.getInetAddress() + "): 登录：用户名 suvan - 密码 123");
					pw.flush();
					socket.shutdownOutput(); //关闭输出流
					
					//B.向服务器发送信息
					InputStream is = socket.getInputStream();
					InputStreamReader isr = new InputStreamReader(is);
					BufferedReader br = new BufferedReader(isr);
					String info = null;
					while((info = br.readLine()) != null){
						System.out.println(info);
					}
					
					//C.关闭资源
					br.close();
					is.close();
					is.close();
					pw.close();
					os.close();
					socket.close();
					
				}catch (UnknownHostException e) {
					e.printStackTrace();
				}catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
```
<br>

---

UDP 的 Socket 通信实现步骤【UDP编程】
```
服务器端
	a.创建DatagramSocket,指定端口号
	b.创建DatamPacket
	c.接收客户端发送的数据信息
	d.读取数据

客户端
	a.定义发送信息
	b.创建DatagramPacket(数据报),包含要发送的信息
	c.创建DatagramSocket
	d.发送数据


服务端
	public static void main(String [] args){
		try{
	
			//A.定义服务器
			DatagramSocket socket = new DatagramSocket(8888);
			byte [] data = new byte[1024];//字节数组,指定接收数据报大小
			DatagramPacket packet = new DatagramPacket(data, data.length);
	
			//B.接收到数据报之前一直阻塞
			System.out.println("*****服务器端启动,正在监控8888端口***");
			socket.receive(packet);   
	
			//C.读取数据
			String info = new String(data,0,packet.getLength());
			System.out.println("来自客户端的数据: " + info);


			//D.向客户端发送数据
			InetAddress adress = packet.getAddress();//获取客户端IP地址
			int clientPort = packet.getPort(); //获取客户端端口
			byte [] data2 = "服务器端欢迎您!".getBytes();
	
			DatagramPacket packet2 = new DatagramPacket(data2,data2.length,address,clentPort);
	
			socket.send(packet2);
	
			//E.关闭资源
			socket.close();


		}catch(IOException e){
			e.printStrackTrace();
		}
	}


客户端
	public static void main(String [] args){

		try{
			//A.定义服务器地址,端口号,数据
			InetAddress address = InetAddress.getByName("localhost");
			int serverPort = 8888;
			byte [] data = "用户名: admin - 密码: 123".getBytes();
	
			//B.创建数据报[包含发送的数据信息]
			DatagramPacket packet = new DatagramPacket(data,data.length,address,serverPort);
	
			//C.创建DatagramSocket对象
			DatagramSocket socket = new DatagramSocket();
	
			//D.向服务器发送数据报
			socket.send(packet);


			//E.接收服务器端的响应数据
			byte [] data2 = new byte[1024];
			DatagramPacket packet2 = new DatagramPacket(data2,data2.length);
	
			socket.receive(packet);
	
			String serverInfo = new String(data2,0, packet2.getLength());
			System.out.println("来自服务器端的信息:" + serverInfo);
	
			//F.关闭资源
			socket.close();


		}catch(UnknownHostException e){
				e.printStrackTrace();
		}catch(SocketException e){
			e.printStackTrace();
		}
	
	}

```
<br>

---
<br><br>


15.IO体系构图
----------------------

参考资料：
+ [Java I/O输入输出流详解【大体介绍】](http://www.cnblogs.com/rocomp/p/4783208.html)
+ [Java 编程要点之 I/O 流详解【主要是原理】 ](https://my.oschina.net/waylau/blog/604550)


构图
```
<1>							
					  |		FileInputOut(文件输入流)
					  |		PipedInputOut(管道输入流)			           |          LineNumberInputStream(行号输入流)
					  |		FiterInputOut (缓冲输入流)                          ->|	|          DataInputStream(数据输入流)
		InpputStrema->	  | 	           ByteArrayInputStream(字节数组输入流)	           |	BufferedInputStream(缓冲输入流)
		(字节输入流)  |	  |	            SequenceInputStrema(顺序输入流)		           |	PushbackInputStream(回压输入)
					  |		StringBufferInputStream(缓冲字符输入流)		
					  |		ObjectInputStream(对象输入流)
字节流

					  |		FileOutputStream(文件输出流)
					  | 	           PipedOutputStream(管道输出流)			|	DataOutputStream(数据输出流)
	    OutputStream->              |		FilterOutputStream(过滤器输出流)	          ->         |          BufferedOutputStream(缓冲输出流)
	    (字节输出流)                   |		ByteArrayOutputStream(字节数组输出流)              |	PrintWriter(格式化输出流)	
					  |		ObjectOutStream(数学怒输)				 



	  				  |		BufferedReader(缓冲输入流)			   ->|	LineNumberReader(行号输入流)
	  				  |		CharArrayReader(字符数组输入流)		   
	  	Reader		  |		InputStreamReader(字符输入流)		   ->|	FileReader(文件输入流)
	  	(字符输入流)  ->       |		FilterReader(过滤器输入流)			   ->|  PushbackReader(回压输入流)
	  				  |		PipedReader(管道输入流)				   
	  				  | 	          StringReader(字符输入流)			   

字符流
	  				  |		BufferedWriter(缓冲输出流)
	  				  |		CharArrayWriter(字符数组输出流)
	  	Writer		  | 	           FilterWriter(过滤器输出流)
	  	(字符输出流)    ->     |		OutputStreamWriter(字符输出流)		  ->|	FileWriter(文件输出流)
	  				  |		PipedWriter(管道输出流)				  
	  				  |		StringWriter(字符输出流)
	  				  |		PrintWriter(标准字符输出流)	




<2>输入流基本方法
	int b = in.read();   [读取一个字节无符号填充到int低八位置,-1是EOF(结尾)]
	in.read(byte [] buf); [读取数据填充到字节数组buf]
	int read(byte [] buf, int start,int size); [读取数组填充到字节数组buf,从start位置开始,存放size长度的数据]


<3>输出流基本方法
	out.write(int b);	[写出一个byte到流,b的低八位]
	out.write(byte [] buf); [将buf字节数组都写到流]
	out.write(byte [] buf,int start,int size);



注意：
	键盘是输入流【往记事本写东西,是从键盘中读到数据,写到txt文本文件里】

```
<br>




16.解析Java注解
---------------------
&emsp;Java提供了一种原程序中的元素关联任何信息和任何元数据的途径和方法
<br>

参考资料
+ [注解反射生成SQL语句](http://blog.csdn.net/zen99t/article/details/50351575)
  ​

JDK常见注解
```
<1>JDK自带
	@Override    //表示覆盖父类的方法,重写
	@Deprecated   //表示该方法已经过时
	@Suppvisewarnings//忽略警告
		【例：@SuppressWarnings("deprecation")  忽略deprecation的警告】



<2>第三方注解
			__@Autowired  //自动依赖注入bean
	Spring  __@Service
			__@Repository
	
			__@InsertProvider
	Mybatis __@UpdateProvider
			__@Options
```
<br>


注解的分类
```
<1>按照运行机制区分
	a.源码注解    【只在源码中存在,编译成.class文件就不存在】
	b.编译时注解  【在源码和.class文件中都存在,运行时忽略】
	c.运行时注解  【在运行阶段还起作用,可以通过反射读取,甚至会影响运行逻辑的注解(例:@Autowired)】


<2>按照来源分
	a.JDK的注解
	b.第三方注解
	c.自定义注解

<3>元注解

```
<br>



自定义注解
```
<1>语法要求
		@Target{ElementType.METHOD,ElementType.TYPE}     
		@Retention(RetentionPolicy.RUNTIME)
		@Inherited
		@Documented
		public @interface Description{
			String desc();
			String author();
	
			int age() default 18;
		}
	
			a.使用@interface关键字定义注解
			b.成员以无参无异常方式声明
			c.可以用default为成员指定一个默认值
			d.成员类型是受限的,合法的类型包括原始基本类型以及String,Class,Annotation,Enumeration
			d.如果注解只有一个成员,成员名必须取名为value(),在使用时,可以忽略成员名和赋值号(=)
			e.注解类可以没有成员,没有成员的注解称为"标识注解"


			顶部四行就是元注解
				_.Target作用域
					ElementType.MODEHOD       //方法声明
								.CONSTRUCTOR  //构造方法声明
								.FIELD 		  //字段声明
								.LOCAL_VARIABLE//局部变量声明
								.PACKAGE  	  //包声明
								.PARAMETER    //参数声明
								.TYPE         //类,接口
				_.Retention生命周期
	        	     			RententionPlicy.SOURCE    //源码注解
	        	     						   .CLASS     //编译时注解
	        	     						   .RUNTIME   //运行时注解
	     		            _.@Inherited标识性元注解
	     			           运行子类继承
	     		            _.@Documented
	     			           生成javadoc时会包含注解信息



<2>使用自定义注解【根据元注解的约束使用,定义在方法体上】
		@Description(desc="I am suvan",author="suvan",age=18)
		public String penColor(){
			return "red";
		}
```
<br>


反射解析注解
+ 【概念】通过反射获取类,函数or成员上的"运行时"注解信息,从而实现动态控制程序运行的逻辑
```
<1>通过反射
		public static void main(String [] args){
			try{
				//A.类加载器加载类
				Class c = Class.forName("com.ann.test.Child");
	
				//B.判断是否存在指定注解
				boolean isExist = c.isAnnotationPresent(Description.class);
				if(isExist){
					Description d = (Description)c.getAnnotation(Description.class);//得到注解实例
				}
	
				//C.找到方法上注解
				Method [] ms = c.getMethod();
				Description d =(Description)ms[0].getAnnotation(Description.class);
	
				//C-2
				Annotation [] as = ms[0].getAnnotations();
				for(Annotation a: as){
					if(a instanceof Description){ //注解属于Description类型
						Description d = (Description)a;
					}
				}


			}catch(ClassNotFoundException e){}
		}


《2》

```
<br>

---



17.VM虚拟机【管理内存分区】
------------------------------------

Java内存区域
1. 线程共享区
2. 线程私有区
  <br>


线程共享区
1. 【Java Heap(堆)】虚拟机管理内存中最大一块,所有线程共享,几乎所有对象实例和数组都在这分配内存。垃圾收集器管理主要区域,又被称为"GC堆"
2. 【方法区】储存已经被虚拟机加载的类信息，常量，静态变量，即是编译器后的代码等数据，又称“永久代”
  <br>


线程私有(随线程产生和消亡,因此不需要过多考虑内存回收的问题)
1. 【虚拟机栈 】描述Java方法执行的内存模型，执行Java方法服务
2. 【本地方法栈】使用到本地操作系统(Native)服务
3. 【程序计数器】一块较小内存区域，当前线程所执行的字节码的行号指示器
  <br>


方法调用
+ 方法调用时,会创建栈帧在栈中,调用完是程序自动出栈释放,俄不是gc
  <br>


垃圾回收(Garbage Collection,缩写GC)算法
+ 【标记清除】标记_清除两阶段(缺点：1.效率低 2.产生大量不连续的内存碎片)
+ 【标记整理】清理后后让所有存活的对象一致后移(缺点：标记-清除后需进行对象移动，成本相对较高)
+ 【复制算法】 将可用内存划分为大小相等两块，每次只使用一块,用完将存活对象复制到另一块,然后把之前的一次性清理掉(缺点：1.系统内存折半)适用于新生代(存活对象少,垃圾对象多)
+ 【标记-压缩】清除标记对象时,还将所有存活对象压缩到内存的一端,之后清理边界所有空间(适用于老年代)
  <br>


垃圾收集动作
+ Minor GC [指对新生代的回收]【Java对象大多朝生夕灭,所以Minor GC频繁,回收速度较快】
+ Major GC [年老代的回收]
+ Full GC[对整个堆进行扫描和回收]【值发生在老年代GC,出现了Major G C,经常伴随至少一次Minor GC(并非绝对).MajorGC的速度一般会比Minor GC慢 10倍以上】
  <br>



JVM类加载器(ClassLoader)
+ 【Bootstrap ClassLoader(启动类加载器)】加载JVM自身工作需要的类
+ 【Application ClassLoader(扩展类加载器)】加载ClassPath指定的库类,一般情况下是程序中的默认类下载器
+ 【Extension ClassLoader(应用程序类加载器)】加载“%JAVA_HOME%\lib\ext目录下的库类”
  <br>


JVM加载类的实现方式
+ 【双亲委托模型】如果一个类加载器收到类加载请求,先请求委托给自己的父加载器,每一层的类加载器都是如此,因此所有的类加载请求最终都应该传到顶层BootStrap ClassLoader,只有当父加载器反馈自己无法完成加载请求时,子加载器才会尝试自己加载
+ 【上述模型主要为了解决类载入过程中的安全性问题】如果有人编写了一个java.lang.Object类,借此欺骗JVM,现在要使用自定义ClassLoader来加载该类,但是双亲委托模型不会让它成功,JVM会优先在BootStrap ClassLoader的路径下找到java.lang.Ojbect类,并载入它
  <br>


Java类的加载过程
+ 加载Loading(生成java.lang.Class对象)
+ 验证Verfication
+ 准备Preparation
+ 解析Resolution(1.类方法发生在该过程)
+ 初始化Initialization
+ 使用Using
+ 卸载Unloading
  <br>


JVM内存配置参数
```
-Xmx  最大堆大小
-Xms  初始堆大小【即是最小内存值】
-Xmn  年轻代大小
-XXurvivorRatio  年轻代中"Eden区"与"Survivor"区大小比值【Eden是Survior的倍数】



一般情况将年轻代分为1块Eden和2块Survivor
举例：
        -Xmn=5120m
        -XXurvivorRatio=3
    
        计算Survivor区大小
            Eden:Survivor = 1:3
            年轻代 = Eden + Survivor = 3x + x + x =5120
            可得出x=1024
```
<br>


内存调试工具
+ jmap  [关擦运行中jvm物理内存的占用情况]
+ jstack [Linux特有,观察jvm中所有线程的运行情况和线程当前状态]
+ jconsole [图形化界面,可以观察java进程的gc.class.内存等信息]
  <br>


---


18.了解Java中的内存泄漏
-------------------------
&emsp;指无用对象(不再使用的对象)持续占有内存或无用对象的内存得不到及时释放，从而造成内存空间的浪费。
<br>


Java的内存
+ 【栈内存】主要存放一些基本类型的变量,数组和对象的引用
+ 【堆内存】主要存放一些对象
  <br>



Java中判断内存控件是否符合垃圾收集标准
1. 给对象赋予了控制null,以下再没有调用过
2. 给对象赋予新值,这样冲洗分配了内存空间
  <br>


Java中内存泄漏发生的场景
1. 静态集合类引起的内存泄漏
2. 当集合里面的对象属性被修改时,再调用remove()方法是不起作用【remove不掉,造成内存泄漏
  】
3. 监听器【数据库连接,网络连接，io连接】
4. 内部类和外部模块的引用【内部类没释放 和 A调用B的一个public方法,传入一个对象(这里就标识B模块保持了该对象的引用)】
5. 单例模式【单例对象在初始化后将在JVM整个生命周期存在(以静态变量的方式),如果单例对象持有外部的引用，该独享不能被JVM正常回收】
  <br>

>内存溢出：是指程序所需要的内存超出了系统所能分配的内存(包括动态扩展)的上限

---
<br><br>



19.专业术语
-------------------

术语
+ EJB(Enterprise JavaBean-企业级JavaBean)一个用来构筑企业级应用的服务器端可被管理组件
+ POJO(Plain Ordinary Java Object-普通的JavaBeans)简单的java对象
+ IoC(Inversion of Control-控制反转),是面向对象编程中的一种设计原则，用于代码之间的耦合度【常见方式：依赖注入(Dependency Injection)】
+ AOP(aspect-oriented programming -面向方面程序设计)
+ Framework(架构)
+ ISP(Interface Segregation Principle)面向对象的核心原则,表明使用多个专门的接口比使用单一的接口好
  <br>

面向对象
+ OOA(面向对象分析)
+ OOD(面向对象设计)
+ OOP(面向对象编程)
+ UML(统一建模语言)
  <br>


语言
+ HTML(Hyper Text Markup Language - 超文本标记语言)
+ CSS(Cascade Style Sheet - 层叠样式表)
+ JS(JavaScript - 脚本语言)
+ XML(Extensible Markup Language - 描述结构话数据语言)
+ DOM(Document Object Model - 文档对象模型)
+ JSP(Java Server Pages - Java服务器网页)
+ EL(Expression Language - 表达式)
  <br>

网络
+ HTTP(HyperText Transfer Protocol - 超本文传输协议)
+ C/S和B/S模式(客户机/服务器 和 浏览器/服务器)
  <br>






20.程序设计
-------------------


java自带程序
+ java用来运行一个.class文件
+ javadoc用来生产api文档
+ jar用来生成jar包
+ javadoc用来把.java文件编译成.class文件
  <br>


程序设计中,模块划分的原则是
+ 高内聚,低耦合【模块内具有高内聚度,模块间具有低耦合度】
  <br>


---


21.项目管理
---------------------

&emsp;Ant和Maven都是基于Java构建(build)工具
<br<

Ant特点
+ Ant是软件构建工具
+ 没有一个约定的目录结构【必须明确让ant做什么,什么时候做,编译,打包】
+ 没有生命周期【必须定义目标及其实现的任务序列】
+ 没有集成依赖管理
+ Maven
  <br>

Maven特点
+ 软件项目管理和理解工具
+ 拥有约定【知道代码在哪,放哪去】
+ 拥有生命周期【执行mvn install就可以自动执行编译,测试,打包构建过程,只需定义pox.xml,把源码放到默认目录】
+ 拥有依赖管理,仓库管理
  <br>


---
r>



22.数据结构基础
-----------------


C++ STL(标准模板库)的实现
```
    【vector】底层数据结构为数组,支持快速随机访问,容量大小有限制(扩容耗时)
    【list】底层数据结构为双向链表,支持快速增删
    【deque(双端队列)】底层结构为一个中央控制器和多个缓冲区,支持首尾(中间不能)快速增删,也支持随机访问
    【stack】底层一般用23实现,封闭头部即可
    【queue】底层一般用23实现,封闭头部即可
    【priority_queue(优先队列)】底层数据结构一般为vector底层容器,heap(堆)为处理规则来管理底层容器实现
    【set】底层数据结构为红黑树,有序,不重复,支持快速增删
    【multiset(多重集)】底层数据结构为红黑树,有序,可重复
    【map】底层数据数据结构为红黑树,有序,不重复,支持快速增删
    【multimap(多重映射)】底层数据结构为红黑树,有序,可重复
    【hash_set】底层数据结构为hash表,无序,不重复
    【hash_multiset】底层数据结构为hash表,无序,可重复
    【hash_map】底层数据结构为hash表,无序,不重复
    【hash_multimap】底层数据结构为hash表,无序,可重复

```
<br>



数据的逻辑结构
```
                                    _一般线性表（顺序存储结构和链式存储结构）
                —线性结构  _受限的线性表(栈,队列,串)
                                    _线性表的推广(数组,广义表)
逻辑结构
                                        _集合
                —非线性结构   _树形结构(一般树,二叉树)
                                        _图形结构(有向图,无向图)


注意：
        a.栈是逻辑结构,链表是存储结构

```
<br>


线性结构
+ 【定义】线性结构指的是数据元素之间存在"一对一"线性关系的数据结构
+ 【组成】线性表是具有n个"数据元素"的有限序列(n>0), 线性表由若干个数据元素组成,而数据元素又由若干个数据项组成
+ 【常用的线性结构】线性表,堆,栈,队列,双队列,数组,串,广义表
+ 【常见的非线性结构】二维数组,多维数组 ,树(二叉树等),图
  +【有序表归并最多比较】将两个各有n个元素的有序表归并成一个有序表,其最多的比较次数是2n-1【想象两个序列(135),(2,4,6)的插入,最多需要比较5次】
  +【折半查找计算】设有100个元素的有序表,采用折半查找方法时,成功时候最大的比较次数为7(使用log100,计算100能被2除多少次
  <br>



链表
+ 【定义】链表是线性表的链式存储结构
+ 【定义】链表是一块不连续的动态空间,长度可变;链表需要按顺序检索节点,效率低
+ 【优点】适合随机存取,可以快速插入和删除节点,大小动态分配,长度不固定,不存在越界问题,
+ 【存储单元地址】线性表链式存储结构的所有节点之间的存储单元地址可连续可不连续
+ 【静态链表】用数组实现,静态链表的指针表示下一个元素在数组中的位置,需要分配较大的空间,元素插入与删除时不需要移动元素(需要改的是游标,用空间换取时间)
+ 【前驱和后继节点】前驱(llink,left,pointr),后继(rlink,right,next)
  优点】
+ 【单链表的存储密度】小于1,(存储密度=单链表数据项所占空间/节点所占空间)
+ 【判断链表有没有环】1.直接遍历判断;2.反转指针;3.快慢指针(追赶法)
+ 【链表相交问题】1.有环的单向链表和无环的单向链表不能相交(会被迫存在1个环,起点可能变化);2.两个单向链表之间相交可以存在环,它们的尾节点一定相同
+ 【双向循环链表插入节点】要先入链(先将被插入的节点的前后链接好),后断链(再将原链表的前后关系节点断链,指向新的引用)
+ 【判空条件】1.带头节点单向链表的判空条件是head.next==null(不带头节点是head==nul)；2.带头节点的单向循环链表的判空条件是head.next==head
+ 【二叉链表存储树】(二叉树链表根节点指针)左孩子,右兄弟(指向树的根节点)
  +【二叉链表指针域】在含有n个节点的二叉链表中,有n+1个空链(空指针)域,n-1个非空链域
  <br>



顺序表
+ 【求存储地址】在顺序表中,只要知道"基地址和结点大小",就可在相同时间内求出任一结点的存储地址
+ 线性表的顺序存储结构是一种"随机存取的存储结构"
  <br>

散列表
+ 【定义】是用散列法存储的线性表
  <br>


串
+ 【定义】是一种特殊的线性表,每个节点是一个字符,页可称为字符串
  <br>


栈
+ 【特点】栈可以是顺序存储,也可以是链式存储,与存储结构无关
+ 【深度 or 广度】深度优先遍历用“栈”,广度优先遍历用"队列"
+ 【栈】是数据结构,先进后出(常用于函数调用.例如main中调用各种方法,java虚拟机栈帧),栈是解决封闭对应问题的有效方法,
+ 【遍历】"后序线索树"的遍历仍然需要栈的支持(因为在前序和中序遍历最后访问的都是左or右节点,后序遍历最后访问的是子树的根节点,子树根节点的两个指针域都指向子树了,所以不能空出来存放线索信息,只能借助栈存储)
+ 【判空】`s-top`是栈顶针,空栈为-1,`s->top++`(插入元素),`s->top--`(出栈),`s-top=maxSize-1`(栈满)
+ 【递归】递归工作栈包括(返回地址,本层局部电量和递归调用的形参代换为实参),无论递归过程有没使用局部变量,转换为非递归过程都需要用栈来模拟这个递归调用的过程
+ 【递归】不是所有的递归转换为非递归都是要用到栈。转化为非递归主要有两种方法(1.对于尾递归或单向递归,可以用循环结构算法代替;2.另外一个才是栈的方法)
+ 【元素个数】在栈中,栈顶指针的动态变化决定栈中元素的个数
  <br>



队列
+  【队列】当队列中只有一个元素时，出队后需要清空对头和队尾指针
+  【循环队列】可以顺序存储,也可以链式存储
+  【STL优先级队列】用堆来实现
```
循环队列的计算问题
1.队空条件：rear==front   
2.队满条件：(rear+1) %QueueSIze==front   
3.计算队列长度(队内元素)：（rear-front+QueueSize）%QueueSize   
4.入队：（rear+1）%QueueSize   
5.出队：（front+1）%QueueSize
发
```
<br>



广义表
+【三个特性】1.层次性(广义表元素可以是子表,子表的元素仍然可以是子表);2.共享性(广义表可被其他广义表共享);3.递归性(广义表可以是其自身的一个子表)
+ 【求长度】广义表(((a,b,c),d,e,f))的长度是1【有三对括号】
+ 【表头 or  表尾】一个非空广义表,1.表头(元素or子表);2.表尾(只能是子表,将除了第一个元素之外的所有元素,看作一个广义表)
  +【广义表表达式】广义表A是(d,e,f)的话,head(A)只取d,tail(A)取(e,f)
  <br>



线性结构的比较
+ 【顺序表和链表】顺序表结构适宜进行随机查询(查询快),链表结构适合进行随机存取;(存取快)
+ 【顺序表和链表】顺序表是一种随机存取的线性结构,链表是一种顺序存取的线性结构(因为存储地址不一定连续,即逻辑上位置不一定相邻,只能通过逐个指针顺序进行存储)
+ 【顺序表和链表】顺序表可以用折半查询,链表不可以(因为折半查询要求线性表必须要顺序存储,且要有序)
+ 【顺序栈和链栈】链表栈的比较明显的优势,是通常不会出现栈满的情况(链栈采用非连续的内存存储的模式,意味着可以从未使用的内存中开辟一个空间,很容易添加一个节点，对于连续存储的顺序栈而言,是很难增加大小的)
  <br>



数组
+ 数组的插入,删除需要移动数组元素,平均移动n/2
  <br>


二叉树
<br>


矩阵
+ 【稀疏矩阵压缩的存储方法】是三元组和十字链表
  <br>


图
+ 【无向图存储】邻接矩阵,邻接表,多重邻接表,边集数组
+ 【有向图存储】邻接矩阵,邻接表,十字链表,边集数组
  <br>

---
<br><br>






二.常用操作
=======================================================


1.操作日期和时间
----------------------

常用类
+ java.util.Date【基础日期类】
+ java.util.GregorianCalendar【是一个Calendar(日历)类的具体实现】
  <br>


案例
1.获取当前时间
2.使用SimpleDateFormat格式化日期
3.字符串转换为日期
4.测量指定操作的具体执行时间
5.Calendar获取时间
```
<1>获取当前时间
	Date date = new Date();
	System.out.println("当前时间:" + date.toString());



<2>使用SimpleDateFormat格式化日期
	Date date = new Date();			 
	SimpleDateFormat sdf = new SimpleDateFormat("E yyyy-MM-dd 'at' hh:mm:ss a zzz");
	System.out.println("格式化后日期：  " + sdf.format(date));
			
			输出：  格式化后日期：  星期一 2017-03-20 at 10:54:04 上午 CST
								   【星期     日期         时间   时辰 时区】


					格式简码
						G 	时代指示器 	AD
						y 	四位数年份 	2001
						M 	年中的月份 	July or 07
						d 	月份中日期 	10
						h 	时间 A.M./P.M.(1~12) 	12
						H 	天中的小时 (0~23) 	22
						m 	小时中的分钟 	30
						s 	分钟中的秒钟 	55
						S 	毫秒 	234
						E 	星期中的天 	Tuesday
						D 	年中的天 	360
						F 	月中星期中的天 	2 (second Wed. in July)
						w 	年中的星期 	40
						W 	月中的星期 	1
						a 	A.M./P.M. 标记 	PM
						k 	天中的小时(1~24) 	24
						K 	小时A.M./P.M. (0~11) 	10
						z 	时区 	东部标准时间
						' 	脱离文本 	分隔符
						" 	单引号 	`


<3>字符串转换为日期
		SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd"); 
		String input = "1818-11-11";
		Date t; 
			   
		try { 
			t = ft.parse(input); 
			System.out.println("日期: " +t); 
		} catch (ParseException e) { 
			e.printStackTrace();
		}
	
				输出 : 日期: Wed Nov 11 00:00:00 CST 1818



<4>测量指定操作的具体执行时间
		long start = System.currentTimeMillis( ); //获得自1970年1月1日午夜十二时起已经过的毫秒数
			...
		long end = System.currentTimeMillis( );
		System.out.println("程序执行时间:" + (start -end));


<5>Calendar获取时间
			//1.Calendar是抽象类,需要通过静态方法获取对象
			Calendar c = Calendar.getInstance();
			  //c1.set(2009, 6 - 1, 12);//把Calendar对象c1的年月日分别设这为：2009、6、12


			//2.获取年月日时分秒
			int year = c.get(Calendar.YEAR);
			int month = c.get(Calendar.MONTH);
			int day = c.get(Calendar.DAY_OF_MONTH);
			int hour = c.get(Calendar.HOUR_OF_DAY);
			int minute = c.get(Calendar.MINUTE);
			int second = c.get(Calendar.SECOND);
	
			//3.将Calendar对象转换为Date对象
			Date date = c.getTime();


```
<br>

---



2.自定义异常
----------------------------

关键字
+ throw   (方法体内抛出)
+ throws  (方法定义时抛出)
  <br>


流程
1. 定义一个类继承Exception
2. 根据情况是否添加构造方法
3. 在某个方法抛出该异常
4. 捕获该异常
  <br>



应用场景
+ 异常链 (throws向上层抛出异常....)
  <br>


设计程序时应注意：
+ try块不宜过长(仔细区分每快抛出异常)
+ 保证所有资源都被正确释放 (充分运用finally关键字)
+ catch尽量指定具体的异常类型(减少使用Exception类)
+ 处理异常(不应只是打印输出,可选择(1.处理; 2.上抛; 3.封装异常))
+ 异常处理模块提供适量信息(便于错误的理解和阅读)
+ 不要在finally处理返回值
+ 不要在构造函数抛出异常


自定义异常
```
    class MyException extends Exception{
            public MyException(){ 
                //根据实际情况判断是否需要使用无参构造函数
            }  
            public MyException(String message){
                super();
                System.out.println(message);
            }
        }
        public  class Test {
            public void show(int i) throws MyException{
                if(i == -1){
                    throw new MyException("当前值为-1，抛出异常"); //跳到异常类MyException里的有参构造函数
                }
            }
            public static void main(String[] args) {
                Test t = new Test();
                try{
                     t.show(-1);
                    
                }catch(MyException my){
                    System.out.println("成功捕获MyException异常***************");
                    my.printStackTrace();
                }
            }
        }
```
<br>
---
<br><br>



3.反射操作
----------------------------
&emsp;Java 反射机制可以让我们在编译期(Compile Time)之外的运行期(Runtime)检查类，接口，变量以及方法的信息。反射还可以让我们在运行期实例化对象，调用方法，通过调用 get/set 方法获取变量的值
<br>



Java反射机制提供以下功能
+ 运行时判断任意一个对象所属的类
+ 运行时构造任意一个类的对象
+ 运行时判断任意一个类所具有的成员变量和方法
+ 运行时调用任意一个对象的方法
+ 生成动态代理
  <br>




反射常用类
+ java.lang.class   (类)
+ java.lang.reflect (包)
  <br>



获取Class对象的三种方式
```

Foo foo1 = new Foo()

	<1>方式1
			Class c1 = Foo.class;
	<2>方式2
			Class c2 = foo1.getClass();
	<3>方式3[动态加载类(编译时刻加载是"静态加载类",运行时刻加载是"动态加载类")]
			Class c3 = null;
			try {
				c3 = Class.forName("com.imooc.reflect.Foo");
			} catch (ClassNotFoundException e) {}


            注意:
            	(1)c1 == c2 == c3
            	(2)可通过类的类类型创建该类的实例对象
            		try {
            			Foo foo = (Foo)c1.newInstance();//需要强转
            		} catch (InstantiationException e) {
            			e.printStackTrace();
            		} catch (IllegalAccessException e) {
            			e.printStackTrace();
            		}
            	(3)new创建对象,是"静态加载类",在编译时刻就需要加载所有的可能用到的类【特点：一个出错,全盘出错】
            	   Class.forName()是动态加载类,在运行时刻加载,可通过Class类类型,创建该类对象

```
<br>




反射获取指定对象的各种信息(成员函数,成员变量,构造函数)
```
package com.imooc.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ClassUtil {
	/**
	 * 获取的成员函数对象的信息
	 * @param obj 该对象所属类的信息
	 */
	public static void printClassMethodMessage(Object obj){
		//1.要获取类的信息  首先要获取类的类类型
		Class c = obj.getClass();			//传递的是哪个子类的对象  c就是该子类的类类型
		System.out.println("类名:"+c.getName());


		//2获取Method对象集合
		Method[] ms = c.getMethods();
		for(int i = 0; i < ms.length;i++){
			
			//a,得到方法的名称
			System.out.print("\t方法名-" + ms[i].getName()+"(");
			
			//b.得到方法的返回值类型的类类型
			Class returnType = ms[i].getReturnType();
			System.out.print("返回值类型:" +returnType.getName()+" ");
			
			//c.获取参数类型--->得到的是参数列表的类型的类类型
			Class[] paramTypes = ms[i].getParameterTypes();
			System.out.print("参数类型:");
			for (Class class1 : paramTypes) {
				System.out.print(class1.getName()+",");
			}
			System.out.println(")");
		}
	}
	
	/**
	 * 获取成员变量对象的信息
	 * @param obj
	 */
	public static void printFieldMessage(Object obj) {
	    //1.获取传入对象的类类型
		Class c = obj.getClass();
	
		//2.获取Field对象集合[Field类封装了关于成员变量的操作]
//		Field[] fs = c.getFields();    			 //获取的是所有的public的成员变量的信息
		Field[] fs = c.getDeclaredFields(); //获取的是该类自己声明的成员变量的信息[public,private,protected,default]
		for (Field field : fs) {
			//得到成员变量的类型的类类型
			Class fieldType = field.getType();
			
			String typeName = fieldType.getName();  	//类型名
			String fieldName = field.getName();			//变量名
			System.out.println(typeName+" "+fieldName); //成员变量类型+变量名
		}
	}
	
	/**
	 * 获取构造函数对象的信息
	 * @param obj
	 */
	public static void printConstructorMessage(Object obj){
		  //1.获取传入对象的类类型
		Class c = obj.getClass();


		//2.获取对象构造函数的对象集合【构造函数也是对象】
		//Constructor[] cs = c.getConstructors();				  //获取所有的public的构造函数
		Constructor[] cs = c.getDeclaredConstructors();  //getDeclaredConstructors得到所有的构造函数
		for (Constructor constructor : cs) {
			System.out.print("构造函数名:" + constructor.getName()+"(");
			
			//a.获取构造函数的参数列表,每个参数的类类型
			Class[] paramTypes = constructor.getParameterTypes();
			for (Class class1 : paramTypes) {
				System.out.print(class1.getName()+",");
			}
			System.out.println(")");
		}
	}
}

```
<br>



方法的反射操作
```
package com.imooc.reflect;

import java.lang.reflect.Method;

class A{
	public void print(){
		System.out.println("helloworld");
	}
	public void print(int a,int b){
		System.out.println(a+b);
	}
	public void print(String a,String b){
		System.out.println(a.toUpperCase()+","+b.toLowerCase());
	}
}
public class MethodDemo1 {
	public static void main(String[] args) {
		//1.创建对象,获取类类型[要获取一个方法就是获取类的信息，获取类的信息首先要获取类的类类型]
		A a1 = new A();
		Class c = a1.getClass();


		//2.获取方法 [名称和参数列表][getMethod获取的是public的方法 和 getDelcaredMethod自己声明的方法]
	    try {
	    	
	        System.out.println("==========方法1[无参构造方法]=========");
	        Method m2 = c.getMethod("print");        		
	       // m2.invoke(a1, new Object[]{});
	        m2.invoke(a1);
	    	
	    	System.out.println("==========方法2[有参构造方法(int,int)]=========");
	    	//a.方法的反射操作 
			//Method m =  c.getMethod("print", new Class[]{int.class,int.class});
	    	Method m = c.getMethod("print", int.class,int.class);
	    	//b.传入参数,调用方法
	    	//Object o = m.invoke(a1,new Object[]{10,20});       //方法如果没有返回值返回null,有返回值返回具体的返回值
	    	  Object o = m.invoke(a1, 10,20);
	   
	    	System.out.println("==========方法3[有参构造方法(Stirng,String)]=========");
	        Method m1 = c.getMethod("print",String.class,String.class);
	         o = m1.invoke(a1, "hello","WORLD");
	     
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
}
```
<br>



4.正则表达式
-----------------

java.util.regex包下常用类
+ Pattern类     【正则表达式编译表示】
  + Matcher类	【匹配正则 and 操作结果序列 的工具类】
  + PatternSyntaxException异常【不被检查的异常，用来表示正则表达式的语法错误】
    <br>


正则表达式匹配符号
```
子表达式    匹配对应
    ^   匹配一行的开头
    $   匹配一行的结尾
    .   匹配除了换行符的任何单个字符，也可以利用 m 选项允许它匹配换行符
    [...]   匹配括号内的任意单个字符。
    [^...]  匹配不在括号内的任意单个字符。
    \A  整个字符串的开始
    \z  整个字符串的结束
    \Z  整个字符串的结束，除了最后一行的结束符
    re*     匹配0或者更多的前表达事件
    re+     匹配1个或更多的之前的事件
    re?     匹配0或者1件前表达事件
    re{ n}  匹配特定的n个前表达事件
    re{ n,}     匹配n或者更多的前表达事件
    re{ n, m}   匹配至少n最多m件前表达事件
    a| b    匹配a或者b
    (re)    正则表达式组匹配文本记忆
    (?: re)     没有匹配文本记忆的正则表达式组
    (?> re)     匹配无回溯的独立的模式
    \w  匹配单词字符
    \W  匹配非单词字符
    \s  匹配空格。等价于 [\t\n\r\f]
    \S  匹配非空格
    \d  匹配数字. 等价于 [0-9]
    \D  匹配非数字
    \A  匹配字符串的开始
    \Z  匹配字符串的末尾，如果存在新的一行，则匹配新的一行之前
    \z  匹配字符串的末尾
    \G  匹配上一次匹配结束的地方
    \n  返回参考捕获组号“N”
    \b  不在括号里时匹配单词边界。在括号里时匹配退格键
    \B  匹配非词边界
    \n, \t, etc.    匹配换行符，回车符，制表符，等
    \Q  引用字符的初始，结束于\E
    \E  结束由\Q开始的引用
```
<br>


从给定字符串中找出数字字符串
```
<1>从给定字符串中找出数字字符串
		Pattern r = Pattern.compile("(.*)(\\d+)(.*)");  										//正则表达式
		Matcher m = r.matcher("This order was placed for QT3000! OK?");							//需要匹配字的符串
		if (m.find( )) { 																	    //查找匹配的内容，得到结果序列
			System.out.println("0：" + m.group(0) );										    //查找结果中，指定索引序列的字符串
			System.out.println("1：Found value: " + m.group(1) );
			System.out.println("2： Found value: " + m.group(2) );
			} else {
				System.out.println("NO MATCH");
			}
	
		//替换第一个符合正则的数据
		System.out.println(matcher.replaceFirst("We are the best"));
	
		//替换全部符合正则的数据
		System.out.println(matcher.replaceAll("We are the best"));

```
<br>
---
<br><br>





5.操作I/O输入输出流和文件
-------------------------
&emsp;java.io.* 【各种输入和输出操作 的工具包】




次级目录

1. 文件
2. 标准流[三种标准输入：STDIN,STDOUT,STDERR]
3. 使用RandomAccessFile进行读写(随机读写)
4. 字节流
5. 字符流

<br>




<1>文件
+ File类  [可用户文件(目录)的信息(名称,大小等)]
```
<1>创建目录
		String dirname = "/tmp/user/java/bin";
		File d = new File(dirname);
		d.mkdirs();

<2>遍历目录
		public static void main(String args[]){
		     File file = null;
		     String[] names;
	
		     try{      
		        file = new File("src/test");  //指定路径
	
		        names = file.list();			     //得到指定路径中的 文件和目录名 的集合
		        for(String name : names) {    //遍历
		           System.out.println(name);
		        }
		     }catch(Exception e){
		        e.printStackTrace();
		     }
		}
```

---
<br>




<2>标准流[三种标准输入：STDIN,STDOUT,STDERR]
```
jave提供3种:Standard Input,Standard Output,Standard Error】

		public static void main(String args[]) throws IOException{
			 InputStreamReader isr = null;
	
		     try {
		    	 //A-实例化 【接收用户输入】
		    	 isr = new InputStreamReader(System.in);  			//Standard Input - [System.in]
		        System.out.println("输入 'q' ，回车 -> 退出程序.");
		        
		        //B-do-while循环，判断用户输入，并打印输出
		        char c;
		        do {
		           c = (char) isr.read();
		           System.out.print(c);										//Standard Output - [System.out]
		        } while(c != 'q');
		     }finally {
		    	 //C-关闭流
		        if (isr != null)  isr.close();
		     }
		}
```

---
<br>

<3>使用RandomAccessFile进行读写(随机读写)
+ java文件模型  [在硬盘上的文件是byte,byte,byte存储的,是数据的集合]
+ 打开文件方式  [有两种方式"rw(读写)","r(只读)"]
  + 写方法[raf.write(int),只写一个字节(后8位),同时指针指向下一个位置,准备再次写入]
  + 读方法[int b = raf.read(),读一个字节]
+ 文件读写完成以后一定要关闭流
```
	RandomAccessFile raf = new new RandomAccessFile(file,"rw");
	System.out.println("指针的位置" + raf.getFilePointer());
	
	//a-1.只写了一个字节
	raf.write('A'); 
	raf.write('B'); 
	
	//a.2-写1个int(用write每次只能写一个字节,如果要把i写进去得写次);
	int i = 0x7ffffff;  //16进制数组,代表999
	raf.write((i >>> 24) & 0xFF);  //高8位(右移后把前面的0给去掉,只留后8位)
	raf.write((i >>> 16) & 0xFF);
	raf.write((i >>> 8) & 0xFF);
	raf.write((i >>> 0) & 0xFF);
	
	//a-3直接写入一个int
	raf.writeInt(i); //底层即是如上操作
	
	//a-4写一个中文
	String s = "中";
	byte [] gbk = s.getBytes("gbk");
	raf.write(gbk);


	//b-1.一次性读取[把文件中内容都读到字节数组中]
	raf.seek(0);  //读文件【必须把指针移动到头部】
	byte [] buf = new byte[(int)raf.length()];
	raf.read(buf);
	System.out.println("字节输出测试" + Arrays.toString(buf));


	//b-2.构造成字符串读取
	String s1 = new String(buf);
	System.out.println(s1);
	
	//b-3.以16进制的方式读取
	for(byte b: buf){
		System.out.println(Integer.toHexString(b & 0xff) + " ");
	}
```
<br>




<1>字节流
a.字节单个读取指定文件内容
b.字节流批量读取
c.文件拷贝，字节批量读取
d.进行文件的拷贝，利用带缓冲的字节流
```
package com.imooc.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class IOUtil {
	/**
	 * 读取指定文件内容，按照16进制输出到控制台并且每输出10个byte换行
	 * 【单字节读取不适合大文件，大文件效率很低】
	 * @param 文件名
	 */
	public static void printHex(String fileName)throws IOException{
		//把文件作为字节流进行读操作
		FileInputStream in = new FileInputStream(fileName);
		
		int b ;
		int i = 1;
		while((b = in.read())!=-1){
			//A.将整型转换为16进制标识的字符串
			if(b <= 0xf){ //即表示1位,则单位数前面补0
				System.out.print("0");
			}
			System.out.print(Integer.toHexString(b)+"  ");
			
			//B.每输出10个byte换行
			if(i++%10==0){
				System.out.println();
			}
		}
		
		in.close();
	}
	/**
	 * 字节流批量读取
	 * 【对大文件而言效率高，也是我们最常用的读文件的方式】
	 * @param 文件名
	 */
	public static void printHexByByteArray(String fileName)throws IOException{
		FileInputStream in = new FileInputStream(fileName);
		
		byte[] buf = new byte[8 * 1024];
		/*从in中批量读取字节，放入到buf这个字节数组中，
		 * 从第0个位置开始放，最多放buf.length个 
		 * 返回的是读到的字节的个数
		*/
		  int bytes = 0;
		  int j = 1;
		  while((bytes = in.read(buf,0,buf.length))!=-1){
			  for(int i = 0 ; i < bytes;i++){
				  System.out.print(Integer.toHexString(buf[i] & 0xff)+"  ");
				  if(j++%10==0){
					  System.out.println();
				  }
			  }
		  }
		  
	  in.close();
	}
	
	/**
	 * 文件拷贝，字节批量读取
	 * @param srcFile
	 * @param destFile
	 * @throws IOException
	 */
	public static void copyFile(File srcFile,File destFile)throws IOException{
		//A.判断
		if(!srcFile.exists()){
			throw new IllegalArgumentException("文件:"+srcFile+"不存在");
		}
		if(!srcFile.isFile()){
			throw new IllegalArgumentException(srcFile+"不是文件");
		}
		
		//B.定义
		FileInputStream in = new FileInputStream(srcFile);
		FileOutputStream out = new FileOutputStream(destFile);
		byte[] buf = new byte[8*1024];
		int b ;
		
		//C.读取
	    while((b = in.read(buf,0,buf.length))!=-1){
	    	out.write(buf,0,b);
	    	
	    }
	    
	    //D.关闭
	    out.flush();//刷新流
	    in.close();
	    out.close();
		
	}


	/**
	 * 进行文件的拷贝，利用带缓冲的字节流
	 * @param srcFile
	 * @param destFile
	 * @throws IOException
	 */
	public static void copyFileByBuffer(File srcFile,File destFile)throws IOException{
		//A.判断
		if(!srcFile.exists()){
			throw new IllegalArgumentException("文件:"+srcFile+"不存在");
		}
		if(!srcFile.isFile()){
			throw new IllegalArgumentException(srcFile+"不是文件");
		}
		
		//B.定义
		BufferedInputStream bis = new BufferedInputStream(
				new FileInputStream(srcFile));
		BufferedOutputStream bos = new BufferedOutputStream(
				new FileOutputStream(destFile));
		int c ;
		
		//C.读取
		while((c = bis.read())!=-1){
			bos.write(c);
			
		}
		
		//D.关闭
		bos.flush();//刷新缓冲区
		bis.close();
		bos.close();
	}
	/**
	 * 单字节，不带缓冲进行文件拷贝
	 * @param srcFile
	 * @param destFile
	 * @throws IOException
	 */
	public static void copyFileByByte(File srcFile,File destFile)throws IOException{
		if(!srcFile.exists()){
			throw new IllegalArgumentException("文件:"+srcFile+"不存在");
		}
		if(!srcFile.isFile()){
			throw new IllegalArgumentException(srcFile+"不是文件");
		}
		FileInputStream in = new FileInputStream(srcFile);
		FileOutputStream out = new FileOutputStream(destFile);
		int c ;
		while((c = in.read())!=-1){
			out.write(c);
			out.flush();
		}
		in.close();
		out.close();
	}
}

```

---
<br>




字符流
+ 字符流读写文件
+ 字符流的过滤器[一次读写1行]
```
package com.imooc.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class IOUtil_RW {

	/**
	 * 字符流读写文件
	 * @param readFileName [读取文件]
	 * @param writeFileName [写入文件]
	 * @throws IOException
	 */
	public static void ReaderWriteFile(String readFileName,String writeFileName) throws IOException{
		
		//A.定义
		FileReader fr = new FileReader(readFileName);
		FileWriter fw = new FileWriter(writeFileName);
		
		//B.读写
		char[] buffer = new char[2056];
		int c ;
		while((c = fr.read(buffer,0,buffer.length))!=-1){
			fw.write(buffer,0,c);
			
		}
		
		//C.刷新,关闭流
		fw.flush();
		fr.close();
		fw.close();
	}


	/**
	 * 字符流的过滤器[一次读写1行]
	* @param writeFileName [写入文件]
	* @param writeFileName [写入文件]
	 * @throws IOException
	 */
	public static void BufferReaderWriteFile(String readFileName,String writeFileName)  throws IOException{
		 //对文件进行读写操作 
		BufferedReader br = new BufferedReader(
											new InputStreamReader(
													new FileInputStream(readFileName)));
		BufferedWriter bw = new BufferedWriter(
											new OutputStreamWriter(
													new FileOutputStream(writeFileName)));
		
		//另一种缓冲流[可通过println换行]
//		PrintWriter pw = new PrintWriter("e:\\javaio\\imooc4.txt");
​		
		String line ;
		while((line = br.readLine()) != null){
			System.out.println(line);//一次读一行，并不能识别换行
			bw.write(line);               //写入文件
			bw.newLine();				//单独换行操作【BufferedWriter无法自动识别换行】

//			pw.println(line);
//			pw.flush();
		}

		bw.flush();
		br.close();
		bw.close();
//		pw.close();
	}
}

```

---
<br>

---
<br><br>






6.编写定时任务
-------------------------

常用类
+ Timer 	[定时器工具,用来在一个后台线程计划执行指定任务]
+ TimerTask [抽象类,子类代表可以被Timer计划的任务]
  <br>


schedule() 和 scheduleAtFixedRate()的区别
+ 两个都是Timer类中安排执行定时器任务的方法
+ schedule[保持时间间隔的稳定]【循环时间】
+ scheduleAtFixedRate[保持执行频率的稳定]【循环速率】
  <br>


Timer的缺点
+ Timer内部一个线程,需要等上一个任务执行完,才会执行下一个(某个任务超过预计指定时间)
  +如果TimerTask 抛出 RuntimeException有异常,Timer会终止所有任务的运行
  <br>


用 ScheduledExecutorService类替代 Timer
1. Timer是基于基于绝对时间【对系统时间比较敏感】;ScheduledExecutorService是基于相对时间
2. Timer内部单一线程;ScheduledExecutorService内部是个线程池,可以支持多个任务并发执行
  <br>

案例
```

1. 延迟定时器(简单实现)
2. 指定具体时间执行任务
3. 循环定时器【指定第一次延迟 and 循环间隔】
4. ScheduledExecutorService定时器



<1>延迟定时器(简单实现)
		class Task extends TimerTask{
			public void run(){
				System.out.println("---------- >开始定时器任务！");
				for(int i = 0;i < 5;i++){
					System.out.println("定时器循环：" + i);          //定时器线程奔跑
					try {
						Thread.sleep(100);
					} catch (Exception e) {}
				}
				System.out.println("---------- >定时器执行完毕！");
				this.cancel();
			}
		}
		public  class Test {
			public static void main(String[] args){
				System.out.println("**************2秒后执行定时器任务**************");
				
				Timer timer = new Timer();
				timer.schedule(new Task(), 2*1000); 				  //2秒后执行定时器任务
				
				for(int i = 0;i < 30; i ++){  
					System.out.println("main线程的循环：" + i);  //main线程奔跑
					try {
						Thread.sleep(100);
					} catch (Exception e) {}
				}
				
				System.out.println("**************main线程完毕**********");
				timer.cancel();    												  // 终止此计时器，丢弃所有当前已安排的任务。[不终止的话,定时器线程一直在运行]
			}
		}


<2>指定具体时间执行任务
		//定时器任务
		class Task extends TimerTask{
			public void run(){
				System.out.println("指定时间执行定时器线程任务!");
			}
		}
		public  class Test {
			
			//设置指定时间
			public static Date getTime(){
				//当时间到达 2017-3-26 10:36:40 时就会执行该线程任务(大于该时间(运行前已经超过指定时间),会马上执行)
				Calendar calendar = Calendar.getInstance();
				     calendar.set(Calendar.YEAR, 2017);     				 //年
				     calendar.set(Calendar.MONTH, 3 -1 );  				 //月份【月份需要-1 ,0(1月),2(3月)】
				     calendar.set(Calendar.DAY_OF_MONTH,26);      //天
				     calendar.set(Calendar.HOUR_OF_DAY,10);         //时
					 calendar.set(Calendar.MINUTE, 36);				     //分
					 calendar.set(Calendar.SECOND, 40);					 //秒


				Date date = calendar.getTime();
				return date;
			}
			
			//主方法
			public static void main(String[] args){
					
				//A-获取指定时间
				Date date = Test.getTime();
				
				//B-设置定时器
				Timer timer = new Timer();
				timer.schedule(new Task(), date); //定时器任务 and 指定时间
				
			}
		}



<3>循环定时器【指定第一次延迟 and 循环间隔】
		//定时器任务
		class Task extends TimerTask{
			public void run(){
				Date nowDate = new Date(this.scheduledExecutionTime());  //获取当前时间
				System.out.println("本次执行定时器线程的时间为: "  + nowDate);
			}
		}
		public  class Test {
			
			//主方法
			public static void main(String[] args){


				//B-设置定时器
				Timer timer = new Timer();
				timer.schedule(new Task(), 1*1000,2*1000); //定时器任务 and 1秒后执行 and 每2秒运行一次 【不关闭定时器会一直运行】
				
				try{
					Thread.sleep(10 * 1000);    //main线程休眠10秒
					timer.cancel();  				  //关闭定时器
				}catch(Exception e){}
				
			}
		}


<4>ScheduledExecutorService定时器
		//一.自定义异常类
		class MyException extends Exception{
			public MyException(){
				
			}
			public MyException(String message){
				super(message);
			}
		}
	
		//二.测试定时器服务类
		class TestScheduledExecutorService{
			public ScheduledExecutorService service;   //服务
			public long  start;										//储存开始时间
			
			//构造函数
			public TestScheduledExecutorService(){
				this.service =  Executors.newScheduledThreadPool(2);           // 创建2个线程池，它可安排在给定延迟后运行命令或者定期地执行。
		        this.start = System.currentTimeMillis();                                    //开始时间
			}
			
			//任务1：
			public void taskOne(){
				service.schedule(new Runnable() {
					@Override
					public void run() {
						System.out.println("任务1(timeOne)的执行时间:" + (System.currentTimeMillis() - start));
						try{
							 throw new MyException("这里抛出自定义异常");   //这里抛出异常后仍然会执行任务2(taskTwo)
						}catch (Exception e) {
							e.printStackTrace();
						}
					}
				}, 1*1000, TimeUnit.MILLISECONDS); //延迟1秒后执行 and 时间单位(毫秒)
			}
			//任务2:
			public void taskTwo(){
				service.schedule(new Runnable() {
					@Override
					public void run() {
						System.out.println("任务2(timeTwo)的执行时间:" + (System.currentTimeMillis() - start));
					}
				}, 2* 1000, TimeUnit.MILLISECONDS); //延迟2秒后执行 and 时间单位(毫秒)
										/*
										 * MICROSECONDS    微秒   一百万分之一秒（就是毫秒/1000）
											MILLISECONDS    毫秒   千分之一秒    
											NANOSECONDS   毫微秒  十亿分之一秒（就是微秒/1000）
											SECONDS          秒
											MINUTES     分钟
											HOURS      小时
											DAYS      天
										 */
			}
		}
	
		//三.测试类
		public  class Test {
			
			//主方法
			public static void main(String[] args){
				TestScheduledExecutorService s = new TestScheduledExecutorService();
				s.taskOne();   //执行任务1
				s.taskTwo();  //执行任务2
				s.service.shutdown();  //关闭服务
			}
		}


********************控制台输出*********************
			任务1(timeOne)的执行时间:1001
			任务2(timeTwo)的执行时间:2002
```
<br>
---
<br><br>





7.获取控制台键盘输入
-------------------
案例
```
<1>键盘输入
	    public static void printIO(int way) throws  IOException{ //参数：方式
	        if(way == 1){
	            int i = 0;
	            while(i != -1){
	                i = System.in.read(); //获取输入流,从输入流中读取数据的下一个字节
	                System.out.print((char)i);  //i是字符的ASCII码值,强转为字符输出
	            }
	        }
	        else if(way == 2){
	            byte [] b = new byte[1024];     //数据缓冲
	            int n = System.in.read(b);
	            String s = new String(b,0,n); //将byte数组转为字符串
	            System.out.println(s);
	        }
	        else if(way == 3){
	            Scanner scanner = new Scanner(System.in);
	            while(scanner.hasNext()){
	                System.out.println(scanner.nextLine());
	            }
	        }
	        else if(way == 4){
	            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	            System.out.println(br.readLine());
	        }
	    }


<2>使用Scanner读取
			public static void main(String[] args) throws Exception{

		        Scanner sr=new Scanner(System.in);
		        String str=sr.next(); //next() --Finds and returns the next complete token from this scanner.
		        System.out.println(str);


		        //读取文件输入
		//      Scanner s=null;
		//      try{
		//          s=new Scanner(new BufferedReader(new FileReader("C:\\Users\\Liu-shuwei\\Desktop\\你好.txt")));
		//          s.useDelimiter("jdk");//使用字符串"jdk作为分隔符"
		//          while(s.hasNext()){
		//              System.out.println(s.next());
		//          }
		//      }finally{
		//          if(s!=null) s.close();
		//      }
		 	}
```
<br>
---
<br><br>




8.文档注释【JavaDoc】
---------------------------------
&emsp;Javadoc是JDK附带的一个工具[用来生成从需要预定义格式的文档的Java源代码至HTML格式的Java代码文档]
<br>


标签
@author 		[添加类的作者] 	
@code 			[不把文本转换成 HTML 标记和嵌套的Java标签而用代码字体展示它] 	
@docRoot 		[表示从任何生成页面到生成文档的根目录的相对路径 ]
@deprecated 	[添加一个注释暗示 API 应该不再被使用] 	
@exception 		[用类名和描述文本给生成的文档添加一个副标题] 
@inheritDoc 	[从最近的可继承的类或可实现的接口继承注释] 	
@link 			[用指向特定的包，类或者一个引用类的成员名的文档的可见文本标签插入在线链接] 	
@linkplain 		[和{@link}相同，除了链接的标签用纯文本标示而不是代码字体] 	
@param 			[给“参数”区域添加一个有特定参数名且后跟着特定描述的参数] 
@return 		[添加一个有描述文本的“Returns”区域] 	
@see 			[添加带有链接或者指向引用的文本入口的标题“See] Also” 	
@serial 		[在默认的序列化字段的文本注释中使用] 	
@serialData 	[记录由 writeObject( ) 或 writeExternal( )方法所写的数据 ]	
@serialField 	[记录一个 ObjectStreamField 成分] 	
@since 			[给生成的文档添加一个带有特定 since 文本的"Since"标题] 	
@throws 		[@throw 和 @exception 标签是同义词] 	
@value 			[当{@value}被用在一个静态字段的文本注释中，它展示了那个常量的值] 
@version 		[当 -version 选项被使用时用特定的 version w文本给生成的文本添加一个“Version”副标题]
<br>

案例
```
<1>转换HTML部分 以及文档
		/**
		* <h1>Hello, World!</h1>
		* The HelloWorld program implements an application that
		* simply displays "Hello World!" to the standard output.
		* <p>
		* Giving proper comments in your program makes it more
		* user friendly and it is assumed as a high quality code.
		* 
		*
		* @author  Zara Ali
		* @version 1.0
		* @since   2014-03-31 
		*/
		public class HelloWorld {
		    public static void main(String[] args) {
		        // Prints Hello, World! on standard output.
		        System.out.println("Hello World!");
		    }
		}
	
		********************************************
		JDK运行环境下输入命令：
			javadoc HelloWorld.java
```
<br>
---
<br><br>





9.编码问题
---------------------
&emsp;char型变量是用来存储Unicode编码的字符的，unicode编码字符集中包含了汉字【你一个特殊工艺汉字没有被包含在unicode编码字符集中，就不能用char来储存】
<br>

资源
+ [Unicode编码](https://zh.wikipedia.org/wiki/Unicode)
  <br>


Unicode码
+ 是java默认字符集,占两个字节byte
+ 一个字节=8比特位
+ 每个Unicode占用16比特位
  <br>


ANSI码f
+ 在简体中文的windows系统,ANSI就是GB2312
+ ASCII码是ANSI码的子集
+ 标准的ASCII只使用7个bit
  <br>

中文字符
+ Java语言中,中文字符所占字节取决于字符的编码方式
```
ISO8859-1  1个中文与1个英文字符一样,占1个字节
GB2312 or GBK编码  1个中文字符,占2个字节
UTF-8编码  1个中文字符,占3个字节
```
<br>

```
<1>
	 ASCII：美国标准信息交换码，用一个字节的7位可以表示一个字符
	 ISO8859-1：拉丁码表，西欧标准字符集，用一个字节的8位表示
	 GB2312：中文编码表，用两个字节来表示中文编码
	 GBK：中文编码表的升级，融合了更多表示中文文字符号
	 GB18030：GBK的取代版本
	 BIG-5：同行与港台地区，是繁体字编码方案，俗称“大五码”
	 Uicode:国际标准码，融合了多种文字
	 UTF-8:是Unicode编码的实现方式，最多用三个字节来表示一个字符
	    
	 GBK编码     中文占用2个字节，英文占用1个字节
	 UTF-8编码   中文占用3个字节，英文占用1个字节
	 UTF-16be编码中文占用2个字节，英文占用2个字节
	 Java是双字节编码 utf-16be，即java中每个字符占用两个字节


	//转码
	String.getBytes("要获得字节序列的编码格式");




<2>
		/*测试Unicode*/
		public static void main(String[] args) {
				char c = 'Ǵ';
				System.out.println((char)500);
				
				Unicode: //尝试将int转为char【得到Unicode编码的字符集】
					for(int i = 0;i < 10000; i++){
						System.out.print((char)i + "\t");
						if(i % 10 == 0){
							System.out.println();//换行
						}
					}
		}
```
<br>
---
<br><br>




10.Applet的简单介绍
------------------------
&emsp;一个Applet是运行在网页浏览器上的Java程序

说明:
+ 一个Applet是继承Applet类(java.applet.Applet)的java类
+ main()不在Applet调用[一个Applet不定义main()]
+ Applet程序被设计嵌入HTML页面
+ 用户查看带有Applet的HTML页面,Applet的代码会被下载到用户机器
+ 需要JVM查看[浏览器的插件 or 独立运行环境]
+ 有网页浏览器实施的严格安全规则[安全性被称为"沙箱安全"]
+ Java Archive(JAR)[下载Applet所需要的类]
  <br>

生命周期
+ 初始化[init(),Applet的参数标签被处理后调用]
+ 开始[(start(),浏览器调用init()后自动调用 和 任何返回该applet的页面时候调用]
+ 描述(paint(),执行start()方法后立刻调用)
+ 离开[stop(),离开applet所在页面时自动调用,在同一个Applet能重复调用]
+ 销毁(destroy(),仅正常关闭浏览器时调用)
  <br>
---
<br><br>




11.发送邮件【JavaMail】
--------------------

参考资料:
+ [Java的扩展jar下载](http://www.oracle.com/technetwork/java/index.html)
+ [JavaMail 1.4](http://www.oracle.com/technetwork/java/javamail-1-4-140512.html)
+ [Java Web(十三) 使用javamail进行发送邮件，(使用QQ，163，新浪邮箱服务器)](http://www.cnblogs.com/whgk/p/6506027.html)
+ Java Activation Framework (JAF)
  <br>

Maven依赖
```
	 <!--JavaMail包[发送邮件]-->
	    <dependency>
	      <groupId>javax.mail</groupId>
	      <artifactId>mail</artifactId>
	      <version>1.4.7</version>
	    </dependency>
```
<br>

Email【POJO,储存邮件信息】
```
		package com.blog.extend.javamail;

		import javax.mail.Authenticator;
		import javax.mail.PasswordAuthentication;
		import java.util.Date;
	
		/**
		 *  POJO对象,储存邮箱信息
		 *
		 * @Author Suvan
		 * @Date 2017-05-29-21:17
		 */
		public class Email {
		    private String username;                    //发送者地址
		    private String pensonalName;               //发信人名称[可自定义]
		    private String authorizationCode;        //授权码
		    private String recipients;                 //收件人
		    private String subject;                    //主题
		    private String text;                       //文本内容
		    private String content;                    //HTML内容
		    private Date sendDate;                     //发送日期
		    private String propertiesWay;               //连接方式[163和qq邮箱都是默认,gmail可选择SSL或者TSL]
	
		    //授权信息
		    private Authenticator authenticator = new Authenticator() {
		        @Override
		        protected PasswordAuthentication getPasswordAuthentication() {
		            //填写自己163邮箱的登录账户和授权密码
		            return new PasswordAuthentication(username, authorizationCode);
		        }
		    };


		    //Getter
		    public String getUsername() {
		        return username;
		    }
	
		    public String getAuthorizationCode() {
		        return authorizationCode;
		    }
	
		    public String getRecipients() {
		        return recipients;
		    }
	
		    public String getSubject() {
		        return subject;
		    }
	
		    public String getText() {
		        return text;
		    }
	
		    public String getContent() {
		        return content;
		    }
	
		    public Date getSendDate() {
		        return sendDate;
		    }
	
		    public Authenticator getAuthenticator() {
		        return authenticator;
		    }
	
		    public String getPropertiesWay() {
		        return propertiesWay;
		    }
	
		    public String getPensonalName() {
		        return pensonalName;
		    }
	
		    //Setter
		    public void setUsername(String username) {
		        this.username = username;
		    }
	
		    public void setAuthorizationCode(String authorizationCode) {
		        this.authorizationCode = authorizationCode;
		    }
	
		    public void setRecipients(String recipients) {
		        this.recipients = recipients;
		    }
	
		    public void setSubject(String subject) {
		        this.subject = subject;
		    }
	
		    public void setText(String text) {
		        this.text = text;
		    }
	
		    public void setContent(String content) {
		        this.content = content;
		    }
	
		    public void setSendDate(Date sendDate) {
		        this.sendDate = sendDate;
		    }
	
		    public void setAuthenticator(Authenticator authenticator) {
		        this.authenticator = authenticator;
		    }
	
		    public void setPropertiesWay(String propertiesWay) {
		        this.propertiesWay = propertiesWay;
		    }
	
		    public void setPensonalName(String pensonalName) {
		        this.pensonalName = pensonalName;
		    }
		}
```
<br>

SendEmail【发送邮件功能】
```
		package com.blog.extend.javamail;


		import com.sun.mail.util.MailSSLSocketFactory;

		import javax.mail.MessagingException;
		import javax.mail.NoSuchProviderException;
		import javax.mail.Session;
		import javax.mail.Transport;
		import javax.mail.internet.InternetAddress;
		import javax.mail.internet.MimeMessage;
		import java.io.UnsupportedEncodingException;
		import java.security.GeneralSecurityException;
		import java.util.Date;
		import java.util.Properties;


		/**
		 * 使用javaMail发送邮件【163邮箱,qq邮箱,gmail邮箱】
		 *
		 * @Author Suvan
		 * @Date 2017-05-29-20:40
		 */
		public class SendEmail {
		    /*个人邮箱*/
		    public Properties getProperties_liushuwei(){
		        Properties props = new Properties();
	
		        props.setProperty("mail.host","mail.liushuwei.cn");      //网易smtp邮箱服务器
		        return props;
		    }
	
		    /*网易邮箱连接参数【需要开启：POP3/SMTP服务 】*/
		    public Properties getProperties_163(){
		        Properties props = new Properties();
		            props.setProperty("mail.smtp.auth", "true");        //开启权限验证
		            props.setProperty("mail.host","smtp.163.com");      //网易smtp邮箱服务器
	
		        return props;
		    }
	
		    /*QQ邮箱连接参数【需要开启：POP3/SMTP服务 】*/
		    public Properties getProperties_qq(){
		        Properties props = new Properties();
		            props.setProperty("mail.smtp.auth", "true");        //开启权限验证
		            props.setProperty("mail.host","smtp.qq.com");      //qq的smtp邮箱服务器
		            props.setProperty("mail.transport.protocol", "smtp");
	
		        //QQ邮箱的SSL加密
		        MailSSLSocketFactory sf = null;
		        try{
		            sf = new MailSSLSocketFactory();
		            sf.setTrustAllHosts(true);
		        }catch (GeneralSecurityException e){
		            e.printStackTrace();
		        }
	
		        props.put("mail.smtp.ssl.enable","true");
		        props.put("mail.smtp.ssl.socketFactory",sf);


		        return props;
		    }
	
		    /*谷歌邮箱连接参数【需要开启：允许不够安全的应用】*/
		    public Properties getProperties_gmail(String way){
	
		        Properties props = new Properties();
		        if("SSL".equals(way)){
		            //1.SSL方式
		            final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
		            props.put("mail.debug", "true");
		            props.put("mail.smtp.host", "smtp.gmail.com");
		            props.put("mail.smtp.ssl.enable", "true");
		            props.put("mail.smtp.socketFactory.class", SSL_FACTORY);
		            props.put("mail.smtp.port", "465");
		            props.put("mail.smtp.socketFactory.port", "465");
		            props.put("mail.smtp.socketFactory.fallback", "false");
		            props.put("mail.smtp.auth", "true");
		        }else{
		            //2.TLS方式
		            props.put("mail.smtp.auth", "true");
		            props.put("mail.smtp.starttls.enable", "true");
		            props.put("mail.smtp.host", "smtp.gmail.com");
		            props.put("mail.smtp.port", "587");
		        }
	
		        return props;
		    }


		    /*发送邮件*/
		    public void GO(Email email){  //Email对象
		        try {
		            //A-构建连接参数
		            String username = email.getUsername();
		            String emailServer = username.substring(username.indexOf("@")+1,username.lastIndexOf("."));
		            Properties props = null;
		            if("163".equals(emailServer)){
		                props = getProperties_163();
		            }else if("qq".equals(emailServer)){
		                props = getProperties_qq();
		            }else if("gmail".equals(emailServer)){
		                props = getProperties_gmail("SSL");
		            }else if("liushuwei".equals(emailServer)){
		                props = getProperties_liushuwei();
		            }


		            //B.获取连接【获取默认会话对】
		            Session mailSession = Session.getInstance(props, email.getAuthenticator()); //连接参数,授权信息
	
		            //B-创建消息【获取MimeMessage对象】
		            MimeMessage  msg = new MimeMessage(mailSession);
	
		            //B-2.发件邮件 + 发信名称[没设置的话默认为空]
		            String personalName =  email.getPensonalName();
		            if (personalName != null){
		                try{
		                    msg.setFrom(new InternetAddress(email.getUsername(),personalName));
		                }catch (UnsupportedEncodingException ue){ue.printStackTrace();}
		            }else{
		                msg.setFrom(new InternetAddress(email.getUsername())); //默认发信名称为：邮箱地址
		            }



		            /*B-3.收件人
		             *    参数1：
		             *      Message.RecipientType.TO  代表收件人(主要收件,能知道抄送给谁,不知道暗送)
		             *      Message.RecipientType.CC  抄送
		             *      Message.RecipientType.BCC 暗送
		             *
		             *    参数2：
		             *      收件人地址 or 抄送or密送名单【群发-Address[]】
		             */
	
		            msg.setRecipient(MimeMessage.RecipientType.TO,
		                             new InternetAddress(email.getRecipients()));
	
		            //B-4.日期,主题,正文
		            Date date = email.getSendDate();
		            String text = email.getText();
		            String content = email.getContent();
	
		            if(date != null){
		                msg.setSentDate(date);                                                 //日期
		            }
		            msg.setSubject(email.getSubject(), "UTF-8");                       //主题
		            if(text != null){
		                msg.setText(text, "UTF-8");                                    //文本内容
		            }else if(content != null){
		                msg.setContent(content,"text/html;charset=UTF-8");                       //HTML内容
		            }
	
		            msg.saveChanges();//保存更改
	
		            //C-发送邮件
		            Transport.send(msg);
	
		            System.out.println("邮件发送成功........!");
	
		        } catch (NoSuchProviderException e) {
		            e.printStackTrace();
		        } catch (MessagingException e) {
		            e.printStackTrace();
		        }
		    }
	
		    //public static void main(String[] args) {
		        //String contentHTML = "<head>" +
		        //        "<title>注册验证码</title>" +
		        //        "</head>" +
		        //        "<body>" +
		        //        "<h1>您好,注册码是:" + 213123 + ",欢迎您注册博客</h1>" +
		        //        "</body>";
		        //Email email = new Email();
		        //    email.setUsername("13202405189@163.com");
		        //    email.setAuthorizationCode("sendemail123");
		        //    email.setRecipients("526097449@qq.com");
		        //    email.setSubject("第一篇");
		        //    email.setContent(contentHTML);
	
		        //Email email = new Email();
		        //    email.setUsername("526097449@qq.com");
		        //    email.setAuthorizationCode("qq邮箱授权码");
		        //    email.setRecipients("13202405189@163.com");
		        //    email.setSubject("第一篇");
		        //    email.setText("123456");
		        //
		        //Email email = new Email();
		        //email.setUsername("liushuwei0925@gmail.com");
		        //email.setAuthorizationCode("谷歌邮箱密码");
		        //email.setRecipients("13202405189@163.com");
		        //email.setSubject("第二篇");
		        //email.setText("asdfasdf");
		        //
		        //Email email = new Email();
		        //email.setUsername("robot@liushuwei.cn");
		        //email.setPensonalName("博客机器人");
		        //email.setAuthorizationCode("密码");
		        //email.setRecipients("526097449@qq.com");
		        //email.setSubject("第二篇");
		        //email.setText("asdfasdf");
		        //SendEmail sendEmail  = new SendEmail();
		        //sendEmail.GO(email);
		    //}
		}
```
<br>
---
<br><br>



12.GUI开发
------------------------
&emsp;学习使用java开发GUI,目前是为了BatchCreate_file4.0开发UI界面

成熟的框架
1. Swing
2. SWT
3. JFace
4. Flex
5. Eclipse RCP
  <br>

资源
+ [swing入门教程](http://www.blogjava.net/jerry-zhaoj/articles/283170.html)
+ [易百-Swing教程](http://www.yiibai.com/swing/home.html)
+ [http://www.cnblogs.com/HJL085/p/5905427.html](http://www.cnblogs.com/HJL085/p/5905427.html)
+ [Java Swing中弹出对话框的几种方式](http://blog.csdn.net/zhao50632/article/details/20999173)
+ [JProgressBar的用法-大牛的思路，有点6](http://www.cnblogs.com/happyPawpaw/archive/2013/03/27/2984750.html)
+ [JProgressBar的api中文版](http://www.apihome.cn/api/java/JProgressBar.html)
+ [JOptionPane类提示框的一些常用的方法](http://847353020-qq-com.iteye.com/blog/954532)
  <br>


获取鼠标位于屏幕坐标的小工具【2017.1.2】
```

/*
 * UI组件
 * JComboBox【组合框】
 * JPasswordFeid【密码文本】
 * JCheckBox【多选-可随意取消】  JRadioButton【单选-唯一和强制必选一】
 * JMenu，JMenuItem【选中时出发】,JMenuBar【菜单系统的基础】
* JSlider【提供文本字段，允许用户输入值,可视化形式获取当前选择的反馈，还能设置接收的值的范围】
* JSpinner【与JSlider类似，允许用户在任意组的值(日期,名称,颜色，任何事)进行选择】
* JToolBar【调色板(充当其他组件)】
* JToolTip【将鼠标停留在某个位置上面，弹出小"泡泡"提示】
* JOptionPane【为开发人员提供了获取和接收简易消息的方法类似"快捷方式"】
* JTextArea【支持多行文本(JTextField局限在在一行文本)】
* JScrollPane【提供处理所有与滚动跳相关的动作(一旦添加它，就会自动处理每件事，包括在需要的时候隐藏/显示滚动条)】
* JList【向用户呈现许多选择(在一列中显示数据)】
* JTabel【类似Excel工作表(在多列中显示数据)】
* JTree【数据采用树结构形式,要求数据模型】
* ​
* 布局
* FlowLayout【流动布局，从左到右安排组件，空间不足，移动到下一行】
* GridLayout【网格布局,指定行列，把组件放进单元格】
* BorderLayout【边界布局，东南西北中的概念在屏幕上放置组件】
* GrodBagLayout【更高级的布局管理器 】
* ​
* 模型
* Collection【Java集合，用于处理数据】
* ​
* 事件
* 登记监听器【listener实现listener接口】
* 事件
  */


package mouse;

import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.plaf.basic.BasicButtonUI;

/**更新时间： 2017年1月2日   
 *    小工具
 *    实时获取鼠标在屏幕的XY坐标
    *
 *  @author Suvan
    */
    public class GetScreen_XY extends JFrame implements MouseMotionListener{

    private  JLabel X_label;        //固定标签
    private  JLabel Y_label;        //固定标签

    private JLabel X;           
    private JLabel Y;                   

    private JPanel panel;           //背景面板
    private JButton button;     //关闭按钮

    //无参构造方法
    public GetScreen_XY (){
        super();

        //A-设置窗体
        this.getContentPane().setLayout(null);
        this.setSize(200,150);  
        this.setUndecorated(true);          //去除最外面边框【所有】
        this.setOpacity(1.0f);                  //设置窗体透明度
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    //关闭窗体,则退出程序结束进程
        
        //B-初始化组件
        load();
        
        //C-注册鼠标移动事件
        addMouseMotionListener(this);//注册鼠标移动事件

    }


    //方法1：初始化，构造组件
    private void load(){
        //A-面板
        if(panel==null){        
            panel =new JPanel();
            panel.setLayout(null);
            panel.setSize(200, 150);
            panel.setBackground(Color.WHITE);
        }
        this.add(panel);
        
        //B-关闭按钮
        if(button==null){
            button = new JButton();
            button.setBounds(170, 0, 30, 30);  
            button.setUI(new BasicButtonUI());                                  //恢复基本的视觉效果
            button.setMargin(new Insets(0, 0, 0, 0));                               // 按钮内容与边框举例【上-左-下-右】
            button.setIcon(new ImageIcon("img/closeButton.png"));
            button.setContentAreaFilled(false);                                     //不绘制按钮区域
            button.setBorderPainted(false);                                         //不绘制边框
            button.setBackground(Color.white);                                  //设置背景色
        }
        button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                System.exit(0); //终止正在运行的Java虚拟机【Sstem.exit(status)不管status(退出状态)为何值都会退出程序 】
            }
        });
        panel.add(button);
        
        //C-显示X,Y坐标
        Font font = new Font("幼圆",Font.PLAIN,20);   //字体，字样，大小
        Font font2=new Font("幼圆",Font.BOLD,30);
        if(X_label==null){
            X_label=new JLabel();
            X_label.setText("X坐标");
            X_label.setFont(font);
            X_label.setBounds(15, 40, 80, 20);//设置位置
        }
        if(Y_label==null){
            Y_label=new JLabel();
            Y_label.setText("Y坐标");
            Y_label.setFont(font);
            Y_label.setBounds(110,40,80, 20);
        }
        if(X==null){
            X = new JLabel();
            X.setFont(font2);
            X.setForeground(Color.RED); //设置字体颜色
            X.setBounds(15, 70, 80, 20);
        }
        if(Y==null){
            Y = new JLabel();
            Y.setFont(font2);
            Y.setForeground(Color.RED);
            Y.setBounds(110, 70, 80, 20);
        }
        
        //将各个组件添加进面板
        panel.add(X_label);         panel.add(Y_label);
        panel.add(X);               panel.add(Y);
    }
    
    @Override
    public void mouseDragged(MouseEvent e) {
    
    }
    
    @Override
    public void mouseMoved(MouseEvent e) {
            PointerInfo pi =java.awt.MouseInfo.getPointerInfo(); //可获取系统鼠标的屏幕坐标
            Point p = pi.getLocation();
    //          System.out.println("X坐标-"+p.x+"-------Y坐标:"+p.y);
    //          X.setText(p.x+"");
    //          Y.setText(p.y+"");
    }
    
    //测试主方法
    public static void main(String[] args) {
    ​    
        GetScreen_XY u = new GetScreen_XY();
        u.setVisible(true);
        
        //定时器
        Timer timer = new Timer();
        timer.schedule(new TimerTask(){
            public void run(){
                //获取鼠标在系统屏幕的坐标
                Point point =java.awt.MouseInfo.getPointerInfo().getLocation();
                u.X.setText(point.x+"");
                u.Y.setText(point.y+"");
            }
        },10,20);//10毫秒后开始，然后固定重复20毫秒执行一次
    
    }

}
```
<br>
---
<br><br>



13.自动化操作【模拟键盘和鼠标】
----------------------------
&emsp;突发奇想，能否用用java实现模拟用户点击来操作《QQ三国》游戏的任务，实现自动刷怪[暂时未实现,QQSG无法响应]

参考资料:
+ [中文API](http://www.apihome.cn/api/java/Robot.html)
+ [java Robot 类模拟键盘按键和鼠标点击（全局模拟） ](http://blog.csdn.net/scholar_man/article/details/48035251)
+ [键盘常用ASCII码 & Ctrl组合键](http://blog.chinaunix.net/uid-25063573-id-2420369.html)
+ [java.awt.event.KeyEvent(键盘按键对应的KeyCode)](http://blog.csdn.net/changqing5818/article/details/49471227)


扩展框架
+ [SWT WIN32 Extension扩展包下载](http://download.csdn.net/detail/ysjian_pingcx/6510391)
+ [Java 实现全局（全操作系统）的键盘鼠标模拟 ](http://blog.csdn.net/linzhanggeorge/article/details/4419860)
+ [Java JFrame实现无边框无标题](http://blog.csdn.net/qiantujava/article/details/10060847)
+ [java实现获取鼠标在屏幕上的坐标](http://blog.csdn.net/code_better/article/details/53505962)
+ [jNative.jar包下载](http://download.csdn.net/detail/a491857321/9531473)
  <br>


案例目录
1. 自动启动qq,网易云和eclipse【2016.12.29】
2. 自动编写HelloWorld【2017.1.6】
3. java版拜年(新年快乐！)【2017.1.26】


<1>自动启动qq,网易云和eclipse【2016.12.29】
```
import java.awt.Dimension;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class startWork1 {
        private static Robot r; 

        //静态块赋初始值
        static{
            try{
                r=new Robot();
            }catch(Exception e){};
        }


        //流程1：开启qq
        public static void startQQ(){
            
            //1秒后开始
            r.delay(1000);
            returnDesktop();//回到桌面
    
            System.out.println("组合键:window+R......");
            r.keyPress(KeyEvent.VK_WINDOWS);            
            r.keyPress(KeyEvent.VK_R); 
            r.keyRelease(KeyEvent.VK_WINDOWS);
            r.keyRelease(KeyEvent.VK_R);            
            //启动QQ
            key(KeyEvent.VK_DELETE);    //删除键
            key(KeyEvent.VK_Q);
            key(KeyEvent.VK_Q);
            key(KeyEvent.VK_ENTER);
            key(KeyEvent.VK_ENTER); //敲两次回车，防止输入法
            
            //等待2s后，回车登录
            r.delay(2000);          
            key(KeyEvent.VK_ENTER);
            
            System.out.println("***************QQ启动完毕***************");
        }


        //流程2:开启网易云
        private static void startCloudmusic(){          
            //5秒后开始操作(等QQ登后)
            r.delay(5000);
            
            //【组合】window+R键         System.out.println("组合键:window+R......");
            r.keyPress(KeyEvent.VK_WINDOWS);            
            r.keyPress(KeyEvent.VK_R); 
            r.keyRelease(KeyEvent.VK_WINDOWS);
            r.keyRelease(KeyEvent.VK_R);
            
            //启动网易云音乐
            System.out.println("输入cloudmusic,按回车......");
            key(KeyEvent.VK_DELETE);
            int [] keys={KeyEvent.VK_C,KeyEvent.VK_L,KeyEvent.VK_O,KeyEvent.VK_U,KeyEvent.VK_D,KeyEvent.VK_M,KeyEvent.VK_U,KeyEvent.VK_S,KeyEvent.VK_I,KeyEvent.VK_C};
            keys(keys);//连续输入cloudmusic
            key(KeyEvent.VK_ENTER);         
            key(KeyEvent.VK_ENTER);         //回车两次
            r.delay(2000);//等待2秒启动易云
            
            //点击发现音乐
            System.out.println("左边栏发现音乐......");
            r.mouseMove(546,295);
            mouse(InputEvent.BUTTON1_MASK);
            r.delay(500);
            
            //点击每日歌曲推荐
            System.out.println("每日歌曲推荐......");
            r.mouseMove(700, 630);
            mouse(InputEvent.BUTTON1_MASK);
            r.delay(500);
            
            //点击播放全部
            System.out.println("播放全部......");
            r.mouseMove(700,415);
            mouse(InputEvent.BUTTON1_MASK);
            r.delay(500);
            
            //最小化               
            r.mouseMove(1401,230);
            mouse(InputEvent.BUTTON1_MASK);
            r.delay(200);       
            System.out.println("***************网易云音乐启动完毕***************");
        }
    
        //流程3：开启eclipse
        private static void startEclipse(){
            //获取屏幕分辨率
            Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
            System.out.println("当前屏幕分辨率: "+d);
            
            //2秒后开始操作
            r.delay(2000);
            
            //【组合】window+R键
            System.out.println("组合键:window+R......");
            r.keyPress(KeyEvent.VK_WINDOWS); 
            r.keyPress(KeyEvent.VK_R); 
            r.keyRelease(KeyEvent.VK_WINDOWS);
            r.keyRelease(KeyEvent.VK_R);
            
            //输入ec，然后回车
            System.out.println("输入ec,按回车......");
            key(KeyEvent.VK_DELETE);
            key(KeyEvent.VK_E);
            key(KeyEvent.VK_C);
            key(KeyEvent.VK_ENTER);
            key(KeyEvent.VK_ENTER);
            r.delay(3000);//等待3秒加载eclise开头动画
            
            //移动到选择下拉框
             System.out.println("鼠标移动到下拉框......");
             r.mouseMove(1119,441); 
             mouse(InputEvent.BUTTON1_MASK);                 
             r.delay(100);
             
           //移动到【E:\javaweb_WorkSpace】工作站选项
             System.out.println("选择javaweb_WokSpace工作站......");
             r.mouseMove(1119,480);     //移动到【E:\javaweb_WorkSpace】工作站选项
             mouse(InputEvent.BUTTON1_MASK);
               
            //移动到确定，开启eclipse
             System.out.println("点击确定,开启eclipse......");
            r.mouseMove(1121,620);
            mouse(InputEvent.BUTTON1_MASK);
            
            System.out.println("等待。。。");
            //开启eclise,7秒启动
            r.delay(7000);
    
            //【全屏操作】
            System.out.println("eclipse全屏......");
            MAX_SHOW();
            System.out.println("***************Eclipse启动完毕***************");


​            
        }

        //工具1：键盘输入
        public static void key(int keyValue){
            r.keyPress(keyValue); 
            r.keyRelease(keyValue);
            r.delay(300);
        }
        //工具1-2：键盘连续输入
        public static void keys(int [] keysValue){
            for(int i=0;i<keysValue.length;i++){
                r.keyPress(keysValue[i]); 
                r.keyRelease(keysValue[i]);
                r.delay(200);
            }
        }
        //工具2：鼠标点击 
        public static void mouse(int mouseValue){
            r.mousePress(mouseValue);
            r.mouseRelease(mouseValue);
            r.delay(500);
        }       
        //工具3：最大化
        public static void MAX_SHOW(){
            r.keyPress(KeyEvent.VK_ALT); 
            r.keyPress(KeyEvent.VK_SPACE); 
            r.keyRelease(KeyEvent.VK_ALT);
            r.keyRelease(KeyEvent.VK_SPACE);            r.delay(500);
            key(KeyEvent.VK_X);
        }
        //工具4：回到桌面
        public static void returnDesktop(){
            //组合键WIN+D回到桌面
            r.keyPress(KeyEvent.VK_WINDOWS);            
            r.keyPress(KeyEvent.VK_D); 
            r.keyRelease(KeyEvent.VK_WINDOWS);
            r.keyRelease(KeyEvent.VK_D);    
            r.delay(500);
        }


​        
​    
​        
        public static void main(String []args){
            try{
                startQQ();
                startCloudmusic();  
                startEclipse();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
}
```

---
<br>

<2>自动编写HelloWorld【2017.1.2】
```
package start;

import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

/**更新时间： 2017年1月26日   
 *    机器人编写Hello World!
    *
 *  @author Suvan
    */
    public class HelloWorld {
    private static Robot r;	
    private  int X;	//X和Y的坐标
    private  int Y;
    //静态块赋初始值
    static{
    	try{
    		r=new Robot();
    	}catch(Exception e){};
    }

    //无参构造方法
    public HelloWorld(){
    	//获取鼠标当前坐标
    	PointerInfo pi =MouseInfo.getPointerInfo();
    	Point p =pi.getLocation();
    	this.X=p.x;
    	this.Y=p.y;
    }

    //方法1：编写HelloWorld
    public  void helloWorld_GO() throws AWTException{
    	r.delay(2000); //2s后开始教程

    	//A-组合键win+D
    	keyCombine(KeyEvent.VK_WINDOWS,KeyEvent.VK_D);
    	
    	//B-在桌面移动鼠标，新建文档
    	mouseMove(1117,396);
    	mouse(InputEvent.BUTTON3_DOWN_MASK);	//单击右键
    	key(KeyEvent.VK_W); 							//'W'新建快捷键
    	for(int i=0;i<20;i++){							//移动方向键，往下
    		key(KeyEvent.VK_DOWN); 				
    	}
    	key(KeyEvent.VK_ENTER); 					//回车
    	String fileName="HelloWorld";				
    	keysInput(fileName)	;							//连续输入文件名
    	key(KeyEvent.VK_ENTER); 					//回车

//
		//C-打开文档编写代码
		mouseMove(1089,457);
		mouse(InputEvent.BUTTON1_MASK);	
		mouse(InputEvent.BUTTON1_MASK);	//双击
			r.delay(1000);										//延迟1秒打开文件
			//public class HelloWorld{
			key(KeyEvent.VK_SHIFT);  					//Shirt键，切换输入法
			
			String value1="public class HelloWorld";
			keysInput(value1);
			keyCombine(KeyEvent.VK_SHIFT, KeyEvent.VK_OPEN_BRACKET);//  { 【Sulime Test 3会自动补齐{}()】
			key(KeyEvent.VK_ENTER);//换行
			
			//public static void main(String [] args){
			key(KeyEvent.VK_TAB);														//TAB
	 		value1="public static void main";
			keysInput(value1);
			keyCombine(KeyEvent.VK_SHIFT, KeyEvent.VK_9);					//  (
			value1="String ";
			keysInput(value1);
			key(KeyEvent.VK_OPEN_BRACKET); 										//  [
			key(KeyEvent.VK_CLOSE_BRACKET); 										// ]
			value1=" args";
			keysInput(value1);
			keyCombine(KeyEvent.VK_SHIFT, KeyEvent.VK_0);//  )
			keyCombine(KeyEvent.VK_SHIFT, KeyEvent.VK_OPEN_BRACKET);//  { 
			key(KeyEvent.VK_ENTER);														//换行
			
			//System.out.println("Hello World!");
			key(KeyEvent.VK_TAB);															//开头空格Tab键
			key(KeyEvent.VK_TAB);
			value1="System"; 
			keysInput(value1);
			key(KeyEvent.VK_PERIOD);														// .
			value1="out"; 
			keysInput(value1);
			key(KeyEvent.VK_PERIOD);														// .
			value1="println"; 
			keysInput(value1);
			keyCombine(KeyEvent.VK_SHIFT, KeyEvent.VK_9);					//  (
			keyCombine(KeyEvent.VK_SHIFT, KeyEvent.VK_QUOTE);		//	"
			value1="Hello Wrold"; 
			keysInput(value1); 
			keyCombine(KeyEvent.VK_SHIFT, KeyEvent.VK_1);
			keyCombine(KeyEvent.VK_SHIFT, KeyEvent.VK_QUOTE);		//	"
			keyCombine(KeyEvent.VK_SHIFT, KeyEvent.VK_0);					//  )
			key(KeyEvent.VK_SEMICOLON); 											 	//;
			key(KeyEvent.VK_ENTER);														//换行
	
			//}
			key(KeyEvent.VK_TAB);															// TAB
			keyCombine(KeyEvent.VK_SHIFT, KeyEvent.VK_CLOSE_BRACKET);//  { 
			key(KeyEvent.VK_ENTER);														//换行
			
			//}
			keyCombine(KeyEvent.VK_SHIFT, KeyEvent.VK_CLOSE_BRACKET);//  { 
		
		//D-保存
		r.delay(100);
		keyCombine(KeyEvent.VK_CONTROL, KeyEvent.VK_S); 			    //Ctrl+	S保存
		keyCombine(KeyEvent.VK_ALT,KeyEvent.VK_SPACE);			    //Alt+空格
		key(KeyEvent.VK_C);																    //'C'-快捷键，关闭文档


		//E-移动到一旁，开启命令行，修改文件名，编译java文件，切执行
		mouseMove(1500,500);
		mouse(InputEvent.BUTTON1_MASK);							//左键单击
		mouse(InputEvent.BUTTON1_MASK);							//左键单击
		mouse(InputEvent.BUTTON1_MASK);							//左键单击
		mouseShift(InputEvent.BUTTON3_DOWN_MASK);		//按住Shirt键，单机右键
		key(KeyEvent.VK_W);														//'W'-快捷键【在此处打开命令窗口】
		key(KeyEvent.VK_ENTER);												//回车
		
		r.delay(500);																		  //修改文件名
		key(KeyEvent.VK_SHIFT);  												  //Shirt键，切换输入法
		String inputValue ="ren HelloWorld.txt HelloWorld.java";//修改文件名
		keysInput(inputValue);
		key(KeyEvent.VK_ENTER);												 //回车


		r.delay(500);																		//延迟500ms
	    inputValue ="javac HelloWorld.java";							    //编译java文件
		keysInput(inputValue);
		key(KeyEvent.VK_ENTER);											   //回车
		
		r.delay(2000);																	
		inputValue ="java HelloWorld";										//执行编译文件
		keysInput(inputValue);
		key(KeyEvent.VK_ENTER);
	}
	
	//工具1：鼠标点击
	private  void mouse(int mouseValue){
		r.mousePress(mouseValue);
		r.delay(10);
		r.mouseRelease(mouseValue);
		r.delay(30);
	}
	//工具2：键盘敲打
	private void key(int keyValue){
		r.keyPress(keyValue); 
		r.delay(5);					//	按下和抬起之间有间隔
		r.keyRelease(keyValue);
		r.delay(30);
	}
	//工具3：组合键
	private void keyCombine(int keyValue1,int keyValue2){
		r.keyPress(keyValue1);
		r.keyPress(keyValue2);
		r.delay(5);
		r.keyRelease(keyValue1);
		r.keyRelease(keyValue2);	
		r.delay(30);
	}
	//工具4：鼠标移动到目标位置
	private void mouseMove(int goX,int goY){
		String signX=goX > X ? "+" : "-";
		String signY=goY > Y ?  "+" : "-";
	
		while(X!=goX || Y!=goY){
			if(X!=goX){
				if("+".equals(signX)) r.mouseMove(++X,Y);
				else if("-".equals(signX)) r.mouseMove(--X, Y);
			}
			if(Y!=goY){
				if("+".equals(signY)) r.mouseMove(X,++Y);
				else if("-".equals(signY)) r.mouseMove(X, --Y);
			}
			r.delay(1);
		}
	}
	
	//工具5：键盘连续输入
	private void keysInput(String value){
		char [] arrayC =value.toUpperCase().toCharArray(); 	//将字符串全部转换为大写，再转换为ASCII码
		int [] arrayI = new int[arrayC.length];
		for(int i=0;i<arrayC.length;i++){
			arrayI[i]=(int)arrayC[i];
		}
		
		//开始连续输入
		
		for(int i=0;i<arrayI.length;i++){
			//判断字符大小写,分别进行处理
			if(65<=(int)value.charAt(i) & (int)value.charAt(i)<=90){  //A-65,Z-90
				keyCombine(KeyEvent.VK_SHIFT,arrayI[i]); //组合键Shift+字母键
				continue;
			}
			key(arrayI[i]);
			r.delay(30);
		}
	}
	
	//工具6：按住Shift键操作鼠标点击
	private void mouseShift(int mouseValue){
		r.keyPress(KeyEvent.VK_SHIFT);
		r.delay(20);
		r.mousePress(mouseValue);
		r.delay(10);
		r.mouseRelease(mouseValue);
		r.delay(20);
		r.keyRelease(KeyEvent.VK_SHIFT);
	}


	public static void main(String []args){
		HelloWorld  hw = new HelloWorld();
		try{
			hw.helloWorld_GO();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
```

---
<br>


<3>java版拜年(新年快乐！
```

package start;

import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

/**更新时间： 2017年1月26日   
 *    机器人编写Hello World!
    *
 *  @author Suvan 
    */
    public class 拜年 {
    private static Robot r;		
    private  int X;	//X和Y的坐标
    private  int Y;
    //静态块赋初始值
    static{
    	try{
    		r=new Robot();
    	}catch(Exception e){};
    }

    //无参构造方法
    public 拜年(){
    ​	
    	//获取鼠标当前坐标
    	PointerInfo pi =MouseInfo.getPointerInfo();
    	Point p =pi.getLocation();
    	this.X=p.x;
    	this.Y=p.y;
    }

    //方法1：编写HelloWorld
    public  void helloWorld_GO() throws AWTException{
    	r.delay(2000); //2s后开始教程

    	//A-组合键win+D
    	keyCombine(KeyEvent.VK_WINDOWS,KeyEvent.VK_D);
    	
    	//B-在桌面移动鼠标，新建文档
    	mouseMove(1117,396);
    	mouse(InputEvent.BUTTON3_DOWN_MASK);	//单击右键
    	key(KeyEvent.VK_W); 							//'W'新建快捷键
    	for(int i=0;i<20;i++){							//移动方向键，往下
    		key(KeyEvent.VK_DOWN); 				
    	}
    	key(KeyEvent.VK_ENTER); 					//回车
    	String fileName="xinniankuaile";				
    	keysInput(fileName)	;							//连续输入文件名
    	key(KeyEvent.VK_1);
    	key(KeyEvent.VK_SHIFT);  					//Shirt键，切换输入法
    	key(KeyEvent.VK_ENTER); 					//回车


    	//C-打开文档编写代码
    	mouseMove(1142,441);
    	mouse(InputEvent.BUTTON1_MASK);	
    	mouse(InputEvent.BUTTON1_MASK);	//双击
    		r.delay(1000);										//延迟1秒打开文件
    	
    		key(KeyEvent.VK_SHIFT);  					//Shirt键，切换输入法
    		
    		String value1="public class ";
    		keysInput(value1);
    		key(KeyEvent.VK_SHIFT);  					//Shirt键，切换输入法
    		 value1="xinniankuaile";				
    		keysInput(fileName)	;							//连续输入文件名
    		key(KeyEvent.VK_1);
    		key(KeyEvent.VK_SHIFT);  					//Shirt键，切换输入法
    		keyCombine(KeyEvent.VK_SHIFT, KeyEvent.VK_OPEN_BRACKET);//  { 【Sulime Test 3会自动补齐{}()】
    		key(KeyEvent.VK_ENTER);//换行
    		
    		//public static void main(String [] args){
    		key(KeyEvent.VK_TAB);														//TAB
     		value1="public static void main";
    		keysInput(value1);
    		keyCombine(KeyEvent.VK_SHIFT, KeyEvent.VK_9);					//  (
    		value1="String ";
    		keysInput(value1);
    		key(KeyEvent.VK_OPEN_BRACKET); 										//  [
    		key(KeyEvent.VK_CLOSE_BRACKET); 										// ]
    		value1=" args";
    		keysInput(value1);
    		keyCombine(KeyEvent.VK_SHIFT, KeyEvent.VK_0);//  )
    		keyCombine(KeyEvent.VK_SHIFT, KeyEvent.VK_OPEN_BRACKET);//  { 
    		key(KeyEvent.VK_ENTER);														//换行
    		
    		//System.out.println("Hello World!");
    		key(KeyEvent.VK_TAB);															//开头空格Tab键
    		key(KeyEvent.VK_TAB);
    		value1="System"; 
    		keysInput(value1);
    		key(KeyEvent.VK_PERIOD);														// .
    		value1="out"; 
    		keysInput(value1);
    		key(KeyEvent.VK_PERIOD);														// .
    		value1="println"; 
    		keysInput(value1);
    		keyCombine(KeyEvent.VK_SHIFT, KeyEvent.VK_9);					//  (
    		keyCombine(KeyEvent.VK_SHIFT, KeyEvent.VK_QUOTE);		//	"
    		key(KeyEvent.VK_SHIFT);  			//Shirt键，切换输入法
    		value1="xinniankuaile";				
    		keysInput(value1)	;						
    		key(KeyEvent.VK_1);
    		key(KeyEvent.VK_SHIFT);  					//Shirt键，切换输入法
    		keyCombine(KeyEvent.VK_SHIFT, KeyEvent.VK_1);
    		keyCombine(KeyEvent.VK_SHIFT, KeyEvent.VK_QUOTE);		//	"
    		keyCombine(KeyEvent.VK_SHIFT, KeyEvent.VK_0);					//  )
    		key(KeyEvent.VK_SEMICOLON); 											 	//;
    		key(KeyEvent.VK_ENTER);														//换行
    	
    		//}
    		key(KeyEvent.VK_TAB);															// TAB
    		keyCombine(KeyEvent.VK_SHIFT, KeyEvent.VK_CLOSE_BRACKET);//  { 
    		key(KeyEvent.VK_ENTER);														//换行
    		
    		//}
    		keyCombine(KeyEvent.VK_SHIFT, KeyEvent.VK_CLOSE_BRACKET);//  { 
    	
    	//D-保存
    	r.delay(100);
    	keyCombine(KeyEvent.VK_CONTROL, KeyEvent.VK_S); 			    //Ctrl+	S保存
    	keyCombine(KeyEvent.VK_ALT,KeyEvent.VK_SPACE);			    //Alt+空格
    	key(KeyEvent.VK_C);																    //'C'-快捷键，关闭文档


		//E-移动到一旁，开启命令行，修改文件名，编译java文件，切执行
		mouseMove(1500,500);
		mouse(InputEvent.BUTTON1_MASK);							//左键单击
		mouse(InputEvent.BUTTON1_MASK);							//左键单击
		mouse(InputEvent.BUTTON1_MASK);							//左键单击
		mouseShift(InputEvent.BUTTON3_DOWN_MASK);		//按住Shirt键，单机右键
		key(KeyEvent.VK_W);														//'W'-快捷键【在此处打开命令窗口】
		key(KeyEvent.VK_ENTER);												//回车
		
		r.delay(100);																		  //修改文件名
		key(KeyEvent.VK_SHIFT);  												  //Shirt键，切换输入法
		String inputValue ="ren ";//修改文件名
		keysInput(inputValue);
		key(KeyEvent.VK_SHIFT);  					//Shirt键，切换输入法
		r.delay(50);
		inputValue = "xinniankuaile";
		keysInput(inputValue);
		key(KeyEvent.VK_1);
		r.delay(10);
		inputValue =".txt ";
		keysInput(inputValue);
		key(KeyEvent.VK_SHIFT);  					//Shirt键，切换输入法
		r.delay(10);
		inputValue ="xinniankuaile";
		keysInput(inputValue);
		key(KeyEvent.VK_1);
		key(KeyEvent.VK_SHIFT);  					//Shirt键，切换输入法
		r.delay(10);
		inputValue = ".java";
		keysInput(inputValue);
		key(KeyEvent.VK_ENTER);												 //回车


		r.delay(100);																		//延迟500ms
	    inputValue ="javac ";							    //编译java文件
	    keysInput(inputValue);
	    key(KeyEvent.VK_SHIFT);  					//Shirt键，切换输入法
	    r.delay(10);
		inputValue = "xinniankuaile";
		keysInput(inputValue);
		key(KeyEvent.VK_1);
		key(KeyEvent.VK_SHIFT);  					//Shirt键，切换输入法
		r.delay(10);
	    inputValue =".java";							    //编译java文件
	    keysInput(inputValue);
		key(KeyEvent.VK_ENTER);											   //回车
		
		r.delay(300);																	
		inputValue ="java ";										//执行编译文件
		keysInput(inputValue); 
		   key(KeyEvent.VK_SHIFT);  					//Shirt键，切换输入法
		   r.delay(10);
			inputValue = "xinniankuaile";
			keysInput(inputValue);
			key(KeyEvent.VK_1);
			key(KeyEvent.VK_SHIFT);  					//Shirt键，切换输入法
			r.delay(10);
		key(KeyEvent.VK_ENTER);
		
		key(KeyEvent.VK_SHIFT);  					//Shirt键，切换输入法
	}
	
	//工具1：鼠标点击
	private  void mouse(int mouseValue){
		r.mousePress(mouseValue);
		r.delay(10);
		r.mouseRelease(mouseValue);
		r.delay(20);
	}
	//工具2：键盘敲打
	private void key(int keyValue){
		r.keyPress(keyValue); 
		r.delay(5);					//	按下和抬起之间有间隔
		r.keyRelease(keyValue);
		r.delay(10);
	}
	//工具3：组合键
	private void keyCombine(int keyValue1,int keyValue2){
		r.keyPress(keyValue1);
		r.keyPress(keyValue2);
		r.delay(5);
		r.keyRelease(keyValue1);
		r.keyRelease(keyValue2);	
		r.delay(15);
	}
	//工具4：鼠标移动到目标位置
	private void mouseMove(int goX,int goY){
		String signX=goX > X ? "+" : "-";
		String signY=goY > Y ?  "+" : "-";
	
		while(X!=goX || Y!=goY){
			if(X!=goX){
				if("+".equals(signX)) r.mouseMove(++X,Y);
				else if("-".equals(signX)) r.mouseMove(--X, Y);
			}
			if(Y!=goY){
				if("+".equals(signY)) r.mouseMove(X,++Y);
				else if("-".equals(signY)) r.mouseMove(X, --Y);
			}
			r.delay(1);
		}
	}
	
	//工具5：键盘连续输入
	private void keysInput(String value){
		char [] arrayC =value.toUpperCase().toCharArray(); 	//将字符串全部转换为大写，再转换为ASCII码
		int [] arrayI = new int[arrayC.length];
		for(int i=0;i<arrayC.length;i++){
			arrayI[i]=(int)arrayC[i];
		}
		
		//开始连续输入
	
		for(int i=0;i<arrayI.length;i++){
			//判断字符大小写,分别进行处理
			if(65<=(int)value.charAt(i) & (int)value.charAt(i)<=90){  //A-65,Z-90
				keyCombine(KeyEvent.VK_SHIFT,arrayI[i]); //组合键Shift+字母键
				continue;
			}
			key(arrayI[i]);
			r.delay(30);
		}
	}
	
	//工具6：按住Shift键操作鼠标点击
	private void mouseShift(int mouseValue){
		r.keyPress(KeyEvent.VK_SHIFT);
		r.delay(20);
		r.mousePress(mouseValue);
		r.delay(10);
		r.mouseRelease(mouseValue);
		r.delay(20);
		r.keyRelease(KeyEvent.VK_SHIFT);
	}


	public static void main(String []args){
		拜年  hw = new 拜年();
		try{
			hw.helloWorld_GO();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}

```
<br>
---
<br><br>




14.JDBC连接数据库
----------------------------
&emsp;全称(java Data Base Connectivity ~ java数据库连接),连接数据库的桥梁,由java语言编写的类和接口组成,可以为多种数据库提供统一的访问,JDBC+DATABASE使Application与数据库分开,开发者只需关心内部逻辑的实现,不需注重数据库连接的具体实现
<br>


JDBC升级的替代产品
+ Commons-dbutils【Apache组织提供的开源JDBC工具类库,对传统操作数据库类进行二次封装,可以把结果集转为List】
+ Hibermate【一种Java语言下的对象关系映射解决方案,轻量级ORM框架,提供缓存机制,提高效率,对大量数据进行频繁操作的话,性能效率低,不如直接使用JDBC】
+ MyBatis【是支持普通SQL查询,存储过程和高级映射的优秀持久层框架,sql卸载xml里,便于统一管理和优化,支持独享与数据库ORM字段关系映射,支持对象关系组建维护,支持编写动态sql】
  <br>

继承关系
```
Statement 
	1.PreparedStatement
		(1)CollableStatement
```
<br>


JDBC的连接与使用
+ 加载驱动程序
+ 数据库连接
+ Statement对象
+ PreparedStatement对象
```
<1>加载驱动程序【须在项目中先Build指定数据库的驱动jar包】
	Class.forName("com.mysql.jdbc.Driver");
	Class.forName("com.mysql.jdbc.Driver");//MySQL
	Class.forName("roacle.jdbc.driver.OracleDriver");//Oracle

<2>数据库连接
	Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/my_blog","root","123");   


<3>Statement对象【直接执行】
         Statement st=conn.createStatement();
         ResultSet rs = st.executeUpdate("select * from user");
    
         while(rs.next()){
         	String name = rs.getString("user_name");//用户名
         }


<4>PreparedStatement对象【预编译】
		 String sql = "insert into user(user_name,user_age) values(?,?)";
	     PreparedStatement pst = conn.prepareStatement(sql);
	     pst.setString(1,"用户名");
	     pst.setString(2,"22岁")
	     pst.execute();
	     //Result rs = pst.executeQuery(); //查询语句,有返回结果集



扩展：
    drop table book   删除整个book表
    truncate table book 删除表中的数据(删除速度比delete更快,无法撤回)
    delete from book 删除表中数据(可以回退)
```
<br>



JDBC调用存储过程
+ 调用无参数存储过程
+ 调用含输入参数存储过程
+ 调用含输出参数存储过程
```
<1>调用无参数*******************************************************
		a.存储过程：
			CREATE PROCEDURE imooc_db.sp_select_nofilter()
			BEGIN
				select * from imooc_gooddess;
			END;
	
		b.程序代码
			Connection conn = DBUtils.getConnection();
			CallableStatement c = conn.prepareCall("call sp_select_nofilter()");
			c.execute(); //执行存储过程
			ResultSet rs = c.getResultSet();//返回处理结果
			while(rs.next()){
				String name = rs.getString("user_name");
				......
			}


<2>调用含输入参数*************************************************
		a.存储过程【IN-输入参数】
			CREATE DEFINER ='imooc'@'localhost' PROCEDURE 'sp_select_filter'(IN sp_name VARCHAR(20))
			BEGIN 
				IF sp_name IS NULL OR sp_name = '' THEN
					SELECT * FROM imooc_goddess;
				ELSE
					IF length(sp_name)=11 AND substring(sp_name,1,1)=1 THEN
						SELECT * FROM imooc_goddess WHERE mobile=sp_name;
					ELSE
						SELECT * FROM imooc_goddess WHERE user_name LIKE concat('%',sp_name,'%');
					END IF;
				END IF;
			END
	
		b.程序代码
			List<User> result = new ArrayList<User>();
	
			Connection conn = DBUtil.getConnection();
			CallableStatement cs = conn.prepareCall("Call sp_select_filter(?)");
			cs.setString(1,"user");
			cs.execute();//执行
			ResultSet rs = cs.getResultSet();
			User user = null;
			while(rs.next()){
				user = new User();
				user.setId(rs.getInt("id"));
				user.setUser(rs.getString("user_name"));
	
				result.add(user);
			}


<2>调用含输出参数****************************************************
		a.存储过程【ON-输出参数】
			CREATE DEFING='imooc'@'localhost' PROCEDURE 'sp_select_count'(OUT count INT(10))
			BEGIN
				SELECT count(*) INTO count FROM imooc_goddess;
			END
	
		b.程序代码
			Connection conn = DBUtils.getConnection();
			CallableStatement cs = conn.prepareCall("call sp_select_count(?)");
			cs.registerOutParameter(1,Types.INTEGER);//注册输出参数
			cs.execute();
	
			Integer count = cs.getInt(1);//得到输出值

```
<br>



JDBC事务管理
+ 事务TRANSACTION是作为"单个逻辑工作单元"执行的一系列操作
+ 这些操作作为一个"整体"一起向系统提交,要么都执行,要么都不执行
+ 【特点】ACID,原子性(Atomicty,事务是一个完整的操作),一致性(Consistency,当事务完成时,数据处于一致状态),隔离性(Isolation,对数据进行修改的所有并发事务是彼此隔离的),永久性(Durability,事务完成后,对数据库的修改被永久保持)
```
<1>JDBC对事务管理的支持
	a.
		commit()  ~  提交
		rollback() ~ 回滚
	b.
		事务操作默认是自动提交,可以通过调用setAutoCommit(false)来禁止自动提交


<2>代码实现
		public String transaction(){
			Connection conn = DBUtil.getConnection();
			conn.setAutoCommit(false);//关闭自动提交,进行手动提交
			try{
				//增删查改。。。。。	
	
				conn.commit();//提交
				return "success";//成功标志
			}catch(Exception e){
				conn.roollback();//回滚
				e.printStrackTrace();
				return "fali";//失败标志
			}
		}
```
<br>


JDBC数据库连接池
+ 【常用开源连接池】dbcp和c3p0
```
<1>使用dbcp连接池************************************************************
	a.导入相关jar包
		commons-dpcp2-2.1.1.jar
		commons-pool2-2.4.2.jar
		commons-logging-1.2.jar
	b.在项目根目录增加配置文件
		dbcp.properties
	
			********************************************
			# 使用JDBC驱动的完整有效的java类名
			driverClassName=com.mysql.jdbc.Driver
			#传递给JDBC驱动的用于建立建立连接的url
			url=jdbc:mysql://127.0.0.1:3306/myblog?useUnicode=true&characterEncoding=UTF-8
			#数据库用户名
			username=root
			#数据库密码
			password=root
			#最大活动连接
			maxActive=
			#最大空闲连接
			maxIdle==
			#无限，最大等待时间
			maxWait=
			initialSize=
			minIdle=1
			romveAbandoned=
			removeAbandonedTimeout=
			********************************************
	
	c.程序初始化代码
		
		(1)【方式1】配置文件
			try{
				//A.加载数据源
				Properties pops = new Properties();
				pops.load(Object.class.getResourceAsStream("/dbcp.properties"))
	
				DataSource DS = BasicDataSourceFactory.createDataSource(pops);
			
				//B.从数据源获取连接
				Connection conn = DS.getConnection();
				conn.setAutoCommit(false);//关闭自动提交
	
			}catch(Exception){}
	
		(2)【方式2】动态加载数据源
			try{
				//A.新建数据源
				BasicDataSource ds = new BasicDataSource();
				ds.setDriverClassName("");
				ds.setUserName("");
				ds.setPassword("");
				ds.setUrl("");
				ds.setInitialSize("");
				ds.setMaxTotal("");
				ds.setMaxIdle("");
				ds.setMaxWaitMillis("");
				ds.setMinIdle(minIdle);
	
				DataSource DS = ds;
	
				//B.从数据源获取连接
				Connection conn = DS.getConnection();
				conn.setAutoCommit(false);
	
			}catch(Exception e){}



<2>使用C3P0连接池**********************************************************
	a.介绍
		C3P0是一个开源的JDBC连接池,它实现了数据源和JNDI绑定,支持JDBC3和JDBC2的标准扩展
	
	b.导入相关jar包
		c3p0-版本号-pre4.jar
		machange-commons-java-0.2.2.jar
	
	c.增加配置文件
		c3p0.properties
	
	d.编写类文件,创建连接池
		public static Connection getConnection(){
			//A.新建数据源
			ComboPooledDataSource ds = new ComboPooleDataSource();
	
			//B.获得连接诶
			try{
				return ds.getConnectino();
			}catch(SQLException e){
				throw new RuntimeException(e);//抛出运行时异常
			}
		}

```
<br> 



---
<br><br>



15.遍历与控制台打印输出
--------------------

+ 输出26个字母
+ 遍历HashMap
```
<1>输出26个字母
	public static void main(String [] arge){
	    int a_num=(int)'a';
	    char [] num=new char[26];
	    int i=0;
	    flag:
	        while(true){
	            if(i==num.length) break flag;
	            num[i]=(char)a_num++;
	            i++;
	        }
	    for(char o:num){
	        System.out.print(o);
	    }
	}
<2>遍历HashMap	
 			Iterator iter =hm.entrySet().iterator();
                while (iter.hasNext()) {
                    Map.Entry entry = (Map.Entry) iter.next();
                    Object key = entry.getKey();
                    Object val = entry.getValue();
                    System.out.println(key+"----"+val);
                }

```
<br>

---
<br><br>


16.XML文件读取与生成
----------------------------

xml应用场景
+ 不同应用程序之间的通信
+ 不同平台间的通信
+ 不同平台间的数据共享
  <br>


常见节点类型
+ Element【ELEMENT_NODE,返回值{nodeName:"element name",nodeVluae:"null"}】
+ Attr【ATTRIBUTE_NODE,返回值{nodeName:"属性名称",nodeVluae:"属性值"}】
+ Text【TEXT_NODE,返回值{nodeName:"#text",nodeVluae:"节点内容"}】
  <br>

注意
+ 使用DOM解析的时候,在xml中某个节点获取子节点,空格和换行符也会算1个子节点
+ SAX解析同上
+ JDOM乱码问题，先考虑xml文档中第一行`<?xml version="1.0" encoding="这里的编码"?>`,若仍然出现乱码,在程序中进行代码处理流【new InputStreamReader(FileInputStream,"编码")】
+ 将XML文件中的输入存入entity对象【各种解析】
  <br>


DOM特点
+ 基于true,DOM树,驻留在内存中,易于删除,修改,重新排列
+ 【优点】形成了树结构,直观好理解,代码更易编写
+ 【优点】解析过程中树结构保留在内存中,方便修改
+ 【缺点】当xml文件较大时,对内存耗费比较大,容易影响解析性能,并造成内存溢出
  <br>

SAX特点
+ 基于事件,无状态性，不易于回头进行操作,不频繁操作的话,SAX性能相对DOM较高
+ 【优点】采用事件驱动,对内存耗费比较小
+ 【优点】适用于只需要处理xml中数据时
+ 【缺点】不易编码
+ 【缺点】很难同时访问同一个xml中的多处不同数据
  <br>

JDM特点
+ 仅仅使用具体类而不使用接口
+ API大量使用了Collections类
+ 是一个开发代码的软件
  <br>

DOM4J特点
+ JDOM的异种智能分支,它合并了许多超出基本XML表示的功能
+ DOM4J使用接口抽象基本类方法,是一个优秀的Java XML API
+ 具有性能优异,灵活性好,功能强大和极端易用使用的特点【在某种程度比JDOM性能要好】
+ 是一个开发代码的软件
  <br>


【读取】java中获取xml文件的四种解析方式
+ DOM 【基础.官方(与平台无关,不只是java),将整个XML文件加载到内存中，再逐个解析】
+ SAX 【基础,官方(基于事件驱动的解析方式),通过自己创建的Hndler处理类,去逐个分析遇到的每个节点,按顺序最外层向里层依次解析】
+ DOM4J 【扩展在基础方法上扩展出的,只有java能够使用】
+ JDOM   【同上】

```
<1>DOM解析**********************************************************
	public DOMTest{
		/*
			a.创建DocumentBuilderFactory对象
			b.创建DocumentBuilder对象
			c.通过DocumentBuilder对象的parse(String pathfileName)解析xml文件
			d.得到Dom对象【org.w3c.dom】
		*/
		public static void main(String [] args){
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			try{
				DocumentBuilder db = dbf.newDocumentBuilder();
				Document document = db.parse("books.xml");
	
				//获取"book"节点对象结合
				NodeList booklist = document.getElementByTagName("book");
				for(int i = 0,len = nlist.getLength(); i < len; i++){
					//A【方式1】每个节点
					Node book = booklist.item(i);
					//节点的属性集合
					NameNodeMap attrs = book.getAttributes();
	
					for(int j = 0,len2 = atts.getLength(); j++){
						//属性节点
						Node attr = attrs.item(j);
						String attrName = attr.getNodeName();
						String attrValue = attr.getNodeValue();
					}
	
					//A【方式2】将节点对象强转为Element类型
					Element book = (Element)booklist.item(i);
					String attrValue = book.getAttribute("id");//id属性值



					//B【获取子节点】
					NodeList childlist = book.getChildNodes();
					for(int j = 0,len2 = childlist.getLength();j < len2;j++){
						//针对节点类型为ELEMENT_NODE的节点
						Node child = childlist.item(j);
						if(child.getNodeType == Node.ELEMENT_NODE){
							//执行操作
							System.out.println("打印节点值"+ node.getTextContent());
						}
					}  
	
				}
	
			}catch(ParserConfigurationException e){
				e.printStrackTrace();
			}catch(SAXException e){}
			catch(IOException e){}
		}
	}




<2>SAX解析**********************************************************
	public class ASXTest{
		/*
			a.通过SAXParserFactory.newInstanse获取通过SAXParserFactory的实例factory
			b.通过factory.newSAXParser()获取SAXParser的实例parser
			c.创建一个类继承DefaultHandler【org.xml.sax.helpers】,重写其中的一些方法进行业务处理,并创建这个类的实例
		*/
		public static void main(String [] args){
			SAXParserFactory  factory = SAXParserFacotry.newInstance();
			try{
	
				SAXParser parser = factory.newSAXParser();
	
				MySAXParserHandler handler = new MySAXParserHandler();
	
				parser.parser("books.xml",handler);
	
				//遍历每一本书
				for(Book book:handler.getBooklist){
					System.out.println(book.toString());
				}
	
			}catch(ParserConfigurationException e){}
			 catch(SAXException e){}
		}
	}
	class MySAXParserHandler extends DefaultHandler{
	
			private String value = null;  //节点值
			private Book book = null;
			private ArrayList<Book> booklist = new  ArrayList<Book>();
			public ArrayList<Book> getBooklist(){
				return bookList;
			}


			//标识解析开始
			@Override
			public void startDocument() throws SAXException{
				super.startDocument();
				System.out.println("SAX解析开始[第一行开始]");
			}


			//解析xml元素
			@Override
			public void startElement(String url,String localName,String qName,Attrubutes attributes) throws SAXException{
				super.startElement(uri,localName,qName,attributes);
	
				//解析book节点属性
				if("book".equals(qName)){
					//创建一个Book对象
					this.book = new Book();
	
					System.out.println("=======开始遍历book======")
					 //获取id属性值
					String value = attributes.getValue("id");
					
					//遍历该节点的所有属性
					int num = attributes.getLength();
					for(int i = 0 ; i < num; i++){
						String name = attributes.getQName(i); //属性名
						String value = attributes.getValue(i);//属性值
					}
				}
	
				//执行完后默认执行character()
			}
	
			//获取节点值[参数:节点内容,开始节点,长度]
			@Override
			public void characters(char [] ch,int start,int length) throws SAXException{
				super.characters(ch,start,length);	
	
				this.value = new String(ch,start,length);
				if(!value.trim().equals("")){ //去掉空格后,不是空字符串
					System.out.println("节点值:" + value);
				}
			}
	
			//节点的结束标签
			@Override
			public void endElement(String uri,String localName,String qName)throws SAXException{
				super.endElement(uri,localName,qName);
	
				//判断是否结束某个book节点的遍历
				if("book".equals(qName)){
					System.out.println("=======结束遍历book======")
					this.booklist.add(book);  //将book对象添加进集合
					this.book = null;		  //重置book对象
				}else if("name".equals(qName)){
					book.setName(value);
				}else if("author".equals(qName)){
					book.setAuthor(value);
				}else if(qName.equals("content")){
					book.setContent(value);
				}
			}


			//标识解析结束
			@Override
			public void endDocuemnt() throws SAXException{
				super.endDocument();
				System.out.println("SAX解析结束[读完最后一行]");
			}
	}
	
	//entity实体类
	class Book{
		private String name;
		private String author;
		private String content;
	
		//Getter和Setter方法
		...... 
	
		//toString()
		public String toString(){
			....
		}
	}



<3>JDOM解析**********************************************************
	public class JDOMTest{

		private ArrayList<Book> booklist = new ArrayList<Book>();

		/*
			a.导入jdom-版本号.jar包
			b.创建SAXBuilder的对象实例通过saxBuilde
			c.指定xml文件创建输入流
			d.通过saxBuilde的builder(),将输入流加载,返回Document【org.jdom2】实例
		*/
		public static void main(String [] args){
			SAXBuilder saxBuilder = new SAXBuilder();
	
			InputStream is;
			try{
				in = new FileInputStream("src/res/books.xml");
	
				Document document = saxBuilder.build(in);
	
				//获取根节点
				Element rootElement = document.getRootElement();
	
				//获取根节点下的子节点集合
				List<Element> booklist = rootElement.getChildren();
				int len = booklist.size(); //集合长度
				for(Element book: booklist){
					Book book = new Book();
					System.out.println("开始解析....");
	
						String id = book.getAttrbuteValue("id");//获取节点的id属性值
	
						//获取属性集合
						List<Attribute> attrlist = book.getAttributes();
						for(Attribute attr: attrlist){
							String attrName = attr.getName();//属性名
							String attrValue = attr.getValue();//属性值
						}
	
						//book节点的子节点的遍历
						List<Element> bookChilds = book.getChildren();
						for(Element child: bookChilds){
							String nodeName = child.getName();//子节点名
							String nodeValue = child.getValue();//子节点值
	
							if("name".equals(nodeName)){
								book.setName(nodeValue);
							}
							else if("author".equals(nodeName)){
								book.setAuthor(nodeValue);
							}
							else if("content".equals(nodeName)){
								book.setContent(nodeValue);
							}
	
						}
	
					System.out.println("结束解析....");
					booklist.add(book); //存储book集合
					book = null;	//重置book
				}


			}catch(FileNotFoundException e){}
			 catch(JDOMException e){}
			 catch(IOException e){}
		}
   }
	//entity实体类
	class Book{
		private String name;
		private String author;
		private String content;
	
		//Getter和Setter方法
		...... 
	
		//toString()
		public String toString(){
			....
		}
	}


<4>DOM4J解析**********************************************************

	public DOM4JTest{
		 /*
			a.导入dom4j-版本号.jar包
			b.创建SAXReader对象reader
			c.通过read的read(),加载xml文件,得到Doument【org.dom4j】实例对象
			d.获取根节点,子节点。。。
		 */
		 public static void main(String [] args){
		 	SAXReader reader = new SAXReader();
	
		 	try{
		 		Document document reader.read(new File("src.res/books.xml"));
	
		 		Element bookRoot = document.getRootElement();
	
		 		//获取迭代器,遍历迭代器,获取根节点中的信息
		 		Iterator it = bookRoot.elementIterator();
		 		while(it.hasNext()){
		 			System.out.println("========开始遍历一本书==========")
		 			Element book = (Element)it.next(); //节点
	
		 			//获取book属性集合
		 			List<Attribute> bookAttrs = book.attributes();
		 			for(Attribute attr: bookAttrs){
		 				String attrName = attr.getName(); //属性名
		 				String attrValue = attr.getValue();//属性值
		 			}


    	 			//获取book节点的子节点迭代器
    	 			Iterator itt = book.elementIterator();
    	 			while(itt.hasNext()){
    	 				Element bookChild = (Element)itt.next();
    
    	 				String childName = bookChild.getName();//子节点名
    	 				String childValue = bookChild.getValue();//子节点值
    	 			}
    	 			println("==========结束遍历===================")
     	 		}
    
    	 	}catch(DocumentException e){}
    	 	
    	 }
    }

```
<br>

---


【生成】java生成XML文件的四种方式
```
<1>DOM生成*********************************************************
	 public void createXML(){
	  	DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	  	
	  	DocumentBuilder db = null;
	  	try{
	  		db = dbf.newDocumentBuilder();
	    }catch(ParserConfigurationException e){}
	
	    //获得Document
	    Doucment document  = db.newDocument(); 
	    document.setXmlStandalone(true);//将第一行的xml标签,standalone属性【yes-表示当前xml文件没有ddt和schema作为说明文档,no-表示有..说明文档,解释说明允许有哪些值,以及值的一些形式】声明为yes(默认是不显示)
	
	    //添加根节点bookstore
	    Element bookstore = document.createElement("bookstore");
	    Element book = document.createElement("book");
	    book.setAttribute("节点属性名","节点属性值");
	    Element name = document.createElement("name");
	    name.setTextContent("节点文本内容");
	
	    book.appendChild(name);		
	    bookstore.appendChild(book);      //给bookstore添加子节点
	    document.appendChild(bookstore);  //添加根节点


	    //创建xml文件
	    TransformerFactory tff = TransformerFactory.newInstance();//工程类创建对象
	    try{
	    	Transformer tr = tff.newTransformer();
	
	    	//设置换行
	    	tf.setOutputProperty(OutputKeys.INDENT,"yes");
	
	    	//传入document,生成xml文档
	    	tf.transform(new DOMSource(document),
	    				 new StreamResult(new File("book1.xml")));
	    }catch(TransformerConfigurationException e){}
	
	}


<1>SAX生成*********************************************************
	public void createXML(){
		//1.得到Book对象集合进行生成xml文件
		ArrayList<Book> bookList = getBooklist();
	
		SAXTransformerFactory tff = (SAXTransformerFactory)SAXTransformerFactory.newInstance();
	
		try{
			TransformerHandler handler = tff.newTransformerHandler();
	
			//通过Transformer对象,设置xml编码与换行
			Transformer tf = handler.getTransformer();
			tf.setOutputProperty(OutputKeys.ENCODING,"UTF-8");
			tf.setOutputProperty(OutputKeys.INDENT,"yes");


			//创建Result对象并与handler关联
			File file = new File("src/res/newbooks.xml");
			if(!file.exists()){
					file.createNewFile();//不存在则创建xml文件
			}
	
			Result result = new StreamResult(new FileInputOutputStream(file));
			handler.setResult(result);
	
			//打开document
			handler.startDocument();
	
				//bookstore节点
				AttributesImpl attr = new AttributesImpl();//属性
				handler.startElement("","","bookstore",attr); //开始标签
		
					//子节点book,并赋予属性
					attr.clear();
					attr.addAttribute("","","id","","1");
					handler.startElement("","","book",attr);
	
						//子节点name
						handler.startElement("","","name",attr);
						handler.characters("文本值".toCharArray(),0,"文本值".length();)
						handler.endElement("","","name");
	
					handler.endElement("","","book");
	
				handler.endElement("","","bookstore";//结束标签
	
			//关闭document
			handler.endDocuemnt();

​	

		}catch(TransformerConfigurationException e){}
		 catch(FileNotFoundException e){}
		 catch(IOException e)
	
	}



<3>JDOM生成*********************************************************
	public void createXML(){
		Element rss = new Element("rss");
		rss.setAttribute("version","2.0");
	
		Document docuemnt = new Document(rss);
	
		//子节点
		Element channel = new Element("channel");
		rss.addcContent(channel);
		Element title = new Element("title");
		title.setText("国内最新新闻");
		channel.addContext(title);
	
		//设置XML格式
		Format format = Format.getCompactFormat()
		format.setIndent(""); //设置换行
		format.setEncoding("GBK");//设置编码


		//创建XML文件
		XMLOutputter outputer = new XMLOutputter(format);
	
		try{
			outputer.output(document,new fileOutputStream(new File("rssnews.xml")));
		}catch(FileNotFoundException e){}
		 catch(IOException e){}
		
	}


<4>DOM4J生成*********************************************************

	public void createXML(){
		Document document = DocumentHepler.createDocument();
		Element rss = document.addElement("rss"); /RSS节点
		rss.addAttribute("version",2.0);
	
		//子节点
		Element channel = rss.addElement("channel");
		Element title = channel.addElement("title");
		title.setText("<![CDATA[国内最新新闻]]>");
	
		//设置漂亮的换行格式
		OutputFormat format = OutputFormat.createPrettyPrint();
		format.sendEncoding("GBK");//设置编码
	
		//生成文件
		File file = new File("src/res/rssnew.xml");
		XMLWrite write;
		try{
			write = new XMLWrite(new FileOutputStream(file),format);
	
			//设置转义,默认为ture(自动将特殊符号转义),false-不转义
			write.setEscapeText(false);
			
			write.write(document);
			write.close();
		}catch(IOException e){
			e.printStackTrace();
		}


	}

```
<br>

---
<br><br>




17_1.Java加密与解密机制介绍
-----------------------
&emsp;加密与解密算法的最终目的是实现数据的安全传输
<br>

密码常用术语
+ 明文【待加密信息】
+ 密文【经过加密后的明文】
+ 加密【明文转密文的过程】
+ 加密算法【明文转密文的转换算法】
+ 加密密钥【通过加密算法进行加密操作的密钥】
+ 解密【将密文转为明文的过程】
+ 解密算法【密文转为明文的算法】
+ 解密密钥【通过解密算法进行解密操作用的密钥】
+ 密码分析【截获密文者视图通过分析截获的密文从而推断出原来的明文或密钥的过程】
+ 主动工具【攻击者非法入侵密码系统,采用伪造,修改,删除等手段向系统注入假消息进行欺骗(对密文具有破坏作用)】
+ 被动攻击【对一个保密系统采取截获密文并对其进行分析和攻击(对密文没有破坏作用)】
+ 密码体质【由明文空间,密文空间,密钥空间,加密算法和解密算法五部分构成】
+ 密码协议【也称安全协议,指以密码为基础的消息交换通信协议,目的是在网络环境中提供安全的服务】
+ 密码系统【指用于加密,解密的系统】
+ 柯科克霍夫原则【数据安全基于密钥而不是算法的保密.即系统的安全取决于密钥,对密钥保密,对算法公开(现代密码学设计的基本原则)】
  <br>



密码的分类
```
<1>时间分类
		a.古典密码【以字符为基本加密单元】
		b.现代密码【以信息块为基本加密单元】



<2>保密内容算法分类
		a.受限制算法【算法保密性基于对算法的保密】
		b.基于密钥算法【...保密性基于对密钥的保密】



<3>密码体质分类
	a.对称密码(单密码or私钥密码)   【值加密密钥与解密密钥相同】
	b.非对称密码(双钥oir公钥密码)	 【指加密密钥与解密密钥不同,密钥分公钥,私钥】
	
	a-2.对称密码算法(单钥密码算法or私钥密码算法)  【指应用于对称密码的加密,解密算法】
	b-2.非对称密码算法(双钥密码算法or公钥密码算法) 【指对应用与非对称密码的加密,解密算法】


<4>明文处理方法分类
	 a.分组密码  【指加密时将明文分成固定长度的组,用同一密钥和算法对每一块加密,输入也是固定长度的密文(多用于网络加密)】
	 b.流密码(序列密码)    【指加密时每次加密1位或一个字节的明文】

```
<br>


散列函数的特点
+ 【作用】用于验证数据的完整性,并不是用来加解密
+ 长度不受限制
+ 哈希值容易计算
+ 散列运算过程不可逆
+ 【相关算法】消息摘要算法MD5,SHA(安全散列算法),MAC(消息认证码算法)
  <br>


数字签名的特点
+ 【是什么】主要是针对以数字的形式存储的消息进行的处理,产生一种带有操作者身份(签名者)信息的编码
  <br>

OSI(Open System Interconnection)安全体系
```
<1>网络通信
	7.应用层
	6.表示层
	5.会话层
	4.传输层
	3.网络层
	2.数据链路层
	1.物理层

<2>安全机制
	+ 加密机制
	+ 数字签名机制
	+ 访问控制机制
	+ 数据完整性机制
	+ 认证机制
	+ 业务流填充机制
	+ 路由控制机制
	+ 公证机制

<3>安全服务
	+ 认证(鉴别)
	+ 访问控制服务
	+ 数据保密性服务
	+ 数据完整性服务
	+ 抗否认服务
```
<br>


TCP/IP安全体系
```
<1>网络通信层
			【这里是指对应的OSI参考模型】
	4.应用层~~应用曾安全【对应应用,表示,会话层】
	3.传输层~~传输层安全【...传输层】
	2.网络层~~网络层安全【...网络层】
	1.网络接口层~~网络接口层安全【...数据链路,物理层】


<2>安全服务与安全机制的对应
	抗否认性服务  ~~~  公正机制
	数据完整性服务 ~~~ 数据完整性机制
	数据保密性服务 ~~~  加密机制,业务流填充机制
	访问控制服务 ~~~  访问控制机制,路由控制机制
	认证(鉴别)服务 ~~~ 数字签名,认证机制


```
<br>

Java安全组成组成
+ JCA(Java Crptography Architecture)【Java加密信息结构,提供基本框架,如消息摘要,数字签名等】
+ JCE(Java Cryptography Extension)【Java加密扩展包,在JCA的基础上进行扩展,提供更多加密算法,消息摘要,密钥管理等功能(例如DES,AES,RSA算法)】
+ JSSE(Java Secure Socket Extension)【Java安全套接字扩展包,提供基于SSL的加密功能,主要用于网络传输】
+ JAAS(Java Authentication and Authentication Service)【Java的鉴别与安全服务,提供在java平台上进行用户身份验证的功能,基于java开发的系统的权限与安全】
+ JCA和JCE是java官方提供的基于用户安全和加密的API,只是提供一些接口,java的安全机制是可以扩展的【可适用JDK意外的扩展包,需要修改资源文件([jdk根目录]~jre~lib~security~[java.security文件]),增加相关内容】
  <br>

相关java包和类
+ java.security 【消息摘要】
+ javax.crypto  【安全信息摘要,消息认证(鉴别)码】
+ java.net.ssl  【安全套接字】
  +【第三方扩展包】Bouncy Castel
  +【第三方扩展包】Commons Codec
  <br>

---
<br><br>



17_2.运用Base64算法
-------------------------------
&emsp;Base64是英文邮件的"历史问题"而产生算法,基于64个字符的编码算法【一种用替换的方式实现的算法】,RFC 2045【64位编码算法的规范】,衍生Base16,Base32,UrlBase64等算法,


Base64算法应用场景
+ e-mail
+ 密钥
+ 证书文件
  <br>



三种方式实现Base64算法
+ JDK
+ Commons Codec【导入commons-codec-版本号.jar包】
+ Bouncy Castle【导入bcprow-jdk15on-版本号.jar包】
```
<1>JDK实现*************************************************************
	public static void jdkBase64(){

		String clearText = "hello world";//明文
		String cipherText; //密文
		String result;//密文解密后
	
		try{
			//加密
			BASE64Encoder encoder = new BASE64Encoder();//编码器
			cipherText = encoder.encode(clearText.getBytes());
	
			//解析
			BASE64Decoder decoder = new BASE64Decoder();//解码器
			result = new String(decoder.decodeBuffer(cipherText));
		}catch(IOException e){
			e.printStackTrace();
		}
		
	}


<2>Coomons Codec实现******************************************************
	public static void commonsCodesBase64(){
		String clearText = "hello world";//明文
		String cipherText; //密文
		String result;//密文解密后
	
		//加密
		byte [] encodeBytes = Base64.encodeBase64(clearText.getBytes());
		cipherText = new String(encodeBytes);
	
		//解密
		byte [] decodeBytes = Base64.decodeBase64(encodeBytes);
		result = new String(decodeBytes);
	}



<2>Bocuncy Castle实现******************************************************
	 public static void bouncyCastleBase64(){
	 	String clearText = "hello world";//明文
		String cipherText; //密文
		String result;//密文解密后
	
		//加密
		byte [] encodeBytes = org.bouncycastle.encoders.Base64(clearText);
		cipherText = new String(encodeBytes);
	
		//解密
		byte [] decodeBytes = org.bouncycastle.util.encoders.Base64.decode(encodeBytes);
		result = new String(decodeBytes);
	
	}

```
<br>
---
<br><br>



17_3.运用消息摘要算法
--------------------------
&emsp;主要用于验证数据的完整性,是整个数字前面的核心算法



主要讲解三种类型
+ MD(Message Digest)【128位摘要信息】
+ SHA(Secure Hash Algorithm)【在MD4的基础上演进而得】
+ MAC(Message Authentication Code)
  <br>


MD5应用场景

1. 用户注册~密码进行信息摘要(密码是使用MD5信息摘要得到的16进制的字符串)~信息持久化~返回注册结果
2. 用户登录~对密码进行信息摘要(判断与注册时的MD5处理结果是否相等)~通过用户名及摘要查询~返回登录结果

<br>



SHA应用场景

1. firefox浏览器~选项~高级~证书-查看证书~随便查看一个【证书,指纹】
2. 发送方~(1)公布消息摘要算法(是SHA..)~(2)对待发布消息进行摘要处理~(3)发送摘要消息与原始消息【加入约定的key 或 时间戳 or 排序】~(4)接收方进行消息鉴别【指将原始消息进行摘要,与接收到摘要信息进行对比】
3. 信息:原始消息 + key + 时间戳

<br>



MAC应用场景
+ SecureCRT【Linux操作系统的客户端】
+ 发送方~公布消息摘要算法~构建密钥~发送密钥~对待发送的信息进行消息摘要处理~发送消息知啊要~发送消息~接收方进行消息鉴别
  <br>




三种消息摘要算法的不同实现
+ JDK
+ Commons Codec【导入commons-codec-版本号.jar包】
+ Bouncy Castle【导入bcprow-jdk15on-版本号.jar包】
```
<1>MD算法【jdk,Bocuncy Castle,Coomons Codec方式实现,同一明文用不同方式实现,得到结果仍然保持一致】
		算法    摘要长度	实现方
		MD2       128  		  JDK            [1代]
		MD4		  128 		  Bouncy Castle  [2代.安全性比前一代提高]
		MD5       128         JDK            [3代,安全性比前一代提高]
	
		/*【jdk实现】MD5-加密*/
		public static void jdkMD5(){
				String clearText = "hello world";//明文
	
			try{
	            MessageDigest md = MessgeDigest.getInstance("MD5");
	            byte [] md5Bytes = md.digest(clearText.getBytes());
	
	            //加密后的内容转为16进制【org.apache.commons.codec.binary.Hex包】
	            String result = Hex.encodeHexString(md5Bytes);
	
			}catch(NoSuchAlgorithmException e){}
		}


		/*【Bouncy Castle实现】MD4-加密*/
		public static void bcMD4(){
			String clearText = "hello world";//明文
	
			//加密
			Digest digest = new MD4Digest();
			digest.update(clearText.getBytes,0,clearTextBytes().length);//byte数组,开始位置,长度
	
			byte [] md4Bytes = new byte[digest.getDigestSize()];
			digest.doFinal(md4Bytes,0);//摘要处理后输入的内容,输入长度
	
			//转成16进制
			String result = org.bouncycastle.util.encoders.Hex.toHexString(md4Bytes);
		}
	
		/*【Bouncy Castle实现】MD5-加密*/
		public static void bcMD5(){
			String clearText = "hello world";//明文
	
			//方式1~~~~~~~~~~~~
			//加密
			Digest digest = new MD5Digest();
			digest.update(clearText.getBytes,0,clearTextBytes().length);
	
			byte [] md5Bytes = new byte[digest.getDigestSize()];
			digest.doFinal(md5Bytes,0);
	
			//转成16进制
			String result = org.bouncycastle.util.encoders.Hex.toHexString(md5Bytes);


			//方式2~~~~~~~~~~JDK动态添加BC的MD5实现【也可以使用配置文件方式】
			try{
				Security.addProvide(new BouncyCastleProvider());
				MessageDigest md = MessageDigest.getInstance("MD5");
				byte [] md5Bytes = md.digest(clearText.getBytes());
	
				String result = Hex.encodeHexString(md5Bytes);
	
			}catch(NoSuchAlgorithmException e){
				e.printStrackTrace();
			}
		}
	
		/*【Coomons Codec实现】MD5-加密*/
		public static void ccMD5(){
			String clearText = "hello world";//明文
	
			String result = DigestUtils.md5Hex(clearTextx.getBytes());
			
		}

****************************************************************

<2>SHA算法
	 .安全散列算法
	 .固定长度摘要信息
	 		算法	摘要长度 	实现方
	 		SHA-1	  160  		  JDK
	 		SHA-224	  224         Bocuny Castle
	 		SHA-256   256         JDK
	 		SHA-384   384         JDK
	 		SHA-512   512         JDK
	
	    /*【JDK实现】SHA1-加密*/
	 	public static void jdkSHA1(){
	
	 		String clearText = "hello world";//明文
	
	 		try{
	 			MessageDigest md = MessageDigest.getInstance("SHA");
	 			md.update(clearText.getBytes());
	
	 			String result = Hex.encodeHexString(md.digest());
	 		}catch(NoSuchAlgorithmException e){}
	 	}
	
	 	/*【Bocuny Castle实现】SHA1-加密*/
	 	public staic void bcSHA1(){
	 		String clearText = "hello world";
	
	 		Digest digest = new SHA1Digest();
	 		digest.update(clearText.getBytes(),0,clearText.getBytes().length);
	
	 		byte [] sha1Bytes = new byte[digest.getDigestSize()];
	 		digest.doFinal(sha1Bytes,0);
	
	 		String result = org.bouncycasetle.util.encoders.Hex.toHexString(sha1Bytes);
	 	}
	
	 	/*【Bocuny Castle实现】SHA224-加密*/
	 	public static void bcSHA224(){
	 		String clearText = "hello world";
	
	 		Digest digest = new SHA22Disgest();
	 		digest.update(clearText.getBytes(),0,clearText.getBytes().length);
	
	 		byte [] sha224Bytes = new byte[digest.getDigestSize()];
	 		digest.doFinal(sha224Bytes,0);
	
	 		String result = org.bouncycastle.util.encoders.Hex.toHexString(sha224Bytes);
	 	}
	
	 	/*【Coomons Codec实现】SHA1-加密*/
	 	public static void ccSHA1(){
	 		String clearText = "hello world";
	
	 		//两种方式,对于同一个字符的摘要结果,都一样
	 		String result1 = DigestUitls.sha1Hex(clearText.getBytes());
	 		String result2 = DigestUitls.sha1Hex(clearText);
	 	}

****************************************************************
<3>MAC算法
	.融合了MD和SHA算法的特性,并在基础上加入密钥
	.也可称为HMAC(keyed-Hash Message Authentication Code)含有密钥的散列函数算法
			算法		摘要长度		实现方
			HmacMD2		128 			Bocuncy Castle   【MD系列】
			HmacMD4	 	128 			Bocuncy Castle
			HmacMD5     128             JDK
			HmacSHA1	160 			JDK
			HmacSHA224  224             Bouncy Castle    【SHA系列】
			HmacSHA256  256 			JDK
			HmacSHA384  384             JDK
			HmacSHA512  512             JDK       


		/*【JDK实现】HmacMD5-加密*/
		public static void jdkHmacMD5(){
			String clearText = "hello world";
	
			try{
				//A.获取密钥[初始化KeyGenerator + 产生 + 获得]
				KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacMD5");
				SecreKey secreKey = keyGenerator.generateKey();
	
				//byte [] key = secreKey.getEncode();//自动生成
				byte [] key = Hex.decodeHex(new char[]{'a','a','a'});//自己指定密钥
	
				//B.还原密钥
				SecretKey restoreSecretKey = new SeretKeySpec(key,"HmacMD5");
	
				//C.实例化MAC + 初始化摘要
				MAC mac = Mac.getInstance(restoreSecretKey.getAlgorithm());
				mac.init(restoreSecretKey);
	
				//D.执行摘要 + 得到结果
				byte [] hmacMD5Bytes = mac.doFinal(clearText.getBytes());
				String result = Hex.encodeHexString(hmacMD5Bytes());
	
			}catch(Exception e){}
		}
	
		/*【Bocuny Castle实现】HmacMD5-加密*/
		public static void bcHmacMD5(){
			String clearText = "hello world";
	
			HMac hmac = new HMac(new MD5Digest());
			hmac.init(new KeyParameter(org.bouncycastle.util.encoders.Hex.decode("aaa")));
			hmac.update(clearText.getBytes(),0,clearText.getBytes().length);
	
			byte [] hmacMD5Bytes = new byte[hmac.getMacSize()];
			hmac.doFinal(hmacMD5Bytes,0);
	
			String result = org.bouncycastle.util.encoders.Hex.toHexString(hmacMD5Bytes);
		}

```
<br>



消息摘要算法其他类型【Bouncy Castle实现】
+ RipeMD
+ Tiger
+ Whirlpool
+ GOST3411
  <br>

---
<br><br>




17_4.运用对称加密算法
-------------------------
&emsp;初等加密的算法,提高了数据安全性,但是密钥管理复杂,密钥传递过程复杂,DES,3DES,AES的这三种的实现方式基本相近,ABE算法常用于.用于移动通信系统加密以及SSH协议的的软件[SSH Client,secureCRT],PBE并不是一个新的加密算法,而是对已有的算法补充,整合
<br>


几种对称加密算法
+ DES算法(Data Encryption Standard ~ 数据加密标准)
+ 三重DES算法(Triple DES 或 DESede)
+ AES算法【DES替代者，安全程度逐级增加】
+ PBE算法(Password Based Encryption ~ 基于口令加密)【结合了消息摘要算法和对称加密算法的优点,对已有算法提供宝座】
  <br>



算法两种库提供实现
+ jdk
+ BC(Bocuny Castle)
  <br>



应用场景
+ 【DES算法】【AES算法】发送者构建密钥~公布密钥~使用密钥对数据加密~发送加密数据~接受者使用密钥对数据解密
+ 【PBE算法】发送者构建口令~公布口令~构建盐~使用口令,盐对数据加密~发送盐,加密数据~接受者使用口令,盐对数据解密
  <br>



代码中运用算法
```
<1>DES算法**************************************************************
		public static void jdkDES(String clearText){ //参数：明文,要被加密
			try{
				//A-生成Key
				KeyGenerator keyGenerator = KeyGernerator.getInstance("DES");
				keyGenerator.init(56); //指定key【默认是56位】
				SecreKey secretKey = keyGenerator.generateKey();
				byte [] bytesKey = secretKey.getEncoded();


				//B-Key转换
				DESKeySpec desKeySpec = new DESKeySpec(bytesKey);
				SecreteKeyFactory factory = SecretKeyFacotry.getInstance("DES");
				Key convertSecretKey = factory.generateSecrete(desKeySpec);
	
				//C.加密
				Cipher cipher = Cipher.getInstance("DES/ECB/PKCSSPadding");
				cipher.init(Cipher.ENCRYPT_MOOD,convertSecretKey);//加密模式,密钥
				byte [] result = cipher.doFinaly(clearText.getBytes());
				System.out.println("加密结果(十六进制)：" + Hex.encodeHexString(result));
	
				//D.解密
				ciper.init(Cipher.DECRYTPT_MODE,convertSecretKey);//解密模式,密钥
				result = ciper.doFinal(result);
				System.out.println("解密结果(字符串):" + new String(result));
			}catch(Exception e){
				e.printStackTrace();
			}
			 
		}


<2>三重DES算法*************************************************************
		public static void jdk3DES(String clearText){ //参数：明文,要被加密
			try{
				//A-生成Key
				KeyGenerator keyGenerator = KeyGernerator.getInstance("DESede");
				//keyGenerator.init(168); //DESede必须是112 or 168
				keyGenerator.init(new SecureRandom());//生成默认长度(根据不同的算法)
				SecreKey secretKey = keyGenerator.generateKey();
				byte [] bytesKey = secretKey.getEncoded();


				//B-Key转换
				DESedeKeySpec desKeySpec = new DESedeKeySpec(bytesKey);
				SecreteKeyFactory factory = SecretKeyFacotry.getInstance("DESede");
				Key convertSecretKey = factory.generateSecrete(desKeySpec);
	
				//C.加密
				Cipher cipher = Cipher.getInstance("DESede/ECB/PKCSSPadding");
				cipher.init(Cipher.ENCRYPT_MOOD,convertSecretKey);//加密模式,密钥
				byte [] result = cipher.doFinaly(clearText.getBytes());
				System.out.println("3DES加密结果(十六进制)：" + Hex.encodeHexString(result));
	
				//D.解密
				ciper.init(Cipher.DECRYTPT_MODE,convertSecretKey);//解密模式,密钥
				result = ciper.doFinal(result);
				System.out.println("3DES解密结果(字符串):" + new String(result));
			}catch(Exception e){
				e.printStackTrace();
			}
			 
		}

<3>AES算法**************************************************************

		public static void jdkAES(String clearText){
			try{
				//A.生成KEY
				KeyGenerator keyGenerator = KeyGeneraator.getInstance("AES");
				keyGenerator.init(128);
				SecraKay secretKey = keyGenerator.generateKey();
				byte [] keyBytes = secretKey.getEncoded();
	
				//B.key转换
				Key key new SecreKeySpec(keyBytes,"AES");
	
				//C.加密
				Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
				cipher.init(Ciphert.ENCRYPT_MODE,key);
				byte [] result = cipher.doFinal(clearText.getBytes());
				System.out.println("AES加密结果(16进制):" + Base64.encodeBase64String(result));
	
				//D.解密
				cipher.init(Cipher.DECRYPT_MODE,key);
				result = cipher.doFinal(result);
				System.out.println("AES解密结果:" + new String(result));
	
			}catch(Exception e){
				e.printStrackTrace();
			}
		}


<3>PBE算法**************************************************************

		public static void jdkPBE(String clearText){
			try{
				//A-初始化盐【通过额外引入东西去扰乱,通常说叫扰码】
				SecureRandom random = new SecureRandom();
				byte [] salt = random.generateSeed(8);//8位,随机数产生盐
	
				//B-口令与密钥
				String password = "study";//定义密码
				PBEKeySpec pbeKeySpec = new PBEKeySpec(password.toCharArray());
				SecretKeyFactory factory = SecretKeyFactory.getInstance("PBEWITHMD5andDES");
				Key key = factory.generateSecret(pbeKeySpec);


				//C.加密
				PBEParameterSpec pbeParameterSpec = new  PBEParameterSpec(salt,100);
				Cipher cipher = Cipher.getInstance("PBEWITHMD5SanDES");
				cipher.init(Cipher.ENCRYPT_MODE,key,pbeParameterSpec);
				byte [] result = cipher.doFinal(clearText.getBytes());
				System.out.println("PBE加密结果(十六进制):" + Base64.encodeBase64String(result));
	
				//D.解密
				ciper.init(Cipher.DECRYPT_MODE,key,pbeParameterSpec);
				result = cipher.doFinal(result);
				System.out.println("PBE解密结果:" + new String(result));
	
		}catch(Exception e){
			e.printStrackTrace();
		}

```
<br>

---
<br><br>


17_5.运用非对称加密算法
--------------------------------
&emsp;高级,双保险,密钥分开【公钥,私钥~成对出现】,提高了
<br>

几种类型
+ DH(Diffie-Hellman)密钥交换算法【构建本地密钥,只有jdk提供实现】
+ RSA算法【基于因子分解,速度较慢,数据加密&数字前面,jdk和BC提供实现】
+ ElGamal【基于离散对数,只有公钥加密算法,只有BC提供实现】
+ ECC(Elliptical Curve CrypTography)椭圆曲线加密
  <br>



代码运用
```
<1>DH算法********************************************************
		/*
			简单思路和类说明：
				a.初始化发送密钥(
								  KeyPairGenerator ~ 生成KeyPair的类
								  KeyPair  ~ 得到公钥,私钥
							      PublicKey类)
				b.初始化接收方密钥(
									KeyFactory
									X509EncodedeKeySpec ~ 根据ASN.1标准进行密钥编码
									DHPublicKey ~ PublicKey的具体形式
									DHParameterSpec ~ DH算法使用参数的集合
									KeyPairGenerator 
									PrivateKey类)
				c.密钥构建(
								KeyAgreeement ~  提供密钥一致性(或密钥交换)协议的功能,主要出现在DH算法中
								SecreteKey ~ 生成一个分组的秘密密钥,也提供一些相应的类型安全的操作,父接口是Key
								KeyFacotry
								X509EncodedKeySpec
								PublicKey)
				d.Cipher(
							Cipher ~ 为加密和解密提供密码的功能)


			【初始化DH算法密钥对】
			发送者构建发送方密钥
				~ 公布发送方密钥
				~ 接收者使用发送方密钥,构建自己密钥
					~公布接收方公钥
	
			【DH算法加密消息传递】
			发送方使用本地密钥加密信息
				~发送加密信息
				~接受者使用本地密钥及诶系消息
	
		*/
		public static void jdkDH(String clearText){
			try{
				//A.初始化发送方密钥
				KeyPairiGenerator senderKeyPairGenerator = KeyPairGenerator.getInstance("DH");
				sendKeyPairGenerator.initialize(512);//长度
				KeyPair senderKeyPair = senderKeyPairGenerator.generateKeyPair();
		 		byte [] senderPublicKeyEnc = senderKeyPair.getPbulic().getEncoded();//发送方公钥,发送给接收方(网络,文件,序列化操作...)		 


		 		//B.初始化接收方密钥
		 		KeyFactory receiverKeyFactory = KeyFactory.getInstance("DH");
		 		X509EncodeKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(senderPublicKeyEnc);
		 		PublicKey receiverPublicKey = receiverKeyFactory.generatePublic(x509EncodedKeySpec);
		 		DPparameterSpec dhParameterSpec = ((DHPublicKey)receiverPublicKey).getParams();
		 		KeyPairGenerator receiverKeyPairGenerator = KeyPairGenerator.getInstance("DH");
		 		receiverKeyPairGenerator.initialize(dhParameterSpec);
		 		KeyPair receiverKeypair = receiverKeyPairGenerator.generateKeyPair();
		 		byte [] receiverPublicKeyEnc = receiverKeypair.getPublic().getEncoded();


		 		//C.密钥构建
		 		KeyAgreement receiverKeyAgreement = KeyAgreement.getInstance("DH");
		 		receiverKeyAgreement.init(receiverPrivateKey);
		 		receiverKeyAgreement.doPhase(receiverPbulic,true);
		 		SecretKey receiverDesKey = receiverKeyAgreement.generateSecrete("DES");//接收方key


		 		KeyFactory senderKeyFactory = KeyFactory.getInstance("DH");
		 		x509EncodeKeySpec = new X509EncodedKeySpec(receiverPublicKeyEnc);
		 		PublicKey senderPublicKey = senderKeyFactory.generatePublic(x509EncodedKeySpec);
		 		KeyAgreement senderKeyAgreement = KeyAgreeemnt.getInstance("DH");
		 		senderKeyAgreement.init(senderKeyPair.getPrivate());//初始化
		 		senderKeyAgreement.doPhase(senderPublicKey,true);
		 		SecretKey senderDesKey = senderKeyAgreement.generateSecrete("DES");/发送方key
	
		 		if(Objects.equals(receiverDesKey,senderDesKey)){
		 			System.out.println("双方密钥相同");
		 		}
	
		 		//D.加密
		 		Cipher cipher = Cipher.getInstance("DES");
		 		cipher.init(Cipher.ENCRYPT_MODE，senderDesKey);
		 		byte [] result = cipher.doFinal(clearText.getBytes());
		 		System.out.println("DH加密(十六进制):" + Base64.encodeBase64String(result));
	
		 		//E.解密
		 		cipher.init(Cipher.DECRYPT_MODE,receiverDesKey);
		 		result = cipher.doFinal(result);
		 		System.out.println("DH解密结果:" + new String(result));



			}catch(Exception e){}
		}


<1>RSA算法********************************************************
		/*
			【RSA算法传输数据~私加公解】
			发送者使用私钥加密数据
				~发送加密数据
					~接受者使用公钥数据
	
			【RSA算法传输数据-公加私解】
			发送者使用公钥加密数据
				~发送加密数据
					~接受者使用私钥解密数据
	
		*/
		public static void jdkRSA(String clearText){
			try{
				//A.初始化密钥
				 KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
				 keyPairGenerator.initialize(512);
				 KeyPair keyPair = keyPairGenerator.generateKeyPair();
				 RSAPublicKey rsaPublicKey = (RSAPublicKey)keyPair.getPublic();
				 RSAPrivateKey rsaPrivateKey = (RSAPrivateKey)keyPair.getPrivate(); 
				 System.out.println("公钥:" + Base64.encodeBase64String(rsaPublicKey.getEncoded()));
				 System.out.println("私钥:" + Base64.encodeBase64String(rsaPrivateKey.getEncoded()));


				 //B-1.加密[私钥加密,公钥解密]
				 PKCS8EncodedKeySpec pkscs8EncodedKeySpec = new PKCS8EncodedKeySpec(rsaPrivateKey.getEncoded());
				 KeyFactory keyFactory = KeyFactory.getInstance("RSA");
				 PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
	
				 Cipher cipher = Cipher.getInstance("RSA");
				 cipher.init(Cipher.ENCRYPT_MODE,privateKey);
				 byte [] result = cipher.doFinal(clearText.getBytes());
				 System.out.println("RSA加密(十六进制)-私加公解:" + Bse64.encodeBase64String(result));
	
				 //B-2.解密[私钥加密,公钥解密]
				 X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(rsaPublicKey.getEncoded());
				 keyFactory = KeyFactory.getInstance("RSA");
				 PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
	
				 cipher.init(Cipher.DECRYPT_MODE,publicKey);
				 result = cipher.doFinal(result);
				 System.out.prtinln("RSA解密(字符串)-私加公解:" + new String(result))；


				 //C-1.加密[公钥加密,私钥解密]
				 x509EncodedKeySpec  = new X509EncodedKeySpec(rsaPublicKey.getEncoded());
				 keyFactory = KeyFactory.getInstance("RSA");
				 publicKey = keyFactory.geteratePublic(x509EncodedKeySpec);
				 cipher = Cipher.getInstance("RSA");
				 cipher.init(Cipher.ENCRYPT_MODE,publicKey);
				 result = cipher.doFinal(clearText.getBytes());
				 System.out.println("RSA加密(十六进制)-公加私解:" + Bse64.encodeBase64String(result));



				 //C-2.解密[公钥加密,私钥解密]
				 pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(rsaPrivateKey.getEncoded());
				 keyFactory = KeyFactory.getInstance('RSA');
				 privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
				 cipher = Cipher.getInstance("RSA");
				 cipher.init(Cipher.DECRYPT_MODE,privateKey);
				 result = cipher.doFinal(result);
				 System.out.prtinln("RSA解密(字符串)-公加私解:" + new String(result))；


		    }catch(Exception e){}
		}


<1>ElGmal算法********************************************************
		/*
			【构建密钥对及加密数据传输过程】
			接受者构建密钥对
				~公布密钥
				~发送者使用公钥加密数据
					~发送加密数据
					~接受者使用私钥解密数据
		*/
		public static void bcElGmail(String clearTexte){
			//公钥加密,私钥解密
			Security.addProvide(new BouncyCastleProvider());
	
			//A.初始化密钥
			AlgorithmParameterGenerator algorithmParameterGenerator = AlgroithmParameterGenerator.getInstance("ElGmal");
			algorithmParameterGenerator.init(256);
			AlgorithmParameters algorithmParameters = algorithmParameterGenerator.generateParameters();
			DHParameterSpec dhParameterSpec = (DHParameterSpec)algorithmParameters.getParameterSpec(DHParameterSpec.class);
	
			KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("ElGamal");
			keyPairGenerator.initialize(dhParameterSpec, new SecureRandom());
			KeyPair keyPair = keyPairGenerator.generateKeyPair();
	
			PublicKey elGamalPublicKey = keyPair.getPublic();//公钥
			PrivateKey elGamilPrivateKey = keyPair.getPrivate();//私钥
	
			//B.加密解密操作【与其他算法类似】
				。。。。



		}

```
<br>

---
<br><br>


17_6.运用数字签名算法
---------------------------
&emsp;带有密钥(公钥,私钥)的消息摘要算法,使用私钥前面,使用公钥进行验证,用于验证数据完整性,认证数据来源,抗否认,遵循DSS(Digital Signature Standard~数字签名标准)

数字签名算法几种类型
+ RSA算法【包括MD.SHA两类,有JDK和BC提供实现】
+ DSA(Digital Signature Algorithm)算法 【仅包含数字签名,证书没法进行加解密,JDK和BC提供实现】
+ ECDSA(Elliptic Curve Digital Signature Algorithm~椭圆区县数字签名)算法【速度快,强度高,签名短,有JDK(7.0之前是没有提供的)和BC提供实现,应用与微软的产品,提供序列号的算法】

代码运用
```
<1>RSA算法*****************************************************************
		/*
			【构建密钥对】
			发送方构建密钥对
				~公布密钥
	
			【发送数据】
			发送方使用私钥对数据签名
				~发送签名,数据
				~接收方使用公钥,签名验证数据
		*/
		public static void jdkRSA(String clearText){
			try{
				//A.初始化密钥
				KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstrance("RSA");
				keyPairGenerator.initialize(512);
				KeyPair keyPair = keyPairGenerator.generateKeyPair();
				RSAPublicKey rsaPublicKey = (RSAPublicKey)keyPair.getPublic();//公钥验证
				RSAPrivateKey rsaPrivateKey = (RSAPrivateKey)keyPair.getPrivate();/私钥签名
	
				//B.执行签名
				PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PCKS8EncodedKeySpec(rsaPrivateKey.getEncoded());//私钥进行签名
				KeyFactory keyFactory = KeyFactory.getInstance("RSA");
				PrivateKey privateKey = kayFactory.generatePrivate(pkcs8EncodedKeySpec);
				Signature signature = Signature.getInstance("MD5withRSA");
				signature.initSign(privateKey);
				signature.update(clearText.getBytes());//处理明文
	
				byte [] result = signature.sign();
				System.out.println("RSA的jdk(十六进制):"+ hex.encodeHexString(result));
	
				//C.验证签名
				X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(rsaPublicKey.getEncoded);//公钥验证签名
				keyFactory = KeyFactory.getInstance("RSA");
				PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
				signature = Signature.getInstance("MD5withRSA");
				signature.initVerify(publicKey);
				signature.update(clearText.getBytes());
				boolean bool = signature.verify(result);
				System.out.println("验证结果:"+ bool);
	 
			}catch(Exception e){}
		}


<2>DSA算法*****************************************************************

		/*
			【构建密钥对】
			 发送方构建密钥对
			 	~公布密钥
	
			 【发送数据】
			 发送方使用私钥对数据签名
			 	~发送签名,数据
			 	~接收方使用公钥,签名验证数据
		*/
		public static void jdkDSA(String clearText){
			try{
				//A.初始化密钥
				KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("DSA");
				keyPairGenerator.initialize(512);
				KeyPair keyPair = keyPairGenerator.generateKeyPair();
				DSAPublicKey dsaPublicKey = (DSAPublicKey)keyPair.getPublic();
				DSAPrivateKey dsaPrivateKey = (DSAPrivateKey)keyPair.getPrivate();
	
				//B.执行签名
				PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(dsaPrivateKey.getEncoded());
				KeyFactory keyFactory = KeyFactory.getInstance("DSA");
				PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
				Signature signature = Signature.getInstance("SHA1withDSA");
				signature.initSign(privateKey);
				signature.update(clearText.getBytes());
				byte [] result = signature.sign();
				System.out.println("DSA数字签名(十六进制):" + Hex.encodeHexString(result));
	
				//C.验证签名
				X509EncodedKey Spec x509EncodedKeySpec = new X509EncodedKeySpec(dsaPublicKey.getEncoded());
				keyFactory = KeyFactory.getInstance("DSA");
				PublicKey publicKey = keyFactory.getInstance("DSA");
				signature.initVerify(publicKey);
				signature.update(clearText.getBytes());
				boolean bool = signature.verify(result);
				System.out.println("DAS数字签名验证结果:" +  bool);


			}catch(Exception e)
		}


<3>ECDSA算法*****************************************************************
		/*
			【构建密钥对】
			 发送方构建密钥对
			 	~公布密钥
	
			 【发送数据】
			 发送方使用私钥对数据签名
			 	~发送签名,数据
			 	~接收方使用公钥,签名验证数据
		*/
		public static void jdkECDSA(String clearText){
			try{
				//A.初始化密钥
				KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("EC");
				KeyPairGenerator.initialize(256);//初始长度
				KeyPair keyPair = keyPairGenerator.generateKeyPair();
				ECPublickEey ecPublicKey = (ECPublicKey)keyPair.getPublic();
				ECPrivate ecPrivateKey = (ECPrivateKey)keyPair.getPrivate();
	
				//B.执行签名
				PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PCKS8EncodedKeySpec(ecPrivateKey.getEncoded());
				KeyFactory keyFactory = KeyFactory.getInstance("EC");
				PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
				Signature signature = Signature.getInstance("SHA1withECDSA");
				signature.initSign(privateKey);
				signature.update(clearText.getBytes());
				byte [] result = signature.sign();
				System.out.println("ECDSA数字签名(十六进制):" + result);
	
				//C.验证签名
				X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(ecPublicKey.getEncoded());
				keyFactory = KeyFactory.getInstance("EC");
				PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
				signature.initVerify(publicKey);
				signature.update(clearText.getBytes());
				boolean bool = signature.verify(result);
				System.out.println("ECDSA数字签名结果:" + boool);


			}catch(Exception e){}
		}


```
<br>
---
<br><br>


18_1.JSP开发
--------------------------
&emsp;JSP全名Java Server Pages,其根本是一个简化的Servlet设计,实现了在Java中使用HTML标签.JSP是一种动态网页技术,标准是JAVAEE标准.JSP与Servlet一样,实在服务器端执行的
<br>



常见动态网站开发技术对比
+ JSP【Java平台,安全性高,适合开发大型的,企业级的Web应用程序】
+ ASP.net【.Net平台,简单易学,可视化组件开发,但是安全性以及跨平台性差(仅支持微软的产品)】
+ Php【简单,高效,成本低开发周期短,特别适合中小型企业的Web应用开发(LAMP(开发环境组合):Linux+Apache+MySQL+PHP)】
  <br>


会话跟踪技术的四种方法
+ 隐藏表单域 【input的type属性设置为hidden,适合不需要大量数据存储的会话应用】
+ URL重写 【URL后面附加参数】
+ Cookie
+ Session 【将对象捆绑到会话】
  <br>

JSP状态管理
+ http协议的无状态性
+ 两种保存用户状态的机制
+ Session与Cookie对比
```
<1>http协议的无状态性
	指当浏览器发送请求给服务器的时候,服务器响应客户端请求，
	但是当同一个浏览器再次发送请求给服务器的时候,服务器并不知道它就是刚才哪个浏览器
	【服务器不会去记得你】

<2>两种保存用户状态的机制
	a.Cookie【是WEb服务器保存在客户端的一系列文本信息】
		(1)作用
			.对特定对象的追踪
			.保存用户网页浏览器记录与习惯
			.简化登录
		(2)使用
			Cookie newCookie = new Cookie(String key, Object value);//创建
			response.addCookie(newCookie);//添加
			Cookie [] cookies = request.getCookies();//读取
			String username = URLDecoder.decode(cookies[0].getValue(),"utf-8");//获取Cookie值与解码
		(3)常用方法
			void setMaxAge(int expiry)   __设置cookie有效期(单位:s/秒)
			void setValue(String value)  __创建Cookie后,对Cookie进行赋值
			String getName()			 __获取cookie名称
			String getValue()  			 __获取cookie值
			int getMaxAge()  			 __获取cookie有效时间(单位:s/秒)
	
	b.Session

<3>Session与Cookie对比
	Session    	~   	Cookie
	[在服务器端保存用户信息]  ~		[在客户端保存用户信息]
	[保存的是Object类型]      ~		[保存的是String类型]
	[随会话的结束而将储存的数据销毁] 	~	[cookie可以长期保存在客户端]
	[保存重要的信息]  	~ 	[保存不重要的信息] 
	[可以保存任意大小的对象类型]   ~ 	[保存cookie个数,大小都有限制]
```
<br>




两种表单提交方式【Get和Post】
```
<1>代码
	<form name="regForm" action="提交之后动作" method="提交方式"></form>

<2>区别
	a.get 
		以明文的方式通过URL提交数据,数据在URL可以看到.提交的数据最多不超过2KB，安全性较低,但效率比post高。适合提交数据量不打,安全性不高的数据.比如:搜索,查询等功能
	b.post
		将用户提交的信息封装在HTML HEADER内.适合提交数据量大,安全性高的用户信息.比如:注册,修改,上传等功能
```
<br>



两种请求处理
+ 【请求转发】服务器行为,`reqeust.getRequestDispatcher("../xxx.jsp").forward(request,response);`是一次请求,转发后请求对象会保存,地址栏的URL地址不会改变
+ 【请求重定向】客户端行为,`response.sendRedirect("../xxx.jsp")`,从本质上讲等同于两次请求,前一次的请求对象不会保存,地址栏的URL会改变
  <br>




JavaBean设计
+ 简介
+ 设计原则
+ 在jsp页面中使用
+ 作用域【scope="作用域"】
+ Model1模式
```
<1>简介
	JavaBeans就是符合某种特定的规范的Java类,使用JavaBeans的好处是解决代码的重复编写,减少代码冗(rong)余,功能区分明确,提高了代码的维护性

<2>设计原则
	.共有类
	.包含无参公有构造方法
	.属性私有
	.getter和setter方法

<3>在jsp页面中使用
	a.普通方式
		<%@ page import="com.po.User"%> <%--page指令导入User类--%>
		<%
			User user = new User();
				user.setName("皮皮");
				user.setPassword("123456");
		%>
		用户名:<%=user.getName() %>
		密码:  <%=user.getPassword() %>
	
	b.使用动作标签
		<jsp:userBean id="user" class="com.po.User" scope="page" />
	
		<!--根据提交表单自动匹配所有元素-->
		<jsp:serProperty name="user" property="*" />
	
		用户名:<jsp:getProperty name="user" property="name" />
		用户值:<jsp:getProperty name="user" property="password" />


<4>作用域【scope="作用域"】
	a.page  __仅在当前页面有效
	b.request __可以通过HttpRequest.getAttribute()获得JavaBean对象
	c.session __可以通过HttpSession.getAttribute()获取JavaBean对象
	d.application __可以通过application.getAttribute()获得JavaBean对象


<5>Model1模式
	a.前景
		Model模型出现前,整个Web应用的情况:几乎全部由JSP页面组成,JSP页面接收处理客户端请求,对请求处理后直接做出响应
	
		弊端：在界面层充斥大量的业务逻辑的代码和数据访问层代码,web程序的可扩展性和可维护性非常差
	
	b.模型
				 (1)请求	   (2)            (3)
		1.浏览器 ~~~~~~~> JSP <~~~> JavaBean <~~~> 企业数据库
				<~~~~~~~
			     (4)响应
```
<br>




JSP动作元素
+ 定义
+ 标签分类
+ include指令与include动作的区别
```
<1>定义
	JSP动作元素(action elements),动作元素为请求处理阶段提供信息。动作元素遵循XML元素语法,有一个包含元素名的开始标签,可以有属性,可选的内容,与开始标签匹配的结束标签

<2>标签分类
	a.存储JavaBeans有关的
		<jsp:userBean>
			例：<jsp:userBean id="标识符" class="包名.类名" scope="作用范围" />
		<jsp:setProperty>
			例:【四种形式】
				<jsp:setProperty name="Java实例名(id名)" property="*" /> //根据表单自动匹配所有属性
				<jsp:setProperty name="" property="属性名" />  //仅自动匹配指定属性名
				<jsp:setProperty name="" property="属性名" value="属性值" /> //手工设置属性为指定值
				<jsp:setProperty name="" property="属性名" param="属性值为reqeust的指定参数名的值">
		<jsp:getProperty>
			例：<jsp:getProperty name="Java实例名(id)" property="属性名" />//获取指定对象的属性值
	
	b.JSP1.2就开有的基本元素,6个动作元素
		<jsp:include>
			例:<jsp:include page="被包含的页面" flush="true | false" />
					//flush表示被包含的页面是否从缓冲区读取
		<jsp:forward>
			例:<jsp:forward page="URL" /> //请求转发,等同于request.getRequestDispatcher("/url").forward(request,response);
		<jsp:param>
			例:<jsp:param name="参数名" value="参数值" /> //请求参数,可传递新的也可由修改原有的参数【与<jsp:forward一起使用,作为其子标签】
		<jsp:plugin>
		<jsp:params>
		<jsp:fallback>
	c_1.JSP2.0新增的6个元素,与JSP Document有关
		<jsp:root>
		<jsp:declaration>
		<jsp:scriptlet>
		<jsp:expression>
		<jsp:text>
		<jsp:output>
	c_2.JSP2.0新增的3个元素,动态生成XML元素标签的值
		<jsp:attribute>
		<jsp:body>
		<jsp:element>
	c_3.JSP新增的2个元素,主要用在Tag File中
		<jsp:invoke>
		<jsp:dobody>


<3>include指令与include动作的区别
	a.语法格式不同
	b.include指令在"页面转换期间"发生作用,而动作在"请求期间"发生作用
	c.include指令包含的内容为"文件的实际内容",动作为"页面的输出"
	d.include指令的"主页面和包含页面转换为一个Servlet",动作为"主页面和包含页面转换为独立的Servlet"
	e.编译时间,include指令"较慢",动作"较快"
	f.执行时间,include指令"稍快",动作"较慢(每次请求资源必须被解析)"


```
<br>



JSP基本语法
```
<1>指令(<%@page 属性值1="属性值" %>)
	page     【通常位于jsp页面的顶端,同一个页面可以有多个page指令】
		language    __指定JSP页面使用的脚本语言(默认java)
		import      __通过该属性来引入脚本语言中使用到的类文件(java包)
		contentType __用来指定jsp页面所采用的编码方式(默认:text/html,ISO-8859-1)
	
	include  【将外部文件嵌入到当前jsp文件中,同时解析这个页面的JSP语句】
		例:
			<%@ include file="要包含页面的URL" %>
	
	tablib   【使用标签库定义新的自定义标签,在jsp页面中启用定制行为】


<2>注释
	<!--HTML注释-->   【客户端源代码可见】
	<%--JSP注释--%>   【客户端不可见】
	//单行注释
	/*多行注释*/


<3>脚本
	<%  Java代码 %>  


<4>声明
	<%! Java代码 %>  【声明全局变量,字段 or 方法,可供jsp脚本调用(注意:jsp脚本里声明的变量是局部变量)】


<5>表达式
	<%=表达式 %>  【直接调用字段 or 方法,不用以分号‘;’结束】


<5>JSP页面生命周期
	1.用户发出index.jsp请求
	2.判断用户访问的资源是否是第一次请求
		a.是  
			————JSP引擎把该JSP文件转换成为Servlet(本质是一个java类),生成字节码文件,并执行spInit()【只在编译生成字节码文件的时候才执行】
			___生成的字节码文件
		b.否
			————生成的字节码文件
	
	3.解析执行,jspService()
	
	 注意点：
		A.jspService()方法被调用来处理客户端的请求。对每一个请求,JSP引擎创建一个新的线程来处理该请求。如果有多个客户端同时请求该JSP文件,则JSP引擎会创建多个线程,每个客户端请求对应一个线程。以多线程方式执行可以大大降低对系统的资源需求,提高系统的并发量及响应时间。但也要注意多线程的编程带来的同步问题,由于该Servlet始终驻与内存,所以响应是非常快的
	
		B.当用户第一次请求jsp页面时,首先被执行的是"构造方法"
```
<br>


JSP内置对象
+ 【定义】是Web容器创建的一组对象,不使用new关键字就可以使用内置对象,有九大内置对象
+ out
+ request    【用户请求】
+ response   【用户响应】
+ session
+ application
+ page
+ pageContext
+ config
+ exception
```
<1>out对象【JspWriter类的实例,向客户端输出内容的对象】
		a.缓冲区(Buffer)
			是内存的一块区域用来保存临时数据
		b.常用方法
			(1)void println()		__向客户端打印字符串
			(2)void clear()			__清除缓冲区内容,如果flush之后调用会抛出异常
			(3)void clearBuffer()   __清楚缓冲区内容,如果在flush之后调用不会抛出异常
			(4)void flush()         __将缓冲内容输出到客户端
			(5)int getBufferSize()  __返回缓冲区以字节数的大小,如果不设缓冲区则为0
			(6)int getRemaining()   __返回含缓冲区还剩余多少可用
			(7)boolean isAutoFlush()__放回缓冲区满时,是自动清空还是抛出异常
			(8)void close()         __关闭输出流


<2>request对象
	a.定义
		客户端的请求信息被封装在request对象中,通过它才能了解到客户的需求,然后做出响应,它是HttpServletRequest类的实例。request对象具有请求域,即完成客户端的请求之前,该对象一直有效
			
	b.常用方法
		String getParameter(String name)  __返回name参数名的参数值
		String [] getParameterValues(String name) __返回name参数名的所有参数值数组
		void setAttribute(String,Object)  __储存给request请求设置参数键值队
		object getAttribute(String name)  __返回name参数名的参数值对象
		String getContextType()			  __得到请求体的MIME类型
		String getProtocol()			  __返回请求用的协议类型以及版本号
		String getServerName()			  __返回接收请求的服务器主机名
		int getServerPort()               __返回服务器接收此请求所用的端口号
		void setCharacterEncoding("编码") __设置请求的字符编码【解决中文乱码】
		String getCharacterEncoding()     __返回字符编码方式
		int getContectLength()            __返回请求体的长度(以字节数)
		String getRemoteAddr()   		  __返回发送此请求的客户端IP地址
		String getRealPath(String path)   __返回虚拟路径的真实路径【括号内可是补充到最后的path】
		String request.getContextPath()   __返回上下文路径



<3>response对象
	a.定义
		response对象包含了响应客户请求的有关信息,但在JSP中很少直接用到它.它是HttpServletResponse类的实例。response对象具有页面作用域,即访问一个页面时,该页面内的response对象只能对此次访问有效,其他页面的response对象对当前页面无效
	b.常用方法
		String getCharacterEncoding()  __返回响应用时使用的字符编码
		void setContentType(String type) __设置相应的MIME类型
		PrintWriter getWriter()			  __返回可以向客户端输出字符的一个对象【注:需比较,与out内置对象的区别(PrintWriter对象打印输出,总是咸鱼out内置对象打印输出)】
		sendRedirect(java.lang.String.location) __重新定向客户端的请求



<4>session对象
	a.定义
		ssession表示客户端与服务器的一次会话,web中的session指的是用户在浏览某个网站时,从进入网站到浏览器关闭所经过的这段时间,也是用户浏览这个网站所花费的时间;因此从上述定义中可以看到,Session实际上是一个特点的时间概念,在服务器的内存中保存着不同用户的session,与用户一一对应;
			.session对象在第一个JSP页面被装载时自动创建,完成会话期管理
			.从一个客户打开浏览器并连接到服务器开始,到客户管理浏览器离开这个服务器结束,称为一个会话
			.当一个客户访问一个服务器时,可能会在服务器的几个页面之间切换,服务器应当通过某种办法知道这是一个客户,就需要session对象
			.session对象实例是HttpSession类的实例
	
	b.常用方法
		long getCreateTime()     __返回session创建时间
		public String getId()    __返回session创建时jsp引擎为它设的唯一ID号
		public Object setAttribute(String name,Object value)   _使用只i定名称将指定对象绑定到此会话
		public Object getAttribute(String name)    __返回此会话中的指定名称的指定对象,若不存在,返回null
		String [] getValueNames()  __返回一个包含此seesion中所有可用属性名数组
		void setMaxInactiveInterval(int time)  __设置session存在时间,指定时间后过期
		int getMaxInactiveInterval()   __返回session几秒钟过期
	
	c.生命周期
		(1)创建
			当客户端第一次访问某个jsp或者Servlet时,服务器会为当前会话创建一个SessionId,每次客户端向服务端发送请求时,都会将此SessionId携带过去,服务端会对此SessionId进行校验
		(2)活动
			.某次会话中通过超链接打开的新页面属于通过一次会话
			.只要当前会话的页面没有全部关闭,重新打开新的浏览器窗口访问同一项目资源时,仍然属于同一次会话
			.本次会话的所有页面都关闭情况下，再次重新访问某个jsp或Servlet时会创建新的会话
			.【注意】创建新的会话,原有的会话仍然存在,只是这个旧的SessionId仍然存在于服务端,只不过再也没有客户端会携带它然后交于服务端校验,超时之后才会自动销毁
		(3)销毁【三种方式】
			.调用session.invalidate()
			.Session过期(超时)【Tomcat默认session超时是30分钟】
				+ session.setMaxInactiveInterval(时间)
				+ 在web.xml里配置
						<!--Session10分钟后过期-->
						<session-config>
							<session-timout
							 10
							 </session-timeout>
						</session-config>
			.服务器重新启动

<5>application对象
	a.定义
		实现了用户间数据的共享,可存放全局变量
		该对象开始于服务器的启动,终止于服务器的关闭
		在用户的前后连接或不同用户之间的连接中,可以对application对象的同一属性进行操作
		在任何地方对application对象属性的操作,都将影响到其他用户对此的访问
		服务器的启动和关闭决定了application对象的生命
		application对象是ServletContext类的实例
	
	b.常用方法
		public void setAttribute(String name,Object value) __设置指定名称的指定对象值,绑定到此会话
		public Object getAttribute(String name)   __返回与此会话中指定名称绑定的对象,若不存在,返回null
		Enumeration getAttributeNames()  __返回所有可用属性名的枚举
		String getServerInfo()		__返回JSP(SERVER)引擎及版本号


<6>page对象
	a.定义
		指向当前jsp页面本身,有点像类中的this指针,它是java.lang.Object类的实例
	
	b.常用方法
		class getClass()		__返回此Object的运行时Class
		int hasCode()			__返回此Object的hash码
		boolean equals(Object obj)	__判断相等
		void copy(Object obj)  __拷贝到指定obj对象
		Object clone()   	   __克隆Object对象
		String toString()      __转换成String类的对象
		void notifyAll()       __唤醒一个等待线程
		void notifyAll()	   __唤醒所有等待线程
		void wait(int timeout) __使一个线程处于等待状态直到timeout结束or被唤醒
		void wait()            __使一个线程处于等待直到被唤醒	


<7>pageContext对象
	a.定义
		提供了对JSP页面内所有的对象及名字空间的访问
		可以访问到本页所在的session,也可以提取本页面所在的application的某一属性值
		该对象相当于页面中所有功能的集大成者
		对象本类名实例也叫做pageContext
	
	b.常用方法
		JspWrite getOut()		__返回当前客户端响应被使用的JspWriter流(out)
		HttpSession getSession() __返回当前页中的HttpSession对象(session)
		Object getPage() 		__返回当前页的Object对象(page)
		ServletRequest getRequest()  __返回当前页的ServletRequest对象(reqeust)
		ServletResponse getResponse  __返回当前页的ServletResponse对象(response)
		void setAttribute(String name,Object obj);   __设置属性名与值
		Object getAttribute(String name,int scope)   __返回指定名称,作用域的值对象
		int getAttributeScope(String name)   __返回某属性的作用范围
		void forward(String relativeUrlPaht) __使当前页面重导到另一页面
		void include(String relativeUrlPath) __在当前位置包含另一文件


<8>Config对象
	a.定义
		config对象是在一个Servlet初始化时,JSP引擎向它传递信息用的,此信息包括Servlet初始化时所要用到的参数(通过属性名和属性值构成)以及服务器的有关信息(通过传递一个ServletContext对象)
	
	b.常用对象
		ServletContext getServletContext()  __返回含有服务器有信息的ServletContext对象
		String getInitParameter(String name)  __返回初始化参数的值
		Enumeration getInitParameterNames()   __返回Servlet初始化所需所有参数的枚举


<9>Exception对象
	a.定义
		该对象是一个异常对象,当一个页面运行过程中发生了异常,就产生这个对象.如果一个JSP页面要应用此对象,就必须在开头page指令中添加属性,isErrorPage="true"【默认是false】,否则无法编译.实际上是java.lang.Throwable的对象
		
	b.常用方法
		String getMessage()   __返回描述异常的消息
		String toString()     __返回关于异常的简短描述消息
		void printStackTrace() __显示异常及其栈轨迹
		Throwable FillInStackTrace()  __重写异常的执行轨迹
```
<br>

---
<br><br>



18_2.Servlet基础
---------------------------
&emsp;Servlet是在服务器上运行的小程序,一个Servlet就是一个Java类,并且可以通过“请求-响应”编程模型来访问的这个驻留在服务器内存里的Servlet程序
<br>


Tomcat容器等级
```
<1>概念与分级
	a.Tomcat容器分为四个等级
		(1)Container容器
		(2)Engine容器  【引擎容器】
		(3)HOST容器 【主机容器】
		(4)Context容器
	b.Servlet的容器管理Context容器
	c.一个COntext对应一个Web工程
```
<br>


Tomcat装载Servlet的三种情况
+ Servlet容器启动时会根据web.xml配置文档自动装载某些Servlet
+ Servlet容器启动后,客户端首次向Servlet发送请求
+ Servlet类文件被更新后
```
<1>Servlet容器启动时会根据web.xml配置文档自动装载某些Servlet【Tomcat启动后自动装载】
		需在配置文件加入如下代码: 
			<Servlet>
					......
					<loadon-startup>1</loadon-startup> <!--数字越小优先级别越高--> 
			</Servlet>


<2>Servlet容器启动后,客户端首次向Servlet发送请求
		Tomcat容器进行装载Servlet


<3>Servlet类文件被更新后
		Tomcat容器重新装载Servlet



注意:
	a.Servlet一旦被加载后,Servlet对象会长期保存在服务器的内存当中
	b.Servlet被装载后,Servlet容器会创建一个Servlet实例并调用Servlet的init()将进行初始化。整个Servlet生命周期内,init()只会被调用一次
	c.构造方法在init()方法执行前执行

```


Servlet生命周期
+ 初始化
+ 加载
+ 实例化
+ 服务和销毁
```
<1>初始化阶段,调用init()
<2>响应客户请求阶段,调用service()【由service()根据提交方式选择执行doGet()或者doPost()】
<3>终止阶段,调用destroy()【服务器终止的时候】


	注意：Servlet编写doPost()方法时,
		会抛出ServletException和IOException异常
```
<br>



Servlet对应JSP的九大内置对象
```
【JSP对象】  ~~~   【Servlet获得】
   out 					response.getWriter();
   reqeust 				service()的request
   response  			service()的response
   session  			request.getSession();
   application   		getServletContext();
   exception 			Throwable
   page 				this
   pageContext  		PageContext
   Config 				getServletConfig();
```
<br>

Servlet应用
+ 获取初始化参数
+ Mdeol2模型
```
<1>获取初始化参数
	【在web.xml中配置Servlet时,可以配置一些初始化参数。而在Servlet中可以通过ServletConfig接口提供的方法来取得这些参数】
		a.在web.xml里
			<servlet>
				......
				<init-param>
					<param-name>username</param-name>
					<param-value>suvan</param-value>
				</init-param>
			</servlet>
	
		b.Servlet的init()里
			public void init() throws ServletException{
				String username = this.getInitParameter("username");
			}



<2>Mdeol2模型
	【Java Web的Model2开发模型就是MVC思想的体现】
		JavaBean(M)
		JSP(V)
		Servlet(C)
		DB

```
<br>

Servlet模版代码
```
<1>程序代码
		public class MyServlet extends HttpServlet{
			@Override
			protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
				//super.doGet(request,response);
	
				String name = request.getParameter("name");//获取name参数
				String [] facorite = request.getParameterValues("favorite");//获取表单复选框的多个参数
	
				Systen.out.println("处理Get请求.....");
	
				PrintWriter out = response.getWriter();
				response.setContentType("text/html;charset=utf-8");
				out.println("<strong>在web页面打印输出</strong><br>")
			}
	
			@Override
			protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
				super.doPost(request,response);
	
				//请求转发
				reqeust.getRequestDispatcher("../xxx.jsp").forward(request,response);
	
				//请求重定向
				response.sendRedirect("test.jsp");
	
			}
		}

<2>在web项目的,web.xml里注册Servlet
		<servlet>
			<servlet-name>MyServlet</servlet-name>
			<servlet-class>com.MyServlet</servlet-class>
		</servlet>
		<servlet-mapping>
			<servlet-name>MyServlet</servlet-name>
			<url-pattern>/com/MyServlet</url-pattern>
		</servlet-mapping>

<3>浏览器hello项目访问servlet
	http:/localhost:8080/HelloApp/com/MyServlet

```
<br>

---
<br><br>


18_3.过滤器
------------------------
&emsp;定义是一个服务器端的组件,可以截取用户端的请求与响应信息,并对这些信息进行过滤【过滤源,过滤规则,过滤结果】
<br>


工作原理
```
web容器启动时加载过滤器


		___发送请求___>                               __过滤器将用户请求发送至Web资源___>
用户                                           过滤器                                         Web资源
        <___过滤器将Web资源的响应发送给用户__         <__资源响应发送至过滤器__

```
<br>

生命周期

1. 实例化【在web.xml进行配置,启动容器则加载过滤器,仅此1次】、
2. 初始化【过滤器启动成功,加载初始化信息,调用过滤器的init(),仅次1次】
3. 过滤  【每次过滤器捕获用户请求,都会执行doFilter(),执行N次】
4. 销毁  【web容器关闭时,调用过滤器的destroy()进行销毁处理的工作】

<br>


过滤器类型(在web.xml的filter标签dispatcher标签配置)
```
Servlet2.5的四种类型
		REQUEST  【默认,用户直接访问页面时,web容器调用过滤器】
		FORWARD  【目标资源是通过ReqeustDispatcher的forward()访问时,即请求转发,调用过滤器】
		INCLUDE  【目标通过RequestDispatcher的include()访问时,请求通过包含方式获得时(在本页面中同时包含了下一个url的内容),调用过滤器】
		ERROR    【目标资源通过声明式异常处理机制调用时,调用过滤器】

Servlet3.0的添加的一种类型
		ASYNC    【目标资源是异步处理时,过滤器将被调用】  
```
<br>


多个过滤器组成过滤器链子
```
<1>服务器会按照web.xml中过滤器的定义的先后顺序组成过滤器链
​	
	用户请求——>过滤器1——>过滤器2——>过滤器3——>web资源


<2>过滤链执行过滤

	过滤器1【释放请求前】
	过滤器2
		web资源
	过滤器2 【释放请求后】
	过滤器1


	用户请求--->Code1【放行前代码】                  ---> Code2
				  |                                 |         |
				  Chain.doFilter【释放请求】   ----      Chain.doFilter  --->     Servlet的Service()
				  																  			|
				  																 			|
				Code1【放行后】		<----           Code2        <--------------	


<3>初始化顺序号和销毁顺序
	 按照web.xml的定义顺序,依次初始化,依次销毁

```
<br>



注意：
+ 过滤器可以更改用户请求的路径
+ 过滤器不能直接返回数据,不能直接处理用户请求【过滤器不是标准的Servlet,不能直接把数据直接返回到用户,只能重定向or请求转发到指定web资源】
  <br>


过滤器代码
+ 程序代码
+ web.xml内配置
```
<1>程序代码
		public class MyFilter implements Filter{

			 public FilterConfig config;     //FilterConfig对象访问web.xml里初始化的参数

			@Override
			public void init(FiterConfig arg0) throws ServletException{
				  System.out.println("MyFilter--init()--->初始化");
	    		  this.config = filterConfig;
			}
	
			@Override
			public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2) throws IOException,ServletException{
	
					/*
			          * servlet理论上可以处理多种形式的请求响应形式,http只是其中之一
			          * HttpServletRequest, HttpServletResponse分别是ServletRequest和ServletResponse的子类,多了一些针对http协议的方法
			          *  4个都是接口
	      			*/
	      			  //1.定义
				        HttpServletRequest httprequest = (HttpServletRequest)request;
				        HttpServletResponse httpresponse = (HttpServletResponse)response;
				        HttpServletResponseWrapper httpresponsewrapper = new HttpServletResponseWrapper(httpresponse);
	
				        //过滤器对请求进行编码转换
				        request.setCharacterEncoding("UTF-8");
	
				       //2.操作Cookie
				        //this.useCookie(httprequest,httpresponse);
	
				        //3.是否执行过滤器
				        String disabletestfilter = config.getInitParameter("disabletestfilter");            //获取配置文件参数,判断过滤器是否有效
				        if (disabletestfilter.toUpperCase().equals("YES")) {    //web.xml设置为YES则过滤器无效,直接跳过该过滤器
				           //把请求传回过滤链
				            chain.doFilter(request, response);  //检查过滤链是否还有其他filter,有就调用,没有就调用目标资源
				            return;
				        }


				        //4.进入博客首页index
				        String includeSuffix = config.getInitParameter("includeSuffix");                    //指定后缀index.jsp
				        String url = httprequest.getRequestURI();
				        if ((url.equals("/blog/")  || (url.indexOf(includeSuffix) != -1))){  //不是欢迎页面 && 不是指定index.jsp页面则跳过
	
				            httprequest.getRequestDispatcher("/article/index").forward(request,response);
				            return;
				        }



				        //请求转发【服务器内部转发,/表示当前web应用程序跟目录】【url不会显示出链接地址】【会导致指定页面的CSS和js的相对路径是指当前CSS的,需把改为绝对路径】
				        //httprequest.getRequestDispatcher("/html/register.html").forward(request,response);
	
				        //请求重定向【服务器外请求】
				        //httpresponsewrapper.sendRedirect("/My_Blog/html/register.html");


				        System.out.println("---------------------->请求仍然停留在IndexFilter过滤器");
			}
	
			@Override
			public void destroy(){
					System.out.println("MyFilter--destroy()--->销毁");
	    			this.config = null;
			}


			 /*【自定义函数】过滤器中操作Cookie*/
		    public void useCookie(HttpServletRequest httprequest,HttpServletResponse httpresponse){
	
		        //1.构造Cookie
		        Cookie myCookie = new Cookie("myRecord", "888888888");
		        myCookie.setSecure(true);       //使用加密传输
		        myCookie.setHttpOnly(true);     //将Cookie设置成HttpOnly[指示浏览器不要在除HTTP（和 HTTPS)请求之外暴露Cookie],防止XSS攻击【跨站点脚本攻击(例如js引用document.cookie0】
		                                        //Servlet 2.5 API 不支持 cookie设置HttpOnly,Servlet3.0支持
		        //2.客户端添加Cookie
		        httpresponse.addCookie(myCookie);
	
		        //3.读取客户端Cookie
		        Cookie[] cookies = httprequest.getCookies();
		        for(int i = 0;i < cookies.length;i++){
		            System.out.println("Cookie\n名字:" + cookies[i].getName() +
		                                "\n值:" + cookies[i].getValue());
	
		            //可选删除指定Cookie
		            if("myCookie".equals(cookies[i].getName())){
		                cookies[i].setMaxAge(0);                    //有效期设置为0
		                httpresponse.addCookie(cookies[i]);            //将cookie重新添加到响应头中
		            }
		        }
	}
	
		}


<2>web.xml配置
	<filter>
		<filter-name>过滤器名字</filter-name>
		<filter-class>过滤器类</filter-class>
		<init-param>
			<description>描述信息【可省略】</description>
			<param-name>参数名</param-name>
			<param-value>参数值</param-value>
		</init-param>
		<init-param>
	        <param-name>参数名2</param-name
	        <param-value>参数值2</param-value>
	    </init-param>
	</filter>
	<filter-mapping>
		<filter-name>过滤器名</filter-name>
		<url-pattern>URL正则路径</url-pattern> <!--设置 filter 所拦截的请求路径(过滤器关联的URL)  /*[路径映射],*.[扩展],/[default servlet]-->
		<dispatcher></dispatcher><!--过滤器分类(四种过滤器),可以设置的值:REQUEST,INCLUDE,FORWARD,ERROR-->
	</filter-mapping>
```
<br>

---
<br><br>


18_4.监听器
-----------------

定义
+ Servlet规范中定义的一种特殊类
+ 用于监听ServletContext,HttpSession和ServletRequest等域对象的创建与销毁事件
+ 用于监听域对象的属性发生修改的事件
+ 可以在事件发生前,发生后做一些必要的处理
  <br>


监听器顺序与优先级
+ 【顺序】安装监听器在web.xml的注册顺序进行加载
+ 【优先级】监听器 > 过滤器 > Servlet
+ Servlet2.5中监听器顺序是按照注册顺序,Servlet3.0中无法决定顺序
  <br>


监听器分类
+ 对象划分
+ 事件划分
```
<1>对象划分
	a.用于监听应用程序环境对象(ServletContext)的事件监听器
	b.用于监听用户会话对象(HttpSession)的事件监听器
	c.用于监听请求消息对象(ServletRequest)的事件监听器

<2>事件划分
	a.监听域对象自身的创建和销毁
		ServletContext【唯1】  ——>  ServletContextListener【n个】
			用途:定时器,全局属性对象
		HttpSession【唯1】   ——> 	HttpSessionListener【n个】
			用途：统计在线人数,记录访问日志
		ServletRequest【唯1】 ——>  ServletRequestListener【n个】
			用途:读取request参数,记录访问历史
	
	b.监听域对象中的属性的增加和删除【不同作用域的属性进行添加,替换(当属性已经存在时,再次添加,则进行替换),移除时调用指定监听器】
		ServletContext   ——>  ServletContextAttributeListener
		HttpSession  ——>  HttpSessionAttributeListener
		ServletRequest ——>  ServletRequestAttributeListener
	
	c.监听绑定到HttpSession域的某个对象状态【注意:下面两个监听器不需要在web.xml文件中注册,要进行钝化活化,监听器还得实现Serializable(序列化)接口】
		HttpSessionBindingListener
			valueBound(绑定)  ——> valueUnbound(解除钝化)
		HttpSessionActivationListener
			sessionWillPassivate(钝化)  ——> sessionDidActivate(活化)
	
		session钝化机制本质是【由SessionManager管理】:
			将服务器不经常使用的session对象暂时序列化到系统文件or数据库系统中,当需要使用时则反序列化到服务器内存中,整个过程由服务器自动完成
```
<br>



Tomcat中有两种Session钝化管理器
```
<1>org.apache.catalina.session.StandaradManager
		(1)当Tomcat服务器被关闭or重启时,tomcat服务器会将当前内存中的Session对象钝化到服务器文件系统中
		(2)另一种情况是Web应用程序被重新加载时,内存中的Session对象也会被钝化到服务器的文件系统中
		(3)钝化后的文件被保存在Tomcat的指定路径[/work/Catalina/hostname/application/SESSION.ser]

<2>org.apache.catalina.session.Persistentmanager
		(1)首先在钝化的基础上进行了扩张。第一种如上1,第二种如上2.第三种情况,可以配置主流内存的Session对象数目,将不常使用的Session对象保存到文件系统or数据库,需要用时再重新加载
		(2)默认情况下,Tomcat提供两种钝化驱动类
		(3)org.apache.Catalina.FileStore和org.apache.Catalina.JDBCStore
```
<br>



监听器实例
+ 程序代码
+ 配置web.xml
```
<1>程序代码
	/*ServletContextListener监听器*/
	public class MyServletContext implements ServletContextListener{
	
		/*web容器启动时调用-初始化*/
		public void contextInitialized(ServletContextEvenet servletcontextevent){
			//获取当前应用ServletContext对象,然后获取web.xml的初始化值
			String initParam = servletcontextevent.getServletContext().getInitParameter("initParam");
		}
	
		/*web容器关闭时调用-销毁*/
		public void contextDestroyed(ServletContextEvent servletcontextevent){
 			//释放资源操作
		}
	}


	/*HttpSessionListener监听器*/
	public class MyHttpSession implements HttpSessionListener{
		public void sessionCreated(HttpSessionEvent httpsessionevent){
	
		}
	
		/*
		 * 销毁的三种场景
		 *    1.关闭服务器
		 *    2.关闭浏览器一段时间,直到session过期
		 *    3.不关闭浏览器,session超时
		 */
		public void sessionDestroyed(HttpSessionEvent httpsessionevent){
	
		}
	}


	/*ServletRequestListener监听器*/
	public class MyServletRequest extends ServletRequestListener{
		/*监听每一个用户请求*/
		public void requestInitialized(ServletRequestEvent servletreqeustevent){
			//获取请求参数
			String username = servletrequestevent.getServletRequest().getParameter("name");
		}
	
		/*用户请求结束后,自动销毁*/
		public void requestDestroy(ServletRequestEvenet servletrequestevent){
	
		}
	}

<2>配置web.xml
	<web-app>
		....
	
		<!--监听器-->
		<listener>
			<listener-class>com.my.MyServletContext</listener-class>
		</listener>
		<listener>
			<listener-class>com.my.MyHttpSession</listener-class>
		</listener>
		<listener>
			<listener-class>com.my.MyServletRequest</listener-class>
		</listener>
	
		<!--session超时限制 -->
		<session-config>
			<session-timeout>1</session-timeout>
		</session-config>
		<!--初始化参数-->
		context-param>
			<param-name>initParam</param-name>
			<param-value>初始化值</param-value>
		</context-param>
	
	</web-app>

```
<br>

---
<br><br>


18_5.JSTL标签
---------------------------
&emsp;JSTL是java中的一个定制标记库集,实现了JSP页面中的代码复用(基于标签库原理,重复率较高的代码块支持复用,提高效率),书写JSP页面时可读性更强(类似于xml,方便前端查看和参与开发)
<br>


参考资料
+ [下载JSTL](http://archive.apache.org/dist/jakarta/taglibs/standard/binaries/)
  <br>


EL表达式【全称Expression Language】
+ 写法比较
+ 格式
+ 运算符"."和"[]"
+ EL变量
+ 类型转换 
+ EL隐式对象
+ 支持的运算符
```
<1>写法比较
	普通: <%=session.getValue("name") %>
	EL表达式: <c:out value="${sessionScope.name}" />

<2>格式: ${表达式}


<3>运算符"."和"[]"
	a.${user.sex} 等同于 ${user["sex"]}
		(1)包含特殊符号时不能等同,只用一种写法${user["first-name"]}
		(2)通过变量动态取值时【param可以是sex.price,之类各种传入值】
				${user[param]}
	
	b."[]"的另一种用法,定位集合中某元素
		${booklist[0].price}


<4>EL变量
	JSP内置对象    ~~   EL变量
	Page                 PageScope
	Request 			 RequestScope
	Session 			 SessionScope
	Application 		 Application
	
	注:
		如果使用时未限制作用域,例:${username}
		则会在Page ——> Request ——> Session ——> Application 依次寻找
		【如果找不到不会输出null,而会输出一个空字符串】


<5>类型转换
	由String变为int类型
		${param.count + 20} 



<6>EL隐式对象
	pageContext
	pageScope
	requetsScope
	sessionScope
	applicationScope
	param
	paramValues
	Header
	headerValues
	cookie
	initParam


<7>支持的运算符
	算术运算符
	关系运算符
	逻辑运算符
	验证运算符 empty  【判断表达式是否为null,返回true or false,】
		例:${empty username}  ""~空字符串也返回true
```
<br>


四种JSTL标签
+ 核心标签
+ 格式化标签
+ SQL标签
+ XML标签
```
<1>核心标签

	导入核心库:
		<%@ taglib prefix="c" url="http://java.sun.com/jsp.jstl/core"%>
	
	a.表达式控制标签 out set remove catch
			<c:out value="输出常量"></c:out>
			<c:out value="${输出变量}" default="默认值"></cout>
			<c:out value="&ltout标签&gt--转义" escapeXml="false"></c:out>
	
			<c:set var="变量名" value="变量值"  scope="作用域"></c:set>
			<c:set var="变量名" scope="作用域">变量值</c:set>
			<jsp:useBean id="person" class="com.my.Person"></jsp:userBean>
			<c:set target="${person}" property="name"  value="小明"></c:set>
			<c:set target="${person}" property="age">18</c:set>
	
			<c:remove var="变量"/>
	
			<!--可包含容易出错的JSTL标签(逻辑)-->
			<c:catch var="error">
				<c:set target="aa" property="bb">测试</c:set>\
			</c:catch>
			<c:out value="error"></c:out>【顶部没有定义aa对象,所以会输出报错信息】


	b.流程控制标签 if choose when otherwise
			<c:if test="判断条件(一般用EL表达式编写)" var="指定名称,存放true or false" scope="var属性存放作用域"></c:if>
			<c:if test="{param.scope >= 90" var="result" scope="application">
				<c:out value="成绩优秀"></c:out>
			</c:if>
			<c:out value="${aplicationScope.result}"></c:out>
	
			<!--类似if-else结构,otherwise可写可不写 -->
			<c:choose>
				<c:when test="${param.scope >= 90 && param.scope<=100}">
						...
				</c:when>
				<c:when test="">
						...
				</c:when>
				<c:otherwise>
					...
				</c:otherwise>
			</:c:choose>


	c.循环标签 forEach forTokens
			<c:forEach items="待遍历集合" var="当前元素的变量">
				...
			</c:forEach>
			<c:forEach item="userlist" var="user"
						begin="开始遍历的index值"
						end="结束遍历的index值"
						step="遍历间隔(步长)"
						varStatus="指定变量,储存当前元素状态(first,index,count,last)">
				...
			</c:forEach>
	
			<!--浏览字符串,并根据指定字符将字符串截取-->
			<c:forTokens items="被迭代的字符串" delims="指定分隔符" 	var="分隔后的变量"
				begin="开始索引"
				end="结束索引"
				step="遍历间隔"
				varStatus="指定变量,储存当前元素状态(first,index,count,last)"
			>
				...
			</c:forTokens>
	
	d.URL操作标签 import url redirect
	
			<!--
				可以把其他静态 or 动态文件包含到本JSP页面
					注意:
						(1)<jsp:include>只能包含同一个web应用的文件
						    <c:import>可以包含其他web应用中的文件,甚至网络上的
						(2)使用context引入其他项目的文件
							a.要修改Tomcat的发布路径
							b.要修改%TOMCAT_HOME%/cof/context/xml的Context标签中的属性
								crossContext="true"
			-->
			<c:import url="被导入资源的URL路径"
					  context="相同服务器下其他web工程(必须以\开头)"
					  scope="作用域"
					  charEncoding="编码字符集"
					  var="以String类型存入被包含文件内容"
					  varReader="以Reader类型存储包含文件内容">
				...
			</c:import>
			<c:import url="text.txt" var="tt" scope="session" charEncoding="gbk"></c:import>
			<c:import url="/importFile.jsp" context="/AnotherWebProject"></c:import>


			<!-- url标签,动态生成一个String类型的URL,可以用<c:param>标签共同使用也可以使用html的<a>实现超链-->	
			<c:url value="url路径值" 
					var="将url路径储存在变量中"
					scope="作用域" />
			</url>


			<!--请求重定向 -->
			<c:redirect url="重定向地址" context="用于导入其他web应用中的页面"></c:redirec>

```
<br>


JSTL函数
```
导入函数库
	<%@ taglib prefix="fn" url="http://java.sun.com/jsp/jstl/functions" %>

使用
	<:out value="在指定字符串中是否包含——————————${fn.contains('整个字符串是否包含啥','啥')}"></c:out>

	${fn.contains('整个字符串是否包含啥','啥')}
	${fn.containsIgoreCase('哈哈哈你好,Hello World','hello')}    //忽略大小写
	${fn.endsWith("不懂","董")}									 //判断是否以指定字符结尾
	
	${fn.escapeXml('<book>冰与火之歌</book>')}"></c:out>         //输出$it;book$gt...,输出未转义的
	${fn.indexOf('哈哈哈,KKKK','K')}              				 //输出字符索引


	fn:length()  			//字符长度
	fn:replace() 			//替换
	fn:split()   			//分隔成数组
	fn:startsWitth() 		//是否以指定字符开头
	fn:substring()  		//字符串截取
	fn:toLowerCase()  		//变小写
	fn:totoUpperCase() 		//变大写
	fn:trim()          		//首尾去空格



```
<br>

---
<br><br>


18_6.JSP自定义标签
-----------------------------


TagSupport类【自定义标签】
+ 标签处理类
+ 标签库描述文件(.tld)中注册标签
+ 在jsp文件中使用自定义标签
```
<1>标签处理类【编写标签处理类,继承TagSupport,一般一个标签对应一个类】
		public class LoginDateTag extends TagSupport{

			//D.【标签属性】类中添加全局变量,并添加Getter和Setter方法,在标签库文件注册
			private String value = "";
			private String name ="";
	
			//Getter
			public String getValue(){
				return value;
			}
			public String getName(){
				return name;
			}
			//Setter
			public void setValue(String value){
				this.value = value;
			}
			public void setName(String name){
				this.name = name;
			}
	
			//用于遍历
			private String [] items;   //遍历数组
			private int i; 			   //用于定位索引



			/*开始标签则调用*/
			@Override
			public int doStartTag() throws jspException{
				//A.记录登录时间
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MMM-dd HH:mm:ss");
				String date = sdf.format(new Date());
				try{
					pageContext.getOut().print(date);	//打印输出
				}catch(IOException e){}


				//B.流程控制【两种合法返回值】
				String name  = pageContext.getRequest().getParameter("name");//请求参数
				if(name != null && name.equals("小明")){
						return EVAL_BODY_INCLUDE;	//显示标签体文字
				}else{
						return SKIP_BODY;			//不显示标签体文字
				}


				//E.遍历(初次循环)
				if(items != null && items.length > 0){
						pageContext.setAttribute("name",items[0]);
						return EVAL_BODY_INCLUDE;  //执行标签体
				}else{
						return SKIP_BODY;   //不执行标签体
				}



				return super.doStartTag();//不执行标签体即是:SKIP_BODY常量
			};


			/*结束标签时调用*/
			@Override 
			public int doEndTag() throws JspException{ 


				//C.防盗链(只能通过超链接访问)【两种合法返回值】
				HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();
	
				String referer = request.getHeader("referer");//HTTP请求头的属性,可以通过该属性判断用户从哪个页面连接过来,只有通过超链接 or 表单提交方式,该属性才有效
				String url = "http://" + request.getServerName();
	
				if(referer!= null && referer.startsWith(url)){  //超链接 && 通过本站的链接
					return EVAL_PAGE;	//执行该标签后的JSP网页
				}else{
	
					//否则不能访问
					try{
 						pageContext.getOut();print("不能访问");
					}catch(IOException e){}

					return SKIP_BODY;	//不执行后续JSP网页
				}


				return super.doEndTag();//执行标签后的内容,返回EVAL_PAGE常量
			}


			/*执行完标签体后执行*/
 			@Override
			public int doAfterBody() throws JspException{

				/*E.遍历
					【两个合法返回值】EVAL_BODY_AGAIN(再显示标签间文字)SKIP_BODY(执行标签后的下一步)
	
					jsp页面中:
						<%
							 String [] list = {"1","2","3"};
							 pageContext.setAttribute("list",list);
						%>
						<my:list items="${list}">
							${name}
						</my:list>
				*/


				 if(i < items.length){
				 	pageContext.setAttribute("name",items[i]);
				 	i++;
				 	return EVAL_BODY_AGAIN;//再次执行标签体
				 }else{
				 	return SKIP_BODY;
				 }
	
			}
		}


<2>标签库描述文件(.tld)中注册标签【可以参考Tomcat目录下的\webapps\examples\WEB-INF\jsp2\jsp2-example-taglib.tld】
		<?xml version="1.0" encoding="UTF-8" ?>
		<taglib xmlns="http://java.sun.com/xml/ns/j2ee"
			xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
			xsi:schemaLocation="http://java.sun.com/xml/ns/j2ee http://java.sun.com/xml/ns/j2ee/web-jsptaglibrary_2_0.xsd"
			version="2.0">
		    <description>A tag library exercising SimpleTag handlers.</description>
		    <tlib-version>1.0</tlib-version>
		    <short-name>SimpleTagLibrary</short-name>
		    <uri>/studyJSPTag</uri>
		    <tag>
		        <description>描述</description>
		        <name>loginDate</name><!--标签名(相当于类名)-->
		        <tag-class>com.my.LoginDateTag</tag-class>
		        <body-content>empty</body-content><!--空标签体-->
		    </tag>
		     <tag>
		        <name>study</name><
		        <tag-class>com.my.Study</tag-class>
		        <body-content>scriptless</body-content><!--有内容标签体-->
		    </tag>
		    <tag>
		        <name>user</name><
		        <tag-class>com.my.User</tag-class>
		        <body-content>empty</body-content><!--有内容标签体-->
		        <attribute> <!--标签属性-->
		        	<name>value</name>
		        	<required>true</required>
		        	<rtexprvalue>true</rtexprvalue><!--表示能在运行时接收表达式的值 -->
		        </attribute>
		         <attribute>
		        	<name>name</name>
		        	<required>true</required>
		        	<rtexprvalue>true</rtexprvalue>
		        </attribute>
		    </tag>
		     <tag>
		        <name>list</name><
		        <tag-class>com.my.List</tag-class>
		        <body-content>scriptless</body-content>
		        <attribute> <!--标签属性-->
		        	<name>items</name>
		        	<required>true</required>
		        	<rtexprvalue>true</rtexprvalue>m
		        </attribute>
		    </tag>
		 </taglib>


	将该标签库描述文件放到WEB-INF 或者其子目录下


<3>在jsp文件中使用自定义标签
		a.导入
			<%@ taglib prefix="my" uri="/studyJSPTag" %>
	
		b.代码调用
			<my:loginDate />


```
<br>




BodyTagSupport类【BodyTag接口的实现类,将标签体中的执行结果,修改后输出】
+ 修改标签体的字符串
```
<1>修改标签体的字符串
		public class MyBodyTagSupport extends BodyTagSupport{
			private BodyContent body bodyContet;
	
			@Override
			public void setBodyContent(BodyContent b){
				this.bodyContent = b;
			}
	
			@Override
			public int doEndTag() throws JspException{
				//A.获取标签体内容,然后修改
				String content = bodyContent.getString();
				System.out.println("原标签体内容" + content);
	
				String newContent = "学习";
				JspWriter jspWriter = bodyContent.getEnclosingWriter();
				try{
					jspWriter.write(newContent); //修改标签内容
				}catch(Exception e){}
			}
	
			return EVAL_PAGE;//执行后续JSP网页
		}
	
		************************************************************************
		在标签库中注册
			<tag>
				<name>bodyTag</name>
				<tag-class>com.my.MyBodyTagSupport</tag-class>
				<body-content>scriptless</body-content>
			</tag>
		************************************************************************
		在页面中调用
			<%@ taglib prefix="my" uri="/my-tag" %>
	
			<my:bodyTag>
				学习
			</my:bodyTag>
```
<br>



SimpleTagSupport类【SimpleTag接口的实现类,用于快速开发自定义标签】
```
		public class MySimple extends SimpleTagSupport{

			private String [] items;
			private String name;
	
			public void setItems(String [] items){ //页面的setter方法都是由容器调用,将属性值填入指定属性
				this.items = items;
			}
			public void setName(String name){
				this.name = name;
			}
	
			@Override
			public void doTag() throws JspException,IOException{
	
				//A.输出时间
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String date = sdf.format(new Date());
				JspWriter jspWriter = getJspContext().getOut();
				jspWriter.writer(date);


				//B.判断是否显示标签体
				PageContext pageContext = (PageContext)getJspContext();
				String name = pageContext.getRequest().getParameter("name");
				if(name != null && name.equals("小明")){
					getJspBody().invoke(null);//显示标签体
				}else{
					super.doTag();//不显示标签体
				}
	
				//C.是否继续执行剩余JSP内容
				PageContext pageContext = (PageContext)getJspContext();
				HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();
				String referer = request.getHeader("referer");
				if(referer == null){
					throw new SkipPageException();
				}


				//D.循环遍历
				if(item != null && items.length > 0){
					PageContext.pageContext = (PageContext)getJspContext();
					for(int i = 0; i < items.length;i++){
						pageContext.setAttribute(name,items[i]);
						getJspBody().invoke(null);
					}
				}
	
				//E.修改标签内容
				StringWriter sw = new StringWriter(); //构建缓冲区
				JspFragment jspFragment = getJspBody();  //标签体中内容
				jspFragment.invoke(sw); //将数据写到缓冲区中
				String content = stringWriter.toString();
				content = "hello";
				PageContext pageContext = (PageContext)getJspContext();
				pageContext.getOut().write(content);
	
			}
		}

```
<br>

---
<br><br>

19.JSON数据处理
----------------------
&emsp;与开发语言无关的,轻量级的数据格式。全称(JavaScript Object Notation),易于解析和生成
<br>


参考资料
+ [GSON的Github项目](https://github.com/google/gson)
  <br>


比较:
+ JSON是Android SDK官方的库,GSON是Google发起的开源库
+ GSON更适用于服务端对JSON数据的处理,功能比JSON强大
  <br>


JSON数据样例
```
wangxiaoer.json
	{
		"name":"王小二",
		"age":25,
		"birthday" : "1990-01-01",
		"school" : "蓝翔",
		"major" : ["理发","炒菜","挖掘机"],
		"girfriend" : false,
		"car" : null,
		"house" : null
	}
	
	注意:json文件是没有注释的格式
```
<br>


java中的json处理
+ org.json
+ GSON
```
<1>org.json
		a.在pom.xml中引入依赖
			<dependencies>
				...
				<dependency>
					<groupId>org.json</groupId>
					<artifoctId>json</artifactId>
					<version>20090211</version>
				</dependency>
			</dependency>
	
		b.程序代码
				/*(1)添加数据*/
				public static void addJSONData(){
					//A.put方式添加
					JSONObject obj = new JSONObject();
					Object nullObj = null;
					try{
						obj.put("name","小明");
						obj.put("age",25);
						obj.put("major",new String[]{"炒菜","挖掘机"});
						obj.put("car",nullObj);
						obj.put("grilfriend",false);
	
						System.out.println(obj.toString());
					}catch(JSONException e){}
	
					//B.通过Map方式构建
					Map<String,Object> mapobj = new HashMap<String,Object>();
						mapobj.put("name","小明");
						mapobj.put("age",25);
						mapobj.put("major",new String[]{"炒菜","挖掘机"});
						mapobj.put("car",nullObj);
						mapobj.put("grilfriend",false);
					System.out.println(mapobj.toString());
	
					//C.JavaBean构建
					User user = new User();
						user.setName("王小二");
						user.setAge(25);
						user.setMajor(new String[]{"炒菜","挖掘机"});
						user.setCar(null);
						user.setGrilFriend(false);
					JSONObject obj = new JSONObject(user);
				}


				/*(2)解析JSON数据*/
				public static void readJSONDate(){
					//A-1.从文件中读取
					File file = new File(ReadJSONSample.class.getResource("/wangxiaoer.json").getFile());
					String content = FileUtils.readFileToString(file);
					JSONObject obj = new JSONObject(content); //得到JSON对象
	
					//A-2.解析
					String name = null;
					if(!obj.isNull("name")){  //判断指定属性是否为null
						name = obj.getString("name");
					}
					double age = obj.getDouble("age");
					boolean girlFriend = obj.getBoolean("grilFriend");
					JSONArray array = obj.getJSONArray("major");
					for(int i = 0,len = array.length(); i < len; i++){
						String tmp = (String)array.get(i);
						System.out.println("专业:" + (i+1) + tmp);
					}
	
					//不支持JavaBean反解析
				}


<2>GSON
		a.加入Maven依赖
			<dependency>
				<groupId>com.google.code.gson</groupId>
				<artifactId>gson</artifactId>
				<version>2.4</version>
			</dependency>


		b.程序代码

			/*【扩展功能】注解变大写*/
			class user{
				@SerializedName("NAME") //可以将gson解析数据后的,将name这个key值变为大写
				private String name;
	
				//加入transient关键字,在生成json格式时,忽略该属性
				private transient String ignore;
				.....
			}
	
			/*(1)添加JSON*/
			public static void addJSONDate(){
				//A.JavaBean对象
				User user = new User();
						user.setName("王小二");
						user.setAge(25);
						user.setMajor(new String[]{"炒菜","挖掘机"});
						user.setCar(null);
						user.setGrilFriend(false);
			
				GSON gson = new GSON();
				System.out.println(gson.toJson(user));
	
				//B.个性话构建
				GsonBuilder gsonBuilder = new GsonBuilder()l
				gsonBuilder.setPrettyPrinting(); //美观换行
				//加入回调函数
				gson.setFieldNamingStrategy(new FieldNamingStrategy(){
					public String translateName(Field f){ //Field是反射对象
	
						if(f.getName(). equals("name")){
							//会和@SerializedName("NAME")冲突,二选1
							return "NAME"; //返回大写NAME
						}
	
						return f.getName(); 
					}
				});
				Gson gson = gsonBuilder.create();
				System.out.println(gson.toJson(user));
			}


			/*(2)解析JSON*/
			public static void readJSNDate(){
	
				//A.从文件中读取,反解析JavaBean
				File file = new File(ReadJSONSample.class.getResource("/wangxiaoer.json").getFile());
				String content = FileUtils.readFileToString(file);
	
				Gson gson = new Gson();
				User user = gson.fromJson(content,User.class);
				System.out.println("user");
	
				//B.个性化转换
				Gson gson = new GsonBuilder.setDateFormat("yyyy-MMM-dd").create();//定制日期格式


				//C.JSON中的数组可以用,JavaBean可以用Set,List各种集合对应
				System.out.println(user.getMajor());//【GSON会自动识别数组,然后转为各种集合】
				System.out.println(user.getMajor().getClass());
			}
```




20.DWR实现服务器向客户端推送
--------------------------
&emsp;DWR是一个基于Ajax的框架,动态将Java类生成Javascript,让客户端JavaScript通过DWR异步访问Java程序
<br>

运行原理
1.读取dwr.xml生成XXX.js文件
2.js触发XXX.js中的方法
3.web容器接收请求创建实例
4.调用方法处理返回
<br>


DWR的基本使用
```
<1>添加Maven依赖
	<dependency>
		<groupId>org.directwebremoting</groupId>
		<artifactId>dwr</artifactId>
		<version>3.0.M1</version>
	</dependency>

<2>web.xml配置DWR的Servlet
	<servlet>
		<servlet-name>dwr</servlet-name>
		<!-- 
		 DWR2.X的版本
			<servlet-class>org.directwebremoting.servlet.DwrServlet</servlet-class>
		-->
		<servlet-class>uk.ltd.getahead.dwr.DWRServlet</servlet-class><!--DWR3.0版本-->
		<!--使用服务器推技术(反转AJAX)-->
		<init-param>
			<param-name>activeReverseAjaxEnabled</param-name>
			<param-value>true</param-value>
		</init-param>
		<!--能够从其他域进行请求【true-开启,false-关闭】-->
		<init-param>
			<param-name>crossDomainSessionSecurity</param-name>
			<param-value>false</param-value>
		</init-param>
		<!--运行远程js-->
		<init-param>
			<param-name>allowScriptTagRemoting</param-name>
			<param-value>true</param-value>
		</init-param>
	</servlet>
	<servlet-mapping>
		<servlet-name>dwr</servlet-name>
		<url-pattern>/js/dwr/*</url-pattern>
	</servlet-mapping>


<3>dwr.xml配置
	<?xml version="1.0" encoding="UTF-8"?>
	<!DOCTYPE dwr PUBLIC "-GetAhead Limited/DTD Direct Web Remoting 2.0//EN" "http://www.getahead.org/dwr/dwr30.tdt">
	<dwr>
		<allow>
			 <!--动态生成js文件-->
			<create creator="new" javascript="PushMessage">
				<param name="class">com.my.PushMessage</param><!--指定java类-->
			</create>
		</allow>
	</dwr>


<4>java代码的推送方法
	public class PushMessage{
		public void send(String msg){
			ScriptBuffer scriptBuffer = new ScriptBuffer();//构建js脚本
			WebContext webContext = WebContextFactory.get();
			ScriptSession mySession = webContext.getScriptSession();//获取所有ScriptSession【一个客户端发送会推送到所有客户端(各种回调函数)】
	
			scriptBuffer.appendScript("callHello("); //调用页面回调方法
			scriptBuffer.appendData(msg);  //传入
			scriptBuffer.appendScript(")");
	
			Util util = new Util(mySession);
			util.addScript(scriptBuffer);//向客户端推送信息
		}
	}


<5>jsp页面中使用DWR【需引入util.js和engine.js】
	<html>
		<head>
			<!--引入js文件 -->
			<script type="text/javascript" src="js/util.js"></script> 
			<script type="text/javascript" src="js/engine.js"></script>
			<script type="text/javascript" src="dwr/interface/PushMessage.js"></script><!--java类由DWR动态生成的JS文件-->
			<script type="text/javascript" src="js/jquery-1.7.1.min.js"></script>
			<!--页面中调用-->
			<script type="text/javascript">
				/*调用java方法*/
				$(document).read({
					function(){
						dwr.engine.setActiveReverseAjax(true); //激活反转AJAX
	
						//点击按钮,调用Java代码
						$("#sign").click(){
							PushMessage.Send("传入参数值");
						}
	
					};
				};


				/*回调函数*/
				function callHello(data){
					alert(data); //提示信息
				}
			</script>
		</head>
		<body>
			<button id="sign">点击这里向客户端推送信息</button>
		</body>
	</html>



<6>思路流程
	a.配置web.xml的DWR的Servlet
	b.配置dwr.xml文件
	c.【推送页面】js页面点击按钮 
	d.调用指定java类的函数(客户端推送功能)
	e.【消息显示页面】推送成功,跳到js的回调函数


```

---
<br><br>

---
<br><

三.设计模式
=========================
1.单例模式
-------------------

资源
+ [Java单例模式——并非看起来那么简单](http://blog.csdn.net/goodlixueyong/article/details/51935526)
  <br>

使用
+ 每次增删查改数据，都要连接数据库,可获取一个实例多次应用
+ 多线程爬虫，不重复创建实例，使用唯一的资源
+ 已应用到Utils【个人工具包】里的UseDB.java
  <br>

思路整理：
1. 懒汉模式
2. 饿汉模式
3. 双重校验锁
4. JDK1.5之后版本增加了volatile关键字
5. 静态内部类实现单例
  <br>

---
<br><br>










```

```