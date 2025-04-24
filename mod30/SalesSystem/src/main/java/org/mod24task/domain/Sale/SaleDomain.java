package org.mod24task.domain.Sale;

import org.mod24task.domain.Customer.CustomerDomain;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

public class SaleDomain {
    private Long id;
    private String code;
    private CustomerDomain customer;
    private BigDecimal total;
    private List<SaleItem> items;
    private Instant dateOfSale;
    private Status status;
}
