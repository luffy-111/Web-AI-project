package com.itluffy.service;

import com.itluffy.pojo.PageResult;
import com.itluffy.pojo.Student;
import com.itluffy.pojo.StudentQueryParam;

public interface StudentService {

    /*
     * 分页查询学生信息
     * */
    PageResult<Student> pageStudent(StudentQueryParam studentQueryParam);
}
