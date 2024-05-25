package co.edu.uptc.client.view.panels.family;

import co.edu.uptc.client.presenter.ClientPresenter;
import co.edu.uptc.client.dto.TransactionData;
import com.google.gson.Gson;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class ViewMemberTransactionsPanel extends JPanel {
    private ClientPresenter presenter;
    private JPanel itemsPanel;

    public ViewMemberTransactionsPanel(ClientPresenter presenter) {
        this.presenter = presenter;
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(new Color(216, 230, 233));
        JLabel titleLabel = new JLabel("Transacciones de Miembro");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        topPanel.add(titleLabel, BorderLayout.CENTER);

        add(topPanel, BorderLayout.NORTH);

        itemsPanel = new JPanel(new GridLayout(0, 1, 10, 10));
        itemsPanel.setBackground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(itemsPanel);
        scrollPane.setPreferredSize(new Dimension(300, getHeight()));
        add(scrollPane, BorderLayout.CENTER);

        JButton backButton = new JButton("Atrás");
        backButton.addActionListener(e -> presenter.showFamilyGroupView());
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(new Color(216, 230, 233));
        buttonPanel.add(backButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    public void loadTransactions(String username) {
        itemsPanel.removeAll();
        try {
            presenter.viewMemberTransactions(username, response -> {
                TransactionData[] transactions = new Gson().fromJson(response.getData(), TransactionData[].class);
                for (TransactionData transaction : transactions) {
                    addTransactionItem(transaction);
                }
                itemsPanel.revalidate();
                itemsPanel.repaint();
            });
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error cargando transacciones: " + e.getMessage());
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

        JLabel amountLabel = new JLabel("Cantidad: " + transactionData.getAmount());
        amountLabel.setFont(new Font("Arial", Font.BOLD, 12));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        infoPanel.add(amountLabel, gbc);

        JLabel categoryLabel = new JLabel("Categoría: " + transactionData.getCategory());
        categoryLabel.setFont(new Font("Arial", Font.BOLD, 12));
        gbc.gridy = 1;
        infoPanel.add(categoryLabel, gbc);

        JLabel descriptionLabel = new JLabel("Descripción: " + transactionData.getDescription());
        descriptionLabel.setFont(new Font("Arial", Font.BOLD, 12));
        gbc.gridy = 2;
        infoPanel.add(descriptionLabel, gbc);

        JLabel typeLabel = new JLabel("Tipo: " + transactionData.getType());
        typeLabel.setFont(new Font("Arial", Font.BOLD, 12));
        gbc.gridy = 3;
        infoPanel.add(typeLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.CENTER;
        itemPanel.add(infoPanel, gbc);

        itemsPanel.add(itemPanel);
    }
}
