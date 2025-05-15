package DAOinterfaces;

import exceptions.DAOException;
import model.Locality;

import java.util.ArrayList;

public interface LocalityDAO {
    public ArrayList<Locality> localitiesList() throws DAOException;
}
