import dao.CustomerDataInitializer;
import dao.CustomerMapDAOImpl;
import dao.ICustomerDAO;

import view.MenuApp;

import javax.swing.*;

/**
 * @author victor.mello
 */
public class Application {
    private static ICustomerDAO iCustomerDAO;

    public static void main(String[] args) {
        iCustomerDAO = new CustomerMapDAOImpl();

        SwingUtilities.invokeLater(() -> {
            MenuApp app = new MenuApp(iCustomerDAO);
            app.setVisible(true);
        });

        CustomerDataInitializer.initializeData(iCustomerDAO);
    }
}


