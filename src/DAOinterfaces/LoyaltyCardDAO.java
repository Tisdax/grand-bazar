package DAOinterfaces;

import exceptions.DAOException;

public interface LoyaltyCardDAO {
    public int deleteLoyaltyCard(int loyaltyCardId) throws DAOException;
    public void addLoyaltyCard(int customerId) throws DAOException;
}
