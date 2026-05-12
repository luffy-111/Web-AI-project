package com.itluffy.service;

import com.itluffy.pojo.OperateLog;
import com.itluffy.pojo.OperateLogQueryParam;
import com.itluffy.pojo.PageResult;

public interface OperateLogService {
    /**
     * 分页查询日志信息
     *
     * @param operateLogQueryParam
     * @return
     */
    PageResult<OperateLog> pageLog(OperateLogQueryParam operateLogQueryParam);
}
