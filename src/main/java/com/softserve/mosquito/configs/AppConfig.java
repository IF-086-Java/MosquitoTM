package com.softserve.mosquito.configs;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashMap;
import java.util.Map;

@ApplicationPath("/*")
public class AppConfig extends Application {
    private static final Logger LOGGER = LogManager.getLogger(AppConfig.class);

    @Override
    public Map<String, Object> getProperties() {
        LOGGER.info("App config...");
        Map<String, Object> properties = new HashMap<String, Object>();
        properties.put("jersey.config.server.provider.packages", "com.softserve.mosquito");
        return properties;
    }
}