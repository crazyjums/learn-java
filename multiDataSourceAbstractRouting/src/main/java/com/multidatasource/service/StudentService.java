package com.multidatasource.service;


import com.multidatasource.domain.po.Student;

public interface StudentService {


    Student getOneByIdFromMaster(Integer id);

    Student getOneByIdFromSlave(Integer id);

}
