package com.huawei.StudentService.sysinit;

import java.io.File;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

@WebListener
public class SystemInitialize implements ServletContextListener {
	
	private static final Logger logger = Logger.getLogger(ConfigCenter.class);

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		logger.info("Stop The System Finished.");
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		logger.info("Begin to Start The System...");
		String contextPath = event.getServletContext().getRealPath("/");
		if(StringUtils.isEmpty(contextPath) || !new File(contextPath).exists())
		{
			logger.error("Can not get the servlet context real path.");
			return;
		}
		ConfigCenter.initSystemConf(contextPath);
		logger.info("Initialize system config finished...");
	}
}
