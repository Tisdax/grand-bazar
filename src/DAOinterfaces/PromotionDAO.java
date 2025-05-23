package DAOinterfaces;

import exceptions.DAOException;

public interface PromotionDAO {
    public int delete(String productId) throws DAOException;
}
