package com.example.utils;
import java.awt.Color;
public class CapacityUtilBase {
	
//	static Color red = Color.RED;
//	static Color green = Color.GREEN;
//	static Color yellow= Color.YELLOW;
public static String FindTypeService(Integer k) {
		
		if(k>=0 && k<=20) {
			return "Free";
		}
		else if(k>=21 && k<=35)
		{
			return "Moderate";
		}
		else
		{
			return "Crowded";
		}
	}

	public static String FindTypeDining(Integer k)
	{
		if(k>=0 && k<=50) {
			return "Free";
		}
		else if(k>=51 && k<=100)
		{
			return "Moderate";
		}
		else
		{
			return "Crowded";
		}
	}

	public static String FindColorDining(Integer k) {
		
		if(k>=0 && k<=50) {
			return "#4B8B3B";
		}
		else if(k>=51 && k<=100)
		{
			return "#FFFF00";
		}
		else
		{
			return "#FF0000";
		}
	}

	public static String FindColorSevice(Integer k) {
		if(k>=0 && k<=20) {
			return "#4B8B3B";
		}
		else if(k>=21 && k<=35)
		{
			return "#FFFF00";
		}
		else
		{
			return "#FF0000";
		}
	}


}
