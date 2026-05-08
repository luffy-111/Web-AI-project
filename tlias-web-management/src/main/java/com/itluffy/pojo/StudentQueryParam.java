package com.itluffy.pojo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class StudentQueryParam {

    private Integer page = 1;  //页码
    private Integer pageSize = 10;  //每页展示的记录数
    private Integer id;  // 'ID,主键'
    private String name;  // '姓名'
    private String no;  // '学号'
    private Integer gender;  // '性别'
    private String phone;  // '电话'
    private String idCard;  // '身份证'
    private Integer isCollege;  // '是否是 College'
    private String address;  // '地址'
    private Integer degree;  // '学位'
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate graduationDate;  // '毕业日期'
    private Integer clazzId;  // '班级ID'
    private Integer violationCount;  // '违规次数'
    private Integer violationScore;  // '违规积分'
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate createTime;  // '创建时间'
    private LocalDate updateTime;  // '更新时间'
}
