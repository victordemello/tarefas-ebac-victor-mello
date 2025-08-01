package org.mod30task.dao;

import org.mod30task.dao.jdbc.ConnectionFactory;
import org.mod30task.domain.Customer.CustomerDomain;
import org.mod30task.domain.Product.ProductDomain;
import org.mod30task.domain.Sale.*;

import java.math.BigDecimal;
import java.sql.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SaleDAO implements ISaleDAO {

    @Override
    public void create(SaleDomain sale) {
        String sql = "INSERT INTO sales (code, customer_id, total, date_of_sale, status) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, sale.getCode());
            pstmt.setLong(2, sale.getCustomer().getId());
            pstmt.setBigDecimal(3, sale.getTotal());
            pstmt.setTimestamp(4, Timestamp.from(sale.getDateOfSale()));
            pstmt.setString(5, sale.getStatus().getDescription());

            pstmt.executeUpdate();

            try (ResultSet keys = pstmt.getGeneratedKeys()) {
                if (keys.next()) {
                    Long saleId = keys.getLong(1);
                    sale.setId(saleId);
                    for (SaleItem item : sale.getItems()) {
                        insertSaleItem(saleId, item);
                    }
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error creating sale", e);
        }
    }

    private void insertSaleItem(Long saleId, SaleItem item) {
        String sql = "INSERT INTO sale_items (sale_id, product_id, quantity) VALUES (?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, saleId);
            pstmt.setLong(2, item.getProductDomain().getId());
            pstmt.setInt(3, item.getQuantity());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error inserting sale item", e);
        }
    }

    @Override
    public Optional<SaleDomain> findById(Long id) {
        String sql = "SELECT * FROM sales WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, id);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(extractSale(rs));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error finding sale by ID", e);
        }

        return Optional.empty();
    }

    @Override
    public Optional<SaleDomain> findByCode(String code) {
        String sql = "SELECT * FROM sales WHERE code = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, code);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(extractSale(rs));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error finding sale by code", e);
        }

        return Optional.empty();
    }

    @Override
    public List<SaleDomain> findByCustomerId(Long customerId) {
        return findSalesByField("customer_id", customerId);
    }

    @Override
    public List<SaleDomain> findByStatus(String status) {
        return findSalesByField("status", status);
    }

    @Override
    public List<SaleDomain> findByDateRange(Instant start, Instant end) {
        String sql = "SELECT * FROM sales WHERE date_of_sale BETWEEN ? AND ?";
        List<SaleDomain> sales = new ArrayList<>();

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setTimestamp(1, Timestamp.from(start));
            pstmt.setTimestamp(2, Timestamp.from(end));

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    sales.add(extractSale(rs));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error finding sales by date range", e);
        }

        return sales;
    }

    private List<SaleDomain> findSalesByField(String field, Object value) {
        String sql = "SELECT * FROM sales WHERE " + field + " = ?";
        List<SaleDomain> sales = new ArrayList<>();

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setObject(1, value);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    sales.add(extractSale(rs));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error finding sales by field: " + field, e);
        }

        return sales;
    }

    @Override
    public List<SaleDomain> findAll() {
        String sql = "SELECT * FROM sales";
        List<SaleDomain> sales = new ArrayList<>();

        try (Connection conn = ConnectionFactory.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                sales.add(extractSale(rs));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error finding all sales", e);
        }

        return sales;
    }

    @Override
    public void update(SaleDomain sale) {
        String sql = "UPDATE sales SET code = ?, customer_id = ?, total = ?, date_of_sale = ?, status = ? WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, sale.getCode());
            pstmt.setLong(2, sale.getCustomer().getId());
            pstmt.setBigDecimal(3, sale.getTotal());
            pstmt.setTimestamp(4, Timestamp.from(sale.getDateOfSale()));
            pstmt.setString(5, sale.getStatus().getDescription());
            pstmt.setLong(6, sale.getId());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error updating sale", e);
        }
    }

    @Override
    public void changeStatus(Long saleId, String newStatus) {
        String sql = "UPDATE sales SET status = ? WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, newStatus);
            pstmt.setLong(2, saleId);

            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error changing sale status", e);
        }
    }

    @Override
    public void delete(Long id) {
        try (Connection conn = ConnectionFactory.getConnection()) {
            deleteSaleItems(id, conn);

            String sql = "DELETE FROM sales WHERE id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setLong(1, id);
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting sale", e);
        }
    }

    private void deleteSaleItems(Long saleId, Connection conn) throws SQLException {
        String sql = "DELETE FROM sale_items WHERE sale_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, saleId);
            pstmt.executeUpdate();
        }
    }

    private SaleDomain extractSale(ResultSet rs) throws SQLException {
        Long id = rs.getLong("id");
        String code = rs.getString("code");
        Long customerId = rs.getLong("customer_id");
        BigDecimal total = rs.getBigDecimal("total");
        Instant dateOfSale = rs.getTimestamp("date_of_sale").toInstant();
        String statusStr = rs.getString("status");

        // Aqui você pode usar um CustomerDAO para buscar o cliente se desejar
        CustomerDomain customer = new CustomerDomain();
        customer.setId(customerId); // mínimo necessário

        List<SaleItem> items = findSaleItemsBySaleId(id);

        return new SaleDomain(id, code, customer, items, dateOfSale, new Status(null, statusStr));
    }

    private List<SaleItem> findSaleItemsBySaleId(Long saleId) {
        List<SaleItem> items = new ArrayList<>();

        String sql = "SELECT si.id, si.quantity, " +
                "p.id as product_id, p.code, p.name, p.description, p.price " +
                "FROM sale_items si " +
                "JOIN products p ON si.product_id = p.id " +
                "WHERE si.sale_id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, saleId);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Long id = rs.getLong("id");
                    Integer quantity = rs.getInt("quantity");

                    ProductDomain product = new ProductDomain(
                            rs.getLong("product_id"),
                            rs.getString("code"),
                            rs.getString("name"),
                            rs.getString("description"),
                            rs.getBigDecimal("price")
                    );

                    items.add(new SaleItem(id, product, quantity));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error loading sale items", e);
        }

        return items;
    }

    @Override
    public boolean exists(Long id) {
        String sql = "SELECT 1 FROM sales WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next();
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error checking if sale exists", e);
        }
    }
}
