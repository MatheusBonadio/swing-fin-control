package src.screen;

import src.model.Transaction;
import src.model.User;
import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class CreateTransaction extends JPanel {
    private TransactionHistoryPanel historyPanel;
    private User user;
    private JComboBox<String> typeComboBox;
    private JComboBox<String> categoryComboBox;
    private JTextField amountField;
    private JTextField dateField;
    private JTextField descriptionField;
    private JButton saveButton;

    public CreateTransaction(TransactionHistoryPanel historyPanel, User user) {
        this.historyPanel = historyPanel;
        this.user = user;
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel titleLabel = new JLabel("Criar Transação", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(titleLabel, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 10));

        typeComboBox = new JComboBox<>(new String[]{"Receita", "Despesa"});
        categoryComboBox = new JComboBox<>();
        updateCategories();
        typeComboBox.addActionListener(e -> updateCategories());

        amountField = new JTextField();
        dateField = new JTextField(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
        descriptionField = new JTextField();

        formPanel.add(new JLabel("Tipo:"));
        formPanel.add(typeComboBox);
        formPanel.add(new JLabel("Categoria:"));
        formPanel.add(categoryComboBox);
        formPanel.add(new JLabel("Valor:"));
        formPanel.add(amountField);
        formPanel.add(new JLabel("Data:"));
        formPanel.add(dateField);
        formPanel.add(new JLabel("Descrição:"));
        formPanel.add(descriptionField);

        add(formPanel, BorderLayout.CENTER);

        saveButton = new JButton("Salvar Transação");
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(saveButton);
        add(buttonPanel, BorderLayout.SOUTH);

        saveButton.addActionListener(e -> saveTransaction());
    }

    private void updateCategories() {
        categoryComboBox.removeAllItems();
        String selectedType = (String) typeComboBox.getSelectedItem();
        for (var category : user.getCategories()) {
            if (category.getType().equalsIgnoreCase(selectedType)) {
                categoryComboBox.addItem(category.getName());
            }
        }
    }

    private void saveTransaction() {
        String typeText = (String) typeComboBox.getSelectedItem();
        String category = (String) categoryComboBox.getSelectedItem();
        String dateText = dateField.getText().trim();
        String description = descriptionField.getText().trim();
        try {
            double amount = Transaction.parseAmount(amountField.getText().trim());
            LocalDate date = LocalDate.parse(dateText, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            Transaction.Type type = typeText.equals("Receita") ? Transaction.Type.INCOME : Transaction.Type.EXPENSE;
            Transaction newTransaction = new Transaction(amount, category, date, description, type);
            user.addTransaction(newTransaction);
            JOptionPane.showMessageDialog(this, "Transação salva com sucesso!");
            historyPanel.applyFilters();
            amountField.setText("");
            descriptionField.setText("");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
