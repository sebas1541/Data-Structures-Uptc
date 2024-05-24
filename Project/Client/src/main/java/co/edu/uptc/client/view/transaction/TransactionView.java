package co.edu.uptc.client.view.transaction;

import co.edu.uptc.client.presenter.ClientPresenter;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class TransactionView extends TransactionPanel {
    private ClientPresenter presenter;
    private CardLayout cardLayout;
    private JPanel cardPanel;
    private AddTransactionPanel addTransactionPanel;
    private EditTransactionPanel editTransactionPanel;
    private DeleteTransactionPanel deleteTransactionPanel;

    public TransactionView(ClientPresenter presenter) {
        this.presenter = presenter;
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout());
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        addTransactionPanel = new AddTransactionPanel(presenter);
        editTransactionPanel = new EditTransactionPanel(presenter);
        deleteTransactionPanel = new DeleteTransactionPanel(presenter);

        cardPanel.add(addTransactionPanel, "AddTransactionPanel");
        cardPanel.add(editTransactionPanel, "EditTransactionPanel");
        cardPanel.add(deleteTransactionPanel, "DeleteTransactionPanel");

        add(cardPanel, BorderLayout.CENTER);

        JPanel navigationPanel = new JPanel();
        navigationPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
        navigationPanel.setBackground(new Color(216, 230, 233));

        JButton addTransactionButton = createButton("Add Transaction");
        addTransactionButton.addActionListener(e -> showPanel("AddTransactionPanel"));
        navigationPanel.add(addTransactionButton);

        JButton editTransactionButton = createButton("View and Edit");
        editTransactionButton.addActionListener(e -> {
            try {
                presenter.viewTransactions(response -> {
                    editTransactionPanel.loadTransactions();
                    showPanel("EditTransactionPanel");
                });
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        navigationPanel.add(editTransactionButton);

        JButton deleteTransactionButton = createButton("Delete Transaction");
        deleteTransactionButton.addActionListener(e -> {
            try {
                presenter.viewTransactions(response -> {
                    deleteTransactionPanel.loadTransactions();
                    showPanel("DeleteTransactionPanel");
                });
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        navigationPanel.add(deleteTransactionButton);

        add(navigationPanel, BorderLayout.SOUTH);
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(150, 50));
        return button;
    }

    private void showPanel(String panelName) {
        cardLayout.show(cardPanel, panelName);
    }
}
