package co.edu.uptc.client.view.transaction;

import co.edu.uptc.client.presenter.ClientPresenter;

import javax.swing.*;
import java.awt.*;

public class DeleteTransactionPanel extends TransactionPanel {
    private ClientPresenter presenter;
    private JTextField transactionIdField;
    private JButton deleteButton;
    private JButton backButton;

    public DeleteTransactionPanel(ClientPresenter presenter) {
        this.presenter = presenter;
        initComponents();
    }

    private void initComponents() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel titleLabel = new JLabel("Delete Transaction");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(titleLabel, gbc);

        JLabel transactionIdLabel = new JLabel("Transaction ID:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        add(transactionIdLabel, gbc);

        transactionIdField = new JTextField(20);
        gbc.gridx = 1;
        add(transactionIdField, gbc);

        deleteButton = new JButton("Delete");
        backButton = new JButton("Back");
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(deleteButton);
        buttonPanel.add(backButton);
        gbc.gridx = 1;
        gbc.gridy = 2;
        add(buttonPanel, gbc);

        deleteButton.addActionListener(e -> deleteTransaction());
        backButton.addActionListener(e -> presenter.showTransactionView());
    }

    private void deleteTransaction() {
        String transactionId = transactionIdField.getText();

        if (transactionId.isEmpty()) {
            showMessage("Please enter a transaction ID.");
            return;
        }

        try {
            presenter.deleteTransaction(transactionId);
        } catch (Exception e) {
            showMessage("Error: " + e.getMessage());
        }
    }
}
