package org.mod30task.dao;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import jakarta.persistence.*;
import org.mod30task.domain.Sale.SaleDomain;

import jakarta.transaction.Transactional;

public class SaleDAO implements ISaleDAO {

    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("your-persistence-unit");

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    @Override
    @Transactional
    public void create(SaleDomain sale) {
        EntityManager em = getEntityManager(); em.persist(sale);
    }

    @Override
    public Optional<SaleDomain> findById(Long id) {
        EntityManager em = getEntityManager(); return Optional.ofNullable(em.find(SaleDomain.class, id));
    }

    @Override
    public Optional<SaleDomain> findByCode(String code) {
        EntityManager em = getEntityManager();
        TypedQuery<SaleDomain> query = em.createQuery(
            "SELECT s FROM SaleDomain s WHERE s.code = :code", SaleDomain.class);
        query.setParameter("code", code);
        return query.getResultStream().findFirst();
    }

    @Override
    public List<SaleDomain> findByCustomerId(Long customerId) {
        EntityManager em = getEntityManager();
        TypedQuery<SaleDomain> query = em.createQuery(
            "SELECT s FROM SaleDomain s WHERE s.customer.id = :customerId", SaleDomain.class);
        query.setParameter("customerId", customerId);
        return query.getResultList();
    }

    @Override
    public List<SaleDomain> findByStatus(String status) {
        EntityManager em = getEntityManager();
        TypedQuery<SaleDomain> query = em.createQuery(
            "SELECT s FROM SaleDomain s WHERE s.status.description = :status", SaleDomain.class);
        query.setParameter("status", status);
        return query.getResultList();
    }

    @Override
    public List<SaleDomain> findByDateRange(Instant start, Instant end) {
        EntityManager em = getEntityManager();
        TypedQuery<SaleDomain> query = em.createQuery(
            "SELECT s FROM SaleDomain s WHERE s.dateOfSale BETWEEN :start AND :end", SaleDomain.class);
        query.setParameter("start", start);
        query.setParameter("end", end);
        return query.getResultList();
    }

    @Override
    public List<SaleDomain> findAll() {
        EntityManager em = getEntityManager();
        return em.createQuery("SELECT s FROM SaleDomain s", SaleDomain.class).getResultList();
    }

    @Override
    @Transactional
    public void update(SaleDomain sale) {
        EntityManager em = getEntityManager();
        em.merge(sale);
    }

    @Override
    @Transactional
    public void changeStatus(Long saleId, String newStatus) {
        EntityManager em = getEntityManager();
        SaleDomain sale = em.find(SaleDomain.class, saleId);
        if (sale != null) {
            sale.getStatus().setDescription(newStatus);
        }
    }

    @Override
    @Transactional
    public void delete(Long id) {
        EntityManager em = getEntityManager();
        SaleDomain sale = em.find(SaleDomain.class, id);
        if (sale != null) {
            em.remove(sale);
        }
    }

    @Override
    public boolean exists(Long id) {
        EntityManager em = getEntityManager();
        return em.find(SaleDomain.class, id) != null;
    }
}
