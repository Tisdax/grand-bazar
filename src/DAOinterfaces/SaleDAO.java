package DAOinterfaces;

import exceptions.DAOException;
import exceptions.InvalidValueException;
import model.Sale;

import java.util.ArrayList;

public interface SaleDAO {
    public int lastId() throws DAOException;
    public void save(Sale sale) throws DAOException;
    public int delete(int customerId) throws DAOException, InvalidValueException;
    public ArrayList<Sale> findByCustomer(int customerId) throws DAOException, InvalidValueException;
    public int removeCustomer(int customerId) throws DAOException;
}
