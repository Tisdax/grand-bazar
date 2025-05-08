package dataAccess;

import exceptions.DBAccesException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SingletonConnection {
    private static Connection connection;

    public static Connection getInstance() throws DBAccesException {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/le_grand_bazar", "root", "legrandbzar");
            }
            catch (SQLException sqlException) {
                throw new DBAccesException(sqlException.getMessage());
            }
        }
        return connection;
    }
}
