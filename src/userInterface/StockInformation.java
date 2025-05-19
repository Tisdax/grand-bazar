package userInterface;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class StockInformation extends JPanel {
    private JLabel titleLabel;
    private JButton SearchByCategoryButton;
    private TableConstruct stockTable;

    public StockInformation() {
        titleLabel = new JLabel("Informations sur le stock");
        this.add(titleLabel, BorderLayout.NORTH);

        String[] columnsNames = {
                "Nom ¨produit", "Qt en stock", "Shelf Level", "Shelf ID", "Est réfrigérer"
        };

    }
}