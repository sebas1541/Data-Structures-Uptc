package co.edu.uptc.client.view.export;

import javax.swing.*;
import java.awt.*;
import co.edu.uptc.client.presenter.ClientPresenter;

public class ExportDataView extends JPanel {
    private ClientPresenter presenter;
    private JButton exportToTxtButton;
    private JButton exportToExcelButton;
    private JButton exportToPdfButton;
    private JButton backButton;

    public ExportDataView(ClientPresenter presenter) {
        this.presenter = presenter;
        initComponents();
    }

    private void initComponents() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel titleLabel = new JLabel("Export Data");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(titleLabel, gbc);

        exportToTxtButton = new JButton("Export to TXT");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        add(exportToTxtButton, gbc);

        exportToExcelButton = new JButton("Export to Excel");
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(exportToExcelButton, gbc);

        exportToPdfButton = new JButton("Export to PDF");
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(exportToPdfButton, gbc);

        backButton = new JButton("Back");
        gbc.gridx = 1;
        gbc.gridy = 2;
        add(backButton, gbc);

        exportToTxtButton.addActionListener(e -> exportData("txt"));
        exportToExcelButton.addActionListener(e -> exportData("excel"));
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
}
