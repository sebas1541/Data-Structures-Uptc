package co.edu.uptc.client.view.family;

import co.edu.uptc.client.net.TransactionData;
import co.edu.uptc.client.presenter.ClientPresenter;
import com.google.gson.Gson;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class ViewMemberTransactionsPanel extends JPanel {
    private ClientPresenter presenter;
    private JList<TransactionData> transactionList;
    private DefaultListModel<TransactionData> listModel;

    public ViewMemberTransactionsPanel(ClientPresenter presenter) {
        this.presenter = presenter;
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout());
        listModel = new DefaultListModel<>();
        transactionList = new JList<>(listModel);
        transactionList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        add(new JScrollPane(transactionList), BorderLayout.CENTER);

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> presenter.showFamilyGroupView());
        add(backButton, BorderLayout.SOUTH);
    }

    public void loadTransactions(String username) {
        try {
            presenter.viewMemberTransactions(username, response -> {
                listModel.clear();
                if ("success".equals(response.getStatus())) {
                    TransactionData[] transactions = new Gson().fromJson(response.getData(), TransactionData[].class);
                    for (TransactionData transaction : transactions) {
                        listModel.addElement(transaction);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Error loading transactions: " + response.getData());
                }
            });
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error loading transactions: " + e.getMessage());
        }
    }

}
