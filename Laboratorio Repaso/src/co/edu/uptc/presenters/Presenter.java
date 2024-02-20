package co.edu.uptc.presenters;
import co.edu.uptc.models.RetailStoreManager;
import co.edu.uptc.views.View;

public class Presenter {
    private RetailStoreManager retailStoreManager;
    private View view;

    public Presenter() {
        this.retailStoreManager = new RetailStoreManager();
        this.view = new View();
    }


    public void run() {
        mainMenu();
    }

    public void mainMenu() {
        int option = 0;
        while (option != 3) {
            option = showMenu();
            switch (option) {
                case 1:
                    //
                    break;

                case 2:
                    end();
                    break;
            }
        }
    }

    

    public int showMenu() {
        int option = view.readInt("Main Menu\n1. X\n2. Y");
        return option;
    }


    public void end() {
        System.exit(0);
    }
}
