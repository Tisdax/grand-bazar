package userInterface;

import controller.ApplicationController;

import javax.swing.*;
import java.awt.*;

public class LowStockProductPanel extends JPanel {
    private JLabel titleLabel;;
    private TableConstruct stockTable;
    private JComboBox<String> categoryComboBox;

    public LowStockProductPanel() {
        ApplicationController controller = new ApplicationController();
        setLayout(new BorderLayout(0, 50));

        String[] columnNames = {"ID", "Nom", "Qt Stock / Qt min", "%", "Nb manquants"};
        stockTable = new TableConstruct(columnNames);
        stockTable.fillLowProductTable();

        titleLabel = new JLabel("Produits ayant un stock insuffisant");
        titleLabel.setFont(new Font("Dialog", Font.PLAIN, 30));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);

        this.add(titleLabel, BorderLayout.NORTH);
        this.add(new JScrollPane(stockTable.getTable()), BorderLayout.CENTER);
    }

}
