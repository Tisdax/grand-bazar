package DAO;

import DAOinterfaces.*;
import exceptions.DBAccesException;
import model.*;

public class MethodTest {
    public static void main(String[] args) {
        CustomerDAO customerDBAccess = new CustomerDBAccess();
        ProductDAO productDBAccess = new ProductDBAccess();
        AddressDAO addressDAO = new AddressDBAccess();
        CategoryDAO categoryDAO = new CategoryDBAccess();
        LocalityDAO localityDAO = new LocalityDBAccess();

        try {
            System.out.println(productDBAccess.deleteProduct("P001"));
        }
        catch (DBAccesException e) {
            System.out.println(e.getMessage());
        }
    }
}
