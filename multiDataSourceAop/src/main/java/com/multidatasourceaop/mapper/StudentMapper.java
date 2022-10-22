package com.multidatasourceaop.mapper;

import com.multidatasourceaop.domain.po.Student;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;


public interface StudentMapper {

    @Select("select * from student where id=#{id}; ")
    Student selectOneStudentById(@Param("id") Integer id);

}
