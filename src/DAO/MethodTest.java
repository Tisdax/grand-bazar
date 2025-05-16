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
            System.out.println(customerDAO.exists(3));
            if (!productDAO.exists("25"))
                productDAO.addProduct(new Product("25", "jus dfraise", (double)1, 12, 200, true, LocalDate.of(2025, 4, 2), "fruits"));
            else
                System.out.println("product exists");
        }
        catch (DAOException | InvalidValueException e) {
            System.out.println(e.getMessage());
        }
    }
}
