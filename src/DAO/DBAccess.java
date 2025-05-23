package DAO;

import DAOinterfaces.DAO;
import exceptions.DAOException;

import java.sql.SQLException;

public class DBAccess implements DAO {
    public void closeConnection() throws DAOException {
        try {
            SingletonConnection.getInstance().close();
        }
        catch (SQLException e) {
            throw new DAOException(e.getMessage(), "Erreur lors de la fermeture de la connexion à la base de données.");
        }
    }
}
