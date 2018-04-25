<%-- 
    Document   : registerSuccess
    Created on : 2016-6-9, 20:55:27
    Author     : Liu-shuwei
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>注册成功</title>
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
    <body style="text-align:center;" onload="bodybegin()" >
        <h1>恭喜注册成功</h1><br><hr><br>
            <%
                  String username,//用户名
                       userpassword,//密码
                       userpasswordtwo,//确认密码
                       usersex,//性别
                       dateformat,dateyear,datemonth,dateday,//出生日期
                       addcountry,addprovince,addcity,//所在地
                       useremail,//邮箱
                       userphone,//手机号码                       
                       useraddr;//通信地址
                  
                    String [] userhobbys;//爱好
                    
                    
                username=request.getParameter("username"); 
                userpassword=request.getParameter("userpassword");
                userpasswordtwo=request.getParameter("userpasswordtwo");
                usersex=request.getParameter("usersex");
                
                dateformat=request.getParameter("dateformat");
                dateyear=request.getParameter("dateyear");
                datemonth=request.getParameter("datemonth");
                dateday=request.getParameter("dateday");
                
                addcountry=request.getParameter("addcountry");
                addprovince=request.getParameter("addprovince");
                addcity=request.getParameter("addcity");
                
                useremail=request.getParameter("useremail");
                userphone=request.getParameter("userphone");
                useraddr=request.getParameter("useraddr");
                userhobbys =request.getParameterValues("userhobbys");
            
            %>
              <div>
                <table border="1" width="400">
                    <caption>用户填写的注册信息</caption>
                    <tr>
                        <th>字段</th>
                        <th>数据</th>
                    </tr>
                    <tr>
                        <td>用户名</td>
                        <td><%=username %></td>
                    </tr>
                     <tr>
                        <td>密码</td>
                        <td><%=userpassword %></td>
                    </tr>
                     <tr>
                        <td>确认密码</td>
                        <td><%=userpasswordtwo %></td>
                    </tr>
                     <tr>
                        <td>性别</td>
                        <td><%=usersex %></td>
                    </tr>
                     <tr>
                        <td>生日</td>
                        <td>生日:
                            <% 
                                out.print(dateformat+"---"+
                                dateyear+"年"+
                                datemonth+"月"+
                                dateday+"日");
                            %>
                        </td>
                    </tr>
                    <tr>
                        <td>所在地</td>
                        <td>
                            <%
                                    out.print(addcountry+" "+
                                      addprovince+"省"+
                                      addcity+"市");
                            %>
                        </td>
                    </tr>
                    <tr>
                        <td>邮箱</td>
                        <td><%=useremail %></td>
                    </tr>
                    <tr>
                        <td>手机号码:</td>
                        <td><%=userphone %></td>
                    </tr>
                    <tr>
                        <td>通信地址:</td>
                        <td><%=useraddr%></td>
                    </tr>
                    <tr>
                        <td>爱好</td>
                        <td>                          
                            <%
                                if(userhobbys!=null){
                                    for(int i=0;i<userhobbys.length;i++){
                                        out.print(userhobbys[i]+" ");        
                                    }
                                }            
                            %>
                        </td>
                    </tr>                 
                </table>
            </div>
                        <h3>恭喜注册成功，<span id="onetime">3</span>秒后跳转到登录界面</h3>
                        <%response.setHeader("refresh", "3;url=index.jsp");  %>
    </body>
</html>
