package co.edu.uptc.client.view.panels.login;

import javax.swing.*;
import java.awt.*;
import co.edu.uptc.client.presenter.ClientPresenter;
import co.edu.uptc.client.view.common.ResizeImage;

public class RegisterView extends JPanel {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;
    private JTextField emailField;
    private JButton registerButton;
    private JButton backButton;
    private ClientPresenter presenter;

    public RegisterView(ClientPresenter presenter) {
        this.presenter = presenter;
        initComponents();
    }

    private void initComponents() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Add logo at the top
        ImageIcon companyIcon = new ImageIcon("resources/images/logo.png");
        companyIcon = new ResizeImage().resize(companyIcon, 120, 120); // Larger size
        JLabel companyIconLabel = new JLabel(companyIcon);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 0, 20, 0);
        add(companyIconLabel, gbc);

        gbc.gridwidth = 1; // Reset gridwidth

        JLabel usernameLabel = new JLabel("Username:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(usernameLabel, gbc);

        usernameField = new JTextField(20);
        gbc.gridx = 1;
        add(usernameField, gbc);

        JLabel passwordLabel = new JLabel("Contraseña:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(passwordLabel, gbc);

        passwordField = new JPasswordField(20);
        gbc.gridx = 1;
        add(passwordField, gbc);

        JLabel confirmPasswordLabel = new JLabel("Confirm Contraseña:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(confirmPasswordLabel, gbc);

        confirmPasswordField = new JPasswordField(20);
        gbc.gridx = 1;
        add(confirmPasswordField, gbc);

        JLabel emailLabel = new JLabel("Email:");
        gbc.gridx = 0;
        gbc.gridy = 4;
        add(emailLabel, gbc);

        emailField = new JTextField(20);
        gbc.gridx = 1;
        add(emailField, gbc);

        registerButton = new JButton("Registrarse");
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        add(registerButton, gbc);

        backButton = new JButton("Atrás");
        gbc.gridy = 6;
        add(backButton, gbc);

        registerButton.addActionListener(e -> register());
        backButton.addActionListener(e -> presenter.showLoginView());
    }

    private void register() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        String confirmPassword = new String(confirmPasswordField.getPassword());
        String email = emailField.getText();

        if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || email.isEmpty()) {
            showMessage("Llene todos los campos");
            return;
        }

        if (!password.equals(confirmPassword)) {
            showMessage("Las contraseñas no son iguales");
            return;
        }

        try {
            presenter.register(username, password, email);
        } catch (Exception e) {
            showMessage("Error: " + e.getMessage());
        }
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}
