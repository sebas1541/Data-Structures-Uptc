package co.edu.uptc.client.view.utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TopPanel extends JPanel {
    private JLabel dateLabel;
    private ImageIcon companyIcon;
    private JLabel companyIconLabelHeader;
    private JLabel date;
    private JLabel usernameLabel;
    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem logoutMenuItem;

    public TopPanel(ActionListener logoutAction) {
        initComponents(logoutAction);
    }

    public void initComponents(ActionListener logoutAction) {
        setLayout(new GridBagLayout());
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(236, 237, 237)));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 17, 5, 10);
        gbc.gridy = 0;

        // Image Styling and Positioning
        companyIcon = new ImageIcon("resources/images/logo.png");
        companyIcon = new ResizeImage().resize(companyIcon, 80, 80); // Previous size
        companyIconLabelHeader = new JLabel(companyIcon);
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.WEST;
        add(companyIconLabelHeader, gbc);

        // Username Label
        usernameLabel = new JLabel();
        usernameLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        add(usernameLabel, gbc);

        // Date Styles
        date = new JLabel();
        date.setFont(new Font("Arial", Font.PLAIN, 12));
        gbc.gridx = 2;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.weightx = 1.0;
        add(date, gbc);

        // Menu Bar
        menuBar = new JMenuBar();
        menuBar.setBackground(Color.WHITE); // Set background to white
        menu = new JMenu("Menu");
        menu.setBackground(Color.WHITE); // Set background to white
        menu.setBorder(BorderFactory.createEmptyBorder()); // Remove borders
        logoutMenuItem = new JMenuItem("Logout");
        logoutMenuItem.addActionListener(logoutAction);
        menu.add(logoutMenuItem);
        menuBar.add(menu);

        gbc.gridx = 3;
        gbc.anchor = GridBagConstraints.EAST;
        add(menuBar, gbc);

        // Timer for date and time
        int delay = 1000;
        new Timer(delay, new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                updateDateTime();
            }
        }).start();
    }

    public void setUsername(String username) {
        usernameLabel.setText("User: " + username);
        logoutMenuItem.setText("Logout " + username);
    }

    private void updateDateTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd 'de' MMM 'de' yyyy HH:mm:ss");
        String dateTimeString = LocalDateTime.now().format(formatter);
        date.setText(dateTimeString);
    }
}
