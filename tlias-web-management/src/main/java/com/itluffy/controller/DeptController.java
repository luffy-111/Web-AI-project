package com.itluffy.controller;

import com.itluffy.pojo.Dept;
import com.itluffy.pojo.Result;
import com.itluffy.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DeptController {

    @Autowired
    private DeptService deptService;

    // @RequestMapping(value = "/depts", method = RequestMethod.GET)  // method: 指定请求方式
    @GetMapping("/depts")
    public Result list(){
        System.out.println("查询所有的部门数据");
        List<Dept> deptList = deptService.findAll();
        return Result.success(deptList);
    }
}
