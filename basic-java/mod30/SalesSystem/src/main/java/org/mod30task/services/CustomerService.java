package org.mod30task.services;

import org.mod30task.dao.ICustomerDAO;
import org.mod30task.domain.Customer.CustomerDomain;

import java.util.List;
import java.util.Optional;

public class CustomerService implements ICustomerService {

    private final ICustomerDAO customerDAO;

    public CustomerService(ICustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    @Override
    public void create(CustomerDomain customer) {
        customerDAO.create(customer);
    }

    @Override
    public Optional<CustomerDomain> findById(Long id) {
        return customerDAO.findById(id);
    }

    @Override
    public Optional<CustomerDomain> findBySsn(Long ssn) {
        return customerDAO.findBySsn(ssn);
    }

    @Override
    public List<CustomerDomain> findAll() {
        return customerDAO.findAll();
    }

    @Override
    public void update(CustomerDomain customer) {
        customerDAO.update(customer);
    }

    @Override
    public void delete(Long id) {
        customerDAO.delete(id);
    }
}
