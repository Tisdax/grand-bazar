package DAOinterfaces;

import exceptions.DAOException;
import exceptions.InvalidValueException;
import model.ProductCategory;

import java.util.ArrayList;

public interface CategoryDAO {
    public ArrayList<ProductCategory> findAll() throws DAOException, InvalidValueException;
}
