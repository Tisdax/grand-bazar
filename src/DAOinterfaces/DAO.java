package DAOinterfaces;

import exceptions.DAOException;

public interface DAO {
    public void closeConnection() throws DAOException;
}
