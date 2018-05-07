package com.softserve.mosquito.repositories;

import com.softserve.mosquito.enitities.Estimation;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

public class FactoryRepo {
    private static Map<Class, RepoCreator> allRepo = new HashMap<>();

    static {
        allRepo.put(Estimation.class, EstimationRepo::new);
    }

    static GenericRepo getRepo(Connection connection, Class className) {
        return allRepo.get(className).create(connection);
    }
}
