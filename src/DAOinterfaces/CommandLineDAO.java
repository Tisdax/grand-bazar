package DAOinterfaces;

import exceptions.DAOException;
import model.CommandLine;

public interface CommandLineDAO {
    public void save(CommandLine commandLine) throws DAOException;
    public int deleteBySale(int saleId) throws DAOException;
    public int deleteByProduct(String productId) throws DAOException;
}
