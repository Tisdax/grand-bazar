package businessLogic;

import DAO.ProductDBAccess;
import DAOinterfaces.ProductDAO;
import exceptions.DAOException;
import exceptions.InvalidValueException;
import model.Product;

import java.util.ArrayList;

public class ProductManager {
    private ProductDAO dao;

    public ProductManager(){
        dao = new ProductDBAccess();
    }

    public void addProduct(Product product) throws DAOException {
        dao.addProduct(product);
    }

    public int deleteProduct(String productId) throws DAOException {
        return dao.deleteProduct(productId);
    }

    public void updateProduct(Product product) throws DAOException {
        dao.updateProduct(product);
    }

    public ArrayList<Product> productList() throws DAOException, InvalidValueException {
        return dao.productList();
    }
}
