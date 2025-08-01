package com.mello.data;

import com.mello.domain.User;
import com.mello.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        // Verifica se ja existem dados
        if (userRepository.count() == 0) {
            // Cria usuarios iniciais
            userRepository.save(new User("João Silva", "joao@email.com"));
            userRepository.save(new User("Maria Santos", "maria@email.com"));
            userRepository.save(new User("Pedro Oliveira", "pedro@email.com"));
            userRepository.save(new User("Ana Costa", "ana@email.com"));
            userRepository.save(new User("Carlos Pereira", "carlos@email.com"));
            userRepository.save(new User("Julia Almeida", "julia@email.com"));
            userRepository.save(new User("Bruno Rodrigues", "bruno@email.com"));
            userRepository.save(new User("Fernanda Lima", "fernanda@email.com"));
            userRepository.save(new User("Rafael Gomes", "rafael@email.com"));
            userRepository.save(new User("Larissa Ferreira", "larissa@email.com"));

            System.out.println("=== DADOS INICIAIS CARREGADOS ===");
            System.out.println("Total de usuarios: " + userRepository.count());
            System.out.println("Servico rodando na porta 8081");
            System.out.println("API: http://localhost:8081/api/users");
            System.out.println("H2 Console: http://localhost:8081/h2-console");
        }
    }
}
