package org.mod30task.dao;

import org.mod30task.dao.jdbc.ConnectionFactory;
import org.mod30task.domain.Product.ProductDomain;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductDAO implements IProductDAO {

    @Override
    public void create(ProductDomain product) {
        String sql = "INSERT INTO products (code, name, description, price) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, product.getCode());
            pstmt.setString(2, product.getName());
            pstmt.setString(3, product.getDescription());
            pstmt.setBigDecimal(4, product.getPrice());

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating product failed, no rows affected.");
            }

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    product.setId(generatedKeys.getLong(1));
                } else {
                    throw new SQLException("Creating product failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error creating product", e);
        }
    }

    @Override
    public Optional<ProductDomain> findById(Long id) {
        String sql = "SELECT id, code, name, description, price FROM products WHERE id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(extractProductFromResultSet(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error finding product by id", e);
        }

        return Optional.empty();
    }

    @Override
    public Optional<ProductDomain> findByCode(String code) {
        String sql = "SELECT id, code, name, description, price FROM products WHERE code = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, code);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(extractProductFromResultSet(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error finding product by code", e);
        }

        return Optional.empty();
    }

    @Override
    public List<ProductDomain> findAll() {
        List<ProductDomain> products = new ArrayList<>();
        String sql = "SELECT id, code, name, description, price FROM products";
        try (Connection conn = ConnectionFactory.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                products.add(extractProductFromResultSet(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error finding all products", e);
        }

        return products;
    }

    @Override
    public List<ProductDomain> findByNameContaining(String name) {
        List<ProductDomain> products = new ArrayList<>();
        String sql = "SELECT id, code, name, description, price FROM products WHERE name LIKE ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, "%" + name + "%");
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    products.add(extractProductFromResultSet(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error finding products by name", e);
        }

        return products;
    }

    @Override
    public void update(ProductDomain product) {
        String sql = "UPDATE products SET code = ?, name = ?, description = ?, price = ? WHERE id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, product.getCode());
            pstmt.setString(2, product.getName());
            pstmt.setString(3, product.getDescription());
            pstmt.setBigDecimal(4, product.getPrice());
            pstmt.setLong(5, product.getId());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error updating product", e);
        }
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM products WHERE id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting product", e);
        }
    }

    @Override
    public boolean exists(Long id) {
        String sql = "SELECT 1 FROM products WHERE id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error checking if product exists", e);
        }
    }

    private ProductDomain extractProductFromResultSet(ResultSet rs) throws SQLException {
        return new ProductDomain(
                rs.getLong("id"),
                rs.getString("code"),
                rs.getString("name"),
                rs.getString("description"),
                rs.getBigDecimal("price")
        );
    }
}