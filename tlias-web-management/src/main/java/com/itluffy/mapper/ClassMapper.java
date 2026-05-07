package com.itluffy.mapper;

import com.itluffy.pojo.ClassQueryParam;
import com.itluffy.pojo.Clazz;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ClassMapper {


    /*
     * 分页查询班级信息
     * */
    List<Clazz> classList(ClassQueryParam classQueryParam);

}
