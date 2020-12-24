package com.example.schedular;

import java.util.Date;
import java.util.Random;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Scheduled;

import com.example.controller.CafeteriaController;
import com.example.dao.CafeteriaRepository;
import com.example.model.Cafeteria;
import com.example.service.CafeteriaService;
import com.example.utils.RestClientTracker;

public class CafeHistoryJob implements Job{

	@Autowired
	CafeteriaService service;
	
	
	
	private static final Logger logger = Logger.getLogger(CafeHistoryJob.class);

	
	
@Override
	
	public void execute(JobExecutionContext context) throws JobExecutionException {
		try {
		
		logger.info("Schedular Job is running...");
		System.out.println("execute");
		System.out.println("add service call in " + new Date().toString());
		
		
		
		ApplicationContext contextInstance = RestClientTracker.getInstance().getApplicationContext();
		CafeteriaService service = contextInstance.getBean(CafeteriaService.class);
		service.saveOccupancyHistory();
		System.out.println("executedddddddddd");}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		
		
	}
}

	
	
