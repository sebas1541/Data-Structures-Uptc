package co.edu.uptc.client.view.panels.login;

import javax.swing.*;
import java.awt.*;
import co.edu.uptc.client.presenter.ClientPresenter;
import co.edu.uptc.client.view.common.ResizeImage;

public class LoginView extends JPanel {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton registerButton;
    private ClientPresenter presenter;

    public LoginView(ClientPresenter presenter) {
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

        loginButton = new JButton("Iniciar Sesión");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        add(loginButton, gbc);

        registerButton = new JButton("Registrarse");
        gbc.gridy = 4;
        add(registerButton, gbc);

        loginButton.addActionListener(e -> {
            try {
                presenter.login(usernameField.getText(), new String(passwordField.getPassword()));
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error:  " + ex.getMessage());
            }
        });

        registerButton.addActionListener(e -> presenter.showRegisterView());
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}
