/*
    封装连接MySQL数据库的操作
 */
package conndb;

/**
 * 2016.6.21 
 * @author Liu-shuwei
 */

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;


public class Conndb {
    private Connection con;
    private Statement st;
    
   //构造函数，在创建Conn类的对象时自动调用
    public Conndb() throws Exception {
        Properties ppts = new Properties();
        InputStream in=Conndb.class.getResourceAsStream("condb.properties");//读取属性文件的4个属性
       ppts.load(in);
       in.close();
        
       String driver=ppts.getProperty("driver");
       String url=ppts.getProperty("url");
       String dateuser=ppts.getProperty("dateuser");
       String dateuserpassword=ppts.getProperty("dateuserpassword");
       
      
       Class.forName(driver);//加载驱动类
       con=DriverManager.getConnection(url,dateuser,dateuserpassword);
       st=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);       
    }
    
    //executeQuery---执行查询语句
    public ResultSet executeQuery(String sql){
        ResultSet rs=null;
        try{
            rs=st.executeQuery(sql);
        }catch(Exception e){
            e.printStackTrace(System.err);
        }
        
        return rs;
    }
    
    //executeUpdate方法 --执行对数据库的更新的SQL语句
    public int executeUpdate(String sql){
        try{
            return st.executeUpdate(sql);
        }catch(Exception e){
            e.printStackTrace(System.err);
            return 0;
        }
    }
    
    //创建预编译对象的方法
    public PreparedStatement prepareStatement(String sql){
        try{
            return con.prepareStatement(sql);
        }catch(Exception e){
            e.printStackTrace(System.err);
            return null;
        }
    }
    
    //关闭各项资源的方法---注意要逆向
    public void close(){
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
}
