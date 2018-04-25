---
title: JSPForumProject_record
date: 2016-7-17 9:25:43
tags: MyProject
categories: MyProject
---

记录
==================

6.6
-------------------
1.JS注册验证，部分特效
2.计时器
　　 a.现在时间
　　 b.今天还剩下多少秒

---

6.7
-------------
1.JS调整
　　 a.获取手机验证码倒计时
　　 b.调整高级选项

2.注册功能_1
　　 a.尝试在jsp页面，接收html注册页面，用户填写的注册信息(表单提交)，
　　 b.添加基本的数据验证。


---


6.8
--------------
1.注册功能_2
　　 a.连接数据库(MySQL)
　　 b.验证用户名的唯一性
　　 c.储存注册信息

```

a的实现~~~~~~~~~~~~~~~~~~~~~~~~~
1.使NetBeens连接mysql数据库，创建Web Project
将MySQL的驱动程序jar包(mysql-connector-java-5.1.38-bin)，复制到，项目--->库--->JDK1.8中

2.
```
   Connection con=null;//声明连接对象
    Statement st=null;//声明语句对象
    ResultSet rs =null;//声明结果集对象--结果集
//注册驱动程序
        Class.forName("com.mysql.jdbc.Driver");
//获取数据库连接;
        con=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/imooc","用户名","用户密码");
//创建Statement对象
        st=con.createStatement(); 
//执行查询语句并得到结果(字符串内的SQL语句结尾可以不写；)
        String sql="SELECT * FROM imooc.items wh";
        rs=st.executeQuery(sql);  

rs.next() //向后移动游标，初始状态游标是位于第一行之前的
rs.getString("name");  //读取当前列，name字段的记录值

```

3.记得try...catch...


4.关闭资源

    rs.close();
    st.close();
    con.close();


>注意点：
>>1.Statement st =con.createStatement();
>>
>>2.Statement st =con.createStatement(游标类型，上锁类型);
>>>游标类型：只读，可更新
>>>
>>>上锁类型：
>>>1.只能向前(默认，只能用next向前移动)
>>>2.游标可随意滚动,不反映数据的变化(他人修改不敏感)
>>>3.游标可随意滚动，反映数据的变化(他人修改敏感)

```

---

b实现


1.mysql创建数据库forumproject(论坛项目)


2.使用Navicat for MySQL工具


3.设计表结构
```
编号 INT 主键,自增
用户名：VARCHAR(19) 唯一约束，非空
密码:   VARCHAR(14)  非空
性别:   CHAR(1)  1代表男，2代表女
生日：  DATE
所在地：VARCHAR(15) 非空
邮箱：  VARCHAR(20) 非空
手机号码：CHAR(11)  非空
通信地址：VARCHAR(50)
爱好：    VARCHAR(20);
用户注册时间： DATETIME  默认值 


CREATE TABLE user(             
    num INT  AUTO_INCREMENT primary key,
    username VARCHAR(19) UNIQUE KEY,
    userpassword VARCHAR(14)  NOT NULL,
    usersex  CHAR(1) NOT NULL,  
    userbirthday DATE NOT NULL,
    useraddcpc VARCHAR(15)  NOT NULL,
    useremail  VARCHAR(20)  NOT NULL,
    userphone VARCHAR(15) NOT NULL,
    useraddr   VARCHAR(20),
    userhobbys VARCHAR(20),
    registertime DATETIME DEFAULT NOW()
)ENGINE = innoDB;
```

>注意点：
>>a.储存手机号用varchar
>>
>>b.DATE类型(系统默认时间)默认值可以设置sysdate()和nowdate()
now() 在执行开始时值就得到了 
sysdate() 在函数执行时动态得到值。


```
//检测用户的唯一性
String sql ="SELECT username FROM user WHERE username='"+username+"'";
                          
rs=st.executeQuery(sql);
if(rs!=null){
    if(rs.next()){
         out.print("<h3>"+username+"用户名已被使用，请改用其他名字</h3>");
    }else{
        out.print("<h3>恭喜，这个用户名"+username+"，可以使用！</h3>");
    }
                                
}else{
    error =error+"________4.结果集未获取到数据;";
}
                           
```

---


实现c

```
//用户名可以使用，执行插入操作
if(error.equals("")){ //数据检测与用户名唯一都通过的话，报错信息err为""
                                       
     //合并日期
    String userbirthday=dateyear+"-"+datemonth+"-"+dateday;
    //合并所在地
     String useraddcpc =addcountry+"国"+addprovince+"省"+addcity+"市";
    //合并爱好
    String userhobbysAND="";
    for(int i=0;i<userhobbys.length;i++){
        if(i==userhobbys.length-1){
            userhobbysAND+=userhobbys[i];
            break;
        }   
        userhobbysAND+=userhobbys[i]+",";
    }
 
    sql="INSERT INTO user(username,userpassword,usersex,"
         + "userbirthday,useraddcpc,useremail,userphone,useraddr,userhobbys)"
         + "VALUES";
    sql +=" ('"+username+"','"+userpassword+"','"+usersex+"','"+userbirthday
             +"','"+useraddcpc+"','"+useremail+"','"+userphone+"','"+useraddr
             +"','"+userhobbysAND+"')";
                                       
     //执行记录的插入操作，并将结果保存在execResult (1)返回行计数 (2)返回0
     int execResult =st.executeUpdate(sql);
                                       
     if(execResult>0){
         out.print("<h2>恭喜，注册成功</h2>");
     }else{
         out.print("<h2>Sorry，注册失败</h2>");
          error =error+"________5.注册失败;";
    }
}

```

---

6.9
---------------------

1.注册功能_3
　　 a.尝试PreparedStatement接口,对SQL语句进行优化
　　 b.页面跳转控制

---

实现1
```

a.
sql="INSERT INTO user(username,userpassword,usersex,"
+ "userbirthday,useraddcpc,useremail,userphone,useraddr,userhobbys)"
+ "VALUES (?,?,?,?,?,?,?,?,?)";

//创建预编译对象，载入SQL语句
pst=con.prepareStatement(sql);

pst.setString(1, username);
pst.setString(2,userpassword);
pst.setString(3,usersex);
pst.setString(4,userbirthday);
pst.setString(5,useraddcpc);
pst.setString(6,useremail);
pst.setString(7,userphone);
pst.setString(8,useraddr);
pst.setString(9,userhobbysAND);

//执行记录的插入操作，将数据插入表中，并将结果保存在execResult (1)返回行计数 (2)返回0
int execResult =pst.executeUpdate();

b.
1.请求转发(同一个请求，多次转发，请求转发后面的代码不会被执行)
1-1.HTML传值
  <jsp:forward page="registerSuccess.jsp">
                            <jsp:param name="utpye" value="happy" />
                        </jsp:forward>

1-2.URL传值
http://localhost:8084/JSPForumProject/jsp/register.jsp?msg=100

1-3.Jave的请求转发
 request.getRequestDispatcher("registerSuccess.jsp").forward(request,response);


2.请求重定向(发出新请求，上次请求的会丢失)
2.1.直接跳转
response.sendRedirect("registerFail.jsp");  

2.2 5秒后再跳转 (HTTP消息头,秒数;url=重定向网页的url)
 response.setHeader("refresh","5;url=registerSuccess.jsp");


```

>注意：
>>1.response.sendRedirect方法后，页面的其他代码仍然会被执行。因此，若需要终止剩余代码的执行，可在该语句后跟随一个return语句
>>
>>2.请求转发的话，页面后面的代码不会执行
>>
>>3.注意：html页面，无法接收jsp：forward发的request请求


---


6.10(未完成)
----------------------
1.登录页面优化

2.登录功能_1
　　a.连接数据库
　　b.验证用户输入的用户名和密码是否正确


---


6.11
------------------
1.登录功能_1
　　a.尝试连接数据库

---


6.12
--------------
1.登陆界面
　　a.JS验证
　　　　　　。用户名密码不能为空，点击登录的时候进行检测
　　b.登录界面优化


2.登录功能_2
　　a.验证用户输入的用户名和密码是否正确

>注意:
>>1.用java.sql.Statement的executequery（）方法查询的结果集永远不会返回null，可以用rs.next（）方法来判断有没结果集，因为一个结果集最初将游标定位在第一行的前面。而不用rs==null,这样没有效果
>>
>>2.Select语句用Statement比较方便，Insert语句用PreoaredStatement
　　

---

6.13
--------------------
1.登录界面功能_3
　　a.在会话期间维持用户的登录信息(session验证)
    b.在index.jsp页面使用静态包含
` <%@include file="../jsp_include/index_bottom.jsp" %>`

>注意：
>>1.使用inclue指令或者jsp:include>动作均可以包含其他文件的内容
>>
>>2.include指令只能静态包含一个文件的内容，而jsp:include>动作则可以在执行时包含一个页面的执行结果
>>
>>3.>jsp:include>动作可以结合jsp:param>动作向包含的页面传递参数

---

2.登录界面优化
　　a.分离index.html和index.jsp
　　b.index.jsp的(右上角登录注册模块)改为欢迎用户XXX

---

3.添加计时器
　　a.使用JavaScript在登录成功和注册成功页面,
　　　　添加页面跳转倒计时;

    
---

6.14
-------------
1.登录功能_4
　　 a.在线会员名单的显示

```
  //从application对象中取出名称为namelist的属性，保存到向量visitors中
    Vector visitors=(Vector)application.getAttribute("namelist");

    if(visitors ==null){//判断向量是否为空
        visitors=new Vector();
         application.setAttribute("namelist",visitors);
    }

     boolean unique=true;
    for(int i=0;i<visitors.size();i++){  
        //遍历向量，看是否存在重复的用户名
         if(((String)visitors.get(i)).equals(username)){
            unique=false;
            break;
         }                       
    }

    if(unique){
        visitors.add(username);
        // 将指定元素添加到此向量的末尾。
    }


//显示在线的用户

String name=null,showAllUsername="";
    //从application对象取出名单列表
    Vector visitors=(Vector)application.getAttribute("namelist");
            
    if(visitors!=null){//判断名单是否为空
        Iterator it= visitors.iterator(); //创建迭代器
                
    while(it.hasNext()){   //遍历所有用户名单，并且将所有用户名取出后连接成一个字符串
            name =(String)it.next();
            showAllUsername +=" "+name+", ";
    }
                
    if(!showAllUsername.equals("")){   //显示在线名单
        out.println("<p>在线会员:"+showAllUsername+"</p>");
    }
}

```

---

b.退出登录状态
```
  //从application对象中去去除在线名单
    Vector visitors=(Vector)application.getAttribute("namelist");

    visitors.remove(session.getAttribute("username"));
            
     //从session对象中清除用户名
    session.removeAttribute("username");

```

---
　　 c.使用Cookie完成自动登录功能


---

6.15
--------------------------

1.帖子浏览功能的实现_1

　　 a.显示所有帖子的概要信息

a-1
```
//设计帖子信息表----------
帖子ID: INT 主键，自增
主题：VARCHAR(15) 非空
作者：VARCHAR(10) 非空,唯一
发表日期: DATETIME  默认值
回复数：INT
点击数：INT 
最后更新时间 (当前更新时间)

CREATE TABLE article(
    artid INT AUTO_INCREMENT PRIMARY KEY,
    arttheme VARCHAR(50)  UNIQUE KEY NOT NULL,
    artauthor VARCHAR(10) NOT NULL,
    pubdate  DATETIME DEFAULT NOW(),
    replynum INT DEFAULT 0,
    clicknum INT DEFAULT 0,
    upddate DATETIME DEFAULT NOW()
)ENGINE = innoDB;

```

>注意：
>>1.mysql不需要是主键也可以自增的。只要字段是数字类型的 int float double之类的
>>
>>2.mysql中每个表只能设置一个自增字段
>>3.修改列名
`ALTER TABLE article CHANGE COLUMN updatedate update DATETIME DEFAULT NOW();`

---

a-2.js实现首页模块切换效果
```
middleshowfirst.style.display="block";
middleshowsecond.style.display="none";
```

---

a-3 尝试格式化日期输出
```
<%@page import="java.sql.*,java.text.*"%>
     Date pubdate,upddate; //发布时间，更新时间(数据库的Datetime类型)

  /*尝试格式化日期，再输出
     pubdate=rs.getDate("pubdate");//得到Date类型的日期
     upddate=rs.getDate("upddate");
                            
     *SimpleDateFormat sdf=new SimpleDateFormat("yyyy年MM月dd日 ");
     *pubdateT=sdf.format(pubdate);
     *upddateT=sdf.format(upddate);


方法2：直接接收字符串
pubdateT=rs.getString("pubdate")==null ?
         "" : rs.getString("pubdate").substring(0,16);//截取字符串
 upddateT=rs.getString("pubdate")==null ?
        "" : rs.getString("upddate").substring(0,16);
```

---
　　
　　b.搜索帖子(根据作者或者主题)
```

//div搜索模块
<div id="middleFindarticle">
        <!--自提交，提交当原来(当前)页面 -->
    <form method="post" name="findarticle"> 
        <select name="keyChoice">
            <option value="arttheme" selected="selected">主题</option>
            <option value="artauthor" >作者</option>
        </select>
        请输入关键字:<input type="text" name="keyValue" />
                    <input type="submit" value="搜索贴子" />
    </form>                        
</div>


//确定搜索条件
<%
     //确定搜索条件
         request.setCharacterEncoding("utf-8");//设置reuqest信息的编码字符集
                     
        String findsql =" ";//确定搜索条件               
        if(request.getParameter("keyValue")!=null){
            String keyChoice=request.getParameter("keyChoice");
             String keyValue=request.getParameter("keyValue");
                        
            /* 
                将得到的表单数据cipher按iso8859-1编码还原成字节数组，再按UTF-8编码重新编译
                keyValue=new String(keyValue.getBytes("iso8859-1"),"UTF-8");
             */
            findsql="WHERE "+keyChoice+" LIKE '%"+keyValue+"%'";  //模糊查询                         
        }
%>

//SQL语句改为
String sql ="SELECT artid,arttheme,artauthor,"
          + " pubdate,replynum,clicknum,upddate "
          + "FROM article "+findsql+" ORDER BY upddate DESC";//根据upddate进行降序排序
```

>注意:
>>1.Like模糊查询%号后面不要加空格，直接%"+keyChoice
>>
>>2.搜索贴子的时候，输入中文的话如果没有转码，会出现乱码，所以搜索不到，所有要先转为字节数组，再按UTF-8编码重新编译解释


---

c.分页显示------（未完全实现）

出现的问题：
+ 查询模块，查询失败
+ 查询之后，分页无法进行
+ 分页完，查询无法进行


---

6.16
--------------------

1.帖子浏览功能的实现_2

分页功能
　　a.根据主题查询，根据作者查询
　　b.根据总记录数显示页面，每5条记录1页

>仍然存在未解决问题:
>>1.搜索贴子后，展示帖子，如果搜索数量多的话，会出现分页，但是一点击分页，就退出搜索，展示所有帖子
>>
>>2.登陆后，显示首页模块，然后点击帖子展示，搜索帖子后，先显示贴子展示模块


2.优化分页显示模块的CSS


---



6.17
--------------------

回帖功能_1
　　a.textarea
　　b.了解CKeditor(在线编辑器)


---


6.18
-----------------------------
回帖功能_1
　　a.使用Ckeditor
　　b.可以通过配置config.js定制自己的工具栏


---


6.19
---------------
1.回帖功能，在index.jsp设计回帖模块
　　a.将textarea转变为Ckeditor，调节背景，宽度，大小
　　b.JS接收Ckeditor的内容（贴子内容）,id,标题,并进行验证

---

2.设计数据表：

```
回复记录的id(rid) int  主键，自增
回复标题(rtitle)  VARCHAR(20)非空
回复内容(rcontent) VARCHAR(200) 5~200 非空
回复作者(rauthor)  VARCHAR(10) 非空
对应的主题id(artid)  INT 
回复时间rdate()  DATETIEM 默认插入时间

CREATE TABLE reply(
    rid INT AUTO_INCREMENT PRIMARY KEY,
    rtitle VARCHAR(20) NOT NULL,
    rcontent VARCHAR(200) NOT NULL,
    rauthor VARCHAR(10) NOT NULL,
    pid INT NOT NULL,
    rpddate DATETIME DEFAULT NOW()
)ENGINE = innoDB;
```
---

3.在文章表增加新的一列(放在artauthor后面),最后更新作者
`
    ALTER table article ADD COLUMN updauthor VARCHAR(10) NOT NULL AFTER artauthor;
`

---

4.在贴子回复模块输入id，标题，回帖内容，验证过后，
　　a.插入数据库的reply表中，
　　b.并且在贴子展示模块，相应贴子回复数+1，最后回复时间更新

---

5.通过
`rcontent=rcontent.replace("</p>","");`
去除ckeditor里回贴内容的p>和/p>标签，再存入数据库

---



6.20
-----------------------------
无

---


6.21
------------

1.封装MySQL的数据库操作
　　在NetBeans工具里,源包------>创建conndb包

　　a.创建保存属性文件condb.properties
```
driver=com.mysql.jdbc.Driver
url=jdbc:mysql://localhost:3306/forumproject
dateuser=root
dateuserpassword=liushuwei
```

---

　　b.创建类Conndb.java
```
<%@page import="conndb.Conndb" %><!--数据库操作的类 -->

package conndb;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;


public class Conndb {
    private Connection con;
    private Statement st;
    
   //构造函数，在创建Conn类的对象时自动调用
    public Conndb() throws Exception {
        Properties ppts = new Properties();
        InputStream in=Conndb.class.getResourceAsStream("condb.properties");//读取属性文件的4个属性
       ppts.load(in);
       in.close();
        
       String driver=ppts.getProperty("driver");
       String url=ppts.getProperty("url");
       String dateuser=ppts.getProperty("dateuser");
       String dateuserpassword=ppts.getProperty("dateuserpassword");
       
      
       Class.forName(driver);//加载驱动类
       con=DriverManager.getConnection(url,dateuser,dateuserpassword);
       st=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);       
    }
    
    //executeQuery---执行查询语句
    public ResultSet executeQuery(String sql){
        ResultSet rs=null;
        try{
            rs=st.executeQuery(sql);
        }catch(Exception e){
            e.printStackTrace(System.err);
        }
        
        return rs;
    }
    
    //executeUpdate方法 --执行对数据库的更新的SQL语句
    public int executeUpdate(String sql){
        try{
            return st.executeUpdate(sql);
        }catch(Exception e){
            e.printStackTrace(System.err);
            return 0;
        }
    }
    
    //创建预编译对象的方法
    public PreparedStatement prepareStatement(String sql){
        try{
            return con.prepareStatement(sql);
        }catch(Exception e){
            e.printStackTrace(System.err);
            return null;
        }
    }
    
    //关闭各项资源的方法---注意要逆向
    public void close(){
        try{
            if(st!=null)
                st.close();
        }catch(Exception e){
            e.printStackTrace(System.err);
        }
         try{
            if(con!=null)
                con.close();
        }catch(Exception e){
            e.printStackTrace(System.err);
        }
    }
}

```

---

1-2.修改register.jsp(注册验证文件)...部分关键代码
```
Conndb conn=null;
PreparedStatement pst=null;
ResultSet rs =null;

try{
    con = new Conndb();
    String sql ="SELECT username FROM user WHERE username='"+username+"'";
                          
    rs=conn.executeQuery(sql);

    pst=conn.prepareStatement(sql);

}catch(Exception e){
      e.printStackTrace(System.err);
}finally{
    try{
        if(con!=null)
        con.close();
    }catch(Exception e){
        e.printStackTrace(System.err);
    }
}

```

6.22
---------------------

1.使用JavaBean

a.语法
```
 <!-- 创建javabean对象user -->
 <jsp:useBean class="javabean.User" id="user" /> 
<!--自动提取用户填的数据,同名传递-->   
             <jsp:setProperty name="user" property="*" />
<jsp:setProperty name="user" property="userbirthday"
                value="<%=userbirthday%>" />

<!--使用JSP动作输出oo的username属性值-->
<jsp:getProperty name="oo" property="username" />
```

---

b.
b-1将注册完成注册功能封装处理逻辑的方法直接编写到实体类JavaBean中
b-2当相关操作比较多的时候，可分别设计根据表结构映射得到的实体类（例：User）和封装了与该表相关的数据库处理操作的操作类（例：UserBean）
```
///////////////////User.java
/**
 * 2016.6.22  注册功能，处理用户信息的JavaBean
 * @author Liu-shuwei
 */
package javabean;

public class User{
   private String username,userpassword, userpasswordtwo,
                  usersex,userbirthday,
                  useraddcpc,useremail,userphone,
                  useraddr,userhobbys;
   
  
    /*
        所有属性的getter和setter方法
   */
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserpassword() {
        return userpassword;
    }

    public void setUserpassword(String userpassword) {
        this.userpassword = userpassword;
    }

    public String getUserpasswordtwo() {
        return userpasswordtwo;
    }

    public void setUserpasswordtwo(String userpasswordtwo) {
        this.userpasswordtwo = userpasswordtwo;
    }

    public String getUsersex() {
        return usersex;
    }

    public void setUsersex(String usersex) {
        this.usersex = usersex;
    }

    public String getUserbirthday() {
        return userbirthday;
    }

    public void setUserbirthday(String userbirthday) {
        this.userbirthday = userbirthday;
    }

    public String getUseraddcpc() {
        return useraddcpc;
    }

    public void setUseraddcpc(String useraddcpc) {
        this.useraddcpc = useraddcpc;
    }

    public String getUseremail() {
        return useremail;
    }

    public void setUseremail(String useremail) {
        this.useremail = useremail;
    }

    public String getUserphone() {
        return userphone;
    }

    public void setUserphone(String userphone) {
        this.userphone = userphone;
    }

    public String getUseraddr() {
        return useraddr;
    }

    public void setUseraddr(String useraddr) {
        this.useraddr = useraddr;
    }

    public String getUserhobbys() {
        return userhobbys;
    }

    public void setUserhobbys(String userhobbys) {
        this.userhobbys = userhobbys;
    }
   
   
}


//////////////////////UserBean.java

/**
 * 2016.6.22 操作类
 * @author Liu-shuwei
 */
package javabean;

import conndb.Conndb;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class UserBean {
     //基本的数据验证
   public String basicCheck(User u){
       
        String msg=""; //错误信息
         if(u.getUsername()==null || u.getUsername().equals("")   
                        || u.getUserpassword()==null || u.getUserpassword().equals("")){
             msg="________1.用户名和密码不能为空!";
         }else if(!u.getUserpassword().equals(u.getUserpasswordtwo())){
             msg="________2.两次输入的密码不一致！";
         }
         
           if(u.getUsersex()==null || u.getUsersex().equals("")
                        || u.getUseremail()==null || u.getUseremail().equals("")
                        ||u.getUserphone()==null || u.getUserphone().equals("")){
                    msg="________3.性别，用户邮箱，手机号码不能其中有选项为空";                
                }
         return msg;
   } 
   
   //验证用户名唯一性
   public String uniqueName(String username){
          Conndb conn =null;
          ResultSet rs =null;
          String msg=""; //错误信息
      try{  
          conn = new Conndb();
          //检测用户的唯一性
           String sql ="SELECT username FROM user WHERE username='"+username+"'";
                             
         rs=conn.executeQuery(sql);       
         if(rs!=null){
             if(rs.next()){
                  msg=username+"用户名已被使用，请改用其他名字";
             }
         }
          
      }catch(Exception e){
            e.printStackTrace(System.err);
      }finally{
          try{
              if(rs!=null)
                  rs.close();
          }catch(SQLException e){
              e.printStackTrace(System.err);
          }
          conn.close();
      }
      return msg;
   }
   
   //插入插入数据
   public boolean insert(User u){
       boolean sign=false;//判断是否插入成功的标志
       
       Conndb conn =null;
       PreparedStatement pst =null;
       try{
           conn = new Conndb();
         String  sql="INSERT INTO user(username,userpassword,usersex,"
                 + "userbirthday,useraddcpc,useremail,userphone,useraddr,userhobbys)"
                   + "VALUES (?,?,?,?,?,?,?,?,?)";
             pst=conn.prepareStatement(sql);                                  
                  pst.setString(1,u.getUsername());
                  pst.setString(2,u.getUserpassword());
                  pst.setString(3,u.getUsersex());
                  pst.setString(4,u.getUserbirthday());
                  pst.setString(5,u.getUseraddcpc());
                  pst.setString(6,u.getUseremail());
                  pst.setString(7,u.getUserphone());
                  pst.setString(8,u.getUseraddr());
                  pst.setString(9,u.getUserhobbys());
                  
                  sign=pst.executeUpdate()>0;
       }catch(Exception e){
           e.printStackTrace(System.err);
       }
       finally{
           try{
               if(pst!=null)
                   pst.close();
           }catch(SQLException e){
               e.printStackTrace(System.err);
           }
           conn.close();
       }
       
       return sign;
   }

}


//////////////////修改后的register.jsp
%@page contentType="text/html" pageEncoding="UTF-8"%><%-- 设置页面字符集--%>
<%@page import="java.sql.*,java.util.*" %> <%--导入访问数据库需要的包--%>
<%@page import="conndb.*,javabean.*" %><!--数据库操作的类 -->
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>register.jsp页面</title>
    </head>
    <body>
        <h1>接收register.html中的用户填写的注册信息,进行验证</h1><br><hr>
        <h2>这里是提示信息:</h2><br>
            <%
                
                //设置请求参数的编码字符集
                request.setCharacterEncoding("utf-8");
                
                 /*
                    1.定义数据类型
                */  
                //合并出生日期  (日期格式规定yyyy-MM-dd,不能在面前加中文)
                String userbirthday=request.getParameter("dateyear")+"-"
                   +request.getParameter("datemonth")+"-"
                   +request.getParameter("dateday");
                //合并所在地
                String useraddcpc=request.getParameter("addcountry")
                    +request.getParameter("addprovince")+"省"
                     +request.getParameter("addcity")+"市";
                
                //合并爱好集合
                String userhobbys="";
               String [] userhobbysAND=request.getParameterValues("userhobbys");
                                for(int i=0;i<userhobbysAND.length;i++){
                                         if(i==userhobbysAND.length-1){
                                              userhobbys+=userhobbysAND[i];
                                              break;
                                           }   
                                        userhobbys+=userhobbysAND[i]+",";
                                }
            %> 

             <jsp:useBean class="javabean.User" id="user" /> <!-- 创建javabean对象user -->
             <jsp:setProperty name="user" property="*" /><!--自动提取用户填的数据,同名传递-->
             
             <jsp:setProperty name="user" property="userbirthday"
                              value="<%=userbirthday%>" />
             <jsp:setProperty name="user" property="useraddcpc"
                              value="<%=useraddcpc%>"/>
             <jsp:setProperty  name="user" property="userhobbys"
                              value="<%=userhobbys%>"/>
                              
             <%   
                /**
                 * 2.服务器端接收客户端输入
                 *      利用request的getParameter方法得到的值均为String类型。
                 *      可以将取得的数据转换为数值型，常用方法
                 *      String sn="89",sp="12.5";
                 *      int n=Integer.parseInt(sn);
                 *      float n=Float.parseFloat(sp);
                 *         double d= double.parseDouble(sp);
                 */
 
                    
                       UserBean ub =new UserBean();
                       String msg=ub.basicCheck(user);//储存错误信息
                        if(msg.equals("")){
                           msg=ub.uniqueName(user.getUsername());
                       }
                       if(msg.equals("")){
                           if(!ub.insert(user)){
                              msg="Sorry,注册失败";
                           }
                       }
                       if(msg.equals("")){
                           out.print("<h3>恭喜，注册成功,直接跳转到注册成功页面</h3>");                
                            request.getRequestDispatcher("registerSuccess.jsp").forward(request,response);                
                       }else{
                           out.print("<h1>注册失败！！<h1>");
                       }      
                  
            %>
            <br><hr><br>
             
            错误信息: <%out.print(msg);%>
                             
    </body>
</html>

```


---


c.注意：
c-1在使用jsp:setProperty>动作接收表单数据前，应先加入代码request.setCharacterEncoding("utf-8"),以便能正确提取中文的请求参数
c-2若希望使用jsp:setProperty property="*" name="instanceName" />这种形式为JavaBean实例中的属性赋值时，请求参数必须与JavaBean实例中的属性同名

---


6.23
---------------------------
1.Servlet和配置web.xml
```
/**
 *2016.6.23 认识Servlet
 * @author Liu-shuwei
 */

package servlet;

import java.io.*;
import java.util.Date;
import javax.servlet.*;
import javax.servlet.http.*;

public class TryServlet extends HttpServlet{
    protected void doGet(HttpServletRequest request,HttpServletResponse response)
                            throws ServletException,IOException{
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out =response.getWriter(); //out对象代表向客户端发送信息的输出流
        out.println("<html>");
        out.println("<head>");
        out.println("<title>一个简单的Serlvet</title>");
        out.println("</head>");
        out.println("<body>");
        out.println(new Date());
        out.println("<br>欢迎来到Serlvet学习区");
        out.println("</body>");
        out.println("</html>");
        out.flush();
        out.close();
        
        /*
            会话对象session是HttpSession类型的对象
        */
        HttpSession session=request.getSession();
        /*
            application为ServletContext类型的对象，
            不论有多少个客户端在访问，每个应用只有一个application对象
        */
        ServletContext application =session.getServletContext();
        
    }
    
    protected void doPost(HttpServletRequest request,HttpServletResponse response)
                            throws ServletException,IOException{
            doGet(request,response);                     
    }
}


///////////////////web.xml的<web-app>和</web-app>标记间插入以下内容
<servlet>
        <servlet-name>OneTry</servlet-name>
        <servlet-class>servlet.TryServlet</servlet-class>
        <load-on-startup>1</load-on-startup>
</servlet>
<servlet-mapping>
        <servlet-name>OneTry</servlet-name>
        <url-pattern>/one</url-pattern>
        <!--指定映射路径-->S
</servlet-mapping>
```

---

2.NetBeans配置上下文路径
右键项目---->属性---->运行----->调整上下文路径
代码获得
`  举例：
<form  action="<%=request.getContextPath()%>/one" method="POST">
`

3.浏览器的URL地址栏可以直接访问
`http://localhost:8084/JSPForumProject/one`

4.NetBeans可以创建Servlet，自动生成的代码中，doGet和doPost方法均调用processRequest方法，因而此时无论客户端以GET方式还是POST方式发起请求，都将得到相同的访问结果。getServletInfo方法返回当前关于Servlet的描述信息。

---


5.使用Servlet实现注册功能
```
///////////注册页面的表单提交改为
<form action="<%=request.getContextPath()%>"/CheckRegister" method="POST"/ >


/**
 * 2016.6.23 使用Servlet实现检测注册信息
 * @author Liu-shuwei
 */

package servlet;

import javabean.User;
import javabean.UserBean;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;


public class CheckRegister extends HttpServlet {
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        doPost(request,response);
    } 


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        User u=new User();
        u.setUsername(request.getParameter("username"));
        u.setUserpassword(request.getParameter("userpassword"));
        u.setUserpasswordtwo(request.getParameter("userpasswordtwo"));
        
        //基本的数据验证
        UserBean ub =new UserBean();
        String msg=ub.basicCheck(u);
        
        if(msg.equals(""))
             msg=ub.uniqueName(u.getUsername());//验证用户唯一性
        if(msg.equals("")){                 
            u.setUsersex(request.getParameter("usersex"));
            u.setUseremail(request.getParameter("usereamil"));
            u.setUserphone(request.getParameter("userphone"));
            
            if(!ub.insert(u)){
                msg="Sorry,注册失败";
            }
        }
        
        request.setAttribute("msg", msg);//存储此请求中的属性
        
        //请求转发
        RequestDispatcher rd =request.getRequestDispatcher("/jsp/registerSuccess.jsp");
        rd.forward(request, response);
    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}

```

---


6.24
--------------------
无

---

6.25
-----------------------------------
1.使用Ajax实现用户唯一性的即时检测

>Ajax
>>1.是异步JavaSscript和XML(Asynchronous JavaScript and XML)的英文缩写，使用Ajax技术可以改善Web页面的即时交互性，可以实现页面的局部刷新
>>
>>2.核心钙奶年在于使用XMLHttpRequest对象发送异步请求

---

代码实现
```



//修改register.html
/////////////////////1.在head>部分添加
<script src="../js/ajax.js"></script>

/////////////////////2.的用户名输入部分
<tr>
     <td class="middleformtdfirst">
         用户名
      </td>
    <td class="middleformtdsecond">
        <input type="text" name="username" id="username"  class="middleformtdsecondinput"
        onmouseover="inputchangecolor1(this)" 
        onmouseout="inputchangecolor2(this)"
        onfocus=" cleanvalue(this)"
        onblur="chkuser()"  
    />                      <!--这里添加onblur事件-->
    </td>
    <td class="middleformtdthird">
        <span id="checkusername"></span>
    </td> 
</tr>

onFocus事件就是当光标落在文本框中时发生的事件。
onBlur事件是光标失去焦点时发生的事件。



//编写完成异步检测的数个函数，创建ajax.js
/*当页面载入时，先创建xmlhttp异步传输对象*/
try{
    xmlHttp=new ActiveXObject("Msxml2.XMLTTP");
}catch(e){
    try{
        xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
    }catch(e2){
        xmlHttp = false;
    }
}
if(!xmlHttp && typeof XMLHttpRequest !='undefined'){
    
    //创建XMLHttpRequest对象，之后将利用该对象向服务器发出异步请求
    xmlHttp = new XMLHttpRequest(); 
}

/*----------各函数的定义------------------*/

//通用函数----通过id获取DOM对象
function $(id){
    obj =document.getElementById(id);
    return obj;
}

/*
    函数chkuser,在注册页面，用户输入用户名，光标离开输入框时调用
    功能：
        向服务器发起请求，请检查用户名是否重复，并等待查询结果当服务器把处理结果传送回来后，
        调用updatePageUser函数继续处理
*/
function chkuser(){
    var username=$('username').value;

    if(username==null || username==""){
        alert('用户名不能为空，请输入用户名');
        //该方法表示将焦点移动到对象上
        $('username').focus();
        return;
    }

    /*
        设置完成后台检测的url,这里准备检测用户名
        是否重复的Servlet发起请求，同时传递请求参数name(即需要检测的用户名),
        该参数需经过编码，因为可能含有中文
    
        传参数username，three是在web.xml配置的路径,(../是返回根目录)
        encodeURI 方法：返回编码为有效的统一资源标识符 (URI) 的字符串。 
    */
    var url="../three?username="+encodeURI(username); 
                       
    /*
        准备向服务器发起请求，设置请求的url，传递数据的方式为GET，第三个参数true表示采用异步请求
    */
    xmlHttp.open("GET",url,true);
    
   //当服务器的状态改变时候,由函数update进行跟踪处理 (指定由函数update跟踪此次请求的执行过程)
   xmlHttp.onreadystatechange = update;
   
   //发出请求
   xmlHttp.send(null);

}

/*
    函数update
    功能：
        根据服务器传回的处理结果，决定在客户端的页面中显示什么信息
*/
function update(){
    
    /*
     * 读取服务器发回的响应状态码(xmlHttp对象的readyState属性),判断是否已经完成本次请求
     *  
    0：请求未初始化（还没有调用 open()）。
    1：请求已经建立，但是还没有发送（还没有调用 send()）。
    2：请求已发送，正在处理中（通常现在可以从响应中获取内容头）。
    3：请求在处理中；通常响应中已有部分数据可用了，但是服务器还没有完成响应的生成。
    4：响应已完成；您可以获取并使用服务器的响应了。

     */
    if(xmlHttp.readyState!=4){
        //本次请求未完成,在信息提示区显示提示信息
        $('checkusername').innerHTML="正在检测...";
    }else{
        //本次请求完成，并传回响应结果

        //读取服务器发回的响应的内容，这里是服务器执行Servlet(ChkUniqueName)后得到的结果(msg---错误信息)
            var response =xmlHttp.responseText;
            if(response!=""){
                //若在ChkUniqueName.java中检测用户名重复，则返回结果信息不为空
                $('checkusername').innerHTML="很抱歉该用户名已被使用，请重新输入";

                //光标定位在用户名输入框，等待用户重新输入
                $('username').focus();

            }else{
                //若内
                $('checkusername').innerHTML="恭喜，该用户名可以使用";
            }
    }
}//end of function update


//////在服务器端执行检测操作的Servlet(ChkUniqueName.java)
/**
 * 2016.6.25 Servlet,检测用户名的唯一性,
 * 只处理GET方式的请求，所以只重写doGet方法
 * @author Liu-shuwei
 */

package servlet;

import javabean.User;
import javabean.UserBean;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class ChkUniqueName extends HttpServlet{
    protected void doGet(HttpServletRequest request,HttpServletResponse response)
                            throws ServletException,IOException{
        System.out.println("正在检测用户唯一性...............ChkUniqueName.java");
        
        //保证浏览器不缓存次次请求的响应内容，这些代码不能省略，否则有可能引起错误的检测结果       
        response.setHeader("pargma", "no-cache");
        response.setHeader("cache-control","no-cache,must-revalidate");
        response.setHeader("expires", "0");
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        
        User u = new User();//创建User类的对象
        u.setUsername(request.getParameter("username"));//设置username的属性值为用户所输入的用户名
        
        UserBean ub = new UserBean();
        String msg =ub.uniqueName(u.getUsername());//调用方法，进行用户唯一性验证，结果保存到msg
        
        //将检测结果输出到客户端
        PrintWriter out =response.getWriter();
        out.print(msg);
        out.flush();//刷新
        out.close();//关闭输出流
        System.out.println("-----------------检测完毕-----------");
        
    }
}

///在WEB-INF目录下的web.xml的web-app>和/web-app>标签之间添加
<servlet>
        <servlet-name>ChkUniqueName</servlet-name>
        <servlet-class>servlet.ChkUniqueName</servlet-class>
    </servlet>
    <servlet-mapping>
        <servlet-name>ChkUniqueName</servlet-name>   
        <url-pattern>/three</url-pattern>   
    </servlet-mapping>
```


---



6.26-6.27
------------------------
无

---



6.28
---------------------
1.在下载了jspsmartupload，还未加入到项目中


---


6.29-----7.31
------------------
无