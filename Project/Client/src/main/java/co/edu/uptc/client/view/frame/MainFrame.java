package co.edu.uptc.client.view.frame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import co.edu.uptc.client.presenter.ClientPresenter;
import co.edu.uptc.client.view.panels.budget.BudgetView;
import co.edu.uptc.client.view.panels.budget.EditBudgetPanel;
import co.edu.uptc.client.view.panels.export.ExportDataView;
import co.edu.uptc.client.view.panels.family.AddFamilyMemberPanel;
import co.edu.uptc.client.view.panels.family.FamilyGroupView;
import co.edu.uptc.client.view.panels.family.ViewFamilyMembersPanel;
import co.edu.uptc.client.view.common.LeftPanel;
import co.edu.uptc.client.view.common.TopPanel;
import co.edu.uptc.client.view.panels.login.LoginView;
import co.edu.uptc.client.view.panels.login.RegisterView;
import co.edu.uptc.client.view.panels.login.SelectServerView;
import co.edu.uptc.client.view.panels.transaction.TransactionView;

public class MainFrame extends JFrame {
    private JPanel mainPanel;
    private TopPanel topPanel;
    private LeftPanel leftPanel;
    private LoginView loginView;
    private RegisterView registerView;
    private TransactionView transactionView;
    private BudgetView budgetView;
    private FamilyGroupView familyGroupView;
    private ExportDataView exportDataView;
    private SelectServerView selectServerView;
    private ClientPresenter presenter;

    public MainFrame(ClientPresenter presenter) {
        this.presenter = presenter;

        setTitle("Administrador de Finanzas");
        setSize(1050, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        mainPanel = new JPanel(new CardLayout());

        loginView = new LoginView(presenter);
        registerView = new RegisterView(presenter);
        transactionView = new TransactionView(presenter);
        budgetView = new BudgetView(presenter);
        familyGroupView = new FamilyGroupView(presenter);
        exportDataView = new ExportDataView(presenter);
        selectServerView = new SelectServerView(presenter);

        mainPanel.add(selectServerView, "SelectServerView");
        mainPanel.add(loginView, "LoginView");
        mainPanel.add(registerView, "RegisterView");
        mainPanel.add(transactionView, "TransactionView");
        mainPanel.add(budgetView, "BudgetView");
        mainPanel.add(familyGroupView, "FamilyGroupView");
        mainPanel.add(exportDataView, "ExportDataView");

        topPanel = new TopPanel(e -> presenter.showLoginView());
        leftPanel = new LeftPanel(e -> handleLeftPanelButtonAction(e, presenter));

        setLayout(new BorderLayout());
        add(mainPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    public RegisterView getRegisterView() {
        return registerView;
    }

    public void showView(String viewName) {
        CardLayout cl = (CardLayout) (mainPanel.getLayout());
        System.out.println("Switching to view: " + viewName);

        if ("LoginView".equals(viewName) || "RegisterView".equals(viewName) || "SelectServerView".equals(viewName)) {
            removePanels();
        } else {
            addPanels();
        }

        if ("FamilyGroupView".equals(viewName)) {
            getFamilyGroupView().getViewFamilyMembersPanel().loadFamilyMembers();
        }

        cl.show(mainPanel, viewName);
    }

    private void addPanels() {
        if (getContentPane().getComponentCount() == 1) { // Only mainPanel is added
            add(topPanel, BorderLayout.NORTH);
            add(leftPanel, BorderLayout.WEST);
            revalidate();
            repaint();
        }
    }

    private void removePanels() {
        if (getContentPane().getComponentCount() > 1) { // More than mainPanel is added
            remove(topPanel);
            remove(leftPanel);
            revalidate();
            repaint();
        }
    }

    private void handleLeftPanelButtonAction(ActionEvent e, ClientPresenter presenter) {
        String command = ((JButton) e.getSource()).getText();
        switch (command) {
            case "Transacciones":
                presenter.showTransactionView();
                break;
            case "Presupuesto":
                presenter.showBudgetView();
                break;
            case "Familia":
                presenter.showFamilyGroupView();
                break;
            case "Reportes":
                presenter.showExportDataView();
                break;
        }
    }

    public void setUsername(String username) {
        topPanel.setUsername(username);
    }

    public LoginView getLoginView() {
        return loginView;
    }

    public TransactionView getTransactionView() {
        return transactionView;
    }

    public BudgetView getBudgetView() {
        return budgetView;
    }

    public EditBudgetPanel getEditBudgetPanel() {
        return budgetView.getEditBudgetPanel();
    }

    public FamilyGroupView getFamilyGroupView() {
        return familyGroupView;
    }

    public AddFamilyMemberPanel getAddFamilyMemberPanel() {
        return familyGroupView.getAddFamilyMemberPanel();
    }

    public ViewFamilyMembersPanel getViewFamilyMembersPanel() {
        return familyGroupView.getViewFamilyMembersPanel();
    }

    public ExportDataView getExportDataView() {
        return exportDataView;
    }
}
