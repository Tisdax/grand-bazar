package userInterface;

import java.awt.*;

public class Vegetable {
    private Rectangle rectangle;
    private Color color;
    private GroceryBag groceryBag;
    public static final int STEP_X = 1;

    public Vegetable(int rectX, int rectY, int rectWidth, int rectHeight, Color color, GroceryBag groceryBag) {
        this.rectangle = new Rectangle(rectX, rectY, rectWidth, rectHeight);
        this.color = color;
        this.groceryBag = groceryBag;
    }

    public void draw(Graphics g) {
        if (rectangle.x <= groceryBag.getRectangle().x)
            g.clearRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
        g.setColor(this.color);
        g.fillOval(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
    }

    public void move() {
        rectangle.x += STEP_X;
    }
}