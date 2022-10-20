package com.multidatasource.controller;

import com.multidatasource.config.DynamicDataSource;
import com.multidatasource.domain.po.Student;
import com.multidatasource.enums.DBType;
import com.multidatasource.service.StudentService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/test")
public class TestController {

    @Resource
    StudentService studentService;


    @RequestMapping("/getStudentFromMaster")
    public String getStudentFromMaster() {
        DynamicDataSource.currDataSourceContext.set(DBType.MASTER);
        Student student = studentService.getOneById(1);
        System.out.println(student);
        return "ok--master" + student;
    }

    @RequestMapping("/getStudentFromSlave")
    public String getStudentFromSlave() {
        DynamicDataSource.currDataSourceContext.set(DBType.SLAVE);
        Student student = studentService.getOneById(1);
        System.out.println(student);
        return "ok--slave" + student;
    }

}
