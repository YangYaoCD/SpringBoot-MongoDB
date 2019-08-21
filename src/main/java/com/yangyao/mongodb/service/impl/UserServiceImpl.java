package com.yangyao.mongodb.service.impl;

import com.mongodb.client.result.DeleteResult;
import com.yangyao.mongodb.model.User;
import com.yangyao.mongodb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public void update(User user) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(user.getId()));
        Update update = new Update().set("name",user.getName())
                .set("number",user.getNumber());
        mongoTemplate.updateFirst(query, update, User.class);

    }

    @Override
    public User findBy(Long id) {
        Query query=new Query();
        query.addCriteria(Criteria.where("id").is(id));
        query.with(new Sort(Sort.Direction.DESC,"createTime"));
        User user = mongoTemplate.findOne(query, User.class);
        return user;
    }

    @Override
    public void insert(User user) {
        mongoTemplate.insert(user);
    }

    @Override
    public DeleteResult delete(long id) {
        Query query = new Query(Criteria.where("id").is(id));
        DeleteResult deleteResult = mongoTemplate.remove(query, User.class);
        return deleteResult;
    }

    @Override
    public List<User> findAll() {
        List<User> users = mongoTemplate.findAll(User.class);
        return users;
    }
}
