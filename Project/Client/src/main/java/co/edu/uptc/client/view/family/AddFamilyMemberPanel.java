package co.edu.uptc.client.view.family;

import co.edu.uptc.client.presenter.ClientPresenter;

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

        JLabel titleLabel = new JLabel("Add Family Member");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(titleLabel, gbc);

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        add(usernameLabel, gbc);

        usernameField = new JTextField(20);
        gbc.gridx = 1;
        add(usernameField, gbc);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(emailLabel, gbc);

        emailField = new JTextField(20);
        gbc.gridx = 1;
        add(emailField, gbc);

        addButton = new JButton("Add Member");
        backButton = new JButton("Back");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(addButton);
        buttonPanel.add(backButton);
        add(buttonPanel, gbc);

        addButton.addActionListener(e -> addFamilyMember());
        backButton.addActionListener(e -> presenter.showFamilyGroupView());
    }

    private void addFamilyMember() {
        String username = usernameField.getText();
        String email = emailField.getText();
        if (username.isEmpty() || email.isEmpty()) {
            showMessage("Please fill in all fields.");
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
}
