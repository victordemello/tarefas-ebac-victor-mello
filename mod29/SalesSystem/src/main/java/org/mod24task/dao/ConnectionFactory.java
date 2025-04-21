package org.mod24task.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author victor.mello
 */

public class ConnectionFactory {

    private static Connection connection;

    private ConnectionFactory(Connection connection){

    }

    public static Connection getConnection() throws SQLException{
        if (connection == null) {
            connection = initConnection();
        } else if (connection.isClosed()) {
            connection = initConnection();
        }
        return connection;
    }

    private static Connection initConnection(){
        try {
            return DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/sales_system_db", "postgres", "0317"
            );
        }catch (SQLException ex){
            throw new RuntimeException(ex);
        }
    }
}
