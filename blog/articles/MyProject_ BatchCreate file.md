---
title: BatchCreate file
date: 2017-01-09 18:21:23
tags: MyProject
categories: MyProject
---


# 前言
用时2天半左右，写了一个批量生成目录文件的 demo（BatchCreate_file【自动生成】）
不算太简洁，目的是为了重点巩固基础，还有很大进步的空间


自己写了一些基础,常用的 method，封装进不同的类里

一.用于测试的主方法: *testMain.java*
二.任务流程: *TaskFlow.java*
三.连接，操作数据库：*UseDB*
四.操作文件,目录：*IOFile.java*
五.创建文件，改变文件内容: *ChangeFile.java*
六.两套相关模版:
七.设计数据库:



如果有错误或者逻辑比较混乱的地方，欢迎探讨指出^_^

---


# 目录
- 版本1.0【2016.8.27】
- 版本2.0【2016.11.15】
- 版本【3.0】 2016.11.27
- 5.3版【2017.1.9】
- 5.4版【2017.1.9下午】


## 版本1.0【2016.8.27】


### 1.testMain.java
```
package review;

public class testMain {
  

  //main()方法，测试
  public static void main(String [] args){
  
    //创建任务流程对象
    TaskFlow tf= new TaskFlow();
    
    //执行流程(参数:路径,目录名)
    tf.GO("C:/Users/Liu-shuwei/Desktop\\","刘淑玮-第二批(03069-03127)","SX");

      
  }
}

```

---


### 2. TaskFlow.java
```
package review;

public class TaskFlow {
  
  public static long  useTime(){
    return System.currentTimeMillis();
  }
  
  //无参构造方法
  public TaskFlow(){
    
    
  }
  
  //任务流程
  public void GO(String path,String name,String province) {
    //开始时间
    long beginTime=useTime();
    
    //执行任务流程
    try{
      UseDB udb = new UseDB();
      IOFile iof=new IOFile();
      
      udb.connDatabase();//连接数据库
      
      //一.往数据库插入模版数据
      String content=null;                                      //注意，路径的话用/或者\\都可以
      content=iof.readFileContent("C:/Users/Liu-shuwei/Desktop/模版文档(修改过)/BackupTask.java","UTF-8"); //数据库的默认编码是GBK,如果是UTF-8\
      udb.insertTXT("article","model,detail","BackupTask&&&&&"+content,"?,?");
      content=iof.readFileContent("C:/Users/Liu-shuwei/Desktop/模版文档(修改过)/ZhaobGgServiceModel.java","UTF-8"); 
      udb.insertTXT("article","model,detail","ZhaobGgServiceModel&&&&&"+content,"?,?");

      
      //二.创建整体目录
      iof.cList(path,name,province);
      
      //关闭资源
      udb.closeAll();
      
    }catch(Exception e){
      e.printStackTrace();
    }
    
    //结束时间
    long endTime=useTime();
    System.out.println("总共用时:"+(endTime-beginTime)+"毫秒！");
  }
}

```

---


### 3. UseDB.java
```
package review;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class UseDB {
  
  
  private String drive="com.mysql.jdbc.Driver";           //数据库驱动
  private String link="jdbc:mysql://127.0.0.1:3306/";     //mysql—JDBC链接+IP地址+端口
  private String database="my_blog";                      //数据库
  
  private String username="root";                         //用户名
  private String password="123";            //密码
  
  private Connection conn;
  private Statement st;
  private PreparedStatement pst;
  private ResultSet rs;
  private ResultSetMetaData rsmd;//获取列的信息
  
  private String sql; //sql语句
  

  //无参构造方法，创建UseDB对象时候，直接连接数据库
  public UseDB(){ 

  }
  
  //调用该方法时候连接数据库
  public void connDatabase()  throws Exception{
     Class.forName(drive);
     conn=DriverManager.getConnection(link+database,username,password); 
     st=conn.createStatement();
     System.out.println("数据库连接成功......");
  }

    //功能1: 使用database数据库                                
    public void useDatabase(String database){//参数: 数据库名称
      sql="USE "+database+";";
      try{
        rs=st.executeQuery(sql);
      }catch(Exception e){
        e.printStackTrace();
      }
      System.out.println(sql+" 执行完毕！-------------------------------------");
    }
    
    //功能2:查询table表中record字段满足condition条件的值
    public String select(String table,String record,String condition){//参数: 表名,字段名,WHERE条件
      
      StringBuilder sb= new StringBuilder();
      sql="SELECT "+record+" FROM " +table+" WHERE "+condition+";";
      
      try{
        rs=st.executeQuery(sql);
           ResultSetMetaData rsm =rs.getMetaData(); //获得列集
        while(rs.next()){
          sb.append(rs.getString(record));
        }

      }catch(Exception e){
        e.printStackTrace();
      }
      System.out.println(sql+" 执行完毕！-------------------------------------");
      return sb.toString();
    }
    
    //功能3:查询table所有数据的数据,打印到控制台
    public String selectAll(String table){//参数: 表名
      
      StringBuilder sb= new StringBuilder();
      sql="SELECT * FROM " +table+";";
      
      try{
        rs=st.executeQuery(sql);
        rsmd =rs.getMetaData(); //获得列集
        while(rs.next()){
          for(int i=1;i<rsmd.getColumnCount();i++){
            System.out.print(rs.getString(i)+"\t");
          }
          System.out.println();//换行
        }

      }catch(Exception e){
        e.printStackTrace();
      }
      System.out.println(sql+" 执行完毕！-------------------------------------");
      return sb.toString();
    }

    
    //功能4:查询webapi表,判断招标预告到控制价字段的记录值是否存在URL  ,返回有URL记录的字段名(例如:zbgg)
    public String checktable(String num){//参数: 编号
      StringBuilder sb= new StringBuilder();
      sql="SELECT * FROM webapi WHERE num='"+num+"';";
      String content=null;
      try{
        rs=st.executeQuery(sql);
        rsmd=rs.getMetaData();
        while(rs.next()){
          for(int i=10;i<18;i++){
            content=rs.getString(i);
            if(content.length()<5) continue;
              
              sb.append(rsmd.getColumnName(i)+"&"); 
          }
        }
      
      }catch(SQLException e){
        e.printStackTrace();
      }
      System.out.println(sql+" 执行完毕！-------------------------------------");
      return sb.toString();
    }
    
    
    
    //功能5:查询table表,record字段的所有记录值(所有行)
    public String selectNum(String table,String record) throws SQLException{
      StringBuilder sb= new StringBuilder();
      
       sql="SELECT "+record+" FROM "+table+";";
       rs=st.executeQuery(sql);
       while(rs.next()){
         sb.append(rs.getString(1)+"&");
       }
       
      System.out.println(sql+" 执行完毕！-------------------------------------");
      return sb.toString();
    }
    
    
    
    
    //功能6: 插入数据--少量数据(标题，日期等)
    public void insert(String table,String record,String content){//参数: 表名,字段(多的话用,分割),内容(多的话用,号分割)
        String sql="INSERT INTO "+table+"("+record+") VALUES("+content+");";
          int i=0;
          try{
            i=st.executeUpdate(sql);
            
          }catch(Exception e){
            e.printStackTrace();
          }

          if(i!=-1){
            System.out.println("插入成功");
          }
        System.out.println(sql+"执行完毕！");
      }
    
    //功能7:插入数据--大量数据(文章，文本内容)
    public void insertTXT(String table,String record,String content,String mark){//参数值: 表名,字段,内容(多个内容用&号分割),?号(多个?用,分割)
      
      sql="INSERT INTO "+table+"("+record+") VALUES("+mark+");";
      
      String [] strA=content.split("&&&&&");
      //判断是否存在相同记录，如果存在则不执行插入
      String ifwhere=record.substring(0,record.indexOf(","));
      String ifexist="SELECT "+record+" FROM "+table+" WHERE "+ifwhere+"='"+strA[0]+"';";
        
      try{
        rs=st.executeQuery(ifexist);
        System.out.println(ifexist+" 执行完毕！================================");
        
        if(rs.next()){//存在相同记录
          System.out.println("数据库中已经存在相同"+strA[0]+"模版,不进行重复插入!");
        }else{
          //不存在相同记录
          System.out.println("数据库不存在"+strA[0]+"模版,正在进行插入......");
          pst=conn.prepareStatement(sql);
          
          for(int i=1;i<=strA.length;i++){//循环次数: ?号个数      
            pst.setString(i,strA[i-1]);
          }
          
          pst.executeUpdate();
        }

      }catch(Exception e){
        e.printStackTrace();
      }
      
      System.out.println(sql+" 执行完毕！================================");
    }
    
  
    
    
    
    //功能8: 查询数据库weiabi表中对应num编号整行记录的需求字段的信息(区域，省份，城市，区县，网站名称，信息来源)
    public String selectNeedInformation_num(String num){
        StringBuilder sb= new StringBuilder();
      
      sql="SELECT * FROM webapi WHERE num='"+num+"';";
      try{
        rs=st.executeQuery(sql);
        while(rs.next()){
          for(int i=2;i<=8;i++){ //区域-信息来源
            if(i==7) continue; //网站网址
            sb.append(rs.getString(i)+"&");
          }   
        }
      }catch(Exception e){
        e.printStackTrace();
      }
      
      System.out.println(sql+" 执行完毕！-------------------------------------");
      return sb.toString(); 
    }
    
    //关闭数据库连接
    public void closeAll(){
      
      //关闭Result
       try{
                if(rs!=null)
                     rs.close();
             }catch(Exception e){
                 e.printStackTrace(System.err);
             }
       
       
      //关闭PreparedStatement
      try{
      if(pst!=null)
                  pst.close();
            }catch(Exception e){
               e.printStackTrace(System.err);
            }
            
      
      //关闭Statement
       try{
                 if(st!=null)
                     st.close();
             }catch(Exception e){
                 e.printStackTrace(System.err);
             }
      
       
       //关闭Connection
           try{
               if(conn!=null)
                   conn.close();
           }catch(Exception e){
               e.printStackTrace(System.err);
           }
    
    }
}


```

---


### 4. IOFile.java
```
package review;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class IOFile {
  
    //功能1:(字节流)读取文件内容--只适用与读取英文，数字，无法设置编码格式
    public String readFileContent2(String path) throws IOException{ //参数:路径
      String content="";
      
      InputStream inf=new FileInputStream(path);
      int size=inf.available();
      for(int i=0;i<size;i++){
        content+=(char)inf.read();
      }
      
      return "\'"+content+"\'"; 
    }
    
    //功能2:(字符流)读取文件内容--可以设置编码，读取中文，英文，数字
      public String readFileContent(String path,String encoding) throws IOException{ //参数: 路径,编码格式
        StringBuilder sb =new StringBuilder();
          
          //定位文件
          File f=new File(path);
          FileInputStream fip=new FileInputStream(f);
          InputStreamReader reader=new InputStreamReader(fip,encoding);
          
          while(reader.ready()){
            sb.append((char)reader.read());
          }
          
        return sb.toString(); 
       }    
    
    
    
    //功能3: 获得路径，创建目录
    public void createFile(String path){
      File d=new File(path);
          d.mkdir();
    }
    
    //功能4: 创建文件,写入内容
    public void cFile(String path,String fileName,String encoding,String content) throws IOException{ //参数：路径,文件名.格式,编码格式,文件内容
      File f=new File(path+"/"+fileName);
      FileOutputStream fop= new FileOutputStream(f);
      OutputStreamWriter writer=new OutputStreamWriter(fop,"UTF-8");
      
      writer.append(content);
      
      writer.close();
      fop.close();
      System.out.println("文件创建完毕");
    }
    
    
    //功能5: 自定义创建目录(标准格式提交,例如:刘淑玮-第二批(02307-02370))
    public void cList(String path,String fileName,String province) throws Exception{ //参数：路径 ,文件名
      
        //一.创建对象  1.连接数据库  2.改变文件
        UseDB u =new UseDB();
        u.connDatabase();
        ChangeFile cf=new ChangeFile(u);
      
        //二.创建父目录
        String dir=path+"/"+fileName;
        createFile(dir);
            
        //三.获得编号,储存起始编号，终止编号
            String number=fileName.substring(fileName.indexOf("(")+1,fileName.lastIndexOf(")"));
            String [] num=number.split("-");
            int first=Integer.parseInt(num[0]);
            int last=Integer.parseInt(num[1]);
            
            //获取使用者名字
            String username=fileName.substring(0,fileName.indexOf("-"));
              
            
            //四.查询webapi表里num字段所有记录，将其保存在数组里
            u.useDatabase("my_blog");//使用my_blog数据库
            String allNum=u.selectNum("webapi","num");
          String [] allNumA=allNum.split("&");
           
          //五.计算webapi中num个数(包括带-的)
          int sum=0;
          String allNumber=u.selectNum("webapi","num");
          String [] allNumber_Arrays=allNumber.split("&");
          for(int i=0;i<allNumber_Arrays.length;i++){
            if(i>1){
              if(!allNumber_Arrays[i].substring(0,5).equals(allNumber_Arrays[i-1].substring(0,5)))
                sum++;
              else
                continue;
            }
          }
            
            //六.生成1级子目录      规格:"接口(n个)&入库(n个)&入库-增量(n个)"
                
                String [] str={"接口","入库","入库-增量"};
                String sonFile=null;//1级子目录
                String sonFile2=null;//2级子目录
                String ifNum=null;//2级目录判定编号
                
                for(int i=0;i<str.length;i++){   
                  sonFile=dir+"/"+str[i]+"("+sum+")";//路径
                  this.createFile(sonFile);  //循环创建1级子目录
                  
                  //进入含有"入库"字样的二级字目录
                  if(str[i].indexOf("入库")!=-1){
                    //生成入库编号
                    for(int j=first;j<=last;j++){ //循环次数：终止编号-起始编号
                      for(int k=0;k<allNumA.length;k++){ //循环次数：数据库webapi表里num记录的数量
                        
                        ifNum=allNumA[k].substring(1,5);
                        if(ifNum.equals(j+"")){   //对比输入编号和Excel表里的编号
                          
                          sonFile2=sonFile+"/入库-0"+j; 
                          createFile(sonFile2); //创建2级子目录
                          
                          if(k==0)
                            cf.createBackupTask(sonFile2+"/",province,allNumA[k],allNumA[0]);//创建BackupTask.java
                          else
                            cf.createBackupTask(sonFile2+"/",province,allNumA[k],allNumA[k-1]);//创建BackupTask.java
                          
                          sonFile2=sonFile2+"/imp";
                          createFile(sonFile2);//创建3级子目录
                          if(str[i].indexOf("入库-增量")!=-1) continue;  //增量不生成入库代码
                          cf.combineCreateRukuCoding(sonFile2+"/",province,allNumA[k],username);
                        }
                      }

                    }
                  }
                }
            
           System.out.println(fileName+"目录生成完毕!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
           u.closeAll();//关闭资源
    }
    
    

    
    
}


```

---


### 5. ChangeFile.java
```
package review;

import java.io.IOException;


public class ChangeFile {
  
  private String zhaobYgService="zhaobYgService";     //招标预告
  private String zhaobGgService="zhaobGgService";   //招标公告
  private String zisJgService="zisJgService";     //咨审结果
  private String zhaobWjService="zhaobWjService";   //招标文件
  private String gonggBgService="gonggBgService";   //公告变更
  private String zhaobDyService="zhaobDyService";     //招标答疑
  private String zhongbXxService="zhongbXxService"; //中标信息
  private String kongZjService="kongZjService";   //控制价
  
  private String model; //BackupTask模版语句

  
  private UseDB udb;
  private IOFile iof;
  
  //getter和Setter方法
  public String getModel(){
    return model;
  }
  public void setModel(String model){
    this.model=model;
  }

  
  //无参构造方法
  public ChangeFile(UseDB u) throws Exception{
    String model="NameA=(NameB) ac.getBean(\"fileName\");";
    String model2="NameA.initNameB();";
    this.setModel("\n\t\t"+model+"\n\t\t"+model2+"\n\t\t");//换行和空格Tab
    
    udb=u;
    iof=new IOFile();
  }
  
  
  //工具1:将首字母变为大写
  public String UpFirstString(String content){ //参数: 内容
    String firstLetter=content.substring(0,1);
    //替换首字母
    content=content.replaceFirst(firstLetter,firstLetter.toUpperCase());
    return content;
  }
  
  //工具1-2:将首字母变为小写
  public String LowFirstString(String content){ //参数: 内容
    String firstLetter=content.substring(0,1);
    //替换首字母
    content=content.replaceFirst(firstLetter,firstLetter.toLowerCase());
    return content;
  }
  
  //工具2:自动生成文件名    
  public String cFileName(String province,String num,String attributeName){//参数:省份拼音缩写(大写),编号,相应的属性名
    StringBuilder sb= new StringBuilder();
    
    num=num.replace("-","_");
    attributeName=UpFirstString(attributeName);
    sb.append(LowFirstString(province)+"_"+num+"_"+attributeName);
    
    return sb.toString();
  }
  
  //工具3:根据BackupTask模版语句,生成相应字符串
  public String cModelString(String province,String num,String attributeName){//参数: 省份,编号,相应的属性名
    StringBuilder sb=new StringBuilder();
    
    
    String fileName=cFileName(province,num,attributeName);
    String cStr=getModel();
    cStr=cStr.replace("NameA",attributeName).replace("NameB",UpFirstString(attributeName)).replace("fileName",fileName);
    
    sb.append(cStr);
    return sb.toString();
  }
  
  //工具4:生成内容注解
  public String cContentNode(String infSource,String modelName,String num){
    StringBuilder sb= new StringBuilder();
    
    //根据模版名字判断类型
    String type="";
    
    if(modelName.equals("zbyg")){
      type="招标预告";
    }else if(modelName.equals("zbgg")){
      type="招标公告";
    }else if(modelName.equals("zsjg")){
      type="咨审结果";
    }else if(modelName.equals("ggbg")){
      type="公告变更";
    }else if(modelName.equals("zbwj")){
      type="招标文件";
    }else if(modelName.equals("zbdy")){
      type="招标答疑";
    }else if(modelName.equals("zbxx")){
      type="中标信息";
    }else if(modelName.equals("kzj")){
      type="招标控制价";
    } 
    
    sb.append("东软一期-"+infSource+"-"+type+"-"+num);
    
    return sb.toString();
  }
  
  
  //工具5: 判断一个编号有需要创建哪些入库代码，循环调用createRukuCoding方法进行
  public void combineCreateRukuCoding(String path,String province,String num,String username){//路径,省份拼音首字母(大写),编号，程旭猿
    
    
    //查询数据库，确认类型
    String type="";
    
    String ifURL=udb.checktable(num);
    String [] ifURL_Arrays=ifURL.split("&");
    for(int i=0;i<ifURL_Arrays.length;i++){
      createRukuCoding(path,province,num,ifURL_Arrays[i],username);
    }
  }
  
  
  //功能1:创建BackupTask.java                                                        (上一个编号，用来处理-)
  public void createBackupTask (String path,String province,String num,String ageNum){//参数: 路径,省份拼音缩写,编号,上一个编号

    //一.检查数据库webapi表,对应num(编号)的整条记录，招标预告-招标公告哪个字段有URL
    String ifURL=udb.checktable(num);
    String [] ownURLArrays=ifURL.split("&");
    
    //二.生成相应内容
    String content=""; //储存总体字符串
    String str="";     //中转字符串
    if(ownURLArrays.length==0){
      System.out.println("这个编号为空，不存在url，跳过");
    }else{
      for(int i=0;i<ownURLArrays.length;i++)  //webapi表格里有url的字段
      {
        
        if(ownURLArrays[i].equals("zbyg")){
          str=cModelString(province,num,zhaobYgService);
        }else if(ownURLArrays[i].equals("zbgg")){
          str=cModelString(province,num,zhaobGgService);
        }else if(ownURLArrays[i].equals("zsjg")){
          str=cModelString(province,num,zisJgService);
        }else if(ownURLArrays[i].equals("ggbg")){
          str=cModelString(province,num,gonggBgService);
        }else if(ownURLArrays[i].equals("zbwj")){
          str=cModelString(province,num,zhaobWjService);
        }else if(ownURLArrays[i].equals("zbdy")){
          str=cModelString(province,num,zhaobDyService);
        }else if(ownURLArrays[i].equals("zbxx")){
          str=cModelString(province,num,zhongbXxService);
        }else if(ownURLArrays[i].equals("kzj")){
          str=cModelString(province,num,kongZjService);
        }
                
        content+=str;
      }
      content+="//ok;";
     }

    //三.判断当前编号是否和上一个编号相同
    boolean sign=false;//设立标示
    if(num.equals(ageNum) || !num.substring(0,5).equals(ageNum.substring(0,5))){
      sign=true;
    }
    
    //四.根据数据库表article表中模版，生成相应编号的文件
    try{
      String dbModelBackupTask=udb.select("article","detail","model='BackupTask'"); //储存数据库article表中,BackupTask模版
    
      if(sign){
        //与上一个编号完全不同(号码不同,且不带-)
        dbModelBackupTask=dbModelBackupTask.replace("//changeModel;",content);
        iof.cFile(path,"BackupTask.java","UTF-8",dbModelBackupTask);//路径,文件名,编码格式,内容
      
      }else{
        //带-的编号,在上一个文件基础上进行叠加
        String ageContent=iof.readFileContent(path+"/BackupTask.java","UTF-8");//前一个编号的内容   
        String changeContent=ageContent.replace("//ok;",content);
        iof.cFile(path,"BackupTask.java","UTF-8",changeContent);
      }
      
    }catch(Exception e){
      e.printStackTrace();
    }
  }
  
  //功能1:创建相应编号,类型的入库代码
  public void createRukuCoding(String path,String province,String num,String type,String username){//参数: 路径,省份拼音首字母缩写,编号,类型,程序猿
    
        //一.生成文件名
        String fileName=""; //注解名
        String typeSpell="";
        String typeService="";
        
          for(int i=0;i<8;i++)  //共8种类型
          {       
            if(type.equals("zbyg")){
              fileName=cFileName(province,num,zhaobYgService);
              typeSpell="zhaoBiaoYuGao";
              typeService="zhaobYgService";
            }else if(type.equals("zbgg")){
              fileName=cFileName(province,num,zhaobGgService);
              typeSpell="zhaoBiaoGongGao";
              typeService="zhaobGgService";
            }else if(type.equals("zsjg")){
              fileName=cFileName(province,num,zisJgService);
              typeSpell="ziShenJieGuo";
              typeService="zisJgService";
            }else if(type.equals("ggbg")){
              fileName=cFileName(province,num,gonggBgService);
              typeSpell="gongGaoBianGeng";
              typeService="gonggBgService";
            }else if(type.equals("zbwj")){
              fileName=cFileName(province,num,zhaobWjService);
              typeSpell="";
              typeService="zhaobWjService";
            }else if(type.equals("zbdy")){
              fileName=cFileName(province,num,zhaobDyService);
              typeSpell="zhaoBiaoWenJian";
              typeService="zhaobDyService";
            }else if(type.equals("zbxx")){
              fileName=cFileName(province,num,zhongbXxService);
              typeSpell="zhongBiaoXinXi";
              typeService="zhongbXxService";
            }else if(type.equals("kzj")){
              fileName=cFileName(province,num,kongZjService);
              typeSpell="kongZhiJia";
              typeService="kongZjService";
            }       
         }
          
        //首字母变为小写
        String fileNameL=LowFirstString(fileName);
        
        //首字母变为大写
        String typeSpellUp=UpFirstString(typeSpell);
        String typeServiceUp=UpFirstString(typeService);
          
        //二.查询数据库模版
        String modelName=fileName.substring(fileName.lastIndexOf("_")+1);
        String modelContent=udb.select("article","detail","model='ZhaobGgServiceModel'");
        
        //三.查询数据库num编号的整行记录,所需要要的信息
        String information_num=udb.selectNeedInformation_num(num);
        String [] information_Arrays=information_num.split("&");
        
        String area=information_Arrays[0];                      //区域
        String provinceDB=information_Arrays[1].replace("省","");          //省份(不要省字)
        String city=information_Arrays[2].replace("市","");              //城市(不要市字)
        String county=information_Arrays[3].replace("区","").replace("县","");    //区县(不要区,县字)
        String webname=information_Arrays[4];                   //网站名称
        String infsource=information_Arrays[5];                   //信息来源
        
        //三.生成顶部内容注解
        String contentNode=cContentNode(infsource,type,num);
        
        //四.修改模版
        String rukuContent=modelContent.replace("zhaoBiaoGongGao",typeSpell).replace("ZhaoBiaoGongGao",typeSpellUp).replace("ZhaobGgService",typeServiceUp).replace("zhaobGgService",typeService).replace("zbgg",type);
        //程序猿;
        rukuContent=rukuContent.replaceFirst("//程序猿;",username).replaceFirst("//注解内容;",contentNode);
        rukuContent=rukuContent.replaceFirst("//注解;",fileNameL).replaceFirst("//类名;",fileName);
        
        rukuContent=rukuContent.replaceFirst("//编号;",num);
        
        rukuContent=rukuContent.replaceFirst("//area;",area);
        rukuContent=rukuContent.replaceFirst("//provinceDB;",provinceDB);
        rukuContent=rukuContent.replaceFirst("//city;",city);
        rukuContent=rukuContent.replaceFirst("//county;",county);
        rukuContent=rukuContent.replaceFirst("//webname;",webname);
        rukuContent=rukuContent.replaceFirst("//infsource;",infsource);
        
        try{
          //创建文件
          iof.cFile(path,fileName+".java","UTF-8",rukuContent);//路径,文件名,编码格式,内容
        }catch(IOException e){
          e.printStackTrace();
        }
        
  }
}


```



---


### 6. 两套java文件模版

#### a. BackupTaskt.java
```
package com.bonait.dataextract.scheduler;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.bonait.dataextract.service.GonggBgService;
import com.bonait.dataextract.service.KongZjService;
import com.bonait.dataextract.service.ZhaobDyService;
import com.bonait.dataextract.service.ZhaobGgService;
import com.bonait.dataextract.service.ZhaobWjService;
import com.bonait.dataextract.service.ZhaobYgService;
import com.bonait.dataextract.service.ZhongbXxService;
import com.bonait.dataextract.service.ZisJgService;

public class BackupTask extends QuartzJobBean {

  private static ZhaobGgService zhaobGgService;
  private static ZhongbXxService zhongbXxService;
  private static ZhaobWjService zhaobWjService;
  private static ZhaobDyService zhaobDyService;
  private static ZisJgService zisJgService;
  private static GonggBgService gonggBgService;
  private static KongZjService kongZjService;
  private static ZhaobYgService zhaobYgService;
  

  @Override
  protected void executeInternal(JobExecutionContext arg0)
      throws JobExecutionException {
  }

  public static void main(String[] args) {
    ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
    
    //changeModel;
    
  }
}
```

---

#### b. ZhaobGgServiceModel.java
```
package com.bonait.dataextract.service.impl;
import javax.annotation.Resource;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;


import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Service;

import com.bonait.dataextract.dao.BaseDao;
import com.bonait.dataextract.domain.*;
import com.bonait.dataextract.service.*;
import com.bonait.dataextract.util.Util;
import com.bonait.dataextract.vo.*;


/**
 * @程序猿：//程序猿;
 * 
 * @内容：//注解内容;
 */
@Service("//注解;")
public class //类名; implements ZhaobGgService{

  @Resource
  private SessionFactory sf ;
  private Query query;
  private ZhaoBiaoGongGao zbgg;
  //列表页接口地址
  private String listUrl="";
  //内容页接口地址
  private String detailUrl="";
  //入库编码
  private String sourceNo="//编号;";
  @Override
  public void initZhaobGgService() {
    try{
            //获取总页数
          String s=Util.sendGet(listUrl,"1");
          JSONObject obj =JSONObject.fromObject(s);
          while (null == obj.get("pageCount")){
           s = Util.sendGet(listUrl, "1");
           obj = JSONObject.fromObject(s);
          }
    int maxPage=Integer.parseInt(obj.get("pageCount").toString());


    Session session=sf.openSession();
    session.beginTransaction();

    //清空数据库里
//    query=session.createSQLQuery("DELETE FROM t_zhao_biao_gong_gao t where t.WEB_SOURCE_NO='"+sourceNo+"'");
//    query.executeUpdate();
//    session.getTransaction().commit();
//    session.beginTransaction();


    int KK=0;
      flag:
      for(int i=1;i<=maxPage;i++){//maxPage
        String jsonList=Util.sendGet(listUrl,i+"");
        JSONObject objList =JSONObject.fromObject(jsonList);

        while(null == objList.get("list")){
          jsonList = Util.sendGet(listUrl, i + "");
          objList = JSONObject.fromObject(jsonList);
        }

        JSONArray temp=(JSONArray) objList.get("list");
        List list = (List)JSONArray.toList(temp,ListVO.class);
        Iterator it = list.iterator();

        int rows = 1;
        for(int row =1;row < rows;row++){
          it.next();
        }

        while(it.hasNext()){
          ListVO listvo =(ListVO) it.next();
          query=session.createSQLQuery("SELECT ID FROM t_zhao_biao_gong_gao t where t.WEB_SOURCE_NO='"+sourceNo+"' and t.RECORD_ID='"+listvo.getId()+"'");
          String getDate=listvo.getDate();
          //获取页数，便于插入数据异常时，先跳过该页
          System.out.println(i+":"+getDate+":"+maxPage);
          
              int date = Integer.parseInt(getDate.substring(0, 4));
              //只取2014年至今的不重复数据 
            if(query.executeUpdate()<=0&&date>=2014){
              KK+=1;
              String detail = null;
            JSONObject o = null;
            String content = null;
            while (content == null || content.length() < 10) {
              detail = Util.sendGet(detailUrl, listvo.getId());
              if (null != detail)
                o = JSONObject.fromObject(detail);
              else
                continue;
              if (null != o&&null != o.get("content"))
                content = o.get("content").toString();
              else
                continue;
              System.out.println("runing..");
            }
              //过滤掉无效数据
              if(content==null||content.length()<10||content.contains("出错")||content.contains("找不到文件")){continue;}
              zbgg=new ZhaoBiaoGongGao();
              zbgg.setWebSourceNo(sourceNo);
              zbgg.setArea("//area;");//区域
              zbgg.setProvince("//provinceDB;");//省份
              zbgg.setCity("//city;");//城市，没有可不填
              zbgg.setDistrict("//county;");// 区县 不要“区”字符
//              zbgg.setCity(listvo.getCiy());
              zbgg.setWebSourceName("//webname;");//网站名称，写完整的名称
              zbgg.setInfoSource("//infsource;");//信息来源
              //未匹配到工程或服务的，都标识为货物
                String infoType=Util.getInfoType(content);
                if(infoType!=null&&infoType.length()>0)
                zbgg.setInfoType(Util.getInfoType(content));
                else {zbgg.setInfoType("货物");}
//              zbgg.setInfoType("服务");//信息类型
//              zbgg.setIndustry("建筑建材"); //行业分类
//              zbgg.setIndustry(Util.getIndustry(content));//行业分类
              zbgg.setRecordId(listvo.getId());
              zbgg.setId(UUID.randomUUID().toString());
              zbgg.setPageTitle(listvo.getTitle());
              zbgg.setPageTime(getDate);
              zbgg.setPageContent(content);
              zbgg.setPageAttachments("");//附件url，暂不需要
              zbgg.setCreateTime(new Date());
            session.save(zbgg);
             }else if(date<=2013){break flag;}//到2013年的页码时，断掉循环
            if(KK%1==0){
              session.flush();  
              session.clear();
              session.getTransaction().commit();
              session.beginTransaction();
            }
        }  
      } 

      
    session.getTransaction().commit();
    session.close();
    System.out.println("000000000");
    
    } catch (Exception e) {
      System.out.println(sourceNo+"接口出错，请检查");
    }
  }

}

```

---


### 7.设计数据库

数据库:Mysql 5.7
数据库工具:Navicat for MySQL10.7
JDBC-Mysql驱动包版本:mysql-connector-java-5.1.38-bin.jar


1. 创建速据库
  `create database my_blog`


2. 创建表article(用于保存模版)
```
 CREATE TABLE article(             
    id INT  AUTO_INCREMENT primary key,
    model VARCHAR(30) NOT NULL,
    detail VARCHAR(20000) NOT NULL,
    date DATETIME DEFAULT NOW()
)ENGINE = innoDB DEFAULT CHARSET=utf8;
```


3. 创建webapi(导入excel表格数据,保存进该表)
```
CREATE TABLE webapi(             
    num VARCHAR(20) primary key,
    area VARCHAR(255) NOT NULL,
    province VARCHAR(255) NOT NULL,
    city VARCHAR(255) NOT NULL,
    county VARCHAR(255) NOT NULL,
    webname VARCHAR(255)  NOT NULL,
    weburl  VARCHAR(255)  NOT NULL,
    infsource VARCHAR(255) NOT NULL,
    inftype   VARCHAR(255),
    worktype VARCHAR(255),
    zbyg  VARCHAR(255),
    zbgg    VARCHAR(255),
    zsjg  VARCHAR(255),
    ggbg VARCHAR(255),
    zbwj VARCHAR(255),
    zbdy VARCHAR(255),
    zbxx VARCHAR(255),
    kzj VARCHAR(255),
)ENGINE = innoDB DEFAULT CHARSET=utf8;
```

---


3-2使用 navicat for MySQL工具导入Excel表格到数据库自动生成

>操作步骤：
>>打开工具--->连接数据库--->左键双击选择数据库--->右键子菜单的表---->导入向导---->
>>-------->选择Excel文件(2007或者以上版本)(*.xlsx)，下一步,之后根据提示操作即可


查看webapi表结构的命令：  
`show create table webapi;`



---


<br><br>

## 版本2.0【2016.11.15】

<br><br>

+ 2.0升级版,只生成入库目录,webapi表增加三列字段,创建新的数据库batchcreate_file
+ 分成了review包和Utils包，主要修改了ChnageFile.java,IOFile和UseDB.java
+ 可自动获取区域名的拼音首字母缩写【GetFirstLetter】，入库模版发生了变化，代码大幅度修改，整体思路不变
+ Macicat for MySQL导入2013的Excel表格会出错，所以需要将excel表另存为【Excel 97-2003工作蒲(*.xls)】
+ 耗时6个小时左右，脑子还是不够灵活，最近犹如咸鱼一般，加油吧，骚年

<br><br><br>

## 1. testMain.java和TaskFlow.java

1.testMain
```
package review;


public class testMain {
    

    //main()方法，测试
    public static void main(String [] args){
    
        //创建任务流程对象
        TaskFlow tf= new TaskFlow();
        
        //执行流程(参数:路径,目录名)
        tf.GO("C:/Users/Liu-shuwei/Desktop\\","02053-02873","某男子");

    }
}
```

---


2.TaskFlow
```
package review;

import Utils.IOFile;
import Utils.UseDB;

public class TaskFlow {
    
    public static long  useTime(){
        return System.currentTimeMillis();
    }
    
    //无参构造方法
    public TaskFlow(){
        
        
    }
    
    //任务流程
    public void GO(String path,String numberArea,String username) {
        //开始时间
        long beginTime=useTime();
        
        //执行任务流程
        try{
            UseDB udb = new UseDB();
            IOFile iof=new IOFile();
            
            udb.connDatabase();//连接数据库
            
            //一.往数据库插入模版数据
            String content=null;                                                                            //注意，路径的话用/或者\\都可以
            content=iof.readFileContent("model_file/BackupTask.java","GBK"); //数据库的默认编码是GBK,如果是UTF-8\
            udb.insertTXT("article","model,detail","BackupTask&&&&&"+content,"?,?");
            content=iof.readFileContent("model_file/ZhaobGgServiceModel.java","GBK"); 
            udb.insertTXT("article","model,detail","ZhaobGgServiceModel&&&&&"+content,"?,?");

            
            //二.创建整体目录
            iof.cList(path,numberArea,username);
            
            //关闭资源
            udb.closeAll();
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        //结束时间
        long endTime=useTime();
        System.out.println("总共用时:"+(endTime-beginTime)+"毫秒！");
    }
}

```

<br>

---


## 2. UseDB.java,IOFile.java和ChangeFile.java
1.UseDb
```
package Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class UseDB {
    
    
    private String drive="com.mysql.jdbc.Driver";           //数据库驱动
    private String link="jdbc:mysql://127.0.0.1:3306/";     //mysql—JDBC链接+IP地址+端口
    private String database="batchcreate_file";                      //数据库
    
    private String username="root";                         //用户名
    private String password="liushuwei";                    //密码
    
    private Connection conn;
    private Statement st;
    private PreparedStatement pst;
    private ResultSet rs;
    private ResultSetMetaData rsmd;//获取列的信息
    
    private String sql; //sql语句
    

    //无参构造方法，创建UseDB对象时候，直接连接数据库
    public UseDB(){ 

    }
    
    //调用该方法时候连接数据库
    public void connDatabase()  throws Exception{
         Class.forName(drive);
         conn=DriverManager.getConnection(link+database,username,password); 
         st=conn.createStatement();
         System.out.println("数据库连接成功......");
    }

        //功能1: 使用database数据库                                
        public void useDatabase(String database){//参数: 数据库名称
            sql="USE "+database+";";
            try{
                rs=st.executeQuery(sql);
            }catch(Exception e){
                e.printStackTrace();
            }
            System.out.println(sql+" 执行完毕！-------------------------------------");
        }
        
        //功能2:查询table表中record字段满足condition条件的值
        public String select(String table,String record,String condition){//参数: 表名,字段名,WHERE条件
            
            StringBuilder sb= new StringBuilder();
            sql="SELECT "+record+" FROM " +table+" WHERE "+condition+";";
            
            try{
                rs=st.executeQuery(sql);
                   ResultSetMetaData rsm =rs.getMetaData(); //获得列集
                while(rs.next()){
                    sb.append(rs.getString(record));
                }

            }catch(Exception e){
                e.printStackTrace();
            }
            System.out.println(sql+" 执行完毕！-------------------------------------");
            return sb.toString();
        }
        
        //功能3:查询table所有数据的数据,打印到控制台
        public String selectAll(String table){//参数: 表名
            
            StringBuilder sb= new StringBuilder();
            sql="SELECT * FROM " +table+";";
            
            try{
                rs=st.executeQuery(sql);
                rsmd =rs.getMetaData(); //获得列集
                while(rs.next()){
                    for(int i=1;i<rsmd.getColumnCount();i++){
                        System.out.print(rs.getString(i)+"\t");
                    }
                    System.out.println();//换行
                }

            }catch(Exception e){
                e.printStackTrace();
            }
            System.out.println(sql+" 执行完毕！-------------------------------------");
            return sb.toString();
        }

        
        //功能4:查询webapi表,判断招标预告到控制价字段的记录值是否存在URL  ,返回有URL记录的字段名(例如:zbgg)
        public String checktable(String num){//参数: 编号        
            StringBuilder sb= new StringBuilder();
            sql="SELECT * FROM webapi WHERE num='"+num+"';";
            String content=null;
            try{
                rs=st.executeQuery(sql);
                rsmd=rs.getMetaData();
                while(rs.next()){
                    for(int i=10;i<18;i++){
                        content=rs.getString(i);
                        if(content.length()<3) continue;
                            
                        sb.append(rsmd.getColumnName(i)+"&");   
                    }
                }
            
            }catch(SQLException e){
                e.printStackTrace();
            }
            System.out.println(sql+" 执行完毕！-------------------------------------");
            return sb.toString();
        }
        
        
        
        //功能5:查询table表,record字段的所有记录值(所有行)
        public String selectNum(String table,String record) throws SQLException{
            StringBuilder sb= new StringBuilder();
            
             sql="SELECT "+record+" FROM "+table+";";
             rs=st.executeQuery(sql);
             while(rs.next()){
                 sb.append(rs.getString(1)+"&");
             }
             
            System.out.println(sql+" 执行完毕！-------------------------------------");
            return sb.toString();
        }
        
        
        
        
        //功能6: 插入数据--少量数据(标题，日期等)
        public void insert(String table,String record,String content){//参数: 表名,字段(多的话用,分割),内容(多的话用,号分割)
                String sql="INSERT INTO "+table+"("+record+") VALUES("+content+");";
                    int i=0;
                    try{
                        i=st.executeUpdate(sql);
                        
                    }catch(Exception e){
                        e.printStackTrace();
                    }

                    if(i!=-1){
                        System.out.println("插入成功");
                    }
                System.out.println(sql+"执行完毕！");
            }
        
        //功能7:插入数据--大量数据(文章，文本内容)
        public void insertTXT(String table,String record,String content,String mark){//参数值: 表名,字段,内容(多个内容用&号分割),?号(多个?用,分割)
            
            sql="INSERT INTO "+table+"("+record+") VALUES("+mark+");";
            
            String [] strA=content.split("&&&&&");
            //判断是否存在相同记录，如果存在则不执行插入
            String ifwhere=record.substring(0,record.indexOf(","));
            String ifexist="SELECT "+record+" FROM "+table+" WHERE "+ifwhere+"='"+strA[0]+"';";
                
            try{
                rs=st.executeQuery(ifexist);
                System.out.println(ifexist+" 执行完毕！================================");
                
                if(rs.next()){//存在相同记录
                    System.out.println("数据库中已经存在相同"+strA[0]+"模版,不进行重复插入!");
                }else{
                    //不存在相同记录
                    System.out.println("数据库不存在"+strA[0]+"模版,正在进行插入......");
                    pst=conn.prepareStatement(sql);
                    
                    for(int i=1;i<=strA.length;i++){//循环次数: ?号个数            
                        pst.setString(i,strA[i-1]);
                    }
                    
                    pst.executeUpdate();
                }

            }catch(Exception e){
                e.printStackTrace();
            }
            
            System.out.println(sql+" 执行完毕！================================");
        }
        
    
        
        
        
        //功能8: 查询数据库weiabi表中对应num编号整行记录的需求字段的信息(区域，省份，城市，区县，网站名称，信息来源)
        public String selectNeedInformation_num(String num){
                StringBuilder sb= new StringBuilder();
            
            sql="SELECT * FROM webapi WHERE num='"+num+"';";
            try{
                rs=st.executeQuery(sql);
                while(rs.next()){
                    for(int i=2;i<=8;i++){ //区域-信息来源
                        if(i==7) continue; //网站网址
                        sb.append(rs.getString(i)+"&");
                    }       
                }
            }catch(Exception e){
                e.printStackTrace();
            }
            
            System.out.println(sql+" 执行完毕！-------------------------------------");
            return sb.toString();   
        }
        
        //关闭数据库连接
        public void closeAll(){
            
            //关闭Result
             try{
                  if(rs!=null)
                       rs.close();
               }catch(Exception e){
                   e.printStackTrace(System.err);
               }
             
             
            //关闭PreparedStatement
            try{
            if(pst!=null)
                  pst.close();
            }catch(Exception e){
               e.printStackTrace(System.err);
            }
            
            
            //关闭Statement
             try{
                   if(st!=null)
                       st.close();
               }catch(Exception e){
                   e.printStackTrace(System.err);
               }
            
             
           //关闭Connection
           try{
               if(conn!=null)
                   conn.close();
           }catch(Exception e){
               e.printStackTrace(System.err);
           }
        
        }
}

```

---


2.IOfile
```
package Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class IOFile {
    
        //功能1:(字节流)读取文件内容--只适用与读取英文，数字，无法设置编码格式
        public String readFileContent2(String path) throws IOException{ //参数:路径
            String content="";
            
            InputStream inf=new FileInputStream(path);
            int size=inf.available();
            for(int i=0;i<size;i++){
                content+=(char)inf.read();
            }
            
            return "\'"+content+"\'";   
        }
        
        //功能2:(字符流)读取文件内容--可以设置编码，读取中文，英文，数字
          public String readFileContent(String path,String encoding) throws IOException{ //参数: 路径,编码格式
                StringBuilder sb =new StringBuilder();
                    
                    //定位文件
                    File f=new File(path);
                    FileInputStream fip=new FileInputStream(f);
                    InputStreamReader reader=new InputStreamReader(fip,encoding);
                    
                    while(reader.ready()){
                        sb.append((char)reader.read());
                    }
                    
                return sb.toString();   
           }        
        
        
        
        //功能3: 获得路径，创建目录
        public void createFile(String path){
            File d=new File(path);
            d.mkdir();
        }
        
        //功能4: 创建文件,写入内容
        public void cFile(String path,String fileName,String encoding,String content) throws IOException{ //参数：路径,文件名.格式,编码格式,文件内容
            File f=new File(path+"/"+fileName);
            FileOutputStream fop= new FileOutputStream(f);
            OutputStreamWriter writer=new OutputStreamWriter(fop,"UTF-8");
            
            writer.append(content);
            
            writer.close();
            fop.close();
            System.out.println( "********************************************"+fileName+"*****创建完毕");
        }
        
        
        //功能5: 创建入库目录
        public void cList(String path,String numberArea,String username) throws Exception{ //参数：路径,编号A-编号B，程序猿姓名
            
                //一.创建对象  1.连接数据库  2.改变文件
                UseDB udb =new UseDB();
                udb.connDatabase();
                udb.useDatabase("batchcreate_file");//使用batchcreate_file数据库
                ChangeFile cf=new ChangeFile(udb);
            
                //二.创建父目录
                String dir=path+"/入库";
                createFile(dir);
                
                //三.获得编号,储存起始编号，终止编号

                String [] num=numberArea.split("-");
                int first=Integer.parseInt(num[0]);//开始编号
                int last=Integer.parseInt(num[1]);//终止编号
                
                
                //四.查询webapi表里num字段所有记录，将其保存在数组里 
                String allNum=udb.selectNum("webapi","num");
                String [] allNumA=allNum.split("&");
               
                //五.计算webapi中num个数(包括带-的)
                int sum=0;
                String allNumber=udb.selectNum("webapi","num");
                String [] allNumber_Arrays=allNumber.split("&");
                for(int i=0;i<allNumber_Arrays.length;i++){
                    if(i>1){
                        if(!allNumber_Arrays[i].substring(0,5).equals(allNumber_Arrays[i-1].substring(0,5)))//判断是否有带-的编号
                            sum++;
                        else
                            continue;
                    }
                }
                
                //六.生成1级子目录      规格:"入库(n个)"
                    String [] str={"入库"};
                    String sonFile=null;//1级子目录
                    String sonFile2=null;//2级子目录
                    String ifNum=null;//2级目录判定编号
                    
                    for(int i=0;i<str.length;i++){   
                        sonFile=dir+"/"+str[i]+"("+sum+")";//路径
                        this.createFile(sonFile);  //循环创建1级子目录(例子:入库(828))
                        
                        //进入含有"入库"字样的二级字目录
                        if(str[i].indexOf("入库")!=-1){
                            //生成入库编号
                            for(int j=first;j<=last;j++){ //循环次数：终止编号-起始编号
                                for(int k=0;k<allNumA.length;k++){ //循环次数：数据库webapi表里num记录的数量
                                    
                                    ifNum=allNumA[k].substring(1,5);
                                    if(ifNum.equals(j+"")){   //对比输入编号和Excel表里的编号
                                        
                                        sonFile2=sonFile+"/入库-0"+j; 
                                        createFile(sonFile2); //创建2级子目录
                                        
                                        String provinceFirstLetter =udb.select("webapi","area","num=0"+j) ;//省份
                                        GetFirstLetter gfl = new GetFirstLetter();
                                        String areaName=gfl.getFirstLetter(provinceFirstLetter).toUpperCase();//获取中文拼音的字母缩写，再转换为大写
                                        if(k==0)
                                            cf.createBackupTask(sonFile2+"/", areaName,allNumA[k],allNumA[0]);//创建BackupTask.java
                                        else
                                            cf.createBackupTask(sonFile2+"/", areaName,allNumA[k],allNumA[k-1]);//创建BackupTask.java,传入路径，编号，当前编号，上一个编号
                                        
                                        sonFile2=sonFile2+"/imp";
                                        createFile(sonFile2);//创建3级子目录              
                                        cf.combineCreateRukuCoding(sonFile2+"/", areaName,allNumA[k],username);//生成相应编号入库代码
                                    }
                                }

                            }
                        }
                    }
                
               System.out.println("***************"+numberArea+"的入库代码目录生成完毕******************************");
               udb.closeAll();//关闭资源
        }
        
    
}


```

---


3.ChangeFile
```
package Utils;

import java.io.IOException;


public class ChangeFile {
    
    private String zhaobYgService="zhaobYgService";     //招标预告
    private String zhaobGgService="zhaobGgService";     //招标公告
    private String zisJgService="zisJgService";         //咨审结果
    private String zhaobWjService="zhaobWjService";     //招标文件
    private String gonggBgService="gonggBgService";     //公告变更
    private String zhaobDyService="zhaobDyService";     //招标答疑
    private String zhongbXxService="zhongbXxService";   //中标信息
    private String kongZjService="kongZjService";       //控制价
    
    private String model;   //BackupTask模版语句

    
    private UseDB udb;
    private IOFile iof;
    
    //getter和Setter方法
    public String getModel(){
        return model;
    }
    public void setModel(String model){
        this.model=model;
    }

    
    //无参构造方法
    public ChangeFile(UseDB u) throws Exception{  //用于给模版语句赋值
        String model="NameA=(NameB) ac.getBean(\"fileName\");";
        String model2="NameA.initNameB();";
        this.setModel("\n\t\t"+model+"\n\t\t"+model2+"\n\t\t");//换行和空格Tab
        
        udb=u;
        iof=new IOFile();
    }
    
    
    //工具1:将首字母变为大写
    public String UpFirstString(String content){ //参数: 内容
        String firstLetter=content.substring(0,1);
        //替换首字母
        content=content.replaceFirst(firstLetter,firstLetter.toUpperCase());
        return content;
    }
    
    //工具1-2:将首字母变为小写
    public String LowFirstString(String content){ //参数: 内容
        String firstLetter=content.substring(0,1);
        //替换首字母
        content=content.replaceFirst(firstLetter,firstLetter.toLowerCase());
        return content;
    }
    
    //工具2:自动生成文件名    
    public String cFileName(String province,String num,String attributeName){//参数:省份拼音缩写(大写),编号,相应的属性名
        StringBuilder sb= new StringBuilder();
        
        province =province.substring(0,2);//例如东北，为DB,防止出现DBDB之类的重复
        
        num=num.replace("-","_");
        sb.append(province+"_"+num+"_"+attributeName);//拼接文件名
        
        return sb.toString();
    }
    
    //工具3:根据BackupTask模版语句,生成相应字符串
    public String cModelString(String province,String num,String attributeName){//参数: 省份,编号,相应的属性名
        StringBuilder sb=new StringBuilder();
        
        
        String fileName=cFileName(province,num,this.UpFirstString(attributeName)); //调用工具2,生成文件名
        String cStr=getModel();
        cStr=cStr.replace("NameA",attributeName).replace("NameB",UpFirstString(attributeName)).replace("fileName",fileName);
        
        sb.append(cStr);
        return sb.toString();
    }
    
    //工具4:生成内容注解
    public String cContentNode(String infSource,String modelName,String num){
        StringBuilder sb= new StringBuilder();
        
        //根据模版名字判断类型
        String type="";
        
        if(modelName.equals("zbyg")){
            type="招标预告";
        }else if(modelName.equals("zbgg")){
            type="招标公告";
        }else if(modelName.equals("zsjg")){
            type="咨审结果";
        }else if(modelName.equals("ggbg")){
            type="公告变更";
        }else if(modelName.equals("zbwj")){
            type="招标文件";
        }else if(modelName.equals("zbdy")){
            type="招标答疑";
        }else if(modelName.equals("zbxx")){
            type="中标信息";
        }else if(modelName.equals("kzj")){
            type="招标控制价";
        }   
        
        sb.append("东软一期-"+infSource+"-"+type+"-"+num);
        
        return sb.toString();
    }
    
    
    //工具5: 判断一个编号有需要创建哪些入库代码，循环调用createRukuCoding方法进行
    public void combineCreateRukuCoding(String path,String province,String num,String username){//路径,省份拼音首字母(大写),编号，程旭猿
        
        
        //查询数据库，确认类型
        String type="";
        Boolean ifUseFilter = true; //是否在入库代码使用title自动判断，默认是true
        String ifURL=udb.checktable(num);
        
        
        //判断对应编号的备注是否是 "含招标、中标",这种则需要招标，答疑，变更，中标都需要生成
        String sRemark=udb.select("webapi","remark", "num='"+num+"'");
        if(sRemark.indexOf("含招标") !=-1){
            ifURL+="zbdy&ggbg&zbxx";
        }else{
            ifUseFilter =false;
        }
        
        String [] ifURL_Arrays=ifURL.split("&");
        for(int i=0;i<ifURL_Arrays.length;i++){
            createRukuCoding(path,province,num,ifURL_Arrays[i],username,ifUseFilter);
        }
    }
    
    
    //功能1:创建BackupTask.java                                                        (上一个编号，用来处理-)
    public void createBackupTask (String path,String province,String num,String ageNum){//参数: 路径,省份拼音缩写,编号,上一个编号

        //一.检查数据库webapi表,对应num(编号)的整条记录，招标预告-招标公告哪个字段有URL
        String ifURL=udb.checktable(num); //返回有URL的字段名
        
        //判断对应编号的备注是否是 "含招标、中标",这种则需要招标，答疑，变更，中标都需要生成
        String sRemark=udb.select("webapi","remark", "num='"+num+"'");
        if(sRemark.indexOf("含招标") !=-1){
            ifURL+="zbdy&ggbg&zbxx";
        }
        
        String [] ownURLArrays=ifURL.split("&");
        
    
        //二.生成相应内容
        String content=""; //储存总体字符串
        String str="";     //中转字符串
        if(ownURLArrays.length==0){
            System.out.println("这个编号为空，不存在url，跳过");
        }else{
            for(int i=0;i<ownURLArrays.length;i++)  //webapi表格里有url的字段
            {
                
                if(ownURLArrays[i].equals("zbyg")){
                    str=cModelString(province,num,zhaobYgService); //调用工具三,生成模版语句
                }else if(ownURLArrays[i].equals("zbgg")){
                    str=cModelString(province,num,zhaobGgService);
                }else if(ownURLArrays[i].equals("zsjg")){
                    str=cModelString(province,num,zisJgService);
                }else if(ownURLArrays[i].equals("ggbg")){
                    str=cModelString(province,num,gonggBgService);
                }else if(ownURLArrays[i].equals("zbwj")){
                    str=cModelString(province,num,zhaobWjService);
                }else if(ownURLArrays[i].equals("zbdy")){
                    str=cModelString(province,num,zhaobDyService);
                }else if(ownURLArrays[i].equals("zbxx")){
                    str=cModelString(province,num,zhongbXxService);
                }else if(ownURLArrays[i].equals("kzj")){
                    str=cModelString(province,num,kongZjService);
                }
                                
                content+=str;
            }
            content+="//ok;";
         }

        //三.判断当前编号是否和上一个编号相同
        boolean sign=false;//设立标示
        if(num.equals(ageNum) || !num.substring(0,5).equals(ageNum.substring(0,5))){
            sign=true;
        }
        
        //四.根据数据库表article表中模版，生成相应编号的文件
        try{
            String dbModelBackupTask=udb.select("article","detail","model='BackupTask'"); //储存数据库article表中,BackupTask模版
        
            if(sign){
                //与上一个编号完全不同(号码不同,且不带-)
                dbModelBackupTask=dbModelBackupTask.replace("//changeModel;",content);
                iof.cFile(path,"BackupTask.java","UTF-8",dbModelBackupTask);//路径,文件名,编码格式,内容
            
            }else{
                //带-的编号,在上一个文件基础上进行叠加
                String ageContent=iof.readFileContent(path+"/BackupTask.java","UTF-8");//前一个编号的内容       
                String changeContent=ageContent.replace("//ok;",content);
                iof.cFile(path,"BackupTask.java","UTF-8",changeContent);
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    
    
    //功能1:创建相应编号,类型的入库代码
    public void createRukuCoding(String path,String province,String num,String type,String username,Boolean ifUseFilter){//参数: 路径,省份拼音首字母缩写,编号,类型,程序猿,是否使用自动判断
        
                //一.生成文件名
                String fileName=""; //注解名
                String typeSpell="";
                String typeService="";
                String typeChinese="";//类型中文名
                String tableName="";//入库表名
                int tNumber =5; //自动识别判定条件
                
                    for(int i=0;i<8;i++)  //共8种类型
                    {               
                        if(type.equals("zbyg")){
                            fileName=cFileName(province,num,"ZhaobYgService");
                            typeSpell="zhaoBiaoYuGao";
                            typeService="zhaobYgService";
                            typeChinese="招标预告";
                            tableName="t_zhao_biao_yu_gao";
                        }else if(type.equals("zbgg")){
                            fileName=cFileName(province,num,"ZhaobGgService");
                            typeSpell="zhaoBiaoGongGao";
                            typeService="zhaobGgService";
                            typeChinese="招标公告";
                            tableName="t_zhao_biao_gong_gao";
                            tNumber=1;
                        }else if(type.equals("zsjg")){
                            fileName=cFileName(province,num,"ZisJgService");
                            typeSpell="ziShenJieGuo";
                            typeService="zisJgService";
                            typeChinese="咨审结果";
                            tableName="t_zi_shen_jie_guo";
                        }else if(type.equals("ggbg")){
                            fileName=cFileName(province,num,"GonggBgService");
                            typeSpell="gongGaoBianGeng";
                            typeService="gonggBgService";
                            typeChinese="公告变更";
                            tableName="t_gong_gao_bian_geng";
                            tNumber=3;
                        }else if(type.equals("zbwj")){
                            fileName=cFileName(province,num,"ZhaobWjService");
                            typeSpell="zhaoBiaoWenJian";
                            typeService="zhaobWjService";
                            typeChinese="招标文件";
                            tableName="t_zhao_biao_wen_jian";
                        }else if(type.equals("zbdy")){
                            fileName=cFileName(province,num,"ZhaobDyService");
                            typeSpell="ZhaoBiaoDaYi";
                            typeService="zhaobDyService";
                            typeChinese="招标答疑";
                            tableName="t_zhao_biao_da_yi";
                            tNumber=4;
                        }else if(type.equals("zbxx")){
                            fileName=cFileName(province,num,"ZhongbXxService");
                            typeSpell="zhongBiaoXinXi";
                            typeService="zhongbXxService";
                            typeChinese="中标信息";
                            tableName="t_zhong_biao_xin_xi";
                            tNumber=2;
                        }else if(type.equals("kzj")){
                            fileName=cFileName(province,num,"KongZjService");
                            typeSpell="kongZhiJia";
                            typeService="kongZjService";
                            typeChinese="控制价";
                            tableName="t_kong_zhi_jia";
                        }               
                 }
                    
                
                //首字母变为大写
                String typeSpellUp=UpFirstString(typeSpell);
                String typeServiceUp=UpFirstString(typeService);
                    
                //二.查询数据库模版
                String modelName=fileName.substring(fileName.lastIndexOf("_")+1);
                String modelContent=udb.select("article","detail","model='ZhaobGgServiceModel'");
                
                //三.查询数据库num编号的整行记录,所需要要的信息
                String information_num=udb.selectNeedInformation_num(num);
                String [] information_Arrays=information_num.split("&");
                
                String area=information_Arrays[0];                                          //区域
                String provinceDB=information_Arrays[1].replace("省","");                    //省份(不要省字)
                String city=information_Arrays[2].replace("市","");                          //城市(不要市字)
                String county=information_Arrays[3].replace("区","").replace("县","").replace("市","");        //区县(不要区,县字)
                String webname=information_Arrays[4];                                       //网站名称
                String infsource=information_Arrays[5];                                     //信息来源
                
                
                //四.修改模版
                String rukuContent=modelContent.replace("zhaoBiaoGongGao",typeSpell).replace("ZhaoBiaoGongGao",typeSpellUp).replace("ZhaobGgService",typeServiceUp).replace("zhaobGgService",typeService).replace("zbgg",type);
                //程序猿;
                rukuContent=rukuContent.replaceFirst("//程序猿;",username);
                //注解内容
                rukuContent=rukuContent.replaceFirst("//注解内容;",area+"_"+information_Arrays[1]+"_"+information_Arrays[2]+"_"+information_Arrays[3]+"_"+typeChinese+"_"+num);
                
                rukuContent=rukuContent.replaceFirst("//注解;",fileName).replaceFirst("//类名;",fileName);
                 
                rukuContent=rukuContent.replaceFirst("//编号;",num);
                
                //是否使用自动判断
                if(ifUseFilter){
                rukuContent=rukuContent.replaceFirst("//使用自动工具类;","int t = Utils.judge(listvo.getTitle());")
                        .replaceFirst("//判断1;","if(t != //生成编号;){").replaceFirst("//判断2;","continue;").replaceFirst("//判断3;", "}");
                rukuContent=rukuContent.replaceFirst("//生成编号;",tNumber+"");
                }else{
                    rukuContent=rukuContent.replaceFirst("//使用自动工具类;","")
                            .replaceFirst("//判断1;","").replaceFirst("//判断2;","").replaceFirst("//判断3;", "");
                }
                
                
                
                rukuContent=rukuContent.replaceFirst("//area;",area);
                rukuContent=rukuContent.replaceFirst("//provinceDB;",provinceDB);
                rukuContent=rukuContent.replaceFirst("//city;",city);
                rukuContent=rukuContent.replaceFirst("//county;",county);
                rukuContent=rukuContent.replaceFirst("//webname;",webname);
                rukuContent=rukuContent.replaceFirst("//infsource;",infsource);
                rukuContent=rukuContent.replace("//表名;",tableName);
                
                try{
                    //创建文件
                    iof.cFile(path,fileName+".java","UTF-8",rukuContent);//路径,文件名,编码格式,内容
                }catch(IOException e){
                    e.printStackTrace();
                }
                
    }
    
    
    
}

```

---



4.GetFirstLetter
```
package Utils;

public class GetFirstLetter {
    private static int BEGIN = 45217;
    private static int END = 63486;
    
    //按照声母表示，这个表是在GB2312中的出现的第一个汉字，也就是说“啊”是代表首字母a的第一个汉字
    // 二十六个字母区间对应二十七个端点    // GB2312码汉字区间十进制表示
    private static char[] chartable = {'啊', '芭', '擦', '搭', '蛾', '发', '噶', '哈',        '哈', '击', '喀', '垃', '妈', '拿', '哦', '啪', '期', '然', '撒', '塌', '塌',        '塌', '挖', '昔', '压', '匝',}; 
    private static int [] table = new int[27]; // 对应首字母区间表

    // 对应首字母区间表
    private static char[] initialtable = {'a', 'b', 'c', 'd', 'e', 'f', 'g',        'h', 'h', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',        't', 't', 'w', 'x', 'y', 'z',};
    
    //1.初始化
     static {        
         for (int i = 0; i < 26; i++) {            
             table[i] = gbValue(chartable[i]);// 得到GB2312码的首字母区间端点表，十进制。 
         }
         table[26] = END;// 区间表结尾
     }

     /* 根据一个包含汉字的字符串返回一个汉字拼音首字母的字符串 最重要的一个方法，思路如下：一个个字符读入、判断、输出     */
     public static String getFirstLetter(String sourceStr) {        
            String result = "";        
            String str = sourceStr.toLowerCase();       
            int StrLength = str.length();        
            int i;       
            try {            
                for (i = 0; i < StrLength; i++) {                
                    result += Char2Initial(str.charAt(i));            
                    }        
                } catch (Exception e) {            
                    result = "";        
                    }        
            return result;   
        }
     
      /* 输入字符,得到他的声母,英文字母返回对应的大写字母,其他非简体汉字返回 '0'     */  
     private static char Char2Initial(char ch) {    
                // 对英文字母的处理：小写字母转换为大写，大写的直接返回
              if (ch >= 'a' && ch <= 'z') {            
                  return ch;        
               }        
              if (ch >= 'A' && ch <= 'Z') {
                  return ch;  
                }
         
              // 对非英文字母的处理：转化为首字母，然后判断是否在码表范围内，        // 若不是，则直接返回。        // 若是，则在码表内的进行判断。   
              int gb = gbValue(ch);// 汉字转换首字母
              if ((gb < BEGIN) || (gb > END)){            
                  return ch;        
                 }
    
              int i;       
              for (i = 0; i < 26; i++) { // 判断匹配码表区间，匹配到就break,判断区间形如“[,)”
                  if ((gb >= table[i]) && (gb < table[i + 1])) {
                      break;            
                      }        
              }
              if (gb == END) { //补上GB2312区间最右端    
                     i = 25;        
                }      
            return initialtable[i]; // 在码表区间中，返回首字母    }
         }

     
     /* 取出汉字的编码 cn 汉字     */  
    private static int gbValue(char ch) {
        String str = new String();        
        str += ch;       
        try {           
            byte[] bytes = str.getBytes("GB2312");            
            if (bytes.length < 2) {               
                    return 0;           
            }            
            return (bytes[0] << 8 & 0xff00) + (bytes[1] & 0xff);      
        } catch (Exception e) {          
            return 0;        
            }    
    }   
    
//  public static void main(String [] args){
//      //在main方法中调用FirstLetterUtil类的getFirstLetter()方法，获取姓名的首字母。如：“刘德华”获取首字母是“ldh”。
//       System.out.print(getFirstLetter("日你仙人掌"));//获取文字首字母的拼音
//  }
    
    
}

```

---


### 3. BackupTask.java和ZhaobGgServiceModel.java模版

BackupTask
```
package com.bonait.dataextract.scheduler;

import java.util.Date;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.bonait.dataextract.service.GonggBgService;
import com.bonait.dataextract.service.KongZjService;
import com.bonait.dataextract.service.ZhaobDyService;
import com.bonait.dataextract.service.ZhaobGgService;
import com.bonait.dataextract.service.ZhaobWjService;
import com.bonait.dataextract.service.ZhaobYgService;
import com.bonait.dataextract.service.ZhongbXxService;
import com.bonait.dataextract.service.ZisJgService;

public class BackupTask extends QuartzJobBean {

    private static ZhaobGgService zhaobGgService;
    private static ZhongbXxService zhongbXxService;
    private static ZhaobWjService zhaobWjService;
    private static ZhaobDyService zhaobDyService;
    private static ZisJgService zisJgService;
    private static GonggBgService gonggBgService;
    private static KongZjService kongZjService;
    private static ZhaobYgService zhaobYgService;
    

    @Override
    protected void executeInternal(JobExecutionContext arg0)
            throws JobExecutionException {
    }

    public static void main(String[] args) {    
        System.out.println("bxkc_Test start time is"+new Date());

        ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
        
        //changeModel;
        
        System.out.println("bxkc_Test end time is"+new Date());
    }
}


```

---

ZhaobGgServiceModel
```
package com.bonait.dataextract.service.impl;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

import com.bonait.dataextract.domain.ZhaoBiaoGongGao;
import com.bonait.dataextract.service.ZhaobGgService;
import com.bonait.dataextract.util.Util;
import com.bonait.dataextract.util.Utils;
import com.bonait.dataextract.vo.ListVO;


/**
 * @程序猿：//程序猿;
 * 
 * @内容：//注解内容;
 */
@Service("//注解;")
public class //类名; implements ZhaobGgService{

    @Resource
    private SessionFactory sf ;
    private Query query;
    private ZhaoBiaoGongGao zbgg;
    //列表页接口地址
    private String listUrl="";
    //内容页接口地址
    private String detailUrl="";
    //入库编码
    private String sourceNo="//编号;";
    @Override
    public void initZhaobGgService() {
        Session session=sf.openSession();
        session.beginTransaction();
        try{
            //获取总页数
              String s=Util.sendGet(listUrl,"1");
              JSONObject obj =JSONObject.fromObject(s);
              int maxPage=Integer.parseInt(obj.get("pageCount").toString());


        //清空数据库里
//      query=session.createSQLQuery("DELETE FROM //表名; t where t.WEB_SOURCE_NO='"+sourceNo+"'");
//      query.executeUpdate();
//      session.getTransaction().commit();
//      session.beginTransaction();

        int KK=0;
            flag:
            for(int i=1;i<=maxPage;i++){//maxPage
                String jsonList=Util.sendGet(listUrl,i+"");
                JSONObject objList =JSONObject.fromObject(jsonList);
                
                JSONArray temp=(JSONArray) objList.get("list");
                List list = (List)JSONArray.toList(temp,ListVO.class);
                Iterator it = list.iterator();
                
                while(it.hasNext()){
                    ListVO listvo = (ListVO) it.next();
                    //使用自动工具类;
                    
                    //判断1;
                        //判断2;
                    //判断3;
                    
                    query=session.createSQLQuery("SELECT ID FROM //表名; t where t.WEB_SOURCE_NO='"+sourceNo+"' and t.RECORD_ID='"+listvo.getId()+"'");
                    String getDate=listvo.getDate();
                    //获取页数，便于插入数据异常时，先跳过该页
//                  System.out.println(i+":"+getDate+":"+maxPage);
                    
                    int date = Integer.parseInt(getDate.substring(0, 4));
                    //只取2014年至今的不重复数据   
                    if(query.executeUpdate()<=0&&date>=2014){
                        KK+=1;
                        String detail=Util.sendGet(detailUrl,listvo.getId());
//                      String detail=Util.sendGet(listvo.getUrl(),"");
                        JSONObject o=JSONObject.fromObject(detail);
                         String content=o.get("content").toString();
                         
                        //过滤掉无效数据
                        if(content==null||content.length()<10||content.contains("出错")||content.contains("找不到文件")){continue;}
                        zbgg=new ZhaoBiaoGongGao();
                        zbgg.setWebSourceNo(sourceNo);
                        zbgg.setArea("//area;");//区域
                        zbgg.setProvince("//provinceDB;");//省份
                        zbgg.setCity("//city;");//城市，没有可不填
                        zbgg.setDistrict("//county;");// 区县 不要“区”字符
//                      zbgg.setCity(listvo.getCiy());
                        zbgg.setWebSourceName("//webname;");//网站名称，写完整的名称
                        zbgg.setInfoSource("//infsource;");//信息来源
                        //未匹配到工程或服务的，都标识为货物
                        String infoType=Util.getInfoType(content);
                        if(infoType!=null&&infoType.length()>0)
                        zbgg.setInfoType(Util.getInfoType(content));
                        else {zbgg.setInfoType("货物");}
//                       zbgg.setInfoType(Util.getInfoType(content));//信息类型
//                      zbgg.setIndustry("建筑建材"); //行业分类
                        zbgg.setIndustry(Util.getIndustry(content));//行业分类
                        zbgg.setRecordId(listvo.getId());
                        zbgg.setId(UUID.randomUUID().toString());
                        zbgg.setPageTitle(listvo.getTitle());
                        zbgg.setPageTime(getDate);
                        zbgg.setPageContent(content);
                        zbgg.setPageAttachments("");//附件url，暂不需要
                        zbgg.setCreateTime(new Date());
                        session.save(zbgg);
                     }else{break flag;}//到2013年的页码时，断掉循环
                    if(KK%1==0){
                        session.flush();  
                        session.clear();
                        session.getTransaction().commit();
                        session.beginTransaction();
                    }
                }  
            }   
        } catch (Exception e) {
            System.out.println(sourceNo+" is error, please cheak ZhaobGgService !!!!!!!");
        }
        session.getTransaction().commit();
        session.close();
        System.out.println("000000000");
    }

}
```

---


### 4.两个表webapi和article
···

 CREATE TABLE article(             
    id INT  AUTO_INCREMENT primary key,
    model VARCHAR(30) NOT NULL,
    detail VARCHAR(20000) NOT NULL,
    date DATETIME DEFAULT NOW()
)ENGINE = innoDB DEFAULT CHARSET=utf8;

CREATE TABLE webapi(             
    num VARCHAR(20) primary key,
    area VARCHAR(255) NOT NULL,
    province VARCHAR(255) NOT NULL,
    city VARCHAR(255) NOT NULL,
    county VARCHAR(255) NOT NULL,
    webname VARCHAR(255)  NOT NULL,
    weburl  VARCHAR(255)  NOT NULL,
    infsource VARCHAR(255) NOT NULL,
    inftype   VARCHAR(255),
    worktype VARCHAR(255),
    zbyg  VARCHAR(255),
    zbgg    VARCHAR(255),
    zsjg  VARCHAR(255),
    ggbg VARCHAR(255),
    zbwj VARCHAR(255),
    zbdy VARCHAR(255),
    zbxx VARCHAR(255),
    kzj VARCHAR(255),
    lot VARCHAR(255),
    webtype VARCHAR(255),
    remark VARCHAR(255)
)ENGINE = innoDB DEFAULT CHARSET=utf8;
···


<br><br><br>

---


<br><br><br>


版本【3.0】 2016.11.27
=======================

优化了程序，更加简洁效率，归纳调理，可移植性和修改性高
有用到ArrayList和HashMap，重构了个人工具类

<br><br>

### 1. 目录结构：
```
BatchCreate_file
    src
        extend.example 
            GetFirstLetter.java
        main
            AlterContent.java
            CreateFile.java
            GetTableData.java
            NumBean.java
            GoTask.java
            tset.java 【运行文件】
        Utils
            IOFile.java
            UseDB.java
            UseString.java
```

---



### 2. 代码

a-1.GetFirstLetter.java
```
package extend.example;

public class GetFirstLetter {
    private static int BEGIN = 45217;
    private static int END = 63486;
    
    //按照声母表示，这个表是在GB2312中的出现的第一个汉字，也就是说“啊”是代表首字母a的第一个汉字
    // 二十六个字母区间对应二十七个端点    // GB2312码汉字区间十进制表示
    private static char[] chartable = {'啊', '芭', '擦', '搭', '蛾', '发', '噶', '哈',        '哈', '击', '喀', '垃', '妈', '拿', '哦', '啪', '期', '然', '撒', '塌', '塌',        '塌', '挖', '昔', '压', '匝',}; 
    private static int [] table = new int[27]; // 对应首字母区间表

    // 对应首字母区间表
    private static char[] initialtable = {'a', 'b', 'c', 'd', 'e', 'f', 'g',        'h', 'h', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',        't', 't', 'w', 'x', 'y', 'z',};
    
    //1.初始化
     static {        
         for (int i = 0; i < 26; i++) {            
             table[i] = gbValue(chartable[i]);// 得到GB2312码的首字母区间端点表，十进制。 
         }
         table[26] = END;// 区间表结尾
     }

     /* 根据一个包含汉字的字符串返回一个汉字拼音首字母的字符串 最重要的一个方法，思路如下：一个个字符读入、判断、输出     */
     public static String getFirstLetter(String sourceStr) {        
            String result = "";        
            String str = sourceStr.toLowerCase();       
            int StrLength = str.length();        
            int i;       
            try {            
                for (i = 0; i < StrLength; i++) {                
                    result += Char2Initial(str.charAt(i));            
                    }        
                } catch (Exception e) {            
                    result = "";        
                    }        
            return result;   
        }
     
      /* 输入字符,得到他的声母,英文字母返回对应的大写字母,其他非简体汉字返回 '0'     */  
     private static char Char2Initial(char ch) {    
                // 对英文字母的处理：小写字母转换为大写，大写的直接返回
              if (ch >= 'a' && ch <= 'z') {            
                  return ch;        
               }        
              if (ch >= 'A' && ch <= 'Z') {
                  return ch;  
                }
         
              // 对非英文字母的处理：转化为首字母，然后判断是否在码表范围内，        // 若不是，则直接返回。        // 若是，则在码表内的进行判断。   
              int gb = gbValue(ch);// 汉字转换首字母
              if ((gb < BEGIN) || (gb > END)){            
                  return ch;        
                 }
    
              int i;       
              for (i = 0; i < 26; i++) { // 判断匹配码表区间，匹配到就break,判断区间形如“[,)”
                  if ((gb >= table[i]) && (gb < table[i + 1])) {
                      break;            
                      }        
              }
              if (gb == END) { //补上GB2312区间最右端    
                     i = 25;        
                }      
            return initialtable[i]; // 在码表区间中，返回首字母    }
         }

     
     /* 取出汉字的编码 cn 汉字     */  
    private static int gbValue(char ch) {
        String str = new String();        
        str += ch;       
        try {           
            byte[] bytes = str.getBytes("GB2312");            
            if (bytes.length < 2) {               
                    return 0;           
            }            
            return (bytes[0] << 8 & 0xff00) + (bytes[1] & 0xff);      
        } catch (Exception e) {          
            return 0;        
            }    
    }   
    
//  public static void main(String [] args){
//      //在main方法中调用FirstLetterUtil类的getFirstLetter()方法，获取姓名的首字母。如：“刘德华”获取首字母是“ldh”。
//       System.out.print(getFirstLetter("日你仙人掌"));//获取文字首字母的拼音
//  }
    
    
}
```

---


b-1.AlterContent.java
```
package main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import Utils.IOFile;
import Utils.UseString;

/**更新时间： 2016.11.27
 * 
 *  获取模版内容，根据编号各个属性值，修改BackupTask.java和入库文件，创建文件
 *          @author Suvan
 *
 */
public class AlterContent {
    

        //方法1:修改BackupTask.java,并创建
        public  void aBackupTask(String path,String fileName,String content,String sign,ArrayList<String> rukuName_list,CreateFile cf_RK,IOFile iof,UseString us) throws IOException{//参数:路径，文件名，内容，标记【修改还是创建】,入库名,,项目类，工具类             
                
             //1.修改模版内容
            String BackupTask_model="\n\t\t"+"NameA=(NameB) ac.getBean(\"fileName\");" +
                                                              "\n\t\t" +"NameA.initNameB();";
                String attributeName="";//属性名
                String cStr ="";
                StringBuilder sb = new StringBuilder();//储存各个所有内容
                for(int i=0;i<rukuName_list.size();i++){
                    attributeName =rukuName_list.get(i).substring(rukuName_list.get(i).lastIndexOf("_")+1);
                    cStr=BackupTask_model.replace("NameA",attributeName)
                                                              .replace("NameB",us.UpFirstString(attributeName))
                                                              .replace("fileName",rukuName_list.get(i));
                    sb.append(cStr);
                }
                sb.append("\n\t\t//ok;");//用于叠加补充
                
                //2.覆盖或者修改文件
                if("cover".equals(sign)){//覆盖 【与上一个编号完全不同(号码不同,且不带-)】
                    content=content.replace("//changeModel;",sb.toString());
                    cf_RK.cBackupTask_RK(path, "BackupTask.java", content, iof);        
                }else if("alter".equals(sign)){ //修改 【带-的编号,在上一个文件基础上进行叠加修改】
                    content=content.replace("//ok;",sb.toString());
                    cf_RK.cBackupTask_RK(path, "BackupTask.java", content, iof);
                }
            
        }
        
        //方法2:修改入库文件
        public void aRK(String path,String fileName,String content,String type,String username,NumBean numbean,CreateFile cf_RK,GetTableData gtd_RK,IOFile iof,UseString us) throws IOException{
            

                //一.根据类型生成相应名称
                HashMap<String,String> hm = gtd_RK.getTypeAllName(type);

                
                //二.根据需求修改content
                        //A.判断信息类型
                        String infType =numbean.getInftype();
                        if(infType !=null  && ! "-0-".equals(infType)){                                     //zbgg.setInfoType("服务");// 信息类型
                            content=content.replaceFirst("//是否有信息类型;","zbgg.setInfoType(\""+infType+"\");//信息类型");
                        }else{
                            content=content.replaceFirst("//是否有信息类型;","//zbgg.setInfoType(\"服务\");//信息类型");
                        }
                    
                        //B.判断行业分类
                        String workType=numbean.getWorktype();
                        if(workType==null && ! "-0-".equals(workType)) {                                 //zbgg.setIndustry("建筑建材"); // 行业分类
                            content=content.replaceFirst("//是否有行业分类;","zbgg.setIndustry(\""+workType+"\"); //行业分类");
                        }else{
                            content=content.replaceFirst("//是否有行业分类;","//zbgg.setIndustry(\"建筑建材\"); // 行业分类");
                        }

                    
                //三.修改模版
                content=content.replace("zhaoBiaoGongGao",hm.get("typeSpell"))
                                                                             .replace("ZhaoBiaoGongGao",us.UpFirstString(hm.get("typeSpell")))
                                                                             .replace("ZhaobGgService",us.UpFirstString(hm.get("typeService")))
                                                                             .replace("zhaobGgService",hm.get("typeService"))
                                                                             .replace("zbgg",type);
                content=content.replaceFirst("//@程序猿;",username)
                                            .replaceFirst("//@内容;",numbean.getArea()+"_"+numbean.getProvince()+"_"+numbean.getCity()+"_"+numbean.getCounty()+"_"+hm.get("typeChinese")+"_"+numbean.getNum())                
                                            .replaceFirst("//编号;",numbean.getNum());        
                String className =fileName.substring(0,fileName.lastIndexOf("."));
                content=content.replaceFirst("//注解;",className)
                                            .replaceFirst("//类名;",className);
                content=content.replaceFirst("//Area;",numbean.getArea())
                                            .replaceFirst("//Province;",numbean.getProvince())
                                            .replaceFirst("//City;",numbean.getCity())
                                            .replaceFirst("//County;",numbean.getCounty())
                                            .replaceFirst("//Webname;",numbean.getWebname())
                                            .replaceFirst("//Infsource;",numbean.getInfsource())
                                            .replace("//表名;",hm.get("tableName"));

                
                            
                //四.创建编号相应类型的入库文件
                cf_RK.c_RK(path,fileName, content, iof);
        }       
}

```

---


b-2.CreateFile.java
```
package main;

import java.io.IOException;
import java.sql.SQLException;

import Utils.IOFile;
import Utils.UseDB;

/**更新时间： 2016.11.27
 * 
 *  创建文件【入库目录，BackupTask.java,相应编号入库文件】
 *          @author Suvan
 *
 */
public class CreateFile {
        
    //方法1：创建入库目录
    public void cCatalog_RK(String path,String catalogName,IOFile iof) throws IOException{//参数：路径，编号，工具类对象
        iof.createCatalog(path, catalogName);
    }
    
    
    //方法2：BackupTask.java
    public void cBackupTask_RK(String path,String fileName,String modelContent,IOFile iof) throws IOException{//参数：路径，文件名，模版内容，工具类对象
         iof.cFile(path, fileName, "UTF-8", modelContent);
    }
    
    
    
    //方法3：入库文件【例如:HZ_02139_ZhaobGgService】
    public void c_RK(String path,String fileName,String modelContent,IOFile iof) throws IOException{//参数：路径，文件名，模版内容，工具类对象
        iof.cFile(path, fileName, "UTF-8", modelContent);
    }
}
```

---

b-3.GetTableData.java
```
package main;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import Utils.IOFile;
import Utils.UseDB;

/**更新时间： 2016.11.27
 * 
 *  获取数据，处理数据，归纳信息
 *          @author Suvan
 *
 */
public class GetTableData {

    
    //方法1：获取table表中，num编号的所有信息,储存进NumBean，返回NumBean
    public  NumBean getNumInformation(String table,String num,UseDB udb) throws SQLException{//参数：表名,编号,工具类

            //查询table表，num字段所有数据，用&分隔
            String numInf = udb.select(table, "num", "num='"+num+"'", '*');
            String [] numInf_arrays=numInf.split("&");
            
            //表共21列，Numbean共21个属性，拥有Getters和Setters
            NumBean nb = new NumBean();
                nb.setNum(numInf_arrays[0]);
                nb.setArea(numInf_arrays[1]);
                nb.setProvince(numInf_arrays[2]);
                nb.setCity(numInf_arrays[3]);
                nb.setCounty(numInf_arrays[4]);
                nb.setWebname(numInf_arrays[5]);
                nb.setWeburl(numInf_arrays[6]);
                nb.setInfsource(numInf_arrays[7]);
                nb.setInftype(numInf_arrays[8]);
                nb.setWorktype(numInf_arrays[9]);
                nb.setZbyg(numInf_arrays[10]);
                nb.setZbgg(numInf_arrays[11]);
                nb.setZsjg(numInf_arrays[12]);
                nb.setGgbg(numInf_arrays[13]);
                nb.setZbwj(numInf_arrays[14]);
                nb.setZbdy(numInf_arrays[15]);
                nb.setZbxx(numInf_arrays[16]);
                nb.setKzj(numInf_arrays[17]);
                nb.setLot(numInf_arrays[18]);
                nb.setWebtype(numInf_arrays[19]);
                nb.setRemark(numInf_arrays[20]);
        
        return nb;
    }
    
    
    //方法2：获取table表的，所有编号
    public ArrayList<String> getAllNum(String table,UseDB udb) throws SQLException{ //参数: 表名，工具类
            ArrayList<String> numList = new  ArrayList<String>();
            
            String allNum =udb.selectAllColumn(table,"num");
            String [] allNum_arrays = allNum.split("##");
            for(int i=0;i<allNum_arrays.length;i++){
                numList.add(allNum_arrays[i]);
            }
            return numList;
    }
    
    
    //方法3:获取当前编号拥有URL的字段名
    public ArrayList<String> getNum_HaveURLRecord(String table,String num,UseDB udb) throws SQLException{ //参数：表名，编号，工具类

        //查询数据库table表，num字段的11-18行，得到拥有记录的列名的ArrayList
        ArrayList<String> alist=udb.selectAskinformation_Scope(table,"num='"+num+"'", "11-18");//【11-18是招标预告到控制价】

        return alist;
    }

    //方法4：根据列名，得到相应类型的字符串
    public String getTypename(String columnName){ //参数：列名
                String type="";

                if(columnName.equals("zbyg")){
                    type="ZhaobYgService";
                }else if(columnName.equals("zbgg")){
                    type="ZhaobGgService";
                }else if(columnName.equals("zsjg")){
                    type="ZisJgService";
                }else if(columnName.equals("ggbg")){
                    type="GonggBgService";
                }else if(columnName.equals("zbwj")){
                    type="ZhaobWjService";
                }else if(columnName.equals("zbdy")){
                    type="ZhaobDyService";
                }else if(columnName.equals("zbxx")){
                    type="ZhongbXxService";
                }else if(columnName.equals("kzj")){
                    type="KongZjService";
                }
        
        return type;
    }
    
    //方法5:判断num编号属否在scope区间里面，返回布尔类型参数【true-属于，false-不属于】
    public Boolean getMinMaxScope(String scope,int num){//参数：范围，编号
            Boolean sign = false;//判断标识，默认为true
            
            String [] scope_arrays = scope.split("-");
            int minScope =Integer.parseInt(scope_arrays[0]);
            int maxScope =Integer.parseInt(scope_arrays[1]);
            
                if(minScope <= num && num <= maxScope){
                    sign =true;
                }
            
            return sign;
    }
    
    //方法6：根据类型生成相应名称，返回HaspMap【无序键值队】
    public HashMap<String,String> getTypeAllName(String type){  //参数：类型名，也是列名
        HashMap<String,String> hm = new HashMap<String,String>();

                if(type.equals("zbyg")){
                     hm.put("typeSpell", "zhaoBiaoYuGao");
                     hm.put("typeService","zhaobYgService");
                     hm.put("typeChinese","招标预告");
                     hm.put("tableName","t_zhao_biao_yu_gao");
                }else if(type.equals("zbgg")){
                     hm.put("typeSpell", "zhaoBiaoGongGao");
                     hm.put("typeService","zhaobGgService");
                     hm.put("typeChinese","招标公告");
                     hm.put("tableName","t_zhao_biao_gong_gao");
                }else if(type.equals("zsjg")){
                     hm.put("typeSpell", "ziShenJieGuo");
                     hm.put("typeService","zisJgService");
                     hm.put("typeChinese","咨审结果");
                     hm.put("tableName","t_zi_shen_jie_guo");
                }else if(type.equals("ggbg")){
                     hm.put("typeSpell", "gongGaoBianGeng");
                     hm.put("typeService","gonggBgService");
                     hm.put("typeChinese","公告变更");
                     hm.put("tableName","t_gong_gao_bian_geng");
                }else if(type.equals("zbwj")){
                     hm.put("typeSpell", "zhaoBiaoWenJian");
                     hm.put("typeService","zhaobWjService");
                     hm.put("typeChinese","招标文件");
                     hm.put("tableName","t_zhao_biao_wen_jian");
                }else if(type.equals("zbdy")){
                    hm.put("typeSpell", "ZhaoBiaoDaYi");
                     hm.put("typeService","zhaobDyService");
                     hm.put("typeChinese","招标答疑");
                     hm.put("tableName","t_zhao_biao_da_yi");
                }else if(type.equals("zbxx")){
                    hm.put("typeSpell", "zhongBiaoXinXi");
                     hm.put("typeService","zhongbXxService");
                     hm.put("typeChinese","中标信息");
                     hm.put("tableName","t_zhong_biao_xin_xi");
                }else if(type.equals("kzj")){
                     hm.put("typeSpell", "kongZhiJia");
                     hm.put("typeService","kongZjService");
                     hm.put("typeChinese","控制价");
                     hm.put("tableName","t_kong_zhi_jia");
                }               
        
        return hm;      
    }
    
    
    //方法6: 插入模版数据,并返回模版内容
    public String getModel(String filePath,IOFile iof,UseDB udb) throws IOException,SQLException{//参数：文件路径，工具类
        
        //1.读取文件内容                                                                                                      
        String fileContent=iof.readFileContent_String(filePath,"GBK"); //注意：路径的话用/或者\\都可以,数据库的默认编码是GBK,如果是UTF-8\   
        
        //2.判断是否存在相同模版
        String modeInf = udb.select("article", "model", "model='"+filePath+"'", '*');
        if(modeInf.indexOf(filePath) == -1){
            udb.insert("article","model,detail",filePath+"&&&&&"+fileContent,"?,?"); 
        }else{
            System.out.println("*******************已存在相同文件名的模版，不进行重复插入*******************");
        }
        
        return fileContent;
    }
}
```

---



b-4.NumBean.java
```
package main;

/**更新时间： 2016.11.27
 * 
 *  JavaBena，每一个编号
 *          @author Suvan
 *
 */
public class NumBean {
    
        private String num;             //网站数据源编码【编号】
        private String area;                //区域
        private String province;        //省份
        private String city;                    //城市
        private String county;          //区县
        private String webname;      //网站数据源名称
        private String weburl;          //网址
        private String infsource;       //信息来源
        private String inftype;         //信息类型
        private String worktype;        //行业分类
        private String zbyg;                //招标预告
        private String zbgg;                //招标公告
        private String zsjg;                //咨审结果
        private String ggbg;                //公告变更
        private String zbwj;                //招标文件
        private String zbdy;                //招标答疑
        private String zbxx;                //中标信息
        private String kzj;                 //控制价
        private String lot;                 //批次
        private String webtype;     //原网站信息分类
        private String remark;          //备注
    
            public String getNum() {
                return num;
            }
            public String getArea() {
                return area;
            }
            public String getProvince() {
                return province;
            }
            public String getCity() {
                return city;
            }
            public String getCounty() {
                return county;
            }
            public String getWebname() {
                return webname;
            }
            public String getWeburl() {
                return weburl;
            }
            public String getInfsource() {
                return infsource;
            }
            public String getInftype() {
                return inftype;
            }
            public String getWorktype() {
                return worktype;
            }
            public String getZbyg() {
                return zbyg;
            }
            public String getZbgg() {
                return zbgg;
            }
            public String getZsjg() {
                return zsjg;
            }
            public String getGgbg() {
                return ggbg;
            }
            public String getZbwj() {
                return zbwj;
            }
            public String getZbdy() {
                return zbdy;
            }
            public String getZbxx() {
                return zbxx;
            }
            public String getKzj() {
                return kzj;
            }
            public String getLot() {
                return lot;
            }
            public String getWebtype() {
                return webtype;
            }
            public String getRemark() {
                return remark;
            }
            public void setNum(String num) {
                this.num = num;
            }
            public void setArea(String area) {
                this.area = area;
            }
            public void setProvince(String province) {
                this.province = province;
            }
            public void setCity(String city) {
                this.city = city;
            }
            public void setCounty(String county) {
                this.county = county;
            }
            public void setWebname(String webname) {
                this.webname = webname;
            }
            public void setWeburl(String weburl) {
                this.weburl = weburl;
            }
            public void setInfsource(String infsource) {
                this.infsource = infsource;
            }
            public void setInftype(String inftype) {
                this.inftype = inftype;
            }
            public void setWorktype(String worktype) {
                this.worktype = worktype;
            }
            public void setZbyg(String zbyg) {
                this.zbyg = zbyg;
            }
            public void setZbgg(String zbgg) {
                this.zbgg = zbgg;
            }
            public void setZsjg(String zsjg) {
                this.zsjg = zsjg;
            }
            public void setGgbg(String ggbg) {
                this.ggbg = ggbg;
            }
            public void setZbwj(String zbwj) {
                this.zbwj = zbwj;
            }
            public void setZbdy(String zbdy) {
                this.zbdy = zbdy;
            }
            public void setZbxx(String zbxx) {
                this.zbxx = zbxx;
            }
            public void setKzj(String kzj) {
                this.kzj = kzj;
            }
            public void setLot(String lot) {
                this.lot = lot;
            }
            public void setWebtype(String webtype) {
                this.webtype = webtype;
            }
            public void setRemark(String remark) {
                this.remark = remark;
            }
}

```

---


b-5.GoTask.java
```
package main;

import java.util.ArrayList;

import extend.example.GetFirstLetter;
import Utils.IOFile;
import Utils.UseDB;
import Utils.UseString;


/**更新时间：2016.11.27
 *      
 * 任务流程
 *           @author Suvan
 *
 */
public class GoTask {
    
    
    private String path="C:/Users/Liu-shuwei/Desktop\\";                                 //路径
    private String catalogName="入库【版本3.0,包含新表and旧表】";               //文件名
    private String numScope="01000-05000";                                                  //编号范围区间
    private String useTable="webapi2";                                                              //使用哪个表的数据
    private String username="某男子";                                                                 //程序员名称
    
    
    public static long  useTime(){
        return System.currentTimeMillis();
    }
    
    //任务流程
    public void GO() {
        long beginTime=useTime();   //开始时间
        
        //执行任务流程
        try{
            
            //一.获取对象
            CreateFile cf_RK =new CreateFile();
            GetTableData  gtd_RK = new GetTableData();
            AlterContent afc = new AlterContent();
            
            UseDB udb = new UseDB();
            IOFile iof=new IOFile();
            UseString us = new UseString();
            
            GetFirstLetter gfl = new GetFirstLetter();

            
            udb.connDatabase("batchcreate_file");//连接数据库
            
            
            //二.创建大目录,往数据库插入模版数据
            iof.createCatalog(path, catalogName);
            String model1_BT =gtd_RK.getModel("model_file/BackupTask.java",iof,udb);
            String model2_RK =gtd_RK.getModel("model_file/ZhaobGgServiceModel.java",iof,udb);
            
            
            
            //三.获取数据表数据，并生成目录and文件
                //三-1获取所有编号
                ArrayList<String> numList =gtd_RK.getAllNum(useTable, udb) ; 
                
                
                //三-2.遍历所有编号
                for(int i=0;i<numList.size();i++){
                
                    String num=numList.get(i);                                  //当前编号
                    String num_=numList.get(i) .replace("-","_");       //编号下划线形态
                    String changePath ="";                                          //变化路径
                    
                    //3-2判断当前编号是否在范围区间
                    if(gtd_RK.getMinMaxScope(numScope, us.getInt(num.substring(0,5)))){                              //范围，编号 进行比较

                        //a.获取当前编号的所有数据，并储存到JavaBean
                        NumBean numBean =gtd_RK.getNumInformation(useTable, num, udb);
                        
                        
                        //b.生成入库文件名，存储进ArrayList
                         ArrayList<String> ifURL_typelist =gtd_RK.getNum_HaveURLRecord(useTable, num, udb); //得到当前编号含有URL的入库文件类型
                         ArrayList<String > rukuName_list=new ArrayList<String>();                                          //用于储存当前编号所需要的所有入库文件名
                         for(int k=0;k<ifURL_typelist.size();k++){
                             String typeName =gtd_RK.getTypename(ifURL_typelist.get(k));                                                //入库类型
                             String rukuName=gfl.getFirstLetter(numBean.getArea()).toUpperCase()+"_"+num_+"_"+typeName;     //入库文件完整名
                             rukuName_list.add(rukuName);
                         }
                        
                        
                        //d.生成入库子目录
                        String catalog_1="入库-"+num.substring(0,5);                          //目录名
                        changePath =path+"/"+catalogName;                                      //子路径1
                        cf_RK.cCatalog_RK(changePath,"入库-"+num.substring(0,5),iof);
                        
                        
                        //生成BackupTask.java
                         changePath +="/"+catalog_1;                                 //子路径1-2
                         String sign = "cover";                                                 //判断当前编号是需要修改还是覆盖,默认是覆盖【带-编号是alter,不带-是cover】
                            if(i > 0){                                                                  //大于0的时候进行判断    【顶部位置没有上一个编号】
                                if(us.getInt(numList.get(i-1).substring(0,5)) == us.getInt(num.substring(0,5))){            
                                    sign="alter";                                                   //如果和上个编号相等，则修改
                                }
                            }
                         if("cover".equals(sign)){                      //覆盖
                                    afc.aBackupTask(changePath,"BackupTask.java", model1_BT,sign,rukuName_list, cf_RK, iof,us);
                         }else if("alter".equals(sign)){                //修改
                             String ageBackupTask=iof.readFileContent_String(changePath+"/BackupTask.java","GBK"); //读取已经已经存在的BackupTask.java
                             afc.aBackupTask(changePath,"BackupTask.java",ageBackupTask ,sign,rukuName_list, cf_RK, iof,us);
                         }
                         
                        
                        //e.生成入库文件
                        cf_RK.cCatalog_RK(changePath,"imp",iof);        //生成imp目录
                        changePath +="/imp";                                         //子路径1-2-3
                        for(int k=0;k<ifURL_typelist.size();k++){                //根据拥有URL的类型，循环生成入库文件
                            afc.aRK(changePath, rukuName_list.get(k)+".java", model2_RK,ifURL_typelist.get(k),username,numBean,cf_RK, gtd_RK,iof, us);
                        }
                }
            }
            
            
        
            //四.关闭资源
            udb.closeAll();
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        //结束时间
        long endTime=useTime();
        System.out.println("总共用时:"+(endTime-beginTime)+"毫秒！");
    }
}

```

---


b-6.test.java
```
package main;

public class test {
    

    //测试主方法
    public static void main(String [] args){
    
        //创建任务流程对象
        GoTask gt= new GoTask();
        
        //执行流程(参数:路径,某个表数据,目录名)
        gt.GO();
    
        
    }
}


```

---

c-1.IOFile.java
c-2.UseDB.java
c-3.UseString.java
```
***********************c-1*************************************
package Utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/** 更新时间： 2016.11.26
 *   
 *  数据库封装类
 *          方法1-读取
 *          方法2-创建
 *          方法3-追加内容
 *              @author suvan
 *
 */
public class IOFile {
    
        //方法1-1:读取文件内容【字节流】【只适用与读取英文，数字，无法设置编码格式】
        public String readFileContent_Char(String filePath) throws IOException{ //参数:文件路径,编码格式
            
            String content="";
            InputStream inf=new FileInputStream(filePath);
            for(int i=0;i<inf.available();i++){
                content+=(char)inf.read();
            }
            

            return "\'"+content+"\'";   
        }
        
        
        //方法1-2:读取文件内容【字符流】【可以设置编码，读取中文，英文，数字】
          public String readFileContent_String(String filePath,String encoding) throws IOException{ //参数: 路径,编码格式
                    StringBuilder sb =new StringBuilder();
                    
                    File f=new File(filePath);//定位文件【小弊端，没有文件的话会自动新建】
                    
                    FileInputStream fip=new FileInputStream(f);
                    InputStreamReader reader=new InputStreamReader(fip,encoding);
                    while(reader.ready()){
                        sb.append((char)reader.read());
                    }
                    
                return sb.toString();   
           }        
        
        
        
        //方法3-1: 创建目录
        public void createCatalog(String path,String catalogName){//参数：路径，目录名
            
            File d=new File(path+"/"+catalogName);
            d.mkdir();
        }
        
        
        //方法3-2: 创建文件,写入内容
        public void cFile(String path,String fileName,String encoding,String content) throws IOException{ //参数：路径,文件名.格式,编码格式,文件内容
            File f=new File(path+"/"+fileName);
            
            FileOutputStream fop= new FileOutputStream(f);
            OutputStreamWriter writer=new OutputStreamWriter(fop,"UTF-8");
            writer.append(content);

            writer.close();
            fop.close();
            System.out.println( "*********************"+fileName+"成功创建！***********************************");
        }
        
        
        //方法3-3:追加文件内容【若不存在文件，则会在路径path下，新建filnName文件】
        public void addContentFile(String path,String fileName,String content) throws IOException{ //参数：路径，文件名，追加的内容
            File f=new File(path+"/"+fileName); 

            FileOutputStream fs = new FileOutputStream(f,true);
            OutputStreamWriter osw = new OutputStreamWriter(fs);
            BufferedWriter out = new BufferedWriter(osw);     
                    
            out.write(content);     
            out.close();     
        }
        
}


************************************c-2***********************************************************
package Utils;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


/** 更新时间： 2016.11.26
 *   
 *  数据库封装类
 *          方法1-基础使用
 *          方法2-查询 【SELECT】
 *          方法3-添加【INSERT】
 *              @author suvan
 *
 */
public class UseDB {
    
    
    private String drive="com.mysql.jdbc.Driver";           //数据库驱动
    private String link="jdbc:mysql://127.0.0.1:3306/";     //mysql—JDBC链接+IP地址+端口

    
    private String username="root";                         //用户名
    private String password="liushuwei";                    //密码
    
    private Connection conn;
    private Statement st;
    private PreparedStatement pst;
    private ResultSet rs;
    private ResultSetMetaData rsmd;//获取列的信息
    
    private String sql; //sql语句
    

        //无参构造方法
        public UseDB(){ 
            
        }
    
        //方法1: 连接database数据库
        public void connDatabase(String database)  throws SQLException,ClassNotFoundException{ //参数：数据库名称
             Class.forName(drive);
             conn=DriverManager.getConnection(link+database,username,password); 
             st=conn.createStatement();
             System.out.println("数据库连接成功......");
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
            if(pst!=null)   pst.close();    //关闭PreparedStatement
            if(st!=null) st.close();//关闭Statement
            if(conn!=null)  conn.close(); //关闭Connection

        }
        
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
                                    sb.append("-0-&");
                                }else{
                                    sb.append(rs.getString(i)+"&");
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
        public ArrayList<String> selectAskinformation_Scope(String table,String condition,String scope) throws SQLException{ //参数:表名,条件,范围行数【使用-进行分隔,例如1-5】
//              StringBuilder sb= new StringBuilder();
                ArrayList<String> alist = new  ArrayList<String>();
                
                String [] scope_arrays =scope.split("-");
                int begin=Integer.parseInt(scope_arrays[0]);
                int end=Integer.parseInt(scope_arrays[1]);
                
                sql="SELECT * FROM "+table+" WHERE "+condition+";";
                rs=st.executeQuery(sql);
                rsmd=rs.getMetaData();
                while(rs.next()){
                    for(int i=begin;i<=end;i++){ //遍历范围区间的字段
                        if(rs.getString(i)==null || rs.getString(i).length()<2) continue;
                        alist.add(rsmd.getColumnName(i));//只要列名
                    }       
                }

            return alist;   
        }
        
        //方法2-9：查询table所有记录总数，返回记录总数
        public int select_allCount(String table) throws SQLException{//参数：表名
            
            
            sql="SELECT count(*)  FROM " +table+";";
            rs=st.executeQuery(sql);
            rs.next();
            
            int recordCount =Integer.parseInt(rs.getString(1));

            return recordCount;
        }
        
        
        //方法3-1: 往table表record字段,插入一行content数据【少量数据(标题，日期等)】【多字段插入   ,分隔字段     ,号分隔内容】
        public void insert(String table,String record,String content) throws SQLException{//参数: 表名,字段,内容
                    String sql="INSERT INTO "+table+"("+record+") VALUES("+content+");";
                    int i=st.executeUpdate(sql);
                    if(i!=-1){
                        System.out.println("*****************"+table+"表INSERT成功！****************");
                    }
        }
                
        //方法3-2:往table表record，插入一行content数据 【大量数据(文章，文本内容)】【,分隔字段 &&&&& 分隔内容   ,分隔占位符】
        public void insert(String table,String record,String content,String mark) throws SQLException, UnsupportedEncodingException{//参数值: 表名,字段,内容，占位符(?)
                    
            
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
                            System.out.println("**********************"+table+"表中不存在"+content_Arrays[0]+"记录,正在进行INSERT。。。。。。。。。。。。。");
                            sql="INSERT INTO "+table+"("+record+") VALUES("+mark+");";
                            
                            pst=conn.prepareStatement(sql);
                            
                            for(int i=1;i<=mark_Arrays.length;i++){//根据占位符个数【?的数量】进行循环: ?号个数            
                                pst.setString(i,mark_Arrays[i-1]);
                            }
                            
                            pst.executeUpdate();
                            System.out.println("*****************"+table+"表INSERT成功！****************");
                        }
            }
                
}




************************************c-3***********************************************************
package Utils;


/**更新时间:2016.11.26
 *          操作字符串
 *              方法1：转换
 *              方法2：判断
 *      @author Suvan
 */
public class UseString {
    
    //方法1-1:将首字母变为大写
        public String UpFirstString(String content){ //参数: 内容
            String firstLetter=content.substring(0,1);
            content=content.replaceFirst(firstLetter,firstLetter.toUpperCase());//替换首字母
            
            return content;
        }
        
        //方法1-2:将首字母变为小写
        public String LowFirstString(String content){ //参数: 内容
            String firstLetter=content.substring(0,1);
            content=content.replaceFirst(firstLetter,firstLetter.toLowerCase());//替换首字母
            
            return content;
        }
        
        //方法:1-3:将字符串变为int类型
        public int getInt(String content){
            return Integer.parseInt(content);
        }
        
        //方法2-1：判断num编号属否在scope区间里面，返回布尔类型参数【true--属于，false--不属于】
        public Boolean getMinMaxScope(String scope,int num){//参数：范围，编号
            Boolean sign = false;//判断标识，默认为true
            
            String [] scope_arrays = scope.split("-");
            int minScope =Integer.parseInt(scope_arrays[0]);
            int maxScope =Integer.parseInt(scope_arrays[1]);
            
                if(minScope <= num && num <= maxScope){
                    sign =true;
                }
            
            return sign;
        }
        
}

```

---

<br><br><br>



版本4.0【2016.12.13】
=======================

&emsp;耗时半天左右，突发奇想，优化+学习,最后勉强搞定，但是仍然有优化的空间

版本改动
+ 优化了代码,每个类都用工具类的属性,GoTask里创建对象的时候传参赋值,删除了CreateFile.java
+ 兼容2种数据库【SQLite和MySQL】,SQLite嵌入到项目中,移植性高
+ MySQL需要自己新建数据库and表,SQLite运行即自动创建【不会重复创建or插入】
+ 使用SQLite第一次创建会在项目相应目录下建库,建表，读取excel数据插入,会有点慢,二次运行快到飞起
+ 添加了excel数据读取【目前是jxl,项目中也包含poi,可根据需求修改GeTableData.java的s_InsertExcel()方法】
+ 可设置程序猿的编号范围匹配和地区匹配

<br>

项目结构规划
BatchCreate_file
&emsp;extend.example
&emsp;&emsp;GetFirstLetter.java
&emsp;main
&emsp;&emsp;AlterContent.java
&emsp;&emsp;GetTableData.java
&emsp;&emsp;NumBean.java
&emsp;&emsp;GoTask.java【任务流程 GO()】
&emsp;&emsp;test.java【运行项目的测试方法】
&emsp;Utils
&emsp;&emsp;IOFile.java
&emsp;&emsp;UseDB.java
&emsp;&emsp;UseExcel.java【新增,用于excel文件的读写】
&emsp;&emsp;UseString.java


<br><br><br>

下面贴出主要改动的代码


main包
------------------
1.AlterContent.java
```
package main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import Utils.IOFile;
import Utils.UseDB;
import Utils.UseString;

/**更新时间： 2016.12.13
 * 
 *  获取模版内容，根据编号各个属性值，修改BackupTask.java和入库文件，创建文件
 *          @author Suvan
 *
 */
public class AlterContent {
        
        private IOFile iof;
        private UseDB udb;
        private UseString us;
        private GetTableData gtd;
        
        //有参构造函数，创建对象时进行赋值
        public AlterContent(IOFile iof,UseDB udb,UseString us,GetTableData gtd){  
            this.iof=iof;
            this.udb=udb;
            this.us=us;
            this.gtd=gtd;
        }
    

        //方法1:修改BackupTask.java,并创建
        public  void aBackupTask(String path,String fileName,String content,String sign,ArrayList<String> rukuName_list) throws IOException{//参数:路径，文件名，内容，标记【修改还是创建】,入库名,项目类           
                
             //1.修改模版内容
            String BackupTask_model="\n\t\t"+"NameA=(NameB) ac.getBean(\"fileName\");" +
                                                              "\n\t\t" +"NameA.initNameB();";
                String attributeName="";//属性名
                String cStr ="";
                StringBuilder sb = new StringBuilder();//储存各个所有内容
                for(int i=0;i<rukuName_list.size();i++){
                    attributeName =rukuName_list.get(i).substring(rukuName_list.get(i).lastIndexOf("_")+1);
                    cStr=BackupTask_model.replace("NameA",us.LowFirstString(attributeName))
                                                              .replace("NameB",us.UpFirstString(attributeName))
                                                              .replace("fileName",rukuName_list.get(i));
                    sb.append(cStr);
                }
                sb.append("\n\t\t//ok;");//用于叠加补充
                
                //2.覆盖或者修改文件
                if("cover".equals(sign)){//覆盖 【与上一个编号完全不同(号码不同,且不带-)】
                    content=content.replace("//changeModel;",sb.toString());
                     iof.cFile(path, "BackupTask.java", "UTF-8", content);
                }else if("alter".equals(sign)){ //修改 【带-的编号,在上一个文件基础上进行叠加修改】
                    content=content.replace("//ok;",sb.toString());
                    iof.cFile(path, "BackupTask.java", "UTF-8", content);
                }
            
        }
        
        
        //方法2:修改入库文件
        public void aRK(String path,String fileName,String content,String type,String username,NumBean numbean) throws IOException{
            

                //一.根据类型生成相应名称
                HashMap<String,String> hm = gtd.getTypeAllName(type);

                
                //二.根据需求修改content
                        //A.判断信息类型
                        String infType =numbean.getInftype();
                        if(infType !=null  && ! "-0-".equals(infType)){                                     //zbgg.setInfoType("服务");// 信息类型
                            content=content.replaceFirst("//是否有信息类型;","zbgg.setInfoType(\""+infType+"\");//信息类型");
                        }else{
                            content=content.replaceFirst("//是否有信息类型;","//zbgg.setInfoType(\"服务\");//信息类型");
                        }
                    
                        //B.判断行业分类
                        String workType=numbean.getWorktype();
                        if(workType==null && ! "-0-".equals(workType)) {                                 //zbgg.setIndustry("建筑建材"); // 行业分类
                            content=content.replaceFirst("//是否有行业分类;","zbgg.setIndustry(\""+workType+"\"); //行业分类");
                        }else{
                            content=content.replaceFirst("//是否有行业分类;","//zbgg.setIndustry(\"建筑建材\"); // 行业分类");
                        }

                    
                //三.修改模版
                content=content.replace("zhaoBiaoGongGao",hm.get("typeSpell"))
                                                                             .replace("ZhaoBiaoGongGao",us.UpFirstString(hm.get("typeSpell")))
                                                                             .replace("ZhaobGgService",us.UpFirstString(hm.get("typeService")))
                                                                             .replace("zhaobGgService",hm.get("typeService"))
                                                                             .replace("zbgg",type);
                content=content.replaceFirst("//@程序猿;",username)
                                            .replaceFirst("//@内容;",numbean.getArea()+"_"+numbean.getProvince()+"_"+numbean.getCity()+"_"+numbean.getCounty()+"_"+hm.get("typeChinese")+"_"+numbean.getNum())                
                                            .replaceFirst("//编号;",numbean.getNum());        
                String className =fileName.substring(0,fileName.lastIndexOf("."));
                content=content.replaceFirst("//注解;",className)
                                            .replaceFirst("//类名;",className);
                content=content.replaceFirst("//Area;",numbean.getArea())
                                             .replaceFirst("//Province;",numbean.getProvince().replaceFirst("省","").replaceFirst("市","").replaceFirst("区","").replaceFirst("县",""))
                                            .replaceFirst("//Webname;",numbean.getWebname())
                                            .replaceFirst("//Infsource;",numbean.getInfsource())
                                            .replace("//表名;",hm.get("tableName"));
                
                //仅有2个字符的时候保留，省,市，区
                if(numbean.getCity().length()==2){
                    content=content.replaceFirst("//City;",numbean.getCity());
                }else{
                    content=content.replaceFirst("//City;",numbean.getCity().replaceFirst("省","").replaceFirst("市","").replaceFirst("区","").replaceFirst("县",""));
                }
                
                if(numbean.getCounty().length()==2){
                    content=content.replaceFirst("//County;",numbean.getCounty());
                }else{
                    content=content.replaceFirst("//County;",numbean.getCounty().replaceFirst("省","").replaceFirst("市","").replaceFirst("区","").replaceFirst("县",""));
                }
                        
                        
                
                            
                //四.创建编号相应类型的入库文件
                iof.cFile(path, fileName, "UTF-8", content);
        }       
}

```

---
<br><br>


2.GetTableData.java
-------------------
```
package main;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Utils.IOFile;
import Utils.UseDB;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

/**更新时间： 2016.12.13
 * 
 *  获取数据，处理数据，归纳信息
 *          @author Suvan
 *
 */
public class GetTableData {

    private IOFile iof;
    private UseDB udb;
    
    //有参构造函数，创建对象时进行赋值
    public GetTableData(IOFile iof,UseDB udb){
        this.iof=iof;
        this.udb=udb;
    }
    
    
    //方法1: 插入模版数据,并返回模版内容
        public String getModel(String filePath) throws IOException,SQLException{//参数：文件路径
            
            //1.读取文件内容                                                                                                      
            String fileContent=iof.readFileContent_String(filePath,"GBK");   //注意：路径的话用/或者\\都可以,数据库的默认编码是GBK
            String modeName=filePath.substring(filePath.indexOf("/")+1);            //模版名字

            //2.判断是否存在相同模版
            String modeInf = udb.select("article", "model", "model='"+modeName+"'");

            if(modeInf.length() <1){    //判断是否已存在模版
                udb.insert("article","model,detail","'"+modeName+"','"+fileContent.replace("'", "''")+"'"); 
            }else{
                System.out.println("\n********************************已存在"+modeInf+"模版，不进行重复插入****************************");
            }
        
            return fileContent;
        }
    
    //方法2：获取table表中，num编号的所有信息,储存进NumBean，返回NumBean
    public  NumBean getNumInformation(String table,String num) throws SQLException{//参数：表名,编号

            //查询table表，num字段所有数据，用&分隔
            String numInf = udb.select(table, "num", "num='"+num+"'", '*');
            String [] numInf_arrays=numInf.split("&");
            
            //表共21列，Numbean共21个属性，拥有Getters和Setters
            NumBean nb = new NumBean();
                nb.setNum(numInf_arrays[0]);
                nb.setArea(numInf_arrays[1]);
                nb.setProvince(numInf_arrays[2]);
                nb.setCity(numInf_arrays[3]);
                nb.setCounty(numInf_arrays[4]);
                nb.setWebname(numInf_arrays[5]);
                nb.setWeburl(numInf_arrays[6]);
                nb.setInfsource(numInf_arrays[7]);
                nb.setInftype(numInf_arrays[8]);
                nb.setWorktype(numInf_arrays[9]);
                nb.setZbyg(numInf_arrays[10]);
                nb.setZbgg(numInf_arrays[11]);
                nb.setZsjg(numInf_arrays[12]);
                nb.setGgbg(numInf_arrays[13]);
                nb.setZbwj(numInf_arrays[14]);
                nb.setZbdy(numInf_arrays[15]);
                nb.setZbxx(numInf_arrays[16]);
                nb.setKzj(numInf_arrays[17]);
                nb.setLot(numInf_arrays[18]);
                nb.setWebtype(numInf_arrays[19]);
                nb.setRemark(numInf_arrays[20]);
        
        return nb;
    }
    
    
    //方法3：获取table表的，所有编号
    public ArrayList<String> getAllNum(String table) throws SQLException{ //参数: 表名
            ArrayList<String> numList = new  ArrayList<String>();
            
            String allNum =udb.selectAllColumn(table,"num");
            String [] allNum_arrays = allNum.split("##");
            for(int i=0;i<allNum_arrays.length;i++){
                numList.add(allNum_arrays[i]);
            }
            return numList;
    }
    
    //方法4:判断num编号属否在scope区间里面，返回布尔类型参数【true-属于，false-不属于】
    public Boolean getMinMaxScope(String scope,int num){//参数：范围，编号
            Boolean sign = false;//判断标识，默认为true
            
            String [] scope_arrays = scope.split("-");
            int minScope =Integer.parseInt(scope_arrays[0]);
            int maxScope =Integer.parseInt(scope_arrays[1]);
            
                if(minScope <= num && num <= maxScope){
                    sign =true;
                }
            
            return sign;
    }
    
    
    //方法5:获取当前编号拥有URL的字段名【类型】
    public ArrayList<String> getNum_HaveURLRecord(String table,String num) throws SQLException{ //参数：表名，编号

        //查询数据库table表，num字段的11-18行，得到拥有记录的列名的ArrayList
        ArrayList<String> alist=udb.selectAskinformation_Scope(table,"num='"+num+"'", "11-18");//【11-18是招标预告到控制价】

        return alist;
    }

    //方法6：根据列名，得到相应类型的字符串
    public String getTypename(String columnName){ //参数：列名
                String type="";

                if(columnName.equals("zbyg")){
                    type="ZhaobYgService";
                }else if(columnName.equals("zbgg")){
                    type="ZhaobGgService";
                }else if(columnName.equals("zsjg")){
                    type="ZisJgService";
                }else if(columnName.equals("ggbg")){
                    type="GonggBgService";
                }else if(columnName.equals("zbwj")){
                    type="ZhaobWjService";
                }else if(columnName.equals("zbdy")){
                    type="ZhaobDyService";
                }else if(columnName.equals("zbxx")){
                    type="ZhongbXxService";
                }else if(columnName.equals("kzj")){
                    type="KongZjService";
                }
        
        return type;
    }
    

    
    //方法7：根据类型生成相应名称，返回HaspMap【无序键值队】
    public HashMap<String,String> getTypeAllName(String type){  //参数：类型名，也是列名
        HashMap<String,String> hm = new HashMap<String,String>();

                if(type.equals("zbyg")){
                     hm.put("typeSpell", "zhaoBiaoYuGao");
                     hm.put("typeService","zhaobYgService");
                     hm.put("typeChinese","招标预告");
                     hm.put("tableName","t_zhao_biao_yu_gao");
                }else if(type.equals("zbgg")){
                     hm.put("typeSpell", "zhaoBiaoGongGao");
                     hm.put("typeService","zhaobGgService");
                     hm.put("typeChinese","招标公告");
                     hm.put("tableName","t_zhao_biao_gong_gao");
                }else if(type.equals("zsjg")){
                     hm.put("typeSpell", "ziShenJieGuo");
                     hm.put("typeService","zisJgService");
                     hm.put("typeChinese","咨审结果");
                     hm.put("tableName","t_zi_shen_jie_guo");
                }else if(type.equals("ggbg")){
                     hm.put("typeSpell", "gongGaoBianGeng");
                     hm.put("typeService","gonggBgService");
                     hm.put("typeChinese","公告变更");
                     hm.put("tableName","t_gong_gao_bian_geng");
                }else if(type.equals("zbwj")){
                     hm.put("typeSpell", "zhaoBiaoWenJian");
                     hm.put("typeService","zhaobWjService");
                     hm.put("typeChinese","招标文件");
                     hm.put("tableName","t_zhao_biao_wen_jian");
                }else if(type.equals("zbdy")){
                    hm.put("typeSpell", "ZhaoBiaoDaYi");
                     hm.put("typeService","zhaobDyService");
                     hm.put("typeChinese","招标答疑");
                     hm.put("tableName","t_zhao_biao_da_yi");
                }else if(type.equals("zbxx")){
                    hm.put("typeSpell", "zhongBiaoXinXi");
                     hm.put("typeService","zhongbXxService");
                     hm.put("typeChinese","中标信息");
                     hm.put("tableName","t_zhong_biao_xin_xi");
                }else if(type.equals("kzj")){
                     hm.put("typeSpell", "kongZhiJia");
                     hm.put("typeService","kongZjService");
                     hm.put("typeChinese","控制价");
                     hm.put("tableName","t_kong_zhi_jia");
                }               
        
        return hm;      
    }
    
    
//************************************************************************************************************************
    
    
    //方法SQLite-1：创建表结构
    public void s_CreateTable(String useTable) throws SQLException{
        
         //创建模版表
        String sql1= "CREATE TABLE if not exists article("  +            
                                "id  INCREMENT primary key," +
                                "model TEXT NOT NULL,"              +
                                " detail TEXT NOT NULL"     +
                                ");" ;
        //获取Excel数据表
        String sql2="CREATE TABLE if not exists "+useTable+"(" +
                             "num TEXT primary key," +
                             "area TEXT NOT NULL," +
                             "province TEXT," +
                             "city TEXT," +
                             "county TEXT," +
                             "webname TEXT," +
                             "weburl  TEXT," +
                             "infsource TEXT," +
                             "inftype   TEXT," +
                             "worktype TEXT," +
                             "zbyg  TEXT," +
                             "zbgg    TEXT," +
                             "zsjg  TEXT," +
                             "ggbg TEXT," +
                             "zbwj TEXT," +
                             "zbdy TEXT," +
                             "zbxx TEXT," +
                             "kzj TEXT," +
                             "lot TEXT," +
                             "webtype TEXT," +
                             "remark TEXT"  +
                             "  )";
        
          udb.createSQL(sql1); 
          udb.createSQL(sql2); 
    }
    
    //方法SQLite-2.读取Excel表的数据，插入SQLite
    public void s_InsertExcel(String useExcel,String useTable) throws IOException,BiffException,SQLException{//参数：Excel文件名,表名
        
            File xlsFile = new File("Excel/"+useExcel);
            //2.获得工作薄对象
            Workbook workbook = Workbook.getWorkbook(xlsFile);
            
            //3.获得所有工作表
            Sheet [] sheets = workbook.getSheets();
            if(sheets.length>0){
                System.out.println("将Excel数据插入SQLite数据库--------------------------->");
                Sheet sheet = sheets[0];    //第1个工作表
                int rows = sheet.getRows(); //总行数
//              int cols = sheet.getRows(); //总列数
                
                
                
                for(int row=0;row<rows;row++){  
                    Cell    [] cells =sheet.getRow(row);
                    
                    //正则判断n行的第1列的数据是否是编号【开头是否为数字】，不是则跳过
                    Pattern p = Pattern.compile("\\d+");
                    Matcher m = p.matcher(cells[0].getContents());
                    if(m.find()){
                        System.out.print(row+"行......");
                        if(row %50 ==0) System.out.println();   //若50条记录换行一次
                        udb.insert(useTable,
                                    "'"+cells[0].getContents()+"','"+cells[1].getContents()+"','"+cells[2].getContents()+"','"+cells[3].getContents()+"','"+cells[4].getContents()+"','"
                                        +cells[5].getContents()+"','"+cells[6].getContents()+"','"+cells[7].getContents()+"','"+cells[8].getContents()+"','"+cells[9].getContents()+"','"
                                        +cells[10].getContents()+"','"+cells[11].getContents()+"','"+cells[12].getContents()+"','"+cells[13].getContents()+"','"+cells[14].getContents()+"','"
                                        +cells[15].getContents()+"','"+cells[16].getContents()+"','"+cells[17].getContents()+"','"+cells[18].getContents()+"','"+cells[19].getContents()+"','"+cells[20].getContents()+"'");
                    }
                }
            }
    }
    
}





```

---
<br><br>


3.GoTask.java
```
package main;

import java.util.ArrayList;

import Utils.IOFile;
import Utils.UseDB;
import Utils.UseString;
import extend.example.GetFirstLetter;


/**更新时间：2016.12.13
 *      
 * 任务流程
 *           @author Suvan
 *
 */
public class GoTask {
    
    

//  private String numScope="00000-05000";                                                  //编号范围区间
//  private String username="某男子";                                                                  //程序员名称
    private String path="C:/Users/Liu-shuwei/Desktop\\";                                             //路径
    private String catalogName="入库【3.3版,823表(区分程序猿and地区)】";             //文件名
    private Boolean areaMatch=false;                                                                    //是否开启区域匹配【默认不开启】
    private Boolean numMatch=false;                                                                 //是否开启默认匹配【默认不开启】
    private String useExcel;                                                                                    //使用项目根目录下哪个Excel
    private String useTable;                                                                                    //使用哪个表的数据【SQLite的话，不存在表则自动创建】
    
    public void setPath(String path){
        this.path=path;
    }
    public void setCatalogName(String cn){
        this.catalogName=cn;
    }
    public void setAreaMatch(Boolean b){
        this.areaMatch=b;
    }
    public void setNumMatch(Boolean b){
        this.numMatch=b;
    }
    public void setUseExcel(String excel){
        this.useExcel=excel;
    }
    public void setUseTable(String table){
        this.useTable=table;
    }
    
    
    //任务1： 使用MySQL数据库
    public void GO(String username,String numScope,String numArea) {    //参数:程序猿，编号范围，地区
            System.out.println(username+"-------------------------------------------------->Go!");
        
        //执行任务流程
        try{
            
            //一.获取对象
            UseDB udb = new UseDB("mysql");
            IOFile iof=new IOFile();
            UseString us = new UseString();
            
            GetFirstLetter gfl = new GetFirstLetter();

            GetTableData  gtd = new GetTableData(iof,udb);
            AlterContent ac = new AlterContent(iof,udb,us,gtd);
            
            
            udb.connDatabase("batchcreate_file");//连接数据库
            
            
            //二.创建大目录,往数据库插入模版数据
            iof.createCatalog(path, catalogName);
            iof.createCatalog(path+"/"+catalogName, username);
            String model1_BT =gtd.getModel("model_file/BackupTask.java");
            String model2_RK =gtd.getModel("model_file/ZhaobGgServiceModel.java");
            
            
            
            //三.获取数据表数据，并生成目录and文件
                //三-1获取所有编号
                ArrayList<String> numList =gtd.getAllNum(useTable) ; 
                
                
                //三-2.遍历所有编号
                for(int i=0;i<numList.size();i++){
                
                    String num=numList.get(i);                                                      //当前编号
                    String num_=numList.get(i) .replace("-","_");                           //编号下划线形态
                    StringBuilder changePath =new StringBuilder("");                                            //变化路径
                    
                    //P1:判断是否开启编号范围匹配 and 比较当前编号是否在范围区间
                    if( numMatch && !gtd.getMinMaxScope(numScope, us.getInt(num.substring(0,5)))){  //获取前5个数字
                        continue;
                    }
                    //P2:判断是否开启地区匹配 and 进行地区匹配
                        if(areaMatch){
                            String area =udb.select(useTable,"area","num='"+num+"'");
                            if(!area.equals(numArea)){
                                continue;
                            }
                        }
                    
                        
                        //a.获取当前编号的所有数据，并储存到JavaBean
                        NumBean numBean =gtd.getNumInformation(useTable, num);
                        
                        //b.生成入库文件名，存储进ArrayList
                         ArrayList<String> ifURLType_list =gtd.getNum_HaveURLRecord(useTable, num);             //获取当前编号所有"含有URL"的字段的类型【例:招标公告 zbgg】
                         ArrayList<String > rukuName_list=new ArrayList<String>();                                                      //用于储存当前编号所需要的所有入库文件名
                         StringBuilder rukuName=new StringBuilder("");
                         for(String type:ifURLType_list){
                             rukuName.append(gfl.getFirstLetter(numBean.getArea()).toUpperCase()+"_"+num_+"_"+gtd.getTypename(type));   //入库文件完整名【地区首字母(大写)_编号_类型服务名】
                             rukuName_list.add(rukuName.toString());
                             rukuName=rukuName.delete(0, rukuName.length());                                                                                //清空
                         }
                         
                        
                        //d.生成入库子目录
                        String catalog_1="入库-"+num.substring(0,5);                                                                  //目录名
                        changePath.insert(0,path+"/"+catalogName+"/"+username);                                     //子路径1                
                        iof.createCatalog(changePath.toString(),"入库-"+num.substring(0,5));                          //创建入库子目录【入库-编号】
                        
                        
                        //生成BackupTask.java
                         changePath.append("/"+catalog_1);                               //子路径1-2
                         String sign = "cover";                                                          //判断当前编号是需要修改还是覆盖【默认是覆盖，带-编号是alter,不带-是cover】
                            if(i > 0){                                                                           //大于0的时候进行判断   【顶部位置没有上一个编号】
                                if(us.getInt(numList.get(i-1).substring(0,5)) == us.getInt(num.substring(0,5))){            
                                    sign="alter";                                                            //如果和上个编号相等，则修改
                                }
                            }
                            
                         if("cover".equals(sign)){                      //覆盖
                                    ac.aBackupTask(changePath.toString(),"BackupTask.java", model1_BT,sign,rukuName_list);
                         }else if("alter".equals(sign)){                //修改
                             String ageBackupTask=iof.readFileContent_String(changePath+"/BackupTask.java","GBK");                  //读取已经已经存在的BackupTask.java
                             ac.aBackupTask(changePath.toString(),"BackupTask.java",ageBackupTask ,sign,rukuName_list);
                         }
                         
                        
                        //e.生成入库文件
                        iof.createCatalog(changePath.toString(),"imp");          //生成imp目录
                        changePath.append("/imp");                                       //子路径1-2-3
                        for(int k=0;k<ifURLType_list.size();k++){                    //根据拥有URL的类型，循环生成入库文件
                            ac.aRK(changePath.toString(), rukuName_list.get(k)+".java", model2_RK,ifURLType_list.get(k),username,numBean);
                        }
            }
            
            //四.关闭资源
            udb.closeAll();
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
//***********************************************************************************************************************************
    
    
    //任务2： 使用SQLite
        public void GO_SQLite(String username,String numScope,String numArea) { //参数:程序猿，编号范围，地区
            System.out.println(username+"-------------------------------------------------->Go!");
        
        //执行任务流程
        try{
            
            //一.获取对象
            UseDB udb = new UseDB("SQLite");
            IOFile iof=new IOFile();
            UseString us = new UseString();
            
            GetFirstLetter gfl = new GetFirstLetter();

            GetTableData  gtd = new GetTableData(iof,udb);
            AlterContent ac = new AlterContent(iof,udb,us,gtd);
            
            
            udb.connDatabase("batchcreate_file.db");                        //连接数据库【若不存在则创建】
            gtd.s_CreateTable(useTable);                                            //创建表【article-保存模版,(useTable)-Excel工作薄数据】
            gtd.s_InsertExcel(useExcel,useTable);                                //插入Excel数据,【传入excel文件名和使用表名】
            
            //二.创建大目录,往数据库插入模版数据
            iof.createCatalog(path, catalogName);
            iof.createCatalog(path+"/"+catalogName, username);
            String model1_BT =gtd.getModel("model_file/BackupTask.java");
            String model2_RK =gtd.getModel("model_file/ZhaobGgServiceModel.java");
            
            
            
            //三.获取数据表数据，并生成目录and文件
                //三-1获取所有编号
                ArrayList<String> numList =gtd.getAllNum(useTable) ; 
                
                
                //三-2.遍历所有编号
                for(int i=0;i<numList.size();i++){
                
                    String num=numList.get(i);                                                      //当前编号
                    String num_=numList.get(i) .replace("-","_");                           //编号下划线形态
                    StringBuilder changePath =new StringBuilder("");                                            //变化路径
                    
                    //P1:判断是否开启编号范围匹配 and 比较当前编号是否在范围区间
                    if( numMatch && !gtd.getMinMaxScope(numScope, us.getInt(num.substring(0,5)))){  //获取前5个数字
                        continue;
                    }
                    //P2:判断是否开启地区匹配 and 进行地区匹配
                        if(areaMatch){
                            String area =udb.select(useTable,"area","num='"+num+"'");
                            if(!area.equals(numArea)){
                                continue;
                            }
                        }
                    
                        
                        //a.获取当前编号的所有数据，并储存到JavaBean
                        NumBean numBean =gtd.getNumInformation(useTable, num);
                        
                        //b.生成入库文件名，存储进ArrayList
                         ArrayList<String> ifURLType_list =gtd.getNum_HaveURLRecord(useTable, num);             //获取当前编号所有"含有URL"的字段的类型【例:招标公告 zbgg】
                         ArrayList<String > rukuName_list=new ArrayList<String>();                                                      //用于储存当前编号所需要的所有入库文件名
                         StringBuilder rukuName=new StringBuilder("");
                         for(String type:ifURLType_list){
                             rukuName.append(gfl.getFirstLetter(numBean.getArea()).toUpperCase()+"_"+num_+"_"+gtd.getTypename(type));   //入库文件完整名【地区首字母(大写)_编号_类型服务名】
                             rukuName_list.add(rukuName.toString());
                             rukuName=rukuName.delete(0, rukuName.length());                                                                                //清空
                         }
                         
                        
                        //d.生成入库子目录
                        String catalog_1="入库-"+num.substring(0,5);                                                                  //目录名
                        changePath.insert(0,path+"/"+catalogName+"/"+username);                                     //子路径1                
                        iof.createCatalog(changePath.toString(),"入库-"+num.substring(0,5));                          //创建入库子目录【入库-编号】
                        
                        
                        //生成BackupTask.java
                         changePath.append("/"+catalog_1);                               //子路径1-2
                         String sign = "cover";                                                          //判断当前编号是需要修改还是覆盖【默认是覆盖，带-编号是alter,不带-是cover】
                            if(i > 0){                                                                           //大于0的时候进行判断   【顶部位置没有上一个编号】
                                if(us.getInt(numList.get(i-1).substring(0,5)) == us.getInt(num.substring(0,5))){            
                                    sign="alter";                                                            //如果和上个编号相等，则修改
                                }
                            }
                            
                         if("cover".equals(sign)){                      //覆盖
                                    ac.aBackupTask(changePath.toString(),"BackupTask.java", model1_BT,sign,rukuName_list);
                         }else if("alter".equals(sign)){                //修改
                             String ageBackupTask=iof.readFileContent_String(changePath+"/BackupTask.java","GBK");                  //读取已经已经存在的BackupTask.java
                             ac.aBackupTask(changePath.toString(),"BackupTask.java",ageBackupTask ,sign,rukuName_list);
                         }
                         
                        
                        //e.生成入库文件
                        iof.createCatalog(changePath.toString(),"imp");          //生成imp目录
                        changePath.append("/imp");                                       //子路径1-2-3
                        for(int k=0;k<ifURLType_list.size();k++){                    //根据拥有URL的类型，循环生成入库文件
                            ac.aRK(changePath.toString(), rukuName_list.get(k)+".java", model2_RK,ifURLType_list.get(k),username,numBean);
                        }
            }
            
            //四.关闭资源
            udb.closeAll();
            
        }catch(Exception e){
            e.printStackTrace();
        }

        }
}

```

---
<br><br>

4.test.java
```
package main;

public class test {
    

    //测试主方法
    public static void main(String [] args){
        long begin=System.currentTimeMillis();
        
        //创建任务流程对象
        GoTask gt= new GoTask();
            gt.setPath("C:/Users/Liu-shuwei/Desktop\\");
            gt.setCatalogName("入库【4.0版,823表(完全版-未筛选)】");
            gt.setNumMatch(false);      //是否开启编号范围匹配
            gt.setAreaMatch(false);     //是否开启地区匹配
        
        //方式1：使用本地MySQL******************************************************************************************
        //执行流程(参数:程序猿,编号范围,地区)
        gt.setUseTable("webapi3");  
        gt.GO("陈宇","02065-02145","华中");
        gt.GO("刘聪","02396-02461","华北");
        gt.GO("房文迪","01311-02769","华东");
//      gt.GO("林佳法","02311-02371",);
        gt.GO("严加远","02770-02781","华东");
        gt.GO("吴健俊","01281-01596","西南");
        gt.GO("周盛","01597-03456","西南");
        gt.GO("占文冲","01552-03100","西北");


        //方式2：使用SQLite******************************************************************************************************************************
//      gt.setUseTable("api");          //使用表名【SQLite的若不存在会自动创建】
//      gt.setUseExcel("8.23.xls");             //使用哪个Excel【项目根目录/Excel/】
//      gt.GO_SQLite("陈宇","02065-02145","华中");
//      gt.GO_SQLite("刘聪","02396-02461","华北");
//      gt.GO_SQLite("房文迪","01311-02769","华东");
//      gt.GO_SQLite("严加远","02770-02781","华东");
//      gt.GO_SQLite("吴健俊","01281-01596","西南");
//      gt.GO_SQLite("周盛","01597-03456","西南");
//      gt.GO_SQLite("占文冲","01552-03100","西北");
        
        
        System.out.println("共花费:"+(double)(System.currentTimeMillis()-begin)/1000+"秒");
    }
}

```

---
<br><br>



Utils包
------------------

1.UseDB.java
```
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
import java.util.List;


/** 更新时间： 2016.12.08
 *   
 *  数据库封装类
 *          单例模式,【兼容MySQL(导入mysql-connector-java-5.1.38-bin.jar)和SQLite(导入sqlite-jdbc-3.15.1.jar)】
 *          方法1-基础使用
 *          方法2-查询 【SELECT】
 *          方法3-插入【INSERT】
 *          方法4-执行SQL语句
 *              
 *              @author suvan
 *
 */
public class UseDB {
    
    //静态成员变量，支持单例模式
    private static  volatile UseDB udb = null;
    
    
    private static String databaseType;  //数据库类型【例如:Mysql,Oracle,SQLite之类的】
    
    private String drive;                            //数据库驱动
    private String link;                             //【mysql—JDBC链接+IP地址+端口】

    private String username;                  //用户名
    private String password;                    //密码
    
    private Connection conn=null;
    private Statement st=null;
    private PreparedStatement pst=null;
    private ResultSet rs=null;
    private ResultSetMetaData rsmd=null;//获取列的信息
    
    private String sql; //sql语句
    
        
        //有参构造函数进行赋值
        public UseDB(String dbType){
        
            this.databaseType=dbType.toLowerCase(); //设定当前工具类使用什么类型的数据库
            
            if("mysql".equals(databaseType)){   //转换为小写匹配
                
                this.drive="com.mysql.jdbc.Driver";             
                this.link="jdbc:mysql://127.0.0.1:3306/";     
                this.username="root";                                   
                this.password="liushuwei";                  
                
            }else if("sqlite".equals(databaseType)){
                
                this.drive="org.sqlite.JDBC";                       //数据库驱动
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
//           System.out.println("数据库连接成功......");
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
            if(pst!=null)   pst.close();    //关闭PreparedStatement
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
                                    sb.append("-0-&");
                                }else{
                                    sb.append(rs.getString(i)+"&");
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
        public ArrayList<String> selectAskinformation_Scope(String table,String condition,String scope) throws SQLException{ //参数:表名,条件,范围行数【使用-进行分隔,例如1-5】
//              StringBuilder sb= new StringBuilder();
                ArrayList<String> alist = new  ArrayList<String>();
                
                String [] scope_arrays =scope.split("-");
                int begin=Integer.parseInt(scope_arrays[0]);
                int end=Integer.parseInt(scope_arrays[1]);
                
                sql="SELECT * FROM "+table+" WHERE "+condition+";";
                rs=st.executeQuery(sql);
                rsmd=rs.getMetaData();
                while(rs.next()){
                    for(int i=begin;i<=end;i++){ //遍历范围区间的字段
                        if(rs.getString(i)==null || rs.getString(i).length()<2) continue;
                        alist.add(rsmd.getColumnName(i));//只要列名
                    }       
                }

            return alist;   
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
                    String sql="INSERT OR IGNORE  INTO  "+table+" VALUES("+content+");";//去重插入【应用于SQLite】遇到重复就跳过OR IGNORE
                    int i=st.executeUpdate(sql);
        }
        
        //方法3-2: 往table表record字段,插入一行content数据【少量数据(标题，日期等)】【多字段插入   ,分隔字段     ,号分隔内容】
        public void insert(String table,String record,String content) throws SQLException{//参数: 表名,字段,内容
                    String sql="INSERT INTO "+table+"("+record+") VALUES("+content+");";
                    int i=st.executeUpdate(sql);
                    if(i!=-1){
//                      System.out.println("*****************"+table+"表INSERT成功！****************");
                    }
        }
                
        //方法3-3:往table表record，插入一行content数据 【大量数据(文章，文本内容)】【,分隔字段 &&&&& 分隔内容   ,分隔占位符】
        public void insert(String table,String record,String content,String mark) throws SQLException, UnsupportedEncodingException{//参数值: 表名,字段,内容，占位符(?)
//                  content =new String(content.getBytes(),"UTF-8");
            
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
//                          System.out.println("**********************"+table+"表中不存在"+content_Arrays[0]+"记录,正在进行INSERT。。。。。。。。。。。。。");
                            sql="INSERT INTO "+table+"("+record+") VALUES("+mark+");";
                            
                            pst=conn.prepareStatement(sql);
                            
                            for(int i=1;i<=mark_Arrays.length;i++){//根据占位符个数【?的数量】进行循环: ?号个数            
                                pst.setString(i,mark_Arrays[i-1]);
                            }
                            
                            pst.executeUpdate();
//                          System.out.println("*****************"+table+"表INSERT成功！****************");
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
        public void selectSQL(String sql) throws SQLException{
            rs = st.executeQuery(sql);
            rsmd=rs.getMetaData();
            while(rs.next()){
                for(int i=1;i<=rsmd.getColumnCount();i++){
                    System.out.print(rsmd.getColumnName(i)+":"+rs.getString(i)+"\t");
                }
                System.out.println();//换行
            }
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
        
}
```

---
<br><br>


2.UseExcel.java
```
package Utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**更新时间:2016.12.13
 *          操作Excel工作薄
 *              方法1：使用jxl.jar   【创建和读取excel】
 *              方法2：使用poi.jar 【创建和读取excel】
 *      @author Suvan
 */
public class UseExcel {
    
    
//  //方法1-2：创建Excel文件，写入数据
//  public  static void createExcel() throws IOException,WriteException{
//      //1.新建文件，设置目录
//      File xlsFile = new File("C:\\Users\\Liu-shuwei\\Desktop\\jxl.xls"); 
//      
//      //2.创建工作簿
//      WritableWorkbook workbook = Workbook.createWorkbook(xlsFile);
//      
//      //3.创建工作表
//       WritableSheet sheet = workbook.createSheet("sheet1", 0);
//        for (int row = 0; row < 10; row++){   
//           for (int col = 0; col < 10; col++) {
//               
//               //第col列【从0开始】，第row行【从0开始】，数据
//               Label lb = new Label(col,row,"数据【"+col+"列"+row+"行】");
//               sheet.addCell(lb);  // 向工作表中添加数据
//           }
//        }
//        
//        //4.写入数据
//        workbook.write();
//        
//        //5.关闭流
//        workbook.close();
//  }
//  
//  //方法1-2：读取Excel
//  public  void readExcel() throws IOException,BiffException{
//      
//      //1.定位文件
//      File xlsFile = new File("C:\\Users\\Liu-shuwei\\Desktop\\第5周-8.23.xls");
//      
//      //2.获得工作薄对象
//      Workbook workbook = Workbook.getWorkbook(xlsFile);
//      
//      //3.获得所有工作表
//      Sheet [] sheets = workbook.getSheets();
//      
//      //4.遍历工作表
//      if(sheets != null){
//          for(Sheet sheet:sheets){
//              int rows = sheet.getRows();             //获得行数
//              int cols = sheet.getColumns();      //获得列数
//              
//              //读取数据
//              for(int row =1;row<rows;row++){
////                    Cell    [] cells =sheet.getRow(row);//获取整行数据,保存进数组,Sheet.getColumn()是获取整列
//
//                  for(int col = 0;col < cols;col++){
//                      System.out.printf("%10s",sheet.getCell(col,row).getContents());
//                  }
//                  System.out.println(); //换行
//              }
//          }
//      }
//  
//      workbook.close();
//  }
    
    
//************************************************************************************************************************
    //方法2-1：poi创建excel
    public static void createExcel_poi() throws IOException{
            //1.创建工作簿
            HSSFWorkbook workbook =new HSSFWorkbook();
            
            //2.创建工作表
            HSSFSheet sheet = workbook.createSheet("suvan");
            for(int row =0;row<10;row++){
                HSSFRow rows = sheet.createRow(row);
                for(int col=0;col<10;col++){
                    //向工作表添加数据
                    rows.createCell(col).setCellValue("数据第"+row+"行-"+col+"列");
                }
            }
            
            //3.创建文件
            File xlsFile = new File("C:\\Users\\Liu-shuwei\\Desktop\\suvan.xls");
            FileOutputStream xlsStream = new FileOutputStream(xlsFile);
            workbook.write(xlsStream);
            workbook.close();
    }
    
    //方法2-2:poi读取excel文件
    public static void readExcel_poi() throws EncryptedDocumentException, InvalidFormatException, IOException{
        //1.定位文件
        File xlsFile = new File("C:\\Users\\Liu-shuwei\\Desktop\\第5周-8.23.xls");
        //2.获得工作薄【WorkbookFactory位于poi-ooxml-3.14-20160307.jar】
        org.apache.poi.ss.usermodel.Workbook workbook =WorkbookFactory.create(xlsFile);
        //3.获得工作表个数
        int sheetCount = workbook.getNumberOfSheets();
        ///4.遍历工作表
        for(int i=0;i<sheetCount;i++){
             //a.选择工作表
            Sheet sheet = workbook.getSheetAt(i);
            
            //b.获取总行数和列数
            int rows =sheet.getLastRowNum()+1;          //总行数
            Row tmp = sheet.getRow(0);
            if(tmp == null) continue;
            int cols = tmp.getPhysicalNumberOfCells();  //总列数

            //c.读取数据
            for(int row =0;row<rows;row++){
                Row r = sheet.getRow(row);
                for(int col=0;col<cols;col++){
                    if(r.getCell(col)!=null){
                        r.getCell(col).setCellType(Cell.CELL_TYPE_STRING); //设置Cell类型，然后就可以把纯数字作为String类型读出来
                        System.out.printf("%10s",r.getCell(col).getStringCellValue());
                    }
                }
        
                System.out.println();//换行
            }
        }
        
        workbook.close();
    }
    
}
```

---



<br><br><br><br><br>


版本5.0【2016.12.18】
==============================
<br>

版本更新：
1. 5.0【GUI版】,添加UI界面,使用Swing原生界面
2. 优化代码,使用工具类UseExcel.java
3. 将程序打包成jar【Export--->Runnable JAR file】

<br><br>

未填的坑
+ 一个帅气的界面
+ 自定义语法修改模版
+ 进度条
+ 回调刷新窗口
+ 定时器
+ 多线程生成

<br><br>

项目结构【打包成suvan.jar】
BatchCreate_file
&emsp;src
&emsp;&emsp;extend.example
&emsp;&emsp;&emsp;GetFirstLetter.java
&emsp;&emsp;main
&emsp;&emsp;&emsp;AlterContent.java
&emsp;&emsp;&emsp;GetTableData.java
&emsp;&emsp;&emsp;GoTask.java
&emsp;&emsp;&emsp;NumBean.java
&emsp;&emsp;&emsp;test.java
&emsp;&emsp;UI
&emsp;&emsp;&emsp;alterModelUI.java
&emsp;&emsp;&emsp;insertUI.java
&emsp;&emsp;&emsp;mainUI.java
&emsp;&emsp;&emsp;ProgressBar.java
&emsp;&emsp;Utils
&emsp;&emsp;&emsp;IOFile.java
&emsp;&emsp;&emsp;UseDB.java
&emsp;&emsp;&emsp;UseExcel.java
&emsp;&emsp;&emsp;UseString.java
&emsp;JRE System Libary[JavASE -1.8]
&emsp;Referenced Libraries
&emsp;Data
&emsp;&emsp;record.suvan
&emsp;Excel
&emsp;lib
&emsp;&emsp;jxl.jar
&emsp;&emsp;mysql-connector-jar.5.1.38-bin.jar
&emsp;&emsp;poi-3.14-20160307.jar
&emsp;&emsp;poi-ooxml-3.140-2016030.jar
&emsp;&emsp;sqlite-jdbc-3.15.1.jar
&emsp;model_file
&emsp;&emsp;BackupTask.java
&emsp;&emsp;BackupTask.txt
&emsp;&emsp;ZhaoGoServiceModel.java
&emsp;&emsp;ZhaoGgServiceModel.txt
&emsp;SQLite
&emsp;&emsp;&emsp;batchcreate_file.db

<br><br>

BrachCreate5.0【GUI版】的文件结构
&emsp;BatchCreate 文档.txt
&emsp;用于测试的excel表.xls
&emsp;运行程序.bat
&emsp;BrachCreate5.0
&emsp;&emsp;Data
&emsp;&emsp;&emsp;record.suvan
&emsp;&emsp;model_file
&emsp;&emsp;&emsp;BackupTask.java
&emsp;&emsp;&emsp;BackupTask.txt
&emsp;&emsp;&emsp;ZhaoGoServiceModel.java
&emsp;&emsp;&emsp;ZhaoGgServiceModel.txt
&emsp;&emsp;SQLite
&emsp;&emsp;&emsp;batchcreate_file.db
&emsp;&emsp;suvan.jar


<br><br>

Code【贴上所有代码】
----------------------

extend.example包
```
package extend.example;

public class GetFirstLetter {
    private static int BEGIN = 45217;
    private static int END = 63486;
    
    //按照声母表示，这个表是在GB2312中的出现的第一个汉字，也就是说“啊”是代表首字母a的第一个汉字
    // 二十六个字母区间对应二十七个端点    // GB2312码汉字区间十进制表示
    private static char[] chartable = {'啊', '芭', '擦', '搭', '蛾', '发', '噶', '哈',        '哈', '击', '喀', '垃', '妈', '拿', '哦', '啪', '期', '然', '撒', '塌', '塌',        '塌', '挖', '昔', '压', '匝',}; 
    private static int [] table = new int[27]; // 对应首字母区间表

    // 对应首字母区间表
    private static char[] initialtable = {'a', 'b', 'c', 'd', 'e', 'f', 'g',        'h', 'h', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',        't', 't', 'w', 'x', 'y', 'z',};
    
    //1.初始化
     static {        
         for (int i = 0; i < 26; i++) {            
             table[i] = gbValue(chartable[i]);// 得到GB2312码的首字母区间端点表，十进制。 
         }
         table[26] = END;// 区间表结尾
     }

     /* 根据一个包含汉字的字符串返回一个汉字拼音首字母的字符串 最重要的一个方法，思路如下：一个个字符读入、判断、输出     */
     public static String getFirstLetter(String sourceStr) {        
            String result = "";        
            String str = sourceStr.toLowerCase();       
            int StrLength = str.length();        
            int i;       
            try {            
                for (i = 0; i < StrLength; i++) {                
                    result += Char2Initial(str.charAt(i));            
                    }        
                } catch (Exception e) {            
                    result = "";        
                    }        
            return result;   
        }
     
      /* 输入字符,得到他的声母,英文字母返回对应的大写字母,其他非简体汉字返回 '0'     */  
     private static char Char2Initial(char ch) {    
                // 对英文字母的处理：小写字母转换为大写，大写的直接返回
              if (ch >= 'a' && ch <= 'z') {            
                  return ch;        
               }        
              if (ch >= 'A' && ch <= 'Z') {
                  return ch;  
                }
         
              // 对非英文字母的处理：转化为首字母，然后判断是否在码表范围内，        // 若不是，则直接返回。        // 若是，则在码表内的进行判断。   
              int gb = gbValue(ch);// 汉字转换首字母
              if ((gb < BEGIN) || (gb > END)){            
                  return ch;        
                 }
    
              int i;       
              for (i = 0; i < 26; i++) { // 判断匹配码表区间，匹配到就break,判断区间形如“[,)”
                  if ((gb >= table[i]) && (gb < table[i + 1])) {
                      break;            
                      }        
              }
              if (gb == END) { //补上GB2312区间最右端    
                     i = 25;        
                }      
            return initialtable[i]; // 在码表区间中，返回首字母    }
         }

     
     /* 取出汉字的编码 cn 汉字     */  
    private static int gbValue(char ch) {
        String str = new String();        
        str += ch;       
        try {           
            byte[] bytes = str.getBytes("GB2312");            
            if (bytes.length < 2) {               
                    return 0;           
            }            
            return (bytes[0] << 8 & 0xff00) + (bytes[1] & 0xff);      
        } catch (Exception e) {          
            return 0;        
            }    
    }   
    
//  public static void main(String [] args){
//      //在main方法中调用FirstLetterUtil类的getFirstLetter()方法，获取姓名的首字母。如：“刘德华”获取首字母是“ldh”。
//       System.out.print(getFirstLetter("日你仙人掌"));//获取文字首字母的拼音
//  }
    
    
}

```

---
<br><br>

main包
```
package main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import Utils.IOFile;
import Utils.UseDB;
import Utils.UseString;

/**更新时间： 2016.12.13
 * 
 *  获取模版内容，根据编号各个属性值，修改BackupTask.java和入库文件，创建文件
 *          @author Suvan
 *
 */
public class AlterContent {
        
        private IOFile iof;
        private UseDB udb;
        private UseString us;
        private GetTableData gtd;
        
        //有参构造函数，创建对象时进行赋值
        public AlterContent(IOFile iof,UseDB udb,UseString us,GetTableData gtd){  
            this.iof=iof;
            this.udb=udb;
            this.us=us;
            this.gtd=gtd;
        }
    

        //方法1:修改BackupTask.java,并创建
        public  void aBackupTask(String path,String fileName,String content,String sign,ArrayList<String> rukuName_list) throws IOException{//参数:路径，文件名，内容，标记【修改还是创建】,入库名,项目类           
                
             //1.修改模版内容
            String BackupTask_model="\n\t\t"+"NameA=(NameB) ac.getBean(\"fileName\");" +
                                                              "\n\t\t" +"NameA.initNameB();";
                String attributeName="";//属性名
                String cStr ="";
                StringBuilder sb = new StringBuilder();//储存各个所有内容
                for(int i=0;i<rukuName_list.size();i++){
                    attributeName =rukuName_list.get(i).substring(rukuName_list.get(i).lastIndexOf("_")+1);
                    cStr=BackupTask_model.replace("NameA",us.LowFirstString(attributeName))
                                                              .replace("NameB",us.UpFirstString(attributeName))
                                                              .replace("fileName",rukuName_list.get(i));
                    sb.append(cStr);
                }
                sb.append("\n\t\t//ok;");//用于叠加补充
                
                //2.覆盖或者修改文件
                if("cover".equals(sign)){//覆盖 【与上一个编号完全不同(号码不同,且不带-)】
                    content=content.replace("//changeModel;",sb.toString());

                     iof.cFile(path, "BackupTask.java", "UTF-8", content);
                }else if("alter".equals(sign)){ //修改 【带-的编号,在上一个文件基础上进行叠加修改】
                    content=content.replace("//ok;",sb.toString());

                    iof.cFile(path, "BackupTask.java", "UTF-8", content);
                }
            
        }
        
        
        //方法2:修改入库文件
        public void aRK(String path,String fileName,String content,String type,String username,NumBean numbean) throws IOException{
            

                //一.根据类型生成相应名称
                HashMap<String,String> hm = gtd.getTypeAllName(type);

                
                //二.根据需求修改content
                        //A.判断信息类型
                        String infType =numbean.getInftype();
                        if(infType !=null  && ! "-0-".equals(infType)){                                     //zbgg.setInfoType("服务");// 信息类型
                            content=content.replaceFirst("//是否有信息类型;","zbgg.setInfoType(\""+infType+"\");//信息类型");
                        }else{
                            content=content.replaceFirst("//是否有信息类型;","//zbgg.setInfoType(\"服务\");//信息类型");
                        }
                    
                        //B.判断行业分类
                        String workType=numbean.getWorktype();
                        if(workType==null && ! "-0-".equals(workType)) {                                 //zbgg.setIndustry("建筑建材"); // 行业分类
                            content=content.replaceFirst("//是否有行业分类;","zbgg.setIndustry(\""+workType+"\"); //行业分类");
                        }else{
                            content=content.replaceFirst("//是否有行业分类;","//zbgg.setIndustry(\"建筑建材\"); // 行业分类");
                        }

                    
                //三.修改模版
                content=content.replace("zhaoBiaoGongGao",hm.get("typeSpell"))
                                                                             .replace("ZhaoBiaoGongGao",us.UpFirstString(hm.get("typeSpell")))
                                                                             .replace("ZhaobGgService",us.UpFirstString(hm.get("typeService")))
                                                                             .replace("zhaobGgService",hm.get("typeService"))
                                                                             .replace("zbgg",type);
                content=content.replaceFirst("//@程序猿;",username)
                                            .replaceFirst("//@内容;",numbean.getArea()+"_"+numbean.getProvince()+"_"+numbean.getCity()+"_"+numbean.getCounty()+"_"+hm.get("typeChinese")+"_"+numbean.getNum())                
                                            .replaceFirst("//编号;",numbean.getNum());        
                String className =fileName.substring(0,fileName.lastIndexOf("."));
                content=content.replaceFirst("//注解;",className)
                                            .replaceFirst("//类名;",className);
                content=content.replaceFirst("//Area;",numbean.getArea())
                                             .replaceFirst("//Province;",numbean.getProvince().replaceFirst("省","").replaceFirst("市","").replaceFirst("区","").replaceFirst("县",""))
                                            .replaceFirst("//Webname;",numbean.getWebname())
                                            .replaceFirst("//Infsource;",numbean.getInfsource())
                                            .replace("//表名;",hm.get("tableName"));
                
                //仅有2个字符的时候保留，省,市，区
                if(numbean.getCity().length()==2){
                    content=content.replaceFirst("//City;",numbean.getCity());
                }else{
                    content=content.replaceFirst("//City;",numbean.getCity().replaceFirst("省","").replaceFirst("市","").replaceFirst("区","").replaceFirst("县",""));
                }
                
                if(numbean.getCounty().length()==2){
                    content=content.replaceFirst("//County;",numbean.getCounty());
                }else{
                    content=content.replaceFirst("//County;",numbean.getCounty().replaceFirst("省","").replaceFirst("市","").replaceFirst("区","").replaceFirst("县",""));
                }
                        
                        
                
                //四.创建编号相应类型的入库文件
                iof.cFile(path, fileName, "UTF-8", content);
        }       
}

**********************************************************************


package main;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Pattern;

import Utils.IOFile;
import Utils.UseDB;
import Utils.UseExcel;
import jxl.read.biff.BiffException;

/**更新时间： 2016.12.18
 * 
 *  获取数据，处理数据，归纳信息
 *          @author Suvan
 *
 */
public class GetTableData {

    private IOFile iof;
    private UseDB udb;
    
    //有参构造函数，创建对象时进行赋值
    public GetTableData(IOFile iof,UseDB udb){
        this.iof=iof;
        this.udb=udb;
    }
    
    
    //方法1: 插入模版数据,并返回模版内容
        public String getModel(String filePath) throws IOException,SQLException{//参数：文件路径
            
            //1.读取文件内容                                                                                                      
            String fileContent=iof.rFileContent(filePath,"UTF-8");   //注意：路径的话用/或者\\都可以,数据库的默认编码是GBK
            String modeName=filePath.substring(filePath.indexOf("/")+1);            //模版名字
            
            //2.判断是否存在相同模版
            String modeInf = udb.select("article", "model", "model='"+modeName+"'");

            if(modeInf.length() <1){    //判断是否已存在模版
                udb.insertReplace("article","model,detail","'"+modeName+"','"+fileContent.replace("'", "''")+"'"); 
            }else{
                System.out.println("\n********************************已存在"+modeInf+"模版，不进行重复插入****************************");
            }
        
            return fileContent;
        }
    
    //方法2：获取table表中，num编号的所有信息,储存进NumBean，返回NumBean
    public  NumBean getNumInformation(String table,String num) throws SQLException{//参数：表名,编号

            //查询table表，num字段所有数据，用&分隔
            String numInf = udb.select(table, "num", "num='"+num+"'", '*');
            String [] numInf_arrays=numInf.split("&");
            
            //表共21列，Numbean共21个属性，拥有Getters和Setters
            NumBean nb = new NumBean();
                nb.setNum(numInf_arrays[0]);
                nb.setArea(numInf_arrays[1]);
                nb.setProvince(numInf_arrays[2]);
                nb.setCity(numInf_arrays[3]);
                nb.setCounty(numInf_arrays[4]);
                nb.setWebname(numInf_arrays[5]);
                nb.setWeburl(numInf_arrays[6]);
                nb.setInfsource(numInf_arrays[7]);
                nb.setInftype(numInf_arrays[8]);
                nb.setWorktype(numInf_arrays[9]);
                nb.setZbyg(numInf_arrays[10]);
                nb.setZbgg(numInf_arrays[11]);
                nb.setZsjg(numInf_arrays[12]);
                nb.setGgbg(numInf_arrays[13]);
                nb.setZbwj(numInf_arrays[14]);
                nb.setZbdy(numInf_arrays[15]);
                nb.setZbxx(numInf_arrays[16]);
                nb.setKzj(numInf_arrays[17]);
                nb.setLot(numInf_arrays[18]);
                nb.setWebtype(numInf_arrays[19]);
                nb.setRemark(numInf_arrays[20]);
        
        return nb;
    }
    
    
    //方法3：获取table表的，所有编号
    public ArrayList<String> getAllNum(String table) throws SQLException{ //参数: 表名
            ArrayList<String> numList = new  ArrayList<String>();
            
            String allNum =udb.selectAllColumn(table,"num");
            String [] allNum_arrays = allNum.split("##");
            for(int i=0;i<allNum_arrays.length;i++){
                numList.add(allNum_arrays[i]);
            }
            return numList;
    }
    
    //方法4:判断num编号属否在scope区间里面，返回布尔类型参数【true-属于，false-不属于】
    public Boolean getMinMaxScope(String scope,int num){//参数：范围，编号
            Boolean sign = false;//判断标识，默认为true
            
            String [] scope_arrays = scope.split("-");
            int minScope =Integer.parseInt(scope_arrays[0]);
            int maxScope =Integer.parseInt(scope_arrays[1]);
            
                if(minScope <= num && num <= maxScope){
                    sign =true;
                }
            
            return sign;
    }
    
    
    //方法5:获取当前编号拥有URL的字段名【类型】
    public ArrayList<String> getNum_HaveURLRecord(String table,String num) throws SQLException{ //参数：表名，编号

        //查询数据库table表，num字段的11-18行，得到拥有记录的列名的ArrayList
        ArrayList<String> alist=udb.selectAskinformation_Scope(table,"num='"+num+"'", "11-18");//【11-18是招标预告到控制价】

        return alist;
    }

    //方法6：根据列名，得到相应类型的字符串
    public String getTypename(String columnName){ //参数：列名
                String type="";

                if(columnName.equals("zbyg")){
                    type="ZhaobYgService";
                }else if(columnName.equals("zbgg")){
                    type="ZhaobGgService";
                }else if(columnName.equals("zsjg")){
                    type="ZisJgService";
                }else if(columnName.equals("ggbg")){
                    type="GonggBgService";
                }else if(columnName.equals("zbwj")){
                    type="ZhaobWjService";
                }else if(columnName.equals("zbdy")){
                    type="ZhaobDyService";
                }else if(columnName.equals("zbxx")){
                    type="ZhongbXxService";
                }else if(columnName.equals("kzj")){
                    type="KongZjService";
                }
        
        return type;
    }
    

    
    //方法7：根据类型生成相应名称，返回HaspMap【无序键值队】
    public HashMap<String,String> getTypeAllName(String type){  //参数：类型名，也是列名
        HashMap<String,String> hm = new HashMap<String,String>();

                if(type.equals("zbyg")){
                     hm.put("typeSpell", "zhaoBiaoYuGao");
                     hm.put("typeService","zhaobYgService");
                     hm.put("typeChinese","招标预告");
                     hm.put("tableName","t_zhao_biao_yu_gao");
                }else if(type.equals("zbgg")){
                     hm.put("typeSpell", "zhaoBiaoGongGao");
                     hm.put("typeService","zhaobGgService");
                     hm.put("typeChinese","招标公告");
                     hm.put("tableName","t_zhao_biao_gong_gao");
                }else if(type.equals("zsjg")){
                     hm.put("typeSpell", "ziShenJieGuo");
                     hm.put("typeService","zisJgService");
                     hm.put("typeChinese","咨审结果");
                     hm.put("tableName","t_zi_shen_jie_guo");
                }else if(type.equals("ggbg")){
                     hm.put("typeSpell", "gongGaoBianGeng");
                     hm.put("typeService","gonggBgService");
                     hm.put("typeChinese","公告变更");
                     hm.put("tableName","t_gong_gao_bian_geng");
                }else if(type.equals("zbwj")){
                     hm.put("typeSpell", "zhaoBiaoWenJian");
                     hm.put("typeService","zhaobWjService");
                     hm.put("typeChinese","招标文件");
                     hm.put("tableName","t_zhao_biao_wen_jian");
                }else if(type.equals("zbdy")){
                    hm.put("typeSpell", "ZhaoBiaoDaYi");
                     hm.put("typeService","zhaobDyService");
                     hm.put("typeChinese","招标答疑");
                     hm.put("tableName","t_zhao_biao_da_yi");
                }else if(type.equals("zbxx")){
                    hm.put("typeSpell", "zhongBiaoXinXi");
                     hm.put("typeService","zhongbXxService");
                     hm.put("typeChinese","中标信息");
                     hm.put("tableName","t_zhong_biao_xin_xi");
                }else if(type.equals("kzj")){
                     hm.put("typeSpell", "kongZhiJia");
                     hm.put("typeService","kongZjService");
                     hm.put("typeChinese","控制价");
                     hm.put("tableName","t_kong_zhi_jia");
                }               
        
        return hm;      
    }
    
    
//************************************************************************************************************************
    
    
    //方法SQLite-1：创建表结构
    public void s_CreateTable(String useTable) throws SQLException{
        
         //创建模版表
        String sql1= "CREATE TABLE if not exists article("  +            
                                "id  INCREMENT primary key," +
                                "model TEXT UNIQUE NOT NULL,"               +
                                "detail TEXT NOT NULL"      +
                                ");" ;
        //获取Excel数据表
        String sql2="CREATE TABLE if not exists "+useTable+"(" +
                             "num TEXT primary key," +
                             "area TEXT NOT NULL," +
                             "province TEXT," +
                             "city TEXT," +
                             "county TEXT," +
                             "webname TEXT," +
                             "weburl  TEXT," +
                             "infsource TEXT," +
                             "inftype   TEXT," +
                             "worktype TEXT," +
                             "zbyg  TEXT," +
                             "zbgg    TEXT," +
                             "zsjg  TEXT," +
                             "ggbg TEXT," +
                             "zbwj TEXT," +
                             "zbdy TEXT," +
                             "zbxx TEXT," +
                             "kzj TEXT," +
                             "lot TEXT," +
                             "webtype TEXT," +
                             "remark TEXT"  +
                             "  )";
        
          udb.createSQL(sql1); 
          udb.createSQL(sql2); 
    }
    
    
    //方法SQLite-2.读取Excel表的数据，插入SQLite
    public void s_InsertExcel(String excelPath) throws IOException,BiffException,SQLException{//参数：Excel文件名,导入哪些工作表编号,导入哪个表
        
    
        
            UseExcel ue =new UseExcel();                                        //工具类
        
            //1.数据获取excel表的数据
            HashMap [] sheets_map=ue.getExcel(excelPath);       
        
            //根据excel文件名建表
            String tableName=excelPath.substring(excelPath.lastIndexOf("\\")+1,excelPath.lastIndexOf("."));
            this.s_CreateTable(tableName);                                                              //创建表【article-保存模版,(useTable)-Excel工作薄数据】
            
            //2.正则判断
            Pattern p = Pattern.compile("\\d+");//正则判断n行的第1列的数据是否是编号【开头是否为数字】，不是则跳过

            //3.执行导入操作
            if(sheets_map.length>0){
                System.out.println("将Excel数据插入SQLite数据库的"+tableName+"表--------------------------->");
                System.out.println("公有"+sheets_map.length);
                //A-遍历工作表
                for(int n=0;n<sheets_map.length;n++){
                    HashMap<Integer,ArrayList> rows_map = sheets_map[n];        //获取行数据

                    //B-遍历行
                    for(int r=0;r<rows_map.size();r++){                                         
                        ArrayList<String> cols_list = (ArrayList)rows_map.get((Integer)r);;                             //获取每列数据
                        
                        System.out.print(r+"行......");  
                        if(cols_list.size() <1  | !(p.matcher(cols_list.get(0))).find())    continue;   //整行不存在数据 or 匹配第1列数据,不是数字开头【编号格式01039】,跳过
                        if(r %50 ==0)   System.out.println();                                                       //若50条记录换行一次
                        
                        //C-遍历列
                        StringBuilder sb =new StringBuilder("'");
                        for(int c=0;c<21;c++){                                                                               //【只需要21列数据】
                            //拼接SQL
                            if(c==20){
                                sb.append(cols_list.get(c)+"'");
                                continue;
                            }
                                
                            if(cols_list.get(c).length()<1)
                                sb.append("无','");
                            else
                                sb.append(cols_list.get(c)+"','");
                            
//                          udb.insert(useTable,                                                                                //插入数据
//                          "'"+cols_list.get(0)+"','"+cols_list.get(1)+"','"+cols_list.get(2)+"','"+cols_list.get(3)+"','"+cols_list.get(4)+"','"
//                              +cols_list.get(5)+"','"+cols_list.get(6)+"','"+cols_list.get(7)+"','"+cols_list.get(8)+"','"+cols_list.get(9)+"','"
//                              +cols_list.get(10)+"','"+cols_list.get(11)+"','"+cols_list.get(12)+"','"+cols_list.get(13)+"','"+cols_list.get(14)+"','"
//                              +cols_list.get(15)+"','"+cols_list.get(16)+"','"+cols_list.get(17)+"','"+cols_list.get(18)+"','"+cols_list.get(19)+"','"
//                              +cols_list.get(20)+"'");
                        }
                        udb.insert(tableName,sb.toString());                                                        //插入数据
                    }
                }
            }
            
    }
    
}


**********************************************************************
package main;

/**更新时间： 2016.11.27
 * 
 *  JavaBena，每一个编号
 *          @author Suvan
 *
 */
public class NumBean {
    
        private String num;             //网站数据源编码【编号】
        private String area;                //区域
        private String province;        //省份
        private String city;                    //城市
        private String county;          //区县
        private String webname;      //网站数据源名称
        private String weburl;          //网址
        private String infsource;       //信息来源
        private String inftype;         //信息类型
        private String worktype;        //行业分类
        private String zbyg;                //招标预告
        private String zbgg;                //招标公告
        private String zsjg;                //咨审结果
        private String ggbg;                //公告变更
        private String zbwj;                //招标文件
        private String zbdy;                //招标答疑
        private String zbxx;                //中标信息
        private String kzj;                 //控制价
        private String lot;                 //批次
        private String webtype;     //原网站信息分类
        private String remark;          //备注
    
            public String getNum() {
                return num;
            }
            public String getArea() {
                return area;
            }
            public String getProvince() {
                return province;
            }
            public String getCity() {
                return city;
            }
            public String getCounty() {
                return county;
            }
            public String getWebname() {
                return webname;
            }
            public String getWeburl() {
                return weburl;
            }
            public String getInfsource() {
                return infsource;
            }
            public String getInftype() {
                return inftype;
            }
            public String getWorktype() {
                return worktype;
            }
            public String getZbyg() {
                return zbyg;
            }
            public String getZbgg() {
                return zbgg;
            }
            public String getZsjg() {
                return zsjg;
            }
            public String getGgbg() {
                return ggbg;
            }
            public String getZbwj() {
                return zbwj;
            }
            public String getZbdy() {
                return zbdy;
            }
            public String getZbxx() {
                return zbxx;
            }
            public String getKzj() {
                return kzj;
            }
            public String getLot() {
                return lot;
            }
            public String getWebtype() {
                return webtype;
            }
            public String getRemark() {
                return remark;
            }
            public void setNum(String num) {
                this.num = num;
            }
            public void setArea(String area) {
                this.area = area;
            }
            public void setProvince(String province) {
                this.province = province;
            }
            public void setCity(String city) {
                this.city = city;
            }
            public void setCounty(String county) {
                this.county = county;
            }
            public void setWebname(String webname) {
                this.webname = webname;
            }
            public void setWeburl(String weburl) {
                this.weburl = weburl;
            }
            public void setInfsource(String infsource) {
                this.infsource = infsource;
            }
            public void setInftype(String inftype) {
                this.inftype = inftype;
            }
            public void setWorktype(String worktype) {
                this.worktype = worktype;
            }
            public void setZbyg(String zbyg) {
                this.zbyg = zbyg;
            }
            public void setZbgg(String zbgg) {
                this.zbgg = zbgg;
            }
            public void setZsjg(String zsjg) {
                this.zsjg = zsjg;
            }
            public void setGgbg(String ggbg) {
                this.ggbg = ggbg;
            }
            public void setZbwj(String zbwj) {
                this.zbwj = zbwj;
            }
            public void setZbdy(String zbdy) {
                this.zbdy = zbdy;
            }
            public void setZbxx(String zbxx) {
                this.zbxx = zbxx;
            }
            public void setKzj(String kzj) {
                this.kzj = kzj;
            }
            public void setLot(String lot) {
                this.lot = lot;
            }
            public void setWebtype(String webtype) {
                this.webtype = webtype;
            }
            public void setRemark(String remark) {
                this.remark = remark;
            }
}


**********************************************************************
package main;

import java.util.ArrayList;

import Utils.IOFile;
import Utils.UseDB;
import Utils.UseExcel;
import Utils.UseString;
import extend.example.GetFirstLetter;


/**更新时间：2016.12.18
 *      
 * 任务流程
 *           @author Suvan
 *
 */
public class GoTask {
    
    

//  private String numScope="00000-05000";                                                  //编号范围区间
//  private String username="某男子";                                                                  //程序员名称
    private String path="C:/Users/Liu-shuwei/Desktop\\";                                             //路径
    private String catalogName="入库【3.3版,823表(区分程序猿and地区)】";             //文件名
    private Boolean areaMatch=false;                                                                    //是否开启区域匹配【默认不开启】
    private Boolean numMatch=false;                                                                 //是否开启默认匹配【默认不开启】
    private String useExcel;                                                                                    //使用项目根目录下哪个Excel
    private String useTable;                                                                                    //使用哪个表的数据【SQLite的话，不存在表则自动创建】
    
    public void setPath(String path){
        this.path=path;
    }
    public void setCatalogName(String cn){
        this.catalogName=cn;
    }
    public void setAreaMatch(Boolean b){
        this.areaMatch=b;
    }
    public void setNumMatch(Boolean b){
        this.numMatch=b;
    }
    public void setUseExcel(String excel){
        this.useExcel=excel;
    }
    public void setUseTable(String table){
        this.useTable=table;
    }
    
    
    //任务1： 使用MySQL数据库
    public void GO(String username,String numScope,String numArea) {    //参数:程序猿，编号范围，地区
            System.out.println(username+"-------------------------------------------------->Go!");
        
        //执行任务流程
        try{
            
            //一.获取对象
            UseDB udb = new UseDB("mysql");
            IOFile iof=new IOFile();
            UseString us = new UseString();
            
            GetFirstLetter gfl = new GetFirstLetter();

            GetTableData  gtd = new GetTableData(iof,udb);
            AlterContent ac = new AlterContent(iof,udb,us,gtd);
            
            
            udb.connDatabase("batchcreate_file");//连接数据库
            
            
            //二.创建大目录,往数据库插入模版数据
            iof.createCatalog(path, catalogName);
            iof.createCatalog(path+"/"+catalogName, username);
            String model1_BT =gtd.getModel("model_file/BackupTask.java");
            String model2_RK =gtd.getModel("model_file/ZhaobGgServiceModel.java");
            
            
            
            //三.获取数据表数据，并生成目录and文件
                //三-1获取所有编号
                ArrayList<String> numList =gtd.getAllNum(useTable) ; 
                
                
                //三-2.遍历所有编号
                for(int i=0;i<numList.size();i++){
                
                    String num=numList.get(i);                                                      //当前编号
                    String num_=numList.get(i) .replace("-","_");                           //编号下划线形态
                    StringBuilder changePath =new StringBuilder("");                                            //变化路径
                    
                    //P1:判断是否开启编号范围匹配 and 比较当前编号是否在范围区间
                    if( numMatch && !gtd.getMinMaxScope(numScope, us.getInt(num.substring(0,5)))){  //获取前5个数字
                        continue;
                    }
                    //P2:判断是否开启地区匹配 and 进行地区匹配
                        if(areaMatch){
                            String area =udb.select(useTable,"area","num='"+num+"'");
                            if(!area.equals(numArea)){
                                continue;
                            }
                        }
                    
                        
                        //a.获取当前编号的所有数据，并储存到JavaBean
                        NumBean numBean =gtd.getNumInformation(useTable, num);
                        
                        //b.生成入库文件名，存储进ArrayList
                         ArrayList<String> ifURLType_list =gtd.getNum_HaveURLRecord(useTable, num);             //获取当前编号所有"含有URL"的字段的类型【例:招标公告 zbgg】
                         ArrayList<String > rukuName_list=new ArrayList<String>();                                                      //用于储存当前编号所需要的所有入库文件名
                         StringBuilder rukuName=new StringBuilder("");
                         for(String type:ifURLType_list){
                             rukuName.append(gfl.getFirstLetter(numBean.getArea()).toUpperCase()+"_"+num_+"_"+gtd.getTypename(type));   //入库文件完整名【地区首字母(大写)_编号_类型服务名】
                             rukuName_list.add(rukuName.toString());
                             rukuName=rukuName.delete(0, rukuName.length());                                                                                //清空
                         }
                         
                        
                        //d.生成入库子目录
                        String catalog_1="入库-"+num.substring(0,5);                                                                  //目录名
                        changePath.insert(0,path+"/"+catalogName+"/"+username);                                     //子路径1                
                        iof.createCatalog(changePath.toString(),"入库-"+num.substring(0,5));                          //创建入库子目录【入库-编号】
                        
                        
                        //生成BackupTask.java
                         changePath.append("/"+catalog_1);                               //子路径1-2
                         String sign = "cover";                                                          //判断当前编号是需要修改还是覆盖【默认是覆盖，带-编号是alter,不带-是cover】
                            if(i > 0){                                                                           //大于0的时候进行判断   【顶部位置没有上一个编号】
                                if(us.getInt(numList.get(i-1).substring(0,5)) == us.getInt(num.substring(0,5))){            
                                    sign="alter";                                                            //如果和上个编号相等，则修改
                                }
                            }
                            
                         if("cover".equals(sign)){                      //覆盖
                                    ac.aBackupTask(changePath.toString(),"BackupTask.java", model1_BT,sign,rukuName_list);
                         }else if("alter".equals(sign)){                //修改
                             String ageBackupTask=iof.rFileContent(changePath+"/BackupTask.java","GBK");                    //读取已经已经存在的BackupTask.java
                             ac.aBackupTask(changePath.toString(),"BackupTask.java",ageBackupTask ,sign,rukuName_list);
                         }
                         
                        
                        //e.生成入库文件
                        iof.createCatalog(changePath.toString(),"imp");          //生成imp目录
                        changePath.append("/imp");                                       //子路径1-2-3
                        for(int k=0;k<ifURLType_list.size();k++){                    //根据拥有URL的类型，循环生成入库文件
                            ac.aRK(changePath.toString(), rukuName_list.get(k)+".java", model2_RK,ifURLType_list.get(k),username,numBean);
                        }
            }
            
            //四.关闭资源
            udb.closeAll();
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
//***********************************************************************************************************************************
    
    
    //任务2： 使用SQLite
        public void GO_SQLite(String username,String numScope,String numArea) { //参数:程序猿，编号范围，地区
            System.out.println(username+"-------------------------------------------------->Go!");
        
        //执行任务流程
        try{
            
            //一.获取对象
            UseDB udb = new UseDB("SQLite");
            IOFile iof=new IOFile();
            UseString us = new UseString();
            UseExcel ue =new UseExcel();
            
            GetFirstLetter gfl = new GetFirstLetter();

            GetTableData  gtd = new GetTableData(iof,udb);
            AlterContent ac = new AlterContent(iof,udb,us,gtd);
            
            
            udb.connDatabase("batchcreate_file.db");                        //连接数据库【若不存在则创建】
//          gtd.s_CreateTable(useTable);                                            //创建表【article-保存模版,(useTable)-Excel工作薄数据】
//          gtd.s_InsertExcel(useExcel,useTable);                                //插入Excel数据,【传入excel文件名和使用表名】
            
            //二.创建大目录,往数据库插入模版数据
            iof.createCatalog(path, catalogName);
            iof.createCatalog(path+"/"+catalogName, username);
            String model1_BT =gtd.getModel("model_file/BackupTask.java");
            String model2_RK =gtd.getModel("model_file/ZhaobGgServiceModel.java");
            
            
            
            //三.获取数据表数据，并生成目录and文件
                //三-1获取所有编号
                ArrayList<String> numList =gtd.getAllNum(useTable) ; 
                
                
                //三-2.遍历所有编号
                for(int i=0;i<numList.size();i++){
                
                    String num=numList.get(i);                                                      //当前编号
                    String num_=numList.get(i) .replace("-","_");                           //编号下划线形态
                    StringBuilder changePath =new StringBuilder("");                                            //变化路径
                    
                    //P1:判断是否开启编号范围匹配 and 比较当前编号是否在范围区间
                    if( numMatch && !gtd.getMinMaxScope(numScope, us.getInt(num.substring(0,5)))){  //获取前5个数字
                        continue;
                    }
                    //P2:判断是否开启地区匹配 and 进行地区匹配
                        if(areaMatch){
                            String area =udb.select(useTable,"area","num='"+num+"'");
                            if(!area.equals(numArea)){
                                continue;
                            }
                        }
                    
                        
                        //a.获取当前编号的所有数据，并储存到JavaBean
                        NumBean numBean =gtd.getNumInformation(useTable, num);
                        
                        //b.生成入库文件名，存储进ArrayList
                         ArrayList<String> ifURLType_list =gtd.getNum_HaveURLRecord(useTable, num);             //获取当前编号所有"含有URL"的字段的类型【例:招标公告 zbgg】
                         ArrayList<String > rukuName_list=new ArrayList<String>();                                                      //用于储存当前编号所需要的所有入库文件名
                         StringBuilder rukuName=new StringBuilder("");
                         for(String type:ifURLType_list){
                             rukuName.append(gfl.getFirstLetter(numBean.getArea()).toUpperCase()+"_"+num_+"_"+gtd.getTypename(type));   //入库文件完整名【地区首字母(大写)_编号_类型服务名】
                             rukuName_list.add(rukuName.toString());
                             rukuName=rukuName.delete(0, rukuName.length());                                                                                //清空
                         }
                         
                        
                        //d.生成入库子目录
                        String catalog_1="入库-"+num.substring(0,5);                                                                  //目录名
                        changePath.insert(0,path+"/"+catalogName+"/"+username);                                     //子路径1                
                        iof.createCatalog(changePath.toString(),"入库-"+num.substring(0,5));                          //创建入库子目录【入库-编号】
                        
                        
                        //生成BackupTask.java
                         changePath.append("/"+catalog_1);                               //子路径1-2
                         String sign = "cover";                                                          //判断当前编号是需要修改还是覆盖【默认是覆盖，带-编号是alter,不带-是cover】
                            if(i > 0){                                                                           //大于0的时候进行判断   【顶部位置没有上一个编号】
                                if(us.getInt(numList.get(i-1).substring(0,5)) == us.getInt(num.substring(0,5))){            
                                    sign="alter";                                                            //如果和上个编号相等，则修改
                                }
                            }
                            
                         if("cover".equals(sign)){                      //覆盖
                                    ac.aBackupTask(changePath.toString(),"BackupTask.java", model1_BT,sign,rukuName_list);
                         }else if("alter".equals(sign)){                //修改
                             String ageBackupTask=iof.rFileContent(changePath+"/BackupTask.java","UTF-8");                  //读取已经已经存在的BackupTask.java
                             ac.aBackupTask(changePath.toString(),"BackupTask.java",ageBackupTask ,sign,rukuName_list);
                         }
                         
                        
                        //e.生成入库文件
                        iof.createCatalog(changePath.toString(),"imp");          //生成imp目录
                        changePath.append("/imp");                                       //子路径1-2-3
                        for(int k=0;k<ifURLType_list.size();k++){                    //根据拥有URL的类型，循环生成入库文件
                            ac.aRK(changePath.toString(), rukuName_list.get(k)+".java", model2_RK,ifURLType_list.get(k),username,numBean);
                        }
            }
            
            //四.关闭资源
            udb.closeAll();
            
        }catch(Exception e){
            e.printStackTrace();
        }

        }
}

**********************************************************************

package main;

public class test {
    

    //测试主方法
    public static void main(String [] args){
        long begin=System.currentTimeMillis();
        
        //创建任务流程对象
        GoTask gt= new GoTask();
            gt.setPath("C:/Users/Liu-shuwei/Desktop\\");
            gt.setCatalogName("入库【4.0版,823表(完全版-未筛选)】");
            gt.setNumMatch(false);      //是否开启编号范围匹配
            gt.setAreaMatch(false);     //是否开启地区匹配
        
        //方式1：使用本地MySQL******************************************************************************************
        //执行流程(参数:程序猿,编号范围,地区)
        gt.setUseTable("webapi3");  
        gt.GO("陈宇","02065-02145","华中");
        gt.GO("刘聪","02396-02461","华北");
        gt.GO("房文迪","01311-02769","华东");
//      gt.GO("林佳法","02311-02371",);
        gt.GO("严加远","02770-02781","华东");
        gt.GO("吴健俊","01281-01596","西南");
        gt.GO("周盛","01597-03456","西南");
        gt.GO("占文冲","01552-03100","西北");


        //方式2：使用SQLite******************************************************************************************************************************
//      gt.setUseTable("api");          //使用表名【SQLite的若不存在会自动创建】
//      gt.setUseExcel("8.23.xls");             //使用哪个Excel【项目根目录/Excel/】
//      gt.GO_SQLite("陈宇","02065-02145","华中");
//      gt.GO_SQLite("刘聪","02396-02461","华北");
//      gt.GO_SQLite("房文迪","01311-02769","华东");
//      gt.GO_SQLite("严加远","02770-02781","华东");
//      gt.GO_SQLite("吴健俊","01281-01596","西南");
//      gt.GO_SQLite("周盛","01597-03456","西南");
//      gt.GO_SQLite("占文冲","01552-03100","西北");
        
        
        System.out.println("共花费:"+(double)(System.currentTimeMillis()-begin)/1000+"秒");
    }
}
```

---
<br><br>

### UI包
```
/**
 * 
 */
package UI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import Utils.IOFile;

/**最后更新：2016.12.18
 *      修改模版界面
 * 
 * @author Suvan
 */
public class alterModelUI extends JFrame{
    
    private JTextArea fileContent;          //多行文本
    private JScrollPane scrollPane;         //滚动条
    private JButton saveAlter;              //保存修改按钮
    
    //有参构造函数,初始化JFrame,切传入要读取的文件名
    public alterModelUI(String fileName){
        super();
        this.getContentPane().setLayout(null);//设置容器，并且不设置布局
        this.setSize(610,500);
        this.setLocation(0, 300);
        
        load(fileName);         //加载界面
        readFile(fileName); //读取文件,且显示到JTextArea
    }
    
    //方法1：加载界面
    private void load(String fileName){
        
        //1-赋值，构建对象
        if(fileContent == null){
            fileContent = new JTextArea();
            fileContent.setBounds(0,0,605,400);
            
        }if(scrollPane == null){
            scrollPane = new JScrollPane(fileContent);
            scrollPane.setBounds(0,0,605,400);
        }
        if(saveAlter == null){
            saveAlter = new JButton("保存修改");
            saveAlter.setBounds(220,403,150,50);
            
            //A.【保存修改】注册按钮点击事件且添加监听
            saveAlter.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    try{
                        IOFile iof = new IOFile();

                        iof.cFile("model_file", fileName, "UTF-8",fileContent.getText());           //新建文件
                        
                        JOptionPane.showMessageDialog(null, "保存成功", "success",JOptionPane.WARNING_MESSAGE);  //弹出提示对话框
                        
                    }catch(Exception k){
                        JOptionPane.showMessageDialog(null, "保存失败,出现异常", "fail",JOptionPane.WARNING_MESSAGE);  
                        k.printStackTrace();
                    }
                }
            });
            
        }
        
        this.add(scrollPane);
        this.add(saveAlter);
    }
    
    //方法2：读取文件
    private void readFile(String fileName){
        try{
            IOFile iof = new IOFile();
            String content = iof.rFileContent("model_file/"+fileName, "utf-8");
            this.fileContent.setText(content);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}

**********************************************************************

/**
 * 
 */
package UI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import Utils.IOFile;
import Utils.UseDB;
import main.GetTableData;

/**最后更新：2016.12.18
 *      导入excel数据页面
 * 
 * @author Suvan
 */
public class insertUI extends JFrame implements ActionListener{
    
    private JTextField excelFilePath;       //excel表格的文件路径
    private JButton fileChoice;             //文件选择
    private JButton beginInsert;            //开始导入
    
    //无参构造函数
    public insertUI(){
        super();
        this.getContentPane().setLayout(null);  //获取Container容器,装载JFrame窗体,且设置空布局
        this.setTitle("导入excel表数据");                //标题
        this.setSize(300,200);                              //面积
        this.setLocation(610, 0);                           //位置
        
        this.load();    //加载界面
    }
    
    //方法1: 加载界面
    private void load(){
        
        //1.构建组件    
        JLabel lb_excelFilePath = new JLabel("excel文件路径:");
        lb_excelFilePath.setBounds(20,30,100,25);           //设置x,y【屏幕坐标】,width,height【组件宽高】
        
        if(excelFilePath == null){
            excelFilePath = new JTextField();               
            excelFilePath.setBounds(20, 60, 220, 25);   
        }
        if(fileChoice == null){
            fileChoice = new JButton("...");
            fileChoice.setBounds(240,60,25,25);
            
            fileChoice.addActionListener(this);                 //注册按钮监听事件
        }
        if(beginInsert == null){
            beginInsert = new JButton("开始导入");
            beginInsert.setBounds(100,100,90,35);
            
            beginInsert.addActionListener(this);                //注册按钮监听事件
        }
        
        //2.为窗体添加组件
        this.add(lb_excelFilePath);         this.add(excelFilePath);
        this.add(fileChoice);
        this.add(beginInsert);
    }


    //方法2.按钮点击事件
    public void actionPerformed(ActionEvent e){
        
        if("...".equals(e.getActionCommand())){
            //A.构建文件选择器
             JFileChooser fc = new JFileChooser(); 
             fc.setFileSelectionMode(JFileChooser.FILES_ONLY);//设置只能选择文件
             fc.setFileFilter(new FileNameExtensionFilter("只支持Excel97-2003 工作簿(*.xls)","xls"));     //【"提示信息","支持类型"】-文件过滤器
             
             int result = fc.showOpenDialog(this);                  //弹出打开文件对话框
//           int resut =fc.showSaveDialog(this);                        //弹出"保存文件"对话框
              if( result == JFileChooser.APPROVE_OPTION){   //表示获得选中对象
                  File f = fc.getSelectedFile();
                 excelFilePath.setText(fc.getSelectedFile().getPath()); //  获得文件路径设置到JTestField
              }
              
        }else if("开始导入".equals(e.getActionCommand())){
            if(excelFilePath.getText().length()>2){
                try{
                    UseDB udb = new UseDB("SQLite");
                    IOFile iof = new IOFile();
                    GetTableData  gtd = new GetTableData(iof,udb);
                    
                    udb.connDatabase("batchcreate_file.db");                        //连接数据库【若不存在则创建】    
                    gtd.s_InsertExcel(excelFilePath.getText());                              //导入Excel数据,【传入excel文件名和使用表名】

                    JOptionPane.showMessageDialog(null, "导入成功！", "success",JOptionPane.PLAIN_MESSAGE);                                                  //提示信息
                }catch(Exception k){
                    JOptionPane.showMessageDialog(null, "抱歉,导入失败,出现异常！", "fail",JOptionPane.ERROR_MESSAGE);                                 //错误信息
                    k.printStackTrace();
                }
            }else{
                JOptionPane.showMessageDialog(null, "大兄弟,请选择目录or手动填写目录！", "success",JOptionPane.WARNING_MESSAGE);   //警告信息
            }
        }
    }
    
    public static void main(String[] args) {
        insertUI i = new insertUI();
        i.setVisible(true);
    }
}

**********************************************************************
package UI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Utils.IOFile;
import Utils.UseDB;
import main.GoTask;

/**最后更新:2016.12.18
 *      主界面     
 * 
 *@author Suvan
 */
public class mainUI  extends JFrame implements ActionListener{
        
    private  JComboBox<String> db_table;                //选择数据表 -组合框
    private JButton db_insertTable;                         //"导入数据"-按钮
    private JButton model_alterB;                               //"修改模版2"-按钮 【BackTaskup.java】
    private JButton model_alterZ;                               //"修改模版2"-按钮【ZhaoGgServiceModel.java】
    
    private JButton go;                                             //"开始"-按钮
    
    private JLabel model1;                                          //BackTaskup.java模版
    private JLabel model2;                                          //ZhaoGgServiceModel.java模版
    private JTextField path;                                        //目录路径
    private JTextField catologName;                         //目录名
    private JTextField userName;                                //程序猿
    private JTextField beginNum;                                //开始编号
    private JTextField endNum;                                  //结束编号
    private JTextField area;                                            //地区
    
    private JCheckBox match_num;                            //是否开启编号范围匹配
    private JCheckBox match_area;                           //是否开启地区匹配
    

    
    //无参构造函数  
    public mainUI(){
        super();
        this.getContentPane().setLayout(null);  //获取Container容器,装载JFrame窗体,且设置空布局
        this.setTitle("BatchCreate 4.0");               //设置标题
        this.setSize(610,300);                              //设置宽,高
        
        this.getLeft();                                             //界面左模块
        this.getRight();                                            //界面右模块
    
        this.ageRecord("read");                         //读取填写记录
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);                //关闭窗口即退出程序
    }
    
    //方法1：界面左模块
    private void  getLeft(){
        
        //1.构建组件
        model1 = new JLabel("BackTask.java");                               //标签
        model1.setBounds(5,100,150,25);
        model2 = new JLabel("ZhaoGgServiceModel.java");         //标签
        model2.setBounds(5,180,150,25);
        
        if(db_table ==null){        
            db_table = new JComboBox<String>();                         //组合框
            db_table.setBounds(5,20,150,25);//x,y【坐标】,width,height【宽,高】

                try{
                    UseDB udb =new UseDB("SQLite");
                    udb.connDatabase("batchcreate_file.db");    
                    String [] table=udb.selectSQL("SELECT name FROM SQLITE_MASTER WHERE type='table'ORDER BY name").split("&&");
                    for(int i=0;i<table.length;i++){
                        if("article".equals(table[i]))  continue;       //去除article表【模版表】
                        db_table.addItem(table[i]);
                    }
                    
                    udb.closeAll();
                }catch(Exception e){
                    e.printStackTrace();
                }
        }
        if(db_insertTable == null){                                 
            db_insertTable = new JButton("导入数据");                   //按钮
            db_insertTable.setBounds(175,20,100,25);                        //设置x,y【屏幕坐标】,width,height【组件宽高】
            
            db_insertTable.addActionListener(this);                         //注册按钮点击事件
        }
        if(model_alterB == null){                                           
            model_alterB = new JButton("修改模版1");                        //按钮
            model_alterB.setBounds(175,100,100,25);
            
            model_alterB.addActionListener(this);                       
        }
        if(model_alterZ == null){
            model_alterZ = new JButton("修改模版2");                        //按钮
            model_alterZ.setBounds(175,180,100,25);
            
            model_alterZ.addActionListener(this);                           
        }
        
        JPanel panel_1 = new JPanel(){                                          //画垂直分割线【分隔左右模块】
            public void paint(Graphics g){
                super.paint(g);
                g.setColor(Color.GRAY);
                 g.drawLine(0,0,0,300); //(x,y)点到(x,y)点
            }
        };
        panel_1.setBounds(280, 0, 1, 300);
        
        
        //2.添加组件
        this.add(panel_1);
        this.add(db_table)  ;       this.add(db_insertTable);
        this.add(model1);           this.add(model_alterB);
        this.add(model2);           this.add(model_alterZ);
    }
    
    //方法2:界面右模块
    private void getRight(){
        
        //1.构建组件
        JLabel lb_path = new JLabel("目录路径");                                        //标签
        JLabel lb_catologName = new JLabel("目录名");                          //标签
        JLabel lb_userName = new JLabel("程序猿");                                 //标签
        JLabel lb_beginNum = new JLabel("开始编号    ----");                    //标签
        JLabel lb_endNum = new JLabel("终止编号");                              //标签
        JLabel lb_area = new JLabel("地区");                                              //标签
        
        lb_path.setBounds(290,20,60,25);                        //设置x,y【屏幕坐标】,width,height【组件宽高】                                
        lb_catologName.setBounds(290,50,55,25);
        lb_userName.setBounds(285,100,55,25);
        lb_beginNum.setBounds(285,145,90,25);
        lb_endNum.setBounds(380,145,55,25);
        lb_area.setBounds(285,220,55,25);
        
        if(path == null){
            path  = new JTextField();                                   //单行文本
            path.setBounds(350,20,220,25);
        }
        if(catologName == null){
            catologName = new JTextField();                  //单行文本
            catologName.setBounds(350,50,220,25);
        }
        if(userName == null){
            userName = new JTextField();                            //单行文本
            userName.setBounds(330,100,100,25);
        }
        if(beginNum == null){
            beginNum = new JTextField();                            //单行文本
            beginNum.setBounds(285,175,50,25);
        }
        if(endNum == null){
            endNum = new JTextField();                          //单行文本
            endNum.setBounds(380,175,50,25);
        }
        if(area == null){
            area = new JTextField();                                    //单行文本
            area.setBounds(330,220,100,25);;
        }
        if(match_num == null){
            match_num = new JCheckBox("是否开启编号范围匹配");         //复选按钮
            match_num.setBounds(440,110,200,25);
        }
        if(match_area == null){
            match_area = new JCheckBox("是否开启区域匹配");             //复选按钮
            match_area.setBounds(440,140,150,25);
        }
        if(go == null){
            go = new JButton("开始");                                      //按钮
            go.setBounds(440,190,145,50);
            
            go.addActionListener(this);                                     //注册按钮点击事件
        }
        
        JPanel panel_1 = new JPanel(){                                  //画水平分割线【分隔右模块的上下】
            public void paint(Graphics g){
                super.paint(g);
                g.setColor(Color.GRAY);
                 g.drawLine(0,0,300,0); //(x,y)点到(x,y)点
            }
        };
        panel_1.setBounds(280, 90, 320, 1);
        
        JPanel panel_2 = new JPanel(){                                //画水垂直割线【分隔右模块的下部分的左右】
            public void paint(Graphics g){
                super.paint(g);
                g.setColor(Color.GRAY);
                 g.drawLine(0,0,0,300); //(x,y)点到(x,y)点
            }
        };
        panel_2.setBounds(435, 90, 1, 300);
        
        
        //2.添加组件
        this.add(panel_1);
        this.add(panel_2);
        this.add(lb_path);                      this.add(path);
        this.add(lb_catologName);       this.add(catologName);
        this.add(lb_userName);          this.add(userName);
        this.add(lb_beginNum);          this.add(beginNum);
        this.add(lb_endNum);                this.add(endNum);
        this.add(lb_area);                      this.add(area);
        this.add(match_num);
        this.add(match_area);
        this.add(go);
    }
    

    //方法3.读取上次记录
    public void ageRecord(String state){    //参数:状态
        
            IOFile iof = new IOFile();
        try{
            
            if("read".equals(state)){               //处理读取状态
                
                //A-读取文件内容,转成utf-8格式
                String content=iof.rFileContent("Data/record.suvan", "UTF-8");          
                
                //B-处理数据,得到数组
                String [] result =content.split("\n");  //以换行符作为分隔符,每一行为1个数组元素
                for(int i=0;i<result.length;i++){
                    result[i]=result[i].substring(result[i].indexOf("<[")+2, result[i].lastIndexOf("]>"));  //
                }
                
                //C-将数据设置到相应组件
                path.setText(result[0]);
                catologName.setText(result[1]);
                userName.setText(result[2]);
                beginNum.setText(result[3]);
                endNum.setText(result[4]);
                area.setText(result[5]);
                
            }else if("save".equals(state)){ //处理保存状态
                
                //点击开始即保存数据到record.suvan文件
                StringBuilder sb =new StringBuilder();
                sb.append("path:<["+path.getText()+"]>\n");
                sb.append("catologName:<["+catologName.getText()+"]>\n");
                sb.append("userName:<["+userName.getText()+"]>\n");
                sb.append("beginNum:<["+beginNum.getText()+"]>\n");
                sb.append("endNum:<["+endNum.getText()+"]>\n");
                sb.append("area:<["+area.getText()+"]>\n");
                
                iof.cFile("Data", "record.suvan", "UTF-8",sb.toString());
            }
            
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }
    
    //方法4:事件点击
    public void actionPerformed(ActionEvent e){
        
        if("导入数据".equals(e.getActionCommand())){
            insertUI i = new insertUI();
            i.setVisible(true);
            
        }else if("修改模版1".equals(e.getActionCommand())){
                //A1-BackupTask模版界面
                alterModelUI a1 = new alterModelUI("BackupTask.java");//加载界面,且传入要读取的文件名
                    a1.setTitle("BackTask.java模版");
                    a1.setVisible(true);
                
                //A2-BackupTask语法修改界面
//              alterModelUI a2 =new alterModelUI("BackupTask.txt");
//                  a2.setLocation(a1.getX()+a1.getWidth(), a1.getY()); //基于a1的坐标进行设定
//                  a2.setTitle("语义修改");
//                  a2.setVisible(true);
                
        }else if("修改模版2".equals(e.getActionCommand())){
                //B1-ZhaobGgServiceModel模版界面
                alterModelUI b1 = new alterModelUI("ZhaobGgServiceModel.java");
                    b1.setTitle("ZhaoGgServiceModel.java模版");
                    b1.setVisible(true);
                
                //B2-ZhaobGgServiceModel语法修改界面
//              alterModelUI b2 = new alterModelUI("ZhaobGgServiceModel.txt");
//                  b2.setTitle("语义修改");;
//                  b2.setLocation(b1.getX()+b1.getWidth(), b1.getY()); //基于a1的坐标进行设定
//                  b2.setVisible(true);
            
        }else if("开始".equals(e.getActionCommand())){
            //1.保存数据
            this.ageRecord("save");
            
            //2.复选框判断
            boolean numSign =true;  //开启了编号范围匹配,则必须填写开始和结束编号
            boolean areaSign = true;//开启了地区匹配,则必须填写地区
            if(match_num.isSelected()){
                if(beginNum.getText().length()<1 || endNum.getText().length()<1){
                    numSign = false;
                }
            }
            if(match_area.isSelected()){
                if(area.getText().length()<1){
                    areaSign =false;
                }
            }
            
            //3.执行自动生成操作
            if(path.getText().length()>1 && catologName.getText().length()>1 && userName.getText().length()>0 ){
                    if(numSign && areaSign){
                        GoTask gt= new GoTask();
                        gt.setPath(path.getText());                                                             //C:/Users/Liu-shuwei/Desktop\\
                        gt.setCatalogName(catologName.getText());                           //入库【4.0版,823表(完全版-未筛选)】
                        gt.setNumMatch(match_num.isSelected());                             //是否开启编号范围匹配
                        gt.setAreaMatch(match_area.isSelected());                               //是否开启地区匹配
                        gt.setUseTable(db_table.getSelectedItem().toString());          //使用表名【SQLite的若不存在会自动创建】
                        
                        gt.GO_SQLite(userName.getText(),beginNum.getText()+"-"+endNum.getText(), area.getText());
//                      gt.GO_SQLite("陈宇","02065-02145","华中");
                        JOptionPane.showMessageDialog(null, "生成完毕！", "success",JOptionPane.PLAIN_MESSAGE);  
                    }else{
                        JOptionPane.showMessageDialog(null, "开启了编号范围匹配 or 地区匹配\n则必须填写相应字段！", "falil",JOptionPane.ERROR_MESSAGE);  
                    }
            }else{
                JOptionPane.showMessageDialog(null, "抱歉,路径,文件名,程序猿不能为空！", "falil",JOptionPane.ERROR_MESSAGE);  
            }       
        }   
    }
    
    
    public static void main(String [] args){
        try{
            mainUI m = new mainUI();
            m.setVisible(true);
        }catch(Exception e){
            e.printStackTrace();
        }

    }


**********************************************************************

package UI;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JProgressBar;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


/**最后更新：2016.12.17
 *          进度条页面，可根据需求配置定时器 【在项目中未实现】
 * @author Suvan
 *
 */
public class ProgressBar implements ActionListener,ChangeListener{      //Change事件，让类能够实现接收和处理的Change事件
    
    
        private JFrame frame=null;
        private JProgressBar progressbar;
        public Timer timer;

        //1.无参构造函数，用于初始化界面
        public ProgressBar(){
             
                if(frame == null){
                    frame=new JFrame("进度条");//窗体
                    frame.setBounds(100, 100, 400, 130);    //x,y 坐标,width,height
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   //点击X健，退出程序且关闭线程
                }
                if(progressbar == null){
                    progressbar = new JProgressBar();//创建进度条实例,
                    progressbar.setOrientation(JProgressBar.HORIZONTAL); //设置水平
                    
                    progressbar.setMinimum(0);                  //设置最小值
                    progressbar.setMaximum(100);                //设置最大值
                    progressbar.setValue(0);                            //设置当前进度
                    progressbar.setBackground(Color.pink);//设置进度条颜色
                    progressbar.setStringPainted(true);     //设置stringPainted属性值，该属性确定进度条是否应该呈现字符串
                    progressbar.setBorderPainted(true);     // 设置 borderPainted 属性，如果进度条是否绘制其边框
                    progressbar.setPreferredSize(new Dimension(300,20));//setSize是设定的固定大小，而setPreferredSize仅仅是设置最好的大小,new Dimension(500,20)，设置精准的尺寸
                    
                    progressbar.addChangeListener(this);    //注册Change事件
                }
                    
                    //创建容器
                    Container contentPanel=frame.getContentPane();
                    contentPanel.add(progressbar);
                   
                    //定时器
                    if(timer == null){
                        timer = new Timer(100,this);
                    }
                    
                    //显示窗体
                    frame.setVisible(true);
        }

        
        @Override   //监听按钮变化
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() ==timer){ 
                int value =progressbar.getValue();
                if(value <100){
                    progressbar.setValue(++value);
                }
            }
        }
        
        //事件监听变化，当ProgressBar类发生变化时候，则回调此函数【观察者模式-初级】
        public void stateChanged(ChangeEvent e1) {
            System.out.println("ProgressBar发生变化了--->"+progressbar.getValue());
            if(e1.getSource()==progressbar){
                if(progressbar.getValue()==100){
                    System.out.println("加载完毕，关闭窗体");
                    frame.dispose();
                }
            }
        }

        //显示进度值
//      public synchronized void GO(int pactent){
//           new Thread(new MyThread()).start();  
//          try{
//              Thread.sleep(5000);
//          }catch(Exception e){
//              e.printStackTrace();
//          }
//      }
        
        //关闭窗体,释放所有资源
        public void closeAll(){
            frame.dispose();
            frame=null;
            progressbar=null;
        }

    public static void main(String[] args){ 
    //1.测试进度条
        ProgressBar app=new ProgressBar();
        app.timer.start();
//      for(int i=0;i<100000000;i++){
//          if(i%10000000>=1){
//              app.GO((i%10000000));
//          }       
//      }
        //2.测试定时器
//      Timer timer =new Timer();
//      timer.schedule(new MyTask(),1*000,5*1000);//任务，1s后开始，每次5s
    }
    
    //定时器类
    class MyTask extends TimerTask{
        public  void run(){
            System.out.println("目前进度："+1+"%");
        }
    }


    
    //线程类
//  class MyThread implements Runnable{
//
//      public void run(){
//          progressbar.setValue(pacent);
//          frame.repaint();
//      }
//  }
}


//  }



}

```

---
<br><br>


### Utils包
```
package Utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/** 更新时间： 2016.12.18
 *   
 *  数据库封装类
 *          方法1-读取
 *          方法2-创建
 *          方法3-追加内容
 *              @author suvan
 *  
 *      扩展【cpdetector.jar包可以字段判断当前文件的内容编码】
 */
public class IOFile {
    
        //方法1-1:读取文件内容【字节流】【只适用与读取英文，数字，无法设置编码格式】
        public String rFileContent(String filePath) throws IOException{ //参数:文件路径,编码格式
            
            String content="";
            InputStream inf=new FileInputStream(filePath);
            for(int i=0;i<inf.available();i++){
                content+=(char)inf.read();
            }
            

            return "\'"+content+"\'";   
        }
        
        
        //方法1-2:读取文件内容【字符流】【可以设置编码，读取中文，英文，数字】
          public String rFileContent(String filePath,String encoding) throws IOException{ //参数: 路径,编码格式
                    StringBuilder sb =new StringBuilder();
                    
                    File f=new File(filePath);//定位文件【小弊端，没有文件的话会自动新建】
                    
                    FileInputStream fip=new FileInputStream(f);
                    InputStreamReader isw=new InputStreamReader(fip,encoding);
                    BufferedReader br = new BufferedReader(isw);
                    
                    while(isw.ready()){
                        sb.append((char)isw.read());
                    }
                    
                return sb.toString();   
           }        
        
         //方法2-3： 读取InputStream
         public String readInputStream(InputStream is) throws IOException{
                
             InputStreamReader isr = new InputStreamReader(is);
             BufferedReader br = new BufferedReader(isr);  
             
             StringBuffer sbf = new StringBuffer();     
             String tmp = "";     
                while((tmp = br.readLine())!=null){     
                    sbf.append(tmp);     
                }     
                return sbf.toString();
         }
        
        
        //方法3-1: 创建目录
        public void createCatalog(String path,String catalogName){//参数：路径，目录名
            
            File d=new File(path+"/"+catalogName);
            d.mkdir();
        }
        
        
        //方法3-2: 创建文件,写入内容
        public void cFile(String path,String fileName,String encoding,String content) throws IOException{ //参数：路径,文件名.格式,编码格式,文件内容
            File f=new File(path+"/"+fileName);
            
            FileOutputStream fop= new FileOutputStream(f);
            OutputStreamWriter osw=new OutputStreamWriter(fop,encoding);
            BufferedWriter writer = new BufferedWriter(osw);
            writer.append(content);
            writer.close();
            fop.close();
            System.out.println( "*********************"+fileName+"成功创建！");
        }
        
        
        //方法3-3:追加文件内容【若不存在文件，则会在路径path下，新建filnName文件】
        public void addContentFile(String path,String fileName,String content) throws IOException{ //参数：路径，文件名，追加的内容
            File f=new File(path+"/"+fileName); 

            FileOutputStream fs = new FileOutputStream(f,true);
            OutputStreamWriter osw = new OutputStreamWriter(fs);
            BufferedWriter out = new BufferedWriter(osw);     
                    
            out.write(content);     
            out.close();     
        }
        
}

**********************************************************************

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
import java.util.List;


/** 更新时间： 2016.12.08
 *   
 *  数据库封装类
 *          单例模式,【兼容MySQL(导入mysql-connector-java-5.1.38-bin.jar)和SQLite(导入sqlite-jdbc-3.15.1.jar)】
 *          方法1-基础使用
 *          方法2-查询 【SELECT】
 *          方法3-插入【INSERT】
 *          方法4-执行SQL语句
 *              
 *              @author suvan
 *
 */
public class UseDB {
    
    //静态成员变量，支持单例模式
    private static  volatile UseDB udb = null;
    
    
    private static String databaseType;  //数据库类型【例如:Mysql,Oracle,SQLite之类的】
    
    private String drive;                            //数据库驱动
    private String link;                             //【mysql—JDBC链接+IP地址+端口】

    private String username;                  //用户名
    private String password;                    //密码
    
    private Connection conn=null;
    private Statement st=null;
    private PreparedStatement pst=null;
    private ResultSet rs=null;
    private ResultSetMetaData rsmd=null;//获取列的信息
    
    private String sql; //sql语句
    
        
        //有参构造函数进行赋值
        public UseDB(String dbType){
        
            this.databaseType=dbType.toLowerCase(); //设定当前工具类使用什么类型的数据库
            
            if("mysql".equals(databaseType)){   //转换为小写匹配
                
                this.drive="com.mysql.jdbc.Driver";             
                this.link="jdbc:mysql://127.0.0.1:3306/";     
                this.username="root";                                   
                this.password="liushuwei";                  
                
            }else if("sqlite".equals(databaseType)){
                
                this.drive="org.sqlite.JDBC";                       //数据库驱动
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
//           System.out.println("数据库连接成功......");
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
            if(pst!=null)   pst.close();    //关闭PreparedStatement
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
                                    sb.append("-0-&");
                                }else{
                                    sb.append(rs.getString(i)+"&");
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
        public ArrayList<String> selectAskinformation_Scope(String table,String condition,String scope) throws SQLException{ //参数:表名,条件,范围行数【使用-进行分隔,例如1-5】
//              StringBuilder sb= new StringBuilder();
                ArrayList<String> alist = new  ArrayList<String>();
                
                String [] scope_arrays =scope.split("-");
                int begin=Integer.parseInt(scope_arrays[0]);
                int end=Integer.parseInt(scope_arrays[1]);
                
                sql="SELECT * FROM "+table+" WHERE "+condition+";";
                rs=st.executeQuery(sql);
                rsmd=rs.getMetaData();
                while(rs.next()){
                    for(int i=begin;i<=end;i++){ //遍历范围区间的字段
                        if(rs.getString(i)==null || rs.getString(i).length()<2) continue;
                        alist.add(rsmd.getColumnName(i));//只要列名
                    }       
                }

            return alist;   
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
                    String sql="INSERT OR IGNORE  INTO  "+table+" VALUES("+content+");";//去重插入【应用于SQLite】遇到重复就跳过OR IGNORE
                    int i=st.executeUpdate(sql);
        }
        
        //方法3-2: 往table表record字段,插入一行content数据【少量数据(标题，日期等)】【多字段插入   ,分隔字段     ,号分隔内容】
        public void insert(String table,String record,String content) throws SQLException{//参数: 表名,字段,内容
                    String sql="INSERT INTO "+table+"("+record+") VALUES("+content+");";
                    int i=st.executeUpdate(sql);
                    if(i!=-1){
//                      System.out.println("*****************"+table+"表INSERT成功！****************");
                    }
        }
                
        //方法3-3:往table表record，插入一行content数据 【大量数据(文章，文本内容)】【,分隔字段 &&&&& 分隔内容   ,分隔占位符】
        public void insert(String table,String record,String content,String mark) throws SQLException, UnsupportedEncodingException{//参数值: 表名,字段,内容，占位符(?)
//                  content =new String(content.getBytes(),"UTF-8");
            
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
//                          System.out.println("**********************"+table+"表中不存在"+content_Arrays[0]+"记录,正在进行INSERT。。。。。。。。。。。。。");
                            sql="INSERT INTO "+table+"("+record+") VALUES("+mark+");";
                            
                            pst=conn.prepareStatement(sql);
                            
                            for(int i=1;i<=mark_Arrays.length;i++){//根据占位符个数【?的数量】进行循环: ?号个数            
                                pst.setString(i,mark_Arrays[i-1]);
                            }
                            
                            pst.executeUpdate();
//                          System.out.println("*****************"+table+"表INSERT成功！****************");
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
        
}


**********************************************************************

package Utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

/**更新时间:2016.12.13
 *          操作Excel工作薄
 *              方法1：使用jxl.jar   【创建和读取excel】
 *              方法2：使用poi.jar 【创建和读取excel】
 *      @author Suvan
 */
public class UseExcel {
    
    
    //方法1-2：创建Excel文件，写入数据
    public   void createExcel() throws IOException,WriteException{
        //1.新建文件，设置目录
        File xlsFile = new File("C:\\Users\\Liu-shuwei\\Desktop\\jxl.xls"); 
        
        //2.创建工作簿
        WritableWorkbook workbook = Workbook.createWorkbook(xlsFile);
        
        //3.创建工作表
         WritableSheet sheet = workbook.createSheet("sheet1", 0);
          for (int row = 0; row < 10; row++){   
             for (int col = 0; col < 10; col++) {
                 
                 //第col列【从0开始】，第row行【从0开始】，数据
                 Label lb = new Label(col,row,"数据【"+col+"列"+row+"行】");
                 sheet.addCell(lb);  // 向工作表中添加数据
             }
          }
          
          //4.写入数据
          workbook.write();
          
          //5.关闭流
          workbook.close();
    }
    
    //方法1-2：读取Excel
    public  HashMap [] getExcel(String filePath) throws IOException,BiffException{  //返回数组[HashMap<“rows”，ArrayList>]  
    
        //1.定位文件
        File xlsFile = new File(filePath);
        
        //2.获得工作薄对象
        Workbook workbook = Workbook.getWorkbook(xlsFile);
        
        //3.获得所有工作表
        Sheet [] sheets = workbook.getSheets();
        
            //构建用于保存数据的数组,无序哈希表,有序集合
            HashMap [] sheets_map=new HashMap[sheets.length];                       
            
        
        //4.遍历工作表
        if(sheets != null){
            for(int s =0;s<sheets.length;s++){
                
                //读取所有rows【所有行】     
                HashMap<Integer,ArrayList> rows_map = new HashMap<Integer,ArrayList>();
                for(int row =0 ;row<sheets[s].getRows();row++){
                    Cell    [] cells =sheets[s].getRow(row);                                        //获取整行数据,保存进数组,Sheet.getColumn()是获取整列
                    
                    
                    //读取所有列列数据
                    ArrayList<String> cols_list = new ArrayList<String>();
                    for(int col = 0;col < sheets[s].getColumns();col++){
//                      System.out.printf("%10s",sheets[s].getCell(col,row).getContents());
                        cols_list.add(sheets[s].getCell(col,row).getContents());        //储存每列【每个单元格】数据
                    }
                    rows_map.put(row, cols_list);                                                   //储存每行数据                            
                }
                sheets_map[s]=rows_map;                                                          //储存每个工作表示数据
                
            }
        }
    

        workbook.close();
        return sheets_map;
        
    }
    
    //方法1-3：读取UseExcel表格,返回拥有的工作表名
    public ArrayList<String> getExcelSheetsName(String filePath) throws IOException, BiffException{
        //1.创建ArrayList对象用于储存表名
        ArrayList<String> alist= new ArrayList<String>();
        
    
        //2.定位Excel,获得工作薄对象
        File xlsFile = new File(filePath);
        Workbook workbook = Workbook.getWorkbook(xlsFile);
        
        //3.获取所有工作表名
        for(Sheet sheet:workbook.getSheets()){
            alist.add(sheet.getName());
        }
        
        return alist;
    }
//************************************************************************************************************************
    //方法2-1：poi创建excel
//  public static void createExcel_poi() throws IOException{
//          //1.创建工作簿
//          HSSFWorkbook workbook =new HSSFWorkbook();
//          
//          //2.创建工作表
//          HSSFSheet sheet = workbook.createSheet("suvan");
//          for(int row =0;row<10;row++){
//              HSSFRow rows = sheet.createRow(row);
//              for(int col=0;col<10;col++){
//                  //向工作表添加数据
//                  rows.createCell(col).setCellValue("数据第"+row+"行-"+col+"列");
//              }
//          }
//          
//          //3.创建文件
//          File xlsFile = new File("C:\\Users\\Liu-shuwei\\Desktop\\suvan.xls");
//          FileOutputStream xlsStream = new FileOutputStream(xlsFile);
//          workbook.write(xlsStream);
//          workbook.close();
//  }
//  
    //方法2-2:poi读取excel文件
//  public static void readExcel_poi() throws EncryptedDocumentException, InvalidFormatException, IOException{
//      //1.定位文件
//      File xlsFile = new File("C:\\Users\\Liu-shuwei\\Desktop\\第5周-8.23.xls");
//      //2.获得工作薄【WorkbookFactory位于poi-ooxml-3.14-20160307.jar】
//      org.apache.poi.ss.usermodel.Workbook workbook =WorkbookFactory.create(xlsFile);
//      //3.获得工作表个数
//      int sheetCount = workbook.getNumberOfSheets();
//      ///4.遍历工作表
//      for(int i=0;i<sheetCount;i++){
//           //a.选择工作表
//          Sheet sheet = workbook.getSheetAt(i);
//          
//          //b.获取总行数和列数
//          int rows =sheet.getLastRowNum()+1;          //总行数
//          Row tmp = sheet.getRow(0);
//          if(tmp == null) continue;
//          int cols = tmp.getPhysicalNumberOfCells();  //总列数
//
//          //c.读取数据
//          for(int row =0;row<rows;row++){
//              Row r = sheet.getRow(row);
//              for(int col=0;col<cols;col++){
//                  if(r.getCell(col)!=null){
//                      r.getCell(col).setCellType(Cell.CELL_TYPE_STRING); //设置Cell类型，然后就可以把纯数字作为String类型读出来
//                      System.out.printf("%10s",r.getCell(col).getStringCellValue());
//                  }
//              }
//      
//              System.out.println();//换行
//          }
//      }
//      
//      workbook.close();
//  }
    
}

**********************************************************************

package Utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**更新时间:2016.12.03
 *          操作字符串
 *              方法1：转换
 *              方法2：判断
 *              方法3：正则表达式
 *              方法4：格式化
 *              方法5：获取
 *      @author Suvan
 */
public class UseString {
    
    //方法1-1:将首字母变为大写
        public String UpFirstString(String content){ //参数: 内容
            String firstLetter=content.substring(0,1);
            content=content.replaceFirst(firstLetter,firstLetter.toUpperCase());//替换首字母
            
            return content;
        }
        
        //方法1-2:将首字母变为小写
        public String LowFirstString(String content){ //参数: 内容
            String firstLetter=content.substring(0,1);
            content=content.replaceFirst(firstLetter,firstLetter.toLowerCase());//替换首字母
            
            return content;
        }
        
        //方法1-3:将字符串变为int类型
        public int getInt(String content){
            return Integer.parseInt(content);
        }
        
        //方法1-4:字符串转码
        public String getTranscoding(String content,String encoding) throws IOException{
            byte[] t_content = content.getBytes(encoding);
            String newS = new String(t_content,encoding);
            return newS;
        }
        
        //方法2-1：判断num编号属否在scope区间里面，返回布尔类型参数【true--属于，false--不属于】
        public Boolean getMinMaxScope(String scope,int num){//参数：范围，编号
            Boolean sign = false;//判断标识，默认为true
            
            String [] scope_arrays = scope.split("-");
            int minScope =Integer.parseInt(scope_arrays[0]);
            int maxScope =Integer.parseInt(scope_arrays[1]);
            
                if(minScope <= num && num <= maxScope){
                    sign =true;
                }
            
            return sign;
        }
        
        //方法3-1：正则表达式
        public ArrayList<String> getRegexResult(String content,String pattern){
                
                ArrayList<String> alist = new ArrayList<String>();
            //1.创建Pattern对象
                Pattern p = Pattern.compile(pattern);
            
                //2.创建Matcher对象
                Matcher m = p.matcher(content);
                if(m.find()){  //如果有发现的话
                    System.out.println(m.groupCount());
                    for(int i=0;i<m.groupCount();i++){
                        System.out.println(m.group(i));
                        alist.add(m.group(i));
                    }
                }else{
                    System.out.println("很抱歉,没有找到任何匹配");
                }
                System.out.println("***********************************************");
                return alist;
        }
        
        //方法4-1：格式化输出语句【应放在项目里的使用类】
        private void print(String msg, Object... args) {
            //%d是一个占位符，标识一个字符串型的数据，%10d是数字的左侧留10个空格，对齐用 %s也是一个占位符，标识一个字符串型的数据
            System.out.println(String.format(msg, args));
        }
        
        //方法4-2：得到拼接的SQL语句
        public String getSQL(ArrayList<String> data_list){
            StringBuilder sb =new StringBuilder("'");
            for(int c=0;c<data_list.size();c++){
                sb.append(data_list.get(c)+"','");
            }
            sb.substring(sb.lastIndexOf(","));
            
            return sb.toString();
        }
        
        //方法5-1：
        public long getTime(){
            //获得的是自1970-1-01 00:00:00.000 到当前时刻的时间距离【毫秒】
            return System.currentTimeMillis();
        }
        
}
```

---

<br><br><br><br><br>

5.3版【2017.1.9】
=============================
<br>

5.3更新：
1. 导入完excel数据，实现主窗体实现异步刷新【导入完可以直接生成】
2. JFileChooser默认启动路径为电脑桌面
3. 加入事务处理，SQLite批量插入速度提升10倍+
4. 优化写细节操作，提升操作友好度
5. 自动生成文档(部分生成详细情况可以自己试下)【若出现-的顺序不同，则需要走自主调整excel表的顺序】


<br><br>


代码
-------
<br>
1. 在main.AlterContent加入了生成文档操作
2. main.GoTask删除了MYSQL的流程
3. Utils.UseDB的insert取消自动提交，需要自主根据需要commit事务
4. UI.insertUI里加入成员变量，可控制mainUI


---
<br><br><br><br><br>


5.4版【2017.1.9下午】
========================
<br>

5.3更新：
1. 添加双模式生成【分配模式 and 集成模式】,文件目录以(入库[双模式],接口(生成包名)，文档分开布局)
2. 文档依旧保留开头编号【用于区分，以后要删除再自己删除】
3. 该版本保留5.3特性，建议把自己相应任务编号内的添加进去，再用此表导入生成.

<br><br>


代码
-------
<br>
1. 在main.GoTask加入组双模式
2. UI.mainUI里面加入文件ButtonGroup(按钮组)和JRadioButton(单选按钮),并添加相应事件逻辑


---
<br><br><br><br><br>