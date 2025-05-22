package userInterface;

import controller.ApplicationController;
import exceptions.DAOException;
import exceptions.InvalidValueException;
import model.Product;
import model.ProductCategory;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ProductForm extends JPanel {
    private JPanel titlePanel, formPanel, buttonPanel;
    private JLabel titleLabel, idLabel, nameLabel, netPriceLabel, vatLabel, isEdibleLabel, loyaltyPointsLabel, minQuantLabel, promotionQuantLabel, saleDateLabel, timeBeforeRemovingLabel, categoryLabel, minQuantCBLabel, promoMinQuantCBLabel, timeBeforeRemovingCBLabel;
    private JTextField idField, nameField;
    private JComboBox<Integer> vatComboBox;
    private JComboBox<String> categoryComboBox;
    private JCheckBox isEdibleCheckBox, minquantCheckBox, promoMinQuantCheckBox, timeBeforeRemovingCheckBox;
    private JSpinner saleDateSpinner, loyaltyPointsSpinner, minQuantSpinner, promotionQuantSpinner, timeBeforeRemovingSpinner, netPriceSpinner;
    private JButton addButton, updateButton;
    private ApplicationController controller;
    public ProductForm() {
        controller = new ApplicationController();

        this.setLayout(new BorderLayout());
        titlePanel = new JPanel();
        formPanel = new JPanel(new GridLayout(14, 2, 5, 10));
        buttonPanel = new JPanel();
        this.add(titlePanel, BorderLayout.NORTH);
        this.add(formPanel, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);

        UIManager.put("Spinner.editorAlignment", JTextField.LEFT);


        // TITLE PANEL
        titleLabel = new JLabel("Formulaire de Produit");
        Font font = new Font("Arial", Font.BOLD, 20);
        titleLabel.setFont(font);
        titlePanel.add(titleLabel);


        // FORM PANEL
        idLabel = new JLabel("Identifiant :", SwingConstants.RIGHT);
        idField = new JTextField(15);
        formPanel.add(idLabel);
        formPanel.add(idField);

        nameLabel = new JLabel("Nom du produit :", SwingConstants.RIGHT);
        nameField = new JTextField(15);
        formPanel.add(nameLabel);
        formPanel.add(nameField);

        netPriceLabel = new JLabel("Prix (€) :", SwingConstants.RIGHT);
        netPriceSpinner = new JSpinner(new SpinnerNumberModel(0.0, 0.0, 9999.99, 0.01));
        JSpinner.NumberEditor netPriceEditor = new JSpinner.NumberEditor(netPriceSpinner, "0.00");
        JFormattedTextField textField = netPriceEditor.getTextField();
        textField.setHorizontalAlignment(SwingConstants.LEFT);
        netPriceSpinner.setEditor(netPriceEditor);
        formPanel.add(netPriceLabel);
        formPanel.add(netPriceSpinner);

        vatLabel = new JLabel("Taux de TVA :", SwingConstants.RIGHT);
        vatComboBox = new JComboBox<>(new Integer[]{6, 12, 21});
        formPanel.add(vatLabel);
        formPanel.add(vatComboBox);

        isEdibleLabel = new JLabel("Le produit est-il comestible :", SwingConstants.RIGHT);
        isEdibleCheckBox = new JCheckBox();
        formPanel.add(isEdibleLabel);
        formPanel.add(isEdibleCheckBox);

        loyaltyPointsLabel = new JLabel("Nombre de points de fidélité :", SwingConstants.RIGHT);
        loyaltyPointsSpinner = new JSpinner(new SpinnerNumberModel(0, 0, null, 10));
        formPanel.add(loyaltyPointsLabel);
        formPanel.add(loyaltyPointsSpinner);

        minQuantCBLabel = new JLabel("Définir une quantité minimale :", SwingConstants.RIGHT);
        minquantCheckBox = new JCheckBox();
        formPanel.add(minQuantCBLabel);
        formPanel.add(minquantCheckBox);
        minQuantLabel = new JLabel("Quantité minimale :", SwingConstants.RIGHT);
        minQuantSpinner = new JSpinner(new SpinnerNumberModel(0, 0, null, 10));
        minQuantSpinner.setEnabled(false);
        formPanel.add(minQuantLabel);
        formPanel.add(minQuantSpinner);

        promoMinQuantCBLabel = new JLabel("Définir une quantité minimale lors de promotion :", SwingConstants.RIGHT);
        promoMinQuantCheckBox = new JCheckBox();
        formPanel.add(promoMinQuantCBLabel);
        formPanel.add(promoMinQuantCheckBox);
        promotionQuantLabel = new JLabel("Quantité minimale en cas de promotion :", SwingConstants.RIGHT);
        promotionQuantSpinner = new JSpinner(new SpinnerNumberModel(0, 0, null, 10));
        promotionQuantSpinner.setEnabled(false);
        formPanel.add(promotionQuantLabel);
        formPanel.add(promotionQuantSpinner);

        timeBeforeRemovingCBLabel = new JLabel("Définir une durée avant la suppression des rayons :", SwingConstants.RIGHT);
        timeBeforeRemovingCheckBox = new JCheckBox();
        formPanel.add(timeBeforeRemovingCBLabel);
        formPanel.add(timeBeforeRemovingCheckBox);
        timeBeforeRemovingLabel = new JLabel("Temps avant suppression des rayons :", SwingConstants.RIGHT);
        timeBeforeRemovingSpinner = new JSpinner(new SpinnerNumberModel(0, 0, null, 10));
        timeBeforeRemovingSpinner.setEnabled(false);
        formPanel.add(timeBeforeRemovingLabel);
        formPanel.add(timeBeforeRemovingSpinner);

        categoryLabel = new JLabel("Catégorie du produit :", SwingConstants.RIGHT);
        categoryComboBox = new JComboBox<>();
        ArrayList<ProductCategory> categories = null;
        try {
            categories = controller.getAllCategory();
        } catch (DAOException e) {
            JOptionPane.showMessageDialog(null, "Erreur lors de la recherche de catégorie veuillez réessayer", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
        for (ProductCategory category : categories) {
            categoryComboBox.addItem(category.getName());
        }
        formPanel.add(categoryLabel);
        formPanel.add(categoryComboBox);

        saleDateLabel = new JLabel("Date de mise en vente :", SwingConstants.RIGHT);
        Date today = new Date();
        saleDateSpinner = new JSpinner(new SpinnerDateModel(today, today, null, Calendar.MONTH));
        saleDateSpinner.setEditor(new JSpinner.DateEditor(saleDateSpinner, "dd/MM/yyyy"));
        formPanel.add(saleDateLabel);
        formPanel.add(saleDateSpinner);


        // BUTTON PANEL
        addButton = new JButton("Ajouter Produit");
        buttonPanel.add(addButton);
        updateButton = new JButton("Modifier Produit");


        // EVENT LISTENER
        minquantCheckBox.addItemListener(e -> {
            minQuantSpinner.setEnabled(minquantCheckBox.isSelected());
        });

        promoMinQuantCheckBox.addItemListener(e -> {
            promotionQuantSpinner.setEnabled(promoMinQuantCheckBox.isSelected());
        });

        timeBeforeRemovingCheckBox.addItemListener(e -> {
            timeBeforeRemovingSpinner.setEnabled(timeBeforeRemovingCheckBox.isSelected());
        });

        addButton.addActionListener(e -> {
            try {
                controller.addProduct(tansformProduct(idField, nameField, netPriceSpinner, vatComboBox,loyaltyPointsSpinner, minQuantSpinner, promotionQuantSpinner, timeBeforeRemovingSpinner, isEdibleCheckBox, saleDateSpinner, categoryComboBox, minquantCheckBox, promoMinQuantCheckBox, timeBeforeRemovingCheckBox));
                JOptionPane.showMessageDialog(null, "Produit ajouté", "Réussite", JOptionPane.INFORMATION_MESSAGE);
                emptyForm(idField, nameField, netPriceSpinner, vatComboBox,loyaltyPointsSpinner, minQuantSpinner, promotionQuantSpinner, timeBeforeRemovingSpinner, isEdibleCheckBox, saleDateSpinner, categoryComboBox, minquantCheckBox, promoMinQuantCheckBox, timeBeforeRemovingCheckBox);
            } catch (DAOException | InvalidValueException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Problèmes lors de l'ajout", JOptionPane.ERROR_MESSAGE);
            }
        });

        updateButton.addActionListener(e -> {
            try {
                controller.updateProduct(tansformProduct(idField, nameField, netPriceSpinner, vatComboBox,loyaltyPointsSpinner, minQuantSpinner, promotionQuantSpinner, timeBeforeRemovingSpinner, isEdibleCheckBox, saleDateSpinner, categoryComboBox, minquantCheckBox, promoMinQuantCheckBox, timeBeforeRemovingCheckBox));
                JOptionPane.showMessageDialog(null, "Produit modifié", "Réussite", JOptionPane.INFORMATION_MESSAGE);
                emptyForm(idField, nameField, netPriceSpinner, vatComboBox,loyaltyPointsSpinner, minQuantSpinner, promotionQuantSpinner, timeBeforeRemovingSpinner, isEdibleCheckBox, saleDateSpinner, categoryComboBox, minquantCheckBox, promoMinQuantCheckBox, timeBeforeRemovingCheckBox);
            } catch (DAOException | InvalidValueException ex){
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Problèmes lors de la mise à jour", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    private Product tansformProduct(JTextField idField, JTextField nameField, JSpinner netPriceSpinner, JComboBox<Integer> vatComboBox, JSpinner loyaltyPointsSpinner, JSpinner minQuantSpinner, JSpinner promotionQuantSpinner, JSpinner timeBeforeRemovingSpinner, JCheckBox isEdibleCheckBox, JSpinner saleDateSpinner, JComboBox<String> categoryComboBox, JCheckBox minquantCheckBox, JCheckBox promoMinQuantCheckBox, JCheckBox timeBeforeRemovingCheckBox) throws DAOException, InvalidValueException {
        String id = idField.getText();
        String name = nameField.getText();
        Integer vat = (Integer) vatComboBox.getSelectedItem();
        Double netPrice = ((Number) netPriceSpinner.getValue()).doubleValue();
        Integer loyaltyPoints = (Integer) loyaltyPointsSpinner.getValue();
        Boolean isEdible = isEdibleCheckBox.isSelected();
        LocalDate saleDate = ((Date) saleDateSpinner.getValue()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        String category = (String) categoryComboBox.getSelectedItem();

        if (controller.exists(id)){
            JOptionPane.showMessageDialog(null, "L'identifiant " + id + " est déjà utilisé. Veuillez en choisir un autre", "Erreur", JOptionPane.ERROR_MESSAGE);
        }

        if (saleDate == null || saleDate.isBefore(LocalDate.now())){
            JOptionPane.showMessageDialog(null, "Veuillez entrez une adresse supérieur à aujourd'hui", "Erreur",JOptionPane.ERROR_MESSAGE);
        }

        Integer minQuant;
        if (minquantCheckBox.isSelected()) {
            minQuant = (Integer) minQuantSpinner.getValue();
        }
        else {
            minQuant = null;
        }

        Integer promotionMinQuant;
        if (promoMinQuantCheckBox.isSelected()) {
            promotionMinQuant = (Integer) promotionQuantSpinner.getValue();
        }
        else {
            promotionMinQuant = null;
        }

        Integer timeBeforeRemoving;
        if (timeBeforeRemovingCheckBox.isSelected()) {
            timeBeforeRemoving = (Integer) timeBeforeRemovingSpinner.getValue();
        }
        else {
            timeBeforeRemoving = null;
        }

        return new Product(id, name, netPrice, vat, loyaltyPoints, minQuant, promotionMinQuant, timeBeforeRemoving, isEdible, saleDate, category);
    }

    public void fillProductForm(Product product){
        updateButton();
        idField.setText(product.getId());
        idField.setEnabled(false);
        nameField.setText(product.getName());
        netPriceSpinner.setValue(product.getNetPrice());
        loyaltyPointsSpinner.setValue(product.getLoyaltyPointsNb());
        minQuantSpinner.setValue(product.getMinQuantity());
        promotionQuantSpinner.setValue(product.getPromotionMinQuantity());
        timeBeforeRemovingSpinner.setValue(product.getTimeBeforeRemoving());
        isEdibleCheckBox.setSelected(product.getEdible());
        saleDateSpinner.setValue(Date.from(product.getSaleDate().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        vatComboBox.setSelectedItem(product.getVatPercentage());
        categoryComboBox.setSelectedItem(product.getCategoryName());
    }

    private void emptyForm(JTextField idField, JTextField nameField, JSpinner netPriceSpinner, JComboBox<Integer> vatComboBox, JSpinner loyaltyPointsSpinner, JSpinner minQuantSpinner, JSpinner promotionQuantSpinner, JSpinner timeBeforeRemovingSpinner, JCheckBox isEdibleCheckBox, JSpinner saleDateSpinner, JComboBox<String> categoryComboBox, JCheckBox minquantCheckBox, JCheckBox promoMinQuantCheckBox, JCheckBox timeBeforeRemovingCheckBox) throws DAOException {
        idField.setText("");
        nameField.setText("");
        netPriceSpinner.setValue(0);
        vatComboBox.setSelectedIndex(0);
        loyaltyPointsSpinner.setValue(0);
        minQuantSpinner.setValue(0);
        promotionQuantSpinner.setValue(0);
        timeBeforeRemovingSpinner.setValue(0);
        isEdibleCheckBox.setSelected(false);
        saleDateSpinner.setValue(new Date());
        categoryComboBox.setSelectedIndex(0);
        minquantCheckBox.setSelected(false);
        promoMinQuantCheckBox.setSelected(false);
        timeBeforeRemovingCheckBox.setSelected(false);
    }
    private void updateButton(){
        buttonPanel.removeAll();
        buttonPanel.add(updateButton);
        revalidate();
        repaint();
    }
}