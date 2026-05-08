package com.itluffy.service;

import com.itluffy.pojo.*;

public interface ClassService {

    /*
     * 分页查询班级信息
     * */
    PageResult<Clazz> pageClass(ClassQueryParam classQueryParam);

    /*
     * 根据ID删除班级信息
     * */
    void deleteClassById(Integer id);

    /*
     * 新增班级
     * */
    void addClass(Clazz clazz);

    /*
     * 根据ID查询班级信息
     * */
    Clazz getClassById(Integer id);

    /*
     * 修改班级信息
     * */
    void updateClass(Clazz clazz);

}
