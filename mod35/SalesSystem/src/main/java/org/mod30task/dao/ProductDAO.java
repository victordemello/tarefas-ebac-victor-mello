package org.mod30task.dao;

import org.mod30task.domain.Product.ProductDomain;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductDAO implements IProductDAO {

    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("your-persistence-unit");

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    @Override
    public void create(ProductDomain product) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(product);
            em.getTransaction().commit();
        } catch (RuntimeException e) {
            em.getTransaction().rollback();
            throw new RuntimeException("Error creating product", e);
        } finally {
            em.close();
        }
    }

    @Override
    public Optional<ProductDomain> findById(Long id) {
        EntityManager em = getEntityManager();
        try {
            ProductDomain product = em.find(ProductDomain.class, id);
            return Optional.ofNullable(product);
        } finally {
            em.close();
        }
    }

    @Override
    public Optional<ProductDomain> findByCode(String code) {
        EntityManager em = getEntityManager();
        try {
            ProductDomain product = em.createQuery(
                    "SELECT p FROM ProductDomain p WHERE p.code = :code", ProductDomain.class)
                    .setParameter("code", code)
                    .getSingleResult();
            return Optional.of(product);
        } catch (Exception e) {
            return Optional.empty();
        } finally {
            em.close();
        }
    }

    @Override
    public List<ProductDomain> findAll() {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("SELECT p FROM ProductDomain p", ProductDomain.class)
                     .getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public List<ProductDomain> findByNameContaining(String name) {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery(
                    "SELECT p FROM ProductDomain p WHERE p.name LIKE :name", ProductDomain.class)
                    .setParameter("name", "%" + name + "%")
                    .getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public void update(ProductDomain product) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(product);
            em.getTransaction().commit();
        } catch (RuntimeException e) {
            em.getTransaction().rollback();
            throw new RuntimeException("Error updating product", e);
        } finally {
            em.close();
        }
    }

    @Override
    public void delete(Long id) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            ProductDomain product = em.find(ProductDomain.class, id);
            if (product != null) {
                em.remove(product);
            }
            em.getTransaction().commit();
        } catch (RuntimeException e) {
            em.getTransaction().rollback();
            throw new RuntimeException("Error deleting product", e);
        } finally {
            em.close();
        }
    }

    @Override
    public boolean exists(Long id) {
        return findById(id).isPresent();
    }
}