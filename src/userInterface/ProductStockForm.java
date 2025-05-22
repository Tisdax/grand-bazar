package userInterface;

import controller.ApplicationController;
import exceptions.DAOException;
import exceptions.InvalidValueException;
import model.ProductCategory;
import model.Shelf;
import model.Stock;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ProductStockForm extends JPanel {
    private ApplicationController controller;
    private JPanel titlePanel, formPanel, buttonPanel;
    private JLabel titleLabel, productIdLabel, productQuantityLabel, shelfLabel, shelfLevelLabel;
    private JTextField productIdField;
    private JSpinner productQuantitySpinner;
    private JComboBox shelfComboBox, shelfLevelComBox;
    private JButton addButton, updateButton;

    public ProductStockForm(){
        controller = new ApplicationController();
        this.setLayout(new BorderLayout());
        titlePanel = new JPanel();
        formPanel = new JPanel(new GridBagLayout());
        buttonPanel = new JPanel();
        formPanel.setSize(100, 100);
        this.add(titlePanel, BorderLayout.NORTH);
        this.add(formPanel, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);


        // TITLE PANEL
        titleLabel = new JLabel("Formulaire de stock produit");
        Font font = new Font("Arial", Font.BOLD, 20);
        titleLabel.setFont(font);
        titlePanel.add(titleLabel);


        // FORM PANEL
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // spacing
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.LINE_END;

        gbc.gridx = 0;
        gbc.gridy = 0;
        productIdLabel = new JLabel("Identifiant du produit :", SwingConstants.RIGHT);
        formPanel.add(productIdLabel, gbc);

        gbc.gridx = 1;
        productIdField = new JTextField();
        formPanel.add(productIdField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        productQuantityLabel = new JLabel("Quantité en stock :", SwingConstants.RIGHT);
        formPanel.add(productQuantityLabel, gbc);

        gbc.gridx = 1;
        productQuantitySpinner = new JSpinner(new SpinnerNumberModel(0, 0, 99999, 1));
        formPanel.add(productQuantitySpinner, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        shelfLabel = new JLabel("Numéro de l'étagère :", SwingConstants.RIGHT);
        formPanel.add(shelfLabel, gbc);

        gbc.gridx = 1;
        shelfComboBox = new JComboBox<>();
        ArrayList<Shelf> shelfs = null;
        try {
            shelfs = controller.shelfList();
        } catch (DAOException e) {
            JOptionPane.showMessageDialog(null, "Erreur lors de la recherche des étagères veuillez réessayer", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
        for (Shelf shelf : shelfs) {
            shelfComboBox.addItem(shelf);
        }
        formPanel.add(shelfComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        shelfLevelLabel = new JLabel("Hauteur dans l'étagère", SwingConstants.RIGHT);
        formPanel.add(shelfLevelLabel, gbc);

        gbc.gridx = 1;
        shelfLevelComBox = new JComboBox<>(new Integer[] {1, 2, 3, 4});
        formPanel.add(shelfLevelComBox, gbc);


        // BUTTON PANEL
        addButton = new JButton("Ajouter");
        updateButton = new JButton("Mettre à jour");
        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);

        addButton.addActionListener(e -> {
            try {
                if (!controller.exists(productIdField.getText())){
                    JOptionPane.showMessageDialog(null, "Le produit que vous voulez ajouter au stock n'as pas été créer veuillez l'ajouter", "Erreur", JOptionPane.ERROR_MESSAGE);
                } else {
                    Stock stock = null;
                    try {
                        stock = transformStock(productIdField, productQuantitySpinner, shelfComboBox, shelfLevelComBox);
                    } catch (InvalidValueException ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                    }
                    try {
                        if (controller.exists(stock)) {
                            JOptionPane.showMessageDialog(null, "Ce produit est déjà inscrit à cette emplacement.", "Erreur", JOptionPane.ERROR_MESSAGE);
                        } else {
                            controller.addStock(stock);
                            JOptionPane.showMessageDialog(null, "Produit ajouté au stock", "Réussite", JOptionPane.INFORMATION_MESSAGE);
                            emptyForm(productIdField, productQuantitySpinner, shelfComboBox, shelfLevelComBox);
                        }
                    } catch (DAOException ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } catch (DAOException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        });

        updateButton.addActionListener(e -> {
            try {
                if (!controller.exists(productIdField.getText())){
                    JOptionPane.showMessageDialog(null, "Le produit que vous voulez modifier n'as pas été créer veuillez l'ajouter", "Erreur", JOptionPane.ERROR_MESSAGE);
                } else {
                    Stock stock = null;
                    try {
                        stock = transformStock(productIdField, productQuantitySpinner, shelfComboBox, shelfLevelComBox);
                    } catch (InvalidValueException ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                    }
                    try {
                        if (controller.exists(stock)) {
                            controller.updateStock(stock);
                            JOptionPane.showMessageDialog(null, "Produit modifié", "Réussite", JOptionPane.INFORMATION_MESSAGE);
                            emptyForm(productIdField, productQuantitySpinner, shelfComboBox, shelfLevelComBox);
                        } else {
                            JOptionPane.showMessageDialog(null, "Ce produit n'est pas inscrit à cette emplacement veuillez le créer", "Erreur", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (DAOException ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);

                    }
                }
            } catch (DAOException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    private Stock transformStock(JTextField productIdField, JSpinner productQuantitySpinner, JComboBox shelfComboBox, JComboBox shelfLevelComBox) throws InvalidValueException {
        String productId = productIdField.getText();
        Integer quantity = (Integer) productQuantitySpinner.getValue();
        Shelf shelf = (Shelf) shelfComboBox.getSelectedItem();
        Integer shelfId = shelf.getIdShelf();
        Integer shelfLevel = (Integer) shelfLevelComBox.getSelectedItem();

        return new Stock(shelfId, shelfLevel, quantity, productId);
    }

    private void emptyForm(JTextField productIdField, JSpinner productQuantitySpinner, JComboBox shelfComboBox, JComboBox shelfLevelComBox){
        productIdField.setText("");
        productQuantitySpinner.setValue(0);
        shelfComboBox.setSelectedIndex(0);
        shelfLevelComBox.setSelectedIndex(0);
    }
}
