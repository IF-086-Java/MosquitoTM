package com.softserve.mosquito.repositories;

import java.sql.Connection;

@FunctionalInterface
public interface RepoCreator {
    GenericRepo create(Connection connection);
}
