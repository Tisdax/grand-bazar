package DAOinterfaces;

import exceptions.DAOException;

public interface StockDAO {
    public int deleteStock(String productId) throws DAOException;
}