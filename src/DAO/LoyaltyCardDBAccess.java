package DAO;

import DAOinterfaces.LoyaltyCardDAO;
import exceptions.DAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoyaltyCardDBAccess implements LoyaltyCardDAO {
    public int delete(int customerId) throws DAOException {
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
            throw new DAOException(e.getMessage(), "Problème lors de la recheche du dernier identifiant");
        }
    }
}
