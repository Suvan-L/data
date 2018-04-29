---
title: 腾讯云服务器搭建 SVN
date: 2016-11-22 15:06:25
tags: SVN
categories: SVN
---


目录
==============================
1. 腾讯云安装SVN服务端
2. SVN服务端新建仓库
3. SVN客户端操作



## 1. 腾讯云安装SVN服务端
```
<1>.进入腾讯云：https://console.qcloud.com/cvm
     (使用的云服务器主机系统:Windows Server 2012 R2 Datacenter)

	搜索到个人云产品---->云主机
	访问
		外网通过：192.29.XXX.XX
		内网: 10.104.XXX.XX


	右键更多----->配置安全组
		ID:sg-6ukk3u85 Windows安全组放通3389端口
		ID:sg-3f1ux07f默认安全组放通全部端口
		(将这两项全部打勾，就可以远程访问外网IP啦)



2.安装
	VisualSVN-Server(服务器端)，一直Next，选择默认项就行(选标准版)
	开启VisualSVNServer服务


3. 在个人本地安装TortoiseSVN【用于连接SVN的客户端】

```
<br>


## 2. SVN服务端新建仓库
```
打开VisualSVN Server Manager
1.右键VisualSVN Server(Local)
			Properties
				Network
					Server name栏填入外网IP,Server port栏填入端口号443---->确定


新建仓库
右键Repositories
	新建
		Repository
			Regular FSFS repository
				输入仓库名	->  一直"下一步"  -> finish


新建用户
右键Users
	新建
		User
			依次输入用户名,密码	->	确定


给仓库配置用户
右键仓库
	Properties
		Add
			选择用户，设置权限	->	确定


仓库的URL
右键仓库
	Copy URL to ....



在远程客户端
右键桌面
	TortoiseSVN
		输入仓库URL
			弹出输入用户名密码	->	成功访问
```
## 3.SVN客户端操作
```
<1>取消自动登录
	桌面右键TortoiseSVN
		Settings
			Saved Data
				Authentication data 的Clear或者Clear选项

<2>连接SVN仓库
        任意位置右键TortoiseSVN  
        ~Repo-browser
        ~输入仓库地址

<3>命令指引
        左界面 ~ 右键文件夹
                checkout  导出
                 create folder   服务器内创建文件夹

```



