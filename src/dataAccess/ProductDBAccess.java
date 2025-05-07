package dataAccess;

import model.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductDBAccess implements ProductDataAccess {
    public void addProduct(String productID) {

    };
    public void deleteProduct() {
        // à compléter
    };
    public void alterProduct() {
        // à compléter
    };
    public ArrayList<Product> productList() throws DBAccesException {
        String sqlInstruction = "select * from product";
        try {
            PreparedStatement preparedStatement = SingletonConnection.getInstance().prepareStatement(sqlInstruction);
            ResultSet data = preparedStatement.executeQuery();
            ArrayList<Product> products = new ArrayList<>();
            Product product;

            while (data.next()) {
                product = new Product(data.getString("id"), data.getString("name"), data.getDouble("netPrice"), data.getInt("vatPercentage"), data.getInt("loyaltyPointsNb"), data.getBoolean("isEdible"), data.getString("categoryName"));

            }
            return products;
        }
        catch (SQLException e) {
            throw new DBAccesException(e.getMessage());
        }
    }
}
