package DAO;

import DAOinterfaces.DAO;
import exceptions.DBAccesException;

import java.sql.SQLException;

public class DBAccess implements DAO {
    @Override
    public void closeConnection() throws DBAccesException {
        try {
            SingletonConnection.getInstance().close();
        }
        catch (SQLException e) {
            throw new DBAccesException(e.getMessage(), "Erreur lors de la fermeture de la connexion à la base de données");
        }
    }
}
