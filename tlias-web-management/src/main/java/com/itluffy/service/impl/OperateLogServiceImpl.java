package com.itluffy.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itluffy.mapper.OperateLogMapper;
import com.itluffy.pojo.OperateLog;
import com.itluffy.pojo.OperateLogQueryParam;
import com.itluffy.pojo.PageResult;
import com.itluffy.service.OperateLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OperateLogServiceImpl implements OperateLogService {

    @Autowired
    private OperateLogMapper operateLogMapper;
    /*
     * 分页查询日志信息
     * */
    @Override
    public PageResult<OperateLog> pageLog(OperateLogQueryParam operateLogQueryParam) {
        //1. 设置分页参数(PageHelper)
        PageHelper.startPage(operateLogQueryParam.getPage(), operateLogQueryParam.getPageSize());
        //2. 执行查询
        List<OperateLog> operateLogsList = operateLogMapper.pageLog(operateLogQueryParam);
        //3. 解析查询结果, 并封装
        Page<OperateLog> operateLogs = (Page<OperateLog>) operateLogsList;
        return new PageResult<>(operateLogs.getTotal(), operateLogs.getResult());
    }
}
