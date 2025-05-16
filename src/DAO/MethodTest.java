package DAO;

import DAOinterfaces.*;
import exceptions.DAOException;
import model.*;

import java.util.ArrayList;

public class MethodTest {
    public static void main(String[] args) {
        CustomerDAO customerDBAccess = new CustomerDBAccess();
        ProductDAO productDBAccess = new ProductDBAccess();
        AddressDAO addressDAO = new AddressDBAccess();
        CategoryDAO categoryDAO = new CategoryDBAccess();
        LocalityDAO localityDAO = new LocalityDBAccess();
        DBAccess dbAccess = new DBAccess();

        try {
            System.out.println(customerDBAccess.exists(3));
        }
        catch (DAOException e) {
            System.out.println(e.getMessage());
        }
    }
}
