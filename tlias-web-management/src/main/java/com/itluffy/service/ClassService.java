package com.itluffy.service;

import com.itluffy.pojo.*;

public interface ClassService {

    /*
     * 分页查询班级信息
     * */
    PageResult<Clazz> pageClass(ClassQueryParam classQueryParam);
}
