package com.itluffy.controller;


import com.itluffy.pojo.OperateLog;
import com.itluffy.pojo.OperateLogQueryParam;
import com.itluffy.pojo.PageResult;
import com.itluffy.pojo.Result;
import com.itluffy.service.OperateLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/log")
public class LogController {

    @Autowired
    private OperateLogService operateLogService;

    /*
     * 分页查询日志信息
     * */
    @GetMapping("/page")
    public Result pageLog(OperateLogQueryParam operateLogQueryParam) {
        log.info("分页查询日志: {}", operateLogQueryParam);
        PageResult<OperateLog> pageResult = operateLogService.pageLog(operateLogQueryParam);
        return Result.success(pageResult);
    }
}
