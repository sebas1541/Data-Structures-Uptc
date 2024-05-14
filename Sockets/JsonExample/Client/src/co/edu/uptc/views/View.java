package co.edu.uptc.views;

import java.util.Scanner;

public class View {
    private Scanner console = new Scanner(System.in);

    public int readInt(String message) {
        System.out.println(message);
        while (true) {
            try {
                return Integer.parseInt(console.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }

    public String readString(String message) {
        System.out.println(message);
        return console.nextLine();
    }

    public void showMessage(String message) {
        System.out.println(message);
    }
}
