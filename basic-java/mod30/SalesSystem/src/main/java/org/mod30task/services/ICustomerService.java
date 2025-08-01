package org.mod30task.services;

import org.mod30task.domain.Customer.CustomerDomain;

import java.util.List;
import java.util.Optional;

public interface ICustomerService {
    void create(CustomerDomain customer);
    Optional<CustomerDomain> findById(Long id);
    Optional<CustomerDomain> findBySsn(Long ssn);
    List<CustomerDomain> findAll();
    void update(CustomerDomain customer);
    void delete(Long id);
}