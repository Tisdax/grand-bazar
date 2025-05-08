package dataAccess;

import model.Product;

import java.util.ArrayList;

public interface ProductDataAccess {
    public void addProduct(Product product) throws DBAccesException;
    public void deleteProduct(String productId) throws DBAccesException;
    public void updateProduct(Product product) throws DBAccesException;
    public ArrayList<Product> productList() throws DBAccesException;
    // à compléter
}
