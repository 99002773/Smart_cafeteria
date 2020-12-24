package com.example.schedular;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Bean;

public class CafeOccupancyInterSchedular {
	
	
	final static Logger LOGGER = Logger.getLogger(CafeOccupancySchedular.class);

	@Bean
	public static void startInsertingPeopleCount() {
		System.out.println("startt");
		
			new CafeInterSchedular().configureScheduler("update schedule", "VendorAppointmentTrigger",
					"vendorAppointmentGroup", "0/30 0/1 * 1/1 * ? *", CafeSchedularJobIntermediate.class);
			
			
		System.out.println("ENDDDDDDDDDD");
	}

}
