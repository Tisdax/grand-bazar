package userInterface;

import javax.swing.*;
import java.awt.*;

public class ProductManager extends JPanel {
    private JLabel titleLabel;
    public ProductManager() {
        titleLabel = new JLabel("Gestion des produits");
        this.add(titleLabel, BorderLayout.NORTH);
    }
}
