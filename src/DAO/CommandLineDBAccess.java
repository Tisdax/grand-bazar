package DAO;

import DAOinterfaces.CommandLineDAO;
import exceptions.DBAccesException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CommandLineDBAccess implements CommandLineDAO {
    public int deleteCommandLine(int saleId) throws DBAccesException {
        String sqlInstruction = "delete from command_line where sale = ?";
        try {
            Connection connection = SingletonConnection.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);

            preparedStatement.setInt(1, saleId);

            return preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            throw new DBAccesException(e.getMessage(), "Erreur lors de la suppression d'une ligne de commande");
        }
    }

    public int deleteCommandLine(String productId) throws DBAccesException {
        String sqlInstruction = "delete from command_line where product = ?";
        try {
            Connection connection = SingletonConnection.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);

            preparedStatement.setString(1, productId);

            return preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            throw new DBAccesException(e.getMessage(), "Erreur lors de la suppression d'une ligne de commande");
        }
    }
}
