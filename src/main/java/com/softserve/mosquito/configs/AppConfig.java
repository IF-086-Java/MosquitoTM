package com.softserve.mosquito.configs;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/*")
public class AppConfig extends Application {

    @Override
    public Map<String, Object> getProperties() {
    	System.out.println("App config...");
        Map<String, Object> properties = new HashMap<String, Object>();
        properties.put("jersey.config.server.provider.packages", "com.softserve.mosquito");
        return properties;
    }
}