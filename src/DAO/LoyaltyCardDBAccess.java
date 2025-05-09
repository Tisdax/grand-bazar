package DAO;

import DAOinterfaces.LoyaltyCardDAO;
import exceptions.DBAccesException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LoyaltyCardDBAccess implements LoyaltyCardDAO {
    public int delete(int customerId) throws DBAccesException {
        String sqlInstruction = "delete from loyalty_card where customer = ?";
        try {
            Connection connection = SingletonConnection.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);

            preparedStatement.setInt(1, customerId);

            return preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            throw new DBAccesException(e.getMessage());
        }
    }
}
