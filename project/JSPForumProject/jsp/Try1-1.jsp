<%-- 
    Document   : one
    Created on : 2016-6-22, 20:35:04
    Author     : Liu-shuwei
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <!--<form  action="newjsp.jsp"method="POST"> -->
        <form  action="<%=request.getContextPath()%>/one" method="POST">
            用户名:<input type="text" name="username" /><br>
            密码:<input type="text" name="password" /></br>
            <input type="submit" value="登录" />       
        </form>
    </body>
</html>
