package src.model;
import java.time.LocalDate;
import java.util.List;

public class Transaction {
    public double value;
    public String category;
    public LocalDate date;
    public String description;
    public TransactionType type;

    public Transaction(double value, String category, LocalDate date, String description, TransactionType type) {
        this.value = value;
        this.category = category;
        this.date = date;
        this.description = description;
        this.type = type;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public static double calculateTotalByType(List<Transaction> transactions, TransactionType type) {
        double total = 0;
        for (Transaction t : transactions) {
            if (t.type == type) {
                total += t.value;
            }
        }
        return total;
    }
}