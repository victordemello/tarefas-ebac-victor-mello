package org.mod30task.domain.Sale;

import org.mod30task.domain.Customer.CustomerDomain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "sale")
public class SaleDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sale_seq")
    @SequenceGenerator(name = "sale_seq", sequenceName = "sale_seq", allocationSize = 1)
    private Long id;

    @Column(nullable = false, unique = true)
    private String code;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private CustomerDomain customer;

    private BigDecimal total;

    @Column(name = "date_of_sale", nullable = false)
    private Instant dateOfSale;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status_id", nullable = false)
    private Status status;

    @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SaleItem> items;

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
