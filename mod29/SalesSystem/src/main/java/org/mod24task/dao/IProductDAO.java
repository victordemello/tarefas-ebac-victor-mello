package org.mod24task.dao;

import org.mod24task.domain.Product;

import java.sql.SQLException;
import java.util.List;

public interface IProductDAO {
    int register(Product product) throws SQLException;
    int update(Product product) throws SQLException;
    int delete(Product product) throws SQLException;
    Product findByCode(String code) throws SQLException;
    List<Product> searchAll() throws SQLException;
}
