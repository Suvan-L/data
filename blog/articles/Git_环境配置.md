---
title: Git_环境配置
date: 2016-10-21 11:08:25
tags: Git
categories: Git
---




简介与安装
=======================

1.简介：
Git是一种的分布式版本控制工具,开发者是Linux操作系统作者Linus Torvalds
初衷是为了更好的管理Linux内核，而现在却早已被广泛应用全球各种大中小型项目中


2.安装
Ubuntu系统:打开shell，输入`sudo apt-get install git-core`
Window系统：访问网址`http://mysgit.github.io/`,点击中央的Downloads,下载安装包，然后一直Next



配置,创建与上传
===========================

## 目录
1. 配置个人信息
2. 创建Github的repository和本地仓库
3. 将本地项目提交到Github


## 1. 配置个人信息
配置用户:`git config --global user.name "suvan"`
配置邮箱:`git config --global user.email "13202405189@163.com"`

查询配置:`git config --global user.xxx`



## 2. 创建Github的repository和本地仓库
```
<1>在Github创建repository
官网：https://github.com/
	Sign in(登录)
		New repository

<2>创建本地仓库

右键 Git Bash Here (1.可以选择在任意界面)
    cd e:(假设进入e盘)
   		cd 目录名(进入到项目根目录，如果是在项目根目录右键的话，就会直接默认在当前目录,cd ..返回上一级)
		
    git init 
    【创建本地仓库，这时候项目根目录会多一个隐藏文件夹.git,表示创建成功(如果要删除本地仓库，把这个文件夹删除即可】


    ls -al 可以产看当前路径所有文件
```


## 3. 将本地项目提交到Github
```
<1>添加
    git add AndroidManifest.xml    【添加单个文件】
    git add src   		【添加某目录(src目录下所有文件)】
    git add .     【将项目所有文件添加(add用于把想要提交的代码先添加进来)】

<2>提交
    git commit -m "First commit"  【将添加的文件提交到库，并且添加"描述"】

<3>关联GitHub
    将GitHub创建的repository的HTPPS复制(例：https://github.com/Suvan-L/Android_Test.git)

    git remote add origin https://github.com/Suvan-L/Android_Test.git

<4>
    git pull origin master

<5>事务提交，将所有更改上传到服务器
    git push -u origin master 【第一次上传会弹框提示输入：Github帐号(邮箱)和密码】

<6>刷新Github项目页面，内容变化，成功提交！


归纳：
git init
git add README.md
git commit -m "first commit"
git remote add origin https://github.com/Suvan-L/BroadcastBestPractice.git
git push -u origin master


```