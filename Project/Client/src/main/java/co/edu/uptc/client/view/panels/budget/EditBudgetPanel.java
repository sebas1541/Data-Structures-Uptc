package co.edu.uptc.client.view.panels.budget;

import co.edu.uptc.client.presenter.ClientPresenter;
import co.edu.uptc.client.dto.BudgetData;
import com.google.gson.Gson;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class EditBudgetPanel extends JPanel {
    private ClientPresenter presenter;
    private JTextField budgetIdField;
    private JTextField amountField;
    private JRadioButton[] categoryButtons;
    private String[] categories = {"Alimentación", "Transporte", "Vivienda", "Salud", "Entretenimiento", "Ropa y Calzado", "Otros Gastos"};
    private JButton saveButton;
    private JButton backButton;
    private JPanel itemsPanel;
    private ButtonGroup budgetGroup;
    private ButtonGroup categoryGroup;
    private Gson gson;

    public EditBudgetPanel(ClientPresenter presenter) {
        this.presenter = presenter;
        this.gson = new Gson();
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

        JLabel titleLabel = new JLabel("Editar Presupuesto");
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

        JLabel amountLabel = new JLabel("Cantidad:");
        amountLabel.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 2;
        leftPanel.add(createWrappedPanel(amountLabel, new Color(216, 230, 233)), gbc);

        amountField = new JTextField(20);
        gbc.gridx = 1;
        leftPanel.add(createWrappedPanelWithTextField(amountField, new Color(216, 230, 233)), gbc);

        // Category Label and Radio Buttons
        JLabel categoryLabel = new JLabel("Seleccionar Categoría:");
        categoryLabel.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 3;
        leftPanel.add(createWrappedPanel(categoryLabel, new Color(216, 230, 233)), gbc);

        JPanel categoryPanel = new JPanel();
        categoryPanel.setLayout(new GridLayout(2, 4));
        categoryPanel.setBackground(new Color(216, 230, 233));
        categoryButtons = new JRadioButton[categories.length];
        categoryGroup = new ButtonGroup();
        for (int i = 0; i < categories.length; i++) {
            categoryButtons[i] = new JRadioButton(categories[i]);
            categoryButtons[i].setFont(new Font("Arial", Font.PLAIN, 12));
            categoryGroup.add(categoryButtons[i]);
            categoryPanel.add(categoryButtons[i]);
        }
        gbc.gridx = 1;
        leftPanel.add(createWrappedPanel(categoryPanel, new Color(216, 230, 233)), gbc);

        saveButton = new JButton("Guardar");
        backButton = new JButton("Atrás");
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(new Color(216, 230, 233));
        buttonPanel.add(saveButton);
        buttonPanel.add(backButton);
        gbc.gridx = 1;
        gbc.gridy = 4;
        leftPanel.add(createWrappedPanel(buttonPanel, new Color(216, 230, 233)), gbc);

        saveButton.addActionListener(e -> saveBudget());
        backButton.addActionListener(e -> presenter.showTransactionView());

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
                try {
                    System.out.println("Raw budget data: " + budgets); // Debugging statement
                    BudgetData[] budgetDataArray = gson.fromJson(budgets, BudgetData[].class);
                    for (BudgetData budgetData : budgetDataArray) {
                        addBudgetItem(budgetData);
                    }
                    itemsPanel.revalidate();
                    itemsPanel.repaint();
                } catch (com.google.gson.JsonSyntaxException e) {
                    showMessage("Error parsing budget data: " + e.getMessage());
                }
            });
        } catch (IOException e) {
            showMessage("Error loading budgets: " + e.getMessage());
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

        JLabel amountLabel = new JLabel("Amount: " + budgetData.getAmount());
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
        gbc.fill = GridBagConstraints.BOTH;
        itemPanel.add(infoPanel, gbc);

        itemsPanel.add(itemPanel);
    }

    private void updateBudgetFields(BudgetData budgetData) {
        budgetIdField.setText(budgetData.getBudgetId());
        amountField.setText(String.valueOf(budgetData.getAmount()));
        for (JRadioButton button : categoryButtons) {
            if (button.getText().equals(budgetData.getCategory())) {
                button.setSelected(true);
                break;
            }
        }
    }

    private void saveBudget() {
        try {
            String budgetId = budgetIdField.getText();
            String category = "";
            for (JRadioButton button : categoryButtons) {
                if (button.isSelected()) {
                    category = button.getText();
                    break;
                }
            }
            double amount = Double.parseDouble(amountField.getText());
            presenter.editBudget(budgetId, category, amount);
            presenter.viewBudgets(); // Refresh the list after saving
        } catch (NumberFormatException | IOException e) {
            showMessage("Error: " + e.getMessage());
        }
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    private JPanel createWrappedPanel(Component component, Color color) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.setBackground(color);
        panel.add(component);
        return panel;
    }

    private JPanel createWrappedPanelWithTextField(JTextField textField, Color color) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.setBackground(color);
        panel.add(textField);
        return panel;
    }
}
