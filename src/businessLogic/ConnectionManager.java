package businessLogic;


import DAO.DBAccess;
import DAOinterfaces.DAO;
import exceptions.DAOException;

public class ConnectionManager {
    private DAO dao;
    public ConnectionManager(){
        dao = new DBAccess();
    }

    public void closeConnection() throws DAOException {
        dao.closeConnection();
    }
}
