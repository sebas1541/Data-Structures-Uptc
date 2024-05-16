package co.edu.uptc.model;

import java.time.LocalDate;

public class Transaction implements Comparable<Transaction> {
    private String transactionId;
    private double amount;
    private LocalDate date;
    private Category category;
    private String description;
    private String type; // "income" or "expense"

    public Transaction(String transactionId, double amount, LocalDate date, Category category, String description, String type) {
        this.transactionId = transactionId;
        this.amount = amount;
        this.date = date;
        this.category = category;
        this.description = description;
        this.type = type;
    }

    // Getters and Setters
    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public int compareTo(Transaction other) {
        return this.date.compareTo(other.date); // Sorting by date
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId='" + transactionId + '\'' +
                ", amount=" + amount +
                ", date=" + date +
                ", category=" + category +
                ", description='" + description + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
