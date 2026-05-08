package com.itluffy.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itluffy.mapper.StudentMapper;
import com.itluffy.pojo.Emp;
import com.itluffy.pojo.PageResult;
import com.itluffy.pojo.Student;
import com.itluffy.pojo.StudentQueryParam;
import com.itluffy.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    /*
     * 分页查询学生信息
     * */
    @Override
    public PageResult<Student> pageStudent(StudentQueryParam studentQueryParam) {
        //1. 设置分页参数(PageHelper)
        PageHelper.startPage(studentQueryParam.getPage(), studentQueryParam.getPageSize());
        //2. 执行查询
        List<Student> studentList = studentMapper.studentList(studentQueryParam);
        //3. 解析查询结果, 并封装
        Page<Student> p = (Page<Student>) studentList;
        return new PageResult<Student>(p.getTotal(), p.getResult());
    }
}
