package org.mod30task.dao;

import org.mod30task.domain.Product.Stock;
import org.mod30task.domain.Product.StockItem;

import java.util.Optional;

public interface IStockDAO extends IGenericDAO<Stock, Long> {
    Optional<StockItem> findStockItemByProductId(Long productId);
    void updateStockItemQuantity(Long productId, Integer quantity);
}
