package userInterface;

import javax.swing.*;
import java.awt.*;

public class StockInformation extends JPanel {
    private JLabel titleLabel;
    public StockInformation() {
        titleLabel = new JLabel("Informations sur le stock");
        this.add(titleLabel, BorderLayout.NORTH);
    }
}
