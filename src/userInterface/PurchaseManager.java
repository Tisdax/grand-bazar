package userInterface;

import controller.ApplicationController;
import userInterface.TableConstructs.PurchaseTable;
import userInterface.TableConstructs.TableConstruct;

import javax.swing.*;
import java.awt.*;
import java.util.Calendar;
import java.util.Date;


public class PurchaseManager extends JPanel {
    private JLabel titleLabel;;
    private PurchaseTable purchaseTable;
    private JSpinner startDate;
    private JSpinner endDate;
    private JLabel premieresVente;
    private JLabel dernieresVentes;

    public PurchaseManager() {
        ApplicationController controller = new ApplicationController();
        setLayout(new BorderLayout(0, 50));
        setBorder(BorderFactory.createEmptyBorder(0, 100, 0, 100));
        JPanel datePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        Date today = new Date();

        // Date début
        premieresVente = new JLabel("Date début :");
        startDate = new JSpinner(new SpinnerDateModel(today, null, today, Calendar.MONTH));
        startDate.setEditor(new JSpinner.DateEditor(startDate, "dd/MM/yyyy"));
        datePanel.add(premieresVente);
        datePanel.add(startDate);

        // Date fin
        dernieresVentes = new JLabel("Date fin :");
        endDate = new JSpinner(new SpinnerDateModel(today, null, today, Calendar.MONTH));
        endDate.setEditor(new JSpinner.DateEditor(endDate, "dd/MM/yyyy"));
        datePanel.add(dernieresVentes);
        datePanel.add(endDate);

        purchaseTable = new PurchaseTable();
        // purchaseTable.fill

        titleLabel = new JLabel("Infos sur les achats effectués dans un laps de temps :");
        titleLabel.setFont(new Font("Dialog", Font.PLAIN, 30));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);

        this.add(titleLabel, BorderLayout.NORTH);
        this.add(new JScrollPane(purchaseTable.getTable()), BorderLayout.CENTER);
        this.add(datePanel, BorderLayout.CENTER);
        // this.add(dernieresVentes);
        // this.add(endDate);
    }
}
