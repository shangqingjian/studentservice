package com.huawei.StudentService.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huawei.StudentService.mapper.StudentMapper;
import com.huawei.StudentService.model.Student;
import com.huawei.StudentService.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentMapper mapper;

	@Override
	public List<Student> getStudentsByPage(int startIndex, int pageSize) {
		return mapper.getInfosByPage(startIndex, pageSize);
	}

}
