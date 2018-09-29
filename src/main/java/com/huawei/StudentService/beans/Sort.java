package com.huawei.StudentService.beans;

public class Sort {

	private String fieldName;

	private String description;

	public Sort(String fieldName, String description) {
		super();
		this.fieldName = fieldName;
		this.description = description;
	}

	public Sort() {
		super();
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
