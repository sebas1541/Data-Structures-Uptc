package co.edu.uptc.client.view;

import co.edu.uptc.client.net.Request;
import co.edu.uptc.client.presenter.ClientPresenter;


import java.io.IOException;
import java.util.Scanner;

public class ExportDataView {
    private ClientPresenter presenter;

    public ExportDataView(ClientPresenter presenter) {
        this.presenter = presenter;
    }

    public void display() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Export Data Menu:\n1. Export to TXT\n2. Export to EXCEL\n3. Export to PDF\n4. Back");
        String option = scanner.nextLine();

        try {
            switch (option) {
                case "1":
                    exportData("txt");
                    break;
                case "2":
                    exportData("excel");
                    break;
                case "3":
                    exportData("pdf");
                    break;
                case "4":
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

    private void exportData(String format) throws IOException {
        Request request = new Request("exportData", format);
        presenter.getConnection().sendRequest(request);
        presenter.getConnection().receiveResponse();
        display();
    }

    public void showMessage(String message) {
        System.out.println(message);
    }
}
