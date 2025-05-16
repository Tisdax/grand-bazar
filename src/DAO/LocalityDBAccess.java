package DAO;

import DAOinterfaces.LocalityDAO;
import exceptions.DAOException;
import exceptions.InvalidValueException;
import model.Locality;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LocalityDBAccess implements LocalityDAO {
    public ArrayList<Locality> localitiesList() throws DAOException, InvalidValueException {
        String sqlInstruction = "select * from locality";
        try {
            Connection connection = SingletonConnection.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            ResultSet data = preparedStatement.executeQuery();
            ArrayList<Locality> localities = new ArrayList<>();

            while(data.next()) {
                localities.add(new Locality(data.getInt("zip_code"), data.getString("name")));
            }
            return localities;
        }
        catch (SQLException e) {
            throw new DAOException(e.getMessage(), "Erreur lors de la lecture des localités dans la base de données");
        }
    }
}
