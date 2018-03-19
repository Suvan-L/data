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
 * 			操作Excel工作薄 
 * 				方法1：使用jxl.jar	【创建和读取excel】
 * 				方法2：使用poi.jar 【创建和读取excel】
 * 		@author Suvan
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
	public  HashMap [] getExcel(String filePath) throws IOException,BiffException{	//返回数组[HashMap<“rows”，ArrayList>]  
	
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
					Cell	[] cells =sheets[s].getRow(row);										//获取整行数据,保存进数组,Sheet.getColumn()是获取整列
					
					
					//读取所有列列数据
					ArrayList<String> cols_list = new ArrayList<String>();
					for(int col = 0;col < sheets[s].getColumns();col++){
//						System.out.printf("%10s",sheets[s].getCell(col,row).getContents());
						cols_list.add(sheets[s].getCell(col,row).getContents());		//储存每列【每个单元格】数据
					}
					rows_map.put(row, cols_list);													//储存每行数据							
				}
				sheets_map[s]=rows_map;					 										 //储存每个工作表示数据
				
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
//	public static void createExcel_poi() throws IOException{
//			//1.创建工作簿
//			HSSFWorkbook workbook =new HSSFWorkbook();
//			
//			//2.创建工作表
//			HSSFSheet sheet = workbook.createSheet("suvan");
//			for(int row =0;row<10;row++){
//				HSSFRow rows = sheet.createRow(row);
//				for(int col=0;col<10;col++){
//					//向工作表添加数据
//					rows.createCell(col).setCellValue("数据第"+row+"行-"+col+"列");
//				}
//			}
//			
//			//3.创建文件
//			File xlsFile = new File("C:\\Users\\Liu-shuwei\\Desktop\\suvan.xls");
//			FileOutputStream xlsStream = new FileOutputStream(xlsFile);
//			workbook.write(xlsStream);
//			workbook.close();
//	}
//	
	//方法2-2:poi读取excel文件
//	public static void readExcel_poi() throws EncryptedDocumentException, InvalidFormatException, IOException{
//		//1.定位文件
//		File xlsFile = new File("C:\\Users\\Liu-shuwei\\Desktop\\第5周-8.23.xls");
//		//2.获得工作薄【WorkbookFactory位于poi-ooxml-3.14-20160307.jar】
//		org.apache.poi.ss.usermodel.Workbook workbook =WorkbookFactory.create(xlsFile);
//		//3.获得工作表个数
//		int sheetCount = workbook.getNumberOfSheets();
//		///4.遍历工作表
//		for(int i=0;i<sheetCount;i++){
//			 //a.选择工作表
//			Sheet sheet = workbook.getSheetAt(i);
//			
//			//b.获取总行数和列数
//			int rows =sheet.getLastRowNum()+1;			//总行数
//			Row tmp = sheet.getRow(0);
//			if(tmp == null) continue;
//			int cols = tmp.getPhysicalNumberOfCells();	//总列数
//
//			//c.读取数据
//			for(int row =0;row<rows;row++){
//				Row r = sheet.getRow(row);
//				for(int col=0;col<cols;col++){
//					if(r.getCell(col)!=null){
//						r.getCell(col).setCellType(Cell.CELL_TYPE_STRING); //设置Cell类型，然后就可以把纯数字作为String类型读出来
//						System.out.printf("%10s",r.getCell(col).getStringCellValue());
//					}
//				}
//		
//				System.out.println();//换行
//			}
//		}
//		
//		workbook.close();
//	}
	
}
