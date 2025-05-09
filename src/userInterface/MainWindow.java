package userInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainWindow extends JFrame {
    private JMenuBar menuBar;
    private JPanel mainPanel;
    private CardLayout cardLayout;

    public MainWindow(){
        super("Accueil");
        setBounds(0,0,1280,720);
        setLocationRelativeTo(null);
        addWindowListener (new WindowAdapter() {
            public void windowClosing (WindowEvent e) {
                System.exit(0);
            }
        } );

        // Menu
        this.setJMenuBar(MenuBar.CreateJMenuBar(this));

        // Layout
        this.cardLayout = new CardLayout();
        this.mainPanel = new JPanel(cardLayout);
        this.add(mainPanel);

        //
        mainPanel.add(new ProductManagement(), "product");
        mainPanel.add(new WelcomePanel(), "welcome");
        mainPanel.add(new SaleManagement(), "sale");
        cardLayout.show(mainPanel, "welcome");

        setVisible(true);
    }
public void changePanel(String panelName){
        cardLayout.show(mainPanel, panelName);
}
    public static void main(String[] args) {
        MainWindow mainWindow = new MainWindow();
    }
}
