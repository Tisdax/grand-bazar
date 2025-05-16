package userInterface;

import javax.swing.*;
import java.awt.*;

public class CartManager extends JPanel {
    private JLabel titleLabel;
    public CartManager() {
        titleLabel = new JLabel("Gestion de la carte");
        this.add(titleLabel, BorderLayout.NORTH);
    }
}
