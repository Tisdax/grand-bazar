package userInterface;

import javax.swing.*;

public class MainWindow extends JFrame {

    public MainWindow(){
        super("Accueil");
        setBounds(0,0,1280,720);

        setVisible(true);
    }

    public static void main(String[] args) {
        MainWindow mainWindow = new MainWindow();
    }
}
