package com.itluffy.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClassOption {

    private List degreeList; // 班级列表
    private List dataList;  // 数据列表
}
