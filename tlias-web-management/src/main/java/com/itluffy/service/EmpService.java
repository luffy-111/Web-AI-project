package com.itluffy.service;

import com.itluffy.pojo.Emp;
import com.itluffy.pojo.EmpQueryParam;
import com.itluffy.pojo.PageResult;

import java.util.List;


public interface EmpService {

    /*
     * 分页查询
     * page: 页码
     * pageSize: 每页展示的记录数
     * */
//    PageResult<Emp> page(Integer page, Integer pageSize,
//                         String name, Integer gender,
//                         LocalDate begin, LocalDate end);

    PageResult<Emp> page(EmpQueryParam empQueryParam);


    /*
     * 新增员工
     * */
    void save(Emp emp);

    /*
     * 批量删除员工信息
     * */
    void delete(List<Integer> ids);

    /*
     * 根据ID查询员工基本信息
     * */
    Emp getInfo(Integer id);

    /*
     * 修改员工
     * */
    void update(Emp emp);
}
