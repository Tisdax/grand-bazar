package userInterface;

import controller.ApplicationController;
import exceptions.DAOException;
import exceptions.InvalidValueException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.ParseException;

public class MainWindow extends JFrame {
    private static MainWindow instance;
    private JMenuBar menuBar;
    private WelcomePanel welcomePanel;
    private ProductForm productForm;
    private JPanel mainPanel;

    public MainWindow(){
        super("Accueil");
        instance = this;
        setBounds(0,0,1400,800);
        setLocationRelativeTo(null);

        addWindowListener (new WindowAdapter() {
            public void windowClosing (WindowEvent e) {
                try {
                    ProductForm productForm;
                    ApplicationController controller = new ApplicationController();
                    controller.closeConnection();
                    System.exit(0);
                } catch (DAOException ex) {
                    JOptionPane.showMessageDialog(null, ex.getDescription(), "Problème lors de la fermeture de la BD",JOptionPane.ERROR_MESSAGE);
                }
            }
        } );

        setJMenuBar(MenuBar.CreateJMenuBar());

        welcomePanel = new WelcomePanel();
        add(welcomePanel);

        setVisible(true);
    }
    public static MainWindow getInstance() {
        if (instance == null) {
            instance = new MainWindow();
        }
        return instance;
    }

    public static void main(String[] args) throws DAOException, ParseException, InvalidValueException {
        MainWindow.getInstance();
    }
}


