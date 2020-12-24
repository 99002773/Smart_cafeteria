package com.example.schedular;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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

public class CafeSchedularJob implements Job{

//	@Autowired
//	ApplicationContext context1;
	//SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//	@Autowired
//	private SessionFactory sessionFactory;
	//Date d =dateFormat.parse("2020-12-04 17:52:09");
	
	//Cafeteria caff1 = new Cafeteria(75,"Dining Area",67,d,d,"admin",d,"admin");
	@Autowired
	CafeteriaService service;
	
	//CafeteriaService cafeteriaRepo=new CafeteriaService();
	
	private static final Logger logger = Logger.getLogger(CafeSchedularJob.class);
//	public void post()
//	{
//		logger.info("Schedular Job iddddddddddddd");	
//	}

	
	

	 
	// Session session = this.sessionFactory.getCurrentSession();
/*@Autowired
	CafeteriaService cafeteriaService;
	@Autowired
	CafeteriaController cafe;

	public CafeteriaService getCafeteriaService() {
		return cafeteriaService;
	}

	public void setCafeteriaService(CafeteriaService cafeteriaService) {
		this.cafeteriaService = cafeteriaService;
	}*/

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		try {
		//Session session = HibernateUtil.getSessionFactory().openSession();
		//Cafeteria caffe=new Cafeteria();
		logger.info("Schedular Job is running...");
		System.out.println("execute");
		System.out.println("add service call in " + new Date().toString());
		
		//CafeteriaService service = new CafeteriaService();
		//CafeteriaService service = context1.getBean(CafeteriaService.class);
		
		ApplicationContext contextInstance = RestClientTracker.getInstance().getApplicationContext();
		CafeteriaService service = contextInstance.getBean(CafeteriaService.class);
		service.saveOccupancyBase();
		System.out.println("executedddddddddd");}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		/*Date d = null;
		try {
			d = dateFormat.parse("2020-12-04 17:52:09");
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Cafeteria caff1 = new Cafeteria(75,"Dining Area",67,d,d,"admin",d,"admin");
		
//		CafeteriaService cafeteriaRepo=new CafeteriaService();
		
			Random rand = new Random();
			/*int selected = rand.nextInt(100);
			caff1.setPeoplecount(24);
			
			caff1.setId(75);
			caff1.setSpacetype("Dining Area");
			try {
				caff1.setCreated_date(dateFormat.parse("2020-12-04 17:52:09"));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			caff1.setCreated_by("Admin");
			try {
				caff1.setInserted_datetime(dateFormat.parse("2020-12-04 17:52:09"));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				caff1.setModified_date(dateFormat.parse("2020-12-04 17:52:09"));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			caff1.setModified_by("Admin");
			//ApplicationContext contextInstance = RestClientTracker.getInstance().getApplicationContext();
			
			
			//JobKey jobKey = context.getJobDetail().getKey();
		    //logger.info("SimpleJob says: " + jobKey + " executing at " + new Date());*/
			//session.save(caff1);
			//System.out.println(caff1);
			
				
			
//			public void p() 
//			{
//				service.post();
//				}
	
		
	}





			
			
			
	/*@Scheduled(fixedDelay = 10000 ,initialDelay = 10000)
	public void postingdata() {
		System.out.println("hiiiii");
//		cafe.save();
	}*/
	
	
}
