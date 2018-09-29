package com.huawei.StudentService.sysinit;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

public class ConfigCenter {
	
	private static final Logger logger = Logger.getLogger(ConfigCenter.class);
	
	private static String BASE_APP_PATH = null;
	
	private static final String CLASSPATH = "WEB-INF" + File.separator + "classes" + File.separator;
	
	private static final String CONFIGPATH = "config";

	private static final Map<String, Object> configMap = new HashMap<String, Object>();
	
	private static final String CONFIG_FILE_SUFFIX = ".properties";

	private ConfigCenter() {

	}
	
	public static Object getCfgByKey(String key) {
		if(StringUtils.isEmpty(key)) {
			return null;
		}
		if(!configMap.containsKey(key)) {
			return null;
		}
		return configMap.get(key);
	}
	
	public static void putCfgToConfigMap(String key, Object value) {
		if(StringUtils.isEmpty(key) || null == value) {
			return;
		}
		configMap.put(key, value);
	}
	
	public static String getBaseAppPath() {
		return null == BASE_APP_PATH ? "" : BASE_APP_PATH;
	}
	
	public static String getClassPath() {
		if(StringUtils.isEmpty(BASE_APP_PATH)) {
			return "";
		}
		return BASE_APP_PATH + CLASSPATH;
	}
	
	public static String getConfigPath() {
		if(StringUtils.isEmpty(BASE_APP_PATH)) {
			return "";
		}
		return BASE_APP_PATH + CLASSPATH + CONFIGPATH;
	}
	
	public static Object getCfgByKeyWithDefault(String key, Object defaultValue) {
		if(StringUtils.isEmpty(key)) {
			return defaultValue;
		}
		if(!configMap.containsKey(key)) {
			return defaultValue;
		}
		return configMap.get(key);
	}

	public static void initSystemConf(String baseAppPath) {
		if(StringUtils.isEmpty(baseAppPath)) {
			return;
		}
		BASE_APP_PATH = baseAppPath;
		String configPath = BASE_APP_PATH + CLASSPATH + CONFIGPATH;
		File cfgPath = new File(configPath);
		if(null == cfgPath || !cfgPath.isDirectory())
		{
			logger.error("The config file path is invalid. Path: " + configPath);
			return;
		}
		File[] fileList = cfgPath.listFiles();
		if(null == fileList || fileList.length == 0) {
			logger.error("Can not find any config file. Path: " + configPath);
			return;
		}
		for(File file : fileList) {
			Map<String, Object> cfg = readPropFile(file);
			if(null == cfg || cfg.isEmpty()) {
				continue;
			}
			configMap.putAll(cfg);
		}
	}
	
	public static Map<String, Object> readPropFile(File file) {
		if(null == file || file.isDirectory()) {
			return null;
		}
		String fileName = file.getName();
		if(!fileName.endsWith(CONFIG_FILE_SUFFIX)) {
			return null;
		}
		Properties props = new Properties();
		try {
			InputStream in = new FileInputStream(file);
			props.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
		Map<String, Object> tempMap = new HashMap<String, Object>();
		if(null == props || props.isEmpty())
		{
			return null;
		}
		for(Entry<Object, Object> entry : props.entrySet()) {
			tempMap.put(String.valueOf(entry.getKey()) , entry.getValue());
		}
		return tempMap;		
	}
	
}
