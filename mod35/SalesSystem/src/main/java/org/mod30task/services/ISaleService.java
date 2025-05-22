package org.mod30task.services;

import org.mod30task.domain.Sale.SaleDomain;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

public interface ISaleService {
    void create(SaleDomain sale);
    Optional<SaleDomain> findById(Long id);
    Optional<SaleDomain> findByCode(String code);
    List<SaleDomain> findByCustomerId(Long customerId);
    List<SaleDomain> findByStatus(String status);
    List<SaleDomain> findByDateRange(Instant start, Instant end);
    List<SaleDomain> findAll();
    void update(SaleDomain sale);
    void changeStatus(Long saleId, String newStatus);
    void delete(Long id);
}
