package com.example;

import java.util.Calendar;
import java.util.TimeZone;

import org.apache.log4j.BasicConfigurator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.ApplicationContext;

import com.example.dao.CafeteriaRepository;
import com.example.schedular.CafeOccupancyInterSchedular;
import com.example.schedular.CafeOccupancySchedular;
import com.example.service.CafeteriaService;



@SpringBootApplication(exclude = HibernateJpaAutoConfiguration.class)
public class SmartCafeteria1Application {

	
	
	public static void main(String[] args) {
		
		TimeZone tz = Calendar.getInstance().getTimeZone();
		System.out.println(tz.getID());
		BasicConfigurator.configure();
		
		SpringApplication.run(SmartCafeteria1Application.class, args);
		CafeOccupancySchedular.startInsertingPeopleCount();
		//CafeOccupancyInterSchedular.startInsertingPeopleCount();
		System.out.println("hiiiii");
		
	}

}
