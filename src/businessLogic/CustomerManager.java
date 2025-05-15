package businessLogic;

import DAO.CustomerDBAccess;
import DAOinterfaces.CustomerDAO;
import exceptions.DBAccesException;
import model.Customer;
import model.CustomerDeletionMode;

import java.util.ArrayList;

public class CustomerManager {
    private CustomerDAO dao;

    public CustomerManager(){
        dao = new CustomerDBAccess();
    }

    public void addCustomer(Customer customer) throws DBAccesException {
        dao.addCustomer(customer);
    }

    public int deleteCustomer(int customerId, CustomerDeletionMode deleteMode) throws DBAccesException{
        return dao.deleteCustomer(customerId, deleteMode);
    }

    public void updateCustomer(Customer customer) throws DBAccesException{
        dao.updateCustomer(customer);
    }

    public ArrayList<Customer> customerList() throws  DBAccesException {
        return dao.customerList();
    }
}
