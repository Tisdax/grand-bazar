package userInterface;

import org.w3c.dom.css.Rect;

import javax.swing.*;
import java.awt.*;

public class GroceryBag {
    private Rectangle bag;

    public GroceryBag () {
        this.bag = new Rectangle(540, 210, 200, 300);
    }

    public void draw(Graphics g) {
        g.setColor(new Color(133, 96, 16));
        g.fillRect(bag.x, bag.y, bag.width, bag.height);
    }
}
