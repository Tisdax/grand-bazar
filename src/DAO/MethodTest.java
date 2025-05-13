package DAO;

import DAOinterfaces.*;
import exceptions.DBAccesException;
import model.*;

import java.time.LocalDate;
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
            ArrayList<ProductOrderSummary> productsSearchInfos = productDBAccess.productSalesSearch(LocalDate.of(2024, 5, 2), LocalDate.of(2024, 12, 2));
            for (ProductOrderSummary productOrderSummary : productsSearchInfos)
                System.out.println(productOrderSummary.getProductId() + " " + productOrderSummary.getProductName() + " " + productOrderSummary.getProductNetPrice() + " " + productOrderSummary.getQuantity() + " " + productOrderSummary.getSaleId() + " " + productOrderSummary.getSaleDate());
            dbAccess.closeConnection();
        }
        catch (DBAccesException e) {
            System.out.println(e.getMessage());
        }

        Product product = new Product("bii", "noix de coco", 1.75, 12, 20, true, LocalDate.of(2025, 6, 2), "fruits");
        System.out.println(product.getNetPrice());
        product.setNetPrice(-1.1);
        System.out.println(product.getNetPrice());

    }
}
