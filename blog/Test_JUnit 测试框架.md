---
title: JUnit 测试框架
date: 2017-05-08 13:54:25
tags: Test
categories: Test
---


目录
========================
1. 概述
2. [注解]和[主要类与函数]
3. 入门代码实践
4. 注意点
5. SSM框架中jUnit单元测试测试Mybatids[增删查改]

参考资料
+ [JUnit官网](http://junit.org/junit4/)
+ [JUnit入门教程【极客学院】](http://wiki.jikexueyuan.com/project/junit/overview.html)
+ [JUnit4单元测试入门教程](http://www.jianshu.com/p/7088822e21a3)
+ [JUnit-Github仓库](https://github.com/junit-team/junit4)





## 1. 概述

&emsp;JUnit是一个Java编程语言单元测试框架。例如：时间,异常,参数化测试....

常用目录结构
```
main  [各种源码]
test  [存放测试类]
	
```




## 2. [注解]和[主要类与函数]
```

注解  ---> (应用于测试类)
	@BeforeClass	[测试类执行前]
	@AfterClass		[测试类执行后]
	@Before     	[每个测试用例执行前]
	@After          [每个测试用例执行后]
	@Ignore			[不执行]
	@Test   		[标识测试用例]
		(expected = ...)  【异常检测】
		(timeout = ...)	  【超时限制】

	@RunWith(Parameterized.class)  		[声明测试类]
	@Parameterized.Parameters      		[声明测试参数与期望值的函数]

	@RunWith(Sutie.class)		   		[声明测试套件类]
	@SuiteClasses({...class,...class})  [将各种测试类的运行时填入]


主要类与函数 
	org.junit.runner.JUnitCore;             [用于接收测试类的Class(运行时)【返回Result】]
	org.junit.runner.Result;                [测试结果]
	org.junit.runner.notification.Failure;  [测试结果的失败信息]


	下面这种比较规范
		junit.framework.Assert       			[断言方法集合]
		junit.framework.TestCase                [测试类]
		junit.framework.TestResult 				[测试结果]
		junit.framework.TestSuite               [测试套件]
 
	也可以使用
			import static org.junit.Assert.*;   [导入各种assert]

```
<br><br>


## 3. 入门代码实践

1. 创建Java项目,导入hamcrest-core-1.3.jar 和 junit-4.12.jar包
2. 在src目录下创建main包【存放源码】和test包【存放测试类】
3. 代码
  ​

main包
```


*************************Math.java*************************************
package main;

public class Math {
	
	/**
	 *  判断是否为0
	 *  @param n     [传入需要判断的数]
	 *  @return  String      [返回布尔值]
	 */
	public String judgment(int n) throws Exception{
		
		if(n == 0){
			return "为0";
		}else{
			return "不为0";
		}
	}
	
	/**
	 *  判断大于小于0
	 *  @param n         [传入需要判断的数]
	 *  @return  int      [返回整型]
	 */
	public int size(int n) throws Exception{
		
		if(n < 0){
//			return -1;		   //小于0
			throw new Exception("n小于0,所以抛出异常");
		}
		if(n > 0){
			return 1;		  //大于0
		}
		
		return 0;             //等于0
	}
	
	
	/**
	 *  排序
	 *   @parram arr      [传入数组]
	 */
	public void sort(int [] nums){
		
		int len = nums.length; //数组长度
		
		//冒泡排序
		for(int i = 0; i < len - 1; i++){
			for(int j = 0; j < len - 1 - i; j++){
				if(nums[j] > nums[j +1]){
					int temp = nums[j] ^ nums[j + 1];
					nums[j] ^= temp;
					nums[j+1] ^= temp;
				}
			}
		}
		
		
	}
}


**************************Person.java************************************

package main;

public class Person {
	public String name;
	public int age;
	
	public Person(String name, int age){
		this.name = name;
		this.age = age;
	}
	
}


**************************Parameter.java************************************

package main;


public class Parameter {
	
	/**
	 * 	 判断是n % 2 是否wield0
	 * 
	 *   @param n  [需要判断的数]
	 *   @return     [返回布尔类型]
	 */
	public boolean remainder(int n){
		if(n % 2 == 0){
			return true;
		}
		
		return false;
	}
	
}

```

---

test包5个类
```
**************************MathTest.java************************************
package test;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Arrays;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import main.Math;


/* 
 *  测试类
 */
public class MathTest {
	
	//所有测试方法"执行前"调用【测试类没有实例化就已经被加载，用static修饰】
	@BeforeClass    
	public static void setUpBeforeClass(){
		System.out.println("===============》BeforeClass");
	}
	//所有测试方法"执行后"调用【测试类没有实例化就已经被加载，用static修饰】
	@AfterClass
	public static void tearDownAfterClass(){
		System.out.println("===============》AfterClass" + "\n");
	}
	
	//每个测试用例"执行前"调用 
	@Before
	public void setUp(){
		System.out.println("--------Before--------");
	}
	//每个测试用例"执行后"调用 
	@After
	public void tearDown(){
		System.out.println("--------Afte--------"+ "\n");
	}

	
	
	@Ignore 													 //Ignore表示不执行该测试用例
	public void testJudgment(){
		System.out.println("执行测试用例1");
		
		try{
			assertEquals("用例1----->","为0",new Math().judgment(12));
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test(expected = Exception.class) 		//ecxceted参数检测是否抛出Exception异常 
	public void testJudgmentException() throws Exception{
		System.out.println("测试用例2");
		
		new Math().size(-1);
		fail("judgment函数的参数不为负数,没有抛出异常");
	}
	

	
	@Test(timeout = 2000)  						//timeout参数超时设置【不超过2000毫秒[即是2s]】
	public void testSort(){
		System.out.println("测试用例4");
		
		//生成5000个随机数
		int [] nums = new int[5000];
		for(int i = 0; i < nums.length; i++){
			nums[i] = (int)(java.lang.Math.random() * 1000);  //0-1000的随机数 
		}

		new Math().sort(nums);											  //执行排序
		
		System.out.println(Arrays.toString(nums));				 //输出排序后数组
	}
	
	
}
	

**************************PersonTest.java************************************
package test;

import static org.junit.Assert.assertSame;

import org.junit.Test;

import main.Person;

/* 
 *  测试类
 */
public class PersonTest {

	@Test
	public void testPerson(){
		System.out.println("PersonTest测试类-测试用例1");
		Person person1 = new Person("小明",29);
		Person person2 = new Person("小明",29);
		Person person3 =person1;
		
		assertSame(person1,person3);
	}
}

**************************ParameterTest.java************************************
package test;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import main.Parameter;
/*
 *   参数化测试
 *   	流程：
 *   			1.使用@RunWith(Parameterized.class)声明测试类
 *   			2.创建测试数据的方法(公共,静态)(返回一个对象的集合(数组)来作为测试数据集合)【使用@Parameterized.Parameters声明方法】
 *				3.创建构造方法【接收测试数据(和2中测试数据的类型相同)】
 *				4.全局变量        【接收构造方法的传值】
 *				5.创建测试用例  【用@Test声明，并以4的全局变量进行测试】
 *				6.在Test.java运行该测试类
 */
@RunWith(Parameterized.class)
public class ParameterTest {

	private Parameter parameter;       //被测试的类
	
	private int inputNumber;				//输入参数
	private boolean expectedResult;   //期望结果
	
	
	
	//构造方法
	public ParameterTest(int inputNumberm, boolean exectedResult) {
		this.inputNumber = inputNumberm;
		this.expectedResult = exectedResult;
	}
	
	@Before
	public void init(){
		parameter = new Parameter();  //每一个测试用例都new一个对象
	}
	
	
	@Parameterized.Parameters        
	public static Collection testDateCollection(){ 
		return Arrays.asList(new Object[][]{
			{2,true},              //测试数据与期望值
			{ 6, false },
		    { 20, true },
		    { 21, true },
		    { 23, false }
		});
	}
	
	
	@Test
	public void testRemainder(){
			System.out.println("输入参数" + inputNumber);
			assertEquals(expectedResult,
					parameter.remainder(inputNumber));
	}
}

**************************TestSuite.java************************************
package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;
import org.junit.runners.Suite;

/* 
 *  测试套件类
 */
@RunWith(Suite.class)
@SuiteClasses({
	MathTest.class,
	PersonTest.class
})
public class TestSuite {

}


**************************Test.java************************************
package test;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;


/* 
 *   运行主类
 */
public class Test {
	public static void main(String [] args){
		
//		//执行测试类1
//		Result result = JUnitCore.runClasses(MathTest.class);
//			System.out.println("~~~~~~~~错误信息~~~~~~~~：");
//		for(Failure failure: result.getFailures()){
//			System.out.println(failure.getMessage());
//
//		}
//		System.out.println("************************************************");
//		System.out.println("MathTest测试类的结果【运行结果(true or false)(有一个测试用例不成功即为false)】： "+result.wasSuccessful());
//			
//		//执行测试类2
//		Result result2 = JUnitCore.runClasses(PersonTest.class);
//		System.out.println("PersonTest测试类的结果："+result2.wasSuccessful());
//		
//		//执行测试套件TestSuite【包含测试类1 和2】
//		Result result3 = JUnitCore.runClasses(TestSuite.class);
//			System.out.println("~~~~~~~~错误信息~~~~~~~~：");
//		for(Failure failure: result3.getFailures()){
//			System.out.println(failure.getMessage());
//
//		}
//		System.out.println("************************************************");
//		System.out.println("TestSuite测试套件类的结果： "+result3.wasSuccessful());
	
	
		//执行测试类4
		Result result4 = JUnitCore.runClasses(ParameterTest.class);
		System.out.println("~~~~~~~~错误信息~~~~~~~~：");
		for(Failure failure: result4.getFailures()){
			System.out.println(failure.getMessage());

		}
		System.out.println("************************************************");
		System.out.println("ParameterTest测试类的结果【参数化测试,一个false即为false】： "+result4.wasSuccessful());
	
	}
}

```

---



控制台输出
```
****************************执行测试类1******************************************
===============》BeforeClass
--------Before--------
测试用例4
--------Afte--------

--------Before--------
测试用例2
--------Afte--------

===============》AfterClass

​~~~~~~~~错误信息~~~~~~~~：
************************************************
MathTest测试类的结果【运行结果(true or false)(有一个测试用例不成功即为false)】： true

****************************执行测试类1******************************************

PersonTest测试类的结果：false


****************************执行测试套件类3**************************************

===============》BeforeClass
--------Before--------
测试用例4
--------Afte--------

--------Before--------
测试用例2
--------Afte--------

===============》AfterClass

PersonTest测试类-测试用例1
​~~~~~~~~错误信息~~~~~~~~：
************************************************
TestSuite测试套件类的结果： true


****************************执行测试类4【参数化测试】*****************************
输入参数2
输入参数6
输入参数20
输入参数21
输入参数23
​~~~~~~~~错误信息~~~~~~~~：
expected:<false> but was:<true>
expected:<true> but was:<false>
************************************************
ParameterTest测试类的结果【参数化测试,一个false即为false】： false


```




## 4. 注意点

+ 静态导入org.junit.Assert.*;【加static】
+ 测试用用例命名【test + 被测试的方法名】
+ 测试类命名【被测试类 + Test】
+ assertEquals(预期结果,测试数)



## 5. SSM框架中jUnit单元测试测试Mybatids[增删查改]
```
package test.com.blog;

import com.alibaba.fastjson.JSON;
import com.blog.pojo.Article;
import com.blog.service.IArticleService;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 *  测试Article表
 *
 * @Author Suvan
 * @Date 2017-05-21-11:12
 */
@RunWith(SpringJUnit4ClassRunner.class)		//表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})
public class TestArticle {

    private static Logger logger = Logger.getLogger(TestArticle.class); //将此类加入日志

    @Resource
    IArticleService articleService;


    @Test
    public void testInsertArticle(){
        Article article = new Article();
        article.setTitle("第6666篇文章");
        article.setContent("内容很多....");
        article.setRead("42");
        article.setComment("1");
        article.setLike("23");
        article.setUserId(1);

        articleService.insertArticle(article);
        logger.info("插入成功 -> " + JSON.toJSONString(article));  //打印语句
    }

    @Test
    public void testDeleteArticle(){
        articleService.deleteArticle(1);

        logger.info("成功删除文章！");
    }

    @Test
    public void testSelectArticle(){
        Article article = articleService.selectArticle(1);
        logger.info("查询文章 -> "+JSON.toJSONString(article) +"~~~~文章发布时间：("+article.getPublictime() + ")");
    }

    @Test
    public void testUpdateArticle(){
        Article newArticle = new Article();
            newArticle.setId(2);                //指定id的文章进行更新
            newArticle.setTitle("我是第二篇");
            newArticle.setContent("哈哈哈");


        String info = articleService.updateArticle(newArticle);
        logger.info(info + "----" + JSON.toJSONString(newArticle));


    }

}

```

