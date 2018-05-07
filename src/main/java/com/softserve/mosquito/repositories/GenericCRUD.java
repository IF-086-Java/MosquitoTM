package com.softserve.mosquito.repositories;

import java.util.Collection;

public interface GenericCRUD<Obj extends Identificator> {

    Obj create(Obj obj);

    Obj read(Long id);

    Collection<Obj> readAll();

    boolean update(Obj obj);

    boolean delete(Long id);
}
