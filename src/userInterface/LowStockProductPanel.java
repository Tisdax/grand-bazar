package userInterface;

import controller.ApplicationController;

import javax.swing.*;

public class LowStockProductPanel extends JPanel {
    private JLabel titleLabel;;
    private TableConstruct stockTable;
    private JComboBox<String> categoryComboBox;
    private ApplicationController controller;

    public LowStockProductPanel() {
        titleLabel = new JLabel("Produits ayant un stock insuffisant");
        controller = new ApplicationController();

        stockTable = new TableConstruct(new String[] {"Nom produit", "Qt en stock"});

    }

}
