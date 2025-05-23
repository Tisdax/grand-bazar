package DAOinterfaces;

import exceptions.DAOException;
import model.Shelf;

import java.util.ArrayList;

public interface ShelfDAO {
    public ArrayList<Shelf> findAll() throws DAOException;
}