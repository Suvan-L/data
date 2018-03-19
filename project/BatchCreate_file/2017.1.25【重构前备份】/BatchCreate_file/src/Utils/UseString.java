package Utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**更新时间:2016.12.03
 * 			操作字符串
 * 				方法1：转换
 * 				方法2：判断 
 * 				方法3：正则表达式
 * 				方法4：格式化
 * 				方法5：获取
 * 		@author Suvan
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
