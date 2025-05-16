package DAOinterfaces;

import exceptions.DAOException;
import model.Product;
import model.ProductOrderSummary;
import model.ProductStockInfo;

import java.time.LocalDate;
import java.util.ArrayList;

public interface ProductDAO {
    public boolean exists(String productId) throws DAOException;
    public void addProduct(Product product) throws DAOException;
    public int deleteProduct(String productId) throws DAOException;
    public void updateProduct(Product product) throws DAOException;
    public ArrayList<Product> productList() throws DAOException;
    public ArrayList<ProductStockInfo> productStockSearch(String categoryId) throws DAOException;
    public ArrayList<ProductOrderSummary> productSalesSearch(LocalDate startDate, LocalDate endDate) throws DAOException;
}