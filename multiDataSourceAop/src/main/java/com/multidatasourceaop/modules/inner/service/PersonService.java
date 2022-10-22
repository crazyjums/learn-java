package com.multidatasourceaop.modules.inner.service;

import com.multidatasourceaop.modules.inner.domain.po.Person;

import java.util.List;

public interface PersonService {

    List<Person> getAllFromMaster();

    List<Person> getAllFromSlave();

}
