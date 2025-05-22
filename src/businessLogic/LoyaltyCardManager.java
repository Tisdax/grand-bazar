package businessLogic;

import DAO.LoyaltyCardDBAccess;
import DAOinterfaces.LoyaltyCardDAO;

public class LoyaltyCardManager {
    private LoyaltyCardDAO dao;

    public LoyaltyCardManager(){
        dao = new LoyaltyCardDBAccess();
    }

    public void addLoyaltyCard(int customerId){
        dao.addLoyaltyCard(customerId);
    }
}
