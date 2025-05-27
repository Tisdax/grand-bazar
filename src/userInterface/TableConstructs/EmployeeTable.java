package userInterface.TableConstructs;

import exceptions.DAOException;
import exceptions.InvalidValueException;
import model.Employee;
import model.Product;

import javax.swing.*;
import java.util.ArrayList;

public class EmployeeTable extends TableConstruct {
    public EmployeeTable() {
        super(new String[]{"ID", "Prénom", "Nom"});
        fillTable();
    }
    public void fillTable() {
        try {
            ArrayList<Employee> employees = controller.findAllEmployee();
            super.refreshTable();
            for(Employee employee : employees) {
                tableModel.addRow(new Object[] {
                        employee.getId(), employee.getFirstName(), employee.getLastName()
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
}
