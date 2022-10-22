package com.multidatasourceaop.service;


import com.multidatasourceaop.domain.po.Student;

public interface StudentService {


    Student getOneByIdFromMaster(Integer id);

    Student getOneByIdFromSlave(Integer id);

}
