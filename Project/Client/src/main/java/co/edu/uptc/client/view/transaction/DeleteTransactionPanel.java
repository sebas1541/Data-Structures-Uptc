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

public class DeleteTransactionPanel extends TransactionPanel {
    private ClientPresenter presenter;
    private JTextField transactionIdField;
    private JButton deleteButton;
    private JButton backButton;
    private JPanel itemsPanel;
    private ButtonGroup transactionGroup;

    public DeleteTransactionPanel(ClientPresenter presenter) {
        this.presenter = presenter;
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        JPanel leftPanel = new JPanel(new GridBagLayout());
        leftPanel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titleLabel = new JLabel("Delete Transaction");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        leftPanel.add(createWrappedPanel(titleLabel, new Color(216, 230, 233)), gbc);

        JLabel transactionIdLabel = new JLabel("Transaction ID:");
        transactionIdLabel.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        leftPanel.add(createWrappedPanel(transactionIdLabel, new Color(216, 230, 233)), gbc);

        transactionIdField = new JTextField(20);
        gbc.gridx = 1;
        leftPanel.add(createWrappedPanelWithTextField(transactionIdField, new Color(216, 230, 233)), gbc);

        deleteButton = new JButton("Delete");
        backButton = new JButton("Back");
        backButton.addActionListener(e -> presenter.showTransactionView());
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(new Color(216, 230, 233));
        buttonPanel.add(deleteButton);
        buttonPanel.add(backButton);
        gbc.gridx = 1;
        gbc.gridy = 2;
        leftPanel.add(createWrappedPanel(buttonPanel, new Color(216, 230, 233)), gbc);

        deleteButton.addActionListener(e -> deleteTransaction());
        backButton.addActionListener(e -> presenter.showTransactionView());

        add(leftPanel, BorderLayout.WEST);

        itemsPanel = new JPanel(new GridLayout(0, 1, 10, 10));
        itemsPanel.setBackground(Color.WHITE);
        transactionGroup = new ButtonGroup();

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
        transactionGroup.add(transactionButton);
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
    }

    private void deleteTransaction() {
        String transactionId = transactionIdField.getText();

        if (transactionId.isEmpty()) {
            showMessage("Please enter a transaction ID.");
            return;
        }

        try {
            presenter.deleteTransaction(transactionId);
            loadTransactions(); // Reload transactions after deleting
        } catch (Exception e) {
            showMessage("Error: " + e.getMessage());
        }
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
