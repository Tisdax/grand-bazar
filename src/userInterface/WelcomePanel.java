package userInterface;

import javax.swing.*;
import java.awt.*;

public class WelcomePanel extends JPanel {
    private GroceryBag groceryBag;

    public WelcomePanel() {
        this.groceryBag = new GroceryBag();
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        groceryBag.draw(g);
    }
}
