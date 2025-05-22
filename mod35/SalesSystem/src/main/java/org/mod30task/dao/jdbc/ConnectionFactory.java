//package org.mod30task.dao.jdbc;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//import java.util.Properties;
//
//public class ConnectionFactory {
//    private static Connection connection;
//    private static final String CONFIG_FILE = "database.properties";
//    private static Properties properties;
//
//    static {
//        try {
//            loadProperties();
//        } catch (IOException e) {
//            throw new RuntimeException("Failed to load database properties", e);
//        }
//    }
//
//    private ConnectionFactory() {
//        // Construtor privado para evitar instanciação
//    }
//
//    private static void loadProperties() throws IOException {
//        properties = new Properties();
//        try (InputStream inputStream = ConnectionFactory.class.getClassLoader().getResourceAsStream(CONFIG_FILE)) {
//            if (inputStream != null) {
//                properties.load(inputStream);
//            } else {
//                throw new IOException("Unable to find " + CONFIG_FILE);
//            }
//        }
//    }
//
//    public static Connection getConnection() throws SQLException {
//        if (connection == null) {
//            connection = initConnection();
//            return connection;
//        } else if (connection.isClosed()) {
//            connection = initConnection();
//            return connection;
//        } else {
//            return connection;
//        }
//    }
//
//    private static Connection initConnection() {
//        try {
//            String url = properties.getProperty("db.url");
//            String user = properties.getProperty("db.user");
//            String password = properties.getProperty("db.password");
//
//            return DriverManager.getConnection(url, user, password);
//        } catch (SQLException e) {
//            throw new RuntimeException("Failed to initialize database connection", e);
//        }
//    }
//
//    public static void closeConnection() {
//        if (connection != null) {
//            try {
//                connection.close();
//                connection = null;
//            } catch (SQLException e) {
//                throw new RuntimeException("Failed to close database connection", e);
//            }
//        }
//    }
//}