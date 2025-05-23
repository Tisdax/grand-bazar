package DAOinterfaces;

import exceptions.DAOException;
import exceptions.InvalidValueException;
import model.Address;

public interface AddressDAO {
    public boolean existsById(Address address) throws DAOException;
    public Address findById(int localityZipCode, String localityName, String street, String houseNumber) throws DAOException, InvalidValueException;
    public void save(Address address) throws DAOException;
}