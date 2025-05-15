package DAO;

import DAOinterfaces.LoyaltyCardDAO;
import exceptions.DAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
}
