package co.edu.uptc.client.view;

import co.edu.uptc.client.net.BudgetData;
import co.edu.uptc.client.net.Request;
import co.edu.uptc.client.net.Response;
import co.edu.uptc.client.presenter.ClientPresenter;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class BudgetView {
    private ClientPresenter presenter;
    private Gson gson;

    public BudgetView(ClientPresenter presenter) {
        this.presenter = presenter;
        this.gson = new Gson();
    }

    public void display() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Budget Menu:\n1. Add Budget\n2. View Budgets\n3. Edit Budget\n4. Delete Budget\n5. Back");
        String option = scanner.nextLine();

        try {
            switch (option) {
                case "1":
                    addBudget();
                    break;
                case "2":
                    viewBudgets();
                    break;
                case "3":
                    listBudgetsForEdit();
                    break;
                case "4":
                    listBudgetsForDelete();
                    break;
                case "5":
                    presenter.getLoginView().display();
                    break;
                default:
                    showMessage("Invalid option. Try again.");
                    display();
                    break;
            }
        } catch (IOException e) {
            showMessage("Error: " + e.getMessage());
        }
    }

    private void addBudget() throws IOException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Select a category:");
        System.out.println("1. Alimentación");
        System.out.println("2. Transporte");
        System.out.println("3. Vivienda");
        System.out.println("4. Salud");
        System.out.println("5. Entretenimiento");
        System.out.println("6. Ropa y Calzado");
        System.out.println("7. Otros Gastos");
        String category = "";
        switch (scanner.nextLine()) {
            case "1":
                category = "Alimentación";
                break;
            case "2":
                category = "Transporte";
                break;
            case "3":
                category = "Vivienda";
                break;
            case "4":
                category = "Salud";
                break;
            case "5":
                category = "Entretenimiento";
                break;
            case "6":
                category = "Ropa y Calzado";
                break;
            case "7":
                category = "Otros Gastos";
                break;
            default:
                showMessage("Invalid category. Try again.");
                display();
                return;
        }

        System.out.print("Enter amount: ");
        double amount = Double.parseDouble(scanner.nextLine());

        BudgetData budgetData = new BudgetData(presenter.getLoginView().getCurrentUser().getUsername(), null, category, amount);
        String budgetDataJson = gson.toJson(budgetData);
        Request request = new Request("addBudget", budgetDataJson);
        presenter.getConnection().sendRequest(request);
        Response response = presenter.getConnection().receiveResponse();
        showMessage(response.getData());
        display();
    }

    private void viewBudgets() throws IOException {
        Request request = new Request("viewBudget", presenter.getLoginView().getCurrentUser().getUsername());
        presenter.getConnection().sendRequest(request);
        Response response = presenter.getConnection().receiveResponse();

        if ("success".equals(response.getStatus())) {
            if (response.getData() == null || response.getData().isEmpty()) {
                showMessage("No budgets found.");
            } else {
                BudgetData[] budgets = gson.fromJson(response.getData(), BudgetData[].class);
                if (budgets.length == 0) {
                    showMessage("No budgets found.");
                } else {
                    int counter = 1;
                    for (BudgetData budget : budgets) {
                        System.out.println(counter + ": " + budget);
                        counter++;
                    }
                }
            }
        } else {
            showMessage("Error: " + response.getData());
        }
        display();
    }

    private void listBudgetsForEdit() throws IOException {
        Request request = new Request("viewBudget", presenter.getLoginView().getCurrentUser().getUsername());
        presenter.getConnection().sendRequest(request);
        Response response = presenter.getConnection().receiveResponse();

        if ("success".equals(response.getStatus())) {
            if (response.getData() == null || response.getData().isEmpty()) {
                showMessage("No budgets found.");
            } else {
                BudgetData[] budgets = gson.fromJson(response.getData(), BudgetData[].class);
                if (budgets.length == 0) {
                    showMessage("No budgets found.");
                } else {
                    int counter = 1;
                    for (BudgetData budget : budgets) {
                        System.out.println(counter + ": " + budget);
                        counter++;
                    }
                    editBudget(budgets);
                }
            }
        } else {
            showMessage("Error: " + response.getData());
        }
    }

    private void listBudgetsForDelete() throws IOException {
        Request request = new Request("viewBudget", presenter.getLoginView().getCurrentUser().getUsername());
        presenter.getConnection().sendRequest(request);
        Response response = presenter.getConnection().receiveResponse();

        if ("success".equals(response.getStatus())) {
            if (response.getData() == null || response.getData().isEmpty()) {
                showMessage("No budgets found.");
            } else {
                BudgetData[] budgets = gson.fromJson(response.getData(), BudgetData[].class);
                if (budgets.length == 0) {
                    showMessage("No budgets found.");
                } else {
                    int counter = 1;
                    for (BudgetData budget : budgets) {
                        System.out.println(counter + ": " + budget);
                        counter++;
                    }
                    deleteBudget(budgets);
                }
            }
        } else {
            showMessage("Error: " + response.getData());
        }
    }

    private void editBudget(BudgetData[] budgets) throws IOException {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter budget number to edit: ");
        int budgetNumber = Integer.parseInt(scanner.nextLine());

        if (budgetNumber < 1 || budgetNumber > budgets.length) {
            showMessage("Invalid budget number. Try again.");
            display();
            return;
        }

        BudgetData budgetToEdit = budgets[budgetNumber - 1];

        System.out.println("Select a new category:");
        System.out.println("1. Alimentación");
        System.out.println("2. Transporte");
        System.out.println("3. Vivienda");
        System.out.println("4. Salud");
        System.out.println("5. Entretenimiento");
        System.out.println("6. Ropa y Calzado");
        System.out.println("7. Otros Gastos");
        String category = "";
        switch (scanner.nextLine()) {
            case "1":
                category = "Alimentación";
                break;
            case "2":
                category = "Transporte";
                break;
            case "3":
                category = "Vivienda";
                break;
            case "4":
                category = "Salud";
                break;
            case "5":
                category = "Entretenimiento";
                break;
            case "6":
                category = "Ropa y Calzado";
                break;
            case "7":
                category = "Otros Gastos";
                break;
            default:
                showMessage("Invalid category. Try again.");
                display();
                return;
        }

        System.out.print("Enter new amount: ");
        double amount = Double.parseDouble(scanner.nextLine());

        BudgetData budgetData = new BudgetData(presenter.getLoginView().getCurrentUser().getUsername(), budgetToEdit.getBudgetId(), category, amount);
        String budgetDataJson = gson.toJson(budgetData);
        Request request = new Request("editBudget", budgetDataJson);
        presenter.getConnection().sendRequest(request);
        Response response = presenter.getConnection().receiveResponse();
        showMessage(response.getData());
        display();
    }


    private void deleteBudget(BudgetData[] budgets) throws IOException {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter budget number to delete: ");
        int budgetNumber = Integer.parseInt(scanner.nextLine());

        if (budgetNumber < 1 || budgetNumber > budgets.length) {
            showMessage("Invalid budget number. Try again.");
            display();
            return;
        }

        BudgetData budgetToDelete = budgets[budgetNumber - 1];

        String userId = presenter.getLoginView().getCurrentUser().getUsername();
        String requestData = userId + "," + budgetToDelete.getBudgetId();
        Request request = new Request("deleteBudget", requestData);
        presenter.getConnection().sendRequest(request);
        Response response = presenter.getConnection().receiveResponse();
        showMessage(response.getData());
        display();
    }

    public void showMessage(String message) {
        System.out.println(message);
    }
}
