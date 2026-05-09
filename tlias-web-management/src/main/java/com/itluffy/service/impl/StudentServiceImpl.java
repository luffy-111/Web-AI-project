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

import java.time.LocalDateTime;
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

    /*
     * 根据ID批量删除学生信息
     * */
    @Override
    public void deleteStudentByIds(List<Integer> ids) {
        studentMapper.deleteStudentByIds(ids);
    }

    /*
     * 新增学生信息
     * */
    @Override
    public void addStudent(Student student) {
        //1. 补全基础信息
        student.setViolationCount((short) 0);
        student.setViolationScore((short) 0);
        student.setCreateTime(LocalDateTime.now());
        student.setUpdateTime(LocalDateTime.now());
        //2. 调用Mapper接口
        studentMapper.addStudent(student);
    }

    /*
     * 根据ID查询学生信息
     * */
    @Override
    public Student getStudentById(Integer id) {
        return studentMapper.getStudentById(id);
    }

    /*
     * 修改学生信息
     * */
    @Override
    public void updateStudent(Student student) {
        studentMapper.updateStudent(student);
    }

    /*
     * 修改学生违纪扣分
     * */
    @Override
    public void updateStudentViolationScore(Integer id, Integer score) {
        studentMapper.updateStudentViolationScore(id, score);
    }
}
