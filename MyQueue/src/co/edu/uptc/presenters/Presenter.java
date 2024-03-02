package co.edu.uptc.presenters;


import co.edu.uptc.models.UserManager;
import co.edu.uptc.models.User;
import co.edu.uptc.models.TurnManager;
import co.edu.uptc.views.View;

public class Presenter {
    private TurnManager turnManager;
    private View view;
    private UserManager userManager;

    public Presenter() {
        this.turnManager = new TurnManager();
        this.view = new View();
        this.userManager = new UserManager();
    }

    public void createTurn(){

        int age = view.readInt("Ingrese la edad del usuario");
        String name = view.readString("Ingrese el nombre del usuario");
        String turnId = "";

        boolean pregnant = false;
        String isPregnant = view.readString("Está embarazada: 1. si / 2. no");

        if (isPregnant.equals("1")) pregnant = true;

        turnManager.addUser(new User(age, name, turnId, pregnant));
    }


    public void run() {
        mainMenu();
    }

    private void mainMenu() {
        int option;
        do {
            view.showMessage("Menú Principal\n1. Crear turno\n2. Ver Siguiente Turno\n3. Salir");
            option = view.readInt("Elige una opción: ");
            switch (option) {
                case 1:
                    createTurn();
                    break;
                case 2:
                    getNextUser();
                    break;
                case 3:
                    view.showMessage("Saliendo del programa");
                    break;
                default:
                    view.showMessage("Intente de nuevo");
            }
        } while (option != 3);
    }



    private void getNextUser(){
        User user = turnManager.getNextUser();
        view.showMessage("Siguiente usuario:\n" + user.getName() + " " + user.getTurnID());
    }
}