package businessLogic;

import DAO.SaleDBAccess;
import DAOinterfaces.SaleDAO;
import exceptions.DAOException;
import model.Sale;

public class SaleManager {
    private SaleDAO dao;

    public SaleManager(){
        dao = new SaleDBAccess();
    }

    public void save(Sale sale) throws DAOException {
        dao.save(sale);
    }
    public int lastId() throws DAOException {
        return dao.lastId();
    }
}
