package org.mod30task.dao;

import org.mod30task.dao.jdbc.ConnectionFactory;
import org.mod30task.domain.Product.Stock;
import org.mod30task.domain.Product.StockItem;
import org.mod30task.domain.Product.ProductDomain;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StockDAO implements IStockDAO {

    private ProductDomain extractProductFromResultSet(ResultSet rs) throws SQLException {
        return new ProductDomain(
                rs.getLong("product_id"),
                rs.getString("code"),
                rs.getString("name"),
                rs.getString("description"),
                rs.getBigDecimal("price")
        );
    }

    private StockItem extractStockItemFromResultSet(ResultSet rs) throws SQLException {
        Long id = rs.getLong("id");
        Integer quantity = rs.getInt("quantity");
        ProductDomain product = extractProductFromResultSet(rs);
        return new StockItem(id, product, quantity);
    }

    @Override
    public void create(Stock stock) {
        String sql = "INSERT INTO stocks (id) VALUES (DEFAULT)"; // Assuming auto-increment
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.executeUpdate();

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    Long stockId = generatedKeys.getLong(1);
                    stock.setId(stockId);

                    for (StockItem item : stock.getStockItems()) {
                        saveStockItem(stockId, item);
                    }
                } else {
                    throw new SQLException("Creating stock failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error creating stock", e);
        }
    }

    private void saveStockItem(Long stockId, StockItem item) {
        String sql = "INSERT INTO stock_items (stock_id, product_id, quantity) VALUES (?, ?, ?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, stockId);
            pstmt.setLong(2, item.getProduct().getId()); // Use getId() here
            pstmt.setInt(3, item.getQuantity());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error saving stock item", e);
        }
    }

    @Override
    public Optional<Stock> findById(Long id) {
        String sql = "SELECT id FROM stocks WHERE id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Long stockId = rs.getLong("id");
                    Stock stock = new Stock(stockId, null);
                    stock.setStockItems(findStockItemsByStockId(stockId));
                    return Optional.of(stock);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error finding stock by id", e);
        }
        return Optional.empty();
    }

    private List<StockItem> findStockItemsByStockId(Long stockId) {
        List<StockItem> items = new ArrayList<>();
        String sql = "SELECT si.id, si.quantity, " +
                "p.id as product_id, p.code, p.name, p.description, p.price " +
                "FROM stock_items si " +
                "JOIN products p ON si.product_id = p.id " +
                "WHERE si.stock_id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, stockId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    items.add(extractStockItemFromResultSet(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error finding stock items by stock id", e);
        }
        return items;
    }

    @Override
    public List<Stock> findAll() {
        List<Stock> stocks = new ArrayList<>();
        String sql = "SELECT id FROM stocks";
        try (Connection conn = ConnectionFactory.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Long stockId = rs.getLong("id");
                Stock stock = new Stock(stockId, findStockItemsByStockId(stockId));
                stocks.add(stock);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error finding all stocks", e);
        }
        return stocks;
    }

    @Override
    public void update(Stock stock) {
        if (stock == null || stock.getId() == null) {
            throw new IllegalArgumentException("Stock and Stock ID cannot be null for update");
        }

        // First, delete existing stock items for this stock
        String deleteItemsSql = "DELETE FROM stock_items WHERE stock_id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(deleteItemsSql)) {
            pstmt.setLong(1, stock.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting existing stock items", e);
        }

        // Now, insert the updated stock items
        for (StockItem item : stock.getStockItems()) {
            saveStockItem(stock.getId(), item);
        }

    }

    @Override
    public void delete(Long id) {
        // First delete all stock items for this stock
        String deleteItemsSql = "DELETE FROM stock_items WHERE stock_id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(deleteItemsSql)) {
            pstmt.setLong(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting stock items", e);
        }

        // Then delete the stock
        String deleteStockSql = "DELETE FROM stocks WHERE id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(deleteStockSql)) {
            pstmt.setLong(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting stock", e);
        }
    }

    @Override
    public boolean exists(Long id) {
        String sql = "SELECT 1 FROM stocks WHERE id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error checking if stock exists", e);
        }
    }

    @Override
    public Optional<StockItem> findStockItemByProductId(Long productId) {
        String sql = "SELECT si.id, si.quantity, " +
                "p.id as product_id, p.code, p.name, p.description, p.price " +
                "FROM stock_items si " +
                "JOIN products p ON si.product_id = p.id " +
                "WHERE p.id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, productId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(extractStockItemFromResultSet(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error finding stock item by product id", e);
        }
        return Optional.empty();
    }

    @Override
    public void updateStockItemQuantity(Long productId, Integer quantity) {
        String sql = "UPDATE stock_items SET quantity = ? WHERE product_id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, quantity);
            pstmt.setLong(2, productId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error updating stock item quantity", e);
        }
    }
}