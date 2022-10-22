package com.multidatasourceaop.service.impl;

import com.multidatasourceaop.annotaion.TargetDataSource;
import com.multidatasourceaop.config.DynamicDataSource;
import com.multidatasourceaop.domain.po.Student;
import com.multidatasourceaop.enums.DBType;
import com.multidatasourceaop.mapper.StudentMapper;
import com.multidatasourceaop.service.StudentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class StudentServiceImpl implements StudentService {

    @Resource
    StudentMapper studentMapper;

    @TargetDataSource(targetDB = DBType.MASTER)
    @Override
    public Student getOneByIdFromMaster(Integer id) {
        try {
            /**
             * DynamicDataSource.targetDataSourceContext.set(DBType.SLAVE);优先级高于@TargetDataSource(targetDB = DBType.MASTER)注解
             */
            DynamicDataSource.targetDataSourceContext.set(DBType.SLAVE);
            return studentMapper.selectOneStudentById(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @TargetDataSource(targetDB = DBType.SLAVE)
    @Override
    public Student getOneByIdFromSlave(Integer id) {
        try {
//            DynamicDataSource.currDataSourceContext.set(DBType.SLAVE);
            return studentMapper.selectOneStudentById(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
