import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mod30task.dao.IStockDAO;
import org.mod30task.domain.Product.ProductDomain;
import org.mod30task.domain.Product.Stock;
import org.mod30task.domain.Product.StockItem;
import org.mod30task.services.StockService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class StockServiceTest {

    @Mock
    private IStockDAO stockDAO;

    @InjectMocks
    private StockService stockService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void addStockItem_NewItem_CreatesNewStock() { // Corrected method placement
        StockItem newItem = new StockItem(null, new ProductDomain(1L, "code1", "name1", "desc1", null), 10);

        when(stockDAO.findStockItemByProductId(1L)).thenReturn(Optional.empty());
        doAnswer(invocation -> {
            Stock stock = invocation.getArgument(0);
            stock.setId(1L);
            return null;
        }).when(stockDAO).create(any(Stock.class));

        stockService.addStockItem(newItem);

        verify(stockDAO, times(1)).findStockItemByProductId(1L);
        verify(stockDAO, times(1)).create(any(Stock.class));
    }

    @Test
    void addStockItem_ExistingItem_UpdatesQuantity() {
        StockItem existingItem = new StockItem(1L, new ProductDomain(1L, "code1", "name1", "desc1", null), 5);
        StockItem newItem = new StockItem(null, new ProductDomain(1L, "code1", "name1", "desc1", null), 10);

        when(stockDAO.findStockItemByProductId(1L)).thenReturn(Optional.of(existingItem));

        stockService.addStockItem(newItem);

        verify(stockDAO, times(1)).findStockItemByProductId(1L);
        verify(stockDAO, times(1)).updateStockItemQuantity(1L, 15);
        verify(stockDAO, never()).create(any(Stock.class));
    }

    @Test
    void updateStockItemQuantity_UpdatesQuantityCorrectly() {
        stockService.updateStockItemQuantity(1L, 20);
        verify(stockDAO, times(1)).updateStockItemQuantity(1L, 20);
    }

    @Test
    void findStockItemByProductId_ReturnsCorrectItem() {
        StockItem item = new StockItem(1L, new ProductDomain(1L, "code1", "name1", "desc1", null), 10);
        when(stockDAO.findStockItemByProductId(1L)).thenReturn(Optional.of(item));

        Optional<StockItem> result = stockService.findStockItemByProductId(1L);

        assertTrue(result.isPresent());
        assertEquals(item, result.get());
    }

    @Test
    void findStockItemByProductId_ReturnsEmptyOptional() {
        when(stockDAO.findStockItemByProductId(1L)).thenReturn(Optional.empty());

        Optional<StockItem> result = stockService.findStockItemByProductId(1L);

        assertTrue(result.isEmpty());
    }

    @Test
    void findStockById_ReturnsCorrectStock() {
        Stock stock = new Stock(1L, new ArrayList<>());
        when(stockDAO.findById(1L)).thenReturn(Optional.of(stock));

        Optional<Stock> result = stockService.findStockById(1L);

        assertTrue(result.isPresent());
        assertEquals(stock, result.get());
    }

    @Test
    void findStockById_ReturnsEmptyOptional_() {
        when(stockDAO.findById(1L)).thenReturn(Optional.empty());

        Optional<Stock> result = stockService.findStockById(1L);

        assertTrue(result.isEmpty());
    }

    @Test
    void findAllStockItems_ReturnsListOfStockItems() {
        List<StockItem> items = new ArrayList<>();
        items.add(new StockItem(1L, new ProductDomain(1L, "code1", "name1", "desc1", null), 10));
        items.add(new StockItem(2L, new ProductDomain(2L, "code2", "name2", "desc2", null), 20));

        Stock stock = new Stock(1L, items);
        List<Stock> stocks = List.of(stock);

        when(stockDAO.findAll()).thenReturn(stocks);

        List<StockItem> result = stockService.findAllStockItems();

        assertEquals(2, result.size());
        assertEquals(items, result);
    }

    @Test
    void removeStockItem_RemovesItemFromStock() {
        StockItem item1 = new StockItem(1L, new ProductDomain(1L, "code1", "name1", "desc1", null), 10);
        StockItem item2 = new StockItem(2L, new ProductDomain(2L, "code2", "name2", "desc2", null), 20);
        Stock stock = new Stock(1L, new ArrayList<>(List.of(item1, item2)));

        when(stockDAO.findAll()).thenReturn(List.of(stock));

        stockService.removeStockItem(2L);

        verify(stockDAO, times(1)).findAll();
        verify(stockDAO, times(1)).update(stock);
        assertEquals(1, stock.getStockItems().size());
        assertTrue(stock.getStockItems().contains(item1));
        assertFalse(stock.getStockItems().contains(item2));
    }

    @Test
    void removeStockItem_StockNotFound_DoesNothing() {
        when(stockDAO.findAll()).thenReturn(new ArrayList<>());

        stockService.removeStockItem(2L);

        verify(stockDAO, times(1)).findAll();
        verify(stockDAO, never()).update(any());
    }
}
