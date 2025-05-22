package controller;

import businessLogic.*;
import exceptions.DAOException;
import exceptions.InvalidValueException;
import model.*;

import java.time.LocalDate;
import java.util.ArrayList;

public class ApplicationController {
    private ConnectionManager connectionManager;
    private CategoryManager categoryManager;
    private ProductManager productManager;
    private AddressManager addressManager;
    private CustomerManager customerManager;
    private LocalityManager localityManager;
    private LoyaltyCardManager loyaltyCardManager;

    public ApplicationController(){
        connectionManager = new ConnectionManager();
        categoryManager = new CategoryManager();
        productManager = new ProductManager();
        addressManager = new AddressManager();
        customerManager = new CustomerManager();
        localityManager = new LocalityManager();
        loyaltyCardManager = new LoyaltyCardManager();
    }

    public void closeConnection() throws DAOException {
        connectionManager.closeConnection();
    }

    // Category
    public ArrayList<ProductCategory> getAllCategory() throws DAOException {
        return categoryManager.getAllCategory();
    }

    // Product
    public boolean exists(String productid) throws DAOException {
        return productManager.exists(productid);
    }

    public void addProduct(Product product) throws DAOException {
        productManager.addProduct(product);
    }
    public Product getProduct(String productId) throws DAOException, InvalidValueException {
        return productManager.getProduct(productId);
    }
    public int deleteProduct(String productId) throws DAOException {
        return productManager.deleteProduct(productId);
    }

    public void updateProduct(Product product) throws DAOException {
        productManager.updateProduct(product);
    }

    public ArrayList<Product> productList() throws DAOException, InvalidValueException {
        return productManager.productList();
    }

    public ArrayList<ProductStockInfo> productStockSearch(String categoryId) throws DAOException, InvalidValueException {
        return productManager.productStockSearch(categoryId);
    }

    public ArrayList<ProductOrderSummary> productSalesSearch(LocalDate startDate, LocalDate endDate) throws DAOException, InvalidValueException {
        return productManager.productSalesSearch(startDate, endDate);
    }

    // Address
    public boolean exists(Address address) throws DAOException {
        return addressManager.exist(address);
    }
    public Address getAddress(int localityZipCode, String localityName, String street, String houseNumber) throws DAOException, InvalidValueException {
        return addressManager.getAddress(localityZipCode, localityName, street, houseNumber);
    }
    public void addAddress(Address address) throws DAOException {
        addressManager.addAddress(address);
    }

    public void addCustomer(Customer customer) throws DAOException {
        customerManager.addCustomer(customer);
    }

    // Customer
    public Customer getCustomer(int customerId) throws DAOException, InvalidValueException {
        return customerManager.getCustomer(customerId);
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

    public int lastId() throws DAOException {
        return customerManager.lastId();
    }

    // Locality
    public ArrayList<Locality> localitiesList() throws DAOException, InvalidValueException {
        return localityManager.localitiesList();
    }

    public void addLoyaltyCard(int customerId) throws DAOException {
        loyaltyCardManager.addLoyaltyCard(customerId);
    }
}
