package DAOinterfaces;

import exceptions.DAOException;

public interface CommandLineDAO {
    public int deleteCommandLine(int saleId) throws DAOException;
    public int deleteCommandLine(String productId) throws DAOException;
}
