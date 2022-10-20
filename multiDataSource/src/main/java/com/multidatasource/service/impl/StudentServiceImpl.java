package com.multidatasource.service.impl;

import com.multidatasource.domain.po.Student;
import com.multidatasource.mapper.StudentMapper;
import com.multidatasource.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentMapper studentMapper;

    @Override
    public Student getOneById(Integer id) {
        try {
            return studentMapper.selectOneStudentById(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
