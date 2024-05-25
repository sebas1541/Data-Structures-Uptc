package co.edu.uptc.client.view.panels.budget;

import co.edu.uptc.client.presenter.ClientPresenter;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class BudgetView extends JPanel {
    private ClientPresenter presenter;
    private JTextArea budgetArea;
    private JButton addBudgetButton;
    private JButton editBudgetButton;
    private JButton deleteBudgetButton;
    private JPanel cardPanel;
    private CardLayout cardLayout;
    private AddBudgetPanel addBudgetPanel;
    private EditBudgetPanel editBudgetPanel;
    private DeleteBudgetPanel deleteBudgetPanel;

    public BudgetView(ClientPresenter presenter) {
        this.presenter = presenter;
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout());
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        addBudgetPanel = new AddBudgetPanel(presenter);
        editBudgetPanel = new EditBudgetPanel(presenter);
        deleteBudgetPanel = new DeleteBudgetPanel(presenter);

        cardPanel.add(addBudgetPanel, "AddBudgetPanel");
        cardPanel.add(editBudgetPanel, "EditBudgetPanel");
        cardPanel.add(deleteBudgetPanel, "DeleteBudgetPanel");

        add(cardPanel, BorderLayout.CENTER);

        JPanel navigationPanel = new JPanel();
        navigationPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
        navigationPanel.setBackground(new Color(216, 230, 233));

        JButton addBudgetButton = createButton("Agregar Presupuesto");
        addBudgetButton.addActionListener(e -> showPanel("AddBudgetPanel"));
        navigationPanel.add(addBudgetButton);

        JButton editBudgetButton = createButton("Editar Presupuesto");
        editBudgetButton.addActionListener(e -> {
            try {
                presenter.viewBudgets(response -> {
                    editBudgetPanel.loadBudgets();
                    showPanel("EditBudgetPanel");
                });
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        navigationPanel.add(editBudgetButton);

        JButton deleteBudgetButton = createButton("Eliminar Presupuesto");
        deleteBudgetButton.addActionListener(e -> {
            try {
                presenter.viewBudgets(response -> {
                    deleteBudgetPanel.loadBudgets();
                    showPanel("DeleteBudgetPanel");
                });
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        navigationPanel.add(deleteBudgetButton);

        add(navigationPanel, BorderLayout.SOUTH);
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(150, 50));
        return button;
    }

    private void showPanel(String panelName) {
        cardLayout.show(cardPanel, panelName);
    }

    public EditBudgetPanel getEditBudgetPanel() {
        return editBudgetPanel;
    }

    public void showBudgets(String budgets) {
        editBudgetPanel.loadBudgets(); // Refresh the budget list in EditBudgetPanel
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}
