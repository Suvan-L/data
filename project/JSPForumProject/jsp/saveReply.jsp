<%-- 
    Document   : savaReply
    Created on : 2016-6-19, 23:08:46
    Author     : Liu-shuwei
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*,java.text.*,java.*,java.util.*"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>saveReply页面</title>
         <script>
            function $(id){
                obj =document.getElementById(id);
                return obj;
            }
            
            function bodybegin(){
                setInterval(counttime,1000);
            }
            
            var one=2;
            function counttime(){
                $('onetime').innerHTML=one;
                one=one-1;
            }
        </script>
    </head>
    <body onload="bodybegin()">
        <%
            request.setCharacterEncoding("UTF-8");
            
            //1.定义数据类型
            String rtitle,rcontent,rauthor,pid;
            String err;//错误信息
            
            //2.接收数据
            rtitle =request.getParameter("ptitle");//标题
            rcontent=request.getParameter("showCkeditor");//内容
            rauthor=(String)session.getAttribute("username");//回复者
            pid=request.getParameter("pid");//主题贴的id
            
            rcontent=rcontent.replace("<p>","");
            rcontent=rcontent.replace("</p>","");
            
            //3.基本的数据验证
            if(rtitle==null || rtitle.equals("")){
                err="1-标题不能为空______";
                out.print("<h4>1-标题不能为空</h4>");
                response.sendRedirect("index.jsp?pid="+pid);
                return;
            } 
            if(rcontent==null ||rcontent.equals("")){
                err="2.1-回复内容不能为空______";
                  out.print("<h4>2。1-回复内容不能为空,3秒后返回登录页面</h4>");
                   response.setHeader("refresh", "3;url=index.jsp");
            }else if(rcontent.length()<5 || rcontent.length()>200){
                 err="2.2-回复内容长度不符合要求______";
                  out.print("<h4>2.2-回复内容长度不符合要求,3秒后返回登录页面</h4>");
                   response.setHeader("refresh", "3;url=index.jsp");
            }
            
            //4.连接数据库
            String jdbc="com.mysql.jdbc.Driver";
            String url="jdbc:mysql://localhost:3306/forumproject";
            String dateuser="root";
            String dateuserpassword="liushuwei";
            
            Connection con=null;//定义连接对象
            PreparedStatement pst=null;//定义预编译对象
            try{
                
                Class.forName(jdbc);
                con =DriverManager.getConnection(url,dateuser,dateuserpassword);             

                String sql="INSERT INTO reply(rtitle,rcontent,rauthor,pid) VALUES"
                        + "(?,?,?,?)";//4个占位符
                
                //创建预编译对象，载入SQL语句
                pst=con.prepareStatement(sql);
                pst.setString(1, rtitle);
                pst.setString(2, rcontent);
                pst.setString(3, rauthor);
                pst.setInt(4, Integer.parseInt(pid));
                
                //executeQuery()是执行查询，executeUpdate() 是执行DDL和DML的三种（增，删，更新）
                pst.executeUpdate();
                
                //格式化日期
               SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
               java.util.Date now = new java.util.Date();
               //回复数+1，最后回复时间更新
               sql="UPDATE article SET replynum=replynum+1,upddate='"
                       +sm.format(now)+"',updauthor='"+rauthor+"' WHERE artid="+pid;
               
               pst=con.prepareStatement(sql);
               pst.executeUpdate();
               
               %>
                    <h3>恭喜回复成功,<span id="onetime">3</span>秒后跳转回首页</h3>
                <%
               response.setHeader("refresh", "3;url=index.jsp");
            }catch(Exception e){
                e.printStackTrace(System.err);
            }finally{
                //关闭资源
                 try{
                    if(pst!=null)
                        pst.close();
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
        %>
    </body>
</html>
