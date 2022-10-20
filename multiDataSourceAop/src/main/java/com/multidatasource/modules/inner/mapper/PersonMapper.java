package com.multidatasource.modules.inner.mapper;

import com.multidatasource.modules.inner.domain.po.Person;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface PersonMapper {

    @Select("select * from person; ")
    List<Person> getAll();

}
