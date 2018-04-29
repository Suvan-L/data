---
title: javaReptile
date: 2016-11-24 22:55:43
tags: MyProject
categories: MyProject
---


前言【最近更新:2016.11.29】
=========================

【1.0版本】
&emsp;学习使用java爬虫,耗时2天，写个小Demo，爬取影视大全(http://www.yingshidaquan.cc/)各个分类的电影,只爬取到1508部的下载链接
<br>

【2.0-Jsoup版】
&emsp;使用Jsoup重新写一遍，优化代码，

目录
- javaReptile【1.0版本】
- javaReptile【2.0-Jsoup版】




---


<br>

javaReptile【1.0版本】
=========================

#### 环境准备


IDE工具:eclipse
java环境: Sun JDK 1.6.0.013【X84 64位】
第三方扩展jar包: 
+ dom4j-1.6.1.jar
+ jdom-2.0.6.jar
+ nekohtml-1.9.21.jar
+ xercesImpl.jar

主要用到HttpURLConnection和HTMLParam【DOMParaser】解析器

---

#### 分析网站


http://www.yingshidaquan.cc/是官网，
电影主要分类：
+ 动作片：http://www.yingshidaquan.cc/vod-show-id-8-p-1.html
+ 喜剧片：http://www.yingshidaquan.cc/vod-show-id-9-p-1.html
+ 爱情片：http://www.yingshidaquan.cc/vod-show-id-10-p-1.html
+ 科幻片：http://www.yingshidaquan.cc/vod-show-id-11-p-1.html
+ 恐怖片：http://www.yingshidaquan.cc/vod-show-id-12-p-1.html
+ 战争片：http://www.yingshidaquan.cc/vod-show-id-13-p-1.html
+ 剧情片：http://www.yingshidaquan.cc/vod-show-id-14-p-1.html
+ 其他片：http://www.yingshidaquan.cc/vod-show-id-24-p-1.html

<br><br>
电影下载页面
举例URL：http://www.yingshidaquan.cc/html/DQ2744.html

<br><br>
目标
主要抓取下载页面的电影名称和迅雷磁力链接,在某目录生成8个分类的txt文档

<br><br>
运行思路
1. 先访问类型页面，获取当前页所有电影下载页面的URL
2. 依次跳转到每一个电影下载页面，进入其中，爬取电影名称和迅雷磁力链接
3. 跳到下一个电影下载页面，一下页，下一个类型


---


#### 遇到错误

1. 爱情类电影跳到第16页会报错而终止程序,网站服务器问题，无法解决
    【报错信息:java.net.SocketException:Unexpected end of file from server】
2. 小部分迅雷磁力链接比较特别，需要修改AnalyzeDocument.java的YSDCgetDownlink()方法内的判断语句
3. 电影下载页面的内容布局不同，有些有磁力链接，有些无，有些有多个


---


#### 效果

在`C:\\Users\\Liu-shuwei\\Desktop\\电影资源\\`目录下
+ 动作电影.txt
+ 喜剧电影.txt
+ 爱情电影.txt
+ 科幻电影.txt
+ 恐怖电影.txt
+ 战争电影.txt
+ 剧情电影.txt
+ 其他电影.txt

每个txt文档里包含
编号+电影名+下载链接
例如`566.爱情麻辣烫之情定终身:ed2k://|file|爱情麻辣烫之情定终身.HD1280超清国语中字.mp4|1534467094|912CB9454B20344822DB4B6676B89F8D|h=A7WH66AT7WEVVVKWV57C6M33PG4VOSG6|/`

----


######　代码【1.0版本,使用java最基础的HttpURLConection网页爬取功能】

序
-----------------------------

+ callURL.java          访问网页
+ StringXmlHtml.java    解析字符串成Document
+ AnalyzeDocument.java  传入Document，提取想要的信息
+ GoTask.java           任务流程，跳类型，跳页，跳页面
+ test.java             主方法，跑程序


---


1.用到封装工具类
------------------------
Utils包下的，IOFIle.java

---


2.callURL.java
--------------------------------------
```
package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 *  2016.11.23
 * 学习使用HttpURLConnection 来访问网页，获取相应内容信息
 * 
 *
 */

public class callURL{
    
    //方法1： 访问网址,返回网页内容
    public String taskURL(String strURL) throws Exception {
        
            //第一步
            URL url = new URL(strURL);      //解析字符串构建URL

            //第二步.使用HttpURLConnection连接网站，打开URL连接
            HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();  //返回一个 URLConnection 对象，它表示到 URL 所引用的远程对象的连接。
            deployHttp(httpConn);//设置http,//输出网站信息头
           
            //第三步.建立HttpURLConnection连接（2-1的配置必须在connect()前完成）
            httpConn.connect();
            
            //第四步. 
            //A.构建输出流向客户端发送信息(当需要发送post请求时候考虑)
//          OutputStream os = httpConn.getOutputStream();   //etOutputStream()和getInputStream()会隐含的进行connect(即：如同调用上面的connect()方法，所以在开发中不调用上述的connect()也可以
//          webPostRequest(os);//设置post请求参数
            
            
            //B.获取输入流，将准备好的http请求正式发送到服务器
            InputStream is = httpConn.getInputStream(); 

            //第五步.读取网页内容(字符串流)
            String content = webContent(is);
//          System.out.println("内容：\n"+content+"\n***********************************************************************");
            
            //第六步.关闭底层连接的socket
            httpConn.disconnect();
            
            return content;
    }
    
    
    //方法2：配置HttpURLConnection对象，并将部分信息头输出
        public void deployHttp( HttpURLConnection httpConn) throws Exception{
                //2-1连接设置
                httpConn.setRequestMethod("GET");//设置URL的请求，默认是GET
                
                httpConn.setDefaultAllowUserInteraction(true);//默认值
                httpConn.setDefaultUseCaches(false);//默认值
                httpConn.setUseCaches(true);//设置用户缓存 ,Post 请求不能使用缓存(要设置为false)
                httpConn.setAllowUserInteraction(true);//如果为 true，则在允许用户交互（例如弹出一个验证对话框）的上下文中对此 URL 进行检查。
                
                httpConn.setDoInput(true);//可以使用httpConn.getInputStream().read(); ,默认是true
                httpConn.setDoOutput(false);//可以使用httpConn.getOutputStream().read(); ,默认是false
                
                httpConn.setConnectTimeout(3000000);//设置连接主机超时（单位：毫秒）  
                httpConn.setReadTimeout(300000);//设置从主机读取数据超时（单位：毫秒）
                
                /*设定传送的内容类型是可序列化的java对象
                 * (如果不设此项,在传送序列化对象时,当WEB服务默认的不是这种类型时可能抛java.io.EOFException)   
                 *          setRequestProperty()会覆盖已经存在的key的所有values
                 *          addRequestProperty()在原来key的基础上继续添加其他value
                 */
    //          httpConn.setRequestProperty("Content-type", "application/x-java-serialized-object");//
    //          httpConn.addRequestProperty("page", "1");
                
                
                //2-2.输出相应信息
                int responseCode = httpConn.getResponseCode();   //判断相应状态,返回响应码
//              System.out.println("响应码："+responseCode);
                if (HttpURLConnection.HTTP_OK == responseCode) {// 判断响应状态，响应码为200，表示连接成功
               
                    
                    //3-2获取相应信息头参数
                    String contentEncoding=httpConn.getContentEncoding();
                    int contentLength=httpConn.getContentLength();
                    String contentType = httpConn.getContentType();
                    long date =httpConn.getDate();
                    long expiration =httpConn.getExpiration();
                    long lastModified=httpConn.getLastModified();
                    
//                  System.out.println("网页所有信息头："+httpConn.getHeaderFields());//获取网页所有信息头参数
//                  System.out.println("内容编码："+contentEncoding
//                                               +"\n内容长度: "+contentLength
//                                               +"\n内容类型："+contentType
//                                               +"\n日期: "+ date
//                                               +"\n有效期:"+expiration
//                                               +"\n最后更新: "+lastModified
//                                              );
//                 System.out.println("*******************************************************************");
                
                }
        }
    
        //方法3：发送请求内容【接收OuputStream,并进行设置】
        public void webPostRequest(OutputStream os) throws IOException{
              //3-11.设置POST参数
            String param = new String();
            String id ="";
            String page="";
            param="ID="+id +
                         "&page="+page;
            os.write(param.getBytes());
            
            
            // 3-2现在通过输出流对象构建对象输出流对象，以实现输出可序列化的对象
            ObjectOutputStream objOS= new ObjectOutputStream(os);
            objOS.writeObject(new String("我是测试数据"));  
            
            //3-3
            objOS.flush();//刷新对象输出流
            objOS.close();//关闭流对象.此时不能再向对象输出流写入任何数据，先前写入的数据存于内存缓冲区
        }
        
        
        
        
    //访法4：读取网址内容【获取InputStream】
    public String webContent(InputStream is) throws IOException{
            StringBuilder content = new StringBuilder();
            
             InputStreamReader isr = new InputStreamReader(is, "utf-8");
             BufferedReader br = new BufferedReader(isr);
             String readLine= null;
            while((readLine=br.readLine())!=null){
                content.append(readLine+"\n");
            }
                 
            
            br.close();
            isr.close();
            is.close();
            
            return content.toString();
    }
}
```

---


2.StringXmlHtml.java和AnalyzeDecument.java
--------------------------------
StringXmlHtml.java
```
package main;

import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.cyberneko.html.parsers.DOMParser;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;


/**
 * 2016.11.23
 *          xml解析用到标准jdk api,dom4j,JDOM【dom4j-1.6.1.jar和jdom-2.0.6.jar】
 *          html解析用到HTMLParams【nekohtml.jar和xercesImpl.jar】
 * 
 * 
 *      方法1:    XML(字符串)    ------>  Document
 *      方法2:    Document        ------> XML(字符串)
 *      方法3:    HTML(字符串)   ------>  Document
 *
 */

public class StringXmlHtml{
    
    //方法1：字符串转xml
    protected Document getDocument(String content) throws Exception{
        
        
        //方式1：使用最原始的javax.xml.parsers  和 org.w3c.dom.Document，标准的jdk api
        StringReader sr = new StringReader(content);
        InputSource is = new InputSource(sr);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder=factory.newDocumentBuilder();
        Document doc = builder.parse(is);
        
    
        //方式2: dom4j
//      Document doc = DocumentHelper.parseText(content);
        
        
        //方式3：JDOM
//      StringReader sr = new StringReader(content);
//      InputSource is = new InputSource(sr);
//      Document doc = (new SAXBuilder()).build(is);
    
        return doc;  
    }
    
    
    
    //方法2：xml转字符串
    protected String getString(Document doc) throws Exception{
        
        //方式1：使用最原始的javax.xml.parsers  和 org.w3c.dom.Document，标准的jdk api
//      TransformerFactory   tf   =   TransformerFactory.newInstance();
//      Transformer t = tf.newTransformer();
//      t.setOutputProperty("encoding","GB2312");//解决中文问题，试过用GBK不行
//      ByteArrayOutputStream   bos   =   new   ByteArrayOutputStream();
//      t.transform(new DOMSource(doc), new StreamResult(bos));
//      String xmlStr = bos.toString();
        
        
        
        //方式2：dom4j
//      String text = doc.asXML();
        
        //方式3：
//      Format format = Format.getPrettyFormat();
//      format.setEncoding("gb2312");//设置xml文件的字符为gb2312，解决中文问题
//      XMLOutputter xmlout = new XMLOutputter(format);
//      ByteArrayOutputStream bo = new ByteArrayOutputStream();
//      xmlout.output(doc,bo);
//      String content = bo.toString();
        
        return "";
    }
    
    
    //方法3：使用html解析器，将html解析成Document
    protected Document htmlGetDocument(String content) throws SAXException,IOException{
        content = content.trim();
        InputSource source = new InputSource(new StringReader(content.replaceFirst("xmlns=\"[^\"]*\"", "")));
        DOMParser parser = new DOMParser();
        parser.parse(source);
        Document doc = parser.getDocument();
        
        return doc;
    }
    
}
```

---

AnalyzeDocument.java
```
package main;

import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * 2016.11.24 
 *          获取Doucment，进行解析提取出需要的信息
 *
 */
public class AnalyzeDocument {


    /*
     * 影视大全:http://www.yingshidaquan.cc/
     *          电影-动作片-电影下载页面(http://www.yingshidaquan.cc/html/DQ20893.html)
     *                  1.电影名
     *                  2.下载链接
     */
    
    //影视大全：获取总页数
    protected int YSDCgetTotalPage(Document doc){
        int page =1;
        
        Element div_ele=(Element)doc.getElementById("pages");
        String content=div_ele.getTextContent();
        if(content!=null){
            content=content.substring(content.indexOf("/")+1,content.indexOf("页"));
            page=Integer.parseInt(content);
        }
        
        return page;
    }
    //影视大全: 获取1.电影名，2.下载链接
    protected String [] YSDCgetDownlink(Document doc){ //接收Document对象，返回String信息
        
        String [] content_arrays =new String[2];
        String title ="";
        String downlink="";
        
        //1.获取标题
        NodeList h1_list = doc.getElementsByTagName("h1");
        Element h1_ele = (Element)h1_list.item(0);
        title = h1_ele.getTextContent();
        
        NodeList input_list = doc.getElementsByTagName("input");
        
        //2.获取下载链接
        NodeList div_list=doc.getElementsByTagName("div");
        for(int i=0;i<div_list.getLength();i++){
            Element  div_ele=(Element)div_list.item(i);
            //方式1:
            if("ed2klist".equals(div_ele.getAttribute("class"))){
                downlink = div_ele.getTextContent();
                if(downlink.indexOf("|/") !=-1){
                    downlink=downlink.substring(downlink.indexOf("$")+1,downlink.indexOf("|/")+2);
                }else if(downlink.indexOf("###") !=-1){
                    downlink=downlink.substring(downlink.indexOf("$")+1,downlink.indexOf("###"));
                }else if(downlink.indexOf("$mag") !=-1){
                    downlink=downlink.substring(downlink.indexOf("$")+1,downlink.lastIndexOf(")")-1);
                }
                
                
                break;
            }
        }

        if(downlink.length()<5){
            downlink="无";
        }
        
        
        content_arrays[0]=title;
        content_arrays[1]=downlink;
        return content_arrays;
    }
    
    //影视大全：获取跳入电影页面的链接
    protected ArrayList<String> YSDCgetSkipLink(Document doc){ //接收Document对象，返回String信息
        
        
        ArrayList<String> alist =new ArrayList<String>();//储存所有跳转链接
        String skipLink ="";//单个跳转到电影页面的链接
        NodeList div_list=doc.getElementsByTagName("div");
        
        for(int i=0;i<div_list.getLength();i++){//<a>循环
                Element div_ele=(Element)div_list.item(i);
            
                if("info".equals(div_ele.getAttribute("class"))){
                        
                            Element a_ele=(Element)div_ele.getElementsByTagName("a").item(0);
                            skipLink="http://www.yingshidaquan.cc"+a_ele.getAttribute("href");
                            alist.add(skipLink);
                }
        }

        
        return alist;
    }
    
//  案例：
//  protected AnalyzeContent(String content){
//
//
//              Document xmlDoc=Utils.getDocByContent(originTxtRspContent);
//              System.out.println(originTxtRspContent);
//              
//              NodeList table_list=xmlDoc.getElementsByTagName("table");
//              int count=0;
//              for (int i = 0; i < table_list.getLength(); i++) {
//                  Element table_ele=(Element)table_list.item(i);
//                  
//                  if("98%".equals(table_ele.getAttribute("width"))){
//                      count++;
//                      if(count!=3){
//                          continue;
//                      }
//                      NodeList a_list=table_ele.getElementsByTagName("a");
//                      if(a_list!=null){
//                          for(int j=0;j<a_list.getLength();j++){
//                              Element a_ele=(Element)table_ele.getElementsByTagName("a").item(j);
//                              String title=a_ele.getTextContent().trim();
//  
//                              String href=a_ele.getAttribute("href");
//                              String iw_ir_2=href.substring(href.indexOf("/")+1,href.lastIndexOf(".")).trim();
//                              
//                              
//                              String a_title=a_ele.getAttribute("title").trim();
//                              String date=a_title.substring(a_title.lastIndexOf("更新时间：")+5,a_title.length()-8).trim();
//                              Date now = (Date) new SimpleDateFormat("yyyy-MM-dd").parse(date); 
//                               date = new SimpleDateFormat("yyyy-MM-dd").format(now);
//                              
//                              
//                                  String url = getNewPathPrefix() + "/?"
//                                          + getAdditionalLinkParamStr()
//                                          + "&iw-cmd=02307HNDetail&iw_ir_2="+iw_ir_2;
//                                      
//                                  BranchNew branchNew=new BranchNew();
//                                  
//                                  branchNew.title=title.substring(0);
//                                  branchNew.id=iw_ir_2;
//                                  branchNew.date=date;
//                                  branchNew.url=url;
//          
//                                  response.list.add(branchNew);
////                                    System.out.println(branchNew.toString());
//                                  }
//                          }
//                      }
//              }
//              
//              String pageCount=null;
//              NodeList b_list=xmlDoc.getElementsByTagName("b");
//              if(b_list!=null){
//                  Element b_ele=(Element)b_list.item(0);
//                  pageCount=b_ele.getTextContent().trim();
//                  int one=Integer.parseInt(pageCount);
//                  if(one%20==0){
//                      one =one/20;
//                  }else{
//                      one=one/20+1;
//                  }
//              }
//  }
}

```

---


3.GoTask.java和test.java
-------------------------
GoTask.java
```
package main;

import java.io.IOException;
import java.util.ArrayList;

import org.w3c.dom.Document;

import Utils.IOFile;


/**
 * 2016.11.24
 *      爬虫任务流程
 *
 */
public class GoTask {
    
    int runningID = 7;//现在要跑哪一个类型编号
    
    String [] filmType={"1-动作","2-喜剧","3-爱情","4-科幻","5-恐怖","6-战争","7-剧情","8-其他"};
    
    
    //影视大全:http://www.yingshidaquan.cc/
    protected void go(){
        
        IOFile iof = new IOFile();
        
        callURL cURL = new callURL();
        StringXmlHtml sxh = new StringXmlHtml();
        AnalyzeDocument ad = new AnalyzeDocument();
        
        String content="";                      //页面所有内容
        Document contentDoc =null;      //页面内容转换为Document
        int pageCount =1;                                   //总页数
        ArrayList<String> alist = null;    //当前页所有链接
        int filmCount =0;                            //计算电影数
        int [] filmTypeId={8,9,10,11,12,13,14,24};  //电影类型id
        
        try{
            //1.在第一页获取总页数
//          content =cURL.taskURL("http://www.yingshidaquan.cc/vod-show-id-"+filmTypeId[runningID-1]+"-p-1.html");
//          contentDoc = sxh.htmlGetDocument(content);
//          pageCount =ad.YSDCgetTotalPage(contentDoc);
            
            for(int z=1;z<=filmType.length;z++){
                    System.out.println("**************************爬取"+filmType[z-1].substring(2)+"类电影************************************");
                    for(int i=1;i<=30;i++){  //每种类型只跑前30页 
                        System.out.println("**************************目前为第"+i+"页************************************");
        
                        content =cURL.taskURL("http://www.yingshidaquan.cc/vod-show-id-"+filmTypeId[z-1]+"-p-"+i+".html");
        
        
                        contentDoc = sxh.htmlGetDocument(content);//解析当前页内容
                        alist = ad.YSDCgetSkipLink(contentDoc);//获取当前页所有链接
            
                            for(int k=0;k<alist.size();k++){
                                
                                //A.跳到每个电影下载页面
                                String downContent =cURL.taskURL(alist.get(k));//跳转下载页面
                                Document downContentDoc= sxh.htmlGetDocument(downContent);//解析下载页
                                String [] downLink =ad.YSDCgetDownlink(downContentDoc);
                                
                                //B.将内容储存到文件            
                                if(downLink[1].length()>5){
                                    filmCount++;//统计电影数量
                                    //传入路径，文件名，储存内容
                                    iof.addContentFile("C:\\Users\\Liu-shuwei\\Desktop\\电影资源\\",filmType[z-1].substring(2)+"电影.txt","\r\n"+filmCount+"."+downLink[0]+":"+downLink[1]+"\r\n");
                                }
                                
                                
                                System.out.println("标题："+downLink[0]+"\n链接："+downLink[1]);
                                System.out.println("**************************************************************");
                            }
                        System.out.println("**************************第"+i+"页结束************************************");
                    }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}

```

---

test.java
```
package main;

import org.w3c.dom.Document;

import Utils.IOFile;

public class test {
    
    
    //测试类主方法
    public static void main(String [] args){
        long begin = System.currentTimeMillis();
    
        GoTask gt = new GoTask();
        gt.go();

            
        
        long spendTime =System.currentTimeMillis()-begin;
         System.out.println("*******************************************************************");
        System.out.println("共花费"+spendTime+"毫秒");
    }
}


```

<br>


javaReptile【2.0-Jsoup版】
=======================================

&emsp;使用Jsoup重构代码，只有一个JsoupGo_2.java,代码尽量简洁,内涵注释

<br><br>

Code
---------

1.测试主方法
```
package 影视大全;

import org.w3c.dom.Document;

import Utils.IOFile;

public class test {
    
    
    //测试类主方法
    public static void main(String [] args){
        long begin = System.currentTimeMillis();

        try{
            JsoupGo_2 jg = new JsoupGo_2();
            jg.goTask();
        }catch(Exception e){
            e.printStackTrace();
        }
    
            
        
        long spendTime =System.currentTimeMillis()-begin;
         System.out.println("*******************************************************************");
        System.out.println("共花费"+spendTime+"毫秒");

        ---



    }
}

```

---


JsoupGo_2.java
```
package 影视大全;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import Utils.IOFile;

/**
 * 更新时间： 2016年11月29日   
 *
 *      使用Jousp抓取电影资源
 *
 *  @author Suvan
 */
public class JsoupGo_2 {

    protected void goTask() throws IOException{

        //电影分类
        String [] filmType={"1-动作","2-喜剧","3-爱情","4-科幻","5-恐怖","6-战争","7-剧情","8-其他"};
        //电影ID
        int [] filmTypeId={8,9,10,11,12,13,14,24};
        //电影数量
        int filmCount=60;
        
        //A.循环类型
        for(int type=1;type<=filmType.length;type++){
            System.out.println("**************************现在爬取"+filmType[type-1].substring(2)+"类电影************************************");
            //第一步：获取当前类型总页数
            Document docA = Jsoup.
                    connect("http://www.yingshidaquan.cc/vod-show-id-"+filmTypeId[type-1]+"-p-1.html").
                    timeout(5*1000).get();
            String maxPage=docA.getElementById("pages").text();
            maxPage = maxPage.substring(maxPage.indexOf("/")+1,maxPage.indexOf("页"));
            
            
            //B.循环页数
            for(int page=6;page<=Integer.parseInt(maxPage);page++){
                System.out.println("**************************目前为第"+page+"页************************************");
                Document docB =Jsoup.
                        connect("http://www.yingshidaquan.cc/vod-show-id-"+filmTypeId[type-1]+"-p-"+page+".html").
                        timeout(5*1000).get();
                //第二步:获取当前页面所有电影页面URL
                Elements a_eles = docB.select("a[class=p]");
                ArrayList<String> URLlist = new ArrayList<String>();//储存所有URL
                for(int i=0;i<a_eles.size();i++){
                    String url = "http://www.yingshidaquan.cc"+a_eles.get(i).attr("href");
                    URLlist.add(url);
                }
                
                //C.循环电影
                for(int k=0;k<URLlist.size();k++){
                    //第三步:获取电影页面的title(电影名)和link(迅雷下载链接)
                    Document docC =Jsoup.
                            connect(URLlist.get(k)).
                            timeout(5*1000).get();//设置5s延迟时间，防止Read timed out
                        //1.获取标题
                        String title=docC.title();
                        title=title.substring(0,title.indexOf("高清电影"));
                        
                        //2.获取迅雷链接
                        String link="";
                            //2-1.先查看有无磁力链接【ed2klist】
                            Elements div_ele1=docC.select("div[class=ed2klist]>script");
                            if(div_ele1.size()>0){
                                String content =div_ele1.get(0).html();
                                if(content.indexOf("|/") !=-1)
                                    link=content.substring(content.indexOf("$")+1,content.indexOf("|/")+2);
                                else if(content.indexOf("###") !=-1)
                                    link=content.substring(content.indexOf("$")+1,content.indexOf("###"));
                                else if(content.indexOf("$mag") !=-1)
                                    link=content.substring(content.indexOf("$")+1,content.lastIndexOf(")")-1);  
                            }else{
                                //2-2.查看迅雷备用下载链接【downlist】
                                Elements div_ele2=docC.select("div[class=downlist]>script");
                                if(div_ele2.size()>0){
                                    String content=div_ele2.get(0).html();
                                    if(content.indexOf("$") != -1){
                                        if(content.indexOf("###") !=-1)
                                            link=content.substring(content.indexOf("$")+1,content.indexOf("###"));
                                        else
                                            link=content.substring(content.indexOf("$")+1,content.lastIndexOf("\")"));
                                    }
                                        
                                }else
                                    if(link.length()<2){
                                        link="无";
                                        continue;//下一次循环
                                    }
                            }
                            filmCount++;
                    //第四步：获得内容储存进文件
                    IOFile iof  = new IOFile();
                    iof.addContentFile("C:\\Users\\Liu-shuwei\\Desktop\\电影资源\\",
                                                filmType[type-1].substring(2)+"电影.txt",
                                                "\r\n"+filmCount+"."+title+":"+link+"\r\n");
                    
                    System.out.println(filmCount+"-"+title+":\t"+link);
                    System.out.println("********************************************************************************************");
                    }//-C
                System.out.println("**************************"+page+"页结束************************************");
                }//-B
            System.out.println("**************************"+filmType[type-1].substring(2)+"类电影爬取结束************************************");
            }//-A
    }
        
            
}


```

---


