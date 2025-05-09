package DAOinterfaces;

import exceptions.DBAccesException;

public interface LoyaltyCardDAO {
    public int delete(int loyaltyCardId) throws DBAccesException;
}
