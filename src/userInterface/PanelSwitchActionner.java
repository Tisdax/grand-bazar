package userInterface;

import exceptions.DAOException;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.Callable;
import java.util.function.Supplier;

public class PanelSwitchActionner {
    public void addPanelToFrameTest(JPanel panel) {
            MainWindow mainWindow = MainWindow.getInstance();
            mainWindow.getContentPane().removeAll();
            mainWindow.add(panel);
            mainWindow.revalidate();
            mainWindow.repaint();
    };

//    public JButton createButton(JPanel panel, String buttonLabel) {
//        JButton button = new JButton(buttonLabel);
//        button.setPreferredSize(new Dimension(250, 60));
//        button.addActionListener(e -> {
//            addPanelToFrameTest(panel);
//        });
//        return button;
//    }
//    public JButton createProductFormButton(String buttonLabel) throws DAOException {
//        JButton button = new JButton(buttonLabel);
//        ProductForm form = new ProductForm();
//        button.setPreferredSize(new Dimension(250, 60));
//        button.addActionListener(e -> {
//            addPanelToFrameTest(form);
//        });
//        return button;
//    }
    public JButton createPanelSwitchButton(String buttonLabel, Callable<JPanel> callablePanel) {
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

    public JMenuItem createMenuItem(JPanel panel, String menuItemLabel) {
        JMenuItem menuItem = new JMenuItem(menuItemLabel);
        menuItem.addActionListener(e -> {
            addPanelToFrameTest(panel);
        });
        return menuItem;
    }
}