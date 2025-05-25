package DAO;

import DAOinterfaces.CustomerTypeDAO;
import exceptions.DAOException;
import model.CustomerType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerTypeDBAccess implements CustomerTypeDAO {
    public ArrayList<CustomerType> findAll() throws DAOException {
        String sqlInstruction = "select * from type";
        try {
            Connection connection = SingletonConnection.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);

            ResultSet data = preparedStatement.executeQuery();

            ArrayList<CustomerType> customerTypes = new ArrayList<>();
            CustomerType customerType;

            while (data.next()) {
                customerType = new CustomerType(data.getString("name"), data.getString("description"));
                customerTypes.add(customerType);
            }

            return  customerTypes;
        }
        catch (SQLException e) {
            throw new DAOException(e.getMessage(), "Erreur lors de la récupération de la liste des types de clients.");
        }
    }
}
