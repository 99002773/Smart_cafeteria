package com.example.schedular;

import org.apache.log4j.Logger;
import org.quartz.CronScheduleBuilder;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.context.annotation.Bean;

public class CafeInterSchedular {
	
	final static Logger LOGGER = Logger.getLogger(CafeSchedular.class);
	
	@Bean
	public void configureScheduler(String jobName, String triggerName, String groupName, String cronExpression, Class<? extends Job> jobClass) {
		System.out.println("schedula");
		LOGGER.info("schedular");
			if (LOGGER.isInfoEnabled()) {
				LOGGER.info("In configureScheduler(). Parameters -" +
						" jobName: " + jobName + 
						" triggerName: " + triggerName +
						" groupName" + groupName +
						" cronExpression" + cronExpression +
						" jobClass Name" + jobClass.getName()); 
			}
			
			System.out.println(jobClass.getName());
			JobDetail job = JobBuilder.newJob(jobClass)
	      			.withIdentity(jobName, groupName) 
	      			.build();
			
			

			Trigger trigger = TriggerBuilder.newTrigger()
					.withIdentity(triggerName, groupName)
					.withSchedule(CronScheduleBuilder.cronSchedule(cronExpression)
					.withMisfireHandlingInstructionFireAndProceed())
					.build();

			try {
				Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
				scheduler.scheduleJob(job, trigger);
				//LOGGER.info("HII");
				scheduler.start();
				//LOGGER.info("HII");
			}
			catch (SchedulerException e) {
				LOGGER.error("Exception in creating Scheduler in configureScheduler(). Exception: " + e);
			}
			if (LOGGER.isInfoEnabled()) {
				LOGGER.info("Exiting configureScheduler()");
			}
			System.out.println("END configure");
		}
		

}
