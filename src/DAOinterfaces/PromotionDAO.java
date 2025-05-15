package DAOinterfaces;

import exceptions.DAOException;

public interface PromotionDAO {
    public int deletePromotion(String productId) throws DAOException;
}
