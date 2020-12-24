package com.example.utils;

import java.awt.Color;

public class CapacityUtilIntermediate {
	
	

	static Color red = Color.RED;
	static Color green = Color.GREEN;
	static Color yellow= Color.YELLOW;
	public static String FindTypeService(Double k) {
		
		if(k>=0 && k<=20) {
			return "Free";
		}
		else if(k>=21 && k<=40)
		{
			return "Moderate";
		}
		else
		{
			return "Crowded";
		}
	}

	public static String FindTypeDining(Double k)
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

	public static String FindColorDining(Double pplCount) {
		
		if(pplCount>=0 && pplCount<=50) {
			return "#296E01";
		}
		else if(pplCount>=51 && pplCount<=100)
		{
			return "#FFFF00";
		}
		else
		{
			return "#FF0000";
		}
	}

	public static String FindColorSevice(Double pplCount) {
		if(pplCount>=0 && pplCount<=20) {
			return "#296E01";
		}
		else if(pplCount>=21 && pplCount<=40)
		{
			return "#FFFF00";
		}
		else
		{
			return "#FF0000";
		}
	}



}
