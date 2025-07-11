package com.mello.service;

import com.mello.dto.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ProductServiceClient {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${app.product-service.url}")
    private String productServiceUrl;

    public Product getProductById(Long id) {
        try {
            return restTemplate.getForObject(
                    productServiceUrl + "/api/products/" + id, Product.class);
        } catch (Exception e) {
            System.err.println("Erro ao buscar produto: " + e.getMessage());
            return null;
        }
    }
}