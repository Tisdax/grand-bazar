package userInterface;

import exceptions.DAOException;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.Callable;
import java.util.function.Supplier;

public class PanelSwitchActionner {
    private static PanelSwitchActionner instance;
    public void addPanelToFrameTest(JPanel panel) {
            MainWindow mainWindow = MainWindow.getInstance();
            mainWindow.getContentPane().removeAll();
            mainWindow.add(panel);
            mainWindow.revalidate();
            mainWindow.repaint();
    };

    public JButton createButton(String buttonLabel, Callable<JPanel> callablePanel) {
        JButton button = new JButton(buttonLabel);
        button.setPreferredSize(new Dimension(250, 60));
        button.addActionListener(e -> {
            try {
                JPanel panel = callablePanel.call();
                addPanelToFrameTest(panel);
            } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        });
        return button;
    }
    public JMenuItem createMenuItem(String menuItemLabel, Callable<JPanel> callablePanel) {
        JMenuItem menuItem = new JMenuItem(menuItemLabel);
        menuItem.addActionListener(e -> {
            try {
                JPanel panel = callablePanel.call();
                addPanelToFrameTest(panel);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        });
        return menuItem;
    }
    public static PanelSwitchActionner getInstance() {
        if(instance == null) {
            instance = new PanelSwitchActionner();
        }
        return instance;
    }
}