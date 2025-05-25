package DAOinterfaces;

import exceptions.DAOException;
import exceptions.InvalidValueException;
import model.CommandLine;

import java.util.ArrayList;

public interface CommandLineDAO {
    public boolean existsById(int sale, String product) throws DAOException;
    public CommandLine findById(int sale, String product) throws DAOException, InvalidValueException;
    public void save(CommandLine commandLine) throws DAOException;
    public int deleteBySale(int saleId) throws DAOException;
    public int deleteByProduct(String productId) throws DAOException;
    public void update(CommandLine commandLine) throws DAOException;
    public ArrayList<CommandLine> findBySale(int sale) throws DAOException, InvalidValueException;
}
