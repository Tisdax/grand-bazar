package DAOinterfaces;

import exceptions.DAOException;
import exceptions.InvalidValueException;
import model.Employee;

import java.util.ArrayList;

public interface EmployeeDAO {
    public boolean existsById(int employeeId) throws DAOException;
    public ArrayList<Employee> findAll() throws DAOException, InvalidValueException;
}