package src.screen;

import src.model.Category;
import src.model.User;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        User user = initializeUser();
        initializeCategories(user);

        JFrame frame = createMainFrame(user);
        frame.setVisible(true);
    }

    private static User initializeUser() {
        return new User("Carlos", "12345");
    }

    private static void initializeCategories(User user) {
        user.addCategory(new Category("Salário"));
        user.addCategory(new Category("Moradia"));
    }

    private static JFrame createMainFrame(User user) {
        JFrame frame = new JFrame("Controle Financeiro");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);

        SummaryPanel summaryPanel = new SummaryPanel(user);
        CreateTransaction createTransactionPanel = new CreateTransaction(summaryPanel, user);

        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, summaryPanel, createTransactionPanel);
        splitPane.setDividerLocation(150);
        frame.add(splitPane);

        return frame;
    }
}