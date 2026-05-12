package com.itluffy.mapper;

import com.itluffy.pojo.OperateLog;
import com.itluffy.pojo.OperateLogQueryParam;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface OperateLogMapper {

    // 添加操作日志
    @Insert("insert into operate_log(operate_emp_id, operate_time, class_name, method_name, method_params, return_value, cost_time) " +
            "values (#{operateEmpId}, #{operateTime}, #{className}, #{methodName}, #{methodParams}, #{returnValue}, #{costTime})")
    void addOperateLog(OperateLog log);

    // 分页查询操作日志
    List<OperateLog> pageLog(OperateLogQueryParam operateLogQueryParam);
}
