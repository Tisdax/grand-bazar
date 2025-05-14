package DAOinterfaces;

import exceptions.DBAccesException;
import model.Stock;

import java.util.ArrayList;

public interface StockDAO {
    public int deleteStock(String productId) throws DBAccesException;
}