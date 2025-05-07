package dataAccess;

import model.Product;

import java.util.ArrayList;

public interface ProductDataAccess {
    public void addProduct(String productID);
    public void deleteProduct();
    public void alterProduct();
    public ArrayList<Product> productList() throws DBAccesException;
    // à compléter
}
