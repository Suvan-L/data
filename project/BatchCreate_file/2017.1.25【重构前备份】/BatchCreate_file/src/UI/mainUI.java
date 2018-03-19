package UI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import Utils.IOFile;
import Utils.UseDB;
import main.GoTask;

/**最后更新:2017.1.9
 * 		主界面		
 * 
 *@author Suvan
 */
public class mainUI  extends JFrame implements ActionListener{
		 
	private  JComboBox<String> db_table;				//选择数据表 -组合框
	private JButton db_insertTable;		   					//"导入数据"-按钮
	private JButton model_alterB;			  					//"修改模版2"-按钮 【BackTaskup.java】
	private JButton model_alterZ;			   					//"修改模版2"-按钮【ZhaoGgServiceModel.java】
	
	private JButton go;								 				//"开始"-按钮
	
	private JLabel model1;											//BackTaskup.java模版
	private JLabel model2;						   					//ZhaoGgServiceModel.java模版
	private JTextField path;					 					//目录路径
	private JTextField catologName;						    //目录名
	private JTextField userName;  								//程序猿
	private JTextField beginNum;								//开始编号
	private JTextField endNum;									//结束编号
	private JTextField area;											//地区
	
	private JCheckBox match_num;							//是否开启编号范围匹配
	private JCheckBox match_area;							//是否开启地区匹配
	
	private ButtonGroup choiceOnlyMode;				//选按钮组对象，用于构造双选
	private JRadioButton  choiceMode_ALLOT;		//分配模式
	private JRadioButton  choiceMode_GATHER;	//集成模式
	
	//无参构造函数  
	public mainUI(){
		super();
		this.getContentPane().setLayout(null);	//获取Container容器,装载JFrame窗体,且设置空布局
		this.setTitle("BatchCreate_file");				//设置标题
		this.setSize(610,300);								//设置宽,高

		this.getLeft();												//界面左模块
		this.getRight();											//界面右模块
	
		this.ageRecord("read");							//读取填写记录
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);				//关闭窗口即退出程序
	}
	
	//方法1：界面左模块
	private void  getLeft(){
		
		//1.构建组件
		model1 = new JLabel("BackTask.java");								//标签
		model1.setBounds(5,100,150,25);
		model2 = new JLabel("ZhaoGgServiceModel.java");			//标签
		model2.setBounds(5,180,150,25);
		
		if(db_table ==null){		
			db_table = new JComboBox<String>();							//组合框
			db_table.setBounds(5,20,150,25);//x,y【坐标】,width,height【宽,高】

				try{
					UseDB udb =new UseDB("SQLite");
					udb.connDatabase("batchcreate_file.db");	
					String [] table=udb.selectSQL("SELECT name FROM SQLITE_MASTER WHERE type='table'ORDER BY name").split("&&");
					for(int i=0;i<table.length;i++){
						if("article".equals(table[i]))	continue;		//去除article表【模版表】
						db_table.addItem(table[i]);
					}
					
					udb.closeAll();
				}catch(Exception e){
					e.printStackTrace();
				}
		}
		if(db_insertTable == null){									
			db_insertTable = new JButton("导入数据");					//按钮
			db_insertTable.setBounds(175,20,100,25);						//设置x,y【屏幕坐标】,width,height【组件宽高】
			
			db_insertTable.addActionListener(this);							//注册按钮点击事件
		}
		if(model_alterB == null){											
			model_alterB = new JButton("修改模版1");						//按钮
			model_alterB.setBounds(175,100,100,25);
			
			model_alterB.addActionListener(this);		   				
		}
		if(model_alterZ == null){
			model_alterZ = new JButton("修改模版2");						//按钮
			model_alterZ.setBounds(175,180,100,25);
			
			model_alterZ.addActionListener(this);							
		}
		
		JPanel panel_1 = new JPanel(){											//画垂直分割线【分隔左右模块】
			public void paint(Graphics g){
				super.paint(g);
				g.setColor(Color.GRAY);
				 g.drawLine(0,0,0,300);	//(x,y)点到(x,y)点
			}
		};
		panel_1.setBounds(280, 0, 1, 300);
		
		
		//2.添加组件
		this.add(panel_1);
		this.add(db_table)	;		this.add(db_insertTable);
		this.add(model1);			this.add(model_alterB);
		this.add(model2);			this.add(model_alterZ);
	}
	
	//方法2:界面右模块
	private void getRight(){
		
		//1.构建组件
		JLabel lb_path = new JLabel("目录路径");										//标签
		JLabel lb_catologName = new JLabel("目录名");							//标签
		JLabel lb_userName = new JLabel("程序猿");									//标签
		JLabel lb_beginNum = new JLabel("开始编号    ----");					//标签
		JLabel lb_endNum = new JLabel("终止编号");								//标签
		JLabel lb_area = new JLabel("地区");												//标签
		
		lb_path.setBounds(290,20,60,25);						//设置x,y【屏幕坐标】,width,height【组件宽高】								
		lb_catologName.setBounds(290,50,55,25);
		lb_userName.setBounds(285,100,55,25);
		lb_beginNum.setBounds(285,145,90,25);
		lb_endNum.setBounds(380,145,55,25);
		lb_area.setBounds(285,220,55,25);
		
		if(path == null){
			path  = new JTextField();									//单行文本
			path.setBounds(350,20,220,25);
		}
		if(catologName == null){
			catologName = new JTextField();		  			 //单行文本
			catologName.setBounds(350,50,220,25);
		}
		if(userName == null){
			userName = new JTextField();							//单行文本
			userName.setBounds(330,100,100,25);
		}
		if(beginNum == null){
			beginNum = new JTextField();							//单行文本
			beginNum.setBounds(285,175,50,25);
		}
		if(endNum == null){
			endNum = new JTextField();							//单行文本
			endNum.setBounds(380,175,50,25);
		}
		if(area == null){
			area = new JTextField();									//单行文本
			area.setBounds(330,220,100,25);;
		}
		if(match_num == null){
			match_num = new JCheckBox("是否开启编号范围匹配");		 //复选按钮
			match_num.setBounds(440,100,200,25);
		}
		if(match_area == null){
			match_area = new JCheckBox("是否开启区域匹配");				//复选按钮
			match_area.setBounds(440,130,150,25);
		}
		if(choiceMode_ALLOT==null){
			choiceMode_ALLOT = new JRadioButton("分配",true);		//默认选择
			choiceMode_ALLOT.setBounds(440,160,70,25);
		}
		if(choiceMode_GATHER==null){
			choiceMode_GATHER = new JRadioButton("集成");
			choiceMode_GATHER.setBounds(520,160,70,25);
		}
		if(choiceOnlyMode==null){
			choiceOnlyMode =new ButtonGroup();
			choiceOnlyMode.add(choiceMode_ALLOT);		//将单选按钮添加进按钮组
			choiceOnlyMode.add(choiceMode_GATHER);
		}
		
		if(go == null){
			go = new JButton("开始");										 //按钮
			go.setBounds(440,190,145,50);
			
			go.addActionListener(this);										//注册按钮点击事件
		}
		
		JPanel panel_1 = new JPanel(){									//画水平分割线【分隔右模块的上下】
			public void paint(Graphics g){
				super.paint(g);
				g.setColor(Color.GRAY);
				 g.drawLine(0,0,300,0);	//(x,y)点到(x,y)点
			}
		};
		panel_1.setBounds(280, 90, 320, 1);
		
		JPanel panel_2 = new JPanel(){								  //画水垂直割线【分隔右模块的下部分的左右】
			public void paint(Graphics g){
				super.paint(g);
				g.setColor(Color.GRAY);
				 g.drawLine(0,0,0,300);	//(x,y)点到(x,y)点
			}
		};
		panel_2.setBounds(435, 90, 1, 300);
		
		
		//2.添加组件
		this.add(panel_1);
		this.add(panel_2);
		this.add(lb_path);						this.add(path);
		this.add(lb_catologName);		this.add(catologName);
		this.add(lb_userName);			this.add(userName);
		this.add(lb_beginNum);			this.add(beginNum);
		this.add(lb_endNum);				this.add(endNum);
		this.add(lb_area);						this.add(area);
		this.add(match_num);				this.add(match_area);
		this.add(choiceMode_ALLOT);	this.add(choiceMode_GATHER);
		this.add(go);
	}
	

	//方法3.读取上次记录
	public void ageRecord(String state){	//参数:状态
		
			IOFile iof = new IOFile();
		try{
			
			if("read".equals(state)){				//处理读取状态
				
				//A-读取文件内容,转成utf-8格式
				String content=iof.rFileContent("Data/record.suvan", "UTF-8");			
				
				//B-处理数据,得到数组
				String [] result =content.split("\n");	//以换行符作为分隔符,每一行为1个数组元素
				for(int i=0;i<result.length;i++){
					result[i]=result[i].substring(result[i].indexOf("<[")+2, result[i].lastIndexOf("]>"));	//
				}
				
				//C-将数据设置到相应组件
				path.setText(result[0]);
				catologName.setText(result[1]);
				userName.setText(result[2]);
				beginNum.setText(result[3]);
				endNum.setText(result[4]);
				area.setText(result[5]);
				
			}else if("save".equals(state)){	//处理保存状态
				
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
			i.getMainUI(this);
			i.setVisible(true);
			
		}else if("修改模版1".equals(e.getActionCommand())){
				//A1-BackupTask模版界面
				alterModelUI a1 = new alterModelUI("BackupTask.java");//加载界面,且传入要读取的文件名
					a1.setTitle("BackTask.java模版");
					a1.setVisible(true);
				
				//A2-BackupTask语法修改界面
//				alterModelUI a2 =new alterModelUI("BackupTask.txt");
//					a2.setLocation(a1.getX()+a1.getWidth(), a1.getY());	//基于a1的坐标进行设定
//					a2.setTitle("语义修改");
//					a2.setVisible(true);
				
		}else if("修改模版2".equals(e.getActionCommand())){
				//B1-ZhaobGgServiceModel模版界面
				alterModelUI b1 = new alterModelUI("ZhaobGgServiceModel.java");
					b1.setTitle("ZhaoGgServiceModel.java模版");
					b1.setVisible(true);
				
				//B2-ZhaobGgServiceModel语法修改界面
//				alterModelUI b2 = new alterModelUI("ZhaobGgServiceModel.txt");
//					b2.setTitle("语义修改");;
//					b2.setLocation(b1.getX()+b1.getWidth(), b1.getY());	//基于a1的坐标进行设定
//					b2.setVisible(true);
			
		}else if("开始".equals(e.getActionCommand())){
			//1.保存数据
			this.ageRecord("save");
			
			//2.复选框判断
			boolean numSign =true;	//开启了编号范围匹配,则必须填写开始和结束编号
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
						gt.setPath(path.getText()); 															//C:/Users/Liu-shuwei/Desktop\\
						gt.setCatalogName(catologName.getText()); 							//入库【4.0版,823表(完全版-未筛选)】
						gt.setNumMatch(match_num.isSelected());								//是否开启编号范围匹配
						gt.setAreaMatch(match_area.isSelected());								//是否开启地区匹配
						gt.setUseTable(db_table.getSelectedItem().toString());			//使用表名【SQLite的若不存在会自动创建】
						
						if(choiceMode_GATHER.isSelected())  //选中集成模式
							gt.GO_SQLite_GATHER(userName.getText(),beginNum.getText()+"-"+endNum.getText(), area.getText());
						else			//未选中继承则默认执行分配模式
							gt.GO_SQLite(userName.getText(),beginNum.getText()+"-"+endNum.getText(), area.getText());
						

						JOptionPane.showMessageDialog(null, "生成完毕！", "success",JOptionPane.PLAIN_MESSAGE);  
					}else{
						JOptionPane.showMessageDialog(null, "开启了编号范围匹配 or 地区匹配\n则必须填写相应字段！", "falil",JOptionPane.ERROR_MESSAGE);  
					}
			}else{
				JOptionPane.showMessageDialog(null, "抱歉,路径,文件名,程序猿不能为空！", "falil",JOptionPane.ERROR_MESSAGE);  
			}		
		}	
	}
	
	//更新选择列表
	public void updateItem(){
		db_table.removeAllItems();//删除所有项
		
		try{
			UseDB udb =new UseDB("SQLite");
			udb.connDatabase("batchcreate_file.db");	
			String [] table=udb.selectSQL("SELECT name FROM SQLITE_MASTER WHERE type='table'ORDER BY name").split("&&");
			for(int i=0;i<table.length;i++){
				if("article".equals(table[i]))	continue;		//去除article表【模版表】
				db_table.addItem(table[i]);
			}
			udb.closeAll();
		}catch(Exception e){
			e.printStackTrace();
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




}






