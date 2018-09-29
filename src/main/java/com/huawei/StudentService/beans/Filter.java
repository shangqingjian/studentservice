package com.huawei.StudentService.beans;

public class Filter {

	//列字段对应的属性名
	private String key;
	//过滤条件的值
	private String value;
	//过滤条件的方式，支持 >=、<=、>、<、=、like
	private String opt;
	
	public Filter() {
		super();
	}

	public Filter(String key, String value, String opt) {
		super();
		this.key = key;
		this.value = value;
		this.opt = opt;
	}


	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getOpt() {
		return opt;
	}

	public void setOpt(String opt) {
		this.opt = opt;
	}
}
