package com.itluffy.controller;

import com.itluffy.pojo.Emp;
import com.itluffy.pojo.LoginUser;
import com.itluffy.pojo.Result;
import com.itluffy.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class LoginController {

    @Autowired
    private EmpService empService;

    /*
    * 用户登录
    * */
    @PostMapping("/login")
    public Result login(@RequestBody Emp emp) {
        log.info("用户登录: {}", emp);
        LoginUser loginUser = empService.login(emp);
        if (loginUser != null){
            return Result.success(loginUser);
        }
        return Result.error("用户名或密码错误");
    }

}
