package businessLogic;


import DAO.AddressDBAccess;
import DAOinterfaces.AddressDAO;
import exceptions.DBAccesException;
import model.Address;

public class AddressManager {
    private AddressDAO dao;

    public AddressManager(){
        dao = new AddressDBAccess();
    }

    public void addAddress(Address address) throws DBAccesException {
        dao.addAddress(address);
    }

    public boolean exist(Address address) throws DBAccesException {
        return dao.exists(address);
    }
}
