package com.itluffy.service;

import com.itluffy.pojo.Dept;

import java.util.List;

public interface DeptService {
    /*
    * 查询所有的部门信息
    * */
    List<Dept> findAll();
}
