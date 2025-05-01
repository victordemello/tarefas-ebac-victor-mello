package org.mod30task.services;

import org.mod30task.dao.IStockDAO;
import org.mod30task.domain.Product.Stock;
import org.mod30task.domain.Product.StockItem;

import java.util.List;
import java.util.Optional;

public class StockService implements IStockService {

    private final IStockDAO stockDAO;

    public StockService(IStockDAO stockDAO) {
        this.stockDAO = stockDAO;
    }

    @Override
    public void addStockItem(StockItem item) {
        Optional<StockItem> existing = stockDAO.findStockItemByProductId(item.getProduct().getId());

        if (existing.isPresent()) {
            StockItem existingItem = existing.get();
            int newQty = existingItem.getQuantity() + item.getQuantity();
            stockDAO.updateStockItemQuantity(existingItem.getProduct().getId(), newQty);
        } else {
            Stock stock = new Stock();
            stock.addStockItem(item);
            stockDAO.create(stock);
        }
    }

    @Override
    public void updateStockItemQuantity(Long productId, Integer quantity) {
        stockDAO.updateStockItemQuantity(productId, quantity);
    }

    @Override
    public Optional<StockItem> findStockItemByProductId(Long productId) {
        return stockDAO.findStockItemByProductId(productId);
    }

    @Override
    public Optional<Stock> findStockById(Long id) {
        return stockDAO.findById(id);
    }

    @Override
    public List<StockItem> findAllStockItems() {
        return stockDAO.findAll().stream()
                .flatMap(stock -> stock.getStockItems().stream())
                .toList();
    }

    @Override
    public void removeStockItem(Long productId) {
        Optional<Stock> stockOpt = stockDAO.findAll().stream().findFirst();
        if (stockOpt.isPresent()) {
            Stock stock = stockOpt.get();
            stock.getStockItems().removeIf(item -> item.getProduct().getId().equals(productId));
            stockDAO.update(stock);
        }
    }
}
