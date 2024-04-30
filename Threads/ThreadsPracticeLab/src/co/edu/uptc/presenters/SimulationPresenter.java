package co.edu.uptc.presenters;

import co.edu.uptc.models.Cashier;
import co.edu.uptc.models.Customer;
import co.edu.uptc.models.QueueFeeder;
import co.edu.uptc.structures.MyQueue;
import co.edu.uptc.views.View;

public class SimulationPresenter {
    private View view;

    public SimulationPresenter() {
        this.view = new View();
    }

    public void run() {
        mainMenu();
    }

    private void mainMenu() {
        int option;
        do {
            view.showMessage("Menú Principal\n1. Ejecutar Simulación\n2. Salir");
            option = view.readInt("Elige una opción: ");
            switch (option) {
                case 1:
                    runSimulation();
                    break;
                case 2:
                    view.showMessage("Saliendo del programa");
                    break;
                default:
                    view.showMessage("Por favor, inténtalo de nuevo");
            }
        } while (option != 2);
    }

    private void runSimulation() {
        int numCashiers = view.readInt("Ingrese el número de cajeros: ");
        int simulationDuration = view.readInt("Cuántos minutos quiere que dure la simulación?");

        MyQueue<Customer> queue = new MyQueue<>();
        QueueFeeder feeder = new QueueFeeder(queue, simulationDuration); // Ajuste QueueFeeder para aceptar la duración
        feeder.start();

        Cashier[] cashiers = new Cashier[numCashiers];
        for (int i = 0; i < numCashiers; i++) {
            cashiers[i] = new Cashier(queue);
            cashiers[i].start();
        }

        try {
            feeder.join(); // Espera a que el alimentador termine de agregar clientes
            Thread.sleep(100); // Pequeña demora para asegurar que todos los clientes sean procesados
            for (Cashier cashier : cashiers) {
                cashier.interrupt();
                cashier.join(); // Asegura que todos los hilos de los cajeros se cierren correctamente
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        view.showMessage("Número de clientes atendidos: " + Cashier.getTotalServed());
        Cashier.resetTotalServed(); // Reinicia para la siguiente simulación
    }
}