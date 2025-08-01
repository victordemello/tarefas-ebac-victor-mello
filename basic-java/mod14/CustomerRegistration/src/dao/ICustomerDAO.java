package dao;

import domain.Customer;
import exceptions.CpfAlreadyExistsException;
import exceptions.CpfNotFoundException;

import java.util.Collection;

public interface ICustomerDAO {
        public void register(Customer customer) throws CpfAlreadyExistsException;
        public void deleteCustomer(Long cpf) throws CpfNotFoundException;
        public void updateCustomer(Customer customerDAO) throws CpfNotFoundException;
        public Customer searchCustomer(Long cpf) throws CpfNotFoundException;
        public Collection<Customer> searchAllCustomers();
}
