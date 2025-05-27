package businessLogic;

import DAO.CustomerDBAccess;
import DAOinterfaces.CustomerDAO;
import exceptions.DAOException;
import exceptions.InvalidValueException;
import model.Customer;
import model.CustomerAddressInfo;
import model.CustomerDeletionMode;

import java.util.ArrayList;

public class CustomerManager {
    private CustomerDAO dao;

    public CustomerManager(){
        dao = new CustomerDBAccess();
    }

    public void save(Customer customer) throws DAOException {
        dao.save(customer);
    }
    public Customer findById(int customerId) throws DAOException, InvalidValueException {
        return dao.findById(customerId);
    }

    public int delete(int customerId, CustomerDeletionMode deleteMode) throws DAOException, InvalidValueException {
        return dao.delete(customerId, deleteMode);
    }

    public void update(Customer customer) throws DAOException {
        dao.update(customer);
    }

    public ArrayList<Customer> findAll() throws DAOException, InvalidValueException {
        return dao.findAll();
    }

    public int lastId() throws DAOException {
        return dao.lastId();
    }
    public ArrayList<CustomerAddressInfo> findByLoyaltyPoints(int nbPointsMin, int nbPointsMax) throws DAOException, InvalidValueException {
        return dao.findByLoyaltyPoints(nbPointsMin, nbPointsMax);
    }

    public boolean existsById(int customerId) throws DAOException {
        return dao.existsById(customerId);
    }
}
