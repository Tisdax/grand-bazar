package userInterface;

import controller.ApplicationController;
import userInterface.TableConstructs.LowStockTable;
import userInterface.TableConstructs.TableConstruct;

import javax.swing.*;
import java.awt.*;

public class LowStockProductPanel extends JPanel {
    private JLabel titleLabel;;
    private LowStockTable stockTable;

    public LowStockProductPanel() {
        ApplicationController controller = new ApplicationController();
        setLayout(new BorderLayout(0, 50));

        stockTable = new LowStockTable();

        titleLabel = new JLabel("Produits ayant un stock insuffisant");
        titleLabel.setFont(new Font("Dialog", Font.PLAIN, 30));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);

        this.add(titleLabel, BorderLayout.NORTH);
        this.add(new JScrollPane(stockTable.getTable()), BorderLayout.CENTER);
    }

}
