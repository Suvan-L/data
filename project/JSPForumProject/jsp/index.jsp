<%-- 
    Document   : index
    Created on : 2016-6-13, 11:23:59
    Author     : Liu-shuwei
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*,java.text.*"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>论坛首页</title>
        
        <link rel="stylesheet" href="../extend/icon/iconfont.css"><!-- 图标-->
        <link rel="stylesheet" href="../css/index_jsp.css">

        <!--JavaScript -->
        <script src="../js/index.js"></script>
        <script src="<%=request.getContextPath()%>/extend/ckeditor/ckeditor.js"></script><!--在线编辑器-->
        <script>
            var ptitle=document.getElementById("ptitle");
            function postChk(){
                if(ptitle.value==null || ptitle.value==""){
                   ptitle.value="回复";
                }
                if(CKEDITOR.instances.showCkeditor.getData()==""){
                    alert('内容不能为空');
                }else{
                    alert('有很多内容。。。。。。。。。');
                }
            }
        </script>
    </head>

    <body onload="bodybegin()">
        <%      
            
            /*
                1.Cookie实现自动登录
            */
            //判断用户是否登录，在seesion中是否存在username属性，如果不存在，则返回登录界面
            String username=(String)session.getAttribute("username");
            
            //判断seesion中去除的用户名是否为null，为null表示未登录
            if(username==null){
                   
                   //从request对象中去除所有的Cookie对象，保存到数组ck中，
                   //该数组的每一个元素都是一个Cookie对象,各标识一向Cookie信息      
                  Cookie [] ck =request.getCookies();
                  
                  if(ck!=null){//若数组ck不为空，则标识有Cookie信息
                      
                    //遍历数组，检查Cookie数组中每一个元素
                    for(int i=0;i<ck.length;i++){                    
                           
                          //判断是否存在，名称为usernameCookie的Cookie
                        if(ck[i].getName().equals("usernameCookie")){
                            
                            username=ck[i].getValue();
                            session.setAttribute("username",java.net.URLDecoder.decode(username,"UTF-8"));
                            break;
                        }
                    }  
                  } 
            }
            
                    //如果不存在Cookie信息，则跳转回登录页面
                    if(username==null){
                            response.sendRedirect("../html/login.html");
                    }
  
            
             /*
                2.贴子展示模块里的，搜索贴子，获取数据库查询条件
            */
                     request.setCharacterEncoding("utf-8");//设置reuqest信息的编码字符集
                     
                     String findsql =" ";//确定搜索条件               
                     if(request.getParameter("keyValue")!=null){    
                         String keyChoice=request.getParameter("keyChoice");
                         String keyValue=request.getParameter("keyValue");
                        /*
                                将得到的表单数据cipher按iso8859-1编码还原成字节数组，再按UTF-8编码重新编译
                                keyValue=new String(keyValue.getBytes("ISO-8859-1"),"utf-8");
                          */
                          findsql="WHERE "+keyChoice+" LIKE '%"+keyValue+"%' ";     
                     }
                 
            /*
                3.获取当前页号
            */
                      int pagingnum=1;//默认页号为1
                      String spaging =request.getParameter("spaging");
                      
                      if(spaging==null){
                          spaging ="1";
                      }                   

                      try{
                          pagingnum =Integer.parseInt(spaging);
                      }catch(Exception e){
                          out.print("贴子展示模块，分页导航栏，获取当前页号出错！！！！");
                          e.printStackTrace(System.err);
                      }
              
                     
            /*
                4.创建数据库连接
            */      
                     Connection con =null;
                     Statement st =null;
                     //PreparedStatement pst=null;
                     ResultSet rs =null;
                     
                     String jdbc="com.mysql.jdbc.Driver";
                     String url="jdbc:mysql://localhost:3306/forumproject";
                     String dateuser="root";
                     String dateuserpassword="liushuwei";
                     
                     try{
                        Class.forName(jdbc);
                        con =DriverManager.getConnection(url,dateuser,dateuserpassword);             
                        st =con.createStatement();
                     }catch(Exception e){
                         e.printStackTrace(System.err);
                         out.print("连接数据库出错,请及时处理!");
                     }
                
                     
            /*
                5.计算分页导航栏的分页号
            */
                    //计算符合的的记录数量
                     int allpaging=0,     //总分页数，
                         allamount=0,     //总记录数，
                         pagingamount=5; //每页显示3条记录
                     
                     //查询符合搜索条件的总记录数
                     String sql="SELECT count(artid) AS 总页数 FROM article "+findsql;
                     
                     rs=st.executeQuery(sql);
                      //判断结果集是否为空，游标向下移是否有数据
                      if(rs!=null && rs.next()){
                          allamount=rs.getInt(1);//获取第1列的字段,等价于rs.getInt("artid"),赋值给allamount(总记录数)
                      }
                      
                      //计算总分页数    如果两者余数等于0          页数刚好                         页数有余，所以+1
                      allpaging= allamount%pagingamount==0 ? allamount/pagingamount:allamount/pagingamount+1;
                      
                      
                      //以当前页好为中点，计算前后各3页的页号
                      int firstp=1,lastp=allpaging;//首页和最后一页
                      
                      if(pagingnum-3>firstp){  //如果当前页面-3 大于 初始1页
                          firstp=pagingnum-3;  //则firstp坐标为当前页面-3页
                      }
                      if(pagingnum+3<lastp){//如果当前页面+3 小于 初始末页
                          lastp=pagingnum+3;  //则lastp坐标为当前页面+3页
                      }
                      

            /*
               6.帖子回复功能                              
            */
               //判断请求参数中是否有相关主题贴的id，如果没有，则无法继续回复操作
               /*int pid=1;
               if(request.getParameter("pid")!=null){
                    try{
                        pid=Integer.parseInt(request.getParameter("pid"));
                    }catch(Exception e){
                        e.printStackTrace(System.err);
                    }
               }*/
               /*
               if(pid==0){
                   //history.back() 方法加载历史列表中的前一个 URL,与在浏览器点击后退按钮相同
                   out.print("<script>history.back()</script>");
                   return;//返回当前函数，不再往下执行
               }*/
               
               
        %>
        
        <!--首页top -->
        <div id="top">
            <div class="topsign">
                <img src="../image/signpig.jpg" alt="please wait..." width="200" height="200" />
                <span class="topspan">ime is a bird for ever on the wing. (时间是一只永远在飞翔的鸟)</span>
            </div>
            <div class="toptitle">
                <h1>轻谈</h1>
            </div>

            <!-- 计时器1,现在时间-->
            <div id="toptimeshow">
                <span id="timeshow"></span>
            </div>
            <!-- 计时器2，计算今天还有多少秒-->

            <div class="topicon">
                <a href="http://www.qq.com/" target="_blank"><i class="icon iconfont">&#xe602;</i></a><!--QQ-->
                <a href="http://weibo.com/" target="_blank"><i class="icon iconfont">&#xe605;</i></a><!--微博-->
                <a href="https://www.zhihu.com/" target="_blank"><i class="icon iconfont">&#xe607;</i></a><!--知乎-->
                <a href="http://www.163.com/" target="_blank"><i class="icon iconfont">&#xe604;</i></a><!--网易-->
            </div>
                <%
                    if(session.getAttribute("username")==null){
                %>
                   <div class="toploginreg">
                        <a href="../html/login.html" target="_blank">登录</a>
                        <a href="../html/register.html" target="_blank">注册</a>
                   </div>
                <%}else{%>
                   <div class="topwelcomeusername">
                        欢迎<b><%=username%></b>
               
                  </div>
                           <div class="toploginreg">
                            <a href="exit.jsp" target="_blank">退出登录</a>
                            
                        </div>
                 <%}%>
              
         
            <div class="topcatalog">      
                    <a onclick="changeModule(1)" onmouseover="changeModule(1)">首页</a>
                    <a  onclick="changeModule(2)" onmouseover="changeModule(2)">帖子展示</a>
                    <a  onclick="changeModule(5)" onmouseover="changeModule(5)">帖子回复</a> 
                    <a   onclick="changeModule(3)" onmouseover="changeModule(3)">官方推荐</a>
                    <a  onclick="changeModule(4)" onmouseover="changeModule(4)">个人空间</a>
                     
            </div>
        </div>
        <br>

        <!--首页middle -->
        <div id="middleshow">
          <div id="middleshowfirst">
            <div class="middleleftcalalog">   
                <ul>         
                    <li><a href="">生活专题</a></li>
                    <li><a href="">情感专题</a></li>
                    <li><a href="">学习专题</a></li>
                </ul>         
            </div>
            <div class="middlerollpicture">
                <marquee  height="450px" behavior="alternate" loop="6"onMouseOut="this.start()" direction="down"  onMouseOver="this.stop()">
                    <a href="http://www.suvan.net.cn/" alt="图片正在加载，请稍后...">
                        <img src="../image/card.jpg" width="200" height="200" />
                    </a>
                </marquee>
            </div>
            <div class="middleleftcalalog2">
                <ul>
                    <li><a href="">旅行</a></li>
                    <li><a href="">身边</a></li>
                    <li><a href="">时光</a></li>
                </ul>
            </div>
            <div id="middlefirstarticle">
                <h1>序</h1>
                <p>&nbsp;&nbsp;&nbsp;&nbsp;大家好，我是Suvan，这是一个微型论坛，我取名为"轻谈"，在这里，我将会把我的一些想法实现出来。</p>
                <hr>
            </div>
            <div id="middleform">
                <table border="0" cellspacing="1" cellpadding="5" width="1000px">
                        <tr>
                            <td width="200">
                                <img src="../image/yesterday.jpg" alt="please wait..." width="250" height="250"/>
                            </td>
                            <td width="800">
                                    <p>&nbsp;&nbsp;&nbsp;&nbsp;Do not, for one repulse, forgo the purpose that you resolved to effort. ( Shakespeare ) </p>
                                 <p>&nbsp;&nbsp;&nbsp;&nbsp;不要只因一次挫败，就放弃你原来决心想达到的目的。（莎士比亚） </p>
                             </td>
                            
                        </tr>

                        <tr>
                            <td width="200">
                                <img src="../image/today.jpg" alt="please wait..." width="250" height="250"/>
                            </td>
                            <td width="800">
                                 <p>&nbsp;&nbsp;&nbsp;&nbsp;Miracles sometimes occur, but one has to work terribly for them. ( C. Weizmann ) </p>
                                 <p>&nbsp;&nbsp;&nbsp;&nbsp;奇迹有时候是会发生的，但是你得为之拼命蒂努力。（魏茨曼） </p>
                            </td>
                        <tr>
                            <td width="200">
                                <img src="../image/tommorrow.jpg" alt="please wait..." width="250" height="250"/>
                            </td>
                            <td width="900">
                                 <p>&nbsp;&nbsp;&nbsp;&nbsp;A certain amount of care or pain or trouble is necessary for every man at all times .A ship without a ballast is unstable and will not go straight. (Arthur Schopenhauer. Geman philosopher) </p>
                                 <p>&nbsp;&nbsp;&nbsp;&nbsp;一定的忧愁、痛苦或烦恼,对每个人都是时时必需的.一艘船如果没有压舱物,便不会稳定,不能朝着目的地一直前进.(德国哲学家 叔本华 A)  </p>
                            </td>
                        </tr>
                
                </table>
            </div>
          </div>
            
          <div id="middleshowsecond">
              <!--分页导航栏，显示分页号的链接-->
              <div id="middlepagingarticle">
                  <%
                       for(int i=firstp;i<=lastp;i++){ //页号展示的firstp坐标 -----> lastp坐标   
                            if(i==pagingnum){
                                out.println(i);
                            }else{
                                out.println("<a href='index.jsp?spaging="+i+"'>"+i+"</a>");
                            }
                       }   
                  %>
              </div>
              <!--搜索贴子模块 -->
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
            <!--展示所有帖子-->
            <div id="middleallarticle">
                 <%
                     int id,replynum,clicknum;//id，回复数，点击数
                     String arttheme,artauthor,updauthor;//主题，作者,最后更新者             
                     Date pubdate,upddate; //发布时间，更新时间(数据库的Datetime类型)
                     String pubdateT,upddateT;//格式化后的时间      
                 try{                                   
                       sql ="SELECT artid,arttheme,artauthor,updauthor,"
                                  + "pubdate,replynum,clicknum,upddate "
                                  + "FROM article "+findsql+" ORDER BY upddate DESC "//根据upddate进行降序排序
                                  + "LIMIT "+(pagingnum-1)*pagingamount+","+pagingamount;//根据页号变量pagingnum的值，查询出一页的记录
                     rs=st.executeQuery(sql);
                 %>  
                 <table id="middleallarticletable" border="1" cellspacing="0">
                     <caption class="middlemiddleallarticlecaption">贴子展示</caption>
                     <tr class="middleallarticlefirstROW">
                         <td>id</td>
                         <td>主题</td>
                         <td>作者</td>
                         <td>最后更新</td>
                         <td>回复数</td>
                         <td>点击数</td>
                     </tr>
                 <%      
                     if(rs!=null){
                        
                        //next()将结果s集的移动到下一条，如果存在，则返回true
                        while(rs.next()){
                                                   
                            id=rs.getInt("artid");
                            replynum=rs.getInt("replynum");
                            clicknum=rs.getInt("clicknum");
                            
                            arttheme=rs.getString("arttheme");
                            artauthor=rs.getString("artauthor");
                            updauthor=rs.getString("updauthor");
                            pubdateT=rs.getString("pubdate")==null ?
                                    "" : rs.getString("pubdate").substring(0,16);//截取字符串
                            upddateT=rs.getString("pubdate")==null ?
                                    "" : rs.getString("upddate").substring(0,16);
                            
                            /*尝试格式化日期，再输出
                             pubdate=rs.getDate("pubdate");//得到Date类型的日期
                             upddate=rs.getDate("upddate");
                            
                             *SimpleDateFormat sdf=new SimpleDateFormat("yyyy年MM月dd日 ");
                             *pubdateT=sdf.format(pubdate);
                             *upddateT=sdf.format(upddate);
                            */
  
                          %> 
                          <tr>                     
                              <td class="middlemiddleallarticleColumn1"><%=id%></td>
                              <td class="middlemiddleallarticleColumn2"><%=arttheme%></td>
                              <td class="middlemiddleallarticleColumn1">
                                  <%=artauthor%><br>
                                  <%=pubdateT%>
                              </td>
                              <td class="middlemiddleallarticleColumn1">
                                  <%=updauthor%><br>
                                   <%=upddateT%>
                              </td>
                              <td class="middlemiddleallarticleColumn1"> <%=replynum%></td>
                              <td class="middlemiddleallarticleColumn1"> <%=clicknum%></td>   
                          </tr>
                         <%
                        }
                        
                     }else{                    
                        %>
                        <h1 style="text-align:center">抱歉，现在暂无贴子...</h1>
                        <%
                      }
                }catch(SQLException e){
                     e.printStackTrace(System.err);
                  
                }finally{

                    //释放资源
                    
                    try{
                        if(rs!=null)
                            rs.close();
                    }catch(Exception e){
                        e.printStackTrace(System.err);
                    }
                    
                    try{
                        if(st!=null)
                            st.close();
                    }catch(Exception e){
                        e.printStackTrace(System.err);
                    }

                /*  try{
                        if(pst!=null)
                            pst.close();
                    }catch(Exception e){
                        e.printStackTrace(System.err);
                    }
                 */
                    try{
                        if(con!=null)
                            con.close();
                    }catch(Exception e){
                        e.printStackTrace(System.err);
                    }
                }
                 %>
                </table>
            </div>
          </div>
           <!-- 帖子回复模块-->
           <div id="middleshowfifth">          
                <form action="saveReply.jsp" methdod="post" onsubmit="return checkPtitleCkeditor()">
                    <div id="middleshowfifthCkeditor">
                        <h2>帖子回复：</h2>
                        <!--隐藏域pid 表示主题帖的pid-->
                        贴子id:<input type="text" name="pid" id="pid" size="5" onfocus="cleaninput(this)"/>
                        标题:<input type="text" name="ptitle" id="ptitle" value="请输入标题...." size="90" onfocus="cleaninput(this)"/>
                        
                        <textarea id="showCkeditor" name="showCkeditor"></textarea>  <!-- extend/ckeditor/config.js可以自定义-->
                        <input type="submit" value="发表" />&nbsp;
                        <input type="button" value="返回" onclick="history.back()" />&nbsp; <Br> 
                        <h3>提示信息：</h2>
                        <span id="checkpid" class="check">无</span>
                        <span id="checkptitle" class="check"></span><br>
                        <span id="checkshowCkeditor" class="check"></span>            
                    </div>
                </form> 
          </div>
                
           <!--官方推荐模块-->
           <div id="middleshowthird">       
                <div id="middleshowthirdTextarea"> <!-- 输入回帖-->
                     <h3>这里是textarea：</h3>
                     <from method="post"><!--使用textarea-->
                        <textarea name="content" cols="60" rows="10">在此处填写意见....</textarea>
                        <br>
                        <input type="submit" value="提交回复内容" />
                     </from>
                </div>
          </div>
          <div id="middleshowfourth">
          </div>
            <div id="middleresiduetime">
                <span id="residuetime"></span>
            </div>
        <!--首页bottom -->
        <div id="bottom">
            <%@include file="../jsp_include/index_bottom.jsp" %>
        </div>
    </body>
</html>  