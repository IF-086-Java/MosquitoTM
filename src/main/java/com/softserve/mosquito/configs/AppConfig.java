package com.softserve.mosquito.configs;

import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/*")
public class AppConfig extends Application {
    private static final Logger LOGGER = Logger.getLogger(AppConfig.class);

    @Override
    public Map<String, Object> getProperties() {
        LOGGER.info("App config...");
        Map<String, Object> properties = new HashMap<String, Object>();
        properties.put("jersey.config.server.provider.packages", "com.softserve.mosquito");
        return properties;
    }
}