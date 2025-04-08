package src.screen;

import src.model.Transaction;
import src.model.User;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class TransactionHistoryPanel extends JPanel {
    private User user;
    private JTable transactionTable;
    private DefaultTableModel tableModel;
    private JTextField startDateField;
    private JTextField endDateField;
    private JComboBox<String> typeComboBox;
    private JComboBox<String> categoryComboBox;
    private JButton filterButton;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private JLabel labelTotalIncome;
    private JLabel labelTotalExpense;
    private JLabel labelBalance;

    private static TransactionHistoryPanel currentInstance;

    public TransactionHistoryPanel(User user) {
        this.user = user;
        currentInstance = this;
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel filterPanel = new JPanel(new GridLayout(2, 4, 10, 10));
        filterPanel.add(new JLabel("Data Início:"));
        startDateField = new JTextField();
        filterPanel.add(startDateField);
        filterPanel.add(new JLabel("Tipo:"));
        typeComboBox = new JComboBox<>(new String[]{"Todas", "Receita", "Despesa"});
        filterPanel.add(typeComboBox);

        filterPanel.add(new JLabel("Data Fim:"));
        endDateField = new JTextField();
        filterPanel.add(endDateField);
        filterPanel.add(new JLabel("Categoria:"));
        categoryComboBox = new JComboBox<>();
        updateCategoryFilter();
        filterPanel.add(categoryComboBox);

        filterButton = new JButton("Filtrar");
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(filterButton);

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(filterPanel, BorderLayout.CENTER);
        topPanel.add(buttonPanel, BorderLayout.SOUTH);
        add(topPanel, BorderLayout.NORTH);

        tableModel = new DefaultTableModel(new String[]{"Valor", "Categoria", "Data", "Descrição", "Tipo"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        transactionTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(transactionTable);
        add(scrollPane, BorderLayout.CENTER);

        JPanel summaryPanel = new JPanel(new GridLayout(1, 3, 10, 10));
        labelTotalIncome = new JLabel("Total Receitas: R$ 0.00", SwingConstants.CENTER);
        labelTotalExpense = new JLabel("Total Despesas: R$ 0.00", SwingConstants.CENTER);
        labelBalance = new JLabel("Saldo: R$ 0.00", SwingConstants.CENTER);
        summaryPanel.add(labelTotalIncome);
        summaryPanel.add(labelTotalExpense);
        summaryPanel.add(labelBalance);
        add(summaryPanel, BorderLayout.SOUTH);

        filterButton.addActionListener(e -> applyFilters());

        updateTable(user.getTransactions());
    }

    public void updateCategoryFilter() {
        categoryComboBox.removeAllItems();
        categoryComboBox.addItem("Todas");
        for (var cat : user.getCategories()) {
            categoryComboBox.addItem(cat.getName());
        }
    }

    public static void refreshCurrentCategoryFilter() {
        if (currentInstance != null) {
            currentInstance.updateCategoryFilter();
        }
    }

    public void applyFilters() {
        List<Transaction> filtered = user.getTransactions();
        String startDateText = startDateField.getText().trim();
        if (!startDateText.isEmpty()) {
            try {
                LocalDate startDate = LocalDate.parse(startDateText, formatter);
                filtered = filtered.stream().filter(t -> !t.getDate().isBefore(startDate)).collect(Collectors.toList());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Data de início inválida. Use dd/MM/yyyy.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        String endDateText = endDateField.getText().trim();
        if (!endDateText.isEmpty()) {
            try {
                LocalDate endDate = LocalDate.parse(endDateText, formatter);
                filtered = filtered.stream().filter(t -> !t.getDate().isAfter(endDate)).collect(Collectors.toList());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Data fim inválida. Use dd/MM/yyyy.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        String selectedType = (String) typeComboBox.getSelectedItem();
        if (!"Todas".equals(selectedType)) {
            filtered = filtered.stream().filter(t ->
                    (selectedType.equals("Receita") && t.getType() == Transaction.Type.INCOME) ||
                            (selectedType.equals("Despesa") && t.getType() == Transaction.Type.EXPENSE)
            ).collect(Collectors.toList());
        }
        String selectedCategory = (String) categoryComboBox.getSelectedItem();
        if (!"Todas".equals(selectedCategory)) {
            filtered = filtered.stream().filter(t -> t.getCategory().equals(selectedCategory)).collect(Collectors.toList());
        }
        updateTable(filtered);
    }

    private void updateTable(List<Transaction> transactions) {
        tableModel.setRowCount(0);
        double totalIncome = 0.0;
        double totalExpense = 0.0;
        for (Transaction t : transactions) {
            tableModel.addRow(new Object[]{
                    String.format("%.2f", t.getAmount()),
                    t.getCategory(),
                    t.getDate().format(formatter),
                    t.getDescription(),
                    (t.getType() == Transaction.Type.INCOME ? "Receita" : "Despesa")
            });
            if (t.getType() == Transaction.Type.INCOME) {
                totalIncome += t.getAmount();
            } else {
                totalExpense += t.getAmount();
            }
        }
        labelTotalIncome.setText("Total Receitas: R$ " + String.format("%.2f", totalIncome));
        labelTotalExpense.setText("Total Despesas: R$ " + String.format("%.2f", totalExpense));
        labelBalance.setText("Saldo: R$ " + String.format("%.2f", totalIncome - totalExpense));
    }
}
