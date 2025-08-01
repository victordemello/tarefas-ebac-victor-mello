package org.mod30task.services;

import org.mod30task.domain.Product.Stock;
import org.mod30task.domain.Product.StockItem;

import java.util.List;
import java.util.Optional;

public interface IStockService {
    void addStockItem(StockItem item);
    void updateStockItemQuantity(Long productId, Integer quantity);
    Optional<StockItem> findStockItemByProductId(Long productId);
    Optional<Stock> findStockById(Long id);
    List<StockItem> findAllStockItems();
    void removeStockItem(Long productId);
}