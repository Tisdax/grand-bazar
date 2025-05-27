package businessLogic;

import DAO.SaleDBAccess;
import DAOinterfaces.SaleDAO;
import model.Sale;

public class SaleManager {
    private SaleDAO dao;

    public SaleManager(){
        dao = new SaleDBAccess();
    }
    public void save(Sale sale) throws Exception {
        dao.save(sale);
    }
    public int lastId() throws Exception {
        return dao.lastId();
    }
}
