package co.edu.uptc.client.view.family;

import javax.swing.*;
import java.awt.*;
import co.edu.uptc.client.presenter.ClientPresenter;

public class FamilyGroupView extends JPanel {
    private ClientPresenter presenter;
    private JTextArea familyGroupArea;
    private JButton addMemberButton;
    private JButton viewMembersButton;
    private JButton backButton;

    public FamilyGroupView(ClientPresenter presenter) {
        this.presenter = presenter;
        initComponents();
    }

    private void initComponents() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel titleLabel = new JLabel("Family Group Management");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(titleLabel, gbc);

        familyGroupArea = new JTextArea(15, 30);
        familyGroupArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(familyGroupArea);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        add(scrollPane, gbc);

        addMemberButton = new JButton("Add Member");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        add(addMemberButton, gbc);

        viewMembersButton = new JButton("View Members");
        gbc.gridx = 1;
        gbc.gridy = 2;
        add(viewMembersButton, gbc);

        backButton = new JButton("Back");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        add(backButton, gbc);

        addMemberButton.addActionListener(e -> addMember());
        viewMembersButton.addActionListener(e -> viewMembers());
        backButton.addActionListener(e -> presenter.showTransactionView());
    }

    private void addMember() {
        String username = JOptionPane.showInputDialog(this, "Enter new member's username:");
        String email = JOptionPane.showInputDialog(this, "Enter new member's email:");
        if (username != null && email != null) {
            try {
                presenter.addFamilyMember(username, email);
            } catch (Exception e) {
                showMessage("Error: " + e.getMessage());
            }
        }
    }

    private void viewMembers() {
        try {
            presenter.viewFamilyMembers();
        } catch (Exception e) {
            showMessage("Error: " + e.getMessage());
        }
    }

    public void showFamilyMembers(String members) {
        familyGroupArea.setText(members);
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}
