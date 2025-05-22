package org.mod30task.dao;

import org.mod30task.domain.Sale.SaleDomain;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

public interface ISaleDAO extends IGenericDAO<SaleDomain, Long> {
    Optional<SaleDomain> findByCode(String code);
    List<SaleDomain> findByCustomerId(Long customerId);
    List<SaleDomain> findByStatus(String status);
    List<SaleDomain> findByDateRange(Instant start, Instant end);

    void changeStatus(Long saleId, String newStatus);
}
