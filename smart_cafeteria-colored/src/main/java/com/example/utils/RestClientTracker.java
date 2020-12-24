package com.example.utils;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

@Component
public class RestClientTracker implements ApplicationContextAware {

	final static Logger LOGGER = Logger.getLogger(RestClientTracker.class);  
	private static RestClientTracker instance;
	Client client = null;
	private static ApplicationContext contextInstance ;
	
	private RestClientTracker() {
		if (LOGGER.isInfoEnabled()) {
			LOGGER.info("RestClientTracker triggered to create instance.");
		}
	}
	
	public static synchronized RestClientTracker getInstance() {  
        if (instance == null) {  
            instance = new RestClientTracker();  
        }  
        return instance;  
    }
	
	public void setUpRestClient() {
		try {
			ClientConfig config = new DefaultClientConfig();
			client = Client.create(config);
		}
		catch (Exception e) {
			LOGGER.error("Couldn't setup Rest Client in RestClientTracker, error: ",  e);
		}
	}
	
	public Client getRestClient() {
		if (LOGGER.isInfoEnabled()) {
			LOGGER.info("getRestClient is called, Client: " + client.toString());
		}
		return client;
	}
	
	public ApplicationContext getApplicationContext() {
		try {
			if(contextInstance == null){
				//Automatically set using the setApplicationContext method in this class
				// TO BE TESTED
				
				// contextInstance = new ClassPathXmlApplicationContext("applicationContext.xml");
				
			}
		} catch (Exception e) {
			LOGGER.error("Couldn't setup ApplicationContext, error: ",  e);
		}
		return contextInstance;
	}
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		contextInstance = applicationContext;
		
	}

	
	
}
