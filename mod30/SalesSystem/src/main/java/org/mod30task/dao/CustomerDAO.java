package org.mod30task.dao;

import org.mod30task.dao.jdbc.ConnectionFactory;
import org.mod30task.domain.Customer.Address;
import org.mod30task.domain.Customer.ContactInfo;
import org.mod30task.domain.Customer.CustomerDomain;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CustomerDAO implements ICustomerDAO {

    @Override
    public void create(CustomerDomain customer) {
        String sql = "INSERT INTO customers (name, ssn) VALUES (?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, customer.getName());
            pstmt.setLong(2, customer.getSsn());

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating customer failed, no rows affected.");
            }

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    customer.setId(generatedKeys.getLong(1));

                    // Save address and contact info
                    saveAddress(customer.getId(), customer.getAddress());
                    saveContactInfo(customer.getId(), customer.getContactInfo());
                } else {
                    throw new SQLException("Creating customer failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error creating customer", e);
        }
    }

    private void saveAddress(Long customerId, Address address) {
        String sql = "INSERT INTO addresses (customer_id, street, number, city, state, zip_code) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setLong(1, customerId);
            pstmt.setString(2, address.getStreet());
            pstmt.setInt(3, address.getNumber());
            pstmt.setString(4, address.getCity());
            pstmt.setString(5, address.getState());
            pstmt.setString(6, address.getZipCode());

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating address failed, no rows affected.");
            }

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    address.setId(generatedKeys.getLong(1));
                } else {
                    throw new SQLException("Creating address failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error creating address", e);
        }
    }

    private void saveContactInfo(Long customerId, ContactInfo contactInfo) {
        String sql = "INSERT INTO contact_info (customer_id, phone, email) VALUES (?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setLong(1, customerId);
            pstmt.setLong(2, contactInfo.getPhone());
            pstmt.setString(3, contactInfo.getEmail());

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating contact info failed, no rows affected.");
            }

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    contactInfo.setId(generatedKeys.getLong(1));
                } else {
                    throw new SQLException("Creating contact info failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error creating contact info", e);
        }
    }

    @Override
    public Optional<CustomerDomain> findById(Long id) {
        String sql = "SELECT c.id, c.name, c.ssn, " +
                "a.id as address_id, a.street, a.number, a.city, a.state, a.zip_code, " +
                "ci.id as contact_id, ci.phone, ci.email " +
                "FROM customers c " +
                "LEFT JOIN addresses a ON c.id = a.customer_id " +
                "LEFT JOIN contact_info ci ON c.id = ci.customer_id " +
                "WHERE c.id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, id);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(extractCustomerFromResultSet(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error finding customer by id", e);
        }

        return Optional.empty();
    }

    @Override
    public Optional<CustomerDomain> findBySsn(Long ssn) {
        String sql = "SELECT c.id, c.name, c.ssn, " +
                "a.id as address_id, a.street, a.number, a.city, a.state, a.zip_code, " +
                "ci.id as contact_id, ci.phone, ci.email " +
                "FROM customers c " +
                "LEFT JOIN addresses a ON c.id = a.customer_id " +
                "LEFT JOIN contact_info ci ON c.id = ci.customer_id " +
                "WHERE c.ssn = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, ssn);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(extractCustomerFromResultSet(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error finding customer by ssn", e);
        }

        return Optional.empty();
    }

    @Override
    public List<CustomerDomain> findAll() {
        List<CustomerDomain> customers = new ArrayList<>();

        String sql = "SELECT c.id, c.name, c.ssn, " +
                "a.id as address_id, a.street, a.number, a.city, a.state, a.zip_code, " +
                "ci.id as contact_id, ci.phone, ci.email " +
                "FROM customers c " +
                "LEFT JOIN addresses a ON c.id = a.customer_id " +
                "LEFT JOIN contact_info ci ON c.id = ci.customer_id";

        try (Connection conn = ConnectionFactory.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                customers.add(extractCustomerFromResultSet(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error finding all customers", e);
        }

        return customers;
    }

    private CustomerDomain extractCustomerFromResultSet(ResultSet rs) throws SQLException {
        Long customerId = rs.getLong("id");
        String name = rs.getString("name");
        Long ssn = rs.getLong("ssn");

        // Extract address
        Long addressId = rs.getLong("address_id");
        String street = rs.getString("street");
        Integer number = rs.getInt("number");
        String city = rs.getString("city");
        String state = rs.getString("state");
        String zipCode = rs.getString("zip_code");
        Address address = new Address(addressId, street, number, city, state, zipCode);

        // Extract contact info
        Long contactId = rs.getLong("contact_id");
        Long phone = rs.getLong("phone");
        String email = rs.getString("email");
        ContactInfo contactInfo = new ContactInfo(contactId, phone, email);

        return new CustomerDomain(customerId, name, ssn, contactInfo, address);
    }

    @Override
    public void update(CustomerDomain customer) {
        String sql = "UPDATE customers SET name = ?, ssn = ? WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, customer.getName());
            pstmt.setLong(2, customer.getSsn());
            pstmt.setLong(3, customer.getId());

            pstmt.executeUpdate();

            // Update address and contact info
            updateAddress(customer.getAddress());
            updateContactInfo(customer.getContactInfo());
        } catch (SQLException e) {
            throw new RuntimeException("Error updating customer", e);
        }
    }

    private void updateAddress(Address address) {
        String sql = "UPDATE addresses SET street = ?, number = ?, city = ?, state = ?, zip_code = ? WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, address.getStreet());
            pstmt.setInt(2, address.getNumber());
            pstmt.setString(3, address.getCity());
            pstmt.setString(4, address.getState());
            pstmt.setString(5, address.getZipCode());
            pstmt.setLong(6, address.getId());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error updating address", e);
        }
    }

    private void updateContactInfo(ContactInfo contactInfo) {
        String sql = "UPDATE contact_info SET phone = ?, email = ? WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, contactInfo.getPhone());
            pstmt.setString(2, contactInfo.getEmail());
            pstmt.setLong(3, contactInfo.getId());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error updating contact info", e);
        }
    }

    @Override
    public void delete(Long id) {
        // First delete related records
        deleteContactInfo(id);
        deleteAddress(id);

        // Then delete customer
        String sql = "DELETE FROM customers WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting customer", e);
        }
    }

    private void deleteAddress(Long customerId) {
        String sql = "DELETE FROM addresses WHERE customer_id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, customerId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting address", e);
        }
    }

    private void deleteContactInfo(Long customerId) {
        String sql = "DELETE FROM contact_info WHERE customer_id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, customerId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting contact info", e);
        }
    }

    @Override
    public boolean exists(Long id) {
        String sql = "SELECT 1 FROM customers WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, id);

            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error checking if customer exists", e);
        }
    }
}
