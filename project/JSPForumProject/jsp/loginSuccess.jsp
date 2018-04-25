<%-- 
    Document   : loginSuccess
    Created on : 2016-6-12, 21:49:56
    Author     : Liu-shuwei
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*,java.lang.*"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>登录成功页面</title>
        <script>
            function $(id){
                obj =document.getElementById(id);
                return obj;
            }
            
            function bodybegin(){
                setInterval(counttime,1000);
            }
            
            var one=4;
            function counttime(){
                $('onetime').innerHTML=one;
                one=one-1;
            }
        </script>
    </head>
    <body onload="bodybegin()">
        <%
                String username =(String)session.getAttribute("username");
        %>
        <h1 style="text-align:center;">登录成功页面</h1><br><hr>
        <h4>恭喜<%=username%>用户，登录成功</h4>
        &nbsp;<span id="onetime">5</span>秒后跳转页面....
        <%
                response.setHeader("refresh", "5;url=index.jsp");  
        %>
        <br><hr>
        <h5>当前在线的用户： </h5>
        <%
            String name=null,showAllUsername="";
            Vector visitors=(Vector)application.getAttribute("namelist");//从application对象去取出名单列表
            
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
        %>
        <br><hr>
        <h5>将Cookie信息保存到客户机的硬盘或者内存中</h5>
        <%
            //读取用户选择的Cookie保存天数，
            int limit=Integer.parseInt(request.getParameter("savelimit"));
            
            //创建一个新的Cookie，名称为username，取值为用户输入的用户名，
            //由于用户名可能为中中文，因此需要先使用URLEncode类的静态方法encode对其进行编码
            Cookie c = new Cookie("usernameCookie",java.net.URLEncoder.encode
                (request.getParameter("username"),"UTF-8"));
            
            //设置Cookie保存时间，单位为秒
            //如果没设置，默认值是-1，表示关闭浏览器，cookie就消失
            c.setMaxAge(limit*24*60*60);
            
            response.addCookie(c);//将Cookie对象的信息发往客户端
            
            out.print("Cookie保存成功！");
        %>
    </body>
</html>
