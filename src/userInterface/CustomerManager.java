package userInterface;

import javax.swing.*;
import java.awt.*;

public class CustomerManager extends JPanel {
    private JLabel titleLabel;
    public CustomerManager() {
        titleLabel = new JLabel("Gestion des clients");
        this.add(titleLabel, BorderLayout.NORTH);
    }
}