package businessLogic;

import DAO.EmployeeDBAccess;
import DAOinterfaces.EmployeeDAO;
import model.Employee;

import java.util.ArrayList;

public class EmployeeManager {
    private EmployeeDAO dao;

    public EmployeeManager() {
        dao = new EmployeeDBAccess();
    }
    public boolean existsById(int employeeId) throws Exception {
        return dao.existsById(employeeId);
    }
    public ArrayList<Employee> findAll() throws Exception {
        return dao.findAll();
    }

}
