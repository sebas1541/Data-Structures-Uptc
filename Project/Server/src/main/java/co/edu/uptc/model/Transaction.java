package co.edu.uptc.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class Transaction implements Comparable<Transaction>, Serializable {
    private String transactionId;
    private String userId; // Add userId here
    private double amount;
    private LocalDateTime dateTime;
    private String category;
    private String description;
    private String type; // "income" or "expense"

    // Full constructor
    public Transaction(String transactionId, String userId, double amount, LocalDateTime dateTime, String category, String description, String type) {
        this.transactionId = transactionId;
        this.userId = userId; // Set userId here
        this.amount = amount;
        this.dateTime = (dateTime == null) ? LocalDateTime.now() : dateTime;
        this.category = category;
        this.description = description;
        this.type = type;
    }

    // Constructor for search purposes
    public Transaction(String transactionId) {
        this.transactionId = transactionId;
    }

    // Getters and Setters
    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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
        this.dateTime = (dateTime == null) ? LocalDateTime.now() : dateTime;
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
        return this.dateTime.compareTo(other.dateTime);
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId='" + transactionId + '\'' +
                ", userId='" + userId + '\'' +
                ", amount=" + amount +
                ", dateTime=" + dateTime +
                ", category='" + category + '\'' +
                ", description='" + description + '\'' +
                ", type='" + type + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Objects.equals(transactionId, that.transactionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(transactionId);
    }
}
