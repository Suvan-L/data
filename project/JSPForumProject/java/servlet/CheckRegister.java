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
