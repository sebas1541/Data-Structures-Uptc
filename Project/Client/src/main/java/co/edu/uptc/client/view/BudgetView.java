package co.edu.uptc.client.view;

import co.edu.uptc.client.net.Request;
import co.edu.uptc.client.presenter.ClientPresenter;


import java.io.IOException;
import java.util.Scanner;

public class BudgetView {
    private ClientPresenter presenter;

    public BudgetView(ClientPresenter presenter) {
        this.presenter = presenter;
    }

    public void display() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Budget Menu:\n1. Add Budget\n2. View Budgets\n3. Back");
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
        System.out.print("Enter budget details (format: id,category,amount): ");
        String budgetDetails = scanner.nextLine();
        Request request = new Request("addBudget", budgetDetails);
        presenter.getConnection().sendRequest(request);
        presenter.getConnection().receiveResponse();
        display();
    }

    private void viewBudgets() throws IOException {
        Request request = new Request("viewBudgets", "");
        presenter.getConnection().sendRequest(request);
        presenter.getConnection().receiveResponse();
        display();
    }

    public void showMessage(String message) {
        System.out.println(message);
    }
}
