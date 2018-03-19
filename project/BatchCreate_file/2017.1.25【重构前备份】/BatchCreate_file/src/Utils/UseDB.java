package Utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/** 更新时间： 2016.12.08
 *   
 *  数据库封装类
 *  		单例模式,【兼容MySQL(导入mysql-connector-java-5.1.38-bin.jar)和SQLite(导入sqlite-jdbc-3.15.1.jar)】
 *  		方法1-基础使用
 *  		方法2-查询 【SELECT】
 *  		方法3-插入【INSERT】
 *  		方法4-执行SQL语句
 *  			
 * 				@author suvan
 *
 */
public class UseDB {
	
	//静态成员变量，支持单例模式
	private static  volatile UseDB udb = null;
	
	
	private static String databaseType;	 //数据库类型【例如:Mysql,Oracle,SQLite之类的】
	
	private String drive;           				 //数据库驱动
	private String link;     						 //【mysql—JDBC链接+IP地址+端口】

	private String username;                  //用户名
	private String password;				    //密码
	
	private Connection conn=null;
	private Statement st=null;
	private PreparedStatement pst=null;
	private ResultSet rs=null;
	private ResultSetMetaData rsmd=null;//获取列的信息
	
	private String sql; //sql语句
	
		
		//有参构造函数进行赋值
		public UseDB(String dbType){
		
			this.databaseType=dbType.toLowerCase();	//设定当前工具类使用什么类型的数据库
			
			if("mysql".equals(databaseType)){	//转换为小写匹配
				
				this.drive="com.mysql.jdbc.Driver";          	
				this.link="jdbc:mysql://127.0.0.1:3306/";     
				this.username="root";                         			
				this.password="liushuwei";				    
				
			}else if("sqlite".equals(databaseType)){
				
				this.drive="org.sqlite.JDBC";           			//数据库驱动
				this.link="jdbc:sqlite:SQLite/";     //jdbc:sqlite:+路径【默认项目根目录/SQLite】+数据库名
			}
		}
	
		
		
		
		//A-1.懒汉式(加双重校验锁):【单例设计模式，获取实例,节约系统资源，提高性能】 ,项目中使用UseDB udb =UseDB.getInstance("javareptile");获取实例
		public static synchronized UseDB getInstance() throws IOException,ClassNotFoundException,SQLException{
			if(udb == null){
					synchronized(UseDB.class){
						if(udb ==null){  //2   双重校验锁，实现延迟加载，解决线程并发，使用volatile指令静止重拍序列化
							udb = new UseDB(databaseType);
							udb.connDatabase("javareptile");
						}
					}
			}
			return udb;
		}
		

		//方法1: 连接database数据库
		public void connDatabase(String database)  throws SQLException,ClassNotFoundException{ //参数：数据库名称
			 Class.forName(drive);
			 if("mysql".equals(databaseType)){
				 conn=DriverManager.getConnection(link+database+"?characterEncoding=UTF-8&useSSL=false",username,password);	
			 }else if("sqlite".equals(databaseType)){
				 conn=DriverManager.getConnection(link+database);	
			 }

			 st=conn.createStatement();
//			 System.out.println("数据库连接成功......");
		}

		//访法1-2: 使用database数据库                                
		public void useDatabase(String database) throws SQLException{//参数: 数据库名称
			
				sql="USE "+database+";";
				rs=st.executeQuery(sql);

			System.out.println("********************目前使用"+database+"数据库**************************");
		}
		
		//方法1-3关闭所有对象
		public void closeAll() throws SQLException{
			
			if(rs!=null)  rs.close();//关闭ResultSet
			if(pst!=null)	pst.close();	//关闭PreparedStatement
			if(st!=null) st.close();//关闭Statement
			if(conn!=null)  conn.close(); //关闭Connection

		}
	
		
//**************************************************************************************************************************************************************************		
		
		//方法2-1:查询table表中record字段满足condition条件的值,返回查询内容【字段值】 ，不能用于 * 查询
		public String select(String table,String record,String condition)throws SQLException{//参数: 表名,字段名,WHERE条件
				StringBuilder sb= new StringBuilder();
				
				sql="SELECT "+record+" FROM " +table+" WHERE "+condition+";";
				rs=st.executeQuery(sql);
				ResultSetMetaData rsm =rs.getMetaData(); //获得列集
				while(rs.next()){
					sb.append(rs.getString(record));
				}

			return sb.toString();
		}
		
		//方法2-2:查询table表中record字段满足condition条件的值,打印到控制台，并返回一个字符串【&换列】
				public String select(String table,String record,String condition,char o)throws SQLException{//参数: 表名,字段名,WHERE条件
						StringBuilder sb= new StringBuilder();
						
						sql="SELECT * FROM " +table+" WHERE "+condition+";";
						rs=st.executeQuery(sql);
						rsmd=rs.getMetaData();
						while(rs.next()){
							for(int i=1;i<=rsmd.getColumnCount();i++){  //获取总列数 数据字段从1开始
								if(rs.getString(i).equals("")){
									sb.append("空&");
								}else{
									sb.append(rs.getString(i)+"_&_");
								}
								System.out.print(rs.getString(i)+"\t");  //输出每个字段值，间隔一个Tab长度
							}
						}

					return sb.toString();
				}
		
		//方法2-3:查询table所有数据的数据,打印到控制台，并返回一个字符串 【&换列，##换行】
		public String select(String table) throws SQLException{//参数: 表名
				StringBuilder sb= new StringBuilder();
				
				sql="SELECT * FROM " +table+";";
				rs=st.executeQuery(sql);
				rsmd =rs.getMetaData(); //获得列集
				
					while(rs.next()){//光标移动
						for(int i=1;i<=rsmd.getColumnCount();i++){  //获取总列数
							sb.append(rs.getString(i)+"&");
							System.out.print(rs.getString(i)+"\t");  //输出每个字段值，间隔一个Tab长度
						}
						System.out.println();//换行
						sb.append("##");
					}
	
			
				return sb.toString();
		}
		
			
		
		//方法2-4:查询table表,record字段的所有记录值(所有行)【##换行】
		public String selectAllColumn(String table,String record) throws SQLException{
			StringBuilder sb= new StringBuilder();
			
			 sql="SELECT "+record+" FROM "+table+";";
			 rs=st.executeQuery(sql);
			 while(rs.next()){
				 sb.append(rs.getString(1)+"##");
			 }
			 
			return sb.toString();
		}
		
		//方法2-5:查询table表,record字段的所有记录值(所有行),用ArrayList保存
				public List<String> selectAllColumn_list(String table,String record) throws SQLException{
					List<String> list = new ArrayList<String>();
					 sql="SELECT "+record+" FROM "+table+";";
					 rs=st.executeQuery(sql);
					 while(rs.next()){
						 list.add(rs.getString(1));
					 }
					 
					return list;
				}
		
		//方法2-6：查询table表,满足content条件【具有唯一性】，有记录的字段直接返回记录值,没有则返回null
		public String selectIfExist_CheckRecord(String table,String record,String condition) throws SQLException{//参数: 表名,字段名，条件
				
				sql="SELECT "+record+" FROM "+table+" WHERE "+condition+";";
				rs=st.executeQuery(sql);
				String record_content=null;
				if(rs.next()){
					record_content=rs.getString(1);
				}

			return record_content;
		}
		
		
		//方法2-7:查询table表,根据条件condition【具有唯一性】,判断整行记录那个字段拥有记录，有记录的字段返回字段名和值【&&&&&分隔】
		public ArrayList<String> selectIfExist_CheckRecord(String table,String condition,char o) throws SQLException{//参数: 表名，条件,*
			
			ArrayList<String> al = new ArrayList<String>();
				
				sql="SELECT * FROM "+table+" WHERE "+condition+";";
				System.out.println(sql);
				rs=st.executeQuery(sql);
				rsmd=rs.getMetaData();

				while(rs.next()){
					for(int i=1;i<=rsmd.getColumnCount();i++){  
						al.add(rsmd.getColumnName(i));//只要列名
					}
				}
			
			return al;
		}
		
		//方法2-8: 查询table表,满足条件condition的,返回规定范围行数的记录的列名【&分隔每列】
		public HashMap<String,String> selectAskinformation_Scope(String table,String condition,String scope) throws SQLException{ //参数:表名,条件,范围行数【使用-进行分隔,例如1-5】
//				StringBuilder sb= new StringBuilder();
			HashMap<String,String> hm = new  HashMap<String,String>();
			 	
				String [] scope_arrays =scope.split("-");
				int begin=Integer.parseInt(scope_arrays[0]);
				int end=Integer.parseInt(scope_arrays[1]);
				
				sql="SELECT * FROM "+table+" WHERE "+condition+";";
				rs=st.executeQuery(sql);
				rsmd=rs.getMetaData();
				while(rs.next()){
					for(int i=begin;i<=end;i++){ //遍历范围区间的字段
						if(rs.getString(i)==null || rs.getString(i).length()<2) continue;
						hm.put(rsmd.getColumnName(i), rs.getString(i));//获取列名和值，存入键值对
					}		
				}

			return hm;	
		}
		
		//方法2-9：查询table所有记录总数，返回记录总数
		public int select_allCount(String table) throws SQLException{//参数：表名
			
			
			sql="SELECT count(*)  FROM " +table+";";
			rs=st.executeQuery(sql);
			rs.next();
			
			int recordCount =Integer.parseInt(rs.getString(1));

			return recordCount;
		}
		
//**************************************************************************************************************************************************************************
		
		//方法3-1: 往table表插入整行数据【按顺序依次匹配第1列到最后1列】
		public void insert(String table,String content) throws SQLException{//参数: 表名,内容
					conn.setAutoCommit(false);//禁止自动提交，待会用事务统一提交【提高效率】
					String sql="INSERT OR IGNORE  INTO  "+table+" VALUES("+content+");";//去重插入【应用于SQLite】遇到重复就跳过OR IGNORE
					int i=st.executeUpdate(sql);
		}
		
		//方法3-2: 往table表record字段,插入一行content数据【少量数据(标题，日期等)】【多字段插入	 ,分隔字段	   ,号分隔内容】
		public void insert(String table,String record,String content) throws SQLException{//参数: 表名,字段,内容
					String sql="INSERT INTO "+table+"("+record+") VALUES("+content+");";
					int i=st.executeUpdate(sql);
					if(i!=-1){
//						System.out.println("*****************"+table+"表INSERT成功！****************");
					}
		}
				
		//方法3-3:往table表record，插入一行content数据 【大量数据(文章，文本内容)】【,分隔字段 &&&&& 分隔内容   ,分隔占位符】
		public void insert(String table,String record,String content,String mark) throws SQLException, UnsupportedEncodingException{//参数值: 表名,字段,内容，占位符(?)
//					content =new String(content.getBytes(),"UTF-8");
			
					String [] content_Arrays=content.split("&&&&&");//分隔内容存入数组
					String [] mark_Arrays = mark.split(",");
					
					//判断是否存在相同记录，如果存在则不执行插入【以第一条record为准】
					String first_record=record.substring(0,record.indexOf(","));
					sql  ="SELECT "+record+" FROM "+table+" WHERE "+first_record+"='"+content_Arrays[0]+"';";
					rs=st.executeQuery(sql);
						
						if(rs.next()){//光标移动
							//存在相同记录
							System.out.println("**********************很抱歉，"+table+"表已经存在"+content_Arrays[0]+"记录,INSERT失败,不进行重复插入！*************************8888");
						}else{
							//不存在相同记录
//							System.out.println("**********************"+table+"表中不存在"+content_Arrays[0]+"记录,正在进行INSERT。。。。。。。。。。。。。");
							sql="INSERT INTO "+table+"("+record+") VALUES("+mark+");";
							
							pst=conn.prepareStatement(sql);
							
							for(int i=1;i<=mark_Arrays.length;i++){//根据占位符个数【?的数量】进行循环: ?号个数			
								pst.setString(i,mark_Arrays[i-1]);
							}
							
							pst.executeUpdate();
//							System.out.println("*****************"+table+"表INSERT成功！****************");
						}
			}
		
		//方法3-4(去重插入1)： 添加唯一索引,使用INSERT INTO ... ON DUPLICATE KEY UPDATE ..【重复插入时更新】
		public void insertUpdate(String table,String record,String content,String UpdateConent) throws SQLException{//参数: 表名,字段,内容,更新内容
			String sql="INSERT INTO "+table+"("+record+") VALUES("+content+") ON DUPLICATE KEY UPDATE "+UpdateConent+";";
			int i=st.executeUpdate(sql);
		}
		
		//方法3-5(去重插入2): 添加唯一索引,使用INSERT IGNORE INTO...【存在相同数据，就跳过该数据】
		public void insertInGore(String table,String record,String content) throws SQLException{//参数: 表名,字段,内容,更新内容
				String sql="INSERT IGNORE INTO "+table+"("+record+") VALUES("+content+");";
				int i=st.executeUpdate(sql);
		}		
		
		//方法3-6(去重插入3):添加唯一索引,使用REPLACE INTO【不重复和INSERT的功能一样,有重复就使用新纪录值替换原来记记录值】
																						 //【表中必须有唯一索引，而且不为空字段，否则就和INSERT完全一样】【将DELETE和INSERT和操作合二唯一】
		public void insertReplace(String table,String record,String content) throws SQLException{//参数: 表名,字段,内容
			String sql="REPLACE INTO "+table+"("+record+") VALUES("+content+");";
			int i=st.executeUpdate(sql);
			//执行REPLACE后，系统返回所受影响行数，=1说明表中没有重复记录，=2则说明有，>2说明有多个唯一索引，有多条记录被删除和插入。
		}
		
		//方法3-6(去重插入4)：使用dual【虚表去重】【度娘度娘....】
		

//**************************************************************************************************************************************************************************
		
		//方法4-1：【执行创建语句】
		public void createSQL(String sql) throws SQLException{
			st.executeUpdate(sql);	
		}
		
		//方法4-2：【执行插入语句】
		public void insertSQL(String sql) throws SQLException{
			st.executeUpdate(sql);
		}
		
		//方法4-3：【执行查找语句】
		public String selectSQL(String sql) throws SQLException{
			StringBuilder sb = new StringBuilder();
			
			rs = st.executeQuery(sql);
			rsmd=rs.getMetaData();
			while(rs.next()){
				for(int i=1;i<=rsmd.getColumnCount();i++){
					System.out.print(rsmd.getColumnName(i)+":"+rs.getString(i)+"\t");
					sb.append(rs.getString(i)+"&&");
				}
				System.out.println();//换行
			}
			
			return sb.toString();
		}
		
		//方法4-4:【执行更新操作】
		public void updateSQL(String sql) throws SQLException{
			conn.setAutoCommit(false);//禁止自动提交
			st.executeUpdate(sql);
			conn.commit();
		}
		
		//方法4-5:【执行删除操作】
		public void deleteSQL(String sql) throws SQLException{
			conn.setAutoCommit(false);//禁止自动提交
			st.executeUpdate(sql);
		    conn.commit();
		}
		
		//**************************************************************************************************************************************************************************
		//事务操作：提交
		public void udbCommit() throws SQLException{
			conn.commit();
		}
}
