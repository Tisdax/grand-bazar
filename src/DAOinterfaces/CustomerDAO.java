package DAOinterfaces;

import exceptions.DAOException;
import exceptions.InvalidValueException;
import model.Customer;
import model.CustomerAddressInfo;
import model.CustomerDeletionMode;

import java.util.ArrayList;

public interface CustomerDAO {
    public boolean existsById(int customerId) throws DAOException;
    public Customer findById(int customerId) throws DAOException, InvalidValueException;
    public int lastId() throws DAOException;
    public void save(Customer customer) throws DAOException;
    public int delete(int customerId, CustomerDeletionMode deleteMode) throws DAOException, InvalidValueException;
    public void update(Customer customer) throws DAOException;
    public ArrayList<Customer> findAll() throws DAOException, InvalidValueException;
    public ArrayList<CustomerAddressInfo> findByLoyaltyPoints(int nbPointsMin, int nbPointsMax) throws DAOException, InvalidValueException;
}