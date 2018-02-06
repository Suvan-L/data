---
title: WebMagic
date: 2016-12-05 10:33:26
tags: MyProject
categories: MyProject
---

前言
==============================

&emsp;学习使用WebMagic框架
2016-12-2：刚接触，挺好的用的模块区分好，可个性化定制，不过还是有点不熟悉，时间慢慢沉淀，加油加油


参考资料
+ [Java爬虫框架WebMagic的使用总结](http://blog.csdn.net/jibaole/article/details/52212886)
+ [中文官方文档](http://webmagic.io/docs/zh/)
+ 学习Q群：373225642【webmagic探讨】
+ [中文教程](http://webmagic.io/docs/zh/)
+ [资源下载](http://webmagic.io/)
+ [AngularJS中文社区](http://angularjs.cn/)
+ [使用Selenium来抓取动态加载的页面](https://my.oschina.net/flashsword/blog/147334)
+ [知乎爬虫](http://www.cnblogs.com/LZYY/p/5146844.html)


目录：
1. 环境 
2. 项目结构
3. 代码
  1. 爬取CSDN的博客【1.0版本】
  2. 爬取CSDN的博客【2.0版】


## 1. 环境

+ jdk版本：1.8.0_06
+ IDE:eclipse,Navicat for MySQL
+ 数据库：MySQL57
+ 框架技术：WebMagc
+ 第三方扩展jar包:[WebMagic下载页面](http://webmagic.io/)，里的所有依赖jar包



## 2. 项目结构

Webmagic
&emsp;src
&emsp;&emsp;bean
&emsp;&emsp;&emsp;......
&emsp;&emsp;Downloader
&emsp;&emsp;PageProcessor
&emsp;&emsp;&emsp;......
&emsp;&emsp;Pipeline
&emsp;&emsp;&emsp;......
&emsp;&emsp;Scheduler
&emsp;&emsp;Utils
&emsp;&emsp;&emsp;IOFile.java
&emsp;&emsp;&emsp;test.java
&emsp;&emsp;&emsp;UseDB.java
&emsp;&emsp;&emsp;UseString
&emsp;JRE System Library[Java SE-1.8]
&emsp;webmagic-lib【Build Path之后的类库】
&emsp;webmagic-lib【目录，存放下载的Jar包】


## 3. 代码：
### 1. 爬取CSDN的博客【1.0版本】

&emsp;耗时1天半左右，边实践边学习，活性动态爬虫
&emsp;官网入口，爬取匹配正则的链接，然后爬取个人博客列表【所有页】,爬取个人资料页【好友....】


思路
1. 分析http://blog.csdn.net/和http://blog.csdn.net/lfdfhl和http://my.csdn.net/lfdfhl
2. 编写PageProcessor，处理抓取数据也业务逻辑
3. 编写Pipeline,将获得的数据经过处理，存入数据库




遇到错误与困惑：
1. 使用PreparedStatement进入存入数据的时候，数据库内容全为?【utf8格式】
2. 如何判断已经爬取过的URL，避免二次爬取？
3. List去重,框架的爬取队列是否有自动去重【可能是我的错觉，我都是手动list去重的】
4. 灵活运用page.setSkip(true);.使页面进行过滤，不执行Pipeline
5. 数据库对象如何重复利用，而不是每次都创建新的？
6. 效率问题，自己还是不善于做数据分析


<br><br>

效果：
1. javareptile数据库,10006条记录【user_csdn】[13312KB],773347条记录【blog_csdn】[270336 KB]
2. 耗时下午14.14开始，大约到19.00【吃晚饭回来就爬完了,有些人没有关注人，所以只爬到1W多个用户】


<br><br>

A.设计数据库表
```

1.用户个人资料表
    自增id【主键】
    博主【唯一】
    个性签名
    博文属性【原创,转载...】
    博文类型【Android,java....】
    访问次数【人气】
    CSDN官博排名

CREATE TABLE user_csdn(             
    id INT auto_increment primary key,
    blogger VARCHAR(50) NOT NULL unique,
    signature VARCHAR(150),
    articlenature VARCHAR(50),
    articletype VARCHAR(2000),
    visitcount VARCHAR(15),
    ranking VARCHAR(10)
)ENGINE = innoDB;

***********************

2.用户博文信息
    编号自增【主键】
    标题
    URL【唯一】
    阅读数
    发表时间
    博主
CREATE TABLE blog_csdn(             
    num INT auto_increment primary key,
    title VARCHAR(100) NOT NULL,
    URL VARCHAR(150) NOT NULL unique,
    readCount VARCHAR(20) ,
    time VARCHAR(20) NOT NULL,
    blogger VARCHAR(50) NOT NULL
)ENGINE = innoDB;
```

---


B.bean
```
package bean;

import java.util.List;

public class CSDNBean {
    private String blogger;          //博主
    private String signature;            //个性签名
    private String ArticleNature;    //文章属性
    private String ArticleType;          //文章分类
    private String visitCount;           //访问次数
    private String ranking;             //排名
    
    private List<Object> blog;      //博客信息
    

    public String getBlogger() {
        return blogger;
    }
    public String getSignature() {
        return signature;
    }
    public String getVisitCount() {
        return visitCount;
    }
    public String getRanking() {
        return ranking;
    }
    public String getArticleNature() {
        return ArticleNature;
    }
    public String getArticleType() {
        return ArticleType;
    }
    public List<Object> getBlog() {
        return blog;
    }
    public void setBlogger(String blogger) {
        this.blogger = blogger;
    }
    public void setSignature(String signature) {
        this.signature = signature;
    }
    public void setVisitCount(String visitCount) {
        this.visitCount = visitCount;
    }
    public void setRanking(String ranking) {
        this.ranking = ranking;
    }
    public void setArticleNature(String articleNature) {
        ArticleNature = articleNature;
    }
    public void setArticleType(String articleType) {
        ArticleType = articleType;
    }
    public void setBlog(List<Object> blog) {
        this.blog = blog;
    }
    
    
}

```

---


C.PageProcessor
```
package PageProcessor;

import java.util.ArrayList;
import java.util.List;

import Pipeline.CSDN_Pipeline;
import bean.CSDNBean;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.scheduler.QueueScheduler;
import us.codecraft.webmagic.scheduler.component.BloomFilterDuplicateRemover;

public class CSDN_Processor implements PageProcessor{

    //个人博文列表页
    private static final String blog_LIST="http://blog.csdn.net/\\w+/"; 
    //个人资料页面
    private static final String blog_SPACELIST="http://my.csdn.net/\\w+";
    
    private int count=0;//统计爬虫爬取次数
    private long beginTime=System.currentTimeMillis();//统计开始爬取的时间
    
    
    private Site site = Site.me().setCharset("utf-8").setRetryTimes(1).setSleepTime(1);

    public Site getSite() {
        return site;
    }


    public void process(Page page){
        System.out.println("************************第"+(++count)+"次*************已花费"+String.valueOf(((double)System.currentTimeMillis()-beginTime)/1000)+"秒*************************");
        System.out.println("***********************正在爬取"+page.getUrl().get()+"......");
        //第一步:爬取入口页面,能够匹配正则的链接【个人博文页面】
        List<String> blog_list=page.getHtml().links().regex(blog_LIST).all();
        if(blog_list.size()>0){
            for(int i=0;i<blog_list.size();i++){    //List集合去重操作
                for(int j=0;j<blog_list.size();j++){
                    if(blog_list.get(i).equals(blog_list.get(j))){
                        blog_list.remove(j);
                    }
                }
            }
        }
        
        page.addTargetRequests(blog_list);
        List<String> blog_spacelist=page.getHtml().links().regex(blog_SPACELIST).all();
        if(blog_spacelist.size()!=0){               //判断是否为空
            for(int i=0;i<blog_spacelist.size();i++){   //List集合去重操作
                for(int j=0;j<blog_spacelist.size();j++){
                    if(blog_spacelist.get(i).equals(blog_spacelist.get(j))){
                        blog_spacelist.remove(j);
                    }
                }
            }
                page.addTargetRequests(blog_spacelist);
        }

        
        
        //第二步：获取当前用户所有页面的博文标题和URL
            if(page.getUrl().regex(blog_LIST).match()){                                 //1.判定URL是否匹配正则
                System.out.println("*************个人博文列表页面***************");
                    //A-1.在第一页中该用户所有页面的URL,添加入待爬去的队列
                    if(!page.getUrl().regex("(.*)/list/(.*)").match()){                             //若URL中不存在/list/，则执行下列操作
                        String pageS =page.getHtml().xpath("//div[@id='papelist']/span[1]/allText()").get(); //获取总页数
                        int pageCount =Integer.parseInt(pageS.substring(pageS.indexOf("共")+1,pageS.lastIndexOf("页")));
                        
                        for(int p=2;p<=pageCount;p++){      //生成URL【跳进来的时候就是第1页了，所以从第2页开始】
                            page.addTargetRequest(page.getUrl()+"article/list/"+p);//将URL添加到爬取队列
                        }   
                    }
                    
                    //A-2.将用户列表添加进个人页面
                    page.addTargetRequest(page.getUrl().get().replace("blog.csdn", "my.csdn"));

                    
                    //B.爬取个人资料，保存到Bean对象
                    CSDNBean bean = new CSDNBean();
                        bean.setBlogger(page.getHtml().xpath("//div[@id='blog_title']/h2/a/allText()").get());                      //【博主】
                        bean.setSignature(page.getHtml().xpath("//div[@id='blog_title']/h3/allText()").get());                      //【个性签名】
                        bean.setArticleNature(page.getHtml().xpath("//ul[@id='blog_statistics']/allText()").get());                 //【文章属性】
                        bean.setArticleType(page.getHtml().xpath("//div[@id='panel_Category']/ul[2]/allText()").get());     //【文章类型】
                        bean.setVisitCount(page.getHtml().xpath("//ul[@id='blog_rank']/li[1]/span[1]/allText()").get());        //【访问次数】
                        bean.setRanking(page.getHtml().xpath("//ul[@id='blog_rank']/li[4]/span[1]/allText()").get());           //【排名】
        
        
                    //C.爬取用户博文信息【n * (标题,URL,阅读次数,发表时间)】，按顺序添加到List
                    List<Object> allList = new ArrayList<Object>();
                        allList.add(page.getHtml().xpath("//div[@class='article_title']/h1/allText()").all());                      //title_list                【文章标题】
                        allList.add(page.getHtml().xpath("//h1/span/a/@href").all());                                                       //URL_list              【内容URL】
                        allList.add(page.getHtml().xpath("//div[@class='article_manage']/span[2]/allText()").all());        //readCount_list    【阅读次数】
                        allList.add(page.getHtml().xpath("//div[@class='article_manage']/span[1]/allText()").all());        //time_list             【发表时间】
                        bean.setBlog(allList);
                    
                    //C.传递数据，储存进数据库
                        page.putField("CSDNBean", bean);    
                        
                }else if(page.getUrl().regex(blog_SPACELIST).match()){              
                    System.out.println("*************进入到个人资料页面***************");//处理个人资料页面【主要获取其他用户的URL】
                    page.setSkip(true);
                    List<String> otherUser_list =page.getHtml().links().regex(blog_SPACELIST).all();
                    for(int i=0;i<otherUser_list.size();i++){   //List集合去重操作
                        for(int j=0;j<otherUser_list.size();j++){
                            if(otherUser_list.get(i).equals(otherUser_list.get(j))){
                                otherUser_list.remove(j);
                            }
                        }
                    }
                    for(int i=0;i<otherUser_list.size();i++){
                        System.out.println("成功爬取到用户----->"+(otherUser_list.get(i).replace("my.csdn","blog.csdn")));
                        page.addTargetRequest(otherUser_list.get(i).replace("my.csdn","blog.csdn"));//将URL添加到爬取队列
                    }
                }else
                    {page.setSkip(true);} //设置skip之后，这个页面的结果不会被Pipeline处理

    }
    
    public static void main(String [] args){
        try{
        Spider.create(new CSDN_Processor())
            .addUrl("http://blog.csdn.net/")                                        //CSDN官网入口
            .scheduler(new QueueScheduler().setDuplicateRemover(new BloomFilterDuplicateRemover(10000000))) //QueueScheduler【使用内存队列保存待抓取URL】，使用BloomFilterDuplicateRemove去重
            .addPipeline(new CSDN_Pipeline())                                   //添加Pipeline,对数据进行处理每个Page如果skip为true，都会执行CSDN_Pipeline的process()
            .thread(10)                                                                     //开启10个线程进爬取
            .run();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}

```

---


D.Pipeline
```
package Pipeline;

import java.util.List;
import java.util.Map;

import Utils.UseDB;
import bean.CSDNBean;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.ConsolePipeline;

public class CSDN_Pipeline extends ConsolePipeline  {

    
    
     // ResultItems保存了抽取结果，它是一个Map结构，
    // 在page.putField(key,value)中保存的数据，可以通过ResultItems.get(key)获取
    public void process(ResultItems resultItems, Task arg1) {
        
        UseDB udb = new UseDB();
        
        try{
            udb.connDatabase("javareptile");
        
            //1.获取对象
            CSDNBean bean =(CSDNBean)resultItems.get("CSDNBean");
            if(bean!=null){
                
                //1.判断是否存在相同用户，若不存在则插入用户个人信息
                String ifExit=udb.selectIfExist_CheckRecord("user_csdn", "blogger","blogger='"+bean.getBlogger()+"'");
                if(ifExit==null){
                    System.out.println("博主\t\t\t\t\t\t"+"个性签名\t\t\t\t\t\t"+"博文属性\t\t\t\t\t\t"+"博文类型\t\t\t\t\t\t"+"访问次数\t\t\t\t\t\t"+"排名\t\t\t\t\t\t");
                    System.out.println(bean.getBlogger()+"\t"+bean.getSignature()+"\t"+bean.getArticleNature()+"\t"+bean.getArticleType()+"\t"+bean.getVisitCount()+"\t"+bean.getRanking());
                    udb.insert("user_csdn", 
                                    "blogger,signature,articlenature,articletype,visitcount,ranking", 
                                    "'"+    bean.getBlogger()+"','"+
                                            bean.getSignature()+"','"+
                                            bean.getArticleNature()+"','"+
                                            bean.getArticleType()+"','"+
                                            bean.getVisitCount()+"','"+
                                            bean.getRanking()+"'");
                }
                
                //2.插入用户博文信息
                List<String> title_list=(List)bean.getBlog().get(0);                //【文章标题】
                List<String> URL_list=(List)bean.getBlog().get(1);              //【内容URL】
                List<String> readCount_list=(List)bean.getBlog().get(2);        //【阅读次数】
                List<String> time_list=(List)bean.getBlog().get(3);             //【发表时间】
                System.out.println("标题\t\t\t\t\t\t\t"+"URL地址\t\t\t\t\t\t\t"+"阅读次数\t\t\t\t\t\t\t"+"发表时间\t\t\t\t\t\t\t"+"作者\t\t\t\t\t\t\t");
                for(int i=0;i<title_list.size();i++){
                    System.out.println(title_list.get(i)+"\t"+ URL_list.get(i)+"\t"+ readCount_list.get(i).replace("阅读","")+"\t"+time_list.get(i)+"\t"+bean.getBlogger());
                    String title=title_list.get(i).replace(",","，").replace("'","‘");
                    udb.insert("blog_csdn", 
                                    "title,URL,readCount,time,blogger",
                                    "'"+title+"','"+
                                          URL_list.get(i)+"','"+
                                          readCount_list.get(i).replace("阅读","")+"','"+
                                          time_list.get(i)+"','"+
                                          bean.getBlogger()+"'");
                }
            
                
            }
            
            //第三步：关闭数据库连接
            udb.closeAll();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}


```

---


<br><br><br>


### 2. 爬取CSDN的博客【2.0版】
+ 数据库连接采用单例链接，节约系统资源
+ 添加WebMagic的爬虫监控，可用Java工具jconsole监控CSDN_Processor线程
+ 重新设计数据库表结构【分为三个表，对应三个JavaBean】
+ 优化Processor,正则XPATH，代码分层，有注释便于以后回顾
+ UseDB.java添加单例模式
+ 使用Redius对爬取的URL进行管理，每次开始程序就会从上次爬取的地方进行爬取,需要启动Redis
+ 终止程序后,下次爬取会自动从Redis上次爬取的URL开始爬取【暂停功能，重新启动】
+ 使用WebMagic的框架的Redis无法实现去重【在数据库设置了num主键自增,爬取重复页面时处理相同，仍然会执行插入操作,虽然会失败，但是会自增】，未解决！！！！


<br><br>

A.设计数据表
```
1.用户资料表
    编号【统计数量,主键自增】
    id【非空,唯一】
    昵称
    简介
    个性签名【有些博主的签名相当长啊】
    关注
    粉丝

CREATE TABLE blogger_csdn(             
    num INT auto_increment primary key,
    id VARCHAR(50) NOT NULL unique,
    b_nickname VARCHAR(50),
    b_intro VARCHAR(500),
    b_sign VARCHAR(2000),
    b_focus INT,
    b_fans INT
)ENGINE = innoDB;


***********************

2.用户博文列表页
    编号【统计数量,主键自增】
    id【非空,唯一】
    访问次数
    积分
    排名【某些人排名是千里之外】
    包含博文属性
    包含博文类型
CREATE TABLE bloglist_csdn(             
    num INT auto_increment primary key,
    id VARCHAR(50) NOT NULL unique,
    bl_visittimes INT,
    bl_score INT,
    bl_rank VARCHAR(10),
    bl_blognature VARCHAR(50),
    bl_blogtype VARCHAR(2000)
)ENGINE = innoDB;


***********************


3.博文页
    编号【统计数量,主键自增】
    标题
    URL【非空,唯一】
    阅读数
    评论数
    文章类型
    发表时间
    博主id【非空】
CREATE TABLE blogarticle_csdn(             
    num INT auto_increment primary key,
    ba_title VARCHAR(200),
    ba_url VARCHAR(500) NOT NULL unique,
    ba_readtimes INT,
    ba_commenttimes INT,
    ba_articletype VARCHAR(50),
    ba_publishdate VARCHAR(20),
    ba_bloggerid VARCHAR(50) NOT NULL
)ENGINE = innoDB;

```

---

<br><br>


2.三种JavaBean
```
package bean;

//CSND-用户资料表
public class CSDN_bloggerBean {
    
        private String id;                          //【id】
        private String b_nickname;          //【昵称】
        private String b_intro;                 //【简介】
        private String b_sign;                  //【签名】
        private int b_focus;                        //【关注】
        private int b_fans;                     //【粉丝】
        
        public String getId() {
            return id;
        }
        public String getB_nickname() {
            return b_nickname;
        }
        public String getB_intro() {
            return b_intro;
        }
        public String getB_sign() {
            return b_sign;
        }
        public int getB_focus() {
            return b_focus;
        }
        public int getB_fans() {
            return b_fans;
        }
        public void setId(String id) {
            this.id = id;
        }
        public void setB_nickname(String b_nickname) {
            this.b_nickname = b_nickname;
        }
        public void setB_intro(String b_intro) {
            this.b_intro = b_intro;
        }
        public void setB_sign(String b_sign) {
            this.b_sign = b_sign;
        }
        public void setB_focus(int b_focus) {
            this.b_focus = b_focus;
        }
        public void setB_fans(int b_fans) {
            this.b_fans = b_fans;
        }

}

***************************************************

package bean;

//CSDN-用户博文列表
public class CSDN_bloglistBean {
    
    private String id;                          //【id】
    private int bl_visitTimes;              //【访问次数】
    private int bl_score;                   //【积分】
    private String bl_rank;                     //【排名】
    private String bl_blogNature;       //【包含博文属性】
    private String bl_blogType;         //【包含博文类型】
    
    public String getId() {
        return id;
    }
    public int getBl_visitTimes() {
        return bl_visitTimes;
    }
    public int getBl_score() {
        return bl_score;
    }
    public String getBl_rank() {
        return bl_rank;
    }
    public String getBl_blogNature() {
        return bl_blogNature;
    }
    public String getBl_blogType() {
        return bl_blogType;
    }
    public void setId(String id) {
        this.id = id;
    }
    public void setBl_visitTimes(int bl_visitTimes) {
        this.bl_visitTimes = bl_visitTimes;
    }
    public void setBl_score(int bl_score) {
        this.bl_score = bl_score;
    }
    public void setBl_rank(String bl_rank) {
        this.bl_rank = bl_rank;
    }
    public void setBl_blogNature(String bl_blogNature) {
        this.bl_blogNature = bl_blogNature;
    }
    public void setBl_blogType(String bl_blogType) {
        this.bl_blogType = bl_blogType;
    }
}


***************************************************

package bean;

//CSDN-博文页
public class CSDN_blogarticleBean {
    
    private String ba_title;                        //【标题】
    private String ba_URL;                  //【URL地址】
    private int ba_readTimes;               //【阅读数】
    private int ba_commentTimes;        //【评论数】
    private String ba_articleType;          //【文章类型】
    private String ba_publishDate;      //【发表时间】
    private String ba_bloggerId;            //【博主id】
    
    
    public String getBa_title() {
        return ba_title;
    }
    public String getBa_URL() {
        return ba_URL;
    }
    public int getBa_readTimes() {
        return ba_readTimes;
    }
    public int getBa_commentTimes() {
        return ba_commentTimes;
    }
    public String getBa_articleType() {
        return ba_articleType;
    }
    public String getBa_publishDate() {
        return ba_publishDate;
    }
    public String getBa_bloggerId() {
        return ba_bloggerId;
    }
    public void setBa_title(String ba_title) {
        this.ba_title = ba_title;
    }
    public void setBa_URL(String ba_URL) {
        this.ba_URL = ba_URL;
    }
    public void setBa_readTimes(int ba_readTimes) {
        this.ba_readTimes = ba_readTimes;
    }
    public void setBa_commentTimes(int ba_commentTimes) {
        this.ba_commentTimes = ba_commentTimes;
    }
    public void setBa_articleType(String ba_articleType) {
        this.ba_articleType = ba_articleType;
    }
    public void setBa_publishDate(String ba_publishDate) {
        this.ba_publishDate = ba_publishDate;
    }
    public void setBa_bloggerId(String ba_bloggerId) {
        this.ba_bloggerId = ba_bloggerId;
    }
}


```

---

<br><br>


C.Processor
```
package PageProcessor;

import java.util.ArrayList;
import java.util.List;

import Pipeline.CSDN_Pipeline2;
import bean.CSDN_blogarticleBean;
import bean.CSDN_bloggerBean;
import bean.CSDN_bloglistBean;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.scheduler.RedisScheduler;



public class CSDN_Processor2 implements PageProcessor{

    //正则列表
    private static final String blogger="http://my.csdn.net/\\w+";                                                  //用户资料页
    private static final String blog_LIST="http://blog.csdn.net/(.*)/article/list/\\d+";                //用户博文列表页
    private static final String blog_ARTICLE="http://blog.csdn.net/(.*)/article/details/\\d+";      //博文页
    
    private static  long beginTime=System.currentTimeMillis();//统计开始爬取的时间
    
    private static int userCount=0;
    
    // 抓取网站的相关配置，包括编码、抓取间隔、重试次数等
    private Site site = Site.me().setCharset("utf-8").setSleepTime(1/1000).setRetryTimes(5);

    
    public Site getSite() {
        return site;
    }


    public void process(Page page){
             //标识：1-用户资料页,2-用户博文列表页,2-博文页
             int mark =0;
        

             if(page.getUrl().regex(blogger).match()){
                 //A.用户资料页
                 
                 //A-1获取该用户数据
                 mark=1;
                 String id=page.getUrl().get().substring(page.getUrl().get().lastIndexOf("/")+1);
                 String b_nickname=page.getHtml().xpath("//dt[@class='person-nick-name']/allText()").get();
                 String b_intro=page.getHtml().xpath("//dd[@class='person-detail']/allText()").get();
                 String b_sign = page.getHtml().xpath("//dd[@class='person-sign']/allText()").get().replace(",", "，").replace("'", "‘").replace("没有内容可显示","");
                 String b_focus=page.getHtml().xpath("//dd[@class='focus_num']/b/allText()").get().replace("未显示简介","");
                 String b_fans =page.getHtml().xpath("//dd[@class='fans_num']/b/allText()").get();
                 CSDN_bloggerBean bean  = new CSDN_bloggerBean();
                        bean.setId(id);                                                 //【用户id】            
                        bean.setB_nickname(b_nickname);                 //【昵称】
                        bean.setB_intro(b_intro);                                   //【简介】
                        bean.setB_sign(b_sign);                                 //【签名】
                        bean.setB_focus(Integer.valueOf(b_focus));      //【关注】
                        bean.setB_fans(Integer.valueOf(b_fans));            //【粉丝】      
                 System.out.println((++userCount)+"号用户--------->id:"+id+"\t\t昵称:"+b_nickname+"\t\t简介:"+b_intro+"\t\t签名:"+b_sign+"\t\t关注:"+b_focus+"\t\t粉丝:"+b_fans);
                
                 page.putField("mark", mark);
                 page.putField("CSDN_bloggerBean", bean);
                        
                //A-2.生成该用户的博客列表页链接
                 page.addTargetRequest("http://blog.csdn.net/"+id+"/article/list/1");

                //A-3.获取其余用户的链接
                List<String> otherBlogger_list=page.getHtml().xpath("//div[@class='mod_relations']//a").links().regex(blogger).all();
                page.addTargetRequests(otherBlogger_list);
                

             }
             else if(page.getUrl().regex(blog_LIST).match()){
                 //B.用户博文列表页
                 
                    //只在第1页获取资料
                    if( page.getUrl().regex("(.*)/list/1").match()){            
                            mark=2;
                            
                            //B-1.获取用户的资料
                            String id=page.getHtml().xpath("//div[@id='blog_userface']/span[1]/allText()").get();
                            String bl_visitTimes =page.getHtml().xpath("//ul[@id='blog_rank']/li[1]/span[1]/allText()").get().replace("次","");
                            String bl_score = page.getHtml().xpath("//ul[@id='blog_rank']/li[2]/span/allText()").get();
                            String bl_rank =page.getHtml().xpath("//ul[@id='blog_rank']/li[4]/span/allText()").get().replace("第","").replace("名","");
                            String bl_blogNature=page.getHtml().xpath("//ul[@id='blog_statistics']/allText()").get().replace("：","(").replace("篇 ",")-")+")";
                            String bl_blogType=page.getHtml().xpath("//div[@id='panel_Category']/ul[2]/allText()").get().replace(" ","-").replace(",", "，").replace("'", "‘");      
                            CSDN_bloglistBean bean =new CSDN_bloglistBean();
                                    bean.setId(id);                                                                     //【用户id】
                                    bean.setBl_visitTimes(Integer.parseInt(bl_visitTimes));         //【访问次数】
                                    bean.setBl_score(Integer.parseInt(bl_score));                       //【积分】
                                    bean.setBl_rank(bl_rank);                                                       //【排名】
                                    bean.setBl_blogNature(bl_blogNature);                               //【包含博文博文属性】
                                    bean.setBl_blogType(bl_blogType);                                       //【包含博文类型】
                                    System.out.println("列表页==>用户id:"+id+"\t\t访问次数:"+bl_visitTimes+"\t\t积分:"+bl_score+"\t\t排名:"+bl_rank+"\t\t博客统计:"+bl_blogNature+"\t\t文章分类:"+bl_blogType);
                            
                            page.putField("mark", mark);
                            page.putField("CSDN_bloglistBean", bean);
                            
                            //B-2.获取总页数【生成该用户所有页的链接】
                            String pageS =page.getHtml().xpath("//div[@id='papelist']/span[1]/allText()").get(); //获取总页数
                            int pageCount =Integer.parseInt(pageS.substring(pageS.lastIndexOf("共")+1,pageS.lastIndexOf("页")));
                            List<String> page_list=new ArrayList<String>();
                            for(int p=2;p<=pageCount;p++){      //生成URL【跳进来的时候就是第1页了，所以从第2页开始】
                                page_list.add("http://blog.csdn.net/"+id+"/article/list/"+p);
                            }
                            page.addTargetRequests(page_list);
                    }
                 
                    
                    //获取当前页所有博文链接
                    List<String> blogArticle_list=page.getHtml().xpath("//span[@class='link_title']/a[1]/@href").all();
                    page.addTargetRequests(blogArticle_list);

                    
             }else if(page.getUrl().regex(blog_ARTICLE).match()){
                 //C.博文页
                 mark=3;

                 //C-1获取博文资料
                 String ba_title=page.getHtml().xpath("//h1/allText()").get().replace(",", "，").replace("'", "‘");
                 String ba_URL=page.getUrl().get();
                 String ba_readTimes=page.getHtml().xpath("//div[@class='article_r']/span[2]/allText()").get().replace("人阅读","");
                 String ba_commentTimes=page.getHtml().xpath("//div[@class='article_r']/span[3]/allText()").get().replace("评论(","").replace(")","");    
                 String ba_articleType="无";
                 if(page.getHtml().xpath("//div[@class='category_r']").get()!= null){  //若类型不为空
                     ba_articleType=page.getHtml().xpath("//div[@class='category_r']//span[1]/allText()").get().replace(",", "，").replace("'", "‘").replaceAll("\\（.*\\）", "");//正则去除（内字符）
                 }
                 String ba_publishDate=page.getHtml().xpath("//div[@class='article_r']//span[1]/allText()").get();
                 String ba_bloggerId=page.getUrl().get().substring(page.getUrl().get().indexOf("net/")+4,page.getUrl().get().lastIndexOf("/article"));
                 CSDN_blogarticleBean bean = new CSDN_blogarticleBean();
                        bean.setBa_title(ba_title);                                                                     //【标题】
                        bean.setBa_URL(ba_URL);                                                                 //【博文URL】
                        bean.setBa_readTimes(Integer.parseInt(ba_readTimes));                       //【阅读数】
                        bean.setBa_commentTimes(Integer.parseInt(ba_commentTimes));     //【评论数】
                        bean.setBa_articleType(ba_articleType);                                             //【文章类型】
                        bean.setBa_publishDate(ba_publishDate);                                         //【发表时间】
                        bean.setBa_bloggerId(ba_bloggerId);                                                 //【博主id】
                 System.out.println("标题:"+ba_title+"\t内容URL:"+ba_URL+"\t\t阅读数:"+ba_readTimes+"\t\t评论:"+ba_commentTimes+"\t\t文章类型:"+ba_articleType+"\t\t发表时间:"+ba_publishDate+"\t\t博主id:"+ba_bloggerId);
                    
                    page.putField("mark", mark);
                    page.putField("CSDN_blogarticleBean", bean);
             }
            else{  //其他页面直接过滤，不执行Pipeline
                 page.setSkip(true);
             }
             
    }
    
    public static void main(String [] args){
        try{
            
            //CSDN任意一个用户的个人资料页面作为入口【这里选得是2016.12.13官网右边的某个签约作者】
            //QueueScheduler【使用内存队列保存待抓取URL】，使用BloomFilterDuplicateRemove去重
            //FileCacheQueueScheduler使用文件保存抓取URL，可以在关闭程序并下次启动时，从之前抓取到的URL继续抓取需指定路径，会建立.urls.txt和.cursor.txt两个文件
            //使用Redis对抓取的URL进行管理【未实现】
            //添加Pipeline,对数据进行处理每个Page如果skip为true，都会执行CSDN_Pipeline的process()
            //开启10个线程进爬取
            
                Spider.create(new CSDN_Processor2()).addUrl("http://my.csdn.net/lfdfhl")    
//                  .scheduler(new QueueScheduler().setDuplicateRemover(new BloomFilterDuplicateRemover(10000000)))
//                  .scheduler(new FileCacheQueueScheduler("E:\\javaweb_WorkSpace\\Webmagic\\savaURLFile"))
                    .scheduler(new RedisScheduler("127.0.0.1"))          //需要开启Redis,填写主机IP
                    .addPipeline(new CSDN_Pipeline2())
                    .thread(10)
                    .run();
            
            //爬虫监控【开始爬取之后,DOS输入jconsole选择CSDN_Processor2进程进行监控】
//          SpiderMonitor.instance().register(task);
//          task.start();
        
        
        System.out.println("爬取完毕,总共耗时:"+(String.valueOf(((double)System.currentTimeMillis()-beginTime)/1000))+"秒.");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}

```

---

<br><br>

Pipeline
```
package Pipeline;

import java.util.List;

import Utils.UseDB;
import bean.CSDN_blogarticleBean;
import bean.CSDN_bloggerBean;
import bean.CSDN_bloglistBean;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.ConsolePipeline;

public class CSDN_Pipeline2 extends ConsolePipeline  {

    
    
     // ResultItems保存了抽取结果，它是一个Map结构，
    // 在page.putField(key,value)中保存的数据，可以通过ResultItems.get(key)获取
    public void process(ResultItems resultItems, Task arg1) {
        
        //获取标识【判断是那种类型页面】
        int mark =resultItems.get("mark");
        
        try{
            UseDB udb =UseDB.getInstance("javareptile"); //单例模式获取实例
//          UseDB udb =new UseDB();
//          udb.connDatabase("javareptile");

            //根据不同的mark，处理来自不同页面的数据
            if(mark==1){
                //A.用户资料页
                CSDN_bloggerBean bean1=(CSDN_bloggerBean)resultItems.get("CSDN_bloggerBean");
                udb.insertReplace("blogger_csdn", 
                        "id,b_nickname,b_intro,b_sign,b_focus,b_fans", 
                        "'"+    bean1.getId()+"','"+
                                bean1.getB_nickname()+"','"+
                                bean1.getB_intro()+"','"+
                                bean1.getB_sign()+"','"+
                                bean1.getB_focus()+"','"+
                                bean1.getB_fans()+"'");
            
            }else if(mark==2){
                //B.用户博文列表页
                CSDN_bloglistBean bean2=(CSDN_bloglistBean)resultItems.get("CSDN_bloglistBean");
                    udb.insertReplace("bloglist_csdn", 
                            "id,bl_visittimes,bl_score,bl_rank,bl_blognature,bl_blogtype", 
                            "'"+    bean2.getId()+"','"+
                                    bean2.getBl_visitTimes()+"','"+
                                    bean2.getBl_score()+"','"+
                                    bean2.getBl_rank()+"','"+
                                    bean2.getBl_blogNature()+"','"+
                                    bean2.getBl_blogType()+"'");

            }else if(mark==3){
                //C.博文页
                CSDN_blogarticleBean bean3=(CSDN_blogarticleBean)resultItems.get("CSDN_blogarticleBean");
                udb.insertReplace("blogarticle_csdn", 
                        "ba_title,ba_url,ba_readtimes,ba_commenttimes,ba_articletype,ba_publishdate,ba_bloggerid", 
                        "'"+    bean3.getBa_title()+"','"+
                                bean3.getBa_URL()+"','"+
                                bean3.getBa_readTimes()+"','"+
                                bean3.getBa_commentTimes()+"','"+
                                bean3.getBa_articleType()+"','"+
                                bean3.getBa_publishDate()+"','"+
                                bean3.getBa_bloggerId()+"'");
            }

            //关闭数据库连接
//          udb.closeAll();
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}


```

---