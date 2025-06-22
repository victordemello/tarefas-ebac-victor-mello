package com.mello.taskmod38.dao;

import com.mello.taskmod38.domain.Customer;

import java.util.List;

public interface ICustomerDao {
    void save(Customer customer);
    List<Customer> findAll();
}
