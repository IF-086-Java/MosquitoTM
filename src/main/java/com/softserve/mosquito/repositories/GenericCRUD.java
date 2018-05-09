package com.softserve.mosquito.repositories;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface GenericCRUD<T> {
    T create(T t) throws SQLException, ClassNotFoundException, IOException;
    T read (Long id);
    T update (T t);
    void delete (T t);

    List<T> readAll();
}
