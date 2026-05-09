package com.itluffy.mapper;

import com.itluffy.pojo.Emp;
import com.itluffy.pojo.EmpQueryParam;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;


/*
 * 员工信息
 * */
@Mapper
public interface EmpMapper {

    // ---------------------------- 原始分页查询实现 ----------------------------
    /*
     * 查询总记录数
     * */
//    @Select("select count(*) from dept d, emp e where d.id = e.dept_id")
//    public Long count();


    /*
     *分页查询
     * */
//    @Select("select e.*, d.name as deptName from dept d, emp e where d.id = e.dept_id " +
//            "order by e.update_time desc limit #{start},#{pageSize}")
//    public List<Emp> list(Integer start, Integer pageSize);


//    @Select("select e.*, d.name as deptName from dept d, emp e where d.id = e.dept_id order by e.update_time desc")
//    public List<Emp> list(String name, Integer gender, LocalDate begin, LocalDate end);

    public List<Emp> list(EmpQueryParam empQueryParam);


    /*
     * 新增员工
     * */
    @Options(useGeneratedKeys = true, keyProperty = "id")  // 获取到生成的主键 - 主键返回
    @Insert("insert into emp(username, name, gender, phone, job, salary, image, entry_date, dept_id, create_time, update_time)\n" +
            "values (#{username}, #{name}, #{gender}, #{phone}, #{job}, #{salary}, #{image}, #{entryDate}, #{deptId}, #{createTime}, #{updateTime})")
    void insert(Emp emp);

    /*
     * 根据ID删除员工的基本信息
     * */
    void deleteByIds(List<Integer> ids);

    /*
     * 根据ID查询员工的基本信息和员工的工作经历信息
     * */
    Emp getById(Integer id);

    /*
     * 根据ID修改员工的基本信息
     * */
    void updateById(Emp emp);

    /*
     * 统计员工职位人数
     * */
    @MapKey("pos")
    List<Map<String, Object>> countEmpJobData();

    /*
     * 统计员工性别人数
     * */
    @MapKey("name")
    List<Map<String, Object>> countEmpGenderData();

    /*
     * 统计学生各学位人数
     * */
    @MapKey("degree")
    List<Map<String, Object>> countStudentDegreeData();

    /*
     * 统计各班学生人数
     * */
    @MapKey("className")
    List<Map<String, Object>> countStudentCountData();
}
