package co.edu.uptc.model;

import java.io.Serializable;

public class Budget implements Serializable {
    private String budgetId;
    private String userId;
    private Category category;
    private double amount;

    public Budget(String budgetId, String userId, Category category, double amount) {
        this.budgetId = budgetId;
        this.userId = userId;
        this.category = category;
        this.amount = amount;
    }

    public String getBudgetId() {
        return budgetId;
    }

    public void setBudgetId(String budgetId) {
        this.budgetId = budgetId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Budget{" +
                "budgetId='" + budgetId + '\'' +
                ", userId='" + userId + '\'' +
                ", category=" + category +
                ", amount=" + amount +
                '}';
    }
}
