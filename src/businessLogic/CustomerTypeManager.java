package businessLogic;

import DAO.CustomerTypeDBAccess;
import DAOinterfaces.CustomerTypeDAO;
import exceptions.DAOException;
import exceptions.InvalidValueException;
import model.CustomerType;

import java.util.ArrayList;

public class CustomerTypeManager {
    private CustomerTypeDAO dao;

    public CustomerTypeManager() {
        this.dao = new CustomerTypeDBAccess();
    }

    public ArrayList<CustomerType> findAll() throws DAOException, InvalidValueException {
        return dao.findAll();
    }
}
