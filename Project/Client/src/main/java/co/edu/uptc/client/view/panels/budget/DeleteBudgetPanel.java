package co.edu.uptc.client.view.panels.budget;

import co.edu.uptc.client.presenter.ClientPresenter;
import co.edu.uptc.client.dto.BudgetData;
import com.google.gson.Gson;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class DeleteBudgetPanel extends JPanel {
    private ClientPresenter presenter;
    private JTextField budgetIdField;
    private JButton deleteButton;
    private JButton backButton;
    private JPanel itemsPanel;
    private ButtonGroup budgetGroup;

    public DeleteBudgetPanel(ClientPresenter presenter) {
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

        JLabel titleLabel = new JLabel("Eliminar Presupuesto");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        leftPanel.add(createWrappedPanel(titleLabel, new Color(216, 230, 233)), gbc);

        JLabel budgetIdLabel = new JLabel("ID de Presupuesto:");
        budgetIdLabel.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        leftPanel.add(createWrappedPanel(budgetIdLabel, new Color(216, 230, 233)), gbc);

        budgetIdField = new JTextField(20);
        gbc.gridx = 1;
        leftPanel.add(createWrappedPanelWithTextField(budgetIdField, new Color(216, 230, 233)), gbc);

        deleteButton = new JButton("Eliminar");
        backButton = new JButton("Atrás");
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(new Color(216, 230, 233));
        buttonPanel.add(deleteButton);
        buttonPanel.add(backButton);
        gbc.gridx = 1;
        gbc.gridy = 2;
        leftPanel.add(createWrappedPanel(buttonPanel, new Color(216, 230, 233)), gbc);

        deleteButton.addActionListener(e -> deleteBudget());
        backButton.addActionListener(e -> presenter.showBudgetView());

        add(leftPanel, BorderLayout.WEST);

        itemsPanel = new JPanel(new GridLayout(0, 1, 10, 10));
        itemsPanel.setBackground(Color.WHITE);
        budgetGroup = new ButtonGroup();

        JScrollPane scrollPane = new JScrollPane(itemsPanel);
        scrollPane.setPreferredSize(new Dimension(300, getHeight()));
        add(scrollPane, BorderLayout.CENTER);
    }

    public void loadBudgets() {
        itemsPanel.removeAll();
        try {
            presenter.viewBudgets(response -> {
                String budgets = response.getData();
                BudgetData[] budgetDataArray = new Gson().fromJson(budgets, BudgetData[].class);
                for (BudgetData budgetData : budgetDataArray) {
                    addBudgetItem(budgetData);
                }
                itemsPanel.revalidate();
                itemsPanel.repaint();
            });
        } catch (IOException e) {
            showMessage("Error cargando presupuestos: " + e.getMessage());
        }
    }

    private void addBudgetItem(BudgetData budgetData) {
        JPanel itemPanel = new JPanel(new GridBagLayout());
        itemPanel.setBackground(new Color(216, 230, 233));
        itemPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        JPanel infoPanel = new JPanel(new GridBagLayout());
        infoPanel.setBackground(new Color(216, 230, 233));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.CENTER;

        JLabel amountLabel = new JLabel("Cantidad: " + budgetData.getAmount());
        amountLabel.setFont(new Font("Arial", Font.BOLD, 12));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        infoPanel.add(amountLabel, gbc);

        JLabel categoryLabel = new JLabel("Categoría: " + budgetData.getCategory());
        categoryLabel.setFont(new Font("Arial", Font.BOLD, 12));
        gbc.gridy = 1;
        infoPanel.add(categoryLabel, gbc);

        JRadioButton budgetButton = new JRadioButton();
        budgetGroup.add(budgetButton);
        budgetButton.addActionListener(e -> updateBudgetFields(budgetData));

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        itemPanel.add(budgetButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.CENTER;
        itemPanel.add(infoPanel, gbc);

        itemsPanel.add(itemPanel);
    }

    private void updateBudgetFields(BudgetData budgetData) {
        budgetIdField.setText(budgetData.getBudgetId());
    }

    private void deleteBudget() {
        String budgetId = budgetIdField.getText();

        if (budgetId.isEmpty()) {
            showMessage("Introduzca el ID de presupuesto.");
            return;
        }

        try {
            presenter.deleteBudget(budgetId);
            loadBudgets();
        } catch (Exception e) {
            showMessage("Error: " + e.getMessage());
        }
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    private JPanel createWrappedPanel(JComponent component, Color bgColor) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(bgColor);
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.add(component, BorderLayout.CENTER);
        return panel;
    }

    private JPanel createWrappedPanelWithTextField(JComponent component, Color bgColor) {
        JPanel outerPanel = new JPanel(new BorderLayout());
        outerPanel.setBackground(bgColor);
        outerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel innerPanel = new JPanel(new BorderLayout());
        innerPanel.setBackground(Color.WHITE);
        innerPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        innerPanel.add(component, BorderLayout.CENTER);

        outerPanel.add(innerPanel, BorderLayout.CENTER);
        return outerPanel;
    }
}
