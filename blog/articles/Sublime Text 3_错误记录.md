---
title: Sublime Text 3_错误记录
date: 2016-04-17 15:04:32
tags: Sublime Text 3
categories: Sublime Text 3
---


一.目录
======================

1. 使用sublime text 3打开文件，文件名显示乱码
2. Sublime Text 破解【输入激活密钥】
3. 汉化
4. 替换整个文件夹多个文件的内容
5. Sublime个人设置







## 1.使用sublime text 3打开文件，文件名显示乱码
```
　以sublime text 3工具随意一个文档
　　　菜单栏 Preference, 
　　　  进入Settings-User
　            最后加上一行
　         "dpi_scale": 1.0【覆盖操作系统设置的DPI】



举例：Settings-User
	{
	"font_face": "Consolas",
	"font_size": 15,
	"ignored_packages":
	[
	"Vintage"
	],
	"line_padding_bottom": 1,
	"line_padding_top": 1,
	"tab_size": 4,
	"translate_tabs_to_spaces": true,
	"word_wrap": "true",
	"dpi_scale": 1.0 #主要是这行生效！
	} 
```



## 2. Sublime Text 破解【输入激活密钥】

+ 输入注册码
```

<1>文档以Sublime Text打开
			————> Help ————> Enter License

<1>3126版本
	—– BEGIN LICENSE —–
	Nicolas Hennion
	Single User License
	EA7E-866075
	8A01AA83 1D668D24 4484AEBC 3B04512C
	827B0DE5 69E9B07A A39ACCC0 F95F5410
	729D5639 4C37CECB B2522FB3 8D37FDC1
	72899363 BBA441AC A5F47F08 6CD3B3FE
	CEFB3783 B2E1BA96 71AAF7B4 AFB61B1D
	0CC513E7 52FF2333 9F726D2C CDE53B4A
	810C0D4F E1F419A3 CDA0832B 8440565A
	35BF00F6 4CA9F869 ED10E245 469C233E
	—— END LICENSE ——

	----- BEGIN LICENSE -----
	Michael Barnes
	Single User License
	EA7E-821385
	8A353C41 872A0D5C DF9B2950 AFF6F667
	C458EA6D 8EA3C286 98D1D650 131A97AB
	AA919AEC EF20E143 B361B1E7 4C8B7F04
	B085E65E 2F5F5360 8489D422 FB8FC1AA
	93F6323C FD7F7544 3F39C318 D95E6480
	FCCC7561 8A4A1741 68FA4223 ADCEDE07
	200C25BE DBBC4855 C4CFB774 C5EC138C
	0FEC1CEF D9DCECEC D3A5DAD1 01316C36
	------ END LICENSE ------

	—– BEGIN LICENSE —–
	Nicolas Hennion
	Single User License
	EA7E-866075
	8A01AA83 1D668D24 4484AEBC 3B04512C
	827B0DE5 69E9B07A A39ACCC0 F95F5410
	729D5639 4C37CECB B2522FB3 8D37FDC1
	72899363 BBA441AC A5F47F08 6CD3B3FE
	CEFB3783 B2E1BA96 71AAF7B4 AFB61B1D
	0CC513E7 52FF2333 9F726D2C CDE53B4A
	810C0D4F E1F419A3 CDA0832B 8440565A
	35BF00F6 4CA9F869 ED10E245 469C233E
	—— END LICENSE ——


<2>3141 ~ 3143版
—– BEGIN LICENSE —–
TwitterInc
200 User License
EA7E-890007
1D77F72E 390CDD93 4DCBA022 FAF60790
61AA12C0 A37081C5 D0316412 4584D136
94D7F7D4 95BC8C1C 527DA828 560BB037
D1EDDD8C AE7B379F 50C9D69D B35179EF
2FE898C4 8E4277A8 555CE714 E1FB0E43
D5D52613 C3D12E98 BC49967F 7652EED2
9D2D2E61 67610860 6D338B72 5CF95C69
E36B85CC 84991F19 7575D828 470A92AB
—— END LICENSE ——

```



## 3. 汉化
```
汉化
网上下载Default.sublime-package 文件。

打开sublime text 3，
　 preferences->
　      Browse Packages：
　     显示出文件管理器
 

　      返回上一层到sublime text 3 文件夹，
　      打开“Installed Packages”文件夹。
　        粘贴汉化包文件“Default.sublime-package”
　     到“Installed 
　　Packages”文件夹下面，无需刷新即可看到汉化效果。不够　汉化不是很完整。

Sublime Text 3汉化包

```




## 4. 替换整个文件夹多个文件的内容

+ [2. 替换整个文件夹多个文件的内容【指定内容】](http://heipark.iteye.com/blog/2124040)





## 5. Sublime个人设置
```
Preferences ---> Settings 


{
	"dpi_scale": 1.0,
	"font_face": "Comic Sans MS",
	"font_size": 12.0,
	"ignored_packages":
	[
		"Vintage"
	],
	"line_padding_bottom": 1,
	"line_padding_top": 1,
	"tab_size": 4,
	"translate_tabs_to_spaces": true,
	"word_wrap": "true"
}

```

