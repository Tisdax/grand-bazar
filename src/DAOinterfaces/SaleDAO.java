package DAOinterfaces;

import exceptions.DAOException;
import model.Sale;

import java.util.ArrayList;

public interface SaleDAO {
    public int deleteSale(int customerId) throws DAOException;
    public ArrayList<Sale> getSales(int customerId) throws DAOException;
    public int removeCustomerFromSales(int customerId) throws DAOException;
}
