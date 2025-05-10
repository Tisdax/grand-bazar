package DAOinterfaces;

import exceptions.DBAccesException;

public interface DAO {
    public void closeConnection() throws DBAccesException;
}
