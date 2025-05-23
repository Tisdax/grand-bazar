package DAOinterfaces;

import exceptions.DAOException;

public interface CommandLineDAO {
    public int deleteBySale(int saleId) throws DAOException;
    public int deleteByProduct(String productId) throws DAOException;
}
