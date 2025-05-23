package DAOinterfaces;

import exceptions.DAOException;

public interface LoyaltyCardDAO {
    public int delete(int loyaltyCardId) throws DAOException;
    public int lastId() throws DAOException;
    public void save(int customerId) throws DAOException;
}
