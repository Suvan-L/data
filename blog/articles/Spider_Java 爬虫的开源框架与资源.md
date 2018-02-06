---
title: Java 爬虫的开源框架与资源
date: 2016-11-25 14:25:43
tags: Spider
categories: Spider
---



目录
===========================
1. jsoup库
2. phantomjs[无界面浏览器]
3. Redis数据库
4. Selenium自动化测试工具
5. SQLite数据库
6. WebMagic框架



1.jsoup库
--------------------------
+ jsoup-1.8.1.jar扩展包
+ [官方中文文档](http://www.open-open.com/jsoup/)


案例1：
```
package Jsoup;

import java.io.File;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * 2016.11.25 
 *          学习使用jsoup
 *
 */
public class testJsoup {

    /*  方法1：解析HTML  
     *          jsoup优势： 
     *                  1.补齐没有关闭标签
     *                  2.将标签包装完整（比如没有<table>只有<td>）
     *                  3.创建可靠的文档结构(html head body)
     *                  4.避免跨站脚本攻击，利用基于 Whitelist 的清除器和 clean(String bodyHtml, Whitelist whitelist)方法来清除用户输入的恶意内容。
     *           Jsoup.parse(html),解析HTML字符串,将html转换为DOM节点
     *          Jsoup.parseBodyFragment(html),明确将用户输入作为 body片段处理,变为body
     * 
     */
    public void analyHTML() throws IOException{
        String html = "<html><head><title>First parse</title></head>"
                  + "<body><p>Parsed HTML into a doc.</p></body></html>";
        String html2 = "<div><p>Lorem ipsum.</p>";
        
        
        Document doc = Jsoup.parse(html); 
        Document doc2 = Jsoup.parseBodyFragment(html2); 
        Element body = doc2.body();

//      System.out.println(doc.html());
        
    }
    
    
    
    /*  方法2：
     *               Jsoup.connect("url").get();----GET请求访问url页面，将整个页面夹在完的HTML解析成Document
     *                                               .post();-----POST请求
     * 
     *              Document.body() 获取当前页面的body标签内所有内容【字符串】,= doc.getElementsByTag("body")
     *               
     *               html()    获取Element标签的所有html内容【字符串】【如果没有head,body会自动补齐结构】
     *               title()  获取页面title标签的文本内容
     *               text()   获取Element标签内的所有文本内容，去除标签【字符串】
     *                      
     */
    public void jsoupCallURL() throws IOException{
        
        Document doc = Jsoup.connect("http://www.yingshidaquan.cc/vod-show-id-8-p-1.html").get();
    
//  Connection接口提供一个方法链解决特殊请求
//  这个方法只支持Web URLs (http和https 协议); 假如你需要从一个文件加载，可以使用 parse(File in, String charsetName) 
//        Document doc = Jsoup.connect("http://example.com")
//                .data("query", "Java")
//                .userAgent("Mozilla")
//                .cookie("auth", "token")
//                .timeout(3000)
//                .post();
        
        String allHTML = doc.html();//获取整个页面的html内容
        String titleLable = doc.title();
        
        Element bodyLabel = doc.body();//body标签
        Element headLabel = doc.head();
        

    
        System.out.println(titleLable);
//      System.out.println(allHTML);
//      System.out.println(bodyLabel.html());
//      System.out.println(allHTML);
//      System.out.println(bodyContent);

    }
    
    
    
    /*方法3：读取硬盘中HTML文件
     *          parse(File in, String charsetName, String baseUri)  3个参数，文件路径，编码格式,
     *                                              【baseUri 参数用于解决文件中URLs是相对路径的问题。如果不需要可以传入一个空的字符串。】
     *          还有一个方法parse(File in, String charsetName) ，它使用文件的路径做为 baseUri。 这个方法适用于如果被解析文件位于网站的本地文件系统，且相关链接也指向该文件系统。
     */
    public void readLocalHTML() throws IOException{
        File input = new File("C:\\Users\\Liu-shuwei\\Desktop\\测试.html");
        Document doc = Jsoup.parse(input, "UTF-8", "http://example.com/");
        
        System.out.println(doc.html());
    }
    
    
    //方法4： 使用jsoup操作Document对象
    public void useDocument(Document doc){
        
        Element content = doc.getElementById("content");
        Elements a_eles = content.getElementsByTag("a");
        for(Element a:a_eles){
            String linkHref = a.attr("href");//获取a标签的href属性的文本内容
            String linkText = a.text(); //获取a标签的文本内容
        }
    }
    
    
    public static void main(String [] args){
        
        testJsoup c = new testJsoup();
        
        try{
//          c.analyHTML();  //解析HTML
//          c.jsoupCallURL();  //jsoup访问网页
            c.readLocalHTML();//读取本地HTML
        
            
        }catch(Exception e){}
    }
}
```

---

案例2：
```
package Jsoup;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


/**
 * 2016.11.25
 *              jousp小案例，获取某个URL页面所有链接
 */
public class getWebAllLink {

    //指定URL
    static String url ="http://www.bilibili.com/";
    
    
     public static void main(String[] args) throws IOException {
           
    
          Validate.isTrue(args.length == 0, "usage: supply url to fetch");

            //print方法的参数可以是任意基本类型或复杂类型
            //当执行print时，会自动调用该类的toString方法。
            print("Fetching .%s", url); //%s表示在当前位置输出字符串(后面的url)

            Document doc = Jsoup.connect(url).get(); //GET请求访问URL链接
            Elements links = doc.select("a[href]"); //查询doc对象带有href属性的a标签
            Elements media = doc.select("[src]");//查询doc对象带有src属性的标签
            Elements imports = doc.select("link[href]");//查询doc对象带有href属性的link标签

            //%d是一个占位符，标识一个字符串型的数据，%10d是数字的左侧留10个空格，对齐用 %s也是一个占位符，标识一个字符串型的数据
            print("\nMedia: (%d)", media.size());//换行输出,在%d位置输出 media.size()字符串
            
            //foreach循环
            for (Element src : media) {
                if (src.tagName().equals("img"))
                    print(" * %s: <%s> %sx%s (%s)", //* 标签名,标签src属性，width属性，height属性，alt属性
                            src.tagName(), src.attr("abs:src"), src.attr("width"), src.attr("height"),trim(src.attr("alt"), 20));
                
                else
                    print(" * %s: <%s>", src.tagName(), src.attr("abs:src"));
            }

            print("\nImports: (%d)", imports.size());//link标签和长度
            for (Element link : imports) {                 //*，标签名，href属性，rel属性
                print(" * %s <%s> (%s)", link.tagName(),link.attr("abs:href"), link.attr("rel"));
            }

            print("\nLinks: (%d)", links.size());
            for (Element link : links) {
                print(" * a: <%s>  (%s)", link.attr("abs:href"), trim(link.text(), 35));
            }
        }

     //格式化输出语句
        private static void print(String msg, Object... args) {
            System.out.println(String.format(msg, args));
        }

        //去尾判断
        private static String trim(String s, int width) {
            if (s.length() > width)    //若s字符长度>width长度，则去除s字符串最后一个字符
                return s.substring(0, width-1) + ".";
            else
                return s;
        }
}

```



## 2. phantomjs[无界面浏览器]
&emsp;PhantomJS是一个基于webkit的javascript API。它使用QtWebKit作为它核心浏览器的功能，使用webkit来编译解释执行JavaScript代码。任何你可以在基于webkit浏览器做的事情，它都能做到。它不仅是个隐形的浏览器，提供了诸如CSS选择器、支持Web标准、DOM操作、JSON、html5、Canvas、SVG等，同时也提供了处理文件I/O的操作，从而使你可以向操作系统读写文件等。PhantomJS的用处可谓非常广泛，诸如网络监测、网页截屏、无需浏览器的 Web 测试、页面访问自动化等。


+ [官网](http://phantomjs.org/)
+ [下载地址](http://phantomjs.org/download.html)

基础使用教程
```
1. 解压压缩包
在bin目录里找到phantomjs.exe【这个就是浏览器程序】

****************************************************************

2.创建js脚本
    //codes.js     
    system = require('system')     
    address = system.args[1];//获得命令行第二个参数 接下来会用到     
    //console.log('Loading a web page');     
    var page = require('webpage').create();     
    var url = address;     
    //console.log(url);     
    page.open(url, function (status) {     
        //Page is loaded!     
        if (status !== 'success') {     
            console.log('Unable to post!');     
        } else {     
            //console.log(page.content);     
            //var title = page.evaluate(function() {     
            //  return document.title;//示范下如何使用页面的jsapi去操作页面的  www.oicqzone.com   
            //  });     
            //console.log(title);     
                 
            console.log(page.content);     
        }        
        phantom.exit();     
    });  

****************************************************************

2.java代码
package phantomjs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class test {
    //获取url,使用phantomjs浏览器访问网页
    public static void getAjaxCotnent(String url) throws IOException { 
        
        Runtime rt = Runtime.getRuntime();     
        //phantomjs.exe路径 【空格】codes.js路径 【空格】url
        Process p = rt.exec("phantomjs/phantomjs.exe phantomjs/codes.js "+url);
       
        //获取InputSream，进行读取，返回String
        String webContent =readInputStream(p.getInputStream());
        System.out.println(webContent);
        
    }     
    
    //读取InputStream
    public static String readInputStream(InputStream is) throws IOException{
          BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));     
            StringBuffer sbf = new StringBuffer();     
            String tmp = "";     
            while((tmp = br.readLine())!=null){     
                sbf.append(tmp+"\n");     
            }   
            return sbf.toString();  
    }
    
    
     
    public static void main(String[] args) throws IOException {     
        try{
            getAjaxCotnent("http://www.yingshidaquan.cc/html/DQ227428.html");    
        }catch(Exception e){
            e.printStackTrace();
        }  
    }     
    
}
```

---

## 3. Redis数据库
&emsp;Redis是一个开源的使用ANSI C语言编写、支持网络、可基于内存亦可持久化的日志型、Key-Value数据库，并提供多种语言的API。从2010年3月15日起，Redis的开发工作由VMware主持。从2013年5月开始，Redis的开发由Pivotal赞助。
<br>

+ 高性能的key-value数据库，存储系统 
+ [百度百科](http://baike.baidu.com/link?url=QoeUBhbxbkfahlSN9uHrcM47dGjKVcrZkFNkhJqg--_gHPjt05mU_gcdNgepN3cHw4TGRnsbcfvAJVK6PKWuyK)
+ [官网](https://redis.io/)
+ [MSOpenTech/redis](https://github.com/MSOpenTech/redis/releases)和[redis-windows](https://github.com/ServiceStack/redis-windows)
+ [百度教程](http://jingyan.baidu.com/article/f25ef2546119fd482c1b8214.html)
+ [Redis快速入门](http://www.yiibai.com/redis/redis_quick_guide.html)
+ [ redis入门——redis常用命令 ](http://blog.csdn.net/wclxyn/article/details/8449082)
  ​

安装
1. GitHub下载了Redis-x64-3.2.100.zip，解压到硬盘某路径
2. CTRL+R ~~~~>cmd,进入DOS操作系统---->cd 解压后目录
3. 输入redis-server.exe  redis.windows.conf启动，出现一个奇葩的图标容纳后port 6379就表示启动成功
4. redis服务的DOS窗口不用关闭，因为服务要一直执行【直接关闭窗口就是关闭服务】
  ​

开启服务,简单运用
```
Ctrl + R  ------>cmd

    cd e:java/redis/3.2.100
    e:
    redis-server.exe redis.windows.conf   【开启服务】

Ctrl + R  ------>cmd
    cd e:java/redis/3.2.100
    e:
    redis-cli.exe                         【启动命令界面】

    set age 21  【存入key-value】
    get age     【通过key获取value】

```



## 4. Selenium自动化测试工具

&emsp;Selenium可以模拟用户操作，自动化测试，兼容Java,Phython,C#,也兼容各种浏览器Google Chrome,firefox,IE以及phantomjs【无UI浏览器】
<br>


资料
+ [基础介绍和教程](http://www.yiibai.com/selenium/)
+ [webDriver中文教程](http://www.webdriver.org/nav1/)
+ [selenium IDE 定时执行case及log保存的方法](http://blog.csdn.net/songjiaping/article/details/49587273)
+ [Selenium IDE或者jar官网资源下载页面](http://seleniumhq.org/download/)
+ [ChromeDriver - WebDriver for Chrome官方下载](https://sites.google.com/a/chromium.org/chromedriver/downloads)
+ [webdrvier各种开发语言实用教程](https://github.com/easonhan007/webdriver_guide/blob/master/README.md)
  <br>


环境
+ IDE:Intellij IDEA/Eclipse
+ jdk:1.8
+ chrome.exe/firefox.exe【谷歌/火狐浏览器】
+ chromedriver/geckodriver【适配浏览器的驱动,需要适应浏览器版本】
  <br>


用法
```

<1>启动
        System.setProperty("webdriver.firefox.bin","chrome快捷方式.lnk");//Chrome浏览器程序【可以是快捷方式】
        System.setProperty(  "webdriver.chrome.driver",  "Chrome\\chromedriver.exe"); //Chrome驱动包
        WebDriver driver = new ChromeDriver();

<2>访问
        driver.get("http://www.baidu.com");

<3>操作浏览器
        driver.manage().window().maximize();//窗口最大化
        driver.manage().window().setSize(new Dimension(320, 240)); //设置大小

        String pageTitle = driver.getTitle();//获取页面标题
        String pageURL = driver.getCurrentUrl();//获取当前URL

        driver.navigate().back();//后退
        driver.navigate().forward();//前进

        //driver.close();//关闭浏览器窗口
        driver.quit(); //关闭窗口,且释放连接资源



<4>定位浏览器对象
		driver.findElement(By.id("inputEamil")).click();//id查找
		driver.findElement(By.name("passward"));//name查找
		String classAttr = driver.findElement(By.tagName("form")).getAttribute("class");//标签查找,且获取class属性值
		WebElement link = driver.findElement(By.linkText("register"));//全文本查找
		WebElement plink = driver.findElement(By.partialLinkText("reg"));//关键字文本查找
		WebElement csslink = driver.findElement(By.cssSelector(".controls"));//CSS类选择器查找
		driver.findElement(By.xpah("/html/body/form/div[3]/div/label/input")).click(); //Xpath定位查找

		//将多选框组对象,全部打勾
		List<WebElement> checkboxes = dr.findElements(By.cssSelector("input[type=checkbox]")); //获取组对象
		for(WebElement checkbox : checkboxes) {
				checkbox.click();
			}


		//定位组对象,点击其中second按钮
		List<WebElement> btns = dr.findElement(By.className("btn-group")).findElements(By.className("btn")); 
			for(WebElement btn : btns){
				if(btn.getText().equals("second")){
					btn.click();
					break;
				}
			}


		//将浏览器从当前定位的主体,切换到指定frame中
		switchTo().frame("id");


<4-2>获取对象内容
	WebElement link = driver.findElement(By.id("tooltip"));
	String classAttr = link.getAttribute("class");//获取属性值
	String text = link.getText();  //获取链接的Text
	String cssValue = link.getCssValue("color"); //获取CSS属性值

<4-3>判断对象状态
	WebElement textField = dr.findElement(By.name("user"));
		System.out.println(textField.isDisplayed());//是否显示
		System.out.println(textField.isEnabled());//是否存在
		System.out.println(textField.isSelected());//是否被选中


<5>鼠标模拟
		
	WebElement menu = driver.findElement(By.id("dropdown1")).findElement(By.linkText("Another action"));
	(new Actions(driver)).moveToElement(menu).perform(); //鼠标移动到对象上


<6>键盘模拟
	driver.findElement(By.linkText("Link1")).click(); //点击
	WebElement element = driver.findElement(By.name("q")); 
	element.sendKeys("something");		//键盘输入
	element.clear();					//清空 

	dr.findElement(By.id("A")).sendKeys(Keys.chord(Keys.CONTROL + "a"));//组合键Ctrl + A
	dr.findElement(By.id("A")).sendKeys(Keys.chord("watir webdriver is better than selenium webdriver"));  //输入字母


<7>对话框处理

	//打开对话框
	drivker.findElement(By.id("show_modal")).click();
			
			(new WebDriverWait(dr, 10)).until(
					new ExpectedCondition<Boolean>() {
						public Boolean apply(WebDriver d) {
							return d.findElement(By.id("myModal")).isDisplayed();
						}
					}
			);
	//定位按钮,js模拟click【对话框的元素被蒙板所遮挡,直接点击会报错Element is not clickable】
	WebElement link = dr.findElement(By.id("myModal")).findElement(By.id("click"));
	((JavascriptExecutor)dr).executeScript("$(arguments[0]).click()", link);


	//获取弹出的对话框提示信息
	Alert alert = dr.switchTo().alert();
			System.out.println(alert.getText());
			alert.accept();


<8>执行js操作

		//页面上直接执行
		((JavascriptExecutor)dr).executeScript("$('#tooltip').fadeOut();");//执行jQuery函数【页面若没包含jquery,则fadeIn()等方法无效】
		((JavascriptExecutor)dr).executeScript("$(arguments[0]).hide()", textField);//js模拟隐藏元素
			
		//已经定位上的元素执行js
		WebElement button = dr.findElement(By.className("btn"));
		((JavascriptExecutor)dr).executeScript("$(arguments[0]).fadeOut();", button);


<9>wait等待操作【常用语等待js动作 or ajax后再执行元素】

    //使用的两个构造方法
    public WebDriverWait(WebDriver driver, long timeOutInSeconds)
    public WebDriverWait(WebDriver driver, long timeOutInSeconds, long sleepInMillis)  //driver,总体的超时时间,每隔多久去检查一次until结果


		//找到菜单项,等下拉菜单出现后,再定位其中元素,再点击
		(new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>(){
				public Boolean apply(WebDriver d){
					return d.findElement(By.id("dropdown1")).isDisplayed();
				}
			} );//显示一个下拉菜单


<10>action类【模拟真实的用户操作】

    keyDown。模拟按键按下
    keyUp。模拟按键弹起
    click
    sendKeys
    doubleClick。鼠标左键双击
    clickAndHold。鼠标左键点击住不放
    release。鼠标左键弹起，可以与click_and_hold配合使用
    moveToElement。把鼠标移动到元素的中心点
    contextClick。鼠标右键点击
    dragAndDrop。拖拽

    	Actions action = new Actions(driver)
			action.keyDown(Keys.SHIFT).
              click(element).
              click(second_element).
              keyUp(Keys.SHIFT).
              dragAndDrop(element, third_element).
			  build().
              perform();//可一连串操作



<11>上传与下载文件
	//【上传】找到input对象，直接sendKeys输入上传文件的正确路径
	driver.findElement(By.cssSelector("input[type=file]")).sendKeys("src/navs.html");

	//【下载】
		自行Google[webdrivker可以允许我们设置文件下载路径]


<12>超时限制
	mplicitlyWait(识别对象时的超时时间。过了这个时间如果对象还没找到的话就会抛出NoSuchElement异常)
	setScriptTimeout(异步脚本的超时时间。webdriver可以异步执行脚本，这个是设置异步执行脚本脚本返回结果的超时时间)
	pageLoadTimeout(页面加载时的超时时间。因为webdriver会等页面加载完毕在进行后面的操作，所以如果页面在这个超时时间内没有加载完成，那么webdriver就会抛出异常)

	  driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
  	  driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
      DriverManager.manage().timeouts().setScriptTimeout(3, TimeUnit.SECONDS);

<13>Cookie与自动登录

    addCookie(Cookie cookie)。添加cookie，参数是Cookie对象
    deleteAllCookies。删除所有cookie
    getCookies。返回所有的cookie
    deleteCookieNamed(String name)。删除name这个cookie
    getCookieNamed(String name)。返回特定name的cookie值

    driver.manager().deleteAllCookies();//删除所有Cookie

     Cookie c1 = new Cookie("BAIDUID", "xxxxxxxxxx");
     Cookie c2 = new Cookie("BDUSS", "xxxxxxxxxx");
        dr.manage().addCookie(c1); //添加Cookie
        dr.manage().addCookie(c2);


```


## 5.SQLite数据库
&emsp;SQLite，是一款轻型的数据库，是遵守ACID的关系型数据库管理系统，它包含在一个相对小的C库中。它是D.RichardHipp建立的公有领域项目。它的设计目标是嵌入式的，而且目前已经在很多嵌入式产品中使用了它，它占用资源非常的低，在嵌入式设备中，可能只需要几百K的内存就够了。它能够支持Windows/Linux/Unix等等主流的操作系统，同时能够跟很多程序语言相结合，比如 Tcl、C#、PHP、Java等，还有ODBC接口，同样比起Mysql、PostgreSQL这两款开源的世界著名数据库管理系统来讲，它的处理速度比他们都快。
<br>

资源
+ [百度百科](http://baike.baidu.com/link?url=qXVaxmtFRZdAbluvhvRYByjYaiuYO2uIscKOUqHogpmie45PkZVphB831TmqV4bL2QpUuDXjm8D08_VMAEfK0q)
+ [菜鸟教程](http://www.runoob.com/sqlite/sqlite-tutorial.html)
+ [官网下载页面](http://www.sqlite.org/download.html)
+ [下载sqllite-jdbc.jar包](https://bitbucket.org/xerial/sqlite-jdbc/downloads)
  <br>


基础使用
```
1.进入数据库
e:
cd java
cd sqlite
sqlite3 test.db【不存在则创建,且进入数据库】


2.查看数据库所有表
SELECT name FROM sqlite_master WHERE type='table' order by name


查询表结构
select * from sqlite_master WHERE type = "table";



3.避免重复创建表
    【a.找到已经存在的所有表，手动判断是否需要建表】
        SELECT name FROM SQLITE_MASTER WHERE type='table'ORDER BY name
                                    //找到已经存在的所有表，手动判断是否需要建表
    【b.建表时sqlite自动判断
        create table if not exists article(id integer PRIMARY KEY autoincrement,type int)
```
<br>

封装基本操作的类
```
package Utils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;


/** 更新时间： 2016.12.08
 *   
 *  SQLite数据库封装类
 *          单例模式
 *          方法1：连接
 *          方法2：关闭
 *          方法3：创建，查找，插入，更新，删除
 *          @author suvan
 *
 */
public class UseDB_SQLite {
    
    //静态成员变量，支持单例模式
    private static  volatile UseDB_SQLite udb_s = null;
    
    private String drive="org.sqlite.JDBC";                     //数据库驱动
    private String link="jdbc:sqlite:";     //jdbc:sqlite:+数据库文件
    
    private Connection conn=null;
    private Statement st=null;
    private PreparedStatement pst=null;
    private ResultSet rs=null;
    private ResultSetMetaData rsmd=null;//获取列的信息
    
    
    //A-1.懒汉式(加双重校验锁):【单例设计模式，获取实例,节约系统资源，提高性能】 ,项目中使用UseDB udb =UseDB.getInstance("javareptile");获取实例
            public static synchronized UseDB_SQLite getInstance() throws IOException,ClassNotFoundException,SQLException{
                if(udb_s == null){
                        synchronized(UseDB.class){
                            if(udb_s ==null){  //2   双重校验锁，实现延迟加载，解决线程并发，使用volatile指令静止重拍序列化
                                udb_s = new UseDB_SQLite();
                                udb_s.connDatabase("javareptile");
                            }
                        }
                }
                return udb_s;
            }
    
        //方法1: 连接database数据库
        public void connDatabase(String database)  throws SQLException,ClassNotFoundException{ //参数：数据库名称
             Class.forName(drive);
             conn=DriverManager.getConnection(link+database);  //直接创建  
             st=conn.createStatement();
//           System.out.println("数据库连接成功......");
        }

        //方法2关闭所有对象
        public void closeAll() throws SQLException{
            if(rs!=null)  rs.close();//关闭ResultSet
            if(pst!=null)   pst.close();    //关闭PreparedStatement
            if(st!=null) st.close();//关闭Statement
            if(conn!=null)  conn.close(); //关闭Connection
        }
    
        
        //方法3-1：【执行创建语句】
        public void create(String sql) throws SQLException{
            st.executeUpdate(sql);  
        }
        
        //方法3-2：【执行插入语句】
        public void insert(String sql) throws SQLException{
            st.executeUpdate(sql);
        }
        
        //方法3-3：【执行查找语句】
        public void select(String sql) throws SQLException{
            rs = st.executeQuery(sql);
            rsmd=rs.getMetaData();
            while(rs.next()){
                for(int i=1;i<=rsmd.getColumnCount();i++){
                    System.out.print(rsmd.getColumnName(i)+":"+rs.getString(i)+"\t");
                }
                System.out.println();//换行
            }
        }
        
        //方法3-4:【执行更新操作】
        public void update(String sql) throws SQLException{
            conn.setAutoCommit(false);//禁止自动提交
            st.executeUpdate(sql);
            conn.commit();
        }
        
        //方法3-5:【执行删除操作】
        public void delete(String sql) throws SQLException{
            conn.setAutoCommit(false);//禁止自动提交
            st.executeUpdate(sql);
            conn.commit();
        }
}


******************************************
测试使用,test.java


//主方法
public static void main(String [] args){
    try{

        //创建SQlite数据库实例
            UseDB_SQLite udb_s= new UseDB_SQLite();
            udb_s.connDatabase("testDB.db"); //连接数据库【不存在则创建】
            String sql=null;
            //1.创建表
             sql = "CREATE TABLE COMPANY " +
                       "(ID INT PRIMARY KEY     NOT NULL," +
                       " NAME           TEXT    NOT NULL, " + 
                       " AGE            INT     NOT NULL, " + 
                       " ADDRESS        CHAR(50), " + 
                       " SALARY         REAL)"; 
             udb_s.create(sql);
             //2.插入
             sql = "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) " +
                "VALUES (2, 'Paul', 32, 'California', 20000.00 );"; 
             udb_s.insert(sql);
             //3.查询
             udb_s.select("SELECT * FROM COMPANY;");
             //4.更新
            sql="UPDATE COMPANY set SALARY = 1111.00 where ID=1;";
            udb_s.update(sql);
            //5.删除
            sql = "DELETE from COMPANY where ID=2;";
            udb_s.delete(sql);

    }catch(Exception e){
        e.printStackTrace();
    }
}
```



## 6. WebMagic框架
资源
+ [中文教程](http://webmagic.io/docs/zh/)
+ [资源下载](http://webmagic.io/)
+ [AngularJS中文社区](http://angularjs.cn/)
+ [使用Selenium来抓取动态加载的页面](https://my.oschina.net/flashsword/blog/147334)
+ [知乎爬虫](http://www.cnblogs.com/LZYY/p/5146844.html)
  <br>

学习关键词
+ Maven
+ Jsoup【HTML解析器】,Xsoup【XPath解析器-webMagic作者开发】,Saxon【XPath解析器】,HtmlCleaner【XPath解析器】
+ 正则表达式,js渲染
+ Chrome的开发者工具【F12】,查看页面源码【CTRL+u】
+ jsonviewer【Chrome浏览器插件,查看AJAX结果】
  ​


简单使用案例
```
package PageProcessor;


import java.util.Map;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

    public class GithubRepoPageProcessor implements PageProcessor {
        
        //部分1：抓取网站的相关配置【编码，抓取间隔，重试次数】
        private Site site = Site.me().setRetryTimes(3).setSleepTime(100);

        //process是定制爬虫逻辑的核心接口，在这里编写抽取逻辑
        public void process(Page page) {
            //部分2： 定义如何抽取页面信息，并保存下来
            
                //A.获取页面所有能够匹配正则表达式的链接,all()获取所有抽取结果，存在List<String>
                   //page.addTargetRequests()则将这些链接加入到待抓取的队列中去
                page.addTargetRequests(page.getHtml().links().regex("(https://github\\.com/\\w+/\\w+)").all());
                
                //B.抓取匹配数据
                page.putField("author", page.getUrl().regex("https://\\\\.com/(\\w+)/.*").toString());
                page.putField("name", page.getHtml().xpath("//h1[@class='entry-title public']/strong/a/text()").toString());
                page.putField("ONE",page.getHtml());
                //C.进行判断JSON格式name参数
                if (page.getResultItems().get("name")==null){ //resultItems抓取结果
                    //skip this page
                    page.setSkip(true);//判断是否要跳过这条结果【当前page】，跳过了就不会被pipeline处理
                }
            
                
            //部分3： 从页面发现后续的url地址来抓取
            page.putField("readme", page.getHtml().xpath("//div[@id='readme']/tidyText()"));
            
            
          //遍历所有结果，输出到控制台，上面例子中的"author"、"name"、"readme"都是一个key，其结果则是对应的value
            for (Map.Entry<String, Object> entry : page.getResultItems().getAll().entrySet()) {
                System.out.println(entry.getKey() + ":\t" + entry.getValue());
            }
            
        }

        @Override
        public Site getSite() {
            return site;
        }

        public static void main(String[] args) {
            Spider.create(new GithubRepoPageProcessor()).
                    addUrl("http://webmagic.io/docs/zh/posts/ch4-basic-page-processor/results.html").  //从那个URL开始抓取【入口】
                    thread(5).                                  //开启n个线程抓取
                    run();                                      //启动爬虫
            

        }
    }
```