package dataAccess;

import exceptions.DBAccesException;
import model.Product;

import java.time.LocalDate;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ProductDBAccess dbAccess = new ProductDBAccess();
        try {
            System.out.println(dbAccess.deleteProduct("jalja"));
            System.out.println(dbAccess.deleteProduct("baab"));

            dbAccess.addProduct(new Product("baab", "pomme", 15.90, 12, 200, true, LocalDate.of(2025, 05, 07), "fruits"));
            dbAccess.addProduct(new Product("jalja", "orange", 15.90, 12, 200, true, LocalDate.of(2025, 05, 07), "fruits"));

            ArrayList<Product> products = dbAccess.productList();
            for (Product product : products) {
                System.out.println(product.getName());
            }

            dbAccess.updateProduct(new Product("jalja", "banane", 15.90, 12, 200, true, LocalDate.of(2025, 07, 18), "fruits"));
            products = dbAccess.productList();
            for (Product product : products) {
                System.out.println(product.getName());
            }
        }
        catch (DBAccesException e) {
            System.out.println(e.getMessage());
        }
    }
}
