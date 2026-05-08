package com.itluffy.mapper;

import com.itluffy.pojo.Student;
import com.itluffy.pojo.StudentQueryParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StudentMapper {


    /*
     * 分页查询学生信息
     * */
    List<Student> studentList(StudentQueryParam studentQueryParam);
}
