package org.mod30task.domain.Sale;

import org.mod30task.domain.Product.ProductDomain;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

import java.util.Objects;

@Entity
@Table(name = "sale_items")
public class SaleItem {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sale_item_seq")
    @SequenceGenerator(name = "sale_item_seq", sequenceName = "sale_item_seq", allocationSize = 1)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private ProductDomain productDomain;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sale_id", nullable = false)
    private SaleDomain sale;

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

