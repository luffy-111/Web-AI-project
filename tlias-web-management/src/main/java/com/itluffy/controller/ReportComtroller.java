package com.itluffy.controller;

import com.itluffy.pojo.ClassOption;
import com.itluffy.pojo.JobOption;
import com.itluffy.pojo.Result;
import com.itluffy.service.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/report")
public class ReportComtroller {

    @Autowired
    private ReportService reportService;

    /*
     * 统计员工职位人数
     * */
    @GetMapping("/empJobData")
    public Result getEmpJobData() {
        log.info("统计员工职位人数");
        JobOption jobOption = reportService.getEmpJobData();
        return Result.success(jobOption);
    }

    /*
     * 统计员工性别人数
     * */
    @GetMapping("/empGenderData")
    public Result getEmpGenderData() {
        log.info("统计员工性别人数");
        List<Map<String, Object>> genderList = reportService.getEmpGenderData();
        return Result.success(genderList);
    }

    /*
     * 统计学生各学位人数
     * */
    @GetMapping("/studentDegreeData")
    public Result getStudentDegreeData() {
        log.info("统计学生各学位人数");
        List<Map<String, Object>> studentDegreeData = reportService.getStudentDegreeData();
        return Result.success(studentDegreeData);
    }

    /*
     * 统计各班级学生的人数
     * */
    @GetMapping("/studentCountData")
    public Result getClassStudentData() {
        log.info("统计各班级学生的人数");
        ClassOption classOption = reportService.getStudentCountData();
        return Result.success(classOption);
    }
}
