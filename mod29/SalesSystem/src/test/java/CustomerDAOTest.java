import org.junit.Before;
import org.junit.Test;
import org.mod24task.dao.CustomerDAO;
import org.mod24task.dao.ICustomerDAO;
import org.mod24task.domain.Customer;

import java.util.List;

import org.junit.After;

import static org.junit.Assert.*;

/**
 * @author victor.mello
 */
public class CustomerDAOTest {

    private ICustomerDAO customerDAO;
    private Customer johnDoe;
    private Customer maryDoe;
    private final String JOHN_CODE = "947583645";
    private final String MARY_CODE = "947583641";

    @Before
    public void setUp() throws Exception{
        // Initialize DAO and test data
        customerDAO = new CustomerDAO();
        johnDoe = new Customer(JOHN_CODE, "John Doe");
        maryDoe = new Customer(MARY_CODE, "Mary Doe");

        // Clean any existing test data
        cleanTestData();
    }

    @After
    public void tearDown() throws Exception{
        // Ensure cleanup after each test
        cleanTestData();
    }

    private void cleanTestData() throws Exception{
        customerDAO.deleteCustomer(johnDoe);
        customerDAO.deleteCustomer(maryDoe);
    }

    @Test
    public void testRegister() throws Exception {
        // Arrange - done in setUp

        // Act
        int result = customerDAO.register(johnDoe);
        Customer retrievedCustomer = customerDAO.findByCode(johnDoe.getCode());

        // Assert
        assertEquals("Insert should affect 1 row", 1, result);
        assertNotNull("Customer should be found after registration", retrievedCustomer);
        assertEquals("Customer code should match", johnDoe.getCode(), retrievedCustomer.getCode());
        assertEquals("Customer name should match", johnDoe.getName(), retrievedCustomer.getName());
    }

    @Test
    public void testUpdateCustomer() throws Exception {
        // Arrange
        customerDAO.register(johnDoe);
        Customer retrievedCustomer = customerDAO.findByCode(johnDoe.getCode());
        Customer updatedCustomer = new Customer(retrievedCustomer.getId(), JOHN_CODE, "John Updated");

        // Act
        int updateResult = customerDAO.updateCustomer(updatedCustomer);
        Customer afterUpdateCustomer = customerDAO.findByCode(JOHN_CODE);

        // Assert
        assertEquals("Update should affect 1 row", 1, updateResult);
        assertEquals("Customer name should be updated", "John Updated", afterUpdateCustomer.getName());
        assertEquals("Customer code should remain the same", JOHN_CODE, afterUpdateCustomer.getCode());
    }

    @Test
    public void testFindByCode() throws Exception {
        // Arrange
        customerDAO.register(johnDoe);

        // Act
        Customer foundCustomer = customerDAO.findByCode(JOHN_CODE);

        // Assert
        assertNotNull("Customer should be found by code", foundCustomer);
        assertEquals("Customer code should match", JOHN_CODE, foundCustomer.getCode());
        assertEquals("Customer name should match", johnDoe.getName(), foundCustomer.getName());
    }

    @Test
    public void testFindByCodeNonExistent() throws Exception {
        // Arrange - no test data needed

        // Act
        Customer foundCustomer = customerDAO.findByCode("NONEXISTENT_CODE");

        // Assert
        assertNull("Should return null for non-existent customer", foundCustomer);
    }

    @Test
    public void testDeleteCustomer() throws Exception {
        // Arrange
        customerDAO.register(johnDoe);
        Customer beforeDelete = customerDAO.findByCode(JOHN_CODE);
        assertNotNull("Test setup failed: Customer not found before delete", beforeDelete);

        // Act
        int deleteResult = customerDAO.deleteCustomer(johnDoe);
        Customer afterDelete = customerDAO.findByCode(JOHN_CODE);

        // Assert
        assertEquals("Delete should affect 1 row", 1, deleteResult);
        assertNull("Customer should not be found after deletion", afterDelete);
    }

    @Test
    public void testSearchAll() throws Exception {
        // Arrange
        customerDAO.register(johnDoe);
        customerDAO.register(maryDoe);

        // Act
        List<Customer> allCustomers = customerDAO.searchAll();

        // Assert
        assertNotNull("Customer list should not be null", allCustomers);
        assertTrue("List should contain at least 2 customers", allCustomers.size() >= 2);

        // Verify both test customers are in the list
        boolean containsJohn = false;
        boolean containsMary = false;

        for (Customer customer : allCustomers) {
            if (JOHN_CODE.equals(customer.getCode())) {
                containsJohn = true;
            }
            if (MARY_CODE.equals(customer.getCode())) {
                containsMary = true;
            }
        }

        assertTrue("List should contain John Doe", containsJohn);
        assertTrue("List should contain Mary Doe", containsMary);
    }

    @Test
    public void testUpdateNonExistentCustomer() throws Exception {
        // Arrange
        Customer nonExistentCustomer = new Customer(999L, "NONEXISTENT", "Nonexistent Name");

        // Act
        int result = customerDAO.updateCustomer(nonExistentCustomer);

        // Assert
        assertEquals("Update of non-existent customer should affect 0 rows", 0, result);
    }

    @Test
    public void testDeleteNonExistentCustomer() throws Exception {
        // Arrange
        Customer nonExistentCustomer = new Customer("NONEXISTENT", "Nonexistent Name");

        // Act
        int result = customerDAO.deleteCustomer(nonExistentCustomer);

        // Assert
        assertEquals("Delete of non-existent customer should affect 0 rows", 0, result);
    }
}
