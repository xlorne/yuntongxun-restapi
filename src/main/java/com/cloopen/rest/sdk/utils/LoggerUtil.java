package com.cloopen.rest.sdk.utils;

import java.util.Date;


import com.cloopen.rest.sdk.CCPRestSDK;
import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class LoggerUtil {
	private static boolean isLog = true;
	private static Logger logger;
	static {
		if (logger == null) {
			logger = LoggerFactory.getLogger(CCPRestSDK.class);
			
		}
	}
	 
	public static void setLogger(boolean isLog) {
		LoggerUtil.isLog = isLog;
	}
	public static void setLog(Logger logger) {
		LoggerUtil.logger = logger;
	}
	
	public static void setLogLevel(int level) {
		if (logger == null) {
			logger = LoggerFactory.getLogger(CCPRestSDK.class);
		}
		PropertyConfigurator.configure(PropertiesUtil.getPropertie(level));
		 


	}

	public static void debug(Object msg) {
		if (isLog)
			logger.debug(new Date()+" "+msg);
	}

	public static void info(Object msg) {
		if (isLog)
			logger.info(new Date()+" "+msg);
	}



	public static void error(String msg) {
		if (isLog)
			logger.error(msg);
	}

	public static void fatal(String msg) {
		if (isLog)
			logger.error(msg);
	}
	  
}
