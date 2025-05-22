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

    public int deleteStock(String productId) throws DAOException {
        return dao.deleteStock(productId);
    }

    public void addStock(Stock stock) throws DAOException {
        dao.addStock(stock);
    }

    public boolean exists(Stock stock) throws DAOException {
        return dao.exists(stock);
    }

    public void updateStock(Stock stock) throws DAOException{
        dao.updateStock(stock);
    }
}
