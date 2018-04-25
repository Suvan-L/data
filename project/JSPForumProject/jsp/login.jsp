<%-- 
    Document   : login
    Created on : 2016-6-11, 9:37:24
    Author     : Liu-shuwei
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*,java.lang.*,java.util.*" %><%--Vector在util工具包中 --%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>login.jsp界面</title>
    </head>
    <body onload="bodybegin()">
        <h1 style="text-align:center"> 登录验证页面</h1><br><hr>
         <h2>提示信息：</h2>
         <%
             /*
                1.定义数据类型
              */
             String username;
             String userpassword;
           
             /*
                2.接收数据
             */
             username=request.getParameter("username");
             userpassword=request.getParameter("userpassword");
       
             /*
                3.基本的数据验证
             */
             if(username==null || username.equals("")
                || userpassword==null || userpassword.equals("")){
                   out.print("<h4>用户名和密码不能为空，请重新输入,3秒后返回登录页面</h4>");
                   response.setHeader("refresh", "3;url=../html/login.html");
             }
             
             /*
                4.验证数据库是否存在用户，密码是否相同
             */
             
        
             boolean valid=false;//表示通过登录验证的标志
             Connection con=null;
             Statement st=null;
             ResultSet rs=null;
             String classforname="com.mysql.jdbc.Driver";
             String url="jdbc:mysql://localhost:3306/forumproject";
             String datausername="root";
             String datauserpassword="liushuwei";
             
             try{
              
                 Class.forName(classforname);
                 con =DriverManager.getConnection(url,datausername,datauserpassword);
                 st =con.createStatement();
                 
                 String sql ="SELECT username,userpassword FROM user WHERE username='"+username+"'";
                 rs=st.executeQuery(sql);
                 if(rs!=null){               
                     if(rs.next()){            
                         out.print("<h4>存在"+username+"用户！</h4><br>");
                         valid=userpassword.equals(rs.getString("userpassword"));//如果相等，则验证通过
                         
                        if(valid){           
                     
                            out.println("<h5>"+username+"用户，密码符合，通过验证</h5>"); 
                            session.setAttribute("username",username);
                            
                            //从application对象中去除名称为namelist的属性，保存到向量visitors中
                            Vector visitors=(Vector)application.getAttribute("namelist");
                            if(visitors ==null){//判断向量是否为空
                                visitors=new Vector();
                                application.setAttribute("namelist",visitors);
                            }
                            boolean unique=true;
                            for(int i=0;i<visitors.size();i++){  //遍历向量，看是否存在重复的用户名
                                if(((String)visitors.get(i)).equals(username)){
                                    unique=false;
                                    break;
                                }                       
                            }
                            if(unique){//如果不存在重复的，则执行添加操作
                                visitors.add(username);// 将指定元素添加到此向量的末尾。
                            }
                            
                            //请求转发
                            request.getRequestDispatcher("loginSuccess.jsp").forward(request,response);
                        }else{
                            out.println("<h5>"+username+"用户，密码不符合，验证失败，3秒后返回登录界面</h5>");
                            response.setHeader("refresh", "3;url=../html/login.html");       
                        }          
                     }else{
                     out.println("<h4>抱歉，不存在"+username+"用户,3秒后返回登录界面</h4>");
                     response.setHeader("refresh", "3;url=../html/login.html");                          
                 }
                     
                 }
                 
                 
             }catch(Exception e){
                 e.printStackTrace(System.err);
             }finally{
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
                  try{
                     if(con!=null)
                         con.close();
                 }catch(Exception e){
                     e.printStackTrace(System.err);
                 }
             }
         %>
         
         <hr>
    </body>
</html>
