package DAO;

import DAOinterfaces.DAO;
import exceptions.DBAccesException;

import java.sql.SQLException;

public class DAODBAccess implements DAO {
    @Override
    public void closeConnection() throws DBAccesException {
        try {
            SingletonConnection.getInstance().close();
        }
        catch (SQLException e) {
            throw new DBAccesException(e.getMessage());
        }
    }
}
