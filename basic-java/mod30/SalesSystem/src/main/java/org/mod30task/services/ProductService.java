package org.mod30task.services;

import org.mod30task.dao.IProductDAO;
import org.mod30task.domain.Product.ProductDomain;

import java.util.List;
import java.util.Optional;

public class ProductService implements IProductService {

    private final IProductDAO productDAO;

    public ProductService(IProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    @Override
    public void create(ProductDomain product) {
        productDAO.create(product);
    }

    @Override
    public Optional<ProductDomain> findById(Long id) {
        return productDAO.findById(id);
    }

    @Override
    public Optional<ProductDomain> findByCode(String code) {
        return productDAO.findByCode(code);
    }

    @Override
    public List<ProductDomain> findAll() {
        return productDAO.findAll();
    }

    @Override
    public List<ProductDomain> findByNameContaining(String name) {
        return productDAO.findByNameContaining(name);
    }

    @Override
    public void update(ProductDomain product) {
        productDAO.update(product);
    }

    @Override
    public void delete(Long id) {
        productDAO.delete(id);
    }
}
