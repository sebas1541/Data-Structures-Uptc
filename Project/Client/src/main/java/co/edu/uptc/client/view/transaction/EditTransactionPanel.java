package co.edu.uptc.client.view.transaction;

import co.edu.uptc.client.presenter.ClientPresenter;
import co.edu.uptc.client.dto.TransactionData;
import co.edu.uptc.client.view.utils.RoundedPanel;
import com.google.gson.Gson;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class EditTransactionPanel extends TransactionPanel {
    private ClientPresenter presenter;
    private JTextField transactionIdField;
    private JTextField amountField;
    private JTextField descriptionField;
    private JRadioButton incomeButton;
    private JRadioButton expenseButton;
    private ButtonGroup typeGroup;
    private JRadioButton[] categoryButtons;
    private String[] categories = {"Alimentación", "Transporte", "Vivienda", "Salud", "Entretenimiento", "Ropa y Calzado", "Otros Gastos"};
    private JButton saveButton;
    private JButton backButton;
    private JPanel itemsPanel;
    private ButtonGroup transactionGroup; // Add ButtonGroup for transaction buttons

    public EditTransactionPanel(ClientPresenter presenter) {
        this.presenter = presenter;
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE); // Set background color to white

        JPanel leftPanel = new JPanel(new GridBagLayout());
        leftPanel.setBackground(Color.WHITE); // Set background color to white
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Title Label
        JLabel titleLabel = new JLabel("Edit Transaction");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        leftPanel.add(createWrappedPanel(titleLabel, new Color(216, 230, 233)), gbc);

        // Transaction ID Label and Field
        JLabel transactionIdLabel = new JLabel("Transaction ID:");
        transactionIdLabel.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        leftPanel.add(createWrappedPanel(transactionIdLabel, new Color(216, 230, 233)), gbc);

        transactionIdField = new JTextField(20);
        gbc.gridx = 1;
        leftPanel.add(createWrappedPanelWithTextField(transactionIdField, new Color(216, 230, 233)), gbc);

        // Amount Label and Field
        JLabel amountLabel = new JLabel("Amount:");
        amountLabel.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 2;
        leftPanel.add(createWrappedPanel(amountLabel, new Color(216, 230, 233)), gbc);

        amountField = new JTextField(20);
        gbc.gridx = 1;
        leftPanel.add(createWrappedPanelWithTextField(amountField, new Color(216, 230, 233)), gbc);

        // Description Label and Field
        JLabel descriptionLabel = new JLabel("Description:");
        descriptionLabel.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 3;
        leftPanel.add(createWrappedPanel(descriptionLabel, new Color(216, 230, 233)), gbc);

        descriptionField = new JTextField(20);
        gbc.gridx = 1;
        leftPanel.add(createWrappedPanelWithTextField(descriptionField, new Color(216, 230, 233)), gbc);

        // Type Label and Radio Buttons
        JLabel typeLabel = new JLabel("Type:");
        typeLabel.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 4;
        leftPanel.add(createWrappedPanel(typeLabel, new Color(216, 230, 233)), gbc);

        incomeButton = new JRadioButton("Income");
        expenseButton = new JRadioButton("Expense");
        typeGroup = new ButtonGroup();
        typeGroup.add(incomeButton);
        typeGroup.add(expenseButton);
        expenseButton.setSelected(true);

        JPanel typePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        typePanel.setBackground(new Color(216, 230, 233));
        typePanel.add(incomeButton);
        typePanel.add(expenseButton);
        gbc.gridx = 1;
        leftPanel.add(createWrappedPanel(typePanel, new Color(216, 230, 233)), gbc);

        // Category Label and Radio Buttons
        JLabel categoryLabel = new JLabel("Select Category:");
        categoryLabel.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 5;
        leftPanel.add(createWrappedPanel(categoryLabel, new Color(216, 230, 233)), gbc);

        JPanel categoryPanel = new JPanel();
        categoryPanel.setLayout(new GridLayout(2, 4));
        categoryPanel.setBackground(new Color(216, 230, 233));
        categoryButtons = new JRadioButton[categories.length];
        ButtonGroup categoryGroup = new ButtonGroup();
        for (int i = 0; i < categories.length; i++) {
            categoryButtons[i] = new JRadioButton(categories[i]);
            categoryButtons[i].setFont(new Font("Arial", Font.PLAIN, 12));
            categoryGroup.add(categoryButtons[i]);
            categoryPanel.add(categoryButtons[i]);
        }
        categoryButtons[0].setSelected(true);
        gbc.gridx = 1;
        leftPanel.add(createWrappedPanel(categoryPanel, new Color(216, 230, 233)), gbc);

        // Save and Back Buttons
        saveButton = new JButton("Save");
        backButton = new JButton("Back");
        backButton.addActionListener(e -> presenter.showTransactionView());
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(new Color(216, 230, 233));
        buttonPanel.add(saveButton);
        buttonPanel.add(backButton);
        gbc.gridx = 1;
        gbc.gridy = 6;
        leftPanel.add(createWrappedPanel(buttonPanel, new Color(216, 230, 233)), gbc);

        saveButton.addActionListener(e -> saveTransaction());
        backButton.addActionListener(e -> presenter.showTransactionView());

        add(leftPanel, BorderLayout.WEST);

        itemsPanel = new JPanel(new GridLayout(0, 1, 10, 10));
        itemsPanel.setBackground(Color.WHITE);
        transactionGroup = new ButtonGroup(); // Initialize ButtonGroup for transaction buttons

        JScrollPane scrollPane = new JScrollPane(itemsPanel);
        scrollPane.setPreferredSize(new Dimension(300, getHeight()));
        add(scrollPane, BorderLayout.CENTER);
    }

    public void loadTransactions() {
        itemsPanel.removeAll();
        try {
            presenter.viewTransactions(response -> {
                String transactions = response.getData();
                TransactionData[] transactionDataArray = new Gson().fromJson(transactions, TransactionData[].class);
                for (TransactionData transactionData : transactionDataArray) {
                    addTransactionItem(transactionData);
                }
                itemsPanel.revalidate();
                itemsPanel.repaint();
            });
        } catch (IOException e) {
            showMessage("Error loading transactions: " + e.getMessage());
        }
    }

    private void addTransactionItem(TransactionData transactionData) {
        JPanel itemPanel = new JPanel(new GridBagLayout());
        itemPanel.setBackground(new Color(216, 230, 233));
        itemPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        JPanel infoPanel = new JPanel(new GridBagLayout());
        infoPanel.setBackground(new Color(216, 230, 233));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.CENTER;

        JLabel amountLabel = new JLabel("Amount: " + transactionData.getAmount());
        amountLabel.setFont(new Font("Arial", Font.BOLD, 12));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        infoPanel.add(amountLabel, gbc);

        JLabel categoryLabel = new JLabel("Category: " + transactionData.getCategory());
        categoryLabel.setFont(new Font("Arial", Font.BOLD, 12));
        gbc.gridy = 1;
        infoPanel.add(categoryLabel, gbc);

        JLabel descriptionLabel = new JLabel("Description: " + transactionData.getDescription());
        descriptionLabel.setFont(new Font("Arial", Font.BOLD, 12));
        gbc.gridy = 2;
        infoPanel.add(descriptionLabel, gbc);

        JLabel typeLabel = new JLabel("Type: " + transactionData.getType());
        typeLabel.setFont(new Font("Arial", Font.BOLD, 12));
        gbc.gridy = 3;
        infoPanel.add(typeLabel, gbc);

        JRadioButton transactionButton = new JRadioButton();
        transactionGroup.add(transactionButton); // Add transaction button to ButtonGroup
        transactionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateTransactionFields(transactionData);
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        itemPanel.add(transactionButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.CENTER;
        itemPanel.add(infoPanel, gbc);

        itemsPanel.add(itemPanel);
    }

    private void updateTransactionFields(TransactionData transactionData) {
        transactionIdField.setText(transactionData.getTransactionId());
        amountField.setText(String.valueOf(transactionData.getAmount()));
        descriptionField.setText(transactionData.getDescription());
        if ("income".equals(transactionData.getType())) {
            incomeButton.setSelected(true);
        } else {
            expenseButton.setSelected(true);
        }
        for (JRadioButton button : categoryButtons) {
            if (button.getText().equals(transactionData.getCategory())) {
                button.setSelected(true);
                break;
            }
        }
    }

    private void saveTransaction() {
        String transactionId = transactionIdField.getText();
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

        if (transactionId.isEmpty() || amountStr.isEmpty() || description.isEmpty() || category.isEmpty()) {
            showMessage("Please fill in all fields.");
            return;
        }

        try {
            double amount = Double.parseDouble(amountStr);
            presenter.editTransaction(transactionId, category, amount, description, type);
            clearFields();
            loadTransactions();
        } catch (NumberFormatException e) {
            showMessage("Invalid amount format.");
        } catch (Exception e) {
            showMessage("Error: " + e.getMessage());
        }
    }

    private void clearFields() {
        transactionIdField.setText("");
        amountField.setText("");
        descriptionField.setText("");
        expenseButton.setSelected(true);
        categoryButtons[0].setSelected(true);
    }

    private JPanel createWrappedPanel(JComponent component, Color bgColor) {
        RoundedPanel panel = new RoundedPanel(new BorderLayout(), 15, bgColor);
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.add(component, BorderLayout.CENTER);
        return panel;
    }

    private JPanel createWrappedPanelWithTextField(JComponent component, Color bgColor) {
        RoundedPanel outerPanel = new RoundedPanel(new BorderLayout(), 15, bgColor);
        outerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        RoundedPanel innerPanel = new RoundedPanel(new BorderLayout(), 10, Color.WHITE);
        innerPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        innerPanel.add(component, BorderLayout.CENTER);

        outerPanel.add(innerPanel, BorderLayout.CENTER);
        return outerPanel;
    }
}
