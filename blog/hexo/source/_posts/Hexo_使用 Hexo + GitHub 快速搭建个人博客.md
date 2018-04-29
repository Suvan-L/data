---
title: 使用 Hexo + GitHub 快速搭建个人博客
date: 2016-07-12 13:45:08
tags: Hexo
categories: Hexo
---

目录
=====================

1. 注册GitHub账户
2. 在GitHub上建立仓库
3. 安装git和node.js
4. Hexo安装
5. 配置SSH Key【让本地git项目与远程的GitHub建立联系】
6. 本地测试Hexo
7. 域名解析
8. 博文模版
9. hexo主题目录结构
10. 修改博客主题
11. 安装Category插件
12. 可能遇到的一些问题



## 1. 注册GitHub账户
GitHub官网：https://github.com访问GitHub,注册十分简单，【一定要记住注册时使用的邮箱，
　因为 GitHub 上很多通知都是通过邮箱的。】

>申请成功后，在GitHub官网上登录，并验证邮箱即可。



## 2. 在GitHub上建立仓库
&emsp;与 GitHub 建立好连接之后，就可以方便的使用它提供的 Pages 服务，

GitHub Pages 分两种:

+ 用你的GitHub用户名建立的username.github.io这样的用户&组织站点，
+ 依附项目的Pages。

【想建立个人博客是用的第一种，形如username.github.io这样的可访问的站点， 每个用户名下面只能建立一个,向用户首页一样】


创建仓库
```
(1)浏览器进入https://github.com
(2)点击右下角的 +New Repository
　　找到Repository name 
　　　     ----按照username.github.io格式(username是你的用户名，就是Owner一样的)
    Description
　　      ----仓库描述随便写
    
    选择Public

    点击Create repository创建


成功！
```


##  3. 安装 git 和 node.js
+ [git for windows](https://git-for-windows.github.io/)
+ [node.js 官网](https://nodejs.org/en/)


安装流程：
1. 安装git(windows版本是gitbash)
2. 安装node.js,包含了npm
3. 任意路径,右键开启git bash,输入node -v和npm -v查看是否安装成功
4. 在node.js目录下新增两个文件夹node_cache(缓存)和node_global(全局)



##  4. Hexo安装
```
<1>以管理员的身份启动命令行【全局任意地方】,使用npm命令安装hexo-cli包
        npm install hexo-cli -g 

<2>新建Hexo目录,命令行进入,部署npm环境
    npm install   

<3>找到hexo脚本存放目录(我的是C:\Program Files\nodejs\node_global)
    将其添加环境变量到Path
```


##  5.配置SSH Key【让本地git项目与远程的GitHub建立联系】
```
<1>开启gitbush配置用户名密码
            git config --global user.name "suvan"
            git config --global user.email "13202405189@163.com"

<2>生成SSH密钥
        ssh-keygen -t rsa -C "13202405189@163.com"
        在C:\Users\Liu\.ssh 的id_rsa即为公钥　	

<3>进入Gihub设置密钥
            a.https://github.com/登录自己Github
            b.右上角小图标Settings  ·~~~ 右边SSH and GPG Keys
            c.SSH keys 点击 New SSH Key
            d.Title随便填,将id_rsa的内容复制到Key中,点击Add SS Keys
            e.点击蓝色小字体generatings SSH keys
            f.点击倒数第二个Testing your SSH connection
            g.开启Gitbash输入
　　　     ssh -T git@github.com
                根据提示如入yes,最后显示
                Hi Suvan-L! Your've successfully authenticated,but GitHub does not provide shell access
                表示验证成功【.ssh目录多出一个known_hosts文件】 


<4>将hexo根目录下的_config.yml配置文件修改为SSH连接【无密码】
            进入https://github.com/Suvan-L/Suvan-L.github.io
            点击clone or download
            选择Use SSH
                复制连接git@github.com:Suvan-L/Suvan-L.github.io.git
    
            修改至config.yml文件内的responsitory
            deploy:
                    type: git
                    repository: git@github.com:Suvan-L/Suvan-L.github.io.git
                    branch: master

```


##  6. 本地测试Hexo
```
<1>以管理员模式启动cmd
　　　 e:
　　　 cd Hexo   

<2>可输入命令

            hexo clean   清除public目录

　　　hexo generate[hexo -g]  ---生成静态页面（生成静态页面）

           hexo deploy[hexo -d]  部署到gitHub

　       hexo server[hexo -s]   ---启动本地服务，进行文章预览调试
　  　　　　	（ctrl+c 然后根据提示输入y退出）
            浏览器中地址输入：http://localhost:4000 查看是否可以显示预览页面
            需要下载本地服务器模块
                1>下载server模块
                    npm install hexo-server
    
                    注意：如果请求localhost:4000无反应
                        可尝试
                            a.切换端口号 hexo server -5000
                            b.使用127.0.0.1:4000
```

##  7.域名解析
```
腾讯云服务器
	域名解析 -> 添加记录

		记录类型 主机记录 记录值
		 CNAME    www      Suvan-L.github.io.


A.在hexo/source,新建CNAME文件【注意，没有扩展名】
	里面添加www.liushuwei.cn   -> 保存 ->使用gitshell部署到Github

B.直接在Github的 Suvan-L.github.io/CNAME
	添加www.liushuwei.cn    -> 提交


注意：
	1.只能有一个域名指向,Suvan-L.github.io/CNAME中默认选定第一个域名，其余无效



```


##  8.博文模版
```
　　  顶端的格式是这样的(注意，冒号后面要+空格)

        ---
        title: windows常用命令        -------【博文标题】
        date: 2016-04-16 09:14:47     -------【创建时间】
        tags: [Hexo,Next]             -------【标签】
        categories: Hexo              -------【分类】
        ---


          ......正文内容
```



##  9.hexo主题目录结构
```
创建 Hexo 主题非常容易，您只要在 themes 文件夹内，新增一个任意名称的文件夹，并修改 _config.yml 内的 theme 设定，即可切换主题。一个主题可能会有以下的结构：

        .
        ├── _config.yml
        ├── languages
        ├── layout
        ├── scripts
        └── source

config.yml
    主题的配置文件。修改时会自动更新，无需重启服务器。
languages
    语言文件夹。请参见 国际化 (i18n)
layout
    布局文件夹。用于存放主题的模板文件，决定了网站内容的呈现方式，Hexo 内建 Swig 模板引擎，您可以另外安装插件来获得 EJS、Haml 或 Jade 支持，Hexo 根据模板文件的扩展名来决定所使用的模板引擎，例如：
layout.ejs   - 使用 EJS
layout.swig  - 使用 Swig您可参考 模板 以获得更多信息。

scripts
    脚本文件夹。在启动时，Hexo 会载入此文件夹内的 JavaScript 文件，请参见 插件 以获得更多信息。

source
    资源文件夹，除了模板以外的 Asset，例如 CSS、JavaScript 文件等，都应该放在这个文件夹中。文件或文件夹开头名称为 _（下划线线）或隐藏的文件会被忽略。

    如果文件可以被渲染的话，会经过解析然后储存到 public 文件夹，否则会直接拷贝到 public 文件夹。
    发布
```


##  10.修改博客主题
&emsp;　Hexo 有两份主要的配置文件（_config.yml），一份位于站点根目录下，另一份位于主题目录下。
为了描述方便，在以下说明中，将前者称为 站点配置文件，后者称为 主题配置文件。


参考资料
+ [NexT主题基本配置与使用](http://theme-next.iissnan.com/getting-started.html)
+ [hexo的next主题个性化配置教程](https://segmentfault.com/a/1190000009544924)



过程
```
A.下载 NexT 主题,使用 Git Shell 进入 Hexo 文件夹，输入以下两条命令： cd Hexo
  git clone https://github.com/iissnan/hexo-theme-next themes/next
        （GitHub网址）（在Hexo根目录下下的themes文件夹中，创建一个新的文件夹next）


B.启用NexT主题
    下载完成后，打开 站点配置文件(Hexo目录下的_config.yml)，
    找到 theme 字段，并将其值更改为 next 
    【注意：这个是themes目录下的next文件夹的名字）（/Hexo/themes）】



C.选择样式
    NexT默认的样式其实也比较丑，幸好作者提供了一款十分漂亮的样式:Mist。启用 Mist 很简单，仅需在主题配置文件_（config.yml）中
    将 #scheme: Mist 前面的 # 注释去掉即可。
    菜单设置 （第60行）


D.推荐主题[连接]
    　Cover- A chic theme with facebook-like cover photo
　　  Oishi- A white theme based on Landscape plus and Writing.
　　  Sidebar- Another theme based on Light with a simple sidebar
　　  TKL- A responsive design theme for Hexo. 一个设计优雅的响应式主题
　　  Tinnypp- A clean, simple theme based on Tinny
　　  Writing- A small and simple hexo theme based on Light
　　  Yilia- Responsive and simple style 优雅简洁响应式主题
```



##  11.安装Category插件
```

A.下载插件
    Hexo官网，https://hexo.io/
　　选择Plugins
　　      Search（搜索） 输入https:category

    点击下面这个
    hexo-generator-category
    Category generator plugin for Hexo.
    #official #generator #category

B.进入Github页面，拉到最下，
根据教程下载插件
　　   打开Git shell 
　　       进入Hexo文件夹
　　   输入
　　　　　　　　npm install hexo-generator-category --save
　　　　　　　　　　---->下载插件

c.然后打开资源管理器，找到Hexo文件夹
选择根目录下，站点配置文件（_config.yml）
打开
　　　　加入
        category_generator:
        per_page: 10            ------->设置分页显示的文章数量
```


##  12. 可能遇到的一些问题
1. master分支只能建一个这样的页面，这算是你的GitHub用户首页
2. 这个首页username.github.io的username和你的用户名相同（GitHub一个用户只有一个用户名）否则会浏览器输入后会一直显示404
3. 先安装git,在安装node.js(内含npm),否则git无法检测出node.js
  4.以管理员身份启动cmd或者camder,因为有些需要在C盘创建目录需要权限
  6.任意路径都可以使用npm命令,安装node.js时默认配置了全局变量
  7.gitbash,gitshell,cmd,cmder都可以用于来使用hexo,不过方式不同
  5.可参考链接 [使用hexo，如果换了电脑怎么更新博客？](https://www.zhihu.com/question/21193762) 和 部署问题[有关使用 Hexo 和 GitHub 搭建博客，出现 hexo -d 报错如何解决？](https://www.zhihu.com/question/38219432) 