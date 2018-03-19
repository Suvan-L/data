package main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import Utils.IOFile;
import Utils.UseDB;
import Utils.UseString;
import bean.NumBean;

/**更新时间： 2017.1.8 
 * 
 * 	获取模版内容，根据编号各个属性值，修改BackupTask.java和入库文件，创建文件
 * 			@author Suvan
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
		//方法2：生成文档【传入参数：路径，修改内容，当前编号含有URL的键值对,当前编号的bean】
		public void aText(String path,String fileName,String sign,HashMap<String,String> hm,NumBean numbean) throws IOException{	

			//A-构建模版
			String num=numbean.getNum();
			String weburl=numbean.getWeburl();
																													//写入txt格式，\r\n是换行符【windows下\r\n是回车的意思】
			String first=fileName.replaceFirst(".txt", "")+"\r\n\r\n"+	
					 "原网站："+weburl+"\r\n\r\n";
			
			String model="入库编码（"+num+"）"+"\r\n\r\n"+
									 "数据来源"+"\r\n";
									 
			StringBuilder sb =new StringBuilder();
			for(Map.Entry<String, String> entry:hm.entrySet()){	//遍历键值对
				sb.append(gtd.getTypeChinaname(entry.getKey())+"："+entry.getValue()+"\r\n");
			}
			sb.append("\r\n\r\n");
			for(String key:hm.keySet()){												//遍历键
				sb.append(gtd.getTypeChinaname(key)+"\r\n"+
									"列表（	为页数，从1开始,顺序递增）"+"\r\n\r\n"+
									"内容（	为id）"+"\r\n\r\n\r\n");
			}
									 
		    //2.覆盖或者修改文件
//			String fileName2=fileName.substring(fileName.indexOf("-")+1);	//5.3修改，文件名不要编号，只要网站名称
			
						if("cover".equals(sign)){//覆盖 【与上一个编号完全不同(号码不同,且不带-)】
							 iof.cFile(path,fileName, "UTF-8", first+model+sb.toString());
						}
						else if("alter".equals(sign)){ //修改 【带-的编号,在上一个文件基础上进行叠加修改】
					
							iof.addContentFile(path, fileName, "\r\n"+"**********************************************************"+"\r\n\r\n"+
															model+sb.toString());
						}		 
		}
		
		//方法2:修改入库文件
		public void aRK(String path,String fileName,String content,String type,String username,NumBean numbean) throws IOException{
		

				//一.根据类型生成相应名称
				HashMap<String,String> hm = gtd.getTypeAllName(type);

				
				//二.根据需求修改content
						//A.判断信息类型
						String infType =numbean.getInftype();
						if(infType !=null  &&  !"无".equals(infType)){										//zbgg.setInfoType("服务");// 信息类型
							content=content.replaceFirst("//是否有信息类型;","zbgg.setInfoType(\""+infType+"\");//信息类型");
						}else{
							content=content.replaceFirst("//是否有信息类型;","//zbgg.setInfoType(\"服务\");//信息类型");
						}
					
						//B.判断行业分类
						String workType=numbean.getWorktype();
						if(!"无".equals(workType)) {								 //zbgg.setIndustry("建筑建材"); // 行业分类
							content=content.replaceFirst("//是否有行业分类;","zbgg.setIndustry(\""+workType+"\"); //行业分类");
						}else{
							content=content.replaceFirst("//是否有行业分类;","//zbgg.setIndustry(\"建筑建材\"); // 行业分类");
						}
						
						//C.判断是否需要进行网站类型匹配【单一 or 混合】  //是否匹配;
						String lot=numbean.getLot();
						if(lot.contains("是")){
							content=content.replaceFirst("//是否导入匹配混合工具包;","\n"+"import com.bonait.dataextract.util.Utils;");
							int t=gtd.getT(type);
							String typeAutoMatch="int t = Utils.judge(lists.get(i).get(j).getTitle());"+"\n"+
																	  "\t\t\t\t\t\t\t\t\t"+"if(t != "+t+"){"+"\n"+
																	  "\t\t\t\t\t\t\t\t\t\t"+"continue;"+"\n"+
																	   "\t\t\t\t\t\t\t\t\t}"+"\n";
							content=content.replaceFirst("//是否开启混合类型匹配;",typeAutoMatch);
						}else{
							content=content.replaceFirst("//是否导入匹配混合工具包;","");
							content=content.replaceFirst("//是否开启混合类型匹配;","");//不是混和类型直接不匹配
						}

					
				//三.修改模版
				content=content.replace("zhaoBiaoGongGao",hm.get("typeSpell"))
																			 .replace("ZhaoBiaoGongGao",us.UpFirstString(hm.get("typeSpell")))
																			 .replace("ZhaobGgService",us.UpFirstString(hm.get("typeService")))
																			 .replace("zhaobGgService",hm.get("typeService"))
																			 .replace("zbgg",type);
				content=content.replaceFirst("//@程序猿;",username)
											 .replaceFirst("//编号;",numbean.getNum());
				if("无".equals(numbean.getCounty())){ //区县是否没有
					content=content.replaceFirst("//@内容;",numbean.getArea()+"_"+numbean.getProvince()+"_"+numbean.getCity()+"_"+hm.get("typeChinese")+"_"+numbean.getNum());				
				}else{
					content=content.replaceFirst("//@内容;",numbean.getArea()+"_"+numbean.getProvince()+"_"+numbean.getCity()+"_"+numbean.getCounty()+"_"+hm.get("typeChinese")+"_"+numbean.getNum());
				}
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
				}else if("无".equals(numbean.getCity())){
					content=content.replaceFirst("//City;","");
				}else{
					content=content.replaceFirst("//City;",numbean.getCity().replaceFirst("省","").replaceFirst("市","").replaceFirst("区","").replaceFirst("县",""));
				}
				
				if(numbean.getCounty().length()==2){
					content=content.replaceFirst("//County;",numbean.getCounty());
				}else if("无".equals(numbean.getCounty())){
					content=content.replaceFirst("//County;","");
				}else{
					content=content.replaceFirst("//County;",numbean.getCounty().replaceFirst("省","").replaceFirst("市","").replaceFirst("区","").replaceFirst("县",""));
				}
						
						
				
				//四.创建编号相应类型的入库文件
				iof.cFile(path, fileName, "UTF-8", content);
		}
		
}
