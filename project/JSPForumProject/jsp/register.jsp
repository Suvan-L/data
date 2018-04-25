<%-- 
    Document   : register
    Created on : 2016-6-7, 9:53:57
    Author     : Liu-shuwei
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%><%-- 设置页面字符集--%>
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