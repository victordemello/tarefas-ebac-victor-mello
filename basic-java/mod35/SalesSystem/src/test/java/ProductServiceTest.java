import org.junit.jupiter.api.*;
import org.mod30task.dao.ProductDAO;
import org.mod30task.domain.Product.ProductDomain;
import org.mod30task.services.ProductService;

import java.math.BigDecimal;
import java.util.Optional;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProductServiceTest {

    private static ProductService service;
    private static ProductDomain product;

    @BeforeAll
    static void setup() {
        service = new ProductService(new ProductDAO());
        product = new ProductDomain(null, "PRD-SRV", "Produto Serviço", "Descrição", new BigDecimal("15.00"));
        service.create(product);
    }

    @Test
    @Order(1)
    void shouldCreateProduct() {
        Assertions.assertNotNull(product.getId());
    }

    @Test
    @Order(2)
    void shouldFindById() {
        Optional<ProductDomain> found = service.findById(product.getId());
        Assertions.assertTrue(found.isPresent());
        Assertions.assertEquals("Produto Serviço", found.get().getName());
    }

    @Test
    @Order(3)
    void shouldUpdateProduct() {
        product.setName("Atualizado");
        service.update(product);
        Assertions.assertEquals("Atualizado", service.findById(product.getId()).get().getName());
    }

    @Test
    @Order(4)
    void shouldDeleteProduct() {
        service.delete(product.getId());
        Assertions.assertFalse(service.findById(product.getId()).isPresent());
    }
}
