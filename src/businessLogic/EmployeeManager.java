package businessLogic;

import DAO.EmployeeDBAccess;
import DAOinterfaces.EmployeeDAO;
import exceptions.DAOException;
import exceptions.InvalidValueException;
import model.Employee;

import java.util.ArrayList;

public class EmployeeManager {
    private EmployeeDAO dao;

    public EmployeeManager() {
        dao = new EmployeeDBAccess();
    }

    public boolean existsById(int employeeId) throws DAOException {
        return dao.existsById(employeeId);
    }
    public ArrayList<Employee> findAll() throws DAOException, InvalidValueException {
        return dao.findAll();
    }

}
