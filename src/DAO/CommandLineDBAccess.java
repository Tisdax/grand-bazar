package DAO;

import DAOinterfaces.CommandLineDAO;
import exceptions.DAOException;
import exceptions.InvalidValueException;
import model.CommandLine;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CommandLineDBAccess implements CommandLineDAO {
    public boolean existsById(int sale, String product) throws DAOException {
        String sqlInstruction = "select * from command_line where sale = ? and product = ?";
        try {
            Connection connection = SingletonConnection.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);

            preparedStatement.setInt(1, sale);
            preparedStatement.setString(2, product);

            ResultSet data = preparedStatement.executeQuery();

            return data.next();
        }
        catch (SQLException e) {
            throw new DAOException(e.getMessage(), "Erreur lors de la recherche de la ligne de commande liée à la vente " + sale + " et au produit " + product + " pour savoir si elle existe.");
        }
    }

    public CommandLine findById(int sale, String product) throws DAOException, InvalidValueException {
        String sqlInstruction = "select * from command_line where sale = ? and product = ?";
        try {
            Connection connection = SingletonConnection.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);

            preparedStatement.setInt(1, sale);
            preparedStatement.setString(2, product);

            ResultSet data = preparedStatement.executeQuery();
            data.next();

            return new CommandLine(data.getInt("sale"), data.getString("product"), data.getInt("quantity"));
        }
        catch (SQLException e) {
            throw new DAOException(e.getMessage(), "Erreur lors de la récupération de la ligne de commande liée à la vente " + sale + " et au produit " + product + ".");
        }
    }

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
            throw new DAOException(e.getMessage(), "Erreur lors de la suppression d'une ligne de commande liée à la vente " + saleId + ".");
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
            throw new DAOException(e.getMessage(), "Erreur lors de la suppression d'une ligne de commande liée au produit " + productId + ".");
        }
    }

    public void update(CommandLine commandLine) throws DAOException {
        String sqlInstruction = "update command_line set quantity = ? where sale = ? and product = ?";
        try {
            Connection connection = SingletonConnection.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);

            preparedStatement.setInt(1, commandLine.getQuantity());
            preparedStatement.setInt(2, commandLine.getSale());
            preparedStatement.setString(3, commandLine.getProduct());

            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            throw new DAOException(e.getMessage(), "Erreur lors de la modification d'une ligne de commande liée à la vente " + commandLine.getSale() + " et au produit " + commandLine.getProduct() + ".");
        }
    }

    public ArrayList<CommandLine> findBySale(int sale) throws DAOException, InvalidValueException {
        String sqlInstruction = "select * from command_line where sale = ?";
        try {
            Connection connection = SingletonConnection.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);

            preparedStatement.setInt(1, sale);

            ResultSet data = preparedStatement.executeQuery();

            ArrayList<CommandLine> commandLines = new ArrayList<>();
            CommandLine commandLine;

            while (data.next()) {
                commandLine = new CommandLine(data.getInt("sale"), data.getString("product"), data.getInt("quantity"));
                commandLines.add(commandLine);
            }

            return commandLines;
        }
        catch (SQLException e) {
            throw new DAOException(e.getMessage(), "Erreur lors récupération de la liste des lignes de commandes liées à la vente " + sale + ".");
        }
    }
}
