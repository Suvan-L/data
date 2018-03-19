package com.bonait.dataextract.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

import com.bonait.dataextract.domain.ZhaoBiaoGongGao;
import com.bonait.dataextract.service.ZhaobGgService;
import com.bonait.dataextract.util.Util;//是否导入匹配混合工具包;
import com.bonait.dataextract.vo.AreaAndProvince;
import com.bonait.dataextract.vo.ListVO;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


/**
 * @程序猿：//@程序猿;
 * 
 * @内容：//@内容;
 */
@Service("//注解;")
public class //类名; implements ZhaobGgService{

	@Resource
	private SessionFactory sf ;
	private Query query;
	private ZhaoBiaoGongGao zbgg;
	//列表页接口地址
	private String listUrl = "";
	//内容页接口地址
	private String detailUrl = "";
	//入库编码
	private String sourceNo = "//编号;";
	
	@Override
	public void initZhaobGgService() {
		
		List<List<ListVO>> lists = new ArrayList<List<ListVO>>();
		Session session = sf.openSession();
		List<ListVO> list;
		JSONArray temp;
		session.beginTransaction();
		
		try{
            //获取总页数
		      String s = Util.sendGet(listUrl,"1");
		      JSONObject obj = JSONObject.fromObject(s);
		      int maxPage = Integer.parseInt(obj.get("pageCount").toString());
		      System.out.println("pageCount:" + maxPage);

				//清空数据库里
//				query=session.createSQLQuery("DELETE FROM //表名; t where t.WEB_SOURCE_NO='"+sourceNo+"'");
//				query.executeUpdate();
//				session.getTransaction().commit();
//				session.beginTransaction();
		
		       
				int KK=0;
				int re;
					//maxPage 页数
					flag:for(int i = 1;i <= maxPage; i++){
							String jsonList = Util.sendGet(listUrl,i + "");
							JSONObject objList = JSONObject.fromObject(jsonList);
							temp = (JSONArray) objList.get("list");
							list = (List<ListVO>)JSONArray.toList(temp,ListVO.class);
							query = session.createSQLQuery("SELECT ID FROM //表名; t where t.WEB_SOURCE_NO='"+sourceNo+"' and t.RECORD_ID='"+list.get(0).getId()+"'");
							if(query.executeUpdate() <= 0){
								lists.add(list);
							}else{
								break flag;
							}
					}
					if(lists.size() != 0)
						for(int i=lists.size()-1; i >= 0 ; i--){
								for(int j = lists.get(i).size()-1;j >= 0;j--){

									//是否开启混合类型匹配;
									//匹配标题和日期
									query = session.createSQLQuery("SELECT ID FROM //表名; t where t.WEB_SOURCE_NO='"+ sourceNo+ "' and t.PAGE_TITLE='"+ lists.get(i).get(j).getTitle() + "' and t.PAGE_TIME='"+lists.get(i).get(j).getDate()+"'");
									if (query.executeUpdate() > 0) {
										//匹配ID
										query = session.createSQLQuery("SELECT ID FROM //表名; t where t.WEB_SOURCE_NO='"+ sourceNo+ "' and t.RECORD_ID='"+ lists.get(i).get(j).getId() + "'");
										if(query.executeUpdate() <= 0){
											//若不同加入随机2位数字
											Random ss = new Random();
											re = ss.nextInt(100);
											lists.get(i).get(j).setTitle(lists.get(i).get(j).getTitle()+"-"+re);
										}else{
											continue;
										}
									}
									System.out.println(lists.get(i).get(j).getDate());
					
									int date = Integer.parseInt(lists.get(i).get(j).getDate().substring(0, 4));
									
									//只取2014年至今的数据
									if(date>=2014){
								    	KK+=1;
								    	String detail = Util.sendGet(detailUrl, lists.get(i).get(j).getId());
										JSONObject o = JSONObject.fromObject(detail);
										String content = o.get("content").toString();
									    //过滤掉无效数据
										 if (content == null || content.length() < 10 || content.contains("出错") || content.contains("找不到文件"))
											{
												continue;
											}
										 List<AreaAndProvince> plist = Util.contiansProvince(lists.get(i).get(j).getTitle());
										for (int x = 0; x < plist.size(); x++) {
												AreaAndProvince areaAndProvince = plist.get(x);
												
											    zbgg = new ZhaoBiaoGongGao();
											    zbgg.setWebSourceNo(sourceNo);
											    zbgg.setArea("//Area;");//区域
											    zbgg.setProvince("//Province;");//省份
											    zbgg.setCity("//City;");//城市，没有可不填
											    zbgg.setDistrict("//County;");// 区县 不要“区”字符
											    zbgg.setWebSourceName("//Webname;");//网站名称，写完整的名称
											    zbgg.setInfoSource("//Infsource;");//信息来源
					    
											    //未匹配到工程或服务的，都标识为货物
									        	String infoType = Util.getInfoType(content);
									        	if (infoType != null && infoType.length() > 0)
									        		zbgg.setInfoType(Util.getInfoType(content));
												else
												{
													zbgg.setInfoType("货物");
												}
									        	//是否有信息类型;
							        	
									        	zbgg.setIndustry(Util.getIndustry(content));//行业分类,自动匹配
									        	//是否有行业分类;
		
											    zbgg.setRecordId(lists.get(i).get(j).getId());
											    zbgg.setId(UUID.randomUUID().toString());
											    zbgg.setPageTitle(lists.get(i).get(j).getTitle());
											    zbgg.setPageTime(lists.get(i).get(j).getDate());
											    zbgg.setPageContent(content);
											    zbgg.setPageAttachments("");//附件url，暂不需要
											    zbgg.setCreateTime(new Date());
												session.save(zbgg);
										}
									}
								    if(KK % 1 == 0){
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
