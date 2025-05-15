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
            ArrayList<CustomerAddressInfo> customersAddressInfos = customerDBAccess.CustomerAddressSearch(500, 875);
            for(CustomerAddressInfo customerAddressInfo: customersAddressInfos)
                System.out.println(customerAddressInfo.getFirstName() + " " +customerAddressInfo.getLastName() + " " +customerAddressInfo.getLocalityName() + " " +customerAddressInfo.getStreet() + " " +customerAddressInfo.getHouseNumber() + " " +(customerAddressInfo.getPostalBoxNumber() == null ? "null" : customerAddressInfo.getPostalBoxNumber())+ " " +customerAddressInfo.getZipCode());
        }
        catch (DAOException e) {
            System.out.println(e.getMessage());
        }
    }
}
