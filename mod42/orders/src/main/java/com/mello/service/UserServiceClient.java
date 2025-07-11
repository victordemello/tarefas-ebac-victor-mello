package com.mello.service;

import com.mello.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserServiceClient {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${app.user-service.url}")
    private String userServiceUrl;

    public User getUserById(Long id) {
        try {
            return restTemplate.getForObject(
                    userServiceUrl + "/api/users/" + id, User.class);
        } catch (Exception e) {
            System.err.println("Erro ao buscar usuario: " + e.getMessage());
            return null;
        }
    }
}
