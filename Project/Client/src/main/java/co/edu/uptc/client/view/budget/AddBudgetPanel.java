package co.edu.uptc.client.view.budget;

import co.edu.uptc.client.presenter.ClientPresenter;
import co.edu.uptc.client.view.utils.RoundedPanel;

import javax.swing.*;
import java.awt.*;

public class AddBudgetPanel extends JPanel {
    private ClientPresenter presenter;
    private JTextField amountField;
    private JRadioButton[] categoryButtons;
    private String[] categories = {"Alimentación", "Transporte", "Vivienda", "Salud", "Entretenimiento", "Ropa y Calzado", "Otros Gastos"};
    private JButton saveButton;
    private JButton backButton;

    public AddBudgetPanel(ClientPresenter presenter) {
        this.presenter = presenter;
        initComponents();
    }

    private void initComponents() {
        setLayout(new GridBagLayout());
        setBackground(Color.WHITE); // Set background color to white
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Title Label
        JLabel titleLabel = new JLabel("Add Budget");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(createWrappedPanel(titleLabel, new Color(216, 230, 233)), gbc);

        // Amount Label and Field
        JLabel amountLabel = new JLabel("Amount:");
        amountLabel.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        add(createWrappedPanel(amountLabel, new Color(216, 230, 233)), gbc);

        amountField = new JTextField(20);
        gbc.gridx = 1;
        add(createWrappedPanelWithTextField(amountField, new Color(216, 230, 233)), gbc);

        // Category Label and Radio Buttons
        JLabel categoryLabel = new JLabel("Select Category:");
        categoryLabel.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(createWrappedPanel(categoryLabel, new Color(216, 230, 233)), gbc);

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
        add(createWrappedPanel(categoryPanel, new Color(216, 230, 233)), gbc);

        // Save and Back Buttons
        saveButton = new JButton("Save");
        backButton = new JButton("Back");
        backButton.addActionListener(e -> presenter.showBudgetView());
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(new Color(216, 230, 233));
        buttonPanel.add(saveButton);
        buttonPanel.add(backButton);
        gbc.gridx = 1;
        gbc.gridy = 3;
        add(createWrappedPanel(buttonPanel, new Color(216, 230, 233)), gbc);

        saveButton.addActionListener(e -> saveBudget());
    }

    private void saveBudget() {
        String amountStr = amountField.getText();
        String category = "";
        for (JRadioButton button : categoryButtons) {
            if (button.isSelected()) {
                category = button.getText();
                break;
            }
        }

        if (amountStr.isEmpty() || category.isEmpty()) {
            showMessage("Please fill in all fields.");
            return;
        }

        try {
            double amount = Double.parseDouble(amountStr);
            presenter.addBudget(category, amount);
            clearFields();
        } catch (NumberFormatException e) {
            showMessage("Invalid amount format.");
        } catch (Exception e) {
            showMessage("Error: " + e.getMessage());
        }
    }

    private void clearFields() {
        amountField.setText("");
        categoryButtons[0].setSelected(true);
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
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
