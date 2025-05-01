import org.junit.jupiter.api.*;
import org.mod30task.dao.*;
import org.mod30task.domain.Customer.*;
import org.mod30task.domain.Product.*;
import org.mod30task.domain.Sale.*;

import java.math.BigDecimal;
import java.time.Instant;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SaleDAOTest {

    private ICustomerDAO customerDAO;
    private IProductDAO productDAO;
    private ISaleDAO saleDAO;

    @BeforeAll
    void setup() {
        customerDAO = new CustomerDAO();
        productDAO = new ProductDAO();
        saleDAO = new SaleDAO();
    }

    @BeforeEach
    void cleanDatabase() {
        // DAO Instances
        ISaleDAO saleDAO = new SaleDAO();
        IStockDAO stockDAO = new StockDAO();
        IProductDAO productDAO = new ProductDAO();
        ICustomerDAO customerDAO = new CustomerDAO();

        // 1. Deletar sales (deleta sale_items em cascade)
        saleDAO.findAll().forEach(sale -> saleDAO.delete(sale.getId()));

        // 2. Deletar stocks (deleta stock_items em cascade)
        stockDAO.findAll().forEach(stock -> stockDAO.delete(stock.getId()));

        // 3. Deletar products
        productDAO.findAll().forEach(product -> productDAO.delete(product.getId()));

        // 4. Deletar customers
        customerDAO.findAll().forEach(customer -> customerDAO.delete(customer.getId()));
    }

    @Test
    void testCreateSale() {
        CustomerDomain customer = createCustomer();
        customerDAO.create(customer);

        ProductDomain product = createProduct();
        productDAO.create(product);

        SaleItem item = new SaleItem(null, product, 2);

        SaleDomain sale = new SaleDomain();
        sale.setCode(TestUtils.uniqueCode("SALE"));
        sale.setCustomer(customer);
        sale.addItem(item);
        sale.setDateOfSale(Instant.now());
        sale.setStatus(new Status(null, Status.PAID));

        saleDAO.create(sale);

        Assertions.assertNotNull(sale.getId());
    }

    private CustomerDomain createCustomer() {
        Address address = new Address(null, "Rua A", 123, "Cidade", "Estado", "00000-000");
        ContactInfo contact = new ContactInfo(null, 123456789L, "test@example.com");
        return new CustomerDomain(null, "Cliente Teste", System.nanoTime(), contact, address);
    }

    private ProductDomain createProduct() {
        return new ProductDomain(null, TestUtils.uniqueCode("PROD"), "Produto Teste", "Descrição", new BigDecimal("19.99"));
    }
}
