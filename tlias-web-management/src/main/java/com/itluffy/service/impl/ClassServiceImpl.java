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

import java.time.LocalDateTime;
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

    /*
     * 根据ID删除班级信息
     * */
    @Override
    public void deleteClassById(Integer id) {
        classMapper.deleteClassById(id);
    }

    /*
     * 新增班级
     * */
    @Override
    public void addClass(Clazz clazz) {
        //1. 补全基础属性
        clazz.setCreateTime(LocalDateTime.now());
        clazz.setUpdateTime(LocalDateTime.now());
        //2. 调用Mapper接口方法
        classMapper.addClass(clazz);
    }

    /*
     * 根据ID查询班级信息
     * */
    @Override
    public Clazz getClassById(Integer id) {
        return classMapper.getClassById(id);
    }

    /*
     * 修改班级信息
     * */
    @Override
    public void updateClass(Clazz clazz) {
        //1. 补全基础属性(updateTime)
        clazz.setUpdateTime(LocalDateTime.now());
        //2. 调用Mapper接口方法
        classMapper.updateClass(clazz);
    }
}
