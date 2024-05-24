package co.edu.uptc.client.view.family;

import co.edu.uptc.client.presenter.ClientPresenter;
import co.edu.uptc.client.net.FamilyMemberData;
import co.edu.uptc.client.view.utils.RoundedPanel;
import com.google.gson.Gson;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class ViewFamilyMembersPanel extends JPanel {
    private ClientPresenter presenter;
    private JPanel itemsPanel;
    private ButtonGroup memberGroup;
    private JButton backButton;

    public ViewFamilyMembersPanel(ClientPresenter presenter) {
        this.presenter = presenter;
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        JPanel leftPanel = new JPanel(new GridBagLayout());
        leftPanel.setBackground(Color.WHITE); // Set background color to white
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Title Label
        JLabel titleLabel = new JLabel("Family Members");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        leftPanel.add(createWrappedPanel(titleLabel, new Color(216, 230, 233)), gbc);

        // Back Button
        backButton = new JButton("Back");
        backButton.addActionListener(e -> presenter.showFamilyGroupView());
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(new Color(216, 230, 233));
        buttonPanel.add(backButton);
        gbc.gridx = 1;
        gbc.gridy = 1;
        leftPanel.add(createWrappedPanel(buttonPanel, new Color(216, 230, 233)), gbc);

        add(leftPanel, BorderLayout.WEST);

        itemsPanel = new JPanel(new GridLayout(0, 1, 10, 10));
        itemsPanel.setBackground(Color.WHITE);
        memberGroup = new ButtonGroup(); // Initialize ButtonGroup for member buttons

        JScrollPane scrollPane = new JScrollPane(itemsPanel);
        scrollPane.setPreferredSize(new Dimension(300, getHeight()));
        add(scrollPane, BorderLayout.CENTER);
    }

    public void loadFamilyMembers() {
        itemsPanel.removeAll();
        try {
            presenter.viewFamilyMembers(response -> {
                String members = response.getData();
                FamilyMemberData[] familyMemberDataArray = new Gson().fromJson(members, FamilyMemberData[].class);
                for (FamilyMemberData familyMemberData : familyMemberDataArray) {
                    addFamilyMemberItem(familyMemberData);
                }
                itemsPanel.revalidate();
                itemsPanel.repaint();
            });
        } catch (IOException e) {
            showMessage("Error loading family members: " + e.getMessage());
        }
    }

    private void addFamilyMemberItem(FamilyMemberData familyMemberData) {
        JPanel itemPanel = new JPanel(new GridBagLayout());
        itemPanel.setBackground(new Color(216, 230, 233));
        itemPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        JPanel infoPanel = new JPanel(new GridBagLayout());
        infoPanel.setBackground(new Color(216, 230, 233));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.CENTER;

        JLabel usernameLabel = new JLabel("Username: " + familyMemberData.getUsername());
        usernameLabel.setFont(new Font("Arial", Font.BOLD, 12));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        infoPanel.add(usernameLabel, gbc);

        JLabel emailLabel = new JLabel("Email: " + familyMemberData.getEmail());
        emailLabel.setFont(new Font("Arial", Font.BOLD, 12));
        gbc.gridy = 1;
        infoPanel.add(emailLabel, gbc);

        JRadioButton memberButton = new JRadioButton();
        memberGroup.add(memberButton); // Add member button to ButtonGroup

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        itemPanel.add(memberButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.CENTER;
        itemPanel.add(infoPanel, gbc);

        itemsPanel.add(itemPanel);
    }

    private JPanel createWrappedPanel(JComponent component, Color bgColor) {
        RoundedPanel panel = new RoundedPanel(new BorderLayout(), 15, bgColor);
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.add(component, BorderLayout.CENTER);
        return panel;
    }

    public void showFamilyMembers(String members) {
        itemsPanel.removeAll();
        FamilyMemberData[] familyMemberDataArray = new Gson().fromJson(members, FamilyMemberData[].class);
        for (FamilyMemberData familyMemberData : familyMemberDataArray) {
            addFamilyMemberItem(familyMemberData);
        }
        itemsPanel.revalidate();
        itemsPanel.repaint();
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}
