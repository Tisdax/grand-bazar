package userInterface;

import DAO.CategoryDBAccess;
import businessLogic.CategoryManager;
import controller.ApplicationController;
import exceptions.DAOException;
import exceptions.InvalidValueException;
import model.ProductCategory;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class StockInformation extends JPanel {
    private JLabel titleLabel;;
    private TableConstruct stockTable;
    private JComboBox<String> categoryComboBox;
    private ApplicationController controller;

    public StockInformation() {
        titleLabel = new JLabel("Informations sur le stock");
        controller = new ApplicationController();
        categoryComboBox = new JComboBox<>();

        try {
            ArrayList<ProductCategory> categories = controller.getAllCategory();
            for (ProductCategory category : categories) {
                categoryComboBox.addItem(category.getName());
            }
        } catch (DAOException ex){
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erreur lors de la recherche de catégorie", JOptionPane.ERROR_MESSAGE);
        }

        String[] columnsNames = {
                "Nom ¨produit", "Qt en stock", "Shelf Level", "Shelf ID", "Est réfrigérer"
        };
        this.add(titleLabel, BorderLayout.NORTH);
        this.add(categoryComboBox, BorderLayout.SOUTH);

    }
}