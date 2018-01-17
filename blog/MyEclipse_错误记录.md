---
title: MyEclipse_错误记录
date: 2016-05-19 12:14:48
tags: MyEclipse
categories: MyEclipse
---

目录
===========================

1. 部署错误





## 1. 部署错误
```
不停的弹出报错信息
Errors occurred during the build.
Errors running builder 'DeploymentBuilder' on project 'JSPImoocStudy'.
java.lang.NullPointerException

有道翻译：
	错误发生在构建。
	错误运行建设者“DeploymentBuilder”项目“JSPImoocStudy”。
	java.lang.NullPointerException


解决办法：
	1、首先关闭MyEclipse工作空间。
	2、然后删除工作空间下的
	“/.metadata/.plugins/org.eclipse.core.runtime/.settings/com.genuitec.eclipse.ast.deploy.core.prefs”

	取消myeclipse的自动部署:
		右键点击项目 ->properties -> Builders,将DeploymentBuilder勾选去除.


注意：
1.此问题一般发生在Myeclipse 保存文件并自动部署时候。
2. L另一种产生此错误的原因是因为此项目不不是由myeclipse创建的。
所以需要检查.project 文件。并且添加com.genuitec.eclipse.j2eedt.core.webnature

```




