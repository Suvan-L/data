/**
 * 
 */
package UI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

import Utils.IOFile;
import Utils.UseDB;
import main.GetTableData;

/**最后更新：2016.12.18
 * 		导入excel数据页面
 * 
 * @author Suvan
 */
public class insertUI extends JFrame implements ActionListener{
	
	private JTextField excelFilePath;		//excel表格的文件路径
	private JButton fileChoice;				//文件选择
	private JButton beginInsert;            //开始导入
	
	private mainUI mui;
	//无参构造函数
	public insertUI(){
		super();
		this.getContentPane().setLayout(null);	//获取Container容器,装载JFrame窗体,且设置空布局
		this.setTitle("导入excel表数据");				//标题
		this.setSize(300,200);								//面积
		this.setLocation(610, 0);							//位置
		
		this.load();	//加载界面
	}
	
	//方法1: 加载界面
	private void load(){
		
		//1.构建组件	
		JLabel lb_excelFilePath = new JLabel("excel文件路径:");
		lb_excelFilePath.setBounds(20,30,100,25);			//设置x,y【屏幕坐标】,width,height【组件宽高】
		
		if(excelFilePath == null){
			excelFilePath = new JTextField();				
			excelFilePath.setBounds(20, 60, 220, 25);	
		}
		if(fileChoice == null){
			fileChoice = new JButton("...");
			fileChoice.setBounds(240,60,25,25);
			
			fileChoice.addActionListener(this);					//注册按钮监听事件
		}
		if(beginInsert == null){
			beginInsert = new JButton("开始导入");
			beginInsert.setBounds(100,100,90,35);
			
			beginInsert.addActionListener(this);				//注册按钮监听事件
		}
		
		//2.为窗体添加组件
		this.add(lb_excelFilePath);  		this.add(excelFilePath);
		this.add(fileChoice);
		this.add(beginInsert);
	}


	//方法2.按钮点击事件
	public void actionPerformed(ActionEvent e){
		
		if("...".equals(e.getActionCommand())){
			//A.构建文件选择器
			 JFileChooser fc = new JFileChooser(); 

			 FileSystemView fsv = FileSystemView.getFileSystemView();         
			 fc.setCurrentDirectory(fsv.getHomeDirectory());  			 //设置桌面为默认路径
			 
			 fc.setFileSelectionMode(JFileChooser.FILES_ONLY);//设置只能选择文件
			 fc.setFileFilter(new FileNameExtensionFilter("只支持Excel97-2003 工作簿(*.xls)","xls"));		//【"提示信息","支持类型"】-文件过滤器
			 
			 int result = fc.showOpenDialog(this);					//弹出打开文件对话框
//			 int resut =fc.showSaveDialog(this);						//弹出"保存文件"对话框
		      if( result == JFileChooser.APPROVE_OPTION){	//表示获得选中对象
		    	  File f = fc.getSelectedFile();
		    	 excelFilePath.setText(fc.getSelectedFile().getPath());	//	获得文件路径设置到JTestField
		      }
		      
		}else if("开始导入".equals(e.getActionCommand())){
			if(excelFilePath.getText().length()>2){
				UseDB udb = new UseDB("SQLite");
				IOFile iof = new IOFile();
				GetTableData  gtd = new GetTableData(iof,udb);
				
				try{
	
					udb.connDatabase("batchcreate_file.db");						//连接数据库【若不存在则创建】	
					gtd.s_InsertExcel(excelFilePath.getText());						 //导入Excel数据,【传入excel文件名和使用表名】
					
					mui.updateItem();	//更新mainUI的数据表选择数据
				
					JOptionPane.showMessageDialog(null, "导入成功！", "success",JOptionPane.PLAIN_MESSAGE);  												//提示信息
					this.setVisible(false);
				}catch(Exception k){
					gtd.s_dropTable(excelFilePath.getText()); 		//插入异常，删除已经存在的表
					try{
						udb.udbCommit();         									 //遇到异常，先把事务提交了先
					}catch(SQLException ee){}

					mui.updateItem();												//更新mainUI的数据表选择数据
					JOptionPane.showMessageDialog(null, "抱歉,导入失败,出现异常！", "fail",JOptionPane.ERROR_MESSAGE);  								//错误信息
					k.printStackTrace();
				}
			}else{
				JOptionPane.showMessageDialog(null, "大兄弟,请选择目录or手动填写目录！", "success",JOptionPane.WARNING_MESSAGE);  	//警告信息
			}
		}
	}
	
	//方法3：获取mainUI的窗体类型，并进行更新
	public void getMainUI(mainUI mui){
		this.mui=mui;
	}
	
	public static void main(String[] args) {
		insertUI i = new insertUI();
		i.setVisible(true);
	}
}
