package userInterface;

import controller.ApplicationController;
import exceptions.DBAccesException;
import model.Product;
import model.ProductCategory;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

public class ProductForm extends JPanel {
    private JPanel titlePanel, formPanel, buttonPanel;
    private JLabel titleLabel, idLabel, nameLabel, netPriceLabel, vatLabel, isEdibleLabel, loyaltyPointsLabel, minQuantLabel, promotionQuantLabel, saleDateLabel, timeBeforeRemovingLabel, categoryLabel;
    private JTextField idField, nameField, netPriceField;
    private JComboBox vatComboBox, categoryComboBox;
    private JCheckBox isEdibleCheckBox;
    private JSpinner saleDateSpinner, loyaltyPointsSpinner, minQuantSpinner, promotionQuantSpinner, timeBeforeRemovingSpinner;
    private JButton addButton, fillButton;
    private ApplicationController controller;
    public ProductForm() throws DBAccesException {
        controller = new ApplicationController();
        this.setLayout(new BorderLayout());
        titlePanel = new JPanel();
        this.add(titlePanel, BorderLayout.NORTH);

        titleLabel = new JLabel("Formulaire de Produit");
        Font font = new Font("Arial", Font.BOLD, 20);
        titleLabel.setFont(font);
        titlePanel.add(titleLabel);

        formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(11, 2, 5, 10));
        this.add(formPanel, BorderLayout.CENTER);

        UIManager.put("Spinner.editorAlignment", JTextField.LEFT);

        buttonPanel = new JPanel();
        this.add(buttonPanel, BorderLayout.SOUTH);

        idLabel = new JLabel("Identifiant : ", SwingConstants.RIGHT);
        idField = new JTextField(15);
        formPanel.add(idLabel);
        formPanel.add(idField);

        nameLabel = new JLabel("Nom du produit : ", SwingConstants.RIGHT);
        nameField = new JTextField(15);
        formPanel.add(nameLabel);
        formPanel.add(nameField);

        netPriceLabel = new JLabel("Prix : ", SwingConstants.RIGHT);
        netPriceField = new JTextField();
        formPanel.add(netPriceLabel);
        formPanel.add(netPriceField);

        vatLabel = new JLabel("Taux de TVA : ", SwingConstants.RIGHT);
        vatComboBox = new JComboBox<>(new Integer[]{6, 12, 21});
        formPanel.add(vatLabel);
        formPanel.add(vatComboBox);

        isEdibleLabel = new JLabel("Le produit est-il comestible : ", SwingConstants.RIGHT);
        isEdibleCheckBox = new JCheckBox();
        formPanel.add(isEdibleLabel);
        formPanel.add(isEdibleCheckBox);

        loyaltyPointsLabel = new JLabel("Nombre de points de fidélité : ", SwingConstants.RIGHT);
        loyaltyPointsSpinner = new JSpinner(new SpinnerNumberModel(0, 0, null, 10));
        formPanel.add(loyaltyPointsLabel);
        formPanel.add(loyaltyPointsSpinner);

        minQuantLabel = new JLabel("Quantité minimale : ", SwingConstants.RIGHT);
        minQuantSpinner = new JSpinner(new SpinnerNumberModel(0, 0, null, 10));
        formPanel.add(minQuantLabel);
        formPanel.add(minQuantSpinner);

        promotionQuantLabel = new JLabel("Quantité minimale en cas de promotion", SwingConstants.RIGHT);
        promotionQuantSpinner = new JSpinner(new SpinnerNumberModel(0, 0, null, 10));
        formPanel.add(promotionQuantLabel);
        formPanel.add(promotionQuantSpinner);

        timeBeforeRemovingLabel = new JLabel("Temps avant suppression des rayons : ", SwingConstants.RIGHT);
        timeBeforeRemovingSpinner = new JSpinner(new SpinnerNumberModel(0, 0, null, 10));
        formPanel.add(timeBeforeRemovingLabel);
        formPanel.add(timeBeforeRemovingSpinner);

        categoryLabel = new JLabel("Catégorie du produit : ", SwingConstants.RIGHT);
        categoryComboBox = new JComboBox<>(controller.getAllCategory().toArray());
        formPanel.add(categoryLabel);
        formPanel.add(categoryComboBox);

        saleDateLabel = new JLabel("Date de mise en vente : ", SwingConstants.RIGHT);
        Date today = new Date();
        saleDateSpinner = new JSpinner(new SpinnerDateModel(today, null, null, Calendar.MONTH));
        JSpinner.DateEditor editor = new JSpinner.DateEditor(saleDateSpinner, "dd/MM/yyyy");
        saleDateSpinner.setEditor(editor);
        formPanel.add(saleDateLabel);
        formPanel.add(saleDateSpinner);

        addButton = new JButton("Ajouter Produit");
        buttonPanel.add(addButton);
        addButton.addActionListener(e -> {
            try {
                controller.addProduct(idField, nameField, netPriceField, vatComboBox,loyaltyPointsSpinner, minQuantSpinner,
                        promotionQuantSpinner, timeBeforeRemovingSpinner, isEdibleCheckBox, saleDateSpinner, categoryComboBox);
            } catch (DBAccesException ex) {
                JOptionPane.showMessageDialog(null, ex.getDescription(), "Problèmes", JOptionPane.ERROR_MESSAGE);
            }
        });

        fillButton = new JButton("Remplir produit");
        buttonPanel.add(fillButton);
        fillButton.addActionListener(fillButtonEvent -> {
            fillProductForm(new Product("I5151", "Fanta 2l", 2.75, 21, 150, 30, 60, 15,
                    true,LocalDate.of(2025, 1, 25), "légumes"));
        });

    }

    private void fillProductForm(Product product){
        idField.setText(product.getId());
        nameField.setText(product.getName());
        netPriceField.setText(String.valueOf(product.getNetPrice()));
        loyaltyPointsSpinner.setValue(product.getLoyaltyPointsNb());
        minQuantSpinner.setValue(product.getMinQuantity());
        promotionQuantSpinner.setValue(product.getPromotionMinQuantity());
        timeBeforeRemovingSpinner.setValue(product.getTimeBeforeRemoving());
        isEdibleCheckBox.setSelected(product.getEdible());
        saleDateSpinner.setValue(Date.from(product.getSaleDate().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        vatComboBox.setSelectedItem(product.getVatPercentage());
        for (int i = 0; i < categoryComboBox.getItemCount(); i++) {
            Object item = categoryComboBox.getItemAt(i);
            if (item instanceof ProductCategory p && p.getName().equals(product.getCategoryName())) {
                categoryComboBox.setSelectedItem(item);
                return;
            }
        }

    }
}