package com.thoughtworks.api.infrastructure.repositories;

import com.thoughtworks.api.infrastructure.core.User;
import com.thoughtworks.api.infrastructure.mybatis.mappers.UserMapper;

import javax.inject.Inject;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

/**
 * Created by syzhang on 7/17/16.
 */
public class UserRepository implements com.thoughtworks.api.infrastructure.core.UserRepository {
    @Inject
    UserMapper userMapper;
    @Override
    public User createUser(Map<String, Object> info) {
        String userId = nextIdentity();
        info.put("id", userId);
        userMapper.save(info);
        return userMapper.findById(userId);

    }

    @Override
    public Optional<User> findUserByName(String name) {
        return Optional.ofNullable(userMapper.findByName(name));
    }

    @Override
    public Optional<User> findUserById(String id) {
        return Optional.ofNullable(userMapper.findById(id));
    }

    private String nextIdentity() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

}
