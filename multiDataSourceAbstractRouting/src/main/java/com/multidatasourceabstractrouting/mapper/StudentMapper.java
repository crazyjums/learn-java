package com.multidatasourceabstractrouting.mapper;

import com.multidatasourceabstractrouting.domain.po.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface StudentMapper {

    @Select("select * from student where id=#{id}; ")
    Student selectOneStudentById(@Param("id") Integer id);

}
