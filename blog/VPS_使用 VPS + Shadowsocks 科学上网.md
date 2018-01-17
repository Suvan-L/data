---
title: 使用 VPS + Shadowsocks 科学上网
date: 2017-01-10 14:03:00
tags: VPS
categories: VPS
---


# 目录
1. 资源 and 教程
2. 构建思路
3. 注意事项


&emsp;之前一直是用大牛老 D 的修改 host 的方法来翻墙，[2016 Google hosts - 老D博客](https://laod.cn/hosts/2016-google-hosts.html)，但是不稳定，DNS 污染验证，而且不定时要更新，现在是在搬瓦工上购买VPS【虚拟服务器】，尝试自己搭建 Shadowsocks server 服务端，科学上网

- 2017.01.08 购买，优惠后 19.03 美刀（== 131.06 RMB）
- 2018.01.16 续费，优惠后 19.04 美刀（== 123.25 RMB）




## 1. 资源 and 教程
+ [搬瓦工中文网](http://banwagong.cn/)
+ [BandwagonHOST-国外官网](https://bwh1.net/)
+ [使用shadowsocks轻松搭建翻墙环境教程](https://blog.phpgao.com/shadowsocks_on_linux.html)
+ [黑科技，效果逆天！使用FinalSpeed给搬瓦工(Bandwagonhost)加速！](https://blog.kuoruan.com/82.html)



## 2. 构建思路
1. 根据搬瓦工中文网教程，去BandwagonHost购买VPS,注意认准配置，然后选好机房【不同地区的网速是不一样的】,我买的年付19.9USD的洛杉矶【内存255,硬盘10G-SSD,流量1000GB/月】，支持支付宝付款【首次需注册帐号】
2. 登录个人用户，进入MyServies模块，选择KiwiVM Control Panel
3. 在KiwiVM的左边栏，KiwiVM Extras里的Shadowsocks Server【一键安装SS，安装完后有教程】，根据windows版本下载相应客户端即可
4. 启动客户端，填入相应资料,右键任务栏小图标，勾选Enable System Proxy，开启代理
5. 修改浏览器代理，【使用系统代理或者手动配置123.0.0.1 端口1080】
6. 开心的浏览https://www.google.com/




## 3. 注意事项
+ 可在Shadowsocks服务端开启多端口，设置多用户，给不同的人使用
+ 浏览器需要设置使用系统代理或者手动配置代理
+ Shadowsocks客户端会自动判断访问改网址的时候是否被墙,如果被强，则使用代理访问，没有被强就使用本地IP【浏览器代理配置~~~设置使用系统代理】【百度搜索栏打IP，进行测试】
+ 如果以前使用修改host的方式，那么需把文件改回最初默认【否则你的所有访问，都会使用国外服务器进行访问】
+ 360浏览器的免费翻墙就是通过修改host实现的【流氓软件】
+ 可以使用FieldSpeed【FS加速器进行加速，需配置服务端和客户端,java编写，还需要安装JAVA运行环境】【1.2版本不稳定，经常报错提示fs服务器连接失败】【我的win8直接就没成功】
+ Shadowsocks简称SS【注意不是SSH】,有很多版本，什么ShadowsocksR之类的，没试过需要的话自己去试试

