<%-- 
    Document   : exit
    Created on : 2016-6-14, 9:40:08
    Author     : Liu-shuwei
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>exit.jsp</title>
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
        <h1 style="text-align:center">退出用户登录页面</h1><br><hr>
        <%
            String username =(String)session.getAttribute("username");
            
            //从application对象中去去除在线名单
            Vector visitors=(Vector)application.getAttribute("namelist");
            visitors.remove(session.getAttribute("username"));
            
            //从session对象中清除用户名
            session.removeAttribute("username");
         %>
         <%=username%>用户退出登录成功，<span id="onetime" >3</span>秒后返回首页。。。。。
         <%
            response.setHeader("refresh", "3;url=../index.html");
        %>
    </body>
</html>
