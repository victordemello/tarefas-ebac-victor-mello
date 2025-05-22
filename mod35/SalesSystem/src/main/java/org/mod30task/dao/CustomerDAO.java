package org.mod30task.dao;

import org.mod30task.domain.Customer.Address;
import org.mod30task.domain.Customer.ContactInfo;
import org.mod30task.domain.Customer.CustomerDomain;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CustomerDAO implements ICustomerDAO {

    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("your-persistence-unit");

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    @Override
    public void create(CustomerDomain customer) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(customer); // Assuming Address and ContactInfo are cascaded
            em.getTransaction().commit();
        } catch (RuntimeException e) {
            em.getTransaction().rollback();
            throw new RuntimeException("Error creating customer", e);
        } finally {
            em.close();
        }
    }

    @Override
    public Optional<CustomerDomain> findById(Long id) {
        EntityManager em = getEntityManager();
        try {
            CustomerDomain customer = em.find(CustomerDomain.class, id);
            return Optional.ofNullable(customer);
        } finally {
            em.close();
        }
    }

    @Override
    public Optional<CustomerDomain> findBySsn(Long ssn) {
        EntityManager em = getEntityManager();
        try {
            CustomerDomain customer = em.createQuery(
                    "SELECT c FROM CustomerDomain c WHERE c.ssn = :ssn", CustomerDomain.class)
                    .setParameter("ssn", ssn)
                    .getSingleResult();
            return Optional.of(customer);
        } catch (Exception e) {
            return Optional.empty();
        } finally {
            em.close();
        }
    }

    @Override
    public List<CustomerDomain> findAll() {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("SELECT c FROM CustomerDomain c", CustomerDomain.class).getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public void update(CustomerDomain customer) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(customer); // JPA fará update de Address e ContactInfo se mapeado com cascade
            em.getTransaction().commit();
        } catch (RuntimeException e) {
            em.getTransaction().rollback();
            throw new RuntimeException("Error updating customer", e);
        } finally {
            em.close();
        }
    }

    @Override
    public void delete(Long id) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            CustomerDomain customer = em.find(CustomerDomain.class, id);
            if (customer != null) {
                em.remove(customer);
            }
            em.getTransaction().commit();
        } catch (RuntimeException e) {
            em.getTransaction().rollback();
            throw new RuntimeException("Error deleting customer", e);
        } finally {
            em.close();
        }
    }

    @Override
    public boolean exists(Long id) {
        return findById(id).isPresent();
    }
}
