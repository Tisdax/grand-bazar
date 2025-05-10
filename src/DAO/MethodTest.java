package DAO;

import DAOinterfaces.*;
import exceptions.DBAccesException;
import model.*;

import java.time.LocalDate;
import java.util.ArrayList;

public class MethodTest {
    public static void main(String[] args) {
        CustomerDAO customerDBAccess = new CustomerDBAccess();
        ProductDAO productDAO = new ProductDBAccess();
        AddressDAO addressDAO = new AddressDBAccess();
        CategoryDAO categoryDAO = new CategoryDBAccess();
        LocalityDAO localityDAO = new LocalityDBAccess();
//
        try {
//            ArrayList<Locality> localities = localityDAO.localitiesList();
//            for (Locality locality : localities) {
//                System.out.println(locality.getZipCode() + ", " + locality.getName());
//            }
//
//            ArrayList<ProductCategory> categories = categoryDAO.categoriesList();
//            for (ProductCategory category : categories) {
//                System.out.println(category.getName() + ", " + category.getDescription());
//            }
//
//            Address outini = new Address("Rue du ruisseau", "1340", "Ottignies", 24);
//            Address lesgensquiontdubloux = new Address("Rue du chêne", "5030", "Gembloux", 7);
//
//            if (!addressDAO.exists(outini))
//                addressDAO.addAddress(outini);
//
//            if (!addressDAO.exists(outini))
//                addressDAO.addAddress(outini);
//
//            if (!addressDAO.exists(lesgensquiontdubloux))
//                addressDAO.addAddress(lesgensquiontdubloux);

            System.out.println(customerDBAccess.deleteCustomer(1, CustomerDeletionMode.DELETE_SALES));
//            System.out.println(customerDBAccess.deleteCustomer(2));
//            System.out.println(customerDBAccess.deleteCustomer(3));
//            customerDBAccess.addCustomer(new Customer(1, "Locht", "Julien", LocalDate.of(2005, 10, 4), false, "Rue du ruisseau", "1340", "Ottignies", 24, "particulier"));
//            customerDBAccess.addCustomer(new Customer(2, "Van der Cuylen", "Mathias", LocalDate.of(2005, 10, 4), false, "Rue du chêne", "5030", "Gembloux", 7, "professionnel"));
//            customerDBAccess.addCustomer(new Customer(3, "Carton de Tournai", "Martin", LocalDate.of(2005, 10, 4), false, "Rue du ruisseau", "1340", "Ottignies", 24, "particulier"));
//            ArrayList<Customer> dbCustomers = customerDBAccess.customerList();
//            for (Customer dbCustomer : dbCustomers)
//                System.out.println(dbCustomer.getFirstName());
////
////            customerDBAccess.updateCustomer(new Customer(1, "Locht", "Juliette", LocalDate.of(2005, 10, 4), false, "Rue du ruisseau", "1340", "Ottignies", 24, "particulier"));
////            dbCustomers = customerDBAccess.customerList();
////            for (Customer dbCustomer : dbCustomers)
////                System.out.println(dbCustomer.getFirstName());
        }
        catch (DBAccesException e) {
            System.out.println(e.getMessage());
        }
    }
}
