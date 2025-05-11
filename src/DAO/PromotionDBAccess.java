package DAO;

import DAOinterfaces.PromotionDAO;
import exceptions.DBAccesException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PromotionDBAccess implements PromotionDAO {
    public int deletePromotion(String productId) throws DBAccesException {
        String sqlInstruction = "delete from promotion where product = ?";
        try {
            Connection connection = SingletonConnection.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);

            preparedStatement.setString(1, productId);

            return preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            throw new DBAccesException(e.getMessage(), "Erreur lors de la suppression d'une promotion");
        }
    }
}
