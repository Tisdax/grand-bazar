package businessLogic;


import model.CommandLine;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

public class InvoiceManagerTest {
    private InvoiceManager invoiceManager;
    private ArrayList<CommandLine> commandLines;

    @BeforeEach
    public void setUp(){
        invoiceManager = new InvoiceManager();
        commandLines = new ArrayList<>();
    }



}

