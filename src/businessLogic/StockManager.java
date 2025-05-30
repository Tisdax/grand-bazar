package businessLogic;

import DAO.StockDBAccess;
import DAOinterfaces.StockDAO;
import exceptions.DAOException;
import exceptions.InvalidValueException;
import model.Stock;

public class StockManager {
    private StockDAO dao;

    public StockManager(){
        dao = new StockDBAccess();
    }

    public int deleteById(String productId) throws DAOException {
        return dao.deleteByProduct(productId);
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

    public int findQuantityByProduct(String product) throws DAOException {
        return dao.findQuantityByProduct(product);
    }

    public int lowerStocks(String product, int quantity) throws DAOException, InvalidValueException {
        return dao.lowerStocks(product, quantity);
    }

}
