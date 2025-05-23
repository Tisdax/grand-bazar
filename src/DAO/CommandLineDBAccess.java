package DAO;

import DAOinterfaces.CommandLineDAO;
import exceptions.DAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CommandLineDBAccess implements CommandLineDAO {
    public int deleteBySale(int saleId) throws DAOException {
        String sqlInstruction = "delete from command_line where sale = ?";
        try {
            Connection connection = SingletonConnection.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);

            preparedStatement.setInt(1, saleId);

            return preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            throw new DAOException(e.getMessage(), "Erreur lors de la suppression d'une ligne de commande attachée à la vente " + saleId + ".");
        }
    }

    public int deleteByProduct(String productId) throws DAOException {
        String sqlInstruction = "delete from command_line where product = ?";
        try {
            Connection connection = SingletonConnection.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);

            preparedStatement.setString(1, productId);

            return preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            throw new DAOException(e.getMessage(), "Erreur lors de la suppression d'une ligne de commande attachée au produit " + productId + ".");
        }
    }
}
