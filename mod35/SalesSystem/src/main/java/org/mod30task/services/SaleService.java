package org.mod30task.services;

import org.mod30task.dao.ISaleDAO;
import org.mod30task.domain.Sale.SaleDomain;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

public class SaleService implements ISaleService {

    private final ISaleDAO saleDAO;

    public SaleService(ISaleDAO saleDAO) {
        this.saleDAO = saleDAO;
    }

    @Override
    public void create(SaleDomain sale) {
        saleDAO.create(sale);
    }

    @Override
    public Optional<SaleDomain> findById(Long id) {
        return saleDAO.findById(id);
    }

    @Override
    public Optional<SaleDomain> findByCode(String code) {
        return saleDAO.findByCode(code);
    }

    @Override
    public List<SaleDomain> findByCustomerId(Long customerId) {
        return saleDAO.findByCustomerId(customerId);
    }

    @Override
    public List<SaleDomain> findByStatus(String status) {
        return saleDAO.findByStatus(status);
    }

    @Override
    public List<SaleDomain> findByDateRange(Instant start, Instant end) {
        return saleDAO.findByDateRange(start, end);
    }

    @Override
    public List<SaleDomain> findAll() {
        return saleDAO.findAll();
    }

    @Override
    public void update(SaleDomain sale) {
        saleDAO.update(sale);
    }

    @Override
    public void changeStatus(Long saleId, String newStatus) {
        saleDAO.changeStatus(saleId, newStatus);
    }

    @Override
    public void delete(Long id) {
        saleDAO.delete(id);
    }
}
