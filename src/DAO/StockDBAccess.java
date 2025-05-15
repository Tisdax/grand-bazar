package DAO;

import DAOinterfaces.StockDAO;
import exceptions.DAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StockDBAccess implements StockDAO {
    public int deleteStock(String productId) throws DAOException {
        String sqlInstruction = "delete from stock where product = ?";
        try {
            Connection connection = SingletonConnection.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);

            preparedStatement.setString(1, productId);

            return preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            throw new DAOException(e.getMessage(), "Erreur lors de la suppression d'un stock");
        }
    }
}
