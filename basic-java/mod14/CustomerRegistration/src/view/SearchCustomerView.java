package view;

import dao.CustomerMapDAOImpl;
import dao.ICustomerDAO;
import domain.Customer;
import exceptions.CpfNotFoundException;

import javax.swing.*;
import java.awt.*;

public class SearchCustomerView extends JFrame {
    private JTextField cpfField;
    private JButton searchButton;
    private JTextArea resultArea;
    private final ICustomerDAO customerDAO;

    public SearchCustomerView(ICustomerDAO customerDAO) {
        setTitle("Search Customer");
        setSize(450, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        this.customerDAO = customerDAO;

        JPanel formPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        Font labelFont = new Font("Arial", Font.BOLD, 14);
        Font fieldFont = new Font("Arial", Font.PLAIN, 14);

        formPanel.add(createLabel("CPF:", labelFont));
        cpfField = createTextField(fieldFont);
        formPanel.add(cpfField);

        searchButton = new JButton("Search");
        searchButton.setFont(new Font("Arial", Font.BOLD, 16));
        searchButton.setPreferredSize(new Dimension(120, 40));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));
        buttonPanel.add(searchButton);

        resultArea = new JTextArea(5, 30);
        resultArea.setFont(new Font("Arial", Font.PLAIN, 14));
        resultArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultArea);

        add(formPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        add(scrollPane, BorderLayout.SOUTH);

        searchButton.addActionListener(e -> searchCustomer());

        setVisible(true);
    }

    private JLabel createLabel(String text, Font font) {
        JLabel label = new JLabel(text);
        label.setFont(font);
        return label;
    }

    private JTextField createTextField(Font font) {
        JTextField textField = new JTextField();
        textField.setFont(font);
        return textField;
    }

    private void searchCustomer() {
        try {
            Long cpf = Long.parseLong(cpfField.getText());
            Customer customer = customerDAO.searchCustomer(cpf);
            resultArea.setText("Customer Found:\n" + customer.toString());
        } catch (CpfNotFoundException ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Invalid input. Please check your data.");
        }
    }
}
