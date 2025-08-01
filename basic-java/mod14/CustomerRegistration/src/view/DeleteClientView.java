package view;

import dao.CustomerMapDAOImpl;
import dao.ICustomerDAO;
import exceptions.CpfNotFoundException;

import javax.swing.*;
import java.awt.*;

public class DeleteClientView extends JFrame {
    private JTextField cpfField;
    private JButton deleteButton;
    private final ICustomerDAO customerDAO;

    public DeleteClientView(ICustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
        setTitle("Delete Customer");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        Font labelFont = new Font("Arial", Font.BOLD, 14);
        Font fieldFont = new Font("Arial", Font.PLAIN, 14);

        formPanel.add(createLabel("CPF:", labelFont));
        cpfField = createTextField(fieldFont);
        formPanel.add(cpfField);

        deleteButton = new JButton("Delete");
        deleteButton.setFont(new Font("Arial", Font.BOLD, 16));
        deleteButton.setPreferredSize(new Dimension(120, 40));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));
        buttonPanel.add(deleteButton);

        add(formPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        deleteButton.addActionListener(e -> deleteCustomer());

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

    private void deleteCustomer() {
        try {
            Long cpf = Long.parseLong(cpfField.getText());
            customerDAO.deleteCustomer(cpf);
            JOptionPane.showMessageDialog(this, "Customer deleted successfully!");
            dispose();
        } catch (CpfNotFoundException ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Invalid input. Please check your data.");
        }
    }
}
