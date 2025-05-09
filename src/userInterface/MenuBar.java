package userInterface;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class MenuBar {

    public static JMenuBar CreateJMenuBar(MainWindow mainWindow){
        JMenuBar menu = new JMenuBar();
        JMenu mainMenu;
        JMenuItem manageMenu, saleMenu;

        mainMenu = new JMenu("Menu");
        menu.add(mainMenu);

        manageMenu = new JMenuItem("Gestion");
        saleMenu = new JMenuItem("Vente");

        mainMenu.add(manageMenu);
        mainMenu.addSeparator();
        mainMenu.add(saleMenu);

        // Listeners
        manageMenu.addActionListener((ActionEvent e) -> {
            mainWindow.changePanel("product");
        });
        saleMenu.addActionListener((ActionEvent e) -> {
            mainWindow.changePanel("sale");
        });

        return menu;
    }
}