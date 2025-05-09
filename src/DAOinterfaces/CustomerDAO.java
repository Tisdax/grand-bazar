package DAOinterfaces;

import exceptions.DBAccesException;
import model.Customer;

import java.util.ArrayList;

public interface CustomerDAO {
    public void addCustomer(Customer customer) throws DBAccesException;
    public int deleteCustomer(int customerId) throws  DBAccesException;
    public void updateCustomer(Customer customer) throws DBAccesException;
    public ArrayList<Customer> customerList() throws  DBAccesException;
}