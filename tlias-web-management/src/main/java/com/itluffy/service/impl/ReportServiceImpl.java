package com.itluffy.service.impl;

import com.itluffy.mapper.EmpMapper;
import com.itluffy.pojo.ClassOption;
import com.itluffy.pojo.JobOption;
import com.itluffy.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private EmpMapper empMapper;

    @Override
    public JobOption getEmpJobData() {
        //1. 调用Mapper接口, 获取统计数据
        List<Map<String, Object>> list = empMapper.countEmpJobData();
        //2. 组装结果并返回
        List<Object> jobList = list.stream().map(dataMap -> dataMap.get("pos")).toList();
        List<Object> dataList = list.stream().map(dataMap -> dataMap.get("num")).toList();

        return new JobOption(jobList, dataList);
    }

    @Override
    public List<Map<String, Object>> getEmpGenderData() {
        return empMapper.countEmpGenderData();
    }

    /*
     * 统计学生各学位人数
     * */
    @Override
    public List<Map<String, Object>> getStudentDegreeData() {
        return empMapper.countStudentDegreeData();
    }

    /*
     * 统计各班级人数
     * */
    @Override
    public ClassOption getStudentCountData() {
        //1. 调用Mapper接口, 获取统计数据
        List<Map<String, Object>> list = empMapper.countStudentCountData();
        //2. 组装结果并返回
        List<Object> classList = list.stream().map(dataMap -> dataMap.get("className")).toList();
        List<Object> dataList = list.stream().map(dataMap -> dataMap.get("num")).toList();
        return new ClassOption(classList, dataList);
    }
}
