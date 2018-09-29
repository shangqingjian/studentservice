package com.huawei.StudentService.service;

import java.util.List;

import com.huawei.StudentService.model.Student;

public interface StudentService {

	public List<Student> getStudentsByPage(int startIndex, int pageSize);

}
