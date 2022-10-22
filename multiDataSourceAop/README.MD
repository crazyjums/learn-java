## 用法

在service层调用：

```java
package com.multidatasourcea.service.impl;

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
