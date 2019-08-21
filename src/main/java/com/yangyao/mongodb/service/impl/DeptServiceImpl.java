package com.yangyao.mongodb.service.impl;

import com.mongodb.client.result.UpdateResult;
import com.yangyao.mongodb.model.Department;
import com.yangyao.mongodb.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@Service
public class DeptServiceImpl implements DeptService {
    @Autowired
    MongoTemplate mongoTemplate;
    @Override
    public void insert(Department department) {
        Department insert = mongoTemplate.insert(department);
        System.out.println("插入："+insert.toString());
    }

    @Override
    public Department findById(long id) {
        Department department = mongoTemplate.findById(id, Department.class);
        System.out.println(department.toString());
        return department;
    }

    @Override
    public void modify(Department department) {
        Query query = new Query(Criteria.where("id").is(department.getId()));
        Update update = new Update().set("deptName", department.getDeptName())
                .set("createTime", department.getCreateTime());
        UpdateResult updateResult = mongoTemplate.updateFirst(query, update, Department.class);
        System.out.println("更新结果："+updateResult);

    }

    @Override
    public void delete(long id) {

    }
}
