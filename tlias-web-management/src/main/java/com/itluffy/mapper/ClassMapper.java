package com.itluffy.mapper;

import com.itluffy.pojo.ClassQueryParam;
import com.itluffy.pojo.Clazz;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ClassMapper {


    /*
     * 分页查询班级信息
     * */
    List<Clazz> classList(ClassQueryParam classQueryParam);

    /*
     * 根据ID删除班级信息
     * */
    @Delete("delete from clazz where id = #{id}")
    void deleteClassById(Integer id);

    /*
     * 新增班级
     * */
    void addClass(Clazz clazz);

    /*
     * 根据ID查询班级信息
     * */
    @Select("select id, name, room, begin_date, end_date, master_id, subject, create_time, update_time from clazz where id = #{id}")
    Clazz getClassById(Integer id);

    /*
     * 修改班级信息
     * */
    void updateClass(Clazz clazz);
}
