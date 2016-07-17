package com.thoughtworks.api.infrastructure.repositories;

import com.thoughtworks.api.infrastructure.core.User;
import com.thoughtworks.api.infrastructure.mybatis.mappers.UserMapper;

import javax.inject.Inject;
import java.util.Map;
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

    private String nextIdentity() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

}
