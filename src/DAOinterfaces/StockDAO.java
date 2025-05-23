package DAOinterfaces;

import exceptions.DAOException;
import model.Stock;

public interface StockDAO {
    public boolean existsById(Stock stock) throws DAOException;
    public void save(Stock stock) throws DAOException;
    public int deleteById(String productId) throws DAOException;
    public void update(Stock stock) throws DAOException;
}