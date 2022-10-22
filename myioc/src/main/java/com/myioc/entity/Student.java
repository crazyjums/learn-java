package com.myioc.entity;

/**
 * 学生类
 * 学生类依赖班级对象
 * 并提供 sayHello() 方法
 **/
public class Student {
    private String name;
    private TClass tClass;

    public void sayHello() {
        System.out.println("大家好,我是" + this.name +
                " 我的班级是==>" + tClass.getCname() +
                " 我的老师是" + tClass.getTeacher().getTName());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TClass getTClass() {
        return tClass;
    }

    public void setTClass(TClass tClass) {
        this.tClass = tClass;
    }
}
