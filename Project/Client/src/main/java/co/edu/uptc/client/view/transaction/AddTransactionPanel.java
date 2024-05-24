package co.edu.uptc.client.view.transaction;

import co.edu.uptc.client.presenter.ClientPresenter;

import javax.swing.*;
import java.awt.*;

public class AddTransactionPanel extends TransactionPanel {
    private ClientPresenter presenter;
    private JTextField amountField;
    private JTextField descriptionField;
    private JRadioButton incomeButton;
    private JRadioButton expenseButton;
    private ButtonGroup typeGroup;
    private JRadioButton[] categoryButtons;
    private String[] categories = {"Alimentación", "Transporte", "Vivienda", "Salud", "Entretenimiento", "Ropa y Calzado", "Otros Gastos"};
    private JButton saveButton;
    private JButton backButton;

    public AddTransactionPanel(ClientPresenter presenter) {
        this.presenter = presenter;
        initComponents();
    }

    private void initComponents() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel titleLabel = new JLabel("Add Transaction");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(titleLabel, gbc);

        JLabel amountLabel = new JLabel("Amount:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        add(amountLabel, gbc);

        amountField = new JTextField(20);
        gbc.gridx = 1;
        add(amountField, gbc);

        JLabel descriptionLabel = new JLabel("Description:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(descriptionLabel, gbc);

        descriptionField = new JTextField(20);
        gbc.gridx = 1;
        add(descriptionField, gbc);

        JLabel typeLabel = new JLabel("Type:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(typeLabel, gbc);

        incomeButton = new JRadioButton("Income");
        expenseButton = new JRadioButton("Expense");
        typeGroup = new ButtonGroup();
        typeGroup.add(incomeButton);
        typeGroup.add(expenseButton);
        expenseButton.setSelected(true);

        JPanel typePanel = new JPanel();
        typePanel.add(incomeButton);
        typePanel.add(expenseButton);
        gbc.gridx = 1;
        add(typePanel, gbc);

        JLabel categoryLabel = new JLabel("Select Category:");
        gbc.gridx = 0;
        gbc.gridy = 4;
        add(categoryLabel, gbc);

        JPanel categoryPanel = new JPanel();
        categoryPanel.setLayout(new GridLayout(2, 4));
        categoryButtons = new JRadioButton[categories.length];
        ButtonGroup categoryGroup = new ButtonGroup();
        for (int i = 0; i < categories.length; i++) {
            categoryButtons[i] = new JRadioButton(categories[i]);
            categoryGroup.add(categoryButtons[i]);
            categoryPanel.add(categoryButtons[i]);
        }
        categoryButtons[0].setSelected(true);

        gbc.gridx = 1;
        add(categoryPanel, gbc);

        saveButton = new JButton("Save");
        backButton = new JButton("Back");
        backButton.addActionListener(e -> presenter.showTransactionView());
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(saveButton);
        buttonPanel.add(backButton);
        gbc.gridx = 1;
        gbc.gridy = 5;
        add(buttonPanel, gbc);

        saveButton.addActionListener(e -> saveTransaction());
        backButton.addActionListener(e -> presenter.showTransactionView());
    }

    private void saveTransaction() {
        String amountStr = amountField.getText();
        String description = descriptionField.getText();
        String type = incomeButton.isSelected() ? "income" : "expense";
        String category = "";
        for (JRadioButton button : categoryButtons) {
            if (button.isSelected()) {
                category = button.getText();
                break;
            }
        }

        if (amountStr.isEmpty() || description.isEmpty() || category.isEmpty()) {
            showMessage("Please fill in all fields.");
            return;
        }

        try {
            double amount = Double.parseDouble(amountStr);
            presenter.addTransaction(category, amount, description, type);
        } catch (NumberFormatException e) {
            showMessage("Invalid amount format.");
        } catch (Exception e) {
            showMessage("Error: " + e.getMessage());
        }
    }
}
