# Java学习

1. [springboot+mybatis配置多数据源](https://github.com/crazyjums/learn-java/tree/master/multiDataSource)
2. [springboot+mybatis配置多数据源，用AbstractRoutingDataSource显示控制](https://github.com/crazyjums/learn-java/tree/master/multiDataSourceAbstractRouting)
3. [springboot+mybatis配置多数据源，用AOP实现注解显示切换数据源](https://github.com/crazyjums/learn-java/tree/master/multiDataSourceAop)

## 第1和2种方式的用法

在service层调用：

```java
package com.multidatasource.service.impl;

import com.multidatasource.config.DynamicDataSource;
import com.multidatasource.domain.po.Student;
import com.multidatasource.enums.DBType;
import com.multidatasource.mapper.StudentMapper;
import com.multidatasource.service.StudentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class StudentServiceImpl implements StudentService {

    @Resource
    StudentMapper studentMapper;

    @Override
    public Student getOneByIdFromMaster(Integer id) {
        /**
         * DynamicDataSource.currDataSource.set(DBType.MASTER);该语句下面的所有查询都走主库
         */
        DynamicDataSource.targetDataSourceContext.set(DBType.MASTER);
        Student student = studentMapper.selectOneStudentById(id);
        System.out.println("student==" + student);

        /**
         * DynamicDataSource.currDataSource.set(DBType.SLAVE);该语句下面的所有查询都走从库
         */
        DynamicDataSource.targetDataSourceContext.set(DBType.SLAVE);
        Student student2 = studentMapper.selectOneStudentById(id);
        System.out.println("student2==" + student2);

        return studentMapper.selectOneStudentById(id);
    }

    @Override
    public Student getOneByIdFromSlave(Integer id) {
        DynamicDataSource.targetDataSourceContext.set(DBType.SLAVE);
        return studentMapper.selectOneStudentById(id);
    }
}
```

## 第3种方式的用法

在service层调用：

```Java
package com.multidatasource.service.impl;

import com.multidatasource.annotaion.TargetDataSource;
import com.multidatasource.config.DynamicDataSource;
import com.multidatasource.domain.po.Student;
import com.multidatasource.enums.DBType;
import com.multidatasource.mapper.StudentMapper;
import com.multidatasource.service.StudentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class StudentServiceImpl implements StudentService {

    @Resource
    StudentMapper studentMapper;

    @TargetDataSource(targetDB = DBType.MASTER)
    @Override
    public Student getOneByIdFromMaster(Integer id) {
        try {
            /**
             * DynamicDataSource.targetDataSourceContext.set(DBType.SLAVE);优先级高于@TargetDataSource(targetDB = DBType.MASTER)注解
             */
            DynamicDataSource.targetDataSourceContext.set(DBType.SLAVE);
            return studentMapper.selectOneStudentById(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @TargetDataSource(targetDB = DBType.SLAVE)
    @Override
    public Student getOneByIdFromSlave(Integer id) {
        try {
//            DynamicDataSource.currDataSourceContext.set(DBType.SLAVE);
            return studentMapper.selectOneStudentById(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
```

