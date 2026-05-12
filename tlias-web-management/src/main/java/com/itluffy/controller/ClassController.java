package com.itluffy.controller;

import com.itluffy.anno.Log;
import com.itluffy.pojo.*;
import com.itluffy.service.ClassService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/clazzs")
public class ClassController {

    @Autowired
    private ClassService classService;

    /*
     * 分页查询班级信息
     * */
    @GetMapping
    public Result pageClass(ClassQueryParam classQueryParam) {
        log.info("分页查询班级: {}", classQueryParam);
        PageResult<Clazz> pageResult = classService.pageClass(classQueryParam);
        return Result.success(pageResult);
    }

    /*
     * 根据ID删除班级信息
     * */
    @Log
    @DeleteMapping("/{id}")
    public Result deleteClassById(@PathVariable Integer id) {
        log.info("根据ID删除班级: {}", id);
        classService.deleteClassById(id);
        return Result.success();
    }

    /*
     * 新增班级
     * */
    @Log
    @PostMapping
    public Result addClass(@RequestBody Clazz clazz) {
        log.info("新增班级: {}", clazz);
        classService.addClass(clazz);
        return Result.success();
    }

    /*
     * 根据ID查询班级
     * */
    @GetMapping("/{id}")
    public Result getClassById(@PathVariable Integer id) {
        log.info("根据ID查询班级: {}", id);
        Clazz clazz = classService.getClassById(id);
        return Result.success(clazz);
    }

    /*
     * 修改班级
     * */
    @Log
    @PutMapping
    public Result updateClass(@RequestBody Clazz clazz) {
        log.info("修改班级: {}", clazz);
        classService.updateClass(clazz);
        return Result.success();
    }

    /*
     * 兼容前端旧版请求：/clazzs/list
     * 分页查询班级信息
     * */
    @GetMapping("/list")
    public Result listClass(ClassQueryParam classQueryParam) {
        log.info("兼容分页查询: {}", classQueryParam);
        PageResult<Clazz> pageResult = classService.pageClass(classQueryParam);
        return Result.success(pageResult);
    }
}
