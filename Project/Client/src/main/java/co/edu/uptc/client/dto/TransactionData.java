package co.edu.uptc.client.dto;

public class TransactionData {
    private String userId;
    private String transactionId;
    private double amount;
    private String dateTime;
    private String category;
    private String description;
    private String type;

    public TransactionData(String userId, String transactionId, double amount, String dateTime, String category, String description, String type) {
        this.userId = userId;
        this.transactionId = transactionId;
        this.amount = amount;
        this.dateTime = dateTime;
        this.category = category;
        this.description = description;
        this.type = type;
    }

    public String getUserId() {
        return userId;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public double getAmount() {
        return amount;
    }

    public String getDateTime() {
        return dateTime;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "ID: " + transactionId + " | Amount: " + amount + " | " + category + " | " + description;
    }
}
