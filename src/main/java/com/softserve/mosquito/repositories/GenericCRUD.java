package com.softserve.mosquito.repositories;

import java.util.List;

public interface GenericCRUD<T> {
    T create(T t);

    T read(Long id);

    T update(T t);

    void delete(T t);

    List<T> readAll();
}
