package DAOinterfaces;

import exceptions.DAOException;
import model.ProductCategory;

import java.util.ArrayList;

public interface CategoryDAO {
    public ArrayList<ProductCategory> categoriesList() throws DAOException;
}
