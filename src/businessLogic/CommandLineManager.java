package businessLogic;

import DAO.CommandLineDBAccess;
import DAOinterfaces.CommandLineDAO;
import exceptions.DAOException;
import exceptions.InvalidValueException;
import model.CommandLine;

import java.util.ArrayList;

public class CommandLineManager {
    private CommandLineDAO dao;

    public CommandLineManager(){
        dao = new CommandLineDBAccess();
    }

    public boolean existsById(int sale, String product) throws DAOException {
        return dao.existsById(sale, product);
    }
    public CommandLine findById(int sale, String product) throws DAOException, InvalidValueException{
        return dao.findById(sale, product);
    }
    public void save(CommandLine commandLine) throws DAOException {
        dao.save(commandLine);
    }
    public int deleteBySale(int saleId) throws DAOException {
        return dao.deleteBySale(saleId);
    }
    public int deleteByProduct(String productId) throws DAOException {
        return dao.deleteByProduct(productId);
    }
    public void update(CommandLine commandLine) throws DAOException {
        dao.update(commandLine);
    }
    public ArrayList<CommandLine> findBySale(int sale) throws DAOException, InvalidValueException {
        return dao.findBySale(sale);
    }
}
