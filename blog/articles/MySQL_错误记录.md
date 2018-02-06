---
title: MySQL_错误记录
date: 2016-04-17 15:04:08
tags: MySQL
categories: MySQL
---

一.目录
========================
1. 每晚12点MySQL会弹出一个初始化窗口,如何禁止？





## 1. 每晚12点MySQL会弹出一个初始化窗口,如何禁止？
```
<1>解决思路
　　Open Task Scheduler by clicking the Start button ,
　　clicking Control Panel, clicking System and Security,
　　clickingAdministrative Tools, 
　　and then double-clicking Task Scheduler. 
　　If you're prompted for an administrator password or confirmation, 
　　　type the password or provide confirmation.


	翻译
		控制面板
		　　   类别-系统和安全
		　　      管理工具-计划任务
		　　           任务计划程序（本地）
		　　           任务计划程序库
		　　              MySQL
		　　                   Installer
		　　                        ManifestUpdate【这个禁用掉】

```