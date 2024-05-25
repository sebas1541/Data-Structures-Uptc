package co.edu.uptc.client.view.panels.family;

import co.edu.uptc.client.presenter.ClientPresenter;
import co.edu.uptc.client.dto.FamilyMemberData;
import co.edu.uptc.client.view.common.RoundedPanel;
import com.google.gson.Gson;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.HashMap;

public class ViewFamilyMembersPanel extends JPanel {
    private ClientPresenter presenter;
    private JPanel itemsPanel;
    private ButtonGroup memberGroup;
    private JButton backButton;
    private JLabel instructionLabel;
    private HashMap<JRadioButton, FamilyMemberData> memberButtons;

    public ViewFamilyMembersPanel(ClientPresenter presenter) {
        this.presenter = presenter;
        this.memberButtons = new HashMap<>();
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        JPanel leftPanel = new JPanel(new GridBagLayout());
        leftPanel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Title Label
        JLabel titleLabel = new JLabel("Miembros");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        leftPanel.add(createWrappedPanel(titleLabel, new Color(216, 230, 233)), gbc);

        // Back Button and Instruction Label
        backButton = new JButton("Atrás");
        backButton.addActionListener(e -> presenter.showTransactionView());
        instructionLabel = new JLabel("Seleccione un miembro para ver transacciones");
        instructionLabel.setFont(new Font("Arial", Font.BOLD, 14));
        instructionLabel.setForeground(Color.GRAY);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.setBackground(new Color(216, 230, 233));
        buttonPanel.add(backButton);
        buttonPanel.add(instructionLabel);

        gbc.gridy = 1;
        leftPanel.add(createWrappedPanel(buttonPanel, new Color(216, 230, 233)), gbc);

        add(leftPanel, BorderLayout.WEST);

        itemsPanel = new JPanel(new GridLayout(0, 1, 10, 10));
        itemsPanel.setBackground(Color.WHITE);
        memberGroup = new ButtonGroup();

        JScrollPane scrollPane = new JScrollPane(itemsPanel);
        scrollPane.setPreferredSize(new Dimension(300, getHeight()));
        add(scrollPane, BorderLayout.CENTER);
    }

    public void loadFamilyMembers() {
        itemsPanel.removeAll();
        memberButtons.clear();
        try {
            presenter.viewFamilyMembers(response -> {
                String members = response.getData();
                if (members.startsWith("[")) {
                    FamilyMemberData[] familyMemberDataArray = new Gson().fromJson(members, FamilyMemberData[].class);
                    for (FamilyMemberData familyMemberData : familyMemberDataArray) {
                        addFamilyMemberItem(familyMemberData);
                    }
                    itemsPanel.revalidate();
                    itemsPanel.repaint();
                } else {
                    showMessage("No hay miembros");
                }
            });
        } catch (IOException e) {
            showMessage("Error cargando miembros: " + e.getMessage());
        }
    }

    private void addFamilyMemberItem(FamilyMemberData familyMemberData) {
        JPanel itemPanel = new RoundedPanel(new GridBagLayout(), 15, new Color(216, 230, 233));
        itemPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

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
        memberGroup.add(memberButton);
        memberButtons.put(memberButton, familyMemberData);

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
        memberButtons.clear();
        FamilyMemberData[] familyMemberDataArray = new Gson().fromJson(members, FamilyMemberData[].class);
        for (FamilyMemberData familyMemberData : familyMemberDataArray) {
            addFamilyMemberItem(familyMemberData);
        }
        itemsPanel.revalidate();
        itemsPanel.repaint();
    }

    public String getSelectedMemberUsername() {
        for (JRadioButton button : memberButtons.keySet()) {
            if (button.isSelected()) {
                return memberButtons.get(button).getUsername();
            }
        }
        return null;
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}
