package com.yangyao.mongodb.service;

import com.mongodb.client.result.DeleteResult;
import com.yangyao.mongodb.model.User;

import java.util.List;

public interface UserService {
    User findBy(Long id);

    void insert(User user);

    void update(User user);

    DeleteResult delete(long id);

    List<User> findAll();

}
