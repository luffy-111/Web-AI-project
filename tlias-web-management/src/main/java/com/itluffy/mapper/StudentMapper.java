package com.itluffy.mapper;

import com.itluffy.pojo.Student;
import com.itluffy.pojo.StudentQueryParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface StudentMapper {


    /*
     * 分页查询学生信息
     * */
    List<Student> studentList(StudentQueryParam studentQueryParam);

    /*
     * 根据ID批量删除学生信息
     * */
    void deleteStudentByIds(List<Integer> ids);

    /*
     * 新增学生信息
     * */
    void addStudent(Student student);

    /*
     * 根据ID查询学生信息
     * */
    @Select("select * from student where id = #{id}")
    Student getStudentById(Integer id);

    /*
     * 修改学生信息
     * */
    void updateStudent(Student student);

    /*
     * 修改学生违纪扣分
     * */
    void updateStudentViolationScore(Integer id, Integer score);
}
