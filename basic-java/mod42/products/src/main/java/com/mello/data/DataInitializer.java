package com.mello.data;

import com.mello.domain.Product;
import com.mello.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void run(String... args) throws Exception {
        // Verifica se ja existem dados
        if (productRepository.count() == 0) {
            // Cria produtos iniciais
            productRepository.save(new Product("Notebook Dell", "Notebook Dell Inspiron 15 - Intel i5, 8GB RAM, 256GB SSD", new BigDecimal("2499.99"), 15));
            productRepository.save(new Product("Mouse Logitech", "Mouse sem fio Logitech MX Master 3 - Ergonomico e preciso", new BigDecimal("299.90"), 50));
            productRepository.save(new Product("Teclado Mecanico", "Teclado mecanico RGB - Switches Cherry MX Blue", new BigDecimal("399.99"), 25));
            productRepository.save(new Product("Monitor 24 Full HD", "Monitor LED 24 polegadas Full HD - 75Hz, IPS", new BigDecimal("599.99"), 20));
            productRepository.save(new Product("Headset Gamer", "Headset Gamer com microfone - Som surround 7.1", new BigDecimal("199.99"), 30));
            productRepository.save(new Product("Webcam HD", "Webcam HD 1080p - Ideal para videoconferencias", new BigDecimal("149.99"), 40));
            productRepository.save(new Product("SSD 500GB", "SSD NVMe 500GB - Velocidade de leitura 3500MB/s", new BigDecimal("299.99"), 35));
            productRepository.save(new Product("Memoria RAM 16GB", "Memoria RAM DDR4 16GB 3200MHz - Kit 2x8GB", new BigDecimal("399.99"), 18));
            productRepository.save(new Product("Placa de Video", "Placa de Video GTX 1660 Super - 6GB GDDR6", new BigDecimal("1299.99"), 8));
            productRepository.save(new Product("Smartphone Android", "Smartphone Android 128GB - Camera 64MP, Tela 6.1", new BigDecimal("899.99"), 0));

            System.out.println("=== DADOS INICIAIS CARREGADOS ===");
            System.out.println("Total de produtos: " + productRepository.count());
            System.out.println("Servico rodando na porta 8082");
            System.out.println("API: http://localhost:8082/api/products");
            System.out.println("H2 Console: http://localhost:8082/h2-console");
        }
    }
}
