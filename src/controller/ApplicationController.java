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
    private ShelfManager shelfManager;
    private StockManager stockManager;
    private CustomerTypeManager customerTypeManager;
    private CommandLineManager commandLineManager;

    public ApplicationController(){
        connectionManager = new ConnectionManager();
        categoryManager = new CategoryManager();
        productManager = new ProductManager();
        addressManager = new AddressManager();
        customerManager = new CustomerManager();
        localityManager = new LocalityManager();
        loyaltyCardManager = new LoyaltyCardManager();
        shelfManager = new ShelfManager();
        stockManager = new StockManager();
        customerTypeManager = new CustomerTypeManager();
        commandLineManager = new CommandLineManager();
    }

    public void closeConnection() throws DAOException {
        connectionManager.closeConnection();
    }

    // Category
    public ArrayList<ProductCategory> findAllCategories() throws DAOException, InvalidValueException {
        return categoryManager.findAll();
    }

    // Product
    public boolean productExistsById(String productid) throws DAOException {
        return productManager.existsById(productid);
    }

    public void saveProduct(Product product) throws DAOException {
        productManager.save(product);
    }
    public Product findProductById(String productId) throws DAOException, InvalidValueException {
        return productManager.findById(productId);
    }
    public int deleteProduct(String productId) throws DAOException {
        return productManager.delete(productId);
    }

    public void updateProduct(Product product) throws DAOException {
        productManager.update(product);
    }

    public ArrayList<Product> findAllProducts() throws DAOException, InvalidValueException {
        return productManager.findAll();
    }

    public ArrayList<ProductStockInfo> findProductsByCategoryId(String categoryId) throws DAOException, InvalidValueException {
        return productManager.findByCategoryId(categoryId);
    }

    public ArrayList<ProductOrderSummary> findProductsBySaleDate(LocalDate startDate, LocalDate endDate) throws DAOException, InvalidValueException {
        return productManager.findBySaleDate(startDate, endDate);
    }

    // Address
    public boolean addressExistsById(Address address) throws DAOException {
        return addressManager.existsById(address);
    }
    public Address findAddressById(int localityZipCode, String localityName, String street, String houseNumber) throws DAOException, InvalidValueException {
        return addressManager.findById(localityZipCode, localityName, street, houseNumber);
    }
    public void saveAddress(Address address) throws DAOException {
        addressManager.save(address);
    }
    public void updateAddress(Address address) throws DAOException {
        addressManager.update(address);
    }


    // Customer
    public void saveCustomer(Customer customer) throws DAOException {
        customerManager.save(customer);
    }
    public Customer findCustomerById(int customerId) throws DAOException, InvalidValueException {
        return customerManager.findById(customerId);
    }

    public int deleteCustomer(int customerId, CustomerDeletionMode deleteMode) throws DAOException, InvalidValueException {
        return customerManager.delete(customerId, deleteMode);
    }

    public void updateCustomer(Customer customer) throws DAOException {
        customerManager.update(customer);
    }

    public boolean customerExistsById(int customerId) throws DAOException {
        return customerManager.existsById(customerId);
    }

    public ArrayList<Customer> findAllCustomers() throws DAOException, InvalidValueException {
        return customerManager.findAll();
    }

    public int lastCustomerId() throws DAOException {
        return customerManager.lastId();
    }

    public ArrayList<CustomerAddressInfo> findCustomersByLoyaltyPoints(int nbPointsMin, int nbPointsMax) throws DAOException, InvalidValueException {
        return customerManager.findByLoyaltyPoints(nbPointsMin, nbPointsMax);
    }
    // Locality
    public ArrayList<Locality> findAllLocalities() throws DAOException, InvalidValueException {
        return localityManager.findAll();
    }

    public void saveLoyaltyCard(int customerId) throws DAOException {
        loyaltyCardManager.save(customerId);
    }

    public ArrayList<Shelf> findAllShelves() throws DAOException {
        return shelfManager.findAll();
    }

    public int deleteStock(String productId) throws DAOException {
        return stockManager.deleteById(productId);
    }

    public void saveStock(Stock stock) throws DAOException {
        stockManager.save(stock);
    }

    public boolean stockExistsById(Stock stock) throws DAOException {
        return stockManager.existsById(stock);
    }

    public void updateStock(Stock stock) throws DAOException{
        stockManager.update(stock);
    }

    public ArrayList<ProductLowStockInfo> findProductsByLowStock() throws DAOException, InvalidValueException {
        return productManager.findByLowStock();
    }

    // CustomerType
    public ArrayList<CustomerType> findAllCustomerTypes() throws DAOException, InvalidValueException {
        return customerTypeManager.findAll();
    }

    public boolean CommandLineManagerexistsById(int sale, String product) throws DAOException {
        return commandLineManager.existsById(sale, product);
    }
    public CommandLine findCommandLineById(int sale, String product) throws DAOException, InvalidValueException{
        return commandLineManager.findById(sale, product);
    }
    public void saveCommandLine(CommandLine commandLine) throws DAOException {
        commandLineManager.save(commandLine);
    }
    public int deleteBySale(int saleId) throws DAOException {
        return commandLineManager.deleteBySale(saleId);
    }
    public int deleteByProduct(String productId) throws DAOException {
        return commandLineManager.deleteByProduct(productId);
    }
    public void update(CommandLine commandLine) throws DAOException {
        commandLineManager.update(commandLine);
    }
    public ArrayList<CommandLine> findBySale(int sale) throws DAOException, InvalidValueException {
        return commandLineManager.findBySale(sale);
    }
}
