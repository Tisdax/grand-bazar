package userInterface;

import javax.swing.*;
import java.awt.*;

public class SaleManagement extends JPanel {
    private JLabel label;
    public SaleManagement() {
        label = new JLabel("Sale Management");
        this.setLayout(new FlowLayout());
        this.add(label);
    }
}
