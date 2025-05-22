package DAOinterfaces;

import exceptions.DAOException;
import model.Stock;

public interface StockDAO {
    public int deleteStock(String productId) throws DAOException;

    public void addStock(Stock stock) throws DAOException;

    public boolean exists(Stock stock) throws DAOException;

    public void updateStock(Stock stock) throws DAOException;

}