import org.junit.Before;
import org.junit.Test;
import org.junit.After;
import org.mod24task.dao.ProductDAO;
import org.mod24task.dao.IProductDAO;
import org.mod24task.domain.Product;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Testes unitários para a classe ProductDAO
 */
public class ProductDAOTest {

    private IProductDAO productDAO;
    private Product laptop;
    private Product smartphone;
    private final String LAPTOP_CODE = "PROD001";
    private final String SMARTPHONE_CODE = "PROD002";

    @Before
    public void setUp() throws Exception {
        // Inicializa o DAO e dados de teste
        productDAO = new ProductDAO();
        laptop = new Product(LAPTOP_CODE, "Laptop XPS", "Laptop Dell XPS 15", new BigDecimal("5999.99"));
        smartphone = new Product(SMARTPHONE_CODE, "Smartphone X", "Smartphone avançado", new BigDecimal("2499.99"));

        // Limpa quaisquer dados de teste existentes
        cleanTestData();
    }

    @After
    public void tearDown() throws Exception {
        // Garante limpeza após cada teste
        cleanTestData();
    }

    private void cleanTestData() throws Exception {
        productDAO.delete(laptop);
        productDAO.delete(smartphone);
    }

    @Test
    public void testRegister() throws Exception {
        // Arrange - feito no setUp

        // Act
        int result = productDAO.register(laptop);
        Product retrievedProduct = productDAO.findByCode(laptop.getCode());

        // Assert
        assertEquals("Insert deve afetar 1 linha", 1, result);
        assertNotNull("Produto deve ser encontrado após registro", retrievedProduct);
        assertEquals("Código do produto deve corresponder", laptop.getCode(), retrievedProduct.getCode());
        assertEquals("Nome do produto deve corresponder", laptop.getName(), retrievedProduct.getName());
        assertEquals("Descrição do produto deve corresponder", laptop.getDescription(), retrievedProduct.getDescription());
        assertEquals("Valor do produto deve corresponder", laptop.getValue().doubleValue(), retrievedProduct.getValue().doubleValue(), 0.001);
    }

    @Test
    public void testUpdate() throws Exception {
        // Arrange
        productDAO.register(laptop);
        Product retrievedProduct = productDAO.findByCode(laptop.getCode());
        Product updatedProduct = new Product(
                retrievedProduct.getId(),
                LAPTOP_CODE,
                "Laptop XPS Atualizado",
                "Laptop Dell XPS 15 novo modelo",
                new BigDecimal("6499.99")
        );

        // Act
        int updateResult = productDAO.update(updatedProduct);
        Product afterUpdateProduct = productDAO.findByCode(LAPTOP_CODE);

        // Assert
        assertEquals("Update deve afetar 1 linha", 1, updateResult);
        assertEquals("Nome do produto deve ser atualizado", "Laptop XPS Atualizado", afterUpdateProduct.getName());
        assertEquals("Descrição do produto deve ser atualizada", "Laptop Dell XPS 15 novo modelo", afterUpdateProduct.getDescription());
        assertEquals("Valor do produto deve ser atualizado", new BigDecimal("6499.99").doubleValue(), afterUpdateProduct.getValue().doubleValue(), 0.001);
        assertEquals("Código do produto deve permanecer o mesmo", LAPTOP_CODE, afterUpdateProduct.getCode());
    }

    @Test
    public void testFindByCode() throws Exception {
        // Arrange
        productDAO.register(laptop);

        // Act
        Product foundProduct = productDAO.findByCode(LAPTOP_CODE);

        // Assert
        assertNotNull("Produto deve ser encontrado pelo código", foundProduct);
        assertEquals("Código do produto deve corresponder", LAPTOP_CODE, foundProduct.getCode());
        assertEquals("Nome do produto deve corresponder", laptop.getName(), foundProduct.getName());
        assertEquals("Descrição do produto deve corresponder", laptop.getDescription(), foundProduct.getDescription());
        assertEquals("Valor do produto deve corresponder", laptop.getValue().doubleValue(), foundProduct.getValue().doubleValue(), 0.001);
    }

    @Test
    public void testFindByCodeNonExistent() throws Exception {
        // Arrange - nenhum dado de teste necessário

        // Act
        Product foundProduct = productDAO.findByCode("CODIGO_INEXISTENTE");

        // Assert
        assertNull("Deve retornar null para produto inexistente", foundProduct);
    }

    @Test
    public void testDeleteProduct() throws Exception {
        // Arrange
        productDAO.register(laptop);
        Product beforeDelete = productDAO.findByCode(LAPTOP_CODE);
        assertNotNull("Configuração do teste falhou: Produto não encontrado antes da exclusão", beforeDelete);

        // Act
        int deleteResult = productDAO.delete(laptop);
        Product afterDelete = productDAO.findByCode(LAPTOP_CODE);

        // Assert
        assertEquals("Delete deve afetar 1 linha", 1, deleteResult);
        assertNull("Produto não deve ser encontrado após a exclusão", afterDelete);
    }

    @Test
    public void testSearchAll() throws Exception {
        // Arrange
        productDAO.register(laptop);
        productDAO.register(smartphone);

        // Act
        List<Product> allProducts = productDAO.searchAll();

        // Assert
        assertNotNull("Lista de produtos não deve ser nula", allProducts);
        assertTrue("Lista deve conter pelo menos 2 produtos", allProducts.size() >= 2);

        // Verifica se ambos os produtos de teste estão na lista
        boolean containsLaptop = false;
        boolean containsSmartphone = false;

        for (Product product : allProducts) {
            if (LAPTOP_CODE.equals(product.getCode())) {
                containsLaptop = true;
            }
            if (SMARTPHONE_CODE.equals(product.getCode())) {
                containsSmartphone = true;
            }
        }

        assertTrue("Lista deve conter o Laptop", containsLaptop);
        assertTrue("Lista deve conter o Smartphone", containsSmartphone);
    }

    @Test
    public void testUpdateNonExistentProduct() throws Exception {
        // Arrange
        Product nonExistentProduct = new Product(999L, "CODIGO_INEXISTENTE", "Nome Inexistente", "Descrição Inexistente", new BigDecimal("999.99"));

        // Act
        int result = productDAO.update(nonExistentProduct);

        // Assert
        assertEquals("Atualização de produto inexistente deve afetar 0 linhas", 0, result);
    }

    @Test
    public void testDeleteNonExistentProduct() throws Exception {
        // Arrange
        Product nonExistentProduct = new Product("CODIGO_INEXISTENTE", "Nome Inexistente", "Descrição Inexistente", new BigDecimal("999.99"));

        // Act
        int result = productDAO.delete(nonExistentProduct);

        // Assert
        assertEquals("Exclusão de produto inexistente deve afetar 0 linhas", 0, result);
    }
}