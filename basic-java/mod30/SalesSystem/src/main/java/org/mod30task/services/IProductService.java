package org.mod30task.services;

import org.mod30task.domain.Product.ProductDomain;

import java.util.List;
import java.util.Optional;

public interface IProductService {
    void create(ProductDomain product);
    Optional<ProductDomain> findById(Long id);
    Optional<ProductDomain> findByCode(String code);
    List<ProductDomain> findAll();
    List<ProductDomain> findByNameContaining(String name);
    void update(ProductDomain product);
    void delete(Long id);
}
