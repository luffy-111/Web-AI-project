package com.itluffy.controller;

import com.itluffy.pojo.*;
import com.itluffy.service.ClassService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
