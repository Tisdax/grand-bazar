package DAO;

import DAOinterfaces.StockDAO;
import exceptions.DAOException;
import model.Stock;

import java.sql.*;

public class StockDBAccess implements StockDAO {
    public boolean existsById(Stock stock) throws DAOException{
        String sqlInstruction = "select * from stock where shelf = ? and shelf_level = ? and product = ?";
        try {
            Connection connection = SingletonConnection.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);

            preparedStatement.setInt(1, stock.getShelfId());
            preparedStatement.setInt(2, stock.getShelfLevel());
            preparedStatement.setString(3, stock.getProductId());

            ResultSet data = preparedStatement.executeQuery();

            return data.next();
        }
        catch (SQLException e) {
            throw new DAOException(e.getMessage(), "Erreur lors de la recherche du stock lié à l'étagère " + stock.getShelfId() + ", au produit " + stock.getProductId() + " et à l'étage " + stock.getShelfLevel() + ".");
        }
    }

    public void save(Stock stock) throws DAOException {
        String sqlInstruction = "insert into stock(shelf, shelf_level, product, quantity) values (?, ?, ?, ?)";
        try {
            Connection connection = SingletonConnection.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);

            connection.prepareStatement(sqlInstruction);

            preparedStatement.setInt(1, stock.getShelfId());
            preparedStatement.setInt(2, stock.getShelfLevel());
            preparedStatement.setString(3, stock.getProductId());
            preparedStatement.setInt(4, stock.getQuantity());

            preparedStatement.executeUpdate();

        }
        catch (SQLException e) {
            throw new DAOException(e.getMessage(), "Erreur lors de l'ajout du produit " + stock.getProductId() + " en stock dans l'étagère " + stock.getShelfId() + " à l'étage " + stock.getShelfLevel() + ".");
        }
    }

    public int deleteById(String productId) throws DAOException {
        String sqlInstruction = "delete from stock where product = ?";
        try {
            Connection connection = SingletonConnection.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);

            preparedStatement.setString(1, productId);

            return preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            throw new DAOException(e.getMessage(), "Erreur lors de la suppression d'un stock lié au produit " + productId + ".");
        }
    }

    public void update(Stock stock) throws DAOException {
        String sqlInstruction = "update stock set quantity = ? where shelf = ? and shelf_level = ? and product = ?";
        try {
            Connection connection = SingletonConnection.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);


            preparedStatement.setInt(1, stock.getQuantity());
            preparedStatement.setInt(2, stock.getShelfId());
            preparedStatement.setInt(3, stock.getShelfLevel());
            preparedStatement.setString(4, stock.getProductId());

            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            throw new DAOException(e.getMessage(), "Erreur lors de la modification d'un produit en stock");
        }
    }
}
