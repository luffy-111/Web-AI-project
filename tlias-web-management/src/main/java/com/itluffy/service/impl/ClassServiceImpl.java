package com.itluffy.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itluffy.mapper.ClassMapper;
import com.itluffy.pojo.ClassQueryParam;
import com.itluffy.pojo.Clazz;
import com.itluffy.pojo.PageResult;
import com.itluffy.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassServiceImpl implements ClassService {

    @Autowired
    private ClassMapper classMapper;

    /*
     * 分页查询班级信息
     * */
    @Override
    public PageResult<Clazz> pageClass(ClassQueryParam classQueryParam) {
        //1. 设置分页参数(PageHelper)
        PageHelper.startPage(classQueryParam.getPage(), classQueryParam.getPageSize());
        //2. 执行查询
        List<Clazz> clazzList = classMapper.classList(classQueryParam);
        //3. 解析查询结果, 并封装
        Page<Clazz> p = (Page<Clazz>) clazzList;
        return new PageResult<>(p.getTotal(), p.getResult());
    }
}
