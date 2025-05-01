package org.mod30task.domain.Sale;

import org.mod30task.domain.Customer.CustomerDomain;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SaleDomain {
    private Long id;
    private String code;
    private CustomerDomain customer;
    private BigDecimal total;
    private List<SaleItem> items;
    private Instant dateOfSale;
    private Status status;

    public SaleDomain() {
        this.items = new ArrayList<>();
        this.total = BigDecimal.ZERO;
    }

    public SaleDomain(Long id, String code, CustomerDomain customer, List<SaleItem> items,
                      Instant dateOfSale, Status status) {
        this.id = id;
        this.code = code;
        this.customer = customer;
        this.items = items != null ? items : new ArrayList<>();
        this.dateOfSale = dateOfSale;
        this.status = status;
        this.calculateTotal();
    }

    public void calculateTotal() {
        if (items == null || items.isEmpty()) {
            this.total = BigDecimal.ZERO;
            return;
        }

        this.total = items.stream()
                .map(item -> item.getProductDomain().getPrice()
                        .multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public void addItem(SaleItem item) {
        if (this.items == null) {
            this.items = new ArrayList<>();
        }
        this.items.add(item);
        calculateTotal();
    }

    public void removeItem(SaleItem item) {
        if (this.items != null) {
            this.items.remove(item);
            calculateTotal();
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public CustomerDomain getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDomain customer) {
        this.customer = customer;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public List<SaleItem> getItems() {
        return items;
    }

    public void setItems(List<SaleItem> items) {
        this.items = items;
        calculateTotal();
    }

    public Instant getDateOfSale() {
        return dateOfSale;
    }

    public void setDateOfSale(Instant dateOfSale) {
        this.dateOfSale = dateOfSale;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SaleDomain that = (SaleDomain) o;
        return Objects.equals(id, that.id) ||
                Objects.equals(code, that.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, code);
    }

    @Override
    public String toString() {
        return "SaleDomain{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", customer=" + customer +
                ", total=" + total +
                ", items=" + items +
                ", dateOfSale=" + dateOfSale +
                ", status=" + status +
                '}';
    }
}
