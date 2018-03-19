package main;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Pattern;

import Utils.IOFile;
import Utils.UseDB;
import Utils.UseExcel;
import bean.NumBean;
import jxl.read.biff.BiffException;

/**更新时间：2017.1.8
 * 
 * 	获取数据，处理数据，归纳信息
 * 			@author Suvan
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
			String fileContent=iof.rFileContent(filePath,"UTF-8");	 //注意：路径的话用/或者\\都可以,数据库的默认编码是GBK
			String modeName=filePath.substring(filePath.indexOf("/")+1);			//模版名字
		

			//2.判断是否存在相同模版
			String modeInf = udb.select("article", "model", "model='"+modeName+"'");
			udb.insertReplace("article","model,detail","'"+modeName+"','"+fileContent.replace("'", "''")+"'");  //每次都替换
//			if(modeInf.length() <1){    //判断是否已存在模版
//				udb.insertReplace("article","model,detail","'"+modeName+"','"+fileContent.replace("'", "''")+"'"); 
//			}else{
//				System.out.println("\n********************************已存在"+modeInf+"模版，不进行重复插入****************************");
//			}
		
			return fileContent;
		}
	
	//方法2：获取table表中，num编号的所有信息,储存进NumBean，返回NumBean
	public  NumBean getNumInformation(String table,String num) throws SQLException{//参数：表名,编号

			//查询table表，num字段所有数据，用&分隔
			String numInf = udb.select(table, "num", "num='"+num+"'", '*');
			String [] numInf_arrays=numInf.split("_&_");
			
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
	public HashMap<String,String> getNum_HaveURLRecord(String table,String num) throws SQLException{ //参数：表名，编号

		//查询数据库table表，num字段的11-18行，得到拥有记录的列名的ArrayList
		HashMap<String,String> hm=udb.selectAskinformation_Scope(table,"num='"+num+"'", "11-18");//【11-18是招标预告到控制价】
	
		return hm;
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
	
	//方法6-2：根据列名得到对应中文
	public String getTypeChinaname(String columnName){ //参数：列名
		String type="";

		if(columnName.equals("zbyg")){
			type="招标预告";
		}else if(columnName.equals("zbgg")){
			type="招标公告";
		}else if(columnName.equals("zsjg")){
			type="资审结果";
		}else if(columnName.equals("ggbg")){
			type="公告变更";
		}else if(columnName.equals("zbwj")){
			type="招标文件";
		}else if(columnName.equals("zbdy")){
			type="招标答疑";
		}else if(columnName.equals("zbxx")){
			type="中标信息";
		}else if(columnName.equals("kzj")){
			type="控制价";
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
	
	//方法8：传入地级【国家or区县】,获取入库文件名开头DS,GJ,QX
	public String getFirstLevel(String webtype){
		if(webtype.contains("地市")) return "DS";
		if(webtype.contains("区县")) return "QX";
		if(webtype.contains("国家")) return "GJ";
		
		return "DS";//默认是地市级
	}
	
	//方法9：处理混合类型网站，根据类型，判断用哪个编号Utils的t类型
	public int getT(String type){
//		int i1 = 1; //招标公告
//		int i2 = 2; //中标信息：成交，中标，评标，结果，公示
//		int i3 = 3; //公告变更：变更，补充，修正
//		int i4 = 4; //招标答疑：答疑
//		int i5 = 5; //不需要，跳过
		
		if("zbgg".equals(type)) return 1;
		else if("zbxx".equals(type)) return 2;
		else if("ggbg".equals(type)) return 3;
		else if("zbdy".equals(type)) return 4;
		
		return 5;
	}
	
	//方法10：判断excel表格数据源编码【编号】字符串，第1个数字所在位置
	public int getFirstFigureLoaction(String num){
		
		Pattern pattern= Pattern.compile("[0-9]");
		for(int i=0;i<num.length();i++){
				char c =num.charAt(i);												//当前位置所对应的字符
				if(pattern.matcher(String.valueOf(c)).matches()){	//判断是否是数字
					return i;																	 //是数字的话直接返回位置
				}
		}
		
		return 0;
	}
	
	
//************************************************************************************************************************
	
	
	//方法SQLite-1：创建表结构
	public void s_CreateTable(String useTable) throws SQLException{
		
		 //创建模版表
		String sql1= "CREATE TABLE if not exists article("  +            
								"id  INCREMENT primary key," +
								"model TEXT UNIQUE NOT NULL,"				+
								"detail TEXT NOT NULL"		+
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
							 "	)";
		
		  udb.createSQL(sql1); 
		  udb.createSQL(sql2); 
	}
	
	
	//方法SQLite-2.读取Excel表的数据，插入SQLite
	public void s_InsertExcel(String excelFilePath) throws IOException,BiffException,SQLException{//参数：Excel文件名,导入哪些工作表编号,导入哪个表
		
	
		
			UseExcel ue =new UseExcel();										//工具类
		
			//1.数据获取excel表的数据
			HashMap [] sheets_map=ue.getExcel(excelFilePath);		
		
			//根据excel文件名建表
			String tableName=excelFilePath.substring(excelFilePath.lastIndexOf("\\")+1,excelFilePath.lastIndexOf("."));	
			this.s_CreateTable(tableName);									   							//创建表【article-保存模版,(useTable)-Excel工作薄数据】
			
			//2.正则判断
			Pattern p = Pattern.compile("^[0-9 | a-z | A-Z](.*)[0-9]$");//正则判断n行的第1列的数据是否是编号【开头是否为数字,结尾也是数字】，不是则跳过

			//3.执行导入操作
			if(sheets_map.length>0){
				System.out.println("将Excel数据插入SQLite数据库的"+tableName+"表--------------------------->");
				//A-遍历工作表
				for(int n=0;n<sheets_map.length;n++){
					if(n >3) break; //只遍历3个工作表  
					HashMap<Integer,ArrayList> rows_map = sheets_map[n];		//获取行数据

					//B-遍历行
					for(int r=0;r<rows_map.size();r++){  
	
//						if(r >800) break;  //只读取800行
						if(r> 2000) break; //最多只读取2000行
				
						System.out.print(r+"行......");	
						ArrayList<String> cols_list = (ArrayList)rows_map.get((Integer)r);;								//获取每列数据
						
						if(cols_list.get(0).length()<3) break;  //如果第一列的num没有数据，直接退出循环
						if(cols_list.size() <1  || !(p.matcher(cols_list.get(0))).find()) 	continue;	//整行不存在数据 or 匹配第1列数据,不是数字开头【编号格式01039】,跳过
						if(r %50 ==0)	System.out.println();  														//若50条记录换行一次

						//C-遍历列
						StringBuilder sb =new StringBuilder("'");
						for(int c=0;c<21;c++){				 //【只需要21列数据】
							//拼接SQL
							if(c==20){
								sb.append(cols_list.get(c)+"'");
								continue;
							}
								
							//判断excel表格11-18行-招标公告到中标信息是否出现特殊字样
							if(10<=c & c<=17){
								if(cols_list.get(c).contains("暂无")){	//出现暂无法录入，原网问题
									sb.append("无','");
									continue;
								}
							}
							
							if(cols_list.get(c).length()<1)
								sb.append("无','");		//若excel 表格单元格为" "【空格】,就设置为无
							else
								sb.append(cols_list.get(c)+"','");
							
//							udb.insert(useTable,																				//插入数据
//							"'"+cols_list.get(0)+"','"+cols_list.get(1)+"','"+cols_list.get(2)+"','"+cols_list.get(3)+"','"+cols_list.get(4)+"','"
//								+cols_list.get(5)+"','"+cols_list.get(6)+"','"+cols_list.get(7)+"','"+cols_list.get(8)+"','"+cols_list.get(9)+"','"
//								+cols_list.get(10)+"','"+cols_list.get(11)+"','"+cols_list.get(12)+"','"+cols_list.get(13)+"','"+cols_list.get(14)+"','"
//								+cols_list.get(15)+"','"+cols_list.get(16)+"','"+cols_list.get(17)+"','"+cols_list.get(18)+"','"+cols_list.get(19)+"','"
//								+cols_list.get(20)+"'");
						}

						udb.insert(tableName,sb.toString());														//插入数据
					}
					 udb.udbCommit();
				}
			}
		
	}
	
	//方法SQLite-3.插入Excel数据时候出现异常，进行删除表的操作
	public void s_dropTable(String excelFilePath){
		String tableName=excelFilePath.substring(excelFilePath.lastIndexOf("\\")+1,excelFilePath.lastIndexOf("."));	
		String sql="DROP TABLE "+excelFilePath;
		try{
			udb.deleteSQL(sql);
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
}




