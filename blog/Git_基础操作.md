---
title: Git_基础操作
date: 2016-12-08 11:08:01
tags: Git
categories: Git
---


# 参考资料
+ [Git for Windows 国内下载站](https://github.com/waylau/git-for-win)
+ [图文详解如何利用Git+Github进行团队协作开发](https://zhuanlan.zhihu.com/p/23478654)
+ [使用 Github 空间搭建 Hexo 技术博客--安装篇（基于 IntelliJ IDEA）](http://code.youmeek.com/2016/02/28/2016/02/Hexo/)
+ [如何使用Git上传项目代码到github](http://1ke.co/course/194)
+ [廖雪峰 史上最浅显易懂的Git教程！](https://www.liaoxuefeng.com/wiki/0013739516305929606dd18361248578c67b8067c8c017b000)


# 目录
1. 忽略文件
2. 查看修改内容
3. 撤销未提交修改
4. 查看提交记录
5. 将项目提交到Gihub



## 1. 忽略文件
```
1. 进入项目跟目录，右键 Git Bash Here,输入`touch .gitigore` 【创建配置文件】
2. 在.gitigone文件里输入要忽略的文件夹及其文件就可以，指定的文件或目录可以使用*通配符

touch .gitigore
bin/
gen/
【在.gitigore加入上传时候就会忽略这两个文件】

```


## 2. 查看修改内容
```
查看文件修改情况：`git status`   
查看那文件更改的内容：`git diff`

修改MainAcitivty.java内容，加入一段注释
再次输入git status 查看文件修改情况


查看相应文件更改的详细内容 
git diff src/com/example/broadcastbestpractice/MainActivity.java
```


## 3.撤销未提交修改
```
<1>撤回提交
	输入以下命令，将文件恢复到修改前
	git checkout src/com/example/broadcastbestpractice/MainActivity.java

	输入git status查看文件修改情况

	注意：
	    这种方式只是用于那些还没执行过add命令的文件如果某个文件已经被添加，那么这种方式无法撤销其更改


<2>撤回添加
	如果已经使用了add命令，那么得先取消添加，容纳后才可以撤回提交

    <1 修改MainActivity.java文件
    <2 git add .
    <3 git reset HEAD src/com/example/broadcastbestpractice/MainActivity.java
    <4 git checkout src/com/example/broadcastbestpractice/MainActivity.java
    <5 git status
```


## 4.查看提交记录
```

打开git bash 
    cd e: 
    cd java
    ls
    cd Intellij\ IDEA  【目录有空格需要加\】

    git add .
    git commit -m "Third price."

    git log     --->查看所有提交
    git bc51e94dd558e30349f87fb059414fdb20c15dd2  -1    -->只想查看一条记录，可在命令行中指定该记录id， 并加上-1 参数表示只想看到一行记录

    git bc51e94dd558e30349f87fb059414fdb20c15dd2  -1 -p  --->表示查看这条记录具体改变了什么内容

```


## 5. 将项目提交到Gihub
```
git status  查看做了那些修改
git diff    查看哪些修改

git add .     添加所有修改
git add  XXX   只添加指定文件修改

git commit -m "修改 TestUser 测试用例"     提交本地修改

git pull origin master     将最新修改pull下来
git push orgin master     将自己的修改push上去

```