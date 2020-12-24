package com.example.utils;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class RandomUtil {
	
	
	
	public static  String RandomDining()
	{    
		//String[] arr={"Dining Area","Service Area"};
	    //Random r=new Random();        
	    //int randomNumber=r.nextInt(arr.length);
	    return "Dining Area";   
	}
	
	public static  String RandomService()
	{    
	    return "Service Area";   
	}
	
	public static  Integer RandomInt(int min, int max)
	{
		    return (int) ((Math.random() * (max - min)) + min);
			
	}
	
	public static Date NewDate() 
	{
		// SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 Date date = new Date();
		 return date;
	      
	}
	public static String admin()
	{
		return "Admin";
	}

}
