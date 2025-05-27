package DAOinterfaces;

import exceptions.DAOException;
import exceptions.InvalidValueException;
import model.CustomerType;

import java.util.ArrayList;

public interface CustomerTypeDAO {
    public ArrayList<CustomerType> findAll() throws DAOException, InvalidValueException;
}
