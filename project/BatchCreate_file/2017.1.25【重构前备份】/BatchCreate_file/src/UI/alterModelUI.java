/**
 * 
 */
package UI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import Utils.IOFile;

/**最后更新：2016.12.18
 * 		修改模版界面
 * 
 * @author Suvan
 */
public class alterModelUI extends JFrame{
	
	private JTextArea fileContent;			//多行文本
	private JScrollPane scrollPane;			//滚动条
	private JButton saveAlter;				//保存修改按钮
	
	//有参构造函数,初始化JFrame,切传入要读取的文件名
	public alterModelUI(String fileName){
		super();
		this.getContentPane().setLayout(null);//设置容器，并且不设置布局
		this.setSize(610,500);
		this.setLocation(0, 300);
		
		load(fileName);			//加载界面
		readFile(fileName);	//读取文件,且显示到JTextArea
	}
	
	//方法1：加载界面
	private void load(String fileName){
		
		//1-赋值，构建对象
		if(fileContent == null){
			fileContent = new JTextArea();
			fileContent.setBounds(0,0,605,400);
			
		}if(scrollPane == null){
			scrollPane = new JScrollPane(fileContent);
			scrollPane.setBounds(0,0,605,400);
		}
		if(saveAlter == null){
			saveAlter = new JButton("保存修改");
			saveAlter.setBounds(220,403,150,50);
			
			//A.【保存修改】注册按钮点击事件且添加监听
			saveAlter.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					try{
						IOFile iof = new IOFile();

						iof.cFile("model_file", fileName, "UTF-8",fileContent.getText());			//新建文件
						
						JOptionPane.showMessageDialog(null, "保存成功", "success",JOptionPane.WARNING_MESSAGE);  //弹出提示对话框
						
					}catch(Exception k){
						JOptionPane.showMessageDialog(null, "保存失败,出现异常", "fail",JOptionPane.WARNING_MESSAGE);  
						k.printStackTrace();
					}
				}
			});
			
		}
		
		this.add(scrollPane);
		this.add(saveAlter);
	}
	
	//方法2：读取文件
	private void readFile(String fileName){
		try{
			IOFile iof = new IOFile();
			String content = iof.rFileContent("model_file/"+fileName, "utf-8");
			this.fileContent.setText(content);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
