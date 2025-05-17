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
            ArrayList<Product> products = productDAO.productList();
            for (Product product : products)
                System.out.println(product.getId());
        }
        catch (DAOException | InvalidValueException e) {
            System.out.println(e.getMessage());
        }
    }
}
