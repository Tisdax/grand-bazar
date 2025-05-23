package DAO;

import DAOinterfaces.*;
import exceptions.DAOException;
import exceptions.InvalidValueException;
import model.*;

import java.time.LocalDate;
import java.util.ArrayList;

public class MethodTest {
    public static void main(String[] args) {
        CustomerDAO customerDAO = new CustomerDBAccess();
        ProductDAO productDAO = new ProductDBAccess();
        AddressDAO addressDAO = new AddressDBAccess();
        CategoryDAO categoryDAO = new CategoryDBAccess();
        LocalityDAO localityDAO = new LocalityDBAccess();
        DAO dao = new DBAccess();

        try {
            Address address = new Address("Rue du ruisseau",1340, "Ottignies", "24", 2);
            System.out.println(address);
            dao.closeConnection();
        }
        catch (DAOException e) {
            System.out.println(e.getDescription());
        }
        catch (InvalidValueException e) {
            System.out.println(e.getMessage());
        }
    }
}
