package com.yangyao.mongodb.service;

import com.yangyao.mongodb.model.Department;

public interface DeptService {
    void insert(Department department);
    Department findById(long id);
    void modify(Department department);
    void delete(long id);
}
