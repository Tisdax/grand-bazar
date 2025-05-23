package businessLogic;

import DAO.ShelfDBAccess;
import DAOinterfaces.ShelfDAO;
import exceptions.DAOException;
import model.Shelf;

import java.util.ArrayList;

public class ShelfManager {
    private ShelfDAO dao;

    public ShelfManager() {
        dao = new ShelfDBAccess();
    }

    public ArrayList<Shelf> findAll() throws DAOException {
        return dao.findAll();
    }
}
