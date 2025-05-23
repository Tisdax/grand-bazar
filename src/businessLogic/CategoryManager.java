package businessLogic;

import DAO.CategoryDBAccess;
import DAOinterfaces.CategoryDAO;
import exceptions.DAOException;
import model.ProductCategory;

import java.util.ArrayList;

public class CategoryManager {
    private CategoryDAO dao;

    public CategoryManager(){
        dao = new CategoryDBAccess();
    }

    public ArrayList<ProductCategory> findAll() throws DAOException {
        return dao.findAll();
    }

}
