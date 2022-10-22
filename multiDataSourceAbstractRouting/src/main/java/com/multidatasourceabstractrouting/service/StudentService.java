package com.multidatasourceabstractrouting.service;


import com.multidatasourceabstractrouting.domain.po.Student;

public interface StudentService {


    Student getOneByIdFromMaster(Integer id);

    Student getOneByIdFromSlave(Integer id);

}
