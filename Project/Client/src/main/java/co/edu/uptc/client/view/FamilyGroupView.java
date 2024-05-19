package co.edu.uptc.client.view;

import co.edu.uptc.client.net.Request;
import co.edu.uptc.client.presenter.ClientPresenter;

import java.io.IOException;
import java.util.Scanner;

public class FamilyGroupView {
    private ClientPresenter presenter;

    public FamilyGroupView(ClientPresenter presenter) {
        this.presenter = presenter;
    }

    public void display() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Family Group Menu:\n1. Add Member\n2. View Members\n3. Back");
        String option = scanner.nextLine();

        try {
            switch (option) {
                case "1":
                    addMember();
                    break;
                case "2":
                    viewMembers();
                    break;
                case "3":
                    presenter.getLoginView().display();
                    break;
                default:
                    showMessage("Invalid option. Try again.");
                    display();
                    break;
            }
        } catch (IOException e) {
            showMessage("Error: " + e.getMessage());
        }
    }

    private void addMember() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter member details (format: id,username,password,email): ");
        String memberDetails = scanner.nextLine();
        Request request = new Request("addMember", memberDetails);
        presenter.getConnection().sendRequest(request);
        presenter.getConnection().receiveResponse();
        display();
    }

    private void viewMembers() throws IOException {
        Request request = new Request("viewMembers", "");
        presenter.getConnection().sendRequest(request);
        presenter.getConnection().receiveResponse();
        display();
    }

    public void showMessage(String message) {
        System.out.println(message);
    }
}
