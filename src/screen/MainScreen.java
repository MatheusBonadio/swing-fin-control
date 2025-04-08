package src.screen;

import src.model.User;
import javax.swing.*;
import java.awt.*;

public class MainScreen extends JFrame {
    public MainScreen(User user) {
        setTitle("Sistema de Gestão Financeira Pessoal");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 500);
        setLayout(new BorderLayout());

        SummaryPanel summaryPanel = new SummaryPanel(user);
        CreateTransaction createTransactionPanel = new CreateTransaction(summaryPanel, user);

        add(summaryPanel, BorderLayout.NORTH);
        add(createTransactionPanel, BorderLayout.CENTER);

        setLocationRelativeTo(null);
        setVisible(true);
    }
}
