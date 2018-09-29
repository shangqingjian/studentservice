package com.huawei.StudentService.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

public class DateUtil {

	public final static Logger logger = Logger.getLogger(DateUtil.class);

	public final static String DATE_FORMAT = "yyyy-MM-dd";

	public final static String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

	public static String getCurrentDate() {
		String datestr = null;
		SimpleDateFormat df = new SimpleDateFormat(DATE_FORMAT);
		datestr = df.format(new Date());
		return datestr;
	}

	public static String getCurrentDateTime() {
		String datestr = null;
		SimpleDateFormat df = new SimpleDateFormat(DATE_TIME_FORMAT);
		datestr = df.format(new Date());
		return datestr;
	}

	public static String getCurrentDateTime(String Dateformat) {
		String datestr = null;
		SimpleDateFormat df = new SimpleDateFormat(Dateformat);
		datestr = df.format(new Date());
		return datestr;
	}

	public static String dateToDateTime(Date date) {
		String datestr = null;
		SimpleDateFormat df = new SimpleDateFormat(DATE_TIME_FORMAT);
		datestr = df.format(date);
		return datestr;
	}

	public static Date stringToDate(String datestr) {
		if (datestr == null || datestr.equals("")) {
			logger.error("The string used to convert to date cannot be empty.");
		}
		SimpleDateFormat df = new SimpleDateFormat(DATE_FORMAT);
		Date date = null;
		try {
			date = df.parse(datestr);
		} catch (ParseException e) {
			logger.error("The string used to convert to date has wrong formatting.");
		}
		return date;
	}

	public static Date stringToDate(String datestr, String dateformat) {
		Date date = null;
		SimpleDateFormat df = new SimpleDateFormat(dateformat);
		try {
			date = df.parse(datestr);
		} catch (ParseException e) {
			logger.error("Can not convert the string to date.", e);
		}
		return date;
	}

	public static String dateToString(Date date) {
		String datestr = null;
		SimpleDateFormat df = new SimpleDateFormat(DATE_FORMAT);
		datestr = df.format(date);
		return datestr;
	}

	public static String dateToString(Date date, String dateformat) {
		String datestr = null;
		SimpleDateFormat df = new SimpleDateFormat(dateformat);
		datestr = df.format(date);
		return datestr;
	}

}
