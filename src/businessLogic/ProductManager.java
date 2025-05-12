package businessLogic;

import DAO.ProductDBAccess;
import DAOinterfaces.ProductDAO;
import exceptions.DBAccesException;
import model.Product;
import model.ProductCategory;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ProductManager {
    private ProductDAO dao;

    public ProductManager(){
        dao = new ProductDBAccess();
    }

    public void addProduct(Product product) throws DBAccesException {
        dao.addProduct(product);
    }

    public int deleteProduct(String productId) throws DBAccesException {
        return dao.deleteProduct(productId);
    }

    public void updateProduct(Product product) throws DBAccesException {
        dao.updateProduct(product);
    }

    public ArrayList<Product> productList() throws DBAccesException {
        return dao.productList();
    }
}
