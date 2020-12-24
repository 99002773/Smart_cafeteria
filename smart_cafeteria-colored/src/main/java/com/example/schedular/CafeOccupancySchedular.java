package com.example.schedular;


import java.util.Properties;

import org.apache.log4j.Logger;
//import org.apache.logging.log4j.Logger;
//import org.springframework.beans.factory.config.PropertiesFactoryBean;
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.scheduling.annotation.EnableScheduling;

import org.springframework.context.annotation.Bean;

public class CafeOccupancySchedular {
	
	final static Logger LOGGER = Logger.getLogger(CafeOccupancySchedular.class);

	@Bean
	public static void startInsertingPeopleCount() {
		System.out.println("startt");
		//PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
		//propertiesFactoryBean.setLocation(new ClassPathResource("/quartz.properties"));
		//propertiesFactoryBean.afterPropertiesSet();
		//Properties prop = new Properties();0 0/5 * 1/1 * ? *
		//0 0/1 * 1/1 * ? *
		
		
		//0 0 0/1 * * ?      every 1 hr
		//String runStatus = prop.getProperty("/src/main/resources/quartz.properties");
		//if (runStatus.equalsIgnoreCase("TRUE")) {0/30 0/1 * 1/1 * ? *
		
			new CafeSchedular().configureScheduler("update schedule", "VendorAppointmentTrigger",
					"vendorAppointmentGroup", "0 0/5 * 1/1 * ? *", CafeSchedularJob.class);
//			
			//System.out.println("ENDDDDDDDDDD 1");
			
			new CafeSchedular().configureScheduler("doit schedule", "Vendortrigger",
					"vendorGroup", "0 0 0/1 * * ?", CafeSchedularJobIntermediate.class);
			new CafeSchedular().configureScheduler("doit ", "Vendor",
					"vendor", "0 0 10 * * ?", CafeHistoryJob.class);
			
			
//			new CafeSchedular().configureScheduler("update capacity schedule", "CapacityTrigger",
//					"CapacityGroup", "0 0/5 * 1/1 * ? *", CafeSchedularJobCapacity.class);
			
			//System.out.println("ENDDDDDDDDDD 2");
		//}
		System.out.println("ENDDDDDDDDDD");
	}
	
}
