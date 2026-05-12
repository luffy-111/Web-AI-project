package com.itluffy.controller;

import com.itluffy.anno.Log;
import com.itluffy.pojo.Dept;
import com.itluffy.pojo.Result;
import com.itluffy.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/depts")  // 提取公共部分
@RestController
public class DeptController {

    //固定格式, @Slf4j代替
    //private static final Logger log = LoggerFactory.getLogger(DeptController.class);

    @Autowired
    private DeptService deptService;

    // @RequestMapping(value = "/depts", method = RequestMethod.GET)  // method: 指定请求方式
    @GetMapping
    public Result list() {
//        System.out.println("查询所有的部门数据");
        log.info("查询所有的部门数据");
        List<Dept> deptList = deptService.findAll();
        return Result.success(deptList);
    }

    /*
     * 删除部门  方式一: HttpServletRequest 获取请求参数
     * */
//    @DeleteMapping("/depts")
//    public Result delete(HttpServletRequest request) {
//        String idStr = request.getParameter("id");
//        int id = Integer.parseInt(idStr);
//        System.out.println("根据ID删除部门: " + id);
//        return Result.success();
//    }

    /*
     * 删除部门  方式三: 省略@RequestParam(前端传递的请求参数名与服务器方法形参名一致)
     * */
    @Log
    @DeleteMapping
    public Result delete(Integer id) {
//        System.out.println("根据ID删除部门: " + id);
        log.info("根据ID删除部门: {}", id);
        deptService.deleteById(id);
        return Result.success();
    }

    /*
     * 新增部门
     * */
    @Log
    @PostMapping
    public Result add(@RequestBody Dept dept) {
//        System.out.println("新增部门");
        log.info("新增部门");
        deptService.add(dept);
        return Result.success();
    }

    /*
     * 根据ID查询部门
     *     方法形参名与路径参数参数名相同的话 PathVariable("id"), ()可以省略
     * */
    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id) {
//        System.out.println("根据ID查询部门: " + id);
        log.info("根据ID查询部门: {}", id);
        Dept dept = deptService.getById(id);
        return Result.success(dept);
    }

    /*
     * 修改部门数据
     * */
    @Log
    @PutMapping
    public Result update(@RequestBody Dept dept) {
//        System.out.println("修改部门" + dept);
        log.info("修改部门: {}", dept);
        deptService.update(dept);
        return Result.success();
    }

}
