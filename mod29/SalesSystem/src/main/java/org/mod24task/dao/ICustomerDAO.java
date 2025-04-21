package org.mod24task.dao;

import org.mod24task.domain.Customer;

import java.util.List;

public interface ICustomerDAO {
    int register(Customer customer) throws Exception;
    int updateCustomer(Customer customer) throws  Exception;
    int deleteCustomer(Customer customer) throws Exception;
    Customer findByCode(String id) throws Exception;
    List<Customer> searchAll() throws Exception;

}
