package DAO;

import DAOinterfaces.CategoryDAO;
import exceptions.DBAccesException;
import model.ProductCategory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CategoryDBAccess implements CategoryDAO {
    public ArrayList<ProductCategory> categoriesList() throws DBAccesException {
        String sqlInstruction = "select * from category";
        try {
            Connection connection = SingletonConnection.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            ResultSet data = preparedStatement.executeQuery();

            ArrayList<ProductCategory> categories = new ArrayList<>();

            while (data.next()) {
                categories.add(new ProductCategory(data.getString("name"), data.getString("description")));
            }
            return categories;
        }
        catch (SQLException e) {
            throw new DBAccesException(e.getMessage(), "Erreur lors de la lecture des catégories dans la base de données");
        }
    }
}
