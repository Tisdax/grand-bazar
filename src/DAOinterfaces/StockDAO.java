package DAOinterfaces;

import exceptions.DAOException;
import exceptions.InvalidValueException;
import model.Stock;

import java.util.ArrayList;

public interface StockDAO {
    public boolean existsById(Stock stock) throws DAOException;
    public ArrayList<Stock> findByProduct(String product) throws DAOException, InvalidValueException;
    public int findQuantityByProduct(String product) throws DAOException;
    public void save(Stock stock) throws DAOException;
    public int delete(int shelf, int shelfLevel, String product) throws DAOException;
    public int deleteByProduct(String productId) throws DAOException;
    public void update(Stock stock) throws DAOException;
    public int lowerStocks(String product, int quantity) throws DAOException, InvalidValueException;
}