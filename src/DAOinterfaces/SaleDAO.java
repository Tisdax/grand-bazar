package DAOinterfaces;

import exceptions.DBAccesException;
import model.Sale;

import java.util.ArrayList;

public interface SaleDAO {
    public int deleteSale(int customerId) throws DBAccesException;
    public ArrayList<Sale> getSales(int customerId) throws DBAccesException;
    public int removeCustomerFromSales(int customerId) throws DBAccesException;
}
