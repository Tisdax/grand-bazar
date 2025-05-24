package userInterface;
import userInterface.TableConstructs.TableConstruct;

import javax.swing.*;
import java.awt.*;

public class ProductManager extends JPanel {
    private JLabel titleLabel;
    private JButton addProductButton;
    private JButton removeProductButton;
    private JButton editProductButton;
    private TableConstruct tableConstruct;

    public ProductManager() {
        PanelSwitchActionner switchActionner = new PanelSwitchActionner();
        // Main Layout
        setLayout(new BorderLayout(0, 50));
        setBorder(BorderFactory.createEmptyBorder(0, 100, 0, 100));
        // Buttons Layout
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        // Title Panel
        JPanel titlePanel = new JPanel();

        // Table
        String[] columnNames = {
                "ID", "Name", "Prix Net", "% TVA", "Pts Fidélité", "Eligible", "Qt min",
                "Qt min en promo", "Date mise en vente", "Temps avant retrait", "Catégorie"
        };
        tableConstruct = new TableConstruct(columnNames);
        tableConstruct.fillProductTable();

        titleLabel = new JLabel("Gestion des produits");
        titleLabel.setFont(new Font("Dialog", Font.PLAIN, 30));
        titlePanel.add(titleLabel);

        // Buttons
        addProductButton = switchActionner.createButton("Ajouter un produit", ProductForm::new);
        removeProductButton = switchActionner.createDeleteProductButton();
        editProductButton = switchActionner.createEditProductButton();
        buttonPanel.add(addProductButton);
        buttonPanel.add(removeProductButton);
        buttonPanel.add(editProductButton);

        this.add(titlePanel, BorderLayout.NORTH);
        this.add(buttonPanel, BorderLayout.CENTER);
        this.add(new JScrollPane(tableConstruct.getTable()), BorderLayout.SOUTH);
    }
}