package co.edu.uptc.client.view.panels.family;

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
    private ViewMemberTransactionsPanel viewMemberTransactionsPanel;

    public FamilyGroupView(ClientPresenter presenter) {
        this.presenter = presenter;
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout());
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        // Initialize panels
        addFamilyMemberPanel = new AddFamilyMemberPanel(presenter);
        viewFamilyMembersPanel = new ViewFamilyMembersPanel(presenter);
        viewMemberTransactionsPanel = new ViewMemberTransactionsPanel(presenter);

        // Add panels to card layout
        cardPanel.add(addFamilyMemberPanel, "AddFamilyMemberPanel");
        cardPanel.add(viewFamilyMembersPanel, "ViewFamilyMembersPanel");
        cardPanel.add(viewMemberTransactionsPanel, "ViewMemberTransactionsPanel");

        add(cardPanel, BorderLayout.CENTER);

        // Navigation panel at the bottom
        JPanel navigationPanel = new JPanel();
        navigationPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
        navigationPanel.setBackground(new Color(216, 230, 233));

        // Buttons for panel navigation
        JButton addFamilyMemberButton = createButton("Agregar Miembro");
        addFamilyMemberButton.addActionListener(e -> showPanel("AddFamilyMemberPanel"));
        navigationPanel.add(addFamilyMemberButton);

        JButton viewFamilyMembersButton = createButton("Ver Miembros");
        viewFamilyMembersButton.addActionListener(e -> {
            try {
                presenter.viewFamilyMembers(response -> {
                    viewFamilyMembersPanel.loadFamilyMembers();
                    showPanel("ViewFamilyMembersPanel");
                });
            } catch (IOException ex) {
                showMessage("Error viendo los miemrbos: " + ex.getMessage());
            }
        });
        navigationPanel.add(viewFamilyMembersButton);

        JButton viewTransactionsButton = createButton("Ver Transacciones");
        viewTransactionsButton.addActionListener(e -> {
            String selectedMember = viewFamilyMembersPanel.getSelectedMemberUsername(); // Method to get selected member
            if (selectedMember != null) {
                viewMemberTransactionsPanel.loadTransactions(selectedMember);
                showPanel("ViewMemberTransactionsPanel");
            } else {
                showMessage("Seleccione un miembro para ver las transacciones");
            }
        });
        navigationPanel.add(viewTransactionsButton);

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

    public ViewMemberTransactionsPanel getViewMemberTransactionsPanel() {
        return viewMemberTransactionsPanel;
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}
