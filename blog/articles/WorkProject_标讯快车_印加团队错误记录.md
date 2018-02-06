---
title: 标讯快车_印加团队错误记录
date: 2016-8-13 11:45:43
tags: WorkProject
categories: WorkProject
---


一.目录
=================

目录共有 103 个错误与解决方案，记录小组开发种遇到的各项问题，整理总结，努力做的更好，We are the best！

1. 无法启动虚拟桌面
2. 导入燕云工程项目，新建API仓库的时候，提示重名
3. 判断Detail是否有附件
4. 判断Detail的附件链接需要补齐
5. reactor(代理)启动失败
6. List和Detail配置相对路径边和编码格式
7. 谷歌浏览器显示数据，无法跳页
8. Utils.getNodeHtml(Element)获取内容缺失
9. 启动代理后，谷歌浏览器显示页面内容重复
10. script>标签也可以抓取
11. 命名把"-"改为"_"
12. 项目运行配置可以只配一个testURL
13. Params类里不需要main方法
14. 虚拟桌面的Ecplise需要配置jdk
15. 如果Detail里的内容是一张图片
16. List和Detail配置正则表达式(健俊)
17. 本地平台抓包乱码
18. 启动reacotor,谷歌浏览器显示效果分层
19. 启动reactor之后，弹出窗口无法连接
20. 谷歌浏览器显示Combine页面，报错"Internal Server Error!"(健俊)
21. 抓取哪些页，哪些需要配正则
22. 启动reactor后，控制台报错空指针异常(陈宇)
23. 抓取的源文件，出现乱码
24. 日期格式不规范，自动补成yyyy-MM-dd
25. 在Combine里增加变量的过程
26. 报错：{"iw-response":{"errorTxt": the specified iw-cmd is invalid"}}
27. 记得要测试List(或者Combine)的URL和附件的URL
28. 源网页的源代码没有显示招标中标的信息
29. 正则只能配置request-path>标签的相对路径
30. String转Document方法(陈宇)
31. 正则可以用空括号
32. Combine记得配置对应Params和List的跳转映射
33. 启动代理后，谷歌浏览器乱码，控制台乱码
34. 遇到api.xml的request-path>标签不能匹配中标和招标等类型
35. 招标公告的Detail有内容，招标答疑的Detail为null
36. 如何把int类型变为String类型
37. 招标的Detail无法匹配中标的Detail
38. 入库错误整理
39. 注意，我们做的项目是的0630的Excel表格
40. 每个Java类里，多余的包要删掉(就是没有用到的包)
41. 入库里，跳转Detail的链接，端口要用1627
42. List页面显示pageCount不能有空格
43. 入库代码里的注解，是唯一标识，不能重名！
44. 入库的数据只要2014年之后的
45. 入库的页数第一页是index，第二页是index_2，入库代码
46. 遇到附件下载链接a标签只有id属性，没有value属性
47. 尝试运用ArrayList
48. 入库代码，页数倒数模版(瑞强)
49. 有些网站的尾页是没有pageCount显示的，但是确实是尾页了
50. 今天碰到编号-1，编号-2，编号-3，的招标用一个模版，中标用一个模版
51. 入库的时候，如果报错，只提示"接口错误"(印加暑期群)
52. 如果入库中个出错，纠错后，续接记录
53. 如果截取总页数，用到字符串截取，条件里有中文
54. 数据库跳空语句
55. List无法按照规定格式显示
56. 使用Combine拼接两个Detail内容(附件和主内容不在同一个api)
57. Combine使用for循环，反复访问
   57-2. 提取JSON格式内容
58. 删除节点
59. Detail通过JS获取动态的附件链接
60. 附件链接中出现(&amp;符号)，导致无法下载
61. 截取的内容只有tr>标签，无法获取tr的Element，需要补全table>再转换为Doment
62. 删除title里的\ (斜杠)
63. 跳类型的话，没有类型参数的话，Params也记得配置正则
64. 判断链接开头
65. 去除Detail页面尾部的图片(每个Detail页都有，并且位置固定是最后)
66. 去除title标签内的不需要内容(加远)
67. 使用NodeList来定位Id属性
68. 编码格式问题转码，解码
69. 去除title前面[]内的内容(小强)
70. Detail里面只有一个链接，链接到另一个页面
71. 入库代码如果是空白页自动跳过(原网站一片的某个Detail页面一片空白)
72. 入库小发现
73. Detail补齐图片，和附件链接通用版
74. ecplise--运行java类快捷键
75. 遇到网站里List内容是[&#xxxxx];编码格式的 (健俊)
76. 增加变量
77. removeChild()方法删除子节点
78. 获取当前年份
79. URL入库，主要是用ListVO_4
80. 入库代码开头要注解清晰
81. URL地址栏,开启代理后
82. Detail页里面有音频
83. URL转码，List和Detail页面内容编码特殊
84. List页面抓包下来全是字符串[并且是83的乱码]
85. Combine增加参数,另一种(健俊)
86. iw_ir_2的理解(健俊)
87. 计数判断，选出符合条件的某个Element
88. 在Combine拼接跳转List的URL，因为多了参数，导致无法跳页(加远)
89. 设置执行SQL命名后，查询界面显示的记录数
90. Eclipse设置不限制控制台输出
91. 在Eclipse中点代码,包资源管理器自动跳动到该文件
92. List里添加URL跳转到Detail页面
93. 招标中标的信息在网站同一个页面，不同模块
94. 终极暴力Detail抓date(健俊)
95. 启动reactor后，报错"创建临时文件夹失败"
96. URL中文编码问题--URL里面包含中文，只有特定格式才能跳入网站
97. 查询数据库相关语句扩展
98. List页面是特殊格式，用字符串进行截取
99. List和Detail无法拿到日期，入库里使用new Date() [健俊]
100. 将时Unix时间戳转换为yyyy-MM-dd格式的日期
101. 谷歌浏览器的版本和燕云dds兼容问题
102. 燕云dds的eclise无法无法抓包and开启代理
103. JSON格式数据解析【使用GSON】





1.无法启动虚拟桌面
------------------------------
　　如果插件下载安装完之后，点击后没有弹出窗口，也没有报错，
那么找到[我的电脑--->下载](这是谷歌的默认下载路径)
　　
目录，里面的ica文件，右键设置他们的默认打开方式
　　`C:\Program Files (x86)\Citrix\ICA Client\wfcrun32.exe`

打开方式就会变成Citrix Connection Manager




2.导入燕云工程项目，新建API仓库的时候，提示重名
-------------------
　　1.改为导入已有燕云Dass工程
　　2.在10号机上删除API工程，然后再次新建




3.判断Detail是否有附件
-----------------------------
　　启动Plant工具，抓取网站，多点击几个Detail，查看是否有附件,
　　优先对有附件的Detail页面进行API接口编写


4.判断Detail的附件链接需要补齐
----------------------------
　　因为大部分抓取的网站的Detail，
　　里面所包含的附件的下载链接是相对路径的，所以需要我们手动补齐
　　
　　并且要测试是否点击能够得到下载链接




5.reactor(代理)启动失败
------------
1. reactor启动失败 原因是修改了运行配置里API调用路径上的文件夹名字 ，删除project.xml的方法在配置了大量API的时候太麻烦 
  - 解决方法1：手动修改project.xml里对应的路径；
  - 解决方法2：在运行配置里将影响到的API重新选择一遍后保存； (陈宇)

2. 选中项目工程，删除根目录下的projext.xml文件
    重新右键项目--------->燕云DaaS----->项目运行配置
    　　再次进行配置！！
3. 还有一个原因是抓的包数据有问题，重新抓包然后把东西改一下，就可以启动(健俊)


6.List和Detail配置相对路径边和编码格式
-------------------
　　List和Detail页面都需要指定数据源的相对路径,
　　编码格式，应该符合数据目录，的api.xml里的编码格式


7.谷歌浏览器显示数据，无法跳页
-------------------------
　　1.去List或者Detail的数据源目录里，查看api.xml文档，看是否有跳页参数，如果没有，则需要配置正则表达式

```

举例:
http://www.jianggan.gov.cn/xzsp/channels/2792_2.html(第二页，无跳页参数)

<request-path>/xzsp/channels/2792_2.html</request-path>
<request-uri-replacement-pattern>![CDATA[(/xzsp/channels/2792_)(.*)(.html)]]</request-uri-replacement-pattern>


然后跳页的时候，用&iw_ir_2=n进行跳页 
```

>注意：
>>request-uri-replacement-pattern>标签内不能有换行，或者有空格，否则会出现跳不了页的情况。




8.Utils.getNodeHtml(Element)获取内容缺失
------------------------------
使用Utils工具类的，getNodeHtml(Element)方法，输出Element元素的所有子节点，包括hmtl标签

获取内容缺失，目前无法解决




9.启动代理后，谷歌浏览器显示页面内容重复
----------------------------
　　查看ZBList类，里面是获取到招标公告的title id date后，
　　记得在循环中创建对象，在把三个值存进BranchNew类的属性里




10.script>标签也可以抓取
-----------------------------
　　依然通过 
```
Document xmlDoc = Utils.getDocByContent(originTxtRspContent);
                
                //获取整个页面的script节点的集合
                NodeList div_list = (NodeList) xmlDoc.getElementsByTagName("script");
```




11.命名把"-"改为"_"
----------------------------------
```
举例
　　01014_1_64d65467e515493d885ff25dab58a61f_ZJList
　　01014_1_2010e378ec9144bf9f0f199c70df8e2a_ZJDetail
　　01014_1ZJDetail
　　01014_1ZJList
```



12.项目运行配置可以只配一个testURL
-----------------------------
　　项目中有很多包，但是只用配置一个包的testURL，
　　这样启动代理后，弹窗不会弹出，太多窗口的testURL,
　　测试的话，再根据需要进行手动修改地址




13.Params类里不需要main方法
-----------------------------
　　模版里的main方法，只是测试用，打印输出看效果；
　　真正用到项目里，不需要main(主)方法。




14.虚拟桌面的Ecplise需要配置jdk
-------------------------------------------
```
Ecplise顶部
	------>Window
	------>Java
	----->Installed JARS
		----->jdk目录的路径(例如：C:\Program Files\Java\jdk1.8.0_65)
```




15.如果Detail里的内容是一张图片
---------------------------------------
　　直接获取图片的源代码，要记得判断里面的a>标签的链接是不是绝对路径
　　如果是相对路径需要补齐


16.List和Detail配置正则表达式(健俊)
-----------------------------------------
案例:
```
List的api.xml配置：
    <request-path>/xzsp/channels/2792_2.html</request-path>
    <request-uri-replacement-pattern><![CDATA[(/wzweb/jyxx/001001/)(.*)(/MoreInfo.aspx)]]></request-uri-replacement-pattern>

Detail的api.xml配置:
<request-path>/xzsp/cotents/2792/7757.html</request-path>
    <request-uri-replacement-pattern>[<![CDATA[(/wzweb/jyxx/001001/)(.*)(/MoreInfo.aspx)]]></request-uri-replacement-pattern>

杭州市江干区公共资源交易中心
http://www.jianggan.gov.cn/

招标公告:     
List页：  http://127.0.0.1:1627/bnuser2/?iw-apikey=123&iw-cmd=01002ZJZBList&iw_ir_2=2792_2  
          跳页参数:iw_ir_2=2792_2
Detail页: http://127.0.0.1:1627/bnuser2/?iw-apikey=123&iw-cmd=01002ZJZBDetail&iw_ir_2=2792/7757
          跳页参数:iw_ir_2=2792/7757

中标公告:     
List页:   http://127.0.0.1:1627/bnuser2/?iw-apikey=123&iw-cmd=01002ZJZBList&iw_ir_2=2793_2
      跳页参数:iw_ir_2=2793_2
Detail页: http://127.0.0.1:1627/bnuser2/?iw-apikey=123&iw-cmd=01002ZJZBDetail&iw_ir_2=2793/7778
      跳页参数:iw_ir_2=2793/7778

```



17.本地平台抓包乱码
------------------------
抓包之后搜索不成功是因为抓下来之后乱码了，只需选择一个sampleResponse右键Properties（就是最后一项），在Test file encoding选择Deault就行




18.启动reacotr,谷歌浏览器显示效果分层
-------------------------------------
　　上面是文字，链接全部跑到下面，无法一个公告对应一个链接

　　可能原因：项目运行配置有错误，(包名.类名)出错

解决方案：
　　1.将抓取数据源目录名子，里面api文档的id>和alias-name>中的"-"改为"_"
　　　　举例
```
000665_b785fa7e99c846eba15cdcb2de3d6c48_YNDetail
<id>000665_b785fa7e99c846eba15cdcb2de3d6c48_YNDetail</id>
<alias-name>000665YNDetail</alias-name>
```
　　2.重新项目运行配置


19.启动reactor之后，弹出窗口无法连接
------------------------
　　选中工程------>启动reactor
　　会自动弹出一个窗口，这个窗口不能上外网的，所以要另外在桌面打开谷歌浏览器，
　　输入测试网址，进行测试



20.谷歌浏览器显示Combine页面，报错"Internal Server Error!"(健俊)
----------------------------

解决方案:
　　抓取的数据目录中例如：00961-e9318c6b14f4489597a3153e20afa9b2-CQWZCombine
　　　　　　里的api.xml
　　　　　　最下面的`<api-type>natural</api-type>`   
里的natural值，改为artificial


第二种情况：

　　　　 Combine里面  跳到List后，拼接的result不是JSON格式的内容


第三种情况
　　　　多回车一下就好了


第四种情况
跳到List页面的时候内容不全
```
 String ahParamsList=Utils.callApi(ahParamsUrl);
             a:for(;null == ahParamsList;){
                 ahParamsList=Utils.callApi(ahParamsUrl);
                 if(null != ahParamsList){
                     break a;
                 }
             }     

```





21.抓取哪些页，哪些需要配正则
-----------------------
1.Params抓取第1页 List抓取第二页  Detail抓取某个公告里的内容(优先抓取有附件的)  Combine复制List
2.List,Params要配置正则   Detail看情况   Combine要改底部标签的natural改为artificial



22.启动reactor后，控制台报错空指针异常(陈宇)
-------------------------
　　在需要做Combine的网站，如果项目文件配置的testURL直接指向List页面，就会报错


解决方案：
　　指向Combiine页面

23.抓取的源文件，出现乱码
--------
```
右键文件----->properties------>Resource

里面修改编码格式，有可能是UTF-8 GBK
```



24.日期格式不规范，自动补成yyyy-MM-dd
------------------------
1.
```
举例：
2016-06-02    变为2016-06-02


String old=2016-6-2;
Date now = (Date) new SimpleDateFormat("yyyy-MM-dd").parse(old); 
String date = new SimpleDateFormat("yyyy-MM-dd").format(now);


如果日期中包含时，分，例如： 2016-7-5 15:58
String date=span_ele.getTextContent().trim();
Date now = (Date) new SimpleDateFormat("yyyy-MM-dd hh:mm").parse(date); 
date = new SimpleDateFormat("yyyy-MM-dd").format(now);


```




2.(瑞强)
```
String d=td_ele2.getTextContent();
                            String date1=d.substring(0,d.indexOf(" "));
                            String date2 =date1.replace('/', '-');
                            Date now = (Date) new SimpleDateFormat("yyyy-MM-dd").parse(date2); 
                             String date3 = new SimpleDateFormat("yyyy-MM-dd").format(now);
                             branchNew.date=date3;


```






25.在Combine里增加变量的过程
---------------------
```
举例：
<request-path>/wzweb/jyxx/001001/001001001/MoreInfo.aspx</request-path>
    <params>
      <param>
        <name>CategoryNum</name>
        <value>001001001</value>
      </param>
       <param>
        <name>Type</name>
        <value>001001001</value>
      </param>
    </params>
    <request-uri-replacement-pattern></request-uri-replacement-pattern>


/////////再Combine的api.xml中增加type变量，value为初始值。


然后在Combine的java文件中获取值，

String type=iwRequest.getRequestPathParm("Type").trim();

```

>注意:需要的时候，只能在Combine里增加变量，不能再List,Detail,Params里增加





26.报错：{"iw-response":{"errorTxt": the specified iw-cmd is invalid"}}
---------------------

原因：配置文件和api.xml文件出错导致的

解决方案：

1.查看api.xml里的params>标签有没有结束标签，如果缺失就补上



27.记得要测试List(或者Combine)的URL和附件的URL
--------------------------

直接将地址复制到浏览器中，看能否跳到Detail页

附件的话，把补齐的地址，在本地输出，然后再复制到浏览器中，看能否直接跳出下载链接





28.源网页的源代码没有显示招标中标的信息
--------------------------------

解决方案:

虽然没有显示，但是抓包工具依然能抓到，

所以搜索api的时候
1.查找招标公告里的关键字符(如果抓取的数据是乱码的话不能搜中文)
2.把默认两个选项<大小写敏感><全字匹配>去掉




29.正则只能配置request-path>标签的相对路径
--------------------------------

看到网页，先别急着作，先分析，
1.List跳页，跳入Detail是否有参数(可以观察网页URL，也可以观察api.xml)
2.还是相对路径直接变化(这种需要配正则)


正则格式通用格式
`
    <![CDATA[(/view/)(.*)(.html)]]>




30.String转Document方法(陈宇)
---------------------------

1.
```
String转Document方法（JSON格式取出html字符串的时候使用）：
String a = “<p></p>” // html格式的字符串
String b = a.replace("&","&tmp;")
String c = html头部+b+html尾部；
StringReader stringReader  =  new StringReader(c);
InputSource  inputSource  =  new 
InputSource(stringReader);
DocumentBuilderFactory domfac=
DocumentBuilderFactory.newInstance();
DocumentBuilder  documentBuilder  =  domfac.newDocumentBuilder();   
Document  document  =  documentBuilder.parse(inputSource );

```

2.使用公司的Utils工具类的getDocByContent(String a)方法
```
String originTxtRspContent;
 //获取页面所有节点
Document xmlDoc=Utils.getDocByContent(originTxtRspContent);
```





31.正则可以用空括号
---------------------------
例如：
```
  <![CDATA[(/view/)(.*)(.html)]]>
```




32.Combine记得配置对应Params和List的跳转映射
--------------------

1.URL跳转到Params      举例：`&iw-cmd=01025_1ZJParams`

2.URL跳转到List     举例：`&iw-cmd=01025_1ZJList



33.启动代理后，谷歌浏览器乱码，控制台乱码
------------------------------

解决方案：

观察api.xml，把编码格式统一到Combine(最下面)，Params，List，Detail





34.遇到api.xml的request-path>标签不能匹配中标和招标等类型
------------------------------------

一，第一种情况
```
招标中标的类型变化在api.xml里的
<header>
        <name>Referer</name>
        <value>http://www.nbjdjy.cn/trade/gongcheng/zhaobiao/</value>
</header>
而不是在
 <request-path>/api/listapi.asp</request-path>中
```

解决方案:
1.每个网站抓一次包，写一个Detail和List (中标和招标当作两个网站)


二，第二种情况
```
<header>
        <name>Referer</name>
        <value>http://paztb.panan.gov.cn/jsxmjy/zbgg/</value>
</header>

<request-path>/jsxmjy/zbgg/index_1.shtml</request-path>
<request-uri-replacement-pattern><![CDATA[(/jsxmjy/)(.*)()]]></request-uri-replacement-pattern>
```
解决方案：
1.如果有招标，中标，答疑惑等网站，那么只需要抓一次包(List,和Detail)，然后复制n次，修改正则，举例：`<request-path>/jsxmjy/zbgs/index_1.shtml</request-path>`  zbgg改为了zbgs





35.招标公告的Detail有内容，招标答疑的Detail为null
------------------------------------------------

解决方案：

分析原网站招标答疑的Detail，看是不是是和招标公告的Detail的排版，模块不同
如果不同，要针对招标答疑写代码，Detail里加入if语句判断，当遇到招标答疑的网站时候，用针对性代码


36.如何把int类型变为String类型
---------------------
```

举例：长度直接等于总页数
  NodeList option_list=select.getElementsByTagName("option");
               if(option_list!=null){
                   response.pageCount=String.valueOf(option_list.getLength());
               }
```



37.招标的Detail无法匹配中标的Detail
---------------------------------

解决方案:
在Detail写两个模版，并且加入判断

当跳入Detail页面的时候，先执行第一个模版，如果内容不为空，则直接输出，保存到response.content里

如果经过第一个模版后，内容任然为空，则执行第二个模版，然后输出，保存到response.content中；



38.入库错误整理
--------------------------------
将群里的整理下

1.ORA-12154: TNS:无法解析指定的连接标识符。
```
　　 解决方案：
　　 仔细检查tnsnames.ora发现添加的配置文件 ：
bxkc_test =
  (DESCRIPTION =
    (ADDRESS_LIST =
      (ADDRESS = (PROTOCOL = TCP)(HOST = 172.26.19.6)(PORT = 1521))
    )
    (CONNECT_DATA =
      (SID = orcl)
    )
  )

把back_test前面的空格去掉,在TAM前有空格，去除空格后，重新保存，一切OK。
```





39.注意，我们做的项目是的0630的Excel表格
---------------------------
`网站抓取数据源基本信息表（区县）0630`




40.每个Java类里，多余的包要删掉(就是没有用到的包)
-----------------------------

特别是这个包
`import org.apche.tools.and taskdefs.Length`,删掉

带黄色波浪线没有使用过的包都可以删掉。


快捷键：ctrl+shift+o,优化导包





41.入库里，跳转Detail的链接，端口要用1627
-----------------------------------------





42.List页面显示pageCount不能有空格
------------------------------------

" 32"   ~~~~》错误格式
"32"    ~~~~>正确格式




43.入库代码里的注解，是唯一标识，不能重名！
-------------------------------------------

如果重名，不仅不能入库，而且还会报错，BackupTask.java也无法运行




44.入库的数据只要2014年之后的
----------------------------
入库的数据只要2014年之后的，其他全部不要

所以，如果网页中只有一条是2014年之后的数据，那么只会入一条




45.入库的页数第一页是index，第二页是index_2，入库代码
-------------------------------
```
package com.bonait.dataextract.service.impl;
import javax.annotation.Resource;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;


import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Service;

import com.bonait.dataextract.dao.BaseDao;
import com.bonait.dataextract.domain.*;
import com.bonait.dataextract.service.*;
import com.bonait.dataextract.util.Util;
import com.bonait.dataextract.vo.*;


/**
 * @程序猿：刘淑玮
 * 
 * @内容：东软一期-建设工程-招标公告-01023-1
 */
@Service("zhejiang_01023_1_ZhaobGgService")
public class Zhejiang_01023_1_ZhaobGgService implements ZhaobGgService{

    @Resource
    private SessionFactory sf ;
    private Query query;
    private ZhaoBiaoGongGao zbgg;
    //列表页接口地址
    private String listUrl="http://127.0.0.1:1627/bnuser_3/?iw-apikey=123&iw-cmd=01023_1ZJList&iw_ir_2=gcjs/zbgg/index";
    //内容页接口地址
    private String detailUrl="http://127.0.0.1:61733/?iw-apikey=123&iw-cmd=01023_1ZJDetail&iw_ir_2=";
//  http://127.0.0.1:51300/?iw-apikey=123&iw-cmd=01023_1ZJDetail&iw_ir_2=gcjs/zbgg/2016_7_1/260084
    //入库编码
    private String sourceNo="01023-1";
    @Override
    public void initZhaobGgService() {
        try{
        String s=Util.sendGet(listUrl,"");
        JSONObject obj =JSONObject.fromObject(s);
        while (null == obj.get("pageCount")){
            s = Util.sendGet(listUrl, "");
            obj = JSONObject.fromObject(s);
        }
//      if(obj.getString("list").length()<10){System.out.println(sourceNo+"列表为空，请检查一下接口。");return;}
        int maxPage=Integer.parseInt(obj.get("pageCount").toString());
//      int maxPage=714;
        Session session=sf.openSession();
        session.beginTransaction();
        //清空数据库里
//      query=session.createSQLQuery("DELETE FROM t_zhao_biao_gong_gao t where t.WEB_SOURCE_NO='"+sourceNo+"'");
//      query.executeUpdate();
//      session.getTransaction().commit();
//      session.beginTransaction();
                                
        int KK=0;
            flag:                   
            for(int i=1;i<=maxPage;i++){        //maxPage是总页数，测试的时候改为3页
                String jsonList =null;
                if(i==1){
                    jsonList=Util.sendGet(listUrl,"");
                }else{
                    jsonList=Util.sendGet(listUrl,"_"+i+"");
                }
                 
                JSONObject objList =JSONObject.fromObject(jsonList);
                while(null == objList.get("list")){
                    if(i==1){
                        jsonList=Util.sendGet(listUrl,"");
                    }else{
                        jsonList=Util.sendGet(listUrl,"_"+i+"");
                    }
                    objList = JSONObject.fromObject(jsonList);
                }
                JSONArray temp=(JSONArray) objList.get("list");
                List list = (List)JSONArray.toList(temp,ListVO.class);
                Iterator it = list.iterator();
                int rows = 1;
                for(int row =1;row < rows;row++){
                    it.next();
                }
                while(it.hasNext()){
                    ListVO listvo =(ListVO) it.next();
                    query=session.createSQLQuery("SELECT ID FROM t_zhao_biao_gong_gao t where t.WEB_SOURCE_NO='"+sourceNo+"' and t.RECORD_ID='"+listvo.getId()+"'");
                    String getDate=listvo.getDate();
                    //获取页数，便于插入数据异常时，先跳过该页
                    System.out.println(i+":"+getDate+":"+maxPage);
                    
                    int date = Integer.parseInt(getDate.substring(0, 4));
                    //只取2014年至今的不重复数据   
                    if(query.executeUpdate()<=0&&date>=2014){
                        KK+=1;
                        String detail = null;
                        JSONObject o = null;
                        String content = null;
                        while (content == null || content.length() < 10) {
                            detail = Util.sendGet(detailUrl, listvo.getId());
                            if (null != detail)
                                o = JSONObject.fromObject(detail);
                            else
                                continue;
                            if (null != o&&null != o.get("content"))
                                content = o.get("content").toString();
                            else
                                continue;
                            System.out.println("runing..");
                        }
                        //过滤掉无效数据
                        if(content==null||content.length()<10||content.contains("出错")||content.contains("找不到文件")){continue;}
                        zbgg=new ZhaoBiaoGongGao();
                        zbgg.setWebSourceNo(sourceNo);
                        zbgg.setArea("华东");//区域
                        zbgg.setProvince("浙江");//省份
                        zbgg.setCity("宁波");//城市，没有可不填
                        zbgg.setDistrict("国家高新");// 区县 不要“区”字符
//                      zbgg.setCity(listvo.getCiy());
                        zbgg.setWebSourceName("宁波国家高新区（新材料科技城）公共资源交易中心");//网站名称，写完整的名称
                        zbgg.setInfoSource("工程建设");//信息来源
                        //未匹配到工程或服务的，都标识为货物
                        String infoType=Util.getInfoType(content);
                        if(infoType!=null&&infoType.length()>0)
                        zbgg.setInfoType(Util.getInfoType(content));
                        else {zbgg.setInfoType("货物");}
                        zbgg.setInfoType("工程");//信息类型
//                      zbgg.setIndustry("建筑建材"); //行业分类
//                      zbgg.setIndustry(Util.getIndustry(content));//行业分类
                        zbgg.setRecordId(listvo.getId());
                        zbgg.setId(UUID.randomUUID().toString());
                        zbgg.setPageTitle(listvo.getTitle());
                        zbgg.setPageTime(getDate);
                        zbgg.setPageContent(content);
                        zbgg.setPageAttachments("");//附件url，暂不需要
                        zbgg.setCreateTime(new Date());
                        session.save(zbgg);
                     }else if(date<=2013){break flag;}//到2013年的页码时，断掉循环
                    if(KK%1==0){
                        session.flush();  
                        session.clear();
                        session.getTransaction().commit();
                        session.beginTransaction();
                    }
                }  
            }   

            
        session.getTransaction().commit();
        session.close();
        System.out.println("000000000");
        
        } catch (Exception e) {
            System.out.println(sourceNo+"接口出错，请检查");
        }
    }

}

```






46.遇到附件下载链接a标签只有id属性，没有value属性
-------------------------------------

思路，先在页面中截取js的传参值，再分析附件下载链接的组成，然后用Utils.allApi跳到页面获取动态值，再进行字符串拼接，设置属性


```
重点方法：Utils.callApi(url),跳到url地址中，并且把里面的内容保存到字符串中


截取参数值cid,i2

    // 扫描里面所有a标签，补全所有
                                NodeList a_list = table_ele.getElementsByTagName("a");
                                if (a_list != null) {
                                    for (int k = 0; k < a_list.getLength(); k++) {
                                        Element a_ele = (Element) a_list.item(k);//遍历每个A标签
                                        String href = null;
                                        // Detail附件的链接用JS拼接后，跳到一个页面，获取参数，并截取需要的参数值，再次拼接
                                        String aUrl = "http://www.zjtxztb.gov.cn/txcms/attachment_url.jspx?&cid="+cid+"&n="+i2;
                                            String ahParams = Utils.callApi(aUrl);//跳到aUrl的路径，并且获取里面的所有内容，保在字符串里
                                            // 截取动态参数(该参数会实时变化)

                                            //对取得参数进行拼接
                                            String parameter = ahParams.substring(ahParams.indexOf("[")+2,ahParams.lastIndexOf("]")-1);                                       
                                            href = "http://www.zjtxztb.gov.cn/txcms/attachment.jspx?cid="+ cid+ "&i=0"+ parameter;
                
                                        
                                        //给a标签设置href属性
                                        a_ele.setAttribute("href", href);  
                                        adjunctContent = Utils.getNodeHtml(table_ele).trim();
                                        //将字符里面所有的&amp;都变为&
                                        adjunctContent = adjunctContent.replaceAll("&amp;", "&");
                                    }
                                }

```



47.尝试运用ArrayList
----------------------------
```
    //获取title，id，date,url      
              Element dl_ele=(Element)xmlDoc.getElementsByTagName("dl").item(0);
               
              //保存a标签和dd标签的List集合类
              List <Element> a_ArrayList=new ArrayList<Element>();
              List <Element> dd_ArrayList=new ArrayList<Element>();
              
              NodeList a_list= dl_ele.getElementsByTagName("a");
              if(a_list!=null){
                  for(int i=0;i<a_list.getLength();i++){
                      Element a_ele=(Element)a_list.item(i);
                      a_ArrayList.add(a_ele);
                  }
              }
              NodeList dd_list=dl_ele.getElementsByTagName("dd");
              if(a_list!=null){
                  for(int i=0;i<dd_list.getLength();i++){
                      Element dd_ele=(Element)dd_list.item(i);
                      dd_ArrayList.add(dd_ele);
                  }
              }
              
              for(int i=0;i<a_list.getLength();i++){
                  Element a_ele=a_ArrayList.get(i);
                  Element dd_ele=dd_ArrayList.get(i);
                  
                String title=a_ele.getTextContent().trim();
                String date =dd_ele.getTextContent().trim();
                
                
                String href=a_ele.getAttribute("href");
                String id=href.substring(href.indexOf("=")+1);
            
                 //拼接URL
                  String url=getNewPathPrefix()+"/?"+
                                         getAdditionalLinkParamStr()+
                                         "&iw-cmd=01043ZJDetail&id"+id;
 
                             
                 //创建对象，赋值            
                             BranchNew branchNew =new BranchNew();
                             branchNew.title=title;
                             branchNew.id=id;
                             branchNew.date=date;
                             branchNew.url=url;
                            
                 //将对象保存到response的list列表中，打印输出
                             response.list.add(branchNew);
                             System.out.println(branchNew.toString());
  
              }
             
```





48.入库代码，页数倒数模版(瑞强)
-----------------------------------
```
int KK=0;
        flag:
        for(int i=maxPage;i>=maxPage-2;i--){
            String jsonList=Util.sendGet(listUrl,i+"");
            if(i==maxPage){
                jsonList=Util.sendGet(listUrl,"");
            }else{
                jsonList=Util.sendGet(listUrl,"_"+i+"");
            }
             
            JSONObject objList =JSONObject.fromObject(jsonList);
            while(null == objList.get("list")){
                if(i==maxPage){
                    jsonList=Util.sendGet(listUrl,"");
                }else{
                    jsonList=Util.sendGet(listUrl,"_"+i+"");
                }
                objList = JSONObject.fromObject(jsonList);
            }
            JSONArray temp=(JSONArray) objList.get("list");
            List list = (List)JSONArray.toList(temp,ListVO.class);
            Iterator it = list.iterator();
            int rows = 1;
            for(int row =1;row < rows;row++){
                it.next();
            }

```






49.有些网站的尾页是没有pageCount显示的，但是确实是尾页了
-------------------------------------

只要不影响入库，那就没影响



50.今天碰到编号-1，编号-2，编号-3，的招标用一个模版，中标用一个模版
-----------------------------------------

解决方案：
招标抓一次包，中标抓一次包

正则也可试试，比较麻烦，未成功





51.入库的时候，如果报错，只提示"接口错误"(印加暑期群)
------------------------------

可以在
```
try{
    
}catch(Exception e){
    e.printStackTrace(); //加入这个，输出报错信息
}

```





52.如果入库中个出错，纠错后，续接记录
-----------------------

手动设置从哪一页，哪一行重新开始，先将那一页数据接完，再修改会原来的
```

x页有错，y行有错

原本
for(int i=1;i<=maxPage;i++){
    

    ...

    int row=1;
    for(int row=1;row<rows;row++){
        it.next();
    }
}


可改为
for(int i=x;i==x;i++){          //锁定某页,x报错页
    
    ...

    int rows=y+1;          //从报错的下一行开始
    for(int row=1;row<rows;row++){
        it.next();
    }
}


//将那一页录完后，再改回原来的，接后面的页

```





53.如果截取总页数，用到字符串截取，条件里有中文
---------------------------------

出现问题：
本地可以，开了代理不行，提示数组下标异常


可能原因：
1.java类的格式和开代理后页面的编码格式不同，用中文的时候出现乱码，所以搜索无法判断

54.数据库跳空语句
-------------------------------
```
举例1：
    if(listvo.getId().equals("art/2016/6/20/art_3439_99350") || listvo.getId().equals("art/2016/6/20/art_3439_99349") || listvo.getId().equals("art/2016/6/20/art_3439_99348")|| listvo.getId().equals("art/2016/6/20/art_3439_99347")){
                        continue;
                    }

举例：2
 if(listvo.getId().equals("zbgs/9930")){
                        continue;
 }

```



55.List无法按照规定格式显示
-------------------
将List的api.xml里的
```
若原本是OTHER改为TXT

  <content-type>TXT</content-type>

```





56.使用Combine拼接两个Detail内容(附件和主内容不在同一个api)
----------------------------

注意：Utils.callApi获取的内容是到JSON格式，如果需要里面的内容，需要提取出来

而拼接两个Detail，就是要把两个都是JSON格式的内容个拼接起来，然后合成1个JSON格式内容(例如，不能有两个{"content: ..."})


```
 public IwResponse sendIwRequest(IwRequest iwRequest) { 
        IwResponse iwRsp = null;   
        try {               //iwRequest可以理解为api.xml文档的内容
            

            //获取ID
            String Id=iwRequest.getRequestPathParam("Id").trim();
            
            //获取Detail1内容
            String ahDetailUrl_1=getNewPathPrefix()+"/?"+getAdditionalLinkParamStr()+"&iw-cmd=01025ZJDetail22"
                    +"&Id="+Id;
            String detail1Content1=Utils.callApi(ahDetailUrl_1);
            a:for(;null == detail1Content1;){
                detail1Content1=Utils.callApi(ahDetailUrl_1);
                if(null != detail1Content1){
                    break a;
                }
            }
//            detail1Content1 = detail1Content1.replace("{", "[{").replace("}", "}]");
//            JSONArray JSO_content1=new JSONArray(detail1Content1);
//            JSONObject  content1Json = JSO_content1.getJSONObject(0);
//            String  content1Main=content1Json.getString("content");      
//            System.out.println("=============content1Main--------->\n"+content1Main);

            //获取Detail2内容
            String ahDetailUrl_2=getNewPathPrefix()+"/?"+getAdditionalLinkParamStr()+"&iw-cmd=01025ZJDetail2"
                    +"&Id="+Id; 
            String detail1Content2=Utils.callApi(ahDetailUrl_2); 
            a:for(;null == detail1Content2;){
                detail1Content2=Utils.callApi(ahDetailUrl_2);
                if(null != detail1Content2){
                    break a;
                }
            }          
//            detail1Content2= detail1Content2.replace("{", "[{").replace("}", "}]");
//            JSONArray JSO_content2=new JSONArray(detail1Content2);
//            JSONObject  content2Json = JSO_content2.getJSONObject(0);
//            String  content2Main=content2Json.getString("content");
//            System.out.println("=============content2Main--------->\n"+content2Main);   
           
                //合并内容
            String andContent=detail1Content1+detail1Content2;
            String one=andContent.substring(andContent.lastIndexOf("{")-2,andContent.lastIndexOf("{")+12);
            String result=andContent.replace(one," ");
           
            System.out.println(result);
            //将result(跳转的内容)，进行转码，变为UTF-8
            iwRsp =new DefaultIwResponse(null,result.getBytes("UTF-8"),null,0,"ok");   
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return iwRsp;

    }


```



57.Combine使用for循环，反复访问
---------------------
```
  String detail1Content1=Utils.callApi(ahDetailUrl_1);
            a:for(;null == detail1Content1;){
                detail1Content1=Utils.callApi(ahDetailUrl_1);
                if(null != detail1Content1){
                    break a;
                }
            }

```





57-2.提取JSON格式内容
----------------------
```
detail1Content1 = detail1Content1.replace("{", "[{").replace("}", "}]");
//            JSONArray JSO_content1=new JSONArray(detail1Content1);
//            JSONObject  content1Json = JSO_content1.getJSONObject(0);
//            String  content1Main=content1Json.getString("content");    

```






58.删除节点
---------------------
```
     Element span_ele=(Element)xmlDoc.getElementById("content");
              
              String content=null;
              
               //删除我要报名
              NodeList div_list=span_ele.getElementsByTagName("div");
              if(div_list.getLength()>=2){
                  content=Utils.getNodeHtml(span_ele);
              }else{
                  NodeList p_list=span_ele.getElementsByTagName("p");
                  if(p_list!=null){
                     Element p_lastele=(Element)p_list.item(p_list.getLength()-1);
                       NodeList a_List=p_lastele.getElementsByTagName("a");
                       if(a_List.getLength()>0){
                           span_ele.removeChild(p_lastele);
                       }
                       content=Utils.getNodeHtml(span_ele);
                  }else{
                    content=Utils.getNodeHtml(span_ele);
                  }
              }
              
```





59.Detail通过JS获取动态的附件链接
-----------------------------

参考网址
`
  http://ztb.pinghu.gov.cn/phcms/gcjszbgg/10313.htm  
`

```
    protected TxtRspObject processTxtRspContent(RspState rspState,
            String originTxtRspContent) {
        Response response = new Response();
        if (rspState == RspState.Login) {
            try {

                // 前后补上div标签
                originTxtRspContent = "<div>" + originTxtRspContent + "</div>";

                // 获取所有节点
                Document xmlDoc = Utils.getDocByContent(originTxtRspContent);
                String content = null;
                
//              //获取附件连接的传入值
//              String cid = null;
//              String n = null;
//              String adjunctContent = null;
//              NodeList script_list = xmlDoc.getElementsByTagName("script");
//              if (script_list != null) {
//                  Element script_ele = (Element) script_list.item(6);
//                  adjunctContent = Utils.getNodeHtml(script_ele);
//                  adjunctContent = adjunctContent.substring(adjunctContent.indexOf("Cms.attachment"));
//                  adjunctContent=adjunctContent.substring(adjunctContent.indexOf(",")+2,adjunctContent.lastIndexOf(";"));
//                  adjunctContent=adjunctContent.substring(0,adjunctContent.indexOf(";")-1);
//                  cid = adjunctContent.substring(0,adjunctContent.indexOf(",")-1);
//                  n = adjunctContent.substring(adjunctContent.indexOf(",")+2,adjunctContent.lastIndexOf(",")-1);
//
//              }
//              //测试输出
//              System.out.println(adjunctContent);
//              System.out.println("cid="+cid+";-----n="+n);
            
                /*
                 *  获取主内容 
                 */
//              int count_div_table = 0;//记录主内容里是否有表格数      
                NodeList div_list = xmlDoc.getElementsByTagName("div");
                if (div_list != null) {
                    for (int i = 0; i < div_list.getLength(); i++) {
                        Element div_ele = (Element) div_list.item(i);

                        if ("Main-p".equals(div_ele.getAttribute("class"))) {//获取主要内容
                            NodeList table_list = div_ele.getElementsByTagName("table");//判断里面有没有table
//                          if (table_list.getLength()>0) {
//                              count_div_table =table_list.getLength();
//                          }
                            content = Utils.getNodeHtml(div_ele);//保存主要内容
                        }   
                    }
                }
                
                response.content=content;
                System.out.println(response.content);

//              //获取附件
//              String twoContent=null;
//              int count = 0;//计算a标签的次数，假设第一位是0
//              int count2=0;   
//              NodeList div_list2=xmlDoc.getElementsByTagName("div");
//              if(div_list!=null){
//                  for(int i=0;i<div_list2.getLength();i++){
//                      Element div_ele=(Element)div_list.item(i);
//                      
//                      if ("Content-Main FloatL Black".equals(div_ele.getAttribute("class"))) {
//                          NodeList table_list =div_ele.getElementsByTagName("table");
//                          if(table_list.getLength()==1){
//                              break;
//                          }
//                          
//                          Element table_ele=null;
//                          NodeList tr_list=null;
//                          NodeList th_list=null;
//                          if(count_div_table==0){
//                              table_ele = (Element) table_list.item(count_div_table);
//                              tr_list=table_ele.getElementsByTagName("tr");
//                              if(tr_list.getLength()!=2){
//                                  table_ele = (Element) table_list.item(count_div_table+1);
//                                   tr_list=table_ele.getElementsByTagName("tr");
//                                  if(tr_list.getLength()!=2){
//                                      break;
//                                  }else{
//                                      th_list=table_ele.getElementsByTagName("th");
//                                      if(th_list.getLength()<=2){
//                                          break;
//                                      }
//                                  }
//                              } 
//                          }else{
//                              table_ele = (Element) table_list.item(count_div_table);
//                              tr_list=table_ele.getElementsByTagName("tr");
//                              if(tr_list.getLength()!=2){
//                                  
//                                  if(table_list.getLength()==count_div_table+1){
//                                      break;
//                                  }
//                                  table_ele = (Element) table_list.item(count_div_table+1);
//                                   tr_list=table_ele.getElementsByTagName("tr");
//                                  if(tr_list.getLength()!=2){
//                                      break;
//                                  }
//                              } 
//                          }
//                                          
//                      
//                          // 扫描里面所有a标签，补全所有
//                              NodeList a_list = table_ele.getElementsByTagName("a");
//                              if (a_list != null) {
//                                  for (int k = 0; k < a_list.getLength(); k++) {
//                                      Element a_ele = (Element) a_list.item(k);//遍历每个A标签
//                                      
//                                      // Detail附件的链接用JS拼接后，跳到一个页面，获取参数，并截取需要的参数值，再次拼接
//                                      String aUrl = "http://ztb.pinghu.gov.cn/phcms/attachment_url.jspx?cid="+cid+"&n=" + n;
//                                          String ahParams = Utils.callApi(aUrl);//跳到aUrl的路径，并且获取里面的所有内容，保在字符串里
//                                          // 截取动态参数
//                                          String parameter = ahParams.substring(ahParams.indexOf("[") + 2,ahParams.lastIndexOf("]") - 1);
//      
//                                          String href = "http://ztb.pinghu.gov.cn/phcms/attachment.jspx?cid="+ cid+ "&i="+count+ parameter;
//                                      if(count2==2){
//                                          count=0;
//                                      }
//                                      
//                                      //建立属性
//                                      a_ele.setAttribute("href", href);
//                                      twoContent = Utils.getNodeHtml(table_ele).trim();
//                                      
//                                      //将字符里面所有的&amp;都变为&
//                                      twoContent =twoContent.replaceAll("&amp;", "&");
//                                      count2++;
//                                  }
//                              }
//                              }
//                          }
//                  }
//              if (twoContent != null) {
//                  response.content = content + twoContent;
//              } else {
//                  response.content = content;
//              }

            

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return response;
    }



```




60.附件链接中出现(&amp;符号)，导致无法下载
-----------------------------------------

```
String content=Uitls.getNodeHtml(div_ele).trim();
content=content.replaceAll("&amp;","&")
```






## 61. 截取的内容只有tr>标签，无法获取tr的Element，需要补全table>再转换为Doment

```
String page=originTxtRspContent.substring(originTxtRspContent.indexOf("totalRecord")+12, originTxtRspContent.indexOf("totalPage")-3);
                String cStr = originTxtRspContent.replace("totalRecord=2092.0;totalPage = 140;dataStore = ['", "").replace("'];", "").replaceAll("','", "").trim();             
                cStr="<table>"+cStr+"</table>";
                Document xmlDoc1=Utils.getDocByContent(cStr);
```





62.删除title里的\ (斜杠)
---------------------------
```
String title=a_ele.getAttribute("title");
 title=title.replace("\\", "").replace("'", "");
branchNew.title=title;
```




63.跳类型的话，没有类型参数的话，Params也记得配置正则
---------------------

```
举例网址：
http://www.ajztb.com/ajzbtb/jyxx/009002/009002003/MoreInfo.aspx?CategoryNum=009002003
http://www.ajztb.com/ajzbtb/jyxx/009002/009002004/

<![CDATA[(/ajzbtb/jyxx/)(.*)(/MoreInfo.aspx)]]>


```



64.判断链接开头
-------------------
```
 if(href.indexOf("http")!=-1){
                                            continue;
                                        }
                                        if(href.indexOf("/")==0){
                                             href=href.replaceFirst("/","http://ggzy.wuxing.gov.cn/");
                                        }
                                        if(href.indexOf("/")==2){
                                             href=href.replaceFirst("../","http://ggzy.wuxing.gov.cn/");
                                        }

```






65.去除Detail页面尾部的图片(每个Detail页都有，并且位置固定是最后)
-------------------------------
```
   originTxtRspContent = "<div>" + originTxtRspContent
                        + "</div>";
;
       
               int div_center=originTxtRspContent.lastIndexOf("<div align=");
               
               String content=originTxtRspContent.substring(0,div_center);
               
                response.content=content;
                
                System.out.println(content);
```






66.去除title标签内的不需要内容(加远)
-------------------------------

```
例如：·    [资格后审] 南浔锦绣实验学校弱电布线工程招标公告

 [资格后审]是不需要的！

String title=a_ele.getAttribute("title").trim();
                        if("【".equals(title.substring(0, 1)) 
                                |"[".equals(title.substring(0, 1)) 
                                |"(".equals(title.substring(0, 1))
                                |"（".equals(title.substring(0, 1))
                            ){
                            title=title.replaceFirst("【.*?】|\\[.*?]", "");
                            title=title.replaceFirst("(.*?) |\\（.*?）","");

                        }else{
                            title =a_ele.getAttribute("title");
                        }

```






67.使用NodeList来定位Id属性
--------------------------

有时候招标的Detail有附件，用id属性来定位，但是中标的Detail没有附件，所以会报错
用NodeList稳妥些

```
  String adjContent=null;
                
                NodeList table_list=(NodeList)xmlDoc.getElementById("grdFileList");
                if(table_list!=null){
                    Element table_ele=(Element)table_list.item(1);
                     NodeList a_list=table_ele.getElementsByTagName("A");
                     if(a_list.getLength()>0){
                         for(int i=0;i<a_list.getLength();i++){
                             Element a_ele=(Element)a_list.item(i);
                             
                             String href=a_ele.getAttribute("href");
                             href="http://www.sxxztb.gov.cn/Bulletin/"+href;

                             a_ele.setAttribute("href", href);
                             adjContent=Utils.getNodeHtml(table_ele).trim();
                             adjContent.replaceAll("&amp;","&");
                         }
                     }
                }

```





68.编码格式问题转码，解码
------------------------------------

```
ieDatumAction.public?p=downloadFileByPath&amp;filePath=iezcuploadfile\bulletin\201607\10765e5-155a554d4d3-77cbd8f533ed99db396a09e7cf6fd455\YWCG2016051GK（终稿）.doc


转为.
http://zfcg.yw.gov.cn/ieDatumAction.public?p=downloadFileByPath&filePath=iezcuploadfile\bulletin\201607\10765e5-155a554d4d3-77cbd8f533ed99db396a09e7cf6fd455\YWCG2016051GK%A3%A8%D6%D5%B8%E5%A3%A9.doc


 //补齐链接
                            href="http://zfcg.yw.gov.cn/"+href; 
                                          
                            String href_age=href.substring(0,href.lastIndexOf("\\")+1);
                            String href_behind=href.substring(href.lastIndexOf("."));
                            
                            //需要解码再转码的部分
                            String href_change=href.substring(href.lastIndexOf("\\")+1,href.lastIndexOf("."));
                   
                            href_change = URLDecoder.decode(href_change,"UTF-8");
                            href_change = URLEncoder.encode(href_change,"GB2312");
                            
                            String href_all=href_age+href_change+href_behind;
                            a_ele.setAttribute("href",href_all);
                            adjContent=Utils.getNodeHtml(div_ele2).trim();
                            adjContent=adjContent.replaceAll("&amp;", "&");


```





69.去除title前面[]内的内容(小强)
---------------------
```
String title=t2;
                            title=a_ele.getAttribute("title").trim();
                            if("(".equals(title.substring(0, 1))
                                    |"（".equals(title.substring(0, 1))
                                ){
                               
                                title=title.replaceFirst("\\(.*?\\)","");

                            }else{
                                title =a_ele.getAttribute("title");
                            }
                            title = title.replace("【询价公告】", "");
                            title = title.replace("【招标公告】", "");
                            
                            branchNew.title=title.trim();



```



70.Detail里面只有一个链接，链接到另一个页面
------------------------------
```
  //第二套Detail，跳到另一个页面
            if(response.content.length()<10){
                    String http=originTxtRspContent;
                    http=http.substring(http.indexOf("'")+1,http.lastIndexOf("'"));
                    
                    //跳到另一个页面，并且获取内容
                    String OtherContent=Utils.callApi(http);
                    Document xmlDoc2 = Utils.getDocByContent(OtherContent); 
                    
                    Element div_ele=xmlDoc2.getElementById("news_content");
                    response.content=Utils.getNodeHtml(div_ele);
            }
```





71.入库代码如果是空白页自动跳过(原网站一片的某个Detail页面一片空白)
---------------------------------------------------
```
package com.bonait.dataextract.service.impl;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

import com.bonait.dataextract.domain.ZhaoBiaoYuGao;
import com.bonait.dataextract.service.ZhaobYgService;
import com.bonait.dataextract.util.Util;
import com.bonait.dataextract.vo.ListVO;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


/**
 * @程序猿：刘淑玮
 * 
 * @内容：东软一期-建设工程-招标预告-01361-1
 */
@Service("zhejiang_01361_1_ZhaobYgService")
public class Zhejiang_01361_1_ZhaobYgService implements ZhaobYgService{

    @Resource
    private SessionFactory sf ;
    private Query query;
    private ZhaoBiaoYuGao zbyg;
    //列表页接口地址
    private String listUrl="http://127.0.0.1:1627/bnuser_3/?iw-apikey=123&iw-cmd=01361ZJCombine&Categorynum=013001&__EVENTARGUMENT=";
    //内容页接口地址
    private String detailUrl="http://127.0.0.1:1627/bnuser_3/?iw-apikey=123&iw-cmd=01361ZJDetail&CategoryNum=013001&infoid=";
    //入库编码
    private String sourceNo="01361-1";
    @Override
    public void initZhaobYgService() {
        try{
        String s=Util.sendGet(listUrl,"1");
        JSONObject obj =JSONObject.fromObject(s);
        while (null == obj.get("pageCount")){
            s = Util.sendGet(listUrl, "1");
            obj = JSONObject.fromObject(s);
        }
//      if(obj.getString("list").length()<10){System.out.println(sourceNo+"列表为空，请检查一下接口。");return;}
        int maxPage=Integer.parseInt(obj.get("pageCount").toString());
//      int maxPage=714;
        Session session=sf.openSession();
        session.beginTransaction();
        //清空数据库里
        query=session.createSQLQuery("DELETE FROM t_zhao_biao_yu_gao t where t.WEB_SOURCE_NO='"+sourceNo+"'");
        query.executeUpdate();
        session.getTransaction().commit();
        session.beginTransaction();
        //
        int KK=0;
        flag:
            for(int i=1;i<=3;i++){//maxPage
                String jsonList=Util.sendGet(listUrl,i+"");
                JSONObject objList =JSONObject.fromObject(jsonList);
                while(null == objList.get("list")){
                    jsonList = Util.sendGet(listUrl, i + "");
                    objList = JSONObject.fromObject(jsonList);
                }
                JSONArray temp=(JSONArray) objList.get("list");
                List list = (List)JSONArray.toList(temp,ListVO.class);
                Iterator it = list.iterator();
                int rows = 1;
                for(int row =1;row < rows;row++){
                    it.next();
                }
                while(it.hasNext()){
                    ListVO listvo =(ListVO) it.next();
                    query=session.createSQLQuery("SELECT ID FROM t_zhao_biao_yu_gao t where t.WEB_SOURCE_NO='"+sourceNo+"' and t.RECORD_ID='"+listvo.getId()+"'");
                    String getDate=listvo.getDate();
                    //获取页数，便于插入数据异常时，先跳过该页
                    System.out.println(i+":"+getDate+":"+maxPage);
                    
                    int date = Integer.parseInt(getDate.substring(0, 4));
                    //只取2014年至今的不重复数据   
                    if(query.executeUpdate()<=0&&date>=2014){
                        KK+=1;
                        String detail = null;
                        JSONObject o = null;
                        String content = null;
                        
                        //判断是否空白页
                        boolean sign=false;
                        int longContent=0;
                        
                        while (content == null || content.length() < 10) {
                            detail = Util.sendGet(detailUrl, listvo.getId());
                            if(detail.toString().length()<20){
                                sign=true;
                                break;
                            }
                            if (null != detail)
                                o = JSONObject.fromObject(detail);
                            else
                                continue;
                            if (null != o&&null != o.get("content"))
                                content = o.get("content").toString();
                            else
                                continue;
                            System.out.println("runing.."); 
                    }
                        
                        if(sign){
                            continue;
                        }
                        //过滤掉无效数据
                        if(content==null||content.length()<10||content.contains("出错")||content.contains("找不到文件")){continue;}
                        zbyg=new ZhaoBiaoYuGao();
                        zbyg.setWebSourceNo(sourceNo);
                        zbyg.setArea("华东");//区域
                        zbyg.setProvince("浙江");//省份
                        zbyg.setCity("金华");//城市，没有可不填
                        zbyg.setDistrict("浦江");// 区县 不要“区”字符
//                      zbyg.setCity(listvo.getCiy());
                        zbyg.setWebSourceName("浦江县公共资源交易中心");//网站名称，写完整的名称
                        zbyg.setInfoSource("政府采购");//信息来源
                        //未匹配到工程或服务的，都标识为货物
                        String infoType=Util.getInfoType(content);
                        if(infoType!=null&&infoType.length()>0)
                        zbyg.setInfoType(Util.getInfoType(content));
                        else {zbyg.setInfoType("货物");}
//                      zbyg.setInfoType("服务");//信息类型
//                      zbyg.setIndustry("建筑建材"); //行业分类
//                      zbyg.setIndustry(Util.getIndustry(content));//行业分类
                        zbyg.setRecordId(listvo.getId());
                        zbyg.setId(UUID.randomUUID().toString());
                        zbyg.setPageTitle(listvo.getTitle());
                        zbyg.setPageTime(listvo.getDate());
                        zbyg.setPageContent(content);
                        zbyg.setPageAttachments("");//附件url，暂不需要
                        zbyg.setCreateTime(new Date());
                        session.save(zbyg);
                     }else if(date<=2013){break flag;}//到2013年的页码时，断掉循环
                    if(KK%1==0){
                        session.flush();  
                        session.clear();
                        session.getTransaction().commit();
                        session.beginTransaction();
                    }
                }  
            }   

            
        session.getTransaction().commit();
        session.close();
        System.out.println("000000000");
        
        } catch (Exception e) {
            System.out.println(sourceNo+"接口出错，请检查");
        }
    }

}

```






72.入库小发现
-----------------------
宿舍可以查测试库，入测试库

正式库无法操作

73.Detail补齐图片，和附件链接通用版
---------------------------------------
```
protected TxtRspObject processTxtRspContent(RspState rspState,
            String originTxtRspContent) {
        Response response = new Response();
        if (rspState == RspState.Login) {
            try {
                originTxtRspContent = "<div>" + originTxtRspContent+ "</div>";
                Document xmlDoc = Utils.getDocByContent(originTxtRspContent);       
                
                String div_content=null;
                NodeList div_list=xmlDoc.getElementsByTagName("div");
                if(div_list!=null){
                    for(int j=0;j<div_list.getLength();j++){
                        Element div_ele=(Element)div_list.item(j);
                        
                        if("t_list_content".equals(div_ele.getAttribute("class"))){
                                                         
                             NodeList a_list=div_ele.getElementsByTagName("a");
                             if(a_list.getLength()>0){
                                 for(int i=0;i<a_list.getLength();i++){
                                     Element a_ele=(Element)a_list.item(i);
                                     String href=a_ele.getAttribute("href");
                                     
                                     if(href.indexOf("http")!=-1 || href.length()<5){
                                         continue;
                                     }

                                     if(href.indexOf("/")>=1){
                                          href=href.replaceFirst("./","http://www.xinhuang.gov.cn/");
                                     }else{
                                         href=href.replaceFirst("/","http://www.xinhuang.gov.cn/");
                                     }
                                     a_ele.setAttribute("href", href);
                                 }
                             }
                             
                             NodeList img_list=div_ele.getElementsByTagName("img");
                             if(img_list.getLength()>0){
                                 for(int i=0;i<img_list.getLength();i++){
                                     Element img_ele=(Element)img_list.item(i);
                                          
                                     String src=img_ele.getAttribute("src");
                                     
                                     if(src.indexOf("http")!=-1  || src.length()<5){
                                         continue;
                                     }

                                     if(src.indexOf("/")>=1){
                                      src=src.replaceFirst("./","http://www.xinhuang.gov.cn/");
                                    }else{
                                     src=src.replaceFirst("/","http://www.xinhuang.gov.cn/");
                                    }
                                     
                                     img_ele.setAttribute("src", src);
                                 }
                             } 
                          div_content=Utils.getNodeHtml(div_ele);
                        }
                    }
                }
                
                
                 response.content=div_content;
                 
                 System.out.println(response.content);
                 
                
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
        return response;
    }


```



74.ecplise--运行java类快捷键
-------------------------------

F11




75.遇到网站里List内容是[&#xxxxx];编码格式的 (健俊)
-----------------------------------

数字是字符的Unicode编码的10进制形式，&#xxxxx;这是字符的HTML实体形式。

解决方案：
抓下来，用字符串转码将其转换成中文
```
String title=Utils.decodeUnicode(txt);
把&#20851;&#20110;&#8220;&#33670;&#30000;&#24066;这种类型的编码转换成中文

```






76,增加变量
--------------------------------
```
 <request-path>/jscg/cggg/gg_list.jsp</request-path>
     <params>
      <param>
        <name>opstatus</name>
        <value>query</value>
      </param>
    </params>
     <params>
      <param>
        <name>gg_year</name>
        <value></value> <!--2015 -->
      </param>
    </params>
    <request-uri-replacement-pattern></request-uri-replacement-pattern>
```





77.removeChild()方法删除子节点
----------------------
tr_ele.removeChild(td_ele);只能删除儿子的，但是有些时候，儿子比较特别，报错

可以尝试定位到爷爷，然后再定位到爷爷的儿子，由爷爷的儿子来删除孙子。
这样输出爷爷的时候，孙子也成功删除了

额，可怕的逻辑能力。。。。






78.获取当前年份
----------------------
两种方法
```
1,优先使用
    Calendar c= Calendar.getInstance();
      int year = c.get(Calendar.YEAR);
      java.util.Calendar c = Calendar.getInstance();
int year = c.get(Calendar.YEAR);
int month = c.get(Calendar.MONTH) + 1; //国外从0月开始，11月结束
int date = c.get(Calendar.DATE);
int hh = c.get(Calendar.HOUR_OF_DAY); //24小时制
int mm = c.get(Calendar.MINUTE);
int ss = c.get(Calendar.SECOND); //秒
2、java.util.Date date = new Date(); //当前日期时间
int yy = date.getYear();
int hh = date.getHours();


2.
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;     
          Date dt=new Date();//如果不需要格式,可直接用dt,dt就是当前系统时间
         DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//设置显示格式
　　 String nowTime="";
        nowTime= df.format(dt);//用DateFormat的format()方法在dt中获取并以yyyy/MM/dd HH:mm:ss
```


案例:
```
        //获取系统当前年份
            Calendar c= Calendar.getInstance();
            int nowYear = c.get(Calendar.YEAR);
            
        String s=Util.sendGet(listUrl,nowYear+"");
        JSONObject obj =JSONObject.fromObject(s);
        while (null == obj.get("pageCount")){
            s = Util.sendGet(listUrl, nowYear+"");
            obj = JSONObject.fromObject(s);
        }

        //
            for(int i=nowYear ;i>=2014 ;i--){//maxPage
                String jsonList=Util.sendGet(listUrl,i+"");
            
                JSONObject objList =JSONObject.fromObject(jsonList);
                while(null == objList.get("list")){     
                        jsonList=Util.sendGet(listUrl,i+"");
                    objList = JSONObject.fromObject(jsonList);
                }
```






79.URL入库，主要是用ListVO_4
-----------------------------

```
package com.bonait.dataextract.service.impl;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

import com.bonait.dataextract.domain.ZhaoBiaoGongGao;
import com.bonait.dataextract.service.ZhaobGgService;
import com.bonait.dataextract.util.Util;
import com.bonait.dataextract.vo.ListVO_4;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


/**
 * @程序猿：刘淑玮
 * 
 * @内容：东软一期-政府采购-招标公告-03152
 */
@Service("shanxi_03152_ZhaobGgService")
public class Shanxi_03152_ZhaobGgService implements ZhaobGgService{

    @Resource
    private SessionFactory sf ;
    private Query query;
    private ZhaoBiaoGongGao zbgg;
    //列表页接口地址
    private String listUrl="http://127.0.0.1:1627/bnuser_3/?iw-apikey=123&iw-cmd=03152SXList&id=363&Page=";
    //内容页接口地址
//  private String detailUrl="http://127.0.0.1:1626/?iw-apikey=123&iw-cmd=sichuang00181Detail&iw_ir_2=002001&InfoID=";
    //入库编码
    private String sourceNo="03152";
    @Override
    public void initZhaobGgService() {
        try{
        String s=Util.sendGet(listUrl,"1");
        JSONObject obj =JSONObject.fromObject(s);
        while (null == obj.get("pageCount")){
            s = Util.sendGet(listUrl, "1");
            obj = JSONObject.fromObject(s);
        }
//      if(obj.getString("list").length()<10){System.out.println(sourceNo+"列表为空，请检查一下接口。");return;}
        int maxPage=Integer.parseInt(obj.get("pageCount").toString());
//      int maxPage=714;
        Session session=sf.openSession();
        session.beginTransaction();
        //清空数据库里
//      query=session.createSQLQuery("DELETE FROM t_zhao_biao_gong_gao t where t.WEB_SOURCE_NO='"+sourceNo+"'");
//      query.executeUpdate();
//      session.getTransaction().commit();
//      session.beginTransaction();
        //
        int KK=0;
            flag:
            for(int i=1;i<=maxPage;i++){//maxPage
                String jsonList=Util.sendGet(listUrl,i+"");
                JSONObject objList =JSONObject.fromObject(jsonList);
                while(null == objList.get("list")){
                    jsonList = Util.sendGet(listUrl, i + "");
                    objList = JSONObject.fromObject(jsonList);
                }
                JSONArray temp=(JSONArray) objList.get("list");
                List list = (List)JSONArray.toList(temp,ListVO_4.class);
                Iterator it = list.iterator();
                int rows = 1;
                for(int row =1;row < rows;row++){
                    it.next();
                }
                while(it.hasNext()){
                    ListVO_4 listvo =(ListVO_4) it.next();
                    query=session.createSQLQuery("SELECT ID FROM t_zhao_biao_gong_gao t where t.WEB_SOURCE_NO='"+sourceNo+"' and t.RECORD_ID='"+listvo.getId()+"'");
                    String getDate=listvo.getDate();
                    //获取页数，便于插入数据异常时，先跳过该页
                    System.out.println(i+":"+getDate+":"+maxPage);
                    
                    int date = Integer.parseInt(getDate.substring(0, 4));
                    //只取2014年至今的不重复数据   
                    if(query.executeUpdate()<=0&&date>=2014){
                        KK+=1;
                        String detail = null;
                        JSONObject o = null;
                        String content = null;
                        while (content == null || content.length() < 10) {
                            detail = Util.sendGet(listvo.getUrl(),"");
                            if (null != detail)
                                o = JSONObject.fromObject(detail);
                            else
                                continue;
                            if (null != o&&null != o.get("content"))
                                content = o.get("content").toString();
                            else
                                continue;
                            System.out.println("runing..");
                        }
                        //过滤掉无效数据
                        if(content==null||content.length()<10||content.contains("出错")||content.contains("找不到文件")){continue;}
                        zbgg=new ZhaoBiaoGongGao();
                        zbgg.setWebSourceNo(sourceNo);
                        zbgg.setArea("华北");//区域
                        zbgg.setProvince("山西");//省份
                        zbgg.setCity("晋中");//城市，没有可不填
                        zbgg.setDistrict("平遥");// 区县 不要“区”字符
//                      zbgg.setCity(listvo.getCiy());
                        zbgg.setWebSourceName("平遥县政府采购中心");//网站名称，写完整的名称
                        zbgg.setInfoSource("政府采购");//信息来源
                        //未匹配到工程或服务的，都标识为货物
                        String infoType=Util.getInfoType(content);
                        if(infoType!=null&&infoType.length()>0)
                        zbgg.setInfoType(Util.getInfoType(content));
                        else {zbgg.setInfoType("货物");}
//                      zbgg.setInfoType("服务");//信息类型
//                      zbgg.setIndustry("建筑建材"); //行业分类
//                      zbgg.setIndustry(Util.getIndustry(content));//行业分类
                        zbgg.setRecordId(listvo.getId());
                        zbgg.setId(UUID.randomUUID().toString());
                        zbgg.setPageTitle(listvo.getTitle());
                        zbgg.setPageTime(getDate);
                        zbgg.setPageContent(content);
                        zbgg.setPageAttachments("");//附件url，暂不需要
                        zbgg.setCreateTime(new Date());
                        session.save(zbgg);
                     }else if(date<=2013){break flag;}//到2013年的页码时，断掉循环
                    if(KK%1==0){
                        session.flush();  
                        session.clear();
                        session.getTransaction().commit();
                        session.beginTransaction();
                    }
                }  
            }   

            
        session.getTransaction().commit();
        session.close();
        System.out.println("000000000");
        
        } catch (Exception e) {
            System.out.println(sourceNo+"接口出错，请检查");
        }
    }

}

```






80.入库代码开头要注解清晰
-----------------------
```
/**
 * @程序猿：刘淑玮
 * 
 * @内容：东软一期-政府采购-公告变更-03152
 */

```





81.URL地址栏,开启代理后
-----------------

补齐链接地址，要补上项目名[不补也可以使用]
```
String url=getNewPathPrefix()+"/bnuser_3(4)/?"+
         getAdditionalLinkParamStr()+
                  "&iw-cmd=03160SXDetail&code="+code;
```





82.Detail页里面有音频
---------------------
音频不用抓，抓附件就行

案例
`
http://www.ptq.sh.gov.cn/gb/n6132/n6134/n6142/n6638/n6651/u1ai153785.html
`




83.URL转码，List和Detail页面内容编码特殊
--------------------------
```
/**
     * 解析str = "%u817E%u8BAF%u79FB%u52A8%u4E92%u8054%u7F51";
     * 
     * @param unicode
     * 
     * @author DJ
     * 
     * @return contents
     */

    public String unicodeString(String unicode){
          
         StringBuilder contents = new StringBuilder() ;
         
         String[] hex = unicode.split("%u");
      
         for (int i = 1; i < hex.length; i++) {
             
             hex[i] = hex[i].substring(0, 4);

             int data = Integer.parseInt(hex[i], 16);

             if(unicode.indexOf("%u")!=0){
                 contents = contents.append(unicode.substring(0,unicode.indexOf("%u"))+(char)data);
                 unicode = unicode.substring(unicode.indexOf("%u")+6);
             }else{
                 unicode = unicode.substring(6);
                 contents = contents.append((char)data);
             }
         }
         contents = contents.append(unicode);
         return contents.toString();
     }

public String urlcodeString(String urlcode){
          
         StringBuilder contents = new StringBuilder() ;
         
         String[] hex = urlcode.split("%");
      
         for (int i = 1; i < hex.length; i++) {
             
             hex[i] = hex[i].substring(0, 2);

             String data = URLDecoder.decode("%"+hex[i]);

             if(urlcode.indexOf("%")!=0){
                 contents = contents.append(urlcode.substring(0,urlcode.indexOf("%"))+data);
                 urlcode = urlcode.substring(urlcode.indexOf("%")+3);
             }else{
                 urlcode = urlcode.substring(3);
                 contents = contents.append(data);
             }
         }
         contents = contents.append(urlcode);
         return contents.toString();
     }
}


```





84.List页面抓包下来全是字符串[并且是83的乱码]
-----------------------------------------
```
        //判断当前页面有多少条List
                String allContent=originTxtRspContent;
                int listCount=0;            //统计匹配字符的数量
                int location=-1;        //记录字符位置(变化) 
                boolean sign=true;
                while(sign){
                    location =allContent.indexOf("datestr");
                    if(location!=-1){
                        listCount++;
                        allContent=allContent.substring(location+7);
                    }else{
                        sign=false;
                    }
                }

        //获得当前页面所有标题
                String allList=originTxtRspContent;   //页面所有内容
                String title="";                      //储存标题内容
                int title_Location=-1;                //标题位置
                String [] arrayTitle=new String[listCount];
                for(int i=0;i<arrayTitle.length;i++){
                    title_Location=allList.indexOf("Title")+10;
                    
                    allList=allList.substring(title_Location);
                    String title_font=allList.substring(0,allList.indexOf("%"));
                    title=allList.substring(allList.indexOf("%"),allList.indexOf(",")-2);
                    //转码，将URL编码格式转成中文(解码)
                    title=this.unicodeString(title);
                    arrayTitle[i]=title_font+title;
                }
```



85.Combine增加参数,另一种(健俊)
--------------------------
Combine是不用配置正则的

```
api.xml里加在下面[conten里面]
<request-uri-replacement-pattern></request-uri-replacement-pattern>
    <request-content>
      <param>
        <name>iw_ir_3</name>
        <value>zbgglist<value>
      </param>
      <param> 


然后Combine里用
String iw_ir_3=iwRequest.getRequestContentParam("iw_ir_3").trim(); 这样拿

```





86.iw_ir_2的理解(健俊)
----------------------------
2是指在*第2个括号里，*号代表变化的东西
`
     <![CDATA[(qztb)(.*)(.aspx)]]>
`


3就是指*在第3个括号里，iw_ir_3=，举例
`<![CDATA[(qztb)(b/)(.*)(.aspx)]]>`



87.计数判断，选出符合条件的某个Element
------------------
```
int count=0;
   if("100%".equals(table_eleA.getAttribute("width"))){
       //计数
        if(++count!=2) continue;
    }

```





88.在Combine拼接跳转List的URL，因为多了参数，导致无法跳页(加远)
--------------------------------------------------
分析网页，测试哪个参数是多余的

举例
```
api.xml文档里
 <param>
        <name>__EVENTTARGET</name>
        <value>ctl00$ContentPlaceHolder1$MoreGctypeInfolist1$Pager</value>
 </param>

            String jxCombine=getNewPathPrefix()+"/?"+getAdditionalLinkParamStr()+"&iw-cmd=03169SHList"
                    +"&__CSRFTOKEN="+__CSRFTOKEN
                    +"&__VIEWSTATE="+__VIEWSTATE
//                    +"&__EVENTTARGET="+__EVENTTARGET                                   //这个参数经过测试不需要，会导致无法跳页
                     +"&__EVENTVALIDATION="+__EVENTVALIDATION
                    +"&ctl00$ContentPlaceHolder1$MoreGctypeInfolist1$Pager_input="+EVENTARGUMENT
                    +"&ctl00$ContentPlaceHolder1$MoreGctypeInfolist1$ddlGctype="+gctype  //类型
                    +"&categoryNum="+categoryNum
                    +"&deptCode=001"
                    +"&gctype="+gctype
                    +"&__EVENTARGUMENT="+EVENTARGUMENT;

```






89.设置执行SQL命名后，查询界面显示的记录数
-------------------------------
```
工具 ->   首选项  ->    窗口类型    ->  SQL窗口    ->每页记录数
```




90.Eclipse设置不限制控制台输出
-----------------------------
```
Eclipse上方菜单栏------>Windows------>Run/Debug----->Console
----->Limit console output

设置控制台输出，取消打勾就是不限制

```






91.在Eclipse中点代码,包资源管理器自动跳动到该文件
----------------------------------
点击：左右箭头的图标[与编辑器连接]
```
-->
<--
```

适用场景：
抓玩包之后搜索API[Ctril+8],然后点击搜索到的文件，编辑器显示文件内容，
点击按钮(Link with Editor)，再点击编辑器，左边的项目管理器会自动搜索文件所在的目录。




92.List里添加URL跳转到Detail页面
---------------------------
适用情况:
List里抓取id的链接包含了，跳类型的参数,如果不包含则不建议做URL，
因为跳类型的判断较为复杂，不简洁
例如
`href="http://www.jjx.gov.cn/html/nn/nn1/nn1_2/53144.html"`

扩展：
可使用URL入库




93.招标中标的信息在网站同一个页面，不同模块
-----------------------------------

招标中标的在同一页面，通过javascrip来切换，
抓招标的List和Detail，然后多复制一份作为中标的映射

举例
`http://www.lushan.cn/ljzwxx/cgjy/zfcg/ `





94.终极暴力Detail抓date(健俊)
---------------------------------
```


String date="";
                NodeList td_list=xmlDoc.getElementsByTagName("td");
                for(int i=0;i<td_list.getLength();i++){
                    Element td_ele=(Element)td_list.item(i);
                    if("700".equals(td_ele.getAttribute("height"))){
                        String test=td_ele.getTextContent().trim();
                        test=test.replace(" ", "");
                        String a=test.substring(test.indexOf("时间：")+3, test.indexOf("时间：")+12).trim();
                        if(a.indexOf("2")==-1) continue;
                        date=a.replace(".", "-").replace("年", "-").replace("月", "-").replace("日", "-");                     
                    }
                    if("500".equals(td_ele.getAttribute("height"))){
                        String content=Utils.getNodeHtml(td_ele).trim();
                        response.content=content;
                        if(date.length()<4){
                            String txt=td_ele.getTextContent();
                            txt=txt.replaceAll(" ", "").replaceAll(" ", "").trim();
                            String year=txt.substring(txt.lastIndexOf("年")-4, txt.lastIndexOf("年"));
                            String month=txt.substring(txt.lastIndexOf("年")+1, txt.lastIndexOf("月"));
                            String day=txt.substring(txt.lastIndexOf("月")+1, txt.lastIndexOf("日"));
                            date=year+"-"+month+"-"+day;
                        }
                    }
                }
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
                Date date1=format.parse(date);
                date=format.format(date1);
                response.date=date;
                System.out.println(response.content+"\n"+response.date);

终极暴力Detail抓时间
```



95.启动reactor后，报错"创建临时文件夹失败"
--------------------------

导致：
​	通过谷歌浏览器访问时候，一直报错 "the specified iw-cmd is invalid"


原因：
​	工程项目根目录下，缺少了thirdPartyLibs目录


解决方案：
​	从别的工程项目里复制过去






96.URL中文编码问题--URL里面包含中文，只有特定格式才能跳入网站
---------------------------
将api.xml文档里的乱码部分改为相应中文，并且设置编码格式为GB2312【如果不行就设置为GBK试试】

```

 <param>
        <name>tid</name>
     <value>政府采购</value>
</param>
 <request-content-type>application/x-www-form-urlencoded</request-content-type>
    <request-content-charset>GBK</request-content-charset>
      <rsp-content-decoding-charset>GBK</rsp-content-decoding-charset>
```






97.查询数据库相关语句扩展
----------------------------
```
1、查看当前用户拥有的角色权限信息：select * from role_sys_privs;
2、查看当前用户的详细信息：select * from user_users;
3、查看当前用户的角色信息：select * from user_role_privs;

1、查看所有表空间及表空间大小：
　　select tablespace_name ,sum(bytes) / 1024 / 1024 as MB　from dba_data_files group by tablespace_name;
2、查看所有表空间对应的数据文件：
　　select tablespace_name,file_name from dba_data_files;
3、修改数据文件大小：
　　alter database datafile 'H:\ORACLE\PRODUCT\10.1.0\ORADATA\ORACLE\USERS01.DBF' RESIZE 10240M; 
```

---



## 98. List页面是特殊格式，用字符串进行截取

案例
```
{"success":true,"totalRows":704,"data":[{"Id":"1-4028819056737d6601567390cc2b0028","XH":1,"Provider":"Zfcg","State":"4028819056737d6601567390cc2b0028","Date":"\/Date(1470817372000)\/","Title":"曲阜市政府采购办公设备（二次）采购询价公告（QFCG2016-176）","Code":"QFCG2016-176","XmStateMc":null},{"Id":"1-4028819056737d66015673803503000b","XH":2,"Provider":"Zfcg","State":"4028819056737d66015673803503000b","Date":"\/Date(1470816587000)\/","Title":"曲阜市石门山镇农村无害化卫生厕所改造采购项目招标公告","Code":"QFCG2016-167","XmStateMc":null},{"Id":"1-402881905668233d0156725cf02e0dfc","XH":3,"Provider":"Zfcg","State":"402881905668233d0156725cf02e0dfc","Date":"\/Date(1470797597000)\/","Title":"曲阜市人民检察屋面防水采购项目竞争性谈判公告（二次）","Code":"QFCG2016-175","XmStateMc":null},{"Id":"1-402881905668233d01566e9b2cff0a45","XH":4,"Provider":"Zfcg","State":"402881905668233d01566e9b2cff0a45","Date":"\/Date(1470734496000)\/","Title":"关于《世界文化遗产-曲阜孔庙、孔林及孔府动态信息及监测预警系统》方案设计项目的变更公
```

<br>

完整版代码
```
package SD03364;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import SD03363.ZBList.BranchNew;

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
        String url;

        public String toString() {
            return "BranchNew[ title=" + title+";id=" + id +";url="+url;
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
//              System.out.println(originTxtRspContent);
                Document xmlDoc=Utils.getDocByContent(originTxtRspContent);
                
                //获取title，id，url
                
                //判断当前页面有多少条List
                String allContent=originTxtRspContent;
                int listCount=0;            //统计匹配字符的数量
                int location=-1;        //记录字符位置(变化) 
                boolean sign=true;
                while(sign){
                    location =allContent.indexOf("Title");
                    if(location!=-1){
                        listCount++;
                        allContent=allContent.substring(location+5);
                    }else{
                        sign=false;
                    }
                }
                
                //获取标题
                String allList=originTxtRspContent;   //页面所有内容
                String title="";                      //储存标题内容
                int title_Location=-1;                //标题位置
                String [] arrayTitle=new String[listCount];
                for(int i=0;i<arrayTitle.length;i++){
                    title_Location=allList.indexOf("Title")+8;
                    
                    allList=allList.substring(title_Location);
                    title=allList.substring(0,allList.indexOf(",")-1);
                    
                    arrayTitle[i]=title;
                }
                
              //获取XmId
                String allXmId=originTxtRspContent;   //页面所有内容
                String XmId=""; 
                String State="";   
                int XmId_Location=-1;                //标题位置
                String [] arrayXmId=new String[listCount];
                for(int i=0;i<arrayXmId.length;i++){
                    XmId_Location=allXmId.indexOf("Id")+5;
                    
                    allXmId=allXmId.substring(XmId_Location);
                    XmId=allXmId.substring(0,allXmId.indexOf(",")-1);
                    State=XmId.substring(XmId.indexOf("-")+1);
                    arrayXmId[i]=XmId+"&State="+State+"&Provider=Zfcg";       
                }
                
                //URL补全地址
               String URL=getNewPathPrefix()+"/bnuser_3/?"+getAdditionalLinkParamStr()+"&iw-cmd=03364SDDetail&XmId=";
               
               //创建对象,保存公告
                for(int i=0;i<listCount;i++){
                    BranchNew branchNew=new BranchNew();        
                    branchNew.title=arrayTitle[i];      
                    branchNew.id=arrayXmId[i];
                    branchNew.url=URL+arrayXmId[i];
                    response.list.add(branchNew);
            
                    System.out.println(branchNew.toString());
                }
              

                //获取总页数
                String pageCount=originTxtRspContent.substring(originTxtRspContent.indexOf("totalRows"));
                int allRecord=Integer.parseInt(pageCount.substring(pageCount.indexOf(":")+1,pageCount.indexOf(",")));
                if(allRecord%20==0)
                    pageCount=String.valueOf(allRecord/20);
                else
                    pageCount=String.valueOf(allRecord/20+1);
               
                response.pageCount=pageCount;
                

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
                    .deserializeString("internetware/bnuser3_3/apis/03364-6339d2309bfcd74420871de692ff3cec6a-SDList/SampleResponse_1","UTF-8");
            handler.processRspContent(
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



99.List和Detail无法拿到日期，入库里使用new Date() [健俊]
-------------------------------------
```

一句话new Date
String getDate=new SimpleDateFormat("yyyy-MM-dd").format(new Date()); 



 ListVO_4 listvo =(ListVO_4) it.next();
 query=session.createSQLQuery("SELECT ID FROM t_zhong_biao_xin_xi t where t.WEB_SOURCE_NO='"+sourceNo+"' and t.RECORD_ID='"+listvo.getId()+"'");
                    String getDate=new SimpleDateFormat("yyyy-MM-dd").format(new Date());                    ----放在这里
 //获取页数，便于插入数据异常时，先跳过该页
 System.out.println(i+":"+getDate+":"+maxPage);
                    

```

---
<br><br>



100.将时Unix时间戳转换为yyyy-MM-dd格式的日期
---------------------
```
   public static String timeStamp2Date(String seconds,String format) {  
            if(seconds == null || seconds.isEmpty() || seconds.equals("null")){  
                return "";  
            }  
            if(format == null || format.isEmpty()) format = "yyyy-MM-dd HH:mm:ss";  
            SimpleDateFormat sdf = new SimpleDateFormat(format);  
            return sdf.format(new Date(Long.valueOf(seconds+"")));  
        }  
        /** 
         * 日期格式字符串转换成时间戳 
         * @param date 字符串日期 
         * @param format 如：yyyy-MM-dd HH:mm:ss 
         * @return 
         */  
        public static String date2TimeStamp(String date_str,String format){  
            try {  
                SimpleDateFormat sdf = new SimpleDateFormat(format);  
                return String.valueOf(sdf.parse(date_str).getTime()/1000);  
            } catch (Exception e) {  
                e.printStackTrace();  
            }  
            return "";  
        }  
          
        /** 
         * 取得当前时间戳（精确到秒） 
         * @return 
         */  
        public static String timeStamp(){  
            long time = System.currentTimeMillis();  
            String t = String.valueOf(time/1000);  
            return t;  
        }  
    


调用
//获取date
                String allDate=originTxtRspContent;   //页面所有内容
                String Date=""; 
                int Date_Location=-1;                //标题位置
                String [] arrayDate=new String[listCount];
                for(int i=0;i<arrayDate.length;i++){
                    Date_Location=allDate.indexOf("/Date")+5;
                    
                    allDate=allDate.substring(Date_Location);
                    Date=allDate.substring(allDate.indexOf("(")+1,allDate.indexOf(")"));
                    Date = timeStamp2Date(Date, "yyyy-MM-dd HH:mm:ss");  
                    Date=Date.substring(0,10);
 
                    arrayDate[i]=Date;    
                }
```

---
<br><br>



101.谷歌浏览器的版本和燕云dds兼容问题
---------------------------------------

问题描述：
    点击Plant不弹出谷歌浏览器+有弹出但是页面一片空白+点击“启动Plant”(按钮仍然显示一直启动)


经过测试一下版本合适：
+ 52.0.2743.116m
+ 52.0.2743.116(64-bit)
+ 53.0.2785.116 m

以下版本不合适
+ 54.0.2840.59 m


最好将谷歌浏览器的自动更新关闭
控制面板----》管理工具------》服务
Google 更新服务(gupdate) ----设置为禁用
Google 更新服务(gupdatem) ----设置为禁用

---
<br><br>



102.燕云dds的eclise无法无法抓包and开启代理
--------------------------------

报错描述:
&emsp;点击`启动Plant`,程序自动结束,谷歌浏览器闪退或者空白页,报错`The license is invalid or expired!`
&emsp;点击`启动Reactor`,程序自动结束,谷歌浏览器没反应报错`The licens is invalid or expired!`


原因：
&emsp;证书过去【软件过期】,


解决方案:
```
工具问题

替换
    eclipse-jee-juno-win32-x86_64\eclipse\configuration\org.eclipse.osgi\bundles\907\2\.cp目录下的
----------------------------->license.iwc文件

```

---
<br><br>


103.JSON格式数据解析【使用GSON】
----------------------------
```
//使用google-gson解析JSON数据
                
                    //A-创建JSON解析器
                    JsonParser jsonParser = new JsonParser();
                    
                    //B-通过parse方法获取最外层的json对象
                    JsonObject jsonObject = (JsonObject)jsonParser.parse(originTxtRspContent); 
                    
                    //C-获取JSON数组
                    JsonArray jsonArray = jsonObject.get("rows").getAsJsonArray(); 
                    
                    //D-依次遍历JSON数组
                    for(int i = 0,lenght = jsonArray.size(); i < lenght; i++){
                        JsonObject subJsonObject = jsonArray.get(i).getAsJsonObject();              //1.对应数组元素
                        
                        BranchNew branchNew = new BranchNew();                                      //2.构建BranchNew对象
                           branchNew.title = subJsonObject.get("CONTENTTITLE").getAsString();
                           branchNew.date  = subJsonObject.get("PUBLISHTIME").getAsString();        
                           branchNew.id    = subJsonObject.get("CONTENTID").getAsString();            
                           branchNew.url   = getNewPathPrefix()+"/?"+ getAdditionalLinkParamStr()+
                                             "&iw-cmd=03658DSDetailCombine&id="  +   
                                              subJsonObject.get("CONTENTID").getAsString();
                           
                        response.list.add(branchNew);                                               //3.将BranchNew对象添加进Response
//                      System.out.println(branchNew.toString());                                   //4.测试输出
                    }

```

---
<br><br>