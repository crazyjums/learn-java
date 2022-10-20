package com.multidatasource.modules.inner.service.impl;

import com.multidatasource.annotaion.TargetDataSource;
import com.multidatasource.enums.DBType;
import com.multidatasource.modules.inner.domain.po.Person;
import com.multidatasource.modules.inner.mapper.PersonMapper;
import com.multidatasource.modules.inner.service.PersonService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

    @Resource
    PersonMapper personMapper;

    @TargetDataSource(targetDB = DBType.SLAVE)
    @Override
    public List<Person> getAllFromSlave() {
        List<Person> list = personMapper.getAll();
        return list;
    }

    @TargetDataSource(targetDB = DBType.MASTER)
    @Override
    public List<Person> getAllFromMaster() {
        List<Person> list = personMapper.getAll();
        return list;
    }
}
