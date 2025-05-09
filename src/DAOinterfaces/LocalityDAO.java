package DAOinterfaces;

import exceptions.DBAccesException;
import model.Locality;

import java.util.ArrayList;

public interface LocalityDAO {
    public ArrayList<Locality> localitiesList() throws DBAccesException;
}
