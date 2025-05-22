package org.mod30task.dao;

import org.mod30task.domain.Customer.CustomerDomain;

import java.util.Optional;

public interface ICustomerDAO extends IGenericDAO<CustomerDomain, Long> {
    Optional<CustomerDomain> findBySsn(Long ssn);
}
