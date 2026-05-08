package com.itluffy.controller;

import com.itluffy.pojo.Emp;
import com.itluffy.pojo.EmpQueryParam;
import com.itluffy.pojo.PageResult;
import com.itluffy.pojo.Result;
import com.itluffy.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
 * 员工管理的Controller
 * */
@RequestMapping("/emps")
@Slf4j
@RestController
public class EmpController {

    @Autowired
    private EmpService empService;

    /*
     * 分页查询
     * */
//    @GetMapping
//    public Result page(@RequestParam(defaultValue = "1") Integer page,
//                       @RequestParam(defaultValue = "5") Integer pageSize,
//                       String name, Integer gender,
//                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
//                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end) {
//        log.info("分页查询: {}, {}, {}, {}, {}, {}", page, pageSize, name, gender, begin, end);
//        PageResult<Emp> pageResult = empService.page(page, pageSize, name, gender, begin, end);
//        return Result.success(pageResult);
//    }

    /*
     * 分页查询
     * */
    @GetMapping
    public Result page(EmpQueryParam empQueryParam) {
        log.info("分页查询: {}", empQueryParam);
        PageResult<Emp> pageResult = empService.page(empQueryParam);
        return Result.success(pageResult);
    }

    /**
     * 兼容前端旧版请求：/emps/list
     */
    @GetMapping("/list")
    public Result list(EmpQueryParam empQueryParam) {
        log.info("兼容分页查询: {}", empQueryParam);
        PageResult<Emp> pageResult = empService.page(empQueryParam);
        return Result.success(pageResult);
    }

    /*
     * 新增员工
     * */
    @PostMapping
    public Result save(@RequestBody Emp emp) {
        log.info("新增员工: {}", emp);
        empService.save(emp);
        return Result.success();
    }

    /*
     * 删除员工
     * */
    @DeleteMapping
    public Result delete(@RequestParam List<Integer> ids) {
        log.info("删除员工: {}", ids);
        empService.delete(ids);
        return Result.success();
    }

    /*
     * 查询回显
     * */
    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id) {
        log.info("根据ID查询员工信息: {}", id);
        Emp emp = empService.getInfo(id);
        return Result.success(emp);
    }

    /*
     *修改员工信息
     * */
    @PutMapping
    public Result update(@RequestBody Emp emp) {
        log.info("修改员工: {}",emp);
        empService.update(emp);
        return Result.success();
    }

}
