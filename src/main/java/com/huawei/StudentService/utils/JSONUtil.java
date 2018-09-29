package com.huawei.StudentService.utils;

import java.io.IOException;
import java.text.SimpleDateFormat;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.codehaus.jackson.type.JavaType;

public class JSONUtil {

	/**
	 * Jackson工具类
	 *
	 * @author suddev
	 * @create 2017-11-25 2:18 PM
	 **/

	private static ObjectMapper objectMapper = new ObjectMapper();
	
	private final static String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

	static {
		// 对象字段全部列入
		objectMapper.setSerializationInclusion(Inclusion.NON_DEFAULT);
		// 取消默认转换timestamps形式
		objectMapper.configure(SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS, false);
		// 忽略空bean转json的错误
		objectMapper.configure(SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS, false);
		// 统一日期格式yyyy-MM-dd HH:mm:ss
		objectMapper.setDateFormat(new SimpleDateFormat(DATE_FORMAT));
		// 忽略在json字符串中存在,但是在java对象中不存在对应属性的情况
		objectMapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}

	/**
	 * Object转json字符串
	 * 
	 * @param obj
	 * @param <T>
	 * @return
	 * @throws IOException
	 * @throws JsonMappingException
	 * @throws JsonGenerationException
	 */
	public static <T> String obj2String(T obj) throws IOException {
		if (obj == null) {
			return null;
		}
		String res = obj instanceof String ? (String) obj : objectMapper.writeValueAsString(obj);
		return StringUtils.isEmpty(res) ? null : res;

	}

	/**
	 * Object转json字符串并格式化美化
	 * 
	 * @param obj
	 * @param <T>
	 * @return
	 * @throws IOException
	 * @throws JsonMappingException
	 * @throws JsonGenerationException
	 */
	public static <T> String obj2StringPretty(T obj) throws IOException {
		if (obj == null) {
			return null;
		}
		String res = obj instanceof String ? (String) obj
				: objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
		return StringUtils.isEmpty(res) ? null : res;
	}

	/**
	 * string转object
	 * 
	 * @param str
	 * @param clazz
	 * @param <T>
	 * @return
	 * @throws IOException
	 * @throws JsonMappingException
	 * @throws JsonParseException
	 */
	@SuppressWarnings("unchecked")
	public static <T> T string2Obj(String str, Class<T> clazz) throws IOException {
		if (StringUtils.isEmpty(str) || clazz == null) {
			return null;
		}
		return clazz.equals(String.class) ? (T) str : objectMapper.readValue(str, clazz);
	}

	/**
	 * string转object 用于转为集合对象
	 * 
	 * @param str
	 * @param collectionClass
	 * @param elementClasses
	 * @param <T>
	 * @return
	 * @throws IOException
	 * @throws JsonMappingException
	 * @throws JsonParseException
	 */
	public static <T> T string2Obj(String str, Class<?> collectionClass, Class<?>... elementClasses)
			throws IOException {
		JavaType javaType = objectMapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
		return objectMapper.readValue(str, javaType);
	}
}
