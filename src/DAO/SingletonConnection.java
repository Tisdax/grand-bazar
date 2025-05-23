package DAO;

import exceptions.DAOException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SingletonConnection {
    private static Connection connection;

    public static Connection getInstance() throws DAOException {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/le_grand_bazar", "root", "legrandbzar");
            }
            catch (SQLException e) {
                throw new DAOException(e.getMessage(), "Erreur lors de la connexion à la base de données.");
            }
        }
        return connection;
    }
}
