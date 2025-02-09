package view;

import dao.CustomerMapDAOImpl;
import dao.ICustomerDAO;
import domain.Customer;
import exceptions.CpfAlreadyExistsException;

import javax.swing.*;
import java.awt.*;

public class CreateClientView extends JFrame {
    private JTextField nameField, cpfField, phoneField, addressField, numberField, cityField, stateField;
    private JButton registerButton;
    private final ICustomerDAO customerDAO;

    public CreateClientView(ICustomerDAO customerDAO) {
        this.customerDAO = customerDAO;

        setTitle("Register Customer");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(8, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        Font labelFont = new Font("Arial", Font.BOLD, 14);
        Font fieldFont = new Font("Arial", Font.PLAIN, 14);

        formPanel.add(createLabel("Name:", labelFont));
        nameField = createTextField(fieldFont);
        formPanel.add(nameField);

        formPanel.add(createLabel("CPF:", labelFont));
        cpfField = createTextField(fieldFont);
        formPanel.add(cpfField);

        formPanel.add(createLabel("Phone:", labelFont));
        phoneField = createTextField(fieldFont);
        formPanel.add(phoneField);

        formPanel.add(createLabel("Address:", labelFont));
        addressField = createTextField(fieldFont);
        formPanel.add(addressField);

        formPanel.add(createLabel("Number:", labelFont));
        numberField = createTextField(fieldFont);
        formPanel.add(numberField);

        formPanel.add(createLabel("City:", labelFont));
        cityField = createTextField(fieldFont);
        formPanel.add(cityField);

        formPanel.add(createLabel("State:", labelFont));
        stateField = createTextField(fieldFont);
        formPanel.add(stateField);

        registerButton = new JButton("Register");
        registerButton.setFont(new Font("Arial", Font.BOLD, 16));
        registerButton.setPreferredSize(new Dimension(120, 40));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));
        buttonPanel.add(registerButton);

        add(formPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        registerButton.addActionListener(e -> registerCustomer());

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

    private void registerCustomer() {
        try {
            Customer customer = new Customer(
                    nameField.getText(),
                    Long.parseLong(cpfField.getText()),
                    Long.parseLong(phoneField.getText()),
                    addressField.getText(),
                    Integer.parseInt(numberField.getText()),
                    cityField.getText(),
                    stateField.getText()
            );
            customerDAO.register(customer);
            JOptionPane.showMessageDialog(this, "Customer registered successfully!");
            dispose();
        } catch (CpfAlreadyExistsException ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Invalid input. Please check your data.");
        }
    }
}
