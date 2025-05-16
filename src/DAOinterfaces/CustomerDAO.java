package DAOinterfaces;

import exceptions.DAOException;
import exceptions.InvalidValueException;
import model.Customer;
import model.CustomerAddressInfo;
import model.CustomerDeletionMode;

import java.util.ArrayList;

public interface CustomerDAO {
    public boolean exists(int customerId) throws DAOException;
    public void addCustomer(Customer customer) throws DAOException;
    public int deleteCustomer(int customerId, CustomerDeletionMode deleteMode) throws DAOException, InvalidValueException;
    public void updateCustomer(Customer customer) throws DAOException;
    public ArrayList<Customer> customerList() throws DAOException, InvalidValueException;
    public ArrayList<CustomerAddressInfo> CustomerAddressSearch(int nbPointsMin, int nbPointsMax) throws DAOException, InvalidValueException;
}