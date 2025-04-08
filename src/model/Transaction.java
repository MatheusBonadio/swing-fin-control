package src.model;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

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
        if (amount < 0) {
            throw new IllegalArgumentException("O valor da transação não pode ser negativo");
        }

        Objects.requireNonNull(category, "Categoria não pode ser nula");
        Objects.requireNonNull(date, "Data não pode ser nula");
        Objects.requireNonNull(description, "Descrição não pode ser nula");
        Objects.requireNonNull(type, "Tipo da transação não pode ser nulo");

        if (category.isBlank()) {
            throw new IllegalArgumentException("Categoria não pode estar em branco");
        }

        if (description.isBlank()) {
            throw new IllegalArgumentException("Descrição não pode estar em branco");
        }

        if (date.isAfter(LocalDate.now()))
            throw new IllegalArgumentException("A data não pode ser futura.");

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

    public static double calculateTotal(List<Transaction> transactions) {
        return transactions.stream()
                .mapToDouble(Transaction::getAmount)
                .sum();
    }

    public static double calculateIncome(List<Transaction> transactions) {
        return transactions.stream()
                .filter(transaction -> transaction.getType() == Type.INCOME)
                .mapToDouble(Transaction::getAmount)
                .sum();
    }

    public static double calculateExpense(List<Transaction> transactions) {
        return transactions.stream()
                .filter(transaction -> transaction.getType() == Type.EXPENSE)
                .mapToDouble(Transaction::getAmount)
                .sum();
    }
}