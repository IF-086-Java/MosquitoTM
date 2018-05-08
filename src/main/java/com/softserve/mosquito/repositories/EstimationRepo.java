package com.softserve.mosquito.repositories;

import com.softserve.mosquito.enitities.Estimation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

public class EstimationRepo implements GenericCRUD<Estimation> {
    @Override
    public Estimation create(Estimation T) {
        return null;
    }

    @Override
    public Estimation read(Long id) {
        return null;
    }

    @Override
    public Collection<Estimation> readAll() {
        return null;
    }

    @Override
    public Estimation update(Estimation T) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
