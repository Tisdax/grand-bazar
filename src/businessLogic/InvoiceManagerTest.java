package businessLogic;


import exceptions.DAOException;
import exceptions.InvalidValueException;
import model.CommandLine;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

public class InvoiceManagerTest {
    private InvoiceManager invoiceManager;
    private ArrayList<CommandLine> commandLines;

    @BeforeEach
    public void setUp()  {
        invoiceManager = new InvoiceManager();
        commandLines = new ArrayList<>();
        try {
            // We will use the product P001 and P002 since they are already in our database.
            CommandLine commandLine1 = new CommandLine(1, "P001", 10);
            CommandLine commandLine2 = new CommandLine(1, "P002", 20);
            commandLines.add(commandLine1);
            commandLines.add(commandLine2);
        } catch (InvalidValueException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Test
    public void testNetPrice() {
        try {
            assertEquals(12.00, invoiceManager.netPrice(commandLines).get(0), 0.01);
            assertEquals(18.00, invoiceManager.netPrice(commandLines).get(1), 0.01);
        } catch (DAOException | InvalidValueException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Test
    public void TestTotalPriceVATInclvoid() {
        try {
            assertEquals(31.80, invoiceManager.totalPriceVATIncl(commandLines), 0.01);
        } catch (DAOException | InvalidValueException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Test
    public void testNetPriceWithEmptyList() {
        try {
            assertNull( invoiceManager.netPrice(new ArrayList<>()));
        } catch (DAOException | InvalidValueException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Test
    public void TestTotalPriceVATInclWithEmptyList() {
        try {
            assertEquals(0.00, invoiceManager.totalPriceVATIncl(new ArrayList<>()), 0.01);
        } catch (DAOException | InvalidValueException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
}

