package main;

public class test {
	

	//测试主方法
	/*public static void main(String [] args){
		long begin=System.currentTimeMillis();
		
		//创建任务流程对象
		GoTask gt= new GoTask();
			gt.setPath("C:/Users/Liu-shuwei/Desktop\\");
			gt.setCatalogName("入库【4.0版,823表(完全版-未筛选)】");
			gt.setNumMatch(false);		//是否开启编号范围匹配
			gt.setAreaMatch(false);		//是否开启地区匹配
		
		//方式1：使用本地MySQL******************************************************************************************
		//执行流程(参数:程序猿,编号范围,地区)
		gt.setUseTable("webapi3");	
		gt.GO("陈宇","02065-02145","华中");
		gt.GO("刘聪","02396-02461","华北");
		gt.GO("房文迪","01311-02769","华东");
//		gt.GO("林佳法","02311-02371",);
		gt.GO("严加远","02770-02781","华东");
		gt.GO("吴健俊","01281-01596","西南");
		gt.GO("周盛","01597-03456","西南");
		gt.GO("占文冲","01552-03100","西北");


		//方式2：使用SQLite******************************************************************************************************************************
//		gt.setUseTable("api");			//使用表名【SQLite的若不存在会自动创建】
//		gt.setUseExcel("8.23.xls");				//使用哪个Excel【项目根目录/Excel/】
//		gt.GO_SQLite("陈宇","02065-02145","华中");
//		gt.GO_SQLite("刘聪","02396-02461","华北");
//		gt.GO_SQLite("房文迪","01311-02769","华东");
//		gt.GO_SQLite("严加远","02770-02781","华东");
//		gt.GO_SQLite("吴健俊","01281-01596","西南");
//		gt.GO_SQLite("周盛","01597-03456","西南");
//		gt.GO_SQLite("占文冲","01552-03100","西北");
		
		
		System.out.println("共花费:"+(double)(System.currentTimeMillis()-begin)/1000+"秒");
	}*/
}
