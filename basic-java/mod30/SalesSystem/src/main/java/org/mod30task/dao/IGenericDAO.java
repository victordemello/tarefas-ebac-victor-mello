package org.mod30task.dao;

import java.util.List;
import java.util.Optional;

public interface IGenericDAO<T, ID> {
    void create(T entity);
    Optional<T> findById(ID id);
    List<T> findAll();
    void update(T entity);
    void delete(ID id);
    boolean exists(ID id);
}
