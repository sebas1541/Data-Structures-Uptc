package co.edu.uptc.model;

import java.time.LocalDateTime;

public class Transaction implements Comparable<Transaction> {
    private String transactionId;
    private double amount;
    private LocalDateTime dateTime;
    private String category; // Changed to a string
    private String description;
    private String type; // "income" or "expense"

    public Transaction(String transactionId, double amount, LocalDateTime dateTime, String category, String description, String type) {
        this.transactionId = transactionId;
        this.amount = amount;
        this.dateTime = dateTime;
        this.category = category; // Changed to a string
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

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
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
        return this.dateTime.compareTo(other.dateTime); // Sorting by dateTime
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId='" + transactionId + '\'' +
                ", amount=" + amount +
                ", dateTime=" + dateTime +
                ", category='" + category + '\'' +
                ", description='" + description + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
