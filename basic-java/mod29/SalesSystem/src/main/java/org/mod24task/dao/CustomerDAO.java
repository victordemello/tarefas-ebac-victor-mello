package org.mod24task.dao;

import org.mod24task.domain.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO implements ICustomerDAO {

    private String getSqlInsert() {
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO tb_customer (id, code, name) ");
        sb.append("VALUES (nextval('seq_customer_id'),?,?)");
        return sb.toString();
    }

    private void addParameterInInsert(PreparedStatement stm, Customer customer) throws SQLException {
        stm.setString(1, customer.getCode());
        stm.setString(2, customer.getName());
    }

    private void addParameterInUpdate(PreparedStatement stm, Customer customer) throws SQLException {
        stm.setString(1, customer.getName());
        stm.setString(2, customer.getCode());
        stm.setLong(3, customer.getId());
    }

    private void addParameterInSelect(PreparedStatement stm, String code) throws SQLException{
        stm.setString(1, code);
    }

    private void addParameterInDelete(PreparedStatement stm, Customer customer) throws  SQLException{
        stm.setString(1, customer.getCode());
    }

    private void closeConnection(Connection connection, PreparedStatement stm, ResultSet rs) {
        try {
            if (rs != null && !rs.isClosed()) {
                rs.close();
            }
            if (stm != null && !stm.isClosed()) {
                stm.close();
            }
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }

    private String getSQLUpdate(){
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE tb_customer ");
        sb.append("SET name = ?, code = ? ");
        sb.append("WHERE id = ?");
        return sb.toString();
    }

    private String getSqlSelect() {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM tb_customer ");
        sb.append("WHERE code = ?");
        return sb.toString();
    }

    private String getSqlDelete() {
        StringBuilder sb = new StringBuilder();
        sb.append("DELETE FROM tb_customer ");
        sb.append("WHERE code = ?");
        return sb.toString();
    }

    private String getSqlSelectAll(){
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM tb_customer");
        return sb.toString();
    }

    @Override
    public int register(Customer customer) throws Exception {

        Connection connection = null;
        PreparedStatement stm = null; // sql execute
        try{
            connection = ConnectionFactory.getConnection();
            String sql = getSqlInsert();
            stm = connection.prepareStatement(sql);
            addParameterInInsert(stm, customer);
            return stm.executeUpdate();

        }catch (Exception ex){
            throw ex;
        }finally {
            closeConnection(connection, stm, null);
        }
    }

    @Override
    public int updateCustomer(Customer customer) throws Exception {
        Connection connection = null;
        PreparedStatement stm = null;

        try{
            connection = ConnectionFactory.getConnection();
            String sql = getSQLUpdate();
            stm = connection.prepareStatement(sql);
            addParameterInUpdate(stm, customer);
            return stm.executeUpdate();
        }catch (Exception ex){
            throw ex;
        }
        finally {
            closeConnection(connection, stm, null);
        }
    }

    @Override
    public int deleteCustomer(Customer customer) throws Exception {
        Connection connection = null;
        PreparedStatement stm = null;
        try {
            connection = ConnectionFactory.getConnection();
            String sql = getSqlDelete();
            stm = connection.prepareStatement(sql);
            addParameterInDelete(stm, customer);
            return stm.executeUpdate();
        } catch(Exception e) {
            throw e;
        } finally {
            closeConnection(connection, stm, null);
        }
    }

    @Override
    public Customer findByCode(String code) throws Exception {
        Connection connection = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        Customer customer = null;
        try {
            connection = ConnectionFactory.getConnection();
            String sql = getSqlSelect();
            stm = connection.prepareStatement(sql);
            addParameterInSelect(stm, code);
            rs = stm.executeQuery();

            if (rs.next()) {
                customer = new Customer();
                Long id = rs.getLong("id");
                String name = rs.getString("name");
                String cd = rs.getString("code");
                customer.setId(id);
                customer.setName(name);
                customer.setCode(cd);
            }
        } catch(Exception e) {
            throw e;
        } finally {
            closeConnection(connection, stm, rs);
        }
        return customer;
    }

    @Override
    public List<Customer> searchAll() throws Exception {
        Connection connection = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<Customer> list = new ArrayList<>();
        Customer customer = null;
        try {
            connection = ConnectionFactory.getConnection();
            String sql = getSqlSelectAll();
            stm = connection.prepareStatement(sql);
            rs = stm.executeQuery();

            while (rs.next()) {
                customer = new Customer();
                Long id = rs.getLong("id");
                String name = rs.getString("name");
                String cd = rs.getString("code");
                customer.setId(id);
                customer.setName(name);
                customer.setCode(cd);
                list.add(customer);
            }
        } catch(Exception e) {
            throw e;
        } finally {
            closeConnection(connection, stm, rs);
        }
        return list;
    }
}
