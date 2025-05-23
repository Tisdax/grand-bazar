package DAOinterfaces;

import exceptions.DAOException;
import exceptions.InvalidValueException;
import model.Product;
import model.ProductLowStockInfo;
import model.ProductOrderSummary;
import model.ProductStockInfo;

import java.time.LocalDate;
import java.util.ArrayList;

public interface ProductDAO {
    public boolean existsById(String productId) throws DAOException;
    public Product findById(String productId) throws DAOException, InvalidValueException;
    public void save(Product product) throws DAOException;
    public int delete(String productId) throws DAOException;
    public void update(Product product) throws DAOException;
    public ArrayList<Product> findAll() throws DAOException, InvalidValueException;
    public ArrayList<ProductStockInfo> findByCategoryId(String categoryId) throws DAOException, InvalidValueException;
    public ArrayList<ProductOrderSummary> findBySaleDate(LocalDate startDate, LocalDate endDate) throws DAOException, InvalidValueException;
    public ArrayList<ProductLowStockInfo> findByLowStock() throws DAOException, InvalidValueException;
}