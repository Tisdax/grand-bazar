package businessLogic;

import DAO.CustomerDBAccess;
import DAOinterfaces.CustomerDAO;
import exceptions.DAOException;
import model.Customer;
import model.CustomerDeletionMode;

import java.util.ArrayList;

public class CustomerManager {
    private CustomerDAO dao;

    public CustomerManager(){
        dao = new CustomerDBAccess();
    }

    public void addCustomer(Customer customer) throws DAOException {
        dao.addCustomer(customer);
    }

    public int deleteCustomer(int customerId, CustomerDeletionMode deleteMode) throws DAOException {
        return dao.deleteCustomer(customerId, deleteMode);
    }

    public void updateCustomer(Customer customer) throws DAOException {
        dao.updateCustomer(customer);
    }

    public ArrayList<Customer> customerList() throws DAOException {
        return dao.customerList();
    }
}
