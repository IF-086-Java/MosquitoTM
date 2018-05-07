package com.softserve.mosquito.repositories;

import com.softserve.mosquito.enitities.Estimation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Collection;

public class EstimationRepo extends GenericRepo<Estimation> {
    public EstimationRepo(Connection connection) {
        super(connection);
    }

    @Override
    protected String getSelectQuery() {
        return null;
    }

    @Override
    protected String getSelectAllQuery() {
        return null;
    }

    @Override
    protected String getCreateQuery() {
        return null;
    }

    @Override
    protected String getUpdateQuery() {
        return null;
    }

    @Override
    protected String getDeleteQuery() {
        return null;
    }

    @Override
    protected Collection<Estimation> parsData(ResultSet set) {
        return null;
    }

    @Override
    protected void setDataForCreate(PreparedStatement statement, Estimation object) {

    }

    @Override
    protected void setDataForUpdate(PreparedStatement statement, Estimation object) {

    }
}
