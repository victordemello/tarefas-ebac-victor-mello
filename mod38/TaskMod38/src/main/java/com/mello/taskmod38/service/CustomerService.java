package com.mello.taskmod38.service;

import com.mello.taskmod38.dao.CustomerDao;
import com.mello.taskmod38.dao.ICustomerDao;
import com.mello.taskmod38.domain.Customer;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class CustomerService implements ICustomerService {

    private ICustomerDao customerDao;

    public CustomerService () {

    }

    @Inject
    public void setCustomerDao(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    @Override
    public void registerCustomer(Customer customer) {
        customerDao.save(customer);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerDao.findAll();
    }
}
