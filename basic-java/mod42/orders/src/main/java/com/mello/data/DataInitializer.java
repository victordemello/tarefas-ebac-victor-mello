package com.mello.data;

import com.mello.dto.CreateOrderRequest;
import com.mello.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private OrderService orderService;

    @Override
    public void run(String... args) throws Exception {
        // Aguarda um pouco para os outros servicos subirem
        Thread.sleep(5000);

        try {
            // Cria alguns pedidos de exemplo
            CreateOrderRequest order1 = new CreateOrderRequest();
            order1.setUserId(1L);
            order1.setProductId(1L);
            order1.setQuantity(1);
            orderService.createOrder(order1);

            CreateOrderRequest order2 = new CreateOrderRequest();
            order2.setUserId(2L);
            order2.setProductId(2L);
            order2.setQuantity(2);
            orderService.createOrder(order2);

            CreateOrderRequest order3 = new CreateOrderRequest();
            order3.setUserId(3L);
            order3.setProductId(3L);
            order3.setQuantity(1);
            orderService.createOrder(order3);

            System.out.println("=== DADOS INICIAIS CARREGADOS ===");
            System.out.println("Total de pedidos: 3");
            System.out.println("Servico rodando na porta 8083");
            System.out.println("API: http://localhost:8083/api/orders");
            System.out.println("H2 Console: http://localhost:8083/h2-console");

        } catch (Exception e) {
            System.err.println("Erro ao criar pedidos iniciais: " + e.getMessage());
            System.err.println("Certifique-se de que os outros servicos estao rodando");
        }
    }
}
