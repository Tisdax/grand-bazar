package DAOinterfaces;

import exceptions.DBAccesException;

public interface StockDAO {
    public int deleteStock(String productId) throws DBAccesException;
}