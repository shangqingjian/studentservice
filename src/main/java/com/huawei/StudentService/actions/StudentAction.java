package com.huawei.StudentService.actions;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huawei.StudentService.beans.PageList;
import com.huawei.StudentService.beans.QueryCondition;
import com.huawei.StudentService.model.Student;
import com.huawei.StudentService.service.StudentService;
import com.huawei.StudentService.sysinit.ConfigCenter;
import com.huawei.StudentService.sysinit.RedisManager;
import com.huawei.StudentService.utils.JSONUtil;

@Controller
@RequestMapping("student")
public class StudentAction {
	private static final Logger logger = Logger.getLogger(StudentAction.class);
	
	@Autowired
	private StudentService service;
	
	
    @ResponseBody	
    @RequestMapping(value = "/query/by-page", method = RequestMethod.GET)
    public List<Student> getStudentsByPage()
    {
    	logger.info("Query by page info");
    	System.out.println(ConfigCenter.getClassPath());
    	
    	try {
			RedisManager.getJedis().set("student_by_page", JSONUtil.obj2StringPretty(service.getStudentsByPage(0, 5)));
			System.out.println(RedisManager.getJedis().get("student_by_page"));
		} catch (IOException e) {
			logger.error(e);
		}
    	
        return service.getStudentsByPage(0, 5);
    }
    
    @ResponseBody
    @RequestMapping(value = "/query/by-condition", method = RequestMethod.POST)
    public PageList<Student> getStudentsByCondition(@RequestBody QueryCondition qc)
    {
    	logger.info("Query by complex condition");
        return new PageList<>();
    }
    
}
