package br.com.mello;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        System.out.println("Starting application...");

        try {
            // Importante: Você precisa adicionar as classes de entidade no persistence.xml
            // Você só tem Product configurado, então precisará adicionar as outras:
            // <class>br.com.mello.domain.Brand</class>
            // <class>br.com.mello.domain.Car</class>
            // <class>br.com.mello.domain.Accessory</class>

            // Criar o EntityManagerFactory com o nome da persistence-unit do arquivo persistence.xml
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("ExampleJPA");

            System.out.println("EntityManagerFactory created successfully!");

            // Criar um EntityManager para testar a conexão
            EntityManager em = emf.createEntityManager();
            System.out.println("EntityManager created successfully!");

            // Testar se podemos realizar operações
            em.getTransaction().begin();
            System.out.println("Transaction started successfully!");

            // Fechar recursos
            em.getTransaction().commit();
            em.close();
            emf.close();

            System.out.println("Application initialized successfully and tables should be created!");

        } catch (Exception e) {
            System.err.println("Error initializing application: " + e.getMessage());
            e.printStackTrace();
        }
    }
}