package userInterface;

import javax.swing.*;
import java.awt.*;

public class WelcomePanel extends JPanel {
    private GroceryBag groceryBag;
    private Vegetable vegetable;
    private JPanel titlePanel;
    private JLabel titleLabel;

    public WelcomePanel() {
        titlePanel = new JPanel();
        this.add(titlePanel, BorderLayout.NORTH);

        titleLabel = new JLabel("Bienvenue sur l'application du Grand Bazar");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        titlePanel.add(titleLabel);

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
