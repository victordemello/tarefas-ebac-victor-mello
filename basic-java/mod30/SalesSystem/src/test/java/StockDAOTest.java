import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mod30task.dao.IStockDAO;
import org.mod30task.dao.StockDAO;
import org.mod30task.dao.jdbc.ConnectionFactory;
import org.mod30task.domain.Product.ProductDomain;
import org.mod30task.domain.Product.Stock;
import org.mod30task.domain.Product.StockItem;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class StockDAOTest {

    private IStockDAO stockDAO;
    private Connection connection;

    @BeforeEach
    void setUp() throws SQLException {
        connection = ConnectionFactory.getConnection();
        //  Certifique-se de que a tabela está limpa antes de cada teste
        clearDatabase(); // <---- Chamada para o método clearDatabase()
        stockDAO = new StockDAO();
    }

    //  Método auxiliar para limpar as tabelas relevantes (stocks e stock_items)
    private void clearDatabase() throws SQLException { // <---- Implementação do método aqui
        try (var statement = connection.createStatement()) {
            //  Primeiro, exclua os registros dependentes na tabela sale_items
            statement.execute("DELETE FROM sale_items");
            //  Em seguida, exclua os registros na tabela stocks e products
            statement.execute("DELETE FROM stocks");
            statement.execute("DELETE FROM products");
        }
    }

    //  Método auxiliar para criar um produto para testes
    private ProductDomain createTestProduct(String code, String name, BigDecimal price) {
        ProductDomain product = new ProductDomain(null, code, name, "Test Description", price);
        try (var pstmt = connection.prepareStatement(
                "INSERT INTO products (code, name, description, price) VALUES (?, ?, ?, ?)",
                java.sql.Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, code);
            pstmt.setString(2, name);
            pstmt.setString(3, "Test Description");
            pstmt.setBigDecimal(4, price);
            pstmt.executeUpdate();
            try (var generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    product.setId(generatedKeys.getLong(1));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return product;
    }

    @Test
    void testCreateStock() {
        // Arrange
        ProductDomain product1 = createTestProduct("PROD-1", "Product One", BigDecimal.TEN);
        ProductDomain product2 = createTestProduct("PROD-2", "Product Two", BigDecimal.valueOf(20));
        StockItem item1 = new StockItem(null, product1, 5);
        StockItem item2 = new StockItem(null, product2, 10);
        Stock stock = new Stock(null, List.of(item1, item2));

        // Act
        stockDAO.create(stock);

        // Assert
        assertNotNull(stock.getId());
        Optional<Stock> retrievedStock = stockDAO.findById(stock.getId());
        assertTrue(retrievedStock.isPresent());
        assertEquals(2, retrievedStock.get().getStockItems().size());
    }

    @Test
    void testFindStockById() {
        // Arrange
        ProductDomain product = createTestProduct("PROD-3", "Product Three", BigDecimal.valueOf(30));
        StockItem item = new StockItem(null, product, 15);
        Stock stock = new Stock(null, List.of(item));
        stockDAO.create(stock);

        // Act
        Optional<Stock> foundStock = stockDAO.findById(stock.getId());

        // Assert
        assertTrue(foundStock.isPresent());
        assertEquals(1, foundStock.get().getStockItems().size());
        assertEquals(15, foundStock.get().getStockItems().get(0).getQuantity());
    }

    @Test
    void testFindAllStocks() {
        // Arrange
        createTestProduct("PROD-4", "Product Four", BigDecimal.ONE);
        createTestProduct("PROD-5", "Product Five", BigDecimal.ONE);
        Stock stock1 = new Stock(null, List.of(new StockItem(null, createTestProduct("PROD-6", "Product Six", BigDecimal.ONE), 1)));
        Stock stock2 = new Stock(null, List.of(new StockItem(null, createTestProduct("PROD-7", "Product Seven", BigDecimal.ONE), 1)));
        stockDAO.create(stock1);
        stockDAO.create(stock2);

        // Act
        List<Stock> allStocks = stockDAO.findAll();

        // Assert
        assertEquals(2, allStocks.size());
    }

    @Test
    void testUpdateStock() {
        // Arrange
        ProductDomain product1 = createTestProduct("PROD-8", "Product Eight", BigDecimal.ONE);
        ProductDomain product2 = createTestProduct("PROD-9", "Product Nine", BigDecimal.ONE);
        StockItem item1 = new StockItem(null, product1, 20);
        StockItem item2 = new StockItem(null, product2, 25);
        Stock stock = new Stock(null, List.of(item1, item2));
        stockDAO.create(stock);

        // Act
        item1.setQuantity(30);
        stock.setStockItems(List.of(item1));
        stockDAO.update(stock);

        // Assert
        Optional<Stock> updatedStock = stockDAO.findById(stock.getId());
        assertTrue(updatedStock.isPresent());
        assertEquals(1, updatedStock.get().getStockItems().size());
        assertEquals(30, updatedStock.get().getStockItems().get(0).getQuantity());
    }

    @Test
    void testDeleteStock() {
        // Arrange
        ProductDomain product = createTestProduct("PROD-10", "Product Ten", BigDecimal.ONE);
        StockItem item = new StockItem(null, product, 30);
        Stock stock = new Stock(null, List.of(item));
        stockDAO.create(stock);

        // Act
        stockDAO.delete(stock.getId());

        // Assert
        Optional<Stock> deletedStock = stockDAO.findById(stock.getId());
        assertTrue(deletedStock.isEmpty());
    }

    @Test
    void testFindStockItemByProductId() {
        // Arrange
        ProductDomain product1 = createTestProduct("PROD-11", "Product Eleven", BigDecimal.ONE);
        ProductDomain product2 = createTestProduct("PROD-12", "Product Twelve", BigDecimal.ONE);
        StockItem item1 = new StockItem(null, product1, 5);
        StockItem item2 = new StockItem(null, product2, 10);
        Stock stock = new Stock(null, List.of(item1, item2));
        stockDAO.create(stock);

        // Act
        Optional<StockItem> foundItem = stockDAO.findStockItemByProductId(product2.getId());

        // Assert
        assertTrue(foundItem.isPresent());
        assertEquals(10, foundItem.get().getQuantity());
    }

    @Test
    void testUpdateStockItemQuantity() {
        // Arrange
        ProductDomain product = createTestProduct("PROD-13", "Product Thirteen", BigDecimal.ONE);
        StockItem item = new StockItem(null, product, 20);
        Stock stock = new Stock(null, List.of(item));
        stockDAO.create(stock);

        // Act
        stockDAO.updateStockItemQuantity(product.getId(), 40);

        // Assert
        Optional<StockItem> updatedItem = stockDAO.findStockItemByProductId(product.getId());
        assertTrue(updatedItem.isPresent());
        assertEquals(40, updatedItem.get().getQuantity());
    }
}