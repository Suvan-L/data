<%-- 
    Document   : newjsp
    Created on : 2016-6-21, 18:33:04
    Author     : Liu-shuwei
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="conndb.Conndb,java.sql.*"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <%
               Conndb one =null;
            try{
                 one =new Conndb();
                 out.print("999999999999999999");

            }catch(Exception e){
                out.print("111111111111");
               e.printStackTrace(System.err);
            }
        %>
        <jsp:useBean class="javabean.one" id="oo" />
        <jsp:setProperty name="oo" property="*" />
        
        用户名：
        <jsp:getProperty name="oo" property="username" />
        密码:
         <jsp:getProperty name="oo" property="password" />
    </body>
</html>
