package co.edu.uptc.client.view.panels.family;

import co.edu.uptc.client.presenter.ClientPresenter;
import co.edu.uptc.client.view.common.RoundedPanel;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class AddFamilyMemberPanel extends JPanel {
    private ClientPresenter presenter;
    private JTextField usernameField;
    private JTextField emailField;
    private JButton addButton;
    private JButton backButton;

    public AddFamilyMemberPanel(ClientPresenter presenter) {
        this.presenter = presenter;
        initComponents();
    }

    private void initComponents() {
        setLayout(new GridBagLayout());
        setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Title Label
        JLabel titleLabel = new JLabel("Agregar Miembro");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(createWrappedPanel(titleLabel, new Color(216, 230, 233)), gbc);

        // Username Label and Field
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        add(createWrappedPanel(usernameLabel, new Color(216, 230, 233)), gbc);

        usernameField = new JTextField(20);
        gbc.gridx = 1;
        add(createWrappedPanelWithTextField(usernameField, new Color(216, 230, 233)), gbc);

        // Email Label and Field
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(createWrappedPanel(emailLabel, new Color(216, 230, 233)), gbc);

        emailField = new JTextField(20);
        gbc.gridx = 1;
        add(createWrappedPanelWithTextField(emailField, new Color(216, 230, 233)), gbc);

        // Add and Back Buttons
        addButton = new JButton("Agregar Miembro");
        backButton = new JButton("Atrás");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(new Color(216, 230, 233));
        buttonPanel.add(addButton);
        buttonPanel.add(backButton);
        add(createWrappedPanel(buttonPanel, new Color(216, 230, 233)), gbc);

        addButton.addActionListener(e -> addFamilyMember());
        backButton.addActionListener(e -> presenter.showTransactionView());
    }

    private void addFamilyMember() {
        String username = usernameField.getText();
        String email = emailField.getText();
        if (username.isEmpty() || email.isEmpty()) {
            showMessage("Llene todos los campos");
            return;
        }
        try {
            presenter.addFamilyMember(username, email);
        } catch (IOException e) {
            showMessage("Error: " + e.getMessage());
        }
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
