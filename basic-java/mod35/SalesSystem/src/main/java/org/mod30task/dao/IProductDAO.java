package org.mod30task.dao;

import org.mod30task.domain.Product.ProductDomain;

import java.util.List;
import java.util.Optional;

public interface IProductDAO extends IGenericDAO<ProductDomain, Long> {
    Optional<ProductDomain> findByCode(String code);
    List<ProductDomain> findByNameContaining(String name);
}
