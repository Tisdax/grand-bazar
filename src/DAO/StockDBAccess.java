package DAO;

import DAOinterfaces.StockDAO;
import exceptions.DAOException;
import exceptions.InvalidValueException;
import model.Stock;

import java.sql.*;
import java.util.ArrayList;

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

    public ArrayList<Stock> findByProduct(String product) throws DAOException, InvalidValueException {
        String sqlInstruction = "select * from stock where product = ?";
        try {
            Connection connection = SingletonConnection.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);

            preparedStatement.setString(1, product);

            ResultSet data = preparedStatement.executeQuery();

            ArrayList<Stock> stocks = new ArrayList<>();
            Stock stock;

            while(data.next()) {
                stock = new Stock(data.getInt("shelf"), data.getInt("shelf_level"), data.getInt("quantity"), data.getString("product"));
                stocks.add(stock);
            }

            return stocks;
        }
        catch (SQLException e) {
            throw new DAOException(e.getMessage(), "Erreur lors de la récupération de la liste des stocks sur base du produit " + product + ".");
        }
    }

    public int findQuantityByProduct(String product) throws DAOException {
        String sqlInstruction = "select sum(quantity) from stock where product = ?";
        try {
            Connection connection = SingletonConnection.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);

            preparedStatement.setString(1, product);

            ResultSet data = preparedStatement.executeQuery();
            data.next();

            return data.getInt(1);
        }
        catch (SQLException e) {
            throw new DAOException(e.getMessage(), "Erreur lors de la recherche de la quantité en stock du produit " + product + ".");
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

    public int delete(int shelf, int shelfLevel, String product) throws DAOException {
        String sqlInstruction = "delete from stock where shelf = ? and shelf_level = ? and product = ?";
        try {
            Connection connection = SingletonConnection.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);

            preparedStatement.setInt(1, shelf);
            preparedStatement.setInt(2, shelfLevel);
            preparedStatement.setString(3, product);

            return preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            throw new DAOException(e.getMessage(), "Erreur lors de la suppression du stock lié au produit " + product + " dans l'étagère " + shelf + " à l'étage " + shelfLevel + ".");
        }
    }

    public int deleteByProduct(String productId) throws DAOException {
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
            throw new DAOException(e.getMessage(), "Erreur lors de la modification de la quantité en stock pour le produit " + stock.getProductId() + " dans l'étagère " + stock.getShelfId() + " à l'étage " + stock.getShelfLevel() + ".");
        }
    }

    public int lowerStocks(String product, int quantity) throws DAOException, InvalidValueException {
        int nbUpdatedLines = 0;
        ArrayList<Stock> stocks = findByProduct(product);
        Stock stock;
        int quantityLeft = quantity;
        int iStock = 0;

        while (quantityLeft > 0 && iStock < stocks.size()) {
            stock = stocks.get(iStock);

            if (quantityLeft >= stock.getQuantity()) {
                nbUpdatedLines += delete(stock.getShelfId(), stock.getShelfLevel(), stock.getProductId());
                quantityLeft -= stock.getQuantity();
            } else {
                stock.setQuantity(stock.getQuantity() - quantityLeft);
                update(stock);
                nbUpdatedLines += 1;
                quantityLeft = 0;
            }

            iStock++;
        }

        return nbUpdatedLines;
    }
}
