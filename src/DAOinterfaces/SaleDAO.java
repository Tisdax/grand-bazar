package DAOinterfaces;

import exceptions.DAOException;
import exceptions.InvalidValueException;
import model.Sale;

import java.util.ArrayList;

public interface SaleDAO {
    public int deleteSale(int customerId) throws DAOException, InvalidValueException;
    public ArrayList<Sale> getSales(int customerId) throws DAOException, InvalidValueException;
    public int removeCustomerFromSales(int customerId) throws DAOException;
}
