---
title: Linux_基础操作命令
date: 2016-04-23 11:18:21
tags: Linux
categories: Linux
---


目录
======================

参考资源
+ [CentOS常用的文件操作命令总结](http://www.haorooms.com/post/centeros_wj_zj)


1. 目录操作
2. 关机命令
3. 文件属性
4. 用户管理
5. 安装MySQL数据库
6. PuTTy远程登录Linux


## 1. 目录操作
```
pwd 					[面试目前目录]
cd  <目录名 or ../>		[切换目录]
mkdir <-p"不存在上级目录依旧报错"> <目录>		[创建目录]
rmdir  <-p"将上级目录一起删除"> <目录>			[删除目录(只能删除目录)]

cp  								[复制文件或目录]
rm 	<-f"强制" -i"互动" -r"递归">	[移除文件或目录]
rm -rf [强制删除目录]

ls / 					[查询当前路径所有子目录]
ls -a  					[全部文件(包含隐藏档,开头为.)]
ls -d  					[仅列出目录本身]
ls –l					[长数据串列出(包含文件属性与权限等)] 


改文件[or 目录名]
  mv 修改前文件夹名    修改后文件夹名
  mv 修改前文件名    修改后文件名

新建与执行Shell脚本
	vi XXX.sh [新建]
	sh XXX.sh [执行]


```



## 2. 关机命令
```
shutdown –h 10 ‘This server will shutdown after 10 mins’ 这个命令告诉大家，计算机将在10分钟后关机，并且会显示在登陆用户的当前屏幕中。
Shutdown –h now 立马关机
Shutdown –h 20:25 系统会在今天20:25关机
Shutdown –h +10 十分钟后关机
Shutdown –r now 系统立马重启
Shutdown –r +10 系统十分钟后重启
reboot 就是重启，等同于 shutdown –r now
halt 关闭系统，等同于shutdown –h now 和 poweroff

```



## 3. 文件属性
```
0123456789
dr-xr-xr-x

0   d/-(目录/文件)	   ->文件类型
123 rwx(读/写/执行)    ->属主权限   【权限的-,代表无权执行相应操作】
457 -wx(读/写/执行)	   ->属组权限	 
789 r-w(读/写/执行)	   ->其他用户权限


查看文件
	
    cat  由第一行开始显示文件内容
    tac  从最后一行开始显示，可以看出 tac 是 cat 的倒著写！
    nl   显示的时候，顺道输出行号！
    more 一页一页的显示文件内容
    less 与 more 类似，但是比 more 更好的是，他可以往前翻页！
    head 只看头几行
    tail 只看尾巴几行


打开文件(vi命令编辑文件)【不存在则创建】
	vi filename :打开或新建文件，并将光标置于第一行首
　　vi +n filename ：打开文件，并将光标置于第n行首
　　vi + filename ：打开文件，并将光标置于最后一行首
　　vi +/pattern filename：打开文件，并将光标置于第一个与pattern匹配的串处
　　vi -r filename ：在上次正用vi编辑时发生系统崩溃，恢复filename
　　vi filename....filename ：打开多个文件，依次进行编辑



		按 Insrt 键进入编辑模式 

		按ESC键进入命令模式
	　    :w 保存当前文档 
		  :q 直接退出 vi 
		  :wq 先保存后退出
		  :wq! 强制先保存后退出 
		  :q! 强制不保存退出



```



## 4. 用户管理
```
添加用户
	useradd [选项] 用户名
	    -c comment 指定一段注释性描述。
	    -d 目录 指定用户主目录，如果此目录不存在，则同时使用-m选项，可以创建主目录。
	    -g 用户组 指定用户所属的用户组。
	    -G 用户组，用户组 指定用户所属的附加组。
	    -s Shell文件 指定用户的登录Shell。
	    -u 用户号 指定用户的用户号，如果同时有-o选项，则可以重复使用其他用户的标识号。

	  例子：useradd –d /usr/sam -m sam


删除用户
	userdel [-r] 用户名

修改用户
	usermod [选项] 用户名


用户口令
	passwd -help (帮助)
	passwd [选项] 用户名
	    -l 锁定口令，即禁用账号。
	    -u 口令解锁。
	    -d 使账号无口令。
	    -f 强迫用户下次登录时修改口令。

	    实例：修改密码：输入passwd suvan -即会弹出new password和Retype new password


切换用户 
	su 用户名
	[su - 切换回root用户]


给用户添加root权限
	a.修改/etc/sudoers 文件，[vi /etc/sudoers]
		[找到下面一行，在root下面添加一行，如下所示]
			## Allow root to run any commands anywhere
			root    ALL=(ALL)     ALL
			suvan1     ALL=(ALL)       ALL                [这个在切换时，是需要输入密码的，密码是当前普通用户的密码]
			suvan2 ALL=(ALL)     NOPASSWD:ALL       [这个在切换时，不需要输入密码]
    b.按ESC进入命令模式,:wq!强制退出保存

    c.可以使用suvan1和suvan2登录Linux,并使用sudo su 切换到root权限

```





## 5. 安装MySQL数据库
-----------------------

参考资料
+ [ Linux学习之CentOS(十三)--CentOS6.4下Mysql数据库的安装与配置](http://www.cnblogs.com/xiaoluo501395377/archive/2013/04/07/3003278.html)
+ [Mysql初始化root密码和允许远程访问](http://www.cnblogs.com/cnblogsfans/archive/2009/09/21/1570942.html)
+ [CentOS 7 yum 安装 MySQL5.7 ](https://my.oschina.net/Laily/blog/713023)
  <br>



## 6. PuTTy远程登录Linux远程登录

次目录：
1. 填写Putty,
```
 a.注意要填写SSH端口(Port)【是5位数那个，不是SS端口】
 b.搬瓦工的KiwiVM的Root password modification【生成随机root密码】

```


2. 修改session的超时限制

```
 a、修改ssh配置文件
	vi /etc/ssh/sshd_config
	找到ClientAliveInterval，指定了服务器端向客户端请求消息的时间间隔, 默认是0，不发送。
	将后面的数值设置修改，单位为秒，如10分钟，则可写600
	再找到ClientAliveCountMax，指如果发现客户端没有相应，则判断一次超时，这个参数设置允许超时的次数，比如10。
	则代表允许超时 6000秒 = 100分钟。


b、Putty
	启用putty keepalive
	putty -> Connection -> Seconds between keepalives ( 0 to turn off )，默认为0，改为60。
	其他第三方工具请根据实际情况修改保活机制的配置。

```

