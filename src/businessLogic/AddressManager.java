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

    public boolean existsById(Address address) throws DAOException {
        return dao.existsById(address);
    }
    public Address findById(int localityZipCode, String localityName, String street, String houseNumber) throws DAOException, InvalidValueException {
        return dao.findById(localityZipCode, localityName, street, houseNumber);
    }
    public void save(Address address) throws DAOException {
        dao.save(address);
    }
    public void update(Address address) throws DAOException {
        dao.update(address);
    }
}
