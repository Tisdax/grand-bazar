package dataAccess;

import exceptions.DBAccesException;
import model.Customer;
import model.Product;

import java.time.LocalDate;
import java.util.ArrayList;

public class MethodTest {
    public static void main(String[] args) {
        CustomerDBAccess dbAccess = new CustomerDBAccess();
        try {
            System.out.println(dbAccess.deleteCustomer(1));
            System.out.println(dbAccess.deleteCustomer(2));
            System.out.println(dbAccess.deleteCustomer(3));
            dbAccess.addCustomer(new Customer(1, "Locht", "Julien", LocalDate.of(2005, 10, 4), false, "Rue du ruisseau", "1340", "Ottignies", 24, "particulier"));
            dbAccess.addCustomer(new Customer(2, "Van der Cuylen", "Mathias", LocalDate.of(2005, 10, 4), false, "Rue du ruisseau", "1340", "Ottignies", 24, "particulier"));
            dbAccess.addCustomer(new Customer(3, "Carton de Tournai", "Martin", LocalDate.of(2005, 10, 4), false, "Rue du ruisseau", "1340", "Ottignies", 24, "particulier"));
            ArrayList<Customer> dbCustomers = dbAccess.customerList();
            for (Customer dbCustomer : dbCustomers)
                System.out.println(dbCustomer.getFirstName());

            dbAccess.updateCustomer(new Customer(1, "Locht", "Juliette", LocalDate.of(2005, 10, 4), false, "Rue du ruisseau", "1340", "Ottignies", 24, "particulier"));
            dbCustomers = dbAccess.customerList();
            for (Customer dbCustomer : dbCustomers)
                System.out.println(dbCustomer.getFirstName());
        }
        catch (DBAccesException e) {
            System.out.println(e.getMessage());
        }
    }
}
