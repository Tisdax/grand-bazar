package DAOinterfaces;

import exceptions.DBAccesException;
import model.Address;

public interface AddressDAO {
    public void addAddress(Address address) throws DBAccesException;
    public boolean exists(Address address) throws DBAccesException;
}
