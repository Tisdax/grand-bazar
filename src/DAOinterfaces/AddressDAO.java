package DAOinterfaces;

import exceptions.DAOException;
import exceptions.InvalidValueException;
import model.Address;

public interface AddressDAO {
    public boolean exists(Address address) throws DAOException;
    public Address getAddress(int localityZipCode, String localityName, String street, String houseNumber) throws DAOException, InvalidValueException;
    public void addAddress(Address address) throws DAOException;
}