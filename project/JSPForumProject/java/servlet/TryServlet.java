/**
 *2016.6.23 认识Servlet
 * @author Liu-shuwei
 */

package servlet;

import java.io.*;
import java.util.Date;
import javax.servlet.*;
import javax.servlet.http.*;

public class TryServlet extends HttpServlet{
    protected void doGet(HttpServletRequest request,HttpServletResponse response)
                            throws ServletException,IOException{
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out =response.getWriter(); //out对象代表向客户端发送信息的输出流
        out.println("<html>");
        out.println("<head>");
        out.println("<title>一个简单的Serlvet</title>");
        out.println("</head>");
        out.println("<body>");
        out.println(new Date());
        out.println("<br>欢迎来到Serlvet学习区");
        out.println("</body>");
        out.println("</html>");
        out.flush();
        out.close();
        
        /*
            会话对象session是HttpSession类型的对象
        */
        HttpSession session=request.getSession();
        /*
            application为ServletContext类型的对象，
            不论有多少个客户端在访问，每个应用只有一个application对象
        */
        ServletContext application =session.getServletContext();
        
    }
    
    protected void doPost(HttpServletRequest request,HttpServletResponse response)
                            throws ServletException,IOException{
            doGet(request,response);                     
    }
}
