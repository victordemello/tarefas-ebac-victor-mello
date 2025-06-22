package com.mello.taskmod38.service;

import com.mello.taskmod38.domain.Customer;

import java.util.List;

public interface ICustomerService {
    void registerCustomer(Customer customer);
    List<Customer> getAllCustomers();
}
