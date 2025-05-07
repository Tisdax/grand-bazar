package userInterface;

import javax.swing.*;
import java.awt.*;

public class WelcomePanel extends JPanel {
    private GroceryBag groceryBag;
    private Vegetable vegetable;

    public WelcomePanel() {
        this.groceryBag = new GroceryBag();
        this.vegetable = new Vegetable(550, 100, 60, 60, Color.RED, groceryBag);
        AnimationThread animationThread = new AnimationThread(this);
        animationThread.start();
    }

    public Vegetable getVegetable() {
        return vegetable;
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        vegetable.draw(g);
        groceryBag.draw(g);
    }
}
