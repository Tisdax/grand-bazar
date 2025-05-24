package userInterface.TableConstructs;

import controller.ApplicationController;
import exceptions.DAOException;
import exceptions.InvalidValueException;
import model.Customer;
import model.Product;
import model.ProductLowStockInfo;
import model.ProductStockInfo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class TableConstruct {
    protected DefaultTableModel tableModel;
    protected JTable table;
    protected ApplicationController controller;

    public TableConstruct(String[] columnNames) {
        tableModel = new DefaultTableModel();
        table = new JTable(tableModel);
        controller = new ApplicationController();
        table.setEnabled(false);

        for (String columnName : columnNames) {
            tableModel.addColumn(columnName);
        }
    }

    public JTable getTable() {
        return table;
    }
    public void refreshTable() {
        tableModel.setRowCount(0);
        tableModel.fireTableDataChanged();
    }
}
