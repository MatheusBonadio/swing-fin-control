package src.model;

import java.util.ArrayList;
import java.util.List;

public class User {
    public String username;
    public String password;
    public List<Transaction> transactions = new ArrayList<>();
    public List<Category> categories = new ArrayList<>();

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    public void addCategory(Category category) {
        categories.add(category);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}
