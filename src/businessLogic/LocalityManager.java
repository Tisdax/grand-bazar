package businessLogic;

import DAO.LocalityDBAccess;
import DAOinterfaces.LocalityDAO;
import exceptions.DAOException;
import exceptions.InvalidValueException;
import model.Locality;

import java.util.ArrayList;

public class LocalityManager {
    private LocalityDAO dao;

    public LocalityManager(){
        dao = new LocalityDBAccess();
    }

    public ArrayList<Locality> findAll() throws DAOException, InvalidValueException {
        return dao.findAll();
    }
}
