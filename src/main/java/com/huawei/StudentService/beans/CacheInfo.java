package com.huawei.StudentService.beans;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

/**
 * 缓存操作类
 * 
 * @author Administrator
 *
 */
public class CacheInfo {

	/**
	 * 实现缓存的关键，设置静态常亮的哈希Map，存储缓存到常量池中
	 */
	private static final Map<String, Object> cacheMap = new HashMap<String, Object>();

	/**
	 * 私有构造器，保证单例
	 */
	private CacheInfo() {

	}

	/**
	 * 获取对应key的缓存
	 * 
	 * @param key
	 * @return
	 */
	public static Object getCacheInfo(String key) {
		if(StringUtils.isEmpty(key)) {
			return null;
		}
		if (cacheMap.containsKey(key)) {
			return cacheMap.get(key);
		}
		return null;
	}

	/**
	 * 设置键值对，key和要缓存的对象，如果存在，则覆盖，线程同步
	 * 
	 * @param key
	 * @param obj
	 */
	public synchronized static void setCacheInfo(String key, Object obj) {
		if(StringUtils.isEmpty(key) || null == obj) {
			return;
		}
		if (cacheMap.containsKey(key)) {
			cacheMap.replace(key, obj);
		} else {
			cacheMap.put(key, obj);
		}
	}

	/**
	 * 删除对应key的缓存，如果存在的话，线程同步
	 * 
	 * @param key
	 */
	public synchronized static void delCacheInfo(String key) {
		if(StringUtils.isEmpty(key)) {
			return;
		}
		if (cacheMap.containsKey(key)) {
			cacheMap.remove(key);
		}
	}

}
