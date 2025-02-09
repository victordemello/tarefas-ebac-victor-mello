package view;

import dao.ICustomerDAO;
import domain.Customer;

import javax.swing.*;
import java.awt.*;
import java.util.Collection;

public class ViewClientsView extends JFrame {
    private JTextArea clientsArea;
    private final ICustomerDAO customerDAO;

    public ViewClientsView(ICustomerDAO customerDAO) {
        this.customerDAO = customerDAO;

        setTitle("View Customers");
        setSize(600, 450);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Registered Customers", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        clientsArea = new JTextArea();
        clientsArea.setFont(new Font("Arial", Font.PLAIN, 16));
        clientsArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(clientsArea);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JButton refreshButton = new JButton("Refresh");
        refreshButton.setFont(new Font("Arial", Font.BOLD, 16));
        refreshButton.setPreferredSize(new Dimension(140, 50));
        refreshButton.addActionListener(e -> loadCustomers());

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));
        buttonPanel.add(refreshButton);

        add(titleLabel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        loadCustomers();
        setVisible(true);
    }

    private void loadCustomers() {
        Collection<Customer> customers = customerDAO.searchAllCustomers();
        clientsArea.setText("");
        if (customers.isEmpty()) {
            clientsArea.setText("No registered customers.");
        } else {
            for (Customer customer : customers) {
                clientsArea.append(customer.toString() + "\n------------------\n");
            }
        }
    }
}