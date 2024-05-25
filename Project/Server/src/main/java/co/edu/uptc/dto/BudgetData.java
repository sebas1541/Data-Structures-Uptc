package co.edu.uptc.dto;

public class BudgetData {
    private String userId;
    private String budgetId; // New field for budget ID
    private String category;
    private double amount;

    // Updated constructor to include budgetId
    public BudgetData(String userId, String budgetId, String category, double amount) {
        this.userId = userId;
        this.budgetId = budgetId;
        this.category = category;
        this.amount = amount;
    }

    // Getters
    public String getUserId() {
        return userId;
    }

    public String getBudgetId() {
        return budgetId;
    }

    public String getCategory() {
        return category;
    }

    public double getAmount() {
        return amount;
    }

    // Setters
    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setBudgetId(String budgetId) {
        this.budgetId = budgetId;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
