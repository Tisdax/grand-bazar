package DAO;

import DAOinterfaces.LoyaltyCardDAO;
import exceptions.DAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoyaltyCardDBAccess implements LoyaltyCardDAO {
    public int deleteLoyaltyCard(int customerId) throws DAOException {
        String sqlInstruction = "delete from loyalty_card where customer = ?";
        try {
            Connection connection = SingletonConnection.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);

            preparedStatement.setInt(1, customerId);

            return preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            throw new DAOException(e.getMessage(), "Erreur lors de la suppression de la carte de fidélité");
        }
    }

    public int lastId() throws DAOException {
        String sqlInstruction = "select max(number) from loyalty_card";
        try {
            Connection connection = SingletonConnection.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);

            ResultSet data = preparedStatement.executeQuery();

            return data.next() ? data.getInt(1) : 0;
        }
        catch (SQLException e) {
            throw new DAOException(e.getMessage(), "Erreur lors de la recheche du dernier identifiant");
        }
    }

    public void addLoyaltyCard(int customerId) throws DAOException {
        String sqlInstruction = "insert into loyalty_card(number, total_points, is_valid, customer) values (?, 0, true, ?)";
        try {
            Connection connection = SingletonConnection.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);

            preparedStatement.setInt(1, lastId()+1);
            preparedStatement.setInt(2, customerId);

            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            throw new DAOException(e.getMessage(), "Erreur lors de l'ajout de carte de fidélité");
        }
    }
}
