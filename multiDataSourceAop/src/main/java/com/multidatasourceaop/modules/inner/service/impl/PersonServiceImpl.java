package com.multidatasourceaop.modules.inner.service.impl;

import com.multidatasourceaop.annotaion.TargetDataSource;
import com.multidatasourceaop.enums.DBType;
import com.multidatasourceaop.modules.inner.domain.po.Person;
import com.multidatasourceaop.modules.inner.mapper.PersonMapper;
import com.multidatasourceaop.modules.inner.service.PersonService;
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
