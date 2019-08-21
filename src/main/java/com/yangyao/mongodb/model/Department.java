package com.yangyao.mongodb.model;

import lombok.Data;

@Data
public class Department {
    private long id;
    private String deptName;
    private long createTime;
}
