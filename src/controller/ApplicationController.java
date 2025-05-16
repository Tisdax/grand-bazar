package controller;

import businessLogic.AddressManager;
import businessLogic.CategoryManager;
import businessLogic.CustomerManager;
import businessLogic.ProductManager;
import exceptions.DAOException;
import exceptions.InvalidValueException;
import model.*;

import java.util.ArrayList;

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

    public ArrayList<ProductCategory> getAllCategory() throws DAOException {
        return categoryManager.getAllCategory();
    }

    public void addProduct(Product product) throws DAOException {
        productManager.addProduct(product);
    }

    public int deletProduct(String productId) throws DAOException {
        return productManager.deleteProduct(productId);
    }

    public void updateProduct(Product product) throws DAOException {
        productManager.updateProduct(product);
    }

    public ArrayList<Product> productList() throws DAOException, InvalidValueException {
        return productManager.productList();
    }

    public void addAddress(Address address) throws DAOException {
        addressManager.addAddress(address);
    }

    public boolean exists(Address address) throws DAOException {
        return addressManager.exist(address);
    }

    public void addCustomer(Customer customer) throws DAOException {
        customerManager.addCustomer(customer);
    }

    public int deleteCustomer(int customerId, CustomerDeletionMode deleteMode) throws DAOException, InvalidValueException {
        return customerManager.deleteCustomer(customerId, deleteMode);
    }

    public void updateCustomer(Customer customer) throws DAOException {
        customerManager.updateCustomer(customer);
    }

    public ArrayList<Customer> customerList() throws DAOException, InvalidValueException {
        return customerManager.customerList();
    }
}
