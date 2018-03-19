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

/** 更新时间： 2016.12.19
 *   
 *  数据库封装类
 *  		方法1-读取
 *  		方法2-创建
 *  		方法3-追加内容
 * 				@author suvan
 *	
 *		扩展【cpdetector.jar包可以字段判断当前文件的内容编码】
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
					String b=null;
					while((b = br.readLine()) != null){
						sb.append(b+"\n");
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
            OutputStreamWriter osw = new OutputStreamWriter(fs,"utf-8");
            BufferedWriter out = new BufferedWriter(osw);     
                    
            out.write(content);     
            out.close();     
        }
        
}
