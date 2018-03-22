# IntelliJ IDEA 快捷键
+ 熟悉常用快捷键，提升开发效率，追求键盘流

## idea 
### 默认
```
F2  定位到错误提示信息(shift + F2 定位到前一个)


Ctrl + F 文本查找
Ctrl + R 文本替换
Ctrl + J 插入自定义代码模版（Live Templates）
Ctrl + P 显示方法参数
Ctrl + Q 光标所在地方（变量，类，函数），显示 javaDoc
Ctrl + B 进入所属函数的父对象（父类，父函数，引用变量）（等同于 ctrl + 左键点击）
Ctrl + [(或者 ]) 移动到花括号，开始 or 结束的地方
Ctrl + Tab 出现 Switcher 面板，切换不同模块与文件

Ctrl + 1...9 跳转到指定书签（Ctrl + shift 定义到指定书签）


两下 Shift  全局文件搜索（快速打开文件， Search EveryWhere）
Shift + F6 文件重命名

Alt + 1..9 切换编辑器面板
Alt + 方向键左右  切换标签视图
Alt + 方向键上下  跳转文件内方法

Ctrl + Alt + L 格式化代码（规范缩进，可对当前类 or 包）
Ctrl + Alt + O 优化导入的类（调整 import 的导入为主，可对当前类 or 包）
Ctrl + Alt + T 代码块环绕层（快捷给加入代码块加入 if else 之类）
Ctrl + Alt + S 打开 intelliJ IDEA 系统设置
Ctrl + Alt + B 接口跳转到实现类


Ctrl + Shift + F 文件内容查询（整个项目）
Ctrl + Shift + R 文件内容替换（整个项目）
Ctrl + Shift + N 文件名搜索（可搜索文件，目录【目录需要加斜杠】）
Ctrl + Shift + F10 运行类 or 函数
Ctrl + shift + 空格  智能提示代码
```

### 自定义
```
Ctrl + ,  [keymap: close]关闭当前页面

Alt + v [keymap: Vim Enulator]启动 vim 模式

ctrl + \ [keymap: AceJumpChar] 光标跳转到指定字符

alt + \ [Local history] 查看当前文件历史

alt + ` [Full Screen] 全屏
```


### idea 快捷功能
```
跳转
	项目间跳转 Next(Previous) Project Window
	上次编辑处跳转  Next(Last) Edit Location
	前后跳转 Back(Forward)
	书签跳转 Bookmarks
	编辑区跳转到文件区 [Alt + 1]
	文件区跳到编辑区 [ESC]

收藏
	单独加入收藏列表 Add to Favorites 【可自定义标签（类 or 函数）】
	显示收藏书签列表 Favorites Alt + 2]

查询
	查询快捷键	Find Action [Ctrl + Shift + a]
	显示最近打开文件 Recent File [Alt + Shift + c]


搜索
	类名搜索类 Class [Ctrl + n]
	文件名搜索文件 File [Ctrl + Shift + n]
	函数名搜索函数 Symbol [Ctrl + Shift + alt + n]
	搜素字符串 Find int Path（全局定义范围） [Ctrl + Shift + f]
	搜索使用位置 Find usages（代码，类，变量被使用情况） [Alt + F7]

列操作
	当前选择的单词上，若存在一样，重复选中 Select All Occurrences
	
移动
	单词移动到前面 Move Caret to Line End
	单词移动到后面 Move Caret to Line End
	定位到错误 F2

转换
	选中转为大写 Toggle Case

补全
	智能补全 Show Intention on Actions（快捷键：alt + enter）
		- 实现接口
		- 单词拼写提示
		- 导包
		- 自动创建函数（先使用，再生成）
		- list replace（重构时，可以直接把循环替换成 for-each）
		- 字符串 format 或 build

Live Templates
	代码自定义模版

pstfix Completion
	【无法编辑，默认定义好的，这是与 Live Templates的不同】
	生成模版参数（代码. -> 出现提示）


重构
	重构变量 Refactor-Rename (自动选中所有引用的地方，同时修改)
	重构方法 Refactor-ChangeSingnature (先修改使用地方，自动补全原函数)

抽取
	抽取局部变量 Refactor-Extract-Variable 
	抽取静态变量 
	抽取成员变量
	抽取方法参数
	抽取函数 Extract Method

git 集成
	显示代码版本信息 Annotate（也可在行号栏，右键点击）
	移动到项目所改动的地方 Previous Change
	撤销，包括单个和项目改动之处 Revert
	撤销文件修改 Revert Changes（光标移动到空行）

local history 本地版本控制
	idea自带的版本控制 Put Label （记录提交）


Debug 模式
	可用 Debug 模式运行服务器
	断点调试
	运行时设定测试值

关联
	与 Spring 关联 
		 -【设置】
		 		-> Project Structure 
		 		-> Facets 
		 		-> 新建一个 Facets，设置配置文件'+' 
		 		-> 添加 Spring
		 - 代码栏会出现 Spring 关联图标（可点击）

	数据库关联
		- 【设置】
				-> Database 设置连接


断点调试
	添加断点 Run - Toggle Line Breakpoint  
	单步运行 Run - Debug（F8 一行一行运行）
	跳到下个断点 Resume Program（若无错，则继续运行）
	查看所有断点 Breakpoints
	禁止所有断点 Mute Breakpoints
	条件断点 添加断点时，设置 Condition
	表达式求值 Evaluate Expression (展示断点值)
	运行指定行 Run to Cursor（运行到光标指定行）
	动态设置值 setValue(Debug 窗体直接，双击值设定)


文件操作 
	在当前文件同一级目录下面新建一个文件 New
	复制当前文件 Copy（Copy 到当前目录下）
	移动当前文件 Move（Move 到指定文件夹，剪贴）


文本操作
	复制文件名 （直接 Ctrl + C）
	复制文件完整路径 （直接 shift + ctrl + C）
	增强剪贴板功能
		- 多复制（将多个复制存在剪贴板里）
		- Choose Content to Paste（显示剪贴板，快捷 + 1）

结构图
	查看 file 大纲 File Structure

	查看 Maven 依赖，类图 Maven - Show Dependencies（当前文件右键窗体）
		- 出现 Maven 依赖图
		- ctrl + m 进行搜素，可双击跳转，线条亦可点击，可以邮件操作

	类图大纲 File Structure（显示类继承关系，方法调用层次）
		- 显示调用层次 Diagram for Son
		- 类层次 Hierarchy Class Son
		- 函数层次 Hierarchy Calers of main（Call Hierarchy 快捷键）

 	页面右键 Diagrams -> Show Diagrams
 		-> Java Class Diagrams
		   CDI Dependencies
		   Spring (需光标在类名的时候)
		   		-> 进入类图内，还可以右键各种功能显示相关依赖的关系
```					  


## ideaVim 插件

### 花式移动
```
Ctrl + [ 进入普通模式（从插入，覆盖模式内退出）
h,j,k,l 左，下，上，右移动
w,b 下个单词，上个单词
100j 下跳 100 行
20G 跳到第 20 行

0  调到行首
^ 跳到行首（首单词，首字母）
$ 跳到行尾
f<字母> 跳到下一个字母出现的位置

o,O  行下，行上，缩进插入，进入插入模式
i,a  词左，词右，插入，进入插入模式
I,A  行首，行尾，插入，进入插入模式
s,S  删除词，删除整行，插入，进入模式模式

r, 只替换单个字符，单次覆盖模式
R，一直向后替换，进入覆盖模式


% 跳到下一个对称级别括号

gg 跳文件头
G 跳到文件尾

H 光标移动到屏幕顶部
M 光标移动到屏幕中部
L 光标移动到屏幕低部

（ctrl + w） + k,j,h,l  上下左右，分屏跳转 
```

### 复制粘贴
```
v 进入选中模式，可以hjkl移动
10p 粘贴10次
y，p，d 复制，粘贴，剪贴（仅一次）
yy 复制一行
dd 剪贴一行
x 删除字符

ctrl + insert 将内容复制到系统剪贴板
```

### 变化
```
gu 变小写
gU 变大写

J 合并 2 行（当前行和下一行）
```

### 命令操作
```
shift + : 
	-> 输入数字，跳转到输入指定行数
	-> 输入 split， 垂直分屏
	-> 输入 vsplit， 水平分屏幕
	-> 输入 q，退出
	-> 输入 wq，保存并退出
	
shift + ? 
	-> 输入搜索内容，
    	-> n-下一个，N-上一个

```

### 列编辑
```
v 单词选中
V 行选中
Ctrl + v 进入选择模式（多光标）

r 替换单词

I (光标前)进入编辑模式，退出（ESC | Ctrl + [）
A (光标后)进入编辑模式，退出（ESC | Ctrk + [）

```

### 宏操作
```
q + 任意键   录制宏，并存到指定字母
@ + 任意键   执行指定字母的宏操作 
@ + @ 执行最新录制的宏 

数字 + @ + 任意键 重复指定次数，执行指定字母的宏操作
数字 + @ + @ 重复指定次数，执行最新录制的宏操作

. 重复命令
u 撤销操作

Ctrl + v 进入块选中模式，可移动光标（同时多列光标，列操作）

Shift + : 出现输入框
	-> 输入 register 显示当前寄存器
       （idea 的复制粘贴与宏操作共用同一个寄存器）
```

### 书签操作
```
m + <小写字母> 储存书签，到指定字母
` + <小写字母> 调到指定字母的书签

```


## emacslIDEAS  插件
