package userInterface;

import controller.ApplicationController;
import userInterface.TableConstructs.TableConstruct;

import javax.swing.*;
import java.awt.*;

public class PurchaseManager extends JPanel {
    private JLabel titleLabel;;
    private TableConstruct purchaseTable;

    public PurchaseManager() {
        ApplicationController controller = new ApplicationController();
        setLayout(new BorderLayout(0, 50));

        String[] columnNames = {"ID Produit", "Nom", "Prix HTVA", "Qt", "ID Vente", "Date de Vente"};
        purchaseTable = new TableConstruct(columnNames);
        // purchaseTable.fill

        titleLabel = new JLabel("Infos sur les achats effectués dans ce laps de temps :");
        titleLabel.setFont(new Font("Dialog", Font.PLAIN, 30));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);

        this.add(titleLabel, BorderLayout.NORTH);
        this.add(new JScrollPane(purchaseTable.getTable()), BorderLayout.CENTER);
    }
}
