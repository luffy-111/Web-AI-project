package com.itluffy.mapper;

import com.itluffy.pojo.Dept;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DeptMapper {
    /*
    * 查询所有的部门数据
    * */
      // 方式一: 手动结果映射
//    @Results({
//            @Result(column = "create_time", property = "createTime"),
//            @Result(column = "update_time", property = "updateTime")
//    })

    // 方式二: 起别名
    // @Select("select id,name,create_time createTime,update_time updateTime from dept order by dept.update_time desc")
    @Select("select id,name,create_time,update_time from dept order by dept.update_time desc")
    List<Dept> findAll();
}