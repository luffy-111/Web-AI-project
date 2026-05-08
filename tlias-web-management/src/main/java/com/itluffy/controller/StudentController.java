package com.itluffy.controller;

import com.itluffy.pojo.*;
import com.itluffy.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    /*
     * 分页查询学生信息
     * */
    @GetMapping
    public Result pageStudent(StudentQueryParam studentQueryParam) {
        log.info("分页查询学生: {}", studentQueryParam);
        PageResult<Student> pageResult = studentService.pageStudent(studentQueryParam);
        return Result.success(pageResult);
    }
}
