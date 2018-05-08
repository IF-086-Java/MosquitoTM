package com.softserve.mosquito.repositories;

import java.util.Collection;

public interface GenericCRUD<T> {

    T create(T T);

    T read(Long id);

    Collection<T> readAll();

    T update(T T);

    void delete(Long id);
}
