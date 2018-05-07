package com.softserve.mosquito.repositories;

import org.aopalliance.reflect.Class;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

public class FactoryRepo {
    private static Map<Class, RepoCreator> allRepo = new HashMap<>();

    static {
        //allRepo.put(User.class,(connection) -> new UserRepo(connection));
    }

    static GenericRepo getRepo(Connection connection, Class className) {
        return allRepo.get(className).create(connection);
    }
}
