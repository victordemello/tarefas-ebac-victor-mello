import org.junit.jupiter.api.*;
import org.mod30task.dao.ICustomerDAO;
import org.mod30task.dao.CustomerDAO;
import org.mod30task.domain.Customer.Address;
import org.mod30task.domain.Customer.ContactInfo;
import org.mod30task.domain.Customer.CustomerDomain;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class CustomerDAOTest {

    private ICustomerDAO customerDAO;
    private CustomerDomain testCustomer;

    @BeforeEach
    public void setUp() {
        customerDAO = new CustomerDAO();
        // Arrange: Cria um cliente de teste com endereço e contato
        Address address = new Address(null, "Test Street", 123, "Test City", "TS", "12345");
        ContactInfo contactInfo = new ContactInfo(null, 999999999L, "test@example.com");
        testCustomer = new CustomerDomain(null, "Test Customer", 1234567890L, contactInfo, address);
        customerDAO.create(testCustomer);
        assertNotNull(testCustomer.getId(), "Cliente deve ter um ID após a criação");
    }

    @AfterEach
    public void tearDown() {
        if (testCustomer.getId() != null) {
            customerDAO.delete(testCustomer.getId());
        }
    }

    @Test
    public void testCreateAndFindById() {
        // Arrange: Cria um novo cliente
        Address newAddress = new Address(null, "New Street", 456, "New City", "NS", "54321");
        ContactInfo newContactInfo = new ContactInfo(null, 888888888L, "new@example.com");
        CustomerDomain newCustomer = new CustomerDomain(null, "New Customer", 11122233344L, newContactInfo, newAddress);

        // Act: Salva o cliente no banco
        customerDAO.create(newCustomer);

        // Assert: Verifica se o cliente foi salvo e se o ID foi gerado
        assertNotNull(newCustomer.getId(), "Cliente deve ter um ID após ser criado");

        // Act: Busca o cliente por ID
        Optional<CustomerDomain> foundCustomer = customerDAO.findById(newCustomer.getId());

        // Assert: Verifica se o cliente foi encontrado e se os dados estão corretos
        assertTrue(foundCustomer.isPresent(), "Cliente deve ser encontrado por ID");
        assertEquals("New Customer", foundCustomer.get().getName(), "Nome do cliente deve ser igual");
        assertEquals(11122233344L, foundCustomer.get().getSsn(), "SSN do cliente deve ser igual");
        assertEquals("New Street", foundCustomer.get().getAddress().getStreet(), "Rua deve ser igual");
        assertEquals("new@example.com", foundCustomer.get().getContactInfo().getEmail(), "Email deve ser igual");
    }

    @Test
    public void testFindBySsn() {
        // Act: Busca o cliente pelo SSN
        Optional<CustomerDomain> foundCustomer = customerDAO.findBySsn(1234567890L);

        // Assert: Verifica se o cliente foi encontrado pelo SSN
        assertTrue(foundCustomer.isPresent(), "Cliente deve ser encontrado por SSN");
        assertEquals("Test Customer", foundCustomer.get().getName(), "Nome deve ser igual");
    }

    @Test
    public void testFindAll() {
        // Act: Busca todos os clientes
        List<CustomerDomain> allCustomers = customerDAO.findAll();

        // Assert: Verifica se a lista não está vazia e contém o cliente de teste
        assertFalse(allCustomers.isEmpty(), "A lista de clientes não deve estar vazia");
        assertTrue(allCustomers.stream().anyMatch(c -> c.getSsn().equals(1234567890L)), "A lista deve conter o cliente de teste");
    }

    @Test
    public void testUpdate() {
        // Arrange: Modifica os dados do cliente de teste
        testCustomer.setName("Updated Customer");
        testCustomer.setSsn(9876543210L);
        testCustomer.getAddress().setCity("Updated City");
        testCustomer.getContactInfo().setEmail("updated@example.com");

        // Act: Atualiza o cliente
        customerDAO.update(testCustomer);

        // Assert: Busca o cliente atualizado e verifica se as mudanças foram aplicadas
        Optional<CustomerDomain> updatedCustomer = customerDAO.findById(testCustomer.getId());
        assertTrue(updatedCustomer.isPresent(), "Cliente deve ser encontrado após atualização");
        assertEquals("Updated Customer", updatedCustomer.get().getName(), "Nome deve ser atualizado");
        assertEquals(9876543210L, updatedCustomer.get().getSsn(), "SSN deve ser atualizado");
        assertEquals("Updated City", updatedCustomer.get().getAddress().getCity(), "Cidade deve ser atualizada");
        assertEquals("updated@example.com", updatedCustomer.get().getContactInfo().getEmail(), "Email deve ser atualizado");
    }

    @Test
    public void testDelete() {
        // Act: Deleta o cliente de teste
        customerDAO.delete(testCustomer.getId());

        // Assert: Tenta encontrar o cliente deletado e verifica se ele não existe
        Optional<CustomerDomain> deletedCustomer = customerDAO.findById(testCustomer.getId());
        assertFalse(deletedCustomer.isPresent(), "Cliente não deve ser encontrado após ser deletado");
    }
}