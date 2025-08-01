package org.mod30task.domain.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Stock {
    private Long id;
    private List<StockItem> stockItems;

    public Stock() {
        this.stockItems = new ArrayList<>();
    }

    public Stock(Long id, List<StockItem> stockItems) {
        this.id = id;
        this.stockItems = stockItems != null ? stockItems : new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<StockItem> getStockItems() {
        return stockItems;
    }

    public void setStockItems(List<StockItem> stockItems) {
        this.stockItems = stockItems;
    }

    public void addStockItem(StockItem stockItem) {
        if (this.stockItems == null) {
            this.stockItems = new ArrayList<>();
        }
        this.stockItems.add(stockItem);
    }

    public void removeStockItem(StockItem stockItem) {
        if (this.stockItems != null) {
            this.stockItems.remove(stockItem);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stock stock = (Stock) o;
        return Objects.equals(id, stock.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Stock{" +
                "id=" + id +
                ", stockItems=" + stockItems +
                '}';
    }
}
