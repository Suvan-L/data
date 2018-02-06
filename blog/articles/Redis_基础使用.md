---
title: Redis_基础使用
date: 2017-07-11 18:04:59
tags: Redis
categories: Redis
---



一.目录
======================

1.NoSQL介绍
2.Redis概述
3.Redis操作命令
4.Jedis连接
5.Redis的数据结构




## 1. NoSQL介绍

NoSQL(Not Only SQL)
+ 非关系型数据库
+ 高并发读写(High performance)
+ 海量数据的高效率存储和访问(Huge Storage)
+ 高可扩展性和可用性(High Scalability && High Availability)
+ 特点【易扩展,大数据,高性能,灵活的数据模型,高可用】



NoSQL数据库的四大分类
+ 键值(Key-Value)存储
+ 列存储
+ 文档数据库
+ 图形化数据库

---
<br><br>


## 2. Redis概述
&emsp;高性能键值队数据库

参考资料
+ [Redis官网](https://redis.io/)
  <br>

支持数据类型
+ 字符串类型
+ 散列类型
+ 列表类型
+ 集合类型
+ 有序集合类型
  <br>

应用场景
+ 缓存【数据查询,常驻内存 】
+ 任务队列
+ 网站访问统计【精确到毫秒】
+ 应用排行榜
+ 数据过期处理
+ 分布式集群架构中的session分离
  <br>

安装环境【CentOS】
+ redis是C语言开发,安装redis需要先将官网下载源码进行编译,编译依赖gcc环境(如果没有该环境,需安装gcc)
  <br>


特性
+ 多数据库
+ 事务
+ 三种同步策略(不同步,每秒同步,每修改同步)
+ 两种持久化操作(RDB【指定时间间隔将数据从内存中写入硬盘】和AOF方式【以日志形式记录数据库每一次操作,数据库启动时会读取日志文件,重新构建数据库,保证启动后数据库是完整的】)
+ 也可无持久化(用作缓存机制)和同时使用RDB和AOF
  <br>

RDB方式
+ 【优势】只有一个文件，时间间隔的数据，可以归档为一个文件，方便压缩转移（就一个文件）
+ 如果数据集很大,RDB启动效率会高些
+ 【劣势】不好保证数据高可用性(最大限度避免数据丢失),数据集太大,可能服务器停止一定时间进行恢复
  <br>

AOF方式
+ 【优势】保证更高的数据安全性
+ 对于日志写入操作采取添加模式,即使出现宕机现象,也不会破坏日志已经写入的内容
+ 如果日志过大,Redis可以自动启动重写机制,不断将修改的数据写入老的磁盘文件当中,同时也会创建新的文件,记录此期间产生了哪些修改命令被执行
+ 包含一个格式清晰,易于理解的日志文件操作
+ 【劣势】同步策略不同,效率会低于RDB
  <br>




## 3.Redis操作命令

windows版本
```
<1>解压Redis-x64-3.2.100

<2>前台启动
		cmd
		cd E:\Java\Redis\3.2.100
		e:
		redis-server.exe redis.windows.conf  
		【再次输入ctrl + c 或者 关闭黑窗体,可以停止Redis】

<3>后端启动【服务启动】
		a.安装服务redis-server --service-install redis.windows.conf
		b.启动服务redis-server --service-start 
		c.停止服务redis-server --service-stop


<4>客户端启动
		redis-cli

<5>测试连接
		输入ping【若回复PONG,表示连接成功】
```
<br>


字符串操作
```

<1>存入和获取
		a.【设置键值对】set name suvan
		b.【根据key获取值】get name
		c.【获取所有key】keys *
		d.【获取所有key】del name
		e.【先获取name的值,再设置】getset name xiaoming

<2>删除
	del name

<3>指定value值递增递减
	incr num     ~    1【如果num不存在,则默认设为0,然后自增1;如果值是Hello,转类型失败,返回错误信息】
	incr num     ~    2
	incrby num 5 ~	  7  【如果不存在,则默认设为0,自加5】

	decr num  ~     -1【如果num不存在,默认设为0,自减少为-1】
	decrby num 2 ~  -3


<4>拼接字符串
	append num 123 【不存在则创建,存在则追加在后尾】

```
<br>


Hash操作
```
<1>存储与获取
	a.【存取单个键值队】hsert myhash name suvan
	b.【存取多个键值队】hset myhash2 name suvan age 18
	c.【获取单个】hget myhash name
	d.【获取多个】hmget myhash2 name age
	c.【获取指定hash的全部】hgetall myhash


<2>删除
	a.【删除1个】hdel myhash2 name
	b.【删除多个】hdel myhash2 name age
	c.【删除整个Hash】del myhash2


<3>增加数字
	a.【自增3】hincrby myhash age 5

<4>判断是否存在
	hexists myhash name

<5>获取长度
	hlen myshah

<6>所有key 和所有value
	hkesy myhash
	hvals myhash



```
<br>




## 4. Jedis连接
--------------------------
&emsp;Jedis是Redis官方首选的Java客户端开发包,用于java连接Redis


参考资料
+ [jedis的Github](https://github.com/xetorthio/jedis)
+ [jedis的jar下载](http://mvnrepository.com/artifact/redis.clients/jedis)
+ [org.apache.commons的jar包下载](http://mvnrepository.com/artifact/org.apache.commons/commons-pool2)
  <br>


Jedis连接
```

<1>Build两个jar包
		jedis-2.9.0.jar
		commons-pool2-2.4.2.jar

<2>程序代码【注意:虚拟机记得打开防火墙端口】
	public void connect{
		//1.设置IP地址和端口
		Jedis jedis = new Jedis("127.0.0.1",6379);

		//2.保存数据
		jedis.set("name","suvan");

		//3.获取数据
		String value = jedis.get("name");

		//4.释放资源
		jedis.close();


	}


<3>连接池
	public void connectPool{
		//1.获取连接池的配置对象
		JedisPoolconfig config = new JedisPoolConfig();

		//2.设置最大连接数
		config.setMaxTotal(30);

		//3.设置最大空闲连接数
		config.setMaxIdle(10);

		//4.获取连接池
		JedisPool jedisPool = new JedisPool(config,"127.0.0.1"，6379);

		//5.获取核心对象
		Jedis jedis = null;

		try{
			//通过连接池连接
			jedis = jedisPool.getResource();

			jedis.set("name","张三");
			String value = jedis.get("name");
			System.out.println(value);
		}catch(Exception e){}
		 finally{
		 	//释放资源
		 	if(jedis != null){
		 		jedis.close();
		 	}
		 	if(jedisPool != null){
		 		jedisPool.close();
		 	}
		 }
	}


<4>注意
	a.连接linux虚拟机的redis
		关闭防火墙命令sudo service iptables stop
		需要注释掉bind127.0.0.1 ，设置protected-mode为NO。如果解决了问题请点个赞
```
<br>


## 5. Redis的数据结构

五种数据类型
+ 字符串(String)
+ 哈希(hash)
+ 字符串列表(list)
+ 字符串集合(set)
+ 有序字符串集合(sorted set)
  <br>

Key定义注意点
+ 不要过长
+ 不要过短
+ 统一的命名规范
  <br>


Hash结构
+ String Key和String Value的map容器
+ 每一个Hash可以存储4294967295个键值对
  <br>
