import org.junit.jupiter.api.*;
import org.mod30task.dao.IProductDAO;
import org.mod30task.dao.ProductDAO;
import org.mod30task.domain.Product.ProductDomain;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class ProductDAOTest {

    private IProductDAO productDAO;
    private ProductDomain testProduct;

    @BeforeEach
    public void setUp() {
        productDAO = new ProductDAO();
        // Arrange: Cria um produto de teste para ser usado nos testes
        testProduct = new ProductDomain(null, "TEST-CODE", "Test Product", "Test Description", new BigDecimal("25.00"));
        productDAO.create(testProduct); // Certifica que o produto está no banco antes de cada teste
        assertNotNull(testProduct.getId(), "Produto deve ter um ID após a criação");
    }

    @AfterEach
    public void tearDown() {
        if (testProduct.getId() != null) {
            productDAO.delete(testProduct.getId()); // Limpa o produto após cada teste
        }
    }

    @Test
    public void testCreateAndFindById() {
        // Act: Cria um novo produto e tenta encontrá-lo por ID
        ProductDomain newProduct = new ProductDomain(null, "NEW-CODE", "New Product", "New Description", new BigDecimal("12.50"));
        productDAO.create(newProduct);

        Optional<ProductDomain> foundProduct = productDAO.findById(newProduct.getId());

        // Assert: Verifica se o produto foi criado e encontrado corretamente
        assertTrue(foundProduct.isPresent(), "Produto deve ser encontrado por ID");
        assertEquals("NEW-CODE", foundProduct.get().getCode(), "Código do produto deve ser igual");
        assertEquals("New Product", foundProduct.get().getName(), "Nome do produto deve ser igual");
    }

    @Test
    public void testFindByCode() {
        // Act: Encontra o produto pelo código
        Optional<ProductDomain> foundProduct = productDAO.findByCode("TEST-CODE");

        // Assert: Verifica se o produto foi encontrado pelo código
        assertTrue(foundProduct.isPresent(), "Produto deve ser encontrado por código");
        assertEquals("Test Product", foundProduct.get().getName(), "Nome do produto deve ser igual");
    }

    @Test
    public void testFindAll() {
        // Act: Busca todos os produtos
        List<ProductDomain> allProducts = productDAO.findAll();

        // Assert: Verifica se a lista não está vazia e contém o produto de teste
        assertFalse(allProducts.isEmpty(), "A lista de produtos não deve estar vazia");
        assertTrue(allProducts.stream().anyMatch(p -> p.getCode().equals("TEST-CODE")), "A lista deve conter o produto de teste");
    }

    @Test
    public void testFindByNameContaining() {
        // Act: Busca produtos que contenham "Test" no nome
        List<ProductDomain> foundProducts = productDAO.findByNameContaining("Test");

        // Assert: Verifica se a lista não está vazia
        assertFalse(foundProducts.isEmpty(), "A lista de produtos encontrados não deve estar vazia");
        assertTrue(foundProducts.stream().anyMatch(p -> p.getName().equals("Test Product")), "A lista deve conter o produto de teste");
    }

    @Test
    public void testUpdate() {
        // Arrange: Modifica o produto de teste
        testProduct.setName("Updated Product");
        testProduct.setPrice(new BigDecimal("30.00"));

        // Act: Atualiza o produto
        productDAO.update(testProduct);

        // Assert: Busca o produto atualizado e verifica se as mudanças foram aplicadas
        Optional<ProductDomain> updatedProduct = productDAO.findById(testProduct.getId());
        assertTrue(updatedProduct.isPresent(), "Produto deve ser encontrado após atualização");
        assertEquals("Updated Product", updatedProduct.get().getName(), "Nome deve ser atualizado");
        assertEquals(new BigDecimal("30.00"), updatedProduct.get().getPrice(), "Preço deve ser atualizado");
    }

    @Test
    public void testDelete() {
        // Act: Deleta o produto de teste
        productDAO.delete(testProduct.getId());

        // Assert: Tenta encontrar o produto deletado e verifica se ele não existe
        Optional<ProductDomain> deletedProduct = productDAO.findById(testProduct.getId());
        assertFalse(deletedProduct.isPresent(), "Produto não deve ser encontrado após ser deletado");
    }
}