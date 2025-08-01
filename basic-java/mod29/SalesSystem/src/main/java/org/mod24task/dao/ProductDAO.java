package org.mod24task.dao;

import org.mod24task.domain.Product;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO implements IProductDAO {

    @Override
    public int register(Product product) throws SQLException {
        Connection connection = null;
        PreparedStatement stm = null;

        try {
            connection = ConnectionFactory.getConnection();
            String sql = getSqlInsert();
            stm = connection.prepareStatement(sql);
            addParameterInInsert(stm, product);
            return stm.executeUpdate();
        } catch (Exception ex) {
            throw ex;
        } finally {
            closeConnection(connection, stm, null);
        }
    }

    @Override
    public int update(Product product) throws SQLException {
        Connection connection = null;
        PreparedStatement stm = null;

        try {
            connection = ConnectionFactory.getConnection();
            String sql = getSqlUpdate();
            stm = connection.prepareStatement(sql);
            addParameterInUpdate(stm, product);
            return stm.executeUpdate();
        } catch (Exception ex) {
            throw ex;
        } finally {
            closeConnection(connection, stm, null);
        }
    }

    @Override
    public int delete(Product product) throws SQLException {
        Connection connection = null;
        PreparedStatement stm = null;

        try {
            connection = ConnectionFactory.getConnection();
            String sql = getSqlDelete();
            stm = connection.prepareStatement(sql);
            addParameterInDelete(stm, product);
            return stm.executeUpdate();
        } catch (Exception ex) {
            throw ex;
        } finally {
            closeConnection(connection, stm, null);
        }
    }

    @Override
    public Product findByCode(String code) throws SQLException {
        Connection connection = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        Product product = null;

        try {
            connection = ConnectionFactory.getConnection();
            String sql = getSqlSelectByCode();
            stm = connection.prepareStatement(sql);
            addParameterInSelectByCode(stm, code);
            rs = stm.executeQuery();

            if (rs.next()) {
                Long id = rs.getLong("id");
                String name = rs.getString("name");
                String description = rs.getString("description");
                BigDecimal value = rs.getBigDecimal("value");
                product = new Product(id, code, name, description, value);
            }

            return product;
        } catch (Exception ex) {
            throw ex;
        } finally {
            closeConnection(connection, stm, rs);
        }
    }

    @Override
    public List<Product> searchAll() throws SQLException {
        Connection connection = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<Product> productsList = new ArrayList<>();

        try {
            connection = ConnectionFactory.getConnection();
            String sql = getSqlSelectAll();
            stm = connection.prepareStatement(sql);
            rs = stm.executeQuery();

            while (rs.next()) {
                Long id = rs.getLong("id");
                String code = rs.getString("code");
                String name = rs.getString("name");
                String description = rs.getString("description");
                BigDecimal value = rs.getBigDecimal("value");
                Product product = new Product(id, code, name, description, value);
                productsList.add(product);
            }

            return productsList;
        } catch (Exception ex) {
            throw ex;
        } finally {
            closeConnection(connection, stm, rs);
        }
    }

    // ===== Métodos privados auxiliares =====

    private String getSqlInsert() {
        return "INSERT INTO tb_product (id, code, name, description, value) " +
                "VALUES (nextval('sq_product_id'), ?, ?, ?, ?)";
    }

    private String getSqlUpdate() {
        return "UPDATE tb_product SET code = ?, name = ?, description = ?, value = ? WHERE id = ?";
    }

    private String getSqlDelete() {
        return "DELETE FROM tb_product WHERE code = ?";
    }

    private String getSqlSelectByCode() {
        return "SELECT * FROM tb_product WHERE code = ?";
    }

    private String getSqlSelectAll() {
        return "SELECT * FROM tb_product";
    }

    private void addParameterInInsert(PreparedStatement stm, Product product) throws SQLException {
        stm.setString(1, product.getCode());
        stm.setString(2, product.getName());
        stm.setString(3, product.getDescription());
        stm.setBigDecimal(4, product.getValue());
    }

    private void addParameterInUpdate(PreparedStatement stm, Product product) throws SQLException {
        stm.setString(1, product.getCode());
        stm.setString(2, product.getName());
        stm.setString(3, product.getDescription());
        stm.setBigDecimal(4, product.getValue());
        stm.setLong(5, product.getId());
    }

    private void addParameterInDelete(PreparedStatement stm, Product product) throws SQLException {
        stm.setString(1, product.getCode());
    }

    private void addParameterInSelectByCode(PreparedStatement stm, String code) throws SQLException {
        stm.setString(1, code);
    }

    private void closeConnection(Connection connection, PreparedStatement stm, ResultSet rs) {
        try {
            if (rs != null && !rs.isClosed()) rs.close();
            if (stm != null && !stm.isClosed()) stm.close();
            if (connection != null && !connection.isClosed()) connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
