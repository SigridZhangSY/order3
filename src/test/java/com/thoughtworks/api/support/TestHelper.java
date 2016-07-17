package com.thoughtworks.api.support;

import java.util.HashMap;
import java.util.Map;

public class TestHelper {
    private static int auto_increment_key = 1;
    public static Map<String, Object> deployment(String appName, String releaseId) {
        return new HashMap<String, Object>() {{
            put("app", String.format("http://service-api.tw.com/apps/%s", appName));
            put("release", String.format("http://service-api.tw.com/apps/%s/releases/%s", appName, releaseId));
        }};
    }

    public static Map<String, Object> stackMap(String id, String name) {
        Map<String, Object> stackMap = new HashMap<String, Object>() {{
            put("id", id);
            put("name", name);
        }};
        return stackMap;
    }



    public static Map<String, Object> product(String name){
        return new HashMap<String, Object>() {{
            put("name", name);
            put("description", "red apple");
            put("price", 1.1);

        }};
    }

    public static Map<String, Object> user(String name){
        return new HashMap<String, Object>(){{
            put("name", name);
        }};
    }
}
