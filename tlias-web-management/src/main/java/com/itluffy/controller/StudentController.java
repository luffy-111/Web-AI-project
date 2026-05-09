package com.itluffy.controller;

import com.itluffy.pojo.*;
import com.itluffy.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    /*
     * 根据ID批量删除学生信息
     * */
    @DeleteMapping("/{ids}")
    public Result deleteStudentByIds(@PathVariable List<Integer> ids) {
        log.info("根据ID批量删除学生: {}", ids);
        studentService.deleteStudentByIds(ids);
        return Result.success();
    }

    /*
     * 新增学生信息
     * */
    @PostMapping
    public Result addStudent(@RequestBody Student student) {
        log.info("新增学生: {}", student);
        studentService.addStudent(student);
        return Result.success();
    }

    /*
     * 根据ID查询学生信息
     * */
    @GetMapping("/{id}")
    public Result getStudentById(@PathVariable Integer id) {
        log.info("根据ID查询学生: {}", id);
        Student student = studentService.getStudentById(id);
        return Result.success(student);
    }

    /*
     * 修改学生信息
     * */
    @PutMapping
    public Result updateStudent(@RequestBody Student student) {
        log.info("修改学生: {}", student);
        studentService.updateStudent(student);
        return Result.success();
    }

    /*
     * 根据ID修改学生的违纪扣分
     * */
    @PutMapping("/violation/{id}/{score}")
    public Result updateStudentViolationScore(@PathVariable Integer id, @PathVariable Integer score) {
        log.info("根据ID修改学生违纪扣分: {}", id);
        studentService.updateStudentViolationScore(id, score);
        return Result.success();
    }
}
