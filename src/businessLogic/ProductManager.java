package businessLogic;

import DAO.ProductDBAccess;
import DAOinterfaces.ProductDAO;
import exceptions.DAOException;
import exceptions.InvalidValueException;
import model.Product;
import model.ProductLowStockInfo;
import model.ProductOrderSummary;
import model.ProductStockInfo;

import java.time.LocalDate;
import java.util.ArrayList;

public class ProductManager {
    private ProductDAO dao;

    public ProductManager(){
        dao = new ProductDBAccess();
    }

    public boolean existsById(String productid) throws DAOException {
        return dao.existsById(productid);
    }

    public Product findById(String productId) throws DAOException, InvalidValueException {
        return dao.findById(productId);
    }

    public void save(Product product) throws DAOException {
        dao.save(product);
    }

    public int delete(String productId) throws DAOException {
        return dao.delete(productId);
    }

    public void update(Product product) throws DAOException {
        dao.update(product);
    }

    public ArrayList<Product> findAll() throws DAOException, InvalidValueException {
        return dao.findAll();
    }
    public ArrayList<ProductStockInfo> findByCategoryId(String categoryId) throws DAOException, InvalidValueException {
        return dao.findByCategoryId(categoryId);
    }
    public ArrayList<ProductOrderSummary> findBySaleDate(LocalDate startDate, LocalDate endDate) throws DAOException, InvalidValueException {
        return dao.findBySaleDate(startDate, endDate);
    }
    public ArrayList<ProductLowStockInfo> findByLowStock() throws DAOException, InvalidValueException {
        return dao.findByLowStock();
    }

}
