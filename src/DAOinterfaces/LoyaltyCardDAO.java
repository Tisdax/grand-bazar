package DAOinterfaces;

import exceptions.DAOException;

public interface LoyaltyCardDAO {
    public int delete(int loyaltyCardId) throws DAOException;

    void addLoyaltyCard(int customerId);
}
