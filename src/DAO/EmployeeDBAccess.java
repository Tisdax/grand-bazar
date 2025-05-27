package DAO;

import DAOinterfaces.EmployeeDAO;
import exceptions.DAOException;
import exceptions.InvalidValueException;
import model.Customer;
import model.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeDBAccess implements EmployeeDAO {
    public boolean existsById(int employeeId) throws DAOException {
        String sqlInstruction = "select * from employee where id = ?";
        try {
            Connection connection = SingletonConnection.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);

            preparedStatement.setInt(1, employeeId);

            ResultSet data = preparedStatement.executeQuery();

            return data.next();
        }
        catch (SQLException e) {
            throw new DAOException(e.getMessage(), "Erreur lors de la recherche de l'employé" + employeeId + " pour savoir s'il existe.");
        }
    }

    public ArrayList<Employee> findAll() throws DAOException, InvalidValueException {
        String sqlInstruction = "select * from employee";
        try {
            Connection connection = SingletonConnection.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);

            ResultSet data = preparedStatement.executeQuery();

            ArrayList<Employee> employees = new ArrayList<>();
            Employee employee;
            int managerId;

            while (data.next()) {
                employee = new Employee(data.getInt("id"), data.getString("last_name"), data.getString("first_name"), data.getInt("address_locality_zip_code"), data.getString("address_locality_name"), data.getString("address_street"), data.getString("address_house_number"));

                managerId = data.getInt("manager_id");
                if (!data.wasNull())
                    employee.setManagerId(managerId);

                employees.add(employee);
            }
            return employees;
        }
        catch (SQLException e) {
            throw new DAOException(e.getMessage(), "Erreur lors de la récupération de la liste des employés.");
        }
    }
}
