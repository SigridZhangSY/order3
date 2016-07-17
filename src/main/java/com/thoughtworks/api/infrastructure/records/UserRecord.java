package com.thoughtworks.api.infrastructure.records;

import com.thoughtworks.api.infrastructure.core.User;

/**
 * Created by syzhang on 7/17/16.
 */
public class UserRecord implements User{
    public String id;
    public String name;

    public UserRecord(){
        this.id = "1";
    }

    @Override
    public String getId() {
        return id;
    }
    @Override
    public String getName() {
        return name;
    }
}
