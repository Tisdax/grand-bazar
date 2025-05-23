package DAO;

import DAOinterfaces.PromotionDAO;
import exceptions.DAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PromotionDBAccess implements PromotionDAO {
    public int deletePromotion(String productId) throws DAOException {
        String sqlInstruction = "delete from promotion where product = ?";
        try {
            Connection connection = SingletonConnection.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);

            preparedStatement.setString(1, productId);

            return preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            throw new DAOException(e.getMessage(), "Erreur lors de la suppression d'une promotion liée au produit " + productId + ".");
        }
    }
}
