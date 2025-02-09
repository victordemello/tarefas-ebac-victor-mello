package dao;

import domain.Customer;
import exceptions.CpfAlreadyExistsException;

public class CustomerDataInitializer {

    /**
     * Populates the iCustomerDAO variable in memory with some inputs
     * @param customerDAO
     */
    public static void initializeData(ICustomerDAO customerDAO) {
        try {
            customerDAO.register(new Customer("João Silva", 12345678901L, 9876543210L, "Rua A", 100, "Salvador", "BA"));
            customerDAO.register(new Customer("Maria Oliveira", 23456789012L, 9123456789L, "Rua B", 200, "Salvador", "BA"));
            customerDAO.register(new Customer("Carlos Souza", 34567890123L, 9234567890L, "Rua C", 300, "Salvador", "BA"));
            customerDAO.register(new Customer("Ana Costa", 45678901234L, 9345678901L, "Rua D", 400, "Salvador", "BA"));
            customerDAO.register(new Customer("Lucas Pereira", 56789012345L, 9456789012L, "Rua E", 500, "Salvador", "BA"));
            customerDAO.register(new Customer("Juliana Santos", 67890123456L, 9567890123L, "Rua F", 600, "Salvador", "BA"));
            customerDAO.register(new Customer("Fernanda Rocha", 78901234567L, 9678901234L, "Rua G", 700, "Salvador", "BA"));
            customerDAO.register(new Customer("Ricardo Almeida", 89012345678L, 9789012345L, "Rua H", 800, "Salvador", "BA"));
            customerDAO.register(new Customer("Patricia Lima", 90123456789L, 9890123456L, "Rua I", 900, "Salvador", "BA"));
            customerDAO.register(new Customer("Fábio Martins", 10234567890L, 9911234567L, "Rua J", 1000, "Salvador", "BA"));
        } catch (CpfAlreadyExistsException e) {
            System.out.println(e.getMessage());
        }
    }
}
