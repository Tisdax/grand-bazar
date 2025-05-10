package DAOinterfaces;

import exceptions.DBAccesException;

public interface CommandLineDAO {
    public int deleteCommandLine(int saleId) throws DBAccesException;
    public int deleteCommandLine(String productId) throws DBAccesException;
}
