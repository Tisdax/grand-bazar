package DAOinterfaces;

import exceptions.DBAccesException;
import model.Product;
import model.ProductOrderSummary;
import model.ProductStockInfo;

import java.time.LocalDate;
import java.util.ArrayList;

public interface ProductDAO {
    public void addProduct(Product product) throws DBAccesException;
    public int deleteProduct(String productId) throws DBAccesException;
    public void updateProduct(Product product) throws DBAccesException;
    public ArrayList<Product> productList() throws DBAccesException;
    public ArrayList<ProductStockInfo> productStockSearch(String categoryId) throws DBAccesException;
    public ArrayList<ProductOrderSummary> productSalesSearch(LocalDate startDate, LocalDate endDate) throws DBAccesException;
}