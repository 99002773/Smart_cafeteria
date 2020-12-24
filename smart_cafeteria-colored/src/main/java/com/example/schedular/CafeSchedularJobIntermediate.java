package com.example.schedular;

import java.util.Date;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.example.service.CafeteriaService;
import com.example.utils.RestClientTracker;

public class CafeSchedularJobIntermediate implements Job {

	
	@Autowired
	CafeteriaService service;
	
	private static final Logger logger = Logger.getLogger(CafeSchedularJob.class);
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		try {
			
			logger.info("Schedular Intermediate Job is running...");
			System.out.println("execute Intermediate");
			System.out.println("add service call in " + new Date().toString());
			
			ApplicationContext contextInstance = RestClientTracker.getInstance().getApplicationContext();
			CafeteriaService service = contextInstance.getBean(CafeteriaService.class);
			service.saveOccupancyIntermediate();
			System.out.println("executedddddddddd intermediate");}
			
		catch(Exception e) {
				e.printStackTrace();
			}
		
		
	}
	

}
