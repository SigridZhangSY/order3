package com.thoughtworks.api.infrastructure.core;

import java.util.Map;
import java.util.Optional;

/**
 * Created by syzhang on 7/17/16.
 */
public interface UserRepository {
    User createUser(Map<String, Object> info);
    Optional<User> findUserByName(String name);
}
