package businessLogic;

import DAO.LoyaltyCardDBAccess;
import DAOinterfaces.LoyaltyCardDAO;
import exceptions.DAOException;

public class LoyaltyCardManager {
    private LoyaltyCardDAO dao;

    public LoyaltyCardManager(){
        dao = new LoyaltyCardDBAccess();
    }

    public void save(int customerId) throws DAOException {
        dao.save(customerId);
    }
}
