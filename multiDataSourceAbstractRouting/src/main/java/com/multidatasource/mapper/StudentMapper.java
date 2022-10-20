package com.multidatasource.mapper;

import com.multidatasource.domain.po.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface StudentMapper {

    @Select("select * from student where id=#{id}; ")
    Student selectOneStudentById(@Param("id") Integer id);

}
