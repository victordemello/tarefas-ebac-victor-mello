import org.junit.jupiter.api.*;
import org.mod30task.dao.CustomerDAO;
import org.mod30task.domain.Customer.*;
import org.mod30task.services.CustomerService;

import java.util.Optional;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CustomerServiceTest {

    private static CustomerService service;
    private static CustomerDomain customer;

    @BeforeAll
    static void setup() {
        service = new CustomerService(new CustomerDAO());

        Address address = new Address(null, "Rua ABC", 100, "SP", "SP", "01000-000");
        ContactInfo contact = new ContactInfo(null, 11990001111L, "cliente@teste.com");
        customer = new CustomerDomain(null, "Fulano", 12345678901L, contact, address);

        service.create(customer);
    }

    @Test
    @Order(1)
    void shouldCreateCustomer() {
        Assertions.assertNotNull(customer.getId());
    }

    @Test
    @Order(2)
    void shouldFindCustomerById() {
        Optional<CustomerDomain> found = service.findById(customer.getId());
        Assertions.assertTrue(found.isPresent());
        Assertions.assertEquals("Fulano", found.get().getName());
    }

    @Test
    @Order(3)
    void shouldUpdateCustomer() {
        customer.setName("Fulano Atualizado");
        service.update(customer);

        Optional<CustomerDomain> updated = service.findById(customer.getId());
        Assertions.assertEquals("Fulano Atualizado", updated.get().getName());
    }

    @Test
    @Order(4)
    void shouldDeleteCustomer() {
        service.delete(customer.getId());
        Assertions.assertFalse(service.findById(customer.getId()).isPresent());
    }
}
