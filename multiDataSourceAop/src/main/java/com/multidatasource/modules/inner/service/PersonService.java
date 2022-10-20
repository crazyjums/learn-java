package com.multidatasource.modules.inner.service;

import com.multidatasource.modules.inner.domain.po.Person;

import java.util.List;

public interface PersonService {

    List<Person> getAllFromMaster();

    List<Person> getAllFromSlave();

}
