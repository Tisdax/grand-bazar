package DAO;

import DAOinterfaces.SaleDAO;
import DAOinterfaces.StockDAO;
import exceptions.DBAccesException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StockDBAccess implements StockDAO {
    public int deleteStock(String productId) throws DBAccesException {
        String sqlInstruction = "delete from stock where product = ?";
        try {
            Connection connection = SingletonConnection.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);

            preparedStatement.setString(1, productId);

            return preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            throw new DBAccesException(e.getMessage(), "Erreur lors de la suppression d'un stock");
        }
    }
}
