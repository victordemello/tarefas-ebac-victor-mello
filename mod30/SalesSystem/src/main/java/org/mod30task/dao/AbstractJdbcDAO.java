package org.mod30task.dao;

import org.mod30task.dao.jdbc.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class AbstractJdbcDAO<T, ID> implements IGenericDAO<T, ID> {

    // Métodos abstratos que devem ser implementados pelas subclasses
    protected abstract String getCreateQuery();
    protected abstract String getFindByIdQuery();
    protected abstract String getFindAllQuery();
    protected abstract String getUpdateQuery();
    protected abstract String getDeleteQuery();
    protected abstract String getExistsQuery();

    protected abstract void setCreateStatement(PreparedStatement statement, T entity) throws SQLException;
    protected abstract void setUpdateStatement(PreparedStatement statement, T entity) throws SQLException;
    protected abstract void setIdStatement(PreparedStatement statement, ID id) throws SQLException;
    protected abstract T extractEntityFromResultSet(ResultSet resultSet) throws SQLException;

    @Override
    public void create(T entity) {
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement statement = conn.prepareStatement(getCreateQuery())) {

            setCreateStatement(statement, entity);
            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating entity failed, no rows affected.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error creating entity", e);
        }
    }

    @Override
    public Optional<T> findById(ID id) {
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement statement = conn.prepareStatement(getFindByIdQuery())) {

            setIdStatement(statement, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(extractEntityFromResultSet(resultSet));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error finding entity by id", e);
        }

        return Optional.empty();
    }

    @Override
    public List<T> findAll() {
        List<T> entities = new ArrayList<>();

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement statement = conn.prepareStatement(getFindAllQuery());
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                entities.add(extractEntityFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error finding all entities", e);
        }

        return entities;
    }

    @Override
    public void update(T entity) {
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement statement = conn.prepareStatement(getUpdateQuery())) {

            setUpdateStatement(statement, entity);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error updating entity", e);
        }
    }

    @Override
    public void delete(ID id) {
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement statement = conn.prepareStatement(getDeleteQuery())) {

            setIdStatement(statement, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting entity", e);
        }
    }

    @Override
    public boolean exists(ID id) {
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement statement = conn.prepareStatement(getExistsQuery())) {

            setIdStatement(statement, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error checking if entity exists", e);
        }
    }
}
