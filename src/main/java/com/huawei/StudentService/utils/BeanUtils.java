package com.huawei.StudentService.utils;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Set;

public class BeanUtils {

	private BeanUtils() {

	}

	/**
	 * 获取对象的指定属性的值
	 * 
	 * @param t
	 * @param fieldName
	 * @return
	 * @throws Exception
	 */
	public static <T> Object getProperty(T t, String fieldName) throws Exception {
		Class<? extends Object> ct = t.getClass();
		Field field = null;
		field = ct.getDeclaredField(fieldName);
		Object obj = null;
		if (field != null) {
			field.setAccessible(true);
			obj = field.get(fieldName);
		}
		return obj;
	}

	/**
	 * 给对象的指定属性赋值
	 * 
	 * @param t
	 * @param fieldName
	 * @param value
	 * @throws Exception
	 */
	public static <T> void setProperty(T t, String fieldName, Object value) throws Exception {
		Class<? extends Object> ct = t.getClass();
		Field field = null;
		field = ct.getDeclaredField(fieldName);
		if (field != null) {
			field.setAccessible(true);
			field.set(t, value);
		}
	}

	/**
	 * 批量的给对象的指定属性赋值
	 * 
	 * @param t
	 * @param values
	 * @throws Exception
	 */
	public static <T> void setProperty(T t, Map<String, Object> values) throws Exception {
		Class<? extends Object> ct = t.getClass();
		Set<String> keys = values.keySet();
		for (String key : keys) {
			Field field = null;
			field = ct.getDeclaredField(key);
			if (field != null) {
				field.setAccessible(true);
				field.set(t, values.get(key));
			}
		}
	}

	/**
	 * 根据Map<String, Object>生成对象
	 * 
	 * @param values
	 * @param ct
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static <T> T mapToBean(Map<String, Object> values, Class<?> ct) throws Exception {
		Object obj = null;
		obj = ct.newInstance();
		if (null == obj) {
			return null;
		}
		Set<String> keys = values.keySet();
		for (String key : keys) {
			Field field = null;
			field = ct.getDeclaredField(key);

			if (field != null) {
				field.setAccessible(true);
				field.set(obj, values.get(key));
			}
		}
		return (T) obj;
	}

}
