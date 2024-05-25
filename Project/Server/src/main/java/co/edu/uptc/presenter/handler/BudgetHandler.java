package co.edu.uptc.presenter.handler;

import co.edu.uptc.model.Budget;
import co.edu.uptc.model.Category;
import co.edu.uptc.model.User;
import co.edu.uptc.model.UserManager;
import co.edu.uptc.dto.BudgetData;
import co.edu.uptc.net.Response;
import com.google.gson.Gson;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class BudgetHandler {
    private final UserManager userManager;
    private final Gson gson;

    public BudgetHandler(UserManager userManager, Gson gson) {
        this.userManager = userManager;
        this.gson = gson;
    }

    public void addBudget(String data, DataOutputStream output) throws IOException {
        synchronized (userManager) {
            BudgetData budgetData = gson.fromJson(data, BudgetData.class);
            User user = userManager.getUserByUsername(budgetData.getUserId());
            if (user != null) {
                if (Category.isValidCategory(budgetData.getCategory())) {
                    String budgetId = "BUD-" + System.currentTimeMillis(); // Generate a unique budget ID
                    Category category = new Category(budgetId, budgetData.getCategory(), ""); // Add description if needed
                    Budget budget = new Budget(budgetId, user.getUserId(), category, budgetData.getAmount());
                    user.addBudget(budget);

                    String responseJson = gson.toJson(new Response("success", "Budget added successfully"));
                    output.writeUTF(responseJson);
                } else {
                    String responseJson = gson.toJson(new Response("error", "Invalid category"));
                    output.writeUTF(responseJson);
                }
            } else {
                String responseJson = gson.toJson(new Response("error", "User not found"));
                output.writeUTF(responseJson);
            }
        }
    }

    public void editBudget(String data, DataOutputStream output) throws IOException {
        synchronized (userManager) {
            BudgetData budgetData = gson.fromJson(data, BudgetData.class);
            User user = userManager.getUserByUsername(budgetData.getUserId());
            if (user != null) {
                if (Category.isValidCategory(budgetData.getCategory())) {
                    List<Budget> budgets = user.getBudgetHistory().toList();
                    Budget budgetToEdit = budgets.stream()
                            .filter(b -> budgetData.getBudgetId().equals(b.getBudgetId()))
                            .findFirst()
                            .orElse(null);
                    if (budgetToEdit != null) {
                        budgetToEdit.setAmount(budgetData.getAmount());
                        budgetToEdit.setCategory(new Category(budgetToEdit.getCategory().getCategoryId(), budgetData.getCategory(), ""));
                        String responseJson = gson.toJson(new Response("success", "Budget updated successfully"));
                        output.writeUTF(responseJson);
                    } else {
                        String responseJson = gson.toJson(new Response("error", "Budget not found"));
                        output.writeUTF(responseJson);
                    }
                } else {
                    String responseJson = gson.toJson(new Response("error", "Invalid category"));
                    output.writeUTF(responseJson);
                }
            } else {
                String responseJson = gson.toJson(new Response("error", "User not found"));
                output.writeUTF(responseJson);
            }
        }
    }

    public void deleteBudget(String data, DataOutputStream output) throws IOException {
        synchronized (userManager) {
            String[] parts = data.split(",");
            String userId = parts[0];
            String budgetId = parts[1];
            User user = userManager.getUserByUsername(userId);
            if (user != null) {
                List<Budget> budgets = user.getBudgetHistory().toList();
                Budget budgetToDelete = budgets.stream()
                        .filter(b -> budgetId.equals(b.getBudgetId()))
                        .findFirst()
                        .orElse(null);
                if (budgetToDelete != null) {
                    user.getBudgetHistory().remove(budgetToDelete);
                    String responseJson = gson.toJson(new Response("success", "Budget deleted successfully"));
                    output.writeUTF(responseJson);
                } else {
                    String responseJson = gson.toJson(new Response("error", "Budget not found"));
                    output.writeUTF(responseJson);
                }
            } else {
                String responseJson = gson.toJson(new Response("error", "User not found"));
                output.writeUTF(responseJson);
            }
        }
    }


    public void viewBudget(String data, DataOutputStream output) throws IOException {
        synchronized (userManager) {
            User user = userManager.getUserByUsername(data);
            if (user != null) {
                List<Budget> budgets = user.getBudgetHistory().toList();
                List<BudgetData> budgetDataList = budgets.stream()
                        .map(budget -> new BudgetData(user.getUserId(), budget.getBudgetId(), budget.getCategory().getName(), budget.getAmount()))
                        .collect(Collectors.toList());

                String responseJson = gson.toJson(new Response("success", gson.toJson(budgetDataList)));
                output.writeUTF(responseJson);
            } else {
                String responseJson = gson.toJson(new Response("error", "User not found"));
                output.writeUTF(responseJson);
            }
        }
    }
}
