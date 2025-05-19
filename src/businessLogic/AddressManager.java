package businessLogic;


import DAO.AddressDBAccess;
import DAOinterfaces.AddressDAO;
import exceptions.DAOException;
import model.Address;


public class AddressManager {
    private AddressDAO dao;

    public AddressManager(){
        dao = new AddressDBAccess();
    }

    public void addAddress(Address address) throws DAOException {
        dao.addAddress(address);
    }

    public boolean exist(Address address) throws DAOException {
        return dao.exists(address);
    }
}
