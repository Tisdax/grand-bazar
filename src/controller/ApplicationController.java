package controller;

import DAOinterfaces.CategoryDAO;
import businessLogic.CategoryManager;
import businessLogic.ProductManager;
import exceptions.DBAccesException;
import model.Product;
import model.ProductCategory;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ApplicationController {
    private CategoryManager categoryManager;
    private ProductManager productManager;

    public ApplicationController(){
        categoryManager = new CategoryManager();
        productManager = new ProductManager();
    }

    public ArrayList<ProductCategory> getAllCategory() throws DBAccesException {
        return categoryManager.getAllCategory();
    }

    public void addProduct(Product product) throws DBAccesException {
        productManager.addProduct(product);
    }

    public int deletProduct(String productId) throws DBAccesException {
        return productManager.deleteProduct(productId);
    }

    public void updateProduct(Product product) throws DBAccesException {
        productManager.updateProduct(product);
    }

    public ArrayList<Product> productList() throws DBAccesException {
        return productManager.productList();
    }
}
