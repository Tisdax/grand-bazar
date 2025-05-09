package DAOinterfaces;

import exceptions.DBAccesException;
import model.ProductCategory;

import java.util.ArrayList;

public interface CategoryDAO {
    public ArrayList<ProductCategory> categoriesList() throws DBAccesException;
}
