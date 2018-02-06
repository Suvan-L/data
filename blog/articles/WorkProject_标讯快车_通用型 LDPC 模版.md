---
title: 标讯快车_通用型 LDPC 模版
date: 2016-8-17 11:28:43
tags: WorkProject
categories: WorkProject
---


目录
================================
  帮助小伙伴们快速开发，总结 6 个通用模版 ，API 文档样式，应根据网站不同进行相应变化，总体设计思路一致

1. List
2. Detail
3. Params
4. Combine
5. API 文档格式
6. 2017.01.16 新增模板说明
7. ListCombine（新增）
8. DetailCombine（新增）


## 1. List
```
package XN03472;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import cn.internetware.phone.extension.response.RspState;
import cn.internetware.phone.extension.response.TxtRspObject;
import cn.internetware.phone.extension.response.impl.TxtBaseResponse;
import cn.internetware.phone.extension.response.impl.TxtRspHandler;
import cn.internetware.utils.IO;
import cn.internetware.utils.Utils;


public class ZBList extends TxtRspHandler {

    public class Response extends TxtBaseResponse {
        List<BranchNew> list = new ArrayList<BranchNew>();
        String pageCount;
        //String currentPage;
    }

    public class BranchNew {
        String title;
        String id;
        String date;
        String url;

        public String toString() {
            return "BranchNew[ title=" + title + ";date="+date+";id=" + id +";url="+url;
        }

    }

    @Override
    protected RspState checkTxtRspContentState(String originTxtRspContent) {
        return RspState.Login;
    }

    @Override
    protected TxtRspObject processTxtRspContent(RspState rspState,
            String originTxtRspContent) {
        Response response = new Response();
        if (rspState == RspState.Login) {
            try {
                
                /* listOne    listSecord   listThird
                 * ele1         ele2       ele3
                 */
                
                
                originTxtRspContent="<div>"+originTxtRspContent+"</div>";
//              System.out.println(originTxtRspContent);
                Document xmlDoc=Utils.getDocByContent(originTxtRspContent);
                
                //获取title，id，url
                int count=0;
                NodeList listOne=xmlDoc.getElementsByTagName("ul");
                for (int i = 0; i < listOne.getLength(); i++) { 
                    Element ele1=(Element)listOne.item(i);
                    
                    if("item_list".equals(ele1.getAttribute("id"))){
                        if(++count!=1) continue;
                        
                        NodeList listSecord=ele1.getElementsByTagName("li");
                        if(listSecord!=null){;
                            for(int j=0;j<listSecord.getLength();j++){              
                                Element ele2=(Element)listSecord.item(j);

                                //没有a标签则退出循环
                                NodeList a_list=ele2.getElementsByTagName("A");
                                if(a_list.getLength()<1) continue;
                                
                                Element a_ele=(Element)ele2.getElementsByTagName("A").item(0);
                                Element span_ele=(Element)ele2.getElementsByTagName("SPAN").item(0);
                                
                                String title=a_ele.getTextContent().trim();
//                              title=title.substring(1);
                                
                                String href=a_ele.getAttribute("href").trim();
                                String id=href.substring(href.indexOf("Item/")+5,href.lastIndexOf(".")).trim();
                                    
                                String url=getNewPathPrefix()+"/bnuser_3/?"+
                                                getAdditionalLinkParamStr()+
                                                "&iw-cmd=03472XNDetail&iw_ir_2="+id;
                                
                                String date=span_ele.getTextContent().trim();
//                              date=date.substring(1,11);
                                date=date.substring(date.indexOf("[")+1,date.lastIndexOf("]"));
//                              Date date_D = (Date) new SimpleDateFormat("yyyy/MM/dd").parse(date); 
//                              date = new SimpleDateFormat("yyyy-MM-dd").format(date_D);
                                    
                                    
                                    //创建对象
                                    BranchNew branchNew=new BranchNew();        
                                    branchNew.title=title;
                                    branchNew.date=date;        
                                    branchNew.id=id;            
                                    branchNew.url=url;
                                    response.list.add(branchNew);
                            
                                    System.out.println(branchNew.toString());
                            }
                        }
                    }
                }
                
                //获取总页数
                int count2=0;
                String pageCount=null;
//              pageCount=originTxtRspContent.substring(originTxtRspContent.indexOf("页次：")+3);
//              response.pageCount=pageCount.substring(pageCount.indexOf("/")+1,pageCount.indexOf("每")).trim().replace("&nbsp;","");
                NodeList listThird=xmlDoc.getElementsByTagName("div");
                if(listThird!=null){
                    for(int i=0;i<listThird.getLength();i++){
                        Element ele3=(Element)listThird.item(i);
                        
                        if("page".equals(ele3.getAttribute("class"))){
                            if(++count2!=1) continue;
                            
                            pageCount=ele3.getTextContent().trim();
                            pageCount=pageCount.substring(pageCount.indexOf("共")+1,pageCount.indexOf("篇"));
                            int page=Integer.valueOf(pageCount);
                            if(page%20==0)
                                pageCount=String.valueOf(page/20);
                            else
                                pageCount=String.valueOf(page/20+1);
                            response.pageCount=pageCount;
                        }
                    }
                }
                
                if(response.pageCount==null){
                    response.pageCount="1";
                }
                
                System.out.println(response.pageCount);
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
        return response;
    }

    final static int TIMEOUT_IN_MS = 100000;

    private static String callApi(String path) {
        String result = "";
        byte[] retBytes = new byte[0];
        try {
            URLConnection conn = new URL(path).openConnection();
            conn.setConnectTimeout(TIMEOUT_IN_MS);
            conn.setReadTimeout(TIMEOUT_IN_MS);
            retBytes = IOUtils.toByteArray(conn);
            result = new String(retBytes, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public static void main(String[] args) {
        ZBList handler = new ZBList();

        try {
            String originRspContent = IO
                    .deserializeString("internetware/bnuser3_3/apis/03472-dcf36ba4a718406786f3adf8c464bca4-XNList/SampleResponse","UTF-8");
            handler.processRspContent(
                    handler.checkTxtRspContentState(originRspContent),
                    originRspContent);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


```




## 2. Detail  
```
package XN03472;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import cn.internetware.phone.extension.response.RspState;
import cn.internetware.phone.extension.response.TxtRspObject;
import cn.internetware.phone.extension.response.impl.TxtBaseResponse;
import cn.internetware.phone.extension.response.impl.TxtRspHandler;
import cn.internetware.utils.IO;
import cn.internetware.utils.Utils;

public class ZBDetail extends TxtRspHandler {
    
    public class Response extends TxtBaseResponse {
        String content;

    }

    
    @Override
    protected RspState checkTxtRspContentState(String originTxtRspContent) {
        return RspState.Login;
    }

    @Override
    protected TxtRspObject processTxtRspContent(RspState rspState,
            String originTxtRspContent) {
        Response response = new Response();
        if (rspState == RspState.Login) {
            try {
                
                /*listFist 
                 * ele1  
                 */
                
                originTxtRspContent = "<div>" + originTxtRspContent+ "</div>";
//              System.out.println(originTxtRspContent);
                
                Document xmlDoc = Utils.getDocByContent(originTxtRspContent);
                
                //获取content
                int count=0;
                String content="";
                NodeList listFist=xmlDoc.getElementsByTagName("div");
                if(listFist!=null){
                    for(int j=0;j<listFist.getLength();j++){
                        Element ele1=(Element)listFist.item(j);
    
                        if("fontZoom".equals(ele1.getAttribute("id"))){
                            if(++count!=1) continue;
                            
                            NodeList a_list=ele1.getElementsByTagName("a");
                             if(a_list.getLength()>0){
                                 for(int i=0;i<a_list.getLength();i++){
                   
                                     Element a_ele=(Element)a_list.item(i);
                                     String href=a_ele.getAttribute("href");
                                     
                                     if(href.indexOf("http")!=-1 || href.length()<5){
                                         continue;
                                     }

                                     if(href.indexOf("/")>=1){
                                          href=href.replaceFirst("./","http://www.ynxy.gov.cn/");
                                     }else{
                                         href=href.replaceFirst("/","http://www.ynxy.gov.cn/");
                                     }
                                     a_ele.setAttribute("href", href);
                                 }
                             }
                             
                             NodeList img_list=ele1.getElementsByTagName("img");
                             if(img_list.getLength()>0){
                                 for(int i=0;i<img_list.getLength();i++){
                                     Element img_ele=(Element)img_list.item(i);
                                          
                                     String src=img_ele.getAttribute("src");
                                     
                                     if(src.indexOf("http")!=-1  || src.length()<5){
                                         continue;
                                     }

                                     if(src.indexOf("/")>=1){
                                      src=src.replaceFirst("./","http://www.ynxy.gov.cn/");
                                    }else{
                                     src=src.replaceFirst("/","http://www.ynxy.gov.cn/");
                                    }
                                     
                                     img_ele.setAttribute("src", src);
                                 }
                             } 
                             
                             content=Utils.getNodeHtml(ele1);
                        }
                    }
                }
            
        
                 response.content=content;
                 
                 //测试输出
                 System.out.println(response.content);
           

            }catch (Exception e) {
                e.printStackTrace();
            }
        }
        return response;
    }

    public static void main(String[] args) {
        ZBDetail handler = new ZBDetail();

        try {
            String originRspContent = IO
                    .deserializeString("internetware/bnuser3_3/apis/03472-50d1ef5fbcb14e7b9068d099b054575a-XNDetail/SampleResponse","UTF-8");
            handler.processRspContent(
                    handler.checkRspContentState(originRspContent),
                    originRspContent);
            handler.transformTableToBeImage(originRspContent);
            // System.out.println(originRspContent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}


```




## 3. Params
```
import java.net.URL;
import java.net.URLConnection;

import org.apache.commons.io.IOUtils;
import org.w3c.dom.Document;

import cn.internetware.phone.extension.response.RspState;
import cn.internetware.phone.extension.response.TxtRspObject;
import cn.internetware.phone.extension.response.impl.TxtBaseResponse;
import cn.internetware.phone.extension.response.impl.TxtRspHandler;
import cn.internetware.utils.IO;
import cn.internetware.utils.Utils;


public class ZBParams extends TxtRspHandler {

    public class Response extends TxtBaseResponse {
        Params params = new Params();

    }

    public class Params { 

        //分析页面，得出跳页需要的几个请求参数(第一页的表单提交数据，可通过浏览器的F12进行查看)
        String __CSRFTOKEN;
        String __VIEWSTATE;
        String __EVENTTARGET;
        String __EVENTARGUMENT;

        @Override
        public String toString() {
            return "Params [__CSRFTOKEN="+__CSRFTOKEN+";__VIEWSTATE=" + __VIEWSTATE 
                    + ";__EVENTTARGET=" + __EVENTTARGET 
                    + ";__EVENTARGUMENT=" + __EVENTARGUMENT+ "]";
        }

    }

    protected RspState checkTxtRspContentState(String originTxtRspContent) {
        return RspState.Login;
    }

    protected TxtRspObject processTxtRspContent(RspState rspState,
            String originTxtRspContent) {
        Response response = new Response();
        if (rspState == RspState .Login) {
            try {
                originTxtRspContent = "<table>" + originTxtRspContent
                        + "</table>";
                Document xmlDoc = Utils.getDocByContent(originTxtRspContent);
                
                //注意要做判断
                if(xmlDoc.getElementById("__CSRFTOKEN")!=null){
                    response.params.__CSRFTOKEN = xmlDoc.getElementById(
                    "__CSRFTOKEN").getAttribute("value");
                }
                
                //动态请求
                if(xmlDoc.getElementById("__VIEWSTATE")!=null){
                    response.params.__VIEWSTATE = xmlDoc.getElementById(
                            "__VIEWSTATE").getAttribute("value");
                }
                
  
                if(xmlDoc.getElementById("__EVENTTARGET")!=null){
                    response.params.__EVENTTARGET = xmlDoc.getElementById(
                            "__EVENTTARGET").getAttribute("value");                 
                }
                
                //一般都是，页数
                if(xmlDoc.getElementById("__EVENTARGUMENT")!=null){
                    response.params.__EVENTARGUMENT = xmlDoc.getElementById(
                            "__EVENTARGUMENT").getAttribute("value");               
                }
                
                System.out.println(response.params.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //System.out.println(response.params.toString());
        return response;
    }

    final static int TIMEOUT_IN_MS = 100000;


    //访问路径的方法，并返回内容
    private static String callApi(String path) {
        String result = "";
        byte[] retBytes = new byte[0];
        try {
            URLConnection conn = new URL(path).openConnection();
            conn.setConnectTimeout(TIMEOUT_IN_MS);
            conn.setReadTimeout(TIMEOUT_IN_MS);
            retBytes = IOUtils.toByteArray(conn);
            result = new String(retBytes, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    public static void main(String[] args) {
        ZBParams handler = new ZBParams();
        try {

            //Params的相对路径(第一页)和编码格式
            String originRspContent = IO
                    .deserializeString("internetware/bnuser3/apis/00961-d2390d537d5949a0836b2be693694581-CQWZParams/SampleResponse_1","UTF-8");
            handler.processRspContent(
                    handler.checkRspContentState(originRspContent),
                    originRspContent);
            // System.out.println(originRspContent);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}

```



## 4. Combine
```
import java.io.IOException;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import cn.internetware.phone.extension.reqrsp.IwRequest;
import cn.internetware.phone.extension.reqrsp.IwResponse;
import cn.internetware.phone.extension.reqrsp.impl.DefaultIwResponse;
import cn.internetware.phone.extension.reqrsp.impl.TxtReqRspHandler;
import cn.internetware.phone.extension.response.RspState;
import cn.internetware.phone.extension.response.TxtRspObject;
import cn.internetware.phone.extension.response.impl.TxtBaseResponse;
import cn.internetware.utils.IO;
import cn.internetware.utils.Utils;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

public class ZBCombine extends TxtReqRspHandler {

   public class Response extends TxtBaseResponse {

        List<BranchNew> list = new ArrayList<BranchNew>();
        String pageCount;
        //String currentPage;
    }

    public class BranchNew {
        String title;
        String id;
        String date;
        String url;
    
        
        public String toString() {
            return "BranchNew[ title=" + title + ";id=" + id + ";date=" + date+ ";url=" + url+ "]";
        }

    }


    @Override
    protected RspState checkTxtRspContentState(String originTxtRspContent) {

        return RspState.Login;
    }



    protected TxtRspObject processTxtRspContent(RspState rspState,
            String originTxtRspContent) {
        Response response = new Response();
        if (rspState == RspState.Login) {
            Gson gson = new Gson();
            Response result = gson.fromJson(originTxtRspContent,
                    new TypeToken<Response>() {
                    }.getType());

            response.list = result.list;
            response.pageCount=result.pageCount;
            //response.currentPage = result.currentPage;
        }

        return response;
    }

    final static int TIMEOUT_IN_MS = 100000;

    //跳转path的方法
    private static String callApi(String path) {
        byte[] retBytes = new byte[0];
        try {
            URLConnection conn = new URL(path).openConnection();
            conn.setConnectTimeout(TIMEOUT_IN_MS);
            conn.setReadTimeout(TIMEOUT_IN_MS);
            retBytes = IOUtils.toByteArray(conn);

            return new String(retBytes, "UTF-8");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }


    @Override
      public IwResponse sendIwRequest(IwRequest iwRequest) { //iwRequest可以理解为api.xml文档的内容
        IwResponse iwRsp = null;   
        try {                   
            
            //获取Combine，api.xml里的"__EVENTARGUMENT"请求参数的值，页数
            String EVENTARGUMENT = iwRequest.getRequestContentParam("__EVENTARGUMENT").trim();
            //获取请求地址路径中，"categoryid"参数的值 (可以理解为获取Combine目录的api.xml参数categoryid的值)
            String categoryid=iwRequest.getRequestPathParam("categoryid").trim();
            
            //拼接成跳到Params页的URL，分析页面跳页URL是否只变化请求，内容是否会变(边的话可以考虑配正则)                      这里填写跳转到Params的映射
            String ahParamsUrl=getNewPathPrefix()+"/?"+getAdditionalLinkParamStr()+"&iw-cmd=01026_1ZJParams" 
                    +"&categoryid="+categoryid; // w_ir_2是List的api.xml 正则表达式(.*)的内容


            //使用Utils工具类的callApi方法，对上面拼接的URL进行跳转，并且将跳转页面的内容，保存在一个字符串变量ahParamsList中
            String ahParamsList=Utils.callApi(ahParamsUrl);
        
            //使用JSOH解析器，解析页面(ahParamsList的内容)，可以理解为解析List的api.xml
            JSONObject json=new JSONObject(ahParamsList);//保存解析内容
            JSONObject parms=(JSONObject) json.get("params");//取出解析内容中params>标签里的里的参数名和参数值

            //获得"__EVENTVALIDATION"和"__VIEWSTATE"两个请求参数的值
            String VIEWSTATE=parms.getString("__VIEWSTATE");
            String __EVENTVALIDATION=parms.getString("__EVENTVALIDATION");

            
            //拼接成地址，&后面传递参数，顺序随意 ，(注意EVENTTARGET请求参数的值可能不管哪一页都一样的，所以直接手动固定补上)     这里填写跳转到List的映射
            String jxCombine=getNewPathPrefix()+"/?"+getAdditionalLinkParamStr()+"&iw-cmd=01026_1ZJList"
                    +"&iw_ir_2=list"
                    +"&__VIEWSTATE="+VIEWSTATE
                    +"&__EVENTTARGET=ctl00$ContentPlaceHolder5$Pager"   
                    +"&__EVENTARGUMENT="+EVENTARGUMENT
                    +"&__EVENTVALIDATION="+__EVENTVALIDATION
                    +"&categoryid="+categoryid; 
                            //&iw_ir_2是正则表达式，需要补到List的api.xml中的(.*)的内容，CategoryNum=001001001
            
            
            //使用Utils工具类的callApi方法进行跳转操作，跳转之后的内容，保存到result中
            String result=Utils.callApi(jxCombine);
            
            //将result(跳转的内容)，进行转码，变为UTF-8
            iwRsp =new DefaultIwResponse(null,result.getBytes("UTF-8"),null,0,"ok");

                
        } catch (Exception e) {
            e.printStackTrace();
        }
        return iwRsp;

    }


    public String getJson(String Content, String ID) {
        String resultStr = "";
        try {
            if (Content.substring(0, 1) != "[") {
                Content = "[" + Content + "]";
            }

            JSONArray jsonArray = new JSONArray(Content);
            resultStr = jsonArray.getJSONObject(0).toString();

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return resultStr;
    }

}

```

---

### 配置List,Parms正则表达式
```

iw_ir_2=....

<request-path>/wzweb/jyxx/001001/001001001/MoreInfo.aspx</request-path>
    <params>
      <param>
        <name>CategoryNum</name>
        <value>001001001</value>
      </param>
    </params>
    <alternative-params>
      <params>
        <param>
          <name>CategoryNum</name>
          <value>001001001</value>
        </param>
      </params>
    </alternative-params>
    <request-uri-replacement-pattern><![CDATA[(/wzweb/jyxx/001001/)(.*)(/MoreInfo.aspx)]]></request-uri-replacement-pattern>
```

---


### Combine的api.xml要修改成
----------------------
```
<api-type>natural</api-type>
            变为
<api-type>artificial</api-type>
```

---


## 5. API文档格式
```
01026-江北区公共资源交易中心


数据来源：
原网站：http://www.jbggjy.cn/


入库编码（01026）
招标公告： 网址.....
中标公告：网址。。。。


招标公告：
列表（__EVENTARGUMENT为页数，从1开始）：

内容(infoid为id)：


中标公告：
列表（__EVENTARGUMENT为页数，从1开始）：

内容：(infoid为id)：

```






## 6. 2017.01.16 新增模板说明

整合将list，Detail转换成listCombine，DetailCombine

优点：
    1.可以通过URL自定义传参,设置全局变量【例如获取当前访问的URL】
    2.不用配置正则，通过代码转换跳页【page=1 转换为 index_1 】
    3.list本质就是1次callApi然后进行解析，现在listCombine自定义URL去callApi,高度解耦
    4.可本地测试，也可以开启代理测试
    5.自控制URL,可将入库代码的判断操作移植到接口代码里,保证入库代码的原始性


适用范围
    1.list,Detail能做的
    2.需要获取api.xml的内容进行解析
    3.Detial页链接不完整，附件链接需要当前动态URL

---
<br><br>


## 7. ListCombine
```
package DS00908;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import cn.internetware.phone.extension.reqrsp.IwRequest;
import cn.internetware.phone.extension.reqrsp.IwResponse;
import cn.internetware.phone.extension.reqrsp.impl.DefaultIwResponse;
import cn.internetware.phone.extension.reqrsp.impl.TxtReqRspHandler;
import cn.internetware.phone.extension.response.RspState;
import cn.internetware.phone.extension.response.TxtRspObject;
import cn.internetware.phone.extension.response.impl.TxtBaseResponse;
import cn.internetware.utils.IO;
import cn.internetware.utils.Utils;

public class DSListCombine extends TxtReqRspHandler {

    
    String type = null;  //类型

    
   //构建bean
   public class Response extends TxtBaseResponse {
        List<BranchNew> list = new ArrayList<BranchNew>();
        String pageCount;

    }

   //构建bean对象
    public class BranchNew {
        String title;
        String id;
        String date;
        String url;
    
        
        public String toString() {
            return "BranchNew[ title=" + title + ";id=" + id + ";date=" + date+ ";url=" + url+ "]";
        }

    }


    @Override
    protected RspState checkTxtRspContentState(String originTxtRspContent) {
        return RspState.Login;  //默认登录状态
    }


    /*第二步：解析页面，获取list*/
    protected TxtRspObject processTxtRspContent(RspState rspState,
            String originTxtRspContent) {
        Response response = new Response();

        if (rspState == RspState.Login) {
                try {
            
                    //A-处理文件内容
                        //A-1 补齐标签
                       
                        //A-2 转换为DOM节点
                        Document xmlDoc = Utils.getDocByContent(originTxtRspContent);
                    
                        
                    //B-获取标题(title),标识(id),链接地址(url),日期(data)
                        int count = 0;                                                          //计数器1
                        NodeList listFirst = xmlDoc.getElementsByTagName("div"); 
                        for (int i = 0, length = listFirst.getLength(); i < length; i++) { 
                            Element ele1 = (Element)listFirst.item(i);
                         
                            if("newsList".equals(ele1.getAttribute("class"))){  
                                if(++count != 1) continue;                                      //选择第n个符合条件的ele1
                                
                                NodeList listSecond = ele1.getElementsByTagName("li");    
                                if(listSecond == null || listSecond.getLength()<1 ){            //NodeList空判断
                                    continue;
                                }
                                
                                for(int j = 0,length2 = listSecond.getLength(); j < length2; j++){               
                                    Element ele2 = (Element)listSecond.item(j);
     
                                     //<1>条件判断
                                     if(ele2.getElementsByTagName("A").getLength() < 1){continue;}
                                        
                                     //<2>定位标签
                                     Element a_ele   = (Element)ele2.getElementsByTagName("A").item(0);             
                                     Element span_ele = (Element)ele2.getElementsByTagName("SPAN").item(0);     
                                        
                                     //<3>获取数据
                                     String title = a_ele.getAttribute("title").trim();
                                      
                                     String href = a_ele.getAttribute("href").trim();
                                     String id = type+"/"+href.substring(href.indexOf("./")+2,href.lastIndexOf(".html")).trim();
                                     
                                     String url = getNewPathPrefix()+"/?"+ getAdditionalLinkParamStr()+
                                                            "&iw-cmd=00908DSDetailCombine&id="+id;
                       
                                     String date = span_ele.getTextContent().trim();
                                            
                                      
                                     //<4>构造BranchNew对象
                                     BranchNew branchNew = new BranchNew();        
                                               branchNew.title = title;
                                               branchNew.date  = date;        
                                               branchNew.id    = id;            
                                               branchNew.url    = url;
                                     response.list.add(branchNew);
                                        
                                    //<5>测试输出
//                                  System.out.println(branchNew.toString());   
                                }
                            }
                        }
                       
                       
                  //C-获取总页数
                      int count2 = 0;                                                           //计数器2
                       String pageCount = null;                                                 //储存总页数
//                      
                        //<1>直接获取总页数
                        NodeList listThird = xmlDoc.getElementsByTagName("div");
                        if(listThird!=null){
                             for(int i = 0;i < listThird.getLength(); i++){
                                 Element ele3 = (Element)listThird.item(i);
                                    
                                 if("page".equals(ele3.getAttribute("class"))){
                                        if(++count2 != 1){continue;}                                //选择第n个符合条件的Element
                                        
                                        //获取总记录数计算pageCount
                                        pageCount = ele3.getTextContent().trim();
                                        pageCount = pageCount.substring(pageCount.indexOf("HTML(")+5,pageCount.indexOf(","));
                                 }
                             }
                             response.pageCount=pageCount;
                         }  
                          

                       if(response.pageCount==null) response.pageCount="1"; //获取不到默认为第1页

                       
                        //<3>测试输出总页数
//                      System.out.println(response.pageCount);
                    
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }


        return response;
    }


      /*第一步：处理URL*/
      @Override
      public IwResponse sendIwRequest(IwRequest iwRequest) {
         IwResponse iwRsp = null;  
         try {   
             //A-处理URL参数
                 this.type = iwRequest.getRequestPathParam("type").trim();
                 String page = iwRequest.getRequestPathParam("page").trim();
                 StringBuilder listPage = new StringBuilder();
                 if("1".equals(page) ) {
                     listPage.append("index");
                 }else{
                     listPage.append("index_"+(Integer.parseInt(page)-1));
                 }      
             
             //B-访问list链接
                String listURL = "http://ldggzy.hnloudi.gov.cn/jyxx/"+type+"/"+listPage.toString()+".html";
       
            //C-视情况是否转码
                String result=Utils.callApi(listURL);
                iwRsp =new DefaultIwResponse(null,result.getBytes("UTF-8"),null,0,"ok");
    
        } catch (Exception e) {
            e.printStackTrace();
        }
       
       return iwRsp;
      }
      
      //主方法:用于本地测试
      public static void main(String[] args) {
          DSListCombine handler = new DSListCombine();

          try {
              String originRspContent = IO
                      .deserializeString("internetware/DS00908/apis/00908DSListCombine/SampleResponse","utf-8");
              
              handler.processTxtRspContent(
                      handler.checkTxtRspContentState(originRspContent),
                      originRspContent);

          } catch (Exception e) {
              e.printStackTrace();
          }
      }
}

```

---
<br><br>


## 8. DetailCombine
```
package DS00908;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import cn.internetware.phone.extension.reqrsp.IwRequest;
import cn.internetware.phone.extension.reqrsp.IwResponse;
import cn.internetware.phone.extension.reqrsp.impl.DefaultIwResponse;
import cn.internetware.phone.extension.reqrsp.impl.TxtReqRspHandler;
import cn.internetware.phone.extension.response.RspState;
import cn.internetware.phone.extension.response.TxtRspObject;
import cn.internetware.phone.extension.response.impl.TxtBaseResponse;
import cn.internetware.utils.IO;
import cn.internetware.utils.Utils;

public class DSDetailCombine extends TxtReqRspHandler {

    
    String id = null;  //类型

    //构建请求
    public class Response extends TxtBaseResponse {
        String content;
    }

    @Override
    protected RspState checkTxtRspContentState(String originTxtRspContent) {
        return RspState.Login;  //默认登录状态
    }


    /*第二步：解析页面，获取list*/
    protected TxtRspObject processTxtRspContent(RspState rspState,
            String originTxtRspContent) {
        Response response = new Response();

        if (rspState == RspState.Login) {
              try {
            
                //A-处理文件内容
                    //A-1 补齐标签

                    //A-2 转换为DOM节点
                    Document xmlDoc = Utils.getDocByContent(originTxtRspContent);
                
                    //A-3 获取类型
                    String type ="";
                    if(id != null){
                        type=id.substring(0,id.indexOf("/t"));
                    }
                    
                //B-获取Detail页的内容
                    int count = 0;                                                      //计数器
                    String content = null;                                              //储存页面内容
                    NodeList listFist = xmlDoc.getElementsByTagName("div");
                    if(listFist != null){
                        for(int j = 0; j < listFist.getLength(); j++){
                            Element ele1 = (Element)listFist.item(j);
                            
                            if("fontzoom".equals(ele1.getAttribute("id"))){         //定位内容节点
                                if(++count != 1) continue;                              //获取第n个符合条件的ele1
                                
//                              //<1>删除不需要部分
                                    
                                
                                //<2>补全附件链接【存在就补全】
                                    NodeList a_list = ele1.getElementsByTagName("A");
                                     if(a_list.getLength()>0){
                                         for(int i=0;i<a_list.getLength();i++){
                           
                                             Element a_ele=(Element)a_list.item(i);
                                             StringBuilder href=new StringBuilder(a_ele.getAttribute("href"));
                                             
                                             if(href.indexOf("http")!=-1 || href.length()<5 || href.toString().contains("mailto")){
                                                 continue;
                                             }
        
                                             if(href.charAt(0) != '/'){
                                                  href.delete(0,1);  //删除.
                                                  href.insert(0, "http://ldggzy.hnloudi.gov.cn/jyxx/"+type);
                   
                                             }else if(href.charAt(0) == '/'){
                                                 href.delete(0,1);                              //删除开头'/'
                                                 href.insert(0,"http://ldggzy.hnloudi.gov.cn/jyxx/"+type); 
                                             }
                                             
                                             a_ele.setAttribute("href", href.toString());
                                         }
                                     }
                                
                                 //<3>补全图片链接【存在就补全】
                                     NodeList img_list=ele1.getElementsByTagName("IMG");
                                     if(img_list.getLength()>0){
                                         for(int i=0;i<img_list.getLength();i++){
                                             Element img_ele=(Element)img_list.item(i);
                                                  
                                             StringBuilder src=new StringBuilder(img_ele.getAttribute("src"));
                                             
                                             if(src.indexOf("http")!=-1  || src.length()<5){
                                                 continue;
                                             }
        
                                             if(src.charAt(0) != '/'){
                                                 src.delete(0,2);  //删除./
                                                src.insert(0,"http://ldggzy.hnloudi.gov.cn/jyxx/"+type);
                                             }else if(src.charAt(0) == '/'){
                                                 src.delete(0,1);                               //删除开头'/'
                                                 src.insert(0,"http://ldggzy.hnloudi.gov.cn/jyxx/"+type); 
                                             }
                                             
                                             img_ele.setAttribute("src", src.toString());
                                         }
                                     } 
                                 
                                 //<3>储存内容【将字符串转换成HTML结构】
                                     content=Utils.getNodeHtml(ele1);   
                                     break;                                                    //获取完后退出循环
                            }    
                        }
                    }
                     response.content=content;
                     
                     
                   //C-测试输出
//                   System.out.println(response.content);
               

            }catch (Exception e) {
                e.printStackTrace();
            }
        }
        return response;
    }


      /*第一步：处理URL*/
      @Override
      public IwResponse sendIwRequest(IwRequest iwRequest) {
         IwResponse iwRsp = null;  
         try {   
             //A-处理URL参数
                 this.id = iwRequest.getRequestPathParam("id").trim();

             
             //B-访问list链接
                String listURL = "http://ldggzy.hnloudi.gov.cn/jyxx/"+id+".html";
                
            //C-视情况是否转码
                String result=Utils.callApi(listURL);
                iwRsp =new DefaultIwResponse(null,result.getBytes("UTF-8"),null,0,"ok");
    
        } catch (Exception e) {
            e.printStackTrace();
        }
       
       return iwRsp;
      }
      
      //主方法：用于本地测试
      public static void main(String[] args) {
          DSDetailCombine handler = new DSDetailCombine();

          try {
              String originRspContent = IO
                      .deserializeString("internetware/DS00908/apis/00908DSDetailCombine/SampleResponse","utf-8");
              
              handler.processTxtRspContent(
                      handler.checkTxtRspContentState(originRspContent),
                      originRspContent);
        
          } catch (Exception e) {
              e.printStackTrace();
          }
      }
}


```

---
<br><br>