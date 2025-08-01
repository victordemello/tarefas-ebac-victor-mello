package dao;

import domain.Customer;
import exceptions.CpfAlreadyExistsException;
import exceptions.CpfNotFoundException;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author victor.mello
 */
public class CustomerMapDAOImpl implements ICustomerDAO{

    private Map<Long, Customer> map;

    public CustomerMapDAOImpl(){
        map = new HashMap<>();
    }

    /**
     *
     * @param customer
     * @throws CpfAlreadyExistsException
     */
    @Override
    public void register(Customer customer) throws CpfAlreadyExistsException {
        if(map.containsKey(customer.getCpf())){
            throw new CpfAlreadyExistsException("CPF already registered.");
        }

        map.put(customer.getCpf(), customer);
    }

    /**
     *
     * @param cpf
     * @throws CpfNotFoundException
     */
    @Override
    public void deleteCustomer(Long cpf) throws CpfNotFoundException {
        Customer customer = searchCustomer(cpf);
        map.remove(customer.getCpf(), customer);
    }

    /**
     *
     * @param customerDAO
     * @throws CpfNotFoundException
     */
    @Override
    public void updateCustomer(Customer customerDAO) throws CpfNotFoundException{
        if(searchCustomer(customerDAO.getCpf()) != null){
            map.replace(customerDAO.getCpf(), customerDAO);
        }
    }

    /**
     *
     * @param cpf
     * @return return a Customer reference type
     * @throws CpfNotFoundException
     */
    @Override
    public Customer searchCustomer(Long cpf) throws CpfNotFoundException {
        if(!map.containsKey(cpf)){
            throw new CpfNotFoundException("CPF " + cpf +" was not found");
        }

        return map.get(cpf);
    }

    /**
     *
     * @return return a list of all customers
     */
    @Override
    public Collection<Customer> searchAllCustomers() {
        return map.values();
    }
}
