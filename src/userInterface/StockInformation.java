package userInterface;

import controller.ApplicationController;
import exceptions.DAOException;
import exceptions.InvalidValueException;
import model.ProductCategory;
import userInterface.TableConstructs.StockTable;
import userInterface.TableConstructs.TableConstruct;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class StockInformation extends JPanel {
    private JLabel titleLabel;;
    private StockTable stockTable;
    private JComboBox<String> categoryComboBox;
    private ApplicationController controller;

    public StockInformation() {
        titleLabel = new JLabel("Informations sur le stock");
        controller = new ApplicationController();

        // Table
        stockTable = new StockTable();

        // ComboBox
        categoryComboBox = new JComboBox<>();
        try {
            ArrayList<ProductCategory> categories = controller.findAllCategories();
            for (ProductCategory category : categories) {
                categoryComboBox.addItem(category.getName());
            }
        } catch (DAOException | InvalidValueException ex){
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erreur lors de la recherche de catégorie", JOptionPane.ERROR_MESSAGE);
        }

        categoryComboBox.addActionListener(e -> {
            String selectedCategory = (String) categoryComboBox.getSelectedItem();
            stockTable.fillTable(selectedCategory);
        });


        this.add(titleLabel, BorderLayout.NORTH);
        this.add(categoryComboBox, BorderLayout.SOUTH);
        this.add(new JScrollPane(stockTable.getTable()));
    }
}