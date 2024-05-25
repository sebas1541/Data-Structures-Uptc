package co.edu.uptc.client.view.panels.login;

import co.edu.uptc.client.presenter.ClientPresenter;
import co.edu.uptc.client.view.common.RoundedPanel;

import javax.swing.*;
import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;

public class SelectServerView extends JPanel {
    private ClientPresenter presenter;
    private JRadioButton localhostButton;
    private JRadioButton customIpButton;
    private JTextField customIpField;
    private JButton submitButton;

    public SelectServerView(ClientPresenter presenter) {
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
        JLabel titleLabel = new JLabel("Seleccione Servidor");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(createWrappedPanel(titleLabel, new Color(216, 230, 233)), gbc);

        // Localhost Radio Button
        localhostButton = new JRadioButton("Usar Localhost");
        localhostButton.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        add(createWrappedPanel(localhostButton, new Color(216, 230, 233)), gbc);

        // Custom IP Radio Button
        customIpButton = new JRadioButton("Usar IP Personalizada");
        customIpButton.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridy = 2;
        add(createWrappedPanel(customIpButton, new Color(216, 230, 233)), gbc);

        // Custom IP Text Field
        customIpField = new JTextField(20);
        gbc.gridx = 1;
        add(createWrappedPanelWithTextField(customIpField, new Color(216, 230, 233)), gbc);

        // Group the radio buttons
        ButtonGroup group = new ButtonGroup();
        group.add(localhostButton);
        group.add(customIpButton);

        // Submit Button
        submitButton = new JButton("Aceptar");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(new Color(216, 230, 233));
        buttonPanel.add(submitButton);
        add(createWrappedPanel(buttonPanel, new Color(216, 230, 233)), gbc);

        submitButton.addActionListener(e -> submit());
    }

    private void submit() {
        String selectedIp;
        if (localhostButton.isSelected()) {
            selectedIp = "localhost";
        } else if (customIpButton.isSelected()) {
            selectedIp = customIpField.getText();
        } else {
            showMessage("Seleccione una opción.");
            return;
        }

        // Save selected IP to file
        try (FileWriter writer = new FileWriter("data/server_address.txt")) {
            writer.write(selectedIp);
        } catch (IOException e) {
            showMessage("Error guardando la dirección del servidor: " + e.getMessage());
            return;
        }

        try {
            presenter.initializeConnection(); // Initialize connection after saving IP
            presenter.start(); // Start presenter after initializing connection
        } catch (IOException e) {
            showMessage("Error inicializando la conexión: " + e.getMessage());
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
