package DAOinterfaces;

import exceptions.DAOException;
import model.Address;

public interface AddressDAO {
    public void addAddress(Address address) throws DAOException;
    public boolean exists(Address address) throws DAOException;
}