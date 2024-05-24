package co.edu.uptc.client.view.budget;

import javax.swing.*;
import java.awt.*;
import co.edu.uptc.client.presenter.ClientPresenter;

public class BudgetView extends JPanel {
    private ClientPresenter presenter;
    private JTextArea budgetArea;
    private JButton addBudgetButton;
    private JButton viewBudgetsButton;
    private JButton editBudgetButton;
    private JButton deleteBudgetButton;
    private JButton backButton;

    public BudgetView(ClientPresenter presenter) {
        this.presenter = presenter;
        initComponents();
    }

    private void initComponents() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel titleLabel = new JLabel("Budget Management");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(titleLabel, gbc);

        budgetArea = new JTextArea(15, 30);
        budgetArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(budgetArea);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        add(scrollPane, gbc);

        addBudgetButton = new JButton("Add Budget");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        add(addBudgetButton, gbc);

        viewBudgetsButton = new JButton("View Budgets");
        gbc.gridx = 1;
        gbc.gridy = 2;
        add(viewBudgetsButton, gbc);

        editBudgetButton = new JButton("Edit Budget");
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(editBudgetButton, gbc);

        deleteBudgetButton = new JButton("Delete Budget");
        gbc.gridx = 1;
        gbc.gridy = 3;
        add(deleteBudgetButton, gbc);

        backButton = new JButton("Back");
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        add(backButton, gbc);

        addBudgetButton.addActionListener(e -> addBudget());
        viewBudgetsButton.addActionListener(e -> viewBudgets());
        editBudgetButton.addActionListener(e -> editBudget());
        deleteBudgetButton.addActionListener(e -> deleteBudget());
        backButton.addActionListener(e -> presenter.showTransactionView());
    }

    private void addBudget() {
        String category = JOptionPane.showInputDialog(this, "Enter category:");
        String amountStr = JOptionPane.showInputDialog(this, "Enter amount:");
        if (category != null && amountStr != null) {
            try {
                double amount = Double.parseDouble(amountStr);
                presenter.addBudget(category, amount);
            } catch (NumberFormatException e) {
                showMessage("Invalid amount format.");
            } catch (Exception e) {
                showMessage("Error: " + e.getMessage());
            }
        }
    }

    private void viewBudgets() {
        try {
            presenter.viewBudgets();
        } catch (Exception e) {
            showMessage("Error: " + e.getMessage());
        }
    }

    private void editBudget() {
        String budgetId = JOptionPane.showInputDialog(this, "Enter budget ID to edit:");
        if (budgetId != null) {
            String newCategory = JOptionPane.showInputDialog(this, "Enter new category:");
            String newAmountStr = JOptionPane.showInputDialog(this, "Enter new amount:");
            if (newCategory != null && newAmountStr != null) {
                try {
                    double newAmount = Double.parseDouble(newAmountStr);
                    presenter.editBudget(budgetId, newCategory, newAmount);
                } catch (NumberFormatException e) {
                    showMessage("Invalid amount format.");
                } catch (Exception e) {
                    showMessage("Error: " + e.getMessage());
                }
            }
        }
    }

    private void deleteBudget() {
        String budgetId = JOptionPane.showInputDialog(this, "Enter budget ID to delete:");
        if (budgetId != null) {
            try {
                presenter.deleteBudget(budgetId);
            } catch (Exception e) {
                showMessage("Error: " + e.getMessage());
            }
        }
    }

    public void showBudgets(String budgets) {
        budgetArea.setText(budgets);
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}
