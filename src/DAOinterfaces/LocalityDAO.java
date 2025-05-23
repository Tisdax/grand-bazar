package DAOinterfaces;

import exceptions.DAOException;
import exceptions.InvalidValueException;
import model.Locality;

import java.util.ArrayList;

public interface LocalityDAO {
    public ArrayList<Locality> findAll() throws DAOException, InvalidValueException;
}
