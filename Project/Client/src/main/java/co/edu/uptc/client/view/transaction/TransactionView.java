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
    private ViewTransactionPanel viewTransactionPanel;
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
        viewTransactionPanel = new ViewTransactionPanel(presenter);
        editTransactionPanel = new EditTransactionPanel(presenter);
        deleteTransactionPanel = new DeleteTransactionPanel(presenter);

        cardPanel.add(addTransactionPanel, "AddTransactionPanel");
        cardPanel.add(viewTransactionPanel, "ViewTransactionPanel");
        cardPanel.add(editTransactionPanel, "EditTransactionPanel");
        cardPanel.add(deleteTransactionPanel, "DeleteTransactionPanel");

        add(cardPanel, BorderLayout.CENTER);

        JPanel navigationPanel = new JPanel();
        navigationPanel.setLayout(new GridLayout(0, 1));

        JButton addTransactionButton = new JButton("Add Transaction");
        addTransactionButton.addActionListener(e -> showPanel("AddTransactionPanel"));
        navigationPanel.add(addTransactionButton);

        JButton viewTransactionsButton = new JButton("View Transactions");
        viewTransactionsButton.addActionListener(e -> {
            try {
                presenter.viewTransactions();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            showPanel("ViewTransactionPanel");
        });
        navigationPanel.add(viewTransactionsButton);

        JButton editTransactionButton = new JButton("Edit Transaction");
        editTransactionButton.addActionListener(e -> showPanel("EditTransactionPanel"));
        navigationPanel.add(editTransactionButton);

        JButton deleteTransactionButton = new JButton("Delete Transaction");
        deleteTransactionButton.addActionListener(e -> showPanel("DeleteTransactionPanel"));
        navigationPanel.add(deleteTransactionButton);

        add(navigationPanel, BorderLayout.WEST);
    }

    private void showPanel(String panelName) {
        cardLayout.show(cardPanel, panelName);
    }
}
