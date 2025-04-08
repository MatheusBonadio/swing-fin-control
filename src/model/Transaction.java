package src.model;

import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.util.List;

public class Transaction {
    public enum Type {
        INCOME,
        EXPENSE
    }

    private double amount;
    private String category;
    private LocalDate date;
    private String description;
    private Type type;

    public Transaction(double amount, String category, LocalDate date, String description, Type type) {
        this.amount = amount;
        this.category = category;
        this.date = date;
        this.description = description;
        this.type = type;
        validate();
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = parseAmount(String.valueOf(amount));
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
        validate();
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
        validate();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
        validate();
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
        validate();
    }

    private void validate() {
        if (category == null || category.isEmpty())
            throw new IllegalArgumentException("A categoria não pode ser vazia.");

        if (date == null)
            throw new IllegalArgumentException("A data não pode ser nula.");

        if (date.isAfter(LocalDate.now()))
            throw new IllegalArgumentException("A data não pode ser futura.");

        if (description == null || description.isEmpty())
            throw new IllegalArgumentException("A descrição não pode ser vazia.");
    }

    public static double parseAmount(String amountText) {
        if (amountText == null || amountText.trim().isEmpty()) {
            throw new IllegalArgumentException("O campo valor não pode estar vazio.");
        }

        try {
            double amount = Double.parseDouble(amountText.trim());
            if (amount <= 0) {
                throw new IllegalArgumentException("O campo valor deve ser maior que 0.");
            }
            return amount;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("O campo valor deve conter um número válido.");
        }
    }

    public static double calculateTotal(@NotNull List<Transaction> transactions) {
        return transactions.stream()
                .mapToDouble(Transaction::getAmount)
                .sum();
    }

    public static double calculateIncome(@NotNull List<Transaction> transactions) {
        return transactions.stream()
                .filter(transaction -> transaction.getType() == Type.INCOME)
                .mapToDouble(Transaction::getAmount)
                .sum();
    }

    public static double calculateExpense(@NotNull List<Transaction> transactions) {
        return transactions.stream()
                .filter(transaction -> transaction.getType() == Type.EXPENSE)
                .mapToDouble(Transaction::getAmount)
                .sum();
    }
}