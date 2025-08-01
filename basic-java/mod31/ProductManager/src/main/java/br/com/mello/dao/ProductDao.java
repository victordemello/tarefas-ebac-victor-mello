package br.com.mello.dao;

import br.com.mello.domain.IProduct;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class ProductDao implements IProductDao{

    public IProduct create(IProduct product){
        EntityManagerFactory entityManagerFactory = Persistence
                .createEntityManagerFactory("ExampleJPA");

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        entityManager.persist(product);
        entityManager.getTransaction().commit();

        entityManager.clear();
        entityManagerFactory.close();

        return product;
    }

}
