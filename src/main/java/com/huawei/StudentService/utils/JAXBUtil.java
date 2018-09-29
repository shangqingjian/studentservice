package com.huawei.StudentService.utils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class JAXBUtil {

	private static final String DEFAULT_ENCODE = "UTF-8";

	@SuppressWarnings("unchecked")
	public static <T> T unmarshal(File xmlfile, Class<T> clazz) throws JAXBException {
		if (!xmlfile.exists()) {
			return null;
		}
		JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
		Unmarshaller u = jaxbContext.createUnmarshaller();
		return (T) u.unmarshal(xmlfile);
	}

	@SuppressWarnings("unchecked")
	public static <T> T unmarshal(String content, Class<T> clazz) throws JAXBException {
		if (null == content || content.isEmpty()) {
			return null;
		}
		InputStream is = null;
		try {
			is = new ByteArrayInputStream(content.getBytes(DEFAULT_ENCODE));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
				is = null;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
		Unmarshaller u = jaxbContext.createUnmarshaller();
		return (T) u.unmarshal(is);
	}

	public static <T> String marshal(T t) throws JAXBException {
		Class<? extends Object> clazz = t.getClass();
		JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
		Marshaller marshaller = jaxbContext.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		marshaller.setProperty(Marshaller.JAXB_ENCODING, DEFAULT_ENCODE);
		marshaller.setProperty(Marshaller.JAXB_FRAGMENT, false);
		StringWriter writer = new StringWriter();
		marshaller.marshal(t, writer);
		return writer.toString();
	}

}
