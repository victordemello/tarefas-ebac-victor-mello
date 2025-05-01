import org.junit.jupiter.api.*;
import org.mod30task.dao.*;
import org.mod30task.domain.Customer.*;
import org.mod30task.domain.Product.ProductDomain;
import org.mod30task.domain.Sale.*;
import org.mod30task.services.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SaleServiceTest {

    private ISaleService saleService;
    private ISaleDAO saleDAO;

    private ICustomerDAO customerDAO;
    private IProductDAO productDAO;

    @BeforeAll
    void setup() {
        saleDAO = new SaleDAO();
        saleService = new SaleService(saleDAO);
        customerDAO = new CustomerDAO();
        productDAO = new ProductDAO();
    }

    @BeforeEach
    void clean() {
        saleDAO.findAll().forEach(s -> saleDAO.delete(s.getId()));
        productDAO.findAll().forEach(p -> productDAO.delete(p.getId()));
        customerDAO.findAll().forEach(c -> customerDAO.delete(c.getId()));
    }

    @Test
    void testCreateSale() {
        CustomerDomain customer = createCustomer();
        customerDAO.create(customer);

        ProductDomain product = createProduct();
        productDAO.create(product);

        SaleItem item = new SaleItem(null, product, 2);

        SaleDomain sale = new SaleDomain();
        sale.setCode("SALE-" + System.nanoTime());
        sale.setCustomer(customer);
        sale.addItem(item);
        sale.setDateOfSale(Instant.now());
        sale.setStatus(new Status(null, Status.PAID));

        saleService.create(sale);

        Assertions.assertNotNull(sale.getId());
        Assertions.assertEquals(new BigDecimal("39.98"), sale.getTotal());
    }

    @Test
    void testFindById() {
        SaleDomain sale = prepareAndSaveSale();

        Optional<SaleDomain> found = saleService.findById(sale.getId());

        Assertions.assertTrue(found.isPresent());
        Assertions.assertEquals(sale.getId(), found.get().getId());
    }

    @Test
    void testFindByCode() {
        SaleDomain sale = prepareAndSaveSale();

        Optional<SaleDomain> found = saleService.findByCode(sale.getCode());

        Assertions.assertTrue(found.isPresent());
        Assertions.assertEquals(sale.getCode(), found.get().getCode());
    }

    @Test
    void testFindByCustomerId() {
        SaleDomain sale = prepareAndSaveSale();

        List<SaleDomain> sales = saleService.findByCustomerId(sale.getCustomer().getId());

        Assertions.assertFalse(sales.isEmpty());
        Assertions.assertEquals(sale.getCustomer().getId(), sales.get(0).getCustomer().getId());
    }

    @Test
    void testChangeStatus() {
        SaleDomain sale = prepareAndSaveSale();

        saleService.changeStatus(sale.getId(), Status.SHIPPED);

        Optional<SaleDomain> updated = saleService.findById(sale.getId());
        Assertions.assertTrue(updated.isPresent());
        Assertions.assertEquals(Status.SHIPPED, updated.get().getStatus().getDescription());
    }

    // ========== MÉTODOS DE APOIO ==========

    private SaleDomain prepareAndSaveSale() {
        CustomerDomain customer = createCustomer();
        customerDAO.create(customer);

        ProductDomain product = createProduct();
        productDAO.create(product);

        SaleItem item = new SaleItem(null, product, 2);
        SaleDomain sale = new SaleDomain();
        sale.setCode("SALE-" + System.nanoTime());
        sale.setCustomer(customer);
        sale.addItem(item);
        sale.setDateOfSale(Instant.now());
        sale.setStatus(new Status(null, Status.PENDING));

        saleService.create(sale);
        return sale;
    }

    private CustomerDomain createCustomer() {
        Address address = new Address(null, "Rua ABC", 123, "Cidade", "Estado", "00000-000");
        ContactInfo contact = new ContactInfo(null, 123456789L, "cliente@example.com");
        return new CustomerDomain(null, "Cliente Teste", System.nanoTime(), contact, address);
    }

    private ProductDomain createProduct() {
        return new ProductDomain(null, "PROD-" + System.nanoTime(), "Produto Teste", "Descrição", new BigDecimal("19.99"));
    }
}
