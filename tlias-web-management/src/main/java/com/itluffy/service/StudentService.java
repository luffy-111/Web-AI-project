package com.itluffy.service;

import com.itluffy.pojo.PageResult;
import com.itluffy.pojo.Student;
import com.itluffy.pojo.StudentQueryParam;

import java.util.List;

public interface StudentService {

    /*
     * 分页查询学生信息
     * */
    PageResult<Student> pageStudent(StudentQueryParam studentQueryParam);

    /*
     * 根据ID批量删除学生信息
     * */
    void deleteStudentByIds(List<Integer> ids);

    /*
     * 新增学生信息
     * */
    void addStudent(Student student);

    /*
     * 根据ID查询学生信息
     * */
    Student getStudentById(Integer id);

    /*
     * 修改学生信息
     * */
    void updateStudent(Student student);

    /*
     * 修改学生违纪扣分
     * */
    void updateStudentViolationScore(Integer id, Integer score);
}
