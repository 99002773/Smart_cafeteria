package com.example.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.dao.CafeteriaRepository;

@Component
public class RandomUtilIntermediate {
	
	
//	@Autowired
//	static
//	CafeteriaRepository cr;
	
	
	//static long id=0;
	
	
//	public static  Integer People_Count()
//	{    
//		System.out.println("random inter");
//		CafeteriaRepository cr = new CafeteriaRepository();
//		
//		
//	    int ppl= cr.getFromBase();
//	    System.out.println("random inter after");
//		return ppl;   
//	}
	
	public static String timeslot()
	{
		Date date = new Date();
        System.out.println("Date: " + date);
        DateFormat df = new SimpleDateFormat("HH:mm:ss");
        String hour = df.format(date);
        System.out.println("Time slot:" +hour);
		return hour;
			
	}
	
	public static Date NewDate() 
	{
		// SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 Date date = new Date();
		 return date;
	      
	}

//	public static long Id() {
//		id++;
//		return id;
//	}

}
