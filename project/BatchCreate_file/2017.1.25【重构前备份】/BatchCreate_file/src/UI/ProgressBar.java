package UI;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JProgressBar;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


/**最后更新：2016.12.17 
 * 			进度条页面，可根据需求配置定时器 【在项目中未实现】
 * @author Suvan
 *
 */
public class ProgressBar implements ActionListener,ChangeListener{		//Change事件，让类能够实现接收和处理的Change事件
	
	
		private JFrame frame=null;
		private JProgressBar progressbar;
		public Timer timer;

		//1.无参构造函数，用于初始化界面
		public ProgressBar(){
			 
				if(frame == null){
					frame=new JFrame("进度条");//窗体
					frame.setBounds(100, 100, 400, 130);	//x,y 坐标,width,height
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	//点击X健，退出程序且关闭线程
				}
				if(progressbar == null){
					progressbar = new JProgressBar();//创建进度条实例,
					progressbar.setOrientation(JProgressBar.HORIZONTAL); //设置水平
					
					progressbar.setMinimum(0);					//设置最小值
					progressbar.setMaximum(100);				//设置最大值
					progressbar.setValue(0);							//设置当前进度
					progressbar.setBackground(Color.pink);//设置进度条颜色
					progressbar.setStringPainted(true);		//设置stringPainted属性值，该属性确定进度条是否应该呈现字符串
					progressbar.setBorderPainted(true);		// 设置 borderPainted 属性，如果进度条是否绘制其边框
					progressbar.setPreferredSize(new Dimension(300,20));//setSize是设定的固定大小，而setPreferredSize仅仅是设置最好的大小,new Dimension(500,20)，设置精准的尺寸
					
					progressbar.addChangeListener(this);	//注册Change事件
				}
					
					//创建容器
					Container contentPanel=frame.getContentPane();
					contentPanel.add(progressbar);
				   
					//定时器
					if(timer == null){
						timer = new Timer(100,this);
					}
					
					//显示窗体
					frame.setVisible(true);
		}

		
		@Override	//监听按钮变化
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() ==timer){ 
				int value =progressbar.getValue();
				if(value <100){
					progressbar.setValue(++value);
				}
			}
		}
		
		//事件监听变化，当ProgressBar类发生变化时候，则回调此函数【观察者模式-初级】
		public void stateChanged(ChangeEvent e1) {
			System.out.println("ProgressBar发生变化了--->"+progressbar.getValue());
			if(e1.getSource()==progressbar){
				if(progressbar.getValue()==100){
					System.out.println("加载完毕，关闭窗体");
					frame.dispose();
				}
			}
		}

		//显示进度值
//		public synchronized void GO(int pactent){
//			 new Thread(new MyThread()).start();  
//			try{
//				Thread.sleep(5000);
//			}catch(Exception e){
//				e.printStackTrace();
//			}
//		}
		
		//关闭窗体,释放所有资源
		public void closeAll(){
			frame.dispose();
			frame=null;
			progressbar=null;
		}

	public static void main(String[] args){	
	//1.测试进度条
		ProgressBar app=new ProgressBar();
		app.timer.start();
//		for(int i=0;i<100000000;i++){
//			if(i%10000000>=1){
//				app.GO((i%10000000));
//			}		
//		}
		//2.测试定时器
//		Timer timer =new Timer();
//		timer.schedule(new MyTask(),1*000,5*1000);//任务，1s后开始，每次5s
	}
	
	//定时器类
	class MyTask extends TimerTask{
		public  void run(){
			System.out.println("目前进度："+1+"%");
		}
	}


	
	//线程类
//	class MyThread implements Runnable{
//
//		public void run(){
//			progressbar.setValue(pacent);
//			frame.repaint();
//		}
//	}
}


//	}

