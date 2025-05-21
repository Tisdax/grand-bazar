package businessLogic;


import DAO.AddressDBAccess;
import DAOinterfaces.AddressDAO;
import exceptions.DAOException;
import exceptions.InvalidValueException;
import model.Address;


public class AddressManager {
    private AddressDAO dao;

    public AddressManager(){
        dao = new AddressDBAccess();
    }

    public boolean exist(Address address) throws DAOException {
        return dao.exists(address);
    }
    public Address getAddress(int localityZipCode, String localityName, String street, String houseNumber) throws DAOException, InvalidValueException {
        return dao.getAddress(localityZipCode, localityName, street, houseNumber);
    }
    public void addAddress(Address address) throws DAOException {
        dao.addAddress(address);
    }
}
