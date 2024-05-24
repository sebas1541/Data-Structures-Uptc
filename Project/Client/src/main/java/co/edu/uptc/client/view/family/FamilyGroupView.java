package co.edu.uptc.client.view.family;

import co.edu.uptc.client.presenter.ClientPresenter;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class FamilyGroupView extends JPanel {
    private ClientPresenter presenter;
    private CardLayout cardLayout;
    private JPanel cardPanel;
    private AddFamilyMemberPanel addFamilyMemberPanel;
    private ViewFamilyMembersPanel viewFamilyMembersPanel;

    public FamilyGroupView(ClientPresenter presenter) {
        this.presenter = presenter;
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout());
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        addFamilyMemberPanel = new AddFamilyMemberPanel(presenter);
        viewFamilyMembersPanel = new ViewFamilyMembersPanel(presenter);

        cardPanel.add(addFamilyMemberPanel, "AddFamilyMemberPanel");
        cardPanel.add(viewFamilyMembersPanel, "ViewFamilyMembersPanel");

        add(cardPanel, BorderLayout.CENTER);

        JPanel navigationPanel = new JPanel();
        navigationPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
        navigationPanel.setBackground(new Color(216, 230, 233));

        JButton addFamilyMemberButton = createButton("Add Member");
        addFamilyMemberButton.addActionListener(e -> showPanel("AddFamilyMemberPanel"));
        navigationPanel.add(addFamilyMemberButton);

        JButton viewFamilyMembersButton = createButton("View Members");
        viewFamilyMembersButton.addActionListener(e -> {
            try {
                presenter.viewFamilyMembers(response -> {
                    viewFamilyMembersPanel.loadFamilyMembers();
                    showPanel("ViewFamilyMembersPanel");
                });
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        navigationPanel.add(viewFamilyMembersButton);

        add(navigationPanel, BorderLayout.SOUTH);
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(150, 50));
        return button;
    }

    public void showPanel(String panelName) {
        cardLayout.show(cardPanel, panelName);
    }

    public AddFamilyMemberPanel getAddFamilyMemberPanel() {
        return addFamilyMemberPanel;
    }

    public ViewFamilyMembersPanel getViewFamilyMembersPanel() {
        return viewFamilyMembersPanel;
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}
