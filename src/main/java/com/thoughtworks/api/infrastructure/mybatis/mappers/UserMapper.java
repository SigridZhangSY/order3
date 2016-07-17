package com.thoughtworks.api.infrastructure.mybatis.mappers;

import com.thoughtworks.api.infrastructure.core.User;

import java.util.Map;

/**
 * Created by syzhang on 7/17/16.
 */
public interface UserMapper {
    int save(Map<String, Object> info);
    User findById(String id);
}
