package com.example.utils;

import org.apache.log4j.Logger;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.example.schedular.CafeOccupancySchedular;


public class ServletInitializer extends SpringBootServletInitializer{
	
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application)
	{
		System.out.println("hiiiiiservlet");
		
		CafeOccupancySchedular.startInsertingPeopleCount();
		System.out.println("hiiiiie");
		return application.sources(ServletInitializer.class);
		
		
	}
}
