package userInterface;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuBar {

    public static JMenuBar CreateJMenuBar(){
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

        manageMenu.addActionListener(e -> {
            new ProductManagement();
        });
        return menu;
    }
}