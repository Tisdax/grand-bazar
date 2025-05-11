package DAOinterfaces;

import exceptions.DBAccesException;

public interface PromotionDAO {
    public int deletePromotion(String productId) throws DBAccesException;
}
