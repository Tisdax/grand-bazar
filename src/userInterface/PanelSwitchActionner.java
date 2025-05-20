package userInterface;

import javax.swing.*;
import java.awt.*;

public class PanelSwitchActionner {
    JFrame mainFrame;

    public PanelSwitchActionner(JFrame mainFrame) {
        this.mainFrame = mainFrame;
    }
    public void addPanelToFrameTest(JPanel panel) {
            mainFrame.getContentPane().removeAll();
            mainFrame.add(panel);
            mainFrame.revalidate();
            mainFrame.repaint();
    };

    public JButton createButton(JPanel panel, String buttonLabel) {
        JButton button = new JButton(buttonLabel);
        button.setPreferredSize(new Dimension(250, 60));
        button.addActionListener(e -> {
            addPanelToFrameTest(panel);
        });
        return button;
    }
    public JMenuItem createMenuItem(JPanel panel, String menuItemLabel) {
        JMenuItem menuItem = new JMenuItem(menuItemLabel);
        menuItem.addActionListener(e -> {
            addPanelToFrameTest(panel);
        });
        return menuItem;
    }
}
