package org.mod30task.domain.Sale;

import org.mod30task.domain.Product.ProductDomain;

import java.util.Objects;

public class SaleItem {
    private Long id;
    private ProductDomain productDomain;
    private Integer quantity;

    public SaleItem() {
    }

    public SaleItem(Long id, ProductDomain productDomain, Integer quantity) {
        this.id = id;
        this.productDomain = productDomain;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProductDomain getProductDomain() {
        return productDomain;
    }

    public void setProductDomain(ProductDomain productDomain) {
        this.productDomain = productDomain;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SaleItem saleItem = (SaleItem) o;
        return Objects.equals(id, saleItem.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "SaleItem{" +
                "id=" + id +
                ", productDomain=" + productDomain +
                ", quantity=" + quantity +
                '}';
    }
}

