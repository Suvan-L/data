---
title: 基础环境配置
date: 2016-12-05 08:50:28
tags: Android
categories: Android
---

# 一.目录
1. eclipse配置Android环境
2. SDK 设置




## 1. eclipse配置Android环境
--------------------------
```
1.安装JDK
2.安装eclipse
3.安装AD(安卓开发工具）
        Eclipse
            _Help   __Install NewSoftware __点击Add(右边上角) 
                    【方式1】在线安装
                                Name: ADT Plugin
                                Location(网址):https://dl-ssl.google.com/android/eclipse/
                    【方式2】离线安装
                                下载压缩包:https://dl.google.com/android/ADT-23.0.6.zip

                                Name: ADT Plugin
                                Localtion:   (点击Archive按钮,选中ADT压缩包)

          __(选中Developer Tools)Next __(下载列表)Next__(阅读接收协议,打勾)Next 、
          __等待安装完成,重启eclipse

4.配置Android SDK(Android环境包)
        windows
            ___Preferences__Android__点击Browse,选中SDK环境(我的是android-sdk-windows)__点击Apply
```


## 2. SDK设置

参考资料
+ [Android SDK 在线更新镜像服务器资源 ](http://ask.android-studio.org/?/article/34)

```
<1>修改在线SDK国内镜像源
        点击Android SDK Manager
                ___Tools__Options__依次填写国内源和端口
```
