package com.bonait.dataextract.scheduler;

import java.util.Date;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.bonait.dataextract.service.GonggBgService;
import com.bonait.dataextract.service.KongZjService;
import com.bonait.dataextract.service.ZhaobDyService;
import com.bonait.dataextract.service.ZhaobGgService;
import com.bonait.dataextract.service.ZhaobWjService;
import com.bonait.dataextract.service.ZhaobYgService;
import com.bonait.dataextract.service.ZhongbXxService;
import com.bonait.dataextract.service.ZisJgService;

public class BackupTask extends QuartzJobBean {

	private static ZhaobGgService zhaobGgService;
	private static ZhongbXxService zhongbXxService;
	private static ZhaobWjService zhaobWjService;
	private static ZhaobDyService zhaobDyService;
	private static ZisJgService zisJgService;
	private static GonggBgService gonggBgService;
	private static KongZjService kongZjService;
	private static ZhaobYgService zhaobYgService;
	

	@Override
	protected void executeInternal(JobExecutionContext arg0)
			throws JobExecutionException {
	}

	public static void main(String[] args) {	
		System.out.println("bxkc_Test start time is"+new Date());

		ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
		
		//changeModel;
		
		System.out.println("bxkc_Test end time is"+new Date());
	}
}
