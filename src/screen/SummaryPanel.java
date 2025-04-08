package src.screen;

import src.model.Transaction;
import src.model.User;

import javax.swing.*;
import java.awt.*;

public class SummaryPanel extends JPanel {

    private JLabel balanceLabel;
    private JLabel incomeLabel;
    private JLabel expenseLabel;
    private User user;

    public SummaryPanel(User user) {
        this.user = user;

        setLayout(new GridLayout(3, 1, 10, 10));

        balanceLabel = new JLabel();
        incomeLabel = new JLabel();
        expenseLabel = new JLabel();

        balanceLabel.setFont(new Font("Arial", Font.BOLD, 18));
        incomeLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        expenseLabel.setFont(new Font("Arial", Font.PLAIN, 16));

        add(balanceLabel);
        add(incomeLabel);
        add(expenseLabel);

        updateSummary();
    }

    public void updateSummary() {
        double totalIncome = Transaction.calculateIncome(user.getTransactions());
        double totalExpenses = Transaction.calculateExpense(user.getTransactions());
        double balance = totalIncome - totalExpenses;

        balanceLabel.setText("Saldo: R$ " + String.format("%.2f", balance));
        incomeLabel.setText("Total de Receitas: R$ " + String.format("%.2f", totalIncome));
        expenseLabel.setText("Total de Despesas: R$ " + String.format("%.2f", totalExpenses));
    }
}