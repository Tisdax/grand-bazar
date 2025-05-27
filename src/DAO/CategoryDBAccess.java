package DAO;

import DAOinterfaces.CategoryDAO;
import exceptions.DAOException;
import exceptions.InvalidValueException;
import model.ProductCategory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CategoryDBAccess implements CategoryDAO {
    public ArrayList<ProductCategory> findAll() throws DAOException, InvalidValueException {
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
            throw new DAOException(e.getMessage(), "Erreur lors de la récupération de la liste des catégories.");
        }
    }
}
