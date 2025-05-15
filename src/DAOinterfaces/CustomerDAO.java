package DAOinterfaces;

import exceptions.DAOException;
import model.Customer;
import model.CustomerAddressInfo;
import model.CustomerDeletionMode;

import java.util.ArrayList;

public interface CustomerDAO {
    public void addCustomer(Customer customer) throws DAOException;
    public int deleteCustomer(int customerId, CustomerDeletionMode deleteMode) throws DAOException;
    public void updateCustomer(Customer customer) throws DAOException;
    public ArrayList<Customer> customerList() throws DAOException;
    public ArrayList<CustomerAddressInfo> CustomerAddressSearch(int nbPointsMin, int nbPointsMax) throws DAOException;
}