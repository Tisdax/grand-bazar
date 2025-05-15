package controller;

import businessLogic.AddressManager;
import businessLogic.CategoryManager;
import businessLogic.CustomerManager;
import businessLogic.ProductManager;
import com.mysql.cj.xdevapi.AddResult;
import exceptions.DBAccesException;
import model.*;

import java.time.*;
import java.util.ArrayList;

import javax.swing.*;
import java.util.Date;

public class ApplicationController {
    private CategoryManager categoryManager;
    private ProductManager productManager;
    private AddressManager addressManager;
    private CustomerManager customerManager;

    public ApplicationController(){
        categoryManager = new CategoryManager();
        productManager = new ProductManager();
        addressManager = new AddressManager();
        customerManager = new CustomerManager();
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

    public void addAddress(Address address) throws DBAccesException {
        addressManager.addAddress(address);
    }

    public boolean exists(Address address) throws DBAccesException{
        return addressManager.exist(address);
    }

    public void addCustomer(Customer customer) throws DBAccesException {
        customerManager.addCustomer(customer);
    }

    public int deleteCustomer(int customerId, CustomerDeletionMode deleteMode) throws DBAccesException{
        return customerManager.deleteCustomer(customerId, deleteMode);
    }

    public void updateCustomer(Customer customer) throws DBAccesException{
        customerManager.updateCustomer(customer);
    }

    public ArrayList<Customer> customerList() throws  DBAccesException {
        return customerManager.customerList();
    }
}
