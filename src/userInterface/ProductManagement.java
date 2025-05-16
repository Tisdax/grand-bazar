package userInterface;
import javax.swing.*;
import java.awt.*;

public class ProductManagement extends JPanel {
    private JLabel label;
    public ProductManagement() {
        label = new JLabel("Product Management");
        this.setLayout(new FlowLayout());
        this.add(label);
    }
}
