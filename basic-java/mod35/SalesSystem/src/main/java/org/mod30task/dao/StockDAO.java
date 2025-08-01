package org.mod30task.dao;

import org.mod30task.domain.Product.Stock;
import org.mod30task.domain.Product.StockItem;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

import org.mod30task.domain.Product.ProductDomain;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StockDAO implements IStockDAO {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public void create(Stock stock) {
        em.persist(stock);
    }

    @Override
    public Optional<Stock> findById(Long id) {
        Stock stock = em.find(Stock.class, id);
        return Optional.ofNullable(stock);
    }

    @Override
    public List<Stock> findAll() {
        TypedQuery<Stock> query = em.createQuery("SELECT s FROM Stock s", Stock.class);
        return query.getResultList();
    }

    @Override
    @Transactional
    public void update(Stock stock) {
        em.merge(stock);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Stock stock = em.find(Stock.class, id);
        if (stock != null) {
            em.remove(stock);
        }
    }

    @Override
    public boolean exists(Long id) {
        return em.find(Stock.class, id) != null;
    }

    @Override
    public Optional<StockItem> findStockItemByProductId(Long productId) {
        TypedQuery<StockItem> query = em.createQuery(
            "SELECT si FROM StockItem si WHERE si.product.id = :productId", StockItem.class);
        query.setParameter("productId", productId);

        List<StockItem> results = query.getResultList();
        return results.isEmpty() ? Optional.empty() : Optional.of(results.get(0));
    }

    @Override
    @Transactional
    public void updateStockItemQuantity(Long productId, Integer quantity) {
        TypedQuery<StockItem> query = em.createQuery(
            "SELECT si FROM StockItem si WHERE si.product.id = :productId", StockItem.class);
        query.setParameter("productId", productId);

        List<StockItem> results = query.getResultList();
        if (!results.isEmpty()) {
            StockItem item = results.get(0);
            item.setQuantity(quantity);
            em.merge(item);
        }
    }
}