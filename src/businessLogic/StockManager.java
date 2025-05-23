package businessLogic;

import DAO.StockDBAccess;
import DAOinterfaces.StockDAO;
import exceptions.DAOException;
import model.Stock;

public class StockManager {
    private StockDAO dao;

    public StockManager(){
        dao = new StockDBAccess();
    }

    public int deleteById(String productId) throws DAOException {
        return dao.deleteById(productId);
    }

    public void save(Stock stock) throws DAOException {
        dao.save(stock);
    }

    public boolean existsById(Stock stock) throws DAOException {
        return dao.existsById(stock);
    }

    public void update(Stock stock) throws DAOException{
        dao.update(stock);
    }
}
