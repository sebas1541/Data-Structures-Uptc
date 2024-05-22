package co.edu.uptc.client.net;

public class BudgetData {
    private String userId;
    private String budgetId; // this parameter seems to be expected but not provided in some calls
    private String category;
    private double amount;

    public BudgetData(String userId, String budgetId, String category, double amount) {
        this.userId = userId;
        this.budgetId = budgetId;
        this.category = category;
        this.amount = amount;
    }

    // Getters and setters
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getBudgetId() {
        return budgetId;
    }

    public void setBudgetId(String budgetId) {
        this.budgetId = budgetId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
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
        return "BudgetData{" +
                "userId='" + userId + '\'' +
                ", budgetId='" + budgetId + '\'' +
                ", category='" + category + '\'' +
                ", amount=" + amount +
                '}';
    }
}
