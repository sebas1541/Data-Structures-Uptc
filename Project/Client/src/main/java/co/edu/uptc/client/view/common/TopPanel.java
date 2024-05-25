package co.edu.uptc.client.view.common;

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

    private void initComponents(ActionListener logoutAction) {
        setLayout(new GridBagLayout());
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(236, 237, 237)));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;

        // Logo
        companyIcon = new ImageIcon("resources/images/logo.png");
        companyIcon = new ResizeImage().resize(companyIcon, 80, 80);
        companyIconLabelHeader = new JLabel(companyIcon);
        gbc.gridx = 0;
        gbc.weightx = 0;
        gbc.anchor = GridBagConstraints.WEST;
        add(companyIconLabelHeader, gbc);

        // Username
        usernameLabel = new JLabel();
        usernameLabel.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 1;
        gbc.weightx = 0;
        gbc.anchor = GridBagConstraints.WEST;
        add(usernameLabel, gbc);

        // Filler to push date and menu to the right
        gbc.gridx = 2;
        gbc.weightx = 1;
        add(Box.createGlue(), gbc);

        // Date
        date = new JLabel();
        date.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 3;
        gbc.weightx = 0;
        gbc.anchor = GridBagConstraints.EAST;
        add(date, gbc);

        // Menu Bar
        menuBar = new JMenuBar() {
            @Override
            public Dimension getPreferredSize() {
                Dimension d = super.getPreferredSize();
                d.height = 25;  // Adjust the height to make it smaller vertically
                return d;
            }
        };
        menuBar.setBackground(Color.WHITE);
        menu = new JMenu("Menu");
        menu.setFont(new Font("Arial", Font.PLAIN, 14));
        menu.setBackground(Color.WHITE);
        menu.setBorder(BorderFactory.createEmptyBorder());
        logoutMenuItem = new JMenuItem("Cerrar Sesión");
        logoutMenuItem.setFont(new Font("Arial", Font.PLAIN, 14));
        logoutMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int response = JOptionPane.showConfirmDialog(
                        null,
                        "¿Está seguro de que desea cerrar sesión?",
                        "Confirmar Cerrar Sesión",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE
                );
                if (response == JOptionPane.YES_OPTION) {
                    logoutAction.actionPerformed(e);
                }
            }
        });
        menu.add(logoutMenuItem);
        menuBar.add(menu);

        gbc.gridx = 4;
        gbc.weightx = 0;
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
        usernameLabel.setText("Usuario: " + username);
        logoutMenuItem.setText("Cerrar Sesión " + username);
    }

    private void updateDateTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd 'de' MMM 'de' yyyy HH:mm:ss");
        String dateTimeString = LocalDateTime.now().format(formatter);
        date.setText(dateTimeString);
    }
}
