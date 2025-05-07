package userInterface;

import java.awt.*;

public class GroceryBag {
    private Rectangle rectangle;

    public GroceryBag () {
        this.rectangle = new Rectangle(540, 210, 200, 300);
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public void draw(Graphics g) {
        g.setColor(new Color(203, 145, 74, 255));
        g.fillRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
    }
}
