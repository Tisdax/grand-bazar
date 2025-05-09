package dataAccess;

import DAOinterfaces.*;
import exceptions.DBAccesException;
import model.*;

import java.time.LocalDate;
import java.util.ArrayList;

public class MethodTest {
    public static void main(String[] args) {
        CustomerDataAccess customerDBAccess = new CustomerDBAccess();
        ProductDataAccess productDataAccess = new ProductDBAccess();
        AddressDataAccess addressDataAccess = new AddressDBAccess();
        CategoryDataAccess categoryDataAccess = new CategoryDBAccess();
        LocalityDataAccess localityDataAccess = new LocalityDBAccess();

        try {
            ArrayList<Locality> localities = localityDataAccess.localitiesList();
            for (Locality locality : localities) {
                System.out.println(locality.getZipCode() + ", " + locality.getName());
            }

            ArrayList<ProductCategory> categories = categoryDataAccess.categoriesList();
            for (ProductCategory category : categories) {
                System.out.println(category.getName() + ", " + category.getDescription());
            }

            Address outini = new Address("Rue du ruisseau", "1340", "Ottignies", 24);
            Address lesgensquiontdubloux = new Address("Rue du chêne", "5030", "Gembloux", 7);

            if (!addressDataAccess.exists(outini))
                addressDataAccess.addAddress(outini);

            if (!addressDataAccess.exists(outini))
                addressDataAccess.addAddress(outini);

            if (!addressDataAccess.exists(lesgensquiontdubloux))
                addressDataAccess.addAddress(lesgensquiontdubloux);

            System.out.println(customerDBAccess.deleteCustomer(1));
            System.out.println(customerDBAccess.deleteCustomer(2));
            System.out.println(customerDBAccess.deleteCustomer(3));
            customerDBAccess.addCustomer(new Customer(1, "Locht", "Julien", LocalDate.of(2005, 10, 4), false, "Rue du ruisseau", "1340", "Ottignies", 24, "particulier"));
            customerDBAccess.addCustomer(new Customer(2, "Van der Cuylen", "Mathias", LocalDate.of(2005, 10, 4), false, "Rue du chêne", "5030", "Gembloux", 7, "professionnel"));
            customerDBAccess.addCustomer(new Customer(3, "Carton de Tournai", "Martin", LocalDate.of(2005, 10, 4), false, "Rue du ruisseau", "1340", "Ottignies", 24, "particulier"));
            ArrayList<Customer> dbCustomers = customerDBAccess.customerList();
            for (Customer dbCustomer : dbCustomers)
                System.out.println(dbCustomer.getFirstName());
//
//            customerDBAccess.updateCustomer(new Customer(1, "Locht", "Juliette", LocalDate.of(2005, 10, 4), false, "Rue du ruisseau", "1340", "Ottignies", 24, "particulier"));
//            dbCustomers = customerDBAccess.customerList();
//            for (Customer dbCustomer : dbCustomers)
//                System.out.println(dbCustomer.getFirstName());
        }
        catch (DBAccesException e) {
            System.out.println(e.getMessage());
        }
    }
}
