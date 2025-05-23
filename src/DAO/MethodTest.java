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
            ArrayList<ProductLowStockInfo> productLowStockInfos = productDAO.productLowStockSearch();
            for (ProductLowStockInfo productLowStockInfo : productLowStockInfos) {
                System.out.println(productLowStockInfo.getProductName());
            }
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
