package com.multidatasource.controller;

import com.multidatasource.domain.po.Student;
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
        Student student = studentService.getOneByIdFromMaster(1);
        System.out.println(student);
        return "ok--master" + student;
    }

    @RequestMapping("/getStudentFromSlave")
    public String getStudentFromSlave() {
        Student student = studentService.getOneByIdFromSlave(1);
        System.out.println(student);
        return "ok--slave" + student;
    }

}
