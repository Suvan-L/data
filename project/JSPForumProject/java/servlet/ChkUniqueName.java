/**
 * 2016.6.25 Servlet,检测用户名的唯一性,
 * 只处理GET方式的请求，所以只重写doGet方法
 * @author Liu-shuwei
 */

package servlet;

import javabean.User;
import javabean.UserBean;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class ChkUniqueName extends HttpServlet{
    protected void doGet(HttpServletRequest request,HttpServletResponse response)
                            throws ServletException,IOException{
        System.out.println("正在检测用户唯一性...............ChkUniqueName.java");
        
        //保证浏览器不缓存次次请求的响应内容，这些代码不能省略，否则有可能引起错误的检测结果       
        response.setHeader("pargma", "no-cache");
        response.setHeader("cache-control","no-cache,must-revalidate");
        response.setHeader("expires", "0");
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        
        User u = new User();//创建User类的对象
        u.setUsername(request.getParameter("username"));//设置username的属性值为用户所输入的用户名
        
        UserBean ub = new UserBean();
        String msg =ub.uniqueName(u.getUsername());//调用方法，进行用户唯一性验证，结果保存到msg
        
        //将检测结果输出到客户端
        PrintWriter out =response.getWriter();
        out.print(msg);
        out.flush();//刷新
        out.close();//关闭输出流
        System.out.println("-----------------检测完毕-----------");
        
    }
}
