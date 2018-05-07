package com.softserve.mosquito.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

public abstract class GenericRepo<Obj extends Identificator> implements GenericCRUD<Obj> {
    private Connection connection;

    public GenericRepo(Connection connection) {
        this.connection = connection;
    }

    protected abstract String getSelectQuery();

    protected abstract String getSelectAllQuery();

    protected abstract String getCreateQuery();

    protected abstract String getUpdateQuery();

    protected abstract String getDeleteQuery();

    protected abstract Collection<Obj> parsData(ResultSet set);

    protected abstract void setDataForCreate(PreparedStatement statement, Obj object);

    protected abstract void setDataForUpdate(PreparedStatement statement, Obj object);

    @Override
    public Obj create(Obj object) {
        Obj result = null;

        String query = getCreateQuery();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            setDataForCreate(statement, object);
            int createdCount = statement.executeUpdate();
            if (createdCount != 1)
                throw new SQLException("Created more or less than one object");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        query = getSelectQuery() + "(SELECT last_insert_id());";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            Collection<Obj> data = parsData(resultSet);
            if (data == null || data.size() != 1)
                throw new SQLException("Error with search created object by id");
            result = data.iterator().next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Obj read(Long id) {
        Obj result = null;

        String query = getSelectQuery() + id + ";";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            Collection<Obj> data = parsData(resultSet);
            if (data == null)
                throw new SQLException("Error with searching object");
            result = data.iterator().next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Collection<Obj> readAll() {
        Collection<Obj> result = null;

        String query = getSelectAllQuery();

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            result = parsData(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean update(Obj object) {
        String query = getUpdateQuery();

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            setDataForUpdate(statement, object);
            int updatedCount = statement.executeUpdate();
            if (updatedCount != 1)
                throw new SQLException("Update more or less than one row");
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(Long id) {
        String query = getDeleteQuery();

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            int deletedCount = statement.executeUpdate();
            if (deletedCount != 1)
                throw new SQLException("Delete more or less than one row");
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}