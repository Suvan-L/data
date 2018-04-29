---
title: Linux_错误记录
date: 2016-04-17 15:03:48
tags: Linux
categories: Linux
---

目录
======================

1. Xshell4无法连接VMWaare虚拟的CentOS系统
2. 忘记root密码




## 1. Xshell4无法连接VMWaare虚拟的CentOS系统
```
<1>在VMWARE 中开启CentOS虚拟机

　　登入系统后直接输入 dhclient (自动获取ip)...

　　然后再输入 ifconfig 
　　就看到系统自己帮你配的ip！
　　　　（eth0:
　　　　inet addr.192.168.92.128）


22>如果上述方法不成功，先CentOS关机，
　　 查看虚拟机设置
　　　　 网络适配器

　　　　  网络连接    
　　　　　　 选择桥接模式：直接连接物理网络
　　　　　　　　（打勾:复制物理网络连接状态）

　　　重新开机
        输入ifconfig/all查看IP
```




## 2. 忘记root密码
```
　关闭客户机

　　打开客户机

　　加载界面按Enter(回车)

　　　　选择**CentOS**系统，按E进入

　　　　　　利用上下键 找到黑色标识的**kernel**,按e进入edit(修改编辑)页面

　　　　　　　　在本行最后，添加一个空格，然后输入single
　　　　　　　　(这是告诉Linux内核下一步是要进入单用户模式)

　　　　　　　　按Enter（回车）

　　　　　　按B，进行重启操作

　　系统自动运行，直到停下来，输入**passwd**（这是修改密码的指令）

　　按Enter(回车)

　　　　输入新密码

　　　再Enter（回车），

　　　　再次输入新密码

当出现successfully，表示修改密码成功

然后输入**reboot**，重启虚拟机

```

