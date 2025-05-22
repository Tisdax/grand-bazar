package DAO;

import DAOinterfaces.ShelfDAO;
import exceptions.DAOException;
import exceptions.InvalidValueException;
import model.ProductCategory;
import model.Shelf;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ShelfDBAccess implements ShelfDAO {
    public ArrayList<Shelf> shelfList() throws DAOException {
        String sqlInstruction = "select * from shelf";
        try {
            Connection connection = SingletonConnection.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            ResultSet data = preparedStatement.executeQuery();

            ArrayList<Shelf> shelfs = new ArrayList<>();

            while (data.next()) {
                shelfs.add(new Shelf(data.getInt("id"), data.getBoolean("is_refrigirated")));
            }
            return shelfs;
        }
        catch (SQLException e) {
            throw new DAOException(e.getMessage(), "Erreur lors de la lecture des catégories dans la base de données");
        }
    }
}
