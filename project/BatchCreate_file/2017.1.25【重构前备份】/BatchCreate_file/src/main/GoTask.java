package main;

import java.util.ArrayList;
import java.util.HashMap;

import Utils.IOFile;
import Utils.UseDB;
import Utils.UseString;
import bean.NumBean;


/**更新时间：2017.1.8
 * 		
 * 任务流程
 *			 @author Suvan
 *	
 */
public class GoTask {
	
	

//	private String numScope="00000-05000";																//编号范围区间
//	private String username="某男子";				   												    			//程序猿名称
	private String path="C:/Users/Liu-shuwei/Desktop\\";											 //路径
	private String catalogName="入库【3.3版,823表(区分程序猿and地区)】";			    //文件名
	
	private Boolean areaMatch=false;																	//是否开启区域匹配【默认不开启】
	private Boolean numMatch=false;																	//是否开启默认匹配【默认不开启】
	private String useExcel;																					//使用项目根目录下哪个Excel
	private String useTable;																					//使用哪个表的数据【SQLite的话，不存在表则自动创建】
	
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
	
	
//***********************************************************************************************************************************
	
	//流程1：使用SQLite
	//任务1： 分隔模式
		public void GO_SQLite(String username,String numScope,String numArea) {	//参数:程序猿，编号范围，地区
			System.out.println(username+"-------------------------------------------------->Go!");
		
		//执行任务流程
		try{
			
			//一.获取对象,构建实例
			UseDB udb = new UseDB("SQLite");
			IOFile iof=new IOFile();
			UseString us = new UseString();
//			UseExcel ue =new UseExcel();
			
//			GetFirstLetter gfl = new GetFirstLetter();

			GetTableData  gtd = new GetTableData(iof,udb);
			AlterContent ac = new AlterContent(iof,udb,us,gtd);
			
			
			udb.connDatabase("batchcreate_file.db");						//连接数据库【若不存在则创建】
//			gtd.s_CreateTable(useTable);									   		//创建表【article-保存模版,(useTable)-Excel工作薄数据】
//			gtd.s_InsertExcel(useExcel,useTable);								 //插入Excel数据,【传入excel文件名和使用表名】
			
			//二.创建大目录
			iof.createCatalog(path, catalogName);	//大目录
			iof.createCatalog(path+"/"+catalogName, username);
			iof.createCatalog(path+"/"+catalogName+"/"+ username, "入库");	//生成3种类型文件
			iof.createCatalog(path+"/"+catalogName+"/"+ username, "接口");
			iof.createCatalog(path+"/"+catalogName+"/"+ username, "文档");
			
			//往数据库插入模版数据
			String model1_BT =gtd.getModel("model_file/BackupTask.java");
			String model2_RK =gtd.getModel("model_file/ZhaobGgServiceModel.java");


			//三.获取数据表数据，并生成目录and文件
				//三-1获取所有编号
				ArrayList<String> numList =gtd.getAllNum(useTable) ; 
				
				
				//三-2.遍历所有编号
				for(int i=0;i<numList.size();i++){
				
					String num=numList.get(i);																					//当前编号
					String numInt ;																										//编号-之前的数字，用于比较
					if(num.contains("-"))
						numInt=num.substring(gtd.getFirstFigureLoaction(num),num.lastIndexOf("-"));//从首数字位置开始截取
					else
						numInt=num.substring(gtd.getFirstFigureLoaction(num),num.length());
//					String num_=numList.get(i) .replace("-","_");														//编号下划线形态
					
					
					//P1:判断是否开启编号范围匹配 and 比较当前编号是否在范围区间
					if( numMatch && !gtd.getMinMaxScope(numScope, us.getInt(numInt))){						//获取数字进行比较
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
			
						//b.生成入库文件名，储存进ArrayList
						 HashMap<String,String> ifURLType_map =gtd.getNum_HaveURLRecord(useTable, num);				//获取当前编号所有"含有URL"的字段的类型【例:招标公告 zbgg】
						 ArrayList<String > rukuName_list=new ArrayList<String>(); 																//用于储存当前编号所需要的所有入库文件名
						 StringBuilder rukuName=new StringBuilder("");
						 for(String type:ifURLType_map.keySet()){		//遍历键
//							 rukuName.append(gfl.getFirstLetter(numBean.getArea()).toUpperCase()+"_"+num_+"_"+gtd.getTypename(type)); 	//【5.2以前】-入库文件完整名【地区首字母(大写)_编号_类型服务名】
//							 rukuName.append("SJ_"+num_+"_"+gtd.getTypename(type)); 																							//【5.2版】入库文件完整名【地区首字母(大写)_编号_类型服务名】
							 rukuName.append(gtd.getFirstLevel(numBean.getWebtype())+"_"+num.replaceFirst("-","_")+"_"+gtd.getTypename(type)); 					//【5.3】入库文件完整名【网站类型级别判读_编号_类型服务名】
							 rukuName_list.add(rukuName.toString());
							 rukuName=rukuName.delete(0, rukuName.length());																				//清空
						 }
						 
						
						//d.生成入库子目录
						StringBuilder changePath =new StringBuilder("");											//变化路径
						String catalog_1="入库-"+numInt; 																											//目录名
						changePath.insert(0,path+"/"+catalogName+"/"+username+"/入库");	  								   						//子路径1				  
						iof.createCatalog(changePath.toString(),catalog_1);				//创建入库子目录【入库-编号】
						
						
						//d2-生成BackupTask.java
						 changePath.append("/"+catalog_1);								 //子路径1-2
						 String sign = "cover";  														 //判断当前编号是需要修改还是覆盖【默认是覆盖，带-编号是alter,不带-是cover】
							if(i > 0){																			 //大于0的时候进行判断	【顶部位置没有上一个编号】
								String ageNum=numList.get(i-1);
								String ageNumInt;
								if(ageNum.contains("-"))
									ageNumInt=	ageNum.substring(gtd.getFirstFigureLoaction(ageNum),ageNum.lastIndexOf("-"));//从首数字位置开始截取
								else
									ageNumInt=ageNum.substring(gtd.getFirstFigureLoaction(ageNum),ageNum.length());
								if(ageNumInt.equals(numInt)){ 			
									sign="alter";															 //如果和上个编号相等，则修改
								}
							}
	
						 if("cover".equals(sign)){ 					
							 		ac.aBackupTask(changePath.toString(),"BackupTask.java", model1_BT,sign,rukuName_list);
						 }else if("alter".equals(sign)){		
							 String ageBackupTask=iof.rFileContent(changePath+"/BackupTask.java","UTF-8"); 					//读取已经已经存在的BackupTask.java
							 ac.aBackupTask(changePath.toString(),"BackupTask.java",ageBackupTask ,sign,rukuName_list);
						 }
						
						 //在接口目录里生成包名,例如DS0085
						 if("cover".equals(sign)){ 			
								iof.createCatalog(path+"/"+catalogName+"/"+ username+"/接口", gtd.getFirstLevel(numBean.getWebtype())+numInt);
						 }
						 
						 //d-3生成编号文档
						 String numFront ;																										//编号-之前的数字，用于比较
							if(num.contains("-"))	numFront=num.substring(0,num.lastIndexOf("-"));//从首数字位置开始截取
							else			numFront=num.substring(0,num.length());
							ac.aText(path+"/"+catalogName+"/"+ username+"/文档",numFront+"-"+numBean.getWebname().trim()+".txt", sign, ifURLType_map, numBean);
						 
					
						 
						 
						//e.生成入库文件
						iof.createCatalog(changePath.toString(),"imp");			 	//生成imp目录
						changePath.append("/imp");										 		//子路径1-2-3
						int point=0;
						for(String mapType:ifURLType_map.keySet()){					//根据拥有URL的类型，循环生成入库文件
							ac.aRK(changePath.toString(), rukuName_list.get(point)+".java", model2_RK,mapType,username,numBean);
							point++;
						}
			}
			
			//四.关闭资源
			udb.closeAll();
			
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		
		//任务2：集成模式
		public void GO_SQLite_GATHER(String username,String numScope,String numArea) {	//参数:程序猿，编号范围，地区
			System.out.println(username+"-------------------------------------------------->Go!");
		
		//执行任务流程
		try{
			
			//一.获取对象,构建实例
			UseDB udb = new UseDB("SQLite");
			IOFile iof=new IOFile();
			UseString us = new UseString();
			GetTableData  gtd = new GetTableData(iof,udb);
			AlterContent ac = new AlterContent(iof,udb,us,gtd);
			
			
			udb.connDatabase("batchcreate_file.db");						//连接数据库【若不存在则创建】

			//二.创建大目录 and 插入模版数据
				iof.createCatalog(path, catalogName);								//1层，大目录【最初】
				iof.createCatalog(path+"/"+catalogName, username);	//1-1层，相应程序猿路径
				String basePath=path+"/"+catalogName+"/"+ username;	
				iof.createCatalog(basePath, "入库");							//1-1-3层生成3种类型文件
				iof.createCatalog(basePath+"/入库", "所有编号");		//1-1-3-1层,存放所有编号文件
				iof.createCatalog(basePath, "接口");
				iof.createCatalog(basePath, "文档");
			
				//往数据库插入模版数据【存在模版则不重复插入】
				String model1_BT =gtd.getModel("model_file/BackupTask.java");
				String model2_RK =gtd.getModel("model_file/ZhaobGgServiceModel.java");


			//三.获取数据表数据，并生成目录and文件
				//c-1获取所有编号
				ArrayList<String> numList =gtd.getAllNum(useTable) ; 
								
				//c-2.遍历所有编号
				for(int i=0;i<numList.size();i++){
				
					String num=numList.get(i);																					//当前编号
					String numInt ;																										//数字开头,编号-之前的数字【int类型，用于既表】
					if(num.contains("-"))
						numInt=num.substring(gtd.getFirstFigureLoaction(num),num.lastIndexOf("-"));//从首数字位置开始截取
					else
						numInt=num.substring(gtd.getFirstFigureLoaction(num),num.length());

					
					
					//c2-P1:判断是否开启编号范围匹配 and 比较当前编号是否在范围区间
					if( numMatch && !gtd.getMinMaxScope(numScope, us.getInt(numInt))){						//获取数字进行比较
						continue;
					}
					//c2-p2:判断是否开启地区匹配 and 进行地区匹配
						if(areaMatch){
							String area =udb.select(useTable,"area","num='"+num+"'");
							if(!area.equals(numArea)){
								continue;
							}
						}
					
						
						//c2-3.获取当前编号的所有数据，并储存到JavaBean
						NumBean numBean =gtd.getNumInformation(useTable, num);
			
						//c2-4.生成入库文件名，储存进ArrayList
						 HashMap<String,String> ifURLType_map =gtd.getNum_HaveURLRecord(useTable, num);				//获取当前编号所有"含有URL"的字段的类型【例:招标公告 zbgg】
						 ArrayList<String > rukuName_list=new ArrayList<String>(); 																//用于储存当前编号所需要的所有入库文件名
						 StringBuilder rukuName=new StringBuilder("");
						 for(String type:ifURLType_map.keySet()){																									//遍历键
							 rukuName.append(gtd.getFirstLevel(numBean.getWebtype())+"_"+num.replaceFirst("-","_")+"_"+gtd.getTypename(type)); 					//【5.3】入库文件完整名【网站类型级别判读_编号_类型服务名】
							 rukuName_list.add(rukuName.toString());
							 rukuName=rukuName.delete(0, rukuName.length());																				//清空
						 }
						 
							//d.生成入库子目录
//							String webNamePath=numBean.getWebname();
//							File f =new File(basePath+"/入库");
//							File [] arrayF =f.listFiles();
//							boolean ifNoHave=true;		//默认不存在
//							for(int z=0;z<arrayF.length;z++){
//								if(arrayF[z].getName().equals(numBean.getWebname()))	ifNoHave=false;
//							}
//							if(ifNoHave) iof.createCatalog(basePath+"/入库",numBean.getWebname());
						
						
						//c2-5生成BackupTask.java
						 if(i==0)
							 ac.aBackupTask(basePath+"/入库","BackupTask.java", model1_BT,"cover",rukuName_list);
						 else{
							 String ageBackupTask=iof.rFileContent(basePath+"/入库/BackupTask.java","UTF-8"); 					//读取已经已经存在的BackupTask.java
							 ac.aBackupTask(basePath+"/入库","BackupTask.java",ageBackupTask ,"alter",rukuName_list);
						 }
						 
						 
						 String sign = "cover";  														 //判断当前编号是需要修改还是覆盖【默认是覆盖，带-编号是alter,不带-是cover】
							if(i > 0){																			 //大于0的时候进行判断	【顶部位置没有上一个编号】
								String ageNum=numList.get(i-1);
								String ageNumInt;
								if(ageNum.contains("-"))
									ageNumInt=	ageNum.substring(gtd.getFirstFigureLoaction(ageNum),ageNum.lastIndexOf("-"));//从首数字位置开始截取
								else
									ageNumInt=ageNum.substring(gtd.getFirstFigureLoaction(ageNum),ageNum.length());
								if(ageNumInt.equals(numInt)){ 			
									sign="alter";															 //如果和上个编号相等，则修改
								}
							}
								
						 //c2-6在接口目录里生成包名,例如DS0085
						 if("cover".equals(sign)){ 			
								iof.createCatalog(path+"/"+catalogName+"/"+ username+"/接口", gtd.getFirstLevel(numBean.getWebtype())+numInt);
						 }
						 
						 //c2-7生成编号文档
						 String numFront ;																										//编号-之前的数字，用于比较
							if(num.contains("-"))	numFront=num.substring(0,num.lastIndexOf("-"));//从首数字位置开始截取
							else			numFront=num.substring(0,num.length());
							ac.aText(path+"/"+catalogName+"/"+ username+"/文档",numFront+"-"+numBean.getWebname().trim()+".txt", sign, ifURLType_map, numBean);
						 
					
						//e.生成入库文件
						int point=0;
						for(String mapType:ifURLType_map.keySet()){					//根据拥有URL的类型，循环生成入库文件
							ac.aRK(basePath+"/入库/所有编号", rukuName_list.get(point)+".java", model2_RK,mapType,username,numBean);
							point++;
						}
			}
			
			//四.关闭资源
			udb.closeAll();
			
			}catch(Exception e){
				e.printStackTrace();
			}
		}
}



