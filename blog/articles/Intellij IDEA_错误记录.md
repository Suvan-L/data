---
title: Intellij IDEA_错误记录
date: 2017-04-27 08:50:41
tags: Intellij IDEA
categories: Intellij IDEA
---

# 目录

+ [IntelliJ IDEA 简体中文专题教程](https://github.com/judasn/IntelliJ-IDEA-Tutorial)

1. 将项目远程提交到Github
2. 设置Tomcat热更新[静态动态网页文件]
3. 安装JRebel插件,实现热部署[java文件]
4. idea的代码生成器[Live Templates]
5. 自动提示方法参数[鼠标移动到方法名]
6. 配置SFTP[远程服务器用于上传文件]
7. idea每次运行Java文件,提示Warning(:java:)资源值1.5已过时
8. idea常用快捷键
9. ideaVim插件
10. emacsIDEAs插件
11. IDEA创建Maven项目
12. IDEA从Github上面Clone项目,本地配置Maven
13. 将clone的项目更改后,pull到Github
14. 安装waketime【记录coding时间】



## 1. 将项目远程提交到Github
```
1.菜单栏VCS
	-> Import into Version Control
		 ->	Share project on GitHub

2.Settings
	->	Version Control
		->	Github 填入帐号密码
		->  Git 安装Git，并指定git.exe路径，Test测试

3.提交
	VSC -> Update Project...[更新项目]
		-> Commit Changes...[提交修改]

	 ->右键项目
	 	->Git 
	 		-> Repository -> Pull..【下载】
	 						 Push..【提交】

```
---
<br><br>


##  2. 设置Tomcat热更新[静态动态网页文件]
```
顶部菜单
Run
	->Edit Comfigurations
		->配置Tomcat Server
			(1)->点击Deployment,添加Deploy at the server startup【相当于部署】
				->'+'号,点击Artifact..
				->选择项目名:war exploded【exploded是部署到Tomcat的webapps目录下,效率个人能更高】

				【可选：可删除底部的Build(提高效率)】

			(2)->回到Server
				On 'Update' action 和On frame deactivation 
				这两项目一定要选择 Update classes and resources


		->Apple  ->OK  【配置成功】


```


##  3. 安装JRebel插件,实现热部署[java文件]

参考资料
+ [IDEA 破解激活Jrebel6.4使用教程.zip【百度云】](https://pan.baidu.com/s/1bpsMm3l)
+ [IntelliJ IDEA - JRebel安装使用教程](http://www.jianshu.com/p/008bd27bbd77)
  ​

安装+破解步骤
```
<1>安装
        a.【直接搜索插件下载】
                IDEA --- File ---Settings --- Plugins --- Browse reponsitories 在搜索框输入 Jrebel 即可安装下载
        b.【压缩包安装】
                IDEA --- File ---Settings --- Plugins---Install plugin from disk...  -->jr-ide-idea-6.4.0-idea-13-15.zip

<2>破解
            a.解压"jrebel-6.4.0-Enterprise.Cracked-ZCT.zip"
            b.将解压出来的
                    jrebel 
                    jrebel6
                    jrebel.lic(许可证)
                 直接拉到C:\Users\sun\.IntelliJIdea15\config\plugins\jr-ide-idea\lib目录,覆盖

            c.File--settings ---Jrebel --- 右上角 Open Activation Dialog,弹框 --》
                中间那行License file from the hard drive,选择Browse,选择上述的jrebe.lic文件


            d.窗口内容改变,变成JRebel activated，激活陈功

```


## 4. idea的代码生成器[Live Templates]
```
顶部菜单 -> File -> Settings【快捷键打开：ctrl + ald + s】
						-> Editor
							-> Live Templates  


java
	sout	-> 	(System.out.printl)
	psvm	->  (main()-主函数)

```


##  5. 自动提示方法参数[鼠标移动到方法名]
```
ctrl + alt + s  
	->Editor -> General 

		-> 右边窗:Show quick documentation on mouse move Delay

```


## 6. 配置SFTP[远程服务器用于上传文件]

流程：
1. 在远程服务器(windows Server)上安装FreeSSHhd软件[FreeHd官网](http://www.freesshd.com/)
2. 在idea配置sftp服务器
3. 上传文件

```
1. 在远程服务器(windows Server)上安装FreeSSHhd软件
 	a.根据提示一步步安装->安装完成后会弹框2个[中译:是否安装密钥 + 是否安装成系统服务]
    b.安装后 Servert status -> 开启SSH server is running[如果报错则吧系统服务关闭后，关闭软件,以系统管理员身份启动]
    c.Authentication 
    		->Password authentication设置成Allow(或者 Required)
    		->Public key authentication 设置成 Disbled
    d.SSH
    	->Listen address设置成 <服务器ip>
    e.SFTP
    	->SFTP home path(上传根目录)
    f.Users
    	->Add(添加用户)
    		->Login [登录名]
 			->Authroization [验证方式]
 			->User can use [权限，打勾SFTP]


****************************************************

2.idea操作
	Settings[快捷键:ctrl + alt + s]
		-> Build,Execution,Deployment
			->Deployment
				->'+'号
					Name [昵称(随意填写)]
					Connection
						<1>Type		[sftp类型]
						<2>SFTP host	[服务器ip]【Test SFTP..测试是否能连接服务器】
						<3>Port			[SSH端口]
						<4>Root path	[根据FreeSSHhd的设置路径后的继续路径]
					Mappings
						<1>Deployment path on server[根路径后的部署路径]


```

## 7. idea每次运行Java文件,提示Warning:java:资源值1.5已过时
```
提示报错
	Warning:java:源值1.5已过时,将在未来所有发行版中删除
	Warning:java:目标值1.5已过时,将在未来所有发行版中删除
	Warning:java:要隐藏有关已过时选项的警告,请使用-Xlint:-options


解决办法
	快捷键”Ctrl+Alt+S”打开设置，
	   ->搜索”Java Compiler”
	   ->将默认jdk和当前modual的jdk版本切换为1.8即可

```


## 8. idea常用快捷键
```

ESC  关闭弹窗

Alt + INS  新疆类or方法
Alt + NUM (数字键)  切换窗口

shrit + F6  文件重命名
shift + shift 搜索框(任意文件)
shift + ESC 关闭窗体模块

ctrl + D 复制行
Ctrl + E 打开最近编辑过的文件
Ctrl + F 文本搜索框(当前页)
Ctrl + H 查看类的层次
Ctrl + j  查看模版
Ctrl + N 搜索类
Ctrl + W 层级选中
Ctrl + Y 删除行


Ctrl + K 本地提交【添加版本控制后】
Ctrl + shfit + K  远程提交(Github)
Ctrl + Tab 切换标签

Ctrl + F4 关闭当前页面
ctrl + F12 查看类的所有方法

Ctrl + shift + A 查找所有命令 or 快捷键
Ctrl + shift + 空格  智能补全
Ctrl + shift + F10  运行类


方法上一行输入/** 然后直接回车,会自动生成javadoc

```


## 9. ideaVim插件
---------------------
参考资料
+ [IdeaVim:JetBrains Plugin Respository](https://plugins.jetbrains.com/plugin/164-ideavim)
+ [如何成为 IntelliJ IDEA 键盘流 -- 知乎](https://www.zhihu.com/question/20783392)

使用插件
+ 翻译插件[Translation Plugs](https://github.com/YiiGuxing/TranslationPlugin)
+ 背景图片插件(BackgroundImage)[https://plugins.jetbrains.com/plugin/72-backgroundimage]
+ 代码特效插件[activate-power-mode for IDEA](https://github.com/ViceFantasyPlace/activate-power-mode)
+ 代码检查[Statistic](https://plugins.jetbrains.com/plugin/4509-statistic)
+ 左侧小地图[CodeGlance](https://plugins.jetbrains.com/plugin/7275-codeglance)

安装
```
a.Ctrl + Alt + s 打开Seetings
b.Plugins
c.Install plugin from dis....
d.选择下载好的压缩包IdeaVim-0.48.zip
e.确认,插件前方打勾,Applet或者ok
f.重启IntelliJ IDEA
```

---

常用操作
```
Alt + v开启工具(原默认是ctrl + alt +v)

Alt + a 左右视图光标跳转(keymap ~ select next windows)
Alt + i 关闭插入模式


i 插入模式【自定alt + i 退出插入模式】
v 选中模式
R 进入 覆盖模式
c 上下移动模式
Esc(或者Ctrl + [) 退回默认模式,光标移动


插入模式
     i 当前词左边光标插入
     a当前词右边光标插入
     A 行首光标插入
     I 行尾光标插入
     o 下行光标
     O 上行光标

    c0 删除到行首,插入光标
    c$ 删除到行尾,插入光标



/  搜索(可输入,输入完回车)
n  下一个
N 上一个

h j k l   左,下,上,右
H J K  L 页面顶部,合并两行,帮助,页面尾部

10j 向下移动10行
5k 向上移动5行

: 12  移动到12行

< >前缩进 ,后缩进

0 $  跳到行首,跳到行尾



w(W)词组"正向"跳转
b(B)词组"反向"跳转

x  删除自己+后面
X 不删除自己+删前面

d 删除“选中行”
dd 删除当前行
y 复制"选中行"
yy 复制当前行

p  粘贴
5p 粘贴5次

u 撤销

r 更改当前词

. 重复命令

```


## 10. emacsIDEAs插件
参考资料：
+ [emacsIDEAs::JeBrains Plugin Response](https://plugins.jetbrains.com/plugin/7163-emacsideas)
+ 下载安装emacsIDEAs.jar插件,重启IntellijIDEA


常用操作
```
在Setings 
	-> Keymap
		-> AcejumpChar【查找字符功能】,修改快键为alt + e
```


## 11. IDEA创建Maven项目

参考资料
+ [构建工具-Maven-相关知识-整理专题](https://github.com/judasn/IntelliJ-IDEA-Tutorial)
+ [IntelliJ IDEA 15 创建maven项目【图文教程】](http://www.cnblogs.com/wql025/p/5215570.html#autoid-0-0-0)
+ [解决intellij idea新建maven项目，加载archetype模型很慢 ](https://my.oschina.net/boltwu/blog/713523)

```
<1>打开IDEA，关闭当前项目Close project
            a.创建 Create New Project   
            b.左侧 Maven,右侧 打勾Create from archetype
            c. 选择org.apache.maven.archetypes:maven-archetype-webapp模版,Next 
            d.输入Groupld(项目分组id,建议org + 组织名字)     org.neusoft  (org非盈利,com商业)
                    Artifactld (项目id,输入项目名)                         neubbs

            e. Maven home Directory(选择Maven版本)
                User settings file (指定本地Maven根目录,conf目录下的settings.xml)
                Local respository(指定本地repository的目录】
                添加：
                    archetypeCatalog: internal (标识仅使用内部元数据)

<2>创建完毕
         a.进入Project Structure【快捷键ctrl+shift+alt+s】
         b.左侧 
                Project     设置JDK
               Models      指定源代码目录(蓝色),测试代码目录(test)
                                    在scr下新建test目录,
                                        src->main下新建java目录

                                    找到Dependencies,点击 + 添加Library
                                            Applciation Server Server Libraries 
                                            添加Tomcat 8.0.41的库

                Libries      根据需求添加jar包
                Artifacts 设置war包【用模版的则默认已经添加好】


<3>设置Tomcat
        a.找到导航栏,'向下三角形' ，点击Edit configurations...
        b.左边找到Tomcat Server, 点击local
        c.右边界面
                Server 
                        Name  显示昵称(任意命名)
                        Applciation server   选择服务器
                        Open..的URL地址栏
                                据爱如项目名,变成http://localhost:8080/neubbs

                 Deplyment
                        右边 '+',选择Artifact
                            选择neubbs: war exploded【将WEB工程以当前文件夹的位置上传到服务器】
                                    【另外的neubbs: war 将web工程以包的形式上传到服务器】

                    回到Server
                        On Update action (更新按钮)
                      On frame deactivation (失去焦点时自动编译)
                                 都修改成Update classes and resoureces 【热更新】

```


## 12. IDEA从Github上面Clone项目,本地配置Maven
```
<1>解压Maven包,打开conf -> settings.xml
        定位到localRepository，取消注释,并指定本地仓库
          <localRepository>本地repository的目录</localRepository>


<2>进入到Welcome to IntelliJ IDEA界面(若之前打开了项目,则File -> Close Project)
                a.右下角下拉列表Configure,点击Settings,进入到Default Settings界面
                b.搜索Git,目录选择Git目录下的cmd的git.exe,点击Test测试
                c.搜索Maven
                            Maven home directory：  选择Bundled(Maven 3)
                            User settings file: 选择Maven根目录的conf目录里的settgins.xml文件
                            Local repositoyr: 选择本地respository仓库
                d.搜索Github
                            Host: github.com                           Auth Type:Password
                            Login: Github账户
                            Password: Githu密码
                                点击,Test进行测试


<3>回到Welcome界面
                a.点击Check out from Version Control下拉列表
                b.  选择Github
                                需要登录
                                        Git Repository URL:  填写项目的URL(比如:https://github.com/nuitcoder/neubbs.git)
                                        Parent Diertory:  填写父目录(项目目录放在哪个目录下)
                                        Directory Name: 项目目录名(不用改变) 
                        选择Githut
                               不用登录
                                        同上填写三个(URL可以进行Test)测试
                c.clone,然后打开项目
```


## 13. 将clone的项目更改后,pull到Github
1. 修改代码
2. 本地提交(Ctrl + k) ,需要输入commit message
3. 远程提交(Ctrl + shift + k), 需要选定分支



## 14. 安装waketime【记录coding时间】
&emsp;记录代码时间

+ [WakaTime :: JetBrains Plugin Repository](https://plugins.jetbrains.com/plugin/7425-wakatime)
+ [wakatime/jetbrains-wakatime](https://github.com/wakatime/jetbrains-wakatime)
+ [官网(注册,然后获取API key)](https://wakatime.com/dashboard)
