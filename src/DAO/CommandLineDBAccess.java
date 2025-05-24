package DAO;

import DAOinterfaces.CommandLineDAO;
import exceptions.DAOException;
import model.CommandLine;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CommandLineDBAccess implements CommandLineDAO {
    public void save(CommandLine commandLine) throws DAOException {
        String sqlInstruction = "insert into command_line(sale, product, quantity) values (?, ? , ?)";
        try {
            Connection connection = SingletonConnection.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);

            preparedStatement.setInt(1, commandLine.getSale());
            preparedStatement.setString(2, commandLine.getProduct());
            preparedStatement.setInt(3, commandLine.getQuantity());

            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            throw new DAOException(e.getMessage(), "Erreur lors de l'ajout de la ligne de commande liée à la vente " + commandLine.getSale() + " et au produit " + commandLine.getProduct() + ".");
        }
    }

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
