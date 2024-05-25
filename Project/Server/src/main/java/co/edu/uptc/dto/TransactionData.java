package co.edu.uptc.dto;

public class TransactionData {
    private String userId;
    private String transactionId;
    private double amount;
    private String dateTime;
    private String category; // Changed to a string
    private String description;
    private String type;

    public TransactionData(String userId, String transactionId, double amount, String dateTime, String category, String description, String type) {
        this.userId = userId;
        this.transactionId = transactionId;
        this.amount = amount;
        this.dateTime = dateTime;
        this.category = category; // Changed to a string
        this.description = description;
        this.type = type;
    }

    // Getters and setters
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

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

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
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
}
