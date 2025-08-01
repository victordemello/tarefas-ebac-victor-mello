package view;

import dao.ICustomerDAO;

import javax.swing.*;
import java.awt.*;

public class MenuApp extends JFrame {

    public MenuApp(ICustomerDAO customerDAO) {
        setTitle("Client Registration System");
        setSize(800, 500); // Aumentei o tamanho da janela para 800x500
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Painel de fundo
        JPanel backgroundPanel = new JPanel(new BorderLayout());
        backgroundPanel.setBackground(new Color(245, 245, 245));
        backgroundPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Título
        JLabel titleLabel = new JLabel("Client Registration System", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 26)); // Aumentei a fonte do título
        titleLabel.setForeground(new Color(50, 50, 50));

        // Subtítulo
        JLabel subtitleLabel = new JLabel("Select an operation below", SwingConstants.CENTER);
        subtitleLabel.setFont(new Font("Arial", Font.PLAIN, 18)); // Aumentei a fonte do subtítulo
        subtitleLabel.setForeground(new Color(80, 80, 80));

        JPanel titlePanel = new JPanel(new GridLayout(2, 1));
        titlePanel.setOpaque(false);
        titlePanel.add(titleLabel);
        titlePanel.add(subtitleLabel);

        // Painel dos botões
        JPanel buttonPanel = new JPanel(new GridLayout(5, 1, 15, 15)); // Aumentei o espaçamento entre os botões
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(30, 200, 30, 200)); // Ajustei margens para melhor centralização
        buttonPanel.setOpaque(false);

        JButton createClientButton = createStyledButton("Create Customer");
        JButton updateClientButton = createStyledButton("Update Customer");
        JButton deleteClientButton = createStyledButton("Delete Customer");
        JButton viewClientsButton = createStyledButton("View Customers");
        JButton searchCustomerButton = createStyledButton("Search Customer");

        buttonPanel.add(createClientButton);
        buttonPanel.add(updateClientButton);
        buttonPanel.add(deleteClientButton);
        buttonPanel.add(viewClientsButton);
        buttonPanel.add(searchCustomerButton);

        createClientButton.addActionListener(e -> new CreateClientView(customerDAO));
        updateClientButton.addActionListener(e -> new UpdateClientView(customerDAO));
        deleteClientButton.addActionListener(e -> new DeleteClientView(customerDAO));
        viewClientsButton.addActionListener(e -> new ViewClientsView(customerDAO));
        searchCustomerButton.addActionListener(e -> new SearchCustomerView(customerDAO));

        backgroundPanel.add(titlePanel, BorderLayout.NORTH);
        backgroundPanel.add(buttonPanel, BorderLayout.CENTER);

        add(backgroundPanel);

        setVisible(true);
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 16)); // Aumentei o tamanho da fonte dos botões
        button.setBackground(new Color(30, 144, 255));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(12, 20, 12, 20)); // Aumentei o padding interno
        return button;
    }
}
