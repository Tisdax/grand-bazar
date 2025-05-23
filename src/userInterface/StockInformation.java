package userInterface;

import controller.ApplicationController;
import exceptions.DAOException;
import model.ProductCategory;

import javax.swing.*;
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

        // Table
        String[] columnsNames = {
                "Nom ¨produit", "Qt en stock", "Shelf Level", "Shelf ID", "Est réfrigérer"
        };
        stockTable = new TableConstruct(columnsNames);

        // ComboBox
        categoryComboBox = new JComboBox<>();
        try {
            ArrayList<ProductCategory> categories = controller.findAllCategories();
            for (ProductCategory category : categories) {
                categoryComboBox.addItem(category.getName());
            }
        } catch (DAOException ex){
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erreur lors de la recherche de catégorie", JOptionPane.ERROR_MESSAGE);
        }

        categoryComboBox.addActionListener(e -> {
            stockTable.refreshTable();
            String selectedCategory = (String) categoryComboBox.getSelectedItem();
            stockTable.fillStockTable(selectedCategory);
        });


        this.add(titleLabel, BorderLayout.NORTH);
        this.add(categoryComboBox, BorderLayout.SOUTH);
        this.add(new JScrollPane(stockTable.getTable()));
    }
}