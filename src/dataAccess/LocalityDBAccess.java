package dataAccess;

import DAOinterfaces.LocalityDataAccess;
import exceptions.DBAccesException;
import model.Locality;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LocalityDBAccess implements LocalityDataAccess {
    public ArrayList<Locality> localitiesList() throws DBAccesException {
        String sqlInstruction = "select * from locality";
        try {
            Connection connection = SingletonConnection.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            ResultSet data = preparedStatement.executeQuery();
            ArrayList<Locality> localities = new ArrayList<>();

            while(data.next()) {
                localities.add(new Locality(data.getString("zip_code"), data.getString("name")));
            }
            return localities;
        }
        catch (SQLException e) {
            throw new DBAccesException(e.getMessage());
        }
    }
}
