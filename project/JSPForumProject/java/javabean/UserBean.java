
/**
 * 2016.6.22 操作类
 * @author Liu-shuwei
 */
package javabean;

import conndb.Conndb;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class UserBean {
     //基本的数据验证
   public String basicCheck(User u){
       
        String msg=""; //错误信息
         if(u.getUsername()==null || u.getUsername().equals("")   
                        || u.getUserpassword()==null || u.getUserpassword().equals("")){
             msg="________1.用户名和密码不能为空!";
         }else if(!u.getUserpassword().equals(u.getUserpasswordtwo())){
             msg="________2.两次输入的密码不一致！";
         }
         
           if(u.getUsersex()==null || u.getUsersex().equals("")
                        || u.getUseremail()==null || u.getUseremail().equals("")
                        ||u.getUserphone()==null || u.getUserphone().equals("")){
                    msg="________3.性别，用户邮箱，手机号码不能其中有选项为空";                
                }
         return msg;
   } 
   
   //验证用户名唯一性
   public String uniqueName(String username){
          Conndb conn =null;
          ResultSet rs =null;
          String msg=""; //错误信息
      try{  
          conn = new Conndb();
          //检测用户的唯一性
           String sql ="SELECT username FROM user WHERE username='"+username+"'";
                             
         rs=conn.executeQuery(sql);       
         if(rs!=null){
             if(rs.next()){
                  msg=username+"用户名已被使用，请改用其他名字";
             }
         }
          
      }catch(Exception e){
            e.printStackTrace(System.err);
      }finally{
          try{
              if(rs!=null)
                  rs.close();
          }catch(SQLException e){
              e.printStackTrace(System.err);
          }
          conn.close();
      }
      return msg;
   }
   
   //插入插入数据
   public boolean insert(User u){
       boolean sign=false;//判断是否插入成功的标志
       
       Conndb conn =null;
       PreparedStatement pst =null;
       try{
           conn = new Conndb();
         String  sql="INSERT INTO user(username,userpassword,usersex,"
                 + "userbirthday,useraddcpc,useremail,userphone,useraddr,userhobbys)"
                   + "VALUES (?,?,?,?,?,?,?,?,?)";
             pst=conn.prepareStatement(sql);                                  
                  pst.setString(1,u.getUsername());
                  pst.setString(2,u.getUserpassword());
                  pst.setString(3,u.getUsersex());
                  pst.setString(4,u.getUserbirthday());
                  pst.setString(5,u.getUseraddcpc());
                  pst.setString(6,u.getUseremail());
                  pst.setString(7,u.getUserphone());
                  pst.setString(8,u.getUseraddr());
                  pst.setString(9,u.getUserhobbys());
                  
                  sign=pst.executeUpdate()>0;
       }catch(Exception e){
           e.printStackTrace(System.err);
       }
       finally{
           try{
               if(pst!=null)
                   pst.close();
           }catch(SQLException e){
               e.printStackTrace(System.err);
           }
           conn.close();
       }
       
       return sign;
   }

}
