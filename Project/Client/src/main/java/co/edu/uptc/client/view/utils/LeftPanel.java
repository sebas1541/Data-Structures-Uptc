package co.edu.uptc.client.view.utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class LeftPanel extends JPanel {
    private static final Color BUTTON_TEXT_COLOR = new Color(189, 189, 189);
    private static final Font BUTTON_FONT = new Font("Arial", Font.PLAIN, 12);
    private static final int IMAGE_WIDTH = 30;
    private static final int IMAGE_HEIGHT = 28;

    public LeftPanel(ActionListener ac) {
        initComponents(ac);
    }

    private JPanel createButtonPanel(String text, String imagePath, Color textColor, ActionListener ac) {
        JPanel buttonPanel = new JPanel(new BorderLayout());
        ImageIcon icon = new ImageIcon(imagePath);
        icon = new ResizeImage().resize(icon, IMAGE_WIDTH, IMAGE_HEIGHT);
        JLabel imageLabel = new JLabel(icon);
        JButton textButton = new JButton(text);
        Cursor pointer = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
        textButton.addActionListener(ac);
        textButton.setCursor(pointer);
        textButton.setForeground(textColor);
        textButton.setFont(BUTTON_FONT);
        textButton.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.add(imageLabel, BorderLayout.CENTER);
        buttonPanel.add(textButton, BorderLayout.SOUTH);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 80, 0));

        return buttonPanel;
    }

    private void add(JPanel panel, GridBagConstraints gbc, int gridy) {
        gbc.gridy = gridy;
        add(panel, gbc);
    }

    private void initComponents(ActionListener ac) {
        setLayout(new GridBagLayout());
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(107, getHeight()));
        setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, new Color(236, 237, 237)));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 0, 10, 0);

        add(createButtonPanel("Transactions", "resources/leftPanelIcons/history.png", BUTTON_TEXT_COLOR, ac), gbc, 0);
        add(createButtonPanel("Budget", "resources/leftPanelIcons/config.png", BUTTON_TEXT_COLOR, ac), gbc, 1);
        add(createButtonPanel("Family", "resources/leftPanelIcons/home.png", BUTTON_TEXT_COLOR, ac), gbc, 2);
        add(createButtonPanel("Report", "resources/leftPanelIcons/order.png", BUTTON_TEXT_COLOR, ac), gbc, 3);
    }
}
