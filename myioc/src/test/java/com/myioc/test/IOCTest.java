package com.myioc.test;

import com.myioc.core.BeanFactory;
import com.myioc.core.XmlBeanFactory;
import com.myioc.entity.Student;

public class IOCTest {

    public static void main(String[] args) {
        // 创建IOC容器
        BeanFactory beanFactory = new XmlBeanFactory("applicationContext.xml");
        // 通过容器获取对象
        Student student = (Student)beanFactory.getBean("student");
        // 调用对象sayHello方法
        student.sayHello();
    }
}
