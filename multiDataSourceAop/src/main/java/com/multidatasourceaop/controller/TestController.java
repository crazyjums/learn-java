package com.multidatasourceaop.controller;

import com.multidatasourceaop.domain.po.Student;
import com.multidatasourceaop.modules.inner.domain.po.Person;
import com.multidatasourceaop.modules.inner.service.PersonService;
import com.multidatasourceaop.modules.workbench.domain.po.VoiceData;
import com.multidatasourceaop.modules.workbench.service.VoiceDataService;
import com.multidatasourceaop.service.StudentService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {

    @Resource
    StudentService studentService;

    @Resource
    PersonService personService;

    @Resource
    VoiceDataService voiceDataService;


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

    @RequestMapping("/getPersonFromMaster")
    public List<Person> getPersonFromMaster() {
        List<Person> list = personService.getAllFromMaster();
        System.out.println(list);
        return list;
    }

    @RequestMapping("/getPersonFromSlave")
    public List<Person> getPersonFromSlave() {
        List<Person> list = personService.getAllFromSlave();
        System.out.println(list);
        return list;
    }


    @RequestMapping("/getVoiceDataFromMaster")
    public List<VoiceData> getVoiceDataFromMaster() {
        List<VoiceData> list = voiceDataService.getAllFromMaster();
        System.out.println(list);
        return list;
    }

    @RequestMapping("/getVoiceDataFromSlave")
    public List<VoiceData> getVoiceDataFromSlave() {
        List<VoiceData> list = voiceDataService.getAllFromSlave();
        System.out.println(list);
        return list;
    }

}
