package com.mello.service;

import com.mello.domain.Order;
import com.mello.dto.CreateOrderRequest;
import com.mello.dto.Product;
import com.mello.dto.User;
import com.mello.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserServiceClient userServiceClient;

    @Autowired
    private ProductServiceClient productServiceClient;

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }

    public List<Order> getOrdersByUserId(Long userId) {
        return orderRepository.findByUserId(userId);
    }

    public Order createOrder(CreateOrderRequest request) {
        // Busca dados do usuario
        User user = userServiceClient.getUserById(request.getUserId());
        if (user == null) {
            throw new RuntimeException("Usuario nao encontrado: " + request.getUserId());
        }

        // Busca dados do produto
        Product product = productServiceClient.getProductById(request.getProductId());
        if (product == null) {
            throw new RuntimeException("Produto nao encontrado: " + request.getProductId());
        }

        // Valida estoque
        if (product.getStock() < request.getQuantity()) {
            throw new RuntimeException("Estoque insuficiente. Disponivel: " + product.getStock());
        }

        // Cria o pedido
        Order order = new Order(
                request.getUserId(),
                request.getProductId(),
                request.getQuantity(),
                product.getPrice()
        );

        // Adiciona dados em cache para facilitar consultas
        order.setUserName(user.getName());
        order.setUserEmail(user.getEmail());
        order.setProductName(product.getName());
        order.setStatus("CONFIRMED");

        return orderRepository.save(order);
    }

    public Order updateOrderStatus(Long id, String status) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido nao encontrado"));

        order.setStatus(status);
        return orderRepository.save(order);
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
}
