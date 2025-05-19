package businessLogic;

import DAO.ProductDBAccess;
import DAOinterfaces.ProductDAO;
import exceptions.DAOException;
import exceptions.InvalidValueException;
import model.Product;
import model.ProductOrderSummary;
import model.ProductStockInfo;

import java.time.LocalDate;
import java.util.ArrayList;

public class ProductManager {
    private ProductDAO dao;

    public ProductManager(){
        dao = new ProductDBAccess();
    }

    public boolean exists(String productid) throws DAOException {
        return dao.exists(productid);
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
    public ArrayList<ProductStockInfo> productStockSearch(String categoryId) throws DAOException, InvalidValueException {
        return dao.productStockSearch(categoryId);
    }
    public ArrayList<ProductOrderSummary> productSalesSearch(LocalDate startDate, LocalDate endDate) throws DAOException, InvalidValueException {
        return dao.productSalesSearch(startDate, endDate);
    }

}
