package co.edu.uptc.client.view.panels.export;

import javax.swing.*;
import java.awt.*;
import co.edu.uptc.client.presenter.ClientPresenter;
import co.edu.uptc.client.view.common.RoundedPanel;

public class ExportDataView extends JPanel {
    private ClientPresenter presenter;
    private JButton exportToTxtButton;
    private JButton exportToCsvButton;
    private JButton exportToPdfButton;
    private JButton backButton;

    public ExportDataView(ClientPresenter presenter) {
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
        JLabel titleLabel = new JLabel("Exportar Reportes");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(createWrappedPanel(titleLabel, new Color(216, 230, 233)), gbc);

        // Export to PDF Button
        exportToPdfButton = new JButton("Exportar a PDF");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        add(createWrappedPanel(exportToPdfButton, new Color(216, 230, 233)), gbc);

        // Export to TXT Button
        exportToTxtButton = new JButton("Exportar a TXT");
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(createWrappedPanel(exportToTxtButton, new Color(216, 230, 233)), gbc);

        // Export to CSV Button
        exportToCsvButton = new JButton("Exportar a EXCEL");
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(createWrappedPanel(exportToCsvButton, new Color(216, 230, 233)), gbc);

        // Back Button
        backButton = new JButton("Atrás");
        gbc.gridx = 1;
        gbc.gridy = 2;
        add(createWrappedPanel(backButton, new Color(216, 230, 233)), gbc);

        // Add action listeners for buttons
        exportToTxtButton.addActionListener(e -> exportData("txt"));
        exportToCsvButton.addActionListener(e -> exportData("csv"));
        exportToPdfButton.addActionListener(e -> exportData("pdf"));
        backButton.addActionListener(e -> presenter.showTransactionView());
    }

    private void exportData(String format) {
        try {
            presenter.exportData(format);
        } catch (Exception e) {
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
}
