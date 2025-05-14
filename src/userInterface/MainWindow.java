package userInterface;

import exceptions.DBAccesException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainWindow extends JFrame {
    private JMenuBar menuBar;
    private WelcomePanel welcomePanel;
    private ProductForm productForm;

    public MainWindow() throws DBAccesException {
        super("Accueil");
        setBounds(0,0,1280,720);
        setLocationRelativeTo(null);

        addWindowListener (new WindowAdapter() {
            public void windowClosing (WindowEvent e) {

                System.exit(0);
            }
        } );

        setJMenuBar(MenuBar.CreateJMenuBar(this));

        welcomePanel = new WelcomePanel();
        add(welcomePanel);

        setVisible(true);
    }

    public static void main(String[] args) throws DBAccesException {
        MainWindow mainWindow = new MainWindow();
    }
}
