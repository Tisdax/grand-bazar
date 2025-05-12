package businessLogic;

import DAO.CategoryDBAccess;
import DAOinterfaces.CategoryDAO;
import exceptions.DBAccesException;
import model.ProductCategory;

import java.util.ArrayList;

public class CategoryManager {
    private CategoryDAO dao;

    public CategoryManager(){
        dao = new CategoryDBAccess();
    }

    public ArrayList<ProductCategory> getAllCategory() throws DBAccesException {
        return dao.categoriesList();
    }

}
