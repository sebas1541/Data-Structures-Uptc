package co.edu.uptc.client.view;

import co.edu.uptc.client.net.FamilyMemberData;
import co.edu.uptc.client.net.Request;
import co.edu.uptc.client.presenter.ClientPresenter;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.Scanner;

public class FamilyGroupView {
    private ClientPresenter presenter;
    private Gson gson;

    public FamilyGroupView(ClientPresenter presenter) {
        this.presenter = presenter;
        this.gson = new Gson();
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
        System.out.print("Enter member details (format: username,email): ");
        String memberDetails = scanner.nextLine();
        String[] details = memberDetails.split(",");
        if (details.length == 2) {
            FamilyMemberData familyMemberData = new FamilyMemberData(
                    presenter.getLoginView().getCurrentUser().getUsername(), // Parent user ID
                    details[0], // New member username
                    details[1]  // New member email
            );
            String familyMemberDataJson = gson.toJson(familyMemberData);
            Request request = new Request("addMember", familyMemberDataJson);
            presenter.getConnection().sendRequest(request);
            presenter.getConnection().receiveResponse();
        } else {
            showMessage("Invalid input format. Please try again.");
        }
        display();
    }

    private void viewMembers() throws IOException {
        Request request = new Request("viewMembers", presenter.getLoginView().getCurrentUser().getUsername());
        presenter.getConnection().sendRequest(request);
        presenter.getConnection().receiveResponse();
        display();
    }

    public void showMessage(String message) {
        System.out.println(message);
    }
}
