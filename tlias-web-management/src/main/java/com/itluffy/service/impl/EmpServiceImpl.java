package com.itluffy.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itluffy.mapper.EmpExprMapper;
import com.itluffy.mapper.EmpMapper;
import com.itluffy.pojo.*;
import com.itluffy.service.EmpService;
import com.itluffy.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Slf4j
@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;

    @Autowired
    private EmpExprMapper empExprMapper;

    /*
     * 原始分页查询
     * page: 页码
     * pageSize: 每页记录数
     * */
//    @Override
//    public PageResult<Emp> page(Integer page, Integer pageSize) {
//        //1. 调用Mapper接口, 查询总记录数
//        Long total = empMapper.count();
//        //2. 调用Mapper接口, 查询结果列表
//        List<Emp> rows = empMapper.list(((page - 1) * pageSize), pageSize);
//        //3. 封装结果, 返回pageResult
//        return new PageResult<Emp>(total, rows);
//    }


    /*
     * PageHelper 分页查询
     * page: 页码
     * pageSize: 每页记录数
     *     注意:
     *          1. 定义的SQL语句不能加分号
     *          2. PageHelper仅仅对紧跟其后的第一个查询语句进行分页处理
     * */
//    @Override
//    public PageResult<Emp> page(Integer page, Integer pageSize,
//                                String name, Integer gender,
//                                LocalDate begin, LocalDate end) {
//        //1. 设置分页参数(PageHelper)
//        PageHelper.startPage(page, pageSize);
//        //2. 执行查询
//        List<Emp> empList = empMapper.list(name, gender, begin, end);
//        //3. 解析查询结果, 并封装
//        Page<Emp> p = (Page<Emp>) empList;
//        return new PageResult<Emp>(p.getTotal(), p.getResult());
//    }

    @Override
    public PageResult<Emp> page(EmpQueryParam empQueryParam) {
        //1. 设置分页参数(PageHelper)
        PageHelper.startPage(empQueryParam.getPage(), empQueryParam.getPageSize());
        //2. 执行查询
        List<Emp> empList = empMapper.list(empQueryParam);
        //3. 解析查询结果, 并封装
        Page<Emp> p = (Page<Emp>) empList;
        return new PageResult<Emp>(p.getTotal(), p.getResult());
    }


    /*
     * 新增员工
     * */
    @Override
    public void save(Emp emp) {
        //1. 保存员工基本信息
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.insert(emp);
        //2. 保存员工工作经历
        List<EmpExpr> exprList = emp.getExprList();
        if (!CollectionUtils.isEmpty(exprList)) {
            // 遍历集合, 为empId赋值
            exprList.forEach(empExpr -> {
                empExpr.setEmpId(emp.getId());
            });
            empExprMapper.insertBatch(exprList);
        }
    }


    /*
     * 批量删除员工
     * */
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void delete(List<Integer> ids) {
        //1. 批量删除员工的基本信息
        empMapper.deleteByIds(ids);

        //2. 批量删除员工的工作经历信息
        empExprMapper.deleteByEmpIds(ids);
    }

    /*
     * 根据ID查询员工信息
     * */
    @Override
    public Emp getInfo(Integer id) {
        return empMapper.getById(id);
    }

    /*
     * 修改员工
     * */
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void update(Emp emp) {
        //1. 根据ID修改员工基本信息
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.updateById(emp);
        //2. 根据ID修改员工工作经历
        //2.1 先删除(根据员工ID删除原有的工作经历)
        empExprMapper.deleteByEmpIds(Collections.singletonList(emp.getId()));
        //2.2 再添加这个员工新的工作经历
        List<EmpExpr> exprList = emp.getExprList();
        if (!CollectionUtils.isEmpty(exprList)) {
            exprList.forEach(empExpr -> empExpr.setEmpId(emp.getId()));
            empExprMapper.insertBatch(exprList);
        }
    }

    /*
     * 员工登录
     * */
    @Override
    public LoginUser login(Emp emp) {
        //1. 根据用户名查询员工信息
        Emp e = empMapper.getByUsernameAndPassword(emp);
        //2. 判断员工是否存在
        if (e != null) {
            log.info("员工登录: {}", e);
            // 生成JWT令牌
            String token = JwtUtils.generateJwt(Collections.singletonMap("id", e.getId()));
            return new LoginUser(e.getId(), e.getUsername(), e.getName(), token);
        }
        return null;
    }

}
