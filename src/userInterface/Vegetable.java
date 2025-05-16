package userInterface;

import java.awt.*;

public class Vegetable {
    private Rectangle rectangle;
    private Color color;
    private GroceryBag groceryBag;
    public static final int STEP_Y = 1;
    public static final int STEP_X = 40;
    private int start_y;
    private int start_x;

    public Vegetable(int rectX, int rectY, int rectWidth, int rectHeight, Color color, GroceryBag groceryBag) {
        this.rectangle = new Rectangle(rectX, rectY, rectWidth, rectHeight);
        this.color = color;
        this.groceryBag = groceryBag;
        this.start_y = rectY;
        this.start_x = rectX;
    }

    public void draw(Graphics g) {
        if (rectangle.x <= groceryBag.getRectangle().x)
            g.clearRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
        g.setColor(this.color);
        g.fillOval(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
    }

    public void move() {
        if (rectangle.y >= groceryBag.getRectangle().y + rectangle.height) {
            rectangle.y = start_y;
            rectangle.x += STEP_X;
            if (rectangle.x >= 700)
                rectangle.x = start_x;
        }
        else {
            rectangle.y += STEP_Y;
        }
    }
}