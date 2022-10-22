package com.multidatasourceabstractrouting.service.impl;

import com.multidatasourceabstractrouting.config.DynamicDataSource;
import com.multidatasourceabstractrouting.domain.po.Student;
import com.multidatasourceabstractrouting.enums.DBType;
import com.multidatasourceabstractrouting.mapper.StudentMapper;
import com.multidatasourceabstractrouting.service.StudentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class StudentServiceImpl implements StudentService {

    @Resource
    StudentMapper studentMapper;

    @Override
    public Student getOneByIdFromMaster(Integer id) {
        /**
         * DynamicDataSource.currDataSource.set(DBType.MASTER);该语句下面的所有查询都走主库
         */
        DynamicDataSource.targetDataSourceContext.set(DBType.MASTER);
        Student student = studentMapper.selectOneStudentById(id);
        System.out.println("student==" + student);

        /**
         * DynamicDataSource.currDataSource.set(DBType.SLAVE);该语句下面的所有查询都走从库
         */
        DynamicDataSource.targetDataSourceContext.set(DBType.SLAVE);
        Student student2 = studentMapper.selectOneStudentById(id);
        System.out.println("student2==" + student2);

        return studentMapper.selectOneStudentById(id);
    }

    @Override
    public Student getOneByIdFromSlave(Integer id) {
        DynamicDataSource.targetDataSourceContext.set(DBType.SLAVE);
        return studentMapper.selectOneStudentById(id);
    }
}
